package com.example.feature.reading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.databinding.FragmentReadingAiBinding
import com.example.domain.model.PracticeCategory
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class ReadingAiUiState(
    val topicId: String = AppConstants.INITIAL_TOPIC_ID,
    val topicTitle: String = "Introduction — Kirish",
    val passageTitle: String = "",
    val passageBody: String = "",
    val uzbekNotes: String = "",
    val comprehensionQuestion: String = "",
    val aiFeedbackText: String = "",
    val isLoading: Boolean = false
)

@HiltViewModel
class ReadingAiViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val aiLearningService: AiLearningService,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReadingAiUiState())
    val uiState: StateFlow<ReadingAiUiState> = _uiState.asStateFlow()

    private var allowedWords: List<String> = emptyList()
    private var allowedGrammar: List<String> = emptyList()

    private var hasInitialized = false

    fun loadTopicAndGenerateInitialPassage(requestedTopicId: String? = null) {
        if (hasInitialized) return
        hasInitialized = true
        viewModelScope.launch {
            learningRepository.ensureSeeded()
            val prefs = preferencesRepository.userPreferencesFlow.first()
            val targetTopicId = requestedTopicId?.takeIf { it.isNotBlank() } ?: prefs.lastActiveTopicId
            val topic = learningRepository.getTopicById(targetTopicId)
            val topicId = topic?.id ?: AppConstants.INITIAL_TOPIC_ID
            val title = topic?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" } ?: "1. Introduction (Kirish)"

            val vocab = learningRepository.getStudiedVocabularyForTopic(topicId)
            val grammar = learningRepository.getStudiedGrammarForTopic(topicId)
            allowedWords = vocab.map { it.englishWord }
            allowedGrammar = grammar.map { "${it.titleUzbek}: ${it.exampleEnglish}" }

            // Provide immediate offline-safe passage from studied material, then allow cloud AI generation
            val initialPassage = vocab.joinToString(" ") { it.exampleEnglish }
                .ifBlank { "Hello, my friend! This is an apple. Clean water in the morning." }
            val initialNotes = vocab.joinToString(" • ") { "${it.englishWord} — ${it.uzbekTranslation}" }

            _uiState.value = ReadingAiUiState(
                topicId = topicId,
                topicTitle = title,
                passageTitle = "Topic Reading: ${topic?.titleEnglish ?: "Introduction"}",
                passageBody = initialPassage,
                uzbekNotes = "Studied Vocabulary: $initialNotes",
                comprehensionQuestion = "What English words did you recognize in this passage?"
            )
        }
    }

    fun generateNewAiPassage() {
        if (_uiState.value.isLoading) return
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            aiFeedbackText = "Generating new topic passage with Gemini Reading AI…"
        )
        viewModelScope.launch {
            val result = aiLearningService.generateReadingPassage(
                topicTitle = _uiState.value.topicTitle,
                allowedVocabulary = allowedWords,
                allowedGrammarSummaries = allowedGrammar
            )
            result.fold(
                onSuccess = { passage ->
                    _uiState.value = _uiState.value.copy(
                        passageTitle = passage.titleEnglish,
                        passageBody = passage.passageEnglish,
                        uzbekNotes = passage.vocabularyNotesUzbek,
                        comprehensionQuestion = passage.comprehensionQuestionEnglish,
                        aiFeedbackText = "",
                        isLoading = false
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        aiFeedbackText = "Offline / Cloud AI Error: ${error.localizedMessage ?: "Please check internet connection."}"
                    )
                }
            )
        }
    }

    fun evaluateUserAnswerOrQuestion(userInput: String) {
        val clean = userInput.trim()
        if (clean.isEmpty() || _uiState.value.isLoading) return

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            aiFeedbackText = "Reading AI Coach is evaluating your answer…"
        )

        viewModelScope.launch {
            val state = _uiState.value
            val result = aiLearningService.evaluateReadingQuery(
                topicTitle = state.topicTitle,
                passageText = state.passageBody,
                comprehensionQuestion = state.comprehensionQuestion,
                studentQueryOrAnswer = clean
            )

            result.fold(
                onSuccess = { feedback ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        aiFeedbackText = feedback
                    )
                    learningRepository.recordPracticeSessionResult(
                        category = PracticeCategory.READING,
                        topicId = state.topicId,
                        sectionId = AppConstants.INITIAL_SECTION_ID,
                        correctCount = 90,
                        totalCount = 100,
                        masteredGrammarRuleIds = emptyList(),
                        memorizedVocabularyIds = emptyList()
                    )
                },
                onFailure = { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        aiFeedbackText = "Error: ${error.localizedMessage ?: "Could not reach Gemini Reading AI."}"
                    )
                }
            )
        }
    }

    fun speakPassage() {
        val text = _uiState.value.passageBody
        if (text.isNotBlank()) {
            viewModelScope.launch {
                ttsManager.speakEnglish(text)
            }
        }
    }

    fun stopAudio() {
        ttsManager.stop()
    }

    override fun onCleared() {
        ttsManager.stop()
        super.onCleared()
    }
}

/**
 * Separate Reading AI Screen with its own Gemini session configuration,
 * passage audio playback, Uzbek word explanations, and comprehension evaluation.
 */
@AndroidEntryPoint
class ReadingAiFragment : Fragment() {

    private var _binding: FragmentReadingAiBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ReadingAiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReadingAiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestedTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        viewModel.loadTopicAndGenerateInitialPassage(requestedTopicId)

        binding.tvHeaderTitle.text = getString(R.string.reading_ai_title)
        binding.tvHeaderSubtitle.text = "Independent Reading Comprehension AI Session"
        binding.btnBack.setOnClickListener {
            viewModel.stopAudio()
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.btnGeneratePassage.setOnClickListener {
            viewModel.generateNewAiPassage()
        }

        binding.btnSpeakPassage.setOnClickListener {
            viewModel.speakPassage()
        }

        binding.btnEvaluateReading.setOnClickListener {
            val query = binding.etReadingUserQuery.text?.toString().orEmpty()
            if (query.isNotBlank()) {
                viewModel.evaluateUserAnswerOrQuestion(query)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.tvReadingCurrentTopic.text = state.topicTitle
                    binding.tvPassageTitle.text = state.passageTitle
                    binding.tvPassageBody.text = state.passageBody
                    binding.tvPassageUzbekNotes.text = state.uzbekNotes
                    binding.tvReadingQuestion.text = state.comprehensionQuestion

                    if (state.aiFeedbackText.isNotBlank()) {
                        binding.tvReadingAiFeedback.visibility = View.VISIBLE
                        binding.tvReadingAiFeedback.text = state.aiFeedbackText
                    } else {
                        binding.tvReadingAiFeedback.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onPause() {
        viewModel.stopAudio()
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
