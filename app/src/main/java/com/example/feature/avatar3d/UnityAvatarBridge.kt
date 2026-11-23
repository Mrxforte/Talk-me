package com.example.feature.avatar3d

/**
 * Unity-as-a-Library adapter boundary.
 *
 * The host app must not depend on UnityPlayer until a matching Unity export
 * has been supplied. An injected sender allows the Android feature to compile
 * and be tested independently of Unity.
 */
fun interface UnityMessageSender {
    fun send(gameObject: String, method: String, payload: String)
}

class UnityAvatarBridge(
    private val sender: UnityMessageSender,
    private val gameObject: String = "TalkMeAvatarBridge"
) {
    fun selectTutor(female: Boolean) {
        sender.send(gameObject, "OnSelectTutor", if (female) "lera" else "alex")
    }

    fun update(command: Avatar3dCommand) {
        sender.send(gameObject, "OnAvatarCommand", command.toUnityPayload())
    }
}
