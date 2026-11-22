package com.example.feature.speaking

import com.example.feature.avatar3d.Avatar3dCommand
import com.example.feature.avatar3d.Avatar3dDirector
import com.example.feature.avatar3d.Avatar3dState

/**
 * One source of truth for Unity and the existing Android avatar fallback.
 * The Unity runtime may subscribe to this mapping once its library is exported.
 */
object SpeakingAvatarMapper {
    private val director = Avatar3dDirector()

    fun map(
        state: ConversationState,
        audioAmplitude: Float = 0f,
        tutorAmplitude: Float = 0f
    ): Avatar3dCommand = when (state) {
        ConversationState.IDLE, ConversationState.STOPPED ->
            director.forConversation(Avatar3dState.IDLE)
        is ConversationState.LISTENING, is ConversationState.USER_SPEAKING ->
            director.forConversation(Avatar3dState.LISTENING, amplitude = audioAmplitude)
        is ConversationState.FINALIZING, is ConversationState.AI_THINKING ->
            director.forConversation(Avatar3dState.THINKING)
        is ConversationState.AI_SPEAKING ->
            director.forConversation(
                Avatar3dState.SPEAKING,
                reply = state.spokenResponse,
                amplitude = tutorAmplitude
            )
        is ConversationState.ERROR -> director.forConversation(Avatar3dState.ERROR)
    }
}
