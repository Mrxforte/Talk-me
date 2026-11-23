package uz.talkme.next

import org.junit.Assert.*
import org.junit.Test
import uz.talkme.next.domain.UtteranceDetector
import kotlin.math.sin

class UtteranceDetectorTest {
    private val silence = ShortArray(320)
    private val speech = ShortArray(320) { (sin(it * 0.2) * 3000).toInt().toShort() }
    @Test fun `continuous silence produces no transcription work`() {
        val detector = UtteranceDetector()
        repeat(3000) { assertNull(detector.accept(silence, 320)) }
    }
    @Test fun `short click is rejected`() {
        val detector = UtteranceDetector()
        repeat(10) { detector.accept(silence, 320) }
        detector.accept(speech, 320)
        repeat(60) { assertNull(detector.accept(silence, 320)) }
    }
    @Test fun `speech completes after silence and preserves normalized PCM with pre roll`() {
        val detector = UtteranceDetector()
        repeat(20) { detector.accept(silence, 320) }
        repeat(30) { assertNull(detector.accept(speech, 320)) }
        var result: FloatArray? = null
        repeat(40) { val value = detector.accept(silence, 320); if (value != null) result = value }
        val pcm = checkNotNull(result)
        assertTrue(pcm.size > 30 * 320)
        assertTrue(pcm.any { it > 0.05f })
        assertTrue(pcm.all { it in -1f..1f })
        repeat(50) { assertNull(detector.accept(silence, 320)) }
    }
    @Test fun `long utterance has a bounded twenty second capture buffer`() {
        val detector = UtteranceDetector()
        var result: FloatArray? = null
        repeat(1010) { val value = detector.accept(speech, 320); if (value != null) result = value }
        assertEquals(320000, checkNotNull(result).size)
    }
}
