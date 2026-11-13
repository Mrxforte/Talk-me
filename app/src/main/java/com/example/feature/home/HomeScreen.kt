package com.example.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.core.common.AppConstants
import com.example.core.ui.ProgressAnimator.animateProgressTo
import com.example.core.ui.ProgressAnimator.replayAllProgressFromZero
import com.example.databinding.FragmentHomeBinding
import com.example.databinding.ViewStatisticCardBinding
import com.example.domain.model.LearningDashboardStats
import com.example.domain.model.PracticeCategory
import com.example.domain.model.TopicPracticeSummary
import com.example.domain.repository.LearningRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val stats: LearningDashboardStats? = null,
    val studiedTopics: List<TopicPracticeSummary> = emptyList(),
    val selectedTopicId: String? = null
) {
    val activeTopicSummary: TopicPracticeSummary?
        get() = studiedTopics.find { it.topicId == selectedTopicId }
            ?: studiedTopics.lastOrNull()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val selectedTopicIdFlow = MutableStateFlow<String?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        learningRepository.observeDashboardStats(),
        learningRepository.observeTopicPracticeSummaries(),
        selectedTopicIdFlow
    ) { stats, topicSummaries, chosenTopicId ->
        val resolvedTopicId = chosenTopicId
            ?: stats.continueLearning.currentTopicId.takeIf { id -> topicSummaries.any { it.topicId == id } }
            ?: topicSummaries.lastOrNull()?.topicId
            ?: AppConstants.INITIAL_TOPIC_ID
        HomeUiState(
            stats = stats,
            studiedTopics = topicSummaries,
            selectedTopicId = resolvedTopicId
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = HomeUiState()
    )

    init {
        viewModelScope.launch {
            learningRepository.ensureSeeded()
        }
    }

    fun selectTopic(topicId: String) {
        selectedTopicIdFlow.value = topicId
    }
}

/**
 * Pixel-perfect Home Dashboard Fragment matching the reference designs:
 * - Greeting header ("Xayrli tong, Azamat") + "1 Day Streak" pill
 * - Continue Learning Hero Card ("TALKME ENGLISH COURSE", "1. Introduction — Kirish", "0% completed", "Continue Learning >")
 * - "Bugungi ko‘nikmalar" 2x2 Grid (Speaking, Listening, Writing, Reading) with 3D illustrations & "Boshlash >" buttons
 * - "Learning tools" 2-column row (Vocabulary +36, Grammar +10)
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private var wasStopped = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        binding.tvGreeting.text = when (hour) {
            in 5..11 -> getString(R.string.greeting_morning)
            in 12..17 -> getString(R.string.greeting_afternoon)
            else -> getString(R.string.greeting_evening)
        }

        // Default click listeners before state arrives
        val defaultTopicId = AppConstants.INITIAL_TOPIC_ID
        wireSkillAndToolCards(defaultTopicId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val stats = state.stats ?: return@collect
                    binding.tvUserName.text = stats.userName.ifBlank { "Azamat" }
                    val streakDays = stats.currentStreakDays.coerceAtLeast(1)
                    binding.tvStreakBadge.text = "$streakDays Day Streak"

                    val cont = stats.continueLearning
                    binding.tvHeroCourseName.text = cont.courseName.ifBlank { "TALKME ENGLISH COURSE (A0–C2)" }
                    val topicHeader = cont.currentTopicTitle.ifBlank { "1. A0 • Kirish (Alifbo va Tanishuv)" }
                    binding.tvHeroTopicName.text =
                        if (topicHeader.contains("Introduction") || topicHeader.contains("Kirish")) {
                            "1. A0 • Kirish (Alifbo va Tanishuv)"
                        } else {
                            topicHeader
                        }
                    binding.ivHeroIllustration.setImageResource(R.drawable.img_home_hero_thumb)

                    binding.tvHeroSectionName.text = if (cont.currentSectionTitle.isNotBlank()) {
                        cont.currentSectionTitle
                    } else {
                        getString(R.string.curriculum_empty)
                    }
                    binding.progressHero.animateProgressTo(cont.progressPercent, maxValue = 100)
                    binding.tvHeroProgressPercent.text = "${cont.progressPercent}% completed"

                    val hasLesson = cont.currentSectionId.isNotBlank()
                    binding.btnContinueLearning.isEnabled = hasLesson
                    binding.cardHeroBanner.isClickable = hasLesson
                    val openContinueLesson = View.OnClickListener {
                        if (!hasLesson) return@OnClickListener
                        findNavController().navigate(
                            R.id.lessonDetailFragment,
                            bundleOf(
                                AppConstants.ARG_TOPIC_ID to cont.currentTopicId,
                                AppConstants.ARG_SECTION_ID to cont.currentSectionId
                            )
                        )
                    }
                    binding.btnContinueLearning.setOnClickListener(openContinueLesson)
                    binding.cardHeroBanner.setOnClickListener(openContinueLesson)

                    val activeTopic = state.activeTopicSummary
                    val resolvedTopicId = activeTopic?.topicId ?: cont.currentTopicId.ifBlank { AppConstants.INITIAL_TOPIC_ID }
                    wireSkillAndToolCards(resolvedTopicId)

                    if (activeTopic != null) {
                        binding.tvHomeVocabBadge.text = "+${activeTopic.totalWordsCount}"
                        binding.tvHomeGrammarBadge.text = "+${activeTopic.totalGrammarCount}"

                        // Keep hidden legacy views updated for any existing unit tests
                        bindHomeCard(
                            card = binding.cardMemorizedWords,
                            valueText = "${activeTopic.memorizedWordsCount} / ${activeTopic.totalWordsCount}",
                            subtitleText = "${activeTopic.titleEnglish}: mavzular va sub-topiclar",
                            newBadgeCount = activeTopic.newWordsBadgeCount
                        ) {
                            findNavController().navigate(
                                R.id.vocabularyTopicsFragment,
                                bundleOf(AppConstants.ARG_TOPIC_ID to activeTopic.topicId)
                            )
                        }
                        bindHomeCard(
                            card = binding.cardMasteredGrammar,
                            valueText = "${activeTopic.masteredGrammarCount} / ${activeTopic.totalGrammarCount}",
                            subtitleText = "${activeTopic.titleEnglish}: grammatika qoidalari",
                            newBadgeCount = activeTopic.newGrammarBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.GRAMMAR, activeTopic.topicId)
                        }
                        bindHomeCard(
                            card = binding.cardSkillReading,
                            valueText = "${activeTopic.totalWordsCount} so'z",
                            subtitleText = "${activeTopic.titleEnglish} • matn va o'qish",
                            newBadgeCount = activeTopic.readingNewBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.READING, activeTopic.topicId)
                        }
                        bindHomeCard(
                            card = binding.cardSkillWriting,
                            valueText = "${activeTopic.totalWordsCount} so'z",
                            subtitleText = "${activeTopic.titleEnglish} • yozma tarjima",
                            newBadgeCount = activeTopic.writingNewBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.WRITING, activeTopic.topicId)
                        }
                        bindHomeCard(
                            card = binding.cardSkillListening,
                            valueText = "${activeTopic.totalWordsCount} audio",
                            subtitleText = "${activeTopic.titleEnglish} • eshitib tushunish",
                            newBadgeCount = activeTopic.listeningNewBadgeCount
                        ) {
                            openTopicExercise(PracticeCategory.LISTENING, activeTopic.topicId)
                        }
                        bindHomeCard(
                            card = binding.cardSkillSpeaking,
                            valueText = "${activeTopic.totalWordsCount} so'z",
                            subtitleText = "${activeTopic.titleEnglish} • AI ovozli suhbat",
                            newBadgeCount = activeTopic.speakingNewBadgeCount
                        ) {
                            findNavController().navigate(
                                R.id.speakingAiFragment,
                                bundleOf(AppConstants.ARG_TOPIC_ID to activeTopic.topicId)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun wireSkillAndToolCards(topicId: String) {
        val openSpeaking = View.OnClickListener {
            findNavController().navigate(
                R.id.speakingAiFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to topicId)
            )
        }
        binding.cardHomeSpeaking.setOnClickListener(openSpeaking)
        binding.btnHomeSpeakingStart.setOnClickListener(openSpeaking)

        val openListening = View.OnClickListener {
            openTopicExercise(PracticeCategory.LISTENING, topicId)
        }
        binding.cardHomeListening.setOnClickListener(openListening)
        binding.btnHomeListeningStart.setOnClickListener(openListening)

        val openWriting = View.OnClickListener {
            openTopicExercise(PracticeCategory.WRITING, topicId)
        }
        binding.cardHomeWriting.setOnClickListener(openWriting)
        binding.btnHomeWritingStart.setOnClickListener(openWriting)

        val openReading = View.OnClickListener {
            openTopicExercise(PracticeCategory.READING, topicId)
        }
        binding.cardHomeReading.setOnClickListener(openReading)
        binding.btnHomeReadingStart.setOnClickListener(openReading)

        val openVocab = View.OnClickListener {
            findNavController().navigate(
                R.id.vocabularyTopicsFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to topicId)
            )
        }
        binding.cardHomeVocabulary.setOnClickListener(openVocab)

        val openGrammar = View.OnClickListener {
            openTopicExercise(PracticeCategory.GRAMMAR, topicId)
        }
        binding.cardHomeGrammar.setOnClickListener(openGrammar)
    }

    private fun bindHomeCard(
        card: ViewStatisticCardBinding,
        valueText: String,
        subtitleText: String,
        newBadgeCount: Int,
        onClick: () -> Unit
    ) {
        card.tvStatValue.text = valueText
        card.tvStatSubtitle.text = subtitleText
        card.tvStatBadge.visibility = View.VISIBLE
        card.tvStatBadge.text = if (newBadgeCount > 0) {
            "+$newBadgeCount"
        } else {
            valueText
        }
        card.root.setOnClickListener { onClick() }
        card.ivStatCoverImage.setOnClickListener { onClick() }
    }

    private fun openTopicExercise(category: PracticeCategory, topicId: String) {
        findNavController().navigate(
            R.id.exerciseSessionFragment,
            bundleOf(
                AppConstants.ARG_PRACTICE_CATEGORY to category.id,
                AppConstants.ARG_TOPIC_ID to topicId
            )
        )
    }

    override fun onStart() {
        super.onStart()
        if (wasStopped) {
            wasStopped = false
            _binding?.root?.replayAllProgressFromZero()
        }
    }

    override fun onStop() {
        super.onStop()
        wasStopped = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        wasStopped = false
        _binding = null
    }
}
