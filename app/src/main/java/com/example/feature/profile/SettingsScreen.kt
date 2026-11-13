package com.example.feature.profile

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.core.audio.TtsManager
import com.example.databinding.FragmentSettingsBinding
import com.example.domain.model.UserPreferencesModel
import com.example.domain.repository.UserPreferencesRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    val preferencesState: StateFlow<UserPreferencesModel> = preferencesRepository.userPreferencesFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = UserPreferencesModel()
        )

    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            preferencesRepository.updateThemeMode(mode)
        }
    }

    fun setSoundEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateSoundEnabled(enabled)
        }
    }

    fun setSpeechSpeed(speed: Float) {
        viewModelScope.launch {
            preferencesRepository.updateSpeechSpeed(speed)
        }
    }

    fun setVoiceEngine(useAiEngine: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updatePremiumSubscribed(useAiEngine)
            preferencesRepository.updatePreferredTtsProvider(if (useAiEngine) "GEMINI" else "NATIVE")
        }
    }

    fun setVoiceGender(gender: String) {
        viewModelScope.launch {
            preferencesRepository.updateVoiceGender(gender)
        }
    }

    fun previewVoice(gender: String) {
        val normalized = if (gender.equals("FEMALE", ignoreCase = true)) "FEMALE" else "MALE"
        viewModelScope.launch {
            preferencesRepository.updateVoiceGender(normalized)
            val sample = if (normalized == "FEMALE") {
                "Hello! I am your female English voice coach."
            } else {
                "Hello! Let's practice clear English pronunciation together."
            }
            ttsManager.speakEnglish(
                text = sample,
                overrideVoiceGender = normalized
            )
        }
    }

    fun setOfflineAudioEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateOfflineAudioEnabled(enabled)
        }
    }

    fun setLessonRemindersEnabled(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updateLessonRemindersEnabled(enabled)
        }
    }

    fun stopSpeech() {
        ttsManager.stop()
    }

    fun getAudioCacheSizeBytes(): Long = ttsManager.getCachedAudioSizeBytes()

    fun clearAudioCache(): Long = ttsManager.clearCachedAudio()

    override fun onCleared() {
        super.onCleared()
        ttsManager.stop()
    }
}

/**
 * Dedicated Settings Fragment opened from the gear icon button in ProfileFragment.
 * Hosts Appearance, Voice & Pronunciation (with male/female preview), Study Settings, and Privacy/App dialogs.
 */
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()
    private var isUpdatingControlsProgrammatically = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSettingsBack.setOnClickListener {
            val nav = findNavController()
            if (!nav.popBackStack()) {
                nav.navigateUp()
            }
        }

        setupControls()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.preferencesState.collect { prefs ->
                    renderPreferences(prefs)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopSpeech()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupControls() {
        binding.toggleThemeMode.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isUpdatingControlsProgrammatically || !isChecked) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.btnThemeSystem -> viewModel.setThemeMode("SYSTEM")
                R.id.btnThemeLight -> viewModel.setThemeMode("LIGHT")
                R.id.btnThemeDark -> viewModel.setThemeMode("DARK")
            }
        }

        binding.switchSoundEnabled.setOnCheckedChangeListener { _, isChecked ->
            if (!isUpdatingControlsProgrammatically) {
                viewModel.setSoundEnabled(isChecked)
            }
        }

        binding.seekSpeechSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val speed = ((progress.coerceIn(0, 75)) + 75) / 100f
                binding.tvSpeechSpeedValue.text = String.format(Locale.US, "%.2f×", speed)
                if (fromUser && !isUpdatingControlsProgrammatically) {
                    viewModel.setSpeechSpeed(speed)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        })

        binding.toggleVoiceEngine.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isUpdatingControlsProgrammatically || !isChecked) return@addOnButtonCheckedListener
            viewModel.setVoiceEngine(checkedId == R.id.btnVoiceEngineAi)
        }

        binding.cardVoiceMale.setOnClickListener {
            viewModel.setVoiceGender("MALE")
        }
        binding.cardVoiceFemale.setOnClickListener {
            viewModel.setVoiceGender("FEMALE")
        }
        binding.btnPreviewVoiceMale.setOnClickListener {
            viewModel.previewVoice("MALE")
        }
        binding.btnPreviewVoiceFemale.setOnClickListener {
            viewModel.previewVoice("FEMALE")
        }

        binding.switchOfflineAudio.setOnCheckedChangeListener { _, isChecked ->
            if (!isUpdatingControlsProgrammatically) {
                viewModel.setOfflineAudioEnabled(isChecked)
            }
        }
        binding.switchLessonReminders.setOnCheckedChangeListener { _, isChecked ->
            if (!isUpdatingControlsProgrammatically) {
                viewModel.setLessonRemindersEnabled(isChecked)
            }
        }

        binding.rowPrivacyPolicy.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.profile_privacy_policy)
                .setMessage(R.string.settings_about_privacy)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }

        binding.rowDataManagement.setOnClickListener {
            val bytes = viewModel.getAudioCacheSizeBytes()
            val kb = bytes / 1024f
            val sizeLabel = if (kb >= 1024f) {
                String.format(Locale.US, "%.2f MB", kb / 1024f)
            } else {
                String.format(Locale.US, "%.1f KB", kb)
            }
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.profile_data_management)
                .setMessage(getString(R.string.settings_data_management_message_format, sizeLabel))
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(R.string.settings_clear_cache) { _, _ ->
                    viewModel.clearAudioCache()
                    Toast.makeText(requireContext(), R.string.settings_cache_cleared, Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        binding.rowAboutApp.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.settings_section_about)
                .setMessage(R.string.settings_about_version)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }

    private fun renderPreferences(prefs: UserPreferencesModel) {
        isUpdatingControlsProgrammatically = true
        try {
            val targetThemeBtnId = when (prefs.themeMode) {
                "LIGHT" -> R.id.btnThemeLight
                "DARK" -> R.id.btnThemeDark
                else -> R.id.btnThemeSystem
            }
            if (binding.toggleThemeMode.checkedButtonId != targetThemeBtnId) {
                binding.toggleThemeMode.check(targetThemeBtnId)
            }
            styleSegmentButtons(
                listOf(binding.btnThemeSystem, binding.btnThemeLight, binding.btnThemeDark),
                targetThemeBtnId,
                selectedColorRes = R.color.color_profile_purple_pill_bg
            )

            if (binding.switchSoundEnabled.isChecked != prefs.soundEnabled) {
                binding.switchSoundEnabled.isChecked = prefs.soundEnabled
            }

            val clampedSpeed = prefs.speechSpeed.coerceIn(0.75f, 1.50f)
            val speedProgress = ((clampedSpeed * 100).toInt() - 75).coerceIn(0, 75)
            if (binding.seekSpeechSpeed.progress != speedProgress) {
                binding.seekSpeechSpeed.progress = speedProgress
            }
            binding.tvSpeechSpeedValue.text = String.format(Locale.US, "%.2f×", clampedSpeed)

            val useAiEngine = prefs.preferredTtsProvider.equals("GEMINI", ignoreCase = true)
            val targetEngineId = if (useAiEngine) R.id.btnVoiceEngineAi else R.id.btnVoiceEngineNative
            if (binding.toggleVoiceEngine.checkedButtonId != targetEngineId) {
                binding.toggleVoiceEngine.check(targetEngineId)
            }
            styleSegmentButtons(
                listOf(binding.btnVoiceEngineNative, binding.btnVoiceEngineAi),
                targetEngineId,
                selectedColorRes = R.color.color_profile_purple_pill_bg
            )

            renderVoiceGenderSelection(prefs.voiceGender)

            if (binding.switchOfflineAudio.isChecked != prefs.offlineAudioEnabled) {
                binding.switchOfflineAudio.isChecked = prefs.offlineAudioEnabled
            }
            if (binding.switchLessonReminders.isChecked != prefs.lessonRemindersEnabled) {
                binding.switchLessonReminders.isChecked = prefs.lessonRemindersEnabled
            }
        } finally {
            isUpdatingControlsProgrammatically = false
        }
    }

    private fun styleSegmentButtons(
        buttons: List<MaterialButton>,
        selectedId: Int,
        selectedColorRes: Int
    ) {
        val ctx = requireContext()
        val selectedBg = ContextCompat.getColor(ctx, selectedColorRes)
        val transparentBg = ContextCompat.getColor(ctx, android.R.color.transparent)
        val white = ContextCompat.getColor(ctx, R.color.white)
        val muted = ContextCompat.getColor(ctx, R.color.color_slate_subtitle)

        buttons.forEach { btn ->
            val selected = btn.id == selectedId
            btn.backgroundTintList = ColorStateList.valueOf(if (selected) selectedBg else transparentBg)
            btn.setTextColor(if (selected) white else muted)
        }
    }

    private fun renderVoiceGenderSelection(voiceGender: String) {
        val isFemale = voiceGender.equals("FEMALE", ignoreCase = true)
        if (isFemale) {
            binding.cardVoiceMale.setBackgroundResource(R.drawable.bg_profile_voice_unselected)
            binding.cardVoiceFemale.setBackgroundResource(R.drawable.bg_profile_voice_selected)
            binding.ivVoiceMaleCheck.setImageResource(R.drawable.bg_studio_radio_ring)
            binding.ivVoiceMaleCheck.imageTintList = null
            binding.ivVoiceFemaleCheck.setImageResource(R.drawable.ic_check_circle)
            binding.ivVoiceFemaleCheck.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.color_neon_cyan)
            )
        } else {
            binding.cardVoiceMale.setBackgroundResource(R.drawable.bg_profile_voice_selected)
            binding.cardVoiceFemale.setBackgroundResource(R.drawable.bg_profile_voice_unselected)
            binding.ivVoiceMaleCheck.setImageResource(R.drawable.ic_check_circle)
            binding.ivVoiceMaleCheck.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireContext(), R.color.color_neon_cyan)
            )
            binding.ivVoiceFemaleCheck.setImageResource(R.drawable.bg_studio_radio_ring)
            binding.ivVoiceFemaleCheck.imageTintList = null
        }
    }
}
