package com.example.core.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * Fun interactive Lock & Flying Stars Burst Animation for locked/mystery missions and levels.
 * Plays a lively 3D lock shake + spring bounce + 360-degree shooting stars burst when a user taps a locked mission.
 */
object LockStarBurstAnimator {

    private val STAR_SYMBOLS = listOf("★", "✦", "✨", "★", "✶", "✧")
    private val STAR_COLORS = listOf(
        Color.parseColor("#FACC15"), // Bright Gold
        Color.parseColor("#F59E0B"), // Amber
        Color.parseColor("#EC4899"), // Vibrant Pink
        Color.parseColor("#38BDF8"), // Sky Cyan
        Color.parseColor("#A855F7"), // Neon Violet
        Color.parseColor("#4ADE80")  // Emerald Spark
    )

    fun playLockTapStarBurst(
        cardView: View,
        lockCenterView: View,
        particleContainer: FrameLayout,
        onAnimationComplete: (() -> Unit)? = null
    ) {
        val density = cardView.resources.displayMetrics.density

        // 1. Lock Medallion Spring Pop + Playful Wiggle
        val lockScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.42f, 0.88f, 1.22f, 1f)
        val lockScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.42f, 0.88f, 1.22f, 1f)
        val lockRotation = PropertyValuesHolder.ofFloat(
            View.ROTATION,
            0f, -26f, 26f, -20f, 20f, -12f, 12f, -5f, 5f, 0f
        )
        val lockAnimator = ObjectAnimator.ofPropertyValuesHolder(
            lockCenterView,
            lockScaleX,
            lockScaleY,
            lockRotation
        ).apply {
            duration = 680L
            interpolator = OvershootInterpolator(1.6f)
        }

        // 2. Card subtle 3D tilt & bounce
        val cardScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0.96f, 1.03f, 1f)
        val cardScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0.96f, 1.03f, 1f)
        val cardTilt = PropertyValuesHolder.ofFloat(View.ROTATION_Y, 0f, -8f, 8f, -4f, 0f)
        val cardAnimator = ObjectAnimator.ofPropertyValuesHolder(
            cardView,
            cardScaleX,
            cardScaleY,
            cardTilt
        ).apply {
            duration = 560L
            interpolator = AccelerateDecelerateInterpolator()
        }

        // 3. Spawn 14 Shooting Stars bursting in 360 degrees from the center of particleContainer
        val starAnimators = mutableListOf<Animator>()
        val spawnedViews = mutableListOf<View>()
        val starCount = 14

        for (i in 0 until starCount) {
            val starTv = TextView(particleContainer.context).apply {
                text = STAR_SYMBOLS[i % STAR_SYMBOLS.size]
                setTextColor(STAR_COLORS[i % STAR_COLORS.size])
                textSize = Random.nextInt(14, 22).toFloat()
                typeface = Typeface.DEFAULT_BOLD
                gravity = Gravity.CENTER
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
                scaleX = 0.2f
                scaleY = 0.2f
                alpha = 1f
            }

            particleContainer.addView(starTv)
            spawnedViews.add(starTv)

            val baseAngle = (2.0 * Math.PI * i) / starCount
            val jitter = Random.nextDouble(-0.22, 0.22)
            val angle = baseAngle + jitter
            val radiusPx = Random.nextInt(48, 92) * density

            val targetDx = (cos(angle) * radiusPx).toFloat()
            val targetDy = (sin(angle) * radiusPx).toFloat() - (10f * density)
            val targetRot = Random.nextInt(-240, 240).toFloat()

            val tx = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, targetDx)
            val ty = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0f, targetDy)
            val sx = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.2f, 1.35f, 0.9f, 0f)
            val sy = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.2f, 1.35f, 0.9f, 0f)
            val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 1f, 0.85f, 0f)
            val rot = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, targetRot)

            val starAnim = ObjectAnimator.ofPropertyValuesHolder(
                starTv,
                tx,
                ty,
                sx,
                sy,
                alpha,
                rot
            ).apply {
                duration = Random.nextLong(550L, 780L)
                startDelay = Random.nextLong(0L, 90L)
                interpolator = DecelerateInterpolator(1.4f)
            }
            starAnimators.add(starAnim)
        }

        AnimatorSet().apply {
            playTogether(listOf(lockAnimator, cardAnimator) + starAnimators)
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    spawnedViews.forEach { view ->
                        particleContainer.removeView(view)
                    }
                    onAnimationComplete?.invoke()
                }
            })
            start()
        }
    }
}
