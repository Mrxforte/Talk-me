package com.example.feature.avatar3d

import org.junit.Assert.assertEquals
import org.junit.Test

class Avatar3dDirectorTest {
    private val director = Avatar3dDirector()

    @Test fun praiseProducesJoyfulGesture() {
        val command = director.forConversation(
            Avatar3dState.SPEAKING, "Great job!", Avatar3dDirector.Cue.PRAISE, 0.7f)
        assertEquals("JOYFUL", command.mood)
        assertEquals("PRAISE", command.gesture)
        assertEquals(0.7f, command.speechAmplitude, 0.0001f)
    }

    @Test fun amplitudeIsClamped() {
        assertEquals(1f, director.forConversation(Avatar3dState.SPEAKING, amplitude = 2f).speechAmplitude, 0.0001f)
    }
}
