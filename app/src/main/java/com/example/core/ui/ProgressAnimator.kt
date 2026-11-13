package com.example.core.ui

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.R
import com.example.feature.dailytasks.TasksCircularProgressView

/**
 * Smoothly animates any determinate [ProgressBar] (including Material [com.google.android.material.progressindicator.LinearProgressIndicator])
 * from 0 up to [targetProgress] when a screen opens or becomes visible, or from the current progress on live updates.
 * Uses internal sub-unit scaling so small-max bars (e.g. 1/3, 2/5) animate at 60fps without visible steps.
 */
object ProgressAnimator {

    private const val DEFAULT_DURATION_MS = 850L

    fun ProgressBar.getLogicalMax(): Int {
        val storedMax = getTag(R.id.tag_progress_logical_max) as? Int
        return (storedMax ?: max).coerceAtLeast(1)
    }

    fun ProgressBar.getLogicalProgress(): Int {
        val storedTarget = getTag(R.id.tag_progress_logical_target) as? Int
        return (storedTarget ?: progress).coerceAtLeast(0)
    }

    fun ProgressBar.resetOpenAnimationState() {
        (getTag(R.id.tag_progress_animator) as? ValueAnimator)?.cancel()
        setTag(R.id.tag_progress_animator, null)
        setTag(R.id.tag_progress_has_animated, false)
    }

    fun ProgressBar.replayProgressFromZero(durationMs: Long = DEFAULT_DURATION_MS) {
        if (isIndeterminate) return
        val target = getTag(R.id.tag_progress_logical_target) as? Int ?: return
        val maxVal = getLogicalMax()
        if (target <= 0) {
            progress = 0
            return
        }
        val running = getTag(R.id.tag_progress_animator) as? ValueAnimator
        if (running?.isRunning == true && running.currentPlayTime < 80L && progress <= (max * 0.05f)) {
            return
        }
        animateProgressTo(
            targetProgress = target,
            maxValue = maxVal,
            durationMs = durationMs,
            forceReplayFromZero = true
        )
    }

    fun View.replayAllProgressFromZero() {
        if (visibility != View.VISIBLE) return
        when (this) {
            is ProgressBar -> replayProgressFromZero()
            is TasksCircularProgressView -> replayAnimationFromZero()
            is LearningRoadmapView -> replayAnimationFromZero()
            is WeeklyGrowthChartView -> replayAnimationFromZero()
            is SkillAnalyticsChartView -> replayOpenAnimation()
            is ViewGroup -> {
                for (i in 0 until childCount) {
                    getChildAt(i).replayAllProgressFromZero()
                }
            }
        }
    }

    fun ProgressBar.animateProgressTo(
        targetProgress: Int,
        maxValue: Int = getLogicalMax(),
        durationMs: Long = DEFAULT_DURATION_MS,
        forceReplayFromZero: Boolean = false
    ) {
        val safeMax = maxValue.coerceAtLeast(1)
        val safeTarget = targetProgress.coerceIn(0, safeMax)
        val scale = if (safeMax <= 250) 100 else 10
        val scaledMax = safeMax * scale
        val scaledTarget = safeTarget * scale

        val previousMax = getTag(R.id.tag_progress_logical_max) as? Int
        val previousTarget = getTag(R.id.tag_progress_logical_target) as? Int
        val hasAnimatedNonZero = getTag(R.id.tag_progress_has_animated) == true

        if (!forceReplayFromZero &&
            hasAnimatedNonZero &&
            previousMax == safeMax &&
            previousTarget == safeTarget
        ) {
            return
        }

        (getTag(R.id.tag_progress_animator) as? ValueAnimator)?.cancel()

        if (getTag(R.id.tag_progress_detach_hooked) != true) {
            setTag(R.id.tag_progress_detach_hooked, true)
            addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                    val bar = v as? ProgressBar ?: return
                    if (bar.isShown && bar.getLogicalProgress() > 0) {
                        bar.replayProgressFromZero()
                    }
                }

                override fun onViewDetachedFromWindow(v: View) {
                    (v.getTag(R.id.tag_progress_animator) as? ValueAnimator)?.cancel()
                }
            })
        }

        val startScaled = if (forceReplayFromZero || !hasAnimatedNonZero) {
            0
        } else {
            val prevScaleMax = ((previousMax ?: safeMax).coerceAtLeast(1) *
                if ((previousMax ?: safeMax) <= 250) 100 else 10).coerceAtLeast(1)
            val fraction = (progress.toFloat() / prevScaleMax.toFloat()).coerceIn(0f, 1f)
            (fraction * scaledMax).toInt()
        }

        if (max != scaledMax) {
            max = scaledMax
        }
        setTag(R.id.tag_progress_logical_max, safeMax)
        setTag(R.id.tag_progress_logical_target, safeTarget)

        if (safeTarget == 0 && startScaled == 0) {
            progress = 0
            return
        }

        setTag(R.id.tag_progress_has_animated, true)
        progress = startScaled

        val animator = ValueAnimator.ofInt(startScaled, scaledTarget).apply {
            duration = durationMs.coerceAtLeast(200L)
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener { anim ->
                progress = anim.animatedValue as Int
            }
        }
        setTag(R.id.tag_progress_animator, animator)
        animator.start()
    }
}
