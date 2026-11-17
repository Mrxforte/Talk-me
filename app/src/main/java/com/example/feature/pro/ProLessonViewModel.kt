package com.example.feature.pro

import android.content.Context
import android.os.SystemClock
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.R
import com.example.core.audio.TtsManager
import com.example.core.common.AppConstants
import com.example.domain.model.CurriculumLevel
import com.example.domain.model.LessonSectionModel
import com.example.domain.model.UnlockStatus
import com.example.domain.repository.AiLearningService
import com.example.domain.repository.LearningRepository
import com.example.domain.repository.SpeechRepository
import com.example.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class ProStage { RULE, DIALOGUE, READ_ALOUD, FOLLOW_UP, WRAP_UP }

enum class ProAction { BUSY, PLAYING, PLAY, MIC, LISTENING, NEXT, FINISH, BLOCKED, DONE }

data class TutorCue(val words: String, val atMs: Long, val amplitude: Float? = null)

data class ProLessonState(
    val stage: ProStage = ProStage.RULE,
    val action: ProAction = ProAction.BUSY,
    @StringRes val status: Int = R.string.pro_preparing,
    val reading: String = "",
    val female: Boolean = true,
    val cue: TutorCue? = null
)

@HiltViewModel
class ProLessonViewModel @Inject constructor(
    state: SavedStateHandle,
    @ApplicationContext private val context: Context,
    private val lessons: LearningRepository,
    private val preferences: UserPreferencesRepository,
    private val speech: SpeechRepository,
    private val tutor: AiLearningService,
    private val tts: TtsManager
) : ViewModel() {
    private val sectionId = state.get<String>(AppConstants.ARG_SECTION_ID).orEmpty()
    private val topicId = state.get<String>(AppConstants.ARG_TOPIC_ID).orEmpty()
    private val _ui = MutableStateFlow(ProLessonState(female = state.get<Boolean>(ARG_FEMALE) ?: true))
    val ui = _ui.asStateFlow()
    private val deviceInput = ProSpeechInput(context)
    private var deviceListening = false
    private var microphoneTimeout: Job? = null
    private val _amplitude = MutableStateFlow(0f)
    val amplitude = _amplitude.asStateFlow()

    private var section: LessonSectionModel? = null
    private var vocabulary = emptyList<String>()
    private var grammar = emptyList<String>()
    private var learnerLevel = "A0_A1"
    private var readingPassage = ""
    private var userName = ""
    private val history = mutableListOf<Pair<String, String>>()
    private var turns = 0
    private var latestFeedback = ""
    private var useGuidedDialogue = false
    private var pendingLine: SpokenLine? = null
    private var work: Job? = null
    private var generation = 0
    private var visible = false

    init {
        viewModelScope.launch {
            speech.utteranceFinalizedEvents.collect { result ->
                if (_ui.value.action == ProAction.LISTENING && !deviceListening) {
                    if (result.sttErrorReason != null) {
                        microphoneTimeout?.cancel()
                        setAction(ProAction.MIC, R.string.pro_mic_error)
                    } else {
                        handleRecording(result.recognizedText)
                    }
                }
            }
        }
        viewModelScope.launch {
            speech.audioAmplitude.collect { if (!deviceListening) _amplitude.value = it }
        }
        viewModelScope.launch {
            try {
                val access = preferences.userPreferencesFlow.first()
                val lesson = lessons.getSectionById(sectionId)
                if (!access.isPremiumSubscribed || lesson == null ||
                    lesson.status == UnlockStatus.LOCKED || lesson.topicId != topicId
                ) {
                    setAction(
                        ProAction.BLOCKED,
                        if (access.isPremiumSubscribed) R.string.pro_lesson_unavailable else R.string.pro_locked
                    )
                    return@launch
                }
                section = lesson
                userName = access.userName
                val words = lessons.observeSectionVocabulary(sectionId).first()
                val rules = lessons.observeSectionGrammarRules(sectionId).first()
                vocabulary = words.map { it.englishWord }
                grammar = rules.map { it.exampleEnglish }.filter { it.isNotBlank() }
                readingPassage = (grammar + words.map { it.exampleEnglish })
                    .filter { it.isNotBlank() }
                    .distinct().take(3).joinToString(" ")
                    .ifBlank { lesson.titleEnglish }
                learnerLevel = when (CurriculumLevel.forTopic(topicId)) {
                    CurriculumLevel.A2, CurriculumLevel.B1 -> "A2_B1"
                    CurriculumLevel.B2, CurriculumLevel.C1, CurriculumLevel.C2 -> "B2_C2"
                    else -> "A0_A1"
                }
                beginStage(ProStage.RULE)
            } catch (error: CancellationException) {
                throw error
            } catch (_: Exception) {
                setAction(ProAction.BLOCKED, R.string.pro_lesson_unavailable)
            }
        }
    }

    fun onVisible() {
        visible = true
        if (_ui.value.stage == ProStage.RULE && _ui.value.action == ProAction.PLAY && generation == 0) {
            playLine()
        }
    }

    fun onHidden() {
        visible = false
        when (_ui.value.action) {
            ProAction.PLAYING -> pauseLine()
            ProAction.LISTENING -> {
                cancelMicrophone()
                setAction(ProAction.MIC, R.string.pro_answer)
            }
            ProAction.BUSY -> if (deviceListening) {
                cancelMicrophone()
                setAction(ProAction.MIC, R.string.pro_answer)
            }
            else -> Unit
        }
    }

    fun useMicrophone() {
        if (_ui.value.action != ProAction.MIC) return
        _amplitude.value = 0f
        setAction(ProAction.LISTENING, R.string.pro_listening)
        deviceListening = deviceInput.start(onResult = { result ->
            deviceListening = false
            handleRecording(result)
        }, onAmplitude = { _amplitude.value = it })
        if (!deviceListening) {
            speech.setMuted(false)
            if (!speech.startListening()) {
                setAction(ProAction.MIC, R.string.pro_mic_error)
                return
            }
        }
        microphoneTimeout = viewModelScope.launch {
            delay(30_000)
            stopMicrophone()
        }
    }

    fun permissionDenied() = setAction(ProAction.MIC, R.string.pro_permission)

    fun stopMicrophone() {
        if (_ui.value.action != ProAction.LISTENING) return
        setAction(ProAction.BUSY, R.string.pro_processing)
        if (deviceListening) {
            deviceInput.stop()
            microphoneTimeout?.cancel()
            microphoneTimeout = viewModelScope.launch {
                delay(8_000)
                cancelMicrophone()
                setAction(ProAction.MIC, R.string.pro_try_again)
            }
        } else {
            work = viewModelScope.launch { handleRecording(speech.stopListening().recognizedText) }
        }
    }

    private fun cancelMicrophone() {
        microphoneTimeout?.cancel()
        if (deviceListening) deviceInput.cancel() else speech.cancelListening()
        deviceListening = false
        _amplitude.value = 0f
    }

    fun pressAction() {
        when (_ui.value.action) {
            ProAction.PLAYING -> pauseLine()
            ProAction.PLAY -> playLine()
            ProAction.LISTENING -> stopMicrophone()
            ProAction.NEXT -> beginStage(ProStage.entries[_ui.value.stage.ordinal + 1])
            ProAction.FINISH -> finishLesson()
            else -> Unit
        }
    }

    private fun beginStage(stage: ProStage) {
        val lesson = section ?: return
        turns = 0
        _ui.update { it.copy(stage = stage, reading = if (stage == ProStage.READ_ALOUD) readingPassage else "") }
        when (stage) {
            ProStage.RULE -> speak(
                context.getString(R.string.pro_rule_intro, lesson.explanationUzbek),
                "uz",
                ProAction.NEXT
            )
            ProStage.DIALOGUE -> {
                val question = context.getString(R.string.pro_first_prompt, lesson.titleEnglish)
                history += "ASSISTANT" to question
                speak(question, "en", ProAction.MIC)
            }
            ProStage.READ_ALOUD -> speak(context.getString(R.string.pro_read_prompt), "uz", ProAction.MIC)
            ProStage.FOLLOW_UP -> {
                val example = grammar.firstOrNull() ?: readingPassage
                val question = context.getString(R.string.pro_second_prompt, example)
                history += "ASSISTANT" to question
                speak(question, "en", ProAction.MIC)
            }
            ProStage.WRAP_UP -> {
                val summary = lesson.wrapUpSummaryUzbek.replace("[USER_NAME]", userName)
                speak(listOf(summary, latestFeedback).filter { it.isNotBlank() }.joinToString(" "), "uz", ProAction.FINISH)
            }
        }
    }

    private fun handleRecording(text: String) {
        microphoneTimeout?.cancel()
        val transcript = text.trim()
        if (transcript.isBlank()) {
            setAction(ProAction.MIC, R.string.pro_try_again)
            return
        }
        if (_ui.value.stage == ProStage.READ_ALOUD) {
            val score = readingMatch(readingPassage, transcript)
            val message = context.getString(
                if (score >= 85) R.string.pro_read_good else R.string.pro_read_retry, score
            )
            speak(message, "uz", ProAction.NEXT)
        } else {
            submitTurn(transcript)
        }
    }

    private fun submitTurn(transcript: String) {
        val lesson = section ?: return
        if (useGuidedDialogue) {
            speakGuidedReply()
            return
        }
        setAction(ProAction.BUSY, R.string.pro_processing)
        work = viewModelScope.launch {
            try {
                val answer = tutor.sendSpeakingTurn(
                    topicTitle = lesson.titleEnglish,
                    allowedVocabulary = vocabulary,
                    allowedGrammarSummaries = grammar,
                    conversationHistory = history.toList(),
                    studentUtterance = transcript,
                    learnerLevel = learnerLevel
                ).getOrThrow()
                history += "USER" to transcript
                history += "ASSISTANT" to answer.spokenEnglishReply
                if (answer.grammarAndVocabFeedbackUzbek.isNotBlank()) {
                    latestFeedback = answer.grammarAndVocabFeedbackUzbek
                }
                turns += 1
                speak(answer.spokenEnglishReply, "en", if (turns < 2) ProAction.MIC else ProAction.NEXT)
            } catch (error: CancellationException) {
                throw error
            } catch (_: Exception) {
                useGuidedDialogue = true
                speakGuidedReply()
            }
        }
    }

    private fun speakGuidedReply() {
        turns += 1
        val reply = if (turns < 2) {
            val example = grammar.firstOrNull() ?: readingPassage
            context.getString(R.string.pro_guided_follow_up, example)
        } else {
            context.getString(R.string.pro_guided_continue)
        }
        speak(reply, "en", if (turns < 2) ProAction.MIC else ProAction.NEXT, guided = true)
    }

    private fun speak(text: String, language: String, after: ProAction, guided: Boolean = false) {
        pendingLine = SpokenLine(text, language, after, guided)
        if (visible) playLine() else setAction(ProAction.PLAY, R.string.pro_play)
    }

    private fun playLine() {
        val line = pendingLine ?: return
        val request = ++generation
        setAction(ProAction.PLAYING, if (line.guided) R.string.pro_guided_status else R.string.pro_speaking)
        work = viewModelScope.launch {
            val spoken = line.text.trim()
            val result = tts.speakPro(spoken, line.language, if (_ui.value.female) "FEMALE" else "MALE") { start, end ->
                val words = if (start in spoken.indices && end in start + 1..spoken.length) {
                    spoken.substring(start, end)
                } else {
                    ""
                }
                viewModelScope.launch {
                    if (request == generation) {
                        _ui.update { it.copy(cue = TutorCue(words, SystemClock.uptimeMillis())) }
                    }
                }
            }
            if (request != generation) return@launch
            if (result.isSuccess) {
                pendingLine = null
                val status = when (line.after) {
                    ProAction.MIC -> if (_ui.value.stage == ProStage.READ_ALOUD) R.string.pro_read_aloud else R.string.pro_answer
                    ProAction.FINISH -> R.string.pro_finish
                    else -> R.string.pro_continue
                }
                setAction(line.after, status)
            } else {
                setAction(ProAction.PLAY, R.string.pro_audio_error)
            }
        }
    }

    private fun pauseLine() {
        generation += 1
        work?.cancel()
        tts.stop()
        setAction(ProAction.PLAY, R.string.pro_play)
    }

    private fun finishLesson() {
        val lesson = section ?: return
        setAction(ProAction.BUSY, R.string.pro_processing)
        work = viewModelScope.launch {
            try {
                lessons.completeLessonSection(lesson.topicId, lesson.id)
                setAction(ProAction.DONE, R.string.pro_finish)
            } catch (error: CancellationException) {
                throw error
            } catch (_: Exception) {
                setAction(ProAction.FINISH, R.string.pro_save_error)
            }
        }
    }

    private fun setAction(action: ProAction, @StringRes status: Int) {
        _ui.update { it.copy(action = action, status = status, cue = null) }
    }

    override fun onCleared() {
        work?.cancel()
        if (_ui.value.action == ProAction.PLAYING) tts.stop()
        if (deviceListening || _ui.value.action == ProAction.LISTENING) cancelMicrophone()
        super.onCleared()
    }

    private data class SpokenLine(val text: String, val language: String, val after: ProAction, val guided: Boolean)

    companion object {
        const val ARG_FEMALE = "arg_pro_female"
    }
}

internal fun readingMatch(expected: String, spoken: String): Int {
    fun tokens(text: String) = Regex("[\\p{L}\\p{N}]+(?:['’][\\p{L}]+)?")
        .findAll(text.lowercase(Locale.ROOT).replace('’', '\'')).map { it.value }.toList()
    val target = tokens(expected)
    val actual = tokens(spoken)
    if (target.isEmpty()) return 0
    var previous = IntArray(actual.size + 1) { it }
    target.forEachIndexed { index, word ->
        val row = IntArray(actual.size + 1)
        row[0] = index + 1
        actual.forEachIndexed { j, heard ->
            row[j + 1] = minOf(row[j] + 1, previous[j + 1] + 1, previous[j] + if (word == heard) 0 else 1)
        }
        previous = row
    }
    return ((target.size - previous.last()).coerceAtLeast(0) * 100) / target.size
}
