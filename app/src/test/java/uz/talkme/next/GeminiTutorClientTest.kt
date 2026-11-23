package uz.talkme.next

import kotlinx.coroutines.async
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.Assert.*
import org.junit.Test
import uz.talkme.next.data.GeminiTutorClient
import uz.talkme.next.domain.*

class GeminiTutorClientTest {
    @Test fun `request contains only text and key is a header`() = runTest {
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody("""{"candidates":[{"content":{"parts":[{"text":"Hi!"}]}}],"usageMetadata":{"promptTokenCount":100,"candidatesTokenCount":8}}"""))
        server.start()
        val client = GeminiTutorClient(baseUrl = server.url("").toString().trimEnd('/'))
        try {
            val reply = client.reply("test-secret", "gemini-2.5-flash-lite", listOf(Turn("user", "hello")))
            val request = server.takeRequest()
            assertEquals("test-secret", request.getHeader("x-goog-api-key"))
            assertFalse(request.path!!.contains("test-secret"))
            val body = JSONObject(request.body.readUtf8())
            assertEquals("hello", body.getJSONArray("contents").getJSONObject(0).getJSONArray("parts").getJSONObject(0).getString("text"))
            assertFalse(body.toString().contains("inlineData"))
            assertEquals(160, body.getJSONObject("generationConfig").getInt("maxOutputTokens"))
            assertEquals(100L, reply.inputTokens)
            assertEquals("Hi!", reply.text)
        } finally { client.close(); server.shutdown() }
    }
    @Test fun `all response text parts are read without thought content`() {
        val reply = GeminiTutorClient.parse(JSONObject("""{"candidates":[{"content":{"parts":[{"thought":true,"text":"private reasoning"},{"text":"Hello "},{"text":"friend!"}]}}],"usageMetadata":{"promptTokenCount":12,"candidatesTokenCount":3,"thoughtsTokenCount":5}}"""))
        assertEquals("Hello friend!", reply.text)
        assertEquals(8L, reply.outputTokens)
    }
    @Test fun `blocked response produces explicit failure rather than fake answer`() {
        try {
            GeminiTutorClient.parse(JSONObject("""{"promptFeedback":{"blockReason":"SAFETY"}}"""))
            fail("Expected response failure")
        } catch (e: ChatFailure) { assertEquals(Issue.RESPONSE, e.issue) }
    }
    @Test fun `quota response is distinguished from network failure`() = runTest {
        val server = MockWebServer()
        server.enqueue(MockResponse().setResponseCode(429))
        server.start()
        val client = GeminiTutorClient(baseUrl = server.url("").toString().trimEnd('/'))
        try {
            client.reply("test", "gemini-2.5-flash-lite", listOf(Turn("user", "hello")))
            fail("Expected quota failure")
        } catch (e: ChatFailure) { assertEquals(Issue.QUOTA, e.issue) }
        finally { client.close(); server.shutdown() }
    }
}
