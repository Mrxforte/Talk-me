package com.example

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.core.database.*
import com.example.data.local.CurriculumSeedData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CurriculumResetTest {
    @Test fun installedContentIsRemovedOnceAndProfileHistorySurvives() = runBlocking {
        val db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            TalkMeDatabase::class.java).allowMainThreadQueries().build()
        try {
            val dao = db.talkMeDao()
            dao.insertTopics(listOf(TopicEntity(id = "old_topic", orderIndex = 1,
                titleEnglish = "Old", titleUzbek = "Eski", descriptionUzbek = "")))
            dao.insertSections(listOf(LessonSectionEntity("old_section", "old_topic", 1,
                "Old", "Eski", "", "", "", true, true)))
            dao.insertUserProfile(UserProfileEntity(1, "Learner", "old_topic", "old_section",
                25, true, 12, 1234L))
            assertTrue(dao.initializeEmptyCurriculum(CurriculumSeedData.buildTopics()))
            assertNull(dao.getTopicById("old_topic"))
            assertTrue(dao.getAllSectionsOnce().isEmpty())
            assertEquals(7, dao.getTopicCount())
            val profile = dao.getUserProfileOnce()!!
            assertEquals("Learner", profile.userName)
            assertEquals(12, profile.streakDays)
            assertEquals(1234L, profile.lastStudyTimestamp)
            assertEquals("", profile.currentSectionId)
            // A new book lesson added later must not be erased on restart.
            dao.insertSections(listOf(LessonSectionEntity("new_section", "level_a0", 1,
                "New", "Yangi", "", "", "", true, false)))
            assertFalse(dao.initializeEmptyCurriculum(CurriculumSeedData.buildTopics()))
            assertNotNull(dao.getSectionById("new_section"))
        } finally {
            db.close()
        }
    }
}
