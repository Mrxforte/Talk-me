package com.example.feature.speaking

import com.example.feature.avatar3d.Avatar3dState
import org.junit.Assert.assertEquals
import org.junit.Test

class SpeakingAvatarMapperTest {
    @Test fun speakingUsesTutorAmplitudeNotMicrophoneAmplitude() {
        val command = SpeakingAvatarMapper.map(
            ConversationState.AI_SPEAKING("turn1", "Hello!"),
            audioAmplitude = 0.9f,
            tutorAmplitude = 0.3f
        )
        assertEquals(Avatar3dState.SPEAKING, command.state)
        assertEquals(0.3f, command.speechAmplitude, 0.0001f)
    }

    @Test fun listeningUsesMicAmplitude() {
        val command = SpeakingAvatarMapper.map(
            ConversationState.LISTENING("turn2"),
            audioAmplitude = 0.4f
        )
        assertEquals(Avatar3dState.LISTENING, command.state)
        assertEquals(0.4f, command.speechAmplitude, 0.0001f)
    }
}
