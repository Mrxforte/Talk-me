package com.example.feature.speaking

import com.example.core.audio.TtsManager
import com.example.core.speech.VadState
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.SpeechRecognitionResult
import com.example.domain.repository.SpeechRecognitionState
import com.example.domain.repository.SpeechRepository
import java.io.IOException
import java.util.UUID
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicReference
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * Production ConversationCoordinator orchestrating the backend-free, local offline STT +
 * context-aware Gemini AI Tutor + Android TTS turn-taking pipeline:
 *
 * Architecture:
 * `Android AudioRecord → Local Silero VAD → Local Sherpa-ONNX STT → Firebase Gemini AI → Android TextToSpeech → Automatic next listening turn`
 *
 * State Machine (Section 9):
 * `IDLE → LISTENING → USER_SPEAKING → FINALIZING → AI_THINKING → AI_SPEAKING → LISTENING`
 * (plus `STOPPED` and `ERROR`)
 *
 * Key Guarantees:
 * - One tap starts automatic hands-free turn-taking; no second microphone tap is required.
 * - Enforces `rawTranscript == displayedUserMessage == geminiStudentUtterance` (Section 3).
 * - Never invents user speech on silence or STT no-match (Tests 6 & 7).
 * - Never injects fabricated AI responses when Gemini fails; preserves the user transcript and offers safe retry (Test 10).
 * - Prevents stale STT/Gemini/TTS callbacks from restarting the microphone after `stopConversation()` or screen exit (Test 9).
 */
class ConversationCoordinator(
    private val scope: CoroutineScope,
    private val speechRepository: SpeechRepository,
    private val aiLearningService: AiLearningService,
    private val ttsManager: TtsManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val maxGeminiRetryAttempts: Int = 0,
    private val baseBackoffDelayMs: Long = 250L
) {
    private val _uiState = MutableStateFlow(ConversationUiState())
    val uiState: StateFlow<ConversationUiState> = _uiState.asStateFlow()

    private val turnMutex = Mutex()
    private val activeTurnId = AtomicReference<String?>(null)
    private val listeningTurnId = AtomicReference<String?>(null)

    private var activeGeminiJob: Job? = null
    private var activeTtsJob: Job? = null

    private var allowedVocabulary: List<String> = emptyList()
    private var allowedGrammar: List<String> = emptyList()
    private val sessionScores = mutableListOf<Int>()

    private var observeStreamsJob: Job? = null

    init {
        observeSpeechRepositoryStreams()
    }

    private fun observeSpeechRepositoryStreams() {
        observeStreamsJob?.cancel()
        observeStreamsJob = scope.launch {
            launch {
                speechRepository.audioAmplitude.collect { rms ->
                    _uiState.update { it.copy(audioAmplitude = rms) }
                }
            }
            launch {
                speechRepository.isMuted.collect { muted ->
                    _uiState.update { it.copy(isMuted = muted) }
                }
            }
            launch {
                speechRepository.speechState.collect { sttState ->
                    val isCapturing = sttState == SpeechRecognitionState.LISTENING_PASS_1
                    _uiState.update { current ->
                        val updatedConvState = if (
                            sttState == SpeechRecognitionState.PROCESSING_PASS_2 &&
                            (current.conversationState is ConversationState.LISTENING ||
                                current.conversationState is ConversationState.USER_SPEAKING)
                        ) {
                            val currentTurn = listeningTurnId.get() ?: UUID.randomUUID().toString()
                            TalkMeConversationLogger.logFinalizingStarted(currentTurn)
                            ConversationState.FINALIZING(currentTurn)
                        } else {
                            current.conversationState
                        }
                        current.copy(
                            isAudioCaptureActuallyRunning = isCapturing,
                            conversationState = updatedConvState,
                            statusMessage = if (updatedConvState is ConversationState.FINALIZING) {
                                "⏳ Nutq yakunlandi, matn aniqlanmoqda..."
                            } else {
                                current.statusMessage
                            }
                        )
                    }
                }
            }
            launch {
                speechRepository.vadState.collect { vad ->
                    val currentListeningTurn = listeningTurnId.get() ?: return@collect
                    val current = _uiState.value
                    if (!current.isHandsFreeConversationActive &&
                        current.conversationState !is ConversationState.LISTENING &&
                        current.conversationState !is ConversationState.USER_SPEAKING
                    ) {
                        return@collect
                    }

                    when (vad) {
                        VadState.SPEECH_BEGIN -> {
                            TalkMeConversationLogger.logSpeechStarted(currentListeningTurn)
                            _uiState.update { state ->
                                state.copy(
                                    conversationState = ConversationState.USER_SPEAKING(
                                        turnId = currentListeningTurn,
                                        provisionalTranscript = state.provisionalTranscript
                                    ),
                                    statusMessage = "🎙️ Eshitilmoqda..."
                                )
                            }
                        }
                        VadState.ACTIVE_SPEECH, VadState.PAUSE -> {
                            if (current.conversationState is ConversationState.LISTENING) {
                                _uiState.update { state ->
                                    state.copy(
                                        conversationState = ConversationState.USER_SPEAKING(
                                            turnId = currentListeningTurn,
                                            provisionalTranscript = state.provisionalTranscript
                                        ),
                                        statusMessage = "🎙️ Eshitilmoqda..."
                                    )
                                }
                            }
                        }
                        VadState.SPEECH_END -> {
                            TalkMeConversationLogger.logFinalizingStarted(currentListeningTurn)
                            _uiState.update { state ->
                                state.copy(
                                    conversationState = ConversationState.FINALIZING(currentListeningTurn),
                                    statusMessage = "⏳ Nutq yakunlandi, matn aniqlanmoqda..."
                                )
                            }
                        }
                        VadState.SILENCE -> Unit
                    }
                }
            }
            launch {
                speechRepository.interimText.collect { partial ->
                    if (partial.isBlank()) return@collect
                    val currentListeningTurn = listeningTurnId.get() ?: return@collect
                    val current = _uiState.value
                    if (current.conversationState is ConversationState.LISTENING ||
                        current.conversationState is ConversationState.USER_SPEAKING
                    ) {
                        TalkMeConversationLogger.logPartialTranscript(currentListeningTurn, partial.length)
                        _uiState.update { state ->
                            state.copy(
                                conversationState = ConversationState.USER_SPEAKING(
                                    turnId = currentListeningTurn,
                                    provisionalTranscript = partial
                                ),
                                provisionalTranscript = partial
                            )
                        }
                    }
                }
            }
            launch {
                speechRepository.utteranceFinalizedEvents.collect { result ->
                    handleFinalizedSttResult(result)
                }
            }
        }
    }

    private suspend fun handleFinalizedSttResult(result: SpeechRecognitionResult) {
        val finishedListeningTurn = listeningTurnId.getAndSet(null) ?: return

        // Ignore stale STT callback if user already stopped the conversation
        if (_uiState.value.conversationState is ConversationState.STOPPED) {
            return
        }

        if (result.sttErrorReason != null) {
            val sttErr = ConversationError(
                code = ConversationError.ErrorCode.STT_ERROR,
                userMessageUzbek = result.sttErrorReason,
                turnId = finishedListeningTurn,
                isRecoverable = true
            )
            _uiState.update {
                it.copy(
                    conversationState = ConversationState.ERROR(sttErr),
                    isHandsFreeConversationActive = false,
                    isAudioCaptureActuallyRunning = false,
                    provisionalTranscript = "",
                    lastError = sttErr,
                    statusMessage = sttErr.userMessageUzbek
                )
            }
            return
        }

        val exactRawTranscript = result.recognizedText.trim()
        if (exactRawTranscript.isEmpty()) {
            // Tests 6 & 7: User said nothing or STT returned no match ->
            // Do NOT create any user message, do NOT invent text, and do NOT call Gemini.
            _uiState.update {
                it.copy(
                    provisionalTranscript = "",
                    isAudioCaptureActuallyRunning = false,
                    statusMessage = "Ovoz aniqlanmadi. Ingliz tilida qayta gapiring..."
                )
            }
            if (_uiState.value.isHandsFreeConversationActive) {
                startNextListeningTurn()
            } else {
                _uiState.update { it.copy(conversationState = ConversationState.IDLE) }
            }
            return
        }

        TalkMeConversationLogger.logFinalTranscript(finishedListeningTurn, exactRawTranscript.length)

        val verifiedTurn = VerifiedUserTurn(
            turnId = finishedListeningTurn,
            rawTranscript = exactRawTranscript
        )
        submitVerifiedTurnToGemini(
            verifiedTurn = verifiedTurn,
            isRetryOfExistingMessage = false
        )
    }

    fun configureLessonContext(
        topicId: String,
        topicTitle: String,
        learnerLevel: String = "A0_A1",
        vocabulary: List<String>,
        grammarSummaries: List<String>,
        welcomeMessage: ConversationMessage? = null,
        speakWelcome: Boolean = true
    ) {
        allowedVocabulary = vocabulary
        allowedGrammar = grammarSummaries

        _uiState.update { current ->
            val initialMessages = if (welcomeMessage != null && current.messages.isEmpty()) {
                listOf(welcomeMessage)
            } else {
                current.messages
            }
            current.copy(
                topicId = topicId,
                topicTitle = topicTitle,
                learnerLevel = learnerLevel,
                messages = initialMessages,
                conversationState = ConversationState.IDLE,
                statusMessage = "Suhbatni boshlash uchun mikrofonni bir marta bosing"
            )
        }

        if (welcomeMessage != null && speakWelcome) {
            speakAssistantResponseAndResumeIfActive(
                turnId = welcomeMessage.turnId,
                spokenText = welcomeMessage.text,
                autoResumeListeningAfterTts = false
            )
        }
    }

    fun setMicPermissionGranted(granted: Boolean) {
        _uiState.update { current ->
            val clearedError = if (granted && current.lastError?.code == ConversationError.ErrorCode.MIC_PERMISSION_REQUIRED) {
                null
            } else {
                current.lastError
            }
            current.copy(
                isMicPermissionGranted = granted,
                lastError = clearedError,
                statusMessage = if (granted) {
                    "Suhbatni boshlash uchun mikrofonni bir marta bosing"
                } else {
                    "Speaking AI uchun mikrofon ruxsati talab qilinadi"
                }
            )
        }
    }

    fun setGrammarFeedbackEnabled(enabled: Boolean) {
        _uiState.update { it.copy(isGrammarFeedbackEnabled = enabled) }
    }

    fun setKeyboardInputVisible(visible: Boolean) {
        _uiState.update { it.copy(isKeyboardInputVisible = visible) }
    }

    fun onKeyboardDraftChanged(newText: String) {
        _uiState.update {
            it.copy(
                keyboardDraftText = newText,
                lastError = if (newText.isNotBlank()) null else it.lastError
            )
        }
    }

    /**
     * Submits a keyboard message as an optional manual alternative without modifying the user's text.
     */
    fun submitKeyboardDraft(explicitText: String? = null): Boolean {
        val cleanText = (explicitText ?: _uiState.value.keyboardDraftText).trim()
        if (cleanText.isEmpty() || _uiState.value.isLoading) return false

        _uiState.update { it.copy(keyboardDraftText = "") }

        val verifiedTurn = VerifiedUserTurn(
            rawTranscript = cleanText
        )
        return submitVerifiedTurnToGemini(
            verifiedTurn = verifiedTurn,
            isRetryOfExistingMessage = false
        )
    }

    /**
     * ONE TAP starts automatic hands-free conversation (`IDLE -> LISTENING`).
     * If the user is currently speaking in `USER_SPEAKING`, tapping finalizes the current utterance immediately.
     * Otherwise tapping while idle in `LISTENING` stops the conversation (`-> STOPPED`).
     */
    fun onMicrophoneButtonTapped() {
        val current = _uiState.value
        if (!current.isMicPermissionGranted || !speechRepository.hasRecordPermission()) {
            val err = ConversationError(
                code = ConversationError.ErrorCode.MIC_PERMISSION_REQUIRED,
                userMessageUzbek = "Ovozli suhbat uchun mikrofon ruxsatini bering."
            )
            _uiState.update {
                it.copy(
                    conversationState = ConversationState.ERROR(err),
                    lastError = err,
                    statusMessage = err.userMessageUzbek
                )
            }
            return
        }

        when (current.conversationState) {
            is ConversationState.USER_SPEAKING -> {
                // Allow optional early finalization if user taps mic while actively speaking
                finalizeCurrentListeningTurnNow()
            }
            is ConversationState.LISTENING,
            is ConversationState.FINALIZING,
            is ConversationState.AI_THINKING,
            is ConversationState.AI_SPEAKING -> {
                stopConversation()
            }
            is ConversationState.IDLE,
            is ConversationState.STOPPED,
            is ConversationState.ERROR -> {
                startConversation()
            }
        }
    }

    /**
     * Starts automatic hands-free conversation (`IDLE -> LISTENING`).
     */
    fun startConversation() {
        if (!_uiState.value.isMicPermissionGranted || !speechRepository.hasRecordPermission()) {
            val err = ConversationError(
                code = ConversationError.ErrorCode.MIC_PERMISSION_REQUIRED,
                userMessageUzbek = "Ovozli suhbat uchun mikrofon ruxsatini bering."
            )
            _uiState.update {
                it.copy(
                    conversationState = ConversationState.ERROR(err),
                    lastError = err,
                    statusMessage = err.userMessageUzbek
                )
            }
            return
        }

        if (_uiState.value.isLoading) return

        speechRepository.setMuted(false)

        // Stop any playing TTS before opening microphone so AI voice is never recorded
        activeTtsJob?.cancel()
        activeTtsJob = null
        ttsManager.stop()

        _uiState.update {
            it.copy(
                isHandsFreeConversationActive = true,
                provisionalTranscript = "",
                lastError = null
            )
        }

        startNextListeningTurn()
    }

    private fun startNextListeningTurn() {
        // Ensure TTS is completely stopped before opening the microphone
        ttsManager.stop()
        speechRepository.cancelListening()

        val turnId = "turn_${UUID.randomUUID()}"
        listeningTurnId.set(turnId)
        TalkMeConversationLogger.logListeningStarted(turnId)

        val started = speechRepository.startListening()
        if (!started) {
            listeningTurnId.compareAndSet(turnId, null)
            val err = ConversationError(
                code = ConversationError.ErrorCode.AUDIO_CAPTURE_UNSUPPORTED,
                userMessageUzbek = "Mikrofon audio yozuvini ishga tushirib bo'lmadi.",
                isRecoverable = true
            )
            _uiState.update {
                it.copy(
                    conversationState = ConversationState.ERROR(err),
                    isHandsFreeConversationActive = false,
                    isAudioCaptureActuallyRunning = false,
                    lastError = err,
                    statusMessage = err.userMessageUzbek
                )
            }
            return
        }

        _uiState.update { current ->
            current.copy(
                conversationState = ConversationState.LISTENING(turnId),
                isAudioCaptureActuallyRunning = true,
                provisionalTranscript = "",
                lastError = null,
                statusMessage = if (current.isMuted) {
                    "🔇 Mikrofon ovozsiz rejimda (Mute)"
                } else {
                    "🎙️ Ingliz tilida gapiring... (Avtomatik tinglanadi)"
                }
            )
        }
    }

    /**
     * Explicitly finalizes the current listening turn via [SpeechRepository.stopListening].
     */
    fun finalizeCurrentListeningTurnNow() {
        val currentTurn = listeningTurnId.get() ?: return
        scope.launch {
            _uiState.update {
                it.copy(
                    conversationState = ConversationState.FINALIZING(currentTurn),
                    statusMessage = "⏳ Nutq yakunlandi, matn aniqlanmoqda..."
                )
            }
            val result = speechRepository.stopListening()
            handleFinalizedSttResult(result)
        }
    }

    /**
     * Stops automatic hands-free turn-taking immediately, cancels STT/Gemini/TTS jobs,
     * and prevents any stale callback from restarting the microphone.
     */
    fun stopConversation() {
        val startCancel = System.currentTimeMillis()
        activeTurnId.set(null)
        listeningTurnId.set(null)
        activeGeminiJob?.cancel()
        activeGeminiJob = null
        activeTtsJob?.cancel()
        activeTtsJob = null

        speechRepository.cancelListening()
        ttsManager.stop()

        val cancelLatency = (System.currentTimeMillis() - startCancel).coerceAtLeast(0L)

        _uiState.update { current ->
            current.copy(
                conversationState = ConversationState.STOPPED,
                isHandsFreeConversationActive = false,
                isAudioCaptureActuallyRunning = false,
                provisionalTranscript = "",
                audioAmplitude = 0f,
                statusMessage = "Suhbat to'xtatildi. Boshlash uchun mikrofonni bir marta bosing",
                latencyMetrics = current.latencyMetrics.copy(
                    lastCancellationLatencyMs = cancelLatency
                )
            )
        }
    }

    fun toggleMute() {
        val nextMuted = !_uiState.value.isMuted
        speechRepository.setMuted(nextMuted)
        _uiState.update { current ->
            current.copy(
                isMuted = nextMuted,
                statusMessage = if (nextMuted) {
                    "🔇 Mikrofon ovozsiz rejimda (Mute)"
                } else if (current.isListeningOrSpeakingUser) {
                    "🎙️ Ingliz tilida gapiring..."
                } else {
                    current.statusMessage
                }
            )
        }
    }

    /**
     * Cancels the currently active AI thinking request or skips TTS playback,
     * resuming listening if hands-free mode is active.
     */
    fun cancelCurrentTurnOrSpeech() {
        val cancelStart = System.currentTimeMillis()
        activeTurnId.set(null)
        activeGeminiJob?.cancel()
        activeGeminiJob = null
        activeTtsJob?.cancel()
        activeTtsJob = null
        ttsManager.stop()

        val cancelLatency = (System.currentTimeMillis() - cancelStart).coerceAtLeast(0L)
        _uiState.update { current ->
            current.copy(
                lastError = null,
                latencyMetrics = current.latencyMetrics.copy(
                    lastCancellationLatencyMs = cancelLatency
                )
            )
        }

        if (_uiState.value.isHandsFreeConversationActive) {
            startNextListeningTurn()
        } else {
            _uiState.update {
                it.copy(
                    conversationState = ConversationState.IDLE,
                    statusMessage = "Suhbatni boshlash uchun mikrofonni bir marta bosing"
                )
            }
        }
    }

    /**
     * Submits a verified user turn (`rawTranscript == displayedUserMessage == geminiStudentUtterance`)
     * to Gemini AI Tutor (`FINALIZING -> AI_THINKING`).
     */
    fun submitVerifiedTurnToGemini(
        verifiedTurn: VerifiedUserTurn,
        isRetryOfExistingMessage: Boolean = false
    ): Boolean {
        val exactText = verifiedTurn.rawTranscript.trim()
        if (exactText.isEmpty()) return false

        val sttReadyTimestampMs = System.currentTimeMillis()

        scope.launch {
            val accepted = turnMutex.withLock {
                if (_uiState.value.conversationState is ConversationState.AI_THINKING) {
                    false
                } else {
                    listeningTurnId.set(null)
                    speechRepository.cancelListening()
                    ttsManager.stop()
                    activeTtsJob?.cancel()

                    activeTurnId.set(verifiedTurn.turnId)

                    _uiState.update { current ->
                        val updatedMessages = if (!isRetryOfExistingMessage) {
                            val userMsg = ConversationMessage(
                                turnId = verifiedTurn.turnId,
                                role = ConversationRole.USER,
                                text = verifiedTurn.displayedUserMessage
                            )
                            (current.messages + userMsg)
                                .takeLast(TutorPromptConfig.MAX_TRANSCRIPT_MESSAGES_IN_MEMORY)
                        } else {
                            current.messages
                        }
                        current.copy(
                            conversationState = ConversationState.AI_THINKING(
                                turnId = verifiedTurn.turnId,
                                rawTranscript = verifiedTurn.rawTranscript
                            ),
                            isAudioCaptureActuallyRunning = false,
                            provisionalTranscript = "",
                            audioAmplitude = 0f,
                            messages = updatedMessages,
                            lastError = null,
                            statusMessage = "✨ AI Tutor javob tayyorlamoqda...",
                            latencyMetrics = current.latencyMetrics.copy(
                                turnId = verifiedTurn.turnId
                            )
                        )
                    }
                    true
                }
            }

            if (!accepted) return@launch

            executeGeminiRequest(
                verifiedTurn = verifiedTurn,
                sttReadyTimestampMs = sttReadyTimestampMs
            )
        }
        return true
    }

    private fun executeGeminiRequest(
        verifiedTurn: VerifiedUserTurn,
        sttReadyTimestampMs: Long
    ) {
        val turnId = verifiedTurn.turnId
        activeGeminiJob = scope.launch {
            try {
                // Exclude the just-added latest USER message from history so it is never duplicated
                val priorMessages = _uiState.value.messages.dropLastWhile {
                    it.turnId == turnId && it.role == ConversationRole.USER
                }
                val historyPairs = TutorPromptConfig.buildRoleHistoryPairs(priorMessages)
                val currentTopicTitle = _uiState.value.topicTitle
                val currentLearnerLevel = _uiState.value.learnerLevel

                var attempt = 0
                var lastFailure: Throwable? = null
                var aiResponseResult: Result<com.example.domain.repository.SpeakingAiResponse>? = null

                TalkMeConversationLogger.logGeminiRequestStarted(turnId)

                while (attempt <= maxGeminiRetryAttempts) {
                    if (activeTurnId.get() != turnId) return@launch
                    if (attempt > 0) {
                        val backoffMs = baseBackoffDelayMs * (1L shl (attempt - 1))
                        delay(backoffMs)
                    }

                    val callResult = withContext(ioDispatcher) {
                        aiLearningService.sendSpeakingTurn(
                            topicTitle = currentTopicTitle,
                            allowedVocabulary = allowedVocabulary,
                            allowedGrammarSummaries = allowedGrammar,
                            conversationHistory = historyPairs,
                            studentUtterance = verifiedTurn.geminiStudentUtterance,
                            audioWavBytes = null,
                            learnerLevel = currentLearnerLevel
                        )
                    }

                    if (callResult.isSuccess) {
                        aiResponseResult = callResult
                        break
                    } else {
                        lastFailure = callResult.exceptionOrNull()
                        if (lastFailure is CancellationException) throw lastFailure
                        attempt++
                    }
                }

                // Stale turn guard
                if (activeTurnId.get() != turnId) return@launch

                val finalResult = aiResponseResult ?: Result.failure(
                    lastFailure ?: IllegalStateException("AI request failed")
                )

                finalResult.fold(
                    onSuccess = { aiReply ->
                        val responseReadyMs = System.currentTimeMillis()
                        val aiLatencyMs = (responseReadyMs - sttReadyTimestampMs).coerceAtLeast(0L)
                        TalkMeConversationLogger.logGeminiResponseReceived(turnId, aiLatencyMs)

                        val assistantText = aiReply.spokenEnglishReply.trim()
                        if (assistantText.isEmpty()) {
                            handleGeminiError(
                                turnId = turnId,
                                error = ConversationError(
                                    code = ConversationError.ErrorCode.EMPTY_AI_RESPONSE,
                                    userMessageUzbek = "AI javobi bo'sh qaytdi. Qayta urinib ko'ring.",
                                    failedTranscript = verifiedTurn.rawTranscript,
                                    turnId = turnId
                                )
                            )
                            return@fold
                        }

                        sessionScores.add(aiReply.accuracyScore)

                        val assistantMessage = ConversationMessage(
                            turnId = turnId,
                            role = ConversationRole.ASSISTANT,
                            text = assistantText,
                            aiGeneratedFeedback = aiReply.grammarAndVocabFeedbackUzbek.takeIf { it.isNotBlank() }
                        )

                        _uiState.update { current ->
                            val boundedMessages = (current.messages + assistantMessage)
                                .takeLast(TutorPromptConfig.MAX_TRANSCRIPT_MESSAGES_IN_MEMORY)
                            current.copy(
                                messages = boundedMessages,
                                latencyMetrics = current.latencyMetrics.copy(
                                    sttToAiResponseMs = aiLatencyMs
                                )
                            )
                        }

                        speakAssistantResponseAndResumeIfActive(
                            turnId = turnId,
                            spokenText = assistantText,
                            responseReadyTimestampMs = responseReadyMs,
                            autoResumeListeningAfterTts = _uiState.value.isHandsFreeConversationActive
                        )
                    },
                    onFailure = { err ->
                        val mappedErr = mapGeminiThrowable(
                            throwable = err,
                            failedTranscript = verifiedTurn.rawTranscript,
                            turnId = turnId
                        )
                        handleGeminiError(turnId = turnId, error = mappedErr)
                    }
                )
            } catch (e: CancellationException) {
                throw e
            }
        }
    }

    private fun handleGeminiError(turnId: String, error: ConversationError) {
        if (!activeTurnId.compareAndSet(turnId, null)) return
        TalkMeConversationLogger.logErrorAndRecovery("GEMINI_ERROR", error.code.name)
        _uiState.update { current ->
            current.copy(
                conversationState = ConversationState.ERROR(error),
                isAudioCaptureActuallyRunning = false,
                lastError = error,
                statusMessage = error.userMessageUzbek
            )
        }
    }

    private fun mapGeminiThrowable(
        throwable: Throwable,
        failedTranscript: String,
        turnId: String
    ): ConversationError {
        val msg = throwable.message?.lowercase().orEmpty()
        val code = when {
            throwable is TimeoutException || msg.contains("timeout") || msg.contains("timed out") ->
                ConversationError.ErrorCode.API_TIMEOUT
            msg.contains("429") || msg.contains("quota") || msg.contains("rate limit") ->
                ConversationError.ErrorCode.RATE_LIMITED
            throwable is IOException || msg.contains("network") || msg.contains("offline") ->
                ConversationError.ErrorCode.NETWORK_ERROR
            else -> ConversationError.ErrorCode.NETWORK_ERROR
        }
        val userUzbek = when (code) {
            ConversationError.ErrorCode.API_TIMEOUT ->
                "AI javob berish vaqti tugadi. 'Qayta' tugmasini bosing."
            ConversationError.ErrorCode.RATE_LIMITED ->
                "So'rovlar limiti to'ldi. Biroz kutib qayta urinib ko'ring."
            else ->
                "Aloqa xatosi: javob olib bo'lmadi. 'Qayta' tugmasini bosing."
        }
        return ConversationError(
            code = code,
            userMessageUzbek = userUzbek,
            failedTranscript = failedTranscript,
            turnId = turnId,
            isRecoverable = true
        )
    }

    /**
     * Speaks the assistant reply via [TtsManager] (`AI_THINKING -> AI_SPEAKING`),
     * waits for actual `UtteranceProgressListener.onDone()` completion, and automatically starts
     * the next listening turn (`AI_SPEAKING -> LISTENING`) when hands-free is active.
     *
     * Section 13: If TTS fails, keeps the displayed AI reply and provides a replay or skip option.
     */
    fun speakAssistantResponseAndResumeIfActive(
        turnId: String = UUID.randomUUID().toString(),
        spokenText: String,
        responseReadyTimestampMs: Long = System.currentTimeMillis(),
        autoResumeListeningAfterTts: Boolean = false
    ) {
        val clean = spokenText.trim()
        if (clean.isEmpty()) return

        activeTtsJob?.cancel()
        activeTurnId.set(turnId)

        activeTtsJob = scope.launch {
            // Ensure microphone is stopped while TTS speaks so assistant voice never enters STT
            listeningTurnId.set(null)
            speechRepository.cancelListening()

            val firstAudioLatency = (System.currentTimeMillis() - responseReadyTimestampMs).coerceAtLeast(0L)
            TalkMeConversationLogger.logTtsStarted(turnId, clean.length)

            _uiState.update { current ->
                current.copy(
                    conversationState = ConversationState.AI_SPEAKING(
                        turnId = turnId,
                        spokenResponse = clean
                    ),
                    isAudioCaptureActuallyRunning = false,
                    lastError = null,
                    statusMessage = "🔊 AI Tutor gapirmoqda...",
                    latencyMetrics = current.latencyMetrics.copy(
                        responseToFirstAudioMs = firstAudioLatency
                    )
                )
            }

            try {
                val targetSpeed = TutorPromptConfig.defaultTtsSpeedForLevel(
                    learnerLevel = _uiState.value.learnerLevel,
                    userPreferredSpeed = 1.0f
                )

                // Suspends until actual UtteranceProgressListener.onDone() / onError() callback
                val speakResult = ttsManager.speakEnglish(
                    text = clean,
                    overrideSpeed = targetSpeed
                )

                if (activeTurnId.compareAndSet(turnId, null)) {
                    TalkMeConversationLogger.logTtsCompleted(turnId)

                    val ttsState = ttsManager.playbackState.value
                    val didTtsFail = speakResult.isFailure || ttsState is com.example.core.audio.TtsPlaybackState.Error

                    if (didTtsFail) {
                        // Section 13: Keep displayed AI reply, provide replay or skip option
                        val ttsErr = ConversationError(
                            code = ConversationError.ErrorCode.TTS_ERROR,
                            userMessageUzbek = "Ovozli o'qishda xatolik. 'Qayta eshitish' yoki 'Skip' tugmasini bosing.",
                            failedSpokenReply = clean,
                            turnId = turnId,
                            isRecoverable = true
                        )
                        _uiState.update { current ->
                            current.copy(
                                conversationState = ConversationState.ERROR(ttsErr),
                                lastError = ttsErr,
                                statusMessage = ttsErr.userMessageUzbek
                            )
                        }
                        return@launch
                    }

                    // Prevent restarting microphone if conversation was stopped during TTS
                    if (_uiState.value.isHandsFreeConversationActive && autoResumeListeningAfterTts) {
                        startNextListeningTurn()
                    } else {
                        _uiState.update { current ->
                            current.copy(
                                conversationState = ConversationState.IDLE,
                                statusMessage = "Suhbatni boshlash uchun mikrofonni bir marta bosing"
                            )
                        }
                    }
                }
            } catch (e: CancellationException) {
                throw e
            }
        }
    }

    /**
     * Retries the last failed action (Gemini request, TTS replay, or STT listening) safely:
     * - If Gemini failed: resubmits the preserved `failedTranscript` without duplicating the user message.
     * - If TTS failed: replays the preserved `failedSpokenReply` and resumes hands-free listening afterwards.
     * - If STT failed: restarts listening.
     */
    fun retryFailedTurn() {
        val error = _uiState.value.lastError ?: return

        if (error.code == ConversationError.ErrorCode.TTS_ERROR && error.failedSpokenReply.isNotBlank()) {
            speakAssistantResponseAndResumeIfActive(
                turnId = error.turnId ?: UUID.randomUUID().toString(),
                spokenText = error.failedSpokenReply,
                autoResumeListeningAfterTts = _uiState.value.isHandsFreeConversationActive
            )
            return
        }

        val preserved = error.failedTranscript.trim()
        if (preserved.isNotEmpty()) {
            val alreadyInHistory = _uiState.value.messages.lastOrNull()?.let {
                it.role == ConversationRole.USER && it.text == preserved
            } == true
            val retryTurn = VerifiedUserTurn(
                turnId = error.turnId ?: UUID.randomUUID().toString(),
                rawTranscript = preserved
            )
            submitVerifiedTurnToGemini(
                verifiedTurn = retryTurn,
                isRetryOfExistingMessage = alreadyInHistory
            )
        } else {
            startConversation()
        }
    }

    fun getAverageAccuracyScore(): Int {
        return if (sessionScores.isNotEmpty()) {
            sessionScores.average().toInt().coerceIn(0, 100)
        } else {
            85
        }
    }

    /**
     * Safely stops all active coroutines, audio recording, and TTS playback (e.g. when user leaves screen).
     */
    fun releaseResources() {
        activeTurnId.set(null)
        listeningTurnId.set(null)
        activeGeminiJob?.cancel()
        activeGeminiJob = null
        activeTtsJob?.cancel()
        activeTtsJob = null
        observeStreamsJob?.cancel()
        observeStreamsJob = null
        speechRepository.cancelListening()
        ttsManager.stop()
        _uiState.update {
            it.copy(
                conversationState = ConversationState.STOPPED,
                isHandsFreeConversationActive = false,
                isAudioCaptureActuallyRunning = false,
                provisionalTranscript = "",
                audioAmplitude = 0f
            )
        }
    }
}
