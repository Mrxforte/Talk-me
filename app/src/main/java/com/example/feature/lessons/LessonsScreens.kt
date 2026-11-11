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

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    val topics: StateFlow<List<TopicModel>> = learningRepository.observeTopics()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    private val _selectedTopicId = MutableStateFlow(AppConstants.INITIAL_TOPIC_ID)
    private val _selectedTopic = MutableStateFlow<TopicModel?>(null)
    val selectedTopic: StateFlow<TopicModel?> = _selectedTopic.asStateFlow()

    val sections: StateFlow<List<LessonSectionModel>> = _selectedTopicId
        .flatMapLatest { topicId -> learningRepository.observeTopicSections(topicId) }
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
}

/**
 * Displays the complete 12-topic TalkMe curriculum with real sequential unlocking enforcement.
 */
@AndroidEntryPoint
class LessonsFragment : Fragment() {

    private var _binding: FragmentLessonsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LessonsViewModel by viewModels()

    private val topicAdapter = TopicAdapter { topic ->
        if (topic.status == UnlockStatus.LOCKED) {
            Toast.makeText(
                requireContext(),
                getString(R.string.topic_locked_toast),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController().navigate(
                R.id.topicDetailFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to topic.id)
            )
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

        binding.rvTopics.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(), 2)
        binding.rvTopics.adapter = topicAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topics.collect { topicList ->
                    topicAdapter.submitList(topicList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/**
 * Displays individual lesson sections inside an unlocked curriculum Topic.
 * Enforces sequential unlocking between sections.
 */
@AndroidEntryPoint
class TopicDetailFragment : Fragment() {

    private var _binding: FragmentTopicDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LessonsViewModel by viewModels()

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

        binding.headerTopicDetail.btnBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.rvSections.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSections.adapter = sectionAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.selectedTopic.collect { topic ->
                        if (topic != null) {
                            binding.headerTopicDetail.tvHeaderTitle.text =
                                "${topic.orderIndex}. ${topic.titleEnglish} — ${topic.titleUzbek}"
                            binding.headerTopicDetail.tvHeaderSubtitle.text = topic.descriptionUzbek
                            binding.ivTopicDetailHero.setImageResource(
                                com.example.core.ui.TopicVisualArtwork.getTopicImageRes(
                                    topic.id,
                                    topic.orderIndex
                                )
                            )
                            binding.tvTopicDetailHeroBadge.text =
                                String.format("LESSON %02d • %d/%d BO'LIM", topic.orderIndex, topic.completedSections, topic.totalSections)
                            binding.tvTopicDetailHeroTitle.text =
                                "${topic.titleEnglish} (${topic.titleUzbek})"
                            binding.tvTopicDetailHeroDesc.text = topic.descriptionUzbek
                        }
                    }
                }
                launch {
                    viewModel.sections.collect { sectionList ->
                        sectionAdapter.submitList(sectionList)
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
