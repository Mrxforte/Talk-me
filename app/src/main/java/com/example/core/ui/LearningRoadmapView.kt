package com.example.core.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

/**
 * Interactive cosmic dark Mountain Roadmap View for the Profile "O'quv yo'li" card.
 * Draws the winding slope trail dynamically between "Boshlanish" and the target peak level
 * ("A1", "A2", ... "C2"), with animated green completed checkpoints and glowing "Bugun" marker.
 */
class LearningRoadmapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val density = resources.displayMetrics.density
    private val scaledDensity = resources.displayMetrics.scaledDensity

    private var startLabel: String = "Boshlanish"
    private var todayLabel: String = "Bugun"
    private var peakLevelLabel: String = "A1"
    private var milestoneCount: Int = 4

    private var targetRatio: Float = 0f
    private var animatedRatio: Float = 0f
    private var hasAnimatedNonZero: Boolean = false
    private var roadmapAnimator: ValueAnimator? = null

    private val fullPath = Path()
    private val completedPath = Path()
    private val dashedRemainingPath = Path()
    private val pathMeasure = PathMeasure()
    private val posCoords = FloatArray(2)
    private val badgeRect = RectF()
    private val trianglePath = Path()

    private val trailGlowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 7f * density
        strokeCap = Paint.Cap.ROUND
        color = Color.argb(72, 0, 230, 118)
    }

    private val completedTrailPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3.2f * density
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#4ADE80")
    }

    private val remainingTrailPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.4f * density
        strokeCap = Paint.Cap.ROUND
        color = Color.argb(210, 226, 232, 240)
        pathEffect = DashPathEffect(floatArrayOf(8f * density, 6f * density), 0f)
    }

    private val checkCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#22C55E")
    }

    private val checkCircleBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.6f * density
        color = Color.parseColor("#DCFCE7")
    }

    private val futureCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.argb(130, 15, 31, 61)
    }

    private val futureCircleBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.5f * density
        color = Color.argb(170, 148, 163, 184)
    }

    private val checkMarkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.0f * density
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
        color = Color.WHITE
    }

    private val glowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val todayOuterPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#00E5FF")
    }

    private val todayInnerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val todayCenterDotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#0284C7")
    }

    private val startFlagBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#0F3B2E")
    }

    private val startFlagStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.2f * density
        color = Color.parseColor("#4ADE80")
    }

    private val startPolePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.2f * density
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#4ADE80")
    }

    private val todayBubbleBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#082942")
    }

    private val todayBubbleStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 1.3f * density
        color = Color.parseColor("#00E5FF")
    }

    private val peakFlagBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#F59E0B")
    }

    private val peakPolePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2.4f * density
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#FDE68A")
    }

    private val labelTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 11f * scaledDensity
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        color = Color.WHITE
    }

    private val peakTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 12.5f * scaledDensity
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        color = Color.parseColor("#111827")
    }

    fun setRoadmapState(
        peakLevelCode: String,
        progressRatio: Float,
        milestones: Int = 4,
        startText: String = "Boshlanish",
        todayText: String = "Bugun",
        forceReplayFromZero: Boolean = false
    ) {
        val clamped = progressRatio.coerceIn(0f, 1f)
        val sameTarget = (peakLevelLabel == peakLevelCode) &&
            (kotlin.math.abs(targetRatio - clamped) < 0.001f) &&
            (milestoneCount == milestones)

        peakLevelLabel = peakLevelCode.ifBlank { "A1" }
        milestoneCount = milestones.coerceIn(3, 6)
        startLabel = startText
        todayLabel = todayText
        targetRatio = clamped

        if (!forceReplayFromZero && hasAnimatedNonZero && sameTarget) {
            return
        }

        if (clamped <= 0f) {
            roadmapAnimator?.cancel()
            animatedRatio = 0f
            invalidate()
            return
        }

        val startAnim = if (forceReplayFromZero || !hasAnimatedNonZero) 0f else animatedRatio
        hasAnimatedNonZero = true
        startRoadmapAnimator(startAnim, clamped)
    }

    fun replayAnimationFromZero() {
        if (targetRatio <= 0f) {
            roadmapAnimator?.cancel()
            animatedRatio = 0f
            invalidate()
            return
        }
        val running = roadmapAnimator
        if (running?.isRunning == true && running.currentPlayTime < 80L && animatedRatio < 0.05f) {
            return
        }
        hasAnimatedNonZero = true
        startRoadmapAnimator(0f, targetRatio)
    }

    private fun startRoadmapAnimator(startRatio: Float, endRatio: Float) {
        roadmapAnimator?.cancel()
        animatedRatio = startRatio
        roadmapAnimator = ValueAnimator.ofFloat(startRatio, endRatio).apply {
            duration = 950L
            interpolator = FastOutSlowInInterpolator()
            addUpdateListener {
                animatedRatio = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    fun getAnimatedRatio(): Float = animatedRatio
    fun getTargetRatio(): Float = targetRatio
    fun getPeakLevelLabel(): String = peakLevelLabel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isShown && targetRatio > 0f) {
            replayAnimationFromZero()
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE && isAttachedToWindow && isShown && targetRatio > 0f) {
            replayAnimationFromZero()
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == VISIBLE && isAttachedToWindow && isShown && targetRatio > 0f) {
            replayAnimationFromZero()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        roadmapAnimator?.cancel()
    }

    private fun buildTrailPath(w: Float, h: Float) {
        fullPath.reset()
        // Winding climb from bottom-left valley up the mountain ridge to the golden peak
        val sx = w * 0.085f
        val sy = h * 0.79f
        val p1x = w * 0.28f
        val p1y = h * 0.75f
        val m1x = w * 0.44f
        val m1y = h * 0.65f
        val p2x = w * 0.56f
        val p2y = h * 0.57f
        val m2x = w * 0.64f
        val m2y = h * 0.53f
        val p3x = w * 0.70f
        val p3y = h * 0.41f
        val ex = w * 0.755f
        val ey = h * 0.255f

        fullPath.moveTo(sx, sy)
        fullPath.quadTo(p1x, p1y, m1x, m1y)
        fullPath.quadTo(p2x, p2y, m2x, m2y)
        fullPath.quadTo(p3x, p3y, ex, ey)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || height <= 0) return

        val w = width.toFloat()
        val h = height.toFloat()
        buildTrailPath(w, h)

        pathMeasure.setPath(fullPath, false)
        val totalLen = pathMeasure.length.coerceAtLeast(1f)
        val currentLen = totalLen * animatedRatio.coerceIn(0f, 1f)

        // 1. Peak golden glow radial highlight at mountain summit
        pathMeasure.getPosTan(totalLen, posCoords, null)
        val peakX = posCoords[0]
        val peakY = posCoords[1]
        glowPaint.shader = RadialGradient(
            peakX,
            peakY,
            34f * density,
            intArrayOf(
                Color.argb(175, 255, 213, 79),
                Color.argb(60, 245, 158, 11),
                Color.TRANSPARENT
            ),
            floatArrayOf(0f, 0.5f, 1f),
            Shader.TileMode.CLAMP
        )
        canvas.drawCircle(peakX, peakY, 34f * density, glowPaint)

        // 2. Remaining dashed trail ahead
        dashedRemainingPath.reset()
        if (currentLen < totalLen) {
            pathMeasure.getSegment(currentLen, totalLen, dashedRemainingPath, true)
            canvas.drawPath(dashedRemainingPath, remainingTrailPaint)
        }

        // 3. Completed neon green trail up to animatedRatio
        completedPath.reset()
        if (currentLen > 1f) {
            pathMeasure.getSegment(0f, currentLen, completedPath, true)
            canvas.drawPath(completedPath, trailGlowPaint)
            canvas.drawPath(completedPath, completedTrailPaint)
        }

        // 4. Intermediate checkpoints along the slope
        val checkpointFractions = FloatArray(milestoneCount) { idx ->
            (idx + 1).toFloat() / (milestoneCount + 1).toFloat()
        }
        val checkRadius = 9f * density
        for (fraction in checkpointFractions) {
            pathMeasure.getPosTan(totalLen * fraction, posCoords, null)
            val cx = posCoords[0]
            val cy = posCoords[1]
            val isReached = animatedRatio >= fraction && targetRatio > 0f
            if (isReached) {
                glowPaint.shader = RadialGradient(
                    cx,
                    cy,
                    checkRadius * 1.9f,
                    intArrayOf(Color.argb(120, 74, 222, 128), Color.TRANSPARENT),
                    null,
                    Shader.TileMode.CLAMP
                )
                canvas.drawCircle(cx, cy, checkRadius * 1.9f, glowPaint)
                canvas.drawCircle(cx, cy, checkRadius, checkCirclePaint)
                canvas.drawCircle(cx, cy, checkRadius, checkCircleBorderPaint)

                // Draw check mark
                canvas.drawLine(
                    cx - 3.6f * density, cy + 0.3f * density,
                    cx - 0.8f * density, cy + 3.2f * density,
                    checkMarkPaint
                )
                canvas.drawLine(
                    cx - 0.8f * density, cy + 3.2f * density,
                    cx + 4.0f * density, cy - 2.6f * density,
                    checkMarkPaint
                )
            } else {
                canvas.drawCircle(cx, cy, checkRadius * 0.72f, futureCirclePaint)
                canvas.drawCircle(cx, cy, checkRadius * 0.72f, futureCircleBorderPaint)
            }
        }

        // 5. Start flag ("Boshlanish") on left valley
        pathMeasure.getPosTan(0f, posCoords, null)
        val startX = posCoords[0]
        val startY = posCoords[1]
        canvas.drawCircle(startX, startY, 4.5f * density, checkCirclePaint)
        val poleTopY = startY - 34f * density
        canvas.drawLine(startX, startY, startX, poleTopY, startPolePaint)

        val startTextWidth = labelTextPaint.measureText(startLabel)
        val startPillW = startTextWidth + 18f * density
        val startPillH = 21f * density
        val pillLeft = (startX - 4f * density).coerceAtLeast(6f * density)
        badgeRect.set(pillLeft, poleTopY, pillLeft + startPillW, poleTopY + startPillH)
        canvas.drawRoundRect(badgeRect, 10.5f * density, 10.5f * density, startFlagBgPaint)
        canvas.drawRoundRect(badgeRect, 10.5f * density, 10.5f * density, startFlagStrokePaint)
        val textCenterOffset = (labelTextPaint.descent() + labelTextPaint.ascent()) / 2f
        labelTextPaint.color = Color.parseColor("#DCFCE7")
        canvas.drawText(
            startLabel,
            badgeRect.centerX(),
            badgeRect.centerY() - textCenterOffset,
            labelTextPaint
        )

        // 6. Peak Flag ("A1" / "A2" / etc.) at right summit
        val peakPoleTopY = (peakY - 30f * density).coerceAtLeast(8f * density)
        canvas.drawLine(peakX, peakY, peakX, peakPoleTopY, peakPolePaint)
        val peakW = (peakTextPaint.measureText(peakLevelLabel) + 22f * density).coerceAtLeast(44f * density)
        val peakH = 24f * density
        val peakLeft = (peakX + 2f * density).coerceAtMost(w - peakW - 6f * density)
        badgeRect.set(peakLeft, peakPoleTopY, peakLeft + peakW, peakPoleTopY + peakH)
        canvas.drawRoundRect(badgeRect, 7f * density, 7f * density, peakFlagBgPaint)
        val peakOffset = (peakTextPaint.descent() + peakTextPaint.ascent()) / 2f
        canvas.drawText(
            peakLevelLabel,
            badgeRect.centerX(),
            badgeRect.centerY() - peakOffset,
            peakTextPaint
        )
        canvas.drawCircle(peakX, peakY, 4.5f * density, peakFlagBgPaint)

        // 7. Current position pulsing cyan beacon + "Bugun" bubble
        pathMeasure.getPosTan(currentLen, posCoords, null)
        val curX = posCoords[0]
        val curY = posCoords[1]
        glowPaint.shader = RadialGradient(
            curX,
            curY,
            22f * density,
            intArrayOf(
                Color.argb(200, 0, 229, 255),
                Color.argb(70, 0, 229, 255),
                Color.TRANSPARENT
            ),
            null,
            Shader.TileMode.CLAMP
        )
        canvas.drawCircle(curX, curY, 22f * density, glowPaint)
        glowPaint.shader = null
        canvas.drawCircle(curX, curY, 9.5f * density, todayOuterPaint)
        canvas.drawCircle(curX, curY, 6.2f * density, todayInnerPaint)
        canvas.drawCircle(curX, curY, 3.0f * density, todayCenterDotPaint)

        // Position "Bugun" pill cleanly above current beacon (shift slightly right if at 0% to avoid covering "Boshlanish")
        val todayTextW = labelTextPaint.measureText(todayLabel)
        val todayPillW = todayTextW + 20f * density
        val todayPillH = 22f * density
        val minCenterX = if (animatedRatio < 0.14f) badgeRect.right.coerceAtMost(w * 0.25f) else 8f * density + todayPillW / 2f
        val bubbleCenterX = curX.coerceIn(
            (if (animatedRatio < 0.14f) w * 0.24f else 8f * density + todayPillW / 2f),
            w - todayPillW / 2f - 8f * density
        )
        val bubbleBottom = (curY - 15f * density).coerceAtLeast(todayPillH + 8f * density)
        val bubbleTop = bubbleBottom - todayPillH
        badgeRect.set(
            bubbleCenterX - todayPillW / 2f,
            bubbleTop,
            bubbleCenterX + todayPillW / 2f,
            bubbleBottom
        )
        canvas.drawRoundRect(badgeRect, 11f * density, 11f * density, todayBubbleBgPaint)
        canvas.drawRoundRect(badgeRect, 11f * density, 11f * density, todayBubbleStrokePaint)

        // Little pointer triangle below bubble pointing toward curX
        val tipX = curX.coerceIn(badgeRect.left + 8f * density, badgeRect.right - 8f * density)
        trianglePath.reset()
        trianglePath.moveTo(tipX - 4f * density, bubbleBottom - 0.5f * density)
        trianglePath.lineTo(tipX + 4f * density, bubbleBottom - 0.5f * density)
        trianglePath.lineTo(tipX, bubbleBottom + 4.5f * density)
        trianglePath.close()
        canvas.drawPath(trianglePath, todayBubbleBgPaint)

        labelTextPaint.color = Color.parseColor("#E0F2FE")
        canvas.drawText(
            todayLabel,
            badgeRect.centerX(),
            badgeRect.centerY() - textCenterOffset,
            labelTextPaint
        )
    }
}
