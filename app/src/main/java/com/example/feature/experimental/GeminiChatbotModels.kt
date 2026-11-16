package com.example.feature.experimental

import com.example.R

/**
 * Gemini model tiers exposed in the Experimental Lesson Chatbot UI.
 */
enum class ChatbotModelTier(
    val modelId: String,
    val labelRes: Int,
    val badgeLabel: String
) {
    HYDROGEN("gemini-4-hydrogen", R.string.chatbot_model_hydrogen, "Hydrogen"),
    ARGON("gemini-4-argon", R.string.chatbot_model_argon, "Argon"),
    CARBON("gemini-4-carbon", R.string.chatbot_model_carbon, "Carbon"),
    FLASH_LITE("gemini-3.1-flash-lite", R.string.chatbot_model_flash_lite, "Flash-Lite")
}

/**
 * Curriculum-grounded lesson context loaded dynamically from LearningRepository.
 */
data class LessonChatContext(
    val topicTitle: String = "",
    val levelCode: String = "A0",
    val currentSectionTitle: String = "",
    val studiedGrammarSummaries: List<String> = emptyList(),
    val studiedVocabularyWords: List<String> = emptyList()
) {
    val displayLabel: String
        get() = when {
            topicTitle.isNotBlank() && currentSectionTitle.isNotBlank() ->
                "$levelCode · $currentSectionTitle"
            topicTitle.isNotBlank() ->
                "$levelCode · $topicTitle"
            else ->
                ""
        }
}

/**
 * Specialized TalkMe lesson-only personas.
 */
enum class ChatbotPersonaPreset(
    val labelRes: Int,
    val roleDirective: String
) {
    ENGLISH_TUTOR(
        labelRes = R.string.chatbot_role_tutor,
        roleDirective = "Act as the TalkMe Lesson Tutor. Explain the exact rule, phrase, or exercise the learner asked about using concise Uzbek explanations paired with clear English examples."
    ),
    CONVERSATION_PARTNER(
        labelRes = R.string.chatbot_role_partner,
        roleDirective = "Act as a TalkMe English Lesson Practice Partner. Practice short, natural dialogues using the lesson vocabulary and grammar, correct mistakes gently in 1 line, and ask 1 short follow-up question."
    ),
    GRAMMAR_EXPERT(
        labelRes = R.string.chatbot_role_grammar,
        roleDirective = "Act as the TalkMe Grammar Coach. Answer directly with: (1) direct rule in 1-2 lines, (2) simple formula, (3) 2 short English examples with Uzbek translation."
    ),
    VOCAB_HELPER(
        labelRes = R.string.chatbot_role_vocab,
        roleDirective = "Act as the TalkMe Vocabulary Coach. Give exact meanings in Uzbek, natural example sentences tied to the lesson, and common usage tips."
    ),
    CUSTOM(
        labelRes = R.string.chatbot_role_custom,
        roleDirective = "Follow the user's custom English lesson instruction strictly while staying within TalkMe English learning."
    )
}

data class ChatbotMessage(
    val id: String,
    val isUser: Boolean,
    val text: String,
    val modelBadge: String = "",
    val isStreaming: Boolean = false
)

sealed interface ChatbotStatus {
    data object Idle : ChatbotStatus
    data object Loading : ChatbotStatus
    data object Streaming : ChatbotStatus
    data class Error(val message: String) : ChatbotStatus
}

data class ChatbotUiState(
    val messages: List<ChatbotMessage> = emptyList(),
    val selectedModel: ChatbotModelTier = ChatbotModelTier.HYDROGEN,
    val selectedPersona: ChatbotPersonaPreset = ChatbotPersonaPreset.ENGLISH_TUTOR,
    val customSystemPrompt: String = ChatbotPersonaPreset.CUSTOM.roleDirective,
    val lessonContext: LessonChatContext = LessonChatContext(),
    val status: ChatbotStatus = ChatbotStatus.Idle
) {
    val activeSystemInstruction: String
        get() = ChatbotPromptBuilder.buildSystemInstruction(
            persona = selectedPersona,
            customDirective = customSystemPrompt,
            lessonContext = lessonContext
        )

    val isBusy: Boolean
        get() = status is ChatbotStatus.Loading || status is ChatbotStatus.Streaming
}

object ChatbotPromptBuilder {

    fun buildSystemInstruction(
        persona: ChatbotPersonaPreset,
        customDirective: String,
        lessonContext: LessonChatContext
    ): String {
        val rolePart = if (persona == ChatbotPersonaPreset.CUSTOM) {
            customDirective.trim().ifEmpty { ChatbotPersonaPreset.CUSTOM.roleDirective }
        } else {
            persona.roleDirective
        }

        val topicLine = if (lessonContext.topicTitle.isNotBlank()) {
            "Active TalkMe Course Topic: ${lessonContext.topicTitle} (Level ${lessonContext.levelCode})" +
                if (lessonContext.currentSectionTitle.isNotBlank()) {
                    " | Current Lesson: ${lessonContext.currentSectionTitle}"
                } else {
                    ""
                }
        } else {
            "Active TalkMe Course: Ingliz tili darslari (A0–C2)"
        }

        val grammarLine = if (lessonContext.studiedGrammarSummaries.isNotEmpty()) {
            "Studied Lesson Grammar: ${lessonContext.studiedGrammarSummaries.take(8).joinToString("; ")}"
        } else {
            ""
        }

        val vocabLine = if (lessonContext.studiedVocabularyWords.isNotEmpty()) {
            "Studied Lesson Vocabulary: ${lessonContext.studiedVocabularyWords.take(20).joinToString(", ")}"
        } else {
            ""
        }

        return buildString {
            appendLine("You are TalkMe Lesson Assistant inside the TalkMe Android English learning app.")
            appendLine("Role: $rolePart")
            appendLine(topicLine)
            if (grammarLine.isNotEmpty()) appendLine(grammarLine)
            if (vocabLine.isNotEmpty()) appendLine(vocabLine)
            appendLine()
            appendLine("STRICT BEHAVIOR RULES:")
            appendLine("1. LESSON-ONLY SCOPE: Only answer questions related to learning English and TalkMe lessons (grammar rules, vocabulary words, pronunciation tips without IPA symbols, reading, writing, listening, speaking exercises, or lesson dialogues).")
            appendLine("2. OFF-TOPIC REFUSAL: If the user asks about anything unrelated to English lessons or language learning (e.g., programming, politics, news, sports, math, jokes, general chat outside English study), politely decline in 1-2 short Uzbek (Latin script) sentences: \"Men faqat TalkMe ingliz tili darslari bo‘yicha yordam bera olaman. Keling, ingliz tili qoidalari, so‘zlar yoki mashqlar haqida gaplashamiz!\"")
            appendLine("3. ANSWER PRECISELY TO THE QUESTION: Address the user's exact question directly. Do not ramble, do not repeat the user's question, and do not add long generic introductions or filler.")
            appendLine("4. CLEAN OUTPUT FORMATTING (CRITICAL): Write clean, compact, easy-to-read plain text. Do NOT use Markdown headers (no # or ###), do NOT wrap lines in excessive ** asterisks, do NOT draw Markdown tables (no |---|), do NOT output code blocks (```), and do NOT include IPA phonetic brackets like /ˈhɛloʊ/.")
            appendLine("5. STRUCTURE & LENGTH: Keep answers crisp (usually 2 to 6 short lines). Use clear Uzbek (Latin script) explanations and short English example sentences. Number short points only when listing distinct examples.")
        }.trim()
    }
}
