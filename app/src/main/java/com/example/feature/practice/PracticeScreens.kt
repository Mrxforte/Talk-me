package com.example.feature.practice

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.audio.TtsPlaybackState
import com.example.core.common.AppConstants
import com.example.core.ui.CosmicWaveformView
import com.example.core.ui.ProgressAnimator.animateProgressTo
import com.example.core.ui.ProgressAnimator.replayAllProgressFromZero
import com.example.databinding.FragmentExerciseSessionBinding
import com.example.databinding.FragmentPracticeBinding
import com.example.domain.model.ExerciseModel
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory
import com.example.domain.repository.LearningRepository
import com.example.domain.usecase.CurriculumDomainLogic
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@HiltViewModel
class PracticeViewModel @Inject constructor(
    learningRepository: LearningRepository
) : ViewModel() {

    val completedStepsCount: StateFlow<Int> = learningRepository
        .observeTodayTaskSteps(AppConstants.INITIAL_TOPIC_ID)
        .map { steps -> steps.take(5).count { it.isCompletedToday } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = 0
        )
}

/**
 * Practice Center hub Fragment.
 * Launches Reading, Writing, Speaking AI, Listening, Grammar, Vocabulary, and 3D Flip Cards
 * strictly filtered to previously studied material.
 */
@AndroidEntryPoint
class PracticeFragment : Fragment() {

    private var _binding: FragmentPracticeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PracticeViewModel by viewModels()
    private var wasStopped = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPracticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.completedStepsCount.collect { done ->
                    val clamped = done.coerceIn(0, 5)
                    binding.tvPracticeDailyGoal.text = "Bugungi maqsad • $clamped/5"
                    binding.progressPracticeDailyGoal.animateProgressTo(clamped, maxValue = 5)
                }
            }
        }

        // Hero Banner: "Aralash mashq • 5 daqiqalik challenge • Boshlash ▶"
        binding.cardPracticeFlipCards.setOnClickListener {
            openExerciseSession(PracticeCategory.SECTION_QUIZ)
        }

        // Row 1: Speaking & Listening
        binding.cardPracticeSpeaking.setOnClickListener {
            findNavController().navigate(R.id.speakingAiFragment)
        }

        binding.cardPracticeListening.setOnClickListener {
            openExerciseSession(PracticeCategory.LISTENING)
        }

        // Row 2: Reading & Writing
        binding.cardPracticeReading.setOnClickListener {
            openExerciseSession(PracticeCategory.READING)
        }

        binding.cardPracticeWriting.setOnClickListener {
            openExerciseSession(PracticeCategory.WRITING)
        }

        // Row 3: Vocabulary (Flipcard • 12 ta) & Grammar
        binding.cardPracticeVocabulary.setOnClickListener {
            findNavController().navigate(R.id.vocabularyTopicsFragment)
        }

        binding.cardPracticeGrammar.setOnClickListener {
            openExerciseSession(PracticeCategory.GRAMMAR)
        }

        binding.btnOpenReadingAiCoach.setOnClickListener {
            findNavController().navigate(R.id.readingAiFragment)
        }
    }

    private fun openExerciseSession(category: PracticeCategory) {
        findNavController().navigate(
            R.id.exerciseSessionFragment,
            bundleOf(AppConstants.ARG_PRACTICE_CATEGORY to category.id)
        )
    }

    override fun onStart() {
        super.onStart()
        if (wasStopped) {
            wasStopped = false
            _binding?.root?.replayAllProgressFromZero()
        }
    }

    override fun onStop() {
        super.onStop()
        wasStopped = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        wasStopped = false
        _binding = null
    }
}

data class ExerciseSessionUiState(
    val category: PracticeCategory = PracticeCategory.VOCABULARY,
    val topicTitle: String = "",
    val exercises: List<ExerciseModel> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOrTypedAnswer: String = "",
    val hasSubmittedCurrent: Boolean = false,
    val isCurrentCorrect: Boolean = false,
    val correctCount: Int = 0,
    val isSessionFinished: Boolean = false,
    val isLoaded: Boolean = false
) {
    val currentExercise: ExerciseModel?
        get() = exercises.getOrNull(currentIndex)
}

@HiltViewModel
class ExerciseSessionViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExerciseSessionUiState())
    val uiState: StateFlow<ExerciseSessionUiState> = _uiState.asStateFlow()

    val ttsPlaybackState: StateFlow<TtsPlaybackState> = ttsManager.playbackState

    private val _selectedSpeed = MutableStateFlow(TtsManager.SPEED_NORMAL)
    val selectedSpeed: StateFlow<Float> = _selectedSpeed.asStateFlow()

    private val masteredGrammarIds = mutableListOf<String>()
    private val memorizedVocabIds = mutableListOf<String>()

    fun startSession(categoryId: String, sectionIdFilter: String?, topicIdFilter: String? = null) {
        if (_uiState.value.exercises.isNotEmpty()) return
        val category = runCatching { PracticeCategory.valueOf(categoryId) }
            .getOrDefault(PracticeCategory.VOCABULARY)

        viewModelScope.launch {
            val topicModel = topicIdFilter?.let { learningRepository.getTopicById(it) }
            val eligible = learningRepository.getEligibleExercises(
                category = category,
                sectionIdFilter = sectionIdFilter,
                topicIdFilter = topicIdFilter
            )
            _uiState.value = ExerciseSessionUiState(
                category = category,
                topicTitle = topicModel?.let { "${it.orderIndex}. ${it.titleEnglish} — ${it.titleUzbek}" }.orEmpty(),
                exercises = eligible,
                currentIndex = 0,
                isLoaded = true
            )
            eligible.firstOrNull()?.audioTextToSpeak?.let { audioText ->
                if (category == PracticeCategory.LISTENING) {
                    ttsManager.speakEnglish(audioText, overrideSpeed = _selectedSpeed.value)
                }
            }
        }
    }

    fun selectAnswer(answer: String) {
        if (_uiState.value.hasSubmittedCurrent) return
        _uiState.value = _uiState.value.copy(selectedOrTypedAnswer = answer)
    }

    fun playCurrentQuestionAudio() {
        val text = _uiState.value.currentExercise?.audioTextToSpeak ?: return
        viewModelScope.launch {
            ttsManager.speakEnglish(text, overrideSpeed = _selectedSpeed.value)
        }
    }

    fun playTextAudio(text: String) {
        if (text.isBlank()) return
        viewModelScope.launch {
            ttsManager.speakEnglish(text, overrideSpeed = _selectedSpeed.value)
        }
    }

    fun toggleListeningPlayPause() {
        val playing = ttsPlaybackState.value is TtsPlaybackState.Playing ||
            ttsPlaybackState.value is TtsPlaybackState.Loading
        if (playing) {
            ttsManager.stop()
        } else {
            playCurrentQuestionAudio()
        }
    }

    fun cyclePlaybackSpeed() {
        val next = when (_selectedSpeed.value) {
            TtsManager.SPEED_SLOW -> TtsManager.SPEED_NORMAL
            TtsManager.SPEED_NORMAL -> TtsManager.SPEED_FAST
            else -> TtsManager.SPEED_SLOW
        }
        _selectedSpeed.value = next
        if (ttsPlaybackState.value is TtsPlaybackState.Playing) {
            playCurrentQuestionAudio()
        }
    }

    fun stopAudio() {
        ttsManager.stop()
    }

    fun submitOrAdvance(typedAnswerFallback: String) {
        val state = _uiState.value
        val current = state.currentExercise ?: return

        if (!state.hasSubmittedCurrent) {
            val candidate = state.selectedOrTypedAnswer.ifBlank { typedAnswerFallback }
            val isCorrect = CurriculumDomainLogic.evaluateExerciseAnswer(current, candidate)
            if (isCorrect) {
                masteredGrammarIds.addAll(current.grammarRuleIds)
                memorizedVocabIds.addAll(current.vocabularyIds)
                viewModelScope.launch {
                    learningRepository.resolveMistakeByAnswer(
                        topicId = current.topicId,
                        correctAnswer = current.correctAnswer
                    )
                }
            } else {
                viewModelScope.launch {
                    learningRepository.recordUserMistake(
                        topicId = current.topicId,
                        category = state.category,
                        itemPrompt = current.promptText,
                        userWrongAnswer = candidate.ifBlank { "(bo'sh javob)" },
                        correctAnswer = current.correctAnswer,
                        explanationUzbek = current.explanationUzbek
                    )
                }
            }
            _uiState.value = state.copy(
                selectedOrTypedAnswer = candidate,
                hasSubmittedCurrent = true,
                isCurrentCorrect = isCorrect,
                correctCount = if (isCorrect) state.correctCount + 1 else state.correctCount
            )
        } else {
            val nextIdx = state.currentIndex + 1
            if (nextIdx < state.exercises.size) {
                val nextEx = state.exercises[nextIdx]
                _uiState.value = state.copy(
                    currentIndex = nextIdx,
                    selectedOrTypedAnswer = "",
                    hasSubmittedCurrent = false,
                    isCurrentCorrect = false
                )
                nextEx.audioTextToSpeak?.let { audio ->
                    if (state.category == PracticeCategory.LISTENING) {
                        viewModelScope.launch {
                            ttsManager.speakEnglish(audio, overrideSpeed = _selectedSpeed.value)
                        }
                    }
                }
            } else {
                ttsManager.stop()
                viewModelScope.launch {
                    val firstEx = state.exercises.firstOrNull()
                    learningRepository.recordPracticeSessionResult(
                        category = state.category,
                        topicId = firstEx?.topicId ?: AppConstants.INITIAL_TOPIC_ID,
                        sectionId = firstEx?.sectionId ?: AppConstants.INITIAL_SECTION_ID,
                        correctCount = state.correctCount,
                        totalCount = state.exercises.size,
                        masteredGrammarRuleIds = masteredGrammarIds,
                        memorizedVocabularyIds = memorizedVocabIds
                    )
                    _uiState.value = state.copy(isSessionFinished = true)
                }
            }
        }
    }

    override fun onCleared() {
        ttsManager.stop()
        super.onCleared()
    }
}

/**
 * Cosmic Studio Exercise Session Fragment supporting Reading Studio, Writing Studio,
 * Listening Studio, Grammar, Vocabulary, and Section Quizzes.
 */
@AndroidEntryPoint
class ExerciseSessionFragment : Fragment() {

    private var _binding: FragmentExerciseSessionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExerciseSessionViewModel by viewModels()
    private var isWritingSampleExpanded: Boolean = false
    private var listeningTimerJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExerciseSessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = arguments?.getString(AppConstants.ARG_PRACTICE_CATEGORY)
            ?: PracticeCategory.VOCABULARY.id
        val sectionId = arguments?.getString(AppConstants.ARG_SECTION_ID)
        val topicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        val isSequentialPlan = arguments?.getBoolean(AppConstants.ARG_SEQUENTIAL_DAILY_PLAN, false) ?: false

        binding.waveformListeningRing.setMode(CosmicWaveformView.Mode.RING)

        val closeAction = View.OnClickListener {
            viewModel.stopAudio()
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }
        binding.btnStudioBack.setOnClickListener(closeAction)
        binding.btnStudioClose.setOnClickListener(closeAction)
        binding.headerExercise.btnBack.setOnClickListener(closeAction)

        binding.btnPlayQuestionAudio.setOnClickListener {
            viewModel.playCurrentQuestionAudio()
        }

        binding.btnListeningPlayPause.setOnClickListener {
            viewModel.toggleListeningPlayPause()
        }

        binding.btnListeningReplay.setOnClickListener {
            viewModel.playCurrentQuestionAudio()
        }

        binding.btnListeningSpeed.setOnClickListener {
            viewModel.cyclePlaybackSpeed()
        }

        binding.btnWritingSampleToggle.setOnClickListener {
            isWritingSampleExpanded = !isWritingSampleExpanded
            binding.cardWritingSample.visibility = if (isWritingSampleExpanded) View.VISIBLE else View.GONE
            binding.ivWritingSampleChevron.rotation = if (isWritingSampleExpanded) 180f else 0f
        }

        binding.btnSubmitOrNext.setOnClickListener {
            val typed = binding.etWrittenAnswer.text?.toString().orEmpty()
            viewModel.submitOrAdvance(typed)
        }

        binding.btnFinishSession.setOnClickListener {
            if (isSequentialPlan && viewModel.uiState.value.exercises.isNotEmpty()) {
                val activeTopicId = topicId ?: AppConstants.INITIAL_TOPIC_ID
                val currentCategory = viewModel.uiState.value.category
                val nextCategory = when (currentCategory) {
                    PracticeCategory.GRAMMAR -> PracticeCategory.VOCABULARY
                    PracticeCategory.VOCABULARY -> PracticeCategory.READING
                    PracticeCategory.READING -> PracticeCategory.WRITING
                    PracticeCategory.WRITING -> PracticeCategory.LISTENING
                    PracticeCategory.LISTENING -> PracticeCategory.SPEAKING
                    else -> null
                }
                val nav = findNavController()
                nav.popBackStack()
                when (nextCategory) {
                    PracticeCategory.VOCABULARY -> {
                        nav.navigate(
                            R.id.flipCardFragment,
                            bundleOf(
                                AppConstants.ARG_TOPIC_ID to activeTopicId,
                                AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to true
                            )
                        )
                    }
                    PracticeCategory.SPEAKING -> {
                        nav.navigate(
                            R.id.speakingAiFragment,
                            bundleOf(
                                AppConstants.ARG_TOPIC_ID to activeTopicId,
                                AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to true
                            )
                        )
                    }
                    null -> Unit
                    else -> {
                        nav.navigate(
                            R.id.exerciseSessionFragment,
                            bundleOf(
                                AppConstants.ARG_PRACTICE_CATEGORY to nextCategory.id,
                                AppConstants.ARG_TOPIC_ID to activeTopicId,
                                AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to true
                            )
                        )
                    }
                }
            } else {
                findNavController().navigateUp()
            }
        }

        viewModel.startSession(categoryId, sectionId, topicId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { state ->
                        renderSessionUi(state, isSequentialPlan)
                    }
                }
                launch {
                    viewModel.selectedSpeed.collect { speed ->
                        val formatted = when (speed) {
                            TtsManager.SPEED_SLOW -> "0.75"
                            TtsManager.SPEED_FAST -> "1.25"
                            else -> "1.0"
                        }
                        binding.tvListeningSpeedLabel.text = getString(R.string.studio_speed_format, formatted)
                    }
                }
                launch {
                    viewModel.ttsPlaybackState.collect { playbackState ->
                        updateListeningAudioVisuals(playbackState)
                    }
                }
            }
        }
    }

    private fun renderSessionUi(state: ExerciseSessionUiState, isSequentialPlan: Boolean) {
        binding.headerExercise.tvHeaderTitle.text = state.category.displayName
        binding.tvStudioTitle.text = formatStudioTitle(state.category)

        if (state.isLoaded && state.exercises.isEmpty()) {
            binding.layoutActiveQuestion.visibility = View.GONE
            binding.btnSubmitOrNext.visibility = View.GONE
            binding.layoutSessionSummary.visibility = View.VISIBLE
            binding.tvSessionSummaryScore.text = getString(R.string.curriculum_empty)
            binding.btnFinishSession.text = getString(R.string.exercise_btn_finish)
            binding.progressExercise.animateProgressTo(0, maxValue = 100, durationMs = 400L)
            binding.tvStudioProgressCounter.text = "0 / 0"
            return
        }

        if (state.isSessionFinished) {
            binding.layoutActiveQuestion.visibility = View.GONE
            binding.btnSubmitOrNext.visibility = View.GONE
            binding.layoutSessionSummary.visibility = View.VISIBLE
            val total = state.exercises.size.coerceAtLeast(1)
            val pct = (state.correctCount * 100) / total
            binding.tvSessionSummaryScore.text = getString(
                R.string.exercise_session_score_format,
                pct,
                state.correctCount,
                state.exercises.size
            )
            binding.btnFinishSession.text = if (isSequentialPlan) {
                "Keyingi kunlik mashqqa o'tish →"
            } else {
                getString(R.string.studio_btn_finish)
            }
            binding.progressExercise.animateProgressTo(100, maxValue = 100, durationMs = 550L)
            return
        }

        val current = state.currentExercise ?: return
        binding.layoutActiveQuestion.visibility = View.VISIBLE
        binding.btnSubmitOrNext.visibility = View.VISIBLE
        binding.layoutSessionSummary.visibility = View.GONE

        val total = state.exercises.size.coerceAtLeast(1)
        val counterText = getString(R.string.studio_counter_format, state.currentIndex + 1, total)
        binding.tvStudioProgressCounter.text = counterText
        binding.headerExercise.tvHeaderSubtitle.text = if (state.topicTitle.isNotBlank()) {
            "${state.topicTitle} • $counterText"
        } else {
            counterText
        }
        binding.progressExercise.animateProgressTo(((state.currentIndex + 1) * 100) / total, maxValue = 100, durationMs = 600L)

        // Toggle studio mode headers
        val isReading = state.category == PracticeCategory.READING
        val isWriting = state.category == PracticeCategory.WRITING
        val isListening = state.category == PracticeCategory.LISTENING

        binding.layoutReadingStudioSection.visibility = if (isReading) View.VISIBLE else View.GONE
        binding.layoutWritingStudioSection.visibility = if (isWriting) View.VISIBLE else View.GONE
        binding.layoutListeningStudioSection.visibility = if (isListening) View.VISIBLE else View.GONE

        if (isReading) {
            renderReadingPassageCard(state, current)
        }

        if (isWriting) {
            renderWritingStudioHeader(state, current, total)
        }

        val isWrittenType = current.options.isEmpty() ||
            current.exerciseType == ExerciseType.TRANSLATION ||
            current.exerciseType == ExerciseType.WRITING_ANSWERS

        // Question card shown for non-writing or when multiple-choice options exist
        binding.cardStudioQuestion.visibility = if (isWriting && isWrittenType) View.GONE else View.VISIBLE
        binding.tvExerciseTypeBadge.text = current.exerciseType.name.replace("_", " ")
        binding.tvExerciseTypeBadge.visibility =
            if (!isReading && !isListening && !isWriting) View.VISIBLE else View.GONE
        binding.tvQuestionPrompt.text = current.promptText
        binding.tvQuestionSubPrompt.text = current.subPromptText
        binding.tvQuestionSubPrompt.visibility =
            if (isReading || current.subPromptText.isBlank()) View.GONE else View.VISIBLE
        binding.btnPlayQuestionAudio.visibility =
            if (isListening || current.audioTextToSpeak.isNullOrBlank()) View.GONE else View.VISIBLE

        if (isWrittenType) {
            binding.containerOptions.visibility = View.GONE
            binding.layoutWritingInputBox.visibility = View.VISIBLE
            if (!state.hasSubmittedCurrent && state.selectedOrTypedAnswer.isEmpty()) {
                binding.etWrittenAnswer.setText("")
            }
        } else {
            binding.layoutWritingInputBox.visibility = View.GONE
            binding.containerOptions.visibility = View.VISIBLE
            renderRadioOptions(current, state)
        }

        if (state.hasSubmittedCurrent) {
            binding.cardFeedback.visibility = View.VISIBLE
            if (state.isCurrentCorrect) {
                binding.cardFeedback.setStrokeColor(ContextCompat.getColor(requireContext(), R.color.color_neon_green))
                binding.cardFeedback.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_cosmic_success_bg))
                binding.tvFeedbackTitle.text = getString(R.string.exercise_feedback_correct)
                binding.tvFeedbackTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_neon_green))
            } else {
                binding.cardFeedback.setStrokeColor(ContextCompat.getColor(requireContext(), R.color.color_error))
                binding.cardFeedback.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_cosmic_error_bg))
                binding.tvFeedbackTitle.text = getString(R.string.exercise_feedback_incorrect)
                binding.tvFeedbackTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_error))
            }
            binding.tvFeedbackExplanation.text =
                "Javob: ${current.correctAnswer}\n${current.explanationUzbek}"
            binding.btnSubmitOrNext.text = getString(R.string.studio_btn_next)
        } else {
            binding.cardFeedback.visibility = View.GONE
            binding.btnSubmitOrNext.text = if (isListening) {
                getString(R.string.studio_btn_check_answer)
            } else {
                getString(R.string.studio_btn_check)
            }
        }
    }

    private fun formatStudioTitle(category: PracticeCategory): CharSequence {
        val (firstWord, rest) = when (category) {
            PracticeCategory.READING -> "Reading" to " Studio"
            PracticeCategory.WRITING -> "Writing" to " Studio"
            PracticeCategory.LISTENING -> "Listening" to " Studio"
            else -> return category.displayName
        }
        return SpannableStringBuilder(firstWord).apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, firstWord.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            append(rest)
        }
    }

    private fun renderReadingPassageCard(state: ExerciseSessionUiState, current: ExerciseModel) {
        val rawTitle = state.topicTitle
            .substringAfter(". ", state.topicTitle)
            .substringBefore(" —")
            .trim()
            .ifBlank { getString(R.string.studio_title_reading) }
        binding.tvReadingPassageTitle.text = rawTitle

        val rawPassage = current.subPromptText.ifBlank { current.audioTextToSpeak.orEmpty() }
        val lines = rawPassage
            .split("\n", ". ")
            .map { it.trim() }
            .filter { it.isNotBlank() }

        binding.containerReadingLines.removeAllViews()
        val cyanColor = ContextCompat.getColor(requireContext(), R.color.color_neon_cyan)
        val density = resources.displayMetrics.density

        lines.forEachIndexed { idx, lineText ->
            val sentence = if (lineText.endsWith(".") || lineText.endsWith("!") || lineText.endsWith("?")) {
                lineText
            } else {
                "$lineText."
            }

            val row = LinearLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = (10 * density).toInt()
                }
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
            }

            if (idx % 2 == 0) {
                val speakerBtn = ImageButton(requireContext()).apply {
                    val minTarget = resources.getDimensionPixelSize(R.dimen.touch_target_min)
                    layoutParams = LinearLayout.LayoutParams(minTarget, minTarget).apply {
                        marginEnd = (10 * density).toInt()
                    }
                    setBackgroundResource(R.drawable.bg_studio_speaker_btn)
                    setImageResource(R.drawable.ic_speaker)
                    setColorFilter(ContextCompat.getColor(requireContext(), R.color.color_cosmic_text_secondary))
                    val pad = (12 * density).toInt()
                    setPadding(pad, pad, pad, pad)
                    scaleType = ImageView.ScaleType.FIT_CENTER
                    contentDescription = getString(R.string.exercise_play_audio)
                    setOnClickListener {
                        viewModel.playTextAudio(sentence)
                    }
                }
                row.addView(speakerBtn)
            } else {
                val spacer = View(requireContext()).apply {
                    val minTarget = resources.getDimensionPixelSize(R.dimen.touch_target_min)
                    layoutParams = LinearLayout.LayoutParams(minTarget, 1).apply {
                        marginEnd = (10 * density).toInt()
                    }
                }
                row.addView(spacer)
            }

            val tvLine = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                textSize = 15.5f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                setLineSpacing(2 * density, 1f)

                val spannable = SpannableStringBuilder(sentence)
                val highlightCandidate = sentence.split(" ")
                    .map { it.trim(',', '.', '!', '?') }
                    .maxByOrNull { it.length }
                if (!highlightCandidate.isNullOrBlank() && highlightCandidate.length >= 4) {
                    val start = sentence.indexOf(highlightCandidate)
                    if (start >= 0) {
                        spannable.setSpan(
                            UnderlineSpan(),
                            start,
                            start + highlightCandidate.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        spannable.setSpan(
                            ForegroundColorSpan(cyanColor),
                            start,
                            start + highlightCandidate.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                }
                text = spannable
            }
            row.addView(tvLine)
            binding.containerReadingLines.addView(row)
        }
    }

    private fun renderWritingStudioHeader(
        state: ExerciseSessionUiState,
        current: ExerciseModel,
        total: Int
    ) {
        binding.tvWritingPinnedPrompt.text = current.promptText
        binding.tvWritingGapCounter.text = getString(
            R.string.studio_writing_gap_counter_format,
            state.currentIndex + 1,
            total
        )
        binding.tvWritingSampleText.text = current.subPromptText
            .ifBlank { current.explanationUzbek }
            .ifBlank { current.correctAnswer }

        binding.containerWritingGoals.removeAllViews()
        val displayCount = total.coerceIn(1, 4)
        val density = resources.displayMetrics.density
        for (i in 0 until displayCount) {
            val isDone = i < state.currentIndex ||
                (i == state.currentIndex && state.hasSubmittedCurrent && state.isCurrentCorrect)
            val row = LinearLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = (4 * density).toInt()
                }
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
            }

            val icon = ImageView(requireContext()).apply {
                val sz = (16 * density).toInt()
                layoutParams = LinearLayout.LayoutParams(sz, sz).apply {
                    marginEnd = (8 * density).toInt()
                }
                if (isDone) {
                    setImageResource(R.drawable.ic_check_green_badge)
                    setColorFilter(ContextCompat.getColor(requireContext(), R.color.color_neon_cyan))
                } else {
                    setBackgroundResource(R.drawable.bg_studio_radio_ring)
                }
                contentDescription = null
            }

            val label = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textSize = 13f
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (isDone) R.color.white else R.color.color_cosmic_text_secondary
                    )
                )
                text = if (isDone) {
                    getString(R.string.studio_writing_goal_done_format, i + 1)
                } else {
                    getString(R.string.studio_writing_goal_pending_format, i + 1)
                }
            }
            row.addView(icon)
            row.addView(label)
            binding.containerWritingGoals.addView(row)
        }
    }

    private fun renderRadioOptions(current: ExerciseModel, state: ExerciseSessionUiState) {
        binding.containerOptions.removeAllViews()
        val density = resources.displayMetrics.density
        val minTarget = resources.getDimensionPixelSize(R.dimen.touch_target_min)

        current.options.forEach { optionText ->
            val isSelected = state.selectedOrTypedAnswer == optionText
            val isCorrectOption = state.hasSubmittedCurrent && optionText == current.correctAnswer
            val isWrongSelected = state.hasSubmittedCurrent && isSelected && !state.isCurrentCorrect

            val bgRes = when {
                isCorrectOption -> R.drawable.bg_studio_option_correct
                isWrongSelected -> R.drawable.bg_studio_option_wrong
                isSelected -> R.drawable.bg_studio_option_selected
                else -> R.drawable.bg_studio_option_default
            }

            val row = LinearLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = (10 * density).toInt()
                }
                minimumHeight = minTarget
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding((14 * density).toInt(), (12 * density).toInt(), (14 * density).toInt(), (12 * density).toInt())
                setBackgroundResource(bgRes)
                isClickable = !state.hasSubmittedCurrent
                isFocusable = !state.hasSubmittedCurrent
                setOnClickListener {
                    viewModel.selectAnswer(optionText)
                }
            }

            val radioIndicator = View(requireContext()).apply {
                val sz = (18 * density).toInt()
                layoutParams = LinearLayout.LayoutParams(sz, sz).apply {
                    marginEnd = (12 * density).toInt()
                }
                setBackgroundResource(
                    if (isSelected || isCorrectOption) R.drawable.bg_studio_radio_ring_selected
                    else R.drawable.bg_studio_radio_ring
                )
            }

            val tvOption = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                text = optionText
                textSize = 15f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                if (isSelected || isCorrectOption) {
                    setTypeface(null, Typeface.BOLD)
                }
            }

            row.addView(radioIndicator)
            row.addView(tvOption)
            binding.containerOptions.addView(row)
        }
    }

    private fun updateListeningAudioVisuals(playbackState: TtsPlaybackState) {
        val currentText = viewModel.uiState.value.currentExercise?.audioTextToSpeak.orEmpty()
        val speed = viewModel.selectedSpeed.value.coerceAtLeast(0.5f)
        val estimatedTotalSec = ((currentText.length * 0.068f) / speed).toInt().coerceIn(4, 30)
        val totalFormatted = String.format(Locale.US, "00:%02d", estimatedTotalSec)

        when (playbackState) {
            is TtsPlaybackState.Playing, is TtsPlaybackState.Loading -> {
                binding.waveformListeningRing.setActive(true)
                binding.btnListeningPlayPause.setImageResource(R.drawable.ic_pause)
                if (listeningTimerJob?.isActive != true) {
                    listeningTimerJob = viewLifecycleOwner.lifecycleScope.launch {
                        var elapsedSec = 0
                        while (isActive) {
                            val clamped = elapsedSec.coerceAtMost(estimatedTotalSec)
                            binding.tvListeningTimer.text = String.format(
                                Locale.US,
                                "00:%02d / %s",
                                clamped,
                                totalFormatted
                            )
                            binding.progressListeningTrack.progress =
                                ((clamped * 100) / estimatedTotalSec).coerceIn(5, 100)
                            delay(1_000L)
                            elapsedSec++
                        }
                    }
                }
            }
            else -> {
                listeningTimerJob?.cancel()
                listeningTimerJob = null
                binding.waveformListeningRing.setActive(false)
                binding.btnListeningPlayPause.setImageResource(R.drawable.ic_play_arrow)
                binding.tvListeningTimer.text = "00:00 / $totalFormatted"
                binding.progressListeningTrack.progress = 0
            }
        }
    }

    override fun onPause() {
        listeningTimerJob?.cancel()
        viewModel.stopAudio()
        super.onPause()
    }

    override fun onDestroyView() {
        listeningTimerJob?.cancel()
        super.onDestroyView()
        _binding = null
    }
}
