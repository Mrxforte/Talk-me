package com.example.core.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Stores learner profile and Continue Learning bookmark in Room Database.
 */
@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val userName: String,
    val currentTopicId: String,
    val currentSectionId: String,
    val lastScrollPosition: Int,
    val hasStartedLearning: Boolean,
    val streakDays: Int,
    val lastStudyTimestamp: Long
)

/**
 * Represents one of the 12 curriculum topics in TalkMe.
 */
@Entity(
    tableName = "topics",
    indices = [Index(value = ["orderIndex"], unique = true)]
)
data class TopicEntity(
    @PrimaryKey val id: String,
    val courseId: String = com.example.core.common.AppConstants.DEFAULT_COURSE_ID,
    val orderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val descriptionUzbek: String,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false
)

/**
 * Represents a subtopic / lesson section inside a Topic.
 */
@Entity(
    tableName = "lesson_sections",
    indices = [Index(value = ["topicId", "orderIndex"])]
)
data class LessonSectionEntity(
    @PrimaryKey val id: String,
    val topicId: String,
    val orderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val objectiveUzbek: String,
    val explanationUzbek: String,
    val wrapUpSummaryUzbek: String,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false
)

/**
 * Represents a unique English vocabulary entry (no IPA/phonetic transcriptions).
 * Tracks spaced repetition review schedule and genuine memorization state.
 */
@Entity(
    tableName = "vocabulary",
    indices = [Index(value = ["topicId"]), Index(value = ["sectionId"]), Index(value = ["englishWord"], unique = true)]
)
data class VocabularyEntity(
    @PrimaryKey val id: String,
    val topicId: String,
    val sectionId: String,
    val englishWord: String,
    val uzbekTranslation: String,
    val exampleEnglish: String,
    val exampleUzbek: String,
    val isStudied: Boolean = false,
    val isMemorized: Boolean = false,
    val reviewCount: Int = 0,
    val nextReviewTimestamp: Long = 0L
)

/**
 * Represents a grammar rule explained in simple Uzbek.
 * Tracks genuine mastery (only marked true when section or grammar exercises are completed).
 */
@Entity(
    tableName = "grammar_rules",
    indices = [Index(value = ["topicId"]), Index(value = ["sectionId"])]
)
data class GrammarRuleEntity(
    @PrimaryKey val id: String,
    val topicId: String,
    val sectionId: String,
    val titleUzbek: String,
    val explanationUzbek: String,
    val exampleEnglish: String,
    val exampleUzbek: String,
    val associatedVocabularyIdsCsv: String,
    val isStudied: Boolean,
    val isMastered: Boolean
)

/**
 * Represents an interactive exercise connected to a topic, section, vocabulary, and grammar rule.
 */
@Entity(
    tableName = "exercises",
    indices = [Index(value = ["topicId"]), Index(value = ["sectionId"]), Index(value = ["practiceCategory"])]
)
data class ExerciseEntity(
    @PrimaryKey val id: String,
    val topicId: String,
    val sectionId: String,
    val practiceCategory: String,
    val exerciseType: String,
    val difficulty: Int,
    val promptText: String,
    val subPromptText: String,
    val audioTextToSpeak: String?,
    val optionsPipeSeparated: String,
    val correctAnswer: String,
    val explanationUzbek: String,
    val vocabularyIdsCsv: String,
    val grammarRuleIdsCsv: String
)

/**
 * Records completed practice, speaking, or reading sessions for real Profile analytics charts.
 */
@Entity(
    tableName = "practice_sessions",
    indices = [Index(value = ["skillCategory"])]
)
data class PracticeSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val skillCategory: String, // READING, WRITING, SPEAKING, LISTENING, GRAMMAR, VOCABULARY
    val topicId: String,
    val sectionId: String,
    val correctCount: Int,
    val totalCount: Int,
    val accuracyPercent: Int,
    val timestamp: Long
)

/**
 * Records chronological learning activities for Home and Profile screens.
 */
@Entity(tableName = "learning_activities")
data class LearningActivityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val subtitle: String,
    val timestamp: Long
)

/**
 * Records unlocked achievements based on real user actions.
 */
@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val isUnlocked: Boolean,
    val unlockedTimestamp: Long
)

/**
 * Tracks daily plan completion across all 6 core activities (Grammar, Vocabulary,
 * Reading, Writing, Listening, Speaking) per calendar day and topic.
 * If all 6 are true, the day is marked completed; otherwise it is recorded as an incomplete/missed day.
 */
@Entity(
    tableName = "daily_task_logs",
    indices = [Index(value = ["dateKey"]), Index(value = ["topicId"])]
)
data class DailyTaskLogEntity(
    @PrimaryKey val dateKey: String, // Format: YYYY-MM-DD
    val displayDateLabel: String,
    val topicId: String,
    val grammarDone: Boolean,
    val vocabularyDone: Boolean,
    val readingDone: Boolean,
    val writingDone: Boolean,
    val listeningDone: Boolean,
    val speakingDone: Boolean,
    val updatedTimestamp: Long
) {
    val completedStepsCount: Int
        get() = listOf(grammarDone, vocabularyDone, readingDone, writingDone, listeningDone, speakingDone).count { it }

    val isAllCompleted: Boolean
        get() = completedStepsCount == 6
}

/**
 * Tracks user mistakes and unmastered rules/words across Reading, Writing, Vocabulary,
 * Grammar, and Listening, scoped by curriculum Topic for targeted remediation and warnings.
 */
@Entity(
    tableName = "user_mistakes",
    indices = [Index(value = ["topicId"]), Index(value = ["skillCategory"]), Index(value = ["isResolved"])]
)
data class UserMistakeEntity(
    @PrimaryKey val id: String,
    val topicId: String,
    val skillCategory: String, // VOCABULARY, GRAMMAR, READING, WRITING, LISTENING
    val itemPrompt: String,
    val userWrongAnswer: String,
    val correctAnswer: String,
    val explanationUzbek: String,
    val mistakeCount: Int,
    val isResolved: Boolean,
    val timestamp: Long
)

