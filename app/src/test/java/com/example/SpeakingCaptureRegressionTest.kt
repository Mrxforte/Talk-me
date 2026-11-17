package com.example

import android.Manifest
import com.example.core.audio.AudioRecordManager
import com.example.core.speech.SileroVadDetector
import com.example.data.repository.SpeechRepositoryImpl
import kotlin.math.PI
import kotlin.math.sin
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class SpeakingCaptureRegressionTest {
    @Test
    fun voiceActivityFinalizesAndPublishesRecordedWav() = runBlocking {
        val context = RuntimeEnvironment.getApplication()
        shadowOf(context).grantPermissions(Manifest.permission.RECORD_AUDIO)
        val recorder = AudioRecordManager(context)
        val repository = SpeechRepositoryImpl(recorder, SileroVadDetector())
        val finalized = async(start = CoroutineStart.UNDISPATCHED) {
            withTimeout(6_000) { repository.utteranceFinalizedEvents.first() }
        }

        try {
            assertTrue(repository.startListening())
            val speech = FloatArray(6 * 512) { index ->
                val time = (index % 512) / 16_000.0
                (0.25 * sin(2 * PI * 130 * time) +
                    0.30 * sin(2 * PI * 520 * time) +
                    0.20 * sin(2 * PI * 1050 * time) +
                    0.10 * sin(2 * PI * 1850 * time)).toFloat()
            }
            recorder.feedPcmSamples(speech)
            recorder.feedPcmSamples(FloatArray(30 * 512))

            val result = finalized.await()
            assertTrue(result.finalizedAutomatically)
            assertTrue(result.hadSpeechDetectedByVad)
            assertTrue(result.audioWavBytes.size > 4096)
            assertArrayEquals("RIFF".toByteArray(), result.audioWavBytes.copyOf(4))
            assertArrayEquals(result.audioWavBytes, repository.stopListening().audioWavBytes)
        } finally {
            repository.cancelListening()
        }
    }
}
