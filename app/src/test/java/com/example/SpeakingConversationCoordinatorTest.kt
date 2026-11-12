package com.example

import com.example.core.audio.TtsManager
import com.example.core.speech.SileroVadDetector
import com.example.core.speech.SpeechModelManager
import com.example.core.speech.VadState
import com.example.domain.model.UserPreferencesModel
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.ReadingPassageResult
import com.example.domain.repository.SpeakingAiResponse
import com.example.domain.repository.SpeechRecognitionResult
import com.example.domain.repository.SpeechRecognitionState
import com.example.domain.repository.SpeechRepository
import com.example.domain.repository.TtsProvider
import com.example.domain.repository.UserPreferencesRepository
import com.example.feature.speaking.ConversationCoordinator
import com.example.feature.speaking.ConversationError
import com.example.feature.speaking.ConversationMessage
import com.example.feature.speaking.ConversationRole
import com.example.feature.speaking.ConversationState
import com.example.feature.speaking.TalkMeConversationLogger
import com.example.feature.speaking.TutorPromptConfig
import com.example.feature.speaking.VerifiedUserTurn
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.PI
import kotlin.math.sin
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FakeTtsProvider(override val providerName: String = "FAKE") : TtsProvider {
    val spokenTexts = mutableListOf<String>()
    var speakDelayMs: Long = 0L
    var shouldFailNext: Boolean = false

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (speakDelayMs > 0L) delay(speakDelayMs)
        if (shouldFailNext) {
            shouldFailNext = false
            return Result.failure(IllegalStateException("Simulated TTS playback failure"))
        }
        spokenTexts.add(text)
        return Result.success(Unit)
    }

    override fun stop() = Unit
    override fun clearCache(): Long = 0L
    override fun getCacheSizeBytes(): Long = 0L
}

class FakeLocalSpeechRepository : SpeechRepository {
    private val _speechState = MutableStateFlow(SpeechRecognitionState.IDLE)
    override val speechState: StateFlow<SpeechRecognitionState> = _speechState.asStateFlow()

    private val _vadState = MutableStateFlow(VadState.SILENCE)
    override val vadState: StateFlow<VadState> = _vadState.asStateFlow()

    private val _interimText = MutableStateFlow("")
    override val interimText: StateFlow<String> = _interimText.asStateFlow()

    private val _finalText = MutableStateFlow("")
    override val finalText: StateFlow<String> = _finalText.asStateFlow()

    private val _audioAmplitude = MutableStateFlow(0f)
    override val audioAmplitude: StateFlow<Float> = _audioAmplitude.asStateFlow()

    private val _isMuted = MutableStateFlow(false)
    override val isMuted: StateFlow<Boolean> = _isMuted.asStateFlow()

    private val _recordingDurationSeconds = MutableStateFlow(0)
    override val recordingDurationSeconds: StateFlow<Int> = _recordingDurationSeconds.asStateFlow()

    private val _utteranceFinalizedEvents = MutableSharedFlow<SpeechRecognitionResult>(extraBufferCapacity = 16)
    override val utteranceFinalizedEvents: SharedFlow<SpeechRecognitionResult> =
        _utteranceFinalizedEvents.asSharedFlow()

    var permissionGranted: Boolean = true
    var startCount: Int = 0
    var cancelCount: Int = 0

    override fun hasRecordPermission(): Boolean = permissionGranted

    override fun setMuted(muted: Boolean) {
        _isMuted.value = muted
    }

    override fun startListening(): Boolean {
        if (!permissionGranted) {
            _speechState.value = SpeechRecognitionState.ERROR
            return false
        }
        startCount++
        _interimText.value = ""
        _finalText.value = ""
        _vadState.value = VadState.SILENCE
        _speechState.value = SpeechRecognitionState.LISTENING_PASS_1
        return true
    }

    override suspend fun stopListening(): SpeechRecognitionResult {
        _speechState.value = SpeechRecognitionState.COMPLETED
        return SpeechRecognitionResult(
            recognizedText = _finalText.value,
            isFinal = true,
            recordingDurationMs = 1200L,
            processingDurationMs = 45L
        )
    }

    override fun cancelListening() {
        cancelCount++
        _speechState.value = SpeechRecognitionState.IDLE
        _vadState.value = VadState.SILENCE
        _interimText.value = ""
    }

    suspend fun simulateUserSpeakingWithVadAndSherpa(
        partialTranscript: String = "",
        finalTranscript: String
    ) {
        _vadState.value = VadState.SPEECH_BEGIN
        _vadState.value = VadState.ACTIVE_SPEECH
        if (partialTranscript.isNotEmpty()) {
            _interimText.value = partialTranscript
        }
        _vadState.value = VadState.SPEECH_END
        _speechState.value = SpeechRecognitionState.PROCESSING_PASS_2
        _finalText.value = finalTranscript
        _speechState.value = SpeechRecognitionState.COMPLETED
        _utteranceFinalizedEvents.emit(
            SpeechRecognitionResult(
                recognizedText = finalTranscript,
                isFinal = true,
                recordingDurationMs = 1500L,
                processingDurationMs = 60L,
                hadSpeechDetectedByVad = finalTranscript.isNotBlank()
            )
        )
    }
}

class FakeContextAwareAiLearningService : AiLearningService {
    val receivedUserUtterances = mutableListOf<String>()
    val receivedHistories = mutableListOf<List<Pair<String, String>>>()
    val receivedLearnerLevels = mutableListOf<String>()
    var artificialDelayMs: Long = 0L
    var shouldFailNextCount: Int = 0
    val callCount = AtomicInteger(0)

    override suspend fun sendSpeakingTurn(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>,
        conversationHistory: List<Pair<String, String>>,
        studentUtterance: String,
        audioWavBytes: ByteArray?,
        learnerLevel: String
    ): Result<SpeakingAiResponse> {
        callCount.incrementAndGet()
        if (artificialDelayMs > 0L) delay(artificialDelayMs)
        if (shouldFailNextCount > 0) {
            shouldFailNextCount--
            return Result.failure(IOException("Simulated Gemini network error"))
        }

        receivedUserUtterances.add(studentUtterance)
        receivedHistories.add(conversationHistory)
        receivedLearnerLevels.add(learnerLevel)

        val lastTutorQuestion = conversationHistory
            .lastOrNull { it.first.equals("ASSISTANT", true) || it.first.equals("AI_TUTOR", true) }
            ?.second
            .orEmpty()

        val reply = when {
            studentUtterance.equals("My name is Alex.", ignoreCase = true) ->
                "Nice to meet you, Alex! Where are you from?"
            studentUtterance.equals("Yes.", ignoreCase = true) &&
                lastTutorQuestion.contains("football", ignoreCase = true) ->
                "Awesome! Do you play football with your friends on weekends?"
            studentUtterance.equals("At school.", ignoreCase = true) &&
                lastTutorQuestion.contains("Where do you study?", ignoreCase = true) ->
                "Studying at school is great! What is your favorite subject at school?"
            studentUtterance.equals("I don't understand.", ignoreCase = true) ->
                "No problem! Let me ask in a simpler way: what food do you like to eat?"
            studentUtterance.equals("Can you repeat that?", ignoreCase = true) ->
                "Sure! I asked: $lastTutorQuestion"
            studentUtterance.equals("Yesterday I go to school.", ignoreCase = true) ->
                "Oh, you went to school yesterday! What did you study?"
            else ->
                "That is interesting! Can you tell me more about that?"
        }

        val feedback = if (studentUtterance.equals("Yesterday I go to school.", ignoreCase = true)) {
            "O'tgan zamon uchun 'go' o'rniga 'went' ishlatiladi."
        } else {
            ""
        }

        return Result.success(
            SpeakingAiResponse(
                spokenEnglishReply = reply,
                grammarAndVocabFeedbackUzbek = feedback,
                accuracyScore = 90,
                studentTranscript = studentUtterance
            )
        )
    }

    override suspend fun generateReadingPassage(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>
    ): Result<ReadingPassageResult> = throw UnsupportedOperationException()

    override suspend fun evaluateReadingQuery(
        topicTitle: String,
        passageText: String,
        comprehensionQuestion: String,
        studentQueryOrAnswer: String
    ): Result<String> = throw UnsupportedOperationException()
}

class FakeUserPreferencesRepository(
    soundEnabled: Boolean = true
) : UserPreferencesRepository {
    override val userPreferencesFlow: Flow<UserPreferencesModel> = flowOf(
        UserPreferencesModel(
            userName = "Alex",
            themeMode = "SYSTEM",
            soundEnabled = soundEnabled,
            speechSpeed = 1.0f,
            isPremiumSubscribed = false,
            preferredTtsProvider = "NATIVE",
            lastActiveTopicId = "topic_01_introduction",
            lastActiveSectionId = "sec_01_intro_1",
            lastScrollOffset = 0
        )
    )
    override suspend fun updateUserName(name: String) = Unit
    override suspend fun updateThemeMode(themeMode: String) = Unit
    override suspend fun updateSoundEnabled(enabled: Boolean) = Unit
    override suspend fun updateSpeechSpeed(speed: Float) = Unit
    override suspend fun updatePremiumSubscribed(subscribed: Boolean) = Unit
    override suspend fun updatePreferredTtsProvider(provider: String) = Unit
    override suspend fun updateLastLearningBookmark(topicId: String, sectionId: String, scrollOffset: Int) = Unit
}

@OptIn(ExperimentalCoroutinesApi::class)
class SpeakingConversationCoordinatorTest {

    @Before
    fun setUp() {
        TalkMeConversationLogger.isLoggingEnabled = false
    }

    private fun createCoordinator(
        testScope: TestScope,
        fakeSpeech: FakeLocalSpeechRepository = FakeLocalSpeechRepository(),
        fakeAi: FakeContextAwareAiLearningService = FakeContextAwareAiLearningService(),
        fakeTts: FakeTtsProvider = FakeTtsProvider()
    ): ConversationCoordinator {
        val dispatcher = StandardTestDispatcher(testScope.testScheduler)
        val fakePrefs = FakeUserPreferencesRepository(soundEnabled = true)
        val ttsManager = TtsManager(
            nativeTtsProvider = fakeTts,
            geminiTtsProvider = fakeTts,
            preferencesRepository = fakePrefs
        )
        return ConversationCoordinator(
            scope = testScope,
            speechRepository = fakeSpeech,
            aiLearningService = fakeAi,
            ttsManager = ttsManager,
            ioDispatcher = dispatcher,
            maxGeminiRetryAttempts = 0
        ).also {
            it.setMicPermissionGranted(fakeSpeech.permissionGranted)
        }
    }

    @Test
    fun `TEST 1 - AI asks What is your name and user says My name is Alex`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val fakeTts = FakeTtsProvider()
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi, fakeTts)

        coordinator.configureLessonContext(
            topicId = "topic_01_introduction",
            topicTitle = "1. Introduction",
            learnerLevel = "A0_A1",
            vocabulary = listOf("name", "hello"),
            grammarSummaries = listOf("My name is ..."),
            welcomeMessage = ConversationMessage(
                turnId = "welcome",
                role = ConversationRole.ASSISTANT,
                text = "What is your name?"
            ),
            speakWelcome = false
        )

        // One tap starts listening
        coordinator.onMicrophoneButtonTapped()
        advanceUntilIdle()
        assertTrue(coordinator.uiState.value.conversationState is ConversationState.LISTENING)

        // User says "My name is Alex."
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(
            partialTranscript = "My name is",
            finalTranscript = "My name is Alex."
        )
        advanceUntilIdle()

        assertEquals("My name is Alex.", fakeAi.receivedUserUtterances.single())
        assertEquals("My name is Alex.", coordinator.uiState.value.messages[1].text)
        assertTrue(coordinator.uiState.value.messages[2].text.contains("Alex"))
        assertTrue(coordinator.uiState.value.conversationState is ConversationState.LISTENING)
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 2 - AI asks Do you like football and user replies short Yes`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi)

        coordinator.configureLessonContext(
            topicId = "topic_01",
            topicTitle = "Sports",
            learnerLevel = "A2_B1",
            vocabulary = listOf("football", "friends"),
            grammarSummaries = emptyList(),
            welcomeMessage = ConversationMessage(
                turnId = "q1",
                role = ConversationRole.ASSISTANT,
                text = "Do you like football?"
            ),
            speakWelcome = false
        )

        coordinator.startConversation()
        advanceUntilIdle()

        // Short reply "Yes." must be preserved verbatim and understood in context of football
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = "Yes.")
        advanceUntilIdle()

        assertEquals("Yes.", coordinator.uiState.value.messages[1].text)
        assertEquals("Yes.", fakeAi.receivedUserUtterances.single())
        assertTrue(coordinator.uiState.value.messages[2].text.contains("football", ignoreCase = true))
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 3 - AI asks Where do you study and user replies incomplete sentence At school`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi)

        coordinator.configureLessonContext(
            topicId = "topic_02",
            topicTitle = "School",
            learnerLevel = "A0_A1",
            vocabulary = listOf("school", "study"),
            grammarSummaries = emptyList(),
            welcomeMessage = ConversationMessage(
                turnId = "q_study",
                role = ConversationRole.ASSISTANT,
                text = "Where do you study?"
            ),
            speakWelcome = false
        )

        coordinator.startConversation()
        advanceUntilIdle()

        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = "At school.")
        advanceUntilIdle()

        // Must never rewrite "At school." into "I study at school."
        assertEquals("At school.", coordinator.uiState.value.messages[1].text)
        assertEquals("At school.", fakeAi.receivedUserUtterances.single())
        assertTrue(coordinator.uiState.value.messages[2].text.contains("school", ignoreCase = true))
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 4 and TEST 5 - user says I don't understand or Can you repeat that`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi)

        coordinator.configureLessonContext(
            topicId = "topic_03",
            topicTitle = "Food",
            learnerLevel = "A0_A1",
            vocabulary = listOf("food", "eat"),
            grammarSummaries = emptyList(),
            welcomeMessage = ConversationMessage(
                turnId = "q_food",
                role = ConversationRole.ASSISTANT,
                text = "What is your favorite cuisine?"
            ),
            speakWelcome = false
        )

        coordinator.startConversation()
        advanceUntilIdle()

        // TEST 4: "I don't understand."
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = "I don't understand.")
        advanceUntilIdle()
        assertEquals("I don't understand.", coordinator.uiState.value.messages[1].text)
        assertTrue(coordinator.uiState.value.messages[2].text.contains("simpler way", ignoreCase = true))

        // TEST 5: "Can you repeat that?"
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = "Can you repeat that?")
        advanceUntilIdle()
        assertEquals("Can you repeat that?", coordinator.uiState.value.messages[3].text)
        assertTrue(coordinator.uiState.value.messages[4].text.contains("I asked:", ignoreCase = true))
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 6 and TEST 7 - user says nothing or STT returns no match creates no user message and no fake text`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi)

        coordinator.startConversation()
        advanceUntilIdle()

        // TEST 6 & 7: Empty STT result
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = "   ")
        advanceUntilIdle()

        assertEquals(0, fakeAi.callCount.get())
        assertTrue(coordinator.uiState.value.messages.isEmpty())
        // Hands-free conversation remains in LISTENING for the next attempt
        assertTrue(coordinator.uiState.value.conversationState is ConversationState.LISTENING)
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 8 - ten consecutive spoken answers automatically proceed through STT, Gemini, and TTS without a second mic press`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val fakeTts = FakeTtsProvider()
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi, fakeTts)

        // Single microphone tap starts the entire 10-turn session
        coordinator.onMicrophoneButtonTapped()
        advanceUntilIdle()

        for (turn in 1..10) {
            assertTrue(
                "Turn $turn must start in LISTENING automatically",
                coordinator.uiState.value.conversationState is ConversationState.LISTENING
            )
            val spoken = "This is my spoken sentence number $turn."
            fakeSpeech.simulateUserSpeakingWithVadAndSherpa(
                partialTranscript = "This is my spoken",
                finalTranscript = spoken
            )
            advanceUntilIdle()
        }

        assertEquals(10, fakeAi.callCount.get())
        assertEquals(10, fakeTts.spokenTexts.size)
        assertEquals(20, coordinator.uiState.value.messages.size)
        for (turn in 1..10) {
            val userMsg = coordinator.uiState.value.messages[(turn - 1) * 2]
            assertEquals(ConversationRole.USER, userMsg.role)
            assertEquals("This is my spoken sentence number $turn.", userMsg.text)
        }
        assertTrue(coordinator.uiState.value.conversationState is ConversationState.LISTENING)
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 9 - user leaves screen during AI speech stops all jobs and prevents stale callbacks from restarting mic`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService()
        val fakeTts = FakeTtsProvider().apply { speakDelayMs = 2_000L }
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi, fakeTts)

        coordinator.startConversation()
        advanceUntilIdle()

        // User speaks -> Gemini responds -> enters AI_SPEAKING (TTS takes 2000ms)
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = "My name is Alex.")
        testScheduler.advanceTimeBy(200L)
        assertTrue(coordinator.uiState.value.conversationState is ConversationState.AI_SPEAKING)

        val startCountBeforeExit = fakeSpeech.startCount

        // User leaves the screen / stops conversation while TTS is speaking
        coordinator.stopConversation()
        advanceUntilIdle()

        assertEquals(ConversationState.STOPPED, coordinator.uiState.value.conversationState)
        assertFalse(coordinator.uiState.value.isHandsFreeConversationActive)
        // Stale TTS completion must NOT restart listening
        assertEquals(startCountBeforeExit, fakeSpeech.startCount)
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 10 - Gemini request fails preserves user transcript, shows retry option, and injects zero fabricated AI messages`() = runTest {
        val fakeSpeech = FakeLocalSpeechRepository()
        val fakeAi = FakeContextAwareAiLearningService().apply { shouldFailNextCount = 1 }
        val coordinator = createCoordinator(this, fakeSpeech, fakeAi)

        coordinator.startConversation()
        advanceUntilIdle()

        val exactTranscript = "Yesterday I go to school."
        fakeSpeech.simulateUserSpeakingWithVadAndSherpa(finalTranscript = exactTranscript)
        advanceUntilIdle()

        // 1. User message is preserved intact, no fake AI message injected, state is ERROR
        assertEquals(1, coordinator.uiState.value.messages.size)
        assertEquals(exactTranscript, coordinator.uiState.value.messages.single().text)
        assertTrue(coordinator.uiState.value.conversationState is ConversationState.ERROR)
        assertNotNull(coordinator.uiState.value.lastError)

        // 2. User taps Retry -> succeeds without duplicating user message, keeps original grammar untouched
        coordinator.retryFailedTurn()
        advanceUntilIdle()

        assertEquals(2, coordinator.uiState.value.messages.size)
        assertEquals("Yesterday I go to school.", coordinator.uiState.value.messages[0].text)
        assertEquals("Oh, you went to school yesterday! What did you study?", coordinator.uiState.value.messages[1].text)
        assertEquals(
            "O'tgan zamon uchun 'go' o'rniga 'went' ishlatiladi.",
            coordinator.uiState.value.messages[1].aiGeneratedFeedback
        )
        coordinator.releaseResources()
    }

    @Test
    fun `TEST 11 - SileroVadDetector multi-feature analysis distinguishes speech harmonics from DC offset and impulse clicks`() {
        val vad = SileroVadDetector()

        // 1. Pure DC offset (high amplitude 0.6f, zero crossings = 0) must be rejected as non-speech
        val dcWindow = FloatArray(512) { 0.60f }
        val dcProb = vad.computeWindowSpeechProbability(dcWindow)
        assertTrue("DC offset must not trigger speech detection (prob=$dcProb)", dcProb < 0.15f)

        // 2. Single-sample loud click (amplitude 0.95f on 1 sample) must be rejected as non-speech
        val clickWindow = FloatArray(512) { if (it == 100) 0.95f else 0.001f }
        val clickProb = vad.computeWindowSpeechProbability(clickWindow)
        assertTrue("Single-sample click must not trigger speech detection (prob=$clickProb)", clickProb < 0.15f)

        // 3. Harmonic voiced speech signal (fundamental 130Hz + formants 520Hz, 1050Hz, 1850Hz)
        val speechWindow = FloatArray(512) { i ->
            val t = i.toDouble() / 16_000.0
            val f0 = 0.25 * sin(2.0 * PI * 130.0 * t)
            val f1 = 0.30 * sin(2.0 * PI * 520.0 * t)
            val f2 = 0.20 * sin(2.0 * PI * 1050.0 * t)
            val f3 = 0.10 * sin(2.0 * PI * 1850.0 * t)
            (f0 + f1 + f2 + f3).toFloat()
        }
        val speechProb = vad.computeWindowSpeechProbability(speechWindow)
        assertTrue("Harmonic human speech must produce high VAD probability (prob=$speechProb)", speechProb >= 0.50f)

        // 4. Feed consecutive speech frames -> SPEECH_BEGIN -> ACTIVE_SPEECH -> silence frames -> PAUSE -> SPEECH_END
        vad.reset()
        var sawSpeechBegin = false
        var sawActiveSpeech = false
        for (frame in 1..6) {
            val res = vad.acceptWaveform(speechWindow)
            if (res.state == VadState.SPEECH_BEGIN) sawSpeechBegin = true
            if (res.state == VadState.ACTIVE_SPEECH) sawActiveSpeech = true
        }
        assertTrue(sawSpeechBegin)
        assertTrue(sawActiveSpeech)

        val silenceWindow = FloatArray(512) { 0.0f }
        var sawPause = false
        var sawSpeechEnd = false
        var segmentedSamples: FloatArray? = null
        for (frame in 1..25) {
            val res = vad.acceptWaveform(silenceWindow)
            if (res.state == VadState.PAUSE) sawPause = true
            if (res.state == VadState.SPEECH_END) {
                sawSpeechEnd = true
                segmentedSamples = res.completedSpeechSegment
                break
            }
        }
        assertTrue(sawPause)
        assertTrue(sawSpeechEnd)
        assertNotNull(segmentedSamples)
        assertTrue(segmentedSamples!!.isNotEmpty())
    }
}
