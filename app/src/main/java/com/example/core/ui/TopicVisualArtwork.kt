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
            secLower == "sec_a0_01" || secLower == "sec_01_01" -> R.drawable.img_lesson_conversation_1794310851367
            secLower == "sec_a0_02" -> R.drawable.img_topic_morning_intro_1794316347713
            secLower == "sec_a0_03" -> R.drawable.img_lesson_adjectives_1794310884758
            secLower == "sec_a0_04" || secLower == "sec_a0_05" -> R.drawable.img_lesson_numbers_1794310853767
            secLower == "sec_a0_06" -> R.drawable.img_lesson_adjectives_1794310884758
            secLower == "sec_a0_07" -> R.drawable.img_topic_pronouns_1794316352550
            secLower == "sec_a0_08" -> R.drawable.img_lesson_actions_1794310855917
            secLower == "sec_a0_09" -> R.drawable.img_lesson_objects_1794310854481
            secLower in listOf("sec_a0_10", "sec_a0_11", "sec_a0_12", "sec_a0_13") ->
                R.drawable.img_topic_morning_intro_1794316347713
            secLower == "sec_a0_14" -> R.drawable.img_lesson_objects_1794310854481
            secLower == "sec_a0_15" || secLower == "sec_a0_16" -> R.drawable.img_lesson_places_1794310885756
            secLower == "sec_a0_17" -> R.drawable.img_skill_vocabulary_1794316277453
            secLower == "sec_a0_18" -> R.drawable.img_topic_morning_intro_1794316347713
            secLower == "sec_a0_19" || secLower == "sec_a0_20" -> R.drawable.img_lesson_adjectives_1794310884758
            secLower in listOf("sec_a0_21", "sec_a0_22", "sec_a0_23", "sec_a0_24", "sec_a0_25") ->
                R.drawable.img_lesson_actions_1794310855917
            secLower in listOf("sec_a0_26", "sec_a0_27", "sec_a0_28", "sec_a0_29", "sec_a0_30") ->
                R.drawable.img_lesson_actions_1794310855917
            secLower in listOf("sec_a0_31", "sec_a0_32", "sec_a0_33") ->
                R.drawable.img_topic_articles_1794316349035
            secLower == "sec_a0_34" || secLower == "sec_a0_35" ->
                R.drawable.img_topic_demonstratives_1794316348388
            secLower in listOf("sec_a0_36", "sec_a0_37", "sec_a0_38") ->
                R.drawable.img_lesson_adjectives_1794310884758
            secLower in listOf("sec_a0_39", "sec_a0_40", "sec_a0_41") ->
                R.drawable.img_daily_tasks_banner_1794310886631
            secLower == "sec_a0_42" || secLower == "sec_a0_43" ->
                R.drawable.img_lesson_places_1794310885756
            secLower == "sec_a0_44" ->
                R.drawable.img_lesson_actions_1794310855917
            secLower in listOf("sec_a0_45", "sec_a0_46", "sec_a0_47") ->
                R.drawable.img_topic_morning_intro_1794316347713
            secLower == "sec_a0_48" ->
                R.drawable.img_lesson_adjectives_1794310884758
            secLower == "sec_a0_49" ->
                R.drawable.img_topic_morning_intro_1794316347713
            secLower == "sec_a0_50" ->
                R.drawable.img_topic_pronouns_1794316352550
            secLower == "sec_a0_51" ->
                R.drawable.img_lesson_objects_1794310854481
            secLower == "sec_a0_52" ->
                R.drawable.img_topic_pronouns_1794316352550
            secLower == "sec_a0_53" ->
                R.drawable.img_hero_learning_1794304411125
            secLower.startsWith("sec_a1") ->
                R.drawable.img_lesson_actions_1794310855917
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
            "hello", "hi", "welcome", "welcome to talkme!", "good bye" ->
                R.drawable.img_lesson_conversation_1794310851367
            "what’s your name?", "what's your name?", "my name is", "sorry", "thank you" ->
                R.drawable.img_topic_morning_intro_1794316347713
            "i love english", "lesson", "english", "learn" ->
                R.drawable.img_skill_vocabulary_1794316277453
            "apple", "an apple", "an egg", "an apple and an egg", "a cup and an apple",
            "this apple", "that apple", "these apples", "those apples", "water", "morning", "milk", "bread" ->
                R.drawable.img_topic_morning_intro_1794316347713
            "cat", "a cat", "a dog", "an elephant", "a cat and a dog", "an elephant and a cat", "big", "small", "good", "new", "red",
            "tiger", "one tiger", "two tigers", "three tigers", "four tigers", "five tigers", "six tigers",
            "seven tigers", "eight tigers", "nine tigers", "ten tigers",
            "sweet", "sour", "salty", "bitter", "hot", "cold",
            "bigger", "smaller", "sweeter", "faster", "hotter", "softer", "than", "more", "more beautiful", "more interesting",
            "the biggest", "the smallest", "the sweetest", "the fastest", "the hottest", "the softest", "the most", "the most beautiful", "the most interesting" ->
                R.drawable.img_lesson_adjectives_1794310884758
            "homework", "my homework", "his homework", "our homework", "her work" ->
                R.drawable.img_daily_tasks_banner_1794310886631
            "do", "does", "like", "do you like", "does he like", "do not", "does not", "don’t", "doesn’t",
            "i do not like", "he does not like", "they do not like" ->
                R.drawable.img_lesson_actions_1794310855917
            "yes, i do.", "no, i do not.", "yes, he does.", "no, he does not." ->
                R.drawable.img_lesson_conversation_1794310851367
            "friend", "teacher", "student", "he", "she", "they", "my", "your" ->
                R.drawable.img_topic_pronouns_1794316352550
            "mistake", "and" -> R.drawable.img_lesson_grammar_1794310851898
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "first",
            "the first tiger", "the second tiger", "the third tiger", "the fourth tiger", "the fifth tiger",
            "the sixth tiger", "the seventh tiger", "the eighth tiger", "the ninth tiger", "the tenth tiger" ->
                R.drawable.img_lesson_numbers_1794310853767
            "book", "a book", "a cup", "a book and a cup", "a pen and a book",
            "this book", "this cup", "that book", "that cup", "these books", "these cups", "those books", "those cups", "table", "bag" ->
                R.drawable.img_lesson_objects_1794310854481
            "this", "that", "these", "those" -> R.drawable.img_topic_demonstratives_1794316348388
            "have", "i have", "have i got", "i have not got", "i haven’t got", "i haven't got",
            "yes, i have.", "no, i have not.", "but", "no" -> R.drawable.img_lesson_grammar_1794310851898
            "i have a book.", "i have a cup.", "have i got a book?",
            "i have not got a book.", "i have not got a cup.",
            "i have one book.", "i have two books.", "i have three cups.",
            "i have no cups.", "i have no books." -> R.drawable.img_lesson_objects_1794310854481
            "i have a pen.", "have i got a pen?", "i have not got a pen.",
            "i have one pen.", "i have five pens.", "i have no pens.",
            "i have an apple.", "i have an egg.", "have i got an apple?", "have i got an egg?",
            "i have not got an apple.", "i have not got an egg.",
            "i have three apples.", "i have ten apples.", "i have no eggs." -> R.drawable.img_topic_articles_1794316349035
            "i have a cat.", "i have no dogs." -> R.drawable.img_lesson_adjectives_1794310884758
            "orange", "an orange", "pen", "a pen", "an umbrella", "an orange and an umbrella", "sun" ->
                R.drawable.img_topic_articles_1794316349035
            "in", "on", "under", "school", "room", "bed", "table", "chair", "door", "window", "my room", "in my room" -> R.drawable.img_lesson_places_1794310885756
            "slowly", "quickly", "well", "always", "today" -> R.drawable.img_topic_adverbs_1794316352573
            "home", "every", "here", "now", "here is", "here are", "there", "there is", "there are" ->
                R.drawable.img_topic_adverbials_1794316386780
            "a boy", "a girl", "boys", "girls", "here is a tiger.", "here is a cat.",
            "here is an apple.", "here is a book.", "here are five tigers.", "here are four books.",
            "here are six apples.", "here are ten pens." ->
                R.drawable.img_topic_demonstratives_1794316348388
            "there is a girl.", "there is a tiger.", "there is a cat.", "there is an apple.",
            "there is a book.", "there are three girls.", "there are five tigers.",
            "there are four books.", "there are six apples.", "there are ten pens.",
            "there are three cats." ->
                R.drawable.img_lesson_places_1794310885756
            "work", "open", "help", "play", "clean", "see", "give me", "show" -> R.drawable.img_lesson_actions_1794310855917
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
