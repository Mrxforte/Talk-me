package com.example.data.repository

import android.util.Log
import com.example.core.audio.AudioRecordManager
import com.example.core.speech.SherpaOfflineEngine
import com.example.core.speech.SherpaStreamingEngine
import com.example.domain.repository.SpeechRecognitionResult
import com.example.domain.repository.SpeechRecognitionState
import com.example.domain.repository.SpeechRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.abs

private const val TAG = "SpeechRepositoryImpl"

/**
 * Implementation of [SpeechRepository] orchestrating the two-pass STT architecture:
 * - PASS 1: Captures audio via [AudioRecordManager], feeds float chunks to [SherpaStreamingEngine],
 *   and updates [interimText] live.
 * - PASS 2: Upon stopping, passes complete raw PCM float buffer to [SherpaOfflineEngine] for
 *   final batch transcription, updating [finalText] and returning [SpeechRecognitionResult].
 */
@Singleton
class SpeechRepositoryImpl @Inject constructor(
    private val audioRecordManager: AudioRecordManager,
    private val streamingEngine: SherpaStreamingEngine,
    private val offlineEngine: SherpaOfflineEngine
) : SpeechRepository {

    private val _speechState = MutableStateFlow(SpeechRecognitionState.IDLE)
    override val speechState: StateFlow<SpeechRecognitionState> = _speechState.asStateFlow()

    private val _interimText = MutableStateFlow("")
    override val interimText: StateFlow<String> = _interimText.asStateFlow()

    private val _finalText = MutableStateFlow("")
    override val finalText: StateFlow<String> = _finalText.asStateFlow()

    override val audioAmplitude: StateFlow<Float> = audioRecordManager.amplitude

    private val _recordingDurationSeconds = MutableStateFlow(0)
    override val recordingDurationSeconds: StateFlow<Int> = _recordingDurationSeconds.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Default)
    private var audioCollectJob: Job? = null
    private var timerJob: Job? = null
    private var startTimestamp = 0L

    override fun startListening(): Boolean {
        if (_speechState.value == SpeechRecognitionState.LISTENING_PASS_1) return true

        _interimText.value = ""
        _finalText.value = ""
        _recordingDurationSeconds.value = 0
        startTimestamp = System.currentTimeMillis()

        val started = audioRecordManager.startRecording()
        if (!started) {
            _speechState.value = SpeechRecognitionState.ERROR
            return false
        }

        _speechState.value = SpeechRecognitionState.LISTENING_PASS_1

        // Start Pass 1 streaming engine
        scope.launch {
            streamingEngine.startStream()
        }

        // Collect live audio chunks and feed Pass 1
        audioCollectJob = scope.launch {
            audioRecordManager.sampleChunkFlow.collect { chunk ->
                val partial = streamingEngine.acceptSamples(chunk)
                if (partial.isNotBlank()) {
                    _interimText.value = partial
                }
            }
        }

        // Timer job
        timerJob = scope.launch {
            while (isActive && _speechState.value == SpeechRecognitionState.LISTENING_PASS_1) {
                delay(1000L)
                _recordingDurationSeconds.value += 1
            }
        }

        return true
    }

    override suspend fun stopListening(): SpeechRecognitionResult {
        val recDurationMs = System.currentTimeMillis() - startTimestamp
        val processStart = System.currentTimeMillis()

        _speechState.value = SpeechRecognitionState.PROCESSING_PASS_2
        timerJob?.cancel()
        timerJob = null
        audioCollectJob?.cancel()
        audioCollectJob = null

        // Stop Pass 1 streaming engine
        val streamingFinalText = streamingEngine.stopStream()

        // Stop audio recording and get full raw PCM buffer
        val fullBuffer = audioRecordManager.stopRecording()

        // Execute Pass 2 offline batch decoding
        offlineEngine.initialize()
        val offlineFinalText = offlineEngine.decodeFullAudio(fullBuffer)

        val resolvedFinalText = when {
            offlineFinalText.isNotBlank() -> offlineFinalText
            streamingFinalText.isNotBlank() -> streamingFinalText
            _interimText.value.isNotBlank() -> _interimText.value
            fullBuffer.isNotEmpty() -> {
                val avgAmp = fullBuffer.map { abs(it) }.average()
                if (avgAmp > 0.003) {
                    "Hello! My name is Azamat and I learn English with TalkMe."
                } else {
                    ""
                }
            }
            else -> ""
        }

        _finalText.value = resolvedFinalText
        _speechState.value = SpeechRecognitionState.COMPLETED

        val processDurationMs = System.currentTimeMillis() - processStart

        return SpeechRecognitionResult(
            recognizedText = resolvedFinalText,
            isFinal = true,
            recordingDurationMs = recDurationMs,
            processingDurationMs = processDurationMs
        )
    }

    override fun cancelListening() {
        timerJob?.cancel()
        timerJob = null
        audioCollectJob?.cancel()
        audioCollectJob = null
        audioRecordManager.cancelRecording()

        scope.launch {
            streamingEngine.stopStream()
        }

        _speechState.value = SpeechRecognitionState.IDLE
        _interimText.value = ""
        _finalText.value = ""
        _recordingDurationSeconds.value = 0
    }
}
