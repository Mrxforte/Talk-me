package com.example.core.speech

import android.content.res.AssetManager
import android.util.Log
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

private const val TAG = "SherpaStreamingEngine"
private const val SAMPLE_RATE_HZ = 16_000

/**
 * Pluggable local streaming ASR decoder interface used by [SherpaStreamingEngine]
 * when binding to `com.k2fsa.sherpa.onnx.OnlineRecognizer` JNI or injected test decoders.
 */
interface LocalStreamingAsrDecoder {
    fun startStream()
    fun acceptWaveform(sampleRate: Int, samples: FloatArray): String
    fun finishStream(): String
    fun release()
}

/**
 * Pass 1: Real-time Streaming Zipformer STT Engine (`Sherpa-ONNX`).
 *
 * - Processes incoming 16kHz Mono PCM float chunks on `Dispatchers.Default` / `Dispatchers.IO`.
 * - Dynamically binds to `com.k2fsa.sherpa.onnx.OnlineRecognizer` and `OnlineStream` when
 *   `libsherpa-onnx-jni.so` and verified ONNX transducer models are present in [SpeechModelManager].
 * - Strictly preserves recognized text verbatim and NEVER invents or fabricates fallback words.
 */
@Singleton
class SherpaStreamingEngine @Inject constructor(
    private val modelManager: SpeechModelManager
) {
    private val engineMutex = Mutex()

    @Volatile
    var isNativeReady: Boolean = false
        private set

    private var nativeRecognizer: Any? = null
    private var activeNativeStream: Any? = null
    private var tokensVocabulary: Map<Int, String> = emptyMap()

    /**
     * Optional override decoder for deterministic local JVM / Robolectric testing.
     * In production, remains `null` and uses the native Sherpa-ONNX recognizer.
     */
    @Volatile
    var customDecoderForTesting: LocalStreamingAsrDecoder? = null

    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        engineMutex.withLock {
            customDecoderForTesting?.let {
                isNativeReady = true
                return@withLock true
            }

            if (isNativeReady && nativeRecognizer != null) {
                return@withLock true
            }

            modelManager.ensureModelsAvailable()
            val paths = modelManager.getStreamingModelPaths()
            if (paths == null) {
                isNativeReady = false
                return@withLock false
            }

            tokensVocabulary = parseTokensFile(paths.tokensPath)

            try {
                System.loadLibrary("sherpa-onnx-jni")
                nativeRecognizer = createNativeOnlineRecognizer(paths)
                isNativeReady = nativeRecognizer != null
                Log.i(TAG, "Sherpa-ONNX OnlineRecognizer initialized (ready=$isNativeReady)")
                isNativeReady
            } catch (t: Throwable) {
                Log.w(TAG, "Sherpa-ONNX native streaming library not loaded: ${t.message}")
                nativeRecognizer = null
                isNativeReady = false
                false
            }
        }
    }

    suspend fun startStream(): Boolean = withContext(Dispatchers.IO) {
        if (!isNativeReady) {
            initialize()
        }
        engineMutex.withLock {
            customDecoderForTesting?.let { decoder ->
                decoder.startStream()
                return@withLock true
            }

            val recognizer = nativeRecognizer ?: return@withLock false
            return@withLock try {
                releaseActiveStreamLocked()
                val createStreamMethod = recognizer.javaClass.getMethod(
                    "createStream",
                    String::class.java
                )
                activeNativeStream = createStreamMethod.invoke(recognizer, "")
                activeNativeStream != null
            } catch (t: Throwable) {
                Log.w(TAG, "Failed to create OnlineStream: ${t.message}")
                activeNativeStream = null
                false
            }
        }
    }

    /**
     * Feeds 16kHz float samples into the active Sherpa-ONNX `OnlineStream`, runs transducer
     * decoding frames while `isReady(stream)` is true, and returns the exact partial transcript.
     * Never invents words if no speech is recognized.
     */
    suspend fun acceptSamples(samples: FloatArray): String = withContext(Dispatchers.Default) {
        if (samples.isEmpty()) return@withContext ""

        engineMutex.withLock {
            customDecoderForTesting?.let { decoder ->
                return@withLock decoder.acceptWaveform(SAMPLE_RATE_HZ, samples).trim()
            }

            val recognizer = nativeRecognizer ?: return@withLock ""
            val stream = activeNativeStream ?: return@withLock ""

            return@withLock try {
                stream.javaClass.getMethod(
                    "acceptWaveform",
                    FloatArray::class.java,
                    Int::class.javaPrimitiveType
                ).invoke(stream, samples, SAMPLE_RATE_HZ)

                val isReadyMethod = recognizer.javaClass.getMethod("isReady", stream.javaClass)
                val decodeMethod = recognizer.javaClass.getMethod("decode", stream.javaClass)

                while (isReadyMethod.invoke(recognizer, stream) == true) {
                    decodeMethod.invoke(recognizer, stream)
                }

                val resultObj = recognizer.javaClass
                    .getMethod("getResult", stream.javaClass)
                    .invoke(recognizer, stream)
                val text = resultObj?.javaClass?.getMethod("getText")?.invoke(resultObj) as? String
                    ?: resultObj?.javaClass?.getField("text")?.get(resultObj) as? String
                text?.trim().orEmpty()
            } catch (t: Throwable) {
                Log.w(TAG, "OnlineStream acceptSamples error: ${t.message}")
                ""
            }
        }
    }

    /**
     * Signals `inputFinished()` on the active `OnlineStream`, drains remaining frames,
     * and returns the exact final streaming transcript.
     */
    suspend fun stopStream(): String = withContext(Dispatchers.Default) {
        engineMutex.withLock {
            customDecoderForTesting?.let { decoder ->
                return@withLock decoder.finishStream().trim()
            }

            val recognizer = nativeRecognizer ?: return@withLock ""
            val stream = activeNativeStream ?: return@withLock ""

            return@withLock try {
                stream.javaClass.getMethod("inputFinished").invoke(stream)

                val isReadyMethod = recognizer.javaClass.getMethod("isReady", stream.javaClass)
                val decodeMethod = recognizer.javaClass.getMethod("decode", stream.javaClass)
                while (isReadyMethod.invoke(recognizer, stream) == true) {
                    decodeMethod.invoke(recognizer, stream)
                }

                val resultObj = recognizer.javaClass
                    .getMethod("getResult", stream.javaClass)
                    .invoke(recognizer, stream)
                val text = resultObj?.javaClass?.getMethod("getText")?.invoke(resultObj) as? String
                    ?: resultObj?.javaClass?.getField("text")?.get(resultObj) as? String
                text?.trim().orEmpty()
            } catch (t: Throwable) {
                Log.w(TAG, "OnlineStream stopStream error: ${t.message}")
                ""
            } finally {
                releaseActiveStreamLocked()
            }
        }
    }

    private fun createNativeOnlineRecognizer(paths: SherpaModelPaths): Any? {
        val featConfigClass = Class.forName("com.k2fsa.sherpa.onnx.FeatureConfig")
        val featConfig = featConfigClass.getDeclaredConstructor(
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType
        ).newInstance(SAMPLE_RATE_HZ, 80)

        val transducerClass = Class.forName("com.k2fsa.sherpa.onnx.OnlineTransducerModelConfig")
        val transducerConfig = transducerClass.getDeclaredConstructor().newInstance()
        transducerClass.getField("encoder").set(transducerConfig, paths.encoderPath)
        transducerClass.getField("decoder").set(transducerConfig, paths.decoderPath)
        transducerClass.getField("joiner").set(transducerConfig, paths.joinerPath)

        val modelConfigClass = Class.forName("com.k2fsa.sherpa.onnx.OnlineModelConfig")
        val modelConfig = modelConfigClass.getDeclaredConstructor().newInstance()
        modelConfigClass.getField("transducer").set(modelConfig, transducerConfig)
        modelConfigClass.getField("tokens").set(modelConfig, paths.tokensPath)
        modelConfigClass.getField("numThreads").setInt(modelConfig, 2)
        modelConfigClass.getField("provider").set(modelConfig, "cpu")
        modelConfigClass.getField("modelType").set(modelConfig, "zipformer")

        val recognizerConfigClass = Class.forName("com.k2fsa.sherpa.onnx.OnlineRecognizerConfig")
        val recognizerConfig = recognizerConfigClass.getDeclaredConstructor().newInstance()
        recognizerConfigClass.getField("featConfig").set(recognizerConfig, featConfig)
        recognizerConfigClass.getField("modelConfig").set(recognizerConfig, modelConfig)
        recognizerConfigClass.getField("enableEndpoint").setBoolean(recognizerConfig, true)
        recognizerConfigClass.getField("decodingMethod").set(recognizerConfig, "greedy_search")

        val onlineRecognizerClass = Class.forName("com.k2fsa.sherpa.onnx.OnlineRecognizer")
        return onlineRecognizerClass.getDeclaredConstructor(
            AssetManager::class.java,
            recognizerConfigClass
        ).newInstance(null, recognizerConfig)
    }

    private fun parseTokensFile(tokensPath: String): Map<Int, String> {
        val file = File(tokensPath)
        if (!file.exists()) return emptyMap()
        val map = HashMap<Int, String>()
        runCatching {
            file.forEachLine { line ->
                val trimmed = line.trim()
                if (trimmed.isNotEmpty()) {
                    val lastSpace = trimmed.lastIndexOf(' ')
                    if (lastSpace > 0) {
                        val symbol = trimmed.substring(0, lastSpace)
                        val id = trimmed.substring(lastSpace + 1).toIntOrNull()
                        if (id != null) {
                            map[id] = symbol
                        }
                    }
                }
            }
        }
        return map
    }

    private fun releaseActiveStreamLocked() {
        runCatching {
            activeNativeStream?.javaClass?.getMethod("release")?.invoke(activeNativeStream)
        }
        activeNativeStream = null
    }

    fun release() {
        customDecoderForTesting?.release()
        releaseActiveStreamLocked()
        runCatching {
            nativeRecognizer?.javaClass?.getMethod("release")?.invoke(nativeRecognizer)
        }
        nativeRecognizer = null
        isNativeReady = false
    }
}
