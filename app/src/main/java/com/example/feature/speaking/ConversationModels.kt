package com.example.feature.speaking

import android.util.Log
import com.example.BuildConfig
import com.example.core.common.AppConstants
import com.example.domain.model.AiTutorMessage
import java.util.UUID

const val TALKME_CONVERSATION_LOG_TAG = "TalkMeConversation"

/**
 * Diagnostic logger for the local Silero VAD + Sherpa-ONNX STT + Gemini + TTS conversation pipeline.
 * Never logs raw user transcript contents in release builds.
 */
object TalkMeConversationLogger {
    var isLoggingEnabled: Boolean = true

    private fun logEvent(event: String, details: String = "") {
        if (!isLoggingEnabled) return
        val message = if (details.isNotBlank()) "[$event] $details" else "[$event]"
        runCatching { Log.d(TALKME_CONVERSATION_LOG_TAG, message) }
    }

    fun logListeningStarted(turnId: String) =
        logEvent("LISTENING_STARTED", "turnId=$turnId")

    fun logSpeechStarted(turnId: String) =
        logEvent("VAD_SPEECH_BEGIN", "turnId=$turnId")

    fun logPartialTranscript(turnId: String, length: Int) =
        logEvent("PARTIAL_TRANSCRIPT", "turnId=$turnId chars=$length")

    fun logFinalizingStarted(turnId: String) =
        logEvent("FINALIZING_STT", "turnId=$turnId")

    fun logFinalTranscript(turnId: String, length: Int) =
        logEvent("FINAL_TRANSCRIPT", "turnId=$turnId chars=$length")

    fun logGeminiRequestStarted(turnId: String) =
        logEvent("AI_THINKING_STARTED", "turnId=$turnId")

    fun logGeminiResponseReceived(turnId: String, latencyMs: Long) =
        logEvent("AI_RESPONSE_RECEIVED", "turnId=$turnId latencyMs=$latencyMs")

    fun logTtsStarted(turnId: String, utteranceLength: Int) =
        logEvent("AI_SPEAKING_STARTED", "turnId=$turnId chars=$utteranceLength")

    fun logTtsCompleted(turnId: String) =
        logEvent("AI_SPEAKING_COMPLETED", "turnId=$turnId")

    fun logErrorAndRecovery(stage: String, reason: String) {
        val safeReason = if (BuildConfig.DEBUG) reason else reason.take(64)
        logEvent("ERROR_AND_RECOVERY", "stage=$stage reason=$safeReason")
    }
}

/**
 * Deterministic Hands-Free Turn-Taking State Machine (Section 9):
 *
 * IDLE
 * → LISTENING
 * → USER_SPEAKING
 * → FINALIZING
 * → AI_THINKING
 * → AI_SPEAKING
 * → LISTENING
 *
 * Plus lifecycle & honest error states:
 * - STOPPED
 * - ERROR
 */
sealed interface ConversationState {
    val label: String

    /** Initial state before the user starts a session. */
    data object IDLE : ConversationState {
        override val label: String = "IDLE"
    }

    /** AudioRecord is active and Local Silero VAD is waiting for speech onset. */
    data class LISTENING(
        val turnId: String
    ) : ConversationState {
        override val label: String = "LISTENING"
    }

    /** Silero VAD detected active user speech; Sherpa-ONNX Streaming is producing partial text. */
    data class USER_SPEAKING(
        val turnId: String,
        val provisionalTranscript: String = ""
    ) : ConversationState {
        override val label: String = "USER_SPEAKING"
    }

    /** Silero VAD detected speech end; Sherpa-ONNX Pass-2 is finalizing the exact transcript. */
    data class FINALIZING(
        val turnId: String
    ) : ConversationState {
        override val label: String = "FINALIZING"
    }

    /** Final transcript is verified and submitted to Firebase Gemini AI Tutor. */
    data class AI_THINKING(
        val turnId: String,
        val rawTranscript: String
    ) : ConversationState {
        override val label: String = "AI_THINKING"
    }

    /** Android TextToSpeech is speaking the AI Tutor's reply while the microphone is off. */
    data class AI_SPEAKING(
        val turnId: String,
        val spokenResponse: String
    ) : ConversationState {
        override val label: String = "AI_SPEAKING"
    }

    /** User explicitly stopped the conversation or left the screen. */
    data object STOPPED : ConversationState {
        override val label: String = "STOPPED"
    }

    /** Honest failure state for STT, Gemini, or TTS errors with safe retry/replay/skip. */
    data class ERROR(
        val error: ConversationError
    ) : ConversationState {
        override val label: String = "ERROR"
    }
}

enum class ConversationRole(val wireName: String) {
    SYSTEM("SYSTEM"),
    USER("USER"),
    ASSISTANT("ASSISTANT")
}

/**
 * Enforces Absolute Transcript Integrity (Section 3):
 *
 * - [rawTranscript]: Actual final text produced by local Sherpa-ONNX STT.
 * - [displayedUserMessage]: Identical to [rawTranscript].
 * - [geminiStudentUtterance]: Identical to [rawTranscript].
 *
 * Never completes incomplete sentences, adds grammar words, paraphrases, translates,
 * or rewrites the user's speech.
 */
data class VerifiedUserTurn(
    val turnId: String = UUID.randomUUID().toString(),
    val rawTranscript: String,
    val createdAtMs: Long = System.currentTimeMillis()
) {
    val displayedUserMessage: String get() = rawTranscript
    val geminiStudentUtterance: String get() = rawTranscript

    // Backward-compatible property aliases
    val finalSttTranscript: String get() = rawTranscript
    val geminiUserMessage: String get() = rawTranscript

    init {
        require(
            displayedUserMessage == rawTranscript &&
                geminiStudentUtterance == rawTranscript
        ) {
            "Transcript integrity violation: displayedUserMessage and geminiStudentUtterance must equal rawTranscript."
        }
    }
}

/**
 * Immutable conversation message with explicit [ConversationRole] (`SYSTEM`, `USER`, `ASSISTANT`).
 * Keeps optional grammar feedback strictly in a separate field ([aiGeneratedFeedback]) so the
 * original user transcript is never modified.
 */
data class ConversationMessage(
    val id: String = UUID.randomUUID().toString(),
    val turnId: String,
    val role: ConversationRole,
    val text: String,
    val aiGeneratedFeedback: String? = null,
    val timestampMs: Long = System.currentTimeMillis()
) {
    fun toAiTutorMessage(includeGrammarFeedback: Boolean = true): AiTutorMessage = AiTutorMessage(
        id = id,
        senderRole = when (role) {
            ConversationRole.USER -> "STUDENT"
            ConversationRole.ASSISTANT, ConversationRole.SYSTEM -> "AI_TUTOR"
        },
        englishText = text,
        correctionOrUzbekFeedback = if (includeGrammarFeedback && role == ConversationRole.ASSISTANT) {
            aiGeneratedFeedback.orEmpty()
        } else {
            ""
        }
    )
}

data class ConversationError(
    val code: ErrorCode,
    val userMessageUzbek: String,
    val failedTranscript: String = "",
    val failedSpokenReply: String = "",
    val turnId: String? = null,
    val isRecoverable: Boolean = true
) {
    enum class ErrorCode {
        MIC_PERMISSION_REQUIRED,
        AUDIO_CAPTURE_UNSUPPORTED,
        STT_ERROR,
        NETWORK_ERROR,
        API_TIMEOUT,
        RATE_LIMITED,
        EMPTY_AI_RESPONSE,
        TTS_ERROR
    }
}

data class TurnLatencyMetrics(
    val turnId: String = "",
    val vadEndToFinalSttMs: Long = 0L,
    val sttToAiResponseMs: Long = 0L,
    val responseToFirstAudioMs: Long = 0L,
    val lastCancellationLatencyMs: Long = 0L
)

/**
 * Single immutable UI state for the TalkMe Speaking screen.
 */
data class ConversationUiState(
    val topicId: String = AppConstants.INITIAL_TOPIC_ID,
    val topicTitle: String = "1. Introduction (Kirish)",
    val learnerLevel: String = "A0_A1",
    val conversationState: ConversationState = ConversationState.IDLE,
    val isHandsFreeConversationActive: Boolean = false,
    val isAudioCaptureActuallyRunning: Boolean = false,
    val isMuted: Boolean = false,
    val isKeyboardInputVisible: Boolean = false,
    val isGrammarFeedbackEnabled: Boolean = true,
    val isMicPermissionGranted: Boolean = false,
    val provisionalTranscript: String = "",
    val keyboardDraftText: String = "",
    val messages: List<ConversationMessage> = emptyList(),
    val statusMessage: String = "Suhbatni boshlash uchun mikrofonni bir marta bosing",
    val durationSeconds: Int = 0,
    val audioAmplitude: Float = 0f,
    val lastError: ConversationError? = null,
    val latencyMetrics: TurnLatencyMetrics = TurnLatencyMetrics()
) {
    val isListeningOrSpeakingUser: Boolean
        get() = isAudioCaptureActuallyRunning &&
            (conversationState is ConversationState.LISTENING ||
                conversationState is ConversationState.USER_SPEAKING)

    val isFinalizing: Boolean
        get() = conversationState is ConversationState.FINALIZING

    val isLoading: Boolean
        get() = conversationState is ConversationState.FINALIZING ||
            conversationState is ConversationState.AI_THINKING

    val isAiSpeaking: Boolean
        get() = conversationState is ConversationState.AI_SPEAKING

    val transcript: List<AiTutorMessage>
        get() = messages
            .filter { it.role != ConversationRole.SYSTEM }
            .map { it.toAiTutorMessage(includeGrammarFeedback = isGrammarFeedbackEnabled) }
}

/**
 * Builds structured system instructions and chronological conversation history for Gemini (Sections 4–8 & 11).
 */
object TutorPromptConfig {
    const val MAX_BOUNDED_HISTORY_TURNS = 12
    const val MAX_TRANSCRIPT_MESSAGES_IN_MEMORY = 80

    /**
     * Derives the learner's English CEFR band (`A0_A1`, `A2_B1`, `B2_C2`) from actual curriculum progress.
     */
    fun resolveLearnerLevel(
        topicOrderIndex: Int,
        completedTopicsCount: Int
    ): String {
        val effectiveStage = maxOf(topicOrderIndex, completedTopicsCount + 1)
        return when {
            effectiveStage <= 4 -> "A0_A1"
            effectiveStage <= 9 -> "A2_B1"
            else -> "B2_C2"
        }
    }

    /**
     * Maps the learner's CEFR band to an appropriate default TTS speech rate (Section 6).
     */
    fun defaultTtsSpeedForLevel(learnerLevel: String, userPreferredSpeed: Float): Float {
        if (userPreferredSpeed != 1.0f) return userPreferredSpeed.coerceIn(0.6f, 1.5f)
        return when (learnerLevel.uppercase()) {
            "A0_A1" -> 0.90f
            "A2_B1" -> 1.00f
            "B2_C2" -> 1.05f
            else -> 0.95f
        }
    }

    /**
     * Returns chronological `USER` and `ASSISTANT` turns excluding the latest user turn so
     * the latest utterance is never duplicated in history.
     */
    fun buildRoleHistoryPairs(
        messages: List<ConversationMessage>,
        maxMessages: Int = MAX_BOUNDED_HISTORY_TURNS
    ): List<Pair<String, String>> {
        return messages
            .filter { it.role == ConversationRole.USER || it.role == ConversationRole.ASSISTANT }
            .takeLast(maxMessages)
            .map { it.role.wireName to it.text }
    }

    fun buildSystemGuidance(
        topicTitle: String,
        learnerLevel: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>,
        lastAssistantQuestion: String? = null
    ): String {
        val vocabText = allowedVocabulary.take(25).joinToString(", ")
            .ifEmpty { "hello, hi, name, friend, student, morning, water, book, school, teacher" }
        val grammarText = allowedGrammarSummaries.take(8).joinToString("; ")
            .ifEmpty { "Simple present and everyday conversation" }

        val levelInstructions = when (learnerLevel.uppercase()) {
            "A0_A1" -> """
                LEARNER LEVEL (A0–A1 Beginner):
                - Use very short, clear sentences (1 to 2 short sentences maximum).
                - Use basic everyday vocabulary.
                - Ask ONE simple, easy-to-answer follow-up question at a time.
                - Avoid complex grammar terminology. Give brief Uzbek help in grammarAndVocabFeedbackUzbek only when genuinely helpful.
            """.trimIndent()
            "A2_B1" -> """
                LEARNER LEVEL (A2–B1 Elementary/Intermediate):
                - Conduct natural everyday conversation with 1 to 3 natural sentences.
                - Use common phrases and expressions.
                - Ask ONE engaging follow-up question connected to what the learner said.
                - Provide gentle grammar or vocabulary tips in grammarAndVocabFeedbackUzbek only when useful.
            """.trimIndent()
            else -> """
                LEARNER LEVEL (B2–C2 Upper-Intermediate/Advanced):
                - Engage in richer conversation with natural idioms and nuanced expressions (2 to 3 sentences).
                - Explore opinions, reasons, and comparisons while asking ONE thoughtful follow-up question.
                - Provide fluency-focused refinement in grammarAndVocabFeedbackUzbek only when appropriate.
            """.trimIndent()
        }

        val lastQuestionContext = if (!lastAssistantQuestion.isNullOrBlank()) {
            "PREVIOUS TUTOR QUESTION/MESSAGE: \"$lastAssistantQuestion\""
        } else {
            "PREVIOUS TUTOR QUESTION/MESSAGE: (Start of conversation)"
        }

        return """
            You are the TalkMe AI English Tutor — a friendly, intelligent, natural English conversation partner.
            Active Lesson Topic: "$topicTitle"
            Target Lesson Vocabulary: [$vocabText]
            Target Lesson Grammar: [$grammarText]
            $lastQuestionContext
            
            $levelInstructions
            
            CORE CONVERSATIONAL & CONTEXT RULES:
            1. UNDERSTAND SHORT & ELLIPTICAL REPLIES IN CONTEXT:
               - Short replies like "Yes.", "No.", "Sometimes.", "With my friends.", "At home.", "At school.", "Every morning.", "Pizza.", "With cheese.", or "Because I like it." are direct answers to your PREVIOUS TUTOR QUESTION.
               - Never require the learner to speak complete sentences every time. Connect their short answer directly to the topic of your previous question.
            2. HANDLE CLARIFICATION & REPEAT REQUESTS:
               - If the learner says "I don't understand", "What does that mean?", or seems confused, gently explain or simplify your previous question in simpler words.
               - If the learner says "Can you repeat that?" or "Say again", repeat or simplify your last question clearly.
            3. NATURAL PERSONALITY (NO ROBOTIC EXAMINER PHRASES):
               - Respond directly to the specific meaning of what the learner said, then ask ONE related follow-up question.
               - NEVER use generic stock replies like "Great job! Can you make another sentence?", "Excellent! Let's practice more.", or "Good answer! What else?".
               - Avoid repeating questions already asked in the conversation history.
               - If the learner asks you a question, answer it naturally first before asking your follow-up question.
            4. SMART GRAMMAR RECASTING & SEPARATE FEEDBACK:
               - Prioritize understanding and conversation flow over constant correction.
               - If the learner makes a grammar error (e.g., "Yesterday I go to school."), naturally recast the correct form in your spoken reply (e.g., "Oh, you went to school yesterday! What did you study?") and optionally put a brief Uzbek note in `grammarAndVocabFeedbackUzbek` (e.g., "O'tgan zamon uchun 'go' o'rniga 'went' ishlatiladi.").
               - NEVER alter or rewrite the learner's original message, and NEVER claim pronunciation accuracy from text alone.
               - Never output phonetic transcriptions or IPA brackets.
        """.trimIndent()
    }
}
