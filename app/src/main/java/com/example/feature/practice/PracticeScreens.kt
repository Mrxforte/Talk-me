package com.example.feature.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.core.common.AppConstants
import com.example.databinding.FragmentExerciseSessionBinding
import com.example.databinding.FragmentPracticeBinding
import com.example.domain.model.ExerciseModel
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory
import com.example.domain.repository.LearningRepository
import com.example.domain.usecase.CurriculumDomainLogic
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Practice Center hub Fragment.
 * Launches Reading, Writing, Speaking AI, Listening, Grammar, Vocabulary, and 3D Flip Cards
 * strictly filtered to previously studied material.
 */
@AndroidEntryPoint
class PracticeFragment : Fragment() {

    private var _binding: FragmentPracticeBinding? = null
    private val binding get() = _binding!!

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

        binding.cardPracticeFlipCards.setOnClickListener {
            findNavController().navigate(R.id.vocabularyTopicsFragment)
        }

        binding.cardPracticeReading.setOnClickListener {
            openExerciseSession(PracticeCategory.READING)
        }

        binding.cardPracticeWriting.setOnClickListener {
            openExerciseSession(PracticeCategory.WRITING)
        }

        binding.cardPracticeSpeaking.setOnClickListener {
            findNavController().navigate(R.id.speakingAiFragment)
        }

        binding.cardPracticeListening.setOnClickListener {
            openExerciseSession(PracticeCategory.LISTENING)
        }

        binding.cardPracticeGrammar.setOnClickListener {
            openExerciseSession(PracticeCategory.GRAMMAR)
        }

        binding.cardPracticeVocabulary.setOnClickListener {
            openExerciseSession(PracticeCategory.VOCABULARY)
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

    override fun onDestroyView() {
        super.onDestroyView()
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
    val isSessionFinished: Boolean = false
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
                currentIndex = 0
            )
            eligible.firstOrNull()?.audioTextToSpeak?.let { audioText ->
                if (category == PracticeCategory.LISTENING) {
                    ttsManager.speakEnglish(audioText)
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
            ttsManager.speakEnglish(text)
        }
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
                        viewModelScope.launch { ttsManager.speakEnglish(audio) }
                    }
                }
            } else {
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
}

/**
 * Reusable Exercise Session Fragment supporting Multiple Choice, Word Matching,
 * Translation, Listening Comprehension, Reading Comprehension, and Grammar Correction.
 */
@AndroidEntryPoint
class ExerciseSessionFragment : Fragment() {

    private var _binding: FragmentExerciseSessionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExerciseSessionViewModel by viewModels()

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

        binding.headerExercise.btnBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.btnPlayQuestionAudio.setOnClickListener {
            viewModel.playCurrentQuestionAudio()
        }

        binding.btnSubmitOrNext.setOnClickListener {
            val typed = binding.etWrittenAnswer.text?.toString().orEmpty()
            viewModel.submitOrAdvance(typed)
        }

        binding.btnFinishSession.setOnClickListener {
            if (isSequentialPlan) {
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
                viewModel.uiState.collect { state ->
                    binding.headerExercise.tvHeaderTitle.text = state.category.displayName

                    if (state.isSessionFinished) {
                        binding.layoutActiveQuestion.visibility = View.GONE
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
                            getString(R.string.exercise_btn_finish)
                        }
                        binding.progressExercise.progress = 100
                        return@collect
                    }

                    val current = state.currentExercise ?: return@collect
                    binding.layoutActiveQuestion.visibility = View.VISIBLE
                    binding.layoutSessionSummary.visibility = View.GONE

                    val total = state.exercises.size.coerceAtLeast(1)
                    val progressText = getString(
                        R.string.exercise_progress_format,
                        state.currentIndex + 1,
                        total
                    )
                    binding.headerExercise.tvHeaderSubtitle.text = if (state.topicTitle.isNotBlank()) {
                        "${state.topicTitle} • $progressText"
                    } else {
                        progressText
                    }
                    binding.progressExercise.progress = ((state.currentIndex + 1) * 100) / total

                    binding.tvExerciseTypeBadge.text = current.exerciseType.name.replace("_", " ")
                    binding.tvQuestionPrompt.text = current.promptText
                    binding.tvQuestionSubPrompt.text = current.subPromptText
                    binding.btnPlayQuestionAudio.visibility =
                        if (current.audioTextToSpeak.isNullOrBlank()) View.GONE else View.VISIBLE

                    // Render either Multiple Choice buttons or Written Input field
                    val isWrittenType = current.options.isEmpty() ||
                        current.exerciseType == ExerciseType.TRANSLATION ||
                        current.exerciseType == ExerciseType.WRITING_ANSWERS

                    if (isWrittenType) {
                        binding.containerOptions.visibility = View.GONE
                        binding.tilWrittenAnswer.visibility = View.VISIBLE
                        if (!state.hasSubmittedCurrent && state.selectedOrTypedAnswer.isEmpty()) {
                            binding.etWrittenAnswer.setText("")
                        }
                    } else {
                        binding.tilWrittenAnswer.visibility = View.GONE
                        binding.containerOptions.visibility = View.VISIBLE
                        binding.containerOptions.removeAllViews()
                        current.options.forEach { optionText ->
                            val btn = MaterialButton(
                                requireContext(),
                                null,
                                com.google.android.material.R.attr.materialButtonOutlinedStyle
                            ).apply {
                                layoutParams = ViewGroup.MarginLayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                ).apply {
                                    bottomMargin = resources.getDimensionPixelSize(R.dimen.spacing_small)
                                }
                                text = optionText
                                isAllCaps = false
                                val isSelected = state.selectedOrTypedAnswer == optionText
                                if (isSelected) {
                                    setBackgroundColor(
                                        ContextCompat.getColor(requireContext(), R.color.color_primary_light)
                                    )
                                }
                                setOnClickListener {
                                    viewModel.selectAnswer(optionText)
                                }
                            }
                            binding.containerOptions.addView(btn)
                        }
                    }

                    if (state.hasSubmittedCurrent) {
                        binding.cardFeedback.visibility = View.VISIBLE
                        if (state.isCurrentCorrect) {
                            binding.tvFeedbackTitle.text = getString(R.string.exercise_feedback_correct)
                            binding.tvFeedbackTitle.setTextColor(
                                ContextCompat.getColor(requireContext(), R.color.color_success)
                            )
                        } else {
                            binding.tvFeedbackTitle.text = getString(R.string.exercise_feedback_incorrect)
                            binding.tvFeedbackTitle.setTextColor(
                                ContextCompat.getColor(requireContext(), R.color.color_error)
                            )
                        }
                        binding.tvFeedbackExplanation.text =
                            "Javob: ${current.correctAnswer}\n${current.explanationUzbek}"
                        binding.btnSubmitOrNext.text = getString(R.string.exercise_btn_next)
                    } else {
                        binding.cardFeedback.visibility = View.GONE
                        binding.btnSubmitOrNext.text = getString(R.string.exercise_btn_check)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
