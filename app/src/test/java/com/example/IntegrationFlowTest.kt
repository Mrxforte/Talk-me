package com.example

import com.example.data.local.CurriculumSeedData
import com.example.domain.model.TopicModel
import com.example.domain.model.UnlockStatus
import com.example.domain.usecase.CurriculumDomainLogic
import org.junit.Assert.*
import org.junit.Test

class IntegrationFlowTest {
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
        assertEquals(
            listOf(
                CurriculumSeedData.DARS_1_SECTION_ID,
                CurriculumSeedData.DARS_2_SECTION_ID,
                CurriculumSeedData.DARS_3_SECTION_ID,
                CurriculumSeedData.DARS_4_SECTION_ID,
                CurriculumSeedData.DARS_5_SECTION_ID,
                CurriculumSeedData.DARS_6_SECTION_ID,
                CurriculumSeedData.DARS_7_SECTION_ID,
                CurriculumSeedData.DARS_8_SECTION_ID,
                CurriculumSeedData.DARS_9_SECTION_ID,
                CurriculumSeedData.DARS_10_SECTION_ID,
                CurriculumSeedData.DARS_11_SECTION_ID,
                CurriculumSeedData.DARS_12_SECTION_ID,
                CurriculumSeedData.DARS_13_SECTION_ID,
                CurriculumSeedData.DARS_14_SECTION_ID,
                CurriculumSeedData.DARS_15_SECTION_ID,
                CurriculumSeedData.DARS_16_SECTION_ID,
                CurriculumSeedData.DARS_17_SECTION_ID,
                CurriculumSeedData.DARS_18_SECTION_ID,
                CurriculumSeedData.DARS_19_SECTION_ID,
                CurriculumSeedData.DARS_20_SECTION_ID,
                CurriculumSeedData.DARS_21_SECTION_ID,
                CurriculumSeedData.DARS_22_SECTION_ID,
                CurriculumSeedData.DARS_23_SECTION_ID,
                CurriculumSeedData.DARS_24_SECTION_ID,
                CurriculumSeedData.DARS_25_SECTION_ID,
                CurriculumSeedData.DARS_26_SECTION_ID,
                CurriculumSeedData.DARS_27_SECTION_ID,
                CurriculumSeedData.DARS_28_SECTION_ID,
                CurriculumSeedData.DARS_29_SECTION_ID,
                CurriculumSeedData.DARS_30_SECTION_ID,
                CurriculumSeedData.DARS_31_SECTION_ID,
                CurriculumSeedData.DARS_32_SECTION_ID,
                CurriculumSeedData.DARS_33_SECTION_ID,
                CurriculumSeedData.DARS_34_SECTION_ID,
                CurriculumSeedData.DARS_35_SECTION_ID,
                CurriculumSeedData.DARS_36_SECTION_ID
            ),
            sections.map { it.id }
        )
        val vocab = CurriculumSeedData.buildVocabulary()
        assertEquals(232, vocab.size)
        assertEquals(232, vocab.distinctBy { it.englishWord.lowercase() }.size)
        assertEquals(63, CurriculumSeedData.buildGrammarRules().size)
        val exercises = CurriculumSeedData.buildExercises()
        assertEquals(302, exercises.size)
        assertEquals(3, exercises.count { it.sectionId == CurriculumSeedData.DARS_1_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_2_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_3_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_4_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_5_SECTION_ID })
        assertEquals(11, exercises.count { it.sectionId == CurriculumSeedData.DARS_6_SECTION_ID })
        assertEquals(9, exercises.count { it.sectionId == CurriculumSeedData.DARS_7_SECTION_ID })
        assertEquals(9, exercises.count { it.sectionId == CurriculumSeedData.DARS_8_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_9_SECTION_ID })
        assertEquals(9, exercises.count { it.sectionId == CurriculumSeedData.DARS_10_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_11_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_12_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_13_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_14_SECTION_ID })
        assertEquals(9, exercises.count { it.sectionId == CurriculumSeedData.DARS_15_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_16_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_17_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_18_SECTION_ID })
        assertEquals(12, exercises.count { it.sectionId == CurriculumSeedData.DARS_19_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_25_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_26_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_27_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_28_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_29_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_30_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_31_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_32_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_33_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_34_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_35_SECTION_ID })
        assertEquals(10, exercises.count { it.sectionId == CurriculumSeedData.DARS_36_SECTION_ID })
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
