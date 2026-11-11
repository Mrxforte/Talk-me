package com.example.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.audio.TtsPlaybackState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Custom Material 3 style speaker button enforcing the TalkMe Global TTS requirement.
 * Touch target >= 48dp, visual state indicators (Idle, Loading, Playing, Error).
 */
class SpeakTextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val speakerIcon: ImageView
    private val progressBar: ProgressBar
    private var textToSpeak: String = ""
    private var languageCode: String = "en"
    private var observeJob: Job? = null

    init {
        // Enforce minimum 48dp touch target
        val minSize = (48 * context.resources.displayMetrics.density).toInt()
        minimumWidth = minSize
        minimumHeight = minSize

        val iconSize = (24 * context.resources.displayMetrics.density).toInt()

        speakerIcon = ImageView(context).apply {
            layoutParams = LayoutParams(iconSize, iconSize).apply {
                gravity = Gravity.CENTER
            }
            setImageResource(R.drawable.ic_speaker)
            contentDescription = "Speak English audio"
        }

        progressBar = ProgressBar(context).apply {
            layoutParams = LayoutParams(iconSize, iconSize).apply {
                gravity = Gravity.CENTER
            }
            visibility = GONE
            isIndeterminate = true
        }

        addView(speakerIcon)
        addView(progressBar)

        isClickable = true
        isFocusable = true
    }

    fun bindText(
        text: String,
        ttsManager: TtsManager,
        scope: CoroutineScope,
        langCode: String = "en"
    ) {
        this.textToSpeak = text
        this.languageCode = langCode

        setOnClickListener {
            if (textToSpeak.isNotBlank()) {
                scope.launch(Dispatchers.Main) {
                    ttsManager.speakEnglish(textToSpeak, languageCode = languageCode)
                }
            }
        }

        observeJob?.cancel()
        observeJob = scope.launch(Dispatchers.Main) {
            ttsManager.playbackState.collectLatest { state ->
                when (state) {
                    is TtsPlaybackState.Idle -> {
                        speakerIcon.visibility = VISIBLE
                        progressBar.visibility = GONE
                        speakerIcon.alpha = 1.0f
                    }
                    is TtsPlaybackState.Loading -> {
                        if (state.text == textToSpeak) {
                            speakerIcon.visibility = INVISIBLE
                            progressBar.visibility = VISIBLE
                        }
                    }
                    is TtsPlaybackState.Playing -> {
                        if (state.text == textToSpeak) {
                            speakerIcon.visibility = VISIBLE
                            progressBar.visibility = GONE
                            speakerIcon.alpha = 0.7f
                        } else {
                            speakerIcon.alpha = 1.0f
                        }
                    }
                    is TtsPlaybackState.Error -> {
                        speakerIcon.visibility = VISIBLE
                        progressBar.visibility = GONE
                        speakerIcon.alpha = 1.0f
                    }
                    else -> {
                        speakerIcon.visibility = VISIBLE
                        progressBar.visibility = GONE
                    }
                }
            }
        }
    }
}
