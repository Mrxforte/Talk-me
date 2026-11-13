package com.example.feature.profile

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
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
import com.example.core.common.AppConstants
import com.example.core.ui.ProgressAnimator.animateProgressTo
import com.example.core.ui.ProgressAnimator.replayAllProgressFromZero
import com.example.databinding.FragmentProfileBinding
import com.example.domain.model.ChartPeriodFilter
import com.example.domain.model.CurriculumLevel
import com.example.domain.model.LearningDashboardStats
import com.example.domain.model.PracticeCategory
import com.example.domain.model.PracticeSessionRecord
import com.example.domain.model.SkillPerformanceMetric
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.model.UserPreferencesModel
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.UserPreferencesRepository
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class RoadmapUiModel(
    val activeTopicId: String = CurriculumLevel.A0.topicId,
    val hasActiveLessons: Boolean = false,
    val currentLevelCode: String = "A0",
    val levelBadgeTitle: String = "A0 Foundation",
    val nextPeakLevelCode: String = "A1",
    val completedLessons: Int = 0,
    val totalLessons: Int = 0,
    val progressPercent: Int = 0
)

data class GrowthChartUiModel(
    val labels: List<String> = listOf("Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya"),
    val currentMinutes: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0),
    val previousMinutes: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0),
    val totalCurrentMinutes: Int = 0,
    val totalPreviousMinutes: Int = 0,
    val deltaPercent: Int = 0
)

data class ProfileUiState(
    val stats: LearningDashboardStats? = null,
    val topics: List<TopicModel> = emptyList(),
    val skillMetrics: List<SkillPerformanceMetric> = emptyList(),
    val sessionRecords: List<PracticeSessionRecord> = emptyList(),
    val selectedPeriod: ChartPeriodFilter = ChartPeriodFilter.WEEKLY,
    val preferences: UserPreferencesModel = UserPreferencesModel()
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val selectedPeriodFlow = MutableStateFlow(ChartPeriodFilter.WEEKLY)

    private val baseDataFlow = combine(
        learningRepository.observeDashboardStats(),
        learningRepository.observeTopics(),
        learningRepository.observeSkillPerformanceMetrics(),
        learningRepository.observePracticeSessionRecords()
    ) { stats, topics, skills, records ->
        QuadData(stats, topics, skills, records)
    }

    val uiState: StateFlow<ProfileUiState> = combine(
        baseDataFlow,
        selectedPeriodFlow,
        preferencesRepository.userPreferencesFlow
    ) { base, period, prefs ->
        ProfileUiState(
            stats = base.stats,
            topics = base.topics,
            skillMetrics = base.skills,
            sessionRecords = base.records,
            selectedPeriod = period,
            preferences = prefs
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ProfileUiState()
    )

    fun setChartPeriod(period: ChartPeriodFilter) {
        selectedPeriodFlow.value = period
    }

    fun saveUserName(name: String) {
        val trimmed = name.trim()
        if (trimmed.isNotEmpty()) {
            viewModelScope.launch {
                preferencesRepository.updateUserName(trimmed)
            }
        }
    }

    private data class QuadData(
        val stats: LearningDashboardStats,
        val topics: List<TopicModel>,
        val skills: List<SkillPerformanceMetric>,
        val records: List<PracticeSessionRecord>
    )
}

/**
 * Cosmic Dark Profile Fragment.
 * Displays user identity header (with settings icon navigation), dynamic animated mountain
 * learning roadmap, weekly/monthly growth chart, 4-skill animated progress breakdown, and achievements.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()
    private var latestRoadmapState: RoadmapUiModel = RoadmapUiModel()
    private var wasStopped = false

    private val avatarDrawableOptions = listOf(
        R.drawable.img_ai_tutor_avatar_1794304412196,
        R.drawable.img_voice_avatar_male_1794557024094,
        R.drawable.img_voice_avatar_female_1794557024589,
        R.drawable.img_topic_morning_intro_1794316347713
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applySavedAvatar()
        setupHeaderActions()
        setupRoadmapActions()
        setupGrowthToggle()
        setupSkillRowNavigation()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderProfileState(state)
                }
            }
        }
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

    private fun setupHeaderActions() {
        binding.containerProfileAvatarTap.setOnClickListener {
            showEditProfileDialog()
        }
        binding.btnScrollToSettings.setOnClickListener {
            findNavController().navigate(R.id.settingsFragment)
        }
    }

    private fun setupRoadmapActions() {
        val openRoadmapDestination = View.OnClickListener {
            val roadmap = latestRoadmapState
            if (roadmap.hasActiveLessons) {
                findNavController().navigate(
                    R.id.topicDetailFragment,
                    bundleOf(AppConstants.ARG_TOPIC_ID to roadmap.activeTopicId)
                )
            } else {
                findNavController().navigate(R.id.lessonsFragment)
            }
        }
        binding.cardLearningRoadmap.setOnClickListener(openRoadmapDestination)
        binding.cardRoadmapScene.setOnClickListener(openRoadmapDestination)
        binding.viewLearningRoadmap.setOnClickListener(openRoadmapDestination)
    }

    private fun setupGrowthToggle() {
        binding.toggleGrowthPeriod.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.btnGrowthWeekly -> viewModel.setChartPeriod(ChartPeriodFilter.WEEKLY)
                R.id.btnGrowthMonthly -> viewModel.setChartPeriod(ChartPeriodFilter.MONTHLY)
            }
        }
    }

    private fun setupSkillRowNavigation() {
        binding.rowSkillReading.setOnClickListener {
            openPracticeSkill(PracticeCategory.READING)
        }
        binding.rowSkillListening.setOnClickListener {
            openPracticeSkill(PracticeCategory.LISTENING)
        }
        binding.rowSkillWriting.setOnClickListener {
            openPracticeSkill(PracticeCategory.WRITING)
        }
        binding.rowSkillSpeaking.setOnClickListener {
            findNavController().navigate(
                R.id.speakingAiFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to latestRoadmapState.activeTopicId)
            )
        }
    }

    private fun openPracticeSkill(category: PracticeCategory) {
        findNavController().navigate(
            R.id.exerciseSessionFragment,
            bundleOf(
                AppConstants.ARG_PRACTICE_CATEGORY to category.id,
                AppConstants.ARG_TOPIC_ID to latestRoadmapState.activeTopicId
            )
        )
    }

    private fun renderProfileState(state: ProfileUiState) {
        val stats = state.stats
        val prefs = state.preferences

        // 1. Header Identity
        val displayName = stats?.userName?.ifBlank { prefs.userName } ?: prefs.userName
        binding.tvProfileName.text = displayName
        val handle = "@${displayName.lowercase(Locale.US).replace(Regex("\\s+"), "_")}_talkme"
        binding.tvProfileHandle.text = handle

        val streakDays = (stats?.currentStreakDays ?: 1).coerceAtLeast(1)
        binding.tvProfileStreak.text = getString(R.string.daily_tasks_streak_format, streakDays)

        // 2. Dynamic Mountain Roadmap
        val roadmap = buildRoadmapUiModel(state.topics, stats)
        latestRoadmapState = roadmap
        binding.tvProfileLevelBadge.text = roadmap.levelBadgeTitle
        binding.tvRoadmapCompletedLessons.text =
            getString(R.string.profile_roadmap_lessons_done_format, roadmap.completedLessons)
        binding.tvRoadmapLevelStage.text =
            getString(R.string.profile_roadmap_level_stage_format, roadmap.currentLevelCode)
        binding.tvRoadmapPercent.text = "${roadmap.progressPercent}%"
        binding.progressRoadmap.animateProgressTo(roadmap.progressPercent, maxValue = 100)
        binding.viewLearningRoadmap.setRoadmapState(
            peakLevelCode = roadmap.nextPeakLevelCode,
            progressRatio = roadmap.progressPercent / 100f,
            milestones = 4,
            startText = getString(R.string.profile_roadmap_start_flag),
            todayText = getString(R.string.profile_roadmap_today_marker)
        )

        // 3. Weekly / Monthly Growth Chart
        val isMonthly = state.selectedPeriod == ChartPeriodFilter.MONTHLY
        val growth = buildGrowthChartModel(state.sessionRecords, isMonthly)
        stylePeriodButtons(isMonthly)
        binding.tvGrowthPeriodPrefix.text = getString(
            if (isMonthly) R.string.profile_this_month_prefix else R.string.profile_this_week_prefix
        )
        binding.tvGrowthTotalTime.text = formatStudyMinutesUz(growth.totalCurrentMinutes)
        binding.tvGrowthDeltaBadge.text = if (growth.deltaPercent >= 0) {
            "↗ +${growth.deltaPercent}%"
        } else {
            "↘ ${growth.deltaPercent}%"
        }
        binding.tvGrowthDeltaCaption.text = getString(
            if (isMonthly) R.string.profile_vs_last_month else R.string.profile_vs_last_week
        )

        val currentWord = if (isMonthly) "Bu oy" else "Bu hafta"
        val prevWord = if (isMonthly) "O‘tgan oy" else "O‘tgan hafta"
        binding.chartWeeklyGrowth.setGrowthData(
            labels = growth.labels,
            currentMinutes = growth.currentMinutes,
            previousMinutes = growth.previousMinutes,
            currentLegend = "$currentWord (${formatCompactMinutesUz(growth.totalCurrentMinutes)})",
            previousLegend = "$prevWord (${formatCompactMinutesUz(growth.totalPreviousMinutes)})"
        )

        // 4. Four Skills Progress Bars (animated cleanly from 0 -> %)
        val metricBySkill = state.skillMetrics.associateBy { it.skillName.uppercase(Locale.US) }
        val readingPct = (metricBySkill["READING"]?.averageAccuracyPercent ?: 0).coerceIn(0, 100)
        val listeningPct = (metricBySkill["LISTENING"]?.averageAccuracyPercent ?: 0).coerceIn(0, 100)
        val writingPct = (metricBySkill["WRITING"]?.averageAccuracyPercent ?: 0).coerceIn(0, 100)
        val speakingPct = (metricBySkill["SPEAKING"]?.averageAccuracyPercent ?: 0).coerceIn(0, 100)

        binding.progressSkillReading.animateProgressTo(readingPct, maxValue = 100)
        binding.tvSkillReadingPercent.text = "$readingPct%"

        binding.progressSkillListening.animateProgressTo(listeningPct, maxValue = 100)
        binding.tvSkillListeningPercent.text = "$listeningPct%"

        binding.progressSkillWriting.animateProgressTo(writingPct, maxValue = 100)
        binding.tvSkillWritingPercent.text = "$writingPct%"

        binding.progressSkillSpeaking.animateProgressTo(speakingPct, maxValue = 100)
        binding.tvSkillSpeakingPercent.text = "$speakingPct%"

        // 5. Achievements
        val memorizedWords = stats?.memorizedUniqueWordsCount ?: 0
        binding.tvAchievementStreakTitle.text = getString(R.string.profile_ach_streak_format, streakDays)
        binding.tvAchievementWordsTitle.text = getString(R.string.profile_ach_words_format, memorizedWords)
    }

    private fun stylePeriodButtons(isMonthly: Boolean) {
        val activeBtn = if (isMonthly) binding.btnGrowthMonthly else binding.btnGrowthWeekly
        val inactiveBtn = if (isMonthly) binding.btnGrowthWeekly else binding.btnGrowthMonthly
        activeBtn.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(), R.color.color_primary_variant)
        )
        activeBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        inactiveBtn.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireContext(), android.R.color.transparent)
        )
        inactiveBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_slate_subtitle))
    }

    private fun buildRoadmapUiModel(
        topics: List<TopicModel>,
        stats: LearningDashboardStats?
    ): RoadmapUiModel {
        val activeTopic = topics.firstOrNull { it.status == UnlockStatus.AVAILABLE }
            ?: topics.lastOrNull { it.status == UnlockStatus.COMPLETED }
            ?: topics.firstOrNull()

        val currentLevel = CurriculumLevel.forTopic(activeTopic?.id.orEmpty()) ?: CurriculumLevel.A0
        val nextLevel = CurriculumLevel.entries.getOrNull(currentLevel.ordinal + 1)
        val peakCode = nextLevel?.name ?: "${currentLevel.name} ★"

        val levelBadgeTitle = when (currentLevel) {
            CurriculumLevel.A0 -> "A0 Foundation"
            CurriculumLevel.A1 -> "A1 Beginner"
            CurriculumLevel.A2 -> "A2 Elementary"
            CurriculumLevel.B1 -> "B1 Intermediate"
            CurriculumLevel.B2 -> "B2 Upper-Int"
            CurriculumLevel.C1 -> "C1 Advanced"
            CurriculumLevel.C2 -> "C2 Mastery"
        }

        val completedLessons = activeTopic?.completedSections ?: stats?.completedSectionsCount ?: 0
        val totalLessons = activeTopic?.totalSections ?: stats?.totalSectionsCount ?: 0
        val progressPercent = if (totalLessons > 0) {
            ((completedLessons * 100) / totalLessons).coerceIn(0, 100)
        } else {
            0
        }

        return RoadmapUiModel(
            activeTopicId = activeTopic?.id ?: CurriculumLevel.A0.topicId,
            hasActiveLessons = totalLessons > 0,
            currentLevelCode = currentLevel.name,
            levelBadgeTitle = levelBadgeTitle,
            nextPeakLevelCode = peakCode,
            completedLessons = completedLessons,
            totalLessons = totalLessons,
            progressPercent = progressPercent
        )
    }

    private fun buildGrowthChartModel(
        records: List<PracticeSessionRecord>,
        isMonthly: Boolean
    ): GrowthChartUiModel {
        val cal = Calendar.getInstance()
        val nowMs = cal.timeInMillis
        val dayMs = 86_400_000L

        return if (!isMonthly) {
            val labels = listOf("Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya")
            val currentBuckets = IntArray(7)
            val prevBuckets = IntArray(7)

            // Normalize Monday as index 0 of current week
            val todayCal = Calendar.getInstance().apply {
                timeInMillis = nowMs
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val dow = todayCal.get(Calendar.DAY_OF_WEEK)
            val offsetFromMonday = if (dow == Calendar.SUNDAY) 6 else (dow - Calendar.MONDAY).coerceIn(0, 6)
            val thisMondayMs = todayCal.timeInMillis - offsetFromMonday * dayMs
            val prevMondayMs = thisMondayMs - 7 * dayMs

            records.forEach { rec ->
                when {
                    rec.timestamp >= thisMondayMs && rec.timestamp < thisMondayMs + 7 * dayMs -> {
                        val idx = ((rec.timestamp - thisMondayMs) / dayMs).toInt().coerceIn(0, 6)
                        currentBuckets[idx] += 5
                    }
                    rec.timestamp >= prevMondayMs && rec.timestamp < thisMondayMs -> {
                        val idx = ((rec.timestamp - prevMondayMs) / dayMs).toInt().coerceIn(0, 6)
                        prevBuckets[idx] += 5
                    }
                }
            }

            val curTotal = currentBuckets.sum()
            val prevTotal = prevBuckets.sum()
            val delta = computeDeltaPercent(curTotal, prevTotal)
            GrowthChartUiModel(
                labels = labels,
                currentMinutes = currentBuckets.toList(),
                previousMinutes = prevBuckets.toList(),
                totalCurrentMinutes = curTotal,
                totalPreviousMinutes = prevTotal,
                deltaPercent = delta
            )
        } else {
            val labels = listOf("1-haf", "2-haf", "3-haf", "4-haf")
            val currentBuckets = IntArray(4)
            val prevBuckets = IntArray(4)

            val thisMonth = cal.get(Calendar.MONTH)
            val thisYear = cal.get(Calendar.YEAR)
            val prevCal = Calendar.getInstance().apply {
                timeInMillis = nowMs
                add(Calendar.MONTH, -1)
            }
            val prevMonth = prevCal.get(Calendar.MONTH)
            val prevYear = prevCal.get(Calendar.YEAR)

            val rCal = Calendar.getInstance()
            records.forEach { rec ->
                rCal.timeInMillis = rec.timestamp
                val rMonth = rCal.get(Calendar.MONTH)
                val rYear = rCal.get(Calendar.YEAR)
                val weekIdx = ((rCal.get(Calendar.DAY_OF_MONTH) - 1) / 7).coerceIn(0, 3)
                if (rYear == thisYear && rMonth == thisMonth) {
                    currentBuckets[weekIdx] += 5
                } else if (rYear == prevYear && rMonth == prevMonth) {
                    prevBuckets[weekIdx] += 5
                }
            }

            val curTotal = currentBuckets.sum()
            val prevTotal = prevBuckets.sum()
            val delta = computeDeltaPercent(curTotal, prevTotal)
            GrowthChartUiModel(
                labels = labels,
                currentMinutes = currentBuckets.toList(),
                previousMinutes = prevBuckets.toList(),
                totalCurrentMinutes = curTotal,
                totalPreviousMinutes = prevTotal,
                deltaPercent = delta
            )
        }
    }

    private fun computeDeltaPercent(current: Int, previous: Int): Int {
        return when {
            current == 0 && previous == 0 -> 0
            previous == 0 -> 100
            else -> (((current - previous) * 100f) / previous.toFloat()).toInt()
        }
    }

    private fun formatStudyMinutesUz(totalMinutes: Int): String {
        val hours = totalMinutes / 60
        val mins = totalMinutes % 60
        return when {
            hours > 0 && mins > 0 -> "$hours soat $mins daqiqa"
            hours > 0 -> "$hours soat"
            else -> "$mins daqiqa"
        }
    }

    private fun formatCompactMinutesUz(totalMinutes: Int): String {
        val hours = totalMinutes / 60
        val mins = totalMinutes % 60
        return when {
            hours > 0 && mins > 0 -> "$hours soat $mins daq."
            hours > 0 -> "$hours soat"
            else -> "$mins daq."
        }
    }

    private fun getSavedAvatarIndex(): Int {
        val sp = requireContext().getSharedPreferences("talkme_avatar_prefs", Context.MODE_PRIVATE)
        return sp.getInt("selected_avatar_idx", 0).coerceIn(0, avatarDrawableOptions.lastIndex)
    }

    private fun saveAvatarIndex(index: Int) {
        val sp = requireContext().getSharedPreferences("talkme_avatar_prefs", Context.MODE_PRIVATE)
        sp.edit().putInt("selected_avatar_idx", index).apply()
        applySavedAvatar()
    }

    private fun applySavedAvatar() {
        val idx = getSavedAvatarIndex()
        binding.ivProfileAvatar.setImageResource(avatarDrawableOptions[idx])
    }

    private fun showEditProfileDialog() {
        val ctx = requireContext()
        val density = resources.displayMetrics.density
        val pad = (18 * density).toInt()

        val container = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad / 2, pad, pad / 4)
        }

        val avatarLabel = TextView(ctx).apply {
            text = "Avatar rasmni tanlang:"
            textSize = 13f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(ctx, R.color.color_text_secondary))
        }
        container.addView(avatarLabel)

        var chosenAvatarIdx = getSavedAvatarIndex()
        val avatarRow = LinearLayout(ctx).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            val topMargin = (10 * density).toInt()
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, topMargin, 0, topMargin)
            }
        }

        val avatarCards = mutableListOf<MaterialCardView>()
        avatarDrawableOptions.forEachIndexed { idx, drawableRes ->
            val sizePx = (60 * density).toInt()
            val marginPx = (6 * density).toInt()
            val card = MaterialCardView(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(sizePx, sizePx).apply {
                    setMargins(marginPx, 0, marginPx, 0)
                }
                radius = 30 * density
                strokeColor = ContextCompat.getColor(
                    ctx,
                    if (idx == chosenAvatarIdx) R.color.color_neon_cyan else R.color.color_card_stroke
                )
                strokeWidth = if (idx == chosenAvatarIdx) (3 * density).toInt() else (1 * density).toInt()
                val iv = ImageView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setImageResource(drawableRes)
                }
                addView(iv)
                setOnClickListener {
                    chosenAvatarIdx = idx
                    avatarCards.forEachIndexed { cIdx, cView ->
                        cView.strokeColor = ContextCompat.getColor(
                            ctx,
                            if (cIdx == chosenAvatarIdx) R.color.color_neon_cyan else R.color.color_card_stroke
                        )
                        cView.strokeWidth =
                            if (cIdx == chosenAvatarIdx) (3 * density).toInt() else (1 * density).toInt()
                    }
                }
            }
            avatarCards.add(card)
            avatarRow.addView(card)
        }
        container.addView(avatarRow)

        val nameLabel = TextView(ctx).apply {
            text = "Ismingiz (Name):"
            textSize = 13f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(ctx, R.color.color_text_secondary))
        }
        container.addView(nameLabel)

        val currentName = viewModel.uiState.value.preferences.userName
        val etName = EditText(ctx).apply {
            setText(currentName)
            hint = getString(R.string.settings_user_name_label)
            setSingleLine(true)
            setSelection(text.length)
        }
        container.addView(etName)

        MaterialAlertDialogBuilder(ctx)
            .setTitle("Profilni tahrirlash")
            .setView(container)
            .setNegativeButton("Bekor qilish", null)
            .setPositiveButton("Saqlash") { _, _ ->
                saveAvatarIndex(chosenAvatarIdx)
                val newName = etName.text?.toString().orEmpty().trim()
                if (newName.isNotEmpty()) {
                    viewModel.saveUserName(newName)
                    Toast.makeText(ctx, "Profil muvaffaqiyatli saqlandi!", Toast.LENGTH_SHORT).show()
                }
            }
            .show()
    }
}
