package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.ai.FirebaseAiLearningService
import com.example.core.database.TalkMeDao
import com.example.core.database.TalkMeDatabase
import com.example.core.datastore.UserPreferencesRepositoryImpl
import com.example.data.repository.LearningRepositoryImpl
import com.example.data.repository.SpeechRepositoryImpl
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.SpeechRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Dependency Injection Module providing Room Database, DAOs, DataStore Preferences,
 * LearningRepository, AI Learning Service, and SpeechRepository.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTalkMeDatabase(
        @ApplicationContext context: Context
    ): TalkMeDatabase {
        return Room.databaseBuilder(
            context,
            TalkMeDatabase::class.java,
            "talkme_offline_db.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTalkMeDao(database: TalkMeDatabase): TalkMeDao {
        return database.talkMeDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLearningRepository(
        impl: LearningRepositoryImpl
    ): LearningRepository

    @Binds
    @Singleton
    abstract fun bindUserPreferencesRepository(
        impl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository

    @Binds
    @Singleton
    abstract fun bindAiLearningService(
        impl: FirebaseAiLearningService
    ): AiLearningService

    @Binds
    @Singleton
    abstract fun bindSpeechRepository(
        impl: SpeechRepositoryImpl
    ): SpeechRepository
}
