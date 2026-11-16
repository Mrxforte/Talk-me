package com.example.feature.pro

import org.junit.Assert.assertEquals
import org.junit.Test

class ProReadingMatchTest {
    @Test
    fun punctuationAndCapitalizationDoNotLowerAccuracy() {
        assertEquals(100, readingMatch("I’d like a coffee, please.", "i'd like a coffee please"))
    }

    @Test
    fun omissionsSubstitutionsAndExtraWordsAreCounted() {
        assertEquals(60, readingMatch("I would like some water.", "I would like tea."))
        assertEquals(75, readingMatch("Can I have tea?", "Can I please have tea?"))
        assertEquals(0, readingMatch("Good morning.", ""))
    }
}
