package com.example.feature.vocabularyreview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.LockStarBurstAnimator
import com.example.core.ui.TopicVisualArtwork
import com.example.databinding.FragmentFlipCardsBinding
import com.example.databinding.FragmentVocabularySubtopicsBinding
import com.example.databinding.FragmentVocabularyTopicsBinding
import com.example.databinding.ItemVocabularySubtopicCardBinding
import com.example.databinding.ItemVocabularyTopicCardBinding
import com.example.databinding.ViewFlipCardRatingBarBinding
import com.example.domain.model.FlipCardRating
import com.example.domain.model.PracticeCategory
import com.example.domain.model.UnlockStatus
import com.example.domain.model.VocabularyModel
import com.example.domain.model.VocabularySubtopicCardModel
import com.example.domain.model.VocabularyTopicCardModel
import com.example.domain.repository.LearningRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class VocabularyHierarchyViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    val topicCards: StateFlow<List<VocabularyTopicCardModel>> =
        learningRepository.observeVocabularyTopicCards()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
            )

    private val selectedTopicIdFlow = MutableStateFlow(AppConstants.INITIAL_TOPIC_ID)

    val subtopicCards: StateFlow<List<VocabularySubtopicCardModel>> =
        selectedTopicIdFlow
            .flatMapLatest { topicId -> learningRepository.observeVocabularySubtopics(topicId) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = emptyList()
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
 * Step 1 of Vocabulary Flow: Major Topics Grid (Katta Mavzular).
 * Shows studied/unlocked major topics with 1-to-1 matching 3D artwork, subtopics count, and green badge.
 * Clicking an unlocked Major Topic opens its Sub-topics screen (VocabularySubtopicsFragment).
 */
@AndroidEntryPoint
class VocabularyTopicsFragment : Fragment() {

    private var _binding: FragmentVocabularyTopicsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VocabularyHierarchyViewModel by viewModels()

    private val adapter = VocabularyTopicsAdapter { topic ->
        if (topic.status == UnlockStatus.LOCKED) {
            Toast.makeText(
                requireContext(),
                getString(R.string.topic_locked_toast),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController().navigate(
                R.id.vocabularySubtopicsFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to topic.topicId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVocabularyTopicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.headerVocabTopics.tvHeaderTitle.text = "Vocabulary — Katta Mavzular"
        binding.headerVocabTopics.tvHeaderSubtitle.text =
            "O'tilgan mavzuni tanlang → Sub-topiclar → 3D Flip Cards"
        binding.headerVocabTopics.btnBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.rvVocabularyTopics.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvVocabularyTopics.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.topicCards.collect { list ->
                    val unlockedCount = list.count { it.status != UnlockStatus.LOCKED }
                    val totalMemorized = list
                        .filter { it.status != UnlockStatus.LOCKED }
                        .sumOf { it.memorizedWordsCount }
                    val totalUnlockedWords = list
                        .filter { it.status != UnlockStatus.LOCKED }
                        .sumOf { it.totalWordsCount }
                    binding.tvVocabTopicsSummary.text =
                        "Ochiq mavzular: $unlockedCount ta • Yodlangan: $totalMemorized/$totalUnlockedWords so'z"
                    adapter.submitList(list)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private class VocabularyTopicsAdapter(
    private val onTopicClick: (VocabularyTopicCardModel) -> Unit
) : ListAdapter<VocabularyTopicCardModel, VocabularyTopicsAdapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<VocabularyTopicCardModel>() {
        override fun areItemsTheSame(
            oldItem: VocabularyTopicCardModel,
            newItem: VocabularyTopicCardModel
        ): Boolean = oldItem.topicId == newItem.topicId

        override fun areContentsTheSame(
            oldItem: VocabularyTopicCardModel,
            newItem: VocabularyTopicCardModel
        ): Boolean = oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemVocabularyTopicCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VocabularyTopicCardModel) {
            binding.tvVocabTopicOrder.text = String.format("%02d-MAVZU", item.orderIndex)
            binding.ivVocabTopicArtwork.setImageResource(
                TopicVisualArtwork.getTopicImageRes(item.topicId, item.orderIndex)
            )

            if (item.status == UnlockStatus.LOCKED) {
                binding.layoutVocabTopicMysteryOverlay.visibility = View.VISIBLE
                binding.tvVocabTopicMysteryHint.text = "★ MAXFIY MAVZU ★"
                binding.tvVocabTopicTitle.text = "❓ Maxfiy Mavzu #${item.orderIndex}"
                binding.tvVocabTopicUzbek.text = "Sirli daraja • Qulflangan"
                binding.tvVocabTopicSubtopicsMeta.text = "Avval oldingi mavzuni tugating"
                binding.tvVocabTopicBadge.text = "🔒"

                val triggerLockAnimation = View.OnClickListener {
                    binding.tvVocabTopicMysteryHint.text = "✨ Oldingi darsni tugating! ✨"
                    LockStarBurstAnimator.playLockTapStarBurst(
                        cardView = binding.cardVocabTopic,
                        lockCenterView = binding.layoutVocabTopicLockMedallion,
                        particleContainer = binding.containerVocabTopicImageFrame
                    ) {
                        onTopicClick(item)
                    }
                }
                binding.cardVocabTopic.setOnClickListener(triggerLockAnimation)
                binding.layoutVocabTopicLockMedallion.setOnClickListener(triggerLockAnimation)
            } else {
                binding.layoutVocabTopicMysteryOverlay.visibility = View.GONE
                binding.tvVocabTopicTitle.text = item.titleEnglish
                binding.tvVocabTopicUzbek.text = item.titleUzbek
                binding.tvVocabTopicSubtopicsMeta.text =
                    "${item.subtopicsCount} ta sub-topic • ${item.memorizedWordsCount}/${item.totalWordsCount} so'z"
                binding.tvVocabTopicBadge.text = if (item.newWordsBadgeCount > 0) {
                    "+${item.newWordsBadgeCount}"
                } else {
                    "✓ ${item.memorizedWordsCount}/${item.totalWordsCount}"
                }
                binding.cardVocabTopic.setOnClickListener { onTopicClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVocabularyTopicCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

/**
 * Step 2 of Vocabulary Flow: Sub-topics inside a selected Major Topic.
 * Clicking a Sub-topic opens FlipCardFragment scoped to that Sub-topic's vocabulary words.
 */
@AndroidEntryPoint
class VocabularySubtopicsFragment : Fragment() {

    private var _binding: FragmentVocabularySubtopicsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VocabularyHierarchyViewModel by viewModels()

    private val subtopicAdapter = VocabularySubtopicsAdapter { subtopic ->
        findNavController().navigate(
            R.id.flipCardFragment,
            bundleOf(
                AppConstants.ARG_TOPIC_ID to subtopic.topicId,
                AppConstants.ARG_SECTION_ID to subtopic.sectionId
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVocabularySubtopicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topicId = arguments?.getString(AppConstants.ARG_TOPIC_ID) ?: AppConstants.INITIAL_TOPIC_ID
        viewModel.selectTopic(topicId)

        binding.headerVocabSubtopics.btnBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        binding.rvVocabularySubtopics.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVocabularySubtopics.adapter = subtopicAdapter

        binding.btnReviewAllTopicWords.setOnClickListener {
            findNavController().navigate(
                R.id.flipCardFragment,
                bundleOf(AppConstants.ARG_TOPIC_ID to topicId)
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.topicCards.collect { topics ->
                        val topic = topics.find { it.topicId == topicId } ?: return@collect
                        binding.headerVocabSubtopics.tvHeaderTitle.text =
                            "${topic.orderIndex}. ${topic.titleEnglish} — ${topic.titleUzbek}"
                        binding.headerVocabSubtopics.tvHeaderSubtitle.text =
                            "Sub-topic tanlang va ichidagi Flip Cards orqali yodlang"

                        binding.ivMajorTopicHero.setImageResource(
                            TopicVisualArtwork.getTopicImageRes(topic.topicId, topic.orderIndex)
                        )
                        binding.tvMajorTopicHeroTitle.text =
                            "${topic.titleEnglish} (${topic.titleUzbek})"
                        binding.tvMajorTopicHeroDesc.text = topic.descriptionUzbek
                        binding.tvMajorTopicHeroBadge.text = if (topic.newWordsBadgeCount > 0) {
                            "+${topic.newWordsBadgeCount} yangi • ${topic.memorizedWordsCount}/${topic.totalWordsCount}"
                        } else {
                            "✓ ${topic.memorizedWordsCount}/${topic.totalWordsCount} so'z"
                        }
                    }
                }
                launch {
                    viewModel.subtopicCards.collect { subtopics ->
                        subtopicAdapter.submitList(subtopics)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private class VocabularySubtopicsAdapter(
    private val onSubtopicClick: (VocabularySubtopicCardModel) -> Unit
) : ListAdapter<VocabularySubtopicCardModel, VocabularySubtopicsAdapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<VocabularySubtopicCardModel>() {
        override fun areItemsTheSame(
            oldItem: VocabularySubtopicCardModel,
            newItem: VocabularySubtopicCardModel
        ): Boolean = oldItem.sectionId == newItem.sectionId

        override fun areContentsTheSame(
            oldItem: VocabularySubtopicCardModel,
            newItem: VocabularySubtopicCardModel
        ): Boolean = oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemVocabularySubtopicCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VocabularySubtopicCardModel) {
            binding.tvVocabSubtopicOrder.text =
                "${item.topicOrderIndex}.${item.sectionOrderIndex} SUB-TOPIC"
            binding.ivVocabSubtopicArtwork.setImageResource(
                TopicVisualArtwork.getSectionImageRes(
                    topicId = item.topicId,
                    sectionId = item.sectionId,
                    sectionOrderIndex = item.sectionOrderIndex
                )
            )
            binding.tvVocabSubtopicTitle.text = item.titleEnglish
            binding.tvVocabSubtopicUzbek.text = item.titleUzbek
            binding.tvVocabSubtopicWordsPreview.text = "So'zlar: ${item.wordsPreview}"
            binding.tvVocabSubtopicCountInfo.text =
                "${item.memorizedWordsCount}/${item.totalWordsCount} so'z yodlangan"
            binding.tvVocabSubtopicBadge.text = if (item.newWordsBadgeCount > 0) {
                "+${item.newWordsBadgeCount} yangi"
            } else {
                "✓ ${item.totalWordsCount}/${item.totalWordsCount}"
            }
            binding.cardVocabSubtopic.setOnClickListener { onSubtopicClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVocabularySubtopicCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

data class FlipCardUiState(
    val topicTitle: String = "",
    val subtopicTitle: String = "",
    val reviewQueue: List<VocabularyModel> = emptyList(),
    val currentIndex: Int = 0,
    val isShowingBack: Boolean = false
) {
    val currentWord: VocabularyModel?
        get() = reviewQueue.getOrNull(currentIndex)
}

@HiltViewModel
class FlipCardViewModel @Inject constructor(
    private val learningRepository: LearningRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlipCardUiState())
    val uiState: StateFlow<FlipCardUiState> = _uiState.asStateFlow()
    private var activeTopicIdFilter: String? = null
    private var activeSectionIdFilter: String? = null

    fun initializeQueue(topicIdFilter: String?, sectionIdFilter: String? = null) {
        activeTopicIdFilter = topicIdFilter
        activeSectionIdFilter = sectionIdFilter
        loadQueue(includeAllStudied = false)
    }

    fun loadQueue(includeAllStudied: Boolean) {
        viewModelScope.launch {
            val topicModel = activeTopicIdFilter?.let { learningRepository.getTopicById(it) }
            val sectionModel = activeSectionIdFilter?.let { learningRepository.getSectionById(it) }
            val queue = learningRepository.getFlipCardReviewQueue(
                includeAllStudied = includeAllStudied,
                topicIdFilter = activeTopicIdFilter,
                sectionIdFilter = activeSectionIdFilter
            )
            _uiState.value = FlipCardUiState(
                topicTitle = topicModel?.let { "${it.orderIndex}. ${it.titleEnglish} (${it.titleUzbek})" }.orEmpty(),
                subtopicTitle = sectionModel?.let { "${it.titleEnglish} — ${it.titleUzbek}" }.orEmpty(),
                reviewQueue = queue,
                currentIndex = 0,
                isShowingBack = false
            )
        }
    }

    fun toggleCardSide() {
        _uiState.value = _uiState.value.copy(isShowingBack = !_uiState.value.isShowingBack)
    }

    fun speakCurrentWord(includeExample: Boolean) {
        val word = _uiState.value.currentWord ?: return
        val text = if (includeExample) "${word.englishWord}. ${word.exampleEnglish}" else word.englishWord
        viewModelScope.launch {
            ttsManager.speakEnglish(text)
        }
    }

    fun submitRatingDecision(rating: FlipCardRating) {
        val state = _uiState.value
        val current = state.currentWord ?: return
        viewModelScope.launch {
            learningRepository.recordVocabularyReviewByRating(current.id, rating)
            val nextIdx = state.currentIndex + 1
            _uiState.value = state.copy(
                currentIndex = nextIdx,
                isShowingBack = false
            )
        }
    }
}

/**
 * Step 3 of Vocabulary Flow: Dedicated 3D Vocabulary Flip Card Review Fragment.
 * Rotates cards smoothly along the Y-axis with camera distance perspective,
 * shows 1-to-1 matching 3D word illustration, plays audio pronunciation on both sides,
 * and houses the 4-button Spaced Repetition rating dock (Qayta 10m, Qiyin 12h, Yaxshi 1d, Oson 4d).
 */
@AndroidEntryPoint
class FlipCardFragment : Fragment() {

    private var _binding: FragmentFlipCardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FlipCardViewModel by viewModels()
    private var isAnimatingFlip = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlipCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scale = resources.displayMetrics.density
        binding.cardFront.cameraDistance = AppConstants.FLIP_CAMERA_DISTANCE * scale
        binding.cardBack.cameraDistance = AppConstants.FLIP_CAMERA_DISTANCE * scale

        val topicIdFilter = arguments?.getString(AppConstants.ARG_TOPIC_ID)
        val sectionIdFilter = arguments?.getString(AppConstants.ARG_SECTION_ID)
        val isSequentialPlan = arguments?.getBoolean(AppConstants.ARG_SEQUENTIAL_DAILY_PLAN, false) ?: false
        viewModel.initializeQueue(topicIdFilter, sectionIdFilter)

        binding.headerFlipCards.tvHeaderTitle.text = getString(R.string.flip_cards_title)
        binding.headerFlipCards.tvHeaderSubtitle.text = getString(R.string.flip_cards_subtitle)
        binding.headerFlipCards.btnBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        // Tapping the body of either side rotates the card in 3D
        binding.layoutFrontTapArea.setOnClickListener {
            animate3DFlip()
        }
        binding.layoutBackTapArea.setOnClickListener {
            animate3DFlip()
        }

        binding.btnFrontSpeak.setOnClickListener {
            viewModel.speakCurrentWord(includeExample = false)
        }

        binding.btnBackSpeak.setOnClickListener {
            viewModel.speakCurrentWord(includeExample = true)
        }

        // Wire the 4 rating buttons inside both Front and Back of the card
        bindRatingBar(binding.ratingBarFront)
        bindRatingBar(binding.ratingBarBack)

        binding.btnResetFlipSession.setOnClickListener {
            if (isSequentialPlan) {
                val nav = findNavController()
                nav.popBackStack()
                nav.navigate(
                    R.id.exerciseSessionFragment,
                    bundleOf(
                        AppConstants.ARG_PRACTICE_CATEGORY to PracticeCategory.READING.id,
                        AppConstants.ARG_TOPIC_ID to (topicIdFilter ?: AppConstants.INITIAL_TOPIC_ID),
                        AppConstants.ARG_SEQUENTIAL_DAILY_PLAN to true
                    )
                )
            } else {
                resetCardRotationInstant()
                viewModel.loadQueue(includeAllStudied = true)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.subtopicTitle.isNotBlank()) {
                        binding.headerFlipCards.tvHeaderTitle.text = state.subtopicTitle
                        binding.headerFlipCards.tvHeaderSubtitle.text = state.topicTitle
                    } else if (state.topicTitle.isNotBlank()) {
                        binding.headerFlipCards.tvHeaderSubtitle.text = "Mavzu: ${state.topicTitle}"
                    }
                    val current = state.currentWord
                    if (current == null) {
                        binding.flipCardContainer.visibility = View.GONE
                        binding.tvFlipQueueCounter.visibility = View.GONE
                        binding.layoutFlipEmpty.visibility = View.VISIBLE
                        binding.btnResetFlipSession.text = if (isSequentialPlan) {
                            "Keyingi mashqqa o'tish (Reading) →"
                        } else {
                            getString(R.string.flip_reset_session)
                        }
                    } else {
                        binding.flipCardContainer.visibility = View.VISIBLE
                        binding.tvFlipQueueCounter.visibility = View.VISIBLE
                        binding.layoutFlipEmpty.visibility = View.GONE

                        binding.tvFlipQueueCounter.text =
                            "Card ${state.currentIndex + 1} of ${state.reviewQueue.size}"
                        binding.ivFlipWordIllustration.setImageResource(
                            TopicVisualArtwork.getWordImageRes(
                                englishWord = current.englishWord,
                                topicId = current.topicId,
                                sectionId = current.sectionId
                            )
                        )
                        binding.tvFrontEnglishWord.text = current.englishWord
                        binding.tvBackUzbekMeaning.text = current.uzbekTranslation
                        binding.tvBackExampleEnglish.text = current.exampleEnglish
                        binding.tvBackExampleUzbek.text = current.exampleUzbek
                    }
                }
            }
        }
    }

    private fun bindRatingBar(ratingBar: ViewFlipCardRatingBarBinding) {
        ratingBar.btnRateAgain.setOnClickListener {
            resetCardRotationInstant()
            viewModel.submitRatingDecision(FlipCardRating.AGAIN)
        }
        ratingBar.btnRateHard.setOnClickListener {
            resetCardRotationInstant()
            viewModel.submitRatingDecision(FlipCardRating.HARD)
        }
        ratingBar.btnRateGood.setOnClickListener {
            resetCardRotationInstant()
            viewModel.submitRatingDecision(FlipCardRating.GOOD)
        }
        ratingBar.btnRateEasy.setOnClickListener {
            resetCardRotationInstant()
            viewModel.submitRatingDecision(FlipCardRating.EASY)
        }
    }

    private fun animate3DFlip() {
        if (isAnimatingFlip) return
        val b = _binding ?: return
        isAnimatingFlip = true

        val showingBack = viewModel.uiState.value.isShowingBack
        val visibleCard = if (showingBack) b.cardBack else b.cardFront
        val hiddenCard = if (showingBack) b.cardFront else b.cardBack

        val outAnim = ObjectAnimator.ofFloat(visibleCard, View.ROTATION_Y, 0f, 90f).apply {
            duration = AppConstants.FLIP_HALF_DURATION_MS
            interpolator = AccelerateDecelerateInterpolator()
        }

        outAnim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibleCard.visibility = View.GONE
                visibleCard.rotationY = 0f
                hiddenCard.rotationY = -90f
                hiddenCard.visibility = View.VISIBLE

                val inAnim = ObjectAnimator.ofFloat(hiddenCard, View.ROTATION_Y, -90f, 0f).apply {
                    duration = AppConstants.FLIP_HALF_DURATION_MS
                    interpolator = AccelerateDecelerateInterpolator()
                }
                inAnim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        viewModel.toggleCardSide()
                        isAnimatingFlip = false
                    }
                })
                inAnim.start()
            }
        })
        outAnim.start()
    }

    private fun resetCardRotationInstant() {
        val b = _binding ?: return
        b.cardFront.rotationY = 0f
        b.cardBack.rotationY = 0f
        b.cardFront.visibility = View.VISIBLE
        b.cardBack.visibility = View.GONE
        isAnimatingFlip = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
