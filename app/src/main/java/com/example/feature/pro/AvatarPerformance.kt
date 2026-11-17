package com.example.feature.pro

import java.util.Locale
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

/** Ten facial expressions and ten directed gestures yield 100 distinct performances. */
internal data class AvatarPerformance(val mood: Mood, val gesture: Gesture) {
    enum class Mood(val smile: Float, val brow: Float, val energy: Float) {
        NEUTRAL(0f, 0f, .9f),
        WARM(.35f, .12f, .8f),
        CURIOUS(.1f, .65f, .85f),
        JOYFUL(.85f, .3f, 1.18f),
        AMUSED(.7f, -.15f, 1.04f),
        EXCITED(.66f, .7f, 1.45f),
        THOUGHTFUL(-.15f, .28f, .56f),
        CONCERNED(-.6f, .30f, .66f),
        SURPRISED(.08f, 1f, 1.34f),
        FIRM(-.8f, -.78f, .72f)
    }

    enum class Gesture {
        STEADY, NOD, DOUBLE_NOD, TILT_LEFT, TILT_RIGHT, GLANCE_UP, GLANCE_DOWN, LEAN_IN, EASE_BACK, SHAKE
    }

    data class Pose(val yaw: Float = 0f, val roll: Float = 0f, val rise: Float = 0f, val zoom: Float = 0f)

    fun pose(seconds: Float): Pose {
        val phase = ((seconds - .1f) / 1.45f).coerceIn(0f, 1f)
        val pulse = sin(phase * PI.toFloat()) * mood.energy
        val cycle = phase * 2f * PI.toFloat()
        return when (gesture) {
            Gesture.STEADY -> Pose(yaw = .02f * pulse)
            Gesture.NOD -> Pose(rise = -.028f * sin(cycle) * pulse)
            Gesture.DOUBLE_NOD -> Pose(rise = -.022f * sin(2f * cycle) * pulse)
            Gesture.TILT_LEFT -> Pose(yaw = -.04f * pulse, roll = -.09f * pulse)
            Gesture.TILT_RIGHT -> Pose(yaw = .04f * pulse, roll = .09f * pulse)
            Gesture.GLANCE_UP -> Pose(yaw = .07f * pulse, rise = .022f * pulse)
            Gesture.GLANCE_DOWN -> Pose(yaw = -.06f * pulse, rise = -.020f * pulse)
            Gesture.LEAN_IN -> Pose(rise = -.013f * pulse, zoom = .075f * pulse)
            Gesture.EASE_BACK -> Pose(zoom = -.065f * pulse)
            Gesture.SHAKE -> Pose(yaw = .10f * sin(2f * cycle) * pulse, roll = .04f * sin(cycle) * pulse)
        }
    }

    fun expressionEnvelope(seconds: Float): Float = 1f - exp(-seconds.coerceAtLeast(0f) * 5f)

    companion object {
        val WAITING = AvatarPerformance(Mood.NEUTRAL, Gesture.STEADY)
        val LISTENING = AvatarPerformance(Mood.CURIOUS, Gesture.LEAN_IN)
        val THINKING = AvatarPerformance(Mood.THOUGHTFUL, Gesture.GLANCE_UP)

        fun forReply(delivery: String, text: String): AvatarPerformance {
            val fields = delivery.trim().uppercase(Locale.ROOT).split(':', limit = 2)
            val mood = Mood.entries.firstOrNull { it.name == fields.first() }
            val gesture = Gesture.entries.firstOrNull { it.name == fields.getOrNull(1) }
            if (mood != null && gesture != null) return AvatarPerformance(mood, gesture)

            val line = text.trim()
            return when {
                line.endsWith("!") -> AvatarPerformance(Mood.JOYFUL, Gesture.NOD)
                line.endsWith("?") -> AvatarPerformance(Mood.CURIOUS, Gesture.TILT_RIGHT)
                else -> AvatarPerformance(Mood.WARM, Gesture.STEADY)
            }
        }
    }
}
