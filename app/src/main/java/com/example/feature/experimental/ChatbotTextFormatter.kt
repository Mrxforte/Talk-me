package com.example.feature.experimental

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

/**
 * Formats raw Gemini Markdown replies into clean, mobile-friendly styled text so learners never see
 * ugly raw syntax like `**`, `###`, backticks, or excessive blank lines.
 */
object ChatbotTextFormatter {

    private data class InlineToken(
        val text: String,
        val bold: Boolean = false,
        val italic: Boolean = false,
        val highlight: Boolean = false
    )

    private val headingRegex = Regex("""^#{1,6}\s*(.+)$""")
    private val bulletRegex = Regex("""^\s*[-*+]\s+(.+)$""")
    private val inlineCodeOrStyleRegex = Regex("""\*\*(.+?)\*\*|`([^`]+)`|\*([^\s*][^*]*?)\*""")

    fun formatForDisplay(rawText: String, accentColor: Int): CharSequence {
        val normalized = preClean(rawText)
        if (normalized.isEmpty()) return ""

        val out = SpannableStringBuilder()
        val lines = normalized.split("\n")
        lines.forEachIndexed { index, rawLine ->
            val headingMatch = headingRegex.matchEntire(rawLine.trim())
            val bulletMatch = bulletRegex.matchEntire(rawLine)

            when {
                headingMatch != null -> {
                    val cleanHeader = stripDanglingMarkers(headingMatch.groupValues[1].trim())
                    val start = out.length
                    out.append(cleanHeader)
                    out.setSpan(
                        StyleSpan(Typeface.BOLD),
                        start,
                        out.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                bulletMatch != null -> {
                    out.append("• ")
                    appendStyledInline(out, bulletMatch.groupValues[1], accentColor)
                }
                else -> {
                    appendStyledInline(out, rawLine, accentColor)
                }
            }

            if (index < lines.lastIndex) {
                out.append("\n")
            }
        }
        return out
    }

    fun formatPlainText(rawText: String): String {
        val normalized = preClean(rawText)
        if (normalized.isEmpty()) return ""
        return normalized.split("\n").joinToString("\n") { rawLine ->
            val headingMatch = headingRegex.matchEntire(rawLine.trim())
            val bulletMatch = bulletRegex.matchEntire(rawLine)
            when {
                headingMatch != null -> stripDanglingMarkers(headingMatch.groupValues[1].trim())
                bulletMatch != null -> "• ${tokenizeInline(bulletMatch.groupValues[1]).joinToString("") { it.text }}"
                else -> tokenizeInline(rawLine).joinToString("") { it.text }
            }
        }
    }

    private fun preClean(rawText: String): String {
        return rawText
            .replace("\r\n", "\n")
            .lines()
            .filterNot { it.trim().startsWith("```") }
            .joinToString("\n")
            .replace(Regex("\n{3,}"), "\n\n")
            .trim()
    }

    private fun appendStyledInline(
        out: SpannableStringBuilder,
        line: String,
        accentColor: Int
    ) {
        val tokens = tokenizeInline(line)
        for (token in tokens) {
            val start = out.length
            out.append(token.text)
            val end = out.length
            if (end > start) {
                if (token.bold) {
                    out.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                if (token.italic) {
                    out.setSpan(StyleSpan(Typeface.ITALIC), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                if (token.highlight) {
                    out.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    out.setSpan(ForegroundColorSpan(accentColor), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
        }
    }

    private fun tokenizeInline(line: String): List<InlineToken> {
        val tokens = mutableListOf<InlineToken>()
        var cursor = 0
        for (match in inlineCodeOrStyleRegex.findAll(line)) {
            if (match.range.first > cursor) {
                tokens += InlineToken(stripDanglingMarkers(line.substring(cursor, match.range.first)))
            }
            val boldGroup = match.groups[1]?.value
            val codeGroup = match.groups[2]?.value
            val italicGroup = match.groups[3]?.value
            when {
                boldGroup != null -> tokens += InlineToken(stripDanglingMarkers(boldGroup), bold = true)
                codeGroup != null -> tokens += InlineToken(codeGroup, highlight = true)
                italicGroup != null -> tokens += InlineToken(stripDanglingMarkers(italicGroup), italic = true)
            }
            cursor = match.range.last + 1
        }
        if (cursor < line.length) {
            tokens += InlineToken(stripDanglingMarkers(line.substring(cursor)))
        }
        return tokens
    }

    private fun stripDanglingMarkers(text: String): String {
        return text
            .replace("**", "")
            .replace("__", "")
    }
}
