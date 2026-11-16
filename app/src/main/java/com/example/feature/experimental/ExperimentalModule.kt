package com.example.feature.experimental

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ExperimentalModule {

    @Binds
    @Singleton
    abstract fun bindGeminiChatbotEngine(
        impl: FirebaseGeminiChatbotEngine
    ): GeminiChatbotEngine
}
