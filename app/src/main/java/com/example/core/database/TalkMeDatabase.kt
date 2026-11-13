package com.example.core.database

import androidx.room.Transaction
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for all offline-first TalkMe learning tables.
 */
@Dao
interface TalkMeDao {
    @Query("DELETE FROM exercises")
    suspend fun clearCurriculumExercises()
    @Query("DELETE FROM grammar_rules")
    suspend fun clearCurriculumGrammar()
    @Query("DELETE FROM vocabulary")
    suspend fun clearCurriculumVocabulary()
    @Query("DELETE FROM lesson_sections")
    suspend fun clearCurriculumSections()
    @Query("DELETE FROM topics")
    suspend fun clearCurriculumTopics()
    @Query("DELETE FROM user_mistakes")
    suspend fun clearRetiredLessonMistakes()

    /** User-requested one-time reset; retains profile, streak and activity history. */
    @Transaction
    suspend fun initializeEmptyCurriculum(topics: List<TopicEntity>): Boolean {
        if (getTopicById(topics.first().id) != null) return false
        clearRetiredLessonMistakes()
        clearCurriculumExercises()
        clearCurriculumGrammar()
        clearCurriculumVocabulary()
        clearCurriculumSections()
        clearCurriculumTopics()
        insertTopics(topics)
        getUserProfileOnce()?.let { profile ->
            insertUserProfile(profile.copy(currentTopicId = topics.first().id,
                currentSectionId = "", lastScrollPosition = 0, hasStartedLearning = false))
        }
        return true
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSectionsIgnore(sections: List<LessonSectionEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertVocabularyIgnore(items: List<VocabularyEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrammarRulesIgnore(rules: List<GrammarRuleEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercisesIgnore(exercises: List<ExerciseEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAchievementsIgnore(achievements: List<AchievementEntity>)

    /** Non-destructive seed sync: adds new textbook lessons without overwriting saved progress. */
    @Transaction
    suspend fun syncTextbookCurriculum(
        sections: List<LessonSectionEntity>,
        vocabulary: List<VocabularyEntity>,
        grammarRules: List<GrammarRuleEntity>,
        exercises: List<ExerciseEntity>,
        achievements: List<AchievementEntity>
    ) {
        if (sections.isNotEmpty()) insertSectionsIgnore(sections)
        if (vocabulary.isNotEmpty()) insertVocabularyIgnore(vocabulary)
        if (grammarRules.isNotEmpty()) insertGrammarRulesIgnore(grammarRules)
        if (exercises.isNotEmpty()) insertExercisesIgnore(exercises)
        if (achievements.isNotEmpty()) insertAchievementsIgnore(achievements)

        // Refresh edited beginner content on existing installations while retaining learner state.
        val beginnerTopics = setOf("level_a0", "level_a1")
        val savedSections = getAllSectionsOnce().associateBy { it.id }
        insertSections(sections.filter { it.topicId in beginnerTopics }.map { seed ->
            val saved = savedSections.getValue(seed.id)
            seed.copy(isUnlocked = saved.isUnlocked, isCompleted = saved.isCompleted)
        })
        val savedWords = getAllVocabularyOnce().associateBy { it.id }
        updateVocabularyContent(vocabulary.filter { it.topicId in beginnerTopics }.mapNotNull { seed ->
            savedWords[seed.id]?.let { saved ->
                saved.copy(englishWord = seed.englishWord, uzbekTranslation = seed.uzbekTranslation, exampleEnglish = seed.exampleEnglish,
                    exampleUzbek = seed.exampleUzbek)
            }
        })
        val savedRules = beginnerTopics.flatMap { getGrammarRulesByTopicOnce(it) }.associateBy { it.id }
        insertGrammarRules(grammarRules.filter { it.topicId in beginnerTopics }.map { seed ->
            val saved = savedRules.getValue(seed.id)
            seed.copy(isStudied = saved.isStudied, isMastered = saved.isMastered)
        })
        insertExercises(exercises.filter { it.topicId in beginnerTopics })

    }


    @Query("SELECT COUNT(*) FROM topics")
    suspend fun getTopicCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun observeUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    suspend fun getUserProfileOnce(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(topics: List<TopicEntity>)

    @Query("SELECT * FROM topics ORDER BY orderIndex ASC")
    fun observeAllTopics(): Flow<List<TopicEntity>>

    @Query("SELECT * FROM topics ORDER BY orderIndex ASC")
    suspend fun getAllTopicsOnce(): List<TopicEntity>

    @Query("SELECT * FROM topics WHERE id = :topicId LIMIT 1")
    suspend fun getTopicById(topicId: String): TopicEntity?

    @Query("UPDATE topics SET isUnlocked = :unlocked, isCompleted = :completed WHERE id = :topicId")
    suspend fun updateTopicStatus(topicId: String, unlocked: Boolean, completed: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSections(sections: List<LessonSectionEntity>)

    @Query("SELECT * FROM lesson_sections WHERE topicId = :topicId ORDER BY orderIndex ASC")
    fun observeSectionsByTopic(topicId: String): Flow<List<LessonSectionEntity>>

    @Query("SELECT * FROM lesson_sections WHERE topicId = :topicId ORDER BY orderIndex ASC")
    suspend fun getSectionsByTopicOnce(topicId: String): List<LessonSectionEntity>

    @Query("SELECT * FROM lesson_sections ORDER BY topicId ASC, orderIndex ASC")
    fun observeAllSections(): Flow<List<LessonSectionEntity>>

    @Query("SELECT * FROM lesson_sections ORDER BY topicId ASC, orderIndex ASC")
    suspend fun getAllSectionsOnce(): List<LessonSectionEntity>

    @Query("SELECT * FROM lesson_sections WHERE id = :sectionId LIMIT 1")
    suspend fun getSectionById(sectionId: String): LessonSectionEntity?

    @Query("UPDATE lesson_sections SET isUnlocked = :unlocked, isCompleted = :completed WHERE id = :sectionId")
    suspend fun updateSectionStatus(sectionId: String, unlocked: Boolean, completed: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabulary(items: List<VocabularyEntity>)

    @androidx.room.Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateVocabularyContent(items: List<VocabularyEntity>)

    @Query("SELECT * FROM vocabulary WHERE sectionId = :sectionId")
    fun observeVocabularyBySection(sectionId: String): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE sectionId = :sectionId")
    suspend fun getVocabularyBySectionOnce(sectionId: String): List<VocabularyEntity>

    @Query("SELECT * FROM vocabulary WHERE topicId = :topicId")
    suspend fun getVocabularyByTopicOnce(topicId: String): List<VocabularyEntity>

    @Query("SELECT * FROM vocabulary WHERE id = :vocabularyId LIMIT 1")
    suspend fun getVocabularyById(vocabularyId: String): VocabularyEntity?

    @Query("SELECT * FROM vocabulary WHERE isStudied = 1 ORDER BY isMemorized ASC, nextReviewTimestamp ASC")
    suspend fun getStudiedVocabularyForReview(): List<VocabularyEntity>

    @Query("SELECT * FROM vocabulary ORDER BY topicId ASC")
    suspend fun getAllVocabularyOnce(): List<VocabularyEntity>

    @Query("SELECT * FROM vocabulary ORDER BY topicId ASC, id ASC")
    fun observeAllVocabulary(): Flow<List<VocabularyEntity>>

    @Query("UPDATE vocabulary SET isStudied = 1 WHERE sectionId = :sectionId")
    suspend fun markSectionVocabularyStudied(sectionId: String)

    @Query("UPDATE vocabulary SET isStudied = 1, isMemorized = :memorized, reviewCount = :reviewCount, nextReviewTimestamp = :nextReview WHERE id = :vocabularyId")
    suspend fun updateVocabularyReview(
        vocabularyId: String,
        memorized: Boolean,
        reviewCount: Int,
        nextReview: Long
    )

    @Query("SELECT COUNT(DISTINCT englishWord) FROM vocabulary WHERE isMemorized = 1")
    fun observeMemorizedUniqueWordsCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrammarRules(rules: List<GrammarRuleEntity>)

    @Query("SELECT * FROM grammar_rules WHERE sectionId = :sectionId")
    fun observeGrammarRulesBySection(sectionId: String): Flow<List<GrammarRuleEntity>>

    @Query("SELECT * FROM grammar_rules WHERE topicId = :topicId")
    suspend fun getGrammarRulesByTopicOnce(topicId: String): List<GrammarRuleEntity>

    @Query("SELECT * FROM grammar_rules ORDER BY topicId ASC, id ASC")
    fun observeAllGrammarRules(): Flow<List<GrammarRuleEntity>>

    @Query("UPDATE grammar_rules SET isStudied = 1, isMastered = :mastered WHERE sectionId = :sectionId")
    suspend fun updateSectionGrammarMastery(sectionId: String, mastered: Boolean)

    @Query("UPDATE grammar_rules SET isStudied = 1, isMastered = 1 WHERE id IN (:ruleIds)")
    suspend fun markGrammarRulesMastered(ruleIds: List<String>)

    @Query("SELECT COUNT(DISTINCT id) FROM grammar_rules WHERE isMastered = 1")
    fun observeMasteredGrammarRulesCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM exercises WHERE sectionId = :sectionId")
    suspend fun getExercisesBySection(sectionId: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE practiceCategory = :category AND topicId IN (:unlockedTopicIds)")
    suspend fun getEligibleExercisesByCategory(
        category: String,
        unlockedTopicIds: List<String>
    ): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE topicId IN (:unlockedTopicIds)")
    suspend fun getAllEligibleExercises(unlockedTopicIds: List<String>): List<ExerciseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPracticeSession(session: PracticeSessionEntity)

    @Query("SELECT * FROM practice_sessions ORDER BY timestamp DESC")
    fun observeAllPracticeSessions(): Flow<List<PracticeSessionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLearningActivity(activity: LearningActivityEntity)

    @Query("SELECT * FROM learning_activities ORDER BY timestamp DESC LIMIT :limit")
    fun observeRecentActivities(limit: Int): Flow<List<LearningActivityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)

    @Query("UPDATE achievements SET isUnlocked = 1, unlockedTimestamp = :timestamp WHERE id = :id")
    suspend fun unlockAchievement(id: String, timestamp: Long)

    @Query("SELECT * FROM achievements ORDER BY isUnlocked DESC, id ASC")
    fun observeAllAchievements(): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyTaskLogs(logs: List<DailyTaskLogEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDailyTaskLog(log: DailyTaskLogEntity)

    @Query("SELECT * FROM daily_task_logs WHERE dateKey = :dateKey LIMIT 1")
    suspend fun getDailyTaskLogByDate(dateKey: String): DailyTaskLogEntity?

    @Query("SELECT * FROM daily_task_logs ORDER BY dateKey DESC")
    fun observeAllDailyTaskLogs(): Flow<List<DailyTaskLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserMistakes(mistakes: List<UserMistakeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserMistake(mistake: UserMistakeEntity)

    @Query("SELECT * FROM user_mistakes WHERE id = :id LIMIT 1")
    suspend fun getUserMistakeById(id: String): UserMistakeEntity?

    @Query("SELECT * FROM user_mistakes WHERE isResolved = 0 ORDER BY timestamp DESC")
    fun observeUnresolvedMistakes(): Flow<List<UserMistakeEntity>>

    @Query("UPDATE user_mistakes SET isResolved = 1 WHERE id = :id")
    suspend fun markMistakeResolved(id: String)

    @Query("UPDATE user_mistakes SET isResolved = 1 WHERE topicId = :topicId AND correctAnswer = :correctAnswer")
    suspend fun resolveMistakesByCorrectAnswer(topicId: String, correctAnswer: String)

    @Query("DELETE FROM topics WHERE id != :preserveTopicId")
    suspend fun deleteObsoleteTopics(preserveTopicId: String)

    @Query("DELETE FROM lesson_sections WHERE topicId != :preserveTopicId")
    suspend fun deleteObsoleteSections(preserveTopicId: String)

    @Query("DELETE FROM vocabulary WHERE topicId != :preserveTopicId")
    suspend fun deleteObsoleteVocabulary(preserveTopicId: String)

    @Query("DELETE FROM grammar_rules WHERE topicId != :preserveTopicId")
    suspend fun deleteObsoleteGrammarRules(preserveTopicId: String)

    @Query("DELETE FROM exercises WHERE topicId != :preserveTopicId")
    suspend fun deleteObsoleteExercises(preserveTopicId: String)

    @Query("DELETE FROM user_mistakes WHERE topicId != :preserveTopicId")
    suspend fun deleteObsoleteUserMistakes(preserveTopicId: String)
}

/**
 * Primary Room Database for TalkMe.
 */
@Database(
    entities = [
        UserProfileEntity::class,
        TopicEntity::class,
        LessonSectionEntity::class,
        VocabularyEntity::class,
        GrammarRuleEntity::class,
        ExerciseEntity::class,
        PracticeSessionEntity::class,
        LearningActivityEntity::class,
        AchievementEntity::class,
        DailyTaskLogEntity::class,
        UserMistakeEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class TalkMeDatabase : RoomDatabase() {
    abstract fun talkMeDao(): TalkMeDao
}
