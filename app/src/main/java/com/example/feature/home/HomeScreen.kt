package com.example.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.core.common.AppConstants
import com.example.databinding.FragmentHomeBinding
import com.example.databinding.ViewStatisticCardBinding
import com.example.domain.model.LearningDashboardStats
import com.example.domain.model.PracticeCategory
import com.example.domain.model.TopicPracticeSummary
import com.example.domain.repository.LearningRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val stats: LearningDashboardStats? = null,
    val studiedTopics: List<TopicPracticeSummary> = emptyList(),
    val selectedTopicId: String? = null
) {
    val activeTopicSummary: TopicPracticeSummary?
        get() = studiedTopics.find { it.topicId == selectedTopicId }
            ?: studiedTopics.lastOrNull()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val selectedTopicIdFlow = MutableStateFlow<String?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        learningRepository.observeDashboardStats(),
        learningRepository.observeTopicPracticeSummaries(),
        selectedTopicIdFlow
    ) { stats, topicSummaries, chosenTopicId ->
        val resolvedTopicId = chosenTopicId
            ?: stats.continueLearning.currentTopicId.takeIf { id -> topicSummaries.any { it.topicId == id } }
            ?: topicSummaries.lastOrNull()?.topicId
        HomeUiState(
            stats = stats,
            studiedTopics = topicSummaries,
            selectedTopicId = resolvedTopicId
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = HomeUiState()
    )

    init {
        viewModelScope.launch {
            learningRepository.ensureSeeded()
        }
    }

    fun selectTopic(topicId: String) {
        selectedTopicIdFlow.value = topicId
    }
}

/**
 * Central learning dashboard Fragment displaying:
 * - Time-of-day greeting & streak
 * - Continue Learning Hero Banner
 * - Studied Topic Selector (separating all content strictly by studied topic)
 * - Vocabulary & Grammar cards with new-word notification badges
 * - 4 Skill Cards (Reading, Writing, Listening, Speaking) with topic-scoped exercises and badges
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.tvGreeting.text = when (hour) {
            in 5..11 -> getString(R.string.greeting_morning)
            in 12..17 -> getString(R.string.greeting_afternoon)
            else -> getString(R.string.greeting_evening)
        }

        // Static titles, icons & 1-to-1 matching 3D artwork for Vocabulary and Grammar cards
        binding.cardMemorizedWords.tvStatTitle.text = getString(R.string.stat_memorized_words_title)
        binding.cardMemorizedWords.ivStatIcon.setImageResource(R.drawable.ic_nav_lessons)
        binding.cardMemorizedWords.ivStatCoverImage.setImageResource(
            com.example.core.ui.TopicVisualArtwork.getSkillImageRes(PracticeCategory.VOCABULARY)
        )

        binding.cardMasteredGrammar.tvStatTitle.text = getString(R.string.stat_mastered_grammar_title)
        binding.cardMasteredGrammar.ivStatIcon.setImageResource(R.drawable.ic_trophy)
        binding.cardMasteredGrammar.ivStatCoverImage.setImageResource(
            com.example.core.ui.TopicVisualArtwork.getSkillImageRes(PracticeCategory.GRAMMAR)
        )

        // Static titles, icons & 1-to-1 matching 3D artwork for Reading, Writing, Listening, Speaking
        binding.cardSkillReading.tvStatTitle.text = getString(R.string.home_skill_reading_title)
        binding.cardSkillReading.ivStatIcon.setImageResource(R.drawable.ic_skill_reading)
        binding.cardSkillReading.ivStatCoverImage.setImageResource(
            com.example.core.ui.TopicVisualArtwork.getSkillImageRes(PracticeCategory.READING)
        )

        binding.cardSkillWriting.tvStatTitle.text = getString(R.string.home_skill_writing_title)
        binding.cardSkillWriting.ivStatIcon.setImageResource(R.drawable.ic_skill_writing)
        binding.cardSkillWriting.ivStatCoverImage.setImageResource(
            com.example.core.ui.TopicVisualArtwork.getSkillImageRes(PracticeCategory.WRITING)
        )

        binding.cardSkillListening.tvStatTitle.text = getString(R.string.home_skill_listening_title)
        binding.cardSkillListening.ivStatIcon.setImageResource(R.drawable.ic_skill_listening)
        binding.cardSkillListening.ivStatCoverImage.setImageResource(
            com.example.core.ui.TopicVisualArtwork.getSkillImageRes(PracticeCategory.LISTENING)
        )

        binding.cardSkillSpeaking.tvStatTitle.text = getString(R.string.home_skill_speaking_title)
        binding.cardSkillSpeaking.ivStatIcon.setImageResource(R.drawable.ic_skill_speaking)
        binding.cardSkillSpeaking.ivStatCoverImage.setImageResource(
            com.example.core.ui.TopicVisualArtwork.getSkillImageRes(PracticeCategory.SPEAKING)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val stats = state.stats ?: return@collect
                    binding.tvUserName.text = stats.userName
                    binding.tvStreakBadge.text = getString(R.string.home_streak_format, stats.currentStreakDays)

                    val cont = stats.continueLearning
                    binding.tvHeroCourseName.text = cont.courseName
                    binding.tvHeroTopicName.text = cont.currentTopicTitle
                    binding.ivHeroIllustration.setImageResource(
                        com.example.core.ui.TopicVisualArtwork.getTopicImageRes(cont.currentTopicId)
                    )
                    binding.tvHeroSectionName.text = if (cont.hasStarted) {
                        cont.currentSectionTitle
                    } else {
                        getString(R.string.hero_not_started_subtitle)
                    }
                    binding.progressHero.progress = cont.progressPercent
                    binding.tvHeroProgressPercent.text = getString(R.string.hero_progress_format, cont.progressPercent)
                    binding.btnContinueLearning.text = if (cont.hasStarted) {
                        getString(R.string.hero_continue_learning)
                    } else {
                        getString(R.string.hero_start_learning)
                    }

                    binding.btnContinueLearning.setOnClickListener {
                        findNavController().navigate(
                            R.id.lessonDetailFragment,
                            bundleOf(
                                AppConstants.ARG_TOPIC_ID to cont.currentTopicId,
                                AppConstants.ARG_SECTION_ID to cont.currentSectionId
                            )
                        )
                    }

                    val activeTopic = state.activeTopicSummary
                    if (activeTopic != null) {

                        // 1. Vocabulary Card -> Opens Major Topics -> Sub-topics -> Flip Cards
                        bindHomeCard(
                            card = binding.cardMemorizedWords,
                            valueText = "${activeTopic.memorizedWordsCount} / ${activeTopic.totalWordsCount}",
                            subtitleText = "${activeTopic.titleEnglish}: mavzular va sub-topiclar",
                            newBadgeCount = activeTopic.newWordsBadgeCount
                        ) {
                            findNavController().navigate(
                                R.id.vocabularyTopicsFragment,
                                bundleOf(AppConstants.ARG_TOPIC_ID to activeTopic.topicId)
                            )
                        }

                        // 2. Grammar Card (scoped to activeTopic)
                        bindHomeCard(
                            card = binding.cardMasteredGrammar,
                            valueText = "${activeTopic.masteredGrammarCount} / ${activeTopic.totalGrammarCount}",
                            subtitleText = "${activeTopic.titleEnglish}: grammatika qoidalari",
                            newBadgeCount = activeTopic.newGrammarBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.GRAMMAR, activeTopic.topicId)
                        }

                        // 3. Reading Card (scoped to activeTopic)
                        bindHomeCard(
                            card = binding.cardSkillReading,
                            valueText = "${activeTopic.totalWordsCount} so'z",
                            subtitleText = "${activeTopic.titleEnglish} • matn va o'qish",
                            newBadgeCount = activeTopic.readingNewBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.READING, activeTopic.topicId)
                        }

                        // 4. Writing Card (scoped to activeTopic)
                        bindHomeCard(
                            card = binding.cardSkillWriting,
                            valueText = "${activeTopic.totalWordsCount} so'z",
                            subtitleText = "${activeTopic.titleEnglish} • yozma tarjima",
                            newBadgeCount = activeTopic.writingNewBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.WRITING, activeTopic.topicId)
                        }

                        // 5. Listening Card (scoped to activeTopic)
                        bindHomeCard(
                            card = binding.cardSkillListening,
                            valueText = "${activeTopic.totalWordsCount} audio",
                            subtitleText = "${activeTopic.titleEnglish} • eshitib tushunish",
                            newBadgeCount = activeTopic.listeningNewBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.LISTENING, activeTopic.topicId)
                        }

                        // 6. Speaking Card (scoped to activeTopic)
                        bindHomeCard(
                            card = binding.cardSkillSpeaking,
                            valueText = "${activeTopic.totalWordsCount} so'z",
                            subtitleText = "${activeTopic.titleEnglish} • AI ovozli suhbat",
                            newBadgeCount = activeTopic.speakingNewBadgeCount
                        ) {
                            findNavController().navigate(
                                R.id.speakingAiFragment,
                                bundleOf(AppConstants.ARG_TOPIC_ID to activeTopic.topicId)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun bindHomeCard(
        card: ViewStatisticCardBinding,
        valueText: String,
        subtitleText: String,
        newBadgeCount: Int,
        onClick: () -> Unit
    ) {
        card.tvStatValue.text = valueText
        card.tvStatSubtitle.text = subtitleText
        card.tvStatBadge.visibility = View.VISIBLE
        card.tvStatBadge.text = if (newBadgeCount > 0) {
            "+$newBadgeCount"
        } else {
            valueText
        }
        card.root.setOnClickListener { onClick() }
        card.ivStatCoverImage.setOnClickListener { onClick() }
    }

    private fun openTopicExercise(category: PracticeCategory, topicId: String) {
        findNavController().navigate(
            R.id.exerciseSessionFragment,
            bundleOf(
                AppConstants.ARG_PRACTICE_CATEGORY to category.id,
                AppConstants.ARG_TOPIC_ID to topicId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
