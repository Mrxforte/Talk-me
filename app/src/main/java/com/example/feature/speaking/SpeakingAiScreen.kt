package com.example.feature.speaking

import android.animation.ObjectAnimator
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.AiChatTranscriptAdapter
import com.example.databinding.FragmentSpeakingAiBinding
import com.example.domain.model.AiTutorMessage
import com.example.domain.model.PracticeCategory
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.SpeechRecognitionState
import com.example.domain.repository.SpeechRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class SpeakingAiUiState(
    val topicId: String = AppConstants.INITIAL_TOPIC_ID,
    val topicTitle: String = "Introduction — Kirish",
    val statusMessage: String = "Connected • Fully Offline STT Ready",
    val durationSeconds: Int = 0,
    val transcript: List<AiTutorMessage> = emptyList(),
    val isLoading: Boolean = false,
    val speechState: SpeechRecognitionState = SpeechRecognitionState.IDLE,
    val interimSpeechText: String = "",
    val finalSpeechText: String = "",
    val audioAmplitude: Float = 0f,
    val speechDurationSeconds: Int = 0
)

@HiltViewModel
class SpeakingAiViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val aiLearningService: AiLearningService,
    private val speechRepository: SpeechRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpeakingAiUiState())
    val uiState: StateFlow<SpeakingAiUiState> = _uiState.asStateFlow()

    private var allowedWords: List<String> = emptyList()
    private var allowedGrammar: List<String> = emptyList()
    private val scores = mutableListOf<Int>()

    private var hasInitialized = false

    init {
        startSessionTimer()
        observeSpeechEngine()
    }

    private fun observeSpeechEngine() {
        viewModelScope.launch {
            launch {
                speechRepository.speechState.collect { state ->
                    _uiState.value = _uiState.value.copy(speechState = state)
                    val statusText = when (state) {
                        SpeechRecognitionState.LISTENING_PASS_1 -> "Oflayn STT (Pass-1): Eshitilmoqda…"
                        SpeechRecognitionState.PROCESSING_PASS_2 -> "Oflayn STT (Pass-2 Batch): Yakuniy matn tahlil qilinmoqda…"
                        SpeechRecognitionState.COMPLETED -> "Oflayn STT muvaffaqiyatli yakunlandi"
                        SpeechRecognitionState.ERROR -> "Mikrofon / STT xatoligi yuz berdi"
                        else -> "Ulandi • Oflayn Ovozli Muloqot Tayyor"
                    }
                    _uiState.value = _uiState.value.copy(statusMessage = statusText)
                }
            }
            launch {
                speechRepository.interimText.collect { text ->
                    _uiState.value = _uiState.value.copy(interimSpeechText = text)
                }
            }
            launch {
                speechRepository.finalText.collect { text ->
                    _uiState.value = _uiState.value.copy(finalSpeechText = text)
                }
            }
            launch {
                speechRepository.audioAmplitude.collect { amp ->
                    _uiState.value = _uiState.value.copy(audioAmplitude = amp)
                }
            }
            launch {
                speechRepository.recordingDurationSeconds.collect { dur ->
                    _uiState.value = _uiState.value.copy(speechDurationSeconds = dur)
                }
            }
        }
    }

    fun initializeSpeakingSession(requestedTopicId: String? = null) {
        if (hasInitialized) return
        hasInitialized = true
        viewModelScope.launch {
            learningRepository.ensureSeeded()
            val prefs = preferencesRepository.userPreferencesFlow.first()
            val targetTopicId = requestedTopicId?.takeIf { it.isNotBlank() } ?: prefs.lastActiveTopicId
            val topic = learningRepository.getTopicById(targetTopicId)
            val topicId = topic?.id ?: AppConstants.INITIAL_TOPIC_ID
            val title = topic?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" } ?: "1. Introduction (Kirish)"

            val vocabModels = learningRepository.getStudiedVocabularyForTopic(topicId)
            val grammarModels = learningRepository.getStudiedGrammarForTopic(topicId)
            allowedWords = vocabModels.map { it.englishWord }
            allowedGrammar = grammarModels.map { "${it.titleUzbek}: ${it.exampleEnglish}" }

            val welcomeEnglish = "Hello! Let's practice speaking about $title. Try using words like ${allowedWords.take(3).joinToString(", ")}. Can you make a short sentence?"
            val welcomeMsg = AiTutorMessage(
                id = UUID.randomUUID().toString(),
                senderRole = "AI_TUTOR",
                englishText = welcomeEnglish,
                correctionOrUzbekFeedback = "Mavzu: $title. Ushbu mavzu so'zlari (${allowedWords.joinToString(", ")}) asosida inglizcha gapiring yoki pastga yozing."
            )

            _uiState.value = _uiState.value.copy(
                topicId = topicId,
                topicTitle = title,
                transcript = listOf(welcomeMsg)
            )
            ttsManager.speakEnglish(welcomeEnglish)
        }
    }

    private fun startSessionTimer() {
        viewModelScope.launch {
            while (isActive) {
                delay(1_000L)
                _uiState.value = _uiState.value.copy(
                    durationSeconds = _uiState.value.durationSeconds + 1
                )
            }
        }
    }

    fun updateStatus(status: String) {
        _uiState.value = _uiState.value.copy(statusMessage = status)
    }

    fun startOfflineSpeechRecording(): Boolean {
        return speechRepository.startListening()
    }

    fun stopOfflineSpeechRecording() {
        viewModelScope.launch {
            val result = speechRepository.stopListening()
            if (result.recognizedText.isNotBlank()) {
                sendStudentUtterance(result.recognizedText)
            }
        }
    }

    fun cancelOfflineSpeechRecording() {
        speechRepository.cancelListening()
    }

    fun sendStudentUtterance(utterance: String) {
        val clean = utterance.trim()
        if (clean.isEmpty() || _uiState.value.isLoading) return

        val studentMsg = AiTutorMessage(
            id = UUID.randomUUID().toString(),
            senderRole = "STUDENT",
            englishText = clean
        )
        val updatedHistory = _uiState.value.transcript + studentMsg
        _uiState.value = _uiState.value.copy(
            transcript = updatedHistory,
            isLoading = true,
            statusMessage = "AI Tutor is analyzing grammar & vocabulary…"
        )

        viewModelScope.launch {
            val historyPairs = updatedHistory.map { it.senderRole to it.englishText }
            val result = aiLearningService.sendSpeakingTurn(
                topicTitle = _uiState.value.topicTitle,
                allowedVocabulary = allowedWords,
                allowedGrammarSummaries = allowedGrammar,
                conversationHistory = historyPairs,
                studentUtterance = clean
            )

            result.fold(
                onSuccess = { aiReply ->
                    scores.add(aiReply.accuracyScore)
                    val tutorMsg = AiTutorMessage(
                        id = UUID.randomUUID().toString(),
                        senderRole = "AI_TUTOR",
                        englishText = aiReply.spokenEnglishReply,
                        correctionOrUzbekFeedback = aiReply.grammarAndVocabFeedbackUzbek
                    )
                    _uiState.value = _uiState.value.copy(
                        transcript = _uiState.value.transcript + tutorMsg,
                        isLoading = false,
                        statusMessage = "Connected • Speaking response delivered"
                    )
                    ttsManager.speakEnglish(aiReply.spokenEnglishReply)
                },
                onFailure = { error ->
                    val errText = error.localizedMessage ?: "Cloud AI connection error. Check internet connection."
                    val errorMsg = AiTutorMessage(
                        id = UUID.randomUUID().toString(),
                        senderRole = "AI_TUTOR",
                        englishText = "Unable to reach Gemini Live Speaking service right now.",
                        correctionOrUzbekFeedback = "Xatolik: $errText"
                    )
                    _uiState.value = _uiState.value.copy(
                        transcript = _uiState.value.transcript + errorMsg,
                        isLoading = false,
                        statusMessage = "Offline / Connection Error: $errText"
                    )
                }
            )
        }
    }

    fun speakMessage(text: String) {
        viewModelScope.launch {
            ttsManager.speakEnglish(text)
        }
    }

    fun endAndRecordSession(onComplete: () -> Unit) {
        viewModelScope.launch {
            speechRepository.cancelListening()
            ttsManager.stop()
            val avgScore = if (scores.isNotEmpty()) {
                scores.average().toInt().coerceIn(0, 100)
            } else {
                85
            }
            learningRepository.recordPracticeSessionResult(
                category = PracticeCategory.SPEAKING,
                topicId = _uiState.value.topicId,
                sectionId = AppConstants.INITIAL_SECTION_ID,
                correctCount = avgScore,
                totalCount = 100,
                masteredGrammarRuleIds = emptyList(),
                memorizedVocabularyIds = emptyList()
            )
            onComplete()
        }
    }
}

/**
 * Dedicated Speaking AI Screen using Fully Offline Sherpa-ONNX STT + Firebase AI Logic (`gemini-3.8-flash`)
 * + spoken TTS feedback strictly scoped to the current topic and learned vocabulary.
 */
@AndroidEntryPoint
class SpeakingAiFragment : Fragment() {

    private var _binding: FragmentSpeakingAiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpeakingAiViewModel by viewModels()
    private lateinit var transcriptAdapter: AiChatTranscriptAdapter

    private val requestMicPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            toggleVoiceRecording()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.speaking_ai_permission_denied),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeakingAiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        viewModel.initializeSpeakingSession(requestedTopicId)

        transcriptAdapter = AiChatTranscriptAdapter { englishText ->
            viewModel.speakMessage(englishText)
        }

        binding.rvSpeakingTranscript.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSpeakingTranscript.adapter = transcriptAdapter

        // 2D AI Character Avatar Breathing Aura Animation
        ObjectAnimator.ofFloat(binding.ivAiCharacterAura, View.SCALE_X, 1.0f, 1.25f).apply {
            duration = 1000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(binding.ivAiCharacterAura, View.SCALE_Y, 1.0f, 1.25f).apply {
            duration = 1000L
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }

        binding.headerSpeakingAi.tvHeaderTitle.text = getString(R.string.speaking_ai_title)
        binding.headerSpeakingAi.tvHeaderSubtitle.text = "Interactive 2D AI Tutor • English Speech Session"
        binding.headerSpeakingAi.btnBack.setOnClickListener {
            viewModel.endAndRecordSession {}
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.btnEndSpeakingSession.setOnClickListener {
            viewModel.endAndRecordSession {}
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.btnMicrophoneSpeak.setOnClickListener {
            val granted = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
            if (granted) {
                toggleVoiceRecording()
            } else {
                requestMicPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }

        binding.btnSendSpeakingText.setOnClickListener {
            val text = binding.etSpeakingInput.text?.toString().orEmpty()
            if (text.isNotBlank()) {
                binding.etSpeakingInput.setText("")
                viewModel.sendStudentUtterance(text)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.tvSpeakingCurrentTopic.text =
                        getString(R.string.speaking_ai_topic_format, state.topicTitle)
                    binding.tvSpeakingConnectionState.text = state.statusMessage
                    val mins = state.durationSeconds / 60
                    val secs = state.durationSeconds % 60
                    binding.tvSpeakingDuration.text = String.format(Locale.US, "Session: %02d:%02d", mins, secs)

                    // Animate 2D Character aura based on live voice audio amplitude
                    if (state.audioAmplitude > 0f) {
                        val norm = state.audioAmplitude.coerceIn(0f, 1f)
                        binding.ivAiCharacterAura.scaleX = 1.0f + norm * 0.5f
                        binding.ivAiCharacterAura.scaleY = 1.0f + norm * 0.5f
                    }

                    // Show interim or final speech text in input box
                    if (state.speechState == SpeechRecognitionState.LISTENING_PASS_1 && state.interimSpeechText.isNotBlank()) {
                        binding.etSpeakingInput.setText(state.interimSpeechText)
                    } else if (state.speechState == SpeechRecognitionState.COMPLETED && state.finalSpeechText.isNotBlank()) {
                        binding.etSpeakingInput.setText(state.finalSpeechText)
                    }

                    transcriptAdapter.submitList(state.transcript) {
                        if (state.transcript.isNotEmpty()) {
                            binding.rvSpeakingTranscript.scrollToPosition(state.transcript.size - 1)
                        }
                    }
                }
            }
        }
    }

    private var speechRecognizer: SpeechRecognizer? = null
    private var isListeningVoice = false

    private fun toggleVoiceRecording() {
        val currentState = viewModel.uiState.value.speechState
        if (isListeningVoice || currentState == SpeechRecognitionState.LISTENING_PASS_1) {
            stopVoiceListening()
        } else {
            startVoiceListening()
        }
    }

    private fun startVoiceListening() {
        if (!isAdded) return
        isListeningVoice = true
        viewModel.updateStatus("Eshitilmoqda... Inglizcha gapiring 🎙️")

        // Start AudioRecordManager PCM capture
        viewModel.startOfflineSpeechRecording()

        if (SpeechRecognizer.isRecognitionAvailable(requireContext())) {
            try {
                speechRecognizer?.destroy()
                speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext()).apply {
                    setRecognitionListener(object : RecognitionListener {
                        override fun onReadyForSpeech(params: Bundle?) {
                            viewModel.updateStatus("Android STT: Eshitilmoqda...")
                        }
                        override fun onBeginningOfSpeech() {}
                        override fun onRmsChanged(rmsdB: Float) {
                            val norm = (rmsdB.coerceIn(0f, 10f) / 10f)
                            binding.ivAiCharacterAura.scaleX = 1.0f + norm * 0.4f
                            binding.ivAiCharacterAura.scaleY = 1.0f + norm * 0.4f
                        }
                        override fun onBufferReceived(buffer: ByteArray?) {}
                        override fun onEndOfSpeech() {
                            viewModel.updateStatus("Ovoz tahlil qilinmoqda...")
                        }
                        override fun onError(error: Int) {
                            isListeningVoice = false
                            viewModel.updateStatus("Mikrofon xatoligi • Qayta urinib ko'ring ($error)")
                        }
                        override fun onResults(results: Bundle?) {
                            isListeningVoice = false
                            val spokenList = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                            val spoken = spokenList?.firstOrNull()?.trim()
                            if (!spoken.isNullOrEmpty()) {
                                binding.etSpeakingInput.setText(spoken)
                                viewModel.sendStudentUtterance(spoken)
                            } else {
                                viewModel.updateStatus("Mikrofon tayyor • Bosib gapiring")
                            }
                        }
                        override fun onPartialResults(partialResults: Bundle?) {
                            val partialList = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                            val partial = partialList?.firstOrNull()?.trim()
                            if (!partial.isNullOrEmpty()) {
                                binding.etSpeakingInput.setText(partial)
                            }
                        }
                        override fun onEvent(eventType: Int, params: Bundle?) {}
                    })
                }

                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US")
                    putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "en-US")
                    putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
                    putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
                }
                speechRecognizer?.startListening(intent)
            } catch (e: Exception) {
                isListeningVoice = false
                viewModel.updateStatus("Mikrofonni ochishda xatolik yuz berdi")
            }
        } else {
            isListeningVoice = false
            viewModel.updateStatus("Qurilmada Android ovoz aniqlash xizmati yo'q")
        }
    }

    private fun stopVoiceListening() {
        isListeningVoice = false
        speechRecognizer?.stopListening()
        viewModel.updateStatus("Ovoz tahlil qilinmoqda...")
    }

    override fun onDestroyView() {
        speechRecognizer?.destroy()
        speechRecognizer = null
        viewModel.cancelOfflineSpeechRecording()
        super.onDestroyView()
        _binding = null
    }
}
