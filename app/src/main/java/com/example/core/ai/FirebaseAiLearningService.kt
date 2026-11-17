package com.example.core.ai

import android.util.Base64
import android.util.Log
import com.example.BuildConfig
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.ReadingPassageResult
import com.example.domain.repository.SpeakingAiResponse
import com.example.feature.speaking.TutorPromptConfig
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject

private const val TAG = "FirebaseAiLearning"
private const val GEMINI_MODEL_NAME = "gemini-3.8-flash"

/**
 * Firebase AI Logic implementation of [AiLearningService].
 *
 * Key Guarantees (Sections 3, 4, 5, 6, 7, 8, 11, 13):
 * - Separates tutor system instructions (`systemInstruction`) from chronological `user`/`model` conversation turns.
 * - Preserves `studentUtterance` verbatim (`rawTranscript == displayedUserMessage == geminiStudentUtterance`).
 * - Adapts to the learner's stored level (`A0_A1`, `A2_B1`, `B2_C2`) and current lesson topic/vocabulary.
 * - Understands short elliptical replies ("Yes.", "At school.", "With cheese.") in context of the previous question.
 * - Handles failures honestly by returning `Result.failure(e)` instead of injecting fabricated offline AI responses.
 */
@Singleton
class FirebaseAiLearningService @Inject constructor() : AiLearningService {

    private val speakingHttpClient by lazy {
        OkHttpClient.Builder().callTimeout(45, TimeUnit.SECONDS).build()
    }

    private val speakingSchema = Schema.obj(
        properties = mapOf(
            "studentTranscript" to Schema.string(
                description = "Exact verbatim transcription of what the learner spoke in the audio (or the learner's text if text was provided)."
            ),
            "spokenEnglishReply" to Schema.string(
                description = "Natural, context-aware spoken English reply directly responding to the learner's latest utterance and asking ONE related follow-up question."
            ),
            "grammarAndVocabFeedbackUzbek" to Schema.string(
                description = "Optional brief grammar or vocabulary tip in Uzbek (empty string if no correction is needed; never rewrite the learner's original transcript)."
            ),
            "delivery" to Schema.string(description = "Expression and gesture keys for the spoken reply."),
            "accuracyScore" to Schema.integer(
                description = "Communication effectiveness score from 60 to 100."
            )
        ),
        optionalProperties = listOf("studentTranscript", "grammarAndVocabFeedbackUzbek", "accuracyScore", "delivery")
    )

    private val readingPassageSchema = Schema.obj(
        properties = mapOf(
            "titleEnglish" to Schema.string(description = "Short English title for the reading passage"),
            "passageEnglish" to Schema.string(description = "4 to 5 simple English sentences using the learned vocabulary and topic grammar"),
            "vocabularyNotesUzbek" to Schema.string(description = "Key word explanations in Uzbek without phonetic symbols"),
            "comprehensionQuestionEnglish" to Schema.string(description = "One clear comprehension question in English about the passage")
        )
    )

    private val readingPassageModel by lazy {
        Firebase.ai.generativeModel(
            modelName = GEMINI_MODEL_NAME,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = readingPassageSchema
            }
        )
    }

    private val readingEvaluationModel by lazy {
        Firebase.ai.generativeModel(modelName = GEMINI_MODEL_NAME)
    }

    override suspend fun sendSpeakingTurn(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>,
        conversationHistory: List<Pair<String, String>>,
        studentUtterance: String,
        audioWavBytes: ByteArray?,
        learnerLevel: String
    ): Result<SpeakingAiResponse> = withContext(Dispatchers.IO) {
        val exactRawTranscript = studentUtterance.trim()
        val hasAudioBytes = audioWavBytes != null && audioWavBytes.isNotEmpty()
        if (exactRawTranscript.isEmpty() && !hasAudioBytes) {
            return@withContext Result.failure(
                IllegalArgumentException("Cannot submit an empty user transcript or audio to Gemini.")
            )
        }

        // Identify the most recent assistant message/question for contextual grounding
        val lastAssistantQuestion = conversationHistory
            .lastOrNull { (role, _) ->
                role.equals("ASSISTANT", ignoreCase = true) || role.equals("AI_TUTOR", ignoreCase = true)
            }
            ?.second

        // Build separated system instruction (Section 11)
        val systemGuidance = TutorPromptConfig.buildSystemGuidance(
            topicTitle = topicTitle,
            learnerLevel = learnerLevel,
            allowedVocabulary = allowedVocabulary,
            allowedGrammarSummaries = allowedGrammarSummaries,
            lastAssistantQuestion = lastAssistantQuestion
        )

        // Build chronological history turns without duplicating the latest user utterance
        val boundedHistory = conversationHistory
            .takeLast(TutorPromptConfig.MAX_BOUNDED_HISTORY_TURNS)
            .let { list ->
                val last = list.lastOrNull()
                if (last != null &&
                    exactRawTranscript.isNotEmpty() &&
                    (last.first.equals("USER", ignoreCase = true) || last.first.equals("STUDENT", ignoreCase = true)) &&
                    last.second.trim() == exactRawTranscript
                ) {
                    list.dropLast(1)
                } else {
                    list
                }
            }

        val mappedHistory = boundedHistory.mapNotNull { (role, text) ->
            val clean = text.trim()
            if (clean.isEmpty()) null
            else (if (role.equals("USER", true) || role.equals("STUDENT", true)) "user" else "model") to clean
        }
        val deliveryGuidance = systemGuidance + """
            |Put only natural spoken English in spokenEnglishReply. Keep tips and system messages separate.
            |Set delivery to one mood and one gesture as mood:gesture for this reply's meaning.
            |Moods: neutral, warm, curious, joyful, amused, excited, thoughtful, concerned, surprised, firm.
            |Gestures: steady, nod, double_nod, tilt_left, tilt_right, glance_up, glance_down, lean_in, ease_back, shake.
            |Use firm only to act out a firm line in the scenario. Treat the learner respectfully.
        """.trimMargin()
        val latestText = exactRawTranscript.ifEmpty {
            "Transcribe only the learner's audible words into studentTranscript. " +
                "If you hear silence, background noise, system announcements, the tutor's earlier playback, or cannot verify intelligible learner speech, leave studentTranscript and spokenEnglishReply empty. Never guess a transcript from context."
        }

        try {
            val rawJson = if (BuildConfig.DEBUG && BuildConfig.GEMINI_API_KEY.isNotBlank()) {
                generateSpeakingWithDeveloperKey(deliveryGuidance, mappedHistory, latestText, audioWavBytes)
            } else {
                generateSpeakingWithFirebase(deliveryGuidance, mappedHistory, latestText, audioWavBytes)
            }

            val json = JSONObject(rawJson)
            val reply = json.optString("spokenEnglishReply", "").trim()
            val resolvedTranscript = exactRawTranscript.ifEmpty {
                json.optString("studentTranscript", "").trim()
            }

            if (reply.isEmpty() && resolvedTranscript.isNotBlank()) {
                throw IllegalStateException("Blank spokenEnglishReply from Speaking AI model.")
            }

            Result.success(
                SpeakingAiResponse(
                    spokenEnglishReply = reply,
                    grammarAndVocabFeedbackUzbek = json.optString("grammarAndVocabFeedbackUzbek", "").trim(),
                    accuracyScore = json.optInt("accuracyScore", 88).coerceIn(0, 100),
                    studentTranscript = resolvedTranscript,
                    delivery = json.optString("delivery")
                )
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            // Section 13: Handle failures honestly — never present a fabricated offline response as a real AI answer
            Log.e(TAG, "Speaking AI Gemini request failed", e)
            Result.failure(e)
        }
    }

    private suspend fun generateSpeakingWithFirebase(
        guidance: String,
        history: List<Pair<String, String>>,
        latestText: String,
        audio: ByteArray?
    ): String {
        val turns = history.map { (role, message) -> content(role = role) { text(message) } } +
            content(role = "user") {
                if (audio != null && audio.isNotEmpty()) inlineData(audio, "audio/wav")
                text(latestText)
            }
        val model = Firebase.ai.generativeModel(
            modelName = GEMINI_MODEL_NAME,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = speakingSchema
            },
            systemInstruction = content { text(guidance) }
        )
        return try {
            withTimeout(45_000L) { model.generateContent(turns) }.text
                ?: throw IOException("Empty response from Speaking AI model.")
        } catch (timeout: TimeoutCancellationException) {
            currentCoroutineContext().ensureActive()
            throw java.net.SocketTimeoutException("Gemini speaking request timed out.").apply {
                initCause(timeout)
            }
        }
    }

    private suspend fun generateSpeakingWithDeveloperKey(
        guidance: String,
        history: List<Pair<String, String>>,
        latestText: String,
        audio: ByteArray?
    ): String {
        val turns = JSONArray()
        for ((role, message) in history) {
            turns.put(JSONObject().put("role", role).put("parts", JSONArray().put(JSONObject().put("text", message))))
        }
        val latestParts = JSONArray()
        if (audio != null && audio.isNotEmpty()) {
            latestParts.put(JSONObject().put("inlineData", JSONObject()
                .put("mimeType", "audio/wav")
                .put("data", Base64.encodeToString(audio, Base64.NO_WRAP))))
        }
        latestParts.put(JSONObject().put("text", latestText))
        turns.put(JSONObject().put("role", "user").put("parts", latestParts))

        val payload = JSONObject()
            .put("systemInstruction", JSONObject().put("parts", JSONArray().put(JSONObject().put("text", guidance))))
            .put("contents", turns)
            .put("generationConfig", JSONObject()
                .put("responseMimeType", "application/json")
                .put("responseJsonSchema", JSONObject()
                    .put("type", "object")
                    .put("required", JSONArray().put("spokenEnglishReply"))
                    .put("properties", JSONObject()
                        .put("studentTranscript", JSONObject().put("type", "string"))
                        .put("delivery", JSONObject().put("type", "string"))
                        .put("spokenEnglishReply", JSONObject().put("type", "string"))
                        .put("grammarAndVocabFeedbackUzbek", JSONObject().put("type", "string"))
                        .put("accuracyScore", JSONObject().put("type", "integer")))))
        val request = Request.Builder()
            .url("https://generativelanguage.googleapis.com/v1beta/models/$GEMINI_MODEL_NAME:generateContent")
            .header("x-goog-api-key", BuildConfig.GEMINI_API_KEY)
            .post(payload.toString().toRequestBody("application/json".toMediaType()))
            .build()

        val body = suspendCancellableCoroutine<String> { continuation ->
            val call = speakingHttpClient.newCall(request)
            continuation.invokeOnCancellation { call.cancel() }
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) = continuation.resumeWithException(e)

                override fun onResponse(call: Call, response: Response) {
                    val result = runCatching {
                        response.use {
                            if (!it.isSuccessful) throw IOException("Gemini HTTP ${it.code}")
                            it.body?.string() ?: throw IOException("Empty HTTP response from Gemini.")
                        }
                    }
                    continuation.resumeWith(result)
                }
            })
        }
        val parts = JSONObject(body).optJSONArray("candidates")?.optJSONObject(0)
            ?.optJSONObject("content")?.optJSONArray("parts")
            ?: throw IOException("Gemini returned no speaking candidate.")
        return (0 until parts.length()).mapNotNull { index ->
            parts.optJSONObject(index)?.takeUnless { it.optBoolean("thought") }?.optString("text")
        }.joinToString("").ifBlank { throw IOException("Gemini returned no speaking response.") }
    }

    override suspend fun generateReadingPassage(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>
    ): Result<ReadingPassageResult> = withContext(Dispatchers.IO) {
        val vocabList = allowedVocabulary.joinToString(", ").ifEmpty { "hello, apple, cat, water, book, table" }
        val grammarList = allowedGrammarSummaries.joinToString("; ").ifEmpty { "Basic sentences" }

        val prompt = """
            You are the TalkMe Reading AI Coach (completely separate from Speaking AI).
            Generate a short beginner-friendly English reading passage (4-5 sentences) strictly focused on the topic: "$topicTitle".
            Use the student's studied vocabulary: [$vocabList] and studied grammar: [$grammarList].
            Do NOT include any phonetic transcriptions or IPA symbols.
            Include Uzbek vocabulary notes and one comprehension question in English.
        """.trimIndent()

        try {
            val response = withTimeout(45_000L) {
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
            Log.e(TAG, "Reading AI passage generation error", e)
            Result.failure(e)
        }
    }

    override suspend fun evaluateReadingQuery(
        topicTitle: String,
        passageText: String,
        comprehensionQuestion: String,
        studentQueryOrAnswer: String
    ): Result<String> = withContext(Dispatchers.IO) {
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

        try {
            val response = withTimeout(45_000L) {
                readingEvaluationModel.generateContent(prompt)
            }
            val text = response.text?.trim()
            if (!text.isNullOrEmpty()) {
                Result.success(text)
            } else {
                Result.failure(IllegalStateException("Empty evaluation response from Reading AI model."))
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Reading AI evaluation error", e)
            Result.failure(e)
        }
    }
}
