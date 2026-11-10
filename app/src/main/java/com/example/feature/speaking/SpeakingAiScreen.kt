package com.example.feature.speaking

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
    val statusMessage: String = "Connected • Ready for voice or text input",
    val durationSeconds: Int = 0,
    val transcript: List<AiTutorMessage> = emptyList(),
    val isLoading: Boolean = false
)

@HiltViewModel
class SpeakingAiViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val aiLearningService: AiLearningService,
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
 * Dedicated Speaking AI Screen using voice recognition + Firebase AI Logic (`gemini-3.8-flash`)
 * + spoken TTS feedback strictly scoped to the current topic and learned vocabulary.
 */
@AndroidEntryPoint
class SpeakingAiFragment : Fragment() {

    private var _binding: FragmentSpeakingAiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpeakingAiViewModel by viewModels()
    private lateinit var transcriptAdapter: AiChatTranscriptAdapter
    private var speechRecognizer: SpeechRecognizer? = null

    private val requestMicPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startVoiceListening()
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

        binding.headerSpeakingAi.tvHeaderTitle.text = getString(R.string.speaking_ai_title)
        binding.headerSpeakingAi.tvHeaderSubtitle.text = "Topic-restricted voice & grammar tutor"
        binding.headerSpeakingAi.btnBack.setOnClickListener {
            viewModel.endAndRecordSession {
                if (isAdded) findNavController().navigateUp()
            }
        }

        binding.btnEndSpeakingSession.setOnClickListener {
            viewModel.endAndRecordSession {
                if (isAdded) findNavController().navigateUp()
            }
        }

        binding.btnMicrophoneSpeak.setOnClickListener {
            val granted = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
            if (granted) {
                startVoiceListening()
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

                    transcriptAdapter.submitList(state.transcript) {
                        if (state.transcript.isNotEmpty()) {
                            binding.rvSpeakingTranscript.scrollToPosition(state.transcript.size - 1)
                        }
                    }
                }
            }
        }
    }

    private fun startVoiceListening() {
        if (!SpeechRecognizer.isRecognitionAvailable(requireContext())) {
            viewModel.updateStatus("Voice recognition service unavailable on device; use text input below.")
            return
        }
        try {
            speechRecognizer?.destroy()
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext()).apply {
                setRecognitionListener(object : RecognitionListener {
                    override fun onReadyForSpeech(params: Bundle?) {
                        viewModel.updateStatus(getString(R.string.speaking_ai_status_listening))
                    }
                    override fun onBeginningOfSpeech() {}
                    override fun onRmsChanged(rmsdB: Float) {}
                    override fun onBufferReceived(buffer: ByteArray?) {}
                    override fun onEndOfSpeech() {}
                    override fun onError(error: Int) {
                        viewModel.updateStatus("Microphone finished • Tap to speak or type below")
                    }
                    override fun onResults(results: Bundle?) {
                        val spoken = results
                            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                            ?.firstOrNull()
                        if (!spoken.isNullOrBlank()) {
                            viewModel.sendStudentUtterance(spoken)
                        }
                    }
                    override fun onPartialResults(partialResults: Bundle?) {}
                    override fun onEvent(eventType: Int, params: Bundle?) {}
                })
            }

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            }
            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            viewModel.updateStatus("Could not start microphone: ${e.localizedMessage}")
        }
    }

    override fun onDestroyView() {
        speechRecognizer?.destroy()
        speechRecognizer = null
        super.onDestroyView()
        _binding = null
    }
}
