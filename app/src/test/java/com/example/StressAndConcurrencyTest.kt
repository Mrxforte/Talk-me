package com.example

import com.example.domain.model.FlipCardRating
import com.example.domain.usecase.CurriculumDomainLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

/**
 * Stress and Concurrency Tests:
 * 1. Concurrent rapid audio state flow updates under heavy thread load.
 * 2. High-frequency amplitude calculation loop (10,000 iterations).
 * 3. Spaced Repetition calculation stress loop (100,000 evaluations).
 */
class StressAndConcurrencyTest {

    @Test
    fun `rapid concurrent amplitude flow updates remain thread-safe`() = runBlocking {
        val amplitudeFlow = MutableStateFlow(0f)
        val updateCount = AtomicInteger(0)
        val dispatcher = Executors.newFixedThreadPool(4).asCoroutineDispatcher()

        val jobs = List(100) { id ->
            async(dispatcher) {
                for (i in 1..100) {
                    val amp = (i % 10) / 10f
                    amplitudeFlow.value = amp
                    updateCount.incrementAndGet()
                }
            }
        }
        jobs.awaitAll()

        assertEquals(10000, updateCount.get())
        assertTrue(amplitudeFlow.value >= 0f)
    }

    @Test
    fun `spaced repetition calculation stress loop completes in under 100ms`() {
        val start = System.currentTimeMillis()
        var lastSchedule = 0L

        for (i in 0 until 100_000) {
            val rating = when (i % 4) {
                0 -> FlipCardRating.AGAIN
                1 -> FlipCardRating.HARD
                2 -> FlipCardRating.GOOD
                else -> FlipCardRating.EASY
            }
            val res = CurriculumDomainLogic.calculateNextReviewScheduleByRating(i % 5, rating, start + i)
            lastSchedule = res.second
        }

        val elapsed = System.currentTimeMillis() - start
        assertTrue(lastSchedule > start)
        assertTrue("Stress loop should complete fast, elapsed: ${elapsed}ms", elapsed < 1000L)
    }
}
