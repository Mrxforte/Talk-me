package com.example.core.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.PlaybackParams
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import com.example.domain.repository.TtsProvider
import com.example.domain.repository.UserPreferencesRepository
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.InlineDataPart
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.SpeechConfig
import com.google.firebase.ai.type.Voice
import com.google.firebase.ai.type.generationConfig
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.isActive
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
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
    private var preferredPitch: Float = 1.0f
    @Volatile
    private var preferredGender: String = "MALE"
    @Volatile
    private var initDeferred = CompletableDeferred<Boolean>()

    fun applyVoiceGender(voiceGender: String) {
        val isFemale = voiceGender.equals("FEMALE", ignoreCase = true)
        preferredGender = if (isFemale) "FEMALE" else "MALE"
        preferredPitch = if (isFemale) 1.08f else 0.92f
    }

    private val pendingUtterances = ConcurrentHashMap<String, CompletableDeferred<Result<Unit>>>()
    private val speechCues = ConcurrentHashMap<String, (Int, Int) -> Unit>()

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
                    utteranceId?.let { speechCues[it]?.invoke(-1, -1) }
                }

                override fun onRangeStart(utteranceId: String?, start: Int, end: Int, frame: Int) {
                    utteranceId?.let { speechCues[it]?.invoke(start, end) }
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

            applyEngineLanguage(engine, "en")
            isReady = true
            initDeferred.complete(true)
        } else {
            isReady = false
            initDeferred.complete(false)
        }
    }

    private fun applyEngineLanguage(engine: TextToSpeech?, languageCode: String) {
        if (engine == null) return
        if (languageCode.equals("uz", ignoreCase = true)) {
            var uzResult = engine.setLanguage(Locale("uz", "UZ"))
            if (uzResult == TextToSpeech.LANG_MISSING_DATA || uzResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                uzResult = engine.setLanguage(Locale("uz"))
            }
            if (uzResult == TextToSpeech.LANG_MISSING_DATA || uzResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w(TAG, "System TTS engine lacks dedicated uz-UZ voice; using default engine voice")
                runCatching { engine.setLanguage(Locale.getDefault()) }
            }
            return
        }
        var langResult = engine.setLanguage(Locale.US)
        if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
            langResult = engine.setLanguage(Locale.UK)
        }
        if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
            langResult = engine.setLanguage(Locale.ENGLISH)
        }
        if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
            runCatching { engine.setLanguage(Locale.getDefault()) }
        }
        selectSystemVoiceForGender(engine, languageCode, preferredGender)
    }

    private fun selectSystemVoiceForGender(
        engine: TextToSpeech,
        languageCode: String,
        gender: String
    ) {
        runCatching {
            val availableVoices = engine.voices.orEmpty()
            if (availableVoices.isEmpty()) return
            val targetLangPrefix = if (languageCode.equals("uz", ignoreCase = true)) "uz" else "en"
            val localeVoices = availableVoices.filter {
                it.locale.language.equals(targetLangPrefix, ignoreCase = true) && !it.isNetworkConnectionRequired
            }.ifEmpty {
                availableVoices.filter { it.locale.language.equals(targetLangPrefix, ignoreCase = true) }
            }
            if (localeVoices.isEmpty()) return

            val wantFemale = gender.equals("FEMALE", ignoreCase = true)
            val matched = localeVoices.firstOrNull { voice ->
                val name = voice.name.lowercase(Locale.US)
                val feats = voice.features.orEmpty().joinToString(" ").lowercase(Locale.US)
                if (wantFemale) {
                    name.contains("female") || feats.contains("female") ||
                        name.contains("-f-") || name.contains("sfg") ||
                        name.contains("tpf") || name.contains("tpc") || name.contains("iob")
                } else {
                    name.contains("male") || feats.contains("male") ||
                        name.contains("-m-") || name.contains("iol") ||
                        name.contains("iom") || name.contains("tpd")
                }
            }
            if (matched != null) {
                engine.voice = matched
            }
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

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> =
        speakWithLanguage(text, speechRate, languageCode = "en")

    override suspend fun speakWithLanguage(
        text: String,
        speechRate: Float,
        languageCode: String
    ): Result<Unit> = speakWithCues(text, speechRate, languageCode, null)

    suspend fun speakWithCues(
        text: String,
        speechRate: Float,
        languageCode: String,
        onSpeechCue: ((Int, Int) -> Unit)?
    ): Result<Unit> {
        if (text.isBlank()) return Result.success(Unit)
        val utteranceId = "talkme_utt_${UUID.randomUUID()}"
        val completionDeferred = CompletableDeferred<Result<Unit>>()
        pendingUtterances[utteranceId] = completionDeferred
        onSpeechCue?.let { speechCues[utteranceId] = it }

        return try {
            val ready = awaitReady()
            val engine = ttsEngine
            if (ready && engine != null && isReady) {
                cancelAllPendingExcept(utteranceId)
                requestTransientAudioFocus()

                val speakResult = withContext(Dispatchers.Main) {
                    applyEngineLanguage(engine, languageCode)
                    engine.setSpeechRate(speechRate.coerceIn(0.5f, 1.75f))
                    engine.setPitch(preferredPitch)
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
            speechCues.remove(utteranceId)
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
 * Firebase AI Logic TTS provider using `gemini-3.1-flash-tts-preview`.
 * - Male voice default: `"Orus"` (`voiceGender = "MALE"`).
 * - Female voice option: `"Kore"` (`voiceGender = "FEMALE"`).
 * - Generates 24kHz 16-bit mono PCM audio and plays it via [AudioTrack] with adjustable speech rate
 *   and instant cancellation via [stop].
 * - Caches synthesized PCM audio in obfuscated local storage (`secure_audio_cache/.nomedia`)
 *   and falls back cleanly to [NativeTtsProvider] only if cloud generation fails.
 */
@Singleton
class GeminiTtsProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val nativeFallback: NativeTtsProvider
) : TtsProvider {

    companion object {
        private const val GEMINI_TTS_MODEL = "gemini-3.1-flash-tts-preview"
        const val GEMINI_VOICE_MALE = "Orus"
        const val GEMINI_VOICE_FEMALE = "Kore"
        private const val PCM_SAMPLE_RATE_HZ = 24_000

        fun resolveVoiceName(voiceGender: String): String =
            if (voiceGender.equals("FEMALE", ignoreCase = true)) GEMINI_VOICE_FEMALE else GEMINI_VOICE_MALE
    }

    override val providerName: String = "GEMINI"

    @Volatile
    private var preferredVoiceGender: String = "MALE"

    fun currentVoiceGender(): String = preferredVoiceGender

    @Volatile
    private var activePlaybackToken: String? = null

    @Volatile
    private var activeAudioTrack: AudioTrack? = null

    private var currentAudioFocusRequest: AudioFocusRequest? = null

    private val audioManager: AudioManager? by lazy {
        context.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    }

    private val ttsCacheDir: File by lazy {
        File(context.cacheDir, "secure_audio_cache").apply {
            mkdirs()
            runCatching { File(this, ".nomedia").createNewFile() }
        }
    }

    fun applyVoiceGender(voiceGender: String) {
        preferredVoiceGender = if (voiceGender.equals("FEMALE", ignoreCase = true)) "FEMALE" else "MALE"
    }

    override suspend fun speak(text: String, speechRate: Float): Result<Unit> =
        speakWithLanguage(text, speechRate, languageCode = "en")

    override suspend fun speakWithLanguage(
        text: String,
        speechRate: Float,
        languageCode: String
    ): Result<Unit> {
        val cleanText = text.trim()
        if (cleanText.isEmpty()) return Result.success(Unit)

        return withContext(Dispatchers.IO) {
            val token = UUID.randomUUID().toString()
            activePlaybackToken = token
            val voiceName = if (preferredVoiceGender.equals("FEMALE", ignoreCase = true)) {
                GEMINI_VOICE_FEMALE
            } else {
                GEMINI_VOICE_MALE
            }

            try {
                autoPruneStaleCache()
                val cacheKey = "${voiceName.lowercase()}_${languageCode.lowercase()}_${cleanText.hashCode()}"
                val cacheFile = File(ttsCacheDir, "tts_$cacheKey.enc")

                val pcmBytes = if (cacheFile.exists() && cacheFile.length() > 64L) {
                    readObfuscatedBytes(cacheFile)
                } else {
                    val generatedPcm = fetchGeminiTtsPcm(cleanText, voiceName, languageCode)
                    if (generatedPcm.isNotEmpty()) {
                        writeObfuscatedBytes(cacheFile, generatedPcm)
                    }
                    generatedPcm
                }

                if (activePlaybackToken != token) {
                    return@withContext Result.success(Unit)
                }

                if (pcmBytes.isEmpty()) {
                    throw IllegalStateException("Gemini TTS returned empty audio payload")
                }

                playPcmBytes(pcmBytes, speechRate, token)
                Result.success(Unit)
            } catch (e: CancellationException) {
                stopAudioTrackOnly()
                throw e
            } catch (e: Exception) {
                Log.w(TAG, "Gemini TTS failed ($voiceName); falling back to NativeTtsProvider", e)
                if (activePlaybackToken != token) {
                    return@withContext Result.success(Unit)
                }
                nativeFallback.applyVoiceGender(preferredVoiceGender)
                nativeFallback.speakWithLanguage(cleanText, speechRate, languageCode)
            }
        }
    }

    @OptIn(com.google.firebase.ai.type.PublicPreviewAPI::class)
    private suspend fun fetchGeminiTtsPcm(
        text: String,
        voiceName: String,
        languageCode: String
    ): ByteArray {
        val speechCfg = if (languageCode.equals("uz", ignoreCase = true)) {
            SpeechConfig(voice = Voice(voiceName))
        } else {
            SpeechConfig(voice = Voice(voiceName), languageCode = "en-US")
        }

        val config = generationConfig {
            responseModalities = listOf(ResponseModality.AUDIO)
            speechConfig = speechCfg
        }

        val model = Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
            modelName = GEMINI_TTS_MODEL,
            generationConfig = config
        )

        val response = withTimeout(30_000L) {
            model.generateContent(text)
        }

        val parts = response.candidates.firstOrNull()?.content?.parts.orEmpty()
        for (part in parts) {
            if (part is InlineDataPart && part.inlineData.isNotEmpty()) {
                return part.inlineData
            }
        }
        return ByteArray(0)
    }

    private suspend fun playPcmBytes(
        pcmBytes: ByteArray,
        speechRate: Float,
        token: String
    ) = withContext(Dispatchers.IO) {
        val clampedRate = speechRate.coerceIn(0.75f, 1.50f)
        val minBuf = AudioTrack.getMinBufferSize(
            PCM_SAMPLE_RATE_HZ,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        ).coerceAtLeast(4096)

        val audioAttrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        val audioFormat = AudioFormat.Builder()
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setSampleRate(PCM_SAMPLE_RATE_HZ)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .build()

        val track = AudioTrack.Builder()
            .setAudioAttributes(audioAttrs)
            .setAudioFormat(audioFormat)
            .setBufferSizeInBytes(minBuf * 2)
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build()

        activeAudioTrack = track
        requestTransientAudioFocus()

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && clampedRate != 1.0f) {
                runCatching {
                    track.playbackParams = PlaybackParams().setSpeed(clampedRate)
                }
            }
            track.play()

            val chunkSize = 4096
            var offset = 0
            while (offset < pcmBytes.size && isActive && activePlaybackToken == token) {
                val toWrite = minOf(chunkSize, pcmBytes.size - offset)
                val written = track.write(pcmBytes, offset, toWrite, AudioTrack.WRITE_BLOCKING)
                if (written < 0) break
                offset += written
            }

            // Wait until buffered PCM frames finish playing out of the speaker (interruptible via stop())
            val totalFrames = pcmBytes.size / 2
            val maxWaitMs = ((pcmBytes.size.toLong() * 1000L) / (PCM_SAMPLE_RATE_HZ * 2L * clampedRate))
                .toLong() + 800L
            val waitStart = System.currentTimeMillis()
            while (isActive &&
                activePlaybackToken == token &&
                track.playbackHeadPosition < totalFrames &&
                System.currentTimeMillis() - waitStart < maxWaitMs
            ) {
                delay(40L)
            }
        } finally {
            if (activeAudioTrack === track) {
                activeAudioTrack = null
            }
            runCatching {
                if (track.playState == AudioTrack.PLAYSTATE_PLAYING) {
                    track.pause()
                }
                track.flush()
                track.stop()
            }
            runCatching { track.release() }
            abandonAudioFocus()
        }
    }

    private fun stopAudioTrackOnly() {
        val track = activeAudioTrack
        activeAudioTrack = null
        if (track != null) {
            runCatching {
                track.pause()
                track.flush()
                track.stop()
                track.release()
            }
        }
        abandonAudioFocus()
    }

    private fun requestTransientAudioFocus() {
        runCatching {
            val am = audioManager ?: return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val attrs = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
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

    private fun writeObfuscatedBytes(file: File, input: ByteArray) {
        val bytes = input.clone()
        val key = 0x5A.toByte()
        for (i in bytes.indices) {
            bytes[i] = (bytes[i].toInt() xor key.toInt()).toByte()
        }
        file.writeBytes(bytes)
    }

    private fun readObfuscatedBytes(file: File): ByteArray {
        val bytes = file.readBytes()
        val key = 0x5A.toByte()
        for (i in bytes.indices) {
            bytes[i] = (bytes[i].toInt() xor key.toInt()).toByte()
        }
        return bytes
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
        activePlaybackToken = null
        stopAudioTrackOnly()
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
        languageCode: String = "en",
        overrideVoiceGender: String? = null,
        onSpeechCue: ((Int, Int) -> Unit)? = null
    ): Result<Unit> {
        if (languageCode.equals("uz", ignoreCase = true)) {
            runCatching { Log.d(TAG, "Skipping speakEnglish for Uzbek explanation text: ${text.trim()}") }
            return Result.success(Unit)
        }
        return speakInternal(
            text = text,
            overrideSpeed = overrideSpeed,
            languageCode = languageCode,
            overrideVoiceGender = overrideVoiceGender,
            onSpeechCue = onSpeechCue
        )
    }

    /**
     * Speaks TalkMe Uzbek guidance/welcome scripts explicitly when requested by UI controls.
     */
    suspend fun speakUzbek(
        text: String,
        overrideSpeed: Float? = null,
        overrideVoiceGender: String? = null
    ): Result<Unit> = speakInternal(
        text = text,
        overrideSpeed = overrideSpeed,
        languageCode = "uz",
        overrideVoiceGender = overrideVoiceGender
    )

    /** Native TTS range callbacks drive the Pro avatar as the words are played. */
    suspend fun speakPro(
        text: String,
        languageCode: String,
        voiceGender: String,
        onSpeechCue: (Int, Int) -> Unit
    ): Result<Unit> = speakInternal(
        text = text,
        languageCode = languageCode,
        overrideVoiceGender = voiceGender,
        onSpeechCue = onSpeechCue,
        forceNative = true
    )

    private suspend fun speakInternal(
        text: String,
        overrideSpeed: Float? = null,
        languageCode: String = "en",
        overrideVoiceGender: String? = null,
        onSpeechCue: ((Int, Int) -> Unit)? = null,
        forceNative: Boolean = false
    ): Result<Unit> {
        val cleanText = text.trim()
        if (cleanText.isEmpty()) return Result.success(Unit)

        val prefs = preferencesRepository.userPreferencesFlow.first()
        if (!prefs.soundEnabled && !forceNative) {
            _playbackState.value = TtsPlaybackState.Idle
            return Result.success(Unit)
        }

        val resolvedGender = overrideVoiceGender ?: prefs.voiceGender
        (nativeTtsProvider as? NativeTtsProvider)?.applyVoiceGender(resolvedGender)
        (geminiTtsProvider as? GeminiTtsProvider)?.applyVoiceGender(resolvedGender)

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
                !forceNative && prefs.preferredTtsProvider.equals("GEMINI", ignoreCase = true)
            ) {
                geminiTtsProvider
            } else {
                nativeTtsProvider
            }

            _playbackState.value = TtsPlaybackState.Playing(cleanText)
            try {
                val native = activeProvider as? NativeTtsProvider
                val result = if (onSpeechCue != null && native != null) {
                    native.speakWithCues(cleanText, speed, languageCode, onSpeechCue)
                } else {
                    activeProvider.speakWithLanguage(cleanText, speed, languageCode)
                }
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
