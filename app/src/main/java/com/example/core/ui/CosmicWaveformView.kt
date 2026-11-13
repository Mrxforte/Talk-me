package com.example.core.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.R
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sin

/**
 * Reusable cosmic neon-cyan waveform visualizer supporting:
 * - [Mode.BARS]: horizontal tapered cyan bars for Speaking tutor video overlay & live speech card
 * - [Mode.RING]: glowing circular cyan equalizer ring for Listening Studio
 */
class CosmicWaveformView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    enum class Mode {
        BARS,
        RING
    }

    private var mode: Mode = Mode.BARS
    private var isActive: Boolean = false
    private var amplitude: Float = 0f
    private var phase: Float = 0f

    private val barCount = 19
    private val barHeights = FloatArray(barCount) { 0.18f }
    private val barRect = RectF()

    private val cyanColor = ContextCompat.getColor(context, R.color.color_neon_cyan)
    private val cyanSoftColor = ContextCompat.getColor(context, R.color.color_neon_cyan_variant)

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = cyanColor
    }

    private val ringGlowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = cyanColor
    }

    private val ringFillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val ringStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = cyanColor
    }

    private val frameRunnable = object : Runnable {
        override fun run() {
            if (!isAttachedToWindow || !isActive) return
            phase += 0.22f
            invalidate()
            postDelayed(this, 45L)
        }
    }

    fun setMode(newMode: Mode) {
        if (mode != newMode) {
            mode = newMode
            invalidate()
        }
    }

    fun setActive(active: Boolean) {
        if (isActive == active) return
        isActive = active
        removeCallbacks(frameRunnable)
        if (active && isAttachedToWindow) {
            post(frameRunnable)
        } else {
            invalidate()
        }
    }

    fun setAmplitude(level: Float) {
        amplitude = level.coerceIn(0f, 1f)
        if (!isActive) {
            invalidate()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isActive) {
            removeCallbacks(frameRunnable)
            post(frameRunnable)
        }
    }

    override fun onDetachedFromWindow() {
        removeCallbacks(frameRunnable)
        super.onDetachedFromWindow()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            val radius = min(w, h) * 0.48f
            ringFillPaint.shader = RadialGradient(
                w / 2f,
                h / 2f,
                radius.coerceAtLeast(1f),
                intArrayOf(
                    0x2A00E5FF,
                    0x100EA5E9,
                    0x00070B14
                ),
                floatArrayOf(0f, 0.72f, 1f),
                Shader.TileMode.CLAMP
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        if (w <= 0f || h <= 0f) return

        when (mode) {
            Mode.BARS -> drawSymmetricBars(canvas, 0f, 0f, w, h, centerYShift = 0f)
            Mode.RING -> {
                val cx = w / 2f
                val cy = h / 2f
                val radius = min(w, h) * 0.44f

                canvas.drawCircle(cx, cy, radius * 1.05f, ringFillPaint)

                ringGlowPaint.alpha = if (isActive) 85 else 45
                ringGlowPaint.strokeWidth = (radius * 0.08f).coerceAtLeast(6f)
                canvas.drawCircle(cx, cy, radius, ringGlowPaint)

                ringStrokePaint.alpha = 235
                ringStrokePaint.strokeWidth = (radius * 0.025f).coerceAtLeast(3f)
                canvas.drawCircle(cx, cy, radius, ringStrokePaint)

                val barsLeft = cx - radius * 0.70f
                val barsRight = cx + radius * 0.70f
                val barsTop = cy - radius * 0.56f
                val barsBottom = cy + radius * 0.04f
                drawSymmetricBars(canvas, barsLeft, barsTop, barsRight, barsBottom, centerYShift = 0f)
            }
        }
    }

    private fun drawSymmetricBars(
        canvas: Canvas,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        centerYShift: Float
    ) {
        val areaW = right - left
        val areaH = bottom - top
        if (areaW <= 0f || areaH <= 0f) return

        val step = areaW / barCount
        val barWidth = (step * 0.46f).coerceIn(2.5f, 8f)
        val centerY = top + areaH / 2f + centerYShift
        val midIndex = (barCount - 1) / 2f

        for (i in 0 until barCount) {
            val distNorm = abs(i - midIndex) / midIndex.coerceAtLeast(1f)
            val envelope = 1f - distNorm * 0.78f
            val wave = if (isActive) {
                val dynamicAmp = amplitude.coerceAtLeast(0.45f)
                val s = abs(sin((phase + i * 0.65f).toDouble())).toFloat()
                (0.22f + 0.78f * s * dynamicAmp) * envelope
            } else {
                val staticPattern = 0.25f + 0.55f * abs(sin((i * 0.75f + PI / 4.0))).toFloat()
                staticPattern * envelope * (0.65f + amplitude * 0.35f)
            }

            barHeights[i] = wave.coerceIn(0.10f, 1f)
            val halfH = (areaH * 0.46f * barHeights[i]).coerceAtLeast(barWidth * 0.65f)
            val cx = left + step * (i + 0.5f)
            barRect.set(cx - barWidth / 2f, centerY - halfH, cx + barWidth / 2f, centerY + halfH)

            barPaint.color = if (distNorm < 0.45f) cyanColor else cyanSoftColor
            barPaint.alpha = ((1f - distNorm * 0.35f) * 255).toInt().coerceIn(90, 255)
            canvas.drawRoundRect(barRect, barWidth / 2f, barWidth / 2f, barPaint)
        }
    }
}
