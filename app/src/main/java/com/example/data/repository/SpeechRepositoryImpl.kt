package com.example.data.repository

import com.example.core.audio.AudioRecordManager
import com.example.core.speech.SherpaOfflineEngine
import com.example.core.speech.SherpaStreamingEngine
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
 * Fully local, backend-free implementation of [SpeechRepository]:
 *
 * Pipeline:
 * `Android AudioRecord (16kHz Mono PCM) → Local Silero VAD → Local Sherpa-ONNX STT (Streaming Pass-1 + Offline Pass-2)`
 *
 * Key Guarantees:
 * - Zero network requests during speech recognition.
 * - Uses [SileroVadDetector] (multi-feature spectral/formant/ZCR/SNR + native Silero ONNX) to detect
 *   `SPEECH_BEGIN`, `ACTIVE_SPEECH`, `PAUSE`, `SPEECH_END`, and `SILENCE`.
 * - Automatically finalizes recognition when VAD detects `SPEECH_END` and emits [utteranceFinalizedEvents].
 * - Never invents, rewrites, or substitutes fallback English sentences when recognition returns empty.
 */
@Singleton
class SpeechRepositoryImpl @Inject constructor(
    private val audioRecordManager: AudioRecordManager,
    private val sileroVadDetector: SileroVadDetector,
    private val streamingEngine: SherpaStreamingEngine,
    private val offlineEngine: SherpaOfflineEngine
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
    private var engineStartupJob: Job? = null
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

                // 1. Run local Silero VAD on the 16kHz float chunk
                val vadResult = sileroVadDetector.acceptWaveform(chunk)

                if (vadResult.isSpeechActive) {
                    speechDetectedInCurrentTurn = true
                    // 2. Feed active speech chunk to Sherpa-ONNX Streaming Engine for live partial transcript
                    val partial = streamingEngine.acceptSamples(chunk)
                    if (partial.isNotBlank()) {
                        // Replace interim text with latest streaming hypothesis (never concatenatepartials)
                        _interimText.value = partial
                    }
                }

                // 3. When Silero VAD detects SPEECH_END, automatically finalize STT
                if (vadResult.state == VadState.SPEECH_END && isFinalizingAtomic.compareAndSet(false, true)) {
                    val segmentedSpeech = vadResult.completedSpeechSegment ?: FloatArray(0)
                    launch {
                        val result = finalizeRecognizedUtterance(
                            vadSegmentedSpeech = segmentedSpeech,
                            hadSpeechFromVad = true
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

        engineStartupJob?.cancel()
        engineStartupJob = scope.launch {
            val streamingReady = streamingEngine.startStream()
            val offlineReady = offlineEngine.initialize()
            if (!streamingReady && !offlineReady &&
                _speechState.value == SpeechRecognitionState.LISTENING_PASS_1 &&
                isFinalizingAtomic.compareAndSet(false, true)
            ) {
                _speechState.value = SpeechRecognitionState.ERROR
                timerJob?.cancel()
                audioCollectJob?.cancel()
                audioRecordManager.cancelRecording()
                _utteranceFinalizedEvents.emit(
                    SpeechRecognitionResult(
                        recognizedText = "",
                        isFinal = true,
                        recordingDurationMs = 0,
                        processingDurationMs = 0,
                        hadSpeechDetectedByVad = false,
                        sttErrorReason = "Nutqni tanish moduli ishga tushmadi. Ilova paketida Sherpa-ONNX kutubxonasi va nutq modellari borligini tekshiring."
                    )
                )
            }
        }

        return true
    }

    override suspend fun stopListening(): SpeechRecognitionResult = withContext(Dispatchers.IO) {
        isFinalizingAtomic.set(true)
        val flushedSegment = sileroVadDetector.flushActiveSpeechSegment()
        finalizeRecognizedUtterance(
            vadSegmentedSpeech = flushedSegment,
            hadSpeechFromVad = speechDetectedInCurrentTurn || flushedSegment.isNotEmpty()
        )
    }

    private suspend fun finalizeRecognizedUtterance(
        vadSegmentedSpeech: FloatArray,
        hadSpeechFromVad: Boolean
    ): SpeechRecognitionResult = finalizationMutex.withLock {
        val recDurationMs = (System.currentTimeMillis() - startTimestamp).coerceAtLeast(0L)
        val processStart = System.currentTimeMillis()

        _speechState.value = SpeechRecognitionState.PROCESSING_PASS_2
        runCatching { timerJob?.cancelAndJoin() }
        timerJob = null
        runCatching { audioCollectJob?.cancelAndJoin() }
        audioCollectJob = null

        val streamingFinalText = streamingEngine.stopStream().trim()

        // Stop AudioRecord coroutine safely and extract full PCM buffer & WAV bytes
        val fullBuffer = audioRecordManager.stopRecording()
        val wavBytes = audioRecordManager.getLastRecordedWavBytes()

        val targetSamplesForPass2 = if (vadSegmentedSpeech.isNotEmpty()) {
            vadSegmentedSpeech
        } else if (hadSpeechFromVad) {
            fullBuffer
        } else {
            FloatArray(0)
        }

        val offlineFinalText = if (targetSamplesForPass2.isNotEmpty()) {
            offlineEngine.decodeFullAudio(targetSamplesForPass2).trim()
        } else {
            ""
        }

        // Strict Transcript Integrity: resolve exact STT output without ever inventing fallback text
        val resolvedFinalText = when {
            offlineFinalText.isNotBlank() -> offlineFinalText
            streamingFinalText.isNotBlank() -> streamingFinalText
            _interimText.value.isNotBlank() -> _interimText.value.trim()
            else -> ""
        }

        _finalText.value = resolvedFinalText
        _speechState.value = SpeechRecognitionState.COMPLETED

        val processDurationMs = (System.currentTimeMillis() - processStart).coerceAtLeast(0L)

        SpeechRecognitionResult(
            recognizedText = resolvedFinalText,
            isFinal = true,
            recordingDurationMs = recDurationMs,
            processingDurationMs = processDurationMs,
            audioWavBytes = wavBytes,
            hadSpeechDetectedByVad = hadSpeechFromVad
        )
    }

    override fun cancelListening() {
        engineStartupJob?.cancel()
        engineStartupJob = null
        isFinalizingAtomic.set(false)
        speechDetectedInCurrentTurn = false
        timerJob?.cancel()
        timerJob = null
        audioCollectJob?.cancel()
        audioCollectJob = null
        audioRecordManager.cancelRecording()
        sileroVadDetector.reset()

        scope.launch {
            streamingEngine.stopStream()
        }

        _speechState.value = SpeechRecognitionState.IDLE
        _interimText.value = ""
        _finalText.value = ""
        _recordingDurationSeconds.value = 0
    }
}
