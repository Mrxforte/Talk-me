package com.example

import com.example.feature.pro.AvatarPerformance
import org.junit.Assert.assertEquals
import org.junit.Test

class AvatarPerformanceTest {
    @Test
    fun allHundredPerformancesResolveToDistinctExpressionAndMovement() {
        val performances = AvatarPerformance.Mood.entries.flatMap { mood ->
            AvatarPerformance.Gesture.entries.map { gesture ->
                AvatarPerformance.forReply("${mood.name}:${gesture.name}", "")
            }
        }
        assertEquals(100, performances.toSet().size)
        val renderedParameters = performances.map { performance ->
            Triple(performance.mood.smile, performance.mood.brow,
                listOf(.45f, .71f, .96f).map(performance::pose))
        }
        assertEquals(100, renderedParameters.toSet().size)
    }

    @Test
    fun anUnrecognizedDeliveryFallsBackToTheReplyTone() {
        val performance = AvatarPerformance.forReply("invalid", "Where are you going?")
        assertEquals(AvatarPerformance.Mood.CURIOUS, performance.mood)
        assertEquals(AvatarPerformance.Gesture.TILT_RIGHT, performance.gesture)
    }
}
