package com.example.feature.speaking

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.util.concurrent.TimeUnit

data class SpeakingTurn(val transcript: String, val reply: String, val correction: String?, val explanation: String?)
private data class ApiResponse(val candidates: List<Candidate>?)
private data class Candidate(val content: Content?)
private data class Content(val parts: List<Part>?)
private data class Part(val text: String?)
private data class TutorJson(
    @SerializedName("user_transcript") val transcript: String?,
    @SerializedName("ai_reply") val reply: String?,
    @SerializedName("grammar_correction") val correction: String?,
    @SerializedName("explanation_uz") val explanation: String?
)

/** Audio is held only in cache and removed after encoding or cancellation. */
class SpeakingRecorder(private val context: Context) {
    private var recorder: MediaRecorder? = null
    private var output: File? = null

    fun start() {
        check(recorder == null)
        val target = File.createTempFile("talkme_speaking_", ".m4a", context.cacheDir)
        var current: MediaRecorder? = null
        try {
            @Suppress("DEPRECATION")
            val r = if (Build.VERSION.SDK_INT >= 31) MediaRecorder(context) else MediaRecorder()
            current = r
            r.setAudioSource(MediaRecorder.AudioSource.MIC)
            r.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            r.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            r.setAudioSamplingRate(16000)
            r.setAudioEncodingBitRate(64000)
            r.setOutputFile(target.absolutePath)
            r.prepare()
            r.start()
            recorder = r
            output = target
        } catch (error: Exception) {
            current?.release()
            target.delete()
            throw error
        }
    }

    fun finish(): String {
        val r = recorder ?: error("Recording is not active")
        val file = output ?: error("Audio file is missing")
        recorder = null
        output = null
        try {
            r.stop()
            val bytes = file.readBytes()
            require(bytes.size > 1024) { "Recording is too short" }
            return Base64.encodeToString(bytes, Base64.NO_WRAP)
        } finally {
            r.release()
            file.delete()
        }
    }

    fun cancel() {
        val r = recorder
        recorder = null
        try { r?.stop() } catch (_: RuntimeException) {}
        r?.release()
        output?.delete()
        output = null
    }
}

class GeminiSpeakingEngine {
    private val gson = Gson()
    private val client = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .build()
    private val history = mutableListOf<Map<String, Any>>()

    suspend fun reply(audioBase64: String, level: String, topic: String, key: String): SpeakingTurn =
        withContext(Dispatchers.IO) {
            val instruction = """
                You are a strict English speaking tutor. Stay on topic: $topic.
                CEFR level: $level. A1: simple 5-7 word sentences; A2: simple everyday English;
                B1: intermediate; B2: fluent detailed English; C1: nuanced advanced English;
                C2: advanced idioms. Match ONLY the chosen level.
                Accurately transcribe actual English speech; never invent words.
                Correct mistakes only when necessary and briefly explain them in Uzbek.
                Always end ai_reply with one engaging follow-up question.
                Return only JSON with user_transcript, ai_reply, grammar_correction, explanation_uz.
            """.trimIndent()
            val schema = mapOf(
                "type" to "OBJECT",
                "properties" to mapOf(
                    "user_transcript" to mapOf("type" to "STRING"),
                    "ai_reply" to mapOf("type" to "STRING"),
                    "grammar_correction" to mapOf("type" to "STRING", "nullable" to true),
                    "explanation_uz" to mapOf("type" to "STRING", "nullable" to true)
                ),
                "required" to listOf("user_transcript", "ai_reply", "grammar_correction", "explanation_uz")
            )
            val latest = mapOf("role" to "user", "parts" to listOf(
                mapOf("text" to "Transcribe this English recording and continue the $topic conversation."),
                mapOf("inlineData" to mapOf("mimeType" to "audio/mp4", "data" to audioBase64))
            ))
            val payload = mapOf(
                "systemInstruction" to mapOf("parts" to listOf(mapOf("text" to instruction))),
                "contents" to (history.takeLast(12) + latest),
                "generationConfig" to mapOf("responseMimeType" to "application/json", "responseSchema" to schema)
            )
            val request = Request.Builder()
                .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent")
                .header("x-goog-api-key", key)
                .post(gson.toJson(payload).toRequestBody("application/json".toMediaType()))
                .build()
            val response = client.newCall(request).execute()
            response.use {
                val body = it.body?.string().orEmpty()
                check(it.isSuccessful) { "Gemini HTTP ${it.code}: ${body.take(180)}" }
                val json = gson.fromJson(body, ApiResponse::class.java)
                    .candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                    ?: error("Gemini returned no response")
                val parsed = gson.fromJson(json, TutorJson::class.java)
                val transcript = parsed.transcript?.trim().orEmpty()
                val answer = parsed.reply?.trim().orEmpty()
                require(transcript.isNotEmpty() && answer.isNotEmpty()) { "Invalid Gemini response" }
                history += mapOf("role" to "user", "parts" to listOf(mapOf("text" to transcript)))
                history += mapOf("role" to "model", "parts" to listOf(mapOf("text" to answer)))
                SpeakingTurn(transcript, answer, parsed.correction, parsed.explanation)
            }
        }
}
