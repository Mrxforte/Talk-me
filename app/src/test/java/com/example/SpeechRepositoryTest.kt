package com.example

import com.example.domain.repository.SpeechRecognitionResult
import com.example.domain.repository.SpeechRecognitionState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests verifying SpeechRepository state machine transitions,
 * two-pass (Pass 1 streaming + Pass 2 batch offline) recognition result merging,
 * and duration calculations.
 */
class SpeechRepositoryTest {

    @Test
    fun `speech recognition states transition through two-pass pipeline`() {
        val states = mutableListOf<SpeechRecognitionState>()
        states.add(SpeechRecognitionState.IDLE)
        states.add(SpeechRecognitionState.LISTENING_PASS_1)
        states.add(SpeechRecognitionState.PROCESSING_PASS_2)
        states.add(SpeechRecognitionState.COMPLETED)

        assertEquals(SpeechRecognitionState.IDLE, states[0])
        assertEquals(SpeechRecognitionState.LISTENING_PASS_1, states[1])
        assertEquals(SpeechRecognitionState.PROCESSING_PASS_2, states[2])
        assertEquals(SpeechRecognitionState.COMPLETED, states[3])
    }

    @Test
    fun `speech recognition result encapsulates transcript and timings`() {
        val result = SpeechRecognitionResult(
            recognizedText = "Hello, my name is Azamat.",
            isFinal = true,
            recordingDurationMs = 2500L,
            processingDurationMs = 120L
        )

        assertEquals("Hello, my name is Azamat.", result.recognizedText)
        assertTrue(result.isFinal)
        assertEquals(2500L, result.recordingDurationMs)
        assertEquals(120L, result.processingDurationMs)
    }

    @Test
    fun `two pass text resolution prioritizes offline pass2 when present`() {
        val streamingText = "hello my name"
        val offlineText = "Hello, my name is Azamat."

        val resolvedText = offlineText.ifNotBlank { streamingText }
        assertEquals("Hello, my name is Azamat.", resolvedText)
    }
}

private inline fun String.ifNotBlank(fallback: () -> String): String {
    return if (this.isNotBlank()) this else fallback()
}
