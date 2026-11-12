package com.example.core.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Renders a crisp vertical solid or dashed connector line for the curriculum timeline path.
 */
class VerticalDashedLineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val density = resources.displayMetrics.density
    private val linePath = Path()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f * density
        strokeCap = Paint.Cap.ROUND
        color = Color.parseColor("#526480")
    }

    private var isDashedMode: Boolean = false

    fun setLineStyle(colorInt: Int, dashed: Boolean) {
        paint.color = colorInt
        if (isDashedMode != dashed) {
            isDashedMode = dashed
            paint.pathEffect = if (dashed) {
                DashPathEffect(floatArrayOf(5f * density, 4.5f * density), 0f)
            } else {
                null
            }
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        linePath.reset()
        linePath.moveTo(centerX, 0f)
        linePath.lineTo(centerX, height.toFloat())
        canvas.drawPath(linePath, paint)
    }
}
