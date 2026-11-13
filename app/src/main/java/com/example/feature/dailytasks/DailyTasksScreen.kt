package com.example.feature.dailytasks

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
import com.example.core.ui.ProgressAnimator.animateProgressTo
import com.example.core.ui.ProgressAnimator.replayAllProgressFromZero
import com.example.databinding.FragmentDailyTasksBinding
import com.example.domain.model.PracticeCategory
import com.example.domain.repository.LearningRepository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DailyTasksUiState(
    val streakDays: Int = 1,
    val completedCount: Int = 0,
    val totalCount: Int = 4,
    val xpEarned: Int = 0,
    val xpGoal: Int = 200,
    val vocabProgress: Int = 0,
    val vocabMax: Int = 5,
    val listeningProgress: Int = 0,
    val listeningMax: Int = 3,
    val speakingProgress: Int = 0,
    val speakingMax: Int = 3,
    val lessonProgress: Int = 0,
    val lessonMax: Int = 1,
    val activeTopicId: String = AppConstants.INITIAL_TOPIC_ID,
    val activeSectionId: String = "",
    val isRewardClaimed: Boolean = false
)

@HiltViewModel
class DailyTasksViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val isRewardClaimedFlow = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            learningRepository.ensureSeeded()
        }
    }

    val uiState: StateFlow<DailyTasksUiState> = combine(
        learningRepository.observeDashboardStats(),
        learningRepository.observeTopics()
    ) { stats, topics ->
        val activeTopicId = stats.continueLearning.currentTopicId.ifBlank {
            topics.firstOrNull()?.id ?: AppConstants.INITIAL_TOPIC_ID
        }
        stats to activeTopicId
    }.flatMapLatest { (stats, activeTopicId) ->
        combine(
            learningRepository.observeTodayTaskSteps(activeTopicId),
            isRewardClaimedFlow
        ) { steps, isClaimed ->
            val streak = stats.currentStreakDays.coerceAtLeast(1)
            val activeSectionId = stats.continueLearning.currentSectionId
            val vocabDone = steps.any { it.category == PracticeCategory.VOCABULARY && it.isCompletedToday }
            val listeningDone = steps.any { it.category == PracticeCategory.LISTENING && it.isCompletedToday }
            val speakingDone = steps.any { it.category == PracticeCategory.SPEAKING && it.isCompletedToday }
            val lessonDone = stats.totalSectionsCount > 0 && stats.completedSectionsCount > 0

            val vocabProg = if (vocabDone) 5 else 0
            val listeningProg = if (listeningDone) 3 else 0
            val speakingProg = if (speakingDone) 3 else 0
            val lessonProg = if (lessonDone) 1 else 0
            val completed = listOf(vocabDone, listeningDone, speakingDone, lessonDone).count { it }
            val xp = completed * 50

            DailyTasksUiState(
                streakDays = streak,
                completedCount = completed,
                totalCount = 4,
                xpEarned = xp,
                xpGoal = 200,
                vocabProgress = vocabProg,
                vocabMax = 5,
                listeningProgress = listeningProg,
                listeningMax = 3,
                speakingProgress = speakingProg,
                speakingMax = 3,
                lessonProgress = lessonProg,
                lessonMax = 1,
                activeTopicId = activeTopicId,
                activeSectionId = activeSectionId,
                isRewardClaimed = isClaimed
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DailyTasksUiState()
    )

    fun claimReward() {
        isRewardClaimedFlow.value = true
    }
}

/**
 * Daily Tasks screen (`Tasks` bottom navigation tab) pixel-perfect to reference design:
 * - Header with Calendar button, "Kunlik vazifalar", and Streak badge "🔥 1 kun"
 * - Hero Card ("Bugungi maqsad") with circular ring "2/4", XP progress, and glowing golden chest
 * - Section header "🎯 Bugungi vazifalar"
 * - 4 interactive task cards:
 *     1. Vocabulary (3/5) -> "Davom etish >"
 *     2. Listening (1/3 daq) -> "▶ Boshlash"
 *     3. Speaking (1/3 gap) -> "Davom etish >"
 *     4. Lesson (0/1) -> "Darsga o‘tish >"
 * - Bottom locked reward banner with golden locked chest
 */
@AndroidEntryPoint
class DailyTasksFragment : Fragment() {

    private var _binding: FragmentDailyTasksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DailyTasksViewModel by viewModels()
    private var wasStopped = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCalendarPicker.setOnClickListener {
            Snackbar.make(view, "Bugun: 12-noyabr • Kunlik vazifalar faol", Snackbar.LENGTH_SHORT).show()
        }

        binding.layoutStreakBadge.setOnClickListener {
            Snackbar.make(
                view,
                "🔥 Doimiy o‘qish: ${viewModel.uiState.value.streakDays} kunlik davomiylik!",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        // Vocabulary Task: Davom etish
        val openVocabAction = View.OnClickListener {
            findNavController().navigate(R.id.vocabularyTopicsFragment)
        }
        binding.btnTaskVocab.setOnClickListener(openVocabAction)
        binding.cardTaskVocab.setOnClickListener(openVocabAction)

        // Listening Task: Boshlash
        val openListeningAction = View.OnClickListener {
            val topicId = viewModel.uiState.value.activeTopicId
            findNavController().navigate(
                R.id.exerciseSessionFragment,
                bundleOf(
                    AppConstants.ARG_PRACTICE_CATEGORY to PracticeCategory.LISTENING.id,
                    AppConstants.ARG_TOPIC_ID to topicId
                )
            )
        }
        binding.btnTaskListening.setOnClickListener(openListeningAction)
        binding.cardTaskListening.setOnClickListener(openListeningAction)

        // Speaking Task: Davom etish
        val openSpeakingAction = View.OnClickListener {
            val topicId = viewModel.uiState.value.activeTopicId
            findNavController().navigate(
                R.id.speakingAiFragment,
                bundleOf(
                    AppConstants.ARG_TOPIC_ID to topicId
                )
            )
        }
        binding.btnTaskSpeaking.setOnClickListener(openSpeakingAction)
        binding.cardTaskSpeaking.setOnClickListener(openSpeakingAction)

        // Lesson Task: Darsga o'tish
        val openLessonAction = View.OnClickListener {
            val state = viewModel.uiState.value
            if (state.activeSectionId.isBlank()) {
                Snackbar.make(view, getString(R.string.curriculum_empty), Snackbar.LENGTH_SHORT).show()
                return@OnClickListener
            }
            findNavController().navigate(
                R.id.lessonDetailFragment,
                bundleOf(
                    AppConstants.ARG_TOPIC_ID to state.activeTopicId,
                    AppConstants.ARG_SECTION_ID to state.activeSectionId
                )
            )
        }
        binding.btnTaskLesson.setOnClickListener(openLessonAction)
        binding.cardTaskLesson.setOnClickListener(openLessonAction)

        // Bottom Reward Banner
        binding.layoutRewardBanner.setOnClickListener {
            val state = viewModel.uiState.value
            if (state.completedCount >= state.totalCount) {
                Snackbar.make(view, getString(R.string.daily_tasks_reward_claimed_msg), Snackbar.LENGTH_LONG).show()
            } else {
                val remaining = state.totalCount - state.completedCount
                Snackbar.make(
                    view,
                    getString(R.string.daily_tasks_reward_remaining_format, remaining),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderUi(state)
                }
            }
        }
    }

    private fun renderUi(state: DailyTasksUiState) {
        // Streak badge
        binding.tvDailyTasksStreak.text = getString(R.string.daily_tasks_streak_format, state.streakDays)

        // Hero card circular progress & info
        binding.circleTasksProgress.setProgress(state.completedCount, state.totalCount)
        binding.tvHeroCompletedCount.text = getString(R.string.daily_tasks_hero_completed_format, state.completedCount)
        binding.tvHeroXpProgress.text = getString(R.string.daily_tasks_hero_xp_format, state.xpEarned, state.xpGoal)
        binding.progressHeroXp.animateProgressTo(state.xpEarned, state.xpGoal)

        // Task 1: Vocabulary
        binding.progressVocab.animateProgressTo(state.vocabProgress, state.vocabMax)
        binding.tvVocabCount.text = "${state.vocabProgress}/${state.vocabMax}"
        binding.ivVocabDoneBadge.visibility =
            if (state.vocabProgress >= state.vocabMax) View.VISIBLE else View.GONE

        // Task 2: Listening
        binding.progressListening.animateProgressTo(state.listeningProgress, state.listeningMax)
        binding.tvListeningCount.text = "${state.listeningProgress}/${state.listeningMax} daq"
        binding.ivListeningDoneBadge.visibility =
            if (state.listeningProgress >= state.listeningMax) View.VISIBLE else View.GONE

        // Task 3: Speaking
        binding.progressSpeaking.animateProgressTo(state.speakingProgress, state.speakingMax)
        binding.tvSpeakingCount.text = "${state.speakingProgress}/${state.speakingMax} gap"
        binding.ivSpeakingDoneBadge.visibility =
            if (state.speakingProgress >= state.speakingMax) View.VISIBLE else View.GONE

        // Task 4: Lesson
        val hasLesson = state.activeSectionId.isNotBlank()
        binding.progressLesson.animateProgressTo(state.lessonProgress, state.lessonMax)
        binding.tvLessonCount.text = "${state.lessonProgress}/${state.lessonMax}"
        binding.ivLessonDoneBadge.visibility =
            if (state.lessonProgress >= state.lessonMax) View.VISIBLE else View.GONE
        binding.tvLessonSubtitle.text = if (hasLesson) {
            getString(R.string.daily_tasks_task_lesson_subtitle)
        } else {
            getString(R.string.curriculum_empty)
        }
        binding.btnTaskLesson.isEnabled = hasLesson
        binding.btnTaskLesson.alpha = if (hasLesson) 1.0f else 0.5f
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
