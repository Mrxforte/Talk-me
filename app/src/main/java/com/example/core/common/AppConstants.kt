package com.example.core.common

/**
 * Navigation bundle argument keys and application-wide constants.
 * Avoids magic strings and numbers across presentation and domain layers.
 */
object AppConstants {
    const val ARG_TOPIC_ID = "arg_topic_id"
    const val ARG_SECTION_ID = "arg_section_id"
    const val ARG_PRACTICE_CATEGORY = "arg_practice_category"
    const val ARG_SEQUENTIAL_DAILY_PLAN = "arg_sequential_daily_plan"

    const val DEFAULT_COURSE_ID = "course_talkme_english_v1"
    const val INITIAL_TOPIC_ID = "topic_01_introduction"
    const val INITIAL_SECTION_ID = "sec_01_01"

    // Spaced Repetition Review intervals in milliseconds (4-button rating system: Again, Hard, Good, Easy)
    const val TEN_MINUTES_MS = 600_000L
    const val ONE_HOUR_MS = 3_600_000L
    const val TWELVE_HOURS_MS = 43_200_000L
    const val ONE_DAY_MS = 86_400_000L
    const val THREE_DAYS_MS = 259_200_000L
    const val FOUR_DAYS_MS = 345_600_000L
    const val SEVEN_DAYS_MS = 604_800_000L

    // Mastery thresholds
    const val VOCABULARY_MASTERY_REQUIRED_REVIEWS = 1
    const val GRAMMAR_MASTERY_MIN_ACCURACY = 70

    // 3D Flip Animation Constants
    const val FLIP_CAMERA_DISTANCE = 8000f
    const val FLIP_HALF_DURATION_MS = 180L
}
