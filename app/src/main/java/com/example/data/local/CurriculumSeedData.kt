package com.example.data.local

import com.example.core.database.*
import com.example.domain.model.CurriculumLevel

/** Empty level scaffold awaiting the replacement textbook curriculum. */
object CurriculumSeedData {
    fun buildTopics(): List<TopicEntity> = CurriculumLevel.entries.map { level ->
        TopicEntity(id = level.topicId, orderIndex = level.ordinal + 1,
            titleEnglish = level.name, titleUzbek = level.name,
            descriptionUzbek = "", isUnlocked = level == CurriculumLevel.A0)
    }
    fun buildSections(): List<LessonSectionEntity> = emptyList()
    fun buildVocabulary(): List<VocabularyEntity> = emptyList()
    fun buildGrammarRules(): List<GrammarRuleEntity> = emptyList()
    fun buildExercises(): List<ExerciseEntity> = emptyList()

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
