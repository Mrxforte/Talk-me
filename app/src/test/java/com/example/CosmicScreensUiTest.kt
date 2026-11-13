package com.example

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.test.core.app.ApplicationProvider
import com.example.core.ui.AiChatTranscriptAdapter
import com.example.core.ui.CosmicWaveformView
import com.example.databinding.FragmentDailyTasksBinding
import com.example.databinding.FragmentExerciseSessionBinding
import com.example.databinding.FragmentReadingAiBinding
import com.example.databinding.FragmentSpeakingAiBinding
import com.example.domain.model.AiTutorMessage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class CosmicScreensUiTest {

    private fun themedContext(): Context =
        ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.Theme_TalkMe)

    @Test
    fun speakingLayoutAndTranscriptAdapterBindCosmicUi() {
        val context = themedContext()
        val binding = FragmentSpeakingAiBinding.inflate(LayoutInflater.from(context))

        assertNotNull(binding.cardAiCharacterSection)
        assertNotNull(binding.waveformTutorHero)
        assertNotNull(binding.cardLiveSpeech)
        assertNotNull(binding.waveformUserLive)
        assertNotNull(binding.btnMicrophoneSpeak)

        val adapter = AiChatTranscriptAdapter {}
        adapter.updateStudentName("Azamat")
        val parent = FrameLayout(context)
        val holder = adapter.onCreateViewHolder(parent, 0)
        adapter.submitList(
            listOf(
                AiTutorMessage("1", "AI_TUTOR", "What's your name?", ""),
                AiTutorMessage("2", "STUDENT", "My name is Azamat.", "")
            )
        )

        adapter.onBindViewHolder(holder, 0)
        assertEquals("AI Tutor", holder.itemView.findViewById<android.widget.TextView>(R.id.tvSenderRole).text)
        assertEquals(View.VISIBLE, holder.itemView.findViewById<View>(R.id.cardTutorAvatar).visibility)
        assertEquals(View.GONE, holder.itemView.findViewById<View>(R.id.tvStudentAvatarBadge).visibility)

        adapter.onBindViewHolder(holder, 1)
        assertEquals("Azamat", holder.itemView.findViewById<android.widget.TextView>(R.id.tvSenderRole).text)
        assertEquals("A", holder.itemView.findViewById<android.widget.TextView>(R.id.tvStudentAvatarBadge).text)
        assertEquals(View.VISIBLE, holder.itemView.findViewById<View>(R.id.rowStudentAudioBadge).visibility)
    }

    @Test
    fun studioAndReadingAiLayoutsInflateWithAllSections() {
        val context = themedContext()
        val studioBinding = FragmentExerciseSessionBinding.inflate(LayoutInflater.from(context))

        assertNotNull(studioBinding.layoutReadingStudioSection)
        assertNotNull(studioBinding.layoutWritingStudioSection)
        assertNotNull(studioBinding.layoutListeningStudioSection)
        assertNotNull(studioBinding.waveformListeningRing)
        assertNotNull(studioBinding.btnListeningPlayPause)
        assertNotNull(studioBinding.btnListeningReplay)
        assertNotNull(studioBinding.btnListeningSpeed)
        assertNotNull(studioBinding.btnWritingSampleToggle)
        assertNotNull(studioBinding.layoutWritingInputBox)
        assertNotNull(studioBinding.btnSubmitOrNext)

        val readingAiBinding = FragmentReadingAiBinding.inflate(LayoutInflater.from(context))
        assertNotNull(readingAiBinding.btnSpeakPassage)
        assertNotNull(readingAiBinding.btnEvaluateReading)
    }

    @Test
    fun dailyTasksLayoutContainsCheckBadgesAndWaveformDrawsCleanly() {
        val context = themedContext()
        val tasksBinding = FragmentDailyTasksBinding.inflate(LayoutInflater.from(context))

        assertNotNull(tasksBinding.ivVocabDoneBadge)
        assertNotNull(tasksBinding.ivListeningDoneBadge)
        assertNotNull(tasksBinding.ivSpeakingDoneBadge)
        assertNotNull(tasksBinding.ivLessonDoneBadge)
        assertNotNull(tasksBinding.layoutRewardBanner)
        assertTrue(tasksBinding.btnCalendarPicker.layoutParams.height >= 48)

        val waveform = CosmicWaveformView(context)
        waveform.layout(0, 0, 200, 200)
        val bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        waveform.setMode(CosmicWaveformView.Mode.BARS)
        waveform.setAmplitude(0.8f)
        waveform.draw(canvas)

        waveform.setMode(CosmicWaveformView.Mode.RING)
        waveform.draw(canvas)
        bitmap.recycle()
    }

    @Test
    fun profileLayoutInflatesWithAllCosmicSectionsAndViewsDrawCleanly() {
        val context = themedContext()
        val binding = com.example.databinding.FragmentProfileBinding.inflate(LayoutInflater.from(context))

        // Header
        assertNotNull(binding.containerProfileAvatarTap)
        assertNotNull(binding.tvProfileLevelBadge)
        assertNotNull(binding.btnScrollToSettings)

        // Dynamic Mountain Roadmap
        assertNotNull(binding.viewLearningRoadmap)
        assertNotNull(binding.progressRoadmap)
        assertNotNull(binding.tvRoadmapCompletedLessons)
        assertNotNull(binding.tvRoadmapLevelStage)

        // Weekly Growth Chart & 4 Skills & Achievements
        assertNotNull(binding.toggleGrowthPeriod)
        assertNotNull(binding.chartWeeklyGrowth)
        assertNotNull(binding.progressSkillReading)
        assertNotNull(binding.progressSkillListening)
        assertNotNull(binding.progressSkillWriting)
        assertNotNull(binding.progressSkillSpeaking)
        // Achievements
        assertNotNull(binding.cardAchievements)
        assertNotNull(binding.tvAchievementStreakTitle)
        assertNotNull(binding.tvAchievementWordsTitle)

        // Draw dynamic roadmap at 0% and 60% A1->A2 cleanly
        val bitmap = Bitmap.createBitmap(480, 260, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        binding.viewLearningRoadmap.layout(0, 0, 480, 260)
        binding.viewLearningRoadmap.setRoadmapState(peakLevelCode = "A1", progressRatio = 0f)
        assertEquals("A1", binding.viewLearningRoadmap.getPeakLevelLabel())
        assertEquals(0f, binding.viewLearningRoadmap.getTargetRatio(), 0.001f)
        binding.viewLearningRoadmap.draw(canvas)

        binding.viewLearningRoadmap.setRoadmapState(peakLevelCode = "A2", progressRatio = 0.60f)
        assertEquals("A2", binding.viewLearningRoadmap.getPeakLevelLabel())
        assertEquals(0.60f, binding.viewLearningRoadmap.getTargetRatio(), 0.001f)
        binding.viewLearningRoadmap.draw(canvas)

        // Draw weekly growth chart cleanly
        binding.chartWeeklyGrowth.layout(0, 0, 480, 260)
        binding.chartWeeklyGrowth.setGrowthData(
            labels = listOf("Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya"),
            currentMinutes = listOf(15, 20, 25, 30, 40, 45, 50),
            previousMinutes = listOf(10, 12, 16, 20, 25, 30, 35),
            currentLegend = "Bu hafta (3 soat 45 daq.)",
            previousLegend = "O'tgan hafta (2 soat 28 daq.)"
        )
        binding.chartWeeklyGrowth.draw(canvas)
        bitmap.recycle()
    }

    @Test
    fun settingsLayoutInflatesWithBackButtonAndAllControls() {
        val context = themedContext()
        val binding = com.example.databinding.FragmentSettingsBinding.inflate(LayoutInflater.from(context))

        assertNotNull(binding.btnSettingsBack)
        assertTrue(binding.btnSettingsBack.layoutParams.width >= 48)
        assertTrue(binding.btnSettingsBack.layoutParams.height >= 48)

        assertNotNull(binding.toggleThemeMode)
        assertNotNull(binding.btnThemeSystem)
        assertNotNull(binding.btnThemeLight)
        assertNotNull(binding.btnThemeDark)
        assertNotNull(binding.switchSoundEnabled)
        assertNotNull(binding.seekSpeechSpeed)
        assertNotNull(binding.tvSpeechSpeedValue)
        assertNotNull(binding.toggleVoiceEngine)
        assertNotNull(binding.btnVoiceEngineNative)
        assertNotNull(binding.btnVoiceEngineAi)
        assertNotNull(binding.cardVoiceMale)
        assertNotNull(binding.cardVoiceFemale)
        assertNotNull(binding.btnPreviewVoiceMale)
        assertNotNull(binding.btnPreviewVoiceFemale)
        assertNotNull(binding.switchOfflineAudio)
        assertNotNull(binding.switchLessonReminders)
        assertNotNull(binding.rowPrivacyPolicy)
        assertNotNull(binding.rowDataManagement)
        assertNotNull(binding.rowAboutApp)
    }

    @Test
    fun mainActivityLayoutConstrainsNavHostAboveBottomBar() {
        val controller = org.robolectric.Robolectric.buildActivity(MainActivity::class.java).setup()
        val activity = controller.get()
        try {
            val navHostView = activity.findViewById<View>(R.id.nav_host_fragment)
            val navLp = navHostView.layoutParams as androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
            assertEquals(R.id.bottom_navigation, navLp.bottomToTop)

            val navHost = activity.supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as androidx.navigation.fragment.NavHostFragment
            val bottomNav = activity.findViewById<View>(R.id.bottom_navigation)

            navHost.navController.navigate(R.id.profileFragment)
            org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
            assertEquals(View.VISIBLE, bottomNav.visibility)

            navHost.navController.navigate(R.id.settingsFragment)
            org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
            assertEquals(View.GONE, bottomNav.visibility)
        } finally {
            controller.pause().stop().destroy()
        }
    }

    @Test
    fun progressAnimatorAnimatesFromZeroAndTracksLogicalTarget() {
        val context = themedContext()
        val bar = android.widget.ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)

        with(com.example.core.ui.ProgressAnimator) {
            bar.animateProgressTo(targetProgress = 20, maxValue = 100)
            assertEquals(20, bar.getLogicalProgress())
            assertEquals(100, bar.getLogicalMax())

            // Subsequent update keeps logical values synchronized
            bar.animateProgressTo(targetProgress = 65, maxValue = 100)
            assertEquals(65, bar.getLogicalProgress())

            // Reset allows clean replay from zero
            bar.resetOpenAnimationState()
            bar.animateProgressTo(targetProgress = 20, maxValue = 100)
            assertEquals(20, bar.getLogicalProgress())

            // Container traversal replays determinate bars while skipping indeterminate spinners
            val container = FrameLayout(context)
            val spinner = android.widget.ProgressBar(context).apply { isIndeterminate = true }
            container.addView(bar)
            container.addView(spinner)
            bar.progress = bar.max
            container.replayAllProgressFromZero()
            assertEquals(0, bar.progress)
        }
    }

    @Test
    fun customProgressViewsAndChartsAnimateFromZeroOnSetAndVisibilityReplay() {
        val context = themedContext()

        // 1. TasksCircularProgressView
        val circular = com.example.feature.dailytasks.TasksCircularProgressView(context)
        circular.setProgress(completed = 3, total = 4)
        assertEquals(0.75f, circular.getTargetRatio(), 0.001f)
        assertEquals(0f, circular.getAnimatedRatio(), 0.01f)
        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idleFor(
            java.time.Duration.ofMillis(1200)
        )
        assertEquals(0.75f, circular.getAnimatedRatio(), 0.01f)

        // Becoming visible replays from 0f -> 0.75f
        circular.replayAnimationFromZero()
        assertEquals(0f, circular.getAnimatedRatio(), 0.01f)

        // 2. LearningRoadmapView
        val roadmap = com.example.core.ui.LearningRoadmapView(context)
        roadmap.setRoadmapState(peakLevelCode = "A2", progressRatio = 0.45f)
        assertEquals(0.45f, roadmap.getTargetRatio(), 0.001f)
        assertEquals(0f, roadmap.getAnimatedRatio(), 0.01f)
        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idleFor(
            java.time.Duration.ofMillis(1200)
        )
        assertEquals(0.45f, roadmap.getAnimatedRatio(), 0.01f)
        roadmap.replayAnimationFromZero()
        assertEquals(0f, roadmap.getAnimatedRatio(), 0.01f)

        // 3. WeeklyGrowthChartView
        val weeklyChart = com.example.core.ui.WeeklyGrowthChartView(context)
        weeklyChart.setGrowthData(
            labels = listOf("Du", "Se", "Ch", "Pa", "Ju", "Sh", "Ya"),
            currentMinutes = listOf(10, 20, 30, 25, 35, 40, 50),
            previousMinutes = listOf(5, 10, 15, 15, 20, 25, 30),
            currentLegend = "Bu hafta",
            previousLegend = "O'tgan hafta"
        )
        assertEquals(0f, weeklyChart.getRevealFraction(), 0.01f)
        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idleFor(
            java.time.Duration.ofMillis(1200)
        )
        assertEquals(1f, weeklyChart.getRevealFraction(), 0.01f)
        weeklyChart.replayAnimationFromZero()
        assertEquals(0f, weeklyChart.getRevealFraction(), 0.01f)

        // 4. SkillAnalyticsChartView
        val analyticsChart = com.example.core.ui.SkillAnalyticsChartView(context)
        analyticsChart.setSkillData(
            listOf(
                com.example.core.ui.SkillAnalyticsChartView.SkillBarEntry(
                    label = "Reading",
                    accuracyPercent = 72,
                    sessionsCount = 4,
                    colorRes = R.color.color_neon_cyan
                )
            )
        )
        assertEquals(0f, analyticsChart.getRevealFraction(), 0.01f)
        org.robolectric.Shadows.shadowOf(android.os.Looper.getMainLooper()).idleFor(
            java.time.Duration.ofMillis(1200)
        )
        assertEquals(1f, analyticsChart.getRevealFraction(), 0.01f)
        analyticsChart.replayOpenAnimation()
        assertEquals(0f, analyticsChart.getRevealFraction(), 0.01f)
    }
}
