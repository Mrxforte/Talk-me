package com.example.data.repository

import com.example.core.common.AppConstants
import com.example.core.database.DailyTaskLogEntity
import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LearningActivityEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.PracticeSessionEntity
import com.example.core.database.TalkMeDao
import com.example.core.database.UserMistakeEntity
import com.example.core.database.UserProfileEntity
import com.example.core.database.VocabularyEntity
import com.example.data.local.CurriculumSeedData
import com.example.data.local.TextbookReference
import com.example.domain.model.ContinueLearningState
import com.example.domain.model.DailyPlanDayRecord
import com.example.domain.model.DailyTaskStepModel
import com.example.domain.model.ExerciseModel
import com.example.domain.model.ExerciseType
import com.example.domain.model.GrammarRuleModel
import com.example.domain.model.LearningActivityModel
import com.example.domain.model.LearningDashboardStats
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.MasteryStatus
import com.example.domain.model.PracticeCategory
import com.example.domain.model.SkillPerformanceMetric
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.model.UserMistakeModel
import com.example.domain.model.VocabularyModel
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.UserPreferencesRepository
import com.example.domain.usecase.CurriculumDomainLogic
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

/**
 * Offline-first Room implementation of LearningRepository.
 * Serves as the single source of truth for curriculum topics, sequential unlocking,
 * dynamic statistics, spaced repetition flip cards, daily tasks, and mistake tracking.
 */
@Singleton
class LearningRepositoryImpl @Inject constructor(
    private val dao: TalkMeDao,
    private val preferencesRepository: UserPreferencesRepository
) : LearningRepository {

    private val isSeedingChecked = AtomicBoolean(false)
    private val seedMutex = Mutex()

    private fun todayDateKey(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    override suspend fun ensureSeeded() = seedMutex.withLock {
        if (isSeedingChecked.get()) return@withLock
        val now = System.currentTimeMillis()

        if (dao.getUserProfileOnce() == null) {
            dao.insertUserProfile(
                UserProfileEntity(
                    id = 1,
                    userName = "Azamat",
                    currentTopicId = AppConstants.INITIAL_TOPIC_ID,
                    currentSectionId = AppConstants.INITIAL_SECTION_ID,
                    lastScrollPosition = 0,
                    hasStartedLearning = false,
                    streakDays = 1,
                    lastStudyTimestamp = now
                )
            )
        }

        if (dao.initializeEmptyCurriculum(CurriculumSeedData.buildTopics())) {
            preferencesRepository.updateLastLearningBookmark(AppConstants.INITIAL_TOPIC_ID, "", 0)
        }
        dao.syncTextbookCurriculum(
            sections = CurriculumSeedData.buildSections(),
            vocabulary = CurriculumSeedData.buildVocabulary(),
            grammarRules = CurriculumSeedData.buildGrammarRules(),
            exercises = CurriculumSeedData.buildExercises(),
            achievements = CurriculumSeedData.buildAchievements()
        )
        isSeedingChecked.set(true)
    }

    override fun observeDashboardStats(): Flow<LearningDashboardStats> {
        return combine(
            dao.observeAllTopics(),
            dao.observeAllSections(),
            dao.observeMemorizedUniqueWordsCount(),
            dao.observeMasteredGrammarRulesCount(),
            combine(preferencesRepository.userPreferencesFlow, dao.observeAllDailyTaskLogs()) { prefs, logs ->
                prefs to logs
            }
        ) { topics, sections, memorizedWords, masteredGrammar, (prefs, logs) ->
            val sectionsByTopic = sections.groupBy { it.topicId }
            val domainTopics = CurriculumDomainLogic.computeTopicUnlockStatuses(
                topics.map { topic ->
                    val topicSections = sectionsByTopic[topic.id].orEmpty()
                    TopicModel(
                        id = topic.id,
                        orderIndex = topic.orderIndex,
                        titleEnglish = topic.titleEnglish,
                        titleUzbek = topic.titleUzbek,
                        descriptionUzbek = topic.descriptionUzbek,
                        totalSections = topicSections.size,
                        completedSections = topicSections.count { it.isCompleted },
                        status = if (topic.isCompleted) UnlockStatus.COMPLETED
                        else if (topic.isUnlocked) UnlockStatus.AVAILABLE
                        else UnlockStatus.LOCKED
                    )
                }
            )

            val totalSections = sections.size.coerceAtLeast(1)
            val completedSections = sections.count { it.isCompleted }
            val overallPercent = ((completedSections * 100) / totalSections).coerceIn(0, 100)

            val currentTopic = domainTopics.find { it.id == prefs.lastActiveTopicId }
                ?: domainTopics.firstOrNull { it.status == UnlockStatus.AVAILABLE }
                ?: domainTopics.firstOrNull()

            val currentSection = sections.find { it.id == prefs.lastActiveSectionId }
                ?: sections.firstOrNull { it.topicId == currentTopic?.id && !it.isCompleted }
                ?: sections.firstOrNull()

            val continueState = ContinueLearningState(
                hasStarted = prefs.hasStartedLearning || completedSections > 0,
                courseName = "TalkMe English Course",
                currentTopicId = currentTopic?.id ?: AppConstants.INITIAL_TOPIC_ID,
                currentTopicTitle = if (currentTopic != null) {
                    if (currentTopic.titleEnglish.equals(currentTopic.titleUzbek, ignoreCase = true)) {
                        "${currentTopic.orderIndex}. ${currentTopic.titleEnglish}"
                    } else {
                        "${currentTopic.orderIndex}. ${currentTopic.titleEnglish} — ${currentTopic.titleUzbek}"
                    }
                } else {
                    "1. A0"
                },
                currentSectionId = currentSection?.id ?: AppConstants.INITIAL_SECTION_ID,
                currentSectionTitle = currentSection?.let {
                    if (it.titleUzbek.contains(it.titleEnglish, ignoreCase = true)) {
                        it.titleUzbek
                    } else {
                        "${it.titleEnglish} (${it.titleUzbek})"
                    }
                } ?: "",
                lastScrollPosition = prefs.lastScrollOffset,
                progressPercent = currentTopic?.progressPercent ?: 0
            )

            // Compute consecutive completed days from daily task logs
            val sortedLogs = logs.sortedByDescending { it.dateKey }
            var consecutiveStreak = 0
            for (log in sortedLogs) {
                if (log.isAllCompleted || log.completedStepsCount > 0) {
                    consecutiveStreak++
                } else {
                    break
                }
            }
            val finalStreak = consecutiveStreak.coerceAtLeast(if (completedSections > 0) 2 else 1)

            LearningDashboardStats(
                userName = prefs.userName,
                memorizedUniqueWordsCount = memorizedWords,
                masteredGrammarRulesCount = masteredGrammar,
                completedTopicsCount = domainTopics.count { it.status == UnlockStatus.COMPLETED },
                totalTopicsCount = domainTopics.size,
                completedSectionsCount = completedSections,
                totalSectionsCount = sections.size,
                overallCourseProgressPercent = overallPercent,
                currentStreakDays = finalStreak,
                continueLearning = continueState
            )
        }
    }

    override fun observeTopics(): Flow<List<TopicModel>> {
        return combine(
            dao.observeAllTopics(),
            dao.observeAllSections()
        ) { topics, sections ->
            val sectionsByTopic = sections.groupBy { it.topicId }
            val rawModels = topics.map { topic ->
                val topicSecs = sectionsByTopic[topic.id].orEmpty()
                TopicModel(
                    id = topic.id,
                    orderIndex = topic.orderIndex,
                    titleEnglish = topic.titleEnglish,
                    titleUzbek = topic.titleUzbek,
                    descriptionUzbek = topic.descriptionUzbek,
                    totalSections = topicSecs.size,
                    completedSections = topicSecs.count { it.isCompleted },
                    status = when {
                        topic.isCompleted -> UnlockStatus.COMPLETED
                        topic.isUnlocked -> UnlockStatus.AVAILABLE
                        else -> UnlockStatus.LOCKED
                    }
                )
            }
            CurriculumDomainLogic.computeTopicUnlockStatuses(rawModels)
        }
    }

    override fun observeTopicSections(topicId: String): Flow<List<LessonSectionModel>> {
        return combine(dao.observeSectionsByTopic(topicId), observeTopics()) { entities, topics ->
            val topicLocked = topics.firstOrNull { it.id == topicId }?.status == UnlockStatus.LOCKED
            val sorted = entities.sortedBy { it.orderIndex }
            val result = mutableListOf<LessonSectionModel>()
            for (i in sorted.indices) {
                val sec = sorted[i]
                val prevCompleted = if (i == 0) true else (result[i - 1].status == UnlockStatus.COMPLETED)
                val status = when {
                    topicLocked || !prevCompleted -> UnlockStatus.LOCKED
                    sec.isCompleted -> UnlockStatus.COMPLETED
                    prevCompleted -> UnlockStatus.AVAILABLE
                    else -> UnlockStatus.LOCKED
                }
                result.add(sec.toDomain(status))
            }
            result
        }
    }

    override suspend fun getSectionById(sectionId: String): LessonSectionModel? {
        ensureSeeded()
        val entity = dao.getSectionById(sectionId) ?: return null
        val topicSecs = dao.getSectionsByTopicOnce(entity.topicId).sortedBy { it.orderIndex }
        val idx = topicSecs.indexOfFirst { it.id == sectionId }
        val prevCompleted = idx >= 0 && topicSecs.take(idx).all { it.isCompleted }
        val status = when {
            getTopicById(entity.topicId)?.status == UnlockStatus.LOCKED || !prevCompleted -> UnlockStatus.LOCKED
            entity.isCompleted -> UnlockStatus.COMPLETED
            prevCompleted -> UnlockStatus.AVAILABLE
            else -> UnlockStatus.LOCKED
        }
        return entity.toDomain(status)
    }

    override suspend fun getTopicById(topicId: String): TopicModel? {
        ensureSeeded()
        val sections = dao.getAllSectionsOnce().groupBy { it.topicId }
        val topics = dao.getAllTopicsOnce().map { topic ->
            val rows = sections[topic.id].orEmpty()
            TopicModel(topic.id, topic.orderIndex, topic.titleEnglish, topic.titleUzbek,
                topic.descriptionUzbek, rows.size, rows.count { it.isCompleted }, UnlockStatus.LOCKED)
        }
        return CurriculumDomainLogic.computeTopicUnlockStatuses(topics).firstOrNull { it.id == topicId }
    }

    override fun observeSectionVocabulary(sectionId: String): Flow<List<VocabularyModel>> {
        return dao.observeVocabularyBySection(sectionId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun observeSectionGrammarRules(sectionId: String): Flow<List<GrammarRuleModel>> {
        return dao.observeGrammarRulesBySection(sectionId).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun saveLastLearningPosition(
        topicId: String,
        sectionId: String,
        scrollOffset: Int
    ) {
        ensureSeeded()
        preferencesRepository.updateLastLearningBookmark(topicId, sectionId, scrollOffset)
        val existingProfile = dao.getUserProfileOnce()
        if (existingProfile != null) {
            dao.insertUserProfile(
                existingProfile.copy(
                    currentTopicId = topicId,
                    currentSectionId = sectionId,
                    lastScrollPosition = scrollOffset,
                    hasStartedLearning = true,
                    lastStudyTimestamp = System.currentTimeMillis()
                )
            )
        }
    }

    override suspend fun completeLessonSection(topicId: String, sectionId: String) {
        ensureSeeded()
        // Stale navigation and locked lessons must not advance the curriculum.
        val topic = getTopicById(topicId) ?: return
        val section = getSectionById(sectionId) ?: return
        if (topic.status == UnlockStatus.LOCKED || section.topicId != topicId ||
            section.status != UnlockStatus.AVAILABLE) return
        val now = System.currentTimeMillis()
        dao.updateSectionStatus(sectionId = sectionId, unlocked = true, completed = true)
        dao.markSectionVocabularyStudied(sectionId)
        dao.updateSectionGrammarMastery(sectionId = sectionId, mastered = true)

        val topicSections = dao.getSectionsByTopicOnce(topicId).sortedBy { it.orderIndex }
        val currentIdx = topicSections.indexOfFirst { it.id == sectionId }
        val nextSection = if (currentIdx >= 0 && currentIdx + 1 < topicSections.size) {
            topicSections[currentIdx + 1]
        } else {
            null
        }

        if (nextSection != null) {
            dao.updateSectionStatus(nextSection.id, unlocked = true, completed = nextSection.isCompleted)
            saveLastLearningPosition(topicId, nextSection.id, 0)
        } else if (topicSections.isNotEmpty() && topicSections.all { it.isCompleted }) {
            // All sections in this topic are completed -> Mark topic completed and unlock next topic!
            dao.updateTopicStatus(topicId = topicId, unlocked = true, completed = true)
            dao.unlockAchievement("ach_first_topic", now)

            val allTopics = dao.getAllTopicsOnce().sortedBy { it.orderIndex }
            val topicIdx = allTopics.indexOfFirst { it.id == topicId }
            val nextTopic = if (topicIdx >= 0 && topicIdx + 1 < allTopics.size) {
                allTopics[topicIdx + 1]
            } else {
                null
            }

            if (nextTopic != null) {
                dao.updateTopicStatus(nextTopic.id, unlocked = true, completed = nextTopic.isCompleted)
                val nextTopicSections = dao.getSectionsByTopicOnce(nextTopic.id).sortedBy { it.orderIndex }
                val firstNextSec = nextTopicSections.firstOrNull()
                if (firstNextSec != null) {
                    dao.updateSectionStatus(firstNextSec.id, unlocked = true, completed = firstNextSec.isCompleted)
                    saveLastLearningPosition(nextTopic.id, firstNextSec.id, 0)
                }
            }
        }

        val completedSecEntity = dao.getSectionById(sectionId)
        dao.insertLearningActivity(
            LearningActivityEntity(
                title = "Completed Lesson Section: ${completedSecEntity?.titleEnglish ?: sectionId}",
                subtitle = "Mastered grammar rule & unlocked next learning step",
                timestamp = now
            )
        )
        dao.unlockAchievement("ach_first_section", now)
    }

    override suspend fun getFlipCardReviewQueue(
        includeAllStudied: Boolean,
        topicIdFilter: String?,
        sectionIdFilter: String?
    ): List<VocabularyModel> {
        ensureSeeded()
        val source = when {
            !sectionIdFilter.isNullOrBlank() -> dao.getVocabularyBySectionOnce(sectionIdFilter)
            !topicIdFilter.isNullOrBlank() -> dao.getVocabularyByTopicOnce(topicIdFilter)
            else -> {
                val studied = dao.getStudiedVocabularyForReview()
                if (studied.isEmpty()) {
                    dao.getVocabularyByTopicOnce(AppConstants.INITIAL_TOPIC_ID)
                } else {
                    studied
                }
            }
        }
        val now = System.currentTimeMillis()
        val filtered = if (includeAllStudied || !sectionIdFilter.isNullOrBlank()) {
            source
        } else {
            val due = source.filter { !it.isMemorized || it.nextReviewTimestamp <= now }
            due.ifEmpty { source }
        }
        return filtered.distinctBy { it.englishWord.lowercase() }.map { it.toDomain() }
    }

    override fun observeVocabularyTopicCards(): Flow<List<com.example.domain.model.VocabularyTopicCardModel>> {
        return combine(
            dao.observeAllTopics(),
            dao.observeAllSections(),
            dao.observeAllVocabulary()
        ) { topics, sections, allVocab ->
            val sectionsByTopic = sections.groupBy { it.topicId }
            val vocabByTopic = allVocab.groupBy { it.topicId }
            val domainTopics = CurriculumDomainLogic.computeTopicUnlockStatuses(
                topics.map { topic ->
                    val topicSecs = sectionsByTopic[topic.id].orEmpty()
                    TopicModel(
                        id = topic.id,
                        orderIndex = topic.orderIndex,
                        titleEnglish = topic.titleEnglish,
                        titleUzbek = topic.titleUzbek,
                        descriptionUzbek = topic.descriptionUzbek,
                        totalSections = topicSecs.size,
                        completedSections = topicSecs.count { it.isCompleted },
                        status = when {
                            topic.isCompleted -> UnlockStatus.COMPLETED
                            topic.isUnlocked -> UnlockStatus.AVAILABLE
                            else -> UnlockStatus.LOCKED
                        }
                    )
                }
            )
            domainTopics.map { topic ->
                val topicWords = vocabByTopic[topic.id].orEmpty().distinctBy { it.englishWord.lowercase() }
                val memorized = topicWords.count { it.isMemorized }
                val total = topicWords.size
                val newBadge = (total - memorized).coerceAtLeast(0)
                com.example.domain.model.VocabularyTopicCardModel(
                    topicId = topic.id,
                    orderIndex = topic.orderIndex,
                    titleEnglish = topic.titleEnglish,
                    titleUzbek = topic.titleUzbek,
                    descriptionUzbek = topic.descriptionUzbek,
                    status = topic.status,
                    subtopicsCount = topic.totalSections,
                    memorizedWordsCount = memorized,
                    totalWordsCount = total,
                    newWordsBadgeCount = newBadge
                )
            }
        }
    }

    override fun observeVocabularySubtopics(
        topicId: String
    ): Flow<List<com.example.domain.model.VocabularySubtopicCardModel>> {
        return combine(
            dao.observeAllTopics(),
            dao.observeSectionsByTopic(topicId),
            dao.observeAllVocabulary()
        ) { allTopics, sections, allVocab ->
            val topicEntity = allTopics.find { it.id == topicId }
            val topicOrder = topicEntity?.orderIndex ?: 1
            val sortedSecs = sections.sortedBy { it.orderIndex }
            val vocabBySection = allVocab.filter { it.topicId == topicId }.groupBy { it.sectionId }

            sortedSecs.mapIndexed { idx, sec ->
                val secWords = vocabBySection[sec.id].orEmpty().distinctBy { it.englishWord.lowercase() }
                val memorized = secWords.count { it.isMemorized }
                val total = secWords.size
                val newBadge = (total - memorized).coerceAtLeast(0)
                val preview = secWords.joinToString(" • ") { "${it.englishWord} (${it.uzbekTranslation})" }
                    .ifBlank { sec.objectiveUzbek }
                val status = when {
                    sec.isCompleted -> UnlockStatus.COMPLETED
                    sec.isUnlocked || idx == 0 || (idx > 0 && sortedSecs[idx - 1].isCompleted) -> UnlockStatus.AVAILABLE
                    else -> UnlockStatus.AVAILABLE // Allow practicing subtopics of an unlocked major topic
                }
                com.example.domain.model.VocabularySubtopicCardModel(
                    sectionId = sec.id,
                    topicId = sec.topicId,
                    topicOrderIndex = topicOrder,
                    sectionOrderIndex = sec.orderIndex,
                    titleEnglish = sec.titleEnglish,
                    titleUzbek = sec.titleUzbek,
                    objectiveUzbek = sec.objectiveUzbek,
                    status = status,
                    wordsPreview = preview,
                    memorizedWordsCount = memorized,
                    totalWordsCount = total,
                    newWordsBadgeCount = newBadge
                )
            }
        }
    }

    override suspend fun recordVocabularyReview(vocabularyId: String, knewWord: Boolean) {
        val rating = if (knewWord) {
            com.example.domain.model.FlipCardRating.GOOD
        } else {
            com.example.domain.model.FlipCardRating.AGAIN
        }
        recordVocabularyReviewByRating(vocabularyId, rating)
    }

    override suspend fun recordVocabularyReviewByRating(
        vocabularyId: String,
        rating: com.example.domain.model.FlipCardRating
    ) {
        ensureSeeded()
        val entity = dao.getVocabularyById(vocabularyId) ?: return
        val now = System.currentTimeMillis()
        val (newStatus, nextTimestamp) = CurriculumDomainLogic.calculateNextReviewScheduleByRating(
            currentReviewCount = entity.reviewCount,
            rating = rating,
            nowMillis = now
        )
        val isMemorized = newStatus == MasteryStatus.MEMORIZED_OR_MASTERED
        val newReviewCount = when (rating) {
            com.example.domain.model.FlipCardRating.AGAIN -> 0
            com.example.domain.model.FlipCardRating.HARD -> entity.reviewCount
            com.example.domain.model.FlipCardRating.GOOD -> entity.reviewCount + 1
            com.example.domain.model.FlipCardRating.EASY -> entity.reviewCount + 2
        }

        dao.updateVocabularyReview(
            vocabularyId = vocabularyId,
            memorized = isMemorized,
            reviewCount = newReviewCount,
            nextReview = nextTimestamp
        )

        val ratingLabel = when (rating) {
            com.example.domain.model.FlipCardRating.AGAIN -> "Again (Qayta • 10m)"
            com.example.domain.model.FlipCardRating.HARD -> "Hard (Qiyin • 12h)"
            com.example.domain.model.FlipCardRating.GOOD -> "Good (Yaxshi • 1d)"
            com.example.domain.model.FlipCardRating.EASY -> "Easy (Oson • 4d)"
        }

        // Update today's Daily Task Log (Vocabulary step completed)
        markDailyStepCompleted(entity.topicId, PracticeCategory.VOCABULARY)

        if (isMemorized) {
            dao.resolveMistakesByCorrectAnswer(entity.topicId, entity.uzbekTranslation)
            dao.resolveMistakesByCorrectAnswer(entity.topicId, entity.englishWord)
            dao.insertLearningActivity(
                LearningActivityEntity(
                    title = "Memorized Word: ${entity.englishWord} [$ratingLabel]",
                    subtitle = "Ma'nosi: ${entity.uzbekTranslation} (3D Flip Cards)",
                    timestamp = now
                )
            )
            val allMemorized = dao.getAllVocabularyOnce().count { it.isMemorized }
            if (allMemorized >= 5) {
                dao.unlockAchievement("ach_vocab_5", now)
            }
        } else {
            // Record as a weak-word / mistake alert in the Daily Tasks Mistake Tracker
            recordUserMistake(
                topicId = entity.topicId,
                category = PracticeCategory.VOCABULARY,
                itemPrompt = "'${entity.englishWord}' so'zining ma'nosi va misoli",
                userWrongAnswer = ratingLabel,
                correctAnswer = entity.uzbekTranslation,
                explanationUzbek = "${entity.englishWord} — ${entity.uzbekTranslation}. Misol: ${entity.exampleEnglish} (${entity.exampleUzbek})"
            )
            dao.insertLearningActivity(
                LearningActivityEntity(
                    title = "Reviewed Word: ${entity.englishWord} [$ratingLabel]",
                    subtitle = "Returned to review queue (${entity.uzbekTranslation})",
                    timestamp = now
                )
            )
        }
    }

    override suspend fun getEligibleExercises(
        category: PracticeCategory,
        sectionIdFilter: String?,
        topicIdFilter: String?
    ): List<ExerciseModel> {
        ensureSeeded()
        if (!sectionIdFilter.isNullOrBlank()) {
            val sectionExercises = dao.getExercisesBySection(sectionIdFilter)
            if (sectionExercises.isNotEmpty()) {
                return sectionExercises.map { it.toDomain() }
            }
        }

        val targetTopicIds = if (!topicIdFilter.isNullOrBlank()) {
            listOf(topicIdFilter)
        } else {
            dao.getAllTopicsOnce()
                .filter { it.isUnlocked || it.isCompleted || it.orderIndex == 1 }
                .map { it.id }
                .ifEmpty { listOf(AppConstants.INITIAL_TOPIC_ID) }
        }

        val entities = if (category == PracticeCategory.SECTION_QUIZ) {
            dao.getAllEligibleExercises(targetTopicIds)
        } else {
            dao.getEligibleExercisesByCategory(category.id, targetTopicIds)
        }

        val baseDomainExercises = entities.map { it.toDomain() }.toMutableList()

        val enrichmentTopics = if (!topicIdFilter.isNullOrBlank()) {
            listOf(topicIdFilter)
        } else {
            targetTopicIds
        }

        enrichmentTopics.forEach { effectiveTopicId ->
            val topicWords = dao.getVocabularyByTopicOnce(effectiveTopicId)
            val topicGrammar = dao.getGrammarRulesByTopicOnce(effectiveTopicId)
            val distractorPool = topicWords.map { it.uzbekTranslation }.distinct()

            when (category) {
                PracticeCategory.VOCABULARY -> {
                    topicWords.forEachIndexed { idx, word ->
                        val options = (listOf(word.uzbekTranslation) + distractorPool.filter { it != word.uzbekTranslation })
                            .distinct()
                            .take(4)
                        val exId = "dyn_vocab_${effectiveTopicId}_$idx"
                        if (baseDomainExercises.none { it.correctAnswer == word.uzbekTranslation }) {
                            baseDomainExercises.add(
                                ExerciseModel(
                                    id = exId,
                                    topicId = effectiveTopicId,
                                    sectionId = word.sectionId,
                                    practiceCategory = PracticeCategory.VOCABULARY,
                                    exerciseType = ExerciseType.MULTIPLE_CHOICE,
                                    difficulty = 1,
                                    promptText = "'${word.englishWord}' so'zining o'zbekcha ma'nosini tanlang:",
                                    subPromptText = "Misol: ${word.exampleEnglish} (${word.exampleUzbek})",
                                    audioTextToSpeak = word.englishWord,
                                    options = CurriculumDomainLogic.randomizeExerciseOptions(options),
                                    correctAnswer = word.uzbekTranslation,
                                    explanationUzbek = "'${word.englishWord}' — '${word.uzbekTranslation}' degan ma'noni bildiradi.",
                                    vocabularyIds = listOf(word.id),
                                    grammarRuleIds = emptyList()
                                )
                            )
                        }
                    }
                }
                PracticeCategory.WRITING -> {
                    topicWords.forEachIndexed { idx, word ->
                        val exId = "dyn_writing_${effectiveTopicId}_$idx"
                        if (baseDomainExercises.none { it.correctAnswer.equals(word.englishWord, ignoreCase = true) }) {
                            baseDomainExercises.add(
                                ExerciseModel(
                                    id = exId,
                                    topicId = effectiveTopicId,
                                    sectionId = word.sectionId,
                                    practiceCategory = PracticeCategory.WRITING,
                                    exerciseType = ExerciseType.TRANSLATION,
                                    difficulty = 2,
                                    promptText = "'${word.uzbekTranslation}' so'zini ingliz tilida yozing:",
                                    subPromptText = "Misol: ${word.exampleUzbek}",
                                    audioTextToSpeak = word.englishWord,
                                    options = emptyList(),
                                    correctAnswer = word.englishWord,
                                    explanationUzbek = "To'g'ri javob: ${word.englishWord} — ${word.uzbekTranslation}.",
                                    vocabularyIds = listOf(word.id),
                                    grammarRuleIds = emptyList()
                                )
                            )
                        }
                    }
                }
                PracticeCategory.LISTENING -> {
                    topicWords.forEachIndexed { idx, word ->
                        val sentencePool = topicWords.map { it.exampleEnglish }.distinct()
                        val options = (listOf(word.exampleEnglish) + sentencePool.filter { it != word.exampleEnglish })
                            .distinct()
                            .take(4)
                        val exId = "dyn_listening_${effectiveTopicId}_$idx"
                        if (baseDomainExercises.none { it.correctAnswer == word.exampleEnglish }) {
                            baseDomainExercises.add(
                                ExerciseModel(
                                    id = exId,
                                    topicId = effectiveTopicId,
                                    sectionId = word.sectionId,
                                    practiceCategory = PracticeCategory.LISTENING,
                                    exerciseType = ExerciseType.LISTENING_COMPREHENSION,
                                    difficulty = 1,
                                    promptText = "Audioni eshiting va aytilgan inglizcha gapni belgilang:",
                                    subPromptText = "O'zbekcha ma'nosi: ${word.exampleUzbek}",
                                    audioTextToSpeak = word.exampleEnglish,
                                    options = CurriculumDomainLogic.randomizeExerciseOptions(options),
                                    correctAnswer = word.exampleEnglish,
                                    explanationUzbek = "Eshitilgan gap: '${word.exampleEnglish}' (${word.exampleUzbek}).",
                                    vocabularyIds = listOf(word.id),
                                    grammarRuleIds = emptyList()
                                )
                            )
                        }
                    }
                }
                PracticeCategory.READING -> {
                    topicWords.forEachIndexed { idx, word ->
                        val options = (listOf(word.uzbekTranslation) + distractorPool.filter { it != word.uzbekTranslation })
                            .distinct()
                            .take(4)
                        val exId = "dyn_reading_${effectiveTopicId}_$idx"
                        if (baseDomainExercises.none { it.correctAnswer == word.uzbekTranslation }) {
                            baseDomainExercises.add(
                                ExerciseModel(
                                    id = exId,
                                    topicId = effectiveTopicId,
                                    sectionId = word.sectionId,
                                    practiceCategory = PracticeCategory.READING,
                                    exerciseType = ExerciseType.READING_COMPREHENSION,
                                    difficulty = 1,
                                    promptText = "Jumla: \"${word.exampleEnglish}\"",
                                    subPromptText = "Ushbu jumladagi '${word.englishWord}' so'zi qanday tarjima qilinadi?",
                                    audioTextToSpeak = word.exampleEnglish,
                                    options = CurriculumDomainLogic.randomizeExerciseOptions(options),
                                    correctAnswer = word.uzbekTranslation,
                                    explanationUzbek = "'${word.englishWord}' so'zining ma'nosi — '${word.uzbekTranslation}'.",
                                    vocabularyIds = listOf(word.id),
                                    grammarRuleIds = emptyList()
                                )
                            )
                        }
                    }
                }
                PracticeCategory.GRAMMAR -> {
                    topicGrammar.forEachIndexed { idx, rule ->
                        val otherExamples = topicGrammar.map { it.exampleEnglish }.filter { it != rule.exampleEnglish }
                        val options = (listOf(rule.exampleEnglish) + otherExamples + topicWords.map { it.exampleEnglish })
                            .distinct()
                            .take(3)
                        val exId = "dyn_grammar_${effectiveTopicId}_$idx"
                        if (baseDomainExercises.none { it.correctAnswer == rule.exampleEnglish }) {
                            baseDomainExercises.add(
                                ExerciseModel(
                                    id = exId,
                                    topicId = effectiveTopicId,
                                    sectionId = rule.sectionId,
                                    practiceCategory = PracticeCategory.GRAMMAR,
                                    exerciseType = ExerciseType.GRAMMAR_CORRECTION,
                                    difficulty = 1,
                                    promptText = "${rule.titleUzbek}: Quyidagi ma'noga mos inglizcha gapni toping:",
                                    subPromptText = rule.exampleUzbek,
                                    audioTextToSpeak = rule.exampleEnglish,
                                    options = CurriculumDomainLogic.randomizeExerciseOptions(options),
                                    correctAnswer = rule.exampleEnglish,
                                    explanationUzbek = rule.explanationUzbek,
                                    vocabularyIds = rule.associatedVocabularyIdsCsv.split(",").filter { it.isNotBlank() },
                                    grammarRuleIds = listOf(rule.id)
                                )
                            )
                        }
                    }
                }
                else -> Unit
            }
        }

        return baseDomainExercises
    }

    override suspend fun recordPracticeSessionResult(
        category: PracticeCategory,
        topicId: String,
        sectionId: String,
        correctCount: Int,
        totalCount: Int,
        masteredGrammarRuleIds: List<String>,
        memorizedVocabularyIds: List<String>
    ) {
        ensureSeeded()
        val now = System.currentTimeMillis()
        val safeTotal = totalCount.coerceAtLeast(1)
        val accuracy = ((correctCount * 100) / safeTotal).coerceIn(0, 100)

        dao.insertPracticeSession(
            PracticeSessionEntity(
                skillCategory = category.id,
                topicId = topicId,
                sectionId = sectionId,
                correctCount = correctCount,
                totalCount = totalCount,
                accuracyPercent = accuracy,
                timestamp = now
            )
        )

        if (accuracy >= AppConstants.GRAMMAR_MASTERY_MIN_ACCURACY && masteredGrammarRuleIds.isNotEmpty()) {
            dao.markGrammarRulesMastered(masteredGrammarRuleIds.distinct())
            dao.unlockAchievement("ach_grammar_3", now)
        }

        memorizedVocabularyIds.distinct().forEach { vocabId ->
            recordVocabularyReview(vocabId, knewWord = true)
        }

        // Update today's Daily Task Log for this skill category (even when practiced individually from Home/Practice)
        markDailyStepCompleted(topicId, category)

        dao.insertLearningActivity(
            LearningActivityEntity(
                title = "Completed ${category.displayName}",
                subtitle = "Score: $accuracy% ($correctCount / $totalCount correct)",
                timestamp = now
            )
        )
    }

    private suspend fun markDailyStepCompleted(topicId: String, category: PracticeCategory) {
        val dateKey = todayDateKey()
        val now = System.currentTimeMillis()
        val existing = dao.getDailyTaskLogByDate(dateKey) ?: DailyTaskLogEntity(
            dateKey = dateKey,
            displayDateLabel = "Bugun ($dateKey)",
            topicId = topicId,
            grammarDone = false,
            vocabularyDone = false,
            readingDone = false,
            writingDone = false,
            listeningDone = false,
            speakingDone = false,
            updatedTimestamp = now
        )
        val updated = when (category) {
            PracticeCategory.GRAMMAR -> existing.copy(topicId = topicId, grammarDone = true, updatedTimestamp = now)
            PracticeCategory.VOCABULARY -> existing.copy(topicId = topicId, vocabularyDone = true, updatedTimestamp = now)
            PracticeCategory.READING -> existing.copy(topicId = topicId, readingDone = true, updatedTimestamp = now)
            PracticeCategory.WRITING -> existing.copy(topicId = topicId, writingDone = true, updatedTimestamp = now)
            PracticeCategory.LISTENING -> existing.copy(topicId = topicId, listeningDone = true, updatedTimestamp = now)
            PracticeCategory.SPEAKING -> existing.copy(topicId = topicId, speakingDone = true, updatedTimestamp = now)
            PracticeCategory.SECTION_QUIZ -> existing.copy(
                topicId = topicId,
                grammarDone = true,
                vocabularyDone = true,
                updatedTimestamp = now
            )
        }
        dao.upsertDailyTaskLog(updated)
    }

    override suspend fun getStudiedVocabularyForTopic(topicId: String): List<VocabularyModel> {
        ensureSeeded()
        return dao.getVocabularyByTopicOnce(topicId).map { it.toDomain() }
    }

    override suspend fun getStudiedGrammarForTopic(topicId: String): List<GrammarRuleModel> {
        ensureSeeded()
        return dao.getGrammarRulesByTopicOnce(topicId).map { it.toDomain() }
    }

    override fun observeTopicPracticeSummaries(): Flow<List<com.example.domain.model.TopicPracticeSummary>> {
        return combine(
            dao.observeAllTopics(),
            dao.observeAllSections(),
            dao.observeAllVocabulary(),
            dao.observeAllGrammarRules(),
            dao.observeAllPracticeSessions()
        ) { topics, sections, allVocab, allGrammar, allSessions ->
            val sectionsByTopic = sections.groupBy { it.topicId }
            val domainTopics = CurriculumDomainLogic.computeTopicUnlockStatuses(
                topics.map { topic ->
                    val topicSecs = sectionsByTopic[topic.id].orEmpty()
                    TopicModel(
                        id = topic.id,
                        orderIndex = topic.orderIndex,
                        titleEnglish = topic.titleEnglish,
                        titleUzbek = topic.titleUzbek,
                        descriptionUzbek = topic.descriptionUzbek,
                        totalSections = topicSecs.size,
                        completedSections = topicSecs.count { it.isCompleted },
                        status = when {
                            topic.isCompleted -> UnlockStatus.COMPLETED
                            topic.isUnlocked -> UnlockStatus.AVAILABLE
                            else -> UnlockStatus.LOCKED
                        }
                    )
                }
            )

            val vocabByTopic = allVocab.groupBy { it.topicId }
            val grammarByTopic = allGrammar.groupBy { it.topicId }
            val sessionsByTopic = allSessions.groupBy { it.topicId }

            domainTopics
                .filter { it.status != UnlockStatus.LOCKED }
                .map { topic ->
                    val tVocab = vocabByTopic[topic.id].orEmpty()
                    val tGrammar = grammarByTopic[topic.id].orEmpty()
                    val tSessions = sessionsByTopic[topic.id].orEmpty()

                    val studiedWords = tVocab.count { it.isStudied }
                    val memorizedWords = tVocab.count { it.isMemorized }
                    // New words added by this studied topic that are not yet memorized
                    val newWordsBadge = tVocab.count { !it.isMemorized }

                    val masteredGrammar = tGrammar.count { it.isMastered }
                    val newGrammarBadge = tGrammar.count { !it.isMastered }

                    val readingSessions = tSessions.count { it.skillCategory == PracticeCategory.READING.id }
                    val writingSessions = tSessions.count { it.skillCategory == PracticeCategory.WRITING.id }
                    val listeningSessions = tSessions.count { it.skillCategory == PracticeCategory.LISTENING.id }
                    val speakingSessions = tSessions.count { it.skillCategory == PracticeCategory.SPEAKING.id }

                    com.example.domain.model.TopicPracticeSummary(
                        topicId = topic.id,
                        orderIndex = topic.orderIndex,
                        titleEnglish = topic.titleEnglish,
                        titleUzbek = topic.titleUzbek,
                        status = topic.status,
                        studiedWordsCount = studiedWords,
                        memorizedWordsCount = memorizedWords,
                        totalWordsCount = tVocab.size,
                        newWordsBadgeCount = newWordsBadge,
                        masteredGrammarCount = masteredGrammar,
                        totalGrammarCount = tGrammar.size,
                        newGrammarBadgeCount = newGrammarBadge,
                        readingSessionsCount = readingSessions,
                        writingSessionsCount = writingSessions,
                        listeningSessionsCount = listeningSessions,
                        speakingSessionsCount = speakingSessions,
                        readingNewBadgeCount = newWordsBadge,
                        writingNewBadgeCount = newWordsBadge,
                        listeningNewBadgeCount = newWordsBadge,
                        speakingNewBadgeCount = newWordsBadge
                    )
                }
        }
    }

    override fun observeDailyPlanHistory(): Flow<List<DailyPlanDayRecord>> {
        return combine(
            dao.observeAllDailyTaskLogs(),
            dao.observeAllTopics()
        ) { logs, topics ->
            val topicMap = topics.associateBy { it.id }
            logs.map { log ->
                val topic = topicMap[log.topicId]
                val topicTitle = topic?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" }
                    ?: "1. Introduction (Kirish)"
                val missing = buildList {
                    if (!log.grammarDone) add("Grammar")
                    if (!log.vocabularyDone) add("Vocabulary")
                    if (!log.readingDone) add("Reading")
                    if (!log.writingDone) add("Writing")
                    if (!log.listeningDone) add("Listening")
                    if (!log.speakingDone) add("Speaking")
                }
                DailyPlanDayRecord(
                    dateKey = log.dateKey,
                    displayDateLabel = log.displayDateLabel,
                    topicTitle = topicTitle,
                    completedCount = log.completedStepsCount,
                    totalCount = 6,
                    isFullyCompleted = log.isAllCompleted,
                    missingTasksSummary = if (missing.isEmpty()) {
                        "Barcha 6 ta kunlik vazifa to'liq bajarilgan!"
                    } else {
                        "Bajarilmagan: ${missing.joinToString(", ")}"
                    }
                )
            }
        }
    }

    override fun observeTodayTaskSteps(topicId: String): Flow<List<DailyTaskStepModel>> {
        return combine(
            dao.observeAllDailyTaskLogs(),
            dao.observeUnresolvedMistakes(),
            dao.observeAllPracticeSessions()
        ) { logs, mistakes, sessions ->
            val todayLog = logs.find { it.dateKey == todayDateKey() }
            val topicMistakes = mistakes.filter { it.topicId == topicId }
            val topicSessions = sessions.filter { it.topicId == topicId }

            fun isStepDone(cat: PracticeCategory, logFlag: Boolean): Boolean {
                return logFlag || topicSessions.any { it.skillCategory == cat.id }
            }

            listOf(
                DailyTaskStepModel(
                    stepOrder = 1,
                    category = PracticeCategory.GRAMMAR,
                    title = "1. Grammar Rules (Qoidalar)",
                    subtitleUzbek = "Mavzu grammatika qoidalari ustida mashq",
                    isCompletedToday = isStepDone(PracticeCategory.GRAMMAR, todayLog?.grammarDone == true),
                    unresolvedMistakeCount = topicMistakes.count { it.skillCategory == PracticeCategory.GRAMMAR.id }
                ),
                DailyTaskStepModel(
                    stepOrder = 2,
                    category = PracticeCategory.VOCABULARY,
                    title = "2. New Vocabulary (Yangi so'zlar)",
                    subtitleUzbek = "Yangi so'zlar va 3D Flip Cards takrorlash",
                    isCompletedToday = isStepDone(PracticeCategory.VOCABULARY, todayLog?.vocabularyDone == true),
                    unresolvedMistakeCount = topicMistakes.count { it.skillCategory == PracticeCategory.VOCABULARY.id }
                ),
                DailyTaskStepModel(
                    stepOrder = 3,
                    category = PracticeCategory.READING,
                    title = "3. Reading Practice (O'qish)",
                    subtitleUzbek = "Mavzu so'zlari ishtirokida matn tushunish",
                    isCompletedToday = isStepDone(PracticeCategory.READING, todayLog?.readingDone == true),
                    unresolvedMistakeCount = topicMistakes.count { it.skillCategory == PracticeCategory.READING.id }
                ),
                DailyTaskStepModel(
                    stepOrder = 4,
                    category = PracticeCategory.WRITING,
                    title = "4. Writing Practice (Yozish)",
                    subtitleUzbek = "So'z va jumlalarni ingliz tilida yozish",
                    isCompletedToday = isStepDone(PracticeCategory.WRITING, todayLog?.writingDone == true),
                    unresolvedMistakeCount = topicMistakes.count { it.skillCategory == PracticeCategory.WRITING.id }
                ),
                DailyTaskStepModel(
                    stepOrder = 5,
                    category = PracticeCategory.LISTENING,
                    title = "5. Listening Practice (Eshitish)",
                    subtitleUzbek = "Audio talaffuzni tinglab jumlani topish",
                    isCompletedToday = isStepDone(PracticeCategory.LISTENING, todayLog?.listeningDone == true),
                    unresolvedMistakeCount = topicMistakes.count { it.skillCategory == PracticeCategory.LISTENING.id }
                ),
                DailyTaskStepModel(
                    stepOrder = 6,
                    category = PracticeCategory.SPEAKING,
                    title = "6. Speaking AI (Gapirish)",
                    subtitleUzbek = "Mavzu doirasida ovozli nutq mashqi",
                    isCompletedToday = isStepDone(PracticeCategory.SPEAKING, todayLog?.speakingDone == true),
                    unresolvedMistakeCount = topicMistakes.count { it.skillCategory == PracticeCategory.SPEAKING.id }
                )
            )
        }
    }

    override fun observeUnresolvedMistakes(): Flow<List<UserMistakeModel>> {
        return combine(
            dao.observeUnresolvedMistakes(),
            dao.observeAllTopics()
        ) { mistakes, topics ->
            val topicMap = topics.associateBy { it.id }
            mistakes.map { m ->
                val topic = topicMap[m.topicId]
                val topicTitle = topic?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" }
                    ?: "1. Introduction (Kirish)"
                val cat = runCatching { PracticeCategory.valueOf(m.skillCategory) }
                    .getOrDefault(PracticeCategory.VOCABULARY)
                UserMistakeModel(
                    id = m.id,
                    topicId = m.topicId,
                    topicTitle = topicTitle,
                    skillCategory = cat,
                    itemPrompt = m.itemPrompt,
                    userWrongAnswer = m.userWrongAnswer,
                    correctAnswer = m.correctAnswer,
                    explanationUzbek = m.explanationUzbek,
                    mistakeCount = m.mistakeCount
                )
            }
        }
    }

    override suspend fun recordUserMistake(
        topicId: String,
        category: PracticeCategory,
        itemPrompt: String,
        userWrongAnswer: String,
        correctAnswer: String,
        explanationUzbek: String
    ) {
        ensureSeeded()
        val safeKey = correctAnswer.lowercase().replace(Regex("[^a-z0-9]"), "_").take(24)
        val mistakeId = "mistake_${topicId}_${category.id}_$safeKey"
        val existing = dao.getUserMistakeById(mistakeId)
        val newCount = (existing?.mistakeCount ?: 0) + 1
        dao.upsertUserMistake(
            UserMistakeEntity(
                id = mistakeId,
                topicId = topicId,
                skillCategory = category.id,
                itemPrompt = itemPrompt,
                userWrongAnswer = userWrongAnswer.ifBlank { "(Bo'sh javob)" },
                correctAnswer = correctAnswer,
                explanationUzbek = explanationUzbek,
                mistakeCount = newCount,
                isResolved = false,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun resolveUserMistake(mistakeId: String) {
        ensureSeeded()
        dao.markMistakeResolved(mistakeId)
    }

    override suspend fun resolveMistakeByAnswer(topicId: String, correctAnswer: String) {
        ensureSeeded()
        dao.resolveMistakesByCorrectAnswer(topicId, correctAnswer)
    }

    override fun observeSkillPerformanceMetrics(): Flow<List<SkillPerformanceMetric>> {
        return dao.observeAllPracticeSessions().map { sessions ->
            val categories = listOf("READING", "WRITING", "SPEAKING", "LISTENING")
            categories.map { cat ->
                val matching = sessions.filter { it.skillCategory == cat }
                val avgAccuracy = if (matching.isNotEmpty()) {
                    matching.sumOf { it.accuracyPercent } / matching.size
                } else {
                    0
                }
                SkillPerformanceMetric(
                    skillName = cat.lowercase().replaceFirstChar { it.uppercase() },
                    averageAccuracyPercent = avgAccuracy,
                    completedSessionsCount = matching.size
                )
            }
        }
    }

    override fun observePracticeSessionRecords(): Flow<List<com.example.domain.model.PracticeSessionRecord>> {
        return dao.observeAllPracticeSessions().map { sessions ->
            sessions.map {
                com.example.domain.model.PracticeSessionRecord(
                    skillCategory = it.skillCategory,
                    accuracyPercent = it.accuracyPercent,
                    timestamp = it.timestamp
                )
            }
        }
    }

    override fun observeRecentActivities(limit: Int): Flow<List<LearningActivityModel>> {
        return dao.observeRecentActivities(limit).map { list ->
            list.map {
                LearningActivityModel(
                    id = it.id,
                    title = it.title,
                    subtitle = it.subtitle,
                    timestamp = it.timestamp
                )
            }
        }
    }

    override fun observeUnlockedAchievements(): Flow<List<String>> {
        return dao.observeAllAchievements().map { list ->
            list.map { ach ->
                val prefix = if (ach.isUnlocked) "✓ [Unlocked]" else "○ [Locked]"
                "$prefix ${ach.title} — ${ach.description}"
            }
        }
    }

    private fun LessonSectionEntity.toDomain(computedStatus: UnlockStatus): LessonSectionModel {
        return LessonSectionModel(
            id = id,
            topicId = topicId,
            orderIndex = orderIndex,
            titleEnglish = titleEnglish,
            titleUzbek = titleUzbek,
            objectiveUzbek = objectiveUzbek,
            explanationUzbek = TextbookReference.explanation(id, explanationUzbek),
            wrapUpSummaryUzbek = wrapUpSummaryUzbek,
            status = computedStatus
        )
    }

    private fun VocabularyEntity.toDomain(): VocabularyModel {
        val status = when {
            isMemorized -> MasteryStatus.MEMORIZED_OR_MASTERED
            isStudied -> MasteryStatus.LEARNING
            else -> MasteryStatus.NEW
        }
        return VocabularyModel(
            id = id,
            topicId = topicId,
            sectionId = sectionId,
            englishWord = englishWord,
            uzbekTranslation = uzbekTranslation,
            exampleEnglish = exampleEnglish,
            exampleUzbek = exampleUzbek,
            masteryStatus = status,
            reviewCount = reviewCount,
            nextReviewTimestamp = nextReviewTimestamp
        )
    }

    private fun GrammarRuleEntity.toDomain(): GrammarRuleModel {
        return GrammarRuleModel(
            id = id,
            topicId = topicId,
            sectionId = sectionId,
            titleUzbek = titleUzbek,
            explanationUzbek = explanationUzbek,
            exampleEnglish = exampleEnglish,
            exampleUzbek = exampleUzbek,
            associatedVocabularyIds = associatedVocabularyIdsCsv.split(",").filter { it.isNotBlank() },
            isMastered = isMastered
        )
    }

    private fun ExerciseEntity.toDomain(): ExerciseModel {
        val rawOptions = optionsPipeSeparated.split("|").filter { it.isNotBlank() }
        val randomizedOptions = CurriculumDomainLogic.randomizeExerciseOptions(rawOptions)
        val cat = runCatching { PracticeCategory.valueOf(practiceCategory) }
            .getOrDefault(PracticeCategory.VOCABULARY)
        val type = runCatching { ExerciseType.valueOf(exerciseType) }
            .getOrDefault(ExerciseType.MULTIPLE_CHOICE)

        return ExerciseModel(
            id = id,
            topicId = topicId,
            sectionId = sectionId,
            practiceCategory = cat,
            exerciseType = type,
            difficulty = difficulty,
            promptText = promptText,
            subPromptText = subPromptText,
            audioTextToSpeak = audioTextToSpeak,
            options = randomizedOptions,
            correctAnswer = correctAnswer,
            explanationUzbek = explanationUzbek,
            vocabularyIds = vocabularyIdsCsv.split(",").filter { it.isNotBlank() },
            grammarRuleIds = grammarRuleIdsCsv.split(",").filter { it.isNotBlank() }
        )
    }
}
