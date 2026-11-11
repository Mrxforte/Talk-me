package com.example.domain.repository

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
    val processingDurationMs: Long
)

interface SpeechRepository {
    val speechState: StateFlow<SpeechRecognitionState>
    val interimText: StateFlow<String>
    val finalText: StateFlow<String>
    val audioAmplitude: StateFlow<Float>
    val recordingDurationSeconds: StateFlow<Int>

    fun startListening(): Boolean
    suspend fun stopListening(): SpeechRecognitionResult
    fun cancelListening()
}
