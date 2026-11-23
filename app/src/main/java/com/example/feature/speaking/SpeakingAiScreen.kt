package com.example.feature.speaking

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
import com.example.feature.avatar3d.UnityAvatarBridge
import com.example.feature.avatar3d.UnityRuntimeSender
import com.example.feature.pro.AvatarPerformance
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
    private var manualStartWhenReady = false

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

                val extra = if (section != null) null else withContext(Dispatchers.IO) {
                    learningRepository.getEligibleExercises(PracticeCategory.SPEAKING, null, topicId)
                        .filter { it.id.startsWith("extra_") }.randomOrNull()
                }
                coordinator.configureLessonContext(
                    topicId = topicId,
                    topicTitle = extra?.promptText ?: title,
                    learnerLevel = learnerLevel,
                    vocabulary = allowedWords,
                    grammarSummaries = allowedGrammar,
                    speakWelcome = false
                )
                isSessionReady = true
                if (manualStartWhenReady) {
                    manualStartWhenReady = false
                    coordinator.onMicrophoneButtonTapped()
                } else if (startWhenReady) startLiveSession()
            } catch (error: kotlinx.coroutines.CancellationException) {
                throw error
            } catch (error: Exception) {
                android.util.Log.w("SpeakingAi", "Could not load speaking lesson", error)
                startWhenReady = false
                manualStartWhenReady = false
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

    fun startLiveSession() {
        if (!isSessionReady) {
            startWhenReady = true
            return
        }
        startWhenReady = false
        if (!coordinator.uiState.value.isHandsFreeConversationActive) coordinator.startConversation()
    }

    fun onMicrophoneButtonTapped() {
        if (isSessionReady) coordinator.onMicrophoneButtonTapped() else manualStartWhenReady = true
    }

    fun stopConversation() {
        startWhenReady = false
        manualStartWhenReady = false
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

/** Owns the foreground live tutor session and releases capture when the screen is paused. */
@AndroidEntryPoint
class SpeakingAiFragment : Fragment() {

    private var _binding: FragmentSpeakingAiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpeakingAiViewModel by viewModels()
    private lateinit var transcriptAdapter: AiChatTranscriptAdapter
    private var femaleTutor = false
    private var recordAfterPermissionGrant = false
    private var avatarSpeakingTurnId: String? = null

    private val requestAudioPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        val shouldRecord = recordAfterPermissionGrant
        recordAfterPermissionGrant = false
        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        if (isGranted) {
            viewModel.onMicrophonePermissionGranted(requestedTopicId, arguments?.getString(AppConstants.ARG_SECTION_ID))
            recordAfterPermissionGrant = shouldRecord
            if (shouldRecord && isResumed) {
                recordAfterPermissionGrant = false
                viewModel.onMicrophoneButtonTapped()
            }
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val statusBarInset = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            binding.layoutTopBar.setPadding(
                binding.layoutTopBar.paddingLeft,
                statusBarInset + (12 * resources.displayMetrics.density).toInt(),
                binding.layoutTopBar.paddingRight,
                binding.layoutTopBar.paddingBottom
            )
            insets
        }

        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        viewModel.initializeSpeakingSession(requestedTopicId, arguments?.getString(AppConstants.ARG_SECTION_ID))
        val tutorNames = resources.getStringArray(R.array.speaking_tutor_names)

        val inLessonFlow = arguments?.getInt(AppConstants.ARG_LESSON_FLOW_STAGE) == 5
        transcriptAdapter = AiChatTranscriptAdapter(showSpeaker = false) {}

        binding.rvSpeakingTranscript.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpeakingTranscript.adapter = transcriptAdapter
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

        // First tap records; the next tap sends the completed audio turn.
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
                recordAfterPermissionGrant = true
                requestMicrophone()
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


        val liveText = if (state.isManualRecording) {
            getString(if (state.isAudioCaptureActuallyRunning) R.string.speaking_manual_recording_hint
                else R.string.speaking_manual_ready_hint)
        } else when (val convState = state.conversationState) {
            is ConversationState.LISTENING -> {
                if (state.isAudioCaptureActuallyRunning) {
                    if (state.isMuted) {
                        getString(R.string.speaking_muted_live_hint)
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
            is ConversationState.AI_SPEAKING -> getString(R.string.speaking_playback_live_hint)
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
        val microphoneAction = microphoneAction(state)
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
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        } else {
            binding.btnMicrophoneSpeak.setColorFilter(ContextCompat.getColor(requireContext(), R.color.color_text_on_primary))
            binding.ivAiCharacterAura.scaleX = 1.0f
            binding.ivAiCharacterAura.scaleY = 1.0f
            binding.btnMicrophoneSpeak.scaleX = 1.0f
            binding.btnMicrophoneSpeak.scaleY = 1.0f
        }
    }

    private fun microphoneAction(state: ConversationUiState): Int = when {
        state.isManualRecording || state.isListeningOrSpeakingUser -> R.string.speaking_manual_send
        state.isLoading -> R.string.speaking_mic_processing
        state.isAiSpeaking -> R.string.speaking_mic_answer_now
        else -> R.string.speaking_manual_start
    }

    private val optionalUnityAvatar by lazy { UnityAvatarBridge(UnityRuntimeSender()) }

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
        val spokenRange = viewModel.coordinator.spokenRange.value?.takeIf { it.turnId == speaking?.turnId }
        if (speaking == null) avatarSpeakingTurnId = null
        if (spokenRange != null && (spokenRange.amplitude ?: 1f) > .04f) {
            avatarSpeakingTurnId = spokenRange.turnId
        }
        optionalUnityAvatar.selectTutor(femaleTutor)
        optionalUnityAvatar.update(
            SpeakingAvatarMapper.map(
                state = state.conversationState,
                audioAmplitude = if (state.isMuted) 0f else state.audioAmplitude,
                tutorAmplitude = spokenRange?.amplitude ?: if (spokenRange != null) 0.55f else 0f
            )
        )
        val performingReply = speaking != null && avatarSpeakingTurnId == speaking.turnId
        binding.ivAiCharacterAvatar.render(
            female = femaleTutor,
            action = when {
                performingReply -> ProAction.PLAYING
                state.isListeningOrSpeakingUser -> ProAction.LISTENING
                state.isLoading -> ProAction.BUSY
                else -> ProAction.MIC
            },
            cue = spokenRange?.let { TutorCue(it.text, it.timestampMs, it.amplitude) },
            showFullHead = true,
            performance = if (performingReply && speaking != null) {
                AvatarPerformance.forReply(speaking.delivery, speaking.spokenResponse)
            } else when {
                state.isLoading || state.isAiSpeaking -> AvatarPerformance.THINKING
                state.isListeningOrSpeakingUser && !state.isMuted -> AvatarPerformance.LISTENING
                else -> AvatarPerformance.WAITING
            }
        )
    }

    private fun navigateBackSafely() {
        val nav = findNavController()
        if (!nav.popBackStack()) {
            nav.navigateUp()
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.let { window ->
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
            }
        }
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.onMicrophonePermissionGranted(
                arguments?.getString(AppConstants.ARG_TOPIC_ID),
                arguments?.getString(AppConstants.ARG_SECTION_ID)
            )
            if (recordAfterPermissionGrant) {
                recordAfterPermissionGrant = false
                viewModel.onMicrophoneButtonTapped()
            }
        } else {
            viewModel.onMicrophonePermissionRevoked()
        }
    }

    private fun requestMicrophone() {
        requestAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    override fun onPause() {
        viewModel.stopConversation()
        activity?.window?.let { window ->
            val typedValue = TypedValue()
            requireContext().theme.resolveAttribute(android.R.attr.navigationBarColor, typedValue, true)
            window.navigationBarColor = typedValue.data

            val sbTyped = TypedValue()
            requireContext().theme.resolveAttribute(android.R.attr.statusBarColor, sbTyped, true)
            window.statusBarColor = sbTyped.data
        }
        super.onPause()
    }

    override fun onDestroyView() {
        binding.rvSpeakingTranscript.adapter = null
        super.onDestroyView()
        _binding = null
    }
}
