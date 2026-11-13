package com.example.feature.lessons

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
import com.example.core.common.AppConstants
import com.example.core.ui.ProgressAnimator.animateProgressTo
import com.example.core.ui.ProgressAnimator.replayAllProgressFromZero
import com.example.core.ui.SectionAdapter
import com.example.core.ui.TopicAdapter
import com.example.databinding.FragmentLessonsBinding
import com.example.databinding.FragmentTopicDetailBinding
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.repository.LearningRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CurrentLessonProgressState(
    val activeTopicId: String = AppConstants.INITIAL_TOPIC_ID,
    val activeSectionId: String = AppConstants.INITIAL_SECTION_ID,
    val activeTopicTitle: String = "",
    val activeSectionTitle: String = "",
    val completedLessonsCount: Int = 0,
    val totalLessonsCount: Int = 0
)

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            learningRepository.ensureSeeded()
        }
    }

    val topics: StateFlow<List<TopicModel>> = learningRepository.observeTopics()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    private val _selectedTopicId = MutableStateFlow(AppConstants.INITIAL_TOPIC_ID)
    val selectedTopicId: StateFlow<String> = _selectedTopicId.asStateFlow()

    private val _selectedTopic = MutableStateFlow<TopicModel?>(null)
    val selectedTopic: StateFlow<TopicModel?> = _selectedTopic.asStateFlow()

    private val _currentSectionId = MutableStateFlow(AppConstants.INITIAL_SECTION_ID)
    val currentSectionId: StateFlow<String> = _currentSectionId.asStateFlow()

    private val _currentSection = MutableStateFlow<LessonSectionModel?>(null)
    val currentSection: StateFlow<LessonSectionModel?> = _currentSection.asStateFlow()

    val sections: StateFlow<List<LessonSectionModel>> = _selectedTopicId
        .flatMapLatest { topicId -> learningRepository.observeTopicSections(topicId) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    val currentVocabulary = _currentSectionId
        .flatMapLatest { sectionId -> learningRepository.observeSectionVocabulary(sectionId) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    val currentGrammarRules = _currentSectionId
        .flatMapLatest { sectionId -> learningRepository.observeSectionGrammarRules(sectionId) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    fun loadTopicDetails(topicId: String) {
        _selectedTopicId.value = topicId
        viewModelScope.launch {
            _selectedTopic.value = learningRepository.getTopicById(topicId)
        }
    }

    fun selectLesson(topicId: String, sectionId: String) {
        _selectedTopicId.value = topicId
        _currentSectionId.value = sectionId
        viewModelScope.launch {
            learningRepository.saveLastLearningPosition(topicId, sectionId, 0)
            _selectedTopic.value = learningRepository.getTopicById(topicId)
            _currentSection.value = learningRepository.getSectionById(sectionId)
        }
    }

    fun completeLessonAndAdvance(
        topicId: String,
        sectionId: String,
        onNextReady: (nextTopicId: String, nextSectionId: String?) -> Unit
    ) {
        viewModelScope.launch {
            learningRepository.completeLessonSection(topicId, sectionId)
            val currentOrder = _currentSection.value?.orderIndex
                ?: sections.value.find { it.id == sectionId }?.orderIndex
                ?: 1
            val nextSection = sections.value.firstOrNull { it.orderIndex == currentOrder + 1 }
            if (nextSection != null) {
                selectLesson(topicId, nextSection.id)
                onNextReady(topicId, nextSection.id)
            } else {
                onNextReady(topicId, null)
            }
        }
    }
}

/**
 * Displays the complete A1–C2 TalkMe curriculum with strict unidirectional unlocking enforcement.
 */
@AndroidEntryPoint
class LessonsFragment : Fragment() {

    private var _binding: FragmentLessonsBinding? = null
    private val binding get() = _binding!!
    private var wasStopped = false

    private val viewModel: LessonsViewModel by viewModels()
    private var allTopicsCache: List<TopicModel> = emptyList()
    private val topicAdapter = TopicAdapter { topic ->
        if (topic.status != UnlockStatus.LOCKED && topic.totalSections > 0) {
            findNavController().navigate(R.id.topicDetailFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to topic.id))
        } else {
            Toast.makeText(requireContext(), getString(
                if (topic.status == UnlockStatus.LOCKED) R.string.topic_locked_toast
                else R.string.curriculum_empty), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTopics.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTopics.adapter = topicAdapter

        val continueClickListener = View.OnClickListener {
            val activeTopic = allTopicsCache.firstOrNull { it.status == UnlockStatus.AVAILABLE }
                ?.takeIf { it.totalSections > 0 } ?: return@OnClickListener
            findNavController().navigate(R.id.topicDetailFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to activeTopic.id))
        }
        binding.btnHeroContinue.setOnClickListener(continueClickListener)
        binding.cardLevelSummary.setOnClickListener(continueClickListener)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topics.collect { topicList ->
                    allTopicsCache = topicList
                    bindTopHeroSummary(topicList)
                    topicAdapter.submitList(topicList.filter { it.status != UnlockStatus.LOCKED })
                }
            }
        }
    }

    private fun bindTopHeroSummary(topicList: List<TopicModel>) {
        val active = topicList.firstOrNull { it.status == UnlockStatus.AVAILABLE }
            ?: topicList.lastOrNull { it.status == UnlockStatus.COMPLETED }
        val hasLessons = active != null && active.totalSections > 0
        binding.tvHeroLevelBadge.text = active?.titleEnglish.orEmpty()
        binding.tvHeroLevelTitle.text = active?.titleUzbek.orEmpty()
        binding.tvHeroLessonCount.text = getString(R.string.curriculum_lesson_count,
            active?.completedSections ?: 0, active?.totalSections ?: 0)
        binding.progressHeroLevel.animateProgressTo(
            targetProgress = active?.completedSections ?: 0,
            maxValue = (active?.totalSections ?: 0).coerceAtLeast(1)
        )
        binding.tvHeroNextLesson.text = if (hasLessons) active?.titleUzbek
            else getString(R.string.curriculum_empty)
        binding.btnHeroContinue.isEnabled = hasLessons
        binding.cardLevelSummary.isClickable = hasLessons
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

/**
 * Displays individual lesson sections inside an unlocked curriculum Topic.
 * Clean sublevel list screen without any static top hero banner, enforcing strict unidirectional unlocking.
 */
@AndroidEntryPoint
class TopicDetailFragment : Fragment() {

    private var _binding: FragmentTopicDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LessonsViewModel by viewModels()
    private var allSectionsCache: List<LessonSectionModel> = emptyList()
    private var selectedBatchIndex: Int = -1 // -1 means All
    private var wasStopped = false

    private val sectionAdapter = SectionAdapter { section ->
        if (section.status == UnlockStatus.LOCKED) {
            Toast.makeText(
                requireContext(),
                getString(R.string.section_locked_toast),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController().navigate(
                R.id.lessonDetailFragment,
                bundleOf(
                    AppConstants.ARG_TOPIC_ID to section.topicId,
                    AppConstants.ARG_SECTION_ID to section.id
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopicDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topicId = arguments?.getString(AppConstants.ARG_TOPIC_ID) ?: AppConstants.INITIAL_TOPIC_ID
        viewModel.loadTopicDetails(topicId)

        val navigateBackAction = View.OnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }
        binding.btnBackSubtopic.setOnClickListener(navigateBackAction)
        binding.headerTopicDetail.btnBack.setOnClickListener(navigateBackAction)

        binding.rvSections.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSections.adapter = sectionAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.selectedTopic.collect { topic ->
                        if (topic != null) {
                            val cefrCode = com.example.domain.model.CurriculumLevel.forTopic(topic.id)?.name.orEmpty()
                            binding.tvSubtopicTopTitle.text = topic.titleUzbek
                            binding.tvSubtopicTopSubtitle.text = getString(R.string.curriculum_lesson_count,
                                topic.completedSections, topic.totalSections)
                            binding.tvSubtopicLevelBadge.text = cefrCode
                            binding.tvSubtopicHeroTitle.text = topic.titleUzbek
                            binding.tvSubtopicHeroCount.text = binding.tvSubtopicTopSubtitle.text
                            binding.progressSubtopicBar.animateProgressTo(topic.progressPercent, maxValue = 100)

                            // Keep legacy header updated for any existing tests
                            binding.headerTopicDetail.tvHeaderTitle.text =
                                "$cefrCode • ${topic.titleUzbek}"
                            binding.headerTopicDetail.tvHeaderSubtitle.text =
                                "${topic.completedSections}/${topic.totalSections} dars bajarildi • ${topic.descriptionUzbek}"
                        }
                    }
                }
                launch {
                    viewModel.sections.collect { sectionList ->
                        allSectionsCache = sectionList
                        rebuildRangeChipsIfNeeded(sectionList.size)
                        applySectionRangeFilter()
                    }
                }
            }
        }
    }

    private fun rebuildRangeChipsIfNeeded(totalCount: Int) {
        val chipGroup = binding.chipGroupSectionRange
        if (totalCount <= 20) {
            binding.scrollSectionRanges.visibility = View.GONE
            return
        }
        binding.scrollSectionRanges.visibility = View.VISIBLE
        val batchCount = (totalCount + 19) / 20
        if (chipGroup.childCount == batchCount + 1) return

        chipGroup.removeAllViews()
        val allChip = com.google.android.material.chip.Chip(requireContext()).apply {
            id = View.generateViewId()
            text = "Barchasi ($totalCount)"
            isCheckable = true
            isChecked = (selectedBatchIndex == -1)
            setOnClickListener {
                selectedBatchIndex = -1
                applySectionRangeFilter()
            }
        }
        chipGroup.addView(allChip)

        for (b in 0 until batchCount) {
            val startIdx = b * 20 + 1
            val endIdx = minOf((b + 1) * 20, totalCount)
            val rangeChip = com.google.android.material.chip.Chip(requireContext()).apply {
                id = View.generateViewId()
                text = "$startIdx–$endIdx dars"
                isCheckable = true
                isChecked = (selectedBatchIndex == b)
                setOnClickListener {
                    selectedBatchIndex = b
                    applySectionRangeFilter()
                }
            }
            chipGroup.addView(rangeChip)
        }
    }

    private fun applySectionRangeFilter() {
        val baseList = if (selectedBatchIndex < 0) {
            allSectionsCache
        } else {
            val startOrder = selectedBatchIndex * 20 + 1
            val endOrder = (selectedBatchIndex + 1) * 20
            allSectionsCache.filter { it.orderIndex in startOrder..endOrder }
        }

        sectionAdapter.submitList(baseList)
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
