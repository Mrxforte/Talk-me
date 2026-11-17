package com.example.feature.pro

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.sin

/**
 * A front-facing depth mesh textured with the tutor portrait. Face and body are projected
 * together so a shallow head turn preserves the artwork and produces real parallax.
 */
class ProAvatarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val portraitPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val detailPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mouthColor = ContextCompat.getColor(context, R.color.pro_avatar_mouth)
    private val eyelidColor = ContextCompat.getColor(context, R.color.pro_avatar_eyelid)
    private val vertices = FloatArray((GRID + 1) * (GRID + 1) * 2)
    private val depths = FloatArray(vertices.size / 2)
    private var female = true
    private var portrait: Bitmap? = null
    private var action = ProAction.BUSY
    private var cue: TutorCue? = null
    private var verticalCropBias = 0.5f
    private var origin = SystemClock.uptimeMillis()
    private var angle = 0f
    private var lift = 0f
    private var scale = 0f
    private var left = 0f
    private var top = 0f
    private var cosine = 1f
    private var sine = 0f
    private val point = FloatArray(2)

    internal fun render(state: ProLessonState) = render(state.female, state.action, state.cue)

    internal fun render(female: Boolean, action: ProAction, cue: TutorCue?, verticalCropBias: Float = 0.5f) {
        this.verticalCropBias = verticalCropBias
        if (this.female != female || portrait == null) {
            this.female = female
            portrait = BitmapFactory.decodeResource(
                resources, if (female) R.drawable.pro_avatar_girl else R.drawable.pro_avatar_boy
            )
            prepareDepths()
        }
        if (this.action != action) origin = SystemClock.uptimeMillis()
        this.action = action
        this.cue = cue
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val image = portrait ?: return
        val now = SystemClock.uptimeMillis()
        val elapsed = (now - origin) / 1000f
        val talking = action == ProAction.PLAYING && cue != null
        val attentive = action == ProAction.LISTENING
        angle = sin(elapsed * (if (talking) 1.5f else 0.8f)) * (if (talking) 0.10f else 0.065f)
        lift = sin(elapsed * 1.65f) * (if (attentive) 0.016f else 0.010f)
        cosine = cos(angle)
        sine = sin(angle)
        scale = max(width.toFloat(), height * image.width.toFloat() / image.height)
        left = (width - scale) / 2f
        top = (height - scale * ASPECT) * verticalCropBias
        val mouth = if (talking) mouthShape(now, cue) else 0f
        val mouthY = if (female) 0.430f else 0.453f
        val mouthX = if (female) 0.500f else 0.511f

        for (row in 0..GRID) {
            val v = row.toFloat() / GRID
            for (col in 0..GRID) {
                val u = col.toFloat() / GRID
                val index = row * (GRID + 1) + col
                val jaw = bump(u, mouthX, 0.14f) * bump(v, mouthY + .032f, .085f) * mouth * .014f
                project(u, v + jaw, depths[index], vertices, index * 2)
            }
        }
        canvas.drawBitmapMesh(image, GRID, GRID, vertices, 0, null, 0, portraitPaint)
        if (mouth > 0.06f) {
            project(mouthX, mouthY + mouth * .003f, depth(mouthX, mouthY), point, 0)
            val radiusX = scale * (.027f + mouth * .019f)
            val radiusY = scale * mouth * .012f
            detailPaint.color = mouthColor
            detailPaint.alpha = (180 + mouth * 60).toInt()
            canvas.drawOval(point[0] - radiusX, point[1] - radiusY, point[0] + radiusX, point[1] + radiusY, detailPaint)
        }
        val blinkTime = elapsed % 4.15f
        if (blinkTime in 3.77f..3.91f) {
            val amount = sin((blinkTime - 3.77f) / .14f * PI.toFloat())
            drawBlink(canvas, if (female) .422f else .425f, if (female) .322f else .354f, amount)
            drawBlink(canvas, if (female) .578f else .590f, if (female) .321f else .339f, amount)
        }
        if (isShown) postInvalidateDelayed(FRAME_MS)
    }

    private fun drawBlink(canvas: Canvas, x: Float, y: Float, amount: Float) {
        project(x, y, depth(x, y), point, 0)
        val wide = scale * .031f
        val high = scale * .027f * amount
        detailPaint.color = eyelidColor
        detailPaint.alpha = (225 * amount).toInt().coerceIn(0, 255)
        canvas.drawOval(point[0] - wide, point[1] - high, point[0] + wide, point[1] + high, detailPaint)
    }

    private fun prepareDepths() {
        for (row in 0..GRID) {
            for (col in 0..GRID) {
                depths[row * (GRID + 1) + col] = depth(col.toFloat() / GRID, row.toFloat() / GRID)
            }
        }
    }

    private fun depth(u: Float, v: Float): Float =
        .14f * bump(u, .50f, .24f) * bump(v, .34f, .27f) +
            .07f * bump(u, .51f, .066f) * bump(v, .38f, .10f) +
            .045f * bump(u, .50f, .36f) * bump(v, .75f, .36f)

    private fun bump(value: Float, center: Float, spread: Float): Float {
        val position = (value - center) / spread
        return exp(-position * position)
    }

    private fun project(u: Float, v: Float, z: Float, output: FloatArray, offset: Int) {
        val x = u - .5f
        val rotatedZ = z * cosine - x * sine
        val perspective = 2.8f / (2.8f - rotatedZ)
        output[offset] = left + scale * (.5f + (x * cosine + z * sine) * perspective)
        output[offset + 1] = top + scale * (ASPECT * (.5f + (v - .5f) * perspective) + lift)
    }

    private fun mouthShape(now: Long, cue: TutorCue?): Float {
        if (cue == null || cue.words.isBlank()) return 0f
        val elapsed = (now - cue.atMs).coerceAtLeast(0)
        val text = cue.words
        val index = (elapsed / PHONEME_MS).toInt()
        if (index >= text.length) return 0f
        fun openness(char: Char) = when (char.lowercaseChar()) {
            'm', 'b', 'p', ' ', '.', ',', '!', '?' -> 0f
            'a', 'e', 'ä' -> .95f
            'o', 'u', 'w' -> .74f
            'i', 'y' -> .54f
            else -> .30f
        }
        val current = openness(text[index])
        val following = text.getOrNull(index + 1)?.let(::openness) ?: 0f
        val fraction = (elapsed % PHONEME_MS).toFloat() / PHONEME_MS
        return current + (following - current) * fraction
    }

    private companion object {
        const val GRID = 28
        const val FRAME_MS = 33L
        const val PHONEME_MS = 72L
        const val ASPECT = 645f / 600f
    }
}
