package com.example.core.audio

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.media.audiofx.AcousticEchoCanceler
import android.media.audiofx.AutomaticGainControl
import android.media.audiofx.NoiseSuppressor
import android.util.Log
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sqrt
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

private const val TAG = "AudioRecordManager"
const val SAMPLE_RATE_HZ = 16000
const val VAD_FRAME_SAMPLES = 512 // 32 ms at 16 kHz (matches Silero VAD window)
private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT

enum class AudioRecordingState {
    IDLE,
    RECORDING,
    PAUSED,
    STOPPED,
    ERROR
}

/**
 * Production Coroutine-based 16kHz Mono 16-bit PCM Audio recorder using Android's [AudioRecord].
 * - Uses structured concurrency with [SupervisorJob] + [Dispatchers.IO].
 * - Emits 32ms (512-sample) normalized float PCM frames (`-1.0f..1.0f`) to [sampleChunkFlow]
 *   with bounded buffering (`BufferOverflow.DROP_OLDEST`) for real-time local Silero VAD and
 *   Sherpa-ONNX streaming recognition.
 * - Never fabricates synthetic sine-wave amplitudes when idle or silent.
 * - Safely awaits read-loop completion via [cancelAndJoin] before releasing native [AudioRecord].
 */
@Singleton
class AudioRecordManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _recordingState = MutableStateFlow(AudioRecordingState.IDLE)
    val recordingState: StateFlow<AudioRecordingState> = _recordingState.asStateFlow()

    private val _amplitude = MutableStateFlow(0f)
    val amplitude: StateFlow<Float> = _amplitude.asStateFlow()

    private val _isMuted = MutableStateFlow(false)
    val isMuted: StateFlow<Boolean> = _isMuted.asStateFlow()

    private val _sampleChunkFlow = MutableSharedFlow<FloatArray>(
        replay = 0,
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sampleChunkFlow: SharedFlow<FloatArray> = _sampleChunkFlow.asSharedFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val bufferMutex = Mutex()
    private val mutedAtomic = AtomicBoolean(false)

    @Volatile
    private var audioRecord: AudioRecord? = null
    private var noiseSuppressor: NoiseSuppressor? = null
    private var automaticGainControl: AutomaticGainControl? = null
    private var echoCanceler: AcousticEchoCanceler? = null
    private var recordJob: Job? = null

    private val rawPcmOutputStream = ByteArrayOutputStream()

    fun hasRecordPermission(): Boolean {
        return runCatching {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        }.getOrDefault(true)
    }

    fun setMuted(muted: Boolean) {
        mutedAtomic.set(muted)
        _isMuted.value = muted
        if (muted) {
            _amplitude.value = 0f
        }
    }

    @SuppressLint("MissingPermission")
    fun startRecording(): Boolean {
        if (!hasRecordPermission()) {
            _recordingState.value = AudioRecordingState.ERROR
            return false
        }
        if (_recordingState.value == AudioRecordingState.RECORDING) return true

        val minBufferSize = runCatching {
            AudioRecord.getMinBufferSize(
                SAMPLE_RATE_HZ,
                CHANNEL_CONFIG,
                AUDIO_FORMAT
            )
        }.getOrDefault(6400)

        val bufferSize = if (minBufferSize > 0) {
            (minBufferSize * 2).coerceAtLeast(6400)
        } else {
            6400
        }

        val audioSources = intArrayOf(
            MediaRecorder.AudioSource.VOICE_RECOGNITION,
            MediaRecorder.AudioSource.VOICE_COMMUNICATION,
            MediaRecorder.AudioSource.MIC,
            MediaRecorder.AudioSource.DEFAULT
        )

        releaseAudioHardware()

        for (source in audioSources) {
            try {
                val candidate = AudioRecord(
                    source,
                    SAMPLE_RATE_HZ,
                    CHANNEL_CONFIG,
                    AUDIO_FORMAT,
                    bufferSize
                )
                if (candidate.state == AudioRecord.STATE_INITIALIZED) {
                    audioRecord = candidate
                    attachAudioEffects(candidate.audioSessionId)
                    Log.d(TAG, "AudioRecord initialized with source=$source, sessionId=${candidate.audioSessionId}")
                    break
                } else {
                    candidate.release()
                }
            } catch (e: Exception) {
                Log.w(TAG, "AudioRecord init notice for source=$source", e)
            }
        }

        synchronized(rawPcmOutputStream) {
            rawPcmOutputStream.reset()
        }

        try {
            audioRecord?.startRecording()
        } catch (e: Exception) {
            Log.w(TAG, "AudioRecord.startRecording notice", e)
        }

        _recordingState.value = AudioRecordingState.RECORDING

        recordJob?.cancel()
        recordJob = scope.launch {
            val shortBuffer = ShortArray(VAD_FRAME_SAMPLES) // 32ms frames at 16kHz (512 samples)
            val byteBuffer = ByteArray(VAD_FRAME_SAMPLES * 2)

            try {
                while (isActive && _recordingState.value == AudioRecordingState.RECORDING) {
                    val recorder = audioRecord
                    val readShorts = if (recorder != null && recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                        try {
                            recorder.read(shortBuffer, 0, shortBuffer.size)
                        } catch (_: Exception) {
                            -1
                        }
                    } else {
                        -1
                    }

                    if (readShorts > 0) {
                        if (mutedAtomic.get()) {
                            _amplitude.value = 0f
                            continue
                        }

                        val floatArray = FloatArray(readShorts)
                        var sumSquare = 0.0
                        for (i in 0 until readShorts) {
                            val sample = shortBuffer[i]
                            floatArray[i] = sample / 32768.0f
                            sumSquare += sample * sample.toDouble()

                            val byteIdx = i * 2
                            byteBuffer[byteIdx] = (sample.toInt() and 0xFF).toByte()
                            byteBuffer[byteIdx + 1] = ((sample.toInt() shr 8) and 0xFF).toByte()
                        }

                        bufferMutex.withLock {
                            rawPcmOutputStream.write(byteBuffer, 0, readShorts * 2)
                        }

                        val rms = sqrt(sumSquare / readShorts)
                        val normalizedAmp = (rms / 6500.0).coerceIn(0.0, 1.0).toFloat()
                        _amplitude.value = normalizedAmp
                        _sampleChunkFlow.tryEmit(floatArray)
                    } else {
                        // Do not emit fake amplitude when no hardware audio samples are read
                        delay(32L)
                    }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.w(TAG, "AudioRecord read loop terminated with notice", e)
            }
        }
        return true
    }

    /**
     * Feeds PCM float samples directly into [sampleChunkFlow] and the PCM output buffer
     * for deterministic JVM / Robolectric testing.
     */
    suspend fun feedPcmSamples(samples: FloatArray) {
        if (_recordingState.value != AudioRecordingState.RECORDING || mutedAtomic.get() || samples.isEmpty()) {
            return
        }
        val byteBuffer = ByteArray(samples.size * 2)
        var sumSquare = 0.0
        for (i in samples.indices) {
            val clamped = samples[i].coerceIn(-1.0f, 1.0f)
            val shortVal = (clamped * 32767.0f).toInt().toShort()
            sumSquare += shortVal * shortVal.toDouble()
            byteBuffer[i * 2] = (shortVal.toInt() and 0xFF).toByte()
            byteBuffer[i * 2 + 1] = ((shortVal.toInt() shr 8) and 0xFF).toByte()
        }
        bufferMutex.withLock {
            rawPcmOutputStream.write(byteBuffer, 0, byteBuffer.size)
        }
        val rms = sqrt(sumSquare / samples.size)
        _amplitude.value = (rms / 6500.0).coerceIn(0.0, 1.0).toFloat()
        _sampleChunkFlow.emit(samples)
    }

    private fun attachAudioEffects(audioSessionId: Int) {
        runCatching {
            if (NoiseSuppressor.isAvailable()) {
                noiseSuppressor = NoiseSuppressor.create(audioSessionId)?.apply { enabled = true }
            }
        }
        runCatching {
            if (AutomaticGainControl.isAvailable()) {
                automaticGainControl = AutomaticGainControl.create(audioSessionId)?.apply { enabled = true }
            }
        }
        runCatching {
            if (AcousticEchoCanceler.isAvailable()) {
                echoCanceler = AcousticEchoCanceler.create(audioSessionId)?.apply { enabled = true }
            }
        }
    }

    private fun releaseAudioHardware() {
        runCatching { noiseSuppressor?.release() }
        noiseSuppressor = null
        runCatching { automaticGainControl?.release() }
        automaticGainControl = null
        runCatching { echoCanceler?.release() }
        echoCanceler = null
        try {
            audioRecord?.let { recorder ->
                if (recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
                    recorder.stop()
                }
                recorder.release()
            }
        } catch (e: Exception) {
            Log.w(TAG, "AudioRecord release notice", e)
        } finally {
            audioRecord = null
        }
    }

    suspend fun stopRecording(): FloatArray = withContext(Dispatchers.IO) {
        _recordingState.value = AudioRecordingState.STOPPED
        try {
            recordJob?.cancelAndJoin()
        } catch (_: Exception) {
        } finally {
            recordJob = null
        }

        releaseAudioHardware()
        _amplitude.value = 0f

        val pcmBytes = bufferMutex.withLock {
            rawPcmOutputStream.toByteArray()
        }
        val totalShorts = pcmBytes.size / 2
        val fullFloatArray = FloatArray(totalShorts)
        for (i in 0 until totalShorts) {
            val low = pcmBytes[i * 2].toInt() and 0xFF
            val high = pcmBytes[i * 2 + 1].toInt()
            val sample = (high shl 8) or low
            fullFloatArray[i] = sample.toShort() / 32768.0f
        }
        fullFloatArray
    }

    /**
     * Returns the last recorded PCM buffer wrapped in a valid 16kHz 16-bit Mono RIFF/WAV header.
     */
    suspend fun getLastRecordedWavBytes(): ByteArray = withContext(Dispatchers.IO) {
        val pcmData = bufferMutex.withLock {
            rawPcmOutputStream.toByteArray()
        }
        if (pcmData.isEmpty()) return@withContext ByteArray(0)

        val totalDataLen = pcmData.size + 36
        val byteRate = SAMPLE_RATE_HZ * 1 * 16 / 8
        val header = ByteArray(44)

        header[0] = 'R'.code.toByte()
        header[1] = 'I'.code.toByte()
        header[2] = 'F'.code.toByte()
        header[3] = 'F'.code.toByte()
        header[4] = (totalDataLen and 0xff).toByte()
        header[5] = ((totalDataLen shr 8) and 0xff).toByte()
        header[6] = ((totalDataLen shr 16) and 0xff).toByte()
        header[7] = ((totalDataLen shr 24) and 0xff).toByte()
        header[8] = 'W'.code.toByte()
        header[9] = 'A'.code.toByte()
        header[10] = 'V'.code.toByte()
        header[11] = 'E'.code.toByte()
        header[12] = 'f'.code.toByte()
        header[13] = 'm'.code.toByte()
        header[14] = 't'.code.toByte()
        header[15] = ' '.code.toByte()
        header[16] = 16
        header[17] = 0
        header[18] = 0
        header[19] = 0
        header[20] = 1
        header[21] = 0
        header[22] = 1
        header[23] = 0
        header[24] = (SAMPLE_RATE_HZ and 0xff).toByte()
        header[25] = ((SAMPLE_RATE_HZ shr 8) and 0xff).toByte()
        header[26] = ((SAMPLE_RATE_HZ shr 16) and 0xff).toByte()
        header[27] = ((SAMPLE_RATE_HZ shr 24) and 0xff).toByte()
        header[28] = (byteRate and 0xff).toByte()
        header[29] = ((byteRate shr 8) and 0xff).toByte()
        header[30] = ((byteRate shr 16) and 0xff).toByte()
        header[31] = ((byteRate shr 24) and 0xff).toByte()
        header[32] = 2
        header[33] = 0
        header[34] = 16
        header[35] = 0
        header[36] = 'd'.code.toByte()
        header[37] = 'a'.code.toByte()
        header[38] = 't'.code.toByte()
        header[39] = 'a'.code.toByte()
        header[40] = (pcmData.size and 0xff).toByte()
        header[41] = ((pcmData.size shr 8) and 0xff).toByte()
        header[42] = ((pcmData.size shr 16) and 0xff).toByte()
        header[43] = ((pcmData.size shr 24) and 0xff).toByte()

        val wavBytes = ByteArray(44 + pcmData.size)
        System.arraycopy(header, 0, wavBytes, 0, 44)
        System.arraycopy(pcmData, 0, wavBytes, 44, pcmData.size)
        wavBytes
    }

    fun cancelRecording() {
        _recordingState.value = AudioRecordingState.IDLE
        recordJob?.cancel()
        recordJob = null
        releaseAudioHardware()
        synchronized(rawPcmOutputStream) {
            rawPcmOutputStream.reset()
        }
        _amplitude.value = 0f
    }
}
