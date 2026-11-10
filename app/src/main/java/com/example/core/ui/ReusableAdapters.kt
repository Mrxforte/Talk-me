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

        fun bind(item: TopicModel) {
            val ctx = binding.root.context
            binding.tvTopicOrder.text = String.format("%02d-DARS", item.orderIndex)

            binding.ivTopicArtwork.setImageResource(
                TopicVisualArtwork.getTopicImageRes(item.id, item.orderIndex)
            )

            when (item.status) {
                UnlockStatus.COMPLETED -> {
                    binding.layoutMysteryVaultOverlay.visibility = View.GONE
                    binding.tvTopicTitle.text = item.titleEnglish
                    binding.tvTopicUzbek.text = item.titleUzbek
                    binding.tvTopicDescription.text = item.descriptionUzbek
                    binding.progressTopic.progress = item.progressPercent
                    binding.tvTopicProgressText.text = "${item.completedSections}/${item.totalSections} bo'lim"

                    binding.ivTopicStatus.setImageResource(R.drawable.ic_check_circle)
                    binding.tvTopicStatusBadge.text = "✓ Tugallangan"
                    binding.tvTopicStatusBadge.setTextColor(ContextCompat.getColor(ctx, R.color.color_success))
                    binding.cardTopic.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.color_surface))
                    binding.tvTopicActionCta.text = "Qayta ko'rish"
                    binding.tvTopicActionCta.setTextColor(ContextCompat.getColor(ctx, R.color.color_success))
                    binding.cardTopic.alpha = 1.0f
                    binding.cardTopic.setOnClickListener { onTopicClick(item) }
                }
                UnlockStatus.AVAILABLE -> {
                    binding.layoutMysteryVaultOverlay.visibility = View.GONE
                    binding.tvTopicTitle.text = item.titleEnglish
                    binding.tvTopicUzbek.text = item.titleUzbek
                    binding.tvTopicDescription.text = item.descriptionUzbek
                    binding.progressTopic.progress = item.progressPercent
                    binding.tvTopicProgressText.text = "${item.completedSections}/${item.totalSections} bo'lim"

                    binding.ivTopicStatus.setImageResource(R.drawable.ic_nav_lessons)
                    binding.tvTopicStatusBadge.text = "Ochiq"
                    binding.tvTopicStatusBadge.setTextColor(ContextCompat.getColor(ctx, R.color.color_primary_variant))
                    binding.cardTopic.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.color_surface))
                    binding.tvTopicActionCta.text = "Darsni boshlash"
                    binding.tvTopicActionCta.setTextColor(ContextCompat.getColor(ctx, R.color.color_primary))
                    binding.cardTopic.alpha = 1.0f
                    binding.cardTopic.setOnClickListener { onTopicClick(item) }
                }
                UnlockStatus.LOCKED -> {
                    // Hide real topic content so locked missions/levels remain mysterious to the user
                    binding.layoutMysteryVaultOverlay.visibility = View.VISIBLE
                    binding.tvMysteryVaultHint.text = "★ MAXFIY MISSIYA ★"
                    binding.tvTopicTitle.text = "❓ Maxfiy Missiya #${item.orderIndex}"
                    binding.tvTopicUzbek.text = "Sirli daraja • Noma'lum"
                    binding.tvTopicDescription.text =
                        "Bu missiya hali sir saqlanmoqda! O'tgan mavzuni tugating yoki qulf ustiga bosing ✨"
                    binding.progressTopic.progress = 0
                    binding.tvTopicProgressText.text = "??? bo'lim"

                    binding.ivTopicStatus.setImageResource(R.drawable.ic_lock)
                    binding.tvTopicStatusBadge.text = "Sirli 🔒"
                    binding.tvTopicStatusBadge.setTextColor(ContextCompat.getColor(ctx, R.color.color_text_muted))
                    binding.cardTopic.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.color_surface))
                    binding.tvTopicActionCta.text = "🔒 Qulfni bosing ✨"
                    binding.tvTopicActionCta.setTextColor(ContextCompat.getColor(ctx, R.color.color_primary_variant))
                    binding.cardTopic.alpha = 0.96f

                    val triggerLockFunAnimation = View.OnClickListener {
                        binding.tvMysteryVaultHint.text = "✨ Oldingi darsni tugating! ✨"
                        LockStarBurstAnimator.playLockTapStarBurst(
                            cardView = binding.cardTopic,
                            lockCenterView = binding.layoutLockMedallion,
                            particleContainer = binding.containerTopicImageFrame
                        ) {
                            onTopicClick(item)
                        }
                    }
                    binding.cardTopic.setOnClickListener(triggerLockFunAnimation)
                    binding.layoutLockMedallion.setOnClickListener(triggerLockFunAnimation)
                    binding.btnTopRightStatusBadge.setOnClickListener(triggerLockFunAnimation)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ItemTopicCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(getItem(position))
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

        fun bind(item: LessonSectionModel) {
            val ctx = binding.root.context
            binding.tvSectionOrder.text = "${item.orderIndex}-BOSQICH"
            binding.ivSectionArtwork.setImageResource(
                TopicVisualArtwork.getSectionImageRes(item.topicId, item.id, item.orderIndex)
            )

            when (item.status) {
                UnlockStatus.COMPLETED -> {
                    binding.layoutSectionMysteryOverlay.visibility = View.GONE
                    binding.tvSectionTitle.text = "${item.titleEnglish} — ${item.titleUzbek}"
                    binding.tvSectionDescription.text = item.objectiveUzbek
                    binding.ivSectionStatusIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.tvSectionStatus.text = ctx.getString(R.string.section_status_completed)
                    binding.tvSectionStatus.setTextColor(ContextCompat.getColor(ctx, R.color.color_success))
                    binding.tvSectionActionCta.text = "Qayta ko'rish ✓"
                    binding.cardSection.alpha = 1.0f
                    binding.cardSection.setOnClickListener { onSectionClick(item) }
                }
                UnlockStatus.AVAILABLE -> {
                    binding.layoutSectionMysteryOverlay.visibility = View.GONE
                    binding.tvSectionTitle.text = "${item.titleEnglish} — ${item.titleUzbek}"
                    binding.tvSectionDescription.text = item.objectiveUzbek
                    binding.ivSectionStatusIcon.setImageResource(R.drawable.ic_nav_lessons)
                    binding.tvSectionStatus.text = ctx.getString(R.string.section_status_unlocked)
                    binding.tvSectionStatus.setTextColor(ContextCompat.getColor(ctx, R.color.color_primary))
                    binding.tvSectionActionCta.text = "Darsga kirish →"
                    binding.cardSection.alpha = 1.0f
                    binding.cardSection.setOnClickListener { onSectionClick(item) }
                }
                UnlockStatus.LOCKED -> {
                    binding.layoutSectionMysteryOverlay.visibility = View.VISIBLE
                    binding.tvSectionTitle.text = "❓ Maxfiy Bosqich #${item.orderIndex}"
                    binding.tvSectionDescription.text =
                        "Ushbu bosqich sir saqlanmoqda! Oldingi bo'limni yakunlab qulfni oching ✨"
                    binding.ivSectionStatusIcon.setImageResource(R.drawable.ic_lock)
                    binding.tvSectionStatus.text = "Sirli bosqich 🔒"
                    binding.tvSectionStatus.setTextColor(ContextCompat.getColor(ctx, R.color.color_text_muted))
                    binding.tvSectionActionCta.text = "🔒 Qulfni bosing ✨"
                    binding.cardSection.alpha = 0.95f

                    val triggerSectionLockAnimation = View.OnClickListener {
                        LockStarBurstAnimator.playLockTapStarBurst(
                            cardView = binding.cardSection,
                            lockCenterView = binding.layoutSectionLockMedallion,
                            particleContainer = binding.containerSectionImageFrame
                        ) {
                            onSectionClick(item)
                        }
                    }
                    binding.cardSection.setOnClickListener(triggerSectionLockAnimation)
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
        holder.bind(getItem(position))
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
            binding.tvSenderRole.text = if (item.senderRole == "STUDENT") "You (Student)" else "TalkMe AI Tutor"
            binding.tvMessageContent.text = item.englishText
            if (item.correctionOrUzbekFeedback.isNotBlank()) {
                binding.tvCorrectionFeedback.visibility = View.VISIBLE
                binding.tvCorrectionFeedback.text = item.correctionOrUzbekFeedback
            } else {
                binding.tvCorrectionFeedback.visibility = View.GONE
            }
            binding.btnSpeakMessage.setOnClickListener {
                onSpeakMessage(item.englishText)
            }
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
