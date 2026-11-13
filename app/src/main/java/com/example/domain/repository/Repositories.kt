package com.example.domain.repository

import com.example.domain.model.ExerciseModel
import com.example.domain.model.GrammarRuleModel
import com.example.domain.model.LearningActivityModel
import com.example.domain.model.LearningDashboardStats
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.PracticeCategory
import com.example.domain.model.SkillPerformanceMetric
import com.example.domain.model.TopicModel
import com.example.domain.model.UserPreferencesModel
import com.example.domain.model.VocabularyModel
import kotlinx.coroutines.flow.Flow

/**
 * Primary Domain Repository contract for TalkMe learning curriculum, progress,
 * vocabulary mastery, grammar mastery, spaced repetition, and analytics.
 */
interface LearningRepository {
    suspend fun ensureSeeded()

    fun observeDashboardStats(): Flow<LearningDashboardStats>
    fun observeTopics(): Flow<List<TopicModel>>
    fun observeTopicSections(topicId: String): Flow<List<LessonSectionModel>>
    suspend fun getSectionById(sectionId: String): LessonSectionModel?
    suspend fun getTopicById(topicId: String): TopicModel?

    fun observeSectionVocabulary(sectionId: String): Flow<List<VocabularyModel>>
    fun observeSectionGrammarRules(sectionId: String): Flow<List<GrammarRuleModel>>

    /**
     * Persists the exact learning position (topic, section, scroll offset) for Continue Learning.
     */
    suspend fun saveLastLearningPosition(topicId: String, sectionId: String, scrollOffset: Int)

    /**
     * Completes a lesson section, marks associated grammar rules/vocabulary as studied,
     * and sequentially unlocks the next section or next topic in the curriculum.
     */
    suspend fun completeLessonSection(topicId: String, sectionId: String)

    /**
     * Returns vocabulary eligible for Flip Card review (only from unlocked/studied topics,
     * or strictly from topicIdFilter / sectionIdFilter when specified), ordered by spaced repetition schedule.
     */
    suspend fun getFlipCardReviewQueue(
        includeAllStudied: Boolean = false,
        topicIdFilter: String? = null,
        sectionIdFilter: String? = null
    ): List<VocabularyModel>

    /**
     * Observes Major Topics with vocabulary counts, subtopics count, and new-word badges.
     */
    fun observeVocabularyTopicCards(): Flow<List<com.example.domain.model.VocabularyTopicCardModel>>

    /**
     * Observes Sub-topics (sections) inside a Major Topic with word previews, counts, and badges.
     */
    fun observeVocabularySubtopics(topicId: String): Flow<List<com.example.domain.model.VocabularySubtopicCardModel>>

    /**
     * Records a Flip Card review decision ("I Know This Word" vs "Need More Practice")
     * using spaced repetition intervals and updates unique memorized words count.
     */
    suspend fun recordVocabularyReview(vocabularyId: String, knewWord: Boolean)

    /**
     * Records a 4-button Flip Card difficulty rating (AGAIN, HARD, GOOD, EASY)
     * with granular spaced repetition intervals and mastery updates.
     */
    suspend fun recordVocabularyReviewByRating(
        vocabularyId: String,
        rating: com.example.domain.model.FlipCardRating
    )

    /**
     * Returns exercises strictly filtered to previously studied/unlocked topics,
     * or strictly scoped to a single studied topic (topicIdFilter) or section (sectionIdFilter).
     */
    suspend fun getEligibleExercises(
        category: PracticeCategory,
        sectionIdFilter: String? = null,
        topicIdFilter: String? = null
    ): List<ExerciseModel>

    /**
     * Records an exercise attempt and practice session result, updating grammar and vocabulary mastery.
     */
    suspend fun recordPracticeSessionResult(
        category: PracticeCategory,
        topicId: String,
        sectionId: String,
        correctCount: Int,
        totalCount: Int,
        masteredGrammarRuleIds: List<String>,
        memorizedVocabularyIds: List<String>
    )

    /**
     * Returns all studied vocabulary words and grammar rules for the given topic
     * so Speaking AI and Reading AI stay strictly within the student's learned boundaries.
     */
    suspend fun getStudiedVocabularyForTopic(topicId: String): List<VocabularyModel>
    suspend fun getStudiedGrammarForTopic(topicId: String): List<GrammarRuleModel>

    /**
     * Observes per-topic practice summaries and new-word notification badges for unlocked/studied topics.
     */
    fun observeTopicPracticeSummaries(): Flow<List<com.example.domain.model.TopicPracticeSummary>>

    /**
     * Observes daily task logs across today and previous days (completed vs incomplete/missed days).
     */
    fun observeDailyPlanHistory(): Flow<List<com.example.domain.model.DailyPlanDayRecord>>

    /**
     * Observes today's 6 daily task steps for the given topicId.
     */
    fun observeTodayTaskSteps(topicId: String): Flow<List<com.example.domain.model.DailyTaskStepModel>>

    /**
     * Observes all unresolved user mistakes across Reading, Writing, Vocabulary, Grammar, and Listening.
     */
    fun observeUnresolvedMistakes(): Flow<List<com.example.domain.model.UserMistakeModel>>

    /**
     * Records a user mistake in Reading, Writing, Vocabulary, Grammar, or Listening for targeted remediation.
     */
    suspend fun recordUserMistake(
        topicId: String,
        category: PracticeCategory,
        itemPrompt: String,
        userWrongAnswer: String,
        correctAnswer: String,
        explanationUzbek: String
    )

    /**
     * Marks a user mistake as resolved after the user reviews or answers it correctly.
     */
    suspend fun resolveUserMistake(mistakeId: String)

    /**
     * Marks any mistake matching the topic and correct answer as resolved when answered right in practice.
     */
    suspend fun resolveMistakeByAnswer(topicId: String, correctAnswer: String)

    fun observeSkillPerformanceMetrics(): Flow<List<SkillPerformanceMetric>>
    fun observePracticeSessionRecords(): Flow<List<com.example.domain.model.PracticeSessionRecord>>
    fun observeRecentActivities(limit: Int = 15): Flow<List<LearningActivityModel>>
    fun observeUnlockedAchievements(): Flow<List<String>>
}

/**
 * Contract for DataStore-backed user preferences.
 */
interface UserPreferencesRepository {
    val userPreferencesFlow: Flow<UserPreferencesModel>
    suspend fun updateUserName(name: String)
    suspend fun updateThemeMode(themeMode: String)
    suspend fun updateSoundEnabled(enabled: Boolean)
    suspend fun updateSpeechSpeed(speed: Float)
    suspend fun updatePremiumSubscribed(subscribed: Boolean)
    suspend fun updatePreferredTtsProvider(provider: String)
    suspend fun updateVoiceGender(voiceGender: String)
    suspend fun updateOfflineAudioEnabled(enabled: Boolean)
    suspend fun updateLessonRemindersEnabled(enabled: Boolean)
    suspend fun updateLastLearningBookmark(topicId: String, sectionId: String, scrollOffset: Int)
}

/**
 * Abstraction for Text-To-Speech providers:
 * - NativeTtsProvider (Android free offline-capable TextToSpeech)
 * - GeminiTtsProvider (Premium cloud natural pronunciation with local caching)
 */
interface TtsProvider {
    val providerName: String
    suspend fun speak(text: String, speechRate: Float = 1.0f): Result<Unit>
    suspend fun speakWithLanguage(
        text: String,
        speechRate: Float = 1.0f,
        languageCode: String = "en"
    ): Result<Unit> = speak(text, speechRate)
    fun stop()
    fun clearCache(): Long
    fun getCacheSizeBytes(): Long
}

/**
 * Separate AI Tutor session contract for Speaking AI and Reading AI.
 * Speaking and Reading use independent system instructions, conversation histories, and session records.
 */
interface AiLearningService {
    /**
     * Sends a student spoken/transcribed turn in the dedicated Speaking AI session.
     * Enforces topic and vocabulary boundaries and returns spoken response + grammar/vocabulary feedback.
     */
    suspend fun sendSpeakingTurn(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>,
        conversationHistory: List<Pair<String, String>>,
        studentUtterance: String,
        audioWavBytes: ByteArray? = null,
        learnerLevel: String = "A0_A1"
    ): Result<SpeakingAiResponse>

    /**
     * Generates a topic-aligned reading passage using only studied vocabulary and grammar.
     */
    suspend fun generateReadingPassage(
        topicTitle: String,
        allowedVocabulary: List<String>,
        allowedGrammarSummaries: List<String>
    ): Result<ReadingPassageResult>

    /**
     * Evaluates a student's comprehension answer or explains a difficult word in Uzbek within the Reading AI session.
     */
    suspend fun evaluateReadingQuery(
        topicTitle: String,
        passageText: String,
        comprehensionQuestion: String,
        studentQueryOrAnswer: String
    ): Result<String>
}

data class SpeakingAiResponse(
    val spokenEnglishReply: String,
    val grammarAndVocabFeedbackUzbek: String,
    val accuracyScore: Int,
    val studentTranscript: String = ""
)

data class ReadingPassageResult(
    val titleEnglish: String,
    val passageEnglish: String,
    val vocabularyNotesUzbek: String,
    val comprehensionQuestionEnglish: String
)
