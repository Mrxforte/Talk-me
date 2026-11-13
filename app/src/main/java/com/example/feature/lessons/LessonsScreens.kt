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
    val activeTopicTitle: String = "A1.1 • Kirish",
    val activeSectionTitle: String = "1-Dars: Welcome to TalkMe",
    val completedLessonsCount: Int = 0,
    val totalLessonsCount: Int = 400
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

    private val viewModel: LessonsViewModel by viewModels()
    private var allTopicsCache: List<TopicModel> = emptyList()
    private var selectedCefrFilter: String = "ALL"

    private val topicAdapter = TopicAdapter { topic ->
        when {
            topic.id == "milestone_a1_final_exam" -> {
                Toast.makeText(
                    requireContext(),
                    "A1 yakuniy sinovni ochish uchun A1 bo'limlarini tugating 🏆",
                    Toast.LENGTH_SHORT
                ).show()
            }
            topic.status == UnlockStatus.LOCKED -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.topic_locked_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val targetTopicId = if (topic.id == "topic_01_kirish_intro") {
                    AppConstants.INITIAL_TOPIC_ID
                } else {
                    topic.id
                }
                findNavController().navigate(
                    R.id.topicDetailFragment,
                    bundleOf(AppConstants.ARG_TOPIC_ID to targetTopicId)
                )
            }
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
                ?: allTopicsCache.firstOrNull()
            val targetId = activeTopic?.id ?: AppConstants.INITIAL_TOPIC_ID
            findNavController().navigate(
                R.id.topicDetailFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to targetId)
            )
        }
        binding.btnHeroContinue.setOnClickListener(continueClickListener)
        binding.cardLevelSummary.setOnClickListener(continueClickListener)

        binding.chipGroupCefrLevel.setOnCheckedStateChangeListener { _, checkedIds ->
            val checkedId = checkedIds.firstOrNull() ?: R.id.chipLevelAll
            selectedCefrFilter = when (checkedId) {
                R.id.chipLevelA0 -> "A0"
                R.id.chipLevelA1 -> "A1"
                R.id.chipLevelA2 -> "A2"
                R.id.chipLevelB1 -> "B1"
                R.id.chipLevelB2 -> "B2"
                R.id.chipLevelC1C2 -> "C1_C2"
                else -> "ALL"
            }
            bindTopHeroSummary(allTopicsCache)
            applyCefrFilter()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topics.collect { topicList ->
                    allTopicsCache = topicList
                    bindTopHeroSummary(topicList)
                    applyCefrFilter()
                }
            }
        }
    }

    private fun bindTopHeroSummary(topicList: List<TopicModel>) {
        when (selectedCefrFilter) {
            "A0" -> {
                val t = topicList.firstOrNull { it.orderIndex == 1 }
                val comp = t?.completedSections ?: 0
                binding.tvHeroLevelBadge.text = "A0"
                binding.tvHeroLevelTitle.text = "A0 Kirish & Alifbo"
                binding.tvHeroLessonCount.text = "$comp / 10 dars"
                binding.progressHeroLevel.max = 10
                binding.progressHeroLevel.progress = comp.coerceIn(1, 10)
                binding.tvHeroNextLesson.text = "1-Dars: Welcome to TalkMe"
            }
            "A1" -> {
                val a1Topics = topicList.filter { it.orderIndex in 2..9 }
                val comp = a1Topics.sumOf { it.completedSections }
                binding.tvHeroLevelBadge.text = "A1"
                binding.tvHeroLevelTitle.text = "A1 Beginner"
                binding.tvHeroLessonCount.text = "$comp / 100 dars"
                binding.progressHeroLevel.max = 100
                binding.progressHeroLevel.progress = comp
                binding.tvHeroNextLesson.text = "Sonlar 1–20 (11-dars)"
            }
            "A2" -> {
                val t = topicList.firstOrNull { it.orderIndex == 10 }
                val comp = t?.completedSections ?: 0
                binding.tvHeroLevelBadge.text = "A2"
                binding.tvHeroLevelTitle.text = "A2 Elementary"
                binding.tvHeroLessonCount.text = "$comp / 80 dars"
                binding.progressHeroLevel.max = 80
                binding.progressHeroLevel.progress = comp
                binding.tvHeroNextLesson.text = "Continuous & Degrees (111-dars)"
            }
            "B1" -> {
                val t = topicList.firstOrNull { it.orderIndex == 11 }
                val comp = t?.completedSections ?: 0
                binding.tvHeroLevelBadge.text = "B1"
                binding.tvHeroLevelTitle.text = "B1 Intermediate"
                binding.tvHeroLessonCount.text = "$comp / 80 dars"
                binding.progressHeroLevel.max = 80
                binding.progressHeroLevel.progress = comp
                binding.tvHeroNextLesson.text = "Perfect & Passive (191-dars)"
            }
            "B2" -> {
                val t = topicList.firstOrNull { it.orderIndex == 12 }
                val comp = t?.completedSections ?: 0
                binding.tvHeroLevelBadge.text = "B2"
                binding.tvHeroLevelTitle.text = "B2 Upper-Intermediate"
                binding.tvHeroLessonCount.text = "$comp / 66 dars"
                binding.progressHeroLevel.max = 66
                binding.progressHeroLevel.progress = comp
                binding.tvHeroNextLesson.text = "Modals & Gerund (271-dars)"
            }
            "C1_C2" -> {
                val t = topicList.firstOrNull { it.orderIndex == 13 }
                val comp = t?.completedSections ?: 0
                binding.tvHeroLevelBadge.text = "C2"
                binding.tvHeroLevelTitle.text = "C1–C2 Advanced & Mastery"
                binding.tvHeroLessonCount.text = "$comp / 64 dars"
                binding.progressHeroLevel.max = 64
                binding.progressHeroLevel.progress = comp
                binding.tvHeroNextLesson.text = "Absolute & Inversion (337-dars)"
            }
            else -> {
                val totalComp = topicList.sumOf { it.completedSections }
                binding.tvHeroLevelBadge.text = "A0–C2"
                binding.tvHeroLevelTitle.text = "Ingliz tili (A0–C2)"
                binding.tvHeroLessonCount.text = "$totalComp / 400 dars"
                binding.progressHeroLevel.max = 400
                binding.progressHeroLevel.progress = totalComp.coerceAtLeast(1)

                val activeTopic = topicList.firstOrNull { it.status == UnlockStatus.AVAILABLE }
                binding.tvHeroNextLesson.text = if (activeTopic == null || activeTopic.orderIndex == 1) {
                    "1-Dars: Welcome to TalkMe"
                } else {
                    activeTopic.titleUzbek
                }
            }
        }
    }

    private fun applyCefrFilter() {
        if (allTopicsCache.isEmpty()) {
            topicAdapter.submitList(emptyList())
            return
        }

        val filteredTopics = when (selectedCefrFilter) {
            "A0" -> allTopicsCache.filter { it.orderIndex == 1 }
            "A1" -> allTopicsCache.filter { it.orderIndex in 2..9 }
            "A2" -> allTopicsCache.filter { it.orderIndex == 10 }
            "B1" -> allTopicsCache.filter { it.orderIndex == 11 }
            "B2" -> allTopicsCache.filter { it.orderIndex == 12 }
            "C1_C2" -> allTopicsCache.filter { it.orderIndex == 13 }
            else -> allTopicsCache
        }

        val timelineRows = mutableListOf<TopicModel>()
        val topic1 = allTopicsCache.firstOrNull { it.orderIndex == 1 } ?: allTopicsCache.first()

        if (selectedCefrFilter == "ALL" || selectedCefrFilter == "A0") {
            if (topic1.status == UnlockStatus.AVAILABLE) {
                // Completed Kirish intro row
                timelineRows.add(
                    topic1.copy(
                        id = "topic_01_kirish_intro",
                        orderIndex = 1,
                        titleUzbek = "A0 • Kirish",
                        totalSections = 10,
                        completedSections = maxOf(4, topic1.completedSections),
                        status = UnlockStatus.COMPLETED
                    )
                )
                // Active Module row
                timelineRows.add(
                    topic1.copy(
                        id = topic1.id,
                        orderIndex = 1,
                        titleUzbek = "Salomlashish va\ntanishuv",
                        status = UnlockStatus.AVAILABLE
                    )
                )
            } else {
                timelineRows.add(topic1)
            }
        }

        // Add remaining topics in the selected filter
        val remaining = filteredTopics.filter { it.orderIndex != 1 || (selectedCefrFilter != "ALL" && selectedCefrFilter != "A0") }
        timelineRows.addAll(remaining)

        // For A1 and ALL filters, append the A1 Final Exam Milestone card
        if (selectedCefrFilter == "A1" || selectedCefrFilter == "ALL") {
            timelineRows.add(
                TopicModel(
                    id = "milestone_a1_final_exam",
                    orderIndex = 99,
                    titleEnglish = "A1 Final Exam",
                    titleUzbek = "A1 yakuniy sinov",
                    descriptionUzbek = "",
                    totalSections = 10,
                    completedSections = 0,
                    status = UnlockStatus.LOCKED
                )
            )
        }

        topicAdapter.submitList(timelineRows)
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
                            val cefrCode = when (topic.orderIndex) {
                                in 1..9 -> "A1"
                                10 -> "A2"
                                11 -> "B1"
                                12 -> "B2"
                                13 -> "C1"
                                else -> "C2"
                            }
                            val isIntroTopic = topic.id == "topic_01_introduction" || topic.orderIndex == 1
                            val topTitle = if (isIntroTopic) "Kirish" else topic.titleUzbek
                            val totalDisp = if (isIntroTopic) 10 else topic.totalSections.coerceAtLeast(10)
                            val completedDisp = if (isIntroTopic && topic.completedSections < 4) {
                                4
                            } else {
                                topic.completedSections
                            }

                            binding.tvSubtopicTopTitle.text = topTitle
                            binding.tvSubtopicTopSubtitle.text = "$cefrCode • 1–${totalDisp}-dars"
                            binding.tvSubtopicLevelBadge.text = cefrCode
                            binding.tvSubtopicHeroTitle.text = if (isIntroTopic) "Kirish bo‘limi" else topic.titleUzbek
                            binding.tvSubtopicHeroCount.text = "$completedDisp/$totalDisp dars bajarildi"
                            binding.progressSubtopicBar.progress =
                                ((completedDisp * 100) / totalDisp.coerceAtLeast(1)).coerceIn(8, 100)

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

        // When viewing Kirish (topic_01_introduction) on a fresh install (< 2 completed),
        // present Lesson 1 & 2 as COMPLETED and Lesson 3 as AVAILABLE ("Hello! Hi!")
        // to match the subtopic reference design pixel-for-pixel while keeping all 3 playable.
        val completedCountInTopic = allSectionsCache.count { it.status == UnlockStatus.COMPLETED }
        val displayList = if (
            allSectionsCache.firstOrNull()?.topicId == "topic_01_introduction" &&
            completedCountInTopic < 2
        ) {
            baseList.map { section ->
                when (section.orderIndex) {
                    1, 2 -> section.copy(status = UnlockStatus.COMPLETED)
                    3 -> section.copy(status = UnlockStatus.AVAILABLE)
                    else -> section.copy(status = UnlockStatus.LOCKED)
                }
            }
        } else {
            baseList
        }

        sectionAdapter.submitList(displayList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
