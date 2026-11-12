package com.example.domain.model

/**
 * Represents the unlock and completion state of a curriculum Topic or Section.
 */
enum class UnlockStatus {
    LOCKED,
    AVAILABLE,
    COMPLETED
}

/**
 * Discrete Voice Activity Detection states representing speech activity:
 * - [SILENCE]: Background silence or non-speech noise.
 * - [SPEECH_BEGIN]: Transition frame where human speech onset is confirmed.
 * - [ACTIVE_SPEECH]: Ongoing voiced/unvoiced human speech.
 * - [PAUSE]: Brief inter-word pause during an utterance before endpoint timeout.
 * - [SPEECH_END]: Confirmed end of speech utterance; triggers final STT decoding & submission.
 */
enum class VadState {
    SILENCE,
    SPEECH_BEGIN,
    ACTIVE_SPEECH,
    PAUSE,
    SPEECH_END
}

/**
 * Represents the learning mastery status of a Vocabulary item or Grammar Rule.
 */
enum class MasteryStatus {
    NEW,
    LEARNING,
    MEMORIZED_OR_MASTERED
}

/**
 * 4-level Spaced Repetition difficulty rating on Flip Cards:
 * AGAIN (Qayta / Bilmadim), HARD (Qiyin), GOOD (Yaxshi), EASY (Oson).
 */
enum class FlipCardRating {
    AGAIN,
    HARD,
    GOOD,
    EASY
}

/**
 * Supported practice categories in the Practice Center.
 */
enum class PracticeCategory(val id: String, val displayName: String) {
    READING("READING", "Reading Practice"),
    WRITING("WRITING", "Writing Practice"),
    SPEAKING("SPEAKING", "Speaking Practice"),
    LISTENING("LISTENING", "Listening Practice"),
    GRAMMAR("GRAMMAR", "Grammar Practice"),
    VOCABULARY("VOCABULARY", "Vocabulary Practice"),
    SECTION_QUIZ("SECTION_QUIZ", "Lesson Section Exercises")
}

/**
 * Supported exercise types in the reusable Exercise Engine.
 */
enum class ExerciseType {
    MULTIPLE_CHOICE,
    WORD_MATCHING,
    TRANSLATION,
    FILL_IN_THE_BLANKS,
    SENTENCE_BUILDING,
    LISTENING_COMPREHENSION,
    READING_COMPREHENSION,
    GRAMMAR_CORRECTION,
    VOCABULARY_RECOGNITION,
    AUDIO_RECOGNITION,
    WRITING_ANSWERS
}

/**
 * Domain representation of one of the 12 TalkMe curriculum topics.
 */
data class TopicModel(
    val id: String,
    val orderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val descriptionUzbek: String,
    val totalSections: Int,
    val completedSections: Int,
    val status: UnlockStatus
) {
    val progressPercent: Int
        get() = if (totalSections > 0) ((completedSections * 100) / totalSections).coerceIn(0, 100) else 0
}

/**
 * Domain representation of a lesson section inside a topic.
 */
data class LessonSectionModel(
    val id: String,
    val topicId: String,
    val orderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val objectiveUzbek: String,
    val explanationUzbek: String,
    val wrapUpSummaryUzbek: String,
    val status: UnlockStatus
)

/**
 * Domain representation of a vocabulary word (no phonetic transcriptions allowed; audio-only pronunciation).
 */
data class VocabularyModel(
    val id: String,
    val topicId: String,
    val sectionId: String,
    val englishWord: String,
    val uzbekTranslation: String,
    val exampleEnglish: String,
    val exampleUzbek: String,
    val masteryStatus: MasteryStatus,
    val reviewCount: Int = 0,
    val nextReviewTimestamp: Long = 0L
)

/**
 * Domain representation of a grammar rule taught in simple Uzbek.
 */
data class GrammarRuleModel(
    val id: String,
    val topicId: String,
    val sectionId: String,
    val titleUzbek: String,
    val explanationUzbek: String,
    val exampleEnglish: String,
    val exampleUzbek: String,
    val associatedVocabularyIds: List<String>,
    val isMastered: Boolean
)

/**
 * Domain representation of an interactive exercise.
 */
data class ExerciseModel(
    val id: String,
    val topicId: String,
    val sectionId: String,
    val practiceCategory: PracticeCategory,
    val exerciseType: ExerciseType,
    val difficulty: Int,
    val promptText: String,
    val subPromptText: String,
    val audioTextToSpeak: String?,
    val options: List<String>,
    val correctAnswer: String,
    val explanationUzbek: String,
    val vocabularyIds: List<String>,
    val grammarRuleIds: List<String>
)

/**
 * Persisted Continue Learning bookmark state on the Home Hero Banner.
 */
data class ContinueLearningState(
    val hasStarted: Boolean,
    val courseName: String,
    val currentTopicId: String,
    val currentTopicTitle: String,
    val currentSectionId: String,
    val currentSectionTitle: String,
    val lastScrollPosition: Int,
    val progressPercent: Int
)

/**
 * Real-time aggregated Home & Profile statistics calculated from Room DB.
 */
data class LearningDashboardStats(
    val userName: String,
    val memorizedUniqueWordsCount: Int,
    val masteredGrammarRulesCount: Int,
    val completedTopicsCount: Int,
    val totalTopicsCount: Int,
    val completedSectionsCount: Int,
    val totalSectionsCount: Int,
    val overallCourseProgressPercent: Int,
    val currentStreakDays: Int,
    val continueLearning: ContinueLearningState
)

/**
 * Skill performance metric for Profile analytics charts.
 */
data class SkillPerformanceMetric(
    val skillName: String,
    val averageAccuracyPercent: Int,
    val completedSessionsCount: Int
)

/**
 * Time period filter for the pixel-perfect multi-line analytics chart:
 * WEEKLY (Hafta), MONTHLY (Oy), YEARLY (Yillik).
 */
enum class ChartPeriodFilter {
    WEEKLY,
    MONTHLY,
    YEARLY
}

/**
 * Represents a single practice session record with timestamp for time-series chart aggregation.
 */
data class PracticeSessionRecord(
    val skillCategory: String,
    val accuracyPercent: Int,
    val timestamp: Long
)

/**
 * Per-topic summary for Home screen cards (Vocabulary, Grammar, Reading, Writing, Listening, Speaking)
 * with new-word and unpracticed skill badges strictly separated by studied topic.
 */
data class TopicPracticeSummary(
    val topicId: String,
    val orderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val status: UnlockStatus,
    val studiedWordsCount: Int,
    val memorizedWordsCount: Int,
    val totalWordsCount: Int,
    val newWordsBadgeCount: Int,
    val masteredGrammarCount: Int,
    val totalGrammarCount: Int,
    val newGrammarBadgeCount: Int,
    val readingSessionsCount: Int,
    val writingSessionsCount: Int,
    val listeningSessionsCount: Int,
    val speakingSessionsCount: Int,
    val readingNewBadgeCount: Int,
    val writingNewBadgeCount: Int,
    val listeningNewBadgeCount: Int,
    val speakingNewBadgeCount: Int
)

/**
 * Recent learning activity log item.
 */
data class LearningActivityModel(
    val id: Long,
    val title: String,
    val subtitle: String,
    val timestamp: Long
)

/**
 * User preferences persisted in DataStore.
 */
data class UserPreferencesModel(
    val userName: String = "Azamat",
    val themeMode: String = "SYSTEM", // SYSTEM, LIGHT, DARK
    val soundEnabled: Boolean = true,
    val speechSpeed: Float = 1.0f,
    val isPremiumSubscribed: Boolean = false,
    val preferredTtsProvider: String = "NATIVE", // NATIVE or GEMINI
    val dailyGoalMinutes: Int = 15,
    val lastActiveTopicId: String = "topic_01_introduction",
    val lastActiveSectionId: String = "sec_01_01",
    val lastScrollOffset: Int = 0,
    val hasStartedLearning: Boolean = false
)

/**
 * Message entry in Speaking AI or Reading AI sessions.
 */
data class AiTutorMessage(
    val id: String,
    val senderRole: String, // "STUDENT" or "AI_TUTOR"
    val englishText: String,
    val correctionOrUzbekFeedback: String = ""
)

/**
 * Single daily task step in the 6-step topic daily plan:
 * 1. GRAMMAR, 2. VOCABULARY, 3. READING, 4. WRITING, 5. LISTENING, 6. SPEAKING.
 */
data class DailyTaskStepModel(
    val stepOrder: Int,
    val category: PracticeCategory,
    val title: String,
    val subtitleUzbek: String,
    val isCompletedToday: Boolean,
    val unresolvedMistakeCount: Int
)

/**
 * Represents a calendar day's daily plan tracking record (either completed 6/6 or incomplete/missed).
 */
data class DailyPlanDayRecord(
    val dateKey: String,
    val displayDateLabel: String,
    val topicTitle: String,
    val completedCount: Int,
    val totalCount: Int = 6,
    val isFullyCompleted: Boolean,
    val missingTasksSummary: String
)

/**
 * Represents an unresolved mistake or unlearned rule/word made by the user in a specific topic & skill.
 */
data class UserMistakeModel(
    val id: String,
    val topicId: String,
    val topicTitle: String,
    val skillCategory: PracticeCategory,
    val itemPrompt: String,
    val userWrongAnswer: String,
    val correctAnswer: String,
    val explanationUzbek: String,
    val mistakeCount: Int
)

/**
 * Topic-grouped warning summary alerting the user about mistakes in a specific topic.
 */
data class TopicMistakeWarning(
    val topicId: String,
    val topicTitle: String,
    val totalMistakesCount: Int,
    val affectedSkillsSummary: String
)

/**
 * Major Topic card model for the Vocabulary Topics hierarchy screen.
 */
data class VocabularyTopicCardModel(
    val topicId: String,
    val orderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val descriptionUzbek: String,
    val status: UnlockStatus,
    val subtopicsCount: Int,
    val memorizedWordsCount: Int,
    val totalWordsCount: Int,
    val newWordsBadgeCount: Int
)

/**
 * Sub-topic (LessonSection) card model inside a selected Major Topic for Vocabulary Flip Cards.
 */
data class VocabularySubtopicCardModel(
    val sectionId: String,
    val topicId: String,
    val topicOrderIndex: Int,
    val sectionOrderIndex: Int,
    val titleEnglish: String,
    val titleUzbek: String,
    val objectiveUzbek: String,
    val status: UnlockStatus,
    val wordsPreview: String,
    val memorizedWordsCount: Int,
    val totalWordsCount: Int,
    val newWordsBadgeCount: Int
)


