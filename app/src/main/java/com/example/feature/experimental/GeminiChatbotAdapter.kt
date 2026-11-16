package com.example.feature.experimental

import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.databinding.ItemChatbotMessageBinding

/**
 * DiffUtil-backed ListAdapter rendering cleanly formatted TalkMe Lesson Chatbot bubbles.
 */
class GeminiChatbotAdapter(
    private val onBubbleClickCopy: (String) -> Unit
) : ListAdapter<ChatbotMessage, GeminiChatbotAdapter.MessageViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemChatbotMessageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MessageViewHolder(binding, onBubbleClickCopy)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MessageViewHolder(
        private val binding: ItemChatbotMessageBinding,
        private val onCopyClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatbotMessage) {
            val ctx = binding.root.context
            val accentColor = ContextCompat.getColor(ctx, R.color.color_primary)

            if (item.isUser) {
                binding.rowBubbleAlignment.gravity = Gravity.END
                binding.cardBotAvatar.visibility = View.GONE
                binding.bubbleContainer.setBackgroundResource(R.drawable.bg_speaking_bubble_student)
                binding.tvSenderLabel.text = ctx.getString(R.string.chatbot_sender_you)
                binding.tvSenderLabel.setTextColor(ContextCompat.getColor(ctx, R.color.color_primary_variant))
                binding.tvModelBadge.visibility = View.GONE
                binding.tvMessageBody.text = item.text.trim()
            } else {
                binding.rowBubbleAlignment.gravity = Gravity.START
                binding.cardBotAvatar.visibility = View.VISIBLE
                binding.bubbleContainer.setBackgroundResource(R.drawable.bg_speaking_bubble_tutor)
                binding.tvSenderLabel.text = ctx.getString(R.string.chatbot_screen_title)
                binding.tvSenderLabel.setTextColor(accentColor)
                if (item.modelBadge.isNotBlank()) {
                    binding.tvModelBadge.visibility = View.VISIBLE
                    binding.tvModelBadge.text = item.modelBadge
                } else {
                    binding.tvModelBadge.visibility = View.GONE
                }
                val formatted = ChatbotTextFormatter.formatForDisplay(item.text, accentColor)
                binding.tvMessageBody.text = if (item.isStreaming) {
                    SpannableStringBuilder(formatted).append(" ▍")
                } else {
                    formatted
                }
            }

            binding.bubbleContainer.setOnClickListener {
                val plainText = ChatbotTextFormatter.formatPlainText(item.text)
                if (plainText.isNotBlank()) {
                    onCopyClick(plainText)
                }
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<ChatbotMessage>() {
        override fun areItemsTheSame(oldItem: ChatbotMessage, newItem: ChatbotMessage): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ChatbotMessage, newItem: ChatbotMessage): Boolean =
            oldItem == newItem
    }
}
