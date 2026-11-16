package com.example.feature.mistakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.databinding.FragmentMistakesBinding
import com.example.databinding.ItemMistakeBinding
import com.example.domain.model.UserMistakeModel
import com.example.domain.repository.LearningRepository
import com.example.domain.usecase.CurriculumDomainLogic
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

private data class MistakeRow(val mistake: UserMistakeModel, val resolved: Boolean)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MistakesViewModel @Inject constructor(
    private val repository: LearningRepository
) : ViewModel() {
    private val resolved = MutableStateFlow(false)
    val entries = resolved.flatMapLatest { done ->
        val mistakes = if (done) repository.observeResolvedMistakes() else repository.observeUnresolvedMistakes()
        mistakes.map { done to it }
    }

    fun selectResolved(done: Boolean) {
        resolved.value = done
    }

    fun review(mistake: UserMistakeModel, answer: String, onResult: (Boolean) -> Unit) {
        val correct = CurriculumDomainLogic.answersMatch(mistake.correctAnswer, answer)
        viewModelScope.launch {
            if (correct) repository.resolveUserMistake(mistake.id)
            onResult(correct)
        }
    }
}

@AndroidEntryPoint
class MistakesFragment : Fragment() {
    private var _binding: FragmentMistakesBinding? = null
    private val viewModel: MistakesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View =
        FragmentMistakesBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = requireNotNull(_binding)
        val adapter = MistakeAdapter { mistake, answer ->
            viewModel.review(mistake, answer) { correct ->
                _binding?.root?.let { root ->
                    Snackbar.make(root, if (correct) R.string.mistakes_solved else R.string.mistakes_try_again,
                        Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        binding.mistakeList.layoutManager = LinearLayoutManager(requireContext())
        binding.mistakeList.adapter = adapter
        binding.openMistakes.setOnClickListener { viewModel.selectResolved(false) }
        binding.resolvedMistakes.setOnClickListener { viewModel.selectResolved(true) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entries.collect { (done, mistakes) ->
                    binding.openMistakes.isChecked = !done
                    binding.resolvedMistakes.isChecked = done
                    binding.emptyMistakes.isVisible = mistakes.isEmpty()
                    binding.emptyMistakes.setText(if (done) R.string.mistakes_empty_done else R.string.mistakes_empty_open)
                    adapter.submitList(mistakes.map { MistakeRow(it, done) })
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding?.mistakeList?.adapter = null
        _binding = null
        super.onDestroyView()
    }
}

private class MistakeAdapter(
    private val onReview: (UserMistakeModel, String) -> Unit
) : ListAdapter<MistakeRow, MistakeAdapter.Holder>(object : DiffUtil.ItemCallback<MistakeRow>() {
    override fun areItemsTheSame(old: MistakeRow, new: MistakeRow) =
        old.mistake.id == new.mistake.id && old.resolved == new.resolved
    override fun areContentsTheSame(old: MistakeRow, new: MistakeRow) = old == new
}) {
    class Holder(val binding: ItemMistakeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(ItemMistakeBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) = with(holder.binding) {
        val row = getItem(position)
        val mistake = row.mistake
        val context = root.context
        mistakeTopic.text = mistake.topicTitle
        mistakePrompt.text = mistake.itemPrompt
        previousAnswer.text = context.getString(R.string.mistakes_previous_answer, mistake.userWrongAnswer)
        correctAnswer.text = context.getString(R.string.mistakes_correct_answer, mistake.correctAnswer)
        correctAnswer.isVisible = row.resolved
        retryAnswer.isVisible = !row.resolved
        checkAnswer.isVisible = !row.resolved
        retryAnswer.text?.clear()
        hintText.text = mistake.explanationUzbek
        hintText.isVisible = false
        showHint.isVisible = mistake.explanationUzbek.isNotBlank()
        showHint.setOnClickListener { hintText.isVisible = !hintText.isVisible }
        checkAnswer.setOnClickListener { onReview(mistake, retryAnswer.text.toString()) }
    }
}
