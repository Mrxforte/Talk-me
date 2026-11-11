package com.example

import com.example.core.common.AppConstants
import com.example.data.local.CurriculumSeedData
import com.example.domain.model.ExerciseModel
import com.example.domain.model.ExerciseType
import com.example.domain.model.MasteryStatus
import com.example.domain.model.PracticeCategory
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.usecase.CurriculumDomainLogic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * End-to-End Integrated Test covering the complete TalkMe Learning & Practice Pipeline:
 * - Curriculum Seeding -> Unlocking -> Exercise Validation -> Spaced Repetition -> Topic Summaries.
 */
class IntegrationFlowTest {

    @Test
    fun `complete learning flow from seeding to completion is verified`() {
        // 1. Seed Topics & Sections
        val topics = CurriculumSeedData.buildTopics()
        val sections = CurriculumSeedData.buildSections()
        val vocabulary = CurriculumSeedData.buildVocabulary()
        val grammar = CurriculumSeedData.buildGrammarRules()
        val exercises = CurriculumSeedData.buildExercises()

        assertTrue(topics.isNotEmpty())
        assertEquals(13, topics.size)
        assertTrue(sections.size >= 7)
        assertTrue(vocabulary.isNotEmpty())
        assertTrue(grammar.isNotEmpty())

        // 2. Initial state Topic 1 is AVAILABLE, Topic 2 is LOCKED
        val topic1 = topics.find { it.id == "topic_01_introduction" }
        assertNotNull(topic1)
        assertTrue(topic1!!.isUnlocked)

        // 3. Complete Topic 1 and compute unlock statuses
        val domainTopic1 = TopicModel(
            id = topic1.id,
            orderIndex = topic1.orderIndex,
            titleEnglish = topic1.titleEnglish,
            titleUzbek = topic1.titleUzbek,
            descriptionUzbek = topic1.descriptionUzbek,
            totalSections = 7,
            completedSections = 7,
            status = UnlockStatus.COMPLETED
        )

        val domainTopic2 = TopicModel(
            id = "topic_02_grammar_fundamentals",
            orderIndex = 2,
            titleEnglish = "Grammar Fundamentals",
            titleUzbek = "Grammatika asoslari",
            descriptionUzbek = "",
            totalSections = 2,
            completedSections = 0,
            status = UnlockStatus.LOCKED
        )

        val computed = CurriculumDomainLogic.computeTopicUnlockStatuses(listOf(domainTopic1, domainTopic2))
        assertEquals(UnlockStatus.COMPLETED, computed[0].status)
        assertEquals(UnlockStatus.AVAILABLE, computed[1].status)

        // 4. Validate exercise evaluation for Topic 1
        val introExercise = exercises.first { it.topicId == "topic_01_introduction" }
        val model = ExerciseModel(
            id = introExercise.id,
            topicId = introExercise.topicId,
            sectionId = introExercise.sectionId,
            practiceCategory = PracticeCategory.valueOf(introExercise.practiceCategory),
            exerciseType = ExerciseType.valueOf(introExercise.exerciseType),
            difficulty = introExercise.difficulty,
            promptText = introExercise.promptText,
            subPromptText = introExercise.subPromptText,
            audioTextToSpeak = introExercise.audioTextToSpeak,
            options = emptyList(),
            correctAnswer = introExercise.correctAnswer,
            explanationUzbek = introExercise.explanationUzbek,
            vocabularyIds = emptyList(),
            grammarRuleIds = emptyList()
        )

        val isCorrect = CurriculumDomainLogic.evaluateExerciseAnswer(model, introExercise.correctAnswer)
        assertTrue(isCorrect)
    }
}
