package com.example.data.local

import com.example.core.database.*
import com.example.domain.model.CurriculumLevel

object A1CurriculumSeed {

    const val DARS_A1_1_SECTION_ID = "sec_a1_01"
    const val DARS_A1_2_SECTION_ID = "sec_a1_02"
    const val DARS_A1_3_SECTION_ID = "sec_a1_03"
    const val DARS_A1_4_SECTION_ID = "sec_a1_04"

    fun buildSections(): List<LessonSectionEntity> = listOf(
        LessonSectionEntity(
            id = DARS_A1_1_SECTION_ID,
            topicId = CurriculumLevel.A1.topicId,
            orderIndex = 1,
            titleEnglish = "I do my homework — “Do” (Qilmoq, bajarmoq)",
            titleUzbek = "1-Dars: I do my homework — “Do” (Qilmoq, bajarmoq)",
            objectiveUzbek = "Uy vazifasi haqida odatiy ishni ayta olish.",
            explanationUzbek = """Darsdan keyin uy vazifangizni bajarishingizni ayting. I, you, we va they bilan do ishlatiladi. He yoki she bilan does ishlatiladi.

I do my homework. — Men uy vazifamni bajaraman.
She does her homework. — U uy vazifasini bajaradi.""",
            wrapUpSummaryUzbek = """I do, she does. Uy vazifangiz haqida bitta gap tuzing. Bitta to‘g‘ri gap bilan boshlash kifoya.""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_A1_2_SECTION_ID,
            topicId = CurriculumLevel.A1.topicId,
            orderIndex = 2,
            titleEnglish = "Do you like...? — “Do” savolda",
            titleUzbek = "2-Dars: Do you like...? — “Do” savolda",
            objectiveUzbek = "Birovdan nimani yoqtirishini so‘rash va qisqa javob berish.",
            explanationUzbek = """Do‘stingiz olmalarni yoqtiradimi, deb so‘rang. Do + you + like? Uchinchi shaxsda Does + he yoki she + like? Does dan keyin like o‘zgarmaydi.

Do you like apples? — Olmani yoqtirasizmi?
Yes, I do. — Ha, yoqtiraman.""",
            wrapUpSummaryUzbek = """Do you like...? bilan do‘stingizdan nimani yoqtirishini so‘rang. Ha bo‘lsa Yes, I do. Yo‘q bo‘lsa No, I don’t.""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_A1_3_SECTION_ID,
            topicId = CurriculumLevel.A1.topicId,
            orderIndex = 3,
            titleEnglish = "I do not like... — “Do” inkorda",
            titleUzbek = "3-Dars: I do not like... — “Do” inkorda",
            objectiveUzbek = "Yoqtirmaydigan narsani odobli va aniq aytish.",
            explanationUzbek = """Taklif qilingan ichimlikni yoqtirmasangiz, “I don’t like milk” deng. He yoki she bilan doesn’t ishlating. Doesn’t dan keyin like o‘zgarmaydi.

She doesn’t like milk. — U sutni yoqtirmaydi.""",
            wrapUpSummaryUzbek = """I don’t like... bilan o‘zingiz haqingizda gapiring. Keyin He doesn’t like... qolipini sinab ko‘ring.""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_A1_4_SECTION_ID,
            topicId = CurriculumLevel.A1.topicId,
            orderIndex = 4,
            titleEnglish = "Modal fe'llar — Can, Must, May, Have to",
            titleUzbek = "4-Dars: Modal fe'llar — Can, Must, May, Have to",
            objectiveUzbek = "Kutubxonada qobiliyat, ruxsat va majburiyatni ifodalash.",
            explanationUzbek = """Kutubxonadasiz. Can bilan nima qila olishingizni ayting. May I...? bilan ruxsat so‘rang. Must yoki have to bilan qoida yoki talabni ayting.

I can read this book. — Bu kitobni o‘qiy olaman.
May I sit here? — Shu yerda o‘tirsam maylimi?
You must show your card. — Kartangizni ko‘rsatishingiz shart.
I have to return this book today. — Bu kitobni bugun qaytarishim kerak.""",
            wrapUpSummaryUzbek = """May I sit here? bilan ruxsat so‘rang. Keyin I have to... bilan bitta majburiyatni ayting. Shu ikki gap kutubxonada asqatadi.""",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity(
            id = "v_a1_01_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "do",
            uzbekTranslation = "qilmoq, bajarmoq",
            exampleEnglish = "I do my homework.",
            exampleUzbek = "Men uy vazifamni qilaman."
        ),
        VocabularyEntity(
            id = "v_a1_01_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "does",
            uzbekTranslation = "qiladi (he, she, it)",
            exampleEnglish = "He does his work.",
            exampleUzbek = "U ishini qiladi."
        ),
        VocabularyEntity(
            id = "v_a1_01_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "homework",
            uzbekTranslation = "uy vazifasi",
            exampleEnglish = "I do my homework.",
            exampleUzbek = "Men uy vazifamni qilaman."
        ),
        VocabularyEntity(
            id = "v_a1_01_04",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "my homework",
            uzbekTranslation = "mening uy vazifam",
            exampleEnglish = "I do my homework.",
            exampleUzbek = "Men uy vazifamni qilaman."
        ),
        VocabularyEntity(
            id = "v_a1_01_05",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "his homework",
            uzbekTranslation = "uning uy vazifasi",
            exampleEnglish = "He does his homework.",
            exampleUzbek = "U uy vazifasini qiladi."
        ),
        VocabularyEntity(
            id = "v_a1_01_06",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "our homework",
            uzbekTranslation = "bizning uy vazifamiz",
            exampleEnglish = "We do our homework.",
            exampleUzbek = "Biz uy vazifamizni qilamiz."
        ),
        VocabularyEntity(
            id = "v_a1_01_07",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            englishWord = "her work",
            uzbekTranslation = "uning ishi",
            exampleEnglish = "She does her work.",
            exampleUzbek = "U ishini qiladi."
        ),
        VocabularyEntity(
            id = "v_a1_02_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "like",
            uzbekTranslation = "yoqtirmoq, sevmoq",
            exampleEnglish = "I like apples.",
            exampleUzbek = "Men olmalarni yoqtiraman."
        ),
        VocabularyEntity(
            id = "v_a1_02_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "do you like",
            uzbekTranslation = "yoqtirasizmi?",
            exampleEnglish = "Do you like tea?",
            exampleUzbek = "Choyni yoqtirasizmi?"
        ),
        VocabularyEntity(
            id = "v_a1_02_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "does he like",
            uzbekTranslation = "u yoqtiradimi?",
            exampleEnglish = "Does he like milk?",
            exampleUzbek = "U sutni yoqtiradimi?"
        ),
        VocabularyEntity(
            id = "v_a1_02_04",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "yes, i do.",
            uzbekTranslation = "ha, yoqtiraman.",
            exampleEnglish = "Yes, I do like it.",
            exampleUzbek = "Ha, men buni yoqtiraman."
        ),
        VocabularyEntity(
            id = "v_a1_02_05",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "no, i do not.",
            uzbekTranslation = "yo'q, yoqtirmayman.",
            exampleEnglish = "No, I do not like it.",
            exampleUzbek = "Yo'q, men buni yoqtirmayman."
        ),
        VocabularyEntity(
            id = "v_a1_02_06",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "yes, he does.",
            uzbekTranslation = "ha, u yoqtiradi.",
            exampleEnglish = "Yes, he does like cats.",
            exampleUzbek = "Ha, u mushuklarni yoqtiradi."
        ),
        VocabularyEntity(
            id = "v_a1_02_07",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            englishWord = "no, he does not.",
            uzbekTranslation = "yo'q, u yoqtirmaydi.",
            exampleEnglish = "No, he does not like cats.",
            exampleUzbek = "Yo'q, u mushuklarni yoqtirmaydi."
        ),
        VocabularyEntity(
            id = "v_a1_03_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "do not",
            uzbekTranslation = "qilmayman, inkor",
            exampleEnglish = "I do not know.",
            exampleUzbek = "Men bilmayman."
        ),
        VocabularyEntity(
            id = "v_a1_03_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "does not",
            uzbekTranslation = "qilmaydi, inkor",
            exampleEnglish = "He does not know.",
            exampleUzbek = "U bilmaydi."
        ),
        VocabularyEntity(
            id = "v_a1_03_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "don't",
            uzbekTranslation = "qilmayman (qisqa)",
            exampleEnglish = "I don't like it.",
            exampleUzbek = "Men buni yoqtirmayman."
        ),
        VocabularyEntity(
            id = "v_a1_03_04",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "doesn't",
            uzbekTranslation = "qilmaydi (qisqa)",
            exampleEnglish = "He doesn't like it.",
            exampleUzbek = "U buni yoqtirmaydi."
        ),
        VocabularyEntity(
            id = "v_a1_03_05",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "i do not like",
            uzbekTranslation = "men yoqtirmayman",
            exampleEnglish = "I do not like cold tea.",
            exampleUzbek = "Men sovuq choyni yoqtirmayman."
        ),
        VocabularyEntity(
            id = "v_a1_03_06",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "he does not like",
            uzbekTranslation = "u yoqtirmaydi",
            exampleEnglish = "He does not like cold water.",
            exampleUzbek = "U sovuq suvni yoqtirmaydi."
        ),
        VocabularyEntity(
            id = "v_a1_03_07",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            englishWord = "they do not like",
            uzbekTranslation = "ular yoqtirmaydilar",
            exampleEnglish = "They do not like dirty shoes.",
            exampleUzbek = "Ular iflos poyabzalni yoqtirmaydilar."
        ),
        VocabularyEntity(
            id = "v_a1_04_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "modal",
            uzbekTranslation = "modal fe'l turi",
            exampleEnglish = "English modal verbs.",
            exampleUzbek = "Ingliz tilidagi modal fe’llar."
        ),
        VocabularyEntity(
            id = "v_a1_04_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "i can go",
            uzbekTranslation = "men bora olaman",
            exampleEnglish = "I can go to the park.",
            exampleUzbek = "Men parkga bora olaman."
        ),
        VocabularyEntity(
            id = "v_a1_04_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "i must go",
            uzbekTranslation = "men borishim shart",
            exampleEnglish = "I must go to school now.",
            exampleUzbek = "Men hozir maktabga borishim shart."
        ),
        VocabularyEntity(
            id = "v_a1_04_04",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "may i go?",
            uzbekTranslation = "borsam maylimi?",
            exampleEnglish = "May I go home?",
            exampleUzbek = "Uyga borsam maylimi?"
        ),
        VocabularyEntity(
            id = "v_a1_04_05",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "have to",
            uzbekTranslation = "majbur bo'lmoq",
            exampleEnglish = "I have to do homework.",
            exampleUzbek = "Uy vazifamni bajarishim kerak."
        ),
        VocabularyEntity(
            id = "v_a1_04_06",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "you may go",
            uzbekTranslation = "borishingiz mumkin",
            exampleEnglish = "You may go now.",
            exampleUzbek = "Hozir borishingiz mumkin."
        ),
        VocabularyEntity(
            id = "v_a1_04_07",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            englishWord = "he can go",
            uzbekTranslation = "u bora oladi",
            exampleEnglish = "He can go with us.",
            exampleUzbek = "U biz bilan bora oladi."
        )
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "g_a1_01_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            titleUzbek = "Do va Does fe'li",
            explanationUzbek = """I/you/we/they bilan do, he/she/it bilan does ishlatiladi.""",
            exampleEnglish = "I do my homework.",
            exampleUzbek = "Men uy vazifamni qilaman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a1_02_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            titleUzbek = "Do va Does savol shakli",
            explanationUzbek = """Savolda Do yoki Does gapning eng boshiga chiqadi.""",
            exampleEnglish = "Do you like apples?",
            exampleUzbek = "Olmani yoqtirasizmi?",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a1_03_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            titleUzbek = "Do not va Does not",
            explanationUzbek = """Inkor gapda not qo'shiladi: do not (don't), does not (doesn't).""",
            exampleEnglish = "I do not like apples.",
            exampleUzbek = "Men olmani yoqtirmayman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a1_04_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            titleUzbek = "Modal fe'llar",
            explanationUzbek = """Can (imkoniyat), Must (majburiyat), May (ruxsat), Have to (zaruriyat).""",
            exampleEnglish = "I can go.",
            exampleUzbek = "Men bora olaman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_a1_01_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Men uy vazifamni bajaraman. Inglizcha gapni tanlang.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "I do my homework.|I does my homework.|I do her homework.",
            correctAnswer = "I do my homework.",
            explanationUzbek = "I bilan do, o‘z uy vazifangiz uchun my homework ishlatiladi.",
            vocabularyIdsCsv = "v_a1_01_01",
            grammarRuleIdsCsv = "g_a1_01_01"
        ),
        ExerciseEntity(
            id = "ex_a1_01_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "He bilan qaysi biri keladi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "He does his homework.",
            optionsPipeSeparated = "does|do|doing",
            correctAnswer = "does",
            explanationUzbek = """He, she, it bilan does bo'ladi.""",
            vocabularyIdsCsv = "v_a1_01_01",
            grammarRuleIdsCsv = "g_a1_01_01"
        ),
        ExerciseEntity(
            id = "ex_a1_01_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I ______ my homework.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I do my homework.",
            optionsPipeSeparated = "do|does|did",
            correctAnswer = "do",
            explanationUzbek = """I bilan do ishlatiladi.""",
            vocabularyIdsCsv = "v_a1_01_01",
            grammarRuleIdsCsv = "g_a1_01_01"
        ),
        ExerciseEntity(
            id = "ex_a1_02_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Savolda “you” bilan qaysi so'z oldinga chiqadi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Do you like apples?",
            optionsPipeSeparated = "Do|Does|Is",
            correctAnswer = "Do",
            explanationUzbek = """You bilan Do ishlatiladi.""",
            vocabularyIdsCsv = "v_a1_02_01",
            grammarRuleIdsCsv = "g_a1_02_01"
        ),
        ExerciseEntity(
            id = "ex_a1_02_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Do you like apples?” javobi:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Yes, I do.",
            optionsPipeSeparated = "Yes, I do.|Yes, I am.|Yes, I can.",
            correctAnswer = "Yes, I do.",
            explanationUzbek = """Do bilan berilgan savolga: Yes, I do.""",
            vocabularyIdsCsv = "v_a1_02_01",
            grammarRuleIdsCsv = "g_a1_02_01"
        ),
        ExerciseEntity(
            id = "ex_a1_02_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Do‘stingiz singlisidan so‘rab berdi. Savolni to‘ldiring: ___ she like apples?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "Does|Do|Is",
            correctAnswer = "Does",
            explanationUzbek = "She bilan Does qo‘llanadi. Like oddiy shaklda qoladi.",
            vocabularyIdsCsv = "v_a1_02_01",
            grammarRuleIdsCsv = "g_a1_02_01"
        ),
        ExerciseEntity(
            id = "ex_a1_03_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I do not like” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I do not like",
            optionsPipeSeparated = "men yoqtirmayman|men yoqtiraman|men bormayman",
            correctAnswer = "men yoqtirmayman",
            explanationUzbek = """I do not like — men yoqtirmayman.""",
            vocabularyIdsCsv = "v_a1_03_01",
            grammarRuleIdsCsv = "g_a1_03_01"
        ),
        ExerciseEntity(
            id = "ex_a1_03_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“do not” ning qisqa shakli:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "don't",
            optionsPipeSeparated = "don't|doesn't|didn't",
            correctAnswer = "don't",
            explanationUzbek = """do not = don't.""",
            vocabularyIdsCsv = "v_a1_03_01",
            grammarRuleIdsCsv = "g_a1_03_01"
        ),
        ExerciseEntity(
            id = "ex_a1_03_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: He ______ not like cats.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "He does not like cats.",
            optionsPipeSeparated = "does|do|is",
            correctAnswer = "does",
            explanationUzbek = """He bilan does ishlatiladi.""",
            vocabularyIdsCsv = "v_a1_03_01",
            grammarRuleIdsCsv = "g_a1_03_01"
        ),
        ExerciseEntity(
            id = "ex_a1_04_01",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "«Bu kitobni o‘qiy olaman» degan gapni tanlang.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "I can read this book.|I must read this book.|May I read this book?",
            correctAnswer = "I can read this book.",
            explanationUzbek = "Bu yerda can qobiliyatni bildiradi.",
            vocabularyIdsCsv = "v_a1_04_01",
            grammarRuleIdsCsv = "g_a1_04_01"
        ),
        ExerciseEntity(
            id = "ex_a1_04_02",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Kutubxona qoidasi: kartangizni ko‘rsatishingiz shart.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "You must show your card.|You may show your card.|You can show your card.",
            correctAnswer = "You must show your card.",
            explanationUzbek = "Bu yerda must majburiy qoidani bildiradi.",
            vocabularyIdsCsv = "v_a1_04_01",
            grammarRuleIdsCsv = "g_a1_04_01"
        ),
        ExerciseEntity(
            id = "ex_a1_04_03",
            topicId = CurriculumLevel.A1.topicId,
            sectionId = DARS_A1_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Kutubxonada o‘tirishga ruxsat so‘rang.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "May I sit here?|I must sit here.|You may sit here.",
            correctAnswer = "May I sit here?",
            explanationUzbek = "May I...? — muloyim ruxsat so‘rash shakli.",
            vocabularyIdsCsv = "v_a1_04_01",
            grammarRuleIdsCsv = "g_a1_04_01"
        )
    )

    fun getMisollar(sectionId: String): List<CurriculumSeedData.LessonExampleSeed>? = when (sectionId) {
        DARS_A1_1_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I do my homework.", "do", "homework", "Men uy vazifamni qilaman.", "uy vazifamni"),
            CurriculumSeedData.LessonExampleSeed("He does his homework.", "does", "homework", "U uy vazifasini qiladi.", "uy vazifasini"),
            CurriculumSeedData.LessonExampleSeed("We do our homework.", "do", "homework", "Biz uy vazifamizni qilamiz.", "uy vazifamizni")
        )
        DARS_A1_2_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Do you like apples?", "Do", "apples", "Olmani yoqtirasizmi?", "olmalarni"),
            CurriculumSeedData.LessonExampleSeed("Yes, I do.", "Yes", "I do", "Ha, yoqtiraman.", "yoqtiraman"),
            CurriculumSeedData.LessonExampleSeed("Does he like cats?", "Does", "cats", "U mushuklarni yoqtiradimi?", "mushuklarni")
        )
        DARS_A1_3_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I do not like apples.", "do not", "apples", "Men olmani yoqtirmayman.", "olmalarni"),
            CurriculumSeedData.LessonExampleSeed("He does not like cats.", "does not", "cats", "U mushuklarni yoqtirmaydi.", "mushuklarni"),
            CurriculumSeedData.LessonExampleSeed("They do not like milk.", "do not", "milk", "Ular sutni yoqtirmaydilar.", "sutni")
        )
        DARS_A1_4_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I can read this book.", "can", "read", "Bu kitobni o‘qiy olaman.", "o‘qiy olaman"),
            CurriculumSeedData.LessonExampleSeed("You must show your card.", "must", "card", "Kartangizni ko‘rsatishingiz shart.", "shart"),
            CurriculumSeedData.LessonExampleSeed("May I sit here?", "May", "sit", "Shu yerda o‘tirsam maylimi?", "maylimi")
        )
        else -> null
    }
}
