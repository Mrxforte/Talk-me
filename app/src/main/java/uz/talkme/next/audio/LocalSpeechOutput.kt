package uz.talkme.next.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import uz.talkme.next.domain.SpeechOutput
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocalSpeechOutput(context: Context) : SpeechOutput {
    private val app = context.applicationContext
    private val audio = app.getSystemService(AudioManager::class.java)
    private val handler = Handler(Looper.getMainLooper())
    private var engine: TextToSpeech? = null
    private var currentId: String? = null
    private var done: (() -> Unit)? = null
    private var failed: (() -> Unit)? = null
    private var ready = false
    private var initialization = 0L
    private val attributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build()
    private val focus = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
        .setAudioAttributes(attributes).setOnAudioFocusChangeListener({ change ->
            if (change == AudioManager.AUDIOFOCUS_LOSS || change == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                val callback = failed
                stop()
                callback?.invoke()
            }
        }, handler).build()

    override suspend fun prepare() = withContext(Dispatchers.Main.immediate) {
        if (ready) return@withContext
        suspendCancellableCoroutine<Unit> { continuation ->
            val ticket = ++initialization
            engine?.shutdown()
            engine = null
            val candidate = TextToSpeech(app) { status -> handler.post {
                if (!continuation.isActive || ticket != initialization) return@post
                val tts = engine
                val voice = tts?.voices?.filter {
                    it.locale.language == Locale.ENGLISH.language && !it.isNetworkConnectionRequired
                }?.sortedByDescending { if (it.locale.country == "US") 1 else 0 }?.firstOrNull()
                if (status != TextToSpeech.SUCCESS || tts == null || voice == null) {
                    continuation.resumeWithException(IllegalStateException("Offline English voice required"))
                } else {
                    tts.voice = voice
                    tts.setSpeechRate(0.92f)
                    tts.setAudioAttributes(attributes)
                    ready = true
                    continuation.resume(Unit)
                }
            } }
            engine = candidate
            continuation.invokeOnCancellation { handler.post {
                if (ticket == initialization && !ready) { candidate.shutdown(); engine = null }
            } }
        }
    }

    override fun speak(text: String, onDone: () -> Unit, onFailure: () -> Unit) {
        stop()
        if (!ready || audio.requestAudioFocus(focus) != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            onFailure(); return
        }
        val id = UUID.randomUUID().toString()
        currentId = id; done = onDone; failed = onFailure
        engine?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) = Unit
            override fun onDone(utteranceId: String?) = finish(utteranceId, true)
            @Deprecated("Required callback")
            override fun onError(utteranceId: String?) = finish(utteranceId, false)
            override fun onError(utteranceId: String?, errorCode: Int) = finish(utteranceId, false)
        })
        if (engine?.speak(text, TextToSpeech.QUEUE_FLUSH, null, id) != TextToSpeech.SUCCESS) finish(id, false)
    }

    private fun finish(id: String?, success: Boolean) { handler.post {
        if (id == null || id != currentId) return@post
        val callback = if (success) done else failed
        currentId = null; done = null; failed = null
        audio.abandonAudioFocusRequest(focus)
        callback?.invoke()
    } }
    override fun stop() {
        currentId = null; done = null; failed = null
        engine?.stop()
        audio.abandonAudioFocusRequest(focus)
    }
    override fun close() { initialization++; stop(); ready = false; engine?.shutdown(); engine = null }
}
