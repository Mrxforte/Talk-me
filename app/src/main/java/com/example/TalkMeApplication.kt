package com.example

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point annotated with [@HiltAndroidApp] for Hilt Dependency Injection.
 * Ensures FirebaseApp is initialized early on process startup.
 */
@HiltAndroidApp
class TalkMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        runCatching {
            if (FirebaseApp.getApps(this).isEmpty()) {
                FirebaseApp.initializeApp(this)
            }
        }
    }
}
