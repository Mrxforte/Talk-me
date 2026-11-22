package com.example.feature.avatar3d

/**
 * Engine-independent tutor animation contract.
 *
 * Keep this model free of Unity dependencies so the existing Android UI can
 * continue working when a Unity runtime is unavailable.
 */
enum class Avatar3dState {
    IDLE,
    LISTENING,
    THINKING,
    SPEAKING,
    ERROR
}

data class Avatar3dCommand(
    val state: Avatar3dState,
    val mood: String = "NEUTRAL",
    val gesture: String = "STEADY",
    val speechAmplitude: Float = 0f
) {
    init {
        require(speechAmplitude in 0f..1f) { "Speech amplitude must be normalized" }
    }

    /** Unity SendMessage receives a single string argument. */
    fun toUnityPayload(): String = listOf(
        state.name,
        mood.uppercase(),
        gesture.uppercase(),
        speechAmplitude.toString()
    ).joinToString("|")
}
