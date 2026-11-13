package com.example.feature.speaking

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
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

    private var hasInitializedSession = false

    init {
        startSessionTimer()
    }

    /**
     * Called once `Manifest.permission.RECORD_AUDIO` is confirmed granted via [MicrophonePermissionGate].
     */
    fun onMicrophonePermissionGranted(requestedTopicId: String? = null) {
        coordinator.setMicPermissionGranted(true)
        initializeSpeakingSession(requestedTopicId)
    }

    fun onMicrophonePermissionRevoked() {
        coordinator.setMicPermissionGranted(false)
        coordinator.stopConversation()
    }

    fun initializeSpeakingSession(requestedTopicId: String? = null) {
        if (hasInitializedSession) return
        hasInitializedSession = true

        viewModelScope.launch {
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
            val title = topic?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" }
                ?: "1. Introduction (Kirish)"

            val vocabModels = withContext(Dispatchers.IO) {
                learningRepository.getStudiedVocabularyForTopic(topicId)
            }
            val grammarModels = withContext(Dispatchers.IO) {
                learningRepository.getStudiedGrammarForTopic(topicId)
            }
            val allowedWords = vocabModels.map { it.englishWord }
            val allowedGrammar = grammarModels.map { "${it.titleUzbek}: ${it.exampleEnglish}" }

            val sampleWords = allowedWords.take(3).joinToString(", ").ifEmpty { "hello, name, student" }
            val welcomeEnglish = "Hello! Let's practice speaking about $title. Try using words like $sampleWords. How can you introduce yourself?"
            val welcomeMessage = ConversationMessage(
                id = UUID.randomUUID().toString(),
                turnId = "welcome_turn",
                role = ConversationRole.ASSISTANT,
                text = welcomeEnglish
            )

            coordinator.configureLessonContext(
                topicId = topicId,
                topicTitle = title,
                learnerLevel = learnerLevel,
                vocabulary = allowedWords,
                grammarSummaries = allowedGrammar,
                welcomeMessage = welcomeMessage,
                speakWelcome = true
            )
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
        coordinator.onMicrophoneButtonTapped()
    }

    fun stopConversation() {
        coordinator.stopConversation()
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
            autoResumeListeningAfterTts = false
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
 * - Requests `RECORD_AUDIO` runtime permission via Jetpack Compose [MicrophonePermissionGate].
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

    private val requestAudioPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        if (isGranted) {
            viewModel.onMicrophonePermissionGranted(requestedTopicId)
            viewModel.onMicrophoneButtonTapped()
        } else {
            viewModel.onMicrophonePermissionRevoked()
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

        transcriptAdapter = AiChatTranscriptAdapter { englishText ->
            viewModel.speakMessage(englishText)
        }

        binding.rvSpeakingTranscript.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpeakingTranscript.adapter = transcriptAdapter
        binding.waveformTutorHero.setMode(CosmicWaveformView.Mode.BARS)
        binding.waveformUserLive.setMode(CosmicWaveformView.Mode.BARS)

        // 1. Setup Jetpack Compose Runtime Permission Gate
        binding.composePermissionGate.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    MicrophonePermissionGate(
                        onPermissionGranted = {
                            viewModel.onMicrophonePermissionGranted(requestedTopicId)
                        },
                        onPermissionRevoked = {
                            viewModel.onMicrophonePermissionRevoked()
                        },
                        onNavigateBack = {
                            navigateBackSafely()
                        }
                    )
                }
            }
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
                        }
                    )
                }
            }
        }

        binding.btnBack.setOnClickListener {
            viewModel.endAndRecordSession {}
            navigateBackSafely()
        }

        binding.btnEndSpeakingSession.setOnClickListener {
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
                viewModel.onMicrophonePermissionGranted(requestedTopicId)
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

                        val mins = state.durationSeconds / 60
                        val secs = state.durationSeconds % 60
                        binding.tvSpeakingDuration.text = String.format(Locale.US, "%02d:%02d", mins, secs)

                        updateMicrophoneVisualState(state)
                        updateLiveSpeechCard(state)

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
        if (state.isListeningOrSpeakingUser) {
            binding.btnMicrophoneSpeak.setColorFilter(
                if (state.isMuted) Color.parseColor("#FBBF24") else Color.WHITE
            )
            val amp = if (state.isMuted) 0f else state.audioAmplitude.coerceIn(0f, 1f)
            val auraScale = 1.08f + amp * 0.36f
            binding.ivAiCharacterAura.scaleX = auraScale
            binding.ivAiCharacterAura.scaleY = auraScale
            binding.btnMicrophoneSpeak.scaleX = 1.08f
            binding.btnMicrophoneSpeak.scaleY = 1.08f
        } else if (state.isAiSpeaking) {
            binding.btnMicrophoneSpeak.setColorFilter(Color.parseColor("#00E5FF"))
            binding.ivAiCharacterAura.scaleX = 1.05f
            binding.ivAiCharacterAura.scaleY = 1.05f
            binding.viewTutorSpeakingBar.scaleX = 1.35f
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        } else {
            binding.btnMicrophoneSpeak.setColorFilter(Color.WHITE)
            binding.ivAiCharacterAura.scaleX = 1.0f
            binding.ivAiCharacterAura.scaleY = 1.0f
            binding.viewTutorSpeakingBar.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        }
    }

    private fun navigateBackSafely() {
        val nav = findNavController()
        if (!nav.popBackStack()) {
            nav.navigateUp()
        }
    }

    override fun onStop() {
        // Safely stop all active STT/Gemini/TTS jobs when leaving the screen (Test 9)
        viewModel.stopConversation()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
