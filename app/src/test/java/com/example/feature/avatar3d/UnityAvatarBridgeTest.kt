package com.example.feature.avatar3d

import org.junit.Assert.assertEquals
import org.junit.Test

class UnityAvatarBridgeTest {
    @Test
    fun tutorSelectionUsesTheUnityResourceIds() {
        val messages = mutableListOf<String>()
        val bridge = UnityAvatarBridge(UnityMessageSender { obj, method, payload ->
            messages.add("$obj:$method:$payload")
        })
        bridge.selectTutor(false)
        bridge.selectTutor(true)
        assertEquals(listOf("TalkMeAvatarBridge:OnSelectTutor:alex", "TalkMeAvatarBridge:OnSelectTutor:lera"), messages)
    }

    @Test
    fun sendsSpeakingCommandToUnity() {
        var received = ""
        val bridge = UnityAvatarBridge(UnityMessageSender { obj, method, payload ->
            received = "$obj:$method:$payload"
        })
        bridge.update(Avatar3dCommand(Avatar3dState.SPEAKING, "WARM", "NOD", 0.5f))
        assertEquals("TalkMeAvatarBridge:OnAvatarCommand:SPEAKING|WARM|NOD|0.5", received)
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectsAmplitudeOutsideNormalizedRange() {
        Avatar3dCommand(Avatar3dState.SPEAKING, speechAmplitude = 1.5f)
    }
}
