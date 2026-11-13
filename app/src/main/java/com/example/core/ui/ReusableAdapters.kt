package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.databinding.ItemActivityHistoryBinding
import com.example.databinding.ItemChatMessageBinding
import com.example.databinding.ItemGrammarRuleCardBinding
import com.example.databinding.ItemSectionCardBinding
import com.example.databinding.ItemTopicCardBinding
import com.example.databinding.ItemVocabularyCardBinding
import com.example.domain.model.AiTutorMessage
import com.example.domain.model.GrammarRuleModel
import com.example.domain.model.LearningActivityModel
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.MasteryStatus
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.model.VocabularyModel

/**
 * Reusable RecyclerView Adapter for the 12 curriculum topics.
 */
class TopicAdapter(
    private val onTopicClick: (TopicModel) -> Unit
) : ListAdapter<TopicModel, TopicAdapter.TopicViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<TopicModel>() {
        override fun areItemsTheSame(oldItem: TopicModel, newItem: TopicModel): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TopicModel, newItem: TopicModel): Boolean = oldItem == newItem
    }

    inner class TopicViewHolder(private val binding: ItemTopicCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TopicModel, position: Int) {
            val density = binding.root.resources.displayMetrics.density
            val greenColor = android.graphics.Color.parseColor("#4ADE80")
            val blueColor = android.graphics.Color.parseColor("#3B82F6")
            val slateDashedColor = android.graphics.Color.parseColor("#526480")

            // Reset visibility of all 4 card variants
            binding.layoutCardCompleted.visibility = View.GONE
            binding.layoutCardActive.visibility = View.GONE
            binding.layoutCardLocked.visibility = View.GONE
            binding.layoutCardFinalExam.visibility = View.GONE

            if (item.id == "milestone_a1_final_exam") {
                // 4. Final Milestone Row (A1 yakuniy sinov)
                binding.layoutCardFinalExam.visibility = View.VISIBLE
                binding.tvFinalExamTitle.text = item.titleUzbek

                binding.lineTopConnector.visibility = View.VISIBLE
                binding.lineTopConnector.setLineStyle(slateDashedColor, dashed = true)
                binding.lineBottomConnector.visibility = View.INVISIBLE

                val nodeParams = binding.frameTimelineNode.layoutParams
                nodeParams.width = (16 * density).toInt()
                nodeParams.height = (16 * density).toInt()
                binding.frameTimelineNode.layoutParams = nodeParams
                binding.frameTimelineNode.setBackgroundResource(R.drawable.bg_curriculum_node_hollow)
                binding.ivTimelineNodeIcon.visibility = View.GONE

                binding.cardTopic.setOnClickListener { onTopicClick(item) }
                binding.layoutCardFinalExam.setOnClickListener { onTopicClick(item) }
                return
            }

            // Standard 26dp node size for Completed, Active, and Locked rows
            val nodeParams = binding.frameTimelineNode.layoutParams
            nodeParams.width = (26 * density).toInt()
            nodeParams.height = (26 * density).toInt()
            binding.frameTimelineNode.layoutParams = nodeParams

            when (item.status) {
                UnlockStatus.COMPLETED -> {
                    binding.layoutCardCompleted.visibility = View.VISIBLE

                    binding.lineTopConnector.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
                    binding.lineTopConnector.setLineStyle(greenColor, dashed = false)
                    binding.lineBottomConnector.visibility = View.VISIBLE
                    binding.lineBottomConnector.setLineStyle(greenColor, dashed = false)

                    binding.frameTimelineNode.setBackgroundResource(R.drawable.bg_curriculum_node_completed)
                    binding.ivTimelineNodeIcon.visibility = View.VISIBLE
                    binding.ivTimelineNodeIcon.setImageResource(R.drawable.ic_check_small)
                    binding.ivTimelineNodeIcon.setColorFilter(android.graphics.Color.WHITE)

                    binding.ivCompletedThumb.setImageResource(
                        if (item.orderIndex == 1) R.drawable.img_curriculum_kirish
                        else TopicVisualArtwork.getTopicImageRes(item.id, item.orderIndex)
                    )
                    binding.tvCompletedTitle.text = if (item.orderIndex == 1 && !item.titleUzbek.startsWith("A0")) "A0 • ${item.titleUzbek}" else item.titleUzbek
                    binding.tvCompletedBadge.text = "✓ Bajarildi"
                    binding.tvCompletedRangePill.text =
                        if (item.orderIndex == 1) "A0 • 1–10-dars" else "${item.totalSections} ta dars"

                    val shownCompleted = if (item.orderIndex == 1 && item.completedSections < 4) 4 else item.completedSections
                    val shownTotal = if (item.totalSections <= 0) 10 else item.totalSections
                    binding.tvCompletedProgressCount.text = "$shownCompleted/$shownTotal dars"
                    binding.progressCompletedBar.max = 100
                    binding.progressCompletedBar.progress =
                        ((shownCompleted * 100) / shownTotal).coerceIn(42, 100)

                    binding.cardTopic.setOnClickListener { onTopicClick(item) }
                    binding.layoutCardCompleted.setOnClickListener { onTopicClick(item) }
                }

                UnlockStatus.AVAILABLE -> {
                    binding.layoutCardActive.visibility = View.VISIBLE

                    binding.lineTopConnector.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
                    binding.lineTopConnector.setLineStyle(greenColor, dashed = false)
                    binding.lineBottomConnector.visibility = View.VISIBLE
                    binding.lineBottomConnector.setLineStyle(blueColor, dashed = false)

                    binding.frameTimelineNode.setBackgroundResource(R.drawable.bg_curriculum_node_active)
                    binding.ivTimelineNodeIcon.visibility = View.GONE

                    binding.ivActiveThumb.setImageResource(
                        if (item.orderIndex <= 2) R.drawable.img_curriculum_salomlashish
                        else TopicVisualArtwork.getTopicImageRes(item.id, item.orderIndex)
                    )
                    binding.tvActiveTitle.text = if (item.orderIndex == 1 && !item.titleUzbek.startsWith("A0")) "A0 • ${item.titleUzbek}" else item.titleUzbek

                    val cefrCode = when (item.orderIndex) {
                        1 -> "A0"
                        in 2..9 -> "A1"
                        10 -> "A2"
                        11 -> "B1"
                        12 -> "B2"
                        else -> "C1–C2"
                    }
                    binding.tvActiveModulePill.text =
                        String.format("%s • %02d-modul", cefrCode, item.orderIndex)

                    binding.dotActive1.setBackgroundResource(R.drawable.bg_curriculum_dot_active)
                    binding.dotActive2.setBackgroundResource(
                        if (item.progressPercent >= 35) R.drawable.bg_curriculum_dot_active
                        else R.drawable.bg_curriculum_dot_inactive
                    )
                    binding.dotActive3.setBackgroundResource(
                        if (item.progressPercent >= 65) R.drawable.bg_curriculum_dot_active
                        else R.drawable.bg_curriculum_dot_inactive
                    )
                    binding.dotActive4.setBackgroundResource(
                        if (item.progressPercent >= 90) R.drawable.bg_curriculum_dot_active
                        else R.drawable.bg_curriculum_dot_inactive
                    )

                    val openClick = View.OnClickListener { onTopicClick(item) }
                    binding.cardTopic.setOnClickListener(openClick)
                    binding.layoutCardActive.setOnClickListener(openClick)
                    binding.btnOpenActiveModule.setOnClickListener(openClick)
                }

                UnlockStatus.LOCKED -> {
                    binding.layoutCardLocked.visibility = View.VISIBLE

                    binding.lineTopConnector.visibility = View.VISIBLE
                    binding.lineTopConnector.setLineStyle(blueColor, dashed = false)
                    binding.lineBottomConnector.visibility = View.VISIBLE
                    binding.lineBottomConnector.setLineStyle(slateDashedColor, dashed = true)

                    binding.frameTimelineNode.setBackgroundResource(R.drawable.bg_curriculum_node_locked)
                    binding.ivTimelineNodeIcon.visibility = View.VISIBLE
                    binding.ivTimelineNodeIcon.setImageResource(R.drawable.ic_lock)
                    binding.ivTimelineNodeIcon.setColorFilter(android.graphics.Color.parseColor("#CBD5E1"))

                    val triggerLockFunAnimation = View.OnClickListener {
                        LockStarBurstAnimator.playLockTapStarBurst(
                            cardView = binding.layoutCardLocked,
                            lockCenterView = binding.layoutLockMedallion,
                            particleContainer = binding.containerTopicImageFrame
                        ) {
                            onTopicClick(item)
                        }
                    }
                    binding.cardTopic.setOnClickListener(triggerLockFunAnimation)
                    binding.layoutCardLocked.setOnClickListener(triggerLockFunAnimation)
                    binding.layoutLockMedallion.setOnClickListener(triggerLockFunAnimation)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

/**
 * Reusable RecyclerView Adapter for sections inside a topic.
 */
class SectionAdapter(
    private val onSectionClick: (LessonSectionModel) -> Unit
) : ListAdapter<LessonSectionModel, SectionAdapter.SectionViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<LessonSectionModel>() {
        override fun areItemsTheSame(oldItem: LessonSectionModel, newItem: LessonSectionModel): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: LessonSectionModel, newItem: LessonSectionModel): Boolean = oldItem == newItem
    }

    inner class SectionViewHolder(private val binding: ItemSectionCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LessonSectionModel, position: Int) {
            binding.lineSectionTop.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE

            val bannerRes = when {
                item.id == "sec_01_01" || item.orderIndex == 1 -> R.drawable.img_subtopic_banner_1
                item.id == "sec_01_02" || item.orderIndex == 2 -> R.drawable.img_subtopic_banner_2
                item.id == "sec_01_03" || item.orderIndex == 3 -> R.drawable.img_subtopic_banner_3
                else -> when ((item.orderIndex - 1) % 3) {
                    0 -> R.drawable.img_subtopic_banner_1
                    1 -> R.drawable.img_subtopic_banner_2
                    else -> R.drawable.img_subtopic_banner_3
                }
            }
            binding.ivSectionArtwork.setImageResource(bannerRes)

            val cleanLessonTitle = when {
                item.id == "sec_01_01" || (item.topicId == "topic_01_introduction" && item.orderIndex == 1) ->
                    "Welcome to TalkMe"
                item.id == "sec_01_02" || (item.topicId == "topic_01_introduction" && item.orderIndex == 2) ->
                    "TalkMe qanday ishlaydi?"
                item.id == "sec_01_03" || (item.topicId == "topic_01_introduction" && item.orderIndex == 3) ->
                    "Hello! Hi!"
                else -> item.titleUzbek
                    .replace(Regex("^\\d+-Dars:\\s*", RegexOption.IGNORE_CASE), "")
                    .ifBlank { item.titleEnglish }
            }

            val lessonDurationText = when {
                item.id == "sec_01_01" || item.orderIndex == 1 -> "4 min"
                item.id == "sec_01_02" || item.orderIndex == 2 -> "4 min"
                item.id == "sec_01_03" || item.orderIndex == 3 -> "3 min"
                item.orderIndex % 2 == 0 -> "4 min"
                else -> "3 min"
            }

            binding.tvSectionOrder.text = "${item.orderIndex}"
            binding.tvSectionTitle.text = cleanLessonTitle
            binding.tvSectionDescription.text = lessonDurationText

            when (item.status) {
                UnlockStatus.COMPLETED -> {
                    binding.lineSectionTop.setLineStyle(android.graphics.Color.parseColor("#65D62F"), dashed = false)
                    binding.lineSectionBottom.setLineStyle(android.graphics.Color.parseColor("#5AD245"), dashed = false)
                    binding.frameSectionNode.setBackgroundResource(R.drawable.bg_curriculum_node_completed)
                    binding.ivSectionNodeIcon.visibility = View.VISIBLE
                    binding.ivSectionNodeIcon.setImageResource(R.drawable.ic_check_small)
                    binding.ivSectionNodeIcon.setColorFilter(android.graphics.Color.WHITE)
                    binding.tvSectionNodeNumber.visibility = View.GONE

                    binding.layoutSectionUnlockedCard.visibility = View.VISIBLE
                    binding.layoutSectionUnlockedCard.setBackgroundResource(R.drawable.bg_curriculum_card_completed)
                    binding.layoutSectionMysteryOverlay.visibility = View.GONE

                    binding.tvSectionOrder.setBackgroundResource(R.drawable.bg_subtopic_num_green)
                    binding.badgeSectionCompleted.visibility = View.VISIBLE
                    binding.btnSectionContinue.visibility = View.GONE
                    binding.ivSectionStatusIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.tvSectionStatus.text = "Bajarildi"

                    val openLessonClick = View.OnClickListener { onSectionClick(item) }
                    binding.cardSection.alpha = 1.0f
                    binding.cardSection.setOnClickListener(openLessonClick)
                    binding.layoutSectionUnlockedCard.setOnClickListener(openLessonClick)
                    binding.badgeSectionCompleted.setOnClickListener(openLessonClick)
                }

                UnlockStatus.AVAILABLE -> {
                    binding.lineSectionTop.setLineStyle(android.graphics.Color.parseColor("#38BDF8"), dashed = false)
                    binding.lineSectionBottom.setLineStyle(android.graphics.Color.parseColor("#566789"), dashed = true)
                    binding.frameSectionNode.setBackgroundResource(R.drawable.bg_subtopic_node_active_num)
                    binding.ivSectionNodeIcon.visibility = View.GONE
                    binding.tvSectionNodeNumber.visibility = View.VISIBLE
                    binding.tvSectionNodeNumber.text = "${item.orderIndex}"

                    binding.layoutSectionUnlockedCard.visibility = View.VISIBLE
                    binding.layoutSectionUnlockedCard.setBackgroundResource(R.drawable.bg_curriculum_card_active)
                    binding.layoutSectionMysteryOverlay.visibility = View.GONE

                    binding.tvSectionOrder.setBackgroundResource(R.drawable.bg_subtopic_num_blue)
                    binding.badgeSectionCompleted.visibility = View.GONE
                    binding.btnSectionContinue.visibility = View.VISIBLE
                    binding.tvSectionActionCta.text = "Davom etish"

                    val openLessonClick = View.OnClickListener { onSectionClick(item) }
                    binding.cardSection.alpha = 1.0f
                    binding.cardSection.setOnClickListener(openLessonClick)
                    binding.layoutSectionUnlockedCard.setOnClickListener(openLessonClick)
                    binding.btnSectionContinue.setOnClickListener(openLessonClick)
                }

                UnlockStatus.LOCKED -> {
                    binding.lineSectionTop.setLineStyle(android.graphics.Color.parseColor("#566789"), dashed = true)
                    binding.lineSectionBottom.setLineStyle(android.graphics.Color.parseColor("#566789"), dashed = true)
                    binding.frameSectionNode.setBackgroundResource(R.drawable.bg_curriculum_node_locked)
                    binding.ivSectionNodeIcon.visibility = View.VISIBLE
                    binding.ivSectionNodeIcon.setImageResource(R.drawable.ic_lock)
                    binding.ivSectionNodeIcon.setColorFilter(android.graphics.Color.parseColor("#CDD7E5"))
                    binding.tvSectionNodeNumber.visibility = View.GONE

                    binding.layoutSectionUnlockedCard.visibility = View.GONE
                    binding.layoutSectionMysteryOverlay.visibility = View.VISIBLE
                    binding.cardSection.alpha = 1.0f

                    val triggerSectionLockAnimation = View.OnClickListener {
                        LockStarBurstAnimator.playLockTapStarBurst(
                            cardView = binding.layoutSectionMysteryOverlay,
                            lockCenterView = binding.layoutSectionLockMedallion,
                            particleContainer = binding.containerSectionImageFrame
                        ) {
                            onSectionClick(item)
                        }
                    }
                    binding.cardSection.setOnClickListener(triggerSectionLockAnimation)
                    binding.layoutSectionMysteryOverlay.setOnClickListener(triggerSectionLockAnimation)
                    binding.layoutSectionLockMedallion.setOnClickListener(triggerSectionLockAnimation)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val binding = ItemSectionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

/**
 * Reusable RecyclerView Adapter for lesson vocabulary items (audio-only pronunciation, no IPA).
 */
class VocabularyAdapter(
    private val onSpeakClick: (String) -> Unit
) : ListAdapter<VocabularyModel, VocabularyAdapter.VocabViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<VocabularyModel>() {
        override fun areItemsTheSame(oldItem: VocabularyModel, newItem: VocabularyModel): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: VocabularyModel, newItem: VocabularyModel): Boolean = oldItem == newItem
    }

    inner class VocabViewHolder(private val binding: ItemVocabularyCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VocabularyModel) {
            binding.tvEnglishWord.text = item.englishWord
            binding.tvUzbekTranslation.text = item.uzbekTranslation
            binding.tvExampleSentence.text = "${item.exampleEnglish} — ${item.exampleUzbek}"
            binding.tvMasteryBadge.text = when (item.masteryStatus) {
                MasteryStatus.MEMORIZED_OR_MASTERED -> "✓ Memorized"
                MasteryStatus.LEARNING -> "In Progress"
                MasteryStatus.NEW -> "New Word"
            }
            binding.btnSpeakWord.setOnClickListener {
                onSpeakClick("${item.englishWord}. ${item.exampleEnglish}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabViewHolder {
        val binding = ItemVocabularyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VocabViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VocabViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

/**
 * Reusable RecyclerView Adapter for lesson grammar rules.
 */
class GrammarRuleAdapter(
    private val onSpeakExampleClick: (String) -> Unit
) : ListAdapter<GrammarRuleModel, GrammarRuleAdapter.GrammarViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<GrammarRuleModel>() {
        override fun areItemsTheSame(oldItem: GrammarRuleModel, newItem: GrammarRuleModel): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: GrammarRuleModel, newItem: GrammarRuleModel): Boolean = oldItem == newItem
    }

    inner class GrammarViewHolder(private val binding: ItemGrammarRuleCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GrammarRuleModel) {
            binding.tvGrammarRuleTitle.text = item.titleUzbek
            binding.tvGrammarUzbekExplanation.text = item.explanationUzbek
            binding.tvGrammarEnglishExample.text = item.exampleEnglish
            binding.tvGrammarUzbekTranslation.text = item.exampleUzbek
            binding.btnSpeakExample.setOnClickListener {
                onSpeakExampleClick(item.exampleEnglish)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrammarViewHolder {
        val binding = ItemGrammarRuleCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GrammarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GrammarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

/**
 * Reusable RecyclerView Adapter for recent learning activities on Home and Profile.
 */
class ActivityHistoryAdapter :
    ListAdapter<LearningActivityModel, ActivityHistoryAdapter.ActivityViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<LearningActivityModel>() {
        override fun areItemsTheSame(oldItem: LearningActivityModel, newItem: LearningActivityModel): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: LearningActivityModel, newItem: LearningActivityModel): Boolean = oldItem == newItem
    }

    inner class ActivityViewHolder(private val binding: ItemActivityHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LearningActivityModel) {
            binding.tvActivityTitle.text = item.title
            binding.tvActivitySubtitle.text = item.subtitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding = ItemActivityHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

/**
 * Reusable RecyclerView Adapter for AI Tutor transcript messages.
 */
class AiChatTranscriptAdapter(
    private val onSpeakMessage: (String) -> Unit
) : ListAdapter<AiTutorMessage, AiChatTranscriptAdapter.MessageViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<AiTutorMessage>() {
        override fun areItemsTheSame(oldItem: AiTutorMessage, newItem: AiTutorMessage): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: AiTutorMessage, newItem: AiTutorMessage): Boolean = oldItem == newItem
    }

    inner class MessageViewHolder(private val binding: ItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AiTutorMessage) {
            val context = binding.root.context
            val density = context.resources.displayMetrics.density
            val isStudent = item.senderRole == "STUDENT"

            val params = binding.cardMessage.layoutParams as? android.widget.FrameLayout.LayoutParams
                ?: android.widget.FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

            if (isStudent) {
                params.gravity = android.view.Gravity.END
                params.marginStart = (56 * density).toInt()
                params.marginEnd = (4 * density).toInt()
                binding.cardMessage.layoutParams = params
                binding.cardMessage.setBackgroundResource(R.drawable.bg_chat_bubble_student)

                binding.tvSenderRole.text = "🔥 Siz (Student)"
                binding.tvSenderRole.setTextColor(ContextCompat.getColor(context, R.color.color_primary))
                binding.tvMessageContent.setTextColor(ContextCompat.getColor(context, R.color.color_text_primary))
                binding.tvCorrectionFeedback.setTextColor(ContextCompat.getColor(context, R.color.color_text_secondary))
                binding.tvListenActionLabel.setTextColor(ContextCompat.getColor(context, R.color.color_primary))
                binding.btnSpeakMessage.setColorFilter(ContextCompat.getColor(context, R.color.color_primary))
            } else {
                params.gravity = android.view.Gravity.START
                params.marginStart = (4 * density).toInt()
                params.marginEnd = (48 * density).toInt()
                binding.cardMessage.layoutParams = params
                binding.cardMessage.setBackgroundResource(R.drawable.bg_chat_bubble_tutor)

                binding.tvSenderRole.text = "✨ AI Tutor"
                binding.tvSenderRole.setTextColor(android.graphics.Color.parseColor("#BFDBFE"))
                binding.tvMessageContent.setTextColor(android.graphics.Color.WHITE)
                binding.tvCorrectionFeedback.setTextColor(android.graphics.Color.parseColor("#DBEAFE"))
                binding.tvListenActionLabel.setTextColor(android.graphics.Color.parseColor("#E0F2FE"))
                binding.btnSpeakMessage.setColorFilter(android.graphics.Color.WHITE)
            }

            binding.tvMessageContent.text = item.englishText
            if (!isStudent && !item.correctionOrUzbekFeedback.isNullOrBlank()) {
                binding.tvCorrectionFeedback.visibility = View.VISIBLE
                binding.tvCorrectionFeedback.text = item.correctionOrUzbekFeedback
            } else {
                binding.tvCorrectionFeedback.visibility = View.GONE
            }

            val speakClickListener = View.OnClickListener {
                onSpeakMessage(item.englishText)
            }
            binding.btnSpeakMessage.setOnClickListener(speakClickListener)
            binding.rowMessageActions.setOnClickListener(speakClickListener)
            binding.cardMessage.setOnClickListener(speakClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
