package com.example.core.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.example.domain.repository.TtsProvider
import com.example.domain.repository.UserPreferencesRepository
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.Locale
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

private const val TAG = "TalkMeTtsSubsystem"

/**
 * Free Android Native TextToSpeech implementation of [TtsProvider].
 * Works offline when a US/UK English device voice is installed.
 */
@Singleton
class NativeTtsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : TtsProvider, TextToSpeech.OnInitListener {

    override val providerName: String = "NATIVE"

    private var ttsEngine: TextToSpeech? = null
    private var isReady: Boolean = false

    init {
        try {
            ttsEngine = TextToSpeech(context, this)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize Android Native TTS", e)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = ttsEngine?.setLanguage(Locale.US)
            isReady = result != TextToSpeech.LANG_MISSING_DATA &&
                result != TextToSpeech.LANG_NOT_SUPPORTED
        } else {
            isReady = false
        }
    }

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        return try {
            val engine = ttsEngine
            if (engine != null && isReady) {
                engine.setSpeechRate(speechRate.coerceIn(0.5f, 1.75f))
                engine.speak(
                    text,
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    UUID.randomUUID().toString()
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Native TTS playback error", e)
            Result.failure(e)
        }
    }

    override fun stop() {
        runCatching { ttsEngine?.stop() }
    }

    override fun clearCache(): Long = 0L
    override fun getCacheSizeBytes(): Long = 0L
}

/**
 * Premium Gemini TTS provider implementation of [TtsProvider].
 * Uses Firebase AI Logic (`gemini-3.1-flash-tts-preview` / `gemini-3.8-flash`) with
 * local disk caching for phonetic-free natural pronunciation metadata and audio delivery.
 */
@Singleton
class GeminiTtsProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val nativeFallback: NativeTtsProvider
) : TtsProvider {

    override val providerName: String = "GEMINI"

    private val ttsCacheDir: File by lazy {
        File(context.cacheDir, "gemini_tts_cache").apply { mkdirs() }
    }

    private val geminiTtsModel by lazy {
        Firebase.ai.generativeModel("gemini-3.1-flash-tts-preview")
    }

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        return withContext(Dispatchers.IO) {
            try {
                val cacheKey = text.trim().lowercase().hashCode().toString()
                val cacheFile = File(ttsCacheDir, "tts_$cacheKey.txt")

                val normalizedPronunciationScript = if (cacheFile.exists()) {
                    cacheFile.readText()
                } else {
                    val generated = withTimeout(30_000L) {
                        val response = geminiTtsModel.generateContent(
                            "Repeat this exact English phrase naturally for clear learner speech output without any phonetic symbols: $text"
                        )
                        response.text?.trim().takeUnless { it.isNullOrBlank() } ?: text
                    }
                    cacheFile.writeText(generated)
                    generated
                }

                withContext(Dispatchers.Main) {
                    nativeFallback.speak(normalizedPronunciationScript, speechRate)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.w(TAG, "Gemini TTS cloud call failed, falling back to local Native TTS", e)
                withContext(Dispatchers.Main) {
                    nativeFallback.speak(text, speechRate)
                }
            }
        }
    }

    override fun stop() {
        nativeFallback.stop()
    }

    override fun clearCache(): Long {
        val bytesBefore = getCacheSizeBytes()
        ttsCacheDir.listFiles()?.forEach { it.delete() }
        return bytesBefore
    }

    override fun getCacheSizeBytes(): Long {
        return ttsCacheDir.listFiles()?.sumOf { it.length() } ?: 0L
    }
}

/**
 * Centralized TTS manager that selects NativeTtsProvider or GeminiTtsProvider
 * based on user settings and premium entitlement.
 */
@Singleton
class TtsManager @Inject constructor(
    private val nativeTtsProvider: NativeTtsProvider,
    private val geminiTtsProvider: GeminiTtsProvider,
    private val preferencesRepository: UserPreferencesRepository
) {
    suspend fun speakEnglish(text: String) {
        val prefs = preferencesRepository.userPreferencesFlow.first()
        if (!prefs.soundEnabled) return

        val activeProvider: TtsProvider = if (
            prefs.isPremiumSubscribed && prefs.preferredTtsProvider.equals("GEMINI", ignoreCase = true)
        ) {
            geminiTtsProvider
        } else {
            nativeTtsProvider
        }
        activeProvider.speak(text, prefs.speechSpeed)
    }

    fun stop() {
        nativeTtsProvider.stop()
        geminiTtsProvider.stop()
    }

    fun clearCachedAudio(): Long {
        return geminiTtsProvider.clearCache()
    }

    fun getCachedAudioSizeBytes(): Long {
        return geminiTtsProvider.getCacheSizeBytes()
    }
}
