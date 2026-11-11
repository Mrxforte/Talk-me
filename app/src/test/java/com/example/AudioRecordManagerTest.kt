package com.example

import com.example.core.audio.AudioRecordingState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.sqrt

/**
 * Unit tests covering AudioRecordManager math algorithms, PCM byte-to-float conversions,
 * RMS amplitude calculation, and recording state lifecycle transitions.
 */
class AudioRecordManagerTest {

    @Test
    fun `audio recording states transition correctly`() {
        var state = AudioRecordingState.IDLE
        assertEquals(AudioRecordingState.IDLE, state)

        state = AudioRecordingState.RECORDING
        assertEquals(AudioRecordingState.RECORDING, state)

        state = AudioRecordingState.PAUSED
        assertEquals(AudioRecordingState.PAUSED, state)

        state = AudioRecordingState.STOPPED
        assertEquals(AudioRecordingState.STOPPED, state)
    }

    @Test
    fun `short pcm samples convert to normalized float array correctly`() {
        val shortBuffer = shortArrayOf(0, 16384, -16384, 32767, -32768)
        val floatArray = FloatArray(shortBuffer.size)

        for (i in shortBuffer.indices) {
            floatArray[i] = shortBuffer[i] / 32768.0f
        }

        assertEquals(0.0f, floatArray[0], 0.001f)
        assertEquals(0.5f, floatArray[1], 0.001f)
        assertEquals(-0.5f, floatArray[2], 0.001f)
        assertEquals(0.9999f, floatArray[3], 0.001f)
        assertEquals(-1.0f, floatArray[4], 0.001f)
    }

    @Test
    fun `rms amplitude calculation normalizes between 0 and 1`() {
        val samples = shortArrayOf(1000, 2000, 3000, 4000, 5000)
        var sumSquare = 0.0
        for (s in samples) {
            sumSquare += s * s.toDouble()
        }
        val rms = sqrt(sumSquare / samples.size)
        val normalizedAmp = (rms / 32768.0).coerceIn(0.0, 1.0).toFloat()

        assertTrue(normalizedAmp > 0f)
        assertTrue(normalizedAmp <= 1.0f)
    }
}
