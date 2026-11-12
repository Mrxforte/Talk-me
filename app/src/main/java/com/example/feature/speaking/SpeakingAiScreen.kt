package com.example.feature.speaking

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
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
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.AiChatTranscriptAdapter
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
                viewModel.uiState.collect { state ->
                    binding.tvSpeakingCurrentTopic.text = state.topicTitle
                    binding.tvSpeakingConnectionState.text = state.statusMessage

                    val mins = state.durationSeconds / 60
                    val secs = state.durationSeconds % 60
                    binding.tvSpeakingDuration.text = String.format(Locale.US, "%02d:%02d", mins, secs)

                    updateMicrophoneVisualState(state)

                    when (val convState = state.conversationState) {
                        is ConversationState.LISTENING -> {
                            if (state.isAudioCaptureActuallyRunning) {
                                binding.tvLiveInterimSpeech.visibility = View.VISIBLE
                                binding.tvLiveInterimSpeech.text = if (state.isMuted) {
                                    "🔇 Mikrofon ovozsiz (Mute)"
                                } else {
                                    "🎙️ Ingliz tilida gapiring..."
                                }
                            } else {
                                binding.tvLiveInterimSpeech.visibility = View.GONE
                            }
                        }
                        is ConversationState.USER_SPEAKING -> {
                            binding.tvLiveInterimSpeech.visibility = View.VISIBLE
                            binding.tvLiveInterimSpeech.text = convState.provisionalTranscript
                                .ifBlank { "🎙️ Eshitilmoqda..." }
                        }
                        is ConversationState.FINALIZING -> {
                            binding.tvLiveInterimSpeech.visibility = View.VISIBLE
                            binding.tvLiveInterimSpeech.text = "⏳ Nutq yakunlandi, matn aniqlanmoqda..."
                        }
                        is ConversationState.AI_THINKING -> {
                            binding.tvLiveInterimSpeech.visibility = View.VISIBLE
                            binding.tvLiveInterimSpeech.text = "✨ AI Tutor javob tayyorlamoqda..."
                        }
                        else -> {
                            binding.tvLiveInterimSpeech.visibility = View.GONE
                        }
                    }

                    transcriptAdapter.submitList(state.transcript) {
                        if (state.transcript.isNotEmpty()) {
                            binding.rvSpeakingTranscript.scrollToPosition(state.transcript.size - 1)
                        }
                    }
                }
            }
        }
    }

    private fun updateMicrophoneVisualState(state: ConversationUiState) {
        binding.btnMicrophoneSpeak.isEnabled = !state.isLoading
        if (state.isListeningOrSpeakingUser) {
            binding.btnMicrophoneSpeak.setColorFilter(
                if (state.isMuted) Color.parseColor("#B45309") else Color.parseColor("#2563EB")
            )
            val amp = if (state.isMuted) 0f else state.audioAmplitude.coerceIn(0f, 1f)
            val auraScale = 1.08f + amp * 0.42f
            binding.ivAiCharacterAura.scaleX = auraScale
            binding.ivAiCharacterAura.scaleY = auraScale
            binding.btnMicrophoneSpeak.scaleX = 1.10f
            binding.btnMicrophoneSpeak.scaleY = 1.10f
        } else if (state.isAiSpeaking) {
            binding.btnMicrophoneSpeak.setColorFilter(Color.parseColor("#F59E0B"))
            binding.ivAiCharacterAura.scaleX = 1.06f
            binding.ivAiCharacterAura.scaleY = 1.06f
            binding.viewTutorSpeakingBar.scaleX = 1.35f
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        } else {
            binding.btnMicrophoneSpeak.setColorFilter(Color.parseColor("#0F172A"))
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
