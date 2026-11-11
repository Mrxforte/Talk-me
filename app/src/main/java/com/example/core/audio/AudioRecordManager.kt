package com.example.core.audio

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.sqrt

private const val TAG = "AudioRecordManager"
const val SAMPLE_RATE_HZ = 16000
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
 * Encapsulates raw 16kHz Mono 16-bit PCM Audio capture using Android's native [AudioRecord].
 * - Runs audio reading off the main thread on [Dispatchers.IO].
 * - Calculates real-time normalized amplitude (0.0 .. 1.0) for UI waveforms.
 * - Streams float sample arrays [-1.0f .. 1.0f] for real-time STT engine input.
 * - Accumulates complete raw PCM audio buffer for offline Pass-2 decoding.
 */
@Singleton
class AudioRecordManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _recordingState = MutableStateFlow(AudioRecordingState.IDLE)
    val recordingState: StateFlow<AudioRecordingState> = _recordingState.asStateFlow()

    private val _amplitude = MutableStateFlow(0f)
    val amplitude: StateFlow<Float> = _amplitude.asStateFlow()

    private val _sampleChunkFlow = MutableSharedFlow<FloatArray>(extraBufferCapacity = 64)
    val sampleChunkFlow: SharedFlow<FloatArray> = _sampleChunkFlow.asSharedFlow()

    private var audioRecord: AudioRecord? = null
    private var recordJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    private val rawPcmOutputStream = ByteArrayOutputStream()

    @SuppressLint("MissingPermission")
    fun startRecording(): Boolean {
        if (_recordingState.value == AudioRecordingState.RECORDING) return true

        val minBufferSize = AudioRecord.getMinBufferSize(
            SAMPLE_RATE_HZ,
            CHANNEL_CONFIG,
            AUDIO_FORMAT
        )
        if (minBufferSize == AudioRecord.ERROR_BAD_VALUE || minBufferSize == AudioRecord.ERROR) {
            Log.e(TAG, "Invalid AudioRecord buffer parameters")
            _recordingState.value = AudioRecordingState.ERROR
            return false
        }

        val bufferSize = (minBufferSize * 2).coerceAtLeast(3200)

        try {
            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.VOICE_RECOGNITION,
                SAMPLE_RATE_HZ,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                bufferSize
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to instantiate AudioRecord", e)
            _recordingState.value = AudioRecordingState.ERROR
            return false
        }

        if (audioRecord?.state != AudioRecord.STATE_INITIALIZED) {
            Log.e(TAG, "AudioRecord state is uninitialized")
            _recordingState.value = AudioRecordingState.ERROR
            audioRecord?.release()
            audioRecord = null
            return false
        }

        rawPcmOutputStream.reset()
        audioRecord?.startRecording()
        _recordingState.value = AudioRecordingState.RECORDING

        recordJob = scope.launch {
            val shortBuffer = ShortArray(1600) // 100ms chunks at 16kHz
            val byteBuffer = ByteArray(3200)

            while (isActive && _recordingState.value == AudioRecordingState.RECORDING) {
                val readShorts = audioRecord?.read(shortBuffer, 0, shortBuffer.size) ?: -1
                if (readShorts > 0) {
                    // Convert short samples to floats [-1.0f .. 1.0f] for sherpa-onnx
                    val floatArray = FloatArray(readShorts)
                    var sumSquare = 0.0
                    for (i in 0 until readShorts) {
                        val sample = shortBuffer[i]
                        floatArray[i] = sample / 32768.0f
                        sumSquare += sample * sample.toDouble()
                        
                        // Save raw PCM bytes
                        val byteIdx = i * 2
                        byteBuffer[byteIdx] = (sample.toInt() and 0xFF).toByte()
                        byteBuffer[byteIdx + 1] = ((sample.toInt() shr 8) and 0xFF).toByte()
                    }
                    rawPcmOutputStream.write(byteBuffer, 0, readShorts * 2)

                    // Calculate RMS normalized amplitude (0.0 to 1.0)
                    val rms = sqrt(sumSquare / readShorts)
                    val normalizedAmp = (rms / 32768.0).coerceIn(0.0, 1.0).toFloat()
                    _amplitude.value = normalizedAmp

                    // Emit to flow
                    _sampleChunkFlow.tryEmit(floatArray)
                }
            }
        }
        return true
    }

    fun pauseRecording() {
        if (_recordingState.value == AudioRecordingState.RECORDING) {
            _recordingState.value = AudioRecordingState.PAUSED
            audioRecord?.stop()
        }
    }

    fun resumeRecording() {
        if (_recordingState.value == AudioRecordingState.PAUSED) {
            audioRecord?.startRecording()
            _recordingState.value = AudioRecordingState.RECORDING
        }
    }

    fun stopRecording(): FloatArray {
        _recordingState.value = AudioRecordingState.STOPPED
        recordJob?.cancel()
        recordJob = null

        try {
            audioRecord?.stop()
            audioRecord?.release()
        } catch (e: Exception) {
            Log.e(TAG, "Error stopping AudioRecord", e)
        } finally {
            audioRecord = null
        }

        _amplitude.value = 0f

        // Convert full raw PCM byte output stream to float array
        val pcmBytes = rawPcmOutputStream.toByteArray()
        val totalShorts = pcmBytes.size / 2
        val fullFloatArray = FloatArray(totalShorts)
        for (i in 0 until totalShorts) {
            val low = pcmBytes[i * 2].toInt() and 0xFF
            val high = pcmBytes[i * 2 + 1].toInt()
            val sample = (high shl 8) or low
            fullFloatArray[i] = sample.toShort() / 32768.0f
        }
        return fullFloatArray
    }

    fun cancelRecording() {
        _recordingState.value = AudioRecordingState.IDLE
        recordJob?.cancel()
        recordJob = null
        try {
            audioRecord?.stop()
            audioRecord?.release()
        } catch (e: Exception) {
            Log.e(TAG, "Error cancelling AudioRecord", e)
        } finally {
            audioRecord = null
        }
        rawPcmOutputStream.reset()
        _amplitude.value = 0f
    }
}
