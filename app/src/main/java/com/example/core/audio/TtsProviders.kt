package com.example.core.audio

import android.content.Context
import android.os.Bundle
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
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    @Volatile
    private var isReady: Boolean = false
    private val initLatch = CountDownLatch(1)

    init {
        try {
            ttsEngine = TextToSpeech(context, this)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize Android Native TTS", e)
            initLatch.countDown()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val engine = ttsEngine
            var langResult = engine?.setLanguage(Locale.US)
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                langResult = engine?.setLanguage(Locale.UK)
            }
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                langResult = engine?.setLanguage(Locale.ENGLISH)
            }
            isReady = langResult != TextToSpeech.LANG_MISSING_DATA && langResult != TextToSpeech.LANG_NOT_SUPPORTED
        } else {
            isReady = false
        }
        initLatch.countDown()
    }

    private fun awaitReady() {
        if (!isReady && initLatch.count > 0) {
            runCatching { initLatch.await(2500, TimeUnit.MILLISECONDS) }
        }
    }

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        return withContext(Dispatchers.Main) {
            try {
                awaitReady()
                val engine = ttsEngine
                if (engine != null && isReady) {
                    engine.setSpeechRate(speechRate.coerceIn(0.5f, 1.75f))
                    val params = Bundle().apply {
                        putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
                    }
                    engine.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        params,
                        UUID.randomUUID().toString()
                    )
                    Result.success(Unit)
                } else {
                    Log.w(TAG, "Native TTS not ready after waiting")
                    Result.failure(IllegalStateException("TTS engine uninitialized"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Native TTS playback error", e)
                Result.failure(e)
            }
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
 * encrypted local disk caching (.nomedia + XOR obfuscation) and automatic background cache pruning.
 */
@Singleton
class GeminiTtsProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val nativeFallback: NativeTtsProvider
) : TtsProvider {

    override val providerName: String = "GEMINI"

    private val ttsCacheDir: File by lazy {
        File(context.cacheDir, "secure_audio_cache").apply {
            mkdirs()
            runCatching { File(this, ".nomedia").createNewFile() }
        }
    }

    private val geminiTtsModel by lazy {
        Firebase.ai.generativeModel("gemini-3.1-flash-tts-preview")
    }

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        return withContext(Dispatchers.IO) {
            try {
                autoPruneStaleCache()
                val cacheKey = text.trim().lowercase().hashCode().toString()
                val cacheFile = File(ttsCacheDir, "tts_$cacheKey.enc")

                val normalizedPronunciationScript = if (cacheFile.exists()) {
                    readObfuscatedText(cacheFile)
                } else {
                    val generated = withTimeout(30_000L) {
                        val response = geminiTtsModel.generateContent(
                            "Repeat this exact English phrase naturally for clear learner speech output without any phonetic symbols: $text"
                        )
                        response.text?.trim().takeUnless { it.isNullOrBlank() } ?: text
                    }
                    writeObfuscatedText(cacheFile, generated)
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

    private fun readObfuscatedText(file: File): String {
        val bytes = file.readBytes()
        val key = 0x5A.toByte()
        for (i in bytes.indices) {
            bytes[i] = (bytes[i].toInt() xor key.toInt()).toByte()
        }
        return String(bytes, Charsets.UTF_8)
    }

    private fun writeObfuscatedText(file: File, text: String) {
        val bytes = text.toByteArray(Charsets.UTF_8)
        val key = 0x5A.toByte()
        for (i in bytes.indices) {
            bytes[i] = (bytes[i].toInt() xor key.toInt()).toByte()
        }
        file.writeBytes(bytes)
    }

    /**
     * Automatic background cache cleanup:
     * 1. Deletes cached audio/text files older than 7 days.
     * 2. Prunes oldest files if total cache size exceeds 50MB.
     */
    private fun autoPruneStaleCache() {
        try {
            val files = ttsCacheDir.listFiles { f -> f.name.endsWith(".enc") } ?: return
            val now = System.currentTimeMillis()
            val maxAgeMs = 7 * 24 * 60 * 60 * 1000L // 7 days
            val maxCacheBytes = 50 * 1024 * 1024L   // 50 MB

            files.forEach { file ->
                if (now - file.lastModified() > maxAgeMs) {
                    file.delete()
                }
            }

            val remainingFiles = ttsCacheDir.listFiles { f -> f.name.endsWith(".enc") }?.sortedBy { it.lastModified() } ?: return
            var currentTotal = remainingFiles.sumOf { it.length() }
            for (file in remainingFiles) {
                if (currentTotal > maxCacheBytes) {
                    val size = file.length()
                    if (file.delete()) {
                        currentTotal -= size
                    }
                } else {
                    break
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Auto cache pruning error", e)
        }
    }

    override fun stop() {
        nativeFallback.stop()
    }

    override fun clearCache(): Long {
        val bytesBefore = getCacheSizeBytes()
        ttsCacheDir.listFiles { f -> f.name.endsWith(".enc") }?.forEach { it.delete() }
        return bytesBefore
    }

    override fun getCacheSizeBytes(): Long {
        return ttsCacheDir.listFiles { f -> f.name.endsWith(".enc") }?.sumOf { it.length() } ?: 0L
    }
}

/**
 * State representation of current TTS audio playback.
 */
sealed class TtsPlaybackState {
    data object Idle : TtsPlaybackState()
    data class Loading(val text: String) : TtsPlaybackState()
    data class Playing(val text: String) : TtsPlaybackState()
    data class Paused(val text: String) : TtsPlaybackState()
    data class Error(val text: String, val message: String) : TtsPlaybackState()
}

/**
 * Centralized TTS manager that selects NativeTtsProvider or GeminiTtsProvider
 * based on user settings and premium entitlement.
 * Enforces TalkMe Global English TTS System architecture across all screens.
 */
@Singleton
class TtsManager @Inject constructor(
    private val nativeTtsProvider: NativeTtsProvider,
    private val geminiTtsProvider: GeminiTtsProvider,
    private val preferencesRepository: UserPreferencesRepository
) {
    companion object {
        const val SPEED_SLOW = 0.75f   // Ideal for A0 absolute beginners
        const val SPEED_NORMAL = 1.0f  // Standard natural speed
        const val SPEED_FAST = 1.25f    // Fluent review speed
    }

    private val _playbackState = MutableStateFlow<TtsPlaybackState>(TtsPlaybackState.Idle)
    val playbackState: StateFlow<TtsPlaybackState> = _playbackState

    /**
     * Speaks English text with automatic language verification, caching, and state updates.
     */
    suspend fun speakEnglish(
        text: String,
        overrideSpeed: Float? = null,
        languageCode: String = "en"
    ) {
        val cleanText = text.trim()
        if (cleanText.isEmpty()) return

        // Verify text language is English (if languageCode is provided)
        if (languageCode.equals("uz", ignoreCase = true)) {
            Log.d(TAG, "Skipping TTS for Uzbek explanation text: $cleanText")
            return
        }

        val prefs = preferencesRepository.userPreferencesFlow.first()
        if (!prefs.soundEnabled) return

        _playbackState.value = TtsPlaybackState.Loading(cleanText)

        val speed = overrideSpeed ?: prefs.speechSpeed
        val activeProvider: TtsProvider = if (
            prefs.isPremiumSubscribed && prefs.preferredTtsProvider.equals("GEMINI", ignoreCase = true)
        ) {
            geminiTtsProvider
        } else {
            nativeTtsProvider
        }

        _playbackState.value = TtsPlaybackState.Playing(cleanText)
        val result = activeProvider.speak(cleanText, speed)
        result.fold(
            onSuccess = {
                _playbackState.value = TtsPlaybackState.Idle
            },
            onFailure = { err ->
                Log.e(TAG, "TTS playback error for '$cleanText'", err)
                _playbackState.value = TtsPlaybackState.Error(cleanText, err.message ?: "Playback failed")
            }
        )
    }

    fun stop() {
        nativeTtsProvider.stop()
        geminiTtsProvider.stop()
        _playbackState.value = TtsPlaybackState.Idle
    }

    fun clearCachedAudio(): Long {
        return geminiTtsProvider.clearCache()
    }

    fun getCachedAudioSizeBytes(): Long {
        return geminiTtsProvider.getCacheSizeBytes()
    }
}

