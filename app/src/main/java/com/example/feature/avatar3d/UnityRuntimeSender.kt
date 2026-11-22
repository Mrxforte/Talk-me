package com.example.feature.avatar3d

import android.util.Log
import java.lang.reflect.Method

/**
 * Optional Unity-as-a-Library runtime integration. This adapter keeps the Android
 * app buildable without unityLibrary; UnityPlayer must be initialized by the host
 * after a real Unity Android export is included.
 *
 * No messages are sent when Unity is absent. The native tutor remains visible.
 */
class UnityRuntimeSender : UnityMessageSender {
    private val unitySendMessage: Method? by lazy {
        runCatching {
            Class.forName("com.unity3d.player.UnityPlayer").getMethod(
                "UnitySendMessage", String::class.java, String::class.java, String::class.java
            )
        }.getOrNull()
    }

    val isUnityAvailable: Boolean get() = unitySendMessage != null

    override fun send(gameObject: String, method: String, payload: String) {
        val send = unitySendMessage ?: return
        runCatching { send.invoke(null, gameObject, method, payload) }
            .onFailure { Log.w("TalkMeUnityAvatar", "Unity avatar command not delivered", it) }
    }
}
