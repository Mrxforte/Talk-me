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
import com.example.core.ui.ProgressAnimator.animateProgressTo
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

enum class LessonExerciseMode {
    STANDARD,
    READ_ALOUD,
    SELF_WRITE,
    SELF_WRITE_ARTICLES,
    SELF_WRITE_PAIRS,
    SELF_WRITE_THIS_THAT,
    SELF_WRITE_THESE_THOSE,
    SELF_WRITE_I_HAVE,
    SELF_WRITE_HAVE_I_GOT,
    SELF_WRITE_I_HAVE_NOT_GOT,
    SELF_WRITE_NUMBERS,
    SELF_WRITE_I_HAVE_NUMBERS,
    SELF_WRITE_BUT_NO,
    SELF_WRITE_TIGERS_COUNT,
    SELF_WRITE_ORDINALS,
    SELF_WRITE_HERE_IS,
    SELF_WRITE_HERE_ARE,
    SELF_WRITE_THERE_IS,
    SELF_WRITE_MY_ROOM
}

data class InLessonExerciseItem(
    val id: String = "",
    val promptSub: String,
    val promptSentenceWithBlank: String,
    val fullAudioSentence: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val mode: LessonExerciseMode = LessonExerciseMode.STANDARD,
    val explanationUzbek: String = ""
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

data class LessonVocabCardItem(
    val badge: String,
    val englishWord: String,
    val uzbekMeaning: String,
    val exampleEnglish: String,
    val exampleUzbek: String,
    val wordAudioText: String,
    val exampleAudioText: String
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
                    totalSectionsInTopic = (topic?.totalSections ?: 1).coerceAtLeast(1),
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

    fun speakUzbek(text: String) {
        viewModelScope.launch {
            ttsManager.speakUzbek(text)
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

    // In-lesson exercise state
    private var inLessonExercises: List<InLessonExerciseItem> = emptyList()
    private var currentExerciseIndex: Int = 0
    private var selectedOptionIndex: Int = -1
    private var isCurrentExerciseSolved: Boolean = false
    private var readAloudTapCount: Int = 0

    private var cachedExamples: List<LessonExampleDisplayItem> = emptyList()
    private var cachedVisualColumns: List<LessonVisualColumnItem> = emptyList()
    private var cachedVocabCards: List<LessonVocabCardItem> = emptyList()
    private var lastLoadedSectionId: String? = null
    private var lastBoundSectionKey: String? = null

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

        // Audio buttons in Qoida (Pixel-Perfect Reference UI)
        val speakVocabWord1 = View.OnClickListener {
            cachedVocabCards.getOrNull(0)?.let { viewModel.speakEnglish(it.wordAudioText) }
        }
        val speakVocabExample1 = View.OnClickListener {
            cachedVocabCards.getOrNull(0)?.let { viewModel.speakEnglish(it.exampleAudioText) }
        }
        binding.btnSpeakVocabWord1.setOnClickListener(speakVocabWord1)
        binding.cardVocabItem1.setOnClickListener(speakVocabWord1)
        binding.btnSpeakVocabExample1.setOnClickListener(speakVocabExample1)
        binding.layoutVocabExample1.setOnClickListener(speakVocabExample1)

        val speakVocabWord2 = View.OnClickListener {
            cachedVocabCards.getOrNull(1)?.let { viewModel.speakEnglish(it.wordAudioText) }
        }
        val speakVocabExample2 = View.OnClickListener {
            cachedVocabCards.getOrNull(1)?.let { viewModel.speakEnglish(it.exampleAudioText) }
        }
        binding.btnSpeakVocabWord2.setOnClickListener(speakVocabWord2)
        binding.cardVocabItem2.setOnClickListener(speakVocabWord2)
        binding.btnSpeakVocabExample2.setOnClickListener(speakVocabExample2)
        binding.layoutVocabExample2.setOnClickListener(speakVocabExample2)

        val speakVocabWord3 = View.OnClickListener {
            cachedVocabCards.getOrNull(2)?.let { viewModel.speakEnglish(it.wordAudioText) }
        }
        val speakVocabExample3 = View.OnClickListener {
            cachedVocabCards.getOrNull(2)?.let { viewModel.speakEnglish(it.exampleAudioText) }
        }
        binding.btnSpeakVocabWord3.setOnClickListener(speakVocabWord3)
        binding.cardVocabItem3.setOnClickListener(speakVocabWord3)
        binding.btnSpeakVocabExample3.setOnClickListener(speakVocabExample3)
        binding.layoutVocabExample3.setOnClickListener(speakVocabExample3)

        val playDars1WelcomeTts = View.OnClickListener {
            val sec = viewModel.uiState.value.section
            if (sec?.id == com.example.data.local.CurriculumSeedData.DARS_1_SECTION_ID &&
                sec.explanationUzbek.isNotBlank()
            ) {
                viewModel.speakUzbek(sec.explanationUzbek)
            }
        }
        binding.btnSpeakSituation.setOnClickListener(playDars1WelcomeTts)
        binding.btnSpeakMisollarDialogue.setOnClickListener {
            viewModel.speakEnglish(com.example.data.local.CurriculumSeedData.DARS_2_DIALOGUE_FULL_TTS)
        }

        binding.colVisual1.setOnClickListener(speakVocabWord1)
        binding.colVisual2.setOnClickListener(speakVocabWord2)
        binding.colVisual3.setOnClickListener(speakVocabWord3)

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

        binding.btnReadAloudListen.setOnClickListener {
            inLessonExercises.getOrNull(currentExerciseIndex)?.let { ex ->
                viewModel.speakEnglish(ex.fullAudioSentence)
            }
        }
        binding.btnReadAloudTap.setOnClickListener {
            val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return@setOnClickListener
            if (ex.mode == LessonExerciseMode.READ_ALOUD) {
                readAloudTapCount = (readAloudTapCount + 1).coerceAtMost(3)
                if (readAloudTapCount >= 3) {
                    isCurrentExerciseSolved = true
                    selectedOptionIndex = ex.correctOptionIndex
                    viewModel.speakEnglish(ex.fullAudioSentence)
                }
                bindCurrentExercise()
            }
        }
        binding.btnCheckSelfWrite.setOnClickListener { validateSelfWriteExercise() }
        binding.btnCheckSelfWriteArticles.setOnClickListener {
            when (inLessonExercises.getOrNull(currentExerciseIndex)?.mode) {
                LessonExerciseMode.SELF_WRITE_PAIRS -> validatePairSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_THIS_THAT -> validateThisThatSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_THESE_THOSE -> validateTheseThoseSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_I_HAVE -> validateIHaveSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_HAVE_I_GOT -> validateHaveIGotSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_I_HAVE_NOT_GOT -> validateIHaveNotGotSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_NUMBERS -> validateNumbersSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_I_HAVE_NUMBERS -> validateIHaveNumbersSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_BUT_NO -> validateButNoSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_TIGERS_COUNT -> validateTigersCountSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_ORDINALS -> validateOrdinalsSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_HERE_IS -> validateHereIsSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_HERE_ARE -> validateHereAreSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_THERE_IS -> validateThereIsSelfWriteExercise()
                LessonExerciseMode.SELF_WRITE_MY_ROOM -> validateMyRoomSelfWriteExercise()
                else -> validateArticleSelfWriteExercise()
            }
        }

        // Primary Bottom CTA Button
        binding.btnLessonPrimaryCta.setOnClickListener {
            when (currentStepIndex) {
                0 -> setLessonStep(1)
                1 -> setLessonStep(2)
                2 -> {
                    val currentEx = inLessonExercises.getOrNull(currentExerciseIndex)
                    if (!isCurrentExerciseSolved) {
                        when (currentEx?.mode) {
                            LessonExerciseMode.SELF_WRITE -> validateSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_ARTICLES -> validateArticleSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_PAIRS -> validatePairSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_THIS_THAT -> validateThisThatSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_THESE_THOSE -> validateTheseThoseSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_I_HAVE -> validateIHaveSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_HAVE_I_GOT -> validateHaveIGotSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_I_HAVE_NOT_GOT -> validateIHaveNotGotSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_NUMBERS -> validateNumbersSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_I_HAVE_NUMBERS -> validateIHaveNumbersSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_BUT_NO -> validateButNoSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_TIGERS_COUNT -> validateTigersCountSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_ORDINALS -> validateOrdinalsSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_HERE_IS -> validateHereIsSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_HERE_ARE -> validateHereAreSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_THERE_IS -> validateThereIsSelfWriteExercise()
                            LessonExerciseMode.SELF_WRITE_MY_ROOM -> validateMyRoomSelfWriteExercise()
                            else -> Toast.makeText(
                                requireContext(),
                                getString(R.string.lesson_select_answer_first),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else if (currentExerciseIndex < inLessonExercises.lastIndex) {
                        currentExerciseIndex++
                        resetExerciseInteractionState()
                        val nextEx = inLessonExercises[currentExerciseIndex]
                        if (isAutoPreselectLesson()) {
                            selectedOptionIndex = nextEx.correctOptionIndex
                            isCurrentExerciseSolved = true
                        }
                        bindCurrentExercise()
                        updateTopProgressBar()
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
                        val isNewSection = lastLoadedSectionId != sec.id
                        if (isNewSection) {
                            lastLoadedSectionId = sec.id
                            lastBoundSectionKey = null
                            currentStepIndex = 0
                            maxUnlockedStepIndex = 2
                            currentExerciseIndex = 0
                            resetExerciseInteractionState()
                        }
                        bindLessonDataToSteps(state, sec, isNewSection)
                        if (isNewSection) {
                            setLessonStep(currentStepIndex)
                        }

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

    private fun isAutoPreselectLesson(): Boolean {
        val secId = lastLoadedSectionId ?: currentSectionId
        return secId != com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID &&
            secId != com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID
    }

    private fun resetExerciseInteractionState() {
        selectedOptionIndex = -1
        isCurrentExerciseSolved = false
        readAloudTapCount = 0
        binding.etSelfWelcome.text?.clear()
        binding.etSelfWhats.text?.clear()
        binding.etSelfMy.text?.clear()
        binding.etSelfName.text?.clear()
        binding.etSelfBye.text?.clear()
        binding.etArticleItem1.text?.clear()
        binding.etArticleItem2.text?.clear()
        binding.etArticleItem3.text?.clear()
    }

    private fun bindLessonDataToSteps(
        state: LessonDetailUiState,
        sec: LessonSectionModel,
        isNewSection: Boolean
    ) {
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
        val headingTitle = when (sec.id) {
            com.example.data.local.CurriculumSeedData.DARS_1_SECTION_ID -> "Welcome to TalkMe"
            com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID -> "Lera and talkme"
            com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID -> "A cup, an apple"
            com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID -> "A book and a cup"
            com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID -> "This and that"
            com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID -> "These and those"
            com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID -> "I have a book"
            com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID -> "Have I got a book?"
            com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID -> "I have not got a book"
            com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID -> "One, two, three"
            com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID -> "I have three books"
            com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID -> "I have a book, but I have no cups"
            com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID -> "Counting the Tigers"
            com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID -> "The first tiger"
            com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID -> "Here is a boy"
            com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID -> "Here are two boys"
            com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID -> "There is a boy"
            com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID -> "There are two boys"
            "sec_01_01" -> "Xush kelibsiz!"
            else -> sec.titleUzbek
                .replace(Regex("^\\d+-Dars:\\s*", RegexOption.IGNORE_CASE), "")
                .substringBefore("—")
                .substringBefore("-")
                .trim()
                .ifBlank { "Dars qoidasi" }
        }
        val headingSubtitle = when (sec.id) {
            com.example.data.local.CurriculumSeedData.DARS_1_SECTION_ID -> "TalkMe ilovasiga xush kelibsiz!"
            com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID -> "9 ta oddiy ibora va ilk suhbat"
            com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID -> "a / an artikllari — bitta narsa"
            com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID -> "and (“va”) bog‘lovchisi — ikkita narsa"
            com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID -> "this (“bu”) va that (“o‘sha”) — bitta narsa"
            com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID -> "these (“bular”) va those (“o‘shalar”) — ko‘p narsa (-s)"
            com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID -> "have (“bor bo‘lmoq”) va I have (“menda bor”) — darak shakli"
            com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID -> "Have I got ...? — savol shakli va qisqa javoblar"
            com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID -> "I have not got / I haven’t got — inkor shakli"
            com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID -> "1 dan 10 gacha sonlar va ko‘p narsa (-s)"
            com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID -> "sonlar + I have — bitta va ko‘p narsani sanash"
            com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID -> "but (“lekin”) va no (“yo‘q”) — bor va yo‘q juftligi"
            com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID -> "sanoq sonlar (“nechta?”) — yo‘lbarslarni sanaymiz"
            com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID -> "tartib sonlar (“nechanchi?”) — the first .. the tenth"
            com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID -> "here (“mana / mana bu”) + is — bitta yaqin narsa"
            com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID -> "here (“mana / mana bu”) + are — ko‘p yaqin narsa (-s)"
            com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID -> "there (“u yerda / bor”) + is — bitta narsa bor"
            com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID -> "there (“u yerda / bor”) + are — ko‘p narsa bor (-s)"
            "sec_01_01" -> "TalkMe bilan ilk qadam"
            else -> "TalkMe bilan bosqichma-bosqich o‘rganamiz"
        }
        binding.tvLessonHeadingTitle.text = headingTitle
        binding.tvLessonHeadingSubtitle.text = headingSubtitle

        // Optional Contextual Photograph
        val photoRes = getLessonSituationPhotoRes(sec)
        if (photoRes != null) {
            binding.cardLessonHeroPhoto.visibility = View.VISIBLE
            binding.ivLessonHeroPhoto.setImageResource(photoRes)
        } else {
            binding.cardLessonHeroPhoto.visibility = View.GONE
        }

        // Context Panel: Bugungi vaziyat / Tushuntirish (TTS faqat Dars 1 TalkMe kirish matni uchun; o‘zbekcha qoida va wrap-upda TTS ishlatilmaydi)
        val (sitTitle, sitBody) = getLessonSituationText(sec)
        binding.tvSituationTitle.text = sitTitle
        binding.tvSituationBody.text = sitBody
        val isTalkMeWelcomeScript = sec.id == com.example.data.local.CurriculumSeedData.DARS_1_SECTION_ID
        binding.btnSpeakSituation.visibility = if (isTalkMeWelcomeScript) View.VISIBLE else View.GONE
        binding.layoutLessonHint.isClickable = false
        if (isTalkMeWelcomeScript) {
            binding.layoutLessonSituation.setOnClickListener {
                if (sec.explanationUzbek.isNotBlank()) {
                    viewModel.speakUzbek(sec.explanationUzbek)
                }
            }
            binding.layoutLessonSituation.isClickable = true
        } else {
            binding.layoutLessonSituation.setOnClickListener(null)
            binding.layoutLessonSituation.isClickable = false
        }

        // Section Heading
        binding.tvQoidaSectionHeading.text = when (sec.id) {
            com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID -> "9 ta oddiy ibora"
            com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID -> "a va an — 10 ta asosiy so‘z"
            com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID -> "and + 7 ta asosiy juftlik"
            com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID -> "this / that + 6 ta asosiy misol"
            com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID -> "these / those + 6 ta ko‘plik misoli"
            com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID -> "I have + 8 ta asosiy gap"
            com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID -> "Have I got + 7 ta savol va javob"
            com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID -> "I have not got + 7 ta inkor shakli"
            com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID -> "1 dan 10 gacha sonlar"
            com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID -> "I have + son + ot"
            com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID -> "but (“lekin”) va no (“yo‘q”)"
            com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID -> "one tiger .. ten tigers"
            com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID -> "the first .. the tenth tiger"
            com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID -> "Here is + a/an + ot"
            com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID -> "Here are + son + ot(-s)"
            com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID -> "There is + a/an + ot"
            com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID -> "There are + son + ot(-s)"
            else -> "Tinglang va tushuning"
        }
        binding.tvQoidaSectionSubheading.text = when (sec.id) {
            com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID -> "Har bir iborani tinglab eslab qoling."
            com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID -> "Undosh bilan a, unli (a, e, i, o, u) bilan an qo‘yiladi."
            com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID -> "Har bir narsa oldida o‘zining a yoki an artikli saqlanadi."
            com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID -> "Yaqin uchun this, uzoqroq uchun that. A/an qo‘yilmaydi."
            com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID -> "Yaqin ko‘p uchun these, uzoq ko‘p uchun those. Oxiriga -s qo‘shiladi."
            com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID -> "“have” — bor bo‘lmoq, “I have” — menda bor. Bitta narsa oldidan a/an qo‘yiladi."
            com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID -> "Savol uchun Have I got + a/an + ot? Qisqa javob: Yes, I have. / No, I have not."
            com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID -> "Inkor uchun have dan keyin not va got qo‘yiladi: I have not got / I haven’t got."
            com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID -> "Har bir sonni bosib talaffuzini tinglang. Ko‘p narsa oxiriga -s qo‘shiladi."
            com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID -> "Bitta → a/an/one (-s yo‘q). Ko‘p → son + -s (I have two books)."
            com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID -> "Biri bor, biri yo‘q: I have ___, but I have no ___ (no bilan a/an qo‘yilmaydi)."
            com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID -> "Sanoq — “nechta?” Bitta bo‘lsa tiger, ko‘p bo‘lsa oxiriga -s (tigers)."
            com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID -> "Tartib — “nechanchi?” Oldida the qo‘yiladi (the first, the second, the third, -th)."
            com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID -> "Here — “mana / mana bu” (yaqin). Bitta narsa uchun Here is + a/an."
            com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID -> "Ko‘p yaqin narsa uchun Here are + son + ot(-s) ishlatiladi."
            com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID -> "There — “u yerda / bor”. Bitta narsa borligini aytishda There is + a/an."
            com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID -> "Ko‘p narsa borligini aytishda There are + son + ot(-s) ishlatiladi."
            else -> "So‘zlarni vaziyat bilan eslab qoling."
        }

        // Stacked Vocabulary Cards (01..03 static + 04..N dynamic)
        cachedVocabCards = buildVocabCardItems(sec, state.vocabulary, state.grammarRules)
        val vc1 = cachedVocabCards[0]
        val vc2 = cachedVocabCards[1]
        val vc3 = cachedVocabCards[2]

        binding.tvVocabBadge1.text = vc1.badge
        binding.tvVocabWord1.text = vc1.englishWord
        binding.tvVocabMeaning1.text = vc1.uzbekMeaning
        binding.tvVocabExampleEn1.text = vc1.exampleEnglish
        binding.tvVocabExampleUz1.text = vc1.exampleUzbek

        binding.tvVocabBadge2.text = vc2.badge
        binding.tvVocabWord2.text = vc2.englishWord
        binding.tvVocabMeaning2.text = vc2.uzbekMeaning
        binding.tvVocabExampleEn2.text = vc2.exampleEnglish
        binding.tvVocabExampleUz2.text = vc2.exampleUzbek

        binding.tvVocabBadge3.text = vc3.badge
        binding.tvVocabWord3.text = vc3.englishWord
        binding.tvVocabMeaning3.text = vc3.uzbekMeaning
        binding.tvVocabExampleEn3.text = vc3.exampleEnglish
        binding.tvVocabExampleUz3.text = vc3.exampleUzbek

        renderExtraQoidaVocabCards(cachedVocabCards.drop(3))

        binding.tvLessonHintText.text = "Audioni tinglang, keyin birga takrorlaymiz."

        // 2. MISOLLAR DATA (including Dialogue for Dars 2 & dynamic examples)
        cachedExamples = buildExampleItems(sec, state.vocabulary, state.grammarRules)
        renderMisollarDialogue(sec)
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
        renderExtraExampleCards(cachedExamples.drop(3))

        // 3. MASHQLAR DATA — rebuild only when section or dataset changes so shuffled options remain stable during interactions
        val bindKey = "${sec.id}:${state.exercises.size}:${state.vocabulary.size}:${state.grammarRules.size}"
        if (isNewSection || lastBoundSectionKey != bindKey) {
            lastBoundSectionKey = bindKey
            inLessonExercises = buildInLessonExercises(
                state.exercises,
                state.vocabulary,
                state.grammarRules,
                sec,
                cachedExamples
            )
            if (currentExerciseIndex >= inLessonExercises.size) {
                currentExerciseIndex = 0
            }
            val activeEx = inLessonExercises[currentExerciseIndex]
            if (isAutoPreselectLesson()) {
                selectedOptionIndex = activeEx.correctOptionIndex
                isCurrentExerciseSolved = true
            } else {
                selectedOptionIndex = -1
                isCurrentExerciseSolved = false
            }
        }
        bindCurrentExercise()

        // 4. WRAP-UP DATA
        when (sec.id) {
            com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Hello / Hi",
                    middleText = " + tanishuv:\n    ",
                    boldExample = "What’s your name? — My name is Lera."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Sorry / Thank you",
                    middleText = " + xayrlashuv:\n    ",
                    boldExample = "I love English! Good bye!"
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_2_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Undosh harf",
                    middleText = " oldidan → a:\n    ",
                    boldExample = "a cup, a book, a cat, a pen, a dog."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Unli (a, e, i, o, u)",
                    middleText = " oldidan → an:\n    ",
                    boldExample = "an apple, an egg, an orange, an umbrella, an elephant."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_3_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "and bog‘lovchisi",
                    middleText = " = va:\n    ",
                    boldExample = "a book and a cup, an apple and an egg."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Har bir narsa",
                    middleText = " o‘z a/an bilan:\n    ",
                    boldExample = "Unli → an, undosh → a (hech qachon tushirib qoldirmang)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_4_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "this / that",
                    middleText = " (yaqin / uzoqroq):\n    ",
                    boldExample = "this book (bu kitob), that cup (o‘sha piyola)."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "a / an qo‘yilmaydi",
                    middleText = ":\n    ",
                    boldExample = "this book ✅ (this a book ❌), that apple ✅ (that an apple ❌)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_5_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "these / those",
                    middleText = " (ko‘p narsa + -s):\n    ",
                    boldExample = "these books (bular), those cups (o‘shalar)."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Ko‘plik va a/an",
                    middleText = ":\n    ",
                    boldExample = "Oxiriga -s qo‘shiladi (books, apples) va a/an qo‘yilmaydi!"
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_6_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "have / I have",
                    middleText = ":\n    ",
                    boldExample = "have — bor bo‘lmoq, I have — menda bor."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "I have + a/an + ot",
                    middleText = ":\n    ",
                    boldExample = "I have a book (undosh), I have an apple (unli)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_7_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Have I got ...?",
                    middleText = " (savol):\n    ",
                    boldExample = "Have I got a book? — Menda kitob bormi?"
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Qisqa javoblar",
                    middleText = ":\n    ",
                    boldExample = "Yes, I have. (Ha, bor.) · No, I have not. (Yo‘q, yo‘q.)"
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_8_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "I have not got",
                    middleText = " (inkor):\n    ",
                    boldExample = "I have not got a book / I haven’t got a book."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "3 ta shakl",
                    middleText = ":\n    ",
                    boldExample = "I have a book · Have I got a book? · I have not got a book."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_9_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "1 dan 10 gacha",
                    middleText = ":\n    ",
                    boldExample = "one, two, three, four, five, six, seven, eight, nine, ten."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Bitta / Ko‘p (-s)",
                    middleText = ":\n    ",
                    boldExample = "a book, an apple → two books, three apples (oxiriga -s)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_10_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Bitta / Ko‘p (-s)",
                    middleText = ":\n    ",
                    boldExample = "I have a book · I have two books (son + -s)."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "one bilan -s yo‘q",
                    middleText = ":\n    ",
                    boldExample = "I have one book ✅ (I have one books ❌)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_11_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "but / no",
                    middleText = ":\n    ",
                    boldExample = "but — lekin · no — yo‘q (no dan keyin a/an yo‘q)."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Qolip",
                    middleText = ":\n    ",
                    boldExample = "I have a book, but I have no cups."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_12_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Sanoq (nechta?)",
                    middleText = ":\n    ",
                    boldExample = "one tiger, two tigers, three tigers .. ten tigers."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Bitta / Ko‘p (-s)",
                    middleText = ":\n    ",
                    boldExample = "tiger (bitta) → tigers (2+ yo‘lbars)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_13_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Tartib (nechanchi?)",
                    middleText = ":\n    ",
                    boldExample = "the first, the second, the third, fourth..tenth tiger."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Sanoq vs Tartib",
                    middleText = ":\n    ",
                    boldExample = "three tigers (nechta?) · the third tiger (nechanchi?)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_14_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Here (yaqin)",
                    middleText = ":\n    ",
                    boldExample = "Here — mana / mana bu (qo‘lingizda, yoningizda)."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Bitta narsa",
                    middleText = ":\n    ",
                    boldExample = "Here is a boy. · Here is an apple."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_15_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Ko‘p narsa",
                    middleText = ":\n    ",
                    boldExample = "Here are two boys. · Here are three girls."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Farqi",
                    middleText = ":\n    ",
                    boldExample = "Bitta → Here is a boy · Ko‘p → Here are two boys."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_16_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "There (uzoq/bor)",
                    middleText = ":\n    ",
                    boldExample = "There — u yerda / bor (bitta: There is a boy)."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "Here vs There",
                    middleText = ":\n    ",
                    boldExample = "Here is (mana, yaqin) · There is (u yerda bor)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_17_WRAP_UP_UZBEK
            }
            com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID -> {
                binding.tvWrapUpBullet1.text = formatWrapUpBullet(
                    highlightLead = "Ko‘p narsa bor",
                    middleText = ":\n    ",
                    boldExample = "There are two boys. · There are five tigers."
                )
                binding.tvWrapUpBullet2.text = formatWrapUpBullet(
                    highlightLead = "4 ta shakl",
                    middleText = ":\n    ",
                    boldExample = "Here is / Here are (yaqin) · There is / There are (bor)."
                )
                binding.tvWrapUpExtendedSummary.visibility = View.VISIBLE
                binding.tvWrapUpExtendedSummary.text =
                    com.example.data.local.CurriculumSeedData.DARS_18_WRAP_UP_UZBEK
            }
            else -> {
                binding.tvWrapUpExtendedSummary.visibility = View.GONE
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
        }
    }

    private fun renderExtraQoidaVocabCards(extraItems: List<LessonVocabCardItem>) {
        val container = binding.layoutExtraQoidaVocab
        container.removeAllViews()
        if (extraItems.isEmpty()) {
            container.visibility = View.GONE
            return
        }
        container.visibility = View.VISIBLE
        val density = resources.displayMetrics.density
        val cardTemplate = binding.cardVocabItem1
        val inflater = LayoutInflater.from(requireContext())
        extraItems.forEach { item ->
            val card = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.VERTICAL
                setBackgroundResource(R.drawable.bg_lesson_vocab_stacked_card)
                val pad = (13 * density).toInt()
                setPadding(pad, pad, pad, pad)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (10 * density).toInt()
                }
            }

            val topRow = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
            }
            val badgeFrame = android.widget.FrameLayout(requireContext()).apply {
                setBackgroundResource(R.drawable.bg_lesson_vocab_badge)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (34 * density).toInt(),
                    (34 * density).toInt()
                )
            }
            val badgeTv = android.widget.TextView(requireContext()).apply {
                text = item.badge
                gravity = android.view.Gravity.CENTER
                setTextColor(Color.parseColor("#38BDF8"))
                textSize = 14f
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            badgeFrame.addView(badgeTv)
            topRow.addView(badgeFrame)

            val sep = View(requireContext()).apply {
                setBackgroundColor(Color.parseColor("#1E3A8A"))
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (1 * density).toInt(),
                    (28 * density).toInt()
                ).apply {
                    marginStart = (10 * density).toInt()
                    marginEnd = (10 * density).toInt()
                }
            }
            topRow.addView(sep)

            val textCol = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.VERTICAL
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }
            textCol.addView(android.widget.TextView(requireContext()).apply {
                text = item.englishWord
                setTextColor(Color.WHITE)
                textSize = 16.5f
                setTypeface(null, android.graphics.Typeface.BOLD)
            })
            textCol.addView(android.widget.TextView(requireContext()).apply {
                text = item.uzbekMeaning
                setTextColor(Color.parseColor("#94A3B8"))
                textSize = 13f
            })
            topRow.addView(textCol)

            val speakWordBtn = android.widget.ImageButton(requireContext()).apply {
                setBackgroundResource(R.drawable.bg_lesson_speaker_btn)
                setImageResource(R.drawable.ic_volume_up)
                setColorFilter(Color.WHITE)
                contentDescription = "Speak ${item.englishWord}"
                val p = (10 * density).toInt()
                setPadding(p, p, p, p)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (48 * density).toInt(),
                    (48 * density).toInt()
                )
                setOnClickListener { viewModel.speakEnglish(item.wordAudioText) }
            }
            topRow.addView(speakWordBtn)
            card.addView(topRow)

            val hDiv = View(requireContext()).apply {
                setBackgroundColor(Color.parseColor("#162847"))
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (1 * density).toInt()
                ).apply {
                    topMargin = (10 * density).toInt()
                    bottomMargin = (10 * density).toInt()
                }
            }
            card.addView(hDiv)

            val exRow = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
                setOnClickListener { viewModel.speakEnglish(item.exampleAudioText) }
            }
            val exCol = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.VERTICAL
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }
            exCol.addView(android.widget.TextView(requireContext()).apply {
                text = item.exampleEnglish
                setTextColor(Color.parseColor("#E2E8F0"))
                textSize = 13.5f
                setTypeface(null, android.graphics.Typeface.BOLD)
            })
            exCol.addView(android.widget.TextView(requireContext()).apply {
                text = item.exampleUzbek
                setTextColor(Color.parseColor("#94A3B8"))
                textSize = 12.5f
            })
            exRow.addView(exCol)
            val speakExIcon = android.widget.ImageView(requireContext()).apply {
                setImageResource(R.drawable.ic_volume_up)
                setColorFilter(Color.parseColor("#60A5FA"))
                contentDescription = "Speak example"
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (22 * density).toInt(),
                    (22 * density).toInt()
                )
            }
            exRow.addView(speakExIcon)
            card.addView(exRow)

            card.setOnClickListener { viewModel.speakEnglish(item.wordAudioText) }
            container.addView(card)
        }
        // Keep unused parameters cleanly scoped
        if (false) {
            inflater.hashCode()
            cardTemplate.hashCode()
        }
    }

    private fun renderMisollarDialogue(sec: LessonSectionModel) {
        val isDars2 = sec.id == com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID
        if (!isDars2) {
            binding.cardMisollarDialogue.visibility = View.GONE
            binding.tvMisollarExamplesLabel.visibility =
                if (sec.id == com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID ||
                    sec.id == com.example.data.local.CurriculumSeedData.DARS_19_SECTION_ID
                ) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            return
        }
        binding.cardMisollarDialogue.visibility = View.VISIBLE
        binding.tvMisollarExamplesLabel.visibility = View.VISIBLE
        val container = binding.layoutDialogueLinesContainer
        container.removeAllViews()
        val density = resources.displayMetrics.density

        com.example.data.local.CurriculumSeedData.DARS_2_DIALOGUE_LINES.forEach { line ->
            val row = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
                val padH = (10 * density).toInt()
                val padV = (8 * density).toInt()
                setPadding(padH, padV, padH, padV)
                setBackgroundResource(
                    if (line.isLearner) R.drawable.bg_lesson_feedback_success else R.drawable.bg_lesson_vocab_stacked_card
                )
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (6 * density).toInt()
                }
                setOnClickListener {
                    if (line.text.contains("tanishganimdan")) {
                        viewModel.speakEnglish("Nice to meet you")
                    } else {
                        viewModel.speakEnglish(line.text)
                    }
                }
            }
            val tvSpeaker = android.widget.TextView(requireContext()).apply {
                text = "${line.speaker}:"
                setTextColor(if (line.isLearner) Color.parseColor("#4ADE80") else Color.parseColor("#38BDF8"))
                textSize = 13f
                setTypeface(null, android.graphics.Typeface.BOLD)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (74 * density).toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            val tvLine = android.widget.TextView(requireContext()).apply {
                text = line.text
                setTextColor(Color.WHITE)
                textSize = 14f
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }
            row.addView(tvSpeaker)
            row.addView(tvLine)
            container.addView(row)
        }
        binding.tvMisollarDialogueNote.visibility = View.VISIBLE
        binding.tvMisollarDialogueNote.text =
            com.example.data.local.CurriculumSeedData.DARS_2_DIALOGUE_OUTRO_UZBEK
    }

    private fun renderExtraExampleCards(extraExamples: List<LessonExampleDisplayItem>) {
        val container = binding.layoutExtraExamples
        container.removeAllViews()
        if (extraExamples.isEmpty()) {
            container.visibility = View.GONE
            return
        }
        container.visibility = View.VISIBLE
        val density = resources.displayMetrics.density
        extraExamples.forEachIndexed { idx, ex ->
            val card = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
                setBackgroundResource(R.drawable.bg_lesson_ice_card)
                val padH = (12 * density).toInt()
                val padV = (12 * density).toInt()
                setPadding(padH, padV, padH, padV)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = (12 * density).toInt()
                }
                setOnClickListener { viewModel.speakEnglish(ex.englishSentence) }
            }

            val icon = android.widget.ImageView(requireContext()).apply {
                setImageResource(R.drawable.ic_lesson_book_cyan)
                setColorFilter(Color.parseColor("#0F172A"))
                contentDescription = "Example ${idx + 4}"
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (30 * density).toInt(),
                    (30 * density).toInt()
                )
            }
            card.addView(icon)

            val contentCol = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.VERTICAL
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
                ).apply {
                    marginStart = (12 * density).toInt()
                }
            }

            val topRow = android.widget.LinearLayout(requireContext()).apply {
                orientation = android.widget.LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER_VERTICAL
            }
            val tvEn = android.widget.TextView(requireContext()).apply {
                text = formatHighlightedEnglishExample(ex.englishSentence, ex.blueHighlightWord, ex.greenHighlightWord)
                setTextColor(Color.parseColor("#0F172A"))
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }
            val speakBtn = android.widget.ImageButton(requireContext()).apply {
                setBackgroundResource(R.drawable.bg_lesson_speaker_btn)
                setImageResource(R.drawable.ic_volume_up)
                setColorFilter(Color.WHITE)
                contentDescription = "Speak example ${idx + 4}"
                val p = (10 * density).toInt()
                setPadding(p, p, p, p)
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    (48 * density).toInt(),
                    (48 * density).toInt()
                ).apply {
                    marginStart = (8 * density).toInt()
                }
                setOnClickListener { viewModel.speakEnglish(ex.englishSentence) }
            }
            topRow.addView(tvEn)
            topRow.addView(speakBtn)
            contentCol.addView(topRow)

            val divider = View(requireContext()).apply {
                setBackgroundColor(Color.parseColor("#B4D2F0"))
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (1 * density).toInt()
                ).apply {
                    topMargin = (7 * density).toInt()
                    bottomMargin = (7 * density).toInt()
                }
            }
            contentCol.addView(divider)

            val tvUz = android.widget.TextView(requireContext()).apply {
                text = formatHighlightedUzbekExample(ex.uzbekSentence, ex.uzbekGreenWord)
                setTextColor(Color.parseColor("#0F172A"))
                textSize = 14.5f
            }
            contentCol.addView(tvUz)

            card.addView(contentCol)
            container.addView(card)
        }
    }

    private fun validateSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val w1 = binding.etSelfWelcome.text.toString().trim()
        val w2 = binding.etSelfWhats.text.toString().trim()
        val w3 = binding.etSelfMy.text.toString().trim()
        val w4 = binding.etSelfName.text.toString().trim()
        val w5 = binding.etSelfBye.text.toString().trim()

        val okWelcome = w1.equals("welcome", ignoreCase = true)
        val normWhats = w2.lowercase().replace("’", "'").replace("'", "")
        val okWhats = normWhats == "whats"
        val okMy = w3.equals("my", ignoreCase = true)
        val okName = w4.length >= 2 && w4.any { it.isLetter() }
        val okBye = w5.equals("bye", ignoreCase = true)

        if (okWelcome && okWhats && okMy && okName && okBye) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("Welcome to TalkMe! What's your name? My name is $w4. Good bye!")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0 // triggers red feedback banner cleanly
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text =
                "Bo‘sh joylarni to‘g‘ri kiriting: Welcome · What’s · My · [Ismingiz] · bye"
        }
    }

    private fun validateArticleSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim(),
            binding.etArticleItem2.text.toString().trim(),
            binding.etArticleItem3.text.toString().trim()
        )
        val phraseRegex = Regex("^(a|an)\\s+([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val allValid = inputs.all { raw ->
            val match = phraseRegex.matchEntire(raw) ?: return@all false
            val article = match.groupValues[1].lowercase()
            val noun = match.groupValues[2].lowercase()
            val startsWithVowel = noun.first() in setOf('a', 'e', 'i', 'o', 'u')
            if (startsWithVowel) article == "an" else article == "a"
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(inputs.joinToString(". "))
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_article_error)
        }
    }

    private fun validatePairSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " "),
            binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ")
        )
        val pairRegex = Regex("^(a|an)\\s+([a-zA-Z]{2,})\\s+and\\s+(a|an)\\s+([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val allValid = inputs.all { raw ->
            val match = pairRegex.matchEntire(raw) ?: return@all false
            val art1 = match.groupValues[1].lowercase()
            val noun1 = match.groupValues[2].lowercase()
            val art2 = match.groupValues[3].lowercase()
            val noun2 = match.groupValues[4].lowercase()
            val ok1 = if (noun1.first() in vowels) art1 == "an" else art1 == "a"
            val ok2 = if (noun2.first() in vowels) art2 == "an" else art2 == "a"
            ok1 && ok2
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(inputs.joinToString(". "))
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_pair_error)
        }
    }

    private fun validateThisThatSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val input1 = binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " ")
        val input2 = binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ")
        val thisRegex = Regex("^this\\s+(?!a\\s+|an\\s+)([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val thatRegex = Regex("^that\\s+(?!a\\s+|an\\s+)([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val allValid = thisRegex.matches(input1) && thatRegex.matches(input2)

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("$input1. $input2.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_this_that_error)
        }
    }

    private fun validateTheseThoseSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val input1 = binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " ")
        val input2 = binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ")
        val theseRegex = Regex("^these\\s+(?!a\\s+|an\\s+)([a-zA-Z]{2,}s)$", RegexOption.IGNORE_CASE)
        val thoseRegex = Regex("^those\\s+(?!a\\s+|an\\s+)([a-zA-Z]{2,}s)$", RegexOption.IGNORE_CASE)
        val allValid = theseRegex.matches(input1) && thoseRegex.matches(input2)

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("$input1. $input2.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_these_those_error)
        }
    }

    private fun validateIHaveSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem3.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.')
        )
        val phraseRegex = Regex("^i\\s+have\\s+(a|an)\\s+([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val allValid = inputs.all { raw ->
            val match = phraseRegex.matchEntire(raw) ?: return@all false
            val article = match.groupValues[1].lowercase()
            val noun = match.groupValues[2].lowercase()
            if (noun.first() in vowels) article == "an" else article == "a"
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(inputs.joinToString(". ") + ".")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_i_have_error)
        }
    }

    private fun validateHaveIGotSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " "),
            binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ")
        )
        val questionRegex = Regex("^have\\s+i\\s+got\\s+(a|an)\\s+([a-zA-Z]{2,})\\??$", RegexOption.IGNORE_CASE)
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val allValid = inputs.all { raw ->
            val match = questionRegex.matchEntire(raw) ?: return@all false
            val article = match.groupValues[1].lowercase()
            val noun = match.groupValues[2].lowercase()
            if (noun.first() in vowels) article == "an" else article == "a"
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            val spoken = inputs.joinToString(" ") { q ->
                val clean = q.trimEnd('?')
                "$clean?"
            }
            viewModel.speakEnglish(spoken)
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_have_i_got_error)
        }
    }

    private fun validateIHaveNotGotSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem3.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.')
        )
        val negRegex = Regex("^(?:i\\s+have\\s+not\\s+got|i\\s+haven['’]t\\s+got)\\s+(a|an)\\s+([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val allValid = inputs.all { raw ->
            val match = negRegex.matchEntire(raw) ?: return@all false
            val article = match.groupValues[1].lowercase()
            val noun = match.groupValues[2].lowercase()
            if (noun.first() in vowels) article == "an" else article == "a"
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(inputs.joinToString(". ") + ".")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_i_have_not_got_error)
        }
    }

    private fun validateNumbersSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val expectedNouns = listOf("books", "cups", "pens")
        val numberMap = mapOf(
            "2" to "two", "two" to "two",
            "3" to "three", "three" to "three",
            "4" to "four", "four" to "four",
            "5" to "five", "five" to "five",
            "6" to "six", "six" to "six",
            "7" to "seven", "seven" to "seven",
            "8" to "eight", "eight" to "eight",
            "9" to "nine", "nine" to "nine",
            "10" to "ten", "ten" to "ten"
        )
        val rawInputs = listOf(
            binding.etArticleItem1.text.toString().trim().lowercase().replace(Regex("\\s+"), " "),
            binding.etArticleItem2.text.toString().trim().lowercase().replace(Regex("\\s+"), " "),
            binding.etArticleItem3.text.toString().trim().lowercase().replace(Regex("\\s+"), " ")
        )
        val resolvedPhrases = rawInputs.mapIndexedNotNull { idx, raw ->
            val expectedNoun = expectedNouns[idx]
            val tokens = raw.split(" ").filter { it.isNotBlank() }
            val numWord = tokens.firstOrNull()?.let { numberMap[it] } ?: return@mapIndexedNotNull null
            val validTokenCount = tokens.size == 1 || (tokens.size == 2 && tokens[1] == expectedNoun)
            if (validTokenCount) "$numWord $expectedNoun" else null
        }

        if (resolvedPhrases.size == 3) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(resolvedPhrases.joinToString(". ") + ".")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_numbers_error)
        }
    }

    private fun validateIHaveNumbersSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem3.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.')
        )
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val pluralNumbers = setOf("2", "two", "3", "three", "4", "four", "5", "five", "6", "six", "7", "seven", "8", "eight", "9", "nine", "10", "ten")
        val regex = Regex("^i\\s+have\\s+(a|an|one|1|two|2|three|3|four|4|five|5|six|6|seven|7|eight|8|nine|9|ten|10)\\s+([a-zA-Z]{2,})$", RegexOption.IGNORE_CASE)
        val allValid = inputs.all { raw ->
            val match = regex.matchEntire(raw) ?: return@all false
            val qty = match.groupValues[1].lowercase()
            val noun = match.groupValues[2].lowercase()
            when (qty) {
                "a" -> noun.first() !in vowels && !noun.endsWith("s")
                "an" -> noun.first() in vowels && !noun.endsWith("s")
                "one", "1" -> !noun.endsWith("s")
                in pluralNumbers -> noun.endsWith("s") && noun.length >= 3
                else -> false
            }
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(inputs.joinToString(". ") + ".")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_i_have_num_error)
        }
    }

    private fun validateButNoSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val inputs = listOf(
            binding.etArticleItem1.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.'),
            binding.etArticleItem2.text.toString().trim().replace(Regex("\\s+"), " ").trimEnd('.')
        )
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        val pluralNumbers = setOf("2", "two", "3", "three", "4", "four", "5", "five", "6", "six", "7", "seven", "8", "eight", "9", "nine", "10", "ten")
        val regex = Regex(
            "^i\\s+have\\s+(a|an|one|1|two|2|three|3|four|4|five|5|six|6|seven|7|eight|8|nine|9|ten|10)\\s+([a-zA-Z]{2,})\\s*,?\\s+but\\s+i\\s+have\\s+no\\s+(?!a\\s+|an\\s+)([a-zA-Z]{2,})$",
            RegexOption.IGNORE_CASE
        )
        val allValid = inputs.all { raw ->
            val match = regex.matchEntire(raw) ?: return@all false
            val qty = match.groupValues[1].lowercase()
            val posNoun = match.groupValues[2].lowercase()
            val negNoun = match.groupValues[3].lowercase()
            val posOk = when (qty) {
                "a" -> posNoun.first() !in vowels && !posNoun.endsWith("s")
                "an" -> posNoun.first() in vowels && !posNoun.endsWith("s")
                "one", "1" -> !posNoun.endsWith("s")
                in pluralNumbers -> posNoun.endsWith("s") && posNoun.length >= 3
                else -> false
            }
            val negOk = negNoun.endsWith("s") && negNoun.length >= 3
            posOk && negOk
        }

        if (allValid) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish(inputs.joinToString(". ") + ".")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_but_no_error)
        }
    }

    private fun validateTigersCountSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val pluralMap = mapOf(
            "2" to "two", "two" to "two",
            "3" to "three", "three" to "three",
            "4" to "four", "four" to "four",
            "5" to "five", "five" to "five",
            "6" to "six", "six" to "six",
            "7" to "seven", "seven" to "seven",
            "8" to "eight", "eight" to "eight",
            "9" to "nine", "nine" to "nine",
            "10" to "ten", "ten" to "ten"
        )
        val raw1 = binding.etArticleItem1.text.toString().trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.')
        val raw2 = binding.etArticleItem2.text.toString().trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.')
        val raw3 = binding.etArticleItem3.text.toString().trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.')

        fun parsePluralTigers(raw: String): String? {
            val tokens = raw.split(" ").filter { it.isNotBlank() }
            val num = tokens.firstOrNull()?.let { pluralMap[it] } ?: return null
            val ok = tokens.size == 1 || (tokens.size == 2 && tokens[1] == "tigers")
            return if (ok) "$num tigers" else null
        }

        fun parseSingleTiger(raw: String): String? {
            val tokens = raw.split(" ").filter { it.isNotBlank() }
            val first = tokens.firstOrNull() ?: return null
            if (first !in setOf("one", "1", "a")) return null
            val word = if (first == "a") "a" else "one"
            val ok = tokens.size == 1 || (tokens.size == 2 && tokens[1] == "tiger")
            return if (ok) "$word tiger" else null
        }

        val p1 = parsePluralTigers(raw1)
        val p2 = parsePluralTigers(raw2)
        val p3 = parseSingleTiger(raw3)

        if (p1 != null && p2 != null && p3 != null) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("$p1. $p2. $p3.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_tigers_error)
        }
    }

    private fun validateOrdinalsSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val raw1 = binding.etArticleItem1.text.toString().trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.')
        val raw2 = binding.etArticleItem2.text.toString().trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.')
        val raw3 = binding.etArticleItem3.text.toString().trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.')

        val ok1 = raw1 == "first" || raw1 == "the first tiger"
        val ok2 = raw2 == "second" || raw2 == "the second tiger"
        val ok3 = raw3 == "third" || raw3 == "the third tiger"

        if (ok1 && ok2 && ok3) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("The first tiger. The second tiger. The third tiger.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_ordinal_error)
        }
    }

    private fun parseSingularArticlePhrase(
        rawInput: String,
        requiredPrefix: String,
        forbiddenPrefix: String
    ): String? {
        val cleaned = rawInput.trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.', '!')
        if (cleaned.isBlank() || cleaned.startsWith(forbiddenPrefix)) return null
        val rest = if (cleaned.startsWith("$requiredPrefix ")) {
            cleaned.removePrefix("$requiredPrefix ").trim()
        } else if (cleaned.startsWith("here ") || cleaned.startsWith("there ")) {
            return null
        } else {
            cleaned
        }
        val tokens = rest.split(" ").filter { it.isNotBlank() }
        if (tokens.size != 2) return null
        val article = tokens[0]
        val noun = tokens[1]
        if (article != "a" && article != "an") return null
        if (noun.length < 2 || !noun.all { it.isLetter() }) return null
        if (noun in setOf("boys", "girls", "tigers", "books", "cups", "apples", "pens", "cats", "dogs", "eggs")) return null
        val startsWithVowel = noun.first() in setOf('a', 'e', 'i', 'o', 'u')
        val validArticle = if (startsWithVowel) article == "an" else article == "a"
        return if (validArticle) "$requiredPrefix $article $noun" else null
    }

    private fun parsePluralArePhrase(
        rawInput: String,
        requiredPrefix: String,
        forbiddenPrefix: String
    ): String? {
        val cleaned = rawInput.trim().lowercase().replace(Regex("\\s+"), " ").trimEnd('.', '!')
        if (cleaned.isBlank() || cleaned.startsWith(forbiddenPrefix)) return null
        val rest = if (cleaned.startsWith("$requiredPrefix ")) {
            cleaned.removePrefix("$requiredPrefix ").trim()
        } else if (cleaned.startsWith("here ") || cleaned.startsWith("there ")) {
            return null
        } else {
            cleaned
        }
        val tokens = rest.split(" ").filter { it.isNotBlank() }
        if (tokens.size != 2) return null
        val countWord = tokens[0]
        val noun = tokens[1]
        val validPluralCounts = setOf(
            "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "many", "some"
        )
        if (countWord !in validPluralCounts) return null
        if (noun.length < 3 || !noun.all { it.isLetter() } || !noun.endsWith("s")) return null
        return "$requiredPrefix $countWord $noun"
    }

    private fun validateHereIsSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val s1 = parseSingularArticlePhrase(binding.etArticleItem1.text.toString(), "here is", "here are")
        val s2 = parseSingularArticlePhrase(binding.etArticleItem2.text.toString(), "here is", "here are")
        val s3 = parseSingularArticlePhrase(binding.etArticleItem3.text.toString(), "here is", "here are")
        if (s1 != null && s2 != null && s3 != null) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("$s1. $s2. $s3.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_here_is_error)
        }
    }

    private fun validateHereAreSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val s1 = parsePluralArePhrase(binding.etArticleItem1.text.toString(), "here are", "here is")
        val s2 = parsePluralArePhrase(binding.etArticleItem2.text.toString(), "here are", "here is")
        val s3 = parsePluralArePhrase(binding.etArticleItem3.text.toString(), "here are", "here is")
        if (s1 != null && s2 != null && s3 != null) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("$s1. $s2. $s3.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_here_are_error)
        }
    }

    private fun validateThereIsSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val s1 = parseSingularArticlePhrase(binding.etArticleItem1.text.toString(), "there is", "there are")
        val s2 = parseSingularArticlePhrase(binding.etArticleItem2.text.toString(), "there is", "there are")
        val s3 = parseSingularArticlePhrase(binding.etArticleItem3.text.toString(), "there is", "there are")
        if (s1 != null && s2 != null && s3 != null) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("$s1. $s2. $s3.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_there_is_error)
        }
    }

    private fun validateMyRoomSelfWriteExercise() {
        val ex = inLessonExercises.getOrNull(currentExerciseIndex) ?: return
        val t1 = binding.etArticleItem1.text.toString().trim().lowercase()
        val t2 = binding.etArticleItem2.text.toString().trim().lowercase()
        val t3 = binding.etArticleItem3.text.toString().trim().lowercase()

        val valid1 = (t1.startsWith("there is ") || t1.startsWith("a ") || t1.startsWith("an ")) &&
            t1.contains("in my room") && (t1.contains(" a ") || t1.contains(" an ") || t1.startsWith("a ") || t1.startsWith("an "))
        val valid2 = (t2.startsWith("there are ") || t2.contains(" in my room")) &&
            t2.contains("in my room") &&
            (t2.contains("two") || t2.contains("three") || t2.contains("four") || t2.contains("five") ||
             t2.contains("six") || t2.contains("seven") || t2.contains("eight") || t2.contains("nine") ||
             t2.contains("ten") || t2.contains("many") || t2.contains("some") || t2.contains("s "))
        val valid3 = t3.startsWith("i see ") && t3.length > 6

        if (valid1 && valid2 && valid3) {
            isCurrentExerciseSolved = true
            selectedOptionIndex = ex.correctOptionIndex
            viewModel.speakEnglish("There is a bed in my room. There are two chairs in my room. I see a window.")
            bindCurrentExercise()
        } else {
            isCurrentExerciseSolved = false
            selectedOptionIndex = 0
            binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
            binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
            binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
            binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_my_room_error)
        }
    }

    private fun updateTopProgressBar() {
        val totalEx = inLessonExercises.size.coerceAtLeast(1)
        val targetProgress = when (currentStepIndex) {
            0 -> 22
            1 -> 48
            2 -> {
                val frac = (currentExerciseIndex + 1).toFloat() / totalEx.toFloat()
                (62 + (frac * 32f)).toInt().coerceIn(65, 95)
            }
            else -> 100
        }
        binding.progressLessonTopBar.animateProgressTo(targetProgress, maxValue = 100, durationMs = 650L)
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
        updateTopProgressBar()

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
        if (currentStepIndex == 0) {
            binding.layoutStageHeaderRow.visibility = View.GONE
            binding.dividerStageHeader.visibility = View.GONE
            binding.cardLessonStageContainer.setBackgroundResource(android.R.color.transparent)
        } else {
            binding.layoutStageHeaderRow.visibility = View.VISIBLE
            binding.dividerStageHeader.visibility = View.VISIBLE
            binding.cardLessonStageContainer.setBackgroundResource(R.drawable.bg_lesson_stage_card)
        }

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
                binding.tvLessonPrimaryCtaText.text = "Misollarni ko‘rish  →"
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
        val totalEx = inLessonExercises.size.coerceAtLeast(1)
        val dispIdx = (currentExerciseIndex + 1).coerceIn(1, totalEx)

        if (currentStepIndex == 2) {
            binding.tvStageHeaderTitle.text = "Mashq $dispIdx / $totalEx"
            binding.tvStageHeaderBadge.text = "$dispIdx / $totalEx"
        }

        binding.tvMashqPromptSub.text = ex.promptSub
        binding.tvMashqPromptMain.text = ex.promptSentenceWithBlank

        when (ex.mode) {
            LessonExerciseMode.READ_ALOUD -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.VISIBLE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.GONE
                binding.btnReadAloudTap.text = getString(R.string.lesson_read_aloud_tap_format, readAloudTapCount)
                binding.btnReadAloudTap.isEnabled = readAloudTapCount < 3
                if (readAloudTapCount >= 3 || isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_read_aloud_done)
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text =
                        "Matnni ovoz chiqarib 3 marta o‘qing va har safar tugmani bosing."
                }
            }
            LessonExerciseMode.SELF_WRITE -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.VISIBLE
                binding.layoutMashqSelfWriteArticles.visibility = View.GONE
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text =
                        "Lera o‘rnida o‘zingiz yozib chiqing va 'Tekshirish' tugmasini bosing."
                }
            }
            LessonExerciseMode.SELF_WRITE_ARTICLES -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_article_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_article_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_article_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text =
                        "3 ta narsani a yoki an bilan yozing va 'Tekshirish' tugmasini bosing."
                }
            }
            LessonExerciseMode.SELF_WRITE_PAIRS -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_pair_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_pair_2_hint)
                binding.etArticleItem3.visibility = View.GONE
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_pair_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_THIS_THAT -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_this_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_that_hint)
                binding.etArticleItem3.visibility = View.GONE
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_this_that_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_THESE_THOSE -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_these_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_those_hint)
                binding.etArticleItem3.visibility = View.GONE
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_these_those_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_I_HAVE -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_i_have_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_i_have_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_i_have_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_i_have_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_HAVE_I_GOT -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_have_i_got_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_have_i_got_2_hint)
                binding.etArticleItem3.visibility = View.GONE
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_have_i_got_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_I_HAVE_NOT_GOT -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_i_have_not_got_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_i_have_not_got_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_i_have_not_got_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_i_have_not_got_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_NUMBERS -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_numbers_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_numbers_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_numbers_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_numbers_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_I_HAVE_NUMBERS -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_i_have_num_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_i_have_num_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_i_have_num_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_i_have_num_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_BUT_NO -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_but_no_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_but_no_2_hint)
                binding.etArticleItem3.visibility = View.GONE
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_but_no_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_TIGERS_COUNT -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_tigers_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_tigers_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_tigers_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_tigers_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_ORDINALS -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_ordinal_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_ordinal_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_ordinal_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_ordinal_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_HERE_IS -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_here_is_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_here_is_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_here_is_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_here_is_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_HERE_ARE -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_here_are_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_here_are_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_here_are_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_here_are_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_THERE_IS -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_there_is_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_there_is_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_there_is_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_there_is_helper)
                }
            }
            LessonExerciseMode.SELF_WRITE_MY_ROOM -> {
                binding.rowMashqOptions.visibility = View.GONE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.VISIBLE
                binding.etArticleItem1.setHint(R.string.lesson_self_write_my_room_1_hint)
                binding.etArticleItem2.setHint(R.string.lesson_self_write_my_room_2_hint)
                binding.etArticleItem3.visibility = View.VISIBLE
                binding.etArticleItem3.setHint(R.string.lesson_self_write_my_room_3_hint)
                if (isCurrentExerciseSolved) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_success)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_check_circle)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#4ADE80"))
                    binding.tvMashqFeedbackText.text =
                        ex.explanationUzbek.ifBlank { "Barakalla! Barcha mashqlar bajarildi." }
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = getString(R.string.lesson_self_write_my_room_helper)
                }
            }
            LessonExerciseMode.STANDARD -> {
                binding.rowMashqOptions.visibility = View.VISIBLE
                binding.layoutMashqReadAloud.visibility = View.GONE
                binding.layoutMashqSelfWrite.visibility = View.GONE
                binding.layoutMashqSelfWriteArticles.visibility = View.GONE

                configureOptionLayoutOrientation(ex.options)

                binding.btnOption1.visibility = if (ex.options.isNotEmpty()) View.VISIBLE else View.GONE
                binding.btnOption2.visibility = if (ex.options.size >= 2) View.VISIBLE else View.GONE
                binding.btnOption3.visibility = if (ex.options.size >= 3) View.VISIBLE else View.GONE

                val opt0 = ex.options.getOrElse(0) { "" }
                val opt1 = ex.options.getOrElse(1) { "" }
                val opt2 = ex.options.getOrElse(2) { "" }

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
                        if (ex.explanationUzbek.isNotBlank()) {
                            ex.explanationUzbek
                        } else if (currentExerciseIndex < inLessonExercises.lastIndex) {
                            "To‘g‘ri! Keyingi mashq ochildi."
                        } else {
                            "Barakalla! Barcha mashqlar bajarildi."
                        }
                } else if (selectedOptionIndex >= 0) {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_option_wrong)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#FCA5A5"))
                    binding.tvMashqFeedbackText.text = "Noto‘g‘ri javob. Yana bir bor urinib ko‘ring!"
                } else {
                    binding.layoutMashqFeedback.setBackgroundResource(R.drawable.bg_lesson_feedback_helper)
                    binding.ivMashqFeedbackIcon.setImageResource(R.drawable.ic_lesson_info_outline)
                    binding.ivMashqFeedbackIcon.setColorFilter(Color.parseColor("#CBD5E1"))
                    binding.tvMashqFeedbackText.text = "To‘g‘ri variantlardan birini tanlang."
                }
            }
        }
    }

    private fun configureOptionLayoutOrientation(options: List<String>) {
        val useVertical = options.any { it.length > 12 }
        val density = resources.displayMetrics.density
        val row = binding.rowMashqOptions
        row.orientation = if (useVertical) {
            android.widget.LinearLayout.VERTICAL
        } else {
            android.widget.LinearLayout.HORIZONTAL
        }
        val optionButtons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3)
        optionButtons.forEachIndexed { idx, btn ->
            val lp = btn.layoutParams as android.widget.LinearLayout.LayoutParams
            if (useVertical) {
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
                lp.weight = 0f
                lp.marginStart = 0
                lp.marginEnd = 0
                lp.bottomMargin = if (idx < optionButtons.lastIndex) (8 * density).toInt() else 0
            } else {
                lp.width = 0
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
                lp.weight = 1f
                lp.bottomMargin = 0
                val side = (8 * density).toInt()
                lp.marginStart = if (idx == 1) side else 0
                lp.marginEnd = if (idx == 1 && options.size >= 3) side else 0
            }
            btn.layoutParams = lp
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
            lower.contains("tiger") || lower.contains("yo'lbars") || lower.contains("yo‘lbars") -> "🐯"
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

    private fun getLessonSituationPhotoRes(sec: LessonSectionModel): Int? {
        return when {
            sec.id == com.example.data.local.CurriculumSeedData.DARS_1_SECTION_ID ->
                R.drawable.img_lesson_conversation_1794310851367
            sec.id == com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID ->
                R.drawable.img_topic_morning_intro_1794316347713
            sec.id == com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID ->
                R.drawable.img_topic_articles_1794316349035
            sec.id == com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID ->
                R.drawable.img_lesson_objects_1794310854481
            sec.id == com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID ->
                R.drawable.img_topic_demonstratives_1794316348388
            sec.id == com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID ->
                R.drawable.img_lesson_grammar_1794310851898
            sec.id == com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID ->
                R.drawable.img_lesson_numbers_1794310853767
            sec.id == com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID ->
                R.drawable.img_lesson_grammar_1794310851898
            sec.id == com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID ->
                R.drawable.img_topic_demonstratives_1794316348388
            sec.id == com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID ->
                R.drawable.img_lesson_places_1794310885756
            sec.id == "sec_01_01" -> R.drawable.img_lesson_conversation_1794310851367
            sec.id == "sec_01_02" -> R.drawable.img_topic_morning_intro_1794316347713
            sec.id == "sec_01_05" -> R.drawable.img_lesson_places_1794310885756
            sec.id == "sec_03_01" -> R.drawable.img_lesson_numbers_1794310853767
            sec.id == "sec_04_01" -> R.drawable.img_lesson_objects_1794310854481
            sec.topicId == "topic_01_introduction" && sec.orderIndex == 1 -> R.drawable.img_lesson_conversation_1794310851367
            else -> null
        }
    }

    private fun getLessonSituationText(sec: LessonSectionModel): Pair<String, String> {
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_1_SECTION_ID &&
            sec.explanationUzbek.isNotBlank()
        ) {
            return Pair("🤖 TalkMe (TTS matni)", sec.explanationUzbek)
        }
        if ((sec.id == com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID ||
                sec.id == com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID) &&
            sec.explanationUzbek.isNotBlank()
        ) {
            return Pair("📘 1️⃣ TUSHUNTIRISH", sec.explanationUzbek)
        }
        val title = "Bugungi vaziyat"
        val body = when {
            sec.id == "sec_01_01" -> "Til klubidagi ilk kun. Tanishamiz va birga boshlaymiz."
            sec.id == "sec_01_02" -> "TalkMe ilovasi bilan qulay va samarali o‘rganish usuli."
            sec.topicId == "topic_01_introduction" -> "Yangi do‘stlar bilan tanishish va salomlashish vaziyati."
            sec.topicId == "topic_03_numbers" -> "Do‘konda yoki bozorda hisob-kitob qilish vaziyati."
            sec.topicId == "topic_04_objects" -> "Xonadagi va ish stolidagi narsalarni nomlash vaziyati."
            else -> sec.objectiveUzbek.takeIf { it.isNotBlank() }
                ?.replace(Regex("[🤖🚀👋💬🎯📖]"), "")
                ?.trim()
                ?.take(80)
                ?: "Bugungi dars mavzusi va kundalik muloqot vaziyati."
        }
        return Pair(title, body)
    }

    private fun buildVocabCardItems(
        sec: LessonSectionModel,
        vocabList: List<VocabularyModel>,
        grammarList: List<GrammarRuleModel>
    ): List<LessonVocabCardItem> {
        val result = mutableListOf<LessonVocabCardItem>()

        if (sec.id == "sec_01_01") {
            result.add(
                LessonVocabCardItem(
                    badge = "01",
                    englishWord = "Welcome",
                    uzbekMeaning = "Xush kelibsiz",
                    exampleEnglish = "Welcome to TalkMe!",
                    exampleUzbek = "TalkMe’ga xush kelibsiz!",
                    wordAudioText = "Welcome",
                    exampleAudioText = "Welcome to TalkMe!"
                )
            )
            result.add(
                LessonVocabCardItem(
                    badge = "02",
                    englishWord = "Learn",
                    uzbekMeaning = "O‘rganmoq",
                    exampleEnglish = "Learn English.",
                    exampleUzbek = "Ingliz tilini o‘rganing.",
                    wordAudioText = "Learn",
                    exampleAudioText = "Learn English."
                )
            )
            result.add(
                LessonVocabCardItem(
                    badge = "03",
                    englishWord = "Start",
                    uzbekMeaning = "Boshlamoq",
                    exampleEnglish = "Let’s start!",
                    exampleUzbek = "Boshlaymiz!",
                    wordAudioText = "Start",
                    exampleAudioText = "Let’s start!"
                )
            )
            return result
        }

        // For other lessons: sort deterministically by id and build all cards
        val orderedVocab = vocabList.sortedBy { it.id }
        val limit = if (orderedVocab.size > 3) orderedVocab.size else 3
        orderedVocab.take(limit).forEachIndexed { idx, v ->
            val numStr = String.format("%02d", idx + 1)
            val enWord = v.englishWord.trim()
            val uzMeaning = v.uzbekTranslation.substringBefore(",").trim().ifBlank { "Ma’nosi" }
            val exEn = v.exampleEnglish.trim().ifBlank { "$enWord is useful." }
            val exUz = v.exampleUzbek.trim().ifBlank { "$uzMeaning ma’nosi." }
            result.add(
                LessonVocabCardItem(
                    badge = numStr,
                    englishWord = enWord,
                    uzbekMeaning = uzMeaning,
                    exampleEnglish = exEn,
                    exampleUzbek = exUz,
                    wordAudioText = enWord,
                    exampleAudioText = exEn
                )
            )
        }

        // If fewer than 3 vocab items, supplement from grammar rules or default
        if (result.size < 3) {
            grammarList.forEach { gr ->
                if (result.size >= 3) return@forEach
                val numStr = String.format("%02d", result.size + 1)
                val word = gr.exampleEnglish.split(" ").firstOrNull().orEmpty().ifBlank { "Grammar" }
                val meaning = gr.titleUzbek.substringBefore("—").substringBefore("-").trim().ifBlank { "Qoida" }
                val exEn = gr.exampleEnglish.ifBlank { "$word." }
                val exUz = gr.exampleUzbek.ifBlank { "$meaning misoli." }
                result.add(
                    LessonVocabCardItem(
                        badge = numStr,
                        englishWord = word,
                        uzbekMeaning = meaning,
                        exampleEnglish = exEn,
                        exampleUzbek = exUz,
                        wordAudioText = word,
                        exampleAudioText = exEn
                    )
                )
            }
        }

        while (result.size < 3) {
            val numStr = String.format("%02d", result.size + 1)
            val fallbackWords = listOf(
                Triple("Listen", "Tinglamoq", "Listen carefully to the audio."),
                Triple("Practice", "Mashq qilmoq", "Practice English every day."),
                Triple("Speak", "Gapirmoq", "Speak confidently with TalkMe.")
            )
            val (w, m, ex) = fallbackWords[result.size % fallbackWords.size]
            result.add(
                LessonVocabCardItem(
                    badge = numStr,
                    englishWord = w,
                    uzbekMeaning = m,
                    exampleEnglish = ex,
                    exampleUzbek = "$m bilan mashq.",
                    wordAudioText = w,
                    exampleAudioText = ex
                )
            )
        }

        return if (result.size > 3) result else result.take(3)
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
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_2_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_2_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_3_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_3_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_4_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_4_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_5_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_5_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_6_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_6_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_7_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_7_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_8_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_8_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_9_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_9_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_10_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_10_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_11_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_11_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_12_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_12_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_13_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_13_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_14_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_14_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_15_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_15_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_16_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_16_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_17_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_17_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }
        if (sec.id == com.example.data.local.CurriculumSeedData.DARS_18_SECTION_ID) {
            return com.example.data.local.CurriculumSeedData.DARS_18_MISOLLAR.map {
                LessonExampleDisplayItem(
                    englishSentence = it.englishSentence,
                    blueHighlightWord = it.blueHighlightWord,
                    greenHighlightWord = it.greenHighlightWord,
                    uzbekSentence = it.uzbekSentence,
                    uzbekGreenWord = it.uzbekGreenWord
                )
            }
        }

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

        // 1. From repository exercises (sorted deterministically by ID; no 3-item cap so 10-exercise lessons show all 10)
        repoExercises.sortedBy { it.id }.forEach { ex ->
            val mode = when (ex.id) {
                "ex_a0_02_09", "ex_a0_03_09", "ex_a0_04_09", "ex_a0_05_09",
                "ex_a0_06_10", "ex_a0_07_08", "ex_a0_08_08", "ex_a0_09_09",
                "ex_a0_10_08", "ex_a0_11_09", "ex_a0_12_08",
                "ex_a0_13_08", "ex_a0_14_08",
                "ex_a0_15_08", "ex_a0_16_08", "ex_a0_17_09", "ex_a0_18_09" ->
                    LessonExerciseMode.READ_ALOUD
                "ex_a0_02_10" -> LessonExerciseMode.SELF_WRITE
                "ex_a0_03_10" -> LessonExerciseMode.SELF_WRITE_ARTICLES
                "ex_a0_04_10" -> LessonExerciseMode.SELF_WRITE_PAIRS
                "ex_a0_05_10" -> LessonExerciseMode.SELF_WRITE_THIS_THAT
                "ex_a0_06_11" -> LessonExerciseMode.SELF_WRITE_THESE_THOSE
                "ex_a0_07_09" -> LessonExerciseMode.SELF_WRITE_I_HAVE
                "ex_a0_08_09" -> LessonExerciseMode.SELF_WRITE_HAVE_I_GOT
                "ex_a0_09_10" -> LessonExerciseMode.SELF_WRITE_I_HAVE_NOT_GOT
                "ex_a0_10_09" -> LessonExerciseMode.SELF_WRITE_NUMBERS
                "ex_a0_11_10" -> LessonExerciseMode.SELF_WRITE_I_HAVE_NUMBERS
                "ex_a0_12_09" -> LessonExerciseMode.SELF_WRITE_BUT_NO
                "ex_a0_13_09" -> LessonExerciseMode.SELF_WRITE_TIGERS_COUNT
                "ex_a0_14_09" -> LessonExerciseMode.SELF_WRITE_ORDINALS
                "ex_a0_15_09" -> LessonExerciseMode.SELF_WRITE_HERE_IS
                "ex_a0_16_09" -> LessonExerciseMode.SELF_WRITE_HERE_ARE
                "ex_a0_17_10" -> LessonExerciseMode.SELF_WRITE_THERE_IS
                else -> LessonExerciseMode.STANDARD
            }
            val options = ex.options.filter { it.isNotBlank() }
            if (options.size >= 2 || mode != LessonExerciseMode.STANDARD) {
                val displayOptions = if (options.size > 3) options.take(3) else options
                val finalCorrectIdx = displayOptions.indexOf(ex.correctAnswer).let { if (it >= 0) it else 0 }
                result.add(
                    InLessonExerciseItem(
                        id = ex.id,
                        promptSub = ex.subPromptText.ifBlank { "Bo‘sh joyni to‘ldiring:" },
                        promptSentenceWithBlank = ex.promptText,
                        fullAudioSentence = ex.audioTextToSpeak?.takeIf { it.isNotBlank() } ?: ex.correctAnswer,
                        options = displayOptions,
                        correctOptionIndex = finalCorrectIdx,
                        mode = mode,
                        explanationUzbek = ex.explanationUzbek
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

        return result
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
