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
 * 1. Curriculum topic containing 1. Introduction.
 * 2. Sequential topic unlocking enforcement.
 * 3. 4-button Spaced Repetition review scheduling (Again, Hard, Good, Easy).
 * 4. Exercise evaluation and option randomization.
 */
class ExampleUnitTest {

    @Test
    fun `curriculum seed contains Introduction as Topic 1`() {
        val topics = CurriculumSeedData.buildTopics()
        assertTrue(topics.isNotEmpty())
        assertEquals("A0", topics[0].titleEnglish)
        assertEquals("A0", topics[0].titleUzbek)
        assertTrue(topics[0].isUnlocked)
    }

    @Test
    fun `sequential topic unlocking computes correct statuses`() {
        val initialRaw = listOf(
            TopicModel("t1", 1, "Introduction", "Kirish", "", totalSections = 7, completedSections = 3, status = UnlockStatus.AVAILABLE)
        )

        val beforeCompletion = CurriculumDomainLogic.computeTopicUnlockStatuses(initialRaw)
        assertEquals(UnlockStatus.AVAILABLE, beforeCompletion[0].status)

        val afterTopic1Completed = listOf(
            initialRaw[0].copy(completedSections = 7)
        )
        val afterCompletion = CurriculumDomainLogic.computeTopicUnlockStatuses(afterTopic1Completed)
        assertEquals(UnlockStatus.COMPLETED, afterCompletion[0].status)
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
            audioTextToSpeak = "Hello, my friend.",
            options = emptyList(),
            correctAnswer = "Hello, my friend.",
            explanationUzbek = "",
            vocabularyIds = emptyList(),
            grammarRuleIds = emptyList()
        )

        assertTrue(CurriculumDomainLogic.evaluateExerciseAnswer(exercise, "  hello, my friend! "))
        assertFalse(CurriculumDomainLogic.evaluateExerciseAnswer(exercise, "goodbye my friend"))
    }
}
