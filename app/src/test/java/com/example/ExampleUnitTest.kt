package com.example

import com.example.core.common.AppConstants
import com.example.data.local.CurriculumSeedData
import com.example.domain.model.ExerciseModel
import com.example.domain.model.ExerciseType
import com.example.domain.model.FlipCardRating
import com.example.domain.model.MasteryStatus
import com.example.domain.model.PracticeCategory
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.usecase.CurriculumDomainLogic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests verifying TalkMe's core educational domain logic:
 * 1. Curriculum topics starting with 1. Introduction and 2. Grammar Fundamentals.
 * 2. Sequential topic unlocking enforcement.
 * 3. 4-button Spaced Repetition review scheduling (Again, Hard, Good, Easy).
 * 4. Exercise evaluation and option randomization.
 */
class ExampleUnitTest {

    @Test
    fun `curriculum seed starts with Introduction as Topic 1 and Grammar Fundamentals as Topic 2`() {
        val topics = CurriculumSeedData.buildTopics()
        assertEquals(13, topics.size)
        assertEquals("Introduction", topics[0].titleEnglish)
        assertEquals("Kirish", topics[0].titleUzbek)
        assertTrue(topics[0].isUnlocked)

        assertEquals("Grammar Fundamentals", topics[1].titleEnglish)
        assertEquals("Grammatika asoslari", topics[1].titleUzbek)
        assertFalse(topics[1].isUnlocked)

        assertEquals("Numbers", topics[2].titleEnglish)
        assertEquals("Sonlar", topics[2].titleUzbek)
        assertEquals("Nouns", topics[3].titleEnglish)
        assertEquals("Demonstratives", topics[4].titleEnglish)
        assertEquals("Adjectives", topics[5].titleEnglish)
        assertEquals("Articles", topics[6].titleEnglish)
        assertEquals("Prepositions", topics[7].titleEnglish)
        assertEquals("Pronouns", topics[8].titleEnglish)
        assertEquals("Adverbs", topics[9].titleEnglish)
        assertEquals("Adverbials", topics[10].titleEnglish)
        assertEquals("Verbs", topics[11].titleEnglish)
        assertEquals("Irregular Verbs", topics[12].titleEnglish)
        assertEquals("Noto'g'ri fe'llar", topics[12].titleUzbek)
    }

    @Test
    fun `sequential topic unlocking unlocks Topic 2 only after Topic 1 is completed`() {
        val initialRaw = listOf(
            TopicModel("t1", 1, "Introduction", "Kirish", "", totalSections = 2, completedSections = 1, status = UnlockStatus.AVAILABLE),
            TopicModel("t2", 2, "Grammar Fundamentals", "Grammatika asoslari", "", totalSections = 2, completedSections = 0, status = UnlockStatus.LOCKED),
            TopicModel("t3", 3, "Numbers", "Sonlar", "", totalSections = 2, completedSections = 0, status = UnlockStatus.LOCKED)
        )

        val beforeCompletion = CurriculumDomainLogic.computeTopicUnlockStatuses(initialRaw)
        assertEquals(UnlockStatus.AVAILABLE, beforeCompletion[0].status)
        assertEquals(UnlockStatus.LOCKED, beforeCompletion[1].status)
        assertEquals(UnlockStatus.LOCKED, beforeCompletion[2].status)

        val afterTopic1Completed = listOf(
            initialRaw[0].copy(completedSections = 2),
            initialRaw[1],
            initialRaw[2]
        )
        val afterCompletion = CurriculumDomainLogic.computeTopicUnlockStatuses(afterTopic1Completed)
        assertEquals(UnlockStatus.COMPLETED, afterCompletion[0].status)
        assertEquals(UnlockStatus.AVAILABLE, afterCompletion[1].status)
        assertEquals(UnlockStatus.LOCKED, afterCompletion[2].status)
    }

    @Test
    fun `4-button spaced repetition schedules Again, Hard, Good, and Easy accurately`() {
        val now = 1_000_000L

        val (statusAgain, nextAgain) = CurriculumDomainLogic.calculateNextReviewScheduleByRating(
            currentReviewCount = 1,
            rating = FlipCardRating.AGAIN,
            nowMillis = now
        )
        assertEquals(MasteryStatus.LEARNING, statusAgain)
        assertEquals(now + AppConstants.TEN_MINUTES_MS, nextAgain)

        val (statusHard, nextHard) = CurriculumDomainLogic.calculateNextReviewScheduleByRating(
            currentReviewCount = 1,
            rating = FlipCardRating.HARD,
            nowMillis = now
        )
        assertEquals(MasteryStatus.LEARNING, statusHard)
        assertEquals(now + AppConstants.TWELVE_HOURS_MS, nextHard)

        val (statusGood, nextGood) = CurriculumDomainLogic.calculateNextReviewScheduleByRating(
            currentReviewCount = 0,
            rating = FlipCardRating.GOOD,
            nowMillis = now
        )
        assertEquals(MasteryStatus.MEMORIZED_OR_MASTERED, statusGood)
        assertEquals(now + AppConstants.ONE_DAY_MS, nextGood)

        val (statusEasy, nextEasy) = CurriculumDomainLogic.calculateNextReviewScheduleByRating(
            currentReviewCount = 0,
            rating = FlipCardRating.EASY,
            nowMillis = now
        )
        assertEquals(MasteryStatus.MEMORIZED_OR_MASTERED, statusEasy)
        assertEquals(now + AppConstants.FOUR_DAYS_MS, nextEasy)
    }

    @Test
    fun `exercise answer evaluation normalizes punctuation and case`() {
        val exercise = ExerciseModel(
            id = "ex_test",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_01",
            practiceCategory = PracticeCategory.WRITING,
            exerciseType = ExerciseType.TRANSLATION,
            difficulty = 1,
            promptText = "Translate:",
            subPromptText = "",
            audioTextToSpeak = "This is an apple.",
            options = emptyList(),
            correctAnswer = "This is an apple.",
            explanationUzbek = "",
            vocabularyIds = emptyList(),
            grammarRuleIds = emptyList()
        )

        assertTrue(CurriculumDomainLogic.evaluateExerciseAnswer(exercise, "  this is an apple! "))
        assertFalse(CurriculumDomainLogic.evaluateExerciseAnswer(exercise, "this is a book"))
    }
}
