package uz.talkme.next.data

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import uz.talkme.next.domain.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GeminiTutorClient(
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS).callTimeout(45, TimeUnit.SECONDS).build(),
    private val baseUrl: String = "https://generativelanguage.googleapis.com"
) : TutorClient {
    override suspend fun reply(apiKey: String, model: String, history: List<Turn>): Reply {
        require(model.matches(Regex("[a-zA-Z0-9._-]+")))
        val request = Request.Builder()
            .url("$baseUrl/v1beta/models/$model:generateContent")
            .header("x-goog-api-key", apiKey)
            .post(payload(history).toString().toRequestBody("application/json".toMediaType()))
            .build()
        return suspendCancellableCoroutine { continuation ->
            val call = client.newCall(request)
            continuation.invokeOnCancellation { call.cancel() }
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    if (continuation.isActive) continuation.resumeWithException(ChatFailure(Issue.NETWORK))
                }
                override fun onResponse(call: Call, response: Response) {
                    val result = runCatching {
                        response.use {
                            if (!it.isSuccessful) throw ChatFailure(when (it.code) {
                                400, 401, 403, 404 -> Issue.AUTH
                                429 -> Issue.QUOTA
                                else -> Issue.NETWORK
                            })
                            parse(JSONObject(it.body?.string().orEmpty()))
                        }
                    }
                    if (continuation.isActive) result.fold(continuation::resume) {
                        continuation.resumeWithException(if (it is ChatFailure) it else ChatFailure(Issue.RESPONSE))
                    }
                }
            })
        }
    }
    override fun close() { client.dispatcher.cancelAll(); client.connectionPool.evictAll() }

    companion object {
        fun payload(history: List<Turn>): JSONObject = JSONObject().apply {
            put("systemInstruction", JSONObject().put("parts", JSONArray().put(JSONObject().put("text",
                "You are Lera, a friendly English conversation partner for an Uzbek-speaking learner. " +
                "Respond to the learner's actual words. Speak clear, simple English. Keep replies to 1-3 short " +
                "sentences, usually under 45 words. Ask one relevant follow-up question. Explain in Uzbek " +
                "only when requested. Never invent words the learner said. No markdown or stage directions. " +
                "Do not greet again on every turn. Be natural, helpful, and concise."))))
            put("contents", JSONArray().apply {
                history.forEach { turn -> put(JSONObject().put("role", turn.role)
                    .put("parts", JSONArray().put(JSONObject().put("text", turn.text)))) }
            })
            put("generationConfig", JSONObject().put("maxOutputTokens", 160).put("temperature", 0.7))
        }
        fun parse(body: JSONObject): Reply {
            val candidate = body.optJSONArray("candidates")?.optJSONObject(0)
                ?: throw ChatFailure(Issue.RESPONSE)
            val parts = candidate.optJSONObject("content")?.optJSONArray("parts")
                ?: throw ChatFailure(Issue.RESPONSE)
            val text = buildString {
                for (i in 0 until parts.length()) {
                    val part = parts.optJSONObject(i) ?: continue
                    if (!part.optBoolean("thought")) append(part.optString("text"))
                }
            }.trim()
            if (text.isEmpty()) throw ChatFailure(Issue.RESPONSE)
            val usage = body.optJSONObject("usageMetadata")
            return Reply(text, usage?.optLong("promptTokenCount") ?: 0,
                (usage?.optLong("candidatesTokenCount") ?: 0) + (usage?.optLong("thoughtsTokenCount") ?: 0))
        }
    }
}
