package com.example.feature.dailytasks

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.core.common.AppConstants
import com.example.databinding.FragmentDailyTasksBinding
import com.example.domain.model.DailyPlanDayRecord
import com.example.domain.model.DailyTaskStepModel
import com.example.domain.model.PracticeCategory
import com.example.domain.model.TopicMistakeWarning
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.model.UserMistakeModel
import com.example.domain.repository.LearningRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DailyTasksUiState(
    val studiedTopics: List<TopicModel> = emptyList(),
    val selectedTopic: TopicModel? = null,
    val steps: List<DailyTaskStepModel> = emptyList(),
    val completedStepsCount: Int = 0,
    val totalStepsCount: Int = 6,
    val isTodayAllCompleted: Boolean = false,
    val nextSequentialStep: DailyTaskStepModel? = null,
    val topicWarnings: List<TopicMistakeWarning> = emptyList(),
    val selectedTopicMistakes: List<UserMistakeModel> = emptyList(),
    val allUnresolvedMistakes: List<UserMistakeModel> = emptyList(),
    val historyRecords: List<DailyPlanDayRecord> = emptyList()
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DailyTasksViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val selectedTopicIdFlow = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            learningRepository.ensureSeeded()
        }
    }

    private val stepsForSelectedTopicFlow = combine(
        learningRepository.observeTopics(),
        selectedTopicIdFlow
    ) { topics, manualTopicId ->
        val studied = topics.filter { it.status != UnlockStatus.LOCKED }.ifEmpty { topics.take(1) }
        val activeTopic = studied.find { it.id == manualTopicId }
            ?: studied.lastOrNull()
            ?: topics.firstOrNull()
        activeTopic?.id ?: AppConstants.INITIAL_TOPIC_ID
    }.flatMapLatest { topicId ->
        learningRepository.observeTodayTaskSteps(topicId)
    }

    val uiState: StateFlow<DailyTasksUiState> = combine(
        learningRepository.observeTopics(),
        selectedTopicIdFlow,
        stepsForSelectedTopicFlow,
        learningRepository.observeUnresolvedMistakes(),
        learningRepository.observeDailyPlanHistory()
    ) { topics, manualTopicId, steps, mistakes, history ->
        val studied = topics.filter { it.status != UnlockStatus.LOCKED }.ifEmpty { topics.take(1) }
        val activeTopic = studied.find { it.id == manualTopicId }
            ?: studied.lastOrNull()
            ?: topics.firstOrNull()

        val completedCount = steps.count { it.isCompletedToday }
        val totalCount = steps.size.coerceAtLeast(6)
        val isAllDone = completedCount >= totalCount && steps.isNotEmpty()
        val nextStep = steps.firstOrNull { !it.isCompletedToday } ?: steps.firstOrNull()

        val warnings = mistakes
            .groupBy { it.topicId }
            .map { (tId, topicMistakeList) ->
                val topicObj = topics.find { it.id == tId }
                val title = topicObj?.let { "${it.orderIndex}. ${it.titleEnglish} — ${it.titleUzbek}" }
                    ?: topicMistakeList.first().topicTitle
                val cats = topicMistakeList.map { it.skillCategory.displayName }.distinct()
                TopicMistakeWarning(
                    topicId = tId,
                    topicTitle = title,
                    totalMistakesCount = topicMistakeList.size,
                    affectedSkillsSummary = cats.joinToString(", ")
                )
            }

        val topicMistakes = if (activeTopic != null) {
            mistakes.filter { it.topicId == activeTopic.id }.ifEmpty { mistakes }
        } else {
            mistakes
        }

        DailyTasksUiState(
            studiedTopics = studied,
            selectedTopic = activeTopic,
            steps = steps,
            completedStepsCount = completedCount,
            totalStepsCount = totalCount,
            isTodayAllCompleted = isAllDone,
            nextSequentialStep = nextStep,
            topicWarnings = warnings,
            selectedTopicMistakes = topicMistakes,
            allUnresolvedMistakes = mistakes,
            historyRecords = history
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DailyTasksUiState()
    )

    fun selectTopic(topicId: String) {
        selectedTopicIdFlow.value = topicId
    }

    fun resolveMistake(mistakeId: String) {
        viewModelScope.launch {
            learningRepository.resolveUserMistake(mistakeId)
        }
    }
}

/**
 * Daily Tasks & Mistake Tracker Screen (`Tasks` bottom navigation tab).
 * - Tracks daily completion of all 6 topic activities (Grammar Rules, New Vocabulary, Reading, Writing, Listening, Speaking).
 * - Supports both sequential ("Birin-ketin bajarish") and individual skill execution.
 * - Tracks completed days vs missed/incomplete days ("Bajarilmagan kunlar").
 * - Displays topic-based mistake warnings and interactive error remediation cards.
 */
@AndroidEntryPoint
class DailyTasksFragment : Fragment() {

    private var _binding: FragmentDailyTasksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DailyTasksViewModel by viewModels()

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

        binding.btnStartSequentialTasks.setOnClickListener {
            val state = viewModel.uiState.value
            val step = state.nextSequentialStep ?: return@setOnClickListener
            val activeTopicId = state.selectedTopic?.id ?: AppConstants.INITIAL_TOPIC_ID
            launchTaskStep(activeTopicId, step.category, isSequential = true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val activeTopicId = state.selectedTopic?.id ?: AppConstants.INITIAL_TOPIC_ID
                    renderTopicTabs(state.studiedTopics, activeTopicId)
                    renderHeroSummary(state)
                    renderMistakeWarnings(state)
                    renderDailyTaskSteps(activeTopicId, state.steps)
                    renderDailyHistory(state.historyRecords)
                }
            }
        }
    }

    private fun renderTopicTabs(topics: List<TopicModel>, selectedTopicId: String) {
        binding.layoutDailyTopicTabs.removeAllViews()
        val density = resources.displayMetrics.density

        topics.forEach { topic ->
            val isSelected = topic.id == selectedTopicId
            val button = MaterialButton(
                requireContext(),
                null,
                com.google.android.material.R.attr.materialButtonOutlinedStyle
            ).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    (38 * density).toInt()
                ).apply {
                    marginEnd = (8 * density).toInt()
                }
                text = "${topic.orderIndex}. ${topic.titleEnglish}"
                isAllCaps = false
                textSize = 12f
                insetTop = 0
                insetBottom = 0
                cornerRadius = (19 * density).toInt()
                if (isSelected) {
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_primary))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_on_primary))
                } else {
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_surface))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_primary))
                }
                setOnClickListener {
                    viewModel.selectTopic(topic.id)
                }
            }
            binding.layoutDailyTopicTabs.addView(button)
        }
    }

    private fun renderHeroSummary(state: DailyTasksUiState) {
        val activeTopic = state.selectedTopic
        if (activeTopic != null) {
            binding.tvDailyActiveTopicBadge.text =
                "Mavzu: ${activeTopic.orderIndex}. ${activeTopic.titleEnglish} — ${activeTopic.titleUzbek}"
        }

        binding.tvDailyCompletedCount.text =
            "Bugungi reja: ${state.completedStepsCount} / ${state.totalStepsCount} bosqich bajarildi"

        val progressPct = if (state.totalStepsCount > 0) {
            (state.completedStepsCount * 100) / state.totalStepsCount
        } else {
            0
        }
        binding.progressDailyTasks.progress = progressPct

        if (state.isTodayAllCompleted) {
            binding.tvDailyStatusBadge.text = "✓ Bajarilgan kun"
            binding.tvDailyStatusBadge.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.color_success)
            )
            binding.tvDailySequentialHint.text =
                "Barakalla! Bugungi barcha 6 ta mashq (Qoidalar, So'zlar, Reading, Writing, Listening, Speaking) to'liq bajarildi!"
            binding.btnStartSequentialTasks.text = getString(R.string.daily_tasks_btn_review_again)
        } else {
            val remaining = state.totalStepsCount - state.completedStepsCount
            binding.tvDailyStatusBadge.text = "$remaining ta qoldi"
            binding.tvDailyStatusBadge.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.color_primary)
            )
            val nextStep = state.nextSequentialStep
            if (nextStep != null) {
                binding.tvDailySequentialHint.text =
                    "Keyingi navbatdagi mashq: ${nextStep.stepOrder}-bosqich • ${nextStep.title}"
                binding.btnStartSequentialTasks.text =
                    "Birin-ketin bajarish (${nextStep.stepOrder}. ${nextStep.category.displayName})"
            } else {
                binding.btnStartSequentialTasks.text = getString(R.string.daily_tasks_btn_sequential)
            }
        }
    }

    private fun renderMistakeWarnings(state: DailyTasksUiState) {
        val selectedTopicId = state.selectedTopic?.id
        val activeWarning = state.topicWarnings.find { it.topicId == selectedTopicId }
            ?: state.topicWarnings.firstOrNull()

        if (activeWarning != null) {
            binding.cardTopicWarningAlert.visibility = View.VISIBLE
            binding.cardTopicWarningAlert.strokeColor =
                ContextCompat.getColor(requireContext(), R.color.color_error)
            binding.tvTopicWarningBannerTitle.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.color_error)
            )
            binding.tvTopicWarningBannerTitle.text =
                "⚠️ Ogohlantirish: ${activeWarning.topicTitle} (${activeWarning.totalMistakesCount} ta xato)"
            binding.tvTopicWarningBannerBody.text =
                "Ushbu mavzuda ${activeWarning.affectedSkillsSummary} bo'yicha bilmagan qoida va so'zlaringiz bor. Quyida xatolarni ko'rib chiqing va mashq qilib tuzating."
        } else {
            binding.cardTopicWarningAlert.visibility = View.VISIBLE
            binding.cardTopicWarningAlert.strokeColor =
                ContextCompat.getColor(requireContext(), R.color.color_success)
            binding.tvTopicWarningBannerTitle.setTextColor(
                ContextCompat.getColor(requireContext(), R.color.color_success)
            )
            binding.tvTopicWarningBannerTitle.text = "✓ Xatolar topilmadi"
            binding.tvTopicWarningBannerBody.text = getString(R.string.daily_tasks_no_mistakes)
        }

        binding.containerMistakeCards.removeAllViews()
        val density = resources.displayMetrics.density

        state.selectedTopicMistakes.forEach { mistake ->
            val card = MaterialCardView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = (10 * density).toInt()
                }
                radius = 14 * density
                cardElevation = 1 * density
                strokeWidth = (1 * density).toInt()
                strokeColor = ContextCompat.getColor(requireContext(), R.color.color_divider)
                setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_surface))
            }

            val inner = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                val pad = (14 * density).toInt()
                setPadding(pad, pad, pad, pad)
            }

            val topRow = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
            }

            val badgeTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                text = "${mistake.skillCategory.displayName.uppercase()} • ${mistake.topicTitle}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_error))
                textSize = 11f
                setTypeface(typeface, Typeface.BOLD)
            }

            val countTv = TextView(requireContext()).apply {
                text = "${mistake.mistakeCount}x xato"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_secondary))
                textSize = 11f
                setTypeface(typeface, Typeface.BOLD)
            }

            topRow.addView(badgeTv)
            topRow.addView(countTv)
            inner.addView(topRow)

            val promptTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (6 * density).toInt()
                }
                text = "Savol / So'z: ${mistake.itemPrompt}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_primary))
                textSize = 14f
                setTypeface(typeface, Typeface.BOLD)
            }
            inner.addView(promptTv)

            val wrongTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (4 * density).toInt()
                }
                text = "✗ Sizning javobingiz: ${mistake.userWrongAnswer}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_error))
                textSize = 13f
            }
            inner.addView(wrongTv)

            val correctTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (2 * density).toInt()
                }
                text = "✓ To'g'ri javob: ${mistake.correctAnswer}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_success))
                textSize = 13f
                setTypeface(typeface, Typeface.BOLD)
            }
            inner.addView(correctTv)

            val explainTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (4 * density).toInt()
                }
                text = "Qoida izohi: ${mistake.explanationUzbek}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_secondary))
                textSize = 12f
            }
            inner.addView(explainTv)

            val actionsRow = LinearLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (10 * density).toInt()
                }
                orientation = LinearLayout.HORIZONTAL
            }

            val practiceBtn = MaterialButton(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, (40 * density).toInt(), 1.2f).apply {
                    marginEnd = (8 * density).toInt()
                }
                text = "Mashq qilib tuzatish"
                isAllCaps = false
                textSize = 12f
                insetTop = 0
                insetBottom = 0
                setOnClickListener {
                    launchTaskStep(mistake.topicId, mistake.skillCategory, isSequential = false)
                }
            }

            val resolveBtn = MaterialButton(
                requireContext(),
                null,
                com.google.android.material.R.attr.materialButtonOutlinedStyle
            ).apply {
                layoutParams = LinearLayout.LayoutParams(0, (40 * density).toInt(), 0.8f)
                text = "Tuzatildi ✓"
                isAllCaps = false
                textSize = 12f
                insetTop = 0
                insetBottom = 0
                setOnClickListener {
                    viewModel.resolveMistake(mistake.id)
                }
            }

            actionsRow.addView(practiceBtn)
            actionsRow.addView(resolveBtn)
            inner.addView(actionsRow)

            card.addView(inner)
            binding.containerMistakeCards.addView(card)
        }
    }

    private fun renderDailyTaskSteps(activeTopicId: String, steps: List<DailyTaskStepModel>) {
        binding.containerDailyTaskSteps.removeAllViews()
        val density = resources.displayMetrics.density

        steps.chunked(2).forEach { pair ->
            val rowLayout = LinearLayout(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = (10 * density).toInt()
                }
                orientation = LinearLayout.HORIZONTAL
                isBaselineAligned = false
            }

            pair.forEachIndexed { idx, step ->
                val card = MaterialCardView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1f
                    ).apply {
                        if (idx == 0 && pair.size > 1) {
                            marginEnd = (5 * density).toInt()
                        } else if (idx == 1) {
                            marginStart = (5 * density).toInt()
                        }
                    }
                    radius = 16 * density
                    cardElevation = 2f * density
                    strokeWidth = (1 * density).toInt()
                    strokeColor = if (step.isCompletedToday) {
                        ContextCompat.getColor(requireContext(), R.color.color_success)
                    } else {
                        ContextCompat.getColor(requireContext(), R.color.color_card_stroke)
                    }
                    setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_surface))
                    isClickable = true
                    isFocusable = true
                    setOnClickListener {
                        launchTaskStep(activeTopicId, step.category, isSequential = false)
                    }
                }

                val cardCol = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                }

                // 1. Tall Top Image Container (Uzum.uz Card Style)
                val imgFrame = android.widget.FrameLayout(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (138 * density).toInt()
                    )
                }

                val coverImg = android.widget.ImageView(requireContext()).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
                    setImageResource(
                        com.example.core.ui.TopicVisualArtwork.getSkillImageRes(step.category)
                    )
                }
                imgFrame.addView(coverImg)

                if (step.unresolvedMistakeCount > 0) {
                    val errBadge = TextView(requireContext()).apply {
                        layoutParams = android.widget.FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            Gravity.BOTTOM or Gravity.START
                        ).apply {
                            val m = (8 * density).toInt()
                            setMargins(m, m, m, m)
                        }
                        setBackgroundResource(R.drawable.bg_new_badge)
                        val pxH = (8 * density).toInt()
                        val pxV = (3 * density).toInt()
                        setPadding(pxH, pxV, pxH, pxV)
                        text = "⚠️ ${step.unresolvedMistakeCount} ta xato"
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        textSize = 10f
                        setTypeface(typeface, Typeface.BOLD)
                    }
                    imgFrame.addView(errBadge)
                }

                cardCol.addView(imgFrame)

                // 2. All Text & Action Button Cleanly Below the Image
                val textCol = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                    val pad = (10 * density).toInt()
                    setPadding(pad, pad, pad, pad)
                }

                val titleTv = TextView(requireContext()).apply {
                    text = step.title
                    maxLines = 1
                    ellipsize = android.text.TextUtils.TruncateAt.END
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_primary))
                    textSize = 14f
                    setTypeface(typeface, Typeface.BOLD)
                }
                textCol.addView(titleTv)

                val subTv = TextView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        topMargin = (2 * density).toInt()
                    }
                    text = step.subtitleUzbek
                    maxLines = 2
                    minLines = 2
                    ellipsize = android.text.TextUtils.TruncateAt.END
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_secondary))
                    textSize = 11f
                }
                textCol.addView(subTv)

                val statusPill = TextView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        topMargin = (6 * density).toInt()
                    }
                    setBackgroundResource(R.drawable.bg_uzum_metric_pill)
                    val pxH = (8 * density).toInt()
                    val pxV = (2 * density).toInt()
                    setPadding(pxH, pxV, pxH, pxV)
                    text = if (step.isCompletedToday) "✓ Bajarildi" else "Jarayonda"
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_primary))
                    textSize = 10f
                    setTypeface(typeface, Typeface.BOLD)
                }
                textCol.addView(statusPill)

                val actionTv = TextView(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        (34 * density).toInt()
                    ).apply {
                        topMargin = (8 * density).toInt()
                    }
                    setBackgroundResource(R.drawable.bg_uzum_action_btn)
                    gravity = Gravity.CENTER
                    text = if (step.isCompletedToday) "Qayta ko'rish" else "Bajarish"
                    setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            if (step.isCompletedToday) R.color.color_success else R.color.color_primary
                        )
                    )
                    textSize = 12f
                    setTypeface(typeface, Typeface.BOLD)
                    setOnClickListener {
                        launchTaskStep(activeTopicId, step.category, isSequential = false)
                    }
                }
                textCol.addView(actionTv)

                cardCol.addView(textCol)
                card.addView(cardCol)
                rowLayout.addView(card)
            }

            if (pair.size == 1) {
                val spacer = View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(0, 1, 1f).apply {
                        marginStart = (5 * density).toInt()
                    }
                }
                rowLayout.addView(spacer)
            }

            binding.containerDailyTaskSteps.addView(rowLayout)
        }
    }

    private fun renderDailyHistory(records: List<DailyPlanDayRecord>) {
        binding.containerDailyHistory.removeAllViews()
        val density = resources.displayMetrics.density

        records.forEach { day ->
            val card = MaterialCardView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = (10 * density).toInt()
                }
                radius = 12 * density
                cardElevation = 1 * density
                strokeWidth = (1 * density).toInt()
                strokeColor = if (day.isFullyCompleted) {
                    ContextCompat.getColor(requireContext(), R.color.color_success)
                } else {
                    ContextCompat.getColor(requireContext(), R.color.color_error)
                }
                setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_surface))
            }

            val col = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                val pad = (12 * density).toInt()
                setPadding(pad, pad, pad, pad)
            }

            val headerRow = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
            }

            val dateTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
                text = "${day.displayDateLabel} • ${day.topicTitle}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_primary))
                textSize = 14f
                setTypeface(typeface, Typeface.BOLD)
            }

            val badgeTv = TextView(requireContext()).apply {
                text = if (day.isFullyCompleted) {
                    "✓ Bajarilgan kun (${day.completedCount}/${day.totalCount})"
                } else {
                    "✗ Bajarilmagan kun (${day.completedCount}/${day.totalCount})"
                }
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        if (day.isFullyCompleted) R.color.color_success else R.color.color_error
                    )
                )
                textSize = 12f
                setTypeface(typeface, Typeface.BOLD)
            }

            headerRow.addView(dateTv)
            headerRow.addView(badgeTv)
            col.addView(headerRow)

            val detailTv = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (4 * density).toInt()
                }
                text = day.missingTasksSummary
                setTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_secondary))
                textSize = 12f
            }
            col.addView(detailTv)

            card.addView(col)
            binding.containerDailyHistory.addView(card)
        }
    }

    private fun launchTaskStep(
        topicId: String,
        category: PracticeCategory,
        isSequential: Boolean
    ) {
        when (category) {
            PracticeCategory.VOCABULARY -> {
                findNavController().navigate(
                    R.id.flipCardFragment,
                    bundleOf(
                        AppConstants.ARG_TOPIC_ID to topicId,
                        AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to isSequential
                    )
                )
            }
            PracticeCategory.SPEAKING -> {
                findNavController().navigate(
                    R.id.speakingAiFragment,
                    bundleOf(
                        AppConstants.ARG_TOPIC_ID to topicId,
                        AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to isSequential
                    )
                )
            }
            else -> {
                findNavController().navigate(
                    R.id.exerciseSessionFragment,
                    bundleOf(
                        AppConstants.ARG_PRACTICE_CATEGORY to category.id,
                        AppConstants.ARG_TOPIC_ID to topicId,
                        AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to isSequential
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
