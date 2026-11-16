package com.example.feature.pro

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.databinding.FragmentProLessonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProLessonFragment : Fragment() {
    private var _binding: FragmentProLessonBinding? = null
    private val viewModel: ProLessonViewModel by viewModels()
    private val microphone = registerForActivityResult(ActivityResultContracts.RequestPermission()) { allowed ->
        if (allowed) viewModel.useMicrophone() else viewModel.permissionDenied()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentProLessonBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = checkNotNull(_binding)
        binding.proReading.movementMethod = android.text.method.ScrollingMovementMethod.getInstance()
        binding.proBack.setOnClickListener { findNavController().popBackStack() }
        binding.proAction.setOnClickListener {
            if (viewModel.ui.value.action == ProAction.MIC) {
                val permission = Manifest.permission.RECORD_AUDIO
                if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
                    viewModel.useMicrophone()
                } else {
                    microphone.launch(permission)
                }
            } else if (viewModel.ui.value.action == ProAction.BLOCKED) {
                findNavController().popBackStack()
            } else {
                viewModel.pressAction()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.ui.collect(::render) }
                launch {
                    viewModel.amplitude.collect { amount ->
                        if (viewModel.ui.value.action == ProAction.LISTENING) binding.proWaveform.setAmplitude(amount)
                    }
                }
            }
        }
    }

    private fun render(state: ProLessonState) {
        val binding = _binding ?: return
        if (state.action == ProAction.DONE) {
            val nav = findNavController()
            if (!nav.popBackStack(R.id.topicDetailFragment, false)) nav.popBackStack()
            return
        }
        binding.proProgress.text = getString(R.string.pro_progress, state.stage.ordinal + 1, ProStage.entries.size)
        binding.proReading.isVisible = state.reading.isNotBlank()
        binding.proReading.text = state.reading
        binding.proStatus.setText(state.status)
        binding.proAvatar.contentDescription = getString(if (state.female) R.string.pro_avatar_girl else R.string.pro_avatar_boy)
        binding.proAvatar.render(state)
        binding.proWaveform.isVisible = state.action == ProAction.PLAYING || state.action == ProAction.LISTENING
        binding.proWaveform.setActive(binding.proWaveform.isVisible)
        if (state.action == ProAction.PLAYING) binding.proWaveform.setAmplitude(.56f)
        if (state.action == ProAction.LISTENING) binding.proWaveform.setAmplitude(viewModel.amplitude.value)

        val icon = when (state.action) {
            ProAction.PLAYING, ProAction.LISTENING -> R.drawable.ic_pause
            ProAction.PLAY -> R.drawable.ic_play_arrow
            ProAction.MIC -> R.drawable.ic_mic
            else -> null
        }
        binding.proAction.isEnabled = state.action != ProAction.BUSY
        binding.proAction.icon = icon?.let { ContextCompat.getDrawable(requireContext(), it) }
        val label = when (state.action) {
            ProAction.PLAYING -> R.string.pro_pause
            ProAction.PLAY -> R.string.pro_play
            ProAction.MIC -> R.string.pro_answer
            ProAction.LISTENING -> R.string.pro_stop
            ProAction.NEXT -> R.string.pro_continue
            ProAction.FINISH -> R.string.pro_finish
            ProAction.BLOCKED -> R.string.pro_back
            else -> R.string.pro_processing
        }
        binding.proAction.contentDescription = getString(label)
        binding.proAction.text = if (icon == null) getString(label) else ""
        val width = if (icon == null) ViewGroup.LayoutParams.MATCH_PARENT
            else resources.getDimensionPixelSize(R.dimen.pro_voice_control)
        if (binding.proAction.layoutParams.width != width) {
            binding.proAction.layoutParams = binding.proAction.layoutParams.apply { this.width = width }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onVisible()
    }

    override fun onPause() {
        viewModel.onHidden()
        super.onPause()
    }

    override fun onDestroyView() {
        _binding?.proWaveform?.setActive(false)
        _binding = null
        super.onDestroyView()
    }
}
