package com.example

import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.core.database.*
import com.example.data.local.CurriculumSeedData
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import android.os.Looper

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CurriculumResetTest {

    @Test
    fun installedContentIsRemovedOnceAndProfileHistorySurvives() = runBlocking {
        val db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TalkMeDatabase::class.java
        ).allowMainThreadQueries().build()
        try {
            val dao = db.talkMeDao()
            dao.insertTopics(
                listOf(
                    TopicEntity(
                        id = "old_topic",
                        orderIndex = 1,
                        titleEnglish = "Old",
                        titleUzbek = "Eski",
                        descriptionUzbek = ""
                    )
                )
            )
            dao.insertSections(
                listOf(
                    LessonSectionEntity(
                        id = "old_section",
                        topicId = "old_topic",
                        orderIndex = 1,
                        titleEnglish = "Old",
                        titleUzbek = "Eski",
                        objectiveUzbek = "",
                        explanationUzbek = "",
                        wrapUpSummaryUzbek = "",
                        isUnlocked = true,
                        isCompleted = true
                    )
                )
            )
            dao.insertVocabulary(
                listOf(
                    VocabularyEntity(
                        id = "old_vocab",
                        topicId = "old_topic",
                        sectionId = "old_section",
                        englishWord = "Apple",
                        uzbekTranslation = "Olma",
                        exampleEnglish = "One apple",
                        exampleUzbek = "Bitta olma"
                    )
                )
            )
            dao.insertGrammarRules(
                listOf(
                    GrammarRuleEntity(
                        id = "old_grammar",
                        topicId = "old_topic",
                        sectionId = "old_section",
                        titleUzbek = "Old rule",
                        explanationUzbek = "Old",
                        exampleEnglish = "Old",
                        exampleUzbek = "Eski",
                        associatedVocabularyIdsCsv = "old_vocab",
                        isStudied = true,
                        isMastered = true
                    )
                )
            )
            dao.insertExercises(
                listOf(
                    ExerciseEntity(
                        id = "old_exercise",
                        topicId = "old_topic",
                        sectionId = "old_section",
                        practiceCategory = "VOCABULARY",
                        exerciseType = "MULTIPLE_CHOICE",
                        difficulty = 1,
                        promptText = "Old?",
                        subPromptText = "",
                        audioTextToSpeak = null,
                        optionsPipeSeparated = "A|B",
                        correctAnswer = "A",
                        explanationUzbek = "",
                        vocabularyIdsCsv = "old_vocab",
                        grammarRuleIdsCsv = "old_grammar"
                    )
                )
            )
            dao.upsertUserMistake(
                UserMistakeEntity(
                    id = "old_mistake",
                    topicId = "old_topic",
                    skillCategory = "VOCABULARY",
                    itemPrompt = "Old?",
                    userWrongAnswer = "B",
                    correctAnswer = "A",
                    explanationUzbek = "",
                    mistakeCount = 1,
                    isResolved = false,
                    timestamp = 999L
                )
            )
            dao.insertUserProfile(
                UserProfileEntity(
                    id = 1,
                    userName = "Learner",
                    currentTopicId = "old_topic",
                    currentSectionId = "old_section",
                    lastScrollPosition = 25,
                    hasStartedLearning = true,
                    streakDays = 12,
                    lastStudyTimestamp = 1234L
                )
            )
            dao.insertPracticeSession(
                PracticeSessionEntity(
                    skillCategory = "READING",
                    topicId = "old_topic",
                    sectionId = "old_section",
                    correctCount = 4,
                    totalCount = 5,
                    accuracyPercent = 80,
                    timestamp = 5678L
                )
            )
            dao.insertLearningActivity(
                LearningActivityEntity(
                    title = "Past Session",
                    subtitle = "Preserved",
                    timestamp = 5678L
                )
            )
            dao.upsertDailyTaskLog(
                DailyTaskLogEntity(
                    dateKey = "2026-11-12",
                    displayDateLabel = "12-noyabr",
                    topicId = "old_topic",
                    grammarDone = true,
                    vocabularyDone = true,
                    readingDone = false,
                    writingDone = false,
                    listeningDone = false,
                    speakingDone = false,
                    updatedTimestamp = 5678L
                )
            )

            assertTrue(dao.initializeEmptyCurriculum(CurriculumSeedData.buildTopics()))
            assertNull(dao.getTopicById("old_topic"))
            assertTrue(dao.getAllSectionsOnce().isEmpty())
            assertTrue(dao.getAllVocabularyOnce().isEmpty())
            assertTrue(dao.getGrammarRulesByTopicOnce("old_topic").isEmpty())
            assertTrue(dao.getExercisesBySection("old_section").isEmpty())
            assertTrue(dao.observeUnresolvedMistakes().first().isEmpty())
            assertEquals(7, dao.getTopicCount())

            val profile = dao.getUserProfileOnce()!!
            assertEquals("Learner", profile.userName)
            assertEquals("level_a0", profile.currentTopicId)
            assertEquals("", profile.currentSectionId)
            assertEquals(12, profile.streakDays)
            assertEquals(1234L, profile.lastStudyTimestamp)
            assertEquals(1, dao.observeAllPracticeSessions().first().size)
            assertEquals(1, dao.observeRecentActivities(10).first().size)
            assertEquals(1, dao.observeAllDailyTaskLogs().first().size)

            // A new book lesson added later must not be erased on restart.
            dao.insertSections(
                listOf(
                    LessonSectionEntity(
                        id = "new_section",
                        topicId = "level_a0",
                        orderIndex = 1,
                        titleEnglish = "New",
                        titleUzbek = "Yangi",
                        objectiveUzbek = "",
                        explanationUzbek = "",
                        wrapUpSummaryUzbek = "",
                        isUnlocked = true,
                        isCompleted = false
                    )
                )
            )
            assertFalse(dao.initializeEmptyCurriculum(CurriculumSeedData.buildTopics()))
            assertNotNull(dao.getSectionById("new_section"))
        } finally {
            db.close()
        }
    }

    @Test
    fun lessonOneWelcomeToTalkMeRendersHomeLessonsTasksLessonDetailAndPractice() {
        val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
        val activity = controller.get()
        try {
            val navHost = activity.supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHost.navController

            // 1. Home tab (start destination) — A0 with 60 lessons (241 vocab, 60 grammar)
            val homeView = navHost.childFragmentManager.primaryNavigationFragment?.requireView()!!
            waitUntil {
                homeView.findViewById<TextView>(R.id.tvHeroCourseName).text.toString() == "TalkMe English Course" &&
                    homeView.findViewById<View>(R.id.btnContinueLearning).isEnabled &&
                    homeView.findViewById<TextView>(R.id.tvHomeVocabBadge).text.toString() == "+241"
            }
            assertEquals(
                "1-Dars: Hello!",
                homeView.findViewById<TextView>(R.id.tvHeroSectionName).text.toString()
            )
            assertTrue(homeView.findViewById<View>(R.id.btnContinueLearning).isEnabled)
            assertEquals("+241", homeView.findViewById<TextView>(R.id.tvHomeVocabBadge).text.toString())
            assertEquals("+60", homeView.findViewById<TextView>(R.id.tvHomeGrammarBadge).text.toString())

            // 2. Lessons tab — only A0 unlocked, genuine 0/60 count, no filter chip row
            navController.navigate(R.id.lessonsFragment)
            waitUntil {
                val v = navHost.childFragmentManager.primaryNavigationFragment?.view
                v?.findViewById<TextView>(R.id.tvHeroLevelBadge)?.text?.toString() == "A0" &&
                    (v.findViewById<RecyclerView>(R.id.rvTopics)?.adapter?.itemCount ?: 0) > 0
            }
            val lessonsView = navHost.childFragmentManager.primaryNavigationFragment?.requireView()!!
            assertEquals("A0", lessonsView.findViewById<TextView>(R.id.tvHeroLevelBadge).text.toString())
            assertEquals(
                activity.getString(R.string.curriculum_lesson_count, 0, 60),
                lessonsView.findViewById<TextView>(R.id.tvHeroLessonCount).text.toString()
            )
            assertTrue(lessonsView.findViewById<View>(R.id.btnHeroContinue).isEnabled)
            assertNull(findFirstChipGroup(lessonsView))
            val topicsRv = lessonsView.findViewById<RecyclerView>(R.id.rvTopics)
            waitUntil { topicsRv.adapter?.itemCount == 7 }
            assertEquals(7, topicsRv.adapter?.itemCount)

            // 3. Practice tab + Listening exercise session from curriculum content
            navController.navigate(R.id.practiceFragment)
            waitUntil {
                val v = navHost.childFragmentManager.primaryNavigationFragment?.view
                v?.findViewById<View>(R.id.cardPracticeListening) != null
            }
            val practiceView = navHost.childFragmentManager.primaryNavigationFragment?.requireView()!!
            assertEquals(
                "Bugungi maqsad • 0/5",
                practiceView.findViewById<TextView>(R.id.tvPracticeDailyGoal).text.toString()
            )
            practiceView.findViewById<View>(R.id.cardPracticeListening).performClick()
            waitUntil {
                val v = navHost.childFragmentManager.primaryNavigationFragment?.view
                v?.findViewById<View>(R.id.layoutListeningStudioSection)?.visibility == View.VISIBLE
            }
            navController.navigateUp()

            // 4. Daily Tasks tab — Lesson task enabled and launches Dars 1
            navController.navigate(R.id.dailyTasksFragment)
            waitUntil {
                val v = navHost.childFragmentManager.primaryNavigationFragment?.view
                v?.findViewById<View>(R.id.btnTaskLesson)?.isEnabled == true
            }
            val tasksView = navHost.childFragmentManager.primaryNavigationFragment?.requireView()!!
            assertEquals("0/5", tasksView.findViewById<TextView>(R.id.tvVocabCount).text.toString())
            assertEquals("0/3 daq", tasksView.findViewById<TextView>(R.id.tvListeningCount).text.toString())
            assertEquals("0/3 gap", tasksView.findViewById<TextView>(R.id.tvSpeakingCount).text.toString())
            assertEquals("0/1", tasksView.findViewById<TextView>(R.id.tvLessonCount).text.toString())
            assertEquals(
                activity.getString(R.string.daily_tasks_task_lesson_subtitle),
                tasksView.findViewById<TextView>(R.id.tvLessonSubtitle).text.toString()
            )
            assertTrue(tasksView.findViewById<View>(R.id.btnTaskLesson).isEnabled)

            // 5. Open DARS 1: HELLO! from Daily Tasks and walk through to completion
            tasksView.findViewById<View>(R.id.btnTaskLesson).performClick()
            waitUntil {
                val v = navHost.childFragmentManager.primaryNavigationFragment?.view
                v?.findViewById<TextView>(R.id.tvLessonHeadingTitle)?.text?.toString() == "Hello!"
            }
            val lessonView = navHost.childFragmentManager.primaryNavigationFragment?.requireView()!!
            assertEquals("1 / 6", lessonView.findViewById<TextView>(R.id.tvLessonTopCounter).text.toString())
            assertEquals(
                activity.getString(R.string.lesson_explanation_heading),
                lessonView.findViewById<TextView>(R.id.tvSituationTitle).text.toString()
            )
            assertEquals(View.VISIBLE, lessonView.findViewById<View>(R.id.btnSpeakSituation).visibility)
            assertTrue(
                lessonView.findViewById<TextView>(R.id.tvSituationBody).text.toString().contains("Hello") ||
                lessonView.findViewById<TextView>(R.id.tvSituationBody).text.toString().contains("salomlashish")
            )
            assertEquals("hello", lessonView.findViewById<TextView>(R.id.tvVocabWord1).text.toString())
            assertEquals("hello, mom!", lessonView.findViewById<TextView>(R.id.tvVocabWord2).text.toString())
            assertEquals("hello, dad!", lessonView.findViewById<TextView>(R.id.tvVocabWord3).text.toString())
            assertEquals(View.VISIBLE, lessonView.findViewById<View>(R.id.layoutExtraQoidaVocab).visibility)

            val primaryCta = lessonView.findViewById<View>(R.id.btnLessonPrimaryCta)
            primaryCta.performClick()
            waitUntil { lessonView.findViewById<View>(R.id.layoutStageMisollar).visibility == View.VISIBLE }
            assertEquals("2 / 6", lessonView.findViewById<TextView>(R.id.tvLessonTopCounter).text.toString())
            val reading = lessonView.findViewById<android.widget.RadioGroup>(R.id.readingAnswers)
            val readingAnswer = (0 until reading.childCount)
                .map { reading.getChildAt(it) as TextView }.single { it.text.toString() == "Hello, Dad!" }
            readingAnswer.performClick()
            primaryCta.performClick()
            primaryCta.performClick()
            waitUntil { lessonView.findViewById<View>(R.id.layoutStageMashqlar).visibility == View.VISIBLE }
            assertEquals("3 / 6", lessonView.findViewById<TextView>(R.id.tvLessonTopCounter).text.toString())
            val written = lessonView.findViewById<android.widget.EditText>(R.id.etLessonGuidedAnswer)
            assertEquals(View.VISIBLE, written.visibility)
            written.setText("Goodbye!")
            primaryCta.performClick()
            written.setText("Hello!")
            primaryCta.performClick()
            primaryCta.performClick()

            waitUntil {
                navHost.childFragmentManager.primaryNavigationFragment?.view
                    ?.findViewById<android.widget.LinearLayout>(R.id.containerOptions)?.childCount == 3
            }
            val listening = navHost.childFragmentManager.primaryNavigationFragment!!.requireView()
            assertEquals("4 / 6", listening.findViewById<TextView>(R.id.tvStudioProgressCounter).text.toString())
            val options = listening.findViewById<android.widget.LinearLayout>(R.id.containerOptions)
            val correct = (0 until options.childCount).map { options.getChildAt(it) as android.widget.LinearLayout }
                .single { row -> (row.getChildAt(1) as TextView).text.toString() == "salom" }
            correct.performClick()
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            repeat(2) { listening.findViewById<View>(R.id.btnSubmitOrNext).performClick() }
            waitUntil { listening.findViewById<View>(R.id.btnFinishSession).isShown }
            listening.findViewById<View>(R.id.btnFinishSession).performClick()

            waitUntil {
                navHost.childFragmentManager.primaryNavigationFragment?.view
                    ?.findViewById<TextView>(R.id.tvLessonTopCounter)?.text.toString() == "5 / 6"
            }
            val resumed = navHost.childFragmentManager.primaryNavigationFragment!!.requireView()
            val resumedCta = resumed.findViewById<View>(R.id.btnLessonPrimaryCta)
            resumedCta.performClick()
            assertEquals("6 / 6", resumed.findViewById<TextView>(R.id.tvLessonTopCounter).text.toString())
            assertEquals(View.VISIBLE, resumed.findViewById<View>(R.id.layoutStageWrapUp).visibility)
            assertTrue(resumed.findViewById<TextView>(R.id.tvLessonResultScore).text.toString().contains("1 ta xato"))

            resumed.findViewById<View>(R.id.btnLessonMistakes).performClick()
            waitUntil {
                navHost.childFragmentManager.primaryNavigationFragment?.view
                    ?.findViewById<RecyclerView>(R.id.mistakeList)?.adapter?.itemCount == 1
            }
            navController.navigateUp()
            waitUntil {
                navHost.childFragmentManager.primaryNavigationFragment?.view
                    ?.findViewById<TextView>(R.id.tvLessonTopCounter)?.text.toString() == "6 / 6"
            }
            navHost.childFragmentManager.primaryNavigationFragment!!.requireView()
                .findViewById<View>(R.id.btnLessonPrimaryCta).performClick()
            waitUntil {
                navHost.childFragmentManager.primaryNavigationFragment?.view
                    ?.findViewById<TextView>(R.id.tvLessonHeadingTitle)?.text.toString() == "My name is..."
            }
        } finally {
            controller.pause().stop().destroy()
        }
    }

    private fun clickMatchingOption(lessonView: View, expectedAnswer: String) {
        val pairs = listOf(
            R.id.btnOption1 to R.id.tvOption1Text,
            R.id.btnOption2 to R.id.tvOption2Text,
            R.id.btnOption3 to R.id.tvOption3Text
        )
        for ((btnId, textId) in pairs) {
            val btn = lessonView.findViewById<View>(btnId)
            val tv = lessonView.findViewById<TextView>(textId)
            if (btn.visibility == View.VISIBLE && tv.text.toString() == expectedAnswer) {
                btn.performClick()
                return
            }
        }
        fail("Could not find option '$expectedAnswer' among visible buttons")
    }

    private fun waitUntil(timeoutMs: Long = 5_000L, predicate: () -> Boolean) {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (System.currentTimeMillis() < deadline) {
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            if (predicate()) return
            Thread.sleep(25)
        }
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("Timed out waiting for Robolectric UI state", predicate())
    }

    private fun findFirstChipGroup(root: View): ChipGroup? {
        if (root is ChipGroup) return root
        if (root is android.view.ViewGroup) {
            for (i in 0 until root.childCount) {
                findFirstChipGroup(root.getChildAt(i))?.let { return it }
            }
        }
        return null
    }

    private fun completeCurrentLesson(lessonView: View, primaryCta: View, exerciseCount: Int = 10) {
        // Explanation -> Misollar
        primaryCta.performClick()
        waitUntil { lessonView.findViewById<View>(R.id.layoutStageMisollar)?.visibility == View.VISIBLE }
        // Misollar -> Mashqlar
        primaryCta.performClick()
        waitUntil { lessonView.findViewById<View>(R.id.layoutStageMashqlar)?.visibility == View.VISIBLE }
        // Mashqlar (1..exerciseCount)
        for (i in 1..exerciseCount) {
            waitUntil { lessonView.findViewById<TextView>(R.id.tvStageHeaderBadge)?.text?.toString()?.startsWith("$i /") == true }
            val btn1 = lessonView.findViewById<View>(R.id.btnOption1)
            val btn2 = lessonView.findViewById<View>(R.id.btnOption2)
            val btn3 = lessonView.findViewById<View>(R.id.btnOption3)
            val readAloud = lessonView.findViewById<View>(R.id.btnReadAloudTap)

            if (btn1?.visibility == View.VISIBLE) btn1.performClick()
            else if (btn2?.visibility == View.VISIBLE) btn2.performClick()
            else if (btn3?.visibility == View.VISIBLE) btn3.performClick()
            else if (readAloud?.visibility == View.VISIBLE) {
                repeat(3) { readAloud.performClick() }
            }
            primaryCta.performClick()
        }
        // Wrap-up -> Complete lesson
        waitUntil { lessonView.findViewById<View>(R.id.layoutStageWrapUp)?.visibility == View.VISIBLE }
        primaryCta.performClick()
    }

}
