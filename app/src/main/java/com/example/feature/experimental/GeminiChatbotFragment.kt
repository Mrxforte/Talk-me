package com.example.feature.experimental

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.R
import com.example.databinding.FragmentGeminiChatbotBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Experimental Multi-Turn TalkMe Lesson Chatbot Fragment.
 * Answers strictly within TalkMe English lessons with clean formatted mobile replies.
 */
@AndroidEntryPoint
class GeminiChatbotFragment : Fragment() {

    private var _binding: FragmentGeminiChatbotBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GeminiChatbotViewModel by viewModels()
    private lateinit var chatAdapter: GeminiChatbotAdapter
    private var isUpdatingChipsProgrammatically = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeminiChatbotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatAdapter = GeminiChatbotAdapter { messageText ->
            copyToClipboard(messageText)
        }
        binding.rvChatbotMessages.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
            }
            adapter = chatAdapter
            itemAnimator = null
        }

        setupListeners()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderUiState(state)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListeners() {
        binding.btnChatbotBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.btnClearChat.setOnClickListener {
            viewModel.clearConversation()
        }

        binding.btnEditSystemPrompt.setOnClickListener {
            showSystemInstructionDialog()
        }

        binding.chipGroupModels.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingChipsProgrammatically || checkedIds.isEmpty()) return@setOnCheckedStateChangeListener
            when (checkedIds.first()) {
                R.id.chipModelHydrogen -> viewModel.selectModel(ChatbotModelTier.HYDROGEN)
                R.id.chipModelArgon -> viewModel.selectModel(ChatbotModelTier.ARGON)
                R.id.chipModelCarbon -> viewModel.selectModel(ChatbotModelTier.CARBON)
                R.id.chipModelFlashLite -> viewModel.selectModel(ChatbotModelTier.FLASH_LITE)
            }
        }

        binding.chipGroupRoles.setOnCheckedStateChangeListener { _, checkedIds ->
            if (isUpdatingChipsProgrammatically || checkedIds.isEmpty()) return@setOnCheckedStateChangeListener
            when (checkedIds.first()) {
                R.id.chipRoleTutor -> viewModel.selectPersona(ChatbotPersonaPreset.ENGLISH_TUTOR)
                R.id.chipRolePartner -> viewModel.selectPersona(ChatbotPersonaPreset.CONVERSATION_PARTNER)
                R.id.chipRoleGrammar -> viewModel.selectPersona(ChatbotPersonaPreset.GRAMMAR_EXPERT)
                R.id.chipRoleVocab -> viewModel.selectPersona(ChatbotPersonaPreset.VOCAB_HELPER)
                R.id.chipRoleCustom -> showSystemInstructionDialog()
            }
        }

        binding.btnQuickPrompt1.setOnClickListener {
            submitPrompt(getString(R.string.chatbot_quick_prompt_1))
        }
        binding.btnQuickPrompt2.setOnClickListener {
            submitPrompt(getString(R.string.chatbot_quick_prompt_2))
        }

        binding.btnSendMessage.setOnClickListener {
            val text = binding.etChatInput.text?.toString().orEmpty()
            if (text.isNotBlank()) {
                binding.etChatInput.setText("")
                submitPrompt(text)
            }
        }

        binding.etChatInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                val text = binding.etChatInput.text?.toString().orEmpty()
                if (text.isNotBlank()) {
                    binding.etChatInput.setText("")
                    submitPrompt(text)
                }
                true
            } else {
                false
            }
        }
    }

    private fun submitPrompt(prompt: String) {
        viewModel.sendMessage(
            rawText = prompt,
            timeoutMessage = getString(R.string.chatbot_timeout_error),
            emptyResponseMessage = getString(R.string.chatbot_empty_response_error)
        )
    }

    private fun renderUiState(state: ChatbotUiState) {
        isUpdatingChipsProgrammatically = true
        try {
            val modelChipId = when (state.selectedModel) {
                ChatbotModelTier.HYDROGEN -> R.id.chipModelHydrogen
                ChatbotModelTier.ARGON -> R.id.chipModelArgon
                ChatbotModelTier.CARBON -> R.id.chipModelCarbon
                ChatbotModelTier.FLASH_LITE -> R.id.chipModelFlashLite
            }
            if (binding.chipGroupModels.checkedChipId != modelChipId) {
                binding.chipGroupModels.check(modelChipId)
            }

            val roleChipId = when (state.selectedPersona) {
                ChatbotPersonaPreset.ENGLISH_TUTOR -> R.id.chipRoleTutor
                ChatbotPersonaPreset.CONVERSATION_PARTNER -> R.id.chipRolePartner
                ChatbotPersonaPreset.GRAMMAR_EXPERT -> R.id.chipRoleGrammar
                ChatbotPersonaPreset.VOCAB_HELPER -> R.id.chipRoleVocab
                ChatbotPersonaPreset.CUSTOM -> R.id.chipRoleCustom
            }
            if (binding.chipGroupRoles.checkedChipId != roleChipId) {
                binding.chipGroupRoles.check(roleChipId)
            }
        } finally {
            isUpdatingChipsProgrammatically = false
        }

        val roleLabel = getString(state.selectedPersona.labelRes)
        val lessonLabel = state.lessonContext.displayLabel.ifBlank {
            getString(R.string.chatbot_lesson_scope_default)
        }
        binding.tvActiveConfigSummary.text = "$lessonLabel · $roleLabel"

        val hasMessages = state.messages.isNotEmpty()
        binding.layoutChatbotEmptyState.visibility = if (hasMessages) View.GONE else View.VISIBLE
        binding.rvChatbotMessages.visibility = if (hasMessages) View.VISIBLE else View.GONE

        chatAdapter.submitList(state.messages) {
            if (hasMessages) {
                binding.rvChatbotMessages.scrollToPosition(state.messages.lastIndex)
            }
        }

        binding.btnSendMessage.isEnabled = !state.isBusy
        binding.btnSendMessage.alpha = if (state.isBusy) 0.5f else 1f

        when (val status = state.status) {
            is ChatbotStatus.Idle -> {
                binding.layoutChatbotStatus.visibility = View.GONE
            }
            is ChatbotStatus.Loading, is ChatbotStatus.Streaming -> {
                binding.layoutChatbotStatus.visibility = View.VISIBLE
                binding.progressChatbotThinking.visibility = View.VISIBLE
                binding.tvChatbotStatus.setTextColor(
                    requireContext().getColor(R.color.color_on_surface_secondary)
                )
                binding.tvChatbotStatus.text = getString(R.string.chatbot_thinking_status)
            }
            is ChatbotStatus.Error -> {
                binding.layoutChatbotStatus.visibility = View.VISIBLE
                binding.progressChatbotThinking.visibility = View.GONE
                binding.tvChatbotStatus.setTextColor(requireContext().getColor(R.color.color_error))
                binding.tvChatbotStatus.text = status.message
            }
        }
    }

    private fun showSystemInstructionDialog() {
        val ctx = requireContext()
        val input = EditText(ctx).apply {
            setText(
                if (viewModel.uiState.value.selectedPersona == ChatbotPersonaPreset.CUSTOM) {
                    viewModel.uiState.value.customSystemPrompt
                } else {
                    viewModel.uiState.value.selectedPersona.roleDirective
                }
            )
            hint = getString(R.string.chatbot_system_dialog_hint)
            minLines = 3
            maxLines = 6
        }
        val pad = (20 * resources.displayMetrics.density).toInt()
        val container = android.widget.FrameLayout(ctx).apply {
            setPadding(pad, pad / 2, pad, 0)
            addView(input)
        }
        MaterialAlertDialogBuilder(ctx)
            .setTitle(R.string.chatbot_system_dialog_title)
            .setView(container)
            .setNegativeButton(R.string.chatbot_cancel_btn) { _, _ ->
                renderUiState(viewModel.uiState.value)
            }
            .setPositiveButton(R.string.chatbot_save_btn) { _, _ ->
                viewModel.setCustomSystemInstruction(input.text?.toString().orEmpty())
            }
            .show()
    }

    private fun copyToClipboard(text: String) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        clipboard?.setPrimaryClip(ClipData.newPlainText("TalkMe AI Chat", text))
        Toast.makeText(requireContext(), R.string.chatbot_copied_toast, Toast.LENGTH_SHORT).show()
    }
}
