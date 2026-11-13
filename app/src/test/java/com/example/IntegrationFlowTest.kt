package com.example

import com.example.data.local.CurriculumSeedData
import com.example.domain.model.CurriculumLevel
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.usecase.CurriculumDomainLogic
import org.junit.Assert.*
import org.junit.Test

class IntegrationFlowTest {
    private val introductoryTopics = setOf(CurriculumLevel.A0.topicId, CurriculumLevel.A1.topicId)

    @Test fun a2ThroughC2HaveSixtyCompleteSeededLessonsEach() {
        val topics = setOf(CurriculumLevel.A2.topicId, CurriculumLevel.B1.topicId, CurriculumLevel.B2.topicId, CurriculumLevel.C1.topicId, CurriculumLevel.C2.topicId)
        val sections = CurriculumSeedData.buildSections().filter { it.topicId in topics }
        val allWords = CurriculumSeedData.buildVocabulary()
        val words = allWords.groupBy { it.sectionId }
        val exercises = CurriculumSeedData.buildExercises().groupBy { it.sectionId }
        assertEquals(topics, sections.map { it.topicId }.toSet())
        sections.groupBy { it.topicId }.values.forEach { level ->
            assertEquals((1..60).toList(), level.map { it.orderIndex })
        }
        assertEquals(allWords.size, allWords.distinctBy { it.englishWord.lowercase() }.size)
        sections.forEach { lesson ->
            assertTrue(lesson.explanationUzbek.isNotBlank())
            assertTrue(lesson.wrapUpSummaryUzbek.isNotBlank())
            assertEquals(3, words.getValue(lesson.id).size)
            val questions = exercises.getValue(lesson.id)
            assertEquals(3, questions.size)
            assertEquals(1, questions.count { it.exerciseType == "LISTENING_COMPREHENSION" })
            questions.forEach {
                val options = it.optionsPipeSeparated.split("|")
                assertEquals(3, options.distinct().size)
                assertTrue(it.correctAnswer in options)
            }
        }
    }

    private fun levels() = CurriculumSeedData.buildTopics().map {
        TopicModel(it.id, it.orderIndex, it.titleEnglish, it.titleUzbek,
            it.descriptionUzbek, 0, 0, UnlockStatus.AVAILABLE)
    }

    @Test fun initialLessonOneInA0DoesNotUnlockFutureLevels() {
        val topics = CurriculumSeedData.buildTopics()
        val sections = CurriculumSeedData.buildSections()
        val sectionsByTopic = sections.groupBy { it.topicId }
        val initialModels = topics.map {
            val secCount = sectionsByTopic[it.id]?.size ?: 0
            TopicModel(it.id, it.orderIndex, it.titleEnglish, it.titleUzbek,
                it.descriptionUzbek, secCount, 0, UnlockStatus.AVAILABLE)
        }
        assertEquals(listOf("A0", "A1", "A2", "B1", "B2", "C1", "C2"), initialModels.map { it.titleEnglish })
        val actual = CurriculumDomainLogic.computeTopicUnlockStatuses(initialModels)
        assertEquals(UnlockStatus.AVAILABLE, actual.first().status)
        assertTrue(actual.drop(1).all { it.status == UnlockStatus.LOCKED })
        val expectedSections = (1..60).map { "sec_a0_%02d".format(it) } + (1..60).map { "sec_a1_%02d".format(it) }
        assertEquals(expectedSections, sections.filter { it.topicId in introductoryTopics }.map { it.id }.sorted())
        val vocab = CurriculumSeedData.buildVocabulary().filter { it.topicId in introductoryTopics }
        assertEquals(437, vocab.size)
        assertEquals(437, vocab.distinctBy { it.englishWord.lowercase() }.size)
        assertEquals(120, CurriculumSeedData.buildGrammarRules().count { it.topicId in introductoryTopics })
        val exercises = CurriculumSeedData.buildExercises().filter { it.topicId in introductoryTopics }
        assertEquals(372, exercises.size)
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_1_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_2_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_3_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_50_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_51_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_52_SECTION_ID })
        assertEquals(15, exercises.count { it.sectionId == CurriculumSeedData.DARS_53_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_A1_1_SECTION_ID })
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_A1_4_SECTION_ID })
    }

    @Test fun onlyTheLastRequiredLessonUnlocksTheNextLevel() {
        val initial = levels().map { it.copy(totalSections = 2) }.toMutableList()
        for (index in initial.indices) {
            initial[index] = initial[index].copy(completedSections = 1)
            var actual = CurriculumDomainLogic.computeTopicUnlockStatuses(initial)
            assertEquals(UnlockStatus.AVAILABLE, actual[index].status)
            assertTrue(actual.drop(index + 1).all { it.status == UnlockStatus.LOCKED })
            initial[index] = initial[index].copy(completedSections = 2)
            actual = CurriculumDomainLogic.computeTopicUnlockStatuses(initial)
            assertEquals(UnlockStatus.COMPLETED, actual[index].status)
            if (index < initial.lastIndex) assertEquals(UnlockStatus.AVAILABLE, actual[index + 1].status)
        }
    }

    @Test fun staleUnlockedFlagsCannotBypassPrerequisites() {
        val raw = levels().map { it.copy(totalSections = 2, completedSections = 2) }.toMutableList()
        raw[0] = raw[0].copy(completedSections = 1)
        val actual = CurriculumDomainLogic.computeTopicUnlockStatuses(raw)
        assertTrue(actual.drop(1).all { it.status == UnlockStatus.LOCKED })
    }
}
