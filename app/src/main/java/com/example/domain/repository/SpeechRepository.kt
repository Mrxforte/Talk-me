package com.example.domain.repository

import com.example.domain.model.VadState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

enum class SpeechRecognitionState {
    IDLE,
    LISTENING_PASS_1,
    PROCESSING_PASS_2,
    COMPLETED,
    ERROR
}

data class SpeechRecognitionResult(
    val recognizedText: String,
    val isFinal: Boolean,
    val recordingDurationMs: Long,
    val processingDurationMs: Long,
    val audioWavBytes: ByteArray = ByteArray(0),
    val hadSpeechDetectedByVad: Boolean = true,
    val sttErrorReason: String? = null
)

/**
 * Local Speech-to-Text Repository contract combining:
 * - Android [com.example.core.audio.AudioRecordManager] (16kHz Mono PCM16)
 * - Local [com.example.core.speech.SileroVadDetector] (`SILENCE`, `SPEECH_BEGIN`, `ACTIVE_SPEECH`, `PAUSE`, `SPEECH_END`)
 * - Local [com.example.core.speech.SherpaStreamingEngine] (Pass 1 streaming partial transcripts)
 * - Local [com.example.core.speech.SherpaOfflineEngine] (Pass 2 batch final decoding)
 */
interface SpeechRepository {
    val speechState: StateFlow<SpeechRecognitionState>
    val vadState: StateFlow<VadState>
    val interimText: StateFlow<String>
    val finalText: StateFlow<String>
    val audioAmplitude: StateFlow<Float>
    val isMuted: StateFlow<Boolean>
    val recordingDurationSeconds: StateFlow<Int>

    /**
     * Emits automatically finalized [SpeechRecognitionResult] whenever Silero VAD detects `SPEECH_END`
     * and Sherpa-ONNX completes Pass-2 decoding.
     */
    val utteranceFinalizedEvents: SharedFlow<SpeechRecognitionResult>

    fun hasRecordPermission(): Boolean
    fun setMuted(muted: Boolean)
    fun startListening(): Boolean
    suspend fun stopListening(): SpeechRecognitionResult
    fun cancelListening()
}
