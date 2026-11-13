package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.CurriculumLevel
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/** Maps the continuation lessons onto the same offline seed tables as the A0 course. */
internal object ContinuationCurriculum {
    private val lessons by lazy {
        listOf(
            CurriculumLevel.A2 to a2Lessons,
            CurriculumLevel.B1 to b1Lessons,
            CurriculumLevel.B2 to b2Lessons,
            CurriculumLevel.C1 to c1Lessons,
            CurriculumLevel.C2 to c2Lessons
        ).flatMap { (level, units) ->
            units.mapIndexed { index, unit -> SeedLesson(level, index + 1, unit) }
        }
    }

    fun buildSections() = lessons.map { it.section() }

    fun buildVocabulary() = lessons.flatMap { it.vocabulary() }

    fun buildGrammarRules() = lessons.map { it.grammarRule() }

    fun buildExercises() = lessons.flatMap { it.exercises() }

    private class SeedLesson(
        private val level: CurriculumLevel,
        private val position: Int,
        private val lesson: ContinuationLesson
    ) {
        private val prefix = "${level.name.lowercase()}_${position.toString().padStart(2, '0')}"
        private val sectionId = "sec_$prefix"
        private val ruleId = "g_${prefix}_01"

        private fun vocabularyId(index: Int) = "v_${prefix}_0${index + 1}"

        fun section() = LessonSectionEntity(
            id = sectionId,
            topicId = level.topicId,
            orderIndex = position,
            titleEnglish = lesson.englishTitle,
            titleUzbek = "$position-Dars: ${lesson.uzbekTitle}",
            objectiveUzbek = lesson.objective,
            explanationUzbek = lesson.rule,
            wrapUpSummaryUzbek = lesson.wrapUp
        )

        fun vocabulary() = lesson.examples.mapIndexed { index, example ->
            VocabularyEntity(
                id = vocabularyId(index),
                topicId = level.topicId,
                sectionId = sectionId,
                englishWord = example.phrase,
                uzbekTranslation = example.meaning,
                exampleEnglish = example.english,
                exampleUzbek = example.uzbek
            )
        }

        fun grammarRule() = GrammarRuleEntity(
            id = ruleId,
            topicId = level.topicId,
            sectionId = sectionId,
            titleUzbek = lesson.uzbekTitle,
            explanationUzbek = lesson.rule,
            exampleEnglish = lesson.examples.first().english,
            exampleUzbek = lesson.examples.first().uzbek,
            associatedVocabularyIdsCsv = lesson.examples.indices.joinToString(",") { vocabularyId(it) },
            isStudied = false,
            isMastered = false
        )

        fun exercises() = lesson.questions.mapIndexed { index, question ->
            ExerciseEntity(
                id = "ex_${prefix}_0${index + 1}",
                topicId = level.topicId,
                sectionId = sectionId,
                practiceCategory = PracticeCategory.SECTION_QUIZ.id,
                exerciseType = when {
                    question.audio != null -> ExerciseType.LISTENING_COMPREHENSION.name
                    "___" in question.prompt -> ExerciseType.FILL_IN_THE_BLANKS.name
                    else -> ExerciseType.MULTIPLE_CHOICE.name
                },
                difficulty = (level.ordinal - 1).coerceIn(1, 5),
                promptText = question.prompt,
                subPromptText = "",
                audioTextToSpeak = question.audio,
                optionsPipeSeparated = question.options.joinToString("|"),
                correctAnswer = question.options.first(),
                explanationUzbek = question.feedback,
                vocabularyIdsCsv = "",
                grammarRuleIdsCsv = ruleId
            )
        }
    }
}

internal data class ContinuationLesson(
    val englishTitle: String,
    val uzbekTitle: String,
    val objective: String,
    val rule: String,
    val wrapUp: String,
    val examples: List<LessonPhrase>,
    val questions: List<LessonQuestion>
)

internal data class LessonPhrase(
    val phrase: String,
    val meaning: String,
    val english: String,
    val uzbek: String
)

internal data class LessonQuestion(
    val prompt: String,
    val options: List<String>,
    val feedback: String,
    val audio: String? = null
)

internal fun question(
    prompt: String,
    answer: String,
    distractor1: String,
    distractor2: String,
    feedback: String,
    audio: String? = null
) = LessonQuestion(prompt, listOf(answer, distractor1, distractor2), feedback, audio)
