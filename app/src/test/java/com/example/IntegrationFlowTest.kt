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

    @Test fun emptyCurriculumDoesNotUnlockFutureLevels() {
        assertEquals(listOf("A0", "A1", "A2", "B1", "B2", "C1", "C2"), levels().map { it.titleEnglish })
        val actual = CurriculumDomainLogic.computeTopicUnlockStatuses(levels())
        assertEquals(UnlockStatus.AVAILABLE, actual.first().status)
        assertTrue(actual.drop(1).all { it.status == UnlockStatus.LOCKED })
        assertTrue(CurriculumSeedData.buildSections().isEmpty())
        assertTrue(CurriculumSeedData.buildVocabulary().isEmpty())
        assertTrue(CurriculumSeedData.buildGrammarRules().isEmpty())
        assertTrue(CurriculumSeedData.buildExercises().isEmpty())
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
