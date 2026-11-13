package com.example.data.local

import com.example.core.database.*
import com.example.domain.model.CurriculumLevel

/** Sequential CEFR curriculum (A0–C2). */
object CurriculumSeedData {

    // A0 Section IDs
    const val DARS_1_SECTION_ID = A0CurriculumSeed.DARS_1_SECTION_ID
    const val DARS_2_SECTION_ID = A0CurriculumSeed.DARS_2_SECTION_ID
    const val DARS_3_SECTION_ID = A0CurriculumSeed.DARS_3_SECTION_ID
    const val DARS_4_SECTION_ID = A0CurriculumSeed.DARS_4_SECTION_ID
    const val DARS_5_SECTION_ID = A0CurriculumSeed.DARS_5_SECTION_ID
    const val DARS_6_SECTION_ID = A0CurriculumSeed.DARS_6_SECTION_ID
    const val DARS_7_SECTION_ID = A0CurriculumSeed.DARS_7_SECTION_ID
    const val DARS_8_SECTION_ID = A0CurriculumSeed.DARS_8_SECTION_ID
    const val DARS_9_SECTION_ID = A0CurriculumSeed.DARS_9_SECTION_ID
    const val DARS_10_SECTION_ID = A0CurriculumSeed.DARS_10_SECTION_ID
    const val DARS_11_SECTION_ID = A0CurriculumSeed.DARS_11_SECTION_ID
    const val DARS_12_SECTION_ID = A0CurriculumSeed.DARS_12_SECTION_ID
    const val DARS_13_SECTION_ID = A0CurriculumSeed.DARS_13_SECTION_ID
    const val DARS_14_SECTION_ID = A0CurriculumSeed.DARS_14_SECTION_ID
    const val DARS_15_SECTION_ID = A0CurriculumSeed.DARS_15_SECTION_ID
    const val DARS_16_SECTION_ID = A0CurriculumSeed.DARS_16_SECTION_ID
    const val DARS_17_SECTION_ID = A0CurriculumSeed.DARS_17_SECTION_ID
    const val DARS_18_SECTION_ID = A0CurriculumSeed.DARS_18_SECTION_ID
    const val DARS_19_SECTION_ID = A0CurriculumSeed.DARS_19_SECTION_ID
    const val DARS_20_SECTION_ID = A0CurriculumSeed.DARS_20_SECTION_ID
    const val DARS_21_SECTION_ID = A0CurriculumSeed.DARS_21_SECTION_ID
    const val DARS_22_SECTION_ID = A0CurriculumSeed.DARS_22_SECTION_ID
    const val DARS_23_SECTION_ID = A0CurriculumSeed.DARS_23_SECTION_ID
    const val DARS_24_SECTION_ID = A0CurriculumSeed.DARS_24_SECTION_ID
    const val DARS_25_SECTION_ID = A0CurriculumSeed.DARS_25_SECTION_ID
    const val DARS_26_SECTION_ID = A0CurriculumSeed.DARS_26_SECTION_ID
    const val DARS_27_SECTION_ID = A0CurriculumSeed.DARS_27_SECTION_ID
    const val DARS_28_SECTION_ID = A0CurriculumSeed.DARS_28_SECTION_ID
    const val DARS_29_SECTION_ID = A0CurriculumSeed.DARS_29_SECTION_ID
    const val DARS_30_SECTION_ID = A0CurriculumSeed.DARS_30_SECTION_ID
    const val DARS_31_SECTION_ID = A0CurriculumSeed.DARS_31_SECTION_ID
    const val DARS_32_SECTION_ID = A0CurriculumSeed.DARS_32_SECTION_ID
    const val DARS_33_SECTION_ID = A0CurriculumSeed.DARS_33_SECTION_ID
    const val DARS_34_SECTION_ID = A0CurriculumSeed.DARS_34_SECTION_ID
    const val DARS_35_SECTION_ID = A0CurriculumSeed.DARS_35_SECTION_ID
    const val DARS_36_SECTION_ID = A0CurriculumSeed.DARS_36_SECTION_ID
    const val DARS_37_SECTION_ID = A0CurriculumSeed.DARS_37_SECTION_ID
    const val DARS_38_SECTION_ID = A0CurriculumSeed.DARS_38_SECTION_ID
    const val DARS_39_SECTION_ID = A0CurriculumSeed.DARS_39_SECTION_ID
    const val DARS_40_SECTION_ID = A0CurriculumSeed.DARS_40_SECTION_ID
    const val DARS_41_SECTION_ID = A0CurriculumSeed.DARS_41_SECTION_ID
    const val DARS_42_SECTION_ID = A0CurriculumSeed.DARS_42_SECTION_ID
    const val DARS_43_SECTION_ID = A0CurriculumSeed.DARS_43_SECTION_ID
    const val DARS_44_SECTION_ID = A0CurriculumSeed.DARS_44_SECTION_ID
    const val DARS_45_SECTION_ID = A0CurriculumSeed.DARS_45_SECTION_ID
    const val DARS_46_SECTION_ID = A0CurriculumSeed.DARS_46_SECTION_ID
    const val DARS_47_SECTION_ID = A0CurriculumSeed.DARS_47_SECTION_ID
    const val DARS_48_SECTION_ID = A0CurriculumSeed.DARS_48_SECTION_ID
    const val DARS_49_SECTION_ID = A0CurriculumSeed.DARS_49_SECTION_ID
    const val DARS_50_SECTION_ID = A0CurriculumSeed.DARS_50_SECTION_ID
    const val DARS_51_SECTION_ID = A0CurriculumSeed.DARS_51_SECTION_ID
    const val DARS_52_SECTION_ID = A0CurriculumSeed.DARS_52_SECTION_ID
    const val DARS_53_SECTION_ID = A0CurriculumSeed.DARS_53_SECTION_ID

    // A1 Section IDs
    const val DARS_A1_1_SECTION_ID = A1CurriculumSeed.DARS_A1_1_SECTION_ID
    const val DARS_A1_2_SECTION_ID = A1CurriculumSeed.DARS_A1_2_SECTION_ID
    const val DARS_A1_3_SECTION_ID = A1CurriculumSeed.DARS_A1_3_SECTION_ID
    const val DARS_A1_4_SECTION_ID = A1CurriculumSeed.DARS_A1_4_SECTION_ID

    const val DARS_1_TALKME_TTS_TEXT = """Salom, aziz do‘stim! Men TalkMe ilovasiman.
Ingliz tilini birga o‘rganamiz! Qo‘rqmang, shoshilmang.
Har bir xato — bu o‘rganishning bir qismi.
Keling, ilk darsni boshlaymiz!"""

    data class LessonDialogueLine(
        val speakerName: String,
        val englishText: String,
        val uzbekText: String,
        val isUserTurn: Boolean = false,
        val audioText: String = englishText
    )

    data class LessonExampleSeed(
        val englishSentence: String,
        val blueHighlightWord: String,
        val greenHighlightWord: String,
        val uzbekSentence: String,
        val uzbekGreenWord: String
    )

    fun buildTopics(): List<TopicEntity> = CurriculumLevel.entries.map { level ->
        TopicEntity(
            id = level.topicId,
            orderIndex = level.ordinal + 1,
            titleEnglish = level.name,
            titleUzbek = level.name,
            descriptionUzbek = when (level) {
                CurriculumLevel.A0 -> "A0: Boshlang'ich bolalar uchun ingliz tili (53 ta dars)"
                CurriculumLevel.A1 -> "A1: Do fe'li va modal fe'llar (4 ta dars)"
                else -> ""
            },
            isUnlocked = level == CurriculumLevel.A0
        )
    }

    fun buildSections(): List<LessonSectionEntity> =
        A0CurriculumSeed.buildSections() + A1CurriculumSeed.buildSections() + ContinuationCurriculum.buildSections()

    fun buildVocabulary(): List<VocabularyEntity> =
        A0CurriculumSeed.buildVocabulary() + A1CurriculumSeed.buildVocabulary() + ContinuationCurriculum.buildVocabulary()

    fun buildGrammarRules(): List<GrammarRuleEntity> =
        A0CurriculumSeed.buildGrammarRules() + A1CurriculumSeed.buildGrammarRules() + ContinuationCurriculum.buildGrammarRules()

    fun buildExercises(): List<ExerciseEntity> =
        A0CurriculumSeed.buildExercises() + A1CurriculumSeed.buildExercises() + ContinuationCurriculum.buildExercises()

    fun getMisollarForSection(sectionId: String): List<LessonExampleSeed> =
        A0CurriculumSeed.getMisollar(sectionId) ?: A1CurriculumSeed.getMisollar(sectionId) ?: emptyList()

    fun buildAchievements(): List<AchievementEntity> = listOf(
        AchievementEntity(
            id = "ach_first_section",
            title = "First Step Taken",
            description = "Complete your first lesson section in TalkMe",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_vocab_5",
            title = "Word Collector",
            description = "Memorize at least 5 English vocabulary words via Flip Cards",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_grammar_3",
            title = "Grammar Builder",
            description = "Master grammar rules through interactive practice",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_first_topic",
            title = "Topic Conqueror",
            description = "Complete all sections in an entire Topic to unlock the next level",
            isUnlocked = false,
            unlockedTimestamp = 0L
        )
    )
}
