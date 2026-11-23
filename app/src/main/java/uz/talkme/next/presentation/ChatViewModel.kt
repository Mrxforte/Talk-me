package uz.talkme.next.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.talkme.next.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val input: SpeechInput,
    private val output: SpeechOutput,
    private val tutor: TutorClient
) : ViewModel() {
    private val mutableState = MutableStateFlow(ChatState())
    val state = mutableState.asStateFlow()
    private val conversation = Conversation()
    private var job: Job? = null
    private var generation = 0L
    private var key = ""

    fun start(apiKey: String) {
        stop()
        key = apiKey
        conversation.clear()
        mutableState.value = ChatState(phase = Phase.PREPARING, active = true)
        val token = generation
        job = viewModelScope.launch {
            try {
                try { input.prepare() } catch (e: CancellationException) { throw e }
                catch (_: Exception) { throw ChatFailure(Issue.MODEL) }
                catch (_: LinkageError) { throw ChatFailure(Issue.MODEL) }
                try { output.prepare() } catch (e: CancellationException) { throw e }
                catch (_: Exception) { throw ChatFailure(Issue.TTS) }
                if (generation == token) listen()
            } catch (e: CancellationException) { throw e }
            catch (e: ChatFailure) { fail(e.issue) }
        }
    }

    private fun listen() {
        if (!state.value.active) return
        val token = generation
        mutableState.update { it.copy(phase = Phase.PREPARING, partial = "", level = 0f) }
        input.listen(
            onPartial = { text -> viewModelScope.launch {
                if (generation == token && state.value.phase == Phase.LISTENING)
                    mutableState.update { it.copy(partial = text) }
            } },
            onFinal = { text -> viewModelScope.launch {
                if (generation == token && state.value.phase in setOf(Phase.LISTENING, Phase.TRANSCRIBING)) answer(text)
            } },
            onLevel = { level -> viewModelScope.launch {
                if (generation == token && state.value.phase == Phase.LISTENING)
                    mutableState.update { it.copy(level = level) }
            } },
            onFailure = { viewModelScope.launch { if (generation == token) fail(Issue.MICROPHONE) } },
            onReady = { viewModelScope.launch {
                if (generation == token && state.value.active)
                    mutableState.update { it.copy(phase = Phase.LISTENING) }
            } },
            onProcessing = { viewModelScope.launch {
                if (generation == token && state.value.active)
                    mutableState.update { it.copy(phase = Phase.TRANSCRIBING, level = 0f) }
            } }
        )
    }

    private fun answer(text: String) {
        input.stop()
        conversation.addUser(text)
        mutableState.update { it.copy(phase = Phase.THINKING, userText = text, partial = "", level = 0f) }
        val token = generation
        job = viewModelScope.launch {
            try {
                val reply = tutor.reply(key, MODEL, conversation.snapshot())
                if (generation != token || !state.value.active) return@launch
                conversation.addModel(reply.text)
                mutableState.update { it.copy(
                    phase = Phase.SPEAKING, assistantText = reply.text,
                    inputTokens = it.inputTokens + reply.inputTokens,
                    outputTokens = it.outputTokens + reply.outputTokens,
                    estimatedUsd = it.estimatedUsd + reply.inputTokens * 0.10 / 1_000_000 +
                        reply.outputTokens * 0.40 / 1_000_000
                ) }
                output.speak(reply.text,
                    onDone = { viewModelScope.launch { if (generation == token) listen() } },
                    onFailure = { viewModelScope.launch { if (generation == token) fail(Issue.TTS) } })
            } catch (e: CancellationException) { throw e }
            catch (e: ChatFailure) { if (generation == token) fail(e.issue) }
            catch (_: Exception) { if (generation == token) fail(Issue.NETWORK) }
        }
    }

    /** One button mutes listening, resumes it, or interrupts the current reply. */
    fun microphone() {
        if (!state.value.active || state.value.phase == Phase.PREPARING) return
        generation++
        job?.cancel()
        input.stop(); output.stop()
        when (state.value.phase) {
            Phase.LISTENING -> mutableState.update { it.copy(phase = Phase.MUTED, partial = "", level = 0f) }
            else -> {
                conversation.removePendingUser()
                listen()
            }
        }
    }

    fun stop() {
        generation++
        job?.cancel(); job = null
        input.stop(); output.stop()
        key = ""
        mutableState.update { it.copy(phase = Phase.IDLE, active = false, partial = "", level = 0f, issue = null) }
    }
    fun fail(issue: Issue) {
        stop()
        mutableState.update { it.copy(phase = Phase.ERROR, issue = issue) }
    }
    override fun onCleared() {
        stop(); input.close(); output.close(); tutor.close()
    }
    companion object { const val MODEL = "gemini-2.5-flash-lite" }
}
