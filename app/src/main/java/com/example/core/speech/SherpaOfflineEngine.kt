package com.example.core.speech

import android.content.res.AssetManager
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

private const val TAG = "SherpaOfflineEngine"
private const val SAMPLE_RATE_HZ = 16_000

/**
 * Pluggable local offline ASR decoder interface used by [SherpaOfflineEngine]
 * when binding to `com.k2fsa.sherpa.onnx.OfflineRecognizer` JNI or injected test decoders.
 */
interface LocalOfflineAsrDecoder {
    fun decodeUtterance(sampleRate: Int, samples: FloatArray): String
    fun release()
}

/**
 * Pass 2: Offline Batch Zipformer STT Engine (`Sherpa-ONNX`).
 *
 * - Decodes the complete VAD-segmented 16kHz Mono PCM float utterance buffer on `Dispatchers.Default`.
 * - Dynamically binds to `com.k2fsa.sherpa.onnx.OfflineRecognizer` and `OfflineStream` when
 *   `libsherpa-onnx-jni.so` and verified ONNX transducer models are present in [SpeechModelManager].
 * - Preserves exact recognized output verbatim and NEVER fabricates or substitutes fallback text.
 */
@Singleton
class SherpaOfflineEngine @Inject constructor(
    private val modelManager: SpeechModelManager
) {
    private val decodeMutex = Mutex()

    @Volatile
    var isNativeReady: Boolean = false
        private set

    private var nativeOfflineRecognizer: Any? = null

    /**
     * Optional override decoder for deterministic local JVM / Robolectric testing.
     * In production, remains `null` and uses the native Sherpa-ONNX offline recognizer.
     */
    @Volatile
    var customDecoderForTesting: LocalOfflineAsrDecoder? = null

    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        decodeMutex.withLock {
            customDecoderForTesting?.let {
                isNativeReady = true
                return@withLock true
            }

            if (isNativeReady && nativeOfflineRecognizer != null) {
                return@withLock true
            }

            modelManager.ensureModelsAvailable()
            val paths = modelManager.getOfflineModelPaths() ?: modelManager.getStreamingModelPaths()
            if (paths == null) {
                isNativeReady = false
                return@withLock false
            }

            try {
                System.loadLibrary("sherpa-onnx-jni")
                nativeOfflineRecognizer = createNativeOfflineRecognizer(paths)
                isNativeReady = nativeOfflineRecognizer != null
                Log.i(TAG, "Sherpa-ONNX OfflineRecognizer initialized (ready=$isNativeReady)")
                isNativeReady
            } catch (t: Throwable) {
                Log.w(TAG, "Sherpa-ONNX native offline library not loaded: ${t.message}")
                nativeOfflineRecognizer = null
                isNativeReady = false
                false
            }
        }
    }

    /**
     * Decodes a complete VAD-segmented utterance (`16,000 Hz` float PCM in `-1.0f..1.0f`)
     * and returns the exact recognized transcript string, or `""` if no speech was recognized.
     * Never fabricates fallback sentences.
     */
    suspend fun decodeFullAudio(fullAudioSamples: FloatArray): String = withContext(Dispatchers.Default) {
        if (fullAudioSamples.isEmpty()) return@withContext ""

        if (!isNativeReady) {
            initialize()
        }

        decodeMutex.withLock {
            customDecoderForTesting?.let { decoder ->
                return@withLock decoder.decodeUtterance(SAMPLE_RATE_HZ, fullAudioSamples).trim()
            }

            val recognizer = nativeOfflineRecognizer ?: return@withLock ""
            var stream: Any? = null
            return@withLock try {
                stream = recognizer.javaClass.getMethod("createStream").invoke(recognizer)
                if (stream == null) return@withLock ""

                stream.javaClass.getMethod(
                    "acceptWaveform",
                    FloatArray::class.java,
                    Int::class.javaPrimitiveType
                ).invoke(stream, fullAudioSamples, SAMPLE_RATE_HZ)

                recognizer.javaClass.getMethod("decode", stream.javaClass).invoke(recognizer, stream)

                val resultObj = recognizer.javaClass
                    .getMethod("getResult", stream.javaClass)
                    .invoke(recognizer, stream)
                val text = resultObj?.javaClass?.getMethod("getText")?.invoke(resultObj) as? String
                    ?: resultObj?.javaClass?.getField("text")?.get(resultObj) as? String
                text?.trim().orEmpty()
            } catch (t: Throwable) {
                Log.w(TAG, "OfflineStream decodeFullAudio error: ${t.message}")
                ""
            } finally {
                runCatching {
                    stream?.javaClass?.getMethod("release")?.invoke(stream)
                }
            }
        }
    }

    private fun createNativeOfflineRecognizer(paths: SherpaModelPaths): Any? {
        val featConfigClass = Class.forName("com.k2fsa.sherpa.onnx.FeatureConfig")
        val featConfig = featConfigClass.getDeclaredConstructor(
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType
        ).newInstance(SAMPLE_RATE_HZ, 80)

        val transducerClass = Class.forName("com.k2fsa.sherpa.onnx.OfflineTransducerModelConfig")
        val transducerConfig = transducerClass.getDeclaredConstructor().newInstance()
        transducerClass.getField("encoder").set(transducerConfig, paths.encoderPath)
        transducerClass.getField("decoder").set(transducerConfig, paths.decoderPath)
        transducerClass.getField("joiner").set(transducerConfig, paths.joinerPath)

        val modelConfigClass = Class.forName("com.k2fsa.sherpa.onnx.OfflineModelConfig")
        val modelConfig = modelConfigClass.getDeclaredConstructor().newInstance()
        modelConfigClass.getField("transducer").set(modelConfig, transducerConfig)
        modelConfigClass.getField("tokens").set(modelConfig, paths.tokensPath)
        modelConfigClass.getField("numThreads").setInt(modelConfig, 2)
        modelConfigClass.getField("provider").set(modelConfig, "cpu")
        modelConfigClass.getField("modelType").set(modelConfig, "transducer")

        val recognizerConfigClass = Class.forName("com.k2fsa.sherpa.onnx.OfflineRecognizerConfig")
        val recognizerConfig = recognizerConfigClass.getDeclaredConstructor().newInstance()
        recognizerConfigClass.getField("featConfig").set(recognizerConfig, featConfig)
        recognizerConfigClass.getField("modelConfig").set(recognizerConfig, modelConfig)
        recognizerConfigClass.getField("decodingMethod").set(recognizerConfig, "greedy_search")

        val offlineRecognizerClass = Class.forName("com.k2fsa.sherpa.onnx.OfflineRecognizer")
        return offlineRecognizerClass.getDeclaredConstructor(
            AssetManager::class.java,
            recognizerConfigClass
        ).newInstance(null, recognizerConfig)
    }

    fun release() {
        customDecoderForTesting?.release()
        runCatching {
            nativeOfflineRecognizer?.javaClass?.getMethod("release")?.invoke(nativeOfflineRecognizer)
        }
        nativeOfflineRecognizer = null
        isNativeReady = false
    }
}
