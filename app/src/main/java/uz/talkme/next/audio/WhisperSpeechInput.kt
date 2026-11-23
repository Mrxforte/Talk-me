package uz.talkme.next.audio

import android.annotation.SuppressLint
import android.content.Context
import android.media.*
import android.media.audiofx.AcousticEchoCanceler
import android.media.audiofx.NoiseSuppressor
import android.os.Build
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import uz.talkme.next.domain.SpeechInput
import uz.talkme.next.domain.UtteranceDetector
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

/** Offline whisper.cpp. Capture, endpointing and native decoding have one worker owner. */
class WhisperSpeechInput(context: Context) : SpeechInput {
    private val app = context.applicationContext
    private val worker = Executors.newSingleThreadExecutor()
    private val dispatcher = worker.asCoroutineDispatcher()
    private val generation = AtomicLong()
    private val handleLock = Any()
    private var handle = 0L
    @Volatile private var recording: AudioRecord? = null
    @Volatile private var closed = false

    override suspend fun prepare() = withContext(dispatcher) {
        check(!closed)
        if (handle == 0L) {
            val file = File(app.filesDir, "ggml-base.en-q5_1.bin")
            if (!file.exists() || file.length() != 59721011L) {
                val temporary = File(app.filesDir, "whisper-model.part")
                app.assets.open("whisper/ggml-base.en-q5_1.bin").use { source ->
                    temporary.outputStream().use { source.copyTo(it) }
                }
                check(temporary.length() == 59721011L)
                check(temporary.renameTo(file))
            }
            val loaded = WhisperNative.load(file.absolutePath)
            check(loaded != 0L)
            synchronized(handleLock) { handle = loaded; WhisperNative.setEpoch(handle, generation.get()) }
        }
    }

    override fun listen(onPartial: (String) -> Unit, onFinal: (String) -> Unit,
                        onLevel: (Float) -> Unit, onFailure: () -> Unit,
                        onReady: () -> Unit, onProcessing: () -> Unit) {
        stop()
        val token = generation.get()
        if (closed) return
        worker.execute {
            try {
                while (!closed && generation.get() == token) {
                    val samples = capture(token, onLevel, onReady, onFailure) ?: break
                    if (closed || generation.get() != token) break
                    onProcessing()
                    val text = WhisperNative.transcribe(handle, samples, token,
                        Runtime.getRuntime().availableProcessors().coerceIn(1, 4)).trim()
                    if (closed || generation.get() != token) break
                    // Bracket-only music/noise annotations never trigger a paid AI request.
                    if (text.isNotBlank() && !text.matches(Regex("[\\[(].*[\\])]"))) {
                        onFinal(text)
                        break
                    }
                }
            } catch (_: Exception) {
                if (!closed && generation.get() == token) onFailure()
            } catch (_: LinkageError) {
                if (!closed && generation.get() == token) onFailure()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun capture(token: Long, onLevel: (Float) -> Unit, onReady: () -> Unit,
                        onFailure: () -> Unit): FloatArray? {
        val minimum = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
        check(minimum > 0)
        val recorder = AudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION, 16000,
            AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, maxOf(minimum * 2, 6400))
        var echo: AcousticEchoCanceler? = null
        var noise: NoiseSuppressor? = null
        var privacy: AudioManager.AudioRecordingCallback? = null
        try {
            check(recorder.state == AudioRecord.STATE_INITIALIZED)
            if (AcousticEchoCanceler.isAvailable()) echo = AcousticEchoCanceler.create(recorder.audioSessionId)
            if (NoiseSuppressor.isAvailable()) noise = NoiseSuppressor.create(recorder.audioSessionId)
            recording = recorder
            if (closed || generation.get() != token) return null
            if (Build.VERSION.SDK_INT >= 29) {
                val sessionId = recorder.audioSessionId
                privacy = object : AudioManager.AudioRecordingCallback() {
                    override fun onRecordingConfigChanged(configs: MutableList<AudioRecordingConfiguration>) {
                        if (generation.get() == token && !closed && configs.any {
                            it.clientAudioSessionId == sessionId && it.isClientSilenced
                        }) onFailure()
                    }
                }
                recorder.registerAudioRecordingCallback(app.mainExecutor, privacy)
            }
            recorder.startRecording()
            check(recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING)
            onReady()
            val detector = UtteranceDetector()
            val frame = ShortArray(320)
            var frames = 0
            while (!closed && generation.get() == token) {
                val count = recorder.read(frame, 0, frame.size, AudioRecord.READ_BLOCKING)
                if (closed || generation.get() != token) return null
                check(count > 0)
                val result = detector.accept(frame, count)
                if (++frames % 5 == 0) onLevel((detector.rms * 12).coerceIn(0f, 1f))
                if (result != null) return result
            }
            return null
        } finally {
            if (recording === recorder) recording = null
            runCatching { recorder.stop() }
            if (Build.VERSION.SDK_INT >= 29 && privacy != null)
                runCatching { recorder.unregisterAudioRecordingCallback(privacy) }
            echo?.release(); noise?.release(); recorder.release()
        }
    }
    override fun stop() {
        val token = generation.incrementAndGet()
        synchronized(handleLock) { if (handle != 0L) WhisperNative.setEpoch(handle, token) }
        runCatching { recording?.stop() }
    }
    override fun close() {
        if (closed) return
        closed = true; stop()
        worker.execute { synchronized(handleLock) {
            if (handle != 0L) WhisperNative.release(handle)
            handle = 0L
        } }
        worker.shutdown()
    }
}
