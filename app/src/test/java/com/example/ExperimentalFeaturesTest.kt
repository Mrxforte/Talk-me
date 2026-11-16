package com.example

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ApplicationProvider
import com.example.databinding.FragmentGeminiChatbotBinding
import com.example.databinding.FragmentProfileBinding
import com.example.databinding.FragmentSettingsBinding
import com.example.feature.experimental.ChatbotMessage
import com.example.feature.experimental.ChatbotModelTier
import com.example.feature.experimental.ChatbotPersonaPreset
import com.example.feature.experimental.ChatbotPromptBuilder
import com.example.feature.experimental.ChatbotStatus
import com.example.feature.experimental.ChatbotTextFormatter
import com.example.feature.experimental.GeminiChatbotAdapter
import com.example.feature.experimental.GeminiChatbotEngine
import com.example.feature.experimental.GeminiChatbotViewModel
import com.example.feature.experimental.LessonChatContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ExperimentalFeaturesTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun themedContext(): Context =
        ContextThemeWrapper(ApplicationProvider.getApplicationContext(), R.style.Theme_TalkMe)

    @Test
    fun chatbotLayoutAndProfileSettingsEntryPointsInflateCleanly() {
        val context = themedContext()
        val inflater = LayoutInflater.from(context)

        val chatbotBinding = FragmentGeminiChatbotBinding.inflate(inflater)
        assertNotNull(chatbotBinding.chipModelHydrogen)
        assertNotNull(chatbotBinding.chipModelArgon)
        assertNotNull(chatbotBinding.chipModelCarbon)
        assertNotNull(chatbotBinding.chipModelFlashLite)
        assertNotNull(chatbotBinding.chipRoleTutor)
        assertNotNull(chatbotBinding.chipRolePartner)
        assertNotNull(chatbotBinding.chipRoleGrammar)
        assertNotNull(chatbotBinding.chipRoleVocab)
        assertNotNull(chatbotBinding.chipRoleCustom)
        assertNotNull(chatbotBinding.rvChatbotMessages)
        assertNotNull(chatbotBinding.btnSendMessage)

        val profileBinding = FragmentProfileBinding.inflate(inflater)
        assertNotNull(profileBinding.cardExperimentalLab)
        assertNotNull(profileBinding.rowProfileExperimentalChatbot)

        val settingsBinding = FragmentSettingsBinding.inflate(inflater)
        assertNotNull(settingsBinding.cardExperimentalSettings)
        assertNotNull(settingsBinding.rowExperimentalChatbot)
    }

    @Test
    fun chatbotAdapterBindsUserAndFormattedModelMessagesWithBadges() {
        val context = themedContext()
        val adapter = GeminiChatbotAdapter {}
        val parent = FrameLayout(context)
        val holder = adapter.onCreateViewHolder(parent, 0)

        adapter.submitList(
            listOf(
                ChatbotMessage("u1", isUser = true, text = "Hello so'zi qanday ishlatiladi?"),
                ChatbotMessage(
                    "m1",
                    isUser = false,
                    text = "### Qoida\n- **Hello!** — rasmiy va kundalik salomlashuv.\n* `Hi!` — do'stona variant.",
                    modelBadge = "Carbon"
                )
            )
        )

        adapter.onBindViewHolder(holder, 0)
        assertEquals(View.GONE, holder.itemView.findViewById<View>(R.id.cardBotAvatar).visibility)
        assertEquals(
            "Hello so'zi qanday ishlatiladi?",
            holder.itemView.findViewById<android.widget.TextView>(R.id.tvMessageBody).text.toString()
        )

        adapter.onBindViewHolder(holder, 1)
        assertEquals(View.VISIBLE, holder.itemView.findViewById<View>(R.id.cardBotAvatar).visibility)
        assertEquals("Carbon", holder.itemView.findViewById<android.widget.TextView>(R.id.tvModelBadge).text)
        val rendered = holder.itemView.findViewById<android.widget.TextView>(R.id.tvMessageBody).text.toString()
        assertFalse(rendered.contains("###"))
        assertFalse(rendered.contains("**"))
        assertFalse(rendered.contains("`"))
        assertTrue(rendered.contains("• Hello!"))
        assertTrue(rendered.contains("• Hi!"))
    }

    @Test
    fun chatbotTextFormatterStripsMarkdownClutterAndPreservesMeaning() {
        val raw = """
            ### Present Simple
            ```
            Subject + V1
            ```
            **Muhim qoida:** Har doim *to'g'ri* shakl ishlating.
            - `I study` — Men o'qiyman.
            * She **works** every day.
            Dangling **marker** and __underscore__.
        """.trimIndent()

        val plain = ChatbotTextFormatter.formatPlainText(raw)
        assertFalse(plain.contains("###"))
        assertFalse(plain.contains("```"))
        assertFalse(plain.contains("**"))
        assertFalse(plain.contains("__"))
        assertFalse(plain.contains("`"))
        assertTrue(plain.contains("Present Simple"))
        assertTrue(plain.contains("• I study — Men o'qiyman."))
        assertTrue(plain.contains("• She works every day."))
    }

    @Test
    fun chatbotPromptBuilderEnforcesLessonScopeAndIncludesActiveContext() {
        val prompt = ChatbotPromptBuilder.buildSystemInstruction(
            persona = ChatbotPersonaPreset.GRAMMAR_EXPERT,
            customDirective = "",
            lessonContext = LessonChatContext(
                topicTitle = "Salomlashish va Tanishuv",
                levelCode = "A0",
                currentSectionTitle = "1-bo'lim: Salomlashish",
                studiedGrammarSummaries = listOf("To be: I am / You are"),
                studiedVocabularyWords = listOf("hello (salom)", "friend (do'st)")
            )
        )

        assertTrue(prompt.contains("LESSON-ONLY SCOPE"))
        assertTrue(prompt.contains("OFF-TOPIC REFUSAL"))
        assertTrue(prompt.contains("CLEAN OUTPUT FORMATTING"))
        assertTrue(prompt.contains("Salomlashish va Tanishuv (Level A0)"))
        assertTrue(prompt.contains("1-bo'lim: Salomlashish"))
        assertTrue(prompt.contains("To be: I am / You are"))
        assertTrue(prompt.contains("hello (salom)"))
    }

    @Test
    fun mainActivityNavigatesToChatbotDestinationAndHidesBottomBar() {
        val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
        val activity = controller.get()
        try {
            val navHost = activity.supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val bottomNav = activity.findViewById<View>(R.id.bottom_navigation)

            navHost.navController.navigate(R.id.geminiChatbotFragment)
            Shadows.shadowOf(android.os.Looper.getMainLooper()).idle()
            assertEquals(View.GONE, bottomNav.visibility)
        } finally {
            controller.pause().stop().destroy()
        }
    }

    @Test
    fun chatbotViewModelMaintainsMultiTurnHistorySwitchesModelsAndPersonas() = runTest(testDispatcher) {
        val recordedCalls = mutableListOf<Triple<String, String, List<ChatbotMessage>>>()
        val fakeEngine = object : GeminiChatbotEngine {
            override fun streamReply(
                modelId: String,
                systemInstruction: String,
                priorMessages: List<ChatbotMessage>,
                userPrompt: String
            ): Flow<String> {
                recordedCalls += Triple(modelId, systemInstruction, priorMessages)
                return flow {
                    if (userPrompt == "fail") {
                        throw IllegalStateException("Network error 503")
                    }
                    emit("Part 1 ")
                    emit("Part 2")
                }
            }
        }

        val vm = GeminiChatbotViewModel(fakeEngine, learningRepository = null)
        assertEquals(ChatbotModelTier.HYDROGEN, vm.uiState.value.selectedModel)

        vm.sendMessage("First question", "timeout", "empty")
        advanceUntilIdle()

        assertEquals(2, vm.uiState.value.messages.size)
        assertEquals("Part 1 Part 2", vm.uiState.value.messages[1].text)
        assertEquals("Hydrogen", vm.uiState.value.messages[1].modelBadge)
        assertEquals(ChatbotStatus.Idle, vm.uiState.value.status)

        // Switch model & custom system instruction, keep history and lesson guardrails
        vm.selectModel(ChatbotModelTier.CARBON)
        vm.setCustomSystemInstruction("Explain A0 greetings simply.")
        vm.sendMessage("Second question", "timeout", "empty")
        advanceUntilIdle()

        assertEquals(4, vm.uiState.value.messages.size)
        assertEquals("gemini-4-carbon", recordedCalls[1].first)
        assertTrue(recordedCalls[1].second.contains("Explain A0 greetings simply."))
        assertTrue(recordedCalls[1].second.contains("LESSON-ONLY SCOPE"))
        assertEquals(2, recordedCalls[1].third.size)
        assertEquals("Carbon", vm.uiState.value.messages[3].modelBadge)

        // Error propagation check
        vm.selectPersona(ChatbotPersonaPreset.VOCAB_HELPER)
        vm.sendMessage("fail", "timeout", "empty")
        advanceUntilIdle()
        assertTrue(vm.uiState.value.status is ChatbotStatus.Error)
        assertEquals("Network error 503", (vm.uiState.value.status as ChatbotStatus.Error).message)

        // Clear thread resets history & error
        vm.clearConversation()
        assertTrue(vm.uiState.value.messages.isEmpty())
        assertEquals(ChatbotStatus.Idle, vm.uiState.value.status)
    }
}
