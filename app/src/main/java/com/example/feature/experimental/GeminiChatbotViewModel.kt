package com.example.feature.experimental

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CurriculumLevel
import com.example.domain.repository.LearningRepository
import com.google.firebase.Firebase
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

private const val TAG = "GeminiChatbotVM"
private const val CHAT_TIMEOUT_MS = 60_000L

interface GeminiChatbotEngine {
    fun streamReply(
        modelId: String,
        systemInstruction: String,
        priorMessages: List<ChatbotMessage>,
        userPrompt: String
    ): Flow<String>
}

@Singleton
class FirebaseGeminiChatbotEngine @Inject constructor() : GeminiChatbotEngine {

    private val modelCache = ConcurrentHashMap<Pair<String, String>, GenerativeModel>()

    private fun getOrCreateModel(modelId: String, systemInstruction: String): GenerativeModel {
        val key = modelId to systemInstruction
        return modelCache.getOrPut(key) {
            Firebase.ai.generativeModel(
                modelName = modelId,
                systemInstruction = content { text(systemInstruction) }
            )
        }
    }

    override fun streamReply(
        modelId: String,
        systemInstruction: String,
        priorMessages: List<ChatbotMessage>,
        userPrompt: String
    ): Flow<String> {
        val model = getOrCreateModel(modelId, systemInstruction)
        val history = priorMessages
            .filter { it.text.isNotBlank() }
            .takeLast(20)
            .map { msg ->
                content(role = if (msg.isUser) "user" else "model") {
                    text(msg.text)
                }
            }
        val chat = model.startChat(history = history)
        return chat.sendMessageStream(userPrompt).mapNotNull { chunk -> chunk.text }
    }
}

@HiltViewModel
class GeminiChatbotViewModel @Inject constructor(
    private val chatbotEngine: GeminiChatbotEngine,
    private val learningRepository: LearningRepository?
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatbotUiState())
    val uiState: StateFlow<ChatbotUiState> = _uiState.asStateFlow()

    init {
        loadActiveLessonContext()
    }

    private fun loadActiveLessonContext() {
        val repo = learningRepository ?: return
        viewModelScope.launch {
            try {
                val stats = repo.observeDashboardStats().firstOrNull()
                val activeTopicId = stats?.continueLearning?.currentTopicId.orEmpty()
                val topic = if (activeTopicId.isNotEmpty()) repo.getTopicById(activeTopicId) else null
                val level = CurriculumLevel.forTopic(activeTopicId)?.name ?: "A0"

                val vocab = if (activeTopicId.isNotEmpty()) {
                    repo.getStudiedVocabularyForTopic(activeTopicId)
                        .ifEmpty { repo.observeLibraryVocabulary().firstOrNull().orEmpty() }
                } else {
                    repo.observeLibraryVocabulary().firstOrNull().orEmpty()
                }

                val grammar = if (activeTopicId.isNotEmpty()) {
                    repo.getStudiedGrammarForTopic(activeTopicId)
                        .ifEmpty { repo.observeLibraryGrammar().firstOrNull().orEmpty() }
                } else {
                    repo.observeLibraryGrammar().firstOrNull().orEmpty()
                }

                val lessonContext = LessonChatContext(
                    topicTitle = stats?.continueLearning?.currentTopicTitle
                        ?.ifBlank { topic?.titleUzbek?.ifBlank { topic.titleEnglish }.orEmpty() }
                        .orEmpty(),
                    levelCode = level,
                    currentSectionTitle = stats?.continueLearning?.currentSectionTitle.orEmpty(),
                    studiedGrammarSummaries = grammar.map { "${it.titleUzbek}: ${it.explanationUzbek}" },
                    studiedVocabularyWords = vocab.map { "${it.englishWord} (${it.uzbekTranslation})" }
                )

                _uiState.update { it.copy(lessonContext = lessonContext) }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.w(TAG, "Could not load curriculum context for chatbot", e)
            }
        }
    }

    fun selectModel(model: ChatbotModelTier) {
        _uiState.update { state ->
            state.copy(
                selectedModel = model,
                status = if (state.status is ChatbotStatus.Error) ChatbotStatus.Idle else state.status
            )
        }
    }

    fun selectPersona(persona: ChatbotPersonaPreset) {
        _uiState.update { state ->
            state.copy(
                selectedPersona = persona,
                status = if (state.status is ChatbotStatus.Error) ChatbotStatus.Idle else state.status
            )
        }
    }

    fun setCustomSystemInstruction(instruction: String) {
        val trimmed = instruction.trim()
        _uiState.update { state ->
            state.copy(
                selectedPersona = ChatbotPersonaPreset.CUSTOM,
                customSystemPrompt = trimmed.ifBlank { ChatbotPersonaPreset.CUSTOM.roleDirective }
            )
        }
    }

    fun clearConversation() {
        _uiState.update { state ->
            state.copy(messages = emptyList(), status = ChatbotStatus.Idle)
        }
    }

    fun sendMessage(rawText: String, timeoutMessage: String, emptyResponseMessage: String) {
        val trimmed = rawText.trim()
        if (trimmed.isEmpty()) return
        val currentState = _uiState.value
        if (currentState.isBusy) return

        val priorHistory = currentState.messages
        val userMsg = ChatbotMessage(
            id = UUID.randomUUID().toString(),
            isUser = true,
            text = trimmed
        )
        val botMsgId = UUID.randomUUID().toString()
        val chosenModel = currentState.selectedModel
        val systemInstruction = currentState.activeSystemInstruction

        _uiState.update { state ->
            state.copy(
                messages = state.messages + userMsg,
                status = ChatbotStatus.Loading
            )
        }

        viewModelScope.launch {
            val accumulated = StringBuilder()
            try {
                withTimeout(CHAT_TIMEOUT_MS) {
                    chatbotEngine.streamReply(
                        modelId = chosenModel.modelId,
                        systemInstruction = systemInstruction,
                        priorMessages = priorHistory,
                        userPrompt = trimmed
                    ).collect { chunkText ->
                        if (chunkText.isNotEmpty()) {
                            accumulated.append(chunkText)
                            val partial = accumulated.toString()
                            _uiState.update { state ->
                                val withoutPlaceholder = state.messages.filterNot { it.id == botMsgId }
                                val botMsg = ChatbotMessage(
                                    id = botMsgId,
                                    isUser = false,
                                    text = partial,
                                    modelBadge = chosenModel.badgeLabel,
                                    isStreaming = true
                                )
                                state.copy(
                                    messages = withoutPlaceholder + botMsg,
                                    status = ChatbotStatus.Streaming
                                )
                            }
                        }
                    }
                }

                val finalText = ChatbotTextFormatter.formatPlainText(accumulated.toString())
                if (finalText.isNotBlank()) {
                    _uiState.update { state ->
                        val updatedMessages = state.messages.map { msg ->
                            if (msg.id == botMsgId) {
                                msg.copy(text = accumulated.toString().trim(), isStreaming = false)
                            } else {
                                msg
                            }
                        }
                        state.copy(messages = updatedMessages, status = ChatbotStatus.Idle)
                    }
                } else {
                    _uiState.update { state ->
                        state.copy(
                            messages = state.messages.filterNot { it.id == botMsgId },
                            status = ChatbotStatus.Error(emptyResponseMessage)
                        )
                    }
                }
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "Gemini Chatbot streaming timed out", e)
                finalizePartialOrError(botMsgId, accumulated.toString(), timeoutMessage)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Gemini Chatbot inference failed for ${chosenModel.modelId}", e)
                finalizePartialOrError(
                    botMsgId,
                    accumulated.toString(),
                    e.localizedMessage ?: "Gemini AI xatoligi yuz berdi."
                )
            }
        }
    }

    private fun finalizePartialOrError(
        botMsgId: String,
        partialText: String,
        errorMessage: String
    ) {
        val cleanPartial = partialText.trim()
        _uiState.update { state ->
            val updatedMessages = if (cleanPartial.isNotEmpty()) {
                state.messages.map { msg ->
                    if (msg.id == botMsgId) msg.copy(text = cleanPartial, isStreaming = false) else msg
                }
            } else {
                state.messages.filterNot { it.id == botMsgId }
            }
            state.copy(
                messages = updatedMessages,
                status = ChatbotStatus.Error(errorMessage)
            )
        }
    }
}
