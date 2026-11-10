package com.example.core.ai

import android.util.Log
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.ReadingPassageResult
import com.example.domain.repository.SpeakingAiResponse
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.generationConfig
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

private const val TAG = "FirebaseAiLearning"

/**
 * Firebase AI Logic implementation of [AiLearningService].
 * Provides two strictly separate AI experiences:
 * 1. Speaking AI Session (`speakingModel` with structured JSON feedback & spoken reply).
 * 2. Reading AI Session (`readingGenerationModel` and `readingEvaluationModel`).
 *
 * Uses lazy initialization (`by lazy`) and 60-second coroutine timeouts per `firebase-ai-logic-android`.
 */
@Singleton
class FirebaseAiLearningService @Inject constructor() : AiLearningService {

    private val speakingSchema = Schema.obj(
        properties = mapOf(
            "spokenEnglishReply" to Schema.string(
                description = "Simple English spoken reply and follow-up question strictly about the active topic"
            ),
            "grammarAndVocabFeedbackUzbek" to Schema.string(
                description = "Short constructive grammar and vocabulary feedback in simple Uzbek"
            ),
            "accuracyScore" to Schema.integer(
                description = "Score from 60 to 100 evaluating student grammar and vocabulary usage"
            )
        )
    )

    private val readingPassageSchema = Schema.obj(
        properties = mapOf(
            "titleEnglish" to Schema.string(description = "Short English title for the reading passage"),
            "passageEnglish" to Schema.string(description = "4 to 5 simple English sentences using the learned vocabulary and topic grammar"),
            "vocabularyNotesUzbek" to Schema.string(description = "Key word explanations in Uzbek without phonetic symbols"),
            "comprehensionQuestionEnglish" to Schema.string(description = "One clear comprehension question in English about the passage")
        )
    )

    // Separate lazy model for Speaking AI Tutor
    private val speakingModel by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = speakingSchema
            }
        )
    }

    // Separate lazy model for Reading AI Passage Generation
    private val readingPassageModel by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = readingPassageSchema
            }
        )
    }

    // Separate lazy model for Reading AI Comprehension Evaluation & Uzbek Explanations
    private val readingEvaluationModel by lazy {
        Firebase.ai.generativeModel(modelName = "gemini-3.8-flash")
    }

    override suspend fun sendSpeakingTurn(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>,
        conversationHistory: List<Pair<String, String>>,
        studentUtterance: String
    ): Result<SpeakingAiResponse> {
        val vocabList = allowedVocabulary.joinToString(", ").ifEmpty { "hello, book, apple, table, student" }
        val grammarList = allowedGrammarSummaries.joinToString("; ").ifEmpty { "Basic simple sentences" }
        val historyText = conversationHistory.takeLast(6).joinToString("\n") { "${it.first}: ${it.second}" }

        val prompt = """
            You are the TalkMe Speaking AI English Tutor for a beginner Uzbek student.
            STRICT EDUCATIONAL BOUNDARIES:
            1. Current Topic: $topicTitle. Stay strictly on this topic.
            2. Studied Vocabulary: [$vocabList]. Use primarily these words and very simple beginner words.
            3. Studied Grammar: [$grammarList].
            4. Never output phonetic transcriptions or IPA symbols.
            5. Detect any grammar or vocabulary mistakes in the student's sentence, provide a short explanation in simple Uzbek in 'grammarAndVocabFeedbackUzbek', and reply in simple spoken English with a follow-up question on '$topicTitle' in 'spokenEnglishReply'.
            
            Recent Speaking Session Transcript:
            $historyText
            
            Student just said: "$studentUtterance"
        """.trimIndent()

        return try {
            val response = withTimeout(60_000L) {
                speakingModel.generateContent(prompt)
            }
            val rawJson = response.text ?: throw IllegalStateException("Empty response from Speaking AI model.")
            val json = JSONObject(rawJson)
            Result.success(
                SpeakingAiResponse(
                    spokenEnglishReply = json.optString("spokenEnglishReply", "Good job! Can you make another sentence about $topicTitle?"),
                    grammarAndVocabFeedbackUzbek = json.optString("grammarAndVocabFeedbackUzbek", "Juda yaxshi! Gap tuzilishi to'g'ri."),
                    accuracyScore = json.optInt("accuracyScore", 85).coerceIn(0, 100)
                )
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Speaking AI Gemini session failed", e)
            Result.failure(e)
        }
    }

    override suspend fun generateReadingPassage(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>
    ): Result<ReadingPassageResult> {
        val vocabList = allowedVocabulary.joinToString(", ").ifEmpty { "hello, apple, cat, water, book, table" }
        val grammarList = allowedGrammarSummaries.joinToString("; ").ifEmpty { "Basic sentences" }

        val prompt = """
            You are the TalkMe Reading AI Coach (completely separate from Speaking AI).
            Generate a short beginner-friendly English reading passage (4-5 sentences) strictly focused on the topic: "$topicTitle".
            Use the student's studied vocabulary: [$vocabList] and studied grammar: [$grammarList].
            Do NOT include any phonetic transcriptions or IPA symbols.
            Include Uzbek vocabulary notes and one comprehension question in English.
        """.trimIndent()

        return try {
            val response = withTimeout(60_000L) {
                readingPassageModel.generateContent(prompt)
            }
            val rawJson = response.text ?: throw IllegalStateException("Empty response from Reading AI model.")
            val json = JSONObject(rawJson)
            Result.success(
                ReadingPassageResult(
                    titleEnglish = json.optString("titleEnglish", "Reading: $topicTitle"),
                    passageEnglish = json.optString("passageEnglish", "Hello! This is a book on the table. A good student reads the book every morning."),
                    vocabularyNotesUzbek = json.optString("vocabularyNotesUzbek", "Lug'at: book — kitob, table — stol, morning — tong."),
                    comprehensionQuestionEnglish = json.optString("comprehensionQuestionEnglish", "Where is the book?")
                )
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Reading AI passage generation failed", e)
            Result.failure(e)
        }
    }

    override suspend fun evaluateReadingQuery(
        topicTitle: String,
        passageText: String,
        comprehensionQuestion: String,
        studentQueryOrAnswer: String
    ): Result<String> {
        val prompt = """
            You are the TalkMe Reading AI Comprehension Tutor.
            Topic: $topicTitle
            Reading Passage: "$passageText"
            Comprehension Question: "$comprehensionQuestion"
            Student's Answer or Question: "$studentQueryOrAnswer"
            
            Instructions:
            - Evaluate the student's comprehension answer or explain any word they asked about in clear, friendly Uzbek and simple English.
            - Never use phonetic transcriptions or IPA symbols.
            - Keep your response concise (2-4 sentences).
        """.trimIndent()

        return try {
            val response = withTimeout(60_000L) {
                readingEvaluationModel.generateContent(prompt)
            }
            val text = response.text?.trim()
            if (!text.isNullOrEmpty()) {
                Result.success(text)
            } else {
                Result.failure(IllegalStateException("Empty evaluation response from Reading AI."))
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Reading AI evaluation failed", e)
            Result.failure(e)
        }
    }
}
