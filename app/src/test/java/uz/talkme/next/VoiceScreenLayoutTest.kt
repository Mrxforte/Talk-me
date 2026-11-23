package uz.talkme.next

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35], application = Application::class, qualifiers = "w393dp-h852dp-port-xxhdpi")
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class VoiceScreenLayoutTest {
    @Test fun `transcripts render without panels and controls remain accessible`() {
        val context = ContextThemeWrapper(RuntimeEnvironment.getApplication(), R.style.AppTheme)
        val root = LayoutInflater.from(context).inflate(R.layout.fragment_chat, null)
        val transcript = root.findViewById<ViewGroup>(R.id.transcriptScroll)
        assertNull(transcript.background)
        assertNull(transcript.getChildAt(0).background)
        root.findViewById<TextView>(R.id.userText).text = "I want to speak English confidently."
        root.findViewById<TextView>(R.id.assistantText).text = "Of course! What would you like to talk about?"
        root.findViewById<TextView>(R.id.status).setText(R.string.listening)
        root.findViewById<TextView>(R.id.actionLabel).setText(R.string.speak)
        root.setBackgroundColor(context.getColor(R.color.background))
        val density = context.resources.displayMetrics.density
        val width = (393 * density).toInt()
        val height = (852 * density).toInt()
        root.setPadding((24 * density).toInt(), (40 * density).toInt(), (24 * density).toInt(), (32 * density).toInt())
        root.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY))
        root.layout(0, 0, width, height)
        for (id in listOf(R.id.micButton, R.id.endButton, R.id.transcriptButton, R.id.keyButton)) {
            val control = root.findViewById<View>(id)
            assertTrue(control.width >= 48 * density)
            assertTrue(control.height >= 48 * density)
            assertFalse(control.contentDescription.isNullOrBlank())
        }
        assertTrue(root.findViewById<View>(R.id.actionLabel).bottom < height)
        System.getenv("TALKME_PREVIEW_DIR")?.let {
            val output = File(it, "TalkMe-B-containerless.png")
            output.parentFile.mkdirs()
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            root.draw(Canvas(bitmap))
            output.outputStream().use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
            bitmap.recycle()
        }
    }
}
