package uz.talkme.next

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import uz.talkme.next.domain.*
import uz.talkme.next.presentation.ChatViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    @Before fun setUp() { Dispatchers.setMain(dispatcher) }
    @After fun tearDown() { Dispatchers.resetMain() }

    @Test fun `one start supports multiple turns and returns to listening after playback`() = runTest(dispatcher) {
        val input = Input(); val output = Output(); val client = Client()
        val vm = ChatViewModel(input, output, client)
        vm.start("secret"); runCurrent()
        assertEquals(Phase.LISTENING, vm.state.value.phase)
        input.final("hello"); runCurrent()
        assertEquals(Phase.SPEAKING, vm.state.value.phase)
        assertEquals("hello", vm.state.value.userText)
        output.complete(); runCurrent()
        assertEquals(Phase.LISTENING, vm.state.value.phase)
        input.final("i like music"); runCurrent()
        assertEquals(2, client.calls)
        assertEquals(200L, vm.state.value.inputTokens)
        assertEquals(0.000024, vm.state.value.estimatedUsd, 0.00000001)
        vm.stop()
    }
    @Test fun `stopping prevents late microphone and TTS callbacks restarting conversation`() = runTest(dispatcher) {
        val input = Input(); val output = Output(); val client = Client()
        val vm = ChatViewModel(input, output, client)
        vm.start("secret"); runCurrent()
        val late = input.final
        input.final("hello"); runCurrent()
        val latePlayback = output.complete
        vm.stop(); late("ghost"); latePlayback(); runCurrent()
        assertFalse(vm.state.value.active)
        assertEquals(Phase.IDLE, vm.state.value.phase)
        assertEquals(1, client.calls)
    }
    @Test fun `interrupt cancels pending paid request and starts listening again`() = runTest(dispatcher) {
        val input = Input(); val output = Output(); val client = Client().apply { pending = CompletableDeferred() }
        val vm = ChatViewModel(input, output, client)
        vm.start("secret"); runCurrent()
        input.final("hello"); runCurrent()
        assertEquals(Phase.THINKING, vm.state.value.phase)
        vm.microphone(); runCurrent()
        client.pending!!.complete(Reply("late answer", 100, 5)); runCurrent()
        assertEquals(Phase.LISTENING, vm.state.value.phase)
        assertEquals("", vm.state.value.assistantText)
        vm.stop()
    }
    @Test fun `mute rejects a queued final transcript`() = runTest(dispatcher) {
        val input = Input(); val client = Client()
        val vm = ChatViewModel(input, Output(), client)
        vm.start("secret"); runCurrent()
        val queued = input.final
        vm.microphone(); queued("do not send"); runCurrent()
        assertEquals(Phase.MUTED, vm.state.value.phase)
        assertEquals(0, client.calls)
        vm.microphone(); runCurrent()
        assertEquals(Phase.LISTENING, vm.state.value.phase)
        vm.stop()
    }

    private class Input : SpeechInput {
        var final: (String) -> Unit = {}
        override suspend fun prepare() = Unit
        override fun listen(onPartial: (String) -> Unit, onFinal: (String) -> Unit, onLevel: (Float) -> Unit, onFailure: () -> Unit, onReady: () -> Unit, onProcessing: () -> Unit) { final = onFinal; onReady() }
        override fun stop() = Unit
        override fun close() = Unit
    }
    private class Output : SpeechOutput {
        var complete: () -> Unit = {}
        override suspend fun prepare() = Unit
        override fun speak(text: String, onDone: () -> Unit, onFailure: () -> Unit) { complete = onDone }
        override fun stop() = Unit
        override fun close() = Unit
    }
    private class Client : TutorClient {
        var calls = 0
        var pending: CompletableDeferred<Reply>? = null
        override suspend fun reply(apiKey: String, model: String, history: List<Turn>): Reply {
            calls++
            return pending?.await() ?: Reply("Hi! How are you?", 100, 5)
        }
        override fun close() = Unit
    }
}
