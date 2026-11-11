package com.example.core.ui

import android.content.Context
import android.provider.Settings
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Typewriter animation helper for rendering main Uzbek lesson explanations word-by-word or chunk-by-chunk.
 * Includes skip support and accessibility/reduced-motion detection.
 */
class TypewriterTextAnimator(
    private val textView: TextView,
    private val fullText: String,
    private val scope: CoroutineScope,
    private val delayMsPerWord: Long = 60L
) {
    private var animationJob: Job? = null
    private var isCompleted: Boolean = false

    fun start(onComplete: (() -> Unit)? = null) {
        if (fullText.isEmpty()) {
            textView.text = ""
            isCompleted = true
            onComplete?.invoke()
            return
        }

        // Check accessibility reduced-motion setting
        if (isReducedMotionEnabled(textView.context)) {
            textView.text = fullText
            isCompleted = true
            onComplete?.invoke()
            return
        }

        animationJob?.cancel()
        isCompleted = false
        textView.text = ""

        val words = fullText.split(" ")
        animationJob = scope.launch(Dispatchers.Main) {
            val builder = StringBuilder()
            for (i in words.indices) {
                if (i > 0) builder.append(" ")
                builder.append(words[i])
                textView.text = builder.toString()
                delay(delayMsPerWord)
            }
            isCompleted = true
            onComplete?.invoke()
        }
    }

    fun skip() {
        if (isCompleted) return
        animationJob?.cancel()
        textView.text = fullText
        isCompleted = true
    }

    fun cancel() {
        animationJob?.cancel()
    }

    private fun isReducedMotionEnabled(context: Context): Boolean {
        return try {
            val durationScale = Settings.Global.getFloat(
                context.contentResolver,
                Settings.Global.ANIMATOR_DURATION_SCALE,
                1.0f
            )
            val transitionScale = Settings.Global.getFloat(
                context.contentResolver,
                Settings.Global.TRANSITION_ANIMATION_SCALE,
                1.0f
            )
            durationScale == 0f || transitionScale == 0f
        } catch (e: Exception) {
            false
        }
    }
}
