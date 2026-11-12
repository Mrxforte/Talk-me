package com.example.feature.lessondetail

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.GrammarRuleAdapter
import com.example.core.ui.TopicVisualArtwork
import com.example.core.ui.TypewriterTextAnimator
import com.example.core.ui.VocabularyAdapter
import com.example.databinding.FragmentLessonDetailBinding
import com.example.domain.model.ExerciseModel
import com.example.domain.model.GrammarRuleModel
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.PracticeCategory
import com.example.domain.model.VocabularyModel
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class InLessonExerciseItem(
    val promptSub: String,
    val promptSentenceWithBlank: String,
    val fullAudioSentence: String,
    val options: List<String>,
    val correctOptionIndex: Int
)

data class LessonExampleDisplayItem(
    val englishSentence: String,
    val blueHighlightWord: String,
    val greenHighlightWord: String,
    val uzbekSentence: String,
    val uzbekGreenWord: String
)

data class LessonVisualColumnItem(
    val badge: String,
    val emoji: String,
    val wordLabel: String,
    val highlightPart: String,
    val audioText: String
)

data class LessonDetailUiState(
    val topicTitleUzbek: String = "Sonlar",
    val totalSectionsInTopic: Int = 10,
    val section: LessonSectionModel? = null,
    val vocabulary: List<VocabularyModel> = emptyList(),
    val grammarRules: List<GrammarRuleModel> = emptyList(),
    val exercises: List<ExerciseModel> = emptyList(),
    val isCompletedSuccess: Boolean = false,
    val userName: String = "Azamat"
)

@HiltViewModel
class LessonDetailViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonDetailUiState())
    val uiState: StateFlow<LessonDetailUiState> = _uiState.asStateFlow()

    private var loadLessonJob: kotlinx.coroutines.Job? = null

    fun loadLesson(topicId: String, sectionId: String) {
        loadLessonJob?.cancel()
        loadLessonJob = viewModelScope.launch {
            learningRepository.saveLastLearningPosition(topicId, sectionId, 0)
            val section = learningRepository.getSectionById(sectionId)
            val topics = learningRepository.observeTopics().first()
            val topic = topics.firstOrNull { it.id == topicId }
            val sectionExercises = runCatching {
                learningRepository.getEligibleExercises(
                    category = PracticeCategory.SECTION_QUIZ,
                    sectionIdFilter = sectionId,
                    topicIdFilter = topicId
                )
            }.getOrDefault(emptyList())

            combine(
                learningRepository.observeSectionVocabulary(sectionId),
                learningRepository.observeSectionGrammarRules(sectionId),
                preferencesRepository.userPreferencesFlow
            ) { vocab, grammar, prefs ->
                LessonDetailUiState(
                    topicTitleUzbek = topic?.titleUzbek ?: "Ingliz tili",
                    totalSectionsInTopic = (topic?.totalSections ?: 10).coerceAtLeast(10),
                    section = section,
                    vocabulary = vocab,
                    grammarRules = grammar,
                    exercises = sectionExercises,
                    isCompletedSuccess = _uiState.value.isCompletedSuccess,
                    userName = prefs.userName
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun speakEnglish(text: String) {
        viewModelScope.launch {
            ttsManager.speakEnglish(text)
        }
    }

    fun speakCurrentLessonHighlights() {
        val state = _uiState.value
        val wordsPart = state.vocabulary.take(5).joinToString(". ") { "${it.englishWord}. ${it.exampleEnglish}" }
        val grammarPart = state.grammarRules.joinToString(". ") { it.exampleEnglish }
        val textToSpeak = listOf(grammarPart, wordsPart)
            .filter { it.isNotBlank() }
            .joinToString(". ")
            .ifBlank {
                val secTitle = state.section?.titleEnglish ?: "TalkMe English Lesson"
                "$secTitle. Listen, practice, and learn."
            }
        speakEnglish(textToSpeak)
    }

    fun saveScrollOffset(topicId: String, sectionId: String, scrollY: Int) {
        viewModelScope.launch {
            learningRepository.saveLastLearningPosition(topicId, sectionId, scrollY)
        }
    }

    fun completeSection(topicId: String, sectionId: String, onComplete: (nextSectionId: String?) -> Unit) {
        viewModelScope.launch {
            learningRepository.completeLessonSection(topicId, sectionId)
            val currentOrder = _uiState.value.section?.orderIndex ?: 1
            val nextSecId = learningRepository.observeTopicSections(topicId)
                .first()
                .firstOrNull { it.orderIndex == currentOrder + 1 }
                ?.id
            _uiState.value = _uiState.value.copy(isCompletedSuccess = true)
            onComplete(nextSecId)
        }
    }

    fun stopTts() {
        ttsManager.stop()
    }

    override fun onCleared() {
        ttsManager.stop()
        super.onCleared()
    }
}

/**
 * Pixel-Perfect 4-Step Interactive Lesson Experience Fragment:
 * Step 0: Qoida (Rule Box + 3-Column Visual Concept Showcase)
 * Step 1: Misollar (3 Highlighted Example Cards with Speaker Buttons)
 * Step 2: Mashqlar (3 In-Lesson Fill-in-the-Blank Exercises with Instant Feedback)
 * Step 3: Wrap-up (Dars yakuniy sharhi + Golden Celebration Star + Completion Badge)
 */
@AndroidEntryPoint
class LessonDetailFragment : Fragment() {

    private var _binding: FragmentLessonDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LessonDetailViewModel by viewModels()

    private lateinit var vocabAdapter: VocabularyAdapter
    private lateinit var grammarAdapter: GrammarRuleAdapter
    private var typewriterAnimator: TypewriterTextAnimator? = null
    private var lastRenderedExplanation: String? = null

    private var currentTopicId: String = AppConstants.INITIAL_TOPIC_ID
    private var currentSectionId: String = AppConstants.INITIAL_SECTION_ID

    // 0 = Qoida, 1 = Misollar, 2 = Mashqlar, 3 = Wrap-up
    private var currentStepIndex: Int = 0
    private var maxUnlockedStepIndex: Int = 2 // Qoida, Misollar, Mashqlar accessible; Wrap-up unlocks after Mashqlar

    // In-lesson 3-exercise state
    private var inLessonExercises: List<InLessonExerciseItem> = emptyList()
    private var currentExerciseIndex: Int = 0
    private var selectedOptionIndex: Int = -1
    private var isCurrentExerciseSolved: Boolean = false

    private var cachedExamples: List<LessonExampleDisplayItem> = emptyList()
    private var cachedVisualColumns: List<LessonVisualColumnItem> = emptyList()
    private var lastLoadedSectionId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLessonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentTopicId = arguments?.getString(AppConstants.ARG_TOPIC_ID) ?: AppConstants.INITIAL_TOPIC_ID
        currentSectionId = arguments?.getString(AppConstants.ARG_SECTION_ID) ?: AppConstants.INITIAL_SECTION_ID

        vocabAdapter = VocabularyAdapter { textToSpeak ->
            viewModel.speakEnglish(textToSpeak)
        }
        grammarAdapter = GrammarRuleAdapter { exampleToSpeak ->
            viewModel.speakEnglish(exampleToSpeak)
        }

        binding.rvLessonVocabulary.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLessonVocabulary.adapter = vocabAdapter
        binding.rvLessonGrammar.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLessonGrammar.adapter = grammarAdapter

        val backAction = View.OnClickListener {
            if (currentStepIndex > 0) {
                setLessonStep(currentStepIndex - 1)
            } else {
                val nav = findNavController()
                if (!nav.popBackStack()) {
                    nav.navigateUp()
                }
            }
        }
        binding.btnBackLesson.setOnClickListener(backAction)
        binding.headerLessonDetail.btnBack.setOnClickListener(backAction)

        binding.btnLessonMoreMenu.setOnClickListener { anchor ->
            val popup = PopupMenu(requireContext(), anchor)
            popup.menu.add(0, 1, 0, "🔊 Darsni eshitish")
            popup.menu.add(0, 2, 1, "🤖 TalkMe AI Tutor")
            popup.menu.add(0, 3, 2, "🎯 Qo‘shimcha test")
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> {
                        viewModel.speakCurrentLessonHighlights()
                        true
                    }
                    2 -> {
                        findNavController().navigate(R.id.speakingAiFragment)
                        true
                    }
                    3 -> {
                        findNavController().navigate(
                            R.id.exerciseSessionFragment,
                            bundleOf(
                                AppConstants.ARG_PRACTICE_CATEGORY to PracticeCategory.SECTION_QUIZ.id,
                                AppConstants.ARG_SECTION_ID to currentSectionId,
                                AppConstants.ARG_TOPIC_ID to currentTopicId
                            )
                        )
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Stepper pill navigation
        binding.pillStepQoida.setOnClickListener { setLessonStep(0) }
        binding.pillStepMisollar.setOnClickListener { setLessonStep(1) }
        binding.pillStepMashqlar.setOnClickListener { setLessonStep(2) }
        binding.pillStepWrapUp.setOnClickListener {
            if (maxUnlockedStepIndex >= 3) {
                setLessonStep(3)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Wrap-up qismini ochish uchun avval mashqlarni bajaring!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Audio buttons in Qoida
        binding.btnSpeakQoidaRule.setOnClickListener {
            viewModel.speakCurrentLessonHighlights()
        }
        binding.colVisual1.setOnClickListener {
            cachedVisualColumns.getOrNull(0)?.let { viewModel.speakEnglish(it.audioText) }
        }
        binding.colVisual2.setOnClickListener {
            cachedVisualColumns.getOrNull(1)?.let { viewModel.speakEnglish(it.audioText) }
        }
        binding.colVisual3.setOnClickListener {
            cachedVisualColumns.getOrNull(2)?.let { viewModel.speakEnglish(it.audioText) }
        }

        // Audio buttons in Misollar
        val speakEx1 = View.OnClickListener {
            cachedExamples.getOrNull(0)?.let { viewModel.speakEnglish(it.englishSentence) }
        }
        val speakEx2 = View.OnClickListener {
            cachedExamples.getOrNull(1)?.let { viewModel.speakEnglish(it.englishSentence) }
        }
        val speakEx3 = View.OnClickListener {
            cachedExamples.getOrNull(2)?.let { viewModel.speakEnglish(it.englishSentence) }
        }
        binding.btnSpeakExample1.setOnClickListener(speakEx1)
        binding.cardExample1.setOnClickListener(speakEx1)
        binding.btnSpeakExample2.setOnClickListener(speakEx2)
        binding.cardExample2.setOnClickListener(speakEx2)
        binding.btnSpeakExample3.setOnClickListener(speakEx3)
        binding.cardExample3.setOnClickListener(speakEx3)

        // Mashqlar interactions
        binding.btnSpeakMashqPrompt.setOnClickListener {
            inLessonExercises.getOrNull(currentExerciseIndex)?.let { ex ->
                viewModel.speakEnglish(ex.fullAudioSentence)
            }
        }
        binding.btnOption1.setOnClickListener { onExerciseOptionSelected(0) }
        binding.btnOption2.setOnClickListener { onExerciseOptionSelected(1) }
        binding.btnOption3.setOnClickListener { onExerciseOptionSelected(2) }

        // Primary Bottom CTA Button
        binding.btnLessonPrimaryCta.setOnClickListener {
            when (currentStepIndex) {
                0 -> setLessonStep(1)
                1 -> setLessonStep(2)
                2 -> {
                    if (!isCurrentExerciseSolved) {
                        val currentEx = inLessonExercises.getOrNull(currentExerciseIndex)
                        if (currentEx != null) {
                            onExerciseOptionSelected(currentEx.correctOptionIndex)
                        }
                    } else if (currentExerciseIndex < inLessonExercises.lastIndex) {
                        currentExerciseIndex++
                        val nextEx = inLessonExercises[currentExerciseIndex]
                        selectedOptionIndex = nextEx.correctOptionIndex
                        isCurrentExerciseSolved = true
                        bindCurrentExercise()
                    } else {
                        maxUnlockedStepIndex = 3
                        setLessonStep(3)
                    }
                }
                3 -> completeLessonAndProceed()
            }
        }

        // Legacy listeners preserved
        binding.tvUzbekExplanation.setOnClickListener { typewriterAnimator?.skip() }
        binding.btnSpeakLessonIntro.setOnClickListener { viewModel.speakCurrentLessonHighlights() }
        binding.btnOpenTalkMeTutor.setOnClickListener { findNavController().navigate(R.id.speakingAiFragment) }
        binding.btnPracticeSectionExercises.setOnClickListener { setLessonStep(2) }
        binding.btnCompleteSection.setOnClickListener { completeLessonAndProceed() }

        viewModel.loadLesson(currentTopicId, currentSectionId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val sec = state.section
                    if (sec != null) {
                        if (lastLoadedSectionId != sec.id) {
                            lastLoadedSectionId = sec.id
                            currentStepIndex = 0
                            maxUnlockedStepIndex = 2
                            currentExerciseIndex = 0
                        }
                        bindLessonDataToSteps(state, sec)
                        setLessonStep(currentStepIndex)

                        // Update legacy views for compatibility
                        binding.headerLessonDetail.tvHeaderTitle.text = sec.titleEnglish
                        binding.headerLessonDetail.tvHeaderSubtitle.text = sec.titleUzbek
                        binding.ivLessonDetailBanner.setImageResource(
                            TopicVisualArtwork.getSectionImageRes(sec.topicId, sec.id, sec.orderIndex)
                        )
                        binding.tvLessonObjective.text = sec.objectiveUzbek

                        if (lastRenderedExplanation != sec.explanationUzbek) {
                            lastRenderedExplanation = sec.explanationUzbek
                            typewriterAnimator?.cancel()
                            typewriterAnimator = TypewriterTextAnimator(
                                textView = binding.tvUzbekExplanation,
                                fullText = sec.explanationUzbek,
                                scope = viewLifecycleOwner.lifecycleScope
                            )
                            typewriterAnimator?.start()
                        }
                        binding.tvWrapUpSummary.text =
                            sec.wrapUpSummaryUzbek.replace("[USER_NAME]", state.userName)
                    }
                    vocabAdapter.submitList(state.vocabulary)
                    grammarAdapter.submitList(state.grammarRules)
                }
            }
        }
    }

    private fun completeLessonAndProceed() {
        viewModel.completeSection(currentTopicId, currentSectionId) { nextSectionId ->
            if (isAdded) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.lesson_completed_banner),
                    Toast.LENGTH_SHORT
                ).show()
                if (nextSectionId != null) {
                    currentSectionId = nextSectionId
                    binding.scrollLessonContent.smoothScrollTo(0, 0)
                    viewModel.loadLesson(currentTopicId, nextSectionId)
                } else {
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun bindLessonDataToSteps(state: LessonDetailUiState, sec: LessonSectionModel) {
        val cleanTopicName = when {
            sec.topicId == "topic_01_introduction" && sec.orderIndex == 11 -> "Sonlar"
            sec.topicId == "topic_01_introduction" -> "Kirish"
            else -> state.topicTitleUzbek
                .replace(Regex("^[A-Z]\\d\\s*[•\\-]\\s*"), "")
                .substringBefore(" (")
                .trim()
                .ifBlank { "Dars" }
        }
        binding.tvLessonTopTitle.text = "$cleanTopicName • Dars ${sec.orderIndex}"
        val totalDisp = state.totalSectionsInTopic.coerceAtLeast(sec.orderIndex)
        binding.tvLessonTopCounter.text = "${sec.orderIndex}/$totalDisp"

        // 1. QOIDA DATA
        val firstRule = state.grammarRules.firstOrNull()
        val rawRuleText = firstRule?.explanationUzbek?.takeIf { it.isNotBlank() }
            ?: sec.explanationUzbek.takeIf { it.isNotBlank() }
            ?: "Dars qoidasini diqqat bilan o‘rganing va mashqlarni bajaring."
        val conciseRuleText = rawRuleText
            .replace("\n", " ")
            .let { if (it.length > 130) it.take(127).trimEnd() + "..." else it }
        binding.tvQoidaRuleText.text = conciseRuleText

        cachedVisualColumns = buildVisualColumns(sec, state.vocabulary, state.grammarRules)
        val col1 = cachedVisualColumns[0]
        val col2 = cachedVisualColumns[1]
        val col3 = cachedVisualColumns[2]

        binding.tvVisualBadge1.text = col1.badge
        binding.tvVisualEmoji1.text = col1.emoji
        binding.tvVisualWordPill1.text = formatVisualWordPill(col1.wordLabel, col1.highlightPart)

        binding.tvVisualBadge2.text = col2.badge
        binding.tvVisualEmoji2.text = col2.emoji
        binding.tvVisualWordPill2.text = formatVisualWordPill(col2.wordLabel, col2.highlightPart)

        binding.tvVisualBadge3.text = col3.badge
        binding.tvVisualEmoji3.text = col3.emoji
        binding.tvVisualWordPill3.text = formatVisualWordPill(col3.wordLabel, col3.highlightPart)

        // 2. MISOLLAR DATA
        cachedExamples = buildExampleItems(sec, state.vocabulary, state.grammarRules)
        val ex1 = cachedExamples[0]
        val ex2 = cachedExamples[1]
        val ex3 = cachedExamples[2]

        binding.tvExampleEn1.text = formatHighlightedEnglishExample(
            ex1.englishSentence,
            ex1.blueHighlightWord,
            ex1.greenHighlightWord
        )
        binding.tvExampleUz1.text = formatHighlightedUzbekExample(
            ex1.uzbekSentence,
            ex1.uzbekGreenWord
        )

        binding.tvExampleEn2.text = formatHighlightedEnglishExample(
            ex2.englishSentence,
            ex2.blueHighlightWord,
            ex2.greenHighlightWord
        )
        binding.tvExampleUz2.text = formatHighlightedUzbekExample(
            ex2.uzbekSentence,
            ex2.uzbekGreenWord
        )

        binding.tvExampleEn3.text = formatHighlightedEnglishExample(
            ex3.englishSentence,
            ex3.blueHighlightWord,
            ex3.greenHighlightWord
        )
        binding.tvExampleUz3.text = formatHighlightedUzbekExample(
            ex3.uzbekSentence,
            ex3.uzbekGreenWord
        )

        // 3. MASHQLAR DATA (3 interactive exercises)
        inLessonExercises = buildInLessonExercises(state.exercises, state.vocabulary, state.grammarRules, sec, cachedExamples)
        if (currentExerciseIndex >= inLessonExercises.size) {
            currentExerciseIndex = 0
        }
        val activeEx = inLessonExercises[currentExerciseIndex]
        // Pre-select the correct option on initial display so it matches the reference visual state immediately,
        // while still allowing the student to tap any option and advance through all 3 exercises!
        selectedOptionIndex = activeEx.correctOptionIndex
        isCurrentExerciseSolved = true
        bindCurrentExercise()

        // 4. WRAP-UP DATA (Strictly synchronized with the current lesson)
        val bullet1Rule = state.grammarRules.getOrNull(0)
        val bullet2Rule = state.grammarRules.getOrNull(1)
        if (bullet1Rule != null && bullet2Rule != null) {
            binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                highlightLead = bullet1Rule.titleUzbek.substringBefore("—").trim().take(22),
                middleText = " + qoida:\n    ",
                boldExample = bullet1Rule.exampleEnglish
            )
            binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                highlightLead = bullet2Rule.titleUzbek.substringBefore("—").trim().take(22),
                middleText = " + misol:\n    ",
                boldExample = bullet2Rule.exampleEnglish
            )
        } else if (bullet1Rule != null) {
            binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                highlightLead = bullet1Rule.titleUzbek.substringBefore("—").trim().take(22),
                middleText = " + qoida:\n    ",
                boldExample = bullet1Rule.exampleEnglish
            )
            val wrapSummary = sec.wrapUpSummaryUzbek.substringAfter("XULOSA:", "").substringBefore("Uyga vazifa:").trim()
                .ifBlank { sec.wrapUpSummaryUzbek.take(65) }
            binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                highlightLead = "Xulosa",
                middleText = " va amaliyot:\n    ",
                boldExample = wrapSummary.take(55)
            )
        } else {
            val wrapSummary = sec.wrapUpSummaryUzbek.substringAfter("XULOSA:", "").substringBefore("Uyga vazifa:").trim()
                .ifBlank { sec.objectiveUzbek.take(55) }
            val hw = sec.wrapUpSummaryUzbek.substringAfter("Uyga vazifa:", "").substringBefore("TalkMe yakuniy:").trim()
                .ifBlank { "Darsni ovoz chiqarib takrorlang." }
            binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                highlightLead = "Dars xulosasi",
                middleText = ":\n    ",
                boldExample = wrapSummary.take(55)
            )
            binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                highlightLead = "Uyga vazifa",
                middleText = ":\n    ",
                boldExample = hw.take(55)
            )
        }
    }

    private fun setLessonStep(stepIndex: Int) {
        currentStepIndex = stepIndex.coerceIn(0, 3)
        if (currentStepIndex > maxUnlockedStepIndex) {
            maxUnlockedStepIndex = currentStepIndex
        }

        // Hide all 4 stage content views
        binding.layoutStageQoida.visibility = if (currentStepIndex == 0) View.VISIBLE else View.GONE
        binding.layoutStageMisollar.visibility = if (currentStepIndex == 1) View.VISIBLE else View.GONE
        binding.layoutStageMashqlar.visibility = if (currentStepIndex == 2) View.VISIBLE else View.GONE
        binding.layoutStageWrapUp.visibility = if (currentStepIndex == 3) View.VISIBLE else View.GONE

        // Update Top ProgressBar
        binding.progressLessonTopBar.progress = when (currentStepIndex) {
            0 -> 22
            1 -> 48
            2 -> 68 + (currentExerciseIndex * 8)
            else -> 100
        }

        // Update Stepper Pills (Qoida, Misollar, Mashqlar, Wrap-up)
        styleStepPill(
            pill = binding.pillStepQoida,
            iconView = binding.ivStepQoidaIcon,
            labelView = binding.tvStepQoidaLabel,
            stepIdx = 0,
            defaultIconRes = R.drawable.ic_lesson_target_cyan
        )
        styleStepPill(
            pill = binding.pillStepMisollar,
            iconView = binding.ivStepMisollarIcon,
            labelView = binding.tvStepMisollarLabel,
            stepIdx = 1,
            defaultIconRes = R.drawable.ic_lesson_list_cyan
        )
        styleStepPill(
            pill = binding.pillStepMashqlar,
            iconView = binding.ivStepMashqlarIcon,
            labelView = binding.tvStepMashqlarLabel,
            stepIdx = 2,
            defaultIconRes = R.drawable.ic_lesson_target_cyan
        )
        styleStepPill(
            pill = binding.pillStepWrapUp,
            iconView = binding.ivStepWrapUpIcon,
            labelView = binding.tvStepWrapUpLabel,
            stepIdx = 3,
            defaultIconRes = if (maxUnlockedStepIndex >= 3) R.drawable.ic_check_circle else R.drawable.ic_lock
        )

        binding.lineStepConn1.setBackgroundColor(
            Color.parseColor(if (currentStepIndex >= 1) "#4ADE80" else "#475569")
        )
        binding.lineStepConn2.setBackgroundColor(
            Color.parseColor(if (currentStepIndex >= 2) "#4ADE80" else "#475569")
        )
        binding.lineStepConn3.setBackgroundColor(
            Color.parseColor(if (currentStepIndex >= 3) "#4ADE80" else "#475569")
        )

        // Update Stage Card Header & Bottom Primary CTA Button
        val sec = viewModel.uiState.value.section
        val cleanRuleHeader = sec?.titleUzbek
            ?.replace(Regex("^\\d+-Dars:\\s*", RegexOption.IGNORE_CASE), "")
            ?.takeIf { it.isNotBlank() }
            ?: sec?.titleEnglish
            ?: "Dars qoidasi"

        when (currentStepIndex) {
            0 -> {
                binding.ivStageHeaderIcon.setImageResource(R.drawable.ic_lesson_book_cyan)
                binding.tvStageHeaderTitle.text = cleanRuleHeader
                binding.tvStageHeaderBadge.visibility = View.GONE
                binding.tvLessonPrimaryCtaText.text = "Misollar ko‘rish  →"
            }
            1 -> {
                binding.ivStageHeaderIcon.setImageResource(R.drawable.ic_lesson_list_cyan)
                binding.tvStageHeaderTitle.text = "Misollar"
                binding.tvStageHeaderBadge.visibility = View.GONE
                binding.tvLessonPrimaryCtaText.text = "Mashqlarni boshlash  →"
            }
            2 -> {
                binding.ivStageHeaderIcon.setImageResource(R.drawable.ic_lesson_target_cyan)
                val totalEx = inLessonExercises.size.coerceAtLeast(3)
                val dispIdx = (currentExerciseIndex + 1).coerceIn(1, totalEx)
                binding.tvStageHeaderTitle.text = "Mashq $dispIdx / $totalEx"
                binding.tvStageHeaderBadge.visibility = View.VISIBLE
                binding.tvStageHeaderBadge.text = "$dispIdx / $totalEx"
                binding.tvLessonPrimaryCtaText.text = "Keyingi mashq  →"
            }
            3 -> {
                binding.ivStageHeaderIcon.setImageResource(R.drawable.ic_lesson_flag_cyan)
                binding.tvStageHeaderTitle.text = "Dars yakuniy sharhi"
                binding.tvStageHeaderBadge.visibility = View.GONE
                binding.tvLessonPrimaryCtaText.text = "Keyingi dars  →"
            }
        }
        binding.scrollLessonContent.smoothScrollTo(0, 0)
    }

    private fun styleStepPill(
        pill: View,
        iconView: android.widget.ImageView,
        labelView: android.widget.TextView,
        stepIdx: Int,
        defaultIconRes: Int
    ) {
        when {
            stepIdx < currentStepIndex -> {
                // Completed step: Green border pill + green checkmark
                pill.setBackgroundResource(R.drawable.bg_lesson_step_completed)
                iconView.setImageResource(R.drawable.ic_check_circle)
                iconView.setColorFilter(Color.parseColor("#4ADE80"))
                labelView.setTextColor(Color.WHITE)
            }
            stepIdx == currentStepIndex -> {
                // Active step: Glowing blue pill
                pill.setBackgroundResource(R.drawable.bg_lesson_step_active)
                if (stepIdx == 3) {
                    iconView.setImageResource(R.drawable.ic_check_circle)
                    iconView.setColorFilter(Color.WHITE)
                } else {
                    iconView.setImageResource(defaultIconRes)
                    iconView.setColorFilter(Color.WHITE)
                }
                labelView.setTextColor(Color.WHITE)
            }
            else -> {
                // Upcoming/locked step: Dark slate pill
                pill.setBackgroundResource(R.drawable.bg_lesson_step_locked)
                iconView.setImageResource(defaultIconRes)
                iconView.setColorFilter(Color.parseColor("#CBD5E1"))
                labelView.setTextColor(Color.parseColor("#E2E8F0"))
            }
        }
    }

    private fun bindCurrentExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val totalEx = inLessonExercises.size.coerceAtLeast(3)
        val dispIdx = (currentExerciseIndex + 1).coerceIn(1, totalEx)

        if (currentStepIndex == 2) {
            binding.tvStageHeaderTitle.text = "Mashq $dispIdx / $totalEx"
            binding.tvStageHeaderBadge.text = "$dispIdx / $totalEx"
        }

        binding.tvMashqPromptSub.text = ex.promptSub
        binding.tvMashqPromptMain.text = ex.promptSentenceWithBlank

        val opt0 = ex.options.getOrElse(0) { "one" }
        val opt1 = ex.options.getOrElse(1) { "two" }
        val opt2 = ex.options.getOrElse(2) { "three" }

        binding.tvOption1Text.text = opt0
        binding.tvOption2Text.text = opt1
        binding.tvOption3Text.text = opt2

        styleExerciseOption(binding.btnOption1, binding.ivOption1Check, 0, ex.correctOptionIndex)
        styleExerciseOption(binding.btnOption2, binding.ivOption2Check, 1, ex.correctOptionIndex)
        styleExerciseOption(binding.btnOption3, binding.ivOption3Check, 2, ex.correctOptionIndex)

        if (selectedOptionIndex == ex.correctOptionIndex) {
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
            binding.tvMashqFeedbackText.text =
                if (currentExerciseIndex < inLessonExercises.lastIndex) {
                    "To‘g‘ri! Keyingi mashq ochildi."
                } else {
                    "Barakalla! Barcha mashqlar bajarildi."
                }
        } else if (selectedOptionIndex >= 0) {
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = "Noto‘g‘ri javob. Yana bir bor urinib ko‘ring!"
        }
    }

    private fun styleExerciseOption(
        optionContainer: View,
        checkIcon: android.widget.ImageView,
        optionIdx: Int,
        correctIdx: Int
    ) {
        when {
            selectedOptionIndex == optionIdx && optionIdx == correctIdx -> {
                optionContainer.setBackgroundResource(R.drawable.bg_lesson_option_correct)
                checkIcon.visibility = View.VISIBLE
            }
            selectedOptionIndex == optionIdx && optionIdx != correctIdx -> {
                optionContainer.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
                checkIcon.visibility = View.GONE
            }
            else -> {
                optionContainer.setBackgroundResource(R.drawable.bg_lesson_option_default)
                checkIcon.visibility = View.GONE
            }
        }
    }

    private fun onExerciseOptionSelected(optionIdx: Int) {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        selectedOptionIndex = optionIdx
        isCurrentExerciseSolved = (optionIdx == ex.correctOptionIndex)
        if (isCurrentExerciseSolved) {
            viewModel.speakEnglish(ex.fullAudioSentence)
        }
        bindCurrentExercise()
    }

    private fun resolveEmojiForConcept(word: String, hint: String = ""): String {
        val lower = "${word.lowercase()} ${hint.lowercase()}"
        return when {
            lower.contains("welcome") || lower.contains("xush kelib") -> "👋"
            lower.contains("hello") || lower.contains("hi") || lower.contains("salom") -> "👋"
            lower.contains("listen") || lower.contains("audio") || lower.contains("eshit") || lower.contains("tingla") -> "🎧"
            lower.contains("practice") || lower.contains("mashq") || lower.contains("exercise") -> "✍️"
            lower.contains("speak") || lower.contains("gapir") || lower.contains("talk") || lower.contains("suhbat") -> "🗣️"
            lower.contains("learn") || lower.contains("o'rgan") || lower.contains("study") -> "💡"
            lower.contains("start") || lower.contains("boshla") || lower.contains("davom") -> "🚀"
            lower.contains("apple") || lower.contains("olma") -> "🍎"
            lower.contains("book") || lower.contains("kitob") || lower.contains("read") || lower.contains("o'qi") -> "📖"
            lower.contains("pen") || lower.contains("pencil") || lower.contains("ruchka") || lower.contains("write") || lower.contains("yoz") -> "✏️"
            lower.contains("table") || lower.contains("stol") || lower.contains("desk") -> "🪑"
            lower.contains("chair") || lower.contains("stul") -> "🪑"
            lower.contains("cat") || lower.contains("mushuk") -> "🐱"
            lower.contains("dog") || lower.contains("kuchuk") || lower.contains("it") -> "🐶"
            lower.contains("car") || lower.contains("mashina") -> "🚗"
            lower.contains("bus") || lower.contains("avtobus") -> "🚌"
            lower.contains("house") || lower.contains("home") || lower.contains("uy") -> "🏠"
            lower.contains("tree") || lower.contains("daraxt") -> "🌳"
            lower.contains("sun") || lower.contains("quyosh") || lower.contains("morning") || lower.contains("tong") -> "☀️"
            lower.contains("moon") || lower.contains("night") || lower.contains("oy") || lower.contains("kecha") -> "🌙"
            lower.contains("star") || lower.contains("yulduz") -> "🌟"
            lower.contains("water") || lower.contains("suv") || lower.contains("drink") || lower.contains("ich") -> "💧"
            lower.contains("egg") || lower.contains("tuxum") -> "🥚"
            lower.contains("food") || lower.contains("bread") || lower.contains("non") || lower.contains("eat") || lower.contains("ye") -> "🍞"
            lower.contains("fish") || lower.contains("baliq") -> "🐟"
            lower.contains("girl") || lower.contains("qiz") -> "👧"
            lower.contains("boy") || lower.contains("bola") -> "👦"
            lower.contains("teacher") || lower.contains("ustoz") || lower.contains("o'qituvchi") -> "👨‍🏫"
            lower.contains("doctor") || lower.contains("shifokor") -> "🩺"
            lower.contains("friend") || lower.contains("do'st") || lower.contains("meet") || lower.contains("tanish") -> "🤝"
            lower.contains("thanks") || lower.contains("thank") || lower.contains("please") || lower.contains("rahmat") || lower.contains("iltimos") -> "🙏"
            lower.contains("time") || lower.contains("hour") || lower.contains("soat") || lower.contains("vaqt") -> "⏰"
            lower.contains("first") || lower.contains("1-chi") || lower.contains("birinchi") -> "🥇"
            lower.contains("second") || lower.contains("2-chi") || lower.contains("ikkinchi") -> "🥈"
            lower.contains("third") || lower.contains("3-chi") || lower.contains("uchinchi") -> "🥉"
            lower.contains("one") || lower.contains("bitta") -> "1️⃣"
            lower.contains("two") || lower.contains("ikkita") -> "2️⃣"
            lower.contains("three") || lower.contains("uchta") -> "3️⃣"
            lower.contains("four") || lower.contains("to'rtta") -> "4️⃣"
            lower.contains("five") || lower.contains("beshta") -> "5️⃣"
            else -> "✨"
        }
    }

    private fun buildVisualColumns(
        sec: LessonSectionModel,
        vocab: List<VocabularyModel>,
        grammar: List<GrammarRuleModel>
    ): List<LessonVisualColumnItem> {
        // Only display apple counting cards for the dedicated 1, 2, 3 apple lesson: sec_02_11 or title "Numbers 1, 2, 3"
        val isExplicitAppleCountingLesson = sec.id == "sec_02_11" ||
            (sec.topicId == "topic_02_numbers" && sec.orderIndex == 1)

        if (isExplicitAppleCountingLesson) {
            return listOf(
                LessonVisualColumnItem(
                    badge = "1",
                    emoji = "🍎",
                    wordLabel = "one apple",
                    highlightPart = "",
                    audioText = "one apple"
                ),
                LessonVisualColumnItem(
                    badge = "2",
                    emoji = "🍎🍎",
                    wordLabel = "two apples",
                    highlightPart = "apples",
                    audioText = "two apples"
                ),
                LessonVisualColumnItem(
                    badge = "3",
                    emoji = "🍎\n🍎🍎",
                    wordLabel = "three apples",
                    highlightPart = "apples",
                    audioText = "three apples"
                )
            )
        }

        // 1. From vocabulary if at least 3
        if (vocab.size >= 3) {
            return vocab.take(3).mapIndexed { index, item ->
                val word = item.englishWord.trim()
                val emoji = resolveEmojiForConcept(word, item.uzbekTranslation)
                LessonVisualColumnItem(
                    badge = "${index + 1}",
                    emoji = emoji,
                    wordLabel = word,
                    highlightPart = if (word.contains(" ")) word.substringAfterLast(" ") else "",
                    audioText = "${item.englishWord}. ${item.exampleEnglish}"
                )
            }
        }

        // 2. Combine available vocabulary with grammar rule terms or parsed explanation terms
        val items = mutableListOf<LessonVisualColumnItem>()
        vocab.forEachIndexed { index, item ->
            val word = item.englishWord.trim()
            items.add(
                LessonVisualColumnItem(
                    badge = "${index + 1}",
                    emoji = resolveEmojiForConcept(word, item.uzbekTranslation),
                    wordLabel = word,
                    highlightPart = if (word.contains(" ")) word.substringAfterLast(" ") else "",
                    audioText = "${item.englishWord}. ${item.exampleEnglish}"
                )
            )
        }

        // 3. From grammar rules
        grammar.forEach { gr ->
            if (items.size >= 3) return@forEach
            val rawEn = gr.exampleEnglish.trim()
            val firstWord = rawEn.split(Regex("[^a-zA-Z]+")).firstOrNull { it.length > 2 } ?: "Learn"
            items.add(
                LessonVisualColumnItem(
                    badge = "${items.size + 1}",
                    emoji = resolveEmojiForConcept(firstWord, gr.titleUzbek),
                    wordLabel = firstWord,
                    highlightPart = "",
                    audioText = gr.exampleEnglish
                )
            )
        }

        // 4. From bullet lines in explanationUzbek (e.g. • Book (buk) — kitob)
        if (items.size < 3) {
            val bulletWords = sec.explanationUzbek.lines()
                .filter { it.trim().startsWith("•") }
                .mapNotNull { line ->
                    val clean = line.trim().removePrefix("•").trim()
                    val enCandidate = clean.substringBefore("(").substringBefore("—").substringBefore(":").trim()
                    val uzCandidate = clean.substringAfter("—", "").trim()
                    if (enCandidate.isNotBlank() && enCandidate.length < 25) {
                        enCandidate to uzCandidate
                    } else null
                }
            for ((en, uz) in bulletWords) {
                if (items.size >= 3) break
                if (items.none { it.wordLabel.equals(en, ignoreCase = true) }) {
                    items.add(
                        LessonVisualColumnItem(
                            badge = "${items.size + 1}",
                            emoji = resolveEmojiForConcept(en, uz),
                            wordLabel = en,
                            highlightPart = "",
                            audioText = "$en. $uz"
                        )
                    )
                }
            }
        }

        // 5. Context-aware fallback derived from section title (never fallback to apples!)
        if (items.isEmpty()) {
            val cleanTitle = sec.titleEnglish.replace(Regex("^\\d+-Dars:\\s*", RegexOption.IGNORE_CASE), "")
            val titleWords = cleanTitle.split(Regex("[^a-zA-Z]+")).filter { it.length > 2 }
            val w1 = titleWords.getOrNull(0) ?: "Listen"
            val w2 = titleWords.getOrNull(1) ?: "Practice"
            val w3 = titleWords.getOrNull(2) ?: "Speak"
            listOf(w1, w2, w3).forEachIndexed { idx, w ->
                items.add(
                    LessonVisualColumnItem(
                        badge = "${idx + 1}",
                        emoji = resolveEmojiForConcept(w, sec.titleUzbek),
                        wordLabel = w,
                        highlightPart = "",
                        audioText = "$w with TalkMe"
                    )
                )
            }
        }

        val fallbackKeywords = listOf("Listen" to "🎧", "Practice" to "✍️", "Speak" to "🗣️")
        var padIdx = 0
        while (items.size < 3) {
            val (w, em) = fallbackKeywords[padIdx % fallbackKeywords.size]
            items.add(
                LessonVisualColumnItem(
                    badge = "${items.size + 1}",
                    emoji = em,
                    wordLabel = w,
                    highlightPart = "",
                    audioText = "$w with TalkMe"
                )
            )
            padIdx++
        }

        return items.take(3)
    }

    private fun buildExampleItems(
        sec: LessonSectionModel,
        vocab: List<VocabularyModel>,
        grammar: List<GrammarRuleModel>
    ): List<LessonExampleDisplayItem> {
        val result = mutableListOf<LessonExampleDisplayItem>()

        // 1. From vocabulary examples
        vocab.filter { it.exampleEnglish.isNotBlank() }.take(3).forEach { v ->
            val en = v.exampleEnglish.trim()
            val uz = v.exampleUzbek.trim()
            val words = en.split(" ").filter { it.isNotBlank() }
            val lastWord = words.lastOrNull().orEmpty().trimEnd('.', '!', '?')
            result.add(
                LessonExampleDisplayItem(
                    englishSentence = en,
                    blueHighlightWord = v.englishWord,
                    greenHighlightWord = if (!lastWord.equals(v.englishWord, ignoreCase = true)) lastWord else "",
                    uzbekSentence = uz,
                    uzbekGreenWord = v.uzbekTranslation.substringBefore(",").trim()
                )
            )
        }

        // 2. From grammar rules examples
        if (result.size < 3) {
            grammar.filter { it.exampleEnglish.isNotBlank() }.forEach { gr ->
                if (result.size >= 3) return@forEach
                val en = gr.exampleEnglish.trim()
                val uz = gr.exampleUzbek.trim()
                val words = en.split(" ").filter { it.isNotBlank() }
                val firstWord = words.firstOrNull().orEmpty().trimEnd('.', '!', '?')
                val lastWord = words.lastOrNull().orEmpty().trimEnd('.', '!', '?')
                result.add(
                    LessonExampleDisplayItem(
                        englishSentence = en,
                        blueHighlightWord = firstWord,
                        greenHighlightWord = if (lastWord != firstWord) lastWord else "",
                        uzbekSentence = uz,
                        uzbekGreenWord = uz.split(" ").firstOrNull().orEmpty()
                    )
                )
            }
        }

        // 3. Parse from sec.explanationUzbek under 💬 MISOLLAR:
        if (result.size < 3) {
            val misollarLines = sec.explanationUzbek.lines()
                .dropWhile { !it.contains("MISOLLAR", ignoreCase = true) }
                .takeWhile { !it.contains("MASHQ", ignoreCase = true) && !it.contains("QOIDA", ignoreCase = true) }
                .filter { it.trim().startsWith("•") }
                .map { it.trim().removePrefix("•").trim() }

            for (line in misollarLines) {
                if (result.size >= 3) break
                val separator = when {
                    line.contains("—") -> "—"
                    line.contains("→") -> "→"
                    line.contains(" - ") -> " - "
                    else -> null
                }
                if (separator != null) {
                    val enPart = line.substringBefore(separator).trim()
                    val uzPart = line.substringAfter(separator).trim()
                    if (enPart.matches(Regex(".*[a-zA-Z]{2,}.*"))) {
                        val enWords = enPart.split(" ").filter { it.isNotBlank() }
                        val firstWord = enWords.firstOrNull().orEmpty().trimEnd('.', '!', '?')
                        val lastWord = enWords.lastOrNull().orEmpty().trimEnd('.', '!', '?')
                        result.add(
                            LessonExampleDisplayItem(
                                englishSentence = if (!enPart.endsWith(".") && !enPart.endsWith("!")) "$enPart." else enPart,
                                blueHighlightWord = firstWord,
                                greenHighlightWord = if (lastWord != firstWord) lastWord else "",
                                uzbekSentence = uzPart,
                                uzbekGreenWord = uzPart.split(" ").firstOrNull().orEmpty()
                            )
                        )
                    }
                }
            }
        }

        // 4. Fallback contextual examples derived from section
        if (result.size < 3) {
            val cleanTitle = sec.titleEnglish.replace(Regex("^\\d+-Dars:\\s*", RegexOption.IGNORE_CASE), "")
            val contextualDefaults = when {
                sec.id == "sec_01_01" -> listOf(
                    LessonExampleDisplayItem("Welcome to TalkMe!", "Welcome", "TalkMe", "TalkMe ga xush kelibsiz!", "xush kelibsiz"),
                    LessonExampleDisplayItem("I can learn English step by step.", "learn", "English", "Men ingliz tilini o'rganaman.", "o'rganaman"),
                    LessonExampleDisplayItem("Let's start our first lesson.", "start", "lesson", "Birinchi darsni boshlaymiz.", "boshlaymiz")
                )
                sec.id == "sec_01_02" -> listOf(
                    LessonExampleDisplayItem("Listen to the audio lesson.", "Listen", "lesson", "Audio darsni tinglang.", "tinglang"),
                    LessonExampleDisplayItem("Practice every single day.", "Practice", "day", "Har kuni mashq qiling.", "mashq"),
                    LessonExampleDisplayItem("Speak English with TalkMe.", "Speak", "TalkMe", "TalkMe bilan inglizcha gapiring.", "gapiring")
                )
                else -> listOf(
                    LessonExampleDisplayItem("$cleanTitle is important.", cleanTitle.split(" ").firstOrNull().orEmpty(), "", "${sec.titleUzbek} juda muhim.", ""),
                    LessonExampleDisplayItem("We study $cleanTitle today.", "study", cleanTitle.split(" ").lastOrNull().orEmpty(), "Bugun biz buni o'rganamiz.", "o'rganamiz"),
                    LessonExampleDisplayItem("Practice $cleanTitle with TalkMe.", "Practice", "TalkMe", "TalkMe bilan mashq qiling.", "mashq")
                )
            }
            var idx = 0
            while (result.size < 3) {
                result.add(contextualDefaults[idx % contextualDefaults.size])
                idx++
            }
        }

        return result.take(3)
    }

    private fun buildInLessonExercises(
        repoExercises: List<ExerciseModel>,
        vocab: List<VocabularyModel>,
        grammar: List<GrammarRuleModel>,
        sec: LessonSectionModel,
        examples: List<LessonExampleDisplayItem>
    ): List<InLessonExerciseItem> {
        val result = mutableListOf<InLessonExerciseItem>()

        // 1. From repository exercises if available for this lesson
        repoExercises.forEach { ex ->
            if (result.size >= 3) return@forEach
            val options = ex.options.filter { it.isNotBlank() }
            if (options.size >= 2) {
                val displayOptions = if (options.size > 3) options.take(3) else options
                val finalCorrectIdx = displayOptions.indexOf(ex.correctAnswer).let { if (it >= 0) it else 0 }
                result.add(
                    InLessonExerciseItem(
                        promptSub = ex.subPromptText.ifBlank { "Bo‘sh joyni to‘ldiring:" },
                        promptSentenceWithBlank = ex.promptText,
                        fullAudioSentence = ex.audioTextToSpeak?.takeIf { it.isNotBlank() } ?: ex.correctAnswer,
                        options = displayOptions,
                        correctOptionIndex = finalCorrectIdx
                    )
                )
            }
        }

        // 2. Synthesize from current lesson examples (testing the EXACT sentences from Misollar)
        if (result.size < 3) {
            examples.forEachIndexed { index, ex ->
                if (result.size >= 3) return@forEachIndexed
                val targetWord = ex.blueHighlightWord.ifBlank {
                    ex.englishSentence.split(" ").firstOrNull { it.length > 2 } ?: "English"
                }.trimEnd('.', ',', '!', '?')

                val sentenceWithBlank = if (ex.englishSentence.contains(targetWord, ignoreCase = true)) {
                    ex.englishSentence.replaceFirst(Regex(Regex.escape(targetWord), RegexOption.IGNORE_CASE), "___")
                } else {
                    "___: ${ex.englishSentence}"
                }

                val distractorPool = (vocab.map { it.englishWord } + grammar.map { it.exampleEnglish.split(" ").firstOrNull().orEmpty() })
                    .filter { it.isNotBlank() && !it.equals(targetWord, ignoreCase = true) }
                    .distinct()

                val d1 = distractorPool.getOrNull(0) ?: when (index) {
                    0 -> "Wait"
                    1 -> "Stop"
                    else -> "No"
                }
                val d2 = distractorPool.getOrNull(1) ?: when (index) {
                    0 -> "Never"
                    1 -> "Leave"
                    else -> "Wrong"
                }

                val targetPos = index % 3
                val options = when (targetPos) {
                    0 -> listOf(targetWord, d1, d2)
                    1 -> listOf(d1, targetWord, d2)
                    else -> listOf(d1, d2, targetWord)
                }

                result.add(
                    InLessonExerciseItem(
                        promptSub = "Bo‘sh joyni to‘ldiring:",
                        promptSentenceWithBlank = sentenceWithBlank,
                        fullAudioSentence = ex.englishSentence,
                        options = options,
                        correctOptionIndex = targetPos
                    )
                )
            }
        }

        // 3. Fallback contextual exercises matching lesson
        if (result.isEmpty()) {
            val titleWord = sec.titleEnglish.split(Regex("[^a-zA-Z]+")).firstOrNull { it.length > 2 } ?: "TalkMe"
            result.add(
                InLessonExerciseItem(
                    promptSub = "Bo‘sh joyni to‘ldiring:",
                    promptSentenceWithBlank = "Welcome to ___!",
                    fullAudioSentence = "Welcome to TalkMe!",
                    options = listOf("TalkMe", "Stop", "Sleep"),
                    correctOptionIndex = 0
                )
            )
            result.add(
                InLessonExerciseItem(
                    promptSub = "Bo‘sh joyni to‘ldiring:",
                    promptSentenceWithBlank = "I can learn ___ step by step.",
                    fullAudioSentence = "I can learn English step by step.",
                    options = listOf("Spanish", "English", "French"),
                    correctOptionIndex = 1
                )
            )
            result.add(
                InLessonExerciseItem(
                    promptSub = "Bo‘sh joyni to‘ldiring:",
                    promptSentenceWithBlank = "Let's practice with ___.",
                    fullAudioSentence = "Let's practice with TalkMe.",
                    options = listOf("Sleep", "Game", "TalkMe"),
                    correctOptionIndex = 2
                )
            )
        }

        return result.take(3)
    }

    private fun formatVisualWordPill(fullLabel: String, highlightPart: String): CharSequence {
        val builder = SpannableStringBuilder(fullLabel)
        if (highlightPart.isNotBlank()) {
            val start = fullLabel.indexOf(highlightPart, ignoreCase = true)
            if (start >= 0) {
                builder.setSpan(
                    ForegroundColorSpan(Color.parseColor("#D9F99D")),
                    start,
                    start + highlightPart.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return builder
    }

    private fun formatHighlightedEnglishExample(
        sentence: String,
        blueWord: String,
        greenWord: String
    ): CharSequence {
        val builder = SpannableStringBuilder(sentence)
        if (blueWord.isNotBlank()) {
            val start = sentence.indexOf(blueWord, ignoreCase = true)
            if (start >= 0) {
                val end = start + blueWord.length
                builder.setSpan(
                    BackgroundColorSpan(Color.parseColor("#93C5FD")),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                builder.setSpan(
                    ForegroundColorSpan(Color.parseColor("#0F172A")),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        if (greenWord.isNotBlank()) {
            val start = sentence.lastIndexOf(greenWord, ignoreCase = true)
            if (start >= 0) {
                val end = start + greenWord.length
                builder.setSpan(
                    BackgroundColorSpan(Color.parseColor("#BBF7D0")),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                builder.setSpan(
                    ForegroundColorSpan(Color.parseColor("#0F172A")),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return builder
    }

    private fun formatHighlightedUzbekExample(
        sentence: String,
        greenWord: String
    ): CharSequence {
        val builder = SpannableStringBuilder(sentence)
        if (greenWord.isNotBlank()) {
            val start = sentence.indexOf(greenWord, ignoreCase = true)
            if (start >= 0) {
                val end = start + greenWord.length
                builder.setSpan(
                    BackgroundColorSpan(Color.parseColor("#BBF7D0")),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                builder.setSpan(
                    ForegroundColorSpan(Color.parseColor("#0F172A")),
                    start,
                    end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return builder
    }

    private fun formatWrapUpBullet(
        highlightLead: String,
        middleText: String,
        boldExample: String
    ): CharSequence {
        val fullText = "•  $highlightLead$middleText$boldExample"
        val builder = SpannableStringBuilder(fullText)
        // Color bullet and lead in cyan (#38BDF8)
        val leadEnd = (3 + highlightLead.length).coerceAtMost(fullText.length)
        builder.setSpan(
            ForegroundColorSpan(Color.parseColor("#38BDF8")),
            0,
            leadEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return builder
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopTts()
        _binding?.scrollLessonContent?.scrollY?.let { scrollY ->
            viewModel.saveScrollOffset(currentTopicId, currentSectionId, scrollY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
