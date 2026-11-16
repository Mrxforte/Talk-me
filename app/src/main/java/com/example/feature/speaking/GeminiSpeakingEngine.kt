package com.example.feature.speaking

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Content
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import java.io.File
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

private const val TAG = "GeminiSpeakingEngine"
private const val GEMINI_SPEAKING_MODEL = "gemini-3.8-flash"
private const val MAX_INLINE_AUDIO_BYTES = 15 * 1024 * 1024
private const val INFERENCE_TIMEOUT_MS = 60_000L

data class SpeakingTurn(
    val transcript: String,
    val reply: String,
    val correction: String?,
    val explanation: String?
)

/** Audio is held only in cache and removed after reading bytes or cancellation. */
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

    fun finish(): ByteArray {
        val r = recorder ?: error("Recording is not active")
        val file = output ?: error("Audio file is missing")
        recorder = null
        output = null
        try {
            r.stop()
            val bytes = file.readBytes()
            require(bytes.size > 1024) { "Ovoz yozuvi juda qisqa" }
            require(bytes.size <= MAX_INLINE_AUDIO_BYTES) { "Ovoz yozuvi hajmi juda katta" }
            return bytes
        } finally {
            r.release()
            file.delete()
        }
    }

    fun cancel() {
        val r = recorder
        recorder = null
        try {
            r?.stop()
        } catch (_: RuntimeException) {
        }
        r?.release()
        output?.delete()
        output = null
    }
}

class GeminiSpeakingEngine {
    private val history = mutableListOf<Content>()
    private var cachedSessionKey: String? = null
    private var cachedModel: GenerativeModel? = null

    private val speakingTurnSchema = Schema.obj(
        properties = mapOf(
            "user_transcript" to Schema.string(
                description = "Accurate verbatim English transcription of the student's spoken audio."
            ),
            "ai_reply" to Schema.string(
                description = "Natural conversational reply matched strictly to the selected CEFR level, ending with one engaging follow-up question."
            ),
            "grammar_correction" to Schema.string(
                description = "Corrected English sentence if there is a clear grammar or vocabulary mistake, otherwise null.",
                nullable = true
            ),
            "explanation_uz" to Schema.string(
                description = "Short, clear explanation in Uzbek (Latin script) if a correction was provided, otherwise null.",
                nullable = true
            )
        ),
        optionalProperties = listOf("grammar_correction", "explanation_uz")
    )

    private fun getOrCreateModel(level: String, topic: String): GenerativeModel {
        val key = "$level|$topic"
        val existing = cachedModel
        if (existing != null && cachedSessionKey == key) {
            return existing
        }

        val instruction = """
            You are a strict, encouraging English speaking tutor. Stay on topic: $topic.
            CEFR level: $level.
            - A1: simple 5-7 word sentences
            - A2: simple everyday English
            - B1: intermediate conversational English
            - B2: fluent detailed English
            - C1: nuanced advanced English
            - C2: natural advanced idioms
            Match ONLY the chosen level.
            Accurately transcribe actual English speech from the audio; never invent words.
            Correct mistakes only when necessary and briefly explain them in Uzbek (Latin script).
            Always end ai_reply with one engaging follow-up question.
        """.trimIndent()

        val created = Firebase.ai.generativeModel(
            modelName = GEMINI_SPEAKING_MODEL,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = speakingTurnSchema
            },
            systemInstruction = content {
                text(instruction)
            }
        )
        cachedSessionKey = key
        cachedModel = created
        return created
    }

    suspend fun reply(audioBytes: ByteArray, level: String, topic: String): SpeakingTurn =
        withContext(Dispatchers.IO) {
            require(audioBytes.size <= MAX_INLINE_AUDIO_BYTES) { "Audio exceeds 15 MB limit" }

            val latestUserTurn = content(role = "user") {
                inlineData(audioBytes, "audio/mp4")
                text("Transcribe this English recording accurately and continue the $topic conversation.")
            }

            val requestContents = history.takeLast(12) + latestUserTurn

            try {
                val model = getOrCreateModel(level = level, topic = topic)
                val response = withTimeout(INFERENCE_TIMEOUT_MS) {
                    model.generateContent(requestContents)
                }

                val rawJson = response.text?.trim()
                if (rawJson.isNullOrEmpty()) {
                    throw IllegalStateException("Gemini bo‘sh javob qaytardi")
                }

                val json = JSONObject(rawJson)
                val transcript = json.optString("user_transcript", "").trim()
                val answer = json.optString("ai_reply", "").trim()
                if (transcript.isEmpty() || answer.isEmpty()) {
                    throw IllegalStateException("Gemini javobi noto‘g‘ri formatda qaytdi")
                }

                val correction = json.optNullableNonBlank("grammar_correction")
                val explanation = json.optNullableNonBlank("explanation_uz")

                history += content(role = "user") { text(transcript) }
                history += content(role = "model") { text(answer) }

                SpeakingTurn(
                    transcript = transcript,
                    reply = answer,
                    correction = correction,
                    explanation = explanation
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Gemini 3.8 speaking request failed", e)
                throw e
            }
        }

    private fun JSONObject.optNullableNonBlank(key: String): String? {
        if (!has(key) || isNull(key)) return null
        return optString(key, "").trim().takeIf {
            it.isNotEmpty() && !it.equals("null", ignoreCase = true)
        }
    }
}
