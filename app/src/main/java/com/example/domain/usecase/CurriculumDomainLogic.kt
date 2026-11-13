package com.example.domain.usecase

import com.example.core.common.AppConstants
import com.example.domain.model.ExerciseModel
import com.example.domain.model.MasteryStatus
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus

/**
 * Pure Domain logic for sequential unlocking, spaced repetition scheduling,
 * and exercise answer evaluation. Tested directly via unit tests.
 */
object CurriculumDomainLogic {

    /**
     * Enforces sequential topic unlocking:
     * - Topic 1 (orderIndex == 1) is always unlocked (AVAILABLE or COMPLETED).
     * - Topic N is unlocked if and only if Topic N-1 is COMPLETED.
     */
    fun computeTopicUnlockStatuses(
        rawTopics: List<TopicModel>
    ): List<TopicModel> {
        val sorted = rawTopics.sortedBy { it.orderIndex }
        val result = mutableListOf<TopicModel>()

        for (i in sorted.indices) {
            val current = sorted[i]
            val isCompleted = current.totalSections > 0 && current.completedSections >= current.totalSections
            val previousCompleted = if (i == 0) true else (result[i - 1].status == UnlockStatus.COMPLETED)

            val computedStatus = when {
                !previousCompleted -> UnlockStatus.LOCKED
                isCompleted -> UnlockStatus.COMPLETED
                previousCompleted -> UnlockStatus.AVAILABLE
                else -> UnlockStatus.LOCKED
            }
            result.add(current.copy(status = computedStatus))
        }
        return result
    }

    /**
     * Calculates the next review timestamp and new mastery status for a vocabulary item
     * based on the user's Flip Card response.
     */
    fun calculateNextReviewSchedule(
        currentReviewCount: Int,
        knewWord: Boolean,
        nowMillis: Long
    ): Pair<MasteryStatus, Long> {
        return calculateNextReviewScheduleByRating(
            currentReviewCount = currentReviewCount,
            rating = if (knewWord) com.example.domain.model.FlipCardRating.GOOD else com.example.domain.model.FlipCardRating.AGAIN,
            nowMillis = nowMillis
        )
    }

    /**
     * Calculates the next review timestamp and mastery status for the 4-button Flip Card rating system:
     * - AGAIN (Qayta): Returns to learning queue in 10 minutes (LEARNING).
     * - HARD (Qiyin): Schedules review in 12 hours (LEARNING).
     * - GOOD (Yaxshi): Marks as MEMORIZED_OR_MASTERED and schedules 1-3 days out.
     * - EASY (Oson): Marks as MEMORIZED_OR_MASTERED and schedules 4-7 days out.
     */
    fun calculateNextReviewScheduleByRating(
        currentReviewCount: Int,
        rating: com.example.domain.model.FlipCardRating,
        nowMillis: Long
    ): Pair<MasteryStatus, Long> {
        return when (rating) {
            com.example.domain.model.FlipCardRating.AGAIN -> {
                MasteryStatus.LEARNING to (nowMillis + AppConstants.TEN_MINUTES_MS)
            }
            com.example.domain.model.FlipCardRating.HARD -> {
                MasteryStatus.LEARNING to (nowMillis + AppConstants.TWELVE_HOURS_MS)
            }
            com.example.domain.model.FlipCardRating.GOOD -> {
                val newCount = currentReviewCount + 1
                val interval = when {
                    newCount <= 1 -> AppConstants.ONE_DAY_MS
                    newCount == 2 -> AppConstants.THREE_DAYS_MS
                    else -> AppConstants.SEVEN_DAYS_MS
                }
                MasteryStatus.MEMORIZED_OR_MASTERED to (nowMillis + interval)
            }
            com.example.domain.model.FlipCardRating.EASY -> {
                val newCount = currentReviewCount + 2
                val interval = when {
                    newCount <= 2 -> AppConstants.FOUR_DAYS_MS
                    newCount == 3 -> AppConstants.SEVEN_DAYS_MS
                    else -> AppConstants.SEVEN_DAYS_MS * 2
                }
                MasteryStatus.MEMORIZED_OR_MASTERED to (nowMillis + interval)
            }
        }
    }

    /**
     * Evaluates a user's exercise response against the expected answer, normalizing
     * case, punctuation, and extra whitespace.
     */
    fun evaluateExerciseAnswer(exercise: ExerciseModel, userAnswer: String): Boolean {
        val normalizedUser = normalizeText(userAnswer)
        val normalizedExpected = normalizeText(exercise.correctAnswer)
        if (normalizedUser.isEmpty()) return false
        return normalizedUser == normalizedExpected
    }

    /**
     * Randomizes multiple-choice options deterministically or randomly while ensuring
     * the correct answer is included.
     */
    fun randomizeExerciseOptions(options: List<String>): List<String> {
        if (options.size <= 1) return options
        return options.shuffled()
    }

    private fun normalizeText(input: String): String {
        return input
            .trim()
            .lowercase()
            .replace(Regex("[.,!?;:\"]"), "")
            .replace(Regex("\\s+"), " ")
    }
}
