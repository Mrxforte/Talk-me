package com.example.core.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlin.math.ceil

/**
 * Cosmic dark weekly/monthly growth chart comparing current vs previous study minutes.
 * Matches the cosmic dark profile analytics style and animates lines smoothly from baseline
 * up to target values on screen open or period toggle.
 */
class WeeklyGrowthChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val density = resources.displayMetrics.density
    private val scaledDensity = resources.displayMetrics.scaledDensity

    private var xLabels: List<String> = listOf("Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya")
    private var currentValues: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0)
    private var previousValues: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0)
    private var currentLegendLabel: String = "Bu hafta (0 daq.)"
    private var previousLegendLabel: String = "O'tgan hafta (0 daq.)"

    private var revealFraction: Float = 0f
    private var chartAnimator: ValueAnimator? = null
    private var hasAnimatedOpen: Boolean = false
    private var lastSignature: Int = 0

    private val gridPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 0.85f * density
        color = Color.argb(120, 40, 68, 112)
    }

    private val verticalGuidePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 0.9f * density
        color = Color.argb(95, 56, 189, 248)
    }

    private val yAxisTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#8E9CAE")
        textSize = 10.5f * scaledDensity
        textAlign = Paint.Align.RIGHT
    }

    private val xAxisTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#CBD5E1")
        textSize = 11f * scaledDensity
        textAlign = Paint.Align.CENTER
    }

    private val prevLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.8f * density
        color = Color.parseColor("#94A3B8")
        pathEffect = DashPathEffect(floatArrayOf(5f * density, 4f * density), 0f)
    }

    private val prevDotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#94A3B8")
    }

    private val currentGlowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 7f * density
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = Color.argb(72, 0, 229, 255)
    }

    private val currentLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.6f * density
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = Color.parseColor("#00E5FF")
    }

    private val currentNodeOuterPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#00E5FF")
    }

    private val currentNodeCenterPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val pointValuePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 10.5f * scaledDensity
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private val legendTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#CBD5E1")
        textSize = 10.5f * scaledDensity
        textAlign = Paint.Align.LEFT
    }

    private val currentPath = Path()
    private val prevPath = Path()

    fun setGrowthData(
        labels: List<String>,
        currentMinutes: List<Int>,
        previousMinutes: List<Int>,
        currentLegend: String,
        previousLegend: String,
        forceReplayFromZero: Boolean = false
    ) {
        val signature = listOf(labels, currentMinutes, previousMinutes, currentLegend, previousLegend).hashCode()
        xLabels = if (labels.isNotEmpty()) labels else listOf("Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya")
        currentValues = currentMinutes
        previousValues = previousMinutes
        currentLegendLabel = currentLegend
        previousLegendLabel = previousLegend

        if (!forceReplayFromZero && hasAnimatedOpen && lastSignature == signature) {
            return
        }
        lastSignature = signature

        val hasData = hasChartData()
        if (!hasData) {
            chartAnimator?.cancel()
            revealFraction = 0f
            hasAnimatedOpen = false
            invalidate()
            return
        }

        replayAnimationFromZero(ignoreRunningGuard = true)
    }

    fun replayAnimationFromZero(ignoreRunningGuard: Boolean = false) {
        if (!hasChartData()) {
            chartAnimator?.cancel()
            revealFraction = 0f
            hasAnimatedOpen = false
            invalidate()
            return
        }
        val running = chartAnimator
        if (!ignoreRunningGuard &&
            running?.isRunning == true &&
            running.currentPlayTime < 80L &&
            revealFraction < 0.05f
        ) {
            return
        }
        chartAnimator?.cancel()
        hasAnimatedOpen = true
        revealFraction = 0f
        chartAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 900L
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener {
                revealFraction = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    fun getRevealFraction(): Float = revealFraction

    private fun hasChartData(): Boolean =
        currentValues.any { it > 0 } || previousValues.any { it > 0 }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isShown && hasChartData()) {
            replayAnimationFromZero()
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE && isAttachedToWindow && isShown && hasChartData()) {
            replayAnimationFromZero()
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == VISIBLE && isAttachedToWindow && isShown && hasChartData()) {
            replayAnimationFromZero()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        chartAnimator?.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || height <= 0) return

        val w = width.toFloat()
        val h = height.toFloat()

        val chartLeft = 48f * density
        val chartRight = w - 16f * density
        val chartTop = 18f * density
        val chartBottom = h - 46f * density
        val chartWidth = (chartRight - chartLeft).coerceAtLeast(20f)
        val chartHeight = (chartBottom - chartTop).coerceAtLeast(20f)

        val maxObserved = maxOf(
            currentValues.maxOrNull() ?: 0,
            previousValues.maxOrNull() ?: 0
        )
        val yCeiling = if (maxObserved <= 60) {
            60
        } else {
            ((ceil(maxObserved / 10.0) * 10).toInt()).coerceAtLeast(60)
        }
        val stepCount = 6
        val stepVal = yCeiling / stepCount

        // Horizontal & baseline grid + Y labels
        val yTextOffset = (yAxisTextPaint.descent() + yAxisTextPaint.ascent()) / 2f
        for (i in 0..stepCount) {
            val valNum = i * stepVal
            val ratio = i.toFloat() / stepCount.toFloat()
            val y = chartBottom - ratio * chartHeight
            canvas.drawLine(chartLeft, y, chartRight, y, gridPaint)
            canvas.drawText(
                "$valNum daq.",
                chartLeft - 6f * density,
                y - yTextOffset,
                yAxisTextPaint
            )
        }
        // Left Y-axis spine
        canvas.drawLine(chartLeft, chartTop, chartLeft, chartBottom, gridPaint)

        val count = xLabels.size.coerceAtLeast(2)
        val xPadding = 16f * density
        val usableWidth = (chartWidth - 2f * xPadding).coerceAtLeast(10f)
        val stepX = usableWidth / (count - 1).coerceAtLeast(1)

        val xCoords = FloatArray(count)
        for (i in 0 until count) {
            val x = chartLeft + xPadding + i * stepX
            xCoords[i] = x
            canvas.drawText(xLabels[i], x, chartBottom + 16f * density, xAxisTextPaint)
        }

        fun valueToY(minutes: Int): Float {
            val animatedVal = minutes * revealFraction
            val ratio = (animatedVal / yCeiling.toFloat()).coerceIn(0f, 1f)
            return chartBottom - ratio * chartHeight
        }

        // Previous period dashed path
        prevPath.reset()
        for (i in 0 until count) {
            val y = valueToY(previousValues.getOrElse(i) { 0 })
            if (i == 0) prevPath.moveTo(xCoords[i], y) else prevPath.lineTo(xCoords[i], y)
        }
        canvas.drawPath(prevPath, prevLinePaint)
        for (i in 0 until count) {
            val y = valueToY(previousValues.getOrElse(i) { 0 })
            canvas.drawCircle(xCoords[i], y, 2.8f * density, prevDotPaint)
        }

        // Current period glow + line + vertical drop guides
        currentPath.reset()
        for (i in 0 until count) {
            val valMinutes = currentValues.getOrElse(i) { 0 }
            val y = valueToY(valMinutes)
            if (valMinutes > 0) {
                canvas.drawLine(xCoords[i], y, xCoords[i], chartBottom, verticalGuidePaint)
            }
            if (i == 0) currentPath.moveTo(xCoords[i], y) else currentPath.lineTo(xCoords[i], y)
        }
        canvas.drawPath(currentPath, currentGlowPaint)
        canvas.drawPath(currentPath, currentLinePaint)

        // Current period glowing nodes + integer labels above
        val nodeRadius = 4.2f * density
        val labelAlpha = ((revealFraction - 0.35f) / 0.65f).coerceIn(0f, 1f)
        pointValuePaint.alpha = (labelAlpha * 255).toInt()
        for (i in 0 until count) {
            val valMinutes = currentValues.getOrElse(i) { 0 }
            val x = xCoords[i]
            val y = valueToY(valMinutes)
            canvas.drawCircle(x, y, nodeRadius, currentNodeOuterPaint)
            canvas.drawCircle(x, y, nodeRadius * 0.52f, currentNodeCenterPaint)
            if (valMinutes > 0) {
                canvas.drawText(valMinutes.toString(), x, y - 9f * density, pointValuePaint)
            }
        }
        pointValuePaint.alpha = 255

        // Bottom legend row centered
        val legendY = h - 10f * density
        val swatchW = 18f * density
        val gap = 6f * density
        val groupGap = 18f * density
        val w1 = swatchW + gap + legendTextPaint.measureText(currentLegendLabel)
        val w2 = swatchW + gap + legendTextPaint.measureText(previousLegendLabel)
        val totalLegendW = w1 + groupGap + w2
        val startX = ((w - totalLegendW) / 2f).coerceAtLeast(8f * density)
        val legendTextOffset = (legendTextPaint.descent() + legendTextPaint.ascent()) / 2f

        // Item 1: Bu hafta
        canvas.drawLine(startX, legendY, startX + swatchW, legendY, currentLinePaint)
        canvas.drawCircle(startX + swatchW / 2f, legendY, 3.2f * density, currentNodeOuterPaint)
        canvas.drawText(currentLegendLabel, startX + swatchW + gap, legendY - legendTextOffset, legendTextPaint)

        // Item 2: O'tgan hafta
        val item2X = startX + w1 + groupGap
        canvas.drawLine(item2X, legendY, item2X + swatchW, legendY, prevLinePaint)
        canvas.drawCircle(item2X + swatchW / 2f, legendY, 2.6f * density, prevDotPaint)
        canvas.drawText(previousLegendLabel, item2X + swatchW + gap, legendY - legendTextOffset, legendTextPaint)
    }
}
