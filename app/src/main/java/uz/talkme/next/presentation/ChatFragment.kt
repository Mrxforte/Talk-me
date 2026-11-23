package uz.talkme.next.presentation

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.talkme.next.R
import uz.talkme.next.databinding.FragmentChatBinding
import uz.talkme.next.domain.*
import java.util.Locale

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val model: ChatViewModel by viewModels()
    private var apiKey = ""
    private var keyDialog: AlertDialog? = null
    private var transcriptVisible = true
    private var wasActive = false
    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted && lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) start()
        else if (!granted) model.fail(Issue.PERMISSION)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pad = resources.getDimensionPixelSize(R.dimen.space_large)
        ViewCompat.setOnApplyWindowInsetsListener(view) { root, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            root.setPadding(pad + bars.left, pad + bars.top, pad + bars.right, pad + bars.bottom)
            insets
        }
        binding.micButton.setOnClickListener { if (model.state.value.active) model.microphone() else start() }
        binding.endButton.setOnClickListener { model.stop() }
        binding.keyButton.setOnClickListener { showKeyDialog(false) }
        binding.transcriptButton.setOnClickListener {
            transcriptVisible = !transcriptVisible
            binding.transcriptScroll.visibility = if (transcriptVisible) View.VISIBLE else View.GONE
            binding.transcriptButton.contentDescription = getString(
                if (transcriptVisible) R.string.hide_transcript else R.string.show_transcript)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.orb.animateWhileVisible(true)
                try { model.state.collect(::render) }
                finally { _binding?.orb?.animateWhileVisible(false) }
            }
        }
    }
    private fun start() {
        if (apiKey.isBlank()) { showKeyDialog(true); return }
        if (requireContext().checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            permission.launch(Manifest.permission.RECORD_AUDIO); return
        }
        model.start(apiKey)
    }
    private fun showKeyDialog(startAfter: Boolean) {
        if (keyDialog?.isShowing == true) return
        model.stop()
        val field = EditText(requireContext()).apply {
            setText(apiKey); hint = getString(R.string.key_hint)
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
        val content = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            val pad = resources.getDimensionPixelSize(R.dimen.space_large)
            setPadding(pad, pad, pad, pad)
            addView(TextView(context).apply { setText(R.string.key_note) })
            addView(field)
            addView(TextView(context).apply {
                text = getString(R.string.cost, String.format(Locale.US, "%.4f", model.state.value.estimatedUsd))
            })
        }
        keyDialog = AlertDialog.Builder(requireContext()).setTitle(R.string.version).setView(content)
            .setNegativeButton(R.string.cancel, null).setPositiveButton(R.string.save, null).create().also { dialog ->
                dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
                dialog.setOnShowListener {
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                        val value = field.text.toString().trim()
                        if (value.isBlank()) { field.error = getString(R.string.key_hint); return@setOnClickListener }
                        apiKey = value; field.text.clear(); dialog.dismiss()
                        if (startAfter) start()
                    }
                }
                dialog.show()
            }
    }
    private fun render(state: ChatState) {
        val ui = _binding ?: return
        ui.status.setText(when (state.phase) {
            Phase.IDLE -> R.string.idle
            Phase.PREPARING -> R.string.preparing
            Phase.LISTENING -> R.string.listening
            Phase.TRANSCRIBING -> R.string.transcribing
            Phase.THINKING -> R.string.thinking
            Phase.SPEAKING -> R.string.speaking
            Phase.MUTED -> R.string.muted
            Phase.ERROR -> R.string.error
        })
        ui.details.visibility = if (state.issue == null) View.GONE else View.VISIBLE
        state.issue?.let { ui.details.setText(when (it) {
            Issue.PERMISSION -> R.string.permission_error
            Issue.MODEL -> R.string.model_error
            Issue.MICROPHONE -> R.string.mic_error
            Issue.NETWORK -> R.string.network_error
            Issue.AUTH -> R.string.auth_error
            Issue.QUOTA -> R.string.quota_error
            Issue.RESPONSE -> R.string.response_error
            Issue.TTS -> R.string.tts_error
            Issue.FOCUS -> R.string.focus_error
        }) }
        ui.userText.text = state.partial.ifBlank { state.userText.ifBlank { getString(R.string.empty_user) } }
        ui.assistantText.text = state.assistantText.ifBlank { getString(R.string.empty_assistant) }
        val action = when {
            !state.active -> R.string.begin
            state.phase in setOf(Phase.SPEAKING, Phase.THINKING, Phase.TRANSCRIBING) -> R.string.interrupt
            state.phase == Phase.MUTED -> R.string.unmute
            else -> R.string.speak
        }
        ui.actionLabel.setText(action)
        ui.micButton.contentDescription = getString(
            if (state.phase == Phase.LISTENING) R.string.mute else action)
        ui.micButton.setImageResource(if (state.phase == Phase.MUTED) R.drawable.ic_mic_off else R.drawable.ic_mic)
        ui.micButton.isEnabled = state.phase != Phase.PREPARING
        ui.micButton.alpha = if (ui.micButton.isEnabled) 1f else 0.5f
        ui.endButton.isEnabled = state.active
        ui.endButton.alpha = if (state.active) 1f else 0.4f
        ui.orb.update(state.phase, state.level)
        if (state.active && !wasActive) { ui.timer.base = SystemClock.elapsedRealtime(); ui.timer.start() }
        if (!state.active) ui.timer.stop()
        wasActive = state.active
        if (state.active) requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
    override fun onPause() {
        model.stop(); _binding?.orb?.animateWhileVisible(false)
        super.onPause()
    }
    override fun onDestroyView() {
        keyDialog?.dismiss(); keyDialog = null
        binding.orb.animateWhileVisible(false)
        _binding = null
        super.onDestroyView()
    }
    override fun onDestroy() { apiKey = ""; super.onDestroy() }
}
