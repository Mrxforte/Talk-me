package com.example.data.repository

import com.example.core.audio.AudioRecordManager
import com.example.core.speech.SileroVadDetector
import com.example.core.speech.VadState
import com.example.domain.repository.SpeechRecognitionResult
import com.example.domain.repository.SpeechRecognitionState
import com.example.domain.repository.SpeechRepository
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
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

/**
 * Pure-Kotlin speech capture + VAD pipeline for Gemini multimodal audio speaking turns:
 * `Android AudioRecord (16kHz Mono PCM) -> Local Spectral/Formant VAD -> 16kHz WAV -> Gemini 3.8 Multimodal`
 */
@Singleton
class SpeechRepositoryImpl @Inject constructor(
    private val audioRecordManager: AudioRecordManager,
    private val sileroVadDetector: SileroVadDetector
) : SpeechRepository {

    private val _speechState = MutableStateFlow(SpeechRecognitionState.IDLE)
    override val speechState: StateFlow<SpeechRecognitionState> = _speechState.asStateFlow()

    override val vadState: StateFlow<VadState> = sileroVadDetector.vadState

    private val _interimText = MutableStateFlow("")
    override val interimText: StateFlow<String> = _interimText.asStateFlow()

    private val _finalText = MutableStateFlow("")
    override val finalText: StateFlow<String> = _finalText.asStateFlow()

    override val audioAmplitude: StateFlow<Float> = audioRecordManager.amplitude

    override val isMuted: StateFlow<Boolean> = audioRecordManager.isMuted

    private val _recordingDurationSeconds = MutableStateFlow(0)
    override val recordingDurationSeconds: StateFlow<Int> = _recordingDurationSeconds.asStateFlow()

    private val _utteranceFinalizedEvents = MutableSharedFlow<SpeechRecognitionResult>(
        replay = 0,
        extraBufferCapacity = 8,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val utteranceFinalizedEvents: SharedFlow<SpeechRecognitionResult> =
        _utteranceFinalizedEvents.asSharedFlow()

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val finalizationMutex = Mutex()
    private val isFinalizingAtomic = AtomicBoolean(false)

    private var audioCollectJob: Job? = null
    private var timerJob: Job? = null
    private var startTimestamp = 0L

    @Volatile
    private var speechDetectedInCurrentTurn = false

    override fun hasRecordPermission(): Boolean = audioRecordManager.hasRecordPermission()

    override fun setMuted(muted: Boolean) {
        audioRecordManager.setMuted(muted)
    }

    override fun startListening(): Boolean {
        if (_speechState.value == SpeechRecognitionState.LISTENING_PASS_1) return true

        isFinalizingAtomic.set(false)
        speechDetectedInCurrentTurn = false
        _interimText.value = ""
        _finalText.value = ""
        _recordingDurationSeconds.value = 0
        startTimestamp = System.currentTimeMillis()

        sileroVadDetector.initialize()
        sileroVadDetector.reset()

        val started = audioRecordManager.startRecording()
        if (!started) {
            _speechState.value = SpeechRecognitionState.ERROR
            return false
        }

        _speechState.value = SpeechRecognitionState.LISTENING_PASS_1

        audioCollectJob?.cancel()
        audioCollectJob = scope.launch {
            audioRecordManager.sampleChunkFlow.collect { chunk ->
                if (_speechState.value != SpeechRecognitionState.LISTENING_PASS_1 || isFinalizingAtomic.get()) {
                    return@collect
                }

                val vadResult = sileroVadDetector.acceptWaveform(chunk)

                if (vadResult.isSpeechActive) {
                    speechDetectedInCurrentTurn = true
                }

                if (vadResult.state == VadState.SPEECH_END && isFinalizingAtomic.compareAndSet(false, true)) {
                    val segmentedSpeech = vadResult.completedSpeechSegment ?: FloatArray(0)
                    launch {
                        val result = finalizeRecognizedUtterance(
                            hadSpeechFromVad = true,
                            hasSegmentedSpeech = segmentedSpeech.isNotEmpty()
                        )
                        _utteranceFinalizedEvents.emit(result)
                    }
                }
            }
        }

        timerJob?.cancel()
        timerJob = scope.launch {
            while (isActive && _speechState.value == SpeechRecognitionState.LISTENING_PASS_1) {
                delay(1000L)
                _recordingDurationSeconds.value += 1
            }
        }

        return true
    }

    override suspend fun stopListening(): SpeechRecognitionResult = withContext(Dispatchers.IO) {
        isFinalizingAtomic.set(true)
        val flushedSegment = sileroVadDetector.flushActiveSpeechSegment()
        finalizeRecognizedUtterance(
            hadSpeechFromVad = speechDetectedInCurrentTurn || flushedSegment.isNotEmpty(),
            hasSegmentedSpeech = flushedSegment.isNotEmpty()
        )
    }

    private suspend fun finalizeRecognizedUtterance(
        hadSpeechFromVad: Boolean,
        hasSegmentedSpeech: Boolean
    ): SpeechRecognitionResult = finalizationMutex.withLock {
        val recDurationMs = (System.currentTimeMillis() - startTimestamp).coerceAtLeast(0L)
        val processStart = System.currentTimeMillis()

        _speechState.value = SpeechRecognitionState.PROCESSING_PASS_2
        runCatching { timerJob?.cancelAndJoin() }
        timerJob = null
        runCatching { audioCollectJob?.cancelAndJoin() }
        audioCollectJob = null

        audioRecordManager.stopRecording()
        val wavBytes = audioRecordManager.getLastRecordedWavBytes()

        _finalText.value = ""
        _speechState.value = SpeechRecognitionState.COMPLETED

        val processDurationMs = (System.currentTimeMillis() - processStart).coerceAtLeast(0L)

        SpeechRecognitionResult(
            recognizedText = "",
            isFinal = true,
            recordingDurationMs = recDurationMs,
            processingDurationMs = processDurationMs,
            audioWavBytes = wavBytes,
            hadSpeechDetectedByVad = hadSpeechFromVad || hasSegmentedSpeech
        )
    }

    override fun cancelListening() {
        isFinalizingAtomic.set(false)
        speechDetectedInCurrentTurn = false
        timerJob?.cancel()
        timerJob = null
        audioCollectJob?.cancel()
        audioCollectJob = null
        audioRecordManager.cancelRecording()
        sileroVadDetector.reset()

        _speechState.value = SpeechRecognitionState.IDLE
        _interimText.value = ""
        _finalText.value = ""
        _recordingDurationSeconds.value = 0
    }
}
