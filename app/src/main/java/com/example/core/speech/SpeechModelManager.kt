package com.example.core.speech

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

private const val TAG = "SpeechModelManager"

sealed class ModelDownloadStatus {
    data object Idle : ModelDownloadStatus()
    data object Checking : ModelDownloadStatus()
    data class Downloading(
        val progressPercent: Int,
        val bytesDownloaded: Long,
        val totalBytes: Long
    ) : ModelDownloadStatus()
    data object Extracting : ModelDownloadStatus()
    data object Ready : ModelDownloadStatus()
    data class Error(val message: String) : ModelDownloadStatus()
}

data class SherpaModelPaths(
    val encoderPath: String,
    val decoderPath: String,
    val joinerPath: String,
    val tokensPath: String
)

data class SileroVadModelConfigData(
    val modelPath: String,
    val threshold: Float = 0.50f,
    val minSilenceDurationSeconds: Float = 0.65f,
    val minSpeechDurationSeconds: Float = 0.10f,
    val windowSizeSamples: Int = 512,
    val maxSpeechDurationSeconds: Float = 20.0f,
    val sampleRate: Int = 16_000
)

/**
 * Manages local on-device model storage and verification for:
 * - Silero VAD (`silero_vad.onnx`)
 * - Sherpa-ONNX Streaming Zipformer English (`encoder.int8.onnx`, `decoder.int8.onnx`, `joiner.int8.onnx`, `tokens.txt`)
 * - Sherpa-ONNX Offline Zipformer English (`encoder.int8.onnx`, `decoder.int8.onnx`, `joiner.int8.onnx`, `tokens.txt`)
 *
 * Strictly forbids generating dummy/placeholder `.onnx` files.
 * Performs ZERO network calls during speech recognition.
 */
@Singleton
class SpeechModelManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val _downloadStatus = MutableStateFlow<ModelDownloadStatus>(ModelDownloadStatus.Idle)
    val downloadStatus: StateFlow<ModelDownloadStatus> = _downloadStatus.asStateFlow()

    private val modelsRootDir: File by lazy {
        File(context.filesDir, "sherpa_onnx_models").apply { mkdirs() }
    }

    private val vadDir: File by lazy {
        File(modelsRootDir, "vad").apply { mkdirs() }
    }

    private val streamingDir: File by lazy {
        File(modelsRootDir, "streaming_zipformer_en").apply { mkdirs() }
    }

    private val offlineDir: File by lazy {
        File(modelsRootDir, "offline_zipformer_en").apply { mkdirs() }
    }

    init {
        purgeInvalidPlaceholderFiles()
    }

    /**
     * Removes any 1KB/512B dummy files that might have been created by legacy placeholder code.
     */
    private fun purgeInvalidPlaceholderFiles() {
        runCatching {
            listOf(streamingDir, offlineDir).forEach { dir ->
                val enc = File(dir, "encoder.int8.onnx")
                if (enc.exists() && enc.length() <= 2048L) {
                    dir.listFiles()?.forEach { it.delete() }
                }
            }
        }
    }

    fun isVadModelReady(): Boolean {
        val vadFile = File(vadDir, "silero_vad.onnx")
        return isValidOnnxFile(vadFile)
    }

    fun isStreamingModelReady(): Boolean {
        return hasValidTransducerModelFiles(streamingDir)
    }

    fun isOfflineModelReady(): Boolean {
        return hasValidTransducerModelFiles(offlineDir)
    }

    fun isModelReady(): Boolean {
        return isStreamingModelReady() || isOfflineModelReady()
    }

    fun getSileroVadConfig(): SileroVadModelConfigData? {
        val vadFile = File(vadDir, "silero_vad.onnx")
        if (!isValidOnnxFile(vadFile)) return null
        return SileroVadModelConfigData(
            modelPath = vadFile.absolutePath
        )
    }

    fun getStreamingModelPaths(): SherpaModelPaths? {
        if (!hasValidTransducerModelFiles(streamingDir)) return null
        return SherpaModelPaths(
            encoderPath = resolveOnnxFile(streamingDir, "encoder").absolutePath,
            decoderPath = resolveOnnxFile(streamingDir, "decoder").absolutePath,
            joinerPath = resolveOnnxFile(streamingDir, "joiner").absolutePath,
            tokensPath = File(streamingDir, "tokens.txt").absolutePath
        )
    }

    fun getOfflineModelPaths(): SherpaModelPaths? {
        if (!hasValidTransducerModelFiles(offlineDir)) return null
        return SherpaModelPaths(
            encoderPath = resolveOnnxFile(offlineDir, "encoder").absolutePath,
            decoderPath = resolveOnnxFile(offlineDir, "decoder").absolutePath,
            joinerPath = resolveOnnxFile(offlineDir, "joiner").absolutePath,
            tokensPath = File(offlineDir, "tokens.txt").absolutePath
        )
    }

    /**
     * Checks internal storage and APK `assets/sherpa_models/` for bundled offline models.
     * Does not perform network requests during active conversation.
     */
    suspend fun ensureModelsAvailable(): Boolean = withContext(Dispatchers.IO) {
        _downloadStatus.value = ModelDownloadStatus.Checking
        purgeInvalidPlaceholderFiles()

        if (isModelReady()) {
            _downloadStatus.value = ModelDownloadStatus.Ready
            return@withContext true
        }

        val copiedFromAssets = copyBundledModelsFromAssets()
        if (copiedFromAssets && isModelReady()) {
            _downloadStatus.value = ModelDownloadStatus.Ready
            return@withContext true
        }

        _downloadStatus.value = ModelDownloadStatus.Error(
            "Local Sherpa-ONNX model files not found in assets or internal storage."
        )
        false
    }

    private fun copyBundledModelsFromAssets(): Boolean {
        return try {
            val assetManager = context.assets
            copyAssetSubfolderIfPresent(assetManager, "sherpa_models/vad", vadDir)
            copyAssetSubfolderIfPresent(assetManager, "sherpa_models/streaming", streamingDir)
            copyAssetSubfolderIfPresent(assetManager, "sherpa_models/offline", offlineDir)
            isModelReady()
        } catch (e: Exception) {
            Log.d(TAG, "No bundled sherpa_models in APK assets: ${e.message}")
            false
        }
    }

    private fun copyAssetSubfolderIfPresent(
        assetManager: android.content.res.AssetManager,
        assetPath: String,
        targetDir: File
    ) {
        val entries = runCatching { assetManager.list(assetPath) }.getOrNull() ?: return
        if (entries.isEmpty()) return
        targetDir.mkdirs()
        for (fileName in entries) {
            val destFile = File(targetDir, fileName)
            if (destFile.exists() && destFile.length() > 4096L) continue
            runCatching {
                assetManager.open("$assetPath/$fileName").use { input ->
                    FileOutputStream(destFile).use { output ->
                        input.copyTo(output)
                    }
                }
            }
        }
    }

    private fun resolveOnnxFile(dir: File, prefix: String): File {
        val exactInt8 = File(dir, "$prefix.int8.onnx")
        if (exactInt8.exists()) return exactInt8
        val exactFp32 = File(dir, "$prefix.onnx")
        if (exactFp32.exists()) return exactFp32
        val matched = dir.listFiles()?.firstOrNull {
            it.name.startsWith(prefix) && it.name.endsWith(".onnx")
        }
        return matched ?: exactInt8
    }

    private fun hasValidTransducerModelFiles(dir: File): Boolean {
        if (!dir.exists() || !dir.isDirectory) return false
        val encoder = resolveOnnxFile(dir, "encoder")
        val decoder = resolveOnnxFile(dir, "decoder")
        val joiner = resolveOnnxFile(dir, "joiner")
        val tokens = File(dir, "tokens.txt")

        return isValidOnnxFile(encoder) &&
            isValidOnnxFile(decoder) &&
            isValidOnnxFile(joiner) &&
            isValidTokensFile(tokens)
    }

    /**
     * Verifies that [file] exists, exceeds minimum real model threshold (> 4 KB),
     * and starts with a valid protobuf wire tag (`0x08` ir_version field).
     */
    fun isValidOnnxFile(file: File): Boolean {
        if (!file.exists() || !file.isFile || file.length() <= 4096L) return false
        return try {
            val header = ByteArray(4)
            val read = file.inputStream().use { it.read(header) }
            read == 4 && header[0] == 0x08.toByte()
        } catch (_: Exception) {
            false
        }
    }

    fun isValidTokensFile(file: File): Boolean {
        if (!file.exists() || !file.isFile || file.length() <= 16L) return false
        return try {
            file.bufferedReader().useLines { lines ->
                lines.take(5).any { it.isNotBlank() }
            }
        } catch (_: Exception) {
            false
        }
    }
}
