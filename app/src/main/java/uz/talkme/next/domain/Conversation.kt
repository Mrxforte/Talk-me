package uz.talkme.next.domain

data class Turn(val role: String, val text: String)

/** Bounds all repeated context, including the latest user message. No growing audio history. */
class Conversation(private val maximumCharacters: Int = 6000) {
    private val turns = mutableListOf<Turn>()
    fun addUser(text: String) {
        require(text.isNotBlank())
        turns += Turn("user", text.take(1800))
        trim()
    }
    fun addModel(text: String) {
        turns += Turn("model", text.take(1800))
        trim()
    }
    fun removePendingUser() { if (turns.lastOrNull()?.role == "user") turns.removeAt(turns.lastIndex) }
    fun snapshot(): List<Turn> = turns.toList()
    fun clear() = turns.clear()
    private fun trim() {
        // Remove complete oldest pairs; retain a user-led sequence for generateContent.
        while (turns.size > 2 && turns.sumOf { it.text.length } > maximumCharacters) {
            turns.removeAt(0)
            if (turns.firstOrNull()?.role == "model") turns.removeAt(0)
        }
    }
}

enum class Phase { IDLE, PREPARING, LISTENING, TRANSCRIBING, THINKING, SPEAKING, MUTED, ERROR }
enum class Issue {
    PERMISSION, MODEL, MICROPHONE, NETWORK, AUTH, API_PERMISSION, GEMINI_MODEL,
    BILLING, REQUEST, SERVER, QUOTA, RESPONSE, TTS, FOCUS
}
data class ApiDiagnostic(val httpCode: Int, val status: String, val reason: String = "")
data class ChatState(
    val phase: Phase = Phase.IDLE,
    val active: Boolean = false,
    val userText: String = "",
    val assistantText: String = "",
    val partial: String = "",
    val level: Float = 0f,
    val issue: Issue? = null,
    val diagnostic: ApiDiagnostic? = null,
    val inputTokens: Long = 0,
    val outputTokens: Long = 0,
    val estimatedUsd: Double = 0.0
)

data class Reply(val text: String, val inputTokens: Long, val outputTokens: Long)
class ChatFailure(val issue: Issue, val diagnostic: ApiDiagnostic? = null) : Exception(issue.name)
interface TutorClient {
    suspend fun reply(apiKey: String, model: String, history: List<Turn>): Reply
    fun close()
}
interface SpeechInput {
    suspend fun prepare()
    fun listen(onPartial: (String) -> Unit, onFinal: (String) -> Unit,
               onLevel: (Float) -> Unit, onFailure: () -> Unit, onReady: () -> Unit, onProcessing: () -> Unit)
    fun stop()
    fun close()
}
interface SpeechOutput {
    suspend fun prepare()
    fun speak(text: String, onDone: () -> Unit, onFailure: () -> Unit)
    fun stop()
    fun close()
}
