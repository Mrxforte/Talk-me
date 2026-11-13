package com.example.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.core.common.AppConstants
import com.example.domain.model.UserPreferencesModel
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.talkMeDataStore by preferencesDataStore(name = "talkme_preferences")

/**
 * DataStore implementation of UserPreferencesRepository.
 * Persists appearance theme, TTS audio preferences, premium subscription status,
 * and Continue Learning bookmark across app restarts.
 */
@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreferencesRepository {

    private object Keys {
        val USER_NAME = stringPreferencesKey("user_name")
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        val SPEECH_SPEED = floatPreferencesKey("speech_speed")
        val PREMIUM_SUBSCRIBED = booleanPreferencesKey("premium_subscribed")
        val PREFERRED_TTS = stringPreferencesKey("preferred_tts_provider")
        val VOICE_GENDER = stringPreferencesKey("voice_gender")
        val OFFLINE_AUDIO = booleanPreferencesKey("offline_audio_enabled")
        val LESSON_REMINDERS = booleanPreferencesKey("lesson_reminders_enabled")
        val DAILY_GOAL_MINUTES = intPreferencesKey("daily_goal_minutes")
        val LAST_TOPIC_ID = stringPreferencesKey("last_topic_id")
        val LAST_SECTION_ID = stringPreferencesKey("last_section_id")
        val LAST_SCROLL_OFFSET = intPreferencesKey("last_scroll_offset")
        val HAS_STARTED_LEARNING = booleanPreferencesKey("has_started_learning")
    }

    override val userPreferencesFlow: Flow<UserPreferencesModel> =
        context.talkMeDataStore.data.map { prefs ->
            UserPreferencesModel(
                userName = prefs[Keys.USER_NAME] ?: "Azamat",
                themeMode = prefs[Keys.THEME_MODE] ?: "SYSTEM",
                soundEnabled = prefs[Keys.SOUND_ENABLED] ?: true,
                speechSpeed = prefs[Keys.SPEECH_SPEED] ?: 1.0f,
                isPremiumSubscribed = prefs[Keys.PREMIUM_SUBSCRIBED] ?: false,
                preferredTtsProvider = prefs[Keys.PREFERRED_TTS] ?: "NATIVE",
                voiceGender = prefs[Keys.VOICE_GENDER] ?: "MALE",
                offlineAudioEnabled = prefs[Keys.OFFLINE_AUDIO] ?: false,
                lessonRemindersEnabled = prefs[Keys.LESSON_REMINDERS] ?: true,
                dailyGoalMinutes = prefs[Keys.DAILY_GOAL_MINUTES] ?: 15,
                lastActiveTopicId = prefs[Keys.LAST_TOPIC_ID] ?: AppConstants.INITIAL_TOPIC_ID,
                lastActiveSectionId = prefs[Keys.LAST_SECTION_ID] ?: AppConstants.INITIAL_SECTION_ID,
                lastScrollOffset = prefs[Keys.LAST_SCROLL_OFFSET] ?: 0,
                hasStartedLearning = prefs[Keys.HAS_STARTED_LEARNING] ?: false
            )
        }

    override suspend fun updateUserName(name: String) {
        context.talkMeDataStore.edit { it[Keys.USER_NAME] = name.trim().ifEmpty { "Azamat" } }
    }

    override suspend fun updateThemeMode(themeMode: String) {
        context.talkMeDataStore.edit { it[Keys.THEME_MODE] = themeMode }
    }

    override suspend fun updateSoundEnabled(enabled: Boolean) {
        context.talkMeDataStore.edit { it[Keys.SOUND_ENABLED] = enabled }
    }

    override suspend fun updateSpeechSpeed(speed: Float) {
        context.talkMeDataStore.edit { it[Keys.SPEECH_SPEED] = speed.coerceIn(0.5f, 1.75f) }
    }

    override suspend fun updatePremiumSubscribed(subscribed: Boolean) {
        context.talkMeDataStore.edit { it[Keys.PREMIUM_SUBSCRIBED] = subscribed }
    }

    override suspend fun updatePreferredTtsProvider(provider: String) {
        context.talkMeDataStore.edit { it[Keys.PREFERRED_TTS] = provider }
    }

    override suspend fun updateVoiceGender(voiceGender: String) {
        context.talkMeDataStore.edit {
            it[Keys.VOICE_GENDER] = if (voiceGender.equals("FEMALE", ignoreCase = true)) "FEMALE" else "MALE"
        }
    }

    override suspend fun updateOfflineAudioEnabled(enabled: Boolean) {
        context.talkMeDataStore.edit { it[Keys.OFFLINE_AUDIO] = enabled }
    }

    override suspend fun updateLessonRemindersEnabled(enabled: Boolean) {
        context.talkMeDataStore.edit { it[Keys.LESSON_REMINDERS] = enabled }
    }

    override suspend fun updateLastLearningBookmark(
        topicId: String,
        sectionId: String,
        scrollOffset: Int
    ) {
        context.talkMeDataStore.edit { prefs ->
            prefs[Keys.LAST_TOPIC_ID] = topicId
            prefs[Keys.LAST_SECTION_ID] = sectionId
            prefs[Keys.LAST_SCROLL_OFFSET] = scrollOffset
            prefs[Keys.HAS_STARTED_LEARNING] = true
        }
    }
}
