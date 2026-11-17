package com.example.data.repository

import com.example.core.audio.AudioRecordManager
import com.example.core.audio.AudioRecordingState
import com.example.core.speech.SileroVadDetector
import com.example.core.speech.VadState
import com.example.domain.repository.SpeechRecognitionResult
import com.example.domain.repository.SpeechRecognitionState
import com.example.domain.repository.SpeechRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
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
    private val captureLock = Any()
    private var capture: Capture? = null

    private class Capture(val keepRecorderOpen: Boolean) {
        val startedAt = android.os.SystemClock.elapsedRealtime()
        val result = CompletableDeferred<SpeechRecognitionResult>()
        var collector: Job? = null
        var timer: Job? = null
        var finalizer: Job? = null
        var heardSpeech = false
    }

    override fun hasRecordPermission(): Boolean = audioRecordManager.hasRecordPermission()

    override fun setMuted(muted: Boolean) {
        audioRecordManager.setMuted(muted)
    }

    override fun startListening(): Boolean = startListening(keepRecorderOpen = false)

    override fun startLiveListening(): Boolean = startListening(keepRecorderOpen = true)

    private fun startListening(keepRecorderOpen: Boolean): Boolean = synchronized(captureLock) {
        if (_speechState.value == SpeechRecognitionState.LISTENING_PASS_1) return true
        if (_speechState.value == SpeechRecognitionState.PROCESSING_PASS_2) return false

        _interimText.value = ""
        _finalText.value = ""
        _recordingDurationSeconds.value = 0

        sileroVadDetector.initialize()
        sileroVadDetector.reset()

        if (!audioRecordManager.startRecording(streamOnly = keepRecorderOpen)) {
            _speechState.value = SpeechRecognitionState.ERROR
            return false
        }

        val turn = Capture(keepRecorderOpen)
        capture = turn
        _speechState.value = SpeechRecognitionState.LISTENING_PASS_1
        turn.collector = scope.launch(start = CoroutineStart.UNDISPATCHED) {
            audioRecordManager.sampleChunkFlow.collect { chunk ->
                synchronized(captureLock) {
                    if (capture !== turn || turn.finalizer != null) return@collect
                    val vadResult = sileroVadDetector.acceptWaveform(chunk)
                    turn.heardSpeech = turn.heardSpeech || vadResult.isSpeechActive
                    if (vadResult.state == VadState.SPEECH_END) {
                        finalize(turn, automatically = true, completedSpeech = vadResult.completedSpeechSegment)
                    }
                }
            }
        }
        turn.timer = scope.launch {
            while (isActive) {
                delay(1_000)
                val timedOut = synchronized(captureLock) {
                    if (capture !== turn || turn.finalizer != null) return@launch
                    _recordingDurationSeconds.value += 1
                    val recorderFailed = audioRecordManager.recordingState.value == AudioRecordingState.ERROR
                    val limitReached = recorderFailed || _recordingDurationSeconds.value >= MAX_CAPTURE_SECONDS
                    if (limitReached) finalize(turn, automatically = true)
                    limitReached
                }
                if (timedOut) return@launch
            }
        }
        true
    }

    override suspend fun stopListening(): SpeechRecognitionResult {
        val result = synchronized(captureLock) {
            capture?.let { turn ->
                finalize(turn, automatically = false)
                turn.result
            }
        }
        return result?.await() ?: SpeechRecognitionResult(
            recognizedText = "", isFinal = true, recordingDurationMs = 0, processingDurationMs = 0,
            hadSpeechDetectedByVad = false
        )
    }

    /** Called with [captureLock] held. The finalizer is a sibling of the capture jobs. */
    private fun finalize(turn: Capture, automatically: Boolean, completedSpeech: FloatArray? = null) {
        if (turn.finalizer != null) return
        val startedFinalizingAt = android.os.SystemClock.elapsedRealtime()
        val segment = completedSpeech ?: sileroVadDetector.flushActiveSpeechSegment()
        val hadSpeech = turn.heardSpeech || segment.isNotEmpty()
        val recorderFailed = audioRecordManager.recordingState.value == AudioRecordingState.ERROR
        _speechState.value = SpeechRecognitionState.PROCESSING_PASS_2

        turn.finalizer = scope.launch {
            try {
                turn.timer?.cancelAndJoin()
                turn.collector?.cancelAndJoin()
                val wavBytes = if (turn.keepRecorderOpen && !recorderFailed) {
                    audioRecordManager.encodeSpeechSegment(segment)
                } else {
                    audioRecordManager.finishRecordingWav()
                }
                val result = SpeechRecognitionResult(
                    recognizedText = "",
                    isFinal = true,
                    recordingDurationMs = startedFinalizingAt - turn.startedAt,
                    processingDurationMs = android.os.SystemClock.elapsedRealtime() - startedFinalizingAt,
                    audioWavBytes = wavBytes,
                    hadSpeechDetectedByVad = hadSpeech,
                    sttErrorReason = if (recorderFailed) {
                        "Mikrofondan ovoz olinmadi. Qayta urinib ko‘ring."
                    } else null,
                    finalizedAutomatically = automatically
                )
                publishResult(turn, result, automatically)
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (error: Exception) {
                android.util.Log.w("SpeechRepository", "Could not finalize microphone recording", error)
                publishResult(turn, SpeechRecognitionResult(
                    recognizedText = "",
                    isFinal = true,
                    recordingDurationMs = startedFinalizingAt - turn.startedAt,
                    processingDurationMs = 0,
                    sttErrorReason = "Ovoz yozuvini yuborib bo‘lmadi. Qayta urinib ko‘ring.",
                    finalizedAutomatically = automatically
                ), automatically)
            }
        }
    }

    private fun publishResult(turn: Capture, result: SpeechRecognitionResult, automatically: Boolean) {
        synchronized(captureLock) {
            if (capture !== turn) return
            _speechState.value = SpeechRecognitionState.COMPLETED
            turn.result.complete(result)
            if (automatically) _utteranceFinalizedEvents.tryEmit(result)
        }
    }

    override fun discardListeningTurn() = resetListening(releaseRecorder = false)

    override fun cancelListening() = resetListening(releaseRecorder = true)

    private fun resetListening(releaseRecorder: Boolean) {
        synchronized(captureLock) {
            capture?.let { turn ->
                turn.finalizer?.cancel()
                turn.timer?.cancel()
                turn.collector?.cancel()
                turn.result.cancel()
            }
            capture = null
            if (releaseRecorder) audioRecordManager.cancelRecording()
            sileroVadDetector.reset()
            _speechState.value = SpeechRecognitionState.IDLE
            _interimText.value = ""
            _finalText.value = ""
            _recordingDurationSeconds.value = 0
        }
    }

    private companion object {
        const val MAX_CAPTURE_SECONDS = 25
    }
}
