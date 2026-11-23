package uz.talkme.next.domain

import kotlin.math.max
import kotlin.math.sqrt

/** Adaptive energy endpoint detector. This is not a neural VAD. */
class UtteranceDetector(private val rate: Int = 16000) {
    private val preRoll = ShortArray(rate * 3 / 10)
    private val audio = FloatArray(rate * 20)
    private var prePosition = 0
    private var preSize = 0
    private var size = 0
    private var onset = 0
    private var voiced = 0
    private var quiet = 0
    private var active = false
    private var noise = 0.0015f
    var rms: Float = 0f
        private set

    fun accept(samples: ShortArray, count: Int): FloatArray? {
        require(count in 1..samples.size)
        var sum = 0.0
        for (i in 0 until count) {
            val value = samples[i].toDouble() / 32768.0
            sum += value * value
        }
        rms = sqrt(sum / count).toFloat()
        val speech = rms > max(0.0045f, (noise * 3f).coerceAtMost(0.035f))
        if (!active) {
            for (i in 0 until count) {
                preRoll[prePosition] = samples[i]
                prePosition = (prePosition + 1) % preRoll.size
                preSize = minOf(preSize + 1, preRoll.size)
            }
            if (!speech) { noise = noise * 0.97f + rms * 0.03f; onset = 0; return null }
            onset += count
            if (onset < rate * 8 / 100) return null
            active = true; voiced = onset; quiet = 0; size = 0
            val beginning = (prePosition - preSize + preRoll.size) % preRoll.size
            repeat(preSize) { audio[size++] = preRoll[(beginning + it) % preRoll.size] / 32768f }
        } else {
            for (i in 0 until count) if (size < audio.size) audio[size++] = samples[i] / 32768f
            if (speech) { voiced += count; quiet = 0 } else quiet += count
        }
        if (quiet >= rate * 8 / 10 || size >= audio.size) {
            val result = if (voiced >= rate / 4) audio.copyOf(size) else null
            active = false; onset = 0; voiced = 0; quiet = 0; size = 0; preSize = 0
            return result
        }
        return null
    }
}
