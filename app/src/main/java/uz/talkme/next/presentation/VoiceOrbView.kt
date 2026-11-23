package uz.talkme.next.presentation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import uz.talkme.next.R
import uz.talkme.next.domain.Phase
import kotlin.math.min
import kotlin.math.sin

/** Procedural voice artwork; microphone response is driven by measured PCM RMS. */
class VoiceOrbView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circle = Path()
    private val ribbon = Path()
    private var radius = 0f
    private var angle = 0f
    private var amplitude = 0f
    private var phase = Phase.IDLE
    private var glow: Shader? = null
    private var sphere: Shader? = null
    private var silk: Shader? = null
    private var running = false
    private val light = ContextCompat.getColor(context, R.color.orb_center)
    private val accent = ContextCompat.getColor(context, R.color.accent)
    private val deep = ContextCompat.getColor(context, R.color.orb_deep)
    private val pale = ContextCompat.getColor(context, R.color.orb_ribbon)
    private val clear = ContextCompat.getColor(context, R.color.transparent)
    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 10000L
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        addUpdateListener { angle = (it.animatedValue as Float) * 360f; invalidate() }
    }

    fun update(phase: Phase, microphoneLevel: Float) {
        this.phase = phase
        amplitude = microphoneLevel.coerceIn(0f, 1f)
        invalidate()
    }
    fun animateWhileVisible(enabled: Boolean) {
        running = enabled
        if (enabled && isAttachedToWindow && ValueAnimator.areAnimatorsEnabled()) animator.start()
        else animator.cancel()
    }
    override fun onAttachedToWindow() { super.onAttachedToWindow(); if (running) animateWhileVisible(true) }
    override fun onDetachedFromWindow() { animator.cancel(); super.onDetachedFromWindow() }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) * 0.39f
        if (radius <= 0) return
        glow = RadialGradient(0f, 0f, radius * 1.35f, intArrayOf(accent, clear),
            floatArrayOf(0.28f, 1f), Shader.TileMode.CLAMP)
        sphere = RadialGradient(-radius * 0.4f, -radius * 0.45f, radius * 2f,
            intArrayOf(pale, light, deep), floatArrayOf(0f, 0.28f, 1f), Shader.TileMode.CLAMP)
        silk = LinearGradient(-radius, -radius, radius * 0.6f, radius,
            intArrayOf(clear, pale, accent, clear), floatArrayOf(0f, 0.38f, 0.52f, 1f), Shader.TileMode.CLAMP)
        circle.reset(); circle.addCircle(0f, 0f, radius, Path.Direction.CW)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (radius <= 0f) return
        val activity = when (phase) {
            Phase.LISTENING -> amplitude * 0.06f
            Phase.SPEAKING -> (sin(Math.toRadians(angle.toDouble()) * 3).toFloat() + 1f) * 0.015f
            else -> 0f
        }
        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.scale(0.96f + activity, 0.96f + activity)
        paint.style = Paint.Style.FILL
        paint.shader = glow; paint.alpha = if (phase == Phase.MUTED) 16 else 28
        canvas.drawCircle(0f, 0f, radius * 1.35f, paint)
        paint.shader = sphere; paint.alpha = if (phase == Phase.MUTED) 110 else 240
        canvas.drawCircle(0f, 0f, radius, paint)
        canvas.save(); canvas.clipPath(circle)
        canvas.rotate(angle * 0.13f - 24f)
        paint.shader = silk; paint.alpha = 155
        ribbon.reset()
        ribbon.moveTo(-radius * 1.1f, radius * 0.15f)
        ribbon.cubicTo(-radius * 0.4f, -radius * 0.95f, radius * 0.18f, radius * 0.8f, radius * 1.1f, -radius * 0.3f)
        ribbon.cubicTo(radius * 0.4f, radius * 0.45f, -radius * 0.5f, radius * 0.05f, -radius * 1.1f, radius * 0.6f)
        ribbon.close(); canvas.drawPath(ribbon, paint)
        canvas.rotate(92f)
        paint.alpha = 100; canvas.drawPath(ribbon, paint)
        canvas.restore()
        paint.shader = null; paint.style = Paint.Style.STROKE
        paint.color = pale; paint.alpha = 105; paint.strokeWidth = radius * 0.004f
        canvas.drawCircle(0f, 0f, radius, paint)
        paint.style = Paint.Style.FILL; paint.alpha = 255
        canvas.restore()
    }
}
