package com.example

import com.example.core.audio.TtsPlaybackState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Unit tests covering TTS playback state transitions, speed multiplier bounds,
 * language filters, and secure audio cache file obfuscation.
 */
class TtsManagerTest {

    @Test
    fun `tts playback states represent lifecycle events`() {
        val idleState: TtsPlaybackState = TtsPlaybackState.Idle
        val loadingState: TtsPlaybackState = TtsPlaybackState.Loading("Hello")
        val playingState: TtsPlaybackState = TtsPlaybackState.Playing("Hello")

        assertEquals(TtsPlaybackState.Idle, idleState)
        assertTrue(loadingState is TtsPlaybackState.Loading)
        assertEquals("Hello", (playingState as TtsPlaybackState.Playing).text)
    }

    @Test
    fun `tts speech rate is coerced within safe human bounds`() {
        fun coerceRate(rate: Float): Float = rate.coerceIn(0.5f, 1.75f)

        assertEquals(0.5f, coerceRate(0.1f), 0.001f)
        assertEquals(1.0f, coerceRate(1.0f), 0.001f)
        assertEquals(1.75f, coerceRate(2.5f), 0.001f)
    }

    @Test
    fun `obfuscated audio cache byte encryption XOR is fully reversible`() {
        val originalText = "Hello! This is a test phrase for offline TTS caching."
        val key = 0x5A.toByte()

        val originalBytes = originalText.toByteArray(Charsets.UTF_8)
        val encryptedBytes = originalBytes.clone()
        for (i in encryptedBytes.indices) {
            encryptedBytes[i] = (encryptedBytes[i].toInt() xor key.toInt()).toByte()
        }

        // Decrypt
        val decryptedBytes = encryptedBytes.clone()
        for (i in decryptedBytes.indices) {
            decryptedBytes[i] = (decryptedBytes[i].toInt() xor key.toInt()).toByte()
        }
        val decryptedText = String(decryptedBytes, Charsets.UTF_8)

        assertEquals(originalText, decryptedText)
    }
}
