package com.example.feature.avatar3d

/**
 * Converts semantic conversation cues into safe, deterministic avatar commands.
 * Emotion/gesture selection is presentation-only and must not alter tutor text.
 */
class Avatar3dDirector {
    enum class Cue { GREETING, EXPLAINING, QUESTION, PRAISE, CORRECTION, FAREWELL, NONE }

    fun forConversation(
        state: Avatar3dState,
        reply: String = "",
        cue: Cue = Cue.NONE,
        amplitude: Float = 0f
    ): Avatar3dCommand {
        val mood = when {
            state == Avatar3dState.LISTENING -> "CURIOUS"
            state == Avatar3dState.THINKING -> "THOUGHTFUL"
            state == Avatar3dState.ERROR -> "CONCERNED"
            cue == Cue.PRAISE -> "JOYFUL"
            cue == Cue.CORRECTION -> "FIRM"
            reply.trimEnd().endsWith("!") -> "EXCITED"
            reply.trimEnd().endsWith("?") -> "CURIOUS"
            state == Avatar3dState.SPEAKING -> "WARM"
            else -> "NEUTRAL"
        }
        val gesture = when (cue) {
            Cue.GREETING -> "GREETING"
            Cue.EXPLAINING -> "EXPLAINING"
            Cue.QUESTION -> "QUESTION"
            Cue.PRAISE -> "PRAISE"
            Cue.CORRECTION -> "CORRECTION"
            Cue.FAREWELL -> "FAREWELL"
            Cue.NONE -> "STEADY"
        }
        return Avatar3dCommand(state, mood, gesture, amplitude.coerceIn(0f, 1f))
    }
}
