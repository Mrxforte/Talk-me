package com.example.feature.lessondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.GrammarRuleAdapter
import com.example.core.ui.VocabularyAdapter
import com.example.databinding.FragmentLessonDetailBinding
import com.example.domain.model.GrammarRuleModel
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.PracticeCategory
import com.example.domain.model.VocabularyModel
import com.example.domain.repository.LearningRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class LessonDetailUiState(
    val section: LessonSectionModel? = null,
    val vocabulary: List<VocabularyModel> = emptyList(),
    val grammarRules: List<GrammarRuleModel> = emptyList(),
    val isCompletedSuccess: Boolean = false
)

@HiltViewModel
class LessonDetailViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonDetailUiState())
    val uiState: StateFlow<LessonDetailUiState> = _uiState.asStateFlow()

    fun loadLesson(topicId: String, sectionId: String) {
        viewModelScope.launch {
            learningRepository.saveLastLearningPosition(topicId, sectionId, 0)
            val section = learningRepository.getSectionById(sectionId)
            combine(
                learningRepository.observeSectionVocabulary(sectionId),
                learningRepository.observeSectionGrammarRules(sectionId)
            ) { vocab, grammar ->
                LessonDetailUiState(
                    section = section,
                    vocabulary = vocab,
                    grammarRules = grammar,
                    isCompletedSuccess = _uiState.value.isCompletedSuccess
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun speakEnglish(text: String) {
        viewModelScope.launch {
            ttsManager.speakEnglish(text)
        }
    }

    fun saveScrollOffset(topicId: String, sectionId: String, scrollY: Int) {
        viewModelScope.launch {
            learningRepository.saveLastLearningPosition(topicId, sectionId, scrollY)
        }
    }

    fun completeSection(topicId: String, sectionId: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            learningRepository.completeLessonSection(topicId, sectionId)
            _uiState.value = _uiState.value.copy(isCompletedSuccess = true)
            onComplete()
        }
    }
}

/**
 * Reusable Data-Driven Lesson Experience Fragment.
 * Renders Introduction, Uzbek Explanation, New Vocabulary (with audio playback),
 * Grammar Rules & English Examples (with audio playback), Interactive Exercises button,
 * and final Wrap-up section.
 */
@AndroidEntryPoint
class LessonDetailFragment : Fragment() {

    private var _binding: FragmentLessonDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LessonDetailViewModel by viewModels()

    private lateinit var vocabAdapter: VocabularyAdapter
    private lateinit var grammarAdapter: GrammarRuleAdapter

    private var currentTopicId: String = AppConstants.INITIAL_TOPIC_ID
    private var currentSectionId: String = AppConstants.INITIAL_SECTION_ID

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID) ?: AppConstants.INITIAL_TOPIC_ID
        currentSectionId = arguments?.getString(AppConstants.ARG_SECTION_ID) ?: AppConstants.INITIAL_SECTION_ID

        vocabAdapter = VocabularyAdapter { textToSpeak ->
            viewModel.speakEnglish(textToSpeak)
        }
        grammarAdapter = GrammarRuleAdapter { exampleToSpeak ->
            viewModel.speakEnglish(exampleToSpeak)
        }

        binding.rvLessonVocabulary.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLessonVocabulary.adapter = vocabAdapter

        binding.rvLessonGrammar.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLessonGrammar.adapter = grammarAdapter

        binding.headerLessonDetail.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnPracticeSectionExercises.setOnClickListener {
            findNavController().navigate(
                R.id.exerciseSessionFragment,
                bundleOf(
                    AppConstants.ARG_PRACTICE_CATEGORY to PracticeCategory.SECTION_QUIZ.id,
                    AppConstants.ARG_SECTION_ID to currentSectionId,
                    AppConstants.ARG_TOPIC_ID to currentTopicId
                )
            )
        }

        binding.btnCompleteSection.setOnClickListener {
            viewModel.completeSection(currentTopicId, currentSectionId) {
                if (isAdded) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.lesson_completed_banner),
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigateUp()
                }
            }
        }

        viewModel.loadLesson(currentTopicId, currentSectionId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val sec = state.section
                    if (sec != null) {
                        binding.headerLessonDetail.tvHeaderTitle.text = sec.titleEnglish
                        binding.headerLessonDetail.tvHeaderSubtitle.text = sec.titleUzbek
                        binding.ivLessonDetailBanner.setImageResource(
                            com.example.core.ui.TopicVisualArtwork.getTopicImageRes(
                                sec.topicId,
                                sec.orderIndex
                            )
                        )
                        binding.tvLessonObjective.text = sec.objectiveUzbek
                        binding.tvUzbekExplanation.text = sec.explanationUzbek
                        binding.tvWrapUpSummary.text = sec.wrapUpSummaryUzbek
                    }
                    vocabAdapter.submitList(state.vocabulary)
                    grammarAdapter.submitList(state.grammarRules)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        _binding?.scrollLessonContent?.scrollY?.let { scrollY ->
            viewModel.saveScrollOffset(currentTopicId, currentSectionId, scrollY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
