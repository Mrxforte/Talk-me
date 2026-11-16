package com.example.feature.speaking

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BuildConfig
import com.example.R
import com.example.databinding.FragmentGeminiSpeakingSetupBinding
import com.example.databinding.FragmentGeminiSpeakingPracticeBinding
import com.example.databinding.ItemGeminiSpeakingChatBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class GeminiSpeakingSetupFragment : Fragment() {
    private var binding: FragmentGeminiSpeakingSetupBinding? = null
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View {
        val b = FragmentGeminiSpeakingSetupBinding.inflate(inflater, parent, false)
        binding = b
        val levels = listOf("A1", "A2", "B1", "B2", "C1", "C2")
        val topics = listOf("Daily Conversation", "Job Interview", "Airport & Travel", "Ordering Coffee")
        b.levelSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, levels)
        b.topicSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, topics)
        b.startButton.setOnClickListener {
            val args = Bundle().apply {
                putString("level", levels[b.levelSpinner.selectedItemPosition])
                putString("topic", topics[b.topicSpinner.selectedItemPosition])
            }
            findNavController().navigate(R.id.geminiSpeakingPracticeFragment, args)
        }
        return b.root
    }
    override fun onDestroyView() { binding = null; super.onDestroyView() }
}

data class GeminiSpeakingUiState(
    val turns: List<SpeakingTurn> = emptyList(),
    val busy: Boolean = false,
    val error: String? = null
)
class GeminiSpeakingViewModel : ViewModel() {
    private val engine = GeminiSpeakingEngine()
    private val mutable = MutableStateFlow(GeminiSpeakingUiState())
    val state = mutable.asStateFlow()
    fun send(audio: String, level: String, topic: String) {
        if (mutable.value.busy) return
        val key = BuildConfig.GEMINI_SPEAKING_API_KEY
        if (key.isBlank()) {
            mutable.value = mutable.value.copy(error = "GEMINI_SPEAKING_API_KEY is not configured")
            return
        }
        mutable.value = mutable.value.copy(busy = true, error = null)
        viewModelScope.launch {
            try {
                val turn = engine.reply(audio, level, topic, key)
                mutable.value = mutable.value.copy(turns = mutable.value.turns + turn, busy = false)
            } catch (e: Exception) {
                mutable.value = mutable.value.copy(busy = false, error = e.message ?: "Gemini request failed")
            }
        }
    }
}
private class GeminiChatAdapter : RecyclerView.Adapter<GeminiChatAdapter.Holder>() {
    private var turns: List<SpeakingTurn> = emptyList()
    class Holder(val b: ItemGeminiSpeakingChatBinding) : RecyclerView.ViewHolder(b.root)
    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = Holder(
        ItemGeminiSpeakingChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun getItemCount() = turns.size * 2
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val turn = turns[position / 2]
        val isUser = position % 2 == 0
        holder.b.speakerText.text = if (isUser) "You" else "AI Tutor"
        holder.b.messageText.text = if (isUser) turn.transcript else turn.reply
        val feedback = if (isUser) listOfNotNull(
            turn.correction?.takeIf(String::isNotBlank)?.let { "Correction: $it" },
            turn.explanation?.takeIf(String::isNotBlank)
        ).joinToString("\n") else ""
        holder.b.correctionText.text = feedback
        holder.b.correctionText.visibility = if (feedback.isBlank()) View.GONE else View.VISIBLE
    }
    fun show(value: List<SpeakingTurn>) {
        if (turns == value) return
        turns = value
        notifyDataSetChanged()
    }
}

class GeminiSpeakingPracticeFragment : Fragment() {
    private var binding: FragmentGeminiSpeakingPracticeBinding? = null
    private val model: GeminiSpeakingViewModel by viewModels()
    private val adapter = GeminiChatAdapter()
    private lateinit var recorder: SpeakingRecorder
    private var tts: TextToSpeech? = null
    private var ttsReady = false
    private var recording = false
    private var lastSpoken = ""
    private var level = "A1"
    private var topic = "Daily Conversation"
    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        binding?.statusText?.text = if (granted) "Hold to speak" else "Microphone permission required"
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View {
        val b = FragmentGeminiSpeakingPracticeBinding.inflate(inflater, parent, false)
        binding = b
        return b.root
    }
    override fun onViewCreated(view: View, state: Bundle?) {
        val b = binding ?: return
        level = arguments?.getString("level") ?: "A1"
        topic = arguments?.getString("topic") ?: "Daily Conversation"
        b.sessionTitle.text = "$level · $topic"
        recorder = SpeakingRecorder(requireContext())
        tts = TextToSpeech(requireContext().applicationContext) { status ->
            ttsReady = status == TextToSpeech.SUCCESS &&
                (tts?.setLanguage(Locale.US) ?: TextToSpeech.LANG_NOT_SUPPORTED) >= TextToSpeech.LANG_AVAILABLE
        }
        b.chatList.layoutManager = LinearLayoutManager(requireContext())
        b.chatList.adapter = adapter
        b.replayButton.setOnClickListener { model.state.value.turns.lastOrNull()?.reply?.let(::speak) }
        b.finishButton.setOnClickListener {
            if (recording) { recorder.cancel(); recording = false }
            tts?.stop()
            val turns = model.state.value.turns
            val correct = turns.count { it.correction.isNullOrBlank() }
            val summary = if (turns.isEmpty()) "No completed speaking turns" else
                "Grammar accuracy: $correct/${turns.size} turns (${correct * 100 / turns.size}%).\nPractice estimate only."
            MaterialAlertDialogBuilder(requireContext()).setTitle("Speaking score")
                .setMessage(summary).setPositiveButton("Finish") { _, _ -> findNavController().popBackStack() }
                .setNegativeButton("Continue", null).show()
        }
        b.recordButton.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    if (!model.state.value.busy) {
                        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) ==
                            PackageManager.PERMISSION_GRANTED) {
                            try {
                                tts?.stop()
                                recorder.start()
                                recording = true
                                b.recordButton.text = "Release to Send"
                                b.statusText.text = "Recording…"
                            } catch (e: Exception) { b.statusText.text = e.message ?: "Microphone error" }
                        } else permission.launch(Manifest.permission.RECORD_AUDIO)
                    }
                    true
                }
                MotionEvent.ACTION_UP -> {
                    if (recording) {
                        recording = false
                        b.recordButton.text = "Hold to Speak"
                        try { model.send(recorder.finish(), level, topic) }
                        catch (e: Exception) { b.statusText.text = e.message ?: "Recording too short" }
                    }
                    true
                }
                MotionEvent.ACTION_CANCEL -> {
                    if (recording) { recorder.cancel(); recording = false }
                    b.recordButton.text = "Hold to Speak"
                    true
                }
                else -> true
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            model.state.collect { s ->
                adapter.show(s.turns)
                if (s.turns.isNotEmpty()) b.chatList.scrollToPosition(adapter.itemCount - 1)
                b.recordButton.isEnabled = !s.busy
                if (!recording) b.statusText.text = s.error ?: if (s.busy) "Gemini is thinking…" else "Hold to speak"
                val reply = s.turns.lastOrNull()?.reply.orEmpty()
                if (reply.isNotBlank() && reply != lastSpoken) {
                    lastSpoken = reply
                    speak(reply)
                }
            }
        }
    }
    private fun speak(text: String) {
        if (ttsReady) tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "talkme_speaking")
    }
    override fun onStop() {
        if (recording) { recorder.cancel(); recording = false }
        tts?.stop()
        super.onStop()
    }
    override fun onDestroyView() {
        tts?.shutdown()
        tts = null
        ttsReady = false
        binding = null
        super.onDestroyView()
    }
}
