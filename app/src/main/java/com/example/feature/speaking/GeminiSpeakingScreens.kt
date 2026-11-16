package com.example.feature.speaking

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.core.audio.TtsManager
import com.example.databinding.FragmentGeminiSpeakingPracticeBinding
import com.example.databinding.FragmentGeminiSpeakingSetupBinding
import com.example.databinding.ItemGeminiSpeakingChatBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "GeminiSpeakingScreens"

class GeminiSpeakingSetupFragment : Fragment() {
    private var binding: FragmentGeminiSpeakingSetupBinding? = null

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View {
        val b = FragmentGeminiSpeakingSetupBinding.inflate(inflater, parent, false)
        binding = b
        val levels = listOf("A1", "A2", "B1", "B2", "C1", "C2")
        val topics = listOf("Daily Conversation", "Job Interview", "Airport & Travel", "Ordering Coffee")
        b.levelSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, levels)
        b.topicSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, topics)
        b.btnGeminiSetupBack.setOnClickListener {
            findNavController().navigateUp()
        }
        b.startButton.setOnClickListener {
            val args = Bundle().apply {
                putString("level", levels[b.levelSpinner.selectedItemPosition])
                putString("topic", topics[b.topicSpinner.selectedItemPosition])
            }
            findNavController().navigate(R.id.geminiSpeakingPracticeFragment, args)
        }
        return b.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
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

    fun send(audioBytes: ByteArray, level: String, topic: String) {
        if (mutable.value.busy) return
        mutable.value = mutable.value.copy(busy = true, error = null)
        viewModelScope.launch {
            try {
                val turn = engine.reply(audioBytes, level, topic)
                mutable.value = mutable.value.copy(
                    turns = mutable.value.turns + turn,
                    busy = false
                )
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "Gemini 3.8 speaking request timed out", e)
                mutable.value = mutable.value.copy(
                    busy = false,
                    error = "Javob kutish vaqti tugadi. Internetni tekshirib qayta urinib ko‘ring."
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Gemini 3.8 speaking request failed", e)
                mutable.value = mutable.value.copy(
                    busy = false,
                    error = e.localizedMessage ?: "Gemini bilan bog‘lanishda xatolik yuz berdi."
                )
            }
        }
    }
}

private data class GeminiChatItem(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val feedback: String
)

private class GeminiChatAdapter : ListAdapter<GeminiChatItem, GeminiChatAdapter.Holder>(DiffCallback) {

    class Holder(val b: ItemGeminiSpeakingChatBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = Holder(
        ItemGeminiSpeakingChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        val ctx = holder.itemView.context
        holder.b.speakerText.text = if (item.isUser) {
            ctx.getString(R.string.speaking_gemini_speaker_user)
        } else {
            ctx.getString(R.string.speaking_gemini_speaker_ai)
        }
        holder.b.messageText.text = item.text
        holder.b.correctionText.text = item.feedback
        holder.b.correctionText.visibility = if (item.feedback.isBlank()) View.GONE else View.VISIBLE
    }

    fun showTurns(turns: List<SpeakingTurn>, correctionPrefix: String) {
        val rows = ArrayList<GeminiChatItem>(turns.size * 2)
        turns.forEachIndexed { index, turn ->
            val feedback = listOfNotNull(
                turn.correction?.takeIf(String::isNotBlank)?.let { "$correctionPrefix: $it" },
                turn.explanation?.takeIf(String::isNotBlank)
            ).joinToString("\n")
            rows += GeminiChatItem(
                id = "${index}_user",
                isUser = true,
                text = turn.transcript,
                feedback = feedback
            )
            rows += GeminiChatItem(
                id = "${index}_ai",
                isUser = false,
                text = turn.reply,
                feedback = ""
            )
        }
        submitList(rows)
    }

    private object DiffCallback : DiffUtil.ItemCallback<GeminiChatItem>() {
        override fun areItemsTheSame(oldItem: GeminiChatItem, newItem: GeminiChatItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GeminiChatItem, newItem: GeminiChatItem): Boolean =
            oldItem == newItem
    }
}

@AndroidEntryPoint
class GeminiSpeakingPracticeFragment : Fragment() {
    @Inject
    lateinit var ttsManager: TtsManager

    private var binding: FragmentGeminiSpeakingPracticeBinding? = null
    private val model: GeminiSpeakingViewModel by viewModels()
    private val adapter = GeminiChatAdapter()
    private lateinit var recorder: SpeakingRecorder
    private var activeTtsJob: Job? = null
    private var recording = false
    private var lastSpoken = ""
    private var level = "A1"
    private var topic = "Daily Conversation"

    private val permission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        binding?.statusText?.text = if (granted) {
            getString(R.string.speaking_gemini_hold_prompt)
        } else {
            getString(R.string.speaking_gemini_mic_required)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View {
        val b = FragmentGeminiSpeakingPracticeBinding.inflate(inflater, parent, false)
        binding = b
        return b.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, state: Bundle?) {
        val b = binding ?: return
        level = arguments?.getString("level") ?: "A1"
        topic = arguments?.getString("topic") ?: "Daily Conversation"
        b.sessionTitle.text = "$level · $topic"
        recorder = SpeakingRecorder(requireContext())
        b.btnGeminiPracticeBack.setOnClickListener {
            stopActiveMedia()
            findNavController().navigateUp()
        }
        b.chatList.layoutManager = LinearLayoutManager(requireContext())
        b.chatList.adapter = adapter
        b.replayButton.setOnClickListener {
            model.state.value.turns.lastOrNull()?.reply?.let(::speak)
        }
        b.finishButton.setOnClickListener {
            stopActiveMedia()
            val turns = model.state.value.turns
            val correct = turns.count { it.correction.isNullOrBlank() }
            val summary = if (turns.isEmpty()) {
                getString(R.string.speaking_gemini_no_turns)
            } else {
                getString(
                    R.string.speaking_gemini_score_summary,
                    correct,
                    turns.size,
                    correct * 100 / turns.size
                )
            }
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.speaking_gemini_score_title)
                .setMessage(summary)
                .setPositiveButton(R.string.speaking_gemini_finish_btn) { _, _ ->
                    findNavController().popBackStack()
                }
                .setNegativeButton(R.string.speaking_gemini_continue_btn, null)
                .show()
        }
        b.recordButton.setOnTouchListener { _, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    if (!model.state.value.busy) {
                        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) ==
                            PackageManager.PERMISSION_GRANTED
                        ) {
                            try {
                                activeTtsJob?.cancel()
                                ttsManager.stop()
                                recorder.start()
                                recording = true
                                b.recordButton.setText(R.string.speaking_gemini_release_to_send)
                                b.statusText.setText(R.string.speaking_gemini_recording_status)
                            } catch (e: Exception) {
                                Log.e(TAG, "Microphone start error", e)
                                b.statusText.text = e.localizedMessage ?: getString(R.string.speaking_gemini_mic_error)
                            }
                        } else {
                            permission.launch(Manifest.permission.RECORD_AUDIO)
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    if (recording) {
                        recording = false
                        b.recordButton.setText(R.string.speaking_gemini_hold_prompt)
                        try {
                            model.send(recorder.finish(), level, topic)
                        } catch (e: Exception) {
                            Log.w(TAG, "Recording finish rejected", e)
                            b.statusText.text = e.localizedMessage ?: getString(R.string.speaking_gemini_short_recording)
                        }
                    }
                    true
                }

                MotionEvent.ACTION_CANCEL -> {
                    if (recording) {
                        recorder.cancel()
                        recording = false
                    }
                    b.recordButton.setText(R.string.speaking_gemini_hold_prompt)
                    true
                }

                else -> true
            }
        }

        val correctionPrefix = getString(R.string.speaking_gemini_correction_prefix)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.state.collect { s ->
                    adapter.showTurns(s.turns, correctionPrefix)
                    if (s.turns.isNotEmpty()) {
                        b.chatList.scrollToPosition((s.turns.size * 2) - 1)
                    }
                    b.recordButton.isEnabled = !s.busy
                    if (!recording) {
                        b.statusText.text = s.error ?: if (s.busy) {
                            getString(R.string.speaking_gemini_thinking)
                        } else {
                            getString(R.string.speaking_gemini_hold_prompt)
                        }
                    }
                    val reply = s.turns.lastOrNull()?.reply.orEmpty()
                    if (reply.isNotBlank() && reply != lastSpoken) {
                        lastSpoken = reply
                        speak(reply)
                    }
                }
            }
        }
    }

    private fun speak(text: String) {
        activeTtsJob?.cancel()
        activeTtsJob = viewLifecycleOwner.lifecycleScope.launch {
            ttsManager.speakEnglish(text)
        }
    }

    private fun stopActiveMedia() {
        if (recording) {
            recorder.cancel()
            recording = false
        }
        activeTtsJob?.cancel()
        activeTtsJob = null
        ttsManager.stop()
    }

    override fun onPause() {
        stopActiveMedia()
        super.onPause()
    }

    override fun onStop() {
        stopActiveMedia()
        super.onStop()
    }

    override fun onDestroyView() {
        stopActiveMedia()
        binding = null
        super.onDestroyView()
    }
}
