package com.example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application entry point annotated with [@HiltAndroidApp] for Hilt Dependency Injection.
 * Note: Firebase App Check is intentionally configured in MainActivity.onCreate(savedInstanceState)
 * with the launch Intent rather than here, per Firebase AI Logic App Check requirements.
 */
@HiltAndroidApp
class TalkMeApplication : Application()
