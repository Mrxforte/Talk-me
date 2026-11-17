package com.example

import android.Manifest
import com.example.core.audio.AudioRecordManager
import com.example.core.audio.AudioRecordingState
import com.example.core.speech.SileroVadDetector
import com.example.data.repository.SpeechRepositoryImpl
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.math.PI
import kotlin.math.sin
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAudioRecord

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class SpeakingCaptureRegressionTest {
    @Test
    fun voiceActivityFinalizesAndPublishesRecordedWav() = assertCapturedSpeech(live = false)

    @Test
    fun liveUtterancePublishesWavWithoutClosingTheRecorder() = assertCapturedSpeech(live = true)

    private fun assertCapturedSpeech(live: Boolean) = runBlocking {
        val context = RuntimeEnvironment.getApplication()
        shadowOf(context).grantPermissions(Manifest.permission.RECORD_AUDIO)
        val recorder = AudioRecordManager(context)
        val repository = SpeechRepositoryImpl(recorder, SileroVadDetector())
        val finalized = async(start = CoroutineStart.UNDISPATCHED) {
            withTimeout(6_000) { repository.utteranceFinalizedEvents.first() }
        }

        val beginAudio = CountDownLatch(1)
        val speech = ShortArray(6 * 512) { index ->
            val time = (index % 512) / 16_000.0
            val signal = 0.25 * sin(2 * PI * 130 * time) +
                0.30 * sin(2 * PI * 520 * time) +
                0.20 * sin(2 * PI * 1050 * time) +
                0.10 * sin(2 * PI * 1850 * time)
            (signal * Short.MAX_VALUE).toInt().toShort()
        }
        ShadowAudioRecord.setSource(object : ShadowAudioRecord.AudioRecordSource {
            private var position = 0

            override fun readInShortArray(buffer: ShortArray, offset: Int, size: Int, blocking: Boolean): Int {
                if (!beginAudio.await(3, TimeUnit.SECONDS)) return 0
                Thread.sleep(5)
                for (index in offset until offset + size) {
                    buffer[index] = if (position < speech.size) speech[position++] else 0
                }
                return size
            }
        })

        try {
            assertTrue(if (live) repository.startLiveListening() else repository.startListening())
            beginAudio.countDown()

            val result = finalized.await()
            assertTrue(result.finalizedAutomatically)
            assertTrue(result.hadSpeechDetectedByVad)
            assertTrue(result.audioWavBytes.size > 4096)
            assertArrayEquals("RIFF".toByteArray(), result.audioWavBytes.copyOf(4))
            assertArrayEquals(result.audioWavBytes, repository.stopListening().audioWavBytes)
            if (live) {
                assertEquals(AudioRecordingState.RECORDING, recorder.recordingState.value)
                repository.discardListeningTurn()
                assertEquals(AudioRecordingState.RECORDING, recorder.recordingState.value)
                assertTrue(repository.startLiveListening())
                val silent = repository.stopListening()
                assertTrue(silent.audioWavBytes.isEmpty())
            }
        } finally {
            beginAudio.countDown()
            repository.cancelListening()
            ShadowAudioRecord.clearSource()
            assertEquals(AudioRecordingState.IDLE, recorder.recordingState.value)
        }
    }
}
