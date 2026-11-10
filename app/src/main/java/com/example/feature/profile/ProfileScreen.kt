package com.example.feature.profile

import android.content.Context
import android.content.res.Configuration
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
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
import com.example.core.ui.SkillAnalyticsChartView
import com.example.databinding.FragmentProfileBinding
import com.example.domain.model.ChartPeriodFilter
import com.example.domain.model.LearningDashboardStats
import com.example.domain.model.PracticeSessionRecord
import com.example.domain.model.SkillPerformanceMetric
import com.example.domain.model.UserPreferencesModel
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.UserPreferencesRepository
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ProfileUiState(
    val stats: LearningDashboardStats? = null,
    val skillMetrics: List<SkillPerformanceMetric> = emptyList(),
    val sessionRecords: List<PracticeSessionRecord> = emptyList(),
    val selectedPeriod: ChartPeriodFilter = ChartPeriodFilter.YEARLY,
    val preferences: UserPreferencesModel = UserPreferencesModel()
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    learningRepository: LearningRepository,
    private val preferencesRepository: UserPreferencesRepository,
    private val ttsManager: TtsManager
) : ViewModel() {

    private val selectedPeriodFlow = MutableStateFlow(ChartPeriodFilter.YEARLY)

    val uiState: StateFlow<ProfileUiState> = combine(
        learningRepository.observeDashboardStats(),
        learningRepository.observeSkillPerformanceMetrics(),
        learningRepository.observePracticeSessionRecords(),
        selectedPeriodFlow,
        preferencesRepository.userPreferencesFlow
    ) { stats, skills, records, period, prefs ->
        ProfileUiState(
            stats = stats,
            skillMetrics = skills,
            sessionRecords = records,
            selectedPeriod = period,
            preferences = prefs
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ProfileUiState()
    )

    fun setChartPeriod(period: ChartPeriodFilter) {
        selectedPeriodFlow.value = period
    }

    fun saveUserName(name: String) {
        val trimmed = name.trim()
        if (trimmed.isNotEmpty()) {
            viewModelScope.launch {
                preferencesRepository.updateUserName(trimmed)
            }
        }
    }

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

    fun setPremiumSubscribed(subscribed: Boolean) {
        viewModelScope.launch {
            preferencesRepository.updatePremiumSubscribed(subscribed)
        }
    }

    fun setPreferredTtsProvider(provider: String) {
        viewModelScope.launch {
            preferencesRepository.updatePreferredTtsProvider(provider)
        }
    }

    fun getAudioCacheSizeKb(): Long {
        return ttsManager.getCachedAudioSizeBytes() / 1024L
    }

    fun clearAudioCache() {
        ttsManager.clearCachedAudio()
    }
}

/**
 * Unified Telegram-Style Profile & Settings Fragment.
 * - Tapping the Avatar or Edit Profile opens the interactive Edit Profile & Avatar dialog.
 * - #1 Priority Settings: System Default Theme + Dark/Light Mode Switcher.
 * - Telegram-style grouped cells for Account Info, Audio & TTS, Analytics Chart, and Storage.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()
    private var isUpdatingControlsProgrammatically = false

    private val avatarDrawableOptions = listOf(
        R.drawable.img_ai_tutor_avatar_1794304412196,
        R.drawable.img_topic_pronouns_1794316352550,
        R.drawable.img_lesson_conversation_1794310851367,
        R.drawable.img_topic_morning_intro_1794316347713
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        applySavedAvatar()

        // 1. Tapping Avatar or Edit Profile opens the Telegram-style Edit Profile Dialog
        val openEditProfileListener = View.OnClickListener {
            showTelegramEditProfileDialog()
        }
        binding.containerProfileAvatarTap.setOnClickListener(openEditProfileListener)
        binding.btnEditProfileHeader.setOnClickListener(openEditProfileListener)
        binding.rowEditProfileName.setOnClickListener(openEditProfileListener)

        // 2. Tapping Vocabulary row opens the Vocabulary Major Topics -> Subtopics -> Flip Cards flow
        binding.rowProfileVocabulary.setOnClickListener {
            findNavController().navigate(R.id.vocabularyTopicsFragment)
        }

        // 3. #1 Priority Theme Switchers (System Default + Dark/Light Mode Switcher)
        binding.switchSystemTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingControlsProgrammatically) return@setOnCheckedChangeListener
            if (isChecked) {
                viewModel.setThemeMode("SYSTEM")
            } else {
                val isCurrentlyNight = isSystemCurrentlyNightMode()
                viewModel.setThemeMode(if (isCurrentlyNight) "DARK" else "LIGHT")
            }
        }

        binding.switchDarkModeQuick.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingControlsProgrammatically) return@setOnCheckedChangeListener
            viewModel.setThemeMode(if (isChecked) "DARK" else "LIGHT")
        }

        binding.toggleThemeMode.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isUpdatingControlsProgrammatically || !isChecked) return@addOnButtonCheckedListener
            when (checkedId) {
                R.id.btnThemeSystem -> viewModel.setThemeMode("SYSTEM")
                R.id.btnThemeLight -> viewModel.setThemeMode("LIGHT")
                R.id.btnThemeDark -> viewModel.setThemeMode("DARK")
            }
        }

        // 4. Audio & TTS Settings
        binding.switchSoundEnabled.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingControlsProgrammatically) return@setOnCheckedChangeListener
            viewModel.setSoundEnabled(isChecked)
        }

        binding.seekSpeechSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val speed = (progress.coerceIn(50, 150)) / 100f
                binding.tvSpeechSpeedValue.text = String.format(Locale.US, "%.2fx", speed)
                if (fromUser && !isUpdatingControlsProgrammatically) {
                    viewModel.setSpeechSpeed(speed)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.switchPremiumEnabled.setOnCheckedChangeListener { _, isChecked ->
            if (isUpdatingControlsProgrammatically) return@setOnCheckedChangeListener
            viewModel.setPremiumSubscribed(isChecked)
        }

        binding.rgTtsProvider.setOnCheckedChangeListener { _, checkedId ->
            if (isUpdatingControlsProgrammatically) return@setOnCheckedChangeListener
            when (checkedId) {
                R.id.rbTtsGemini -> viewModel.setPreferredTtsProvider("GEMINI")
                else -> viewModel.setPreferredTtsProvider("NATIVE")
            }
        }

        // 5. Chart Period Filter (Hafta | Oy | Yillik)
        binding.toggleChartPeriod.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnFilterWeekly -> viewModel.setChartPeriod(ChartPeriodFilter.WEEKLY)
                    R.id.btnFilterMonthly -> viewModel.setChartPeriod(ChartPeriodFilter.MONTHLY)
                    R.id.btnFilterYearly -> viewModel.setChartPeriod(ChartPeriodFilter.YEARLY)
                }
            }
        }

        // 6. Storage & Audio Cache
        updateCacheLabel()
        binding.btnClearAudioCache.setOnClickListener {
            viewModel.clearAudioCache()
            updateCacheLabel()
            Toast.makeText(requireContext(), getString(R.string.settings_cache_cleared), Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val stats = state.stats
                    val prefs = state.preferences

                    val displayName = stats?.userName?.ifBlank { prefs.userName } ?: prefs.userName
                    binding.tvProfileName.text = displayName
                    binding.tvRowUserNameValue.text = "$displayName ›"

                    if (stats != null) {
                        val handle = "@${displayName.lowercase(Locale.US).replace(" ", "_")}_talkme"
                        binding.tvProfileAccountTier.text =
                            "online • $handle • 🔥 ${stats.currentStreakDays} kun streak"
                        binding.tvProfileWordsBadge.text = "${stats.memorizedUniqueWordsCount} so'z ›"
                        binding.tvProfileGrammarBadge.text = "${stats.masteredGrammarRulesCount} qoida"
                        binding.tvProfileTopicsProgress.text =
                            "${stats.completedTopicsCount}/${stats.totalTopicsCount} mavzu • ${stats.completedSectionsCount}/${stats.totalSectionsCount} bo'lim"
                    }

                    // Update Theme & Audio UI Controls without triggering feedback loops
                    isUpdatingControlsProgrammatically = true
                    try {
                        val mode = prefs.themeMode
                        val isSys = mode == "SYSTEM"
                        val isDarkEffective = when (mode) {
                            "DARK" -> true
                            "LIGHT" -> false
                            else -> isSystemCurrentlyNightMode()
                        }

                        if (binding.switchSystemTheme.isChecked != isSys) {
                            binding.switchSystemTheme.isChecked = isSys
                        }
                        if (binding.switchDarkModeQuick.isChecked != isDarkEffective) {
                            binding.switchDarkModeQuick.isChecked = isDarkEffective
                        }

                        binding.tvCurrentThemeStatus.text = when (mode) {
                            "LIGHT" -> "Kunduzgi rejim tanlangan (Light Mode)"
                            "DARK" -> "Tungi rejim tanlangan (Dark Mode)"
                            else -> "Avtomatik (Tizim mavzusi • Birinchi prioretet)"
                        }
                        binding.tvDarkLightModeHint.text = if (isDarkEffective) {
                            "Hozir: Tungi rejim (Dark Mode 🌙) faol"
                        } else {
                            "Hozir: Kunduzgi rejim (Light Mode ☀️) faol"
                        }

                        val targetThemeBtnId = when (mode) {
                            "LIGHT" -> R.id.btnThemeLight
                            "DARK" -> R.id.btnThemeDark
                            else -> R.id.btnThemeSystem
                        }
                        if (binding.toggleThemeMode.checkedButtonId != targetThemeBtnId) {
                            binding.toggleThemeMode.check(targetThemeBtnId)
                        }

                        if (binding.switchSoundEnabled.isChecked != prefs.soundEnabled) {
                            binding.switchSoundEnabled.isChecked = prefs.soundEnabled
                        }

                        val speedProgress = (prefs.speechSpeed * 100).toInt().coerceIn(50, 150)
                        if (binding.seekSpeechSpeed.progress != speedProgress) {
                            binding.seekSpeechSpeed.progress = speedProgress
                        }
                        binding.tvSpeechSpeedValue.text =
                            String.format(Locale.US, "%.2fx", prefs.speechSpeed)

                        if (binding.switchPremiumEnabled.isChecked != prefs.isPremiumSubscribed) {
                            binding.switchPremiumEnabled.isChecked = prefs.isPremiumSubscribed
                        }
                        binding.rbTtsGemini.isEnabled = prefs.isPremiumSubscribed
                        if (prefs.preferredTtsProvider == "GEMINI" && prefs.isPremiumSubscribed) {
                            if (!binding.rbTtsGemini.isChecked) binding.rbTtsGemini.isChecked = true
                        } else {
                            if (!binding.rbTtsNative.isChecked) binding.rbTtsNative.isChecked = true
                        }
                    } finally {
                        isUpdatingControlsProgrammatically = false
                    }

                    // Update Analytics Chart
                    binding.chartSkillPerformance.setChartConfig(
                        filter = state.selectedPeriod,
                        records = state.sessionRecords
                    )

                    if (state.skillMetrics.isNotEmpty()) {
                        val colors = listOf(
                            R.color.color_chart_reading,
                            R.color.color_chart_writing,
                            R.color.color_chart_speaking,
                            R.color.color_chart_listening
                        )
                        val entries = state.skillMetrics.mapIndexed { idx, metric ->
                            SkillAnalyticsChartView.SkillBarEntry(
                                label = metric.skillName,
                                accuracyPercent = metric.averageAccuracyPercent,
                                sessionsCount = metric.completedSessionsCount,
                                colorRes = colors.getOrElse(idx) { R.color.color_primary }
                            )
                        }
                        binding.chartSkillPerformance.setSkillData(entries)
                        binding.tvSkillBreakdownSummary.text = state.skillMetrics.joinToString("  •  ") {
                            "${it.skillName}: ${it.averageAccuracyPercent}% (${it.completedSessionsCount})"
                        }
                    }
                }
            }
        }
    }

    private fun isSystemCurrentlyNightMode(): Boolean {
        val uiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return uiMode == Configuration.UI_MODE_NIGHT_YES
    }

    private fun getSavedAvatarIndex(): Int {
        val sp = requireContext().getSharedPreferences("talkme_avatar_prefs", Context.MODE_PRIVATE)
        return sp.getInt("selected_avatar_idx", 0).coerceIn(0, avatarDrawableOptions.lastIndex)
    }

    private fun saveAvatarIndex(index: Int) {
        val sp = requireContext().getSharedPreferences("talkme_avatar_prefs", Context.MODE_PRIVATE)
        sp.edit().putInt("selected_avatar_idx", index).apply()
        applySavedAvatar()
    }

    private fun applySavedAvatar() {
        val idx = getSavedAvatarIndex()
        binding.ivProfileAvatar.setImageResource(avatarDrawableOptions[idx])
    }

    private fun showTelegramEditProfileDialog() {
        val ctx = requireContext()
        val density = resources.displayMetrics.density
        val pad = (18 * density).toInt()

        val container = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad / 2, pad, pad / 4)
        }

        val avatarLabel = TextView(ctx).apply {
            text = "Avatar rasmni tanlang:"
            textSize = 13f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(ctx, R.color.color_text_secondary))
        }
        container.addView(avatarLabel)

        var chosenAvatarIdx = getSavedAvatarIndex()
        val avatarRow = LinearLayout(ctx).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            val topMargin = (10 * density).toInt()
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, topMargin, 0, topMargin)
            }
        }

        val avatarCards = mutableListOf<MaterialCardView>()
        avatarDrawableOptions.forEachIndexed { idx, drawableRes ->
            val sizePx = (60 * density).toInt()
            val marginPx = (6 * density).toInt()
            val card = MaterialCardView(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(sizePx, sizePx).apply {
                    setMargins(marginPx, 0, marginPx, 0)
                }
                radius = 30 * density
                strokeColor = ContextCompat.getColor(
                    ctx,
                    if (idx == chosenAvatarIdx) R.color.color_primary else R.color.color_card_stroke
                )
                strokeWidth = if (idx == chosenAvatarIdx) (3 * density).toInt() else (1 * density).toInt()
                val iv = ImageView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    setImageResource(drawableRes)
                }
                addView(iv)
                setOnClickListener {
                    chosenAvatarIdx = idx
                    avatarCards.forEachIndexed { cIdx, cView ->
                        cView.strokeColor = ContextCompat.getColor(
                            ctx,
                            if (cIdx == chosenAvatarIdx) R.color.color_primary else R.color.color_card_stroke
                        )
                        cView.strokeWidth =
                            if (cIdx == chosenAvatarIdx) (3 * density).toInt() else (1 * density).toInt()
                    }
                }
            }
            avatarCards.add(card)
            avatarRow.addView(card)
        }
        container.addView(avatarRow)

        val nameLabel = TextView(ctx).apply {
            text = "Ismingiz (Name):"
            textSize = 13f
            setTypeface(typeface, Typeface.BOLD)
            setTextColor(ContextCompat.getColor(ctx, R.color.color_text_secondary))
        }
        container.addView(nameLabel)

        val currentName = viewModel.uiState.value.preferences.userName
        val etName = EditText(ctx).apply {
            setText(currentName)
            hint = getString(R.string.settings_user_name_label)
            setSingleLine(true)
            setSelection(text.length)
        }
        container.addView(etName)

        MaterialAlertDialogBuilder(ctx)
            .setTitle("Edit Profile (Profilni tahrirlash)")
            .setView(container)
            .setNegativeButton("Bekor qilish", null)
            .setPositiveButton("Saqlash") { _, _ ->
                saveAvatarIndex(chosenAvatarIdx)
                val newName = etName.text?.toString().orEmpty().trim()
                if (newName.isNotEmpty()) {
                    viewModel.saveUserName(newName)
                    Toast.makeText(ctx, "Profil muvaffaqiyatli saqlandi!", Toast.LENGTH_SHORT).show()
                }
            }
            .show()
    }

    private fun updateCacheLabel() {
        val kb = viewModel.getAudioCacheSizeKb()
        binding.tvStorageCacheInfo.text =
            "Room DB: 13 Mavzu Active • Audio kesh: $kb KB"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
