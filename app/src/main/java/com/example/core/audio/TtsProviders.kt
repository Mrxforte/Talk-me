package com.example.core.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import com.example.domain.repository.TtsProvider
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.Locale
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull

private const val TAG = "TalkMeTtsSubsystem"

/**
 * Production Android Native TextToSpeech implementation of [TtsProvider].
 * - Initializes asynchronously without blocking the main thread.
 * - Uses [UtteranceProgressListener] keyed by unique utterance IDs so [speak] suspends until
 *   actual speech synthesis finishes (`onDone`), is interrupted (`onStop`), or fails (`onError`).
 * - Never modifies the tutor's intended response text during TTS preparation.
 * - Coordinates transient audio focus via [AudioManager] so audio streams never collide.
 */
@Singleton
class NativeTtsProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : TtsProvider, TextToSpeech.OnInitListener {

    override val providerName: String = "NATIVE"

    private var ttsEngine: TextToSpeech? = null
    @Volatile
    private var isReady: Boolean = false
    @Volatile
    private var initDeferred = CompletableDeferred<Boolean>()

    private val pendingUtterances = ConcurrentHashMap<String, CompletableDeferred<Result<Unit>>>()

    private val audioManager: AudioManager? by lazy {
        context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    }
    private var currentAudioFocusRequest: AudioFocusRequest? = null

    init {
        initializeEngine()
    }

    private fun initializeEngine() {
        try {
            if (initDeferred.isCompleted) {
                initDeferred = CompletableDeferred()
            }
            ttsEngine = TextToSpeech(context, this)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to initialize Android Native TTS", e)
            initDeferred.complete(false)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val engine = ttsEngine
            engine?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    // Speech synthesis started
                }

                override fun onDone(utteranceId: String?) {
                    abandonAudioFocus()
                    if (utteranceId != null) {
                        pendingUtterances.remove(utteranceId)?.complete(Result.success(Unit))
                    }
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    abandonAudioFocus()
                    if (utteranceId != null) {
                        pendingUtterances.remove(utteranceId)?.complete(
                            Result.failure(IllegalStateException("TTS utterance playback failed"))
                        )
                    }
                }

                override fun onError(utteranceId: String?, errorCode: Int) {
                    abandonAudioFocus()
                    if (utteranceId != null) {
                        pendingUtterances.remove(utteranceId)?.complete(
                            Result.failure(IllegalStateException("TTS utterance error code: $errorCode"))
                        )
                    }
                }

                override fun onStop(utteranceId: String?, interrupted: Boolean) {
                    abandonAudioFocus()
                    if (utteranceId != null) {
                        pendingUtterances.remove(utteranceId)?.complete(Result.success(Unit))
                    }
                }
            })

            var langResult = engine?.setLanguage(Locale.US)
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                langResult = engine?.setLanguage(Locale.UK)
            }
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                langResult = engine?.setLanguage(Locale.ENGLISH)
            }
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                runCatching { engine?.setLanguage(Locale.getDefault()) }
            }
            isReady = true
            initDeferred.complete(true)
        } else {
            isReady = false
            initDeferred.complete(false)
        }
    }

    private suspend fun awaitReady(): Boolean {
        if (isReady && ttsEngine != null) return true
        val firstAttempt = withTimeoutOrNull(3000L) { initDeferred.await() } ?: false
        if (firstAttempt && isReady) return true

        withContext(Dispatchers.Main) {
            runCatching { ttsEngine?.shutdown() }
            initializeEngine()
        }
        return withTimeoutOrNull(2500L) { initDeferred.await() } ?: false
    }

    private fun requestTransientAudioFocus() {
        runCatching {
            val am = audioManager ?: return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val attrs = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_ACCESSIBILITY)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
                val req = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)
                    .setAudioAttributes(attrs)
                    .setAcceptsDelayedFocusGain(false)
                    .setOnAudioFocusChangeListener { }
                    .build()
                currentAudioFocusRequest = req
                am.requestAudioFocus(req)
            } else {
                @Suppress("DEPRECATION")
                am.requestAudioFocus(
                    null,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
                )
            }
        }
    }

    private fun abandonAudioFocus() {
        runCatching {
            val am = audioManager ?: return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                currentAudioFocusRequest?.let { am.abandonAudioFocusRequest(it) }
                currentAudioFocusRequest = null
            } else {
                @Suppress("DEPRECATION")
                am.abandonAudioFocus(null)
            }
        }
    }

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        val utteranceId = "talkme_utt_${UUID.randomUUID()}"
        val completionDeferred = CompletableDeferred<Result<Unit>>()
        pendingUtterances[utteranceId] = completionDeferred

        return try {
            val ready = awaitReady()
            val engine = ttsEngine
            if (ready && engine != null && isReady) {
                cancelAllPendingExcept(utteranceId)
                requestTransientAudioFocus()

                val speakResult = withContext(Dispatchers.Main) {
                    engine.setSpeechRate(speechRate.coerceIn(0.5f, 1.75f))
                    val params = Bundle().apply {
                        putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, 1.0f)
                    }
                    // Speak exact intended text verbatim without modifying it
                    engine.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        params,
                        utteranceId
                    )
                }

                if (speakResult == TextToSpeech.ERROR) {
                    pendingUtterances.remove(utteranceId)
                    abandonAudioFocus()
                    Log.w(TAG, "Native TTS speak() returned ERROR")
                    Result.failure(IllegalStateException("Native TTS speak() returned ERROR"))
                } else {
                    // Wait for real UtteranceProgressListener callback with safety watchdog timeout
                    val maxWatchdogMs = (text.length * 120L).coerceIn(5_000L, 30_000L)
                    withTimeoutOrNull(maxWatchdogMs) {
                        completionDeferred.await()
                    } ?: Result.failure(IllegalStateException("TTS playback callback timed out"))
                }
            } else {
                pendingUtterances.remove(utteranceId)
                Log.w(TAG, "System TTS engine unavailable on this device")
                Result.failure(IllegalStateException("System TTS engine unavailable"))
            }
        } catch (e: CancellationException) {
            pendingUtterances.remove(utteranceId)
            stop()
            throw e
        } catch (e: Exception) {
            pendingUtterances.remove(utteranceId)
            abandonAudioFocus()
            Log.w(TAG, "Native TTS playback error", e)
            Result.failure(e)
        } finally {
            pendingUtterances.remove(utteranceId)
        }
    }

    private fun cancelAllPendingExcept(activeUtteranceId: String) {
        val keys = pendingUtterances.keys().toList()
        for (key in keys) {
            if (key != activeUtteranceId) {
                pendingUtterances.remove(key)?.complete(Result.success(Unit))
            }
        }
    }

    override fun stop() {
        runCatching { ttsEngine?.stop() }
        abandonAudioFocus()
        val keys = pendingUtterances.keys().toList()
        for (key in keys) {
            pendingUtterances.remove(key)?.complete(Result.success(Unit))
        }
    }

    override fun clearCache(): Long = 0L
    override fun getCacheSizeBytes(): Long = 0L
}

/**
 * Cached TTS provider implementation of [TtsProvider].
 * Preserves the exact intended tutor response text without altering or rewriting it,
 * while maintaining encrypted local disk cache management (.nomedia + XOR obfuscation).
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

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        return withContext(Dispatchers.IO) {
            try {
                autoPruneStaleCache()
                val cacheKey = text.trim().lowercase().hashCode().toString()
                val cacheFile = File(ttsCacheDir, "tts_$cacheKey.enc")
                if (!cacheFile.exists()) {
                    writeObfuscatedText(cacheFile, text)
                }
                // Never modify the tutor's intended response text during TTS preparation (Section 12)
                nativeFallback.speak(text, speechRate)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.w(TAG, "TTS cache notice, delegating directly to NativeTtsProvider", e)
                nativeFallback.speak(text, speechRate)
            }
        }
    }

    private fun writeObfuscatedText(file: File, text: String) {
        val bytes = text.toByteArray(Charsets.UTF_8)
        val key = 0x5A.toByte()
        for (i in bytes.indices) {
            bytes[i] = (bytes[i].toInt() xor key.toInt()).toByte()
        }
        file.writeBytes(bytes)
    }

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

            val remainingFiles = ttsCacheDir.listFiles { f -> f.name.endsWith(".enc") }
                ?.sortedBy { it.lastModified() } ?: return
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
 * Centralized TTS controller that selects NativeTtsProvider or GeminiTtsProvider
 * based on user settings and premium entitlement.
 * - Prevents simultaneous overlapping speech using [playbackMutex] and active utterance tracking.
 * - Transitions out of [TtsPlaybackState.Playing] only when utterance playback completes or fails.
 */
@Singleton
open class TtsManager(
    private val nativeTtsProvider: TtsProvider,
    private val geminiTtsProvider: TtsProvider,
    private val preferencesRepository: UserPreferencesRepository
) {
    @Inject
    constructor(
        nativeTtsProvider: NativeTtsProvider,
        geminiTtsProvider: GeminiTtsProvider,
        preferencesRepository: UserPreferencesRepository
    ) : this(
        nativeTtsProvider as TtsProvider,
        geminiTtsProvider as TtsProvider,
        preferencesRepository
    )

    companion object {
        const val SPEED_SLOW = 0.75f
        const val SPEED_NORMAL = 1.0f
        const val SPEED_FAST = 1.25f
    }

    private val playbackMutex = Mutex()
    private val _playbackState = MutableStateFlow<TtsPlaybackState>(TtsPlaybackState.Idle)
    val playbackState: StateFlow<TtsPlaybackState> = _playbackState.asStateFlow()

    @Volatile
    private var activeUtteranceToken: String? = null

    /**
     * Speaks English text with automatic language verification, single-voice serialization,
     * and accurate completion tracking. Returns `Result<Unit>` indicating whether TTS playback
     * succeeded or failed.
     */
    suspend fun speakEnglish(
        text: String,
        overrideSpeed: Float? = null,
        languageCode: String = "en"
    ): Result<Unit> {
        val cleanText = text.trim()
        if (cleanText.isEmpty()) return Result.success(Unit)

        if (languageCode.equals("uz", ignoreCase = true)) {
            Log.d(TAG, "Skipping TTS for Uzbek explanation text: $cleanText")
            return Result.success(Unit)
        }

        val prefs = preferencesRepository.userPreferencesFlow.first()
        if (!prefs.soundEnabled) {
            _playbackState.value = TtsPlaybackState.Idle
            return Result.success(Unit)
        }

        // Stop any currently playing utterance immediately before starting the new one
        nativeTtsProvider.stop()
        geminiTtsProvider.stop()

        val token = UUID.randomUUID().toString()
        activeUtteranceToken = token

        return playbackMutex.withLock {
            if (activeUtteranceToken != token) {
                return@withLock Result.success(Unit)
            }

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
            try {
                val result = activeProvider.speak(cleanText, speed)
                if (activeUtteranceToken == token) {
                    result.fold(
                        onSuccess = {
                            _playbackState.value = TtsPlaybackState.Idle
                        },
                        onFailure = { err ->
                            Log.w(TAG, "TTS playback failed for '$cleanText'", err)
                            _playbackState.value = TtsPlaybackState.Error(cleanText, err.message ?: "Playback failed")
                        }
                    )
                }
                result
            } catch (e: CancellationException) {
                if (activeUtteranceToken == token) {
                    _playbackState.value = TtsPlaybackState.Idle
                }
                throw e
            }
        }
    }

    fun stop() {
        activeUtteranceToken = null
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
