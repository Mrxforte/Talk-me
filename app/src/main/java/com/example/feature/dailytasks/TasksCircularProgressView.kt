package com.example.feature.dailytasks

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.SweepGradient
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class TasksCircularProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#152445")
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    }

    private val rectF = RectF()
    private var progressRatio = 0.5f
    private var textValue = "2/4"

    fun setProgress(completed: Int, total: Int) {
        val safeTotal = if (total > 0) total else 4
        progressRatio = (completed.toFloat() / safeTotal.toFloat()).coerceIn(0f, 1f)
        textValue = "$completed/$safeTotal"
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val stroke = (w * 0.09f).coerceAtLeast(6f)
        trackPaint.strokeWidth = stroke
        progressPaint.strokeWidth = stroke
        val inset = stroke / 2f + 4f
        rectF.set(inset, inset, w - inset, h - inset)

        progressPaint.shader = SweepGradient(
            w / 2f,
            h / 2f,
            intArrayOf(
                Color.parseColor("#00E5FF"),
                Color.parseColor("#9D4EDD"),
                Color.parseColor("#00E5FF")
            ),
            floatArrayOf(0f, 0.5f, 1f)
        )
        textPaint.textSize = (w * 0.28f).coerceAtLeast(24f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(rectF, trackPaint)
        val sweepAngle = progressRatio * 360f
        if (sweepAngle > 0f) {
            canvas.save()
            canvas.rotate(-90f, width / 2f, height / 2f)
            canvas.drawArc(rectF, 0f, sweepAngle, false, progressPaint)
            canvas.restore()
        }
        val fontMetrics = textPaint.fontMetrics
        val baseline = height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f
        canvas.drawText(textValue, width / 2f, baseline, textPaint)
    }
}
