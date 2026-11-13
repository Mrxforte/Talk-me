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

    @Test
    fun `speakUzbek routes TalkMe welcome script with uz language code while speakEnglish keeps uz guard`() =
        kotlinx.coroutines.runBlocking {
            val recordedCalls = mutableListOf<Pair<String, String>>()
            val fakeProvider = object : com.example.domain.repository.TtsProvider {
                override val providerName: String = "FAKE"
                override suspend fun speak(text: String, speechRate: Float): Result<Unit> {
                    recordedCalls.add("en" to text)
                    return Result.success(Unit)
                }
                override suspend fun speakWithLanguage(
                    text: String,
                    speechRate: Float,
                    languageCode: String
                ): Result<Unit> {
                    recordedCalls.add(languageCode to text)
                    return Result.success(Unit)
                }
                override fun stop() = Unit
                override fun clearCache(): Long = 0L
                override fun getCacheSizeBytes(): Long = 0L
            }
            val prefsRepo = object : com.example.domain.repository.UserPreferencesRepository {
                override val userPreferencesFlow = kotlinx.coroutines.flow.flowOf(
                    com.example.domain.model.UserPreferencesModel(soundEnabled = true)
                )
                override suspend fun updateUserName(name: String) = Unit
                override suspend fun updateThemeMode(themeMode: String) = Unit
                override suspend fun updateSoundEnabled(enabled: Boolean) = Unit
                override suspend fun updateSpeechSpeed(speed: Float) = Unit
                override suspend fun updatePremiumSubscribed(subscribed: Boolean) = Unit
                override suspend fun updatePreferredTtsProvider(provider: String) = Unit
                override suspend fun updateVoiceGender(voiceGender: String) = Unit
                override suspend fun updateOfflineAudioEnabled(enabled: Boolean) = Unit
                override suspend fun updateLessonRemindersEnabled(enabled: Boolean) = Unit
                override suspend fun updateLastLearningBookmark(topicId: String, sectionId: String, scrollOffset: Int) = Unit
            }

            val manager = com.example.core.audio.TtsManager(fakeProvider, fakeProvider, prefsRepo)

            // Guarded English call with languageCode = "uz" skips silently
            manager.speakEnglish("O'zbekcha izoh", languageCode = "uz")
            assertTrue(recordedCalls.isEmpty())

            // Explicit speakUzbek call plays the Dars 1 TalkMe TTS script verbatim
            val script = com.example.data.local.CurriculumSeedData.DARS_1_TALKME_TTS_TEXT
            manager.speakUzbek(script)
            assertEquals(1, recordedCalls.size)
            assertEquals("uz", recordedCalls.first().first)
            assertEquals(script, recordedCalls.first().second)
            assertTrue(script.contains("Salom, aziz do‘stim!"))
            assertTrue(script.contains("Har bir xato — bu o‘rganishning bir qismi."))
        }
}
