package com.example.feature.materials

import android.os.Bundle
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.core.ui.GrammarRuleAdapter
import com.example.core.ui.VocabularyAdapter
import com.example.core.ui.curriculumLevelName
import com.example.databinding.FragmentLessonMaterialsBinding
import com.example.databinding.ItemMaterialLessonBinding
import com.example.domain.model.GrammarRuleModel
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.PracticeCategory
import com.example.domain.model.TopicModel
import com.example.domain.model.VocabularyModel
import com.example.domain.repository.LearningRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MaterialSelection(val topic: String? = null, val section: String? = null, val grammar: Boolean = false)
data class MaterialLesson(val lesson: LessonSectionModel, val count: Int)
data class LessonMaterialsUi(
    val selection: MaterialSelection = MaterialSelection(),
    val topics: List<TopicModel> = emptyList(),
    val lessons: List<LessonSectionModel> = emptyList(),
    val index: List<MaterialLesson> = emptyList(),
    val words: List<VocabularyModel> = emptyList(),
    val rules: List<GrammarRuleModel> = emptyList()
)

@HiltViewModel
class LessonMaterialsViewModel @Inject constructor(
    private val repository: LearningRepository,
    private val state: SavedStateHandle,
    private val tts: TtsManager
) : ViewModel() {
    private val topic = state.getStateFlow<String?>(KEY_TOPIC, state.get<String>(AppConstants.ARG_TOPIC_ID))
    private val section = state.getStateFlow<String?>(KEY_SECTION, state.get<String>(AppConstants.ARG_SECTION_ID))
    private val grammar = state.getStateFlow(KEY_GRAMMAR,
        state.get<String>(AppConstants.ARG_PRACTICE_CATEGORY) == PracticeCategory.GRAMMAR.id)
    private val query = state.getStateFlow(KEY_QUERY, "")

    private val selection = combine(topic, section, grammar, query) { t, s, g, q ->
        MaterialSelection(t, s, g) to normalize(q)
    }
    val ui = combine(repository.observeTopics(), repository.observeLibrarySections(),
        repository.observeLibraryVocabulary(), repository.observeLibraryGrammar(), selection
    ) { topics, sections, vocabulary, rules, (filter, query) ->
        val order = topics.associate { it.id to it.orderIndex }
        val inLevel = sections.filter { filter.topic == null || it.topicId == filter.topic }
            .sortedWith(compareBy({ order[it.topicId] ?: Int.MAX_VALUE }, { it.orderIndex }))
        val permitted = inLevel.associateBy { it.id }
        fun matchesTitle(id: String) = permitted[id]?.let { matches(query, it.titleEnglish, it.titleUzbek) } == true
        val words = if (filter.grammar) emptyList() else vocabulary.filter { item ->
            item.sectionId in permitted && (matchesTitle(item.sectionId) ||
                matches(query, item.englishWord, item.uzbekTranslation, item.exampleEnglish, item.exampleUzbek))
        }
        val grammarRules = if (!filter.grammar) emptyList() else rules.filter { item ->
            item.sectionId in permitted && (matchesTitle(item.sectionId) ||
                matches(query, item.titleUzbek, item.explanationUzbek, item.exampleEnglish, item.exampleUzbek))
        }
        val sizes = if (filter.grammar) grammarRules.groupingBy { it.sectionId }.eachCount()
            else words.groupingBy { it.sectionId }.eachCount()
        LessonMaterialsUi(
            filter, topics, inLevel,
            inLevel.mapNotNull { lesson -> sizes[lesson.id]?.let { MaterialLesson(lesson, it) } },
            words.filter { it.sectionId == filter.section },
            grammarRules.filter { it.sectionId == filter.section }
        )
    }.flowOn(Dispatchers.Default).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LessonMaterialsUi())

    init {
        viewModelScope.launch { repository.ensureSeeded() }
    }

    fun updateQuery(value: String) { state[KEY_QUERY] = value }
    fun queryText(): String = query.value
    fun selectTopic(value: String?) {
        if (topic.value == value) return
        state[KEY_SECTION] = null
        state[KEY_TOPIC] = value
    }
    fun selectSection(value: String?) { state[KEY_SECTION] = value }
    fun selectGrammar(value: Boolean) { state[KEY_GRAMMAR] = value }

    fun speak(text: String) {
        viewModelScope.launch { if (text.isNotBlank()) tts.speakEnglish(text) }
    }
    fun stopAudio() { tts.stop() }
    override fun onCleared() { stopAudio(); super.onCleared() }

    private fun matches(query: String, vararg values: String) =
        query.isBlank() || values.any { normalize(it).contains(query) }

    private fun normalize(value: String) = value.trim().lowercase(Locale.ROOT)
        .replace(Regex("['’‘ʻʼ]"), "")

    private companion object {
        const val KEY_TOPIC = "materials_topic"
        const val KEY_SECTION = "materials_section"
        const val KEY_GRAMMAR = "materials_grammar"
        const val KEY_QUERY = "materials_query"
    }
}

@AndroidEntryPoint
class LessonMaterialsFragment : Fragment() {
    private var _binding: FragmentLessonMaterialsBinding? = null
    private val model: LessonMaterialsViewModel by viewModels()
    private var topicIds: List<String?> = emptyList()
    private var lessonIds: List<String?> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saved: Bundle?): View =
        FragmentLessonMaterialsBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = requireNotNull(_binding)
        val words = VocabularyAdapter(model::speak)
        val rules = GrammarRuleAdapter(model::speak)
        val index = MaterialLessonAdapter { model.selectSection(it.lesson.id) }
        binding.materialList.layoutManager = LinearLayoutManager(requireContext())
        binding.materialBack.setOnClickListener { findNavController().navigateUp() }
        binding.vocabularyTab.setOnClickListener { model.selectGrammar(false) }
        binding.grammarTab.setOnClickListener { model.selectGrammar(true) }
        binding.materialSearch.setText(model.queryText())
        binding.materialSearch.doAfterTextChanged { model.updateQuery(it.toString()) }
        binding.materialLevel.onItemSelectedListener = listener { topicIds.getOrNull(it).let(model::selectTopic) }
        binding.materialLesson.onItemSelectedListener = listener { lessonIds.getOrNull(it).let(model::selectSection) }
        binding.materialFlip.setOnClickListener {
            val selected = model.ui.value
            val lesson = selected.lessons.firstOrNull { it.id == selected.selection.section } ?: return@setOnClickListener
            findNavController().navigate(R.id.flipCardFragment, bundleOf(
                AppConstants.ARG_TOPIC_ID to lesson.topicId, AppConstants.ARG_SECTION_ID to lesson.id
            ))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                model.ui.collect { page ->
                    if (page.topics.isEmpty()) return@collect
                    val nextTopics = listOf(null) + page.topics.map { it.id }
                    if (nextTopics != topicIds) {
                        topicIds = nextTopics
                        binding.materialLevel.adapter = spinnerAdapter(
                            listOf(getString(R.string.lesson_materials_all_levels)) +
                                page.topics.map { curriculumLevelName(requireContext(), it) })
                    }
                    val nextLessons = listOf(null) + page.lessons.map { it.id }
                    if (nextLessons != lessonIds) {
                        lessonIds = nextLessons
                        binding.materialLesson.adapter = spinnerAdapter(
                            listOf(getString(R.string.lesson_materials_all_lessons)) + page.lessons.map { it.titleEnglish })
                    }
                    val t = topicIds.indexOf(page.selection.topic).coerceAtLeast(0)
                    val l = lessonIds.indexOf(page.selection.section).coerceAtLeast(0)
                    if (binding.materialLevel.selectedItemPosition != t) binding.materialLevel.setSelection(t)
                    if (binding.materialLesson.selectedItemPosition != l) binding.materialLesson.setSelection(l)
                    binding.vocabularyTab.isChecked = !page.selection.grammar
                    binding.grammarTab.isChecked = page.selection.grammar
                    binding.materialFlip.isVisible = page.selection.section != null && !page.selection.grammar && page.words.isNotEmpty()
                    val count = if (page.selection.section == null) {
                        if (binding.materialList.adapter !== index) binding.materialList.adapter = index
                        index.submitList(page.index)
                        page.index.size
                    } else if (page.selection.grammar) {
                        if (binding.materialList.adapter !== rules) binding.materialList.adapter = rules
                        rules.submitList(page.rules)
                        page.rules.size
                    } else {
                        if (binding.materialList.adapter !== words) binding.materialList.adapter = words
                        words.submitList(page.words)
                        page.words.size
                    }
                    binding.materialEmpty.isVisible = count == 0
                }
            }
        }
    }

    private fun spinnerAdapter(labels: List<String>): ArrayAdapter<String> {
        val context = requireContext()
        val tint = ContextCompat.getColor(context, R.color.color_on_surface)
        return object : ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, labels) {
            override fun getView(position: Int, view: View?, parent: ViewGroup): View =
                super.getView(position, view, parent).also { (it as? TextView)?.setTextColor(tint) }
            override fun getDropDownView(position: Int, view: View?, parent: ViewGroup): View =
                super.getDropDownView(position, view, parent).also { item ->
                    (item as? TextView)?.setTextColor(tint)
                    item.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_cosmic_card))
                }
        }
    }

    private fun listener(action: (Int) -> Unit) = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = action(position)
    }

    override fun onPause() { model.stopAudio(); super.onPause() }
    override fun onDestroyView() {
        _binding?.materialList?.adapter = null
        _binding = null
        topicIds = emptyList()
        lessonIds = emptyList()
        super.onDestroyView()
    }
}

private class MaterialLessonAdapter(private val onClick: (MaterialLesson) -> Unit) :
    ListAdapter<MaterialLesson, MaterialLessonAdapter.Holder>(object : DiffUtil.ItemCallback<MaterialLesson>() {
        override fun areItemsTheSame(old: MaterialLesson, new: MaterialLesson) = old.lesson.id == new.lesson.id
        override fun areContentsTheSame(old: MaterialLesson, new: MaterialLesson) = old == new
    }) {
    class Holder(val binding: ItemMaterialLessonBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemMaterialLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            materialLessonTitle.text = item.lesson.titleEnglish
            materialLessonCount.text = root.context.getString(R.string.lesson_materials_count, item.count)
            root.setOnClickListener { onClick(item) }
        }
    }
}
