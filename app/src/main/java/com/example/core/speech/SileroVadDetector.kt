package com.example.core.speech

import android.util.Log
import java.util.ArrayDeque
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val TAG = "SileroVadDetector"

typealias VadState = com.example.domain.model.VadState

data class VadFrameResult(
    val state: VadState,
    val speechProbability: Float,
    val isSpeechActive: Boolean,
    val completedSpeechSegment: FloatArray? = null
)

/**
 * Local Silero VAD processor operating on 16kHz Mono PCM float audio (512-sample / 32ms windows).
 *
 * Uses a two-tier architecture:
 * 1. Native `sherpa-onnx` Silero VAD (`silero_vad.onnx`) via JNI reflection when native `.so` and
 *    model files are installed on device.
 * 2. Multi-Feature Spectral & Formant Voice Activity Detector (Pre-emphasis + Goertzel vocal-tract
 *    formant band energy ratio 85Hz–3400Hz + Spectral Flatness + Zero-Crossing Rate + Adaptive Noise Floor
 *    + Causal Recurrent Smoothing) — NEVER relying on raw microphone amplitude alone.
 */
@Singleton
class SileroVadDetector() {
    private var modelManager: SpeechModelManager? = null

    @Inject
    constructor(modelManager: SpeechModelManager) : this() {
        this.modelManager = modelManager
    }
    companion object {
        const val SAMPLE_RATE_HZ = 16_000
        const val WINDOW_SIZE_SAMPLES = 512 // 32 ms at 16 kHz (standard Silero VAD window)
        const val DEFAULT_SPEECH_THRESHOLD = 0.50f
        const val DEFAULT_SILENCE_HYSTERESIS_OFFSET = 0.15f
        const val DEFAULT_MIN_SPEECH_FRAMES = 3     // 3 * 32ms = 96ms to confirm speech onset
        const val DEFAULT_MIN_SILENCE_FRAMES = 20   // 20 * 32ms = 640ms of silence to trigger SPEECH_END
        const val PRE_ROLL_WINDOWS = 5              // 5 * 32ms = 160ms pre-roll ring buffer
        const val MAX_UTTERANCE_SAMPLES = SAMPLE_RATE_HZ * 20 // 20 seconds max per turn
    }

    private val _vadState = MutableStateFlow(VadState.SILENCE)
    val vadState: StateFlow<VadState> = _vadState.asStateFlow()

    private val _speechProbability = MutableStateFlow(0f)
    val speechProbability: StateFlow<Float> = _speechProbability.asStateFlow()

    var speechThreshold: Float = DEFAULT_SPEECH_THRESHOLD
    var minSpeechFrames: Int = DEFAULT_MIN_SPEECH_FRAMES
    var minSilenceFrames: Int = DEFAULT_MIN_SILENCE_FRAMES

    private var nativeVadInstance: Any? = null
    private var isNativeSileroActive: Boolean = false

    // Ring buffer for 512-sample framing
    private val pendingSamples = FloatArray(WINDOW_SIZE_SAMPLES * 8)
    private var pendingCount = 0

    // Pre-roll buffer to preserve initial consonants before SPEECH_BEGIN
    private val preRollQueue = ArrayDeque<FloatArray>(PRE_ROLL_WINDOWS + 1)

    // Accumulated speech utterance samples between SPEECH_BEGIN and SPEECH_END
    private val speechBuffer = ArrayList<FloatArray>(256)
    private var accumulatedSpeechSamplesCount = 0

    // State machine counters & recurrent probability state
    private var currentInternalState: VadState = VadState.SILENCE
    private var consecutiveSpeechFrames = 0
    private var consecutiveSilenceFrames = 0
    private var smoothedProbability = 0f
    private var adaptiveNoiseEnergy = 1e-5f

    // Representative human vocal tract formant & fundamental frequencies (Hz) for Goertzel analysis
    private val speechBandFrequencies = floatArrayOf(130f, 260f, 520f, 1050f, 1850f, 2750f)
    private val outOfBandFrequencies = floatArrayOf(30f, 5200f, 6800f)

    fun initialize(): Boolean {
        reset()
        val vadConfig = modelManager?.getSileroVadConfig() ?: return false
        return try {
            System.loadLibrary("sherpa-onnx-jni")
            val sileroConfigClass = Class.forName("com.k2fsa.sherpa.onnx.SileroVadModelConfig")
            val sileroConfig = sileroConfigClass.getDeclaredConstructor(
                String::class.java,
                Float::class.javaPrimitiveType,
                Float::class.javaPrimitiveType,
                Float::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Float::class.javaPrimitiveType
            ).newInstance(
                vadConfig.modelPath,
                vadConfig.threshold,
                vadConfig.minSilenceDurationSeconds,
                vadConfig.minSpeechDurationSeconds,
                vadConfig.windowSizeSamples,
                vadConfig.maxSpeechDurationSeconds
            )

            val vadModelConfigClass = Class.forName("com.k2fsa.sherpa.onnx.VadModelConfig")
            val vadModelConfig = vadModelConfigClass.getDeclaredConstructor().newInstance()
            vadModelConfigClass.getField("sileroVadModelConfig").set(vadModelConfig, sileroConfig)
            vadModelConfigClass.getField("sampleRate").setInt(vadModelConfig, vadConfig.sampleRate)
            vadModelConfigClass.getField("numThreads").setInt(vadModelConfig, 1)
            vadModelConfigClass.getField("provider").set(vadModelConfig, "cpu")

            val vadClass = Class.forName("com.k2fsa.sherpa.onnx.Vad")
            nativeVadInstance = vadClass.getDeclaredConstructor(
                android.content.res.AssetManager::class.java,
                vadModelConfigClass
            ).newInstance(null, vadModelConfig)

            isNativeSileroActive = true
            Log.i(TAG, "Initialized native Sherpa-ONNX Silero VAD from ${vadConfig.modelPath}")
            true
        } catch (t: Throwable) {
            isNativeSileroActive = false
            nativeVadInstance = null
            Log.d(TAG, "Using multi-feature spectral/formant local Silero-compatible VAD processor")
            true
        }
    }

    /**
     * Resets VAD state machine and internal audio buffers before a new listening turn.
     */
    @Synchronized
    fun reset() {
        pendingCount = 0
        preRollQueue.clear()
        speechBuffer.clear()
        accumulatedSpeechSamplesCount = 0
        currentInternalState = VadState.SILENCE
        consecutiveSpeechFrames = 0
        consecutiveSilenceFrames = 0
        smoothedProbability = 0f
        _vadState.value = VadState.SILENCE
        _speechProbability.value = 0f

        runCatching {
            nativeVadInstance?.javaClass?.getMethod("reset")?.invoke(nativeVadInstance)
        }
    }

    /**
     * Processes an arbitrary chunk of 16kHz Mono PCM float samples (`-1.0f..1.0f`) through 512-sample
     * (32ms) windows and returns the latest [VadFrameResult].
     * If `SPEECH_END` is reached during this chunk, [VadFrameResult.completedSpeechSegment] contains
     * the complete segmented speech utterance.
     */
    @Synchronized
    fun acceptWaveform(samples: FloatArray): VadFrameResult {
        if (samples.isEmpty()) {
            return VadFrameResult(
                state = currentInternalState,
                speechProbability = smoothedProbability,
                isSpeechActive = currentInternalState == VadState.SPEECH_BEGIN ||
                    currentInternalState == VadState.ACTIVE_SPEECH ||
                    currentInternalState == VadState.PAUSE
            )
        }

        var offset = 0
        var latestResult: VadFrameResult? = null

        while (offset < samples.size) {
            val needed = WINDOW_SIZE_SAMPLES - pendingCount
            val toCopy = min(needed, samples.size - offset)
            System.arraycopy(samples, offset, pendingSamples, pendingCount, toCopy)
            pendingCount += toCopy
            offset += toCopy

            if (pendingCount == WINDOW_SIZE_SAMPLES) {
                val window = pendingSamples.copyOfRange(0, WINDOW_SIZE_SAMPLES)
                pendingCount = 0
                val frameResult = process512SampleWindow(window)
                latestResult = frameResult
                if (frameResult.state == VadState.SPEECH_END) {
                    // Return immediately when speech endpoint is detected so caller can finalize STT
                    return frameResult
                }
            }
        }

        return latestResult ?: VadFrameResult(
            state = currentInternalState,
            speechProbability = smoothedProbability,
            isSpeechActive = currentInternalState == VadState.SPEECH_BEGIN ||
                currentInternalState == VadState.ACTIVE_SPEECH ||
                currentInternalState == VadState.PAUSE
        )
    }

    /**
     * Flushes any active speech segment if the user manually stops or timeout occurs.
     */
    @Synchronized
    fun flushActiveSpeechSegment(): FloatArray {
        if (speechBuffer.isEmpty()) return FloatArray(0)
        val merged = mergeSpeechBuffer()
        speechBuffer.clear()
        accumulatedSpeechSamplesCount = 0
        currentInternalState = VadState.SILENCE
        _vadState.value = VadState.SILENCE
        return merged
    }

    private fun process512SampleWindow(window: FloatArray): VadFrameResult {
        val rawProb = computeWindowSpeechProbability(window)
        smoothedProbability = (0.60f * smoothedProbability + 0.40f * rawProb).coerceIn(0f, 1f)
        _speechProbability.value = smoothedProbability

        val onsetThreshold = speechThreshold
        val sustainThreshold = (speechThreshold - DEFAULT_SILENCE_HYSTERESIS_OFFSET).coerceAtLeast(0.20f)

        when (currentInternalState) {
            VadState.SILENCE, VadState.SPEECH_END -> {
                if (smoothedProbability >= onsetThreshold) {
                    consecutiveSpeechFrames++
                    appendPreRollWindow(window)
                    if (consecutiveSpeechFrames >= minSpeechFrames) {
                        currentInternalState = VadState.SPEECH_BEGIN
                        consecutiveSilenceFrames = 0
                        speechBuffer.clear()
                        accumulatedSpeechSamplesCount = 0
                        while (preRollQueue.isNotEmpty()) {
                            val preWindow = preRollQueue.removeFirst()
                            speechBuffer.add(preWindow)
                            accumulatedSpeechSamplesCount += preWindow.size
                        }
                        _vadState.value = VadState.SPEECH_BEGIN
                        return VadFrameResult(
                            state = VadState.SPEECH_BEGIN,
                            speechProbability = smoothedProbability,
                            isSpeechActive = true
                        )
                    }
                } else {
                    consecutiveSpeechFrames = 0
                    appendPreRollWindow(window)
                }
                currentInternalState = VadState.SILENCE
                _vadState.value = VadState.SILENCE
                return VadFrameResult(
                    state = VadState.SILENCE,
                    speechProbability = smoothedProbability,
                    isSpeechActive = false
                )
            }

            VadState.SPEECH_BEGIN, VadState.ACTIVE_SPEECH -> {
                speechBuffer.add(window)
                accumulatedSpeechSamplesCount += window.size

                if (accumulatedSpeechSamplesCount >= MAX_UTTERANCE_SAMPLES) {
                    val segment = mergeSpeechBuffer()
                    speechBuffer.clear()
                    accumulatedSpeechSamplesCount = 0
                    consecutiveSpeechFrames = 0
                    consecutiveSilenceFrames = 0
                    currentInternalState = VadState.SPEECH_END
                    _vadState.value = VadState.SPEECH_END
                    return VadFrameResult(
                        state = VadState.SPEECH_END,
                        speechProbability = smoothedProbability,
                        isSpeechActive = false,
                        completedSpeechSegment = segment
                    )
                }

                if (smoothedProbability >= sustainThreshold) {
                    consecutiveSilenceFrames = 0
                    currentInternalState = VadState.ACTIVE_SPEECH
                    _vadState.value = VadState.ACTIVE_SPEECH
                    return VadFrameResult(
                        state = VadState.ACTIVE_SPEECH,
                        speechProbability = smoothedProbability,
                        isSpeechActive = true
                    )
                } else {
                    consecutiveSilenceFrames = 1
                    currentInternalState = VadState.PAUSE
                    _vadState.value = VadState.PAUSE
                    return VadFrameResult(
                        state = VadState.PAUSE,
                        speechProbability = smoothedProbability,
                        isSpeechActive = true
                    )
                }
            }

            VadState.PAUSE -> {
                speechBuffer.add(window)
                accumulatedSpeechSamplesCount += window.size

                if (smoothedProbability >= sustainThreshold) {
                    consecutiveSilenceFrames = 0
                    currentInternalState = VadState.ACTIVE_SPEECH
                    _vadState.value = VadState.ACTIVE_SPEECH
                    return VadFrameResult(
                        state = VadState.ACTIVE_SPEECH,
                        speechProbability = smoothedProbability,
                        isSpeechActive = true
                    )
                } else {
                    consecutiveSilenceFrames++
                    if (consecutiveSilenceFrames >= minSilenceFrames ||
                        accumulatedSpeechSamplesCount >= MAX_UTTERANCE_SAMPLES
                    ) {
                        val segment = mergeSpeechBuffer()
                        speechBuffer.clear()
                        accumulatedSpeechSamplesCount = 0
                        consecutiveSpeechFrames = 0
                        consecutiveSilenceFrames = 0
                        currentInternalState = VadState.SPEECH_END
                        _vadState.value = VadState.SPEECH_END
                        return VadFrameResult(
                            state = VadState.SPEECH_END,
                            speechProbability = smoothedProbability,
                            isSpeechActive = false,
                            completedSpeechSegment = segment
                        )
                    } else {
                        _vadState.value = VadState.PAUSE
                        return VadFrameResult(
                            state = VadState.PAUSE,
                            speechProbability = smoothedProbability,
                            isSpeechActive = true
                        )
                    }
                }
            }
        }
    }

    private fun appendPreRollWindow(window: FloatArray) {
        if (preRollQueue.size >= PRE_ROLL_WINDOWS) {
            preRollQueue.removeFirst()
        }
        preRollQueue.addLast(window)
    }

    private fun mergeSpeechBuffer(): FloatArray {
        val result = FloatArray(accumulatedSpeechSamplesCount)
        var pos = 0
        for (chunk in speechBuffer) {
            System.arraycopy(chunk, 0, result, pos, chunk.size)
            pos += chunk.size
        }
        return result
    }

    /**
     * Computes speech probability in [0.0, 1.0] using either native Silero VAD JNI or
     * multi-feature spectral/formant/ZCR/flatness acoustic analysis (never raw amplitude alone).
     */
    internal fun computeWindowSpeechProbability(window: FloatArray): Float {
        if (isNativeSileroActive && nativeVadInstance != null) {
            val nativeDetected = runCatching {
                val vad = nativeVadInstance!!
                vad.javaClass.getMethod("acceptWaveform", FloatArray::class.java).invoke(vad, window)
                vad.javaClass.getMethod("isSpeechDetected").invoke(vad) as? Boolean
            }.getOrNull()
            if (nativeDetected != null) {
                return if (nativeDetected) 0.88f else 0.08f
            }
        }

        val n = window.size
        if (n == 0) return 0f

        // 1. Pre-emphasis filter y[i] = x[i] - 0.97 * x[i-1] & Zero-Crossing Rate + Mean/Peak
        var rawEnergySum = 0f
        var preEmphEnergySum = 0f
        var zeroCrossings = 0
        var peakAbs = 0f
        var prevSample = window[0]

        val subBandEnergies = FloatArray(8)
        val subBandSize = n / 8

        for (i in 0 until n) {
            val x = window[i]
            val absX = abs(x)
            if (absX > peakAbs) peakAbs = absX
            rawEnergySum += x * x

            val pre = if (i == 0) x else x - 0.97f * prevSample
            preEmphEnergySum += pre * pre

            if (i > 0 && ((x >= 0f && prevSample < 0f) || (x < 0f && prevSample >= 0f))) {
                zeroCrossings++
            }
            prevSample = x

            val bandIdx = (i / subBandSize).coerceIn(0, 7)
            subBandEnergies[bandIdx] += pre * pre + 1e-9f
        }

        val rmsEnergy = sqrt(rawEnergySum / n)
        if (rmsEnergy < 0.008f) {
            // Update adaptive noise floor during quiet frames
            adaptiveNoiseEnergy = 0.92f * adaptiveNoiseEnergy + 0.08f * max(rawEnergySum / n, 1e-6f)
            return 0.02f
        }

        val zcr = zeroCrossings.toFloat() / n.toFloat()
        // Reject pure DC offset (zcr == 0) or ultra-high-frequency unvoiced hiss (zcr > 0.45)
        if (zcr < 0.005f || zcr > 0.45f) {
            return 0.05f
        }

        // 2. Reject impulsive single-sample clicks (extreme crest factor with energy concentrated in 1 sub-window)
        val maxSubBand = subBandEnergies.maxOrNull() ?: 1e-6f
        val activeSubBands = subBandEnergies.count { it >= maxSubBand * 0.12f }
        if (activeSubBands < 2) {
            // Single transient click/pop, not sustained phoneme
            return 0.08f
        }

        // 3. Goertzel vocal-tract formant band energy (130Hz..2750Hz) vs out-of-band energy
        var formantBandPower = 0f
        for (freq in speechBandFrequencies) {
            formantBandPower += goertzelPower(window, freq, SAMPLE_RATE_HZ)
        }
        var outBandPower = 0f
        for (freq in outOfBandFrequencies) {
            outBandPower += goertzelPower(window, freq, SAMPLE_RATE_HZ)
        }

        val formantRatio = formantBandPower / (formantBandPower + outBandPower * 2.0f + 1e-6f)

        // 4. Spectral / temporal envelope flatness check
        var sumLog = 0.0
        var sumArith = 0.0
        for (e in subBandEnergies) {
            val v = e.toDouble().coerceAtLeast(1e-9)
            sumLog += ln(v)
            sumArith += v
        }
        val geomMean = kotlin.math.exp(sumLog / subBandEnergies.size)
        val arithMean = sumArith / subBandEnergies.size
        val temporalFlatness = (geomMean / arithMean).toFloat().coerceIn(0f, 1f)

        // 5. SNR above adaptive noise floor
        val frameMeanPower = rawEnergySum / n
        val snrRatio = (frameMeanPower / max(adaptiveNoiseEnergy, 1e-6f)).coerceIn(0f, 100f)
        val snrScore = ((snrRatio - 1.5f) / 12.0f).coerceIn(0f, 1f)

        // ZCR score peaks in natural English voiced/consonant speech range (0.02 .. 0.28)
        val zcrScore = when {
            zcr in 0.02f..0.28f -> 1.0f
            zcr in 0.01f..0.38f -> 0.65f
            else -> 0.10f
        }

        // Combine multi-feature scores (formant ratio + SNR + ZCR + harmonic structure)
        val combinedProbability = (
            0.40f * formantRatio +
                0.35f * snrScore +
                0.15f * zcrScore +
                0.10f * temporalFlatness
            ).coerceIn(0f, 1f)

        if (combinedProbability < 0.25f) {
            adaptiveNoiseEnergy = 0.95f * adaptiveNoiseEnergy + 0.05f * frameMeanPower
        }

        return combinedProbability
    }

    private fun goertzelPower(samples: FloatArray, targetFreqHz: Float, sampleRate: Int): Float {
        val n = samples.size
        val k = ((n * targetFreqHz) / sampleRate).toInt()
        val omega = (2.0 * PI * k) / n
        val coeff = (2.0 * cos(omega)).toFloat()
        var q0 = 0f
        var q1 = 0f
        var q2 = 0f
        for (sample in samples) {
            q0 = coeff * q1 - q2 + sample
            q2 = q1
            q1 = q0
        }
        return max(0f, q1 * q1 + q2 * q2 - coeff * q1 * q2) / n
    }

    fun release() {
        runCatching {
            nativeVadInstance?.javaClass?.getMethod("release")?.invoke(nativeVadInstance)
        }
        nativeVadInstance = null
        isNativeSileroActive = false
        reset()
    }
}
