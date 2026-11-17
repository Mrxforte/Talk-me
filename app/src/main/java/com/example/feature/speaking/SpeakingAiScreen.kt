package com.example.feature.speaking

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.AiChatTranscriptAdapter
import com.example.core.ui.CosmicWaveformView
import com.example.core.ui.ProgressAnimator.animateProgressTo
import com.example.databinding.FragmentSpeakingAiBinding
import com.example.feature.pro.ProAction
import com.example.feature.pro.TutorCue
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.example.domain.model.PracticeCategory
import com.example.domain.model.UnlockStatus
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.SpeechRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SpeakingAiViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val speechRepository: SpeechRepository,
    private val aiLearningService: AiLearningService,
    private val ttsManager: TtsManager
) : ViewModel() {

    val coordinator = ConversationCoordinator(
        scope = viewModelScope,
        speechRepository = speechRepository,
        aiLearningService = aiLearningService,
        ttsManager = ttsManager
    )

    private val _sessionDurationSeconds = MutableStateFlow(0)

    val uiState: StateFlow<ConversationUiState> = combine(
        coordinator.uiState,
        _sessionDurationSeconds
    ) { coordState, elapsedSeconds ->
        coordState.copy(durationSeconds = elapsedSeconds)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ConversationUiState()
    )

    val studentName: StateFlow<String> = preferencesRepository.userPreferencesFlow
        .map { prefs -> prefs.userName.ifBlank { "Azamat" } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = "Azamat"
        )

    val voiceGender: StateFlow<String> = preferencesRepository.userPreferencesFlow
        .map { prefs -> prefs.voiceGender }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = "MALE"
        )

    private var isInitializingSession = false
    private var isSessionReady = false
    private var startWhenReady = false
    private var welcomeToPlay: ConversationMessage? = null

    init {
        startSessionTimer()
    }

    /**
     * Synchronizes permission and prepares the lesson without interrupting an active turn.
     */
    fun onMicrophonePermissionGranted(requestedTopicId: String? = null, requestedSectionId: String? = null) {
        coordinator.setMicPermissionGranted(true)
        initializeSpeakingSession(requestedTopicId, requestedSectionId)
    }

    fun onMicrophonePermissionRevoked() {
        stopConversation()
        coordinator.setMicPermissionGranted(false)
    }

    fun initializeSpeakingSession(requestedTopicId: String? = null, requestedSectionId: String? = null) {
        if (isInitializingSession || isSessionReady) return
        isInitializingSession = true

        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    learningRepository.ensureSeeded()
                }
                val prefs = preferencesRepository.userPreferencesFlow.first()
                val targetTopicId = requestedTopicId?.takeIf { it.isNotBlank() } ?: prefs.lastActiveTopicId
                val topic = withContext(Dispatchers.IO) {
                    learningRepository.getTopicById(targetTopicId)
                }
                val allTopics = withContext(Dispatchers.IO) {
                    learningRepository.observeTopics().first()
                }
                val completedTopicsCount = allTopics.count { it.status == UnlockStatus.COMPLETED }

                val topicId = topic?.id ?: AppConstants.INITIAL_TOPIC_ID
                val topicOrderIndex = topic?.orderIndex ?: 1
                val learnerLevel = TutorPromptConfig.resolveLearnerLevel(
                    topicOrderIndex = topicOrderIndex,
                    completedTopicsCount = completedTopicsCount
                )
                val section = requestedSectionId?.let { learningRepository.getSectionById(it) }
                    ?.takeIf { it.topicId == topicId }
                val title = section?.titleEnglish ?: topic?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" }
                    ?: "1. Introduction (Kirish)"

                val vocabModels = withContext(Dispatchers.IO) {
                    section?.let { learningRepository.observeSectionVocabulary(it.id).first() }
                        ?: learningRepository.getStudiedVocabularyForTopic(topicId)
                }
                val grammarModels = withContext(Dispatchers.IO) {
                    section?.let { learningRepository.observeSectionGrammarRules(it.id).first() }
                        ?: learningRepository.getStudiedGrammarForTopic(topicId)
                }
                val allowedWords = vocabModels.map { it.englishWord }
                val allowedGrammar = grammarModels.map { "${it.titleUzbek}: ${it.exampleEnglish}" }

                val sampleWords = allowedWords.take(3).joinToString(", ").ifEmpty { "hello, name, student" }
                val extra = if (section != null) null else withContext(Dispatchers.IO) {
                    learningRepository.getEligibleExercises(PracticeCategory.SPEAKING, null, topicId)
                        .filter { it.id.startsWith("extra_") }.randomOrNull()
                }
                val welcomeEnglish = extra?.promptText ?: "Hello! Let's practice speaking about $title. Try using words like $sampleWords. Can you make a sentence about this lesson?"
                val welcomeMessage = ConversationMessage(
                    id = UUID.randomUUID().toString(),
                    turnId = "welcome_turn",
                    role = ConversationRole.ASSISTANT,
                    text = welcomeEnglish
                )

                coordinator.configureLessonContext(
                    topicId = topicId,
                    topicTitle = extra?.promptText ?: title,
                    learnerLevel = learnerLevel,
                    vocabulary = allowedWords,
                    grammarSummaries = allowedGrammar,
                    welcomeMessage = welcomeMessage,
                    speakWelcome = false
                )
                welcomeToPlay = welcomeMessage
                isSessionReady = true
                if (startWhenReady) onMicrophoneButtonTapped()
            } catch (error: kotlinx.coroutines.CancellationException) {
                throw error
            } catch (error: Exception) {
                android.util.Log.w("SpeakingAi", "Could not load speaking lesson", error)
                startWhenReady = false
                coordinator.reportLessonLoadFailure()
            } finally {
                isInitializingSession = false
            }
        }
    }

    private fun startSessionTimer() {
        viewModelScope.launch {
            while (isActive) {
                delay(1_000L)
                _sessionDurationSeconds.value += 1
            }
        }
    }

    fun onMicrophoneButtonTapped() {
        if (!isSessionReady) {
            startWhenReady = true
            return
        }
        startWhenReady = false
        val welcome = welcomeToPlay
        if (welcome != null) {
            welcomeToPlay = null
            coordinator.startConversation(welcome)
        } else {
            coordinator.onMicrophoneButtonTapped()
        }
    }

    fun stopConversation() {
        startWhenReady = false
        coordinator.stopConversation()
    }

    fun selectTutor(female: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateVoiceGender(if (female) "FEMALE" else "MALE")
        }
    }

    fun toggleMute() {
        coordinator.toggleMute()
    }

    fun cancelCurrentTurnOrSpeech() {
        coordinator.cancelCurrentTurnOrSpeech()
    }

    fun onKeyboardDraftChanged(newText: String) {
        coordinator.onKeyboardDraftChanged(newText)
    }

    fun submitKeyboardDraft(): Boolean {
        return coordinator.submitKeyboardDraft()
    }

    fun setKeyboardInputVisible(visible: Boolean) {
        coordinator.setKeyboardInputVisible(visible)
    }

    fun retryFailedTurn() {
        coordinator.retryFailedTurn()
    }

    fun speakMessage(text: String) {
        coordinator.speakAssistantResponseAndResumeIfActive(
            spokenText = text,
            autoResumeListeningAfterTts = uiState.value.isHandsFreeConversationActive
        )
    }

    fun endAndRecordSession(onComplete: () -> Unit) {
        viewModelScope.launch {
            coordinator.releaseResources()
            val avgScore = coordinator.getAverageAccuracyScore()
            val activeTopicId = uiState.value.topicId
            withContext(Dispatchers.IO) {
                learningRepository.recordPracticeSessionResult(
                    category = PracticeCategory.SPEAKING,
                    topicId = activeTopicId,
                    sectionId = AppConstants.INITIAL_SECTION_ID,
                    correctCount = avgScore,
                    totalCount = 100,
                    masteredGrammarRuleIds = emptyList(),
                    memorizedVocabularyIds = emptyList()
                )
            }
            onComplete()
        }
    }

    override fun onCleared() {
        coordinator.releaseResources()
        super.onCleared()
    }
}

/**
 * Production TalkMe Speaking AI Screen powered by Local Silero VAD + Local Sherpa-ONNX STT +
 * Firebase Gemini AI Tutor + Android TextToSpeech:
 * - Requests `RECORD_AUDIO` runtime permission through the microphone control.
 * - One microphone tap starts automatic hands-free turn-taking:
 *   `IDLE → LISTENING → USER_SPEAKING → FINALIZING → AI_THINKING → AI_SPEAKING → LISTENING`
 * - Preserves exact final STT transcripts (`rawTranscript == displayedUserMessage == geminiStudentUtterance`).
 * - Stops all active recording, AI, and TTS jobs safely when leaving the screen.
 */
@AndroidEntryPoint
class SpeakingAiFragment : Fragment() {

    private var _binding: FragmentSpeakingAiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpeakingAiViewModel by viewModels()
    private lateinit var transcriptAdapter: AiChatTranscriptAdapter
    private var femaleTutor = false
    private var lastSpeakingTurnId: String? = null
    private var speechCue: TutorCue? = null

    private val requestAudioPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        if (isGranted) {
            viewModel.onMicrophonePermissionGranted(requestedTopicId, arguments?.getString(AppConstants.ARG_SECTION_ID))
            viewModel.onMicrophoneButtonTapped()
        } else {
            viewModel.onMicrophonePermissionRevoked()
            if (isAdded && !shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(R.string.speaking_mic_settings_message)
                    .setPositiveButton(R.string.speaking_mic_open_settings) { _, _ ->
                        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", requireContext().packageName, null)))
                    }
                    .setNegativeButton(android.R.string.cancel, null)
                    .show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeakingAiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        viewModel.initializeSpeakingSession(requestedTopicId, arguments?.getString(AppConstants.ARG_SECTION_ID))
        val tutorNames = resources.getStringArray(R.array.speaking_tutor_names)

        val inLessonFlow = arguments?.getInt(AppConstants.ARG_LESSON_FLOW_STAGE) == 5
        transcriptAdapter = AiChatTranscriptAdapter(showSpeaker = !inLessonFlow) { englishText ->
            viewModel.speakMessage(englishText)
        }

        binding.rvSpeakingTranscript.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpeakingTranscript.adapter = transcriptAdapter
        binding.waveformTutorHero.setMode(CosmicWaveformView.Mode.BARS)
        binding.waveformUserLive.setMode(CosmicWaveformView.Mode.BARS)

        binding.btnSpeakingTutor.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.speaking_select_tutor)
                .setSingleChoiceItems(R.array.speaking_tutor_names, if (femaleTutor) 0 else 1) { dialog, selected ->
                    viewModel.selectTutor(female = selected == 0)
                    dialog.dismiss()
                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }

        // 2. Setup Jetpack Compose Auxiliary Controls (Stop, Mute, Cancel/Skip, Retry, Keyboard Input)
        binding.composeAuxiliaryControls.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val state by viewModel.uiState.collectAsStateWithLifecycle()
                    SpeakingAuxiliaryControlsBar(
                        uiState = state,
                        onKeyboardDraftChanged = { text ->
                            viewModel.onKeyboardDraftChanged(text)
                        },
                        onSendKeyboardDraft = {
                            viewModel.submitKeyboardDraft()
                        },
                        onToggleKeyboardBar = { visible ->
                            viewModel.setKeyboardInputVisible(visible)
                        },
                        onStopConversation = {
                            viewModel.stopConversation()
                        },
                        onToggleMute = {
                            viewModel.toggleMute()
                        },
                        onCancelTurnOrSpeech = {
                            viewModel.cancelCurrentTurnOrSpeech()
                        },
                        onRetryFailedTurn = {
                            viewModel.retryFailedTurn()
                        },
                        allowKeyboard = !inLessonFlow
                    )
                }
            }
        }

        binding.btnBack.setOnClickListener {
            viewModel.endAndRecordSession {}
            navigateBackSafely()
        }

        binding.btnEndSpeakingSession.setOnClickListener {
            if (arguments?.getInt(AppConstants.ARG_LESSON_FLOW_STAGE) == 5 &&
                viewModel.uiState.value.messages.any { it.role == ConversationRole.USER }) {
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    AppConstants.RESULT_LESSON_SPEAKING, arguments?.getString(AppConstants.ARG_SECTION_ID)
                )
            }
            viewModel.endAndRecordSession {}
            navigateBackSafely()
        }

        // 3. One-tap Microphone button: starts or stops hands-free conversation
        binding.btnMicrophoneSpeak.setOnClickListener {
            val granted = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
            if (granted) {
                viewModel.onMicrophonePermissionGranted(requestedTopicId, arguments?.getString(AppConstants.ARG_SECTION_ID))
                viewModel.onMicrophoneButtonTapped()
            } else {
                viewModel.onMicrophonePermissionRevoked()
                requestAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.studentName.collect { name ->
                        transcriptAdapter.updateStudentName(name)
                    }
                }
                launch {
                    viewModel.voiceGender.collect { gender ->
                        femaleTutor = gender.equals("FEMALE", ignoreCase = true)
                        transcriptAdapter.updateTutor(
                            tutorNames[if (femaleTutor) 0 else 1],
                            if (femaleTutor) R.drawable.pro_avatar_girl else R.drawable.pro_avatar_boy
                        )
                        updateCharacterStage(viewModel.uiState.value)
                    }
                }
                launch {
                    viewModel.coordinator.spokenRange.collect { updateCharacterStage(viewModel.uiState.value) }
                }
                launch {
                    viewModel.uiState.collect { state ->
                        val shortTopic = extractShortTopicTitle(state.topicTitle)
                        binding.tvSpeakingCurrentTopic.text = getString(R.string.speaking_header_format, shortTopic)
                        binding.tvSpeakingConnectionState.text = state.statusMessage

                        val targetTurns = 3
                        val completedTurns = state.messages.count { it.role == ConversationRole.USER }
                        binding.progressSpeakingTurn.animateProgressTo(
                            completedTurns.coerceIn(0, targetTurns),
                            maxValue = targetTurns
                        )
                        binding.tvSpeakingTurnCounter.text = getString(
                            R.string.speaking_turn_progress_format,
                            completedTurns.coerceAtMost(targetTurns),
                            targetTurns
                        )

                        if (arguments?.getInt(AppConstants.ARG_LESSON_FLOW_STAGE) == 5) {
                            binding.tvSpeakingTurnCounter.text = getString(R.string.lesson_flow_counter, 5, 6)
                            binding.progressSpeakingTurn.visibility = View.GONE
                        }
                        val mins = state.durationSeconds / 60
                        val secs = state.durationSeconds % 60
                        binding.tvSpeakingDuration.text = String.format(Locale.US, "%02d:%02d", mins, secs)

                        updateMicrophoneVisualState(state)
                        updateLiveSpeechCard(state)
                        updateCharacterStage(state)

                        transcriptAdapter.submitList(state.transcript) {
                            if (state.transcript.isNotEmpty()) {
                                binding.rvSpeakingTranscript.scrollToPosition(state.transcript.size - 1)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun extractShortTopicTitle(rawTitle: String): String {
        val withoutOrder = rawTitle.substringAfter(". ", rawTitle)
        val withoutParens = withoutOrder.substringBefore(" (").trim()
        return withoutParens.ifBlank { "Introduction" }
    }

    private fun updateLiveSpeechCard(state: ConversationUiState) {
        val cyanColor = ContextCompat.getColor(requireContext(), R.color.color_neon_cyan)
        val isUserCapturing = state.isListeningOrSpeakingUser && !state.isMuted
        binding.waveformUserLive.setActive(isUserCapturing)
        binding.waveformUserLive.setAmplitude(if (state.isMuted) 0f else state.audioAmplitude)

        binding.waveformTutorHero.setActive(state.isAiSpeaking)
        binding.waveformTutorHero.setAmplitude(if (state.isAiSpeaking) 0.85f else 0.15f)

        val liveText = when (val convState = state.conversationState) {
            is ConversationState.LISTENING -> {
                if (state.isAudioCaptureActuallyRunning) {
                    if (state.isMuted) {
                        getString(R.string.speaking_idle_live_hint)
                    } else {
                        getString(R.string.speaking_listening_prompt)
                    }
                } else {
                    getString(R.string.speaking_idle_live_hint)
                }
            }
            is ConversationState.USER_SPEAKING -> {
                convState.provisionalTranscript.ifBlank { getString(R.string.speaking_listening_prompt) }
            }
            is ConversationState.FINALIZING -> "⏳ Nutq yakunlandi, matn aniqlanmoqda…"
            is ConversationState.AI_THINKING -> "✨ AI Tutor javob tayyorlamoqda…"
            else -> getString(R.string.speaking_idle_live_hint)
        }

        val showCursor = state.conversationState is ConversationState.USER_SPEAKING ||
            (state.conversationState is ConversationState.LISTENING && state.isAudioCaptureActuallyRunning && !state.isMuted)

        if (showCursor) {
            val builder = SpannableStringBuilder(liveText).append(" |")
            builder.setSpan(
                ForegroundColorSpan(cyanColor),
                builder.length - 1,
                builder.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.tvLiveInterimSpeech.text = builder
        } else {
            binding.tvLiveInterimSpeech.text = liveText
        }
    }

    private fun updateMicrophoneVisualState(state: ConversationUiState) {
        binding.btnMicrophoneSpeak.isEnabled = !state.isLoading
        val microphoneAction = when {
            state.isListeningOrSpeakingUser -> R.string.speaking_mic_send
            state.isAiSpeaking -> R.string.speaking_mic_answer_now
            state.isLoading -> R.string.speaking_mic_processing
            else -> R.string.speaking_tap_to_speak
        }
        binding.tvTapToSpeakLabel.setText(microphoneAction)
        binding.btnMicrophoneSpeak.contentDescription = getText(microphoneAction)
        if (state.isListeningOrSpeakingUser) {
            binding.btnMicrophoneSpeak.setColorFilter(
                ContextCompat.getColor(requireContext(), if (state.isMuted) R.color.color_warning else R.color.color_text_on_primary)
            )
            val amp = if (state.isMuted) 0f else state.audioAmplitude.coerceIn(0f, 1f)
            val auraScale = 1.08f + amp * 0.36f
            binding.ivAiCharacterAura.scaleX = auraScale
            binding.ivAiCharacterAura.scaleY = auraScale
            binding.btnMicrophoneSpeak.scaleX = 1.08f
            binding.btnMicrophoneSpeak.scaleY = 1.08f
        } else if (state.isAiSpeaking) {
            binding.btnMicrophoneSpeak.setColorFilter(ContextCompat.getColor(requireContext(), R.color.color_text_on_primary))
            binding.ivAiCharacterAura.scaleX = 1.05f
            binding.ivAiCharacterAura.scaleY = 1.05f
            binding.viewTutorSpeakingBar.scaleX = 1.35f
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        } else {
            binding.btnMicrophoneSpeak.setColorFilter(ContextCompat.getColor(requireContext(), R.color.color_text_on_primary))
            binding.ivAiCharacterAura.scaleX = 1.0f
            binding.ivAiCharacterAura.scaleY = 1.0f
            binding.viewTutorSpeakingBar.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        }
    }

    private fun updateCharacterStage(state: ConversationUiState) {
        val ctx = context ?: return
        val statusText = when {
            state.isAiSpeaking -> getString(R.string.speaking_tutor_stage_speaking)
            state.conversationState is ConversationState.AI_THINKING ||
                state.conversationState is ConversationState.FINALIZING ->
                getString(R.string.speaking_tutor_stage_thinking)
            state.isListeningOrSpeakingUser && state.isAudioCaptureActuallyRunning && !state.isMuted ->
                getString(R.string.speaking_tutor_stage_listening)
            else -> getString(R.string.speaking_ai_tutor_badge)
        }
        binding.tvTutorStageStatus.text = statusText
        binding.btnSpeakingTutor.text = getString(
            R.string.speaking_tutor_selector_format,
            resources.getStringArray(R.array.speaking_tutor_names)[if (femaleTutor) 0 else 1]
        )
        binding.btnSpeakingTutor.contentDescription = getString(R.string.speaking_select_tutor)

        val strokeColor = ContextCompat.getColor(
            ctx,
            if (state.isAiSpeaking) R.color.color_neon_cyan else R.color.color_card_outline
        )
        binding.cardAiCharacterSection.strokeColor = strokeColor
        binding.viewTutorStatusDot.alpha = if (state.isAiSpeaking) 1.0f else 0.78f

        val speaking = state.conversationState as? ConversationState.AI_SPEAKING
        if (lastSpeakingTurnId != speaking?.turnId) {
            lastSpeakingTurnId = speaking?.turnId
            speechCue = speaking?.let { TutorCue(it.spokenResponse, SystemClock.uptimeMillis()) }
        }
        val spokenRange = viewModel.coordinator.spokenRange.value?.takeIf { it.turnId == speaking?.turnId }
        binding.ivAiCharacterAvatar.render(
            female = femaleTutor,
            action = when {
                state.isAiSpeaking -> ProAction.PLAYING
                state.isListeningOrSpeakingUser -> ProAction.LISTENING
                else -> ProAction.BUSY
            },
            cue = spokenRange?.let { TutorCue(it.text, it.timestampMs) } ?: speechCue,
            verticalCropBias = 0.34f
        )
    }

    private fun navigateBackSafely() {
        val nav = findNavController()
        if (!nav.popBackStack()) {
            nav.navigateUp()
        }
    }

    override fun onPause() {
        viewModel.stopConversation()
        super.onPause()
    }

    override fun onDestroyView() {
        binding.rvSpeakingTranscript.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
