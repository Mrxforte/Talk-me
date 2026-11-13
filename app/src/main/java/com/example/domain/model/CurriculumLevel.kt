package com.example.domain.model

/** Stable, independent levels in learner progression order. */
enum class CurriculumLevel(val topicId: String) {
    A0("level_a0"), A1("level_a1"), A2("level_a2"),
    B1("level_b1"), B2("level_b2"), C1("level_c1"), C2("level_c2");

    companion object {
        fun forTopic(id: String): CurriculumLevel? = entries.firstOrNull { it.topicId == id }
    }
}
