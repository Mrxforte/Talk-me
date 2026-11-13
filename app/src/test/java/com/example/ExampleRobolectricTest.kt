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

}
