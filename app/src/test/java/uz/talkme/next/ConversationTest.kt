package uz.talkme.next

import org.junit.Assert.*
import org.junit.Test
import uz.talkme.next.domain.*

class ConversationTest {
    @Test fun `context stays bounded and user led over a one hour conversation`() {
        val conversation = Conversation()
        repeat(120) {
            conversation.addUser("u".repeat(350))
            conversation.addModel("m".repeat(300))
            assertTrue(conversation.snapshot().sumOf { it.text.length } <= 6000)
            assertEquals("user", conversation.snapshot().first().role)
        }
    }
    @Test fun `recognizer text is preserved and repeated legitimate sentences remain`() {
        val conversation = Conversation()
        val original = "i i want to learn english"
        conversation.addUser(original)
        conversation.addModel("Let's practice.")
        conversation.addUser(original)
        assertEquals(original, conversation.snapshot().last().text)
        assertEquals(2, conversation.snapshot().count { it.role == "user" })
    }
    @Test fun `cancelled pending request removes only the unfinished user turn`() {
        val conversation = Conversation()
        conversation.addUser("hello"); conversation.addModel("Hi!")
        conversation.addUser("cancel this")
        conversation.removePendingUser()
        assertEquals(listOf(Turn("user", "hello"), Turn("model", "Hi!")), conversation.snapshot())
    }
}
