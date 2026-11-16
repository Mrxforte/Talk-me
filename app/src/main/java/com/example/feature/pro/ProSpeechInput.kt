package com.example.feature.pro

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

/** Uses the device's voice input service when the TalkMe offline model is unavailable. */
internal class ProSpeechInput(private val context: Context) {
    private var recognizer: SpeechRecognizer? = null

    fun start(onResult: (String) -> Unit, onAmplitude: (Float) -> Unit): Boolean {
        cancel()
        val instance = try {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
                    SpeechRecognizer.isOnDeviceRecognitionAvailable(context) ->
                    SpeechRecognizer.createOnDeviceSpeechRecognizer(context)
                SpeechRecognizer.isRecognitionAvailable(context) -> SpeechRecognizer.createSpeechRecognizer(context)
                else -> return false
            }
        } catch (_: RuntimeException) {
            return false
        }
        recognizer = instance
        instance.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) = Unit
            override fun onBeginningOfSpeech() = Unit
            override fun onRmsChanged(rmsdB: Float) {
                if (recognizer === instance) onAmplitude((rmsdB / 10f).coerceIn(0f, 1f))
            }
            override fun onBufferReceived(buffer: ByteArray?) = Unit
            override fun onEndOfSpeech() = Unit
            override fun onError(error: Int) = deliver("")
            override fun onResults(results: Bundle?) = deliver(
                results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull().orEmpty()
            )
            override fun onPartialResults(partialResults: Bundle?) = Unit
            override fun onEvent(eventType: Int, params: Bundle?) = Unit

            private fun deliver(text: String) {
                if (recognizer !== instance) return
                cancel()
                onResult(text)
            }
        })
        return try {
            instance.startListening(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
                putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
                putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            })
            true
        } catch (_: RuntimeException) {
            cancel()
            false
        }
    }

    fun stop() {
        recognizer?.stopListening()
    }

    fun cancel() {
        val active = recognizer ?: return
        recognizer = null
        active.cancel()
        active.destroy()
    }
}
