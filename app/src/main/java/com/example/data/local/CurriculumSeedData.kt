package com.example.data.local

import com.example.core.common.AppConstants
import com.example.core.database.AchievementEntity
import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.TopicEntity
import com.example.core.database.VocabularyEntity

/**
 * Data-driven curriculum repository containing the preserved TalkMe curriculum topic:
 * 1. Introduction — Kirish
 *
 * Follows the core teaching philosophy:
 * - Explains concepts in simple Uzbek first.
 * - Minimal vocabulary acquisition per lesson without phonetic/IPA transcriptions.
 * - Reuses learned vocabulary across grammar examples and exercises.
 */
object CurriculumSeedData {

    fun buildTopics(): List<TopicEntity> = listOf(
        TopicEntity("topic_01_introduction", AppConstants.DEFAULT_COURSE_ID, 1, "Introduction", "Kirish", "Ingliz tiliga kirish, salomlashish, tanishish va eng zarur dastlabki iboralar.", isUnlocked = true, isCompleted = false),
        TopicEntity("topic_02_grammar_fundamentals", AppConstants.DEFAULT_COURSE_ID, 2, "Grammar Fundamentals", "Grammatika asoslari", "Ingliz tilida gap qurilishi asoslari: Ega + Kesim tartibi va bog'lovchi so'zlar.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_03_numbers", AppConstants.DEFAULT_COURSE_ID, 3, "Numbers", "Sonlar", "Sanoq va tartib sonlar, yosh va vaqtni aytish.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_04_nouns", AppConstants.DEFAULT_COURSE_ID, 4, "Nouns", "Otlar", "Birlik va ko'plikdagi otlar, s/es qo'shimchalari.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_05_demonstratives", AppConstants.DEFAULT_COURSE_ID, 5, "Demonstratives", "Ko'rsatkichlar", "Yaqin va uzoqdagi narsalarni ko'rsatish: this, that, these, those.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_06_adjectives", AppConstants.DEFAULT_COURSE_ID, 6, "Adjectives", "Sifatlar", "Narsa va shaxslarning belgisi, hajmi va xususiyatini ifodalash.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_07_articles", AppConstants.DEFAULT_COURSE_ID, 7, "Articles", "Artikllar", "a, an va the artikllarining to'g'ri ishlatilishi.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_08_prepositions", AppConstants.DEFAULT_COURSE_ID, 8, "Prepositions", "Predloglar", "O'rin-joy va vaqt predloglari: in, on, at, under.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_09_pronouns", AppConstants.DEFAULT_COURSE_ID, 9, "Pronouns", "Olmoshlar", "Kishilik va egalik olmoshlari: I, you, he, she, my, your.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_10_adverbs", AppConstants.DEFAULT_COURSE_ID, 10, "Adverbs", "Ravishlar", "Harakat va holatning bajarilish tarzi va darajasi.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_11_adverbials", AppConstants.DEFAULT_COURSE_ID, 11, "Adverbials", "Hol", "Vaqt va o'rin-joy hol birikmalari.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_12_verbs", AppConstants.DEFAULT_COURSE_ID, 12, "Verbs", "Fe'llar", "Harakat va holat fe'llari, hozirgi va o'tgan zamon shakllari.", isUnlocked = false, isCompleted = false),
        TopicEntity("topic_13_irregular_verbs", AppConstants.DEFAULT_COURSE_ID, 13, "Irregular Verbs", "Noto'g'ri fe'llar", "Eng ko'p ishlatiladigan noto'g'ri fe'llar shakllari.", isUnlocked = false, isCompleted = false)
    )

    fun buildSections(): List<LessonSectionEntity> = listOf(
        LessonSectionEntity(
            id = "sec_01_01",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 1,
            titleEnglish = "Hello! · Hi!",
            titleUzbek = "Salomlashish va TalkMe bilan tanishish",
            objectiveUzbek = "Maqsad: Ingliz tilida salomlashish va eng asosiy dastlabki so'zlarni audio yordamida to'g'ri eshitib o'rganish.",
            explanationUzbek = "Ingliz tilini o'rganishni eng asosiy salomlashish so'zlaridan boshlaymiz. Salomlashish — har qanday muloqotning kalitidir!\n\nIngliz tilida salomlashish uchun asosan ikki so'z ishlatiladi: Hello va Hi.\n1. Hello — Rasmiy va umumiy salom berish shakli. Kattalar va o'qituvchilar bilan ishlatiladi.\n2. Hi — Do'stona, norasmiy salom berish shakli. Tengdoshlar va yaqin do'stlar bilan ishlatiladi.\n\nTalkMe haqida: TalkMe ilovasi sizga ingliz tilini noldan, murakkab terminlarsiz o'rganishga yordam beradi. Inglizcha so'zlardan qo'rqmang — har bir so'z yonidagi karnay tugmasini (🔊) bosib, uni qaytarib ayting!",
            wrapUpSummaryUzbek = "Wrap-up: Siz ingliz tilidagi eng asosiy salomlashish iboralari ('Hello' va 'Hi') hamda ularning farqini o'rgandingiz. Keyingi darsda o'z ismingizni aytishni o'rganamiz!",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_02",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 2,
            titleEnglish = "My name is ...",
            titleUzbek = "O'zingizni tanishtirish",
            objectiveUzbek = "Maqsad: O'z ismingizni ingliz tilida erkin aytish va o'zingizni tanishtirish.",
            explanationUzbek = "Salomlashgandan so'ng, muloqotdagi eng muhim qadam — o'z ismingizni aytishdir.\n\nIngliz tilida o'z ismingizni aytish uchun 'My name is ...' iborasidan foydalanasiz.\n\n'My name is' iborasining so'zma-so'z tarjimasi: 'Mening ismim ...' deganidir. Nuqtalar o'rniga o'z ismingizni qo'yasiz. Masalan: Ismingiz Ali bo'lsa, 'My name is Ali' deysiz.",
            wrapUpSummaryUzbek = "Wrap-up: 'My name is ...' iborasi orqali o'zingizni tanishtirishni o'rgandingiz. Endi istalgan inson bilan tanisha olasiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_03",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 3,
            titleEnglish = "What is your name?",
            titleUzbek = "Boshqa insonning ismini so'rash",
            objectiveUzbek = "Maqsad: Suhbatdoshingizning ismini so'rash va sodda dialog tuzish.",
            explanationUzbek = "Muloqot ikki tomonlama bo'ladi. O'zingizni tanishtirgach, suhbatdoshingizning ham ismini so'rash xushmuomalalik belgisidir.\n\nBoshqa insonning ismini so'rash uchun 'What is your name?' savolidan foydalaniladi.\nWhat — Nima, is — bo'ladi, your — sizning, name — ismingiz.\n\nBu savolga javoban suhbatdoshingiz 'My name is ...' deb javob beradi.",
            wrapUpSummaryUzbek = "Wrap-up: 'What is your name?' savoli yordamida boshqalarning ismini so'rash va sodda dialog qurishni bilib oldingiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_04",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 4,
            titleEnglish = "This is ...",
            titleUzbek = "Boshqa insonni tanishtirish",
            objectiveUzbek = "Maqsad: Yoningizdagi do'stingiz yoki hamkasbingizni boshqalarga tanishtirish.",
            explanationUzbek = "Yonimizdagi do'stimiz yoki oila a'zomizni boshqalarga tanishtirish hayotda juda ko'p uchraydi.\n\nBoshqa bir insonni tanishtirish uchun 'This is ...' iborasi ishlatiladi.\n\n'This is' iborasi 'Bu ...' degan ma'noni bildiradi. Nuqtalar o'rniga tanishtirmoqchi bo'lgan insoningizning ismini aytasiz. Masalan: do'stingiz Akromni tanishtirish uchun 'This is Akrom' deysiz.",
            wrapUpSummaryUzbek = "Wrap-up: 'This is ...' iborasi orqali uchinchi shaxsni boshqalarga tanishtirishni o'rgandingiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_05",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 5,
            titleEnglish = "Where are you from? · I am from ...",
            titleUzbek = "Qayerdan ekanligingizni aytish",
            objectiveUzbek = "Maqsad: Insonning qaysi mamlakatdanligini so'rash va o'z davlatingizni aytish.",
            explanationUzbek = "Yangi inson bilan tanishganda, uning qaysi mamlakatdanligini so'rash va o'zingizning davlatingizni aytish muloqotni samimiy qiladi.\n\n1. Insonning qayerdanligini so'rash: 'Where are you from?' (Siz qayerdansiz?)\n2. Javob berish: 'I am from ...' (Men ...danman)\n\nDiqqat! Ba'zi davlat nomlari oldidan 'the' artikli ishlatiladi (Masalan: the UK — Buyuk Britaniya).",
            wrapUpSummaryUzbek = "Wrap-up: Dunyoning istalgan chekkasidan kelgan inson bilan mamlakatingiz haqida gaplashishni o'rgandingiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_06",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 6,
            titleEnglish = "I am a ...",
            titleUzbek = "Kasbingizni aytish",
            objectiveUzbek = "Maqsad: Kasbingiz yoki mashg'ulotingizni grammatik to'g'ri ifodalash.",
            explanationUzbek = "O'z kasbingiz yoki mashg'ulotingiz haqida gapirish tanishuvning muhim qismidir.\n\nKasbingizni aytish uchun 'I am a ...' yoki 'I am an ...' iborasidan foydalanasiz.\n\nGrammatik oltin qoida:\n- Agar kasb nomi unli tovush bilan boshlansa (engineer -> e tovushi), 'an' ishlatiladi: I am an engineer (I am a engineer emas!).\n- Agar kasb nomi undosh tovush bilan boshlansa (student, doctor, worker), 'a' ishlatiladi: I am a student, I am a doctor.",
            wrapUpSummaryUzbek = "Wrap-up: Kasbingizni to'g'ri va grammatik xatolarsiz aytishni o'rgandingiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_07",
            topicId = AppConstants.INITIAL_TOPIC_ID,
            orderIndex = 7,
            titleEnglish = "Goodbye! · Bye!",
            titleUzbek = "Xayrlashish",
            objectiveUzbek = "Maqsad: Suhbatni chiroyli va xushmuomala yakunlash.",
            explanationUzbek = "Har bir samimiy muloqot chiroyli xayrlashuv bilan yakunlanadi.\n\nIngliz tilida xayrlashish uchun ikki asosiy so'z mavjud: Goodbye va Bye.\n1. Goodbye — Rasmiy va muloyim xayrlashish shakli. Kattalar bilan ishlatiladi.\n2. Bye — Do'stona, norasmiy va tezkor xayrlashuv. Do'stlar bilan ishlatiladi.",
            wrapUpSummaryUzbek = "Wrap-up: 🎉 TABRIKLAYMIZ! Siz Introduction bo'limidagi barcha 7 ta darsni muvaffaqiyatli yakunladingiz! Endi umumiy modul Wrap-up suhbatini ko'rishingiz mumkin.",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> {
        val introTopicId = AppConstants.INITIAL_TOPIC_ID
        return listOf(
            // Sec 01_01
            VocabularyEntity("voc_1_1_1", introTopicId, "sec_01_01", "hello", "salom", "Hello!", "Salom!", true, false, 0, 0L),
            VocabularyEntity("voc_1_1_2", introTopicId, "sec_01_01", "hi", "salom (do'stona)", "Hi, my friend!", "Salom, mening do'stim!", true, false, 0, 0L),
            VocabularyEntity("voc_1_1_3", introTopicId, "sec_01_01", "friend", "do'st", "Hello, my friend.", "Salom, mening do'stim.", true, false, 0, 0L),

            // Sec 01_02
            VocabularyEntity("voc_1_2_1", introTopicId, "sec_01_02", "name", "ism", "My name is Ali.", "Mening ismim Ali.", false, false, 0, 0L),
            VocabularyEntity("voc_1_2_2", introTopicId, "sec_01_02", "my", "mening", "My name is Anna.", "Mening ismim Anna.", false, false, 0, 0L),

            // Sec 01_03
            VocabularyEntity("voc_1_3_1", introTopicId, "sec_01_03", "what", "nima", "What is your name?", "Ismingiz nima?", false, false, 0, 0L),
            VocabularyEntity("voc_1_3_2", introTopicId, "sec_01_03", "your", "sizning", "What is your name?", "Ismingiz nima?", false, false, 0, 0L),

            // Sec 01_04
            VocabularyEntity("voc_1_4_1", introTopicId, "sec_01_04", "this", "bu", "This is Akrom.", "Bu — Akrom.", false, false, 0, 0L),

            // Sec 01_05
            VocabularyEntity("voc_1_5_1", introTopicId, "sec_01_05", "where", "qayerda / qayerdan", "Where are you from?", "Siz qayerdansiz?", false, false, 0, 0L),
            VocabularyEntity("voc_1_5_2", introTopicId, "sec_01_05", "from", "-dan", "I am from Uzbekistan.", "Men O'zbekistondanman.", false, false, 0, 0L),

            // Sec 01_06
            VocabularyEntity("voc_1_6_1", introTopicId, "sec_01_06", "student", "talaba", "I am a student.", "Men talabaman.", false, false, 0, 0L),
            VocabularyEntity("voc_1_6_2", introTopicId, "sec_01_06", "doctor", "shifokor", "I am a doctor.", "Men shifokorman.", false, false, 0, 0L),
            VocabularyEntity("voc_1_6_3", introTopicId, "sec_01_06", "engineer", "muhandis", "I am an engineer.", "Men muhandisman.", false, false, 0, 0L),
            VocabularyEntity("voc_1_6_4", introTopicId, "sec_01_06", "worker", "ishchi", "I am a worker.", "Men ishchiman.", false, false, 0, 0L),

            // Sec 01_07
            VocabularyEntity("voc_1_7_1", introTopicId, "sec_01_07", "goodbye", "xayr (rasmiy)", "Goodbye!", "Xayr!", false, false, 0, 0L),
            VocabularyEntity("voc_1_7_2", introTopicId, "sec_01_07", "bye", "xayr (do'stona)", "Bye, my friend!", "Xayr, mening do'stim!", false, false, 0, 0L)
        )
    }

    fun buildGrammarRules(): List<GrammarRuleEntity> {
        val introTopicId = AppConstants.INITIAL_TOPIC_ID
        return listOf(
            GrammarRuleEntity("gram_1_01", introTopicId, "sec_01_01", "Hello va Hi salomlashish iboralari", "Hello (rasmiy va odatiy) hamda Hi (do'stona) salomlashish uchun ishlatiladi.", "Hello, TalkMe! Hi, my friend!", "Salom, TalkMe! Salom, mening do'stim!", "voc_1_1_1,voc_1_1_2", true, false),
            GrammarRuleEntity("gram_1_02", introTopicId, "sec_01_02", "My name is ... iborasi", "O'z ismingizni aytishda 'My name is ...' iborasidan foydalanasiz.", "My name is Ali. My name is Kamola.", "Mening ismim Ali. Mening ismim Kamola.", "voc_1_2_1,voc_1_2_2", false, false),
            GrammarRuleEntity("gram_1_03", introTopicId, "sec_01_03", "What is your name? savoli", "Ism so'rash uchun 'What is your name?' ishlatiladi, javob esa 'My name is ...' bo'ladi.", "What is your name? - My name is John.", "Ismingiz nima? - Mening ismim Djon.", "voc_1_3_1,voc_1_3_2", false, false),
            GrammarRuleEntity("gram_1_04", introTopicId, "sec_01_04", "This is ... iborasi bilan tanishtirish", "Boshqa bir insonni tanishtirish uchun 'This is [Ism]' shakli ishlatiladi.", "This is Akrom. This is Kamola.", "Bu — Akrom. Bu — Kamola.", "voc_1_4_1", false, false),
            GrammarRuleEntity("gram_1_05", introTopicId, "sec_01_05", "Where are you from? va I am from ...", "Qayerdanligini so'rash va aytishda ushbu tayyor iboralar qo'llaniladi.", "Where are you from? - I am from Uzbekistan.", "Siz qayerdansiz? - Men O'zbekistondanman.", "voc_1_5_1,voc_1_5_2", false, false),
            GrammarRuleEntity("gram_1_06", introTopicId, "sec_01_06", "I am a ... va I am an ... (a/an artikli)", "Kasblar oldidan undosh bilan boshlansa 'a', unli bilan boshlansa 'an' qo'yiladi.", "I am a student. I am an engineer.", "Men talabaman. Men muhandisman.", "voc_1_6_1,voc_1_6_3", false, false),
            GrammarRuleEntity("gram_1_07", introTopicId, "sec_01_07", "Goodbye va Bye xayrlashuv iboralari", "Goodbye (rasmiy va muloyim) va Bye (do'stona va tezkor) xayrlashuv shakllaridir.", "Goodbye, teacher! Bye, my friend!", "Xayr, o'qituvchi! Xayr, mening do'stim!", "voc_1_7_1,voc_1_7_2", false, false)
        )
    }

    fun buildExercises(): List<ExerciseEntity> {
        val introTopicId = AppConstants.INITIAL_TOPIC_ID
        return listOf(
            ExerciseEntity(
                id = "ex_1_vocab_mc",
                topicId = introTopicId,
                sectionId = "sec_01_01",
                practiceCategory = "VOCABULARY",
                exerciseType = "MULTIPLE_CHOICE",
                difficulty = 1,
                promptText = "'hello' so'zining o'zbekcha tarjimasini toping:",
                subPromptText = "Misol: Hello, my friend.",
                audioTextToSpeak = "hello",
                optionsPipeSeparated = "salom|xayr|talaba|do'st",
                correctAnswer = "salom",
                explanationUzbek = "'hello' o'zbek tilida 'salom' degan ma'noni bildiradi.",
                vocabularyIdsCsv = "voc_1_1_1",
                grammarRuleIdsCsv = "gram_1_01"
            ),
            ExerciseEntity(
                id = "ex_1_grammar_mc",
                topicId = introTopicId,
                sectionId = "sec_01_02",
                practiceCategory = "GRAMMAR",
                exerciseType = "GRAMMAR_CORRECTION",
                difficulty = 1,
                promptText = "O'zingizni tanishtirish: Quyidagi o'zbekcha gapga mos inglizcha jumlani tanlang:",
                subPromptText = "Mening ismim Ali.",
                audioTextToSpeak = "My name is Ali.",
                optionsPipeSeparated = "My name is Ali.|What is your name?|Goodbye my friend.",
                correctAnswer = "My name is Ali.",
                explanationUzbek = "O'z ismingizni aytishda 'My name is ...' iborasidan foydalanasiz.",
                vocabularyIdsCsv = "voc_1_2_1",
                grammarRuleIdsCsv = "gram_1_02"
            ),
            ExerciseEntity(
                id = "ex_1_listening",
                topicId = introTopicId,
                sectionId = "sec_01_03",
                practiceCategory = "LISTENING",
                exerciseType = "LISTENING_COMPREHENSION",
                difficulty = 1,
                promptText = "Audioni tinglang va aytilgan inglizcha jumlani toping:",
                subPromptText = "Ma'nosi: Ismingiz nima?",
                audioTextToSpeak = "What is your name?",
                optionsPipeSeparated = "What is your name?|My name is Ali.|Where are you from?",
                correctAnswer = "What is your name?",
                explanationUzbek = "Eshitilgan jumla: 'What is your name?' (Ismingiz nima?).",
                vocabularyIdsCsv = "voc_1_3_1",
                grammarRuleIdsCsv = "gram_1_03"
            ),
            ExerciseEntity(
                id = "ex_1_reading",
                topicId = introTopicId,
                sectionId = "sec_01_05",
                practiceCategory = "READING",
                exerciseType = "READING_COMPREHENSION",
                difficulty = 1,
                promptText = "Matnni o'qing: \"Where are you from? - I am from Uzbekistan.\"",
                subPromptText = "Jumlada 'from' so'zi qanday ma'noni bildiradi?",
                audioTextToSpeak = "Where are you from? I am from Uzbekistan.",
                optionsPipeSeparated = "-dan|salom|ism",
                correctAnswer = "-dan",
                explanationUzbek = "'from' so'zi ma'lum bir joydan ekanlikni bildiruvchi '-dan' qo'shimchasi ma'nosida keladi.",
                vocabularyIdsCsv = "voc_1_5_2",
                grammarRuleIdsCsv = "gram_1_05"
            ),
            ExerciseEntity(
                id = "ex_1_writing",
                topicId = introTopicId,
                sectionId = "sec_01_01",
                practiceCategory = "WRITING",
                exerciseType = "TRANSLATION",
                difficulty = 2,
                promptText = "'salom' so'zini ingliz tilida yozing:",
                subPromptText = "Maslahat: Rasmiy salomlashuv so'zi.",
                audioTextToSpeak = "hello",
                optionsPipeSeparated = "",
                correctAnswer = "hello",
                explanationUzbek = "To'g'ri yozilishi: hello (salom).",
                vocabularyIdsCsv = "voc_1_1_1",
                grammarRuleIdsCsv = "gram_1_01"
            )
        )
    }

    fun buildAchievements(): List<AchievementEntity> = listOf(
        AchievementEntity(
            id = "ach_first_section",
            title = "Birinchi Qadam (First Step)",
            description = "Completed your first English lesson section",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_first_topic",
            title = "Mavzu Ustasi (Topic Master)",
            description = "Completed an entire curriculum topic",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_vocab_5",
            title = "So'z Boyligi (Vocabulary Builder)",
            description = "Memorized at least 5 unique English words",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_grammar_3",
            title = "Grammatika Bilimdoni (Grammar Scholar)",
            description = "Mastered 3 or more English grammar rules",
            isUnlocked = false,
            unlockedTimestamp = 0L
        )
    )
}
