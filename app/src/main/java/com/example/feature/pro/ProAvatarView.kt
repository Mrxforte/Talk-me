package com.example.feature.pro

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import com.example.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/**
 * A front-facing depth mesh textured with the tutor portrait. Face and body are projected
 * together so a shallow head turn preserves the artwork and produces real parallax.
 */
class ProAvatarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val portraitPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
    private val vertices = FloatArray((GRID + 1) * (GRID + 1) * 2)
    private val depths = FloatArray(vertices.size / 2)
    private var female = true
    private var portrait: Bitmap? = null
    private var action = ProAction.BUSY
    private var cue: TutorCue? = null
    private var showFullHead = false
    private var performance = AvatarPerformance.WAITING
    private var origin = SystemClock.uptimeMillis()
    private var angle = 0f
    private var lift = 0f
    private var scale = 0f
    private var left = 0f
    private var top = 0f
    private var cosine = 1f
    private var sine = 0f

    internal fun render(state: ProLessonState) = render(state.female, state.action, state.cue)

    internal fun render(
        female: Boolean,
        action: ProAction,
        cue: TutorCue?,
        showFullHead: Boolean = false,
        performance: AvatarPerformance = AvatarPerformance.WAITING
    ) {
        this.showFullHead = showFullHead
        if (this.female != female || portrait == null) {
            this.female = female
            portrait = BitmapFactory.decodeResource(
                resources, if (female) R.drawable.pro_avatar_girl else R.drawable.pro_avatar_boy
            )
            prepareDepths()
        }
        if (this.action != action || this.performance != performance) origin = SystemClock.uptimeMillis()
        this.performance = performance
        this.action = action
        this.cue = cue
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val image = portrait ?: return
        val now = SystemClock.uptimeMillis()
        val elapsed = (now - origin) / 1000f
        val pose = performance.pose(elapsed)
        val expression = performance.expressionEnvelope(elapsed)
        angle = sin(elapsed * 0.74f) * .025f + pose.yaw
        lift = sin(elapsed * 1.65f) * .006f + pose.rise
        cosine = cos(angle)
        sine = sin(angle)
        scale = if (showFullHead) {
            min(width * .98f, height / (ASPECT * .75f))
        } else {
            max(width.toFloat(), height * image.width.toFloat() / image.height)
        } * (1f + pose.zoom)
        left = (width - scale) / 2f
        top = if (showFullHead) max(0f, (height - scale * ASPECT) / 2f) + height * .034f
            else (height - scale * ASPECT) / 2f
        val mouth = if (action == ProAction.PLAYING) mouthShape(now, cue) else 0f
        val mouthY = if (female) .430f else .453f
        val mouthX = if (female) .500f else .511f
        val blinkTime = (elapsed + .65f) % 4.15f
        val blink = if (blinkTime in 3.77f..3.91f) sin((blinkTime - 3.77f) / .14f * PI.toFloat()) else 0f

        for (row in 0..GRID) {
            val v = row.toFloat() / GRID
            for (col in 0..GRID) {
                val u = col.toFloat() / GRID
                val index = row * (GRID + 1) + col
                val jaw = bump(u, mouthX, .13f) * bump(v, mouthY + .035f, .05f) * mouth * .023f
                val smile = performance.mood.smile * expression * .010f *
                    (bump(u, mouthX - .057f, .023f) + bump(u, mouthX + .057f, .023f)) *
                    bump(v, mouthY, .030f)
                val leftEye = eyeDeformation(u, v, if (female) .422f else .425f, if (female) .322f else .354f, blink)
                val rightEye = eyeDeformation(u, v, if (female) .578f else .590f, if (female) .321f else .339f, blink)
                project(u, v + jaw - smile + leftEye + rightEye, depths[index], vertices, index * 2)
            }
        }
        canvas.save()
        canvas.rotate(pose.roll * 57.29578f, width / 2f, height * .44f)
        canvas.drawBitmapMesh(image, GRID, GRID, vertices, 0, null, 0, portraitPaint)
        canvas.restore()
        if (isShown) postInvalidateDelayed(FRAME_MS)
    }

    private fun eyeDeformation(u: Float, v: Float, x: Float, y: Float, blink: Float): Float {
        val eyeWeight = bump(u, x, .045f)
        val lid = (y - v) * bump(v, y, .033f) * blink * .68f
        val brow = bump(v, y - .065f, .028f) * performance.mood.brow * .012f
        return eyeWeight * (lid - brow)
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
        if (cue == null) return 0f
        val elapsed = (now - cue.atMs).coerceAtLeast(0)
        cue.amplitude?.let { return it.coerceIn(0f, 1f) * (1f - elapsed / 125f).coerceAtLeast(0f) }
        if (cue.words.isBlank() || elapsed > 360) return 0f
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
