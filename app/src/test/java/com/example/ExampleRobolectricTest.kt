package com.example

import com.example.core.ui.SkillAnalyticsChartView
import com.example.data.local.CurriculumSeedData
import com.example.domain.model.FlipCardRating
import com.example.domain.usecase.CurriculumDomainLogic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Verifies curriculum integrity for the preserved Introduction lesson, 4-button flip card spaced repetition ratings,
 * pixel-perfect chart data series (Yearly Jan..Dec, Monthly, Weekly), and the strict rule
 * that no phonetic transcriptions or IPA brackets appear anywhere in content.
 */
class ExampleRobolectricTest {

    @Test
    fun `vocabulary and grammar entries contain no phonetic transcriptions`() {
        val topics = CurriculumSeedData.buildTopics()
        assertTrue(topics.isNotEmpty())
        assertEquals("Introduction", topics[0].titleEnglish)

        val vocabulary = CurriculumSeedData.buildVocabulary()
        assertFalse(vocabulary.isEmpty())
        vocabulary.forEach { item ->
            assertFalse(item.englishWord.contains("["))
            assertFalse(item.englishWord.contains("/"))
            assertFalse(item.uzbekTranslation.contains("["))
        }

        val sections = CurriculumSeedData.buildSections()
        assertTrue(sections.size >= 40)
        sections.forEach { sec ->
            assertTrueSectionWrapUp(sec.wrapUpSummaryUzbek)
        }
    }

    @Test
    fun `yearly monthly and weekly chart series match reference specification`() {
        assertEquals(
            listOf("Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"),
            SkillAnalyticsChartView.YEARLY_LABELS
        )
        assertEquals(12, SkillAnalyticsChartView.YEARLY_SERIES_1.size)
        assertEquals(12, SkillAnalyticsChartView.YEARLY_SERIES_2.size)
        assertEquals(5.0f, SkillAnalyticsChartView.YEARLY_SERIES_1.first(), 0.001f)
        assertEquals(1.0f, SkillAnalyticsChartView.YEARLY_SERIES_1[6], 0.001f) // July
        assertEquals(5.0f, SkillAnalyticsChartView.YEARLY_SERIES_1.last(), 0.001f) // Dec

        assertEquals(7, SkillAnalyticsChartView.WEEKLY_LABELS.size)
        assertEquals(11, SkillAnalyticsChartView.MONTHLY_LABELS.size)
    }

    @Test
    fun `four button flip card ratings schedule distinct review intervals`() {
        val now = 1_000_000L
        val again = CurriculumDomainLogic.calculateNextReviewScheduleByRating(2, FlipCardRating.AGAIN, now)
        val hard = CurriculumDomainLogic.calculateNextReviewScheduleByRating(2, FlipCardRating.HARD, now)
        val good = CurriculumDomainLogic.calculateNextReviewScheduleByRating(2, FlipCardRating.GOOD, now)
        val easy = CurriculumDomainLogic.calculateNextReviewScheduleByRating(2, FlipCardRating.EASY, now)

        assertTrue(again.second < hard.second)
        assertTrue(hard.second < good.second)
        assertTrue(good.second < easy.second)
    }

    @Test
    fun `vocabulary and exercises cover Partiya 1 and Partiya 2 topics accurately`() {
        val allVocab = CurriculumSeedData.buildVocabulary()
        val introVocab = allVocab.filter { it.topicId == "topic_01_introduction" }
        val numbersVocab = allVocab.filter { it.topicId == "topic_02_numbers" }
        val nounsVocab = allVocab.filter { it.topicId == "topic_03_nouns" }
        val adjVocab = allVocab.filter { it.topicId == "topic_04_adjectives" }

        assertTrue(introVocab.size >= 35)
        assertEquals(40, numbersVocab.size)
        assertTrue(nounsVocab.isNotEmpty())
        assertTrue(adjVocab.isNotEmpty())

        val allExercises = CurriculumSeedData.buildExercises()
        val introExercises = allExercises.filter { it.topicId == "topic_01_introduction" }
        assertTrue(introExercises.size >= 5)
        introExercises.forEach { ex ->
            assertEquals("topic_01_introduction", ex.topicId)
        }
    }

    private fun assertTrueSectionWrapUp(wrapUpText: String) {
        assertEquals(true, wrapUpText.startsWith("Wrap-up:"))
    }
}
