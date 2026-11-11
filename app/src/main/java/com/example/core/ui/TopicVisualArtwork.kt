package com.example.core.ui

import androidx.annotation.DrawableRes
import com.example.R
import com.example.domain.model.PracticeCategory

/**
 * Maps every curriculum Topic, Sub-topic (LessonSection), Practice Skill, and Vocabulary Word
 * 1-to-1 to its dedicated 3D visual illustration.
 */
object TopicVisualArtwork {

    /**
     * Returns the 1-to-1 matching 3D illustration for each of the 13 curriculum topics.
     */
    @DrawableRes
    fun getTopicImageRes(topicId: String, orderIndex: Int = 1): Int {
        val idLower = topicId.lowercase()
        return when {
            idLower.contains("intro") || orderIndex == 1 ->
                R.drawable.img_lesson_conversation_1794310851367
            idLower.contains("grammar") || orderIndex == 2 ->
                R.drawable.img_lesson_grammar_1794310851898
            idLower.contains("number") || orderIndex == 3 ->
                R.drawable.img_lesson_numbers_1794310853767
            idLower.contains("noun") || orderIndex == 4 ->
                R.drawable.img_lesson_objects_1794310854481
            idLower.contains("demonstrative") || orderIndex == 5 ->
                R.drawable.img_topic_demonstratives_1794316348388
            idLower.contains("adjective") || orderIndex == 6 ->
                R.drawable.img_lesson_adjectives_1794310884758
            idLower.contains("article") || orderIndex == 7 ->
                R.drawable.img_topic_articles_1794316349035
            idLower.contains("preposition") || orderIndex == 8 ->
                R.drawable.img_lesson_places_1794310885756
            idLower.contains("pronoun") || orderIndex == 9 ->
                R.drawable.img_topic_pronouns_1794316352550
            idLower.contains("adverbial") || orderIndex == 11 ->
                R.drawable.img_topic_adverbials_1794316386780
            idLower.contains("adverb") || orderIndex == 10 ->
                R.drawable.img_topic_adverbs_1794316352573
            idLower.contains("irregular") || orderIndex == 13 ->
                R.drawable.img_topic_irregular_verbs_1794316389451
            idLower.contains("verb") || orderIndex == 12 ->
                R.drawable.img_lesson_actions_1794310855917
            else -> R.drawable.img_hero_learning_1794304411125
        }
    }

    /**
     * Returns the 1-to-1 matching 3D illustration for a specific sub-topic (section) inside a topic.
     */
    @DrawableRes
    fun getSectionImageRes(
        topicId: String,
        sectionId: String,
        sectionOrderIndex: Int = 1
    ): Int {
        val secLower = sectionId.lowercase()
        return when {
            secLower == "sec_01_01" -> R.drawable.img_lesson_conversation_1794310851367
            secLower == "sec_01_02" -> R.drawable.img_topic_morning_intro_1794316347713
            secLower == "sec_01_03" -> R.drawable.img_skill_speaking_1794316279625
            secLower == "sec_01_04" -> R.drawable.img_topic_pronouns_1794316352550
            secLower == "sec_01_05" -> R.drawable.img_lesson_places_1794310885756
            secLower == "sec_01_06" -> R.drawable.img_lesson_actions_1794310855917
            secLower == "sec_01_07" -> R.drawable.img_daily_tasks_banner_1794310886631
            secLower == "sec_02_01" -> R.drawable.img_lesson_grammar_1794310851898
            secLower == "sec_02_02" -> R.drawable.img_skill_vocabulary_1794316277453
            secLower == "sec_03_01" -> R.drawable.img_lesson_numbers_1794310853767
            secLower == "sec_03_02" -> R.drawable.img_daily_tasks_banner_1794310886631
            secLower == "sec_04_01" -> R.drawable.img_lesson_objects_1794310854481
            secLower == "sec_04_02" -> R.drawable.img_topic_morning_intro_1794316347713
            secLower.startsWith("sec_05") -> R.drawable.img_topic_demonstratives_1794316348388
            secLower.startsWith("sec_06") -> R.drawable.img_lesson_adjectives_1794310884758
            secLower.startsWith("sec_07") -> R.drawable.img_topic_articles_1794316349035
            secLower.startsWith("sec_08") -> R.drawable.img_lesson_places_1794310885756
            secLower.startsWith("sec_09") -> R.drawable.img_topic_pronouns_1794316352550
            secLower.startsWith("sec_10") -> R.drawable.img_topic_adverbs_1794316352573
            secLower.startsWith("sec_11") -> R.drawable.img_topic_adverbials_1794316386780
            secLower.startsWith("sec_12") -> R.drawable.img_lesson_actions_1794310855917
            secLower.startsWith("sec_13") -> R.drawable.img_topic_irregular_verbs_1794316389451
            else -> getTopicImageRes(topicId, sectionOrderIndex)
        }
    }

    /**
     * Returns the 1-to-1 matching 3D illustration for a specific English vocabulary word on a Flip Card.
     */
    @DrawableRes
    fun getWordImageRes(
        englishWord: String,
        topicId: String = "",
        sectionId: String = ""
    ): Int {
        return when (englishWord.trim().lowercase()) {
            "hello" -> R.drawable.img_lesson_conversation_1794310851367
            "apple", "water", "morning", "milk", "bread" -> R.drawable.img_topic_morning_intro_1794316347713
            "cat", "big", "small", "good", "new", "red" -> R.drawable.img_lesson_adjectives_1794310884758
            "friend", "teacher", "student", "he", "she", "they", "my", "your" ->
                R.drawable.img_topic_pronouns_1794316352550
            "lesson", "english" -> R.drawable.img_skill_vocabulary_1794316277453
            "one", "two", "three", "first", "ten" -> R.drawable.img_lesson_numbers_1794310853767
            "book", "table", "bag" -> R.drawable.img_lesson_objects_1794310854481
            "this", "that", "these", "those" -> R.drawable.img_topic_demonstratives_1794316348388
            "orange", "pen", "sun" -> R.drawable.img_topic_articles_1794316349035
            "in", "on", "under", "school", "room" -> R.drawable.img_lesson_places_1794310885756
            "slowly", "quickly", "well", "always", "today" -> R.drawable.img_topic_adverbs_1794316352573
            "home", "every", "here", "now" -> R.drawable.img_topic_adverbials_1794316386780
            "work", "open", "help", "play", "clean" -> R.drawable.img_lesson_actions_1794310855917
            "go", "went", "saw", "ate", "took" -> R.drawable.img_topic_irregular_verbs_1794316389451
            else -> if (sectionId.isNotBlank()) {
                getSectionImageRes(topicId, sectionId)
            } else {
                getTopicImageRes(topicId)
            }
        }
    }

    /**
     * Returns the 1-to-1 matching 3D illustration for each Practice Skill category.
     */
    @DrawableRes
    fun getSkillImageRes(category: PracticeCategory): Int {
        return when (category) {
            PracticeCategory.VOCABULARY -> R.drawable.img_skill_vocabulary_1794316277453
            PracticeCategory.GRAMMAR -> R.drawable.img_lesson_grammar_1794310851898
            PracticeCategory.READING -> R.drawable.img_skill_reading_1794316278550
            PracticeCategory.WRITING -> R.drawable.img_skill_writing_1794316279347
            PracticeCategory.LISTENING -> R.drawable.img_skill_listening_1794316280104
            PracticeCategory.SPEAKING -> R.drawable.img_skill_speaking_1794316279625
            PracticeCategory.SECTION_QUIZ -> R.drawable.img_daily_tasks_banner_1794310886631
        }
    }
}
