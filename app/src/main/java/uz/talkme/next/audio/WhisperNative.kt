package uz.talkme.next.audio

/** All JNI operations except the atomic cancellation epoch belong to one decoder worker. */
internal object WhisperNative {
    init { System.loadLibrary("talkme_whisper") }
    external fun load(path: String): Long
    external fun setEpoch(handle: Long, epoch: Long)
    external fun transcribe(handle: Long, samples: FloatArray, epoch: Long, threads: Int): String
    external fun release(handle: Long)
}
