package com.example.data.local

import com.example.core.database.*
import com.example.domain.model.CurriculumLevel

/** Sequential CEFR curriculum (A0–C2). Starts with A0 Lesson 1: Welcome to TalkMe. */
object CurriculumSeedData {

    const val DARS_1_SECTION_ID = "sec_a0_01"
    const val DARS_2_SECTION_ID = "sec_a0_02"
    const val DARS_3_SECTION_ID = "sec_a0_03"
    const val DARS_4_SECTION_ID = "sec_a0_04"
    const val DARS_5_SECTION_ID = "sec_a0_05"
    const val DARS_6_SECTION_ID = "sec_a0_06"
    const val DARS_7_SECTION_ID = "sec_a0_07"
    const val DARS_8_SECTION_ID = "sec_a0_08"
    const val DARS_9_SECTION_ID = "sec_a0_09"
    const val DARS_10_SECTION_ID = "sec_a0_10"
    const val DARS_11_SECTION_ID = "sec_a0_11"
    const val DARS_12_SECTION_ID = "sec_a0_12"
    const val DARS_13_SECTION_ID = "sec_a0_13"
    const val DARS_14_SECTION_ID = "sec_a0_14"
    const val DARS_15_SECTION_ID = "sec_a0_15"
    const val DARS_16_SECTION_ID = "sec_a0_16"
    const val DARS_17_SECTION_ID = "sec_a0_17"
    const val DARS_18_SECTION_ID = "sec_a0_18"
    const val DARS_19_SECTION_ID = "sec_a0_19"
    const val DARS_20_SECTION_ID = "sec_a0_20"
    const val DARS_21_SECTION_ID = "sec_a0_21"
    const val DARS_22_SECTION_ID = "sec_a0_22"
    const val DARS_23_SECTION_ID = "sec_a0_23"
    const val DARS_24_SECTION_ID = "sec_a0_24"
    const val DARS_25_SECTION_ID = "sec_a0_25"
    const val DARS_26_SECTION_ID = "sec_a0_26"
    const val DARS_27_SECTION_ID = "sec_a0_27"
    const val DARS_28_SECTION_ID = "sec_a0_28"
    const val DARS_29_SECTION_ID = "sec_a0_29"
    const val DARS_30_SECTION_ID = "sec_a0_30"
    const val DARS_31_SECTION_ID = "sec_a0_31"
    const val DARS_32_SECTION_ID = "sec_a0_32"
    const val DARS_33_SECTION_ID = "sec_a0_33"
    const val DARS_34_SECTION_ID = "sec_a0_34"
    const val DARS_35_SECTION_ID = "sec_a0_35"
    const val DARS_36_SECTION_ID = "sec_a0_36"

    val DARS_25_EXPLANATION_UZBEK = """
        Ingliz tilida a/an — qandaydir (noma'lum, birinchi marta).
        The — o'sha aniq, biz biladigan.
        The — ham bitta, ham ko'p narsa bilan ishlaydi.
    """.trimIndent()
    val DARS_25_MISOLLAR = listOf(
        LessonExampleSeed("I have a book.", "have", "book", "Menda bitta kitob bor.", "kitob"),
        LessonExampleSeed("The book is good.", "The", "good", "O'sha kitob yaxshi.", "yaxshi"),
        LessonExampleSeed("I will go to the shop.", "will", "shop", "Men do'konga boraman.", "do'konga")
    )
    val DARS_25_WRAP_UP_UZBEK = "XULOSA: A/an — noma'lum, the — aniq. The ham bitta, ham ko'p bilan ishlaydi. Tamom!"

    val DARS_26_EXPLANATION_UZBEK = """
        Will — kelasi zamon belgisi.
        Darak: I will go. Inkor: I will not go. Savol: Will I go?
        Will dan keyin fe'l o'zgarmaydi.
    """.trimIndent()
    val DARS_26_MISOLLAR = listOf(
        LessonExampleSeed("I will go to the shop.", "will", "go", "Men do'konga boraman.", "boraman"),
        LessonExampleSeed("I will not go to the shop.", "not", "go", "Men do'konga bormayman.", "bormayman"),
        LessonExampleSeed("Will I go to the shop?", "Will", "go", "Men do'konga boramanmi?", "boramanmi")
    )
    val DARS_26_WRAP_UP_UZBEK = "XULOSA: Will — kelasi zamon. Darak, inkor, savol shakllari va fe'l o'zgarmasligi. Tamom!"

    val DARS_27_EXPLANATION_UZBEK = """
        Can — qila olaman (imkoniyat).
        Darak: I can go. Inkor: I can not go. Savol: Can I go?
    """.trimIndent()
    val DARS_27_MISOLLAR = listOf(
        LessonExampleSeed("I can go to the shop.", "can", "go", "Men do'konga bora olaman.", "bora olaman"),
        LessonExampleSeed("I can not go to the shop.", "not", "go", "Men do'konga bora olmayman.", "bora olmayman"),
        LessonExampleSeed("Can I go to the shop?", "Can", "go", "Men do'konga bora olamanmi?", "bora olamanmi")
    )
    val DARS_27_WRAP_UP_UZBEK = "XULOSA: Can — imkoniyat bildiradi. Darak, inkor, savol. Tamom!"

    val DARS_28_EXPLANATION_UZBEK = """
        Must — majburiyat (kerak/shart).
        Inkor: must not (mumkin emas, taqiq). Savol: Must I go?
    """.trimIndent()
    val DARS_28_MISOLLAR = listOf(
        LessonExampleSeed("I must go to the shop.", "must", "go", "Men do'konga borishim kerak.", "borishim kerak"),
        LessonExampleSeed("I must not go.", "must not", "go", "Borishim mumkin emas (taqiq).", "mumkin emas"),
        LessonExampleSeed("Must I go?", "Must", "go", "Borishim kerakmi?", "kerakmi")
    )
    val DARS_28_WRAP_UP_UZBEK = "XULOSA: Must — majburiyat, must not — taqiq. Tamom!"

    val DARS_29_EXPLANATION_UZBEK = """
        May — ruxsat, ijozat.
        Savol: May I go? (odobliroq, rasmiy). Inkor: You may not go.
    """.trimIndent()
    val DARS_29_MISOLLAR = listOf(
        LessonExampleSeed("May I go to the shop?", "May", "go", "Do'konga borsam maylimi?", "maylimi"),
        LessonExampleSeed("You may go.", "may", "go", "Borishingiz mumkin.", "mumkin"),
        LessonExampleSeed("You may not go.", "may not", "go", "Borishingiz mumkin emas.", "mumkin emas")
    )
    val DARS_29_WRAP_UP_UZBEK = "XULOSA: May — ruxsat va odobli savollar. Tamom!"

    val DARS_30_EXPLANATION_UZBEK = """
        Have to — tashqi majburiyat (ishim bor, onam aytdi).
        Inkor: do not have to (shart emas). Savol: Do I have to go?
    """.trimIndent()
    val DARS_30_MISOLLAR = listOf(
        LessonExampleSeed("I have to go to the shop.", "have to", "go", "Do'konga borishim kerak.", "borishim kerak"),
        LessonExampleSeed("I do not have to go.", "do not", "have to", "Borishim shart emas.", "shart emas"),
        LessonExampleSeed("Do I have to go?", "Do", "have to", "Borishim shartmi?", "shartmi")
    )
    val DARS_30_WRAP_UP_UZBEK = "XULOSA: Have to — tashqi majburiyat, do not have to — shart emas. Tamom!"

    val DARS_31_EXPLANATION_UZBEK = """
        Sifat — narsaning belgisini bildiradi: “Qanday?” (How?) savoliga javob.
        Ko‘rish: big, small, new, old, fast, slow.
        Ta’m: sweet, sour, salty, bitter.
        Hid: good, bad.
        Tegish: soft, hard, hot, cold.
        Model: Narsa + is/are + sifat. The book is big. The apples are sweet.
        Darak, savol (Is the book big?), inkor (The book is not big).
    """.trimIndent()
    val DARS_31_MISOLLAR = listOf(
        LessonExampleSeed("The book is big.", "book", "big", "Kitob katta.", "katta"),
        LessonExampleSeed("The apple is sweet.", "apple", "sweet", "Olma shirin.", "shirin"),
        LessonExampleSeed("The tea is hot.", "tea", "hot", "Choy issiq.", "issiq"),
        LessonExampleSeed("The bed is soft.", "bed", "soft", "Karavot yumshoq.", "yumshoq")
    )
    val DARS_31_WRAP_UP_UZBEK = "XULOSA: Sifat “Qanday?” savoliga javob beradi. The book is big. Is the book big? The book is not big. Tamom!"

    val DARS_32_EXPLANATION_UZBEK = """
        Qiyosiy daraja: “Qandayroq?” (Which is more?) — ikki narsani solishtirish.
        1. Qisqa so‘zlar (1 bo‘g‘inli): sifatga -er qo‘shiladi (big -> bigger, fast -> faster, hot -> hotter, happy -> happier).
        2. Uzun so‘zlar (2+ bo‘g‘inli): -er qo‘shilmaydi, oldiga more qo‘yiladi (more beautiful, more interesting, more careful).
        Bog‘lovchi: than (-dan ko‘ra).
        The book is bigger than the cup. She is more beautiful than her sister.
        Darak, savol (Is the book bigger than the cup?), inkor (The book is not bigger than the cup).
    """.trimIndent()
    val DARS_32_MISOLLAR = listOf(
        LessonExampleSeed("The book is bigger than the cup.", "bigger", "than", "Kitob piyoladan kattaroq.", "kattaroq"),
        LessonExampleSeed("The tiger is faster than the cat.", "faster", "than", "Yo‘lbars mushukdan tezroq.", "tezroq"),
        LessonExampleSeed("She is more beautiful than her sister.", "more", "beautiful", "U singlisidan chiroyliroq.", "chiroyliroq"),
        LessonExampleSeed("This book is more interesting than that book.", "more", "interesting", "Bu kitob o‘sha kitobdan qiziqarliroq.", "qiziqarliroq")
    )
    val DARS_32_WRAP_UP_UZBEK = "XULOSA: Qisqa so‘zlar: -er + than (bigger than). Uzun so‘zlar: more + than (more beautiful than). Ikki narsani solishtirish. Tamom!"

    val DARS_33_EXPLANATION_UZBEK = """
        Orttirma daraja: “Eng qanday?” (Which is the most?) — uchta yoki ko‘p narsa orasida eng.
        1. Qisqa so‘zlar (1 bo‘g‘inli): the + sifat + -est (the biggest, the smallest, the fastest, the happiest).
        2. Uzun so‘zlar (2+ bo‘g‘inli): the most qo‘yiladi (the most beautiful, the most interesting, the most careful).
        The — har doim qo‘yiladi!
        The book is the biggest. She is the most beautiful girl.
        Darak, savol (Is the book the biggest?), inkor (The book is not the biggest).
    """.trimIndent()
    val DARS_33_MISOLLAR = listOf(
        LessonExampleSeed("The book is the biggest.", "the", "biggest", "Kitob eng katta.", "eng katta"),
        LessonExampleSeed("The tiger is the fastest.", "the", "fastest", "Yo‘lbars eng tez.", "eng tez"),
        LessonExampleSeed("She is the most beautiful girl.", "the", "most", "U eng chiroyli qiz.", "eng chiroyli"),
        LessonExampleSeed("This book is the most interesting.", "the", "most", "Bu kitob eng qiziqarli.", "eng qiziqarli")
    )
    val DARS_33_WRAP_UP_UZBEK = "XULOSA: Qisqa so‘zlar: the + -est (the biggest). Uzun so‘zlar: the most (the most beautiful). The har doim qo‘yiladi! Tamom!"

    val DARS_34_EXPLANATION_UZBEK = """
        Do — bu “qilmoq” degani. Ya’ni biror ish-harakatni bajarish.
        Do — asosiy fe’l, mustaqil ishlaydi.
        Savol: “Nima qilasan?” — What do you do? I do my homework.
        He, she, it bilan — does bo‘ladi.
        I do, you do, we do, they do — do.
        He does, she does, it does — does.
        Topic: My Homework. I do my homework. He does his homework.
        Darak: I do my homework. Savol: Do I do my homework? Inkor: I do not do my homework.
    """.trimIndent()
    val DARS_34_MISOLLAR = listOf(
        LessonExampleSeed("I do my homework.", "do", "homework", "Men uy vazifamni qilaman.", "uy vazifamni"),
        LessonExampleSeed("He does his homework.", "does", "homework", "U uy vazifasini qiladi.", "uy vazifasini"),
        LessonExampleSeed("She does her work.", "does", "work", "U ishini qiladi.", "ishini"),
        LessonExampleSeed("We do our homework.", "do", "homework", "Biz uy vazifamizni qilamiz.", "uy vazifamizni")
    )
    val DARS_34_WRAP_UP_UZBEK = "XULOSA: Do — qilmoq (asosiy fe'l). I/you/we/they do, he/she/it does. I do my homework. He does his homework. Tamom!"

    val DARS_35_EXPLANATION_UZBEK = """
        Do — savol qilish uchun yordamchi fe’l sifatida ishlatiladi.
        Do gapning boshida turadi.
        Do you like...? — Siz ... yaxshi ko‘rasizmi?
        Do — I, you, we, they bilan. Does — he, she, it bilan.
        Muhim: do/does dan keyin asosiy fe’l o‘zgarmaydi (faqat like, he bo'lsa ham).
        Topic: Likes. Do you like apples?
        Javob: Yes, I do. / No, I do not. Yes, he does. / No, he does not.
    """.trimIndent()
    val DARS_35_MISOLLAR = listOf(
        LessonExampleSeed("Do you like apples?", "Do", "like", "Olmalarni yaxshi ko‘rasizmi?", "yaxshi ko‘rasizmi"),
        LessonExampleSeed("Does he like cats?", "Does", "like", "U mushuklarni yaxshi ko‘radimi?", "yaxshi ko‘radimi"),
        LessonExampleSeed("Do you like English?", "Do", "like", "Ingliz tilini yaxshi ko‘rasizmi?", "yaxshi ko‘rasizmi"),
        LessonExampleSeed("Yes, I do.", "Yes", "do", "Ha, yaxshi ko‘raman.", "yaxshi ko‘raman")
    )
    val DARS_35_WRAP_UP_UZBEK = "XULOSA: Savolda Do/Does gap boshida keladi. Do you like...? Does he like...? Asosiy fe'l o'zgarmaydi! Javob: Yes, I do / No, I do not. Tamom!"

    val DARS_36_EXPLANATION_UZBEK = """
        Do — inkor uchun ham ishlatiladi: “qilmayman”, “yoqtirmayman”.
        Do not — I, you, we, they bilan. Does not — he, she, it bilan.
        Qisqa shakl: do not -> don’t, does not -> doesn’t.
        Muhim: do not / does not dan keyin fe’l o‘zgarmaydi (faqat like).
        Topic: Likes. I do not like apples. He does not like cats.
        Darak va inkor: I like apples -> I do not like apples.
    """.trimIndent()
    val DARS_36_MISOLLAR = listOf(
        LessonExampleSeed("I do not like apples.", "do", "not", "Men olmalarni yaxshi ko‘rmayman.", "yaxshi ko‘rmayman"),
        LessonExampleSeed("He does not like cats.", "does", "not", "U mushuklarni yaxshi ko‘rmaydi.", "yaxshi ko‘rmaydi"),
        LessonExampleSeed("I don’t like apples.", "don’t", "like", "Men olmalarni yoqtirmayman.", "yoqtirmayman"),
        LessonExampleSeed("He doesn’t like cats.", "doesn’t", "like", "U mushuklarni yoqtirmaydi.", "yoqtirmaydi")
    )
    val DARS_36_WRAP_UP_UZBEK = "XULOSA: Inkor uchun do not (don't) va does not (doesn't) ishlatiladi. Asosiy fe'l o'zgarmaydi! I do not like apples. He does not like cats. Tamom!"

    data class LessonDialogueLine(
        val speaker: String,
        val text: String,
        val isLearner: Boolean
    )

    data class LessonExampleSeed(
        val englishSentence: String,
        val blueHighlightWord: String,
        val greenHighlightWord: String,
        val uzbekSentence: String,
        val uzbekGreenWord: String
    )

    val DARS_1_TALKME_TTS_TEXT = """
        Salom, aziz do‘stim!
        TalkMe ilovasiga xush kelibsiz!
        Bu yerda siz ingliz tilini o‘rganasiz.
        TalkMe — ingliz tili mashg‘ulotlari uchun yordamchi ilova.
        Har bir darsda siz bilan birga bo‘lamiz.
        Savollaringizga javob beramiz.
        Tushunmagan joyingizni qayta tushuntiramiz.
        Sizni doim rag‘batlantiramiz.
        Sizdan hech narsa talab qilmaymiz.
        Xato qilsangiz, xafa bo‘lmang.
        Har bir xato — bu o‘rganishning bir qismi.
    """.trimIndent()

    val DARS_2_EXPLANATION_UZBEK = """
        Bugun biz 9 ta oddiy iborani o‘rganamiz.
        Va ular qanday ishlashini TalkMe bilan Lera suhbati orqali ko‘ramiz.
        Lera — xuddi siz kabi, endi ingliz tilini o‘rganyapti.
        U ham xato qiladi, u ham Sorry deydi.
        Qarang, qanday qilib Hello bilan boshlanib, Good bye bilan tugaydi.
    """.trimIndent()

    val DARS_2_DIALOGUE_OUTRO_UZBEK = """
        Ko‘rdingizmi? Lera Hi dedi, ismini aytdi, Sorry bilan so‘radi, Thank you dedi, I love English dedi.
        Hammasi — bizning 9 ta ibora. Siz ham xuddi Lera kabi qila olasiz.
        Xato qilsangiz — Sorry deb davom eting. Tamom.
    """.trimIndent()

    val DARS_2_DIALOGUE_LINES = listOf(
        LessonDialogueLine("TalkMe", "Hello, Lera! Welcome to TalkMe!", isLearner = false),
        LessonDialogueLine("Lera", "Hi! Thank you.", isLearner = true),
        LessonDialogueLine("TalkMe", "What’s your name?", isLearner = false),
        LessonDialogueLine("Lera", "My name is Lera.", isLearner = true),
        LessonDialogueLine("TalkMe", "Nice to meet you, Lera.", isLearner = false),
        LessonDialogueLine("Lera", "Sorry, what?", isLearner = true),
        LessonDialogueLine("TalkMe", "“Nice to meet you” — tanishganimdan xursandman.", isLearner = false),
        LessonDialogueLine("Lera", "Oh, thank you! I love English!", isLearner = true),
        LessonDialogueLine("TalkMe", "Great, Lera! Good bye!", isLearner = false),
        LessonDialogueLine("Lera", "Good bye!", isLearner = true)
    )

    val DARS_2_DIALOGUE_FULL_TTS = listOf(
        "Hello, Lera! Welcome to TalkMe!",
        "Hi! Thank you.",
        "What’s your name?",
        "My name is Lera.",
        "Nice to meet you, Lera.",
        "Sorry, what?",
        "Oh, thank you! I love English!",
        "Great, Lera! Good bye!",
        "Good bye!"
    ).joinToString(" ")

    val DARS_2_MISOLLAR = listOf(
        LessonExampleSeed("Hello! Welcome to TalkMe!", "Hello", "Welcome", "Salom! TalkMe’ga xush kelibsiz!", "xush kelibsiz"),
        LessonExampleSeed("Hi! Thank you.", "Hi", "Thank", "Salom! Rahmat.", "Rahmat"),
        LessonExampleSeed("What’s your name? — My name is Lera.", "name", "Lera", "Ismingiz nima? — Mening ismim Lera.", "Mening ismim"),
        LessonExampleSeed("Sorry, what?", "Sorry", "what", "Kechirasiz, nima dedingiz?", "Kechirasiz"),
        LessonExampleSeed("Thank you! I love English!", "Thank", "English", "Rahmat! Men ingliz tilini yaxshi ko‘raman!", "yaxshi ko‘raman"),
        LessonExampleSeed("Good bye!", "Good bye", "", "Xayr!", "Xayr")
    )

    val DARS_2_WRAP_UP_UZBEK = """
        Bugun biz Lera bilan birga birinchi suhbatni o‘rgandik. Mana 9 ta asosiy ibora:
        • Hello — rasmiy salom.
        • Hi — do‘stona salom.
        • Sorry — kechirasiz / tushunmadim.
        • What’s your name? — Ismingiz nima?
        • My name is ... — Mening ismim ...
        • Welcome to TalkMe! — TalkMe’ga xush kelibsiz!
        • Thank you! — Rahmat!
        • I love English! — Men ingliz tilini yaxshi ko‘raman!
        • Good bye! — Xayr!

        Xato qilsangiz, qo‘rqmang — Sorry deb davom eting! 👋
    """.trimIndent()

    val DARS_3_EXPLANATION_UZBEK = """
        O‘zbek tilida biz shunchaki “kitob” deymiz.
        Ingliz tilida esa “book” deb bo‘lmaydi — oldiga a yoki an qo‘yish kerak.
        Nega? Chunki biz bitta kitob haqida gapiryapmiz.

        Tasavvur qiling: qo‘lingizda bitta kitob bor.
        O‘zbekcha: “Kitob.”  →  Inglizcha: “A book.”

        Qo‘lingizda bitta olma bor.
        O‘zbekcha: “Olma.”  →  Inglizcha: “An apple.”

        Mana, farq shu: ingliz tilida bitta narsa haqida gapirganda, uning oldiga a yoki an qo‘yiladi. Boshqa hech narsa kerak emas. Faqat a yoki an.

        Endi — qaysi biri? Bu ham oson:
        • So‘z unli bilan boshlansa → an (an apple, an egg, an orange).
        • So‘z undosh bilan boshlansa → a (a cup, a book, a cat).

        Unli — a, e, i, o, u.
        Undosh — qolgan harflar.

        An apple. A cup. — shunday. Bitta narsa → oldida a yoki an. Tamom.
    """.trimIndent()

    val DARS_3_MISOLLAR = listOf(
        LessonExampleSeed("a cup", "a", "cup", "bitta piyola (finjon)", "piyola"),
        LessonExampleSeed("a book", "a", "book", "bitta kitob", "kitob"),
        LessonExampleSeed("a cat", "a", "cat", "bitta mushuk", "mushuk"),
        LessonExampleSeed("a pen", "a", "pen", "bitta ruchka", "ruchka"),
        LessonExampleSeed("a dog", "a", "dog", "bitta kuchuk", "kuchuk"),
        LessonExampleSeed("an apple", "an", "apple", "bitta olma", "olma"),
        LessonExampleSeed("an egg", "an", "egg", "bitta tuxum", "tuxum"),
        LessonExampleSeed("an orange", "an", "orange", "bitta apelsin", "apelsin"),
        LessonExampleSeed("an umbrella", "an", "umbrella", "bitta soyabon", "soyabon"),
        LessonExampleSeed("an elephant", "an", "elephant", "bitta fil", "fil")
    )

    val DARS_3_WRAP_UP_UZBEK = """
        Bugun siz ingliz tilida bitta narsa bilan ishlatiladigan eng muhim qoidani o‘rgandingiz:
        • O‘zbekcha “kitob” → Inglizcha “a book”, “olma” → “an apple”.
        • So‘z unli harf (a, e, i, o, u) bilan boshlansa → an: an apple, an egg, an orange, an umbrella, an elephant.
        • So‘z undosh harf bilan boshlansa → a: a cup, a book, a cat, a pen, a dog.

        Uyga vazifa: Uyingizda ko‘rib turgan 3 ta narsani a yoki an bilan ingliz tilida nomlang (masalan: a table, an apple, a cup).

        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_4_EXPLANATION_UZBEK = """
        O‘tgan darsda biz bitta narsa haqida gapirgan edik:
        a book, a cup, an apple.

        Bugun esa — ikkita narsa.
        O‘zbek tilida biz shunday deymiz: “kitob va piyola”.
        Ingliz tilida esa: “a book and a cup”.

        Ko‘rdingizmi? Faqat and qo‘shildi.
        And — bu “va” degani. Tamom.

        Muhim narsa: har bir narsa oldida o‘zining a yoki an bo‘ladi.
        • a book and a cup
        • an apple and an egg
        • a cat and a dog

        Ya’ni: birinchi narsaga a/an, keyin and, keyin ikkinchi narsaga yana a/an.
        Hech qachon tushirib qoldirmang. Har bir narsa — o‘z a/an bilan.

        Unli → an. Undosh → a. Bu o‘zgarmaydi.
        A book and a cup. — shunday. Oson.
    """.trimIndent()

    val DARS_4_MISOLLAR = listOf(
        LessonExampleSeed("a book and a cup", "and", "cup", "kitob va piyola", "piyola"),
        LessonExampleSeed("a cat and a dog", "and", "dog", "mushuk va kuchuk", "kuchuk"),
        LessonExampleSeed("a pen and a book", "and", "book", "ruchka va kitob", "kitob"),
        LessonExampleSeed("an apple and an egg", "and", "egg", "olma va tuxum", "tuxum"),
        LessonExampleSeed("an orange and an umbrella", "and", "umbrella", "apelsin va soyabon", "soyabon"),
        LessonExampleSeed("an elephant and a cat", "and", "cat", "fil va mushuk", "mushuk"),
        LessonExampleSeed("a cup and an apple", "and", "apple", "piyola va olma", "olma")
    )

    val DARS_4_WRAP_UP_UZBEK = """
        Bugun biz ikkita narsani “and” bilan bog‘lashni o‘rgandik.
        • O‘zbek tilida: “kitob va piyola” → Ingliz tilida: “a book and a cup”.
        • Faqat bitta yangi so‘z — and (“va”).
        • Har bir narsa oldida o‘zining a yoki an bo‘ladi. Unli → an. Undosh → a. Bu hech qachon o‘zgarmaydi.

        Lera ham, siz ham — endi ikkita narsani inglizcha ayta olasiz. Xato qilsangiz ham — hechqisi yo‘q.
        Bugun uyingizdagi 2 ta narsani “a ___ and a ___” deb ayting.

        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_5_EXPLANATION_UZBEK = """
        Ingliz tilida this — bu “bu” degani.
        That — bu “o‘sha” degani.

        Farqi bitta: qayerda turibdi?
        • This — qo‘lingizdagi, yoningizdagi, yaqin joydagi narsa.
        • That — uzoqroqdagi narsa (narigi stolda, xonaning narigi burchagida).

        O‘zbek tilida ham shunday deymiz: “bu kitob”, “o‘sha kitob”.
        Ingliz tilida: “this book”, “that book”. Xuddi shunday.

        Endi bitta muhim narsa: this va that kelganda, a / an aytilmaydi!
        • this book ✅   this a book ❌
        • that apple ✅   that an apple ❌

        Nega? Chunki this va that o‘zi “bitta” ni bildiradi.
        A book — bitta kitob. This book — bu kitob. A kerak emas.

        Xullas: this — bu, that — o‘sha. Oldiga a / an qo‘ymang. Tamom.
    """.trimIndent()

    val DARS_5_MISOLLAR = listOf(
        LessonExampleSeed("this book", "this", "book", "bu kitob (yaqin)", "yaqin"),
        LessonExampleSeed("this cup", "this", "cup", "bu piyola (yaqin)", "yaqin"),
        LessonExampleSeed("this apple", "this", "apple", "bu olma (yaqin)", "yaqin"),
        LessonExampleSeed("that book", "that", "book", "o‘sha kitob (uzoqroq)", "uzoqroq"),
        LessonExampleSeed("that cup", "that", "cup", "o‘sha piyola (uzoqroq)", "uzoqroq"),
        LessonExampleSeed("that apple", "that", "apple", "o‘sha olma (uzoqroq)", "uzoqroq")
    )

    val DARS_5_WRAP_UP_UZBEK = """
        Bugun biz “this” va “that” so‘zlarini o‘rgandik:
        • this — “bu” (qo‘lingizdagi, yaqin joydagi bitta narsa).
        • that — “o‘sha” (narigi stoldagi, uzoqroqdagi bitta narsa).
        • this va that bilan HECH QACHON a / an qo‘yilmaydi: this book ✅, that apple ✅.

        Uyga vazifa: Yoningizdagi 1 ta narsaga “this ___”, xonaning narigi tomonidagi 1 ta narsaga “that ___” deb ayting.

        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_6_EXPLANATION_UZBEK = """
        O‘tgan darsda bitta narsa haqida gapirgan edik:
        • this book — bu kitob (bitta, yaqin)
        • that cup — o‘sha piyola (bitta, uzoqroq)

        Bugun esa — ko‘p narsa (2 ta va undan ko‘p)!
        • These — bu “bular” degani (yaqin joydagi ko‘p narsa).
        • Those — bu “o‘shalar” degani (uzoqroqdagi ko‘p narsa).

        Ko‘p narsa bo‘lganda, so‘zning oxiriga -s qo‘shiladi:
        • book → books (kitoblar)
        • cup → cups (piyolalar)
        • apple → apples (olmalar)
        • cat → cats,  egg → eggs

        Muhim qoida: these va those bilan ham HECH QACHON a / an ishlatilmaydi!
        • these books ✅   these a books ❌
        • those apples ✅   those an apples ❌

        Xullas: these — bular (yaqin + -s), those — o‘shalar (uzoqroq + -s). A/an yo‘q. Tamom!
    """.trimIndent()

    val DARS_6_MISOLLAR = listOf(
        LessonExampleSeed("these books", "these", "books", "bu kitoblar / bular (yaqin)", "yaqin"),
        LessonExampleSeed("these cups", "these", "cups", "bu piyolalar (yaqin)", "yaqin"),
        LessonExampleSeed("these apples", "these", "apples", "bu olmalar (yaqin)", "yaqin"),
        LessonExampleSeed("those books", "those", "books", "o‘sha kitoblar / o‘shalar (uzoqroq)", "uzoqroq"),
        LessonExampleSeed("those cups", "those", "cups", "o‘sha piyolalar (uzoqroq)", "uzoqroq"),
        LessonExampleSeed("those apples", "those", "apples", "o‘sha olmalar (uzoqroq)", "uzoqroq")
    )

    val DARS_6_WRAP_UP_UZBEK = """
        Tabriklaymiz! Endi siz ham bitta, ham ko‘p narsani ko‘rsata olasiz:
        • Yaqin bitta → this book ("bu")   |   Uzoqroq bitta → that book ("o‘sha")
        • Yaqin ko‘p → these books ("bular") |   Uzoqroq ko‘p → those books ("o‘shalar")
        • Ko‘p narsa oxiriga -s qo‘shiladi: books, cups, apples, cats, eggs.
        • this / that / these / those bilan a / an ishlatilmaydi!

        Uyga vazifa: Yoningizdagi ko‘p narsalarga “these ___s”, uzoqdagi ko‘p narsalarga “those ___s” deb ayting.

        A0 darajasidagi barcha 6 ta darsni muvaffaqiyatli yakunladingiz! 🚀
    """.trimIndent()

    val DARS_7_EXPLANATION_UZBEK = """
        Have — bu fe’l. Ma’nosi: “bor bo‘lmoq”, ya’ni biror narsaga ega bo‘lmoq.
        I — men.

        I have — “men ega bo‘ldim” → “menda bor”.
        Ya’ni have o‘zi “menda bor” degani emas. Have — “bor bo‘lmoq”. I have birgalikda — “menda bor”.

        O‘zbek tilida: “Menda kitob bor”.
        Ingliz tilida: “I have a book”.
        So‘zma-so‘z: “Men ega bo‘ldim bitta kitob” → “Menda kitob bor”.

        Bitta narsa bo‘lsa, oldiga a / an qo‘yiladi:
        • I have a book.   • I have an apple.

        Xullas: I have + a / an + ot. Tamom.

        Hayotiy misollar:
        • Do‘stingiz: “Senda ruchka bormi?” → I have a pen. (Menda ruchka bor.)
        • Onangiz: “Sumkangda nima bor?” → I have a book. (Menda kitob bor.)
        • Stolda olma bor → I have an apple. (Menda olma bor.)
    """.trimIndent()

    val DARS_7_MISOLLAR = listOf(
        LessonExampleSeed("I have a book.", "I have", "book", "Menda kitob bor.", "Menda"),
        LessonExampleSeed("I have a cup.", "I have", "cup", "Menda piyola bor.", "bor"),
        LessonExampleSeed("I have a pen.", "I have", "pen", "Menda ruchka bor.", "Menda"),
        LessonExampleSeed("I have a cat.", "I have", "cat", "Menda mushuk bor.", "bor"),
        LessonExampleSeed("I have an apple.", "I have", "apple", "Menda olma bor.", "olma"),
        LessonExampleSeed("I have an egg.", "I have", "egg", "Menda tuxum bor.", "tuxum"),
        LessonExampleSeed("I have an orange.", "I have", "orange", "Menda apelsin bor.", "apelsin")
    )

    val DARS_7_WRAP_UP_UZBEK = """
        Bugun biz “menda bor” deyishni o‘rgandik:
        • Have — “bor bo‘lmoq”.  I have — “menda bor”.
        • I have a book — menda kitob bor.
        • I have an apple — menda olma bor.
        • Bitta narsa → oldiga a / an.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 3 ta narsani “I have a ___” deb ayting.
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_8_EXPLANATION_UZBEK = """
        O‘tgan darsda: I have a book — menda kitob bor.
        Endi savol. “Menda kitob bormi?” deb so‘raymiz.

        Savol uchun have oldinga chiqadi va got qo‘shiladi:
        • Have I got a book? — menda kitob bormi?

        Bu — o‘zingizdan so‘rash. Masalan, sumkangizni tekshiryapsiz: “Menda kitob bormi? Ha, bor.”

        Have I got + a / an + ot? Tamom.
        Javob: Yes, I have. yoki No, I have not.

        Hayotiy misollar:
        • Sumkangizni tekshiryapsiz: Have I got a pen? — Menda ruchka bormi?
        • Nonushtaga qarayapsiz: Have I got an egg? — Menda tuxum bormi?
        • Uyingizni eslayapsiz: Have I got a cat? — Menda mushuk bormi?
    """.trimIndent()

    val DARS_8_MISOLLAR = listOf(
        LessonExampleSeed("Have I got a book?", "Have I got", "book", "Menda kitob bormi?", "bormi"),
        LessonExampleSeed("Have I got a cup?", "Have I got", "cup", "Menda piyola bormi?", "bormi"),
        LessonExampleSeed("Have I got a pen?", "Have I got", "pen", "Menda ruchka bormi?", "bormi"),
        LessonExampleSeed("Have I got an apple?", "Have I got", "apple", "Menda olma bormi?", "bormi"),
        LessonExampleSeed("Have I got an egg?", "Have I got", "egg", "Menda tuxum bormi?", "bormi"),
        LessonExampleSeed("Yes, I have.", "Yes", "have", "Ha, bor.", "Ha, bor"),
        LessonExampleSeed("No, I have not.", "No", "not", "Yo‘q, yo‘q.", "Yo‘q")
    )

    val DARS_8_WRAP_UP_UZBEK = """
        Bugun biz savol berishni o‘rgandik:
        • Have I got a book? — menda kitob bormi?
        • Yes, I have. — ha, bor.
        • No, I have not. — yo‘q, yo‘q.
        • Savol uchun: Have I got + a / an + ot?

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 3 ta savol bering: “Have I got a ___?”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_9_EXPLANATION_UZBEK = """
        O‘tgan darsda savolni o‘rgandik: Have I got a book?
        Endi inkor — “menda kitob yo‘q”.

        Inkor uchun have dan keyin not qo‘yiladi va got qo‘shiladi:
        • I have not got a book. — menda kitob yo‘q.
        • Qisqasi: I haven’t got a book. — xuddi shu ma’no.

        I have not got + a / an + ot. Tamom.

        Hayotiy misollar:
        • Do‘stingiz: “Senda ruchka bormi?” Siz: I have not got a pen. — Menda ruchka yo‘q.
        • Onangiz: “Sumkangda olma bormi?” Siz: I have not got an apple. — Menda olma yo‘q.
        • O‘qituvchi: “Nimang yo‘q?” Siz: I have not got a book. — Menda kitob yo‘q.
    """.trimIndent()

    val DARS_9_MISOLLAR = listOf(
        LessonExampleSeed("I have not got a book.", "have not got", "book", "Menda kitob yo‘q.", "yo‘q"),
        LessonExampleSeed("I have not got a cup.", "have not got", "cup", "Menda piyola yo‘q.", "yo‘q"),
        LessonExampleSeed("I have not got a pen.", "have not got", "pen", "Menda ruchka yo‘q.", "yo‘q"),
        LessonExampleSeed("I have not got an apple.", "have not got", "apple", "Menda olma yo‘q.", "yo‘q"),
        LessonExampleSeed("I have not got an egg.", "have not got", "egg", "Menda tuxum yo‘q.", "yo‘q")
    )

    val DARS_9_WRAP_UP_UZBEK = """
        Bugun biz inkor qilishni o‘rgandik:
        • I have not got a book — menda kitob yo‘q.
        • I have not got an apple — menda olma yo‘q.

        Uchta shaklni endi bilasiz:
        • Darak: I have a book. — menda kitob bor.
        • Savol: Have I got a book? — menda kitob bormi?
        • Inkor: I have not got a book. — menda kitob yo‘q.

        Have — “bor bo‘lmoq”. I have — “menda bor”. Faqat I, faqat have. Do yo‘q. Tamom.
        Bugun 3 ta narsa haqida “I have not got a ___” deb ayting.
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_10_EXPLANATION_UZBEK = """
        Son nima?
        Son — bu “nechta?” degan savolga javob.
        Nechta kitob? Uchta. Nechta olma? Beshta.

        Sonlar har joyda kerak:
        Do‘konda narx aytganda, uyda narsa sanaganda, maktabda, oshxonada.
        Sonlarsiz gapira olmaysiz.

        1 dan 10 gacha — ovoz chiqarib takrorlang:
        • one — 1    • two — 2    • three — 3    • four — 4    • five — 5
        • six — 6    • seven — 7  • eight — 8    • nine — 9    • ten — 10
        Faqat yodlash kerak. Boshqa hech narsa.

        Bitta narsa → a book, an apple.
        Ko‘p narsa → two books, three apples.
        Oxiriga -s qo‘shildi: book → books. Tamom.
    """.trimIndent()

    val DARS_10_MISOLLAR = listOf(
        LessonExampleSeed("Two books, please.", "Two", "books", "Do‘konda: Ikkita kitob, iltimos.", "Ikkita"),
        LessonExampleSeed("Five pens, please.", "Five", "pens", "Do‘konda: Beshta ruchka, iltimos.", "Beshta"),
        LessonExampleSeed("Ten apples, please.", "Ten", "apples", "Do‘konda: O‘nta olma, iltimos.", "O‘nta"),
        LessonExampleSeed("Three cups.", "Three", "cups", "Uyda: Uchta piyola.", "Uchta"),
        LessonExampleSeed("Four eggs.", "Four", "eggs", "Uyda: To‘rtta tuxum.", "To‘rtta"),
        LessonExampleSeed("Six oranges.", "Six", "oranges", "Uyda: Oltita apelsin.", "Oltita"),
        LessonExampleSeed("One book.", "One", "book", "Maktabda: Bitta kitob.", "Bitta"),
        LessonExampleSeed("Eight pens.", "Eight", "pens", "Maktabda: Sakkizta ruchka.", "Sakkizta"),
        LessonExampleSeed("Nine cats?", "Nine", "cats", "Maktabda: To‘qqizta mushuk? (hazil!)", "To‘qqizta")
    )

    val DARS_10_WRAP_UP_UZBEK = """
        Bugun biz 1 dan 10 gacha sanashni o‘rgandik.
        One, two, three, four, five, six, seven, eight, nine, ten.
        Bitta narsa → a book, an apple.
        Ko‘p narsa → two books, three apples. Oxiriga -s.
        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun uyingizdagi 3 ta narsani sanang: “two books, five cups, three pens.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_11_EXPLANATION_UZBEK = """
        O‘tgan darslarda I have ni o‘rgandik: I have a book — menda kitob bor.
        Keyin sonlarni o‘rgandik: one, two, three... ten.
        Bugun ikkisini birlashtiramiz — nechta narsangiz borligini aytamiz.

        Bitta narsa bo‘lsa:
        • I have a book. — Menda bitta kitob bor.
        • I have an apple. — Menda bitta olma bor.

        Ko‘p narsa bo‘lsa:
        • I have two books. — Menda ikkita kitob bor.
        • I have three apples. — Menda uchta olma bor.
        a book → two books. Son qo‘shildi, oxiriga -s keldi. Tamom.

        Muhim: one bilan -s qo‘shilmaydi!
        • I have one book ✅ (to‘g‘ri)
        • I have one books ❌ (xato — chunki one bitta, -s qo‘shilmaydi)

        Xullas: I have + son + ot. Bitta → a / an. Ko‘p → son + -s. Tamom.
    """.trimIndent()

    val DARS_11_MISOLLAR = listOf(
        LessonExampleSeed("I have a book.", "I have", "a book", "Bitta narsa: Menda bitta kitob bor.", "bitta kitob"),
        LessonExampleSeed("I have a cup.", "I have", "a cup", "Bitta narsa: Menda bitta piyola bor.", "bitta piyola"),
        LessonExampleSeed("I have an apple.", "I have", "an apple", "Bitta narsa: Menda bitta olma bor.", "bitta olma"),
        LessonExampleSeed("I have one pen.", "one", "pen", "Bitta narsa: Menda bitta ruchka bor.", "bitta ruchka"),
        LessonExampleSeed("I have two books.", "two", "books", "Ko‘p narsa: Menda ikkita kitob bor.", "ikkita kitob"),
        LessonExampleSeed("I have three cups.", "three", "cups", "Ko‘p narsa: Menda uchta piyola bor.", "uchta piyola"),
        LessonExampleSeed("I have five pens.", "five", "pens", "Ko‘p narsa: Menda beshta ruchka bor.", "beshta ruchka"),
        LessonExampleSeed("I have ten apples.", "ten", "apples", "Ko‘p narsa: Menda o‘nta olma bor.", "o‘nta olma"),
        LessonExampleSeed("I have two books and a pen.", "two books", "a pen", "Hayotdan — Onangiz: “Sumkangda nima bor?” → Menda ikkita kitob va ruchka bor.", "ikkita kitob"),
        LessonExampleSeed("I have three apples.", "three", "apples", "Hayotdan — Do‘stingiz: “Nechta olmang bor?” → Menda uchta olma bor.", "uchta olma"),
        LessonExampleSeed("I have one pen.", "one", "pen", "Hayotdan — O‘qituvchi: “Nechta ruchkang bor?” → Menda bitta ruchka bor.", "bitta ruchka")
    )

    val DARS_11_WRAP_UP_UZBEK = """
        Bugun biz sonlar bilan “menda bor” ni birlashtirdik.

        • Bitta narsa → I have a book.
        • Ko‘p narsa → I have two books.
        • Muhim: one bilan -s qo‘shilmaydi → I have one book ✅ (I have one books ❌).

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun uyingizdagi 3 ta narsani sanab ayting: “I have three books. I have two cups. I have one pen.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_12_EXPLANATION_UZBEK = """
        O‘tgan darsda biz narsa sanashni o‘rgandik:
        I have two books. — Menda ikkita kitob bor.
        I have three apples. — Menda uchta olma bor.

        Bugun ikkita yangi so‘z qo‘shamiz: but va no.
        • But — “lekin”. Ikki gapni bog‘laydi.
        • No — “yo‘q”. Biror narsa yo‘qligini aytadi.

        Mana:
        • I have a book. — Menda kitob bor.
        • I have no cups. — Menda piyola yo‘q.

        Ikkisini but bilan bog‘laymiz:
        I have a book, but I have no cups. — Menda kitob bor, lekin piyola yo‘q.
        I have two books, but I have no cups. — Menda ikkita kitob bor, lekin piyola yo‘q.

        Yana misollar:
        • I have two pens, but I have no books. — Menda ikkita ruchka bor, lekin kitob yo‘q.
        • I have three apples, but I have no eggs. — Menda uchta olma bor, lekin tuxum yo‘q.

        Xullas: I have ___ , but I have no ___. Biri bor, biri yo‘q. Tamom.
    """.trimIndent()

    val DARS_12_MISOLLAR = listOf(
        LessonExampleSeed("I have a book, but I have no cups.", "but", "no", "Menda kitob bor, lekin piyola yo‘q.", "lekin"),
        LessonExampleSeed("I have two pens, but I have no books.", "but", "no", "Menda ikkita ruchka bor, lekin kitob yo‘q.", "yo‘q"),
        LessonExampleSeed("I have three apples, but I have no eggs.", "but", "no", "Menda uchta olma bor, lekin tuxum yo‘q.", "lekin"),
        LessonExampleSeed("I have a cat, but I have no dogs.", "but", "no", "Menda mushuk bor, lekin it yo‘q.", "yo‘q"),
        LessonExampleSeed("I have five books, but I have no pens.", "but", "no", "Menda beshta kitob bor, lekin ruchka yo‘q.", "lekin"),
        LessonExampleSeed("I have a book, but I have no pens.", "but", "no", "Hayotdan — Onangiz: “Nimang bor, nimang yo‘q?” → Menda kitob bor, lekin ruchka yo‘q.", "ruchka yo‘q"),
        LessonExampleSeed("I have two apples, but I have no oranges.", "but", "no", "Hayotdan — Do‘stingiz: “Nechta olmang bor?” → Menda ikkita olma bor, lekin apelsin yo‘q.", "apelsin yo‘q"),
        LessonExampleSeed("I have three books, but I have no cups.", "but", "no", "Hayotdan — O‘qituvchi: “Nimang bor?” → Menda uchta kitob bor, lekin piyola yo‘q.", "piyola yo‘q")
    )

    val DARS_12_WRAP_UP_UZBEK = """
        Bugun biz ikkita yangi so‘zni o‘rgandik:
        • But — “lekin”. Ikki gapni bog‘laydi.
        • No — “yo‘q”. Biror narsa yo‘qligini aytadi.

        Mana shunday gap tuzamiz:
        • I have a book, but I have no cups. — Menda kitob bor, lekin piyola yo‘q.
        • I have two books, but I have no cups. — Menda ikkita kitob bor, lekin piyola yo‘q.

        Bor → I have. Lekin → but. Yo‘q → no. Tamom.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 2 ta gap tuzing: “I have ___, but I have no ___.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_13_EXPLANATION_UZBEK = """
        Bugun biz yo‘lbarslarni sanaymiz.
        Tiger — yo‘lbars. Tigers — yo‘lbarslar.

        Mana ular. Ovoz chiqarib sanang:
        • one tiger — bitta yo‘lbars
        • two tigers — ikkita yo‘lbars
        • three tigers — uchta yo‘lbars
        • four tigers — to‘rtta yo‘lbars
        • five tigers — beshta yo‘lbars
        • six tigers — oltita yo‘lbars
        • seven tigers — yettita yo‘lbars
        • eight tigers — sakkizta yo‘lbars
        • nine tigers — to‘qqizta yo‘lbars
        • ten tigers — o‘nta yo‘lbars

        Ko‘rdingizmi? Bitta bo‘lsa — tiger. Ko‘p bo‘lsa — tiger-s. Oxiriga -s qo‘shildi. Tamom.

        Va yana bir narsa: bitta yo‘lbars → a tiger.
        • I have a tiger. — Menda bitta yo‘lbars bor.
        • I have three tigers. — Menda uchta yo‘lbars bor.

        Xullas: one tiger, two tigers, three tigers... ten tigers.
        Sanoq — bu “nechta?” degan savolga javob. Tamom.
    """.trimIndent()

    val DARS_13_MISOLLAR = listOf(
        LessonExampleSeed("I see three tigers.", "three", "tigers", "Hayotdan — Zoomda: Men uchta yo‘lbarsni ko‘ryapman.", "uchta yo‘lbarsni"),
        LessonExampleSeed("Two tigers.", "Two", "tigers", "Hayotdan — Rasmda: Ikkita yo‘lbars.", "Ikkita yo‘lbars"),
        LessonExampleSeed("Five tigers.", "Five", "tigers", "Hayotdan — Kitobda: Beshta yo‘lbars.", "Beshta yo‘lbars"),
        LessonExampleSeed("I have one tiger.", "one", "tiger", "Hayotdan — Uyda o‘yinchoq: Menda bitta yo‘lbars bor.", "bitta yo‘lbars"),
        LessonExampleSeed("one tiger", "one", "tiger", "bitta yo‘lbars", "bitta"),
        LessonExampleSeed("two tigers", "two", "tigers", "ikkita yo‘lbars", "ikkita"),
        LessonExampleSeed("three tigers", "three", "tigers", "uchta yo‘lbars", "uchta"),
        LessonExampleSeed("four tigers", "four", "tigers", "to‘rtta yo‘lbars", "to‘rtta"),
        LessonExampleSeed("five tigers", "five", "tigers", "beshta yo‘lbars", "beshta"),
        LessonExampleSeed("six tigers", "six", "tigers", "oltita yo‘lbars", "oltita"),
        LessonExampleSeed("seven tigers", "seven", "tigers", "yettita yo‘lbars", "yettita"),
        LessonExampleSeed("eight tigers", "eight", "tigers", "sakkizta yo‘lbars", "sakkizta"),
        LessonExampleSeed("nine tigers", "nine", "tigers", "to‘qqizta yo‘lbars", "to‘qqizta"),
        LessonExampleSeed("ten tigers", "ten", "tigers", "o‘nta yo‘lbars", "o‘nta")
    )

    val DARS_13_WRAP_UP_UZBEK = """
        Bugun biz yo‘lbarslarni sanashni o‘rgandik.

        • One tiger, two tigers, three tigers... ten tigers.
        • Bitta → tiger. Ko‘p → tiger-s. Oxiriga -s. Tamom.

        Sanoq — bu “nechta?” degan savolga javob.
        Two tigers — ikkita yo‘lbars. Nechta? Ikkita.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun uyingizdagi o‘yinchoqlarni sanang: “one tiger, three tigers, five tigers.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_14_EXPLANATION_UZBEK = """
        O‘tgan darsda biz yo‘lbarslarni sanadik: one, two, three tigers.
        Bu — “nechta?” degan savolga javob.

        Bugun esa — “nechanchi?” degan savolga javob beramiz. Ya’ni qaysi o‘rinda turibdi?
        O‘zbek tilida: “birinchi”, “ikkinchi”, “uchinchi”.
        Ingliz tilida: “first”, “second”, “third”.

        Mana yo‘lbarslar tartibi:
        • the first tiger — birinchi yo‘lbars
        • the second tiger — ikkinchi yo‘lbars
        • the third tiger — uchinchi yo‘lbars
        • the fourth tiger — to‘rtinchi yo‘lbars
        • the fifth tiger — beshinchi yo‘lbars
        • the sixth tiger — oltinchi yo‘lbars
        • the seventh tiger — yettinchi yo‘lbars
        • the eighth tiger — sakkizinchi yo‘lbars
        • the ninth tiger — to‘qqizinchi yo‘lbars
        • the tenth tiger — o‘ninchi yo‘lbars

        Ko‘pchiligida oxirida -th bor: fourth, sixth, tenth.
        Faqat first, second, third — boshqacha. Ularni alohida yodlang.

        Muhim: tartib sondan oldin the qo‘yiladi (the first tiger).
        Nega the? Chunki birinchi yo‘lbars — bu aniq bitta yo‘lbars: aynan birinchisi.

        Farqi:
        • Sanoq — nechta? → three tigers. (Uchta yo‘lbars.)
        • Tartib — nechanchi? → the third tiger. (Uchinchi yo‘lbars.)

        Xullas: the first tiger, the second tiger... the tenth tiger. Tamom.
    """.trimIndent()

    val DARS_14_MISOLLAR = listOf(
        LessonExampleSeed("the first tiger", "the", "first", "birinchi yo‘lbars", "birinchi"),
        LessonExampleSeed("the second tiger", "the", "second", "ikkinchi yo‘lbars", "ikkinchi"),
        LessonExampleSeed("the third tiger", "the", "third", "uchinchi yo‘lbars", "uchinchi"),
        LessonExampleSeed("the fourth tiger", "the", "fourth", "to‘rtinchi yo‘lbars", "to‘rtinchi"),
        LessonExampleSeed("the fifth tiger", "the", "fifth", "beshinchi yo‘lbars", "beshinchi"),
        LessonExampleSeed("the sixth tiger", "the", "sixth", "oltinchi yo‘lbars", "oltinchi"),
        LessonExampleSeed("the seventh tiger", "the", "seventh", "yettinchi yo‘lbars", "yettinchi"),
        LessonExampleSeed("the eighth tiger", "the", "eighth", "sakkizinchi yo‘lbars", "sakkizinchi"),
        LessonExampleSeed("the ninth tiger", "the", "ninth", "to‘qqizinchi yo‘lbars", "to‘qqizinchi"),
        LessonExampleSeed("the tenth tiger", "the", "tenth", "o‘ninchi yo‘lbars", "o‘ninchi"),
        LessonExampleSeed("The first tiger is big.", "first", "big", "Hayotdan — Zoomda: Birinchi yo‘lbars katta.", "Birinchi"),
        LessonExampleSeed("The second tiger is small.", "second", "small", "Hayotdan — Rasmda: Ikkinchi yo‘lbars kichkina.", "Ikkinchi"),
        LessonExampleSeed("The fifth tiger is white.", "fifth", "white", "Hayotdan — Kitobda: Beshinchi yo‘lbars oq.", "Beshinchi")
    )

    val DARS_14_WRAP_UP_UZBEK = """
        Bugun biz yo‘lbarslarning tartibini o‘rgandik.

        • The first tiger, the second tiger, the third tiger... the tenth tiger.
        • First, second, third — alohida yodlang. Qolganlarida -th: fourth, fifth, tenth.

        Va bitta muhim farq:
        • Sanoq — nechta? → three tigers.
        • Tartib — nechanchi? → the third tiger. Oldida the.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 3 ta yo‘lbarsni tartib bilan ayting: “the first tiger, the second tiger, the third tiger.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_15_EXPLANATION_UZBEK = """
        Here — bu “mana”. Va yana “mana bu” degan ma’nosi ham bor.
        Ya’ni yaqin narsani ko‘rsatganda ishlatiladi. Qo‘lingizda, oldingizda, yoningizda.

        Agar bitta narsani ko‘rsatsangiz — Here is.
        Is — bu bitta narsa uchun.

        Bitta narsa bo‘lsa, oldiga a yoki an qo‘yiladi.
        A — undosh bilan. An — unli bilan.

        Xullas: bitta narsani ko‘rsatsangiz — Here is. Tamom.

        Muhim: Here is faqat bitta narsa uchun.
        Ikkita yoki ko‘p narsa bo‘lsa — keyingi darsda ko‘ramiz.
    """.trimIndent()

    val DARS_15_MISOLLAR = listOf(
        LessonExampleSeed("Here is a boy.", "Here is", "boy", "Mana bitta bola.", "bola"),
        LessonExampleSeed("Here is a girl.", "Here is", "girl", "Mana bitta qiz.", "qiz"),
        LessonExampleSeed("Here is a tiger.", "Here is", "tiger", "Mana bitta yo‘lbars.", "yo‘lbars"),
        LessonExampleSeed("Here is a cat.", "Here is", "cat", "Mana bitta mushuk.", "mushuk"),
        LessonExampleSeed("Here is an apple.", "Here is", "apple", "Mana bitta olma.", "olma"),
        LessonExampleSeed("Here is a book.", "Here is", "book", "Mana bitta kitob.", "kitob"),
        LessonExampleSeed("Here is a boy!", "Here is", "boy", "Hayotdan — Onangiz: “Bolam qani?” → Mana bola!", "bola"),
        LessonExampleSeed("Here is a book.", "Here is", "book", "Hayotdan — Do‘stingiz: “Kitobing qani?” → Mana kitob.", "kitob"),
        LessonExampleSeed("Here is a tiger.", "Here is", "tiger", "Hayotdan — Zoomda: Mana yo‘lbars.", "yo‘lbars")
    )

    val DARS_15_WRAP_UP_UZBEK = """
        Bugun biz “mana” va “mana bu” deyishni o‘rgandik.

        • Here — mana / mana bu.
        • Bitta narsani ko‘rsatsangiz — Here is.
        • Here is a boy. — Mana bitta bola.
        • Here is an apple. — Mana bitta olma.

        Ko‘p narsa bo‘lsa, Here is emas — keyingi darsda ko‘ramiz.
        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun yoningizdagi 3 ta narsani ko‘rsatib: “Here is a ___” deb ayting.
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_16_EXPLANATION_UZBEK = """
        O‘tgan darsda Here is ni o‘rgandik — bitta narsani ko‘rsatganda ishlatiladi.

        Bugun — ko‘p narsa.
        Ko‘p narsani ko‘rsatsangiz — Here are.
        Are — bu ko‘p narsa uchun.

        Va yana bir narsa: ko‘p bo‘lsa, otning oxiriga -s qo‘shiladi.
        Boy → boys. Girl → girls. Tiger → tigers. Tamom.

        Xullas: ko‘p narsani ko‘rsatsangiz — Here are. Tamom.

        Muhim: Here are faqat ko‘p narsa uchun.
        Bitta narsa bo‘lsa — Here is (o‘tgan dars).
    """.trimIndent()

    val DARS_16_MISOLLAR = listOf(
        LessonExampleSeed("Here are two boys.", "Here are", "boys", "Mana ikkita bola.", "ikkita bola"),
        LessonExampleSeed("Here are three girls.", "Here are", "girls", "Mana uchta qiz.", "uchta qiz"),
        LessonExampleSeed("Here are five tigers.", "Here are", "tigers", "Mana beshta yo‘lbars.", "beshta yo‘lbars"),
        LessonExampleSeed("Here are four books.", "Here are", "books", "Mana to‘rtta kitob.", "to‘rtta kitob"),
        LessonExampleSeed("Here are six apples.", "Here are", "apples", "Mana oltita olma.", "oltita olma"),
        LessonExampleSeed("Here are ten pens.", "Here are", "pens", "Mana o‘nta ruchka.", "o‘nta ruchka"),
        LessonExampleSeed("Here are two boys!", "Here are", "boys", "Hayotdan — Onangiz: “Bolalar qani?” → Mana ikkita bola!", "ikkita bola"),
        LessonExampleSeed("Here are three books.", "Here are", "books", "Hayotdan — Do‘stingiz: “Kitoblaring qani?” → Mana uchta kitob.", "uchta kitob"),
        LessonExampleSeed("Here are five tigers.", "Here are", "tigers", "Hayotdan — Zoomda: Mana beshta yo‘lbars.", "beshta yo‘lbars")
    )

    val DARS_16_WRAP_UP_UZBEK = """
        Bugun biz ko‘p narsani ko‘rsatishni o‘rgandik.

        • Here — mana / mana bu.
        • Ko‘p narsani ko‘rsatsangiz — Here are.
        • Here are two boys. — Mana ikkita bola.
        • Here are three girls. — Mana uchta qiz.

        Va farqi:
        • Here is a boy. — Mana bitta bola.
        • Here are two boys. — Mana ikkita bola.

        Bitta → Here is. Ko‘p → Here are. Tamom.
        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun yoningizdagi 3 ta narsani ko‘rsatib: “Here are ___” deb ayting.
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_17_EXPLANATION_UZBEK = """
        O‘tgan darslarda Here ni o‘rgandik — mana / mana bu. Ya’ni yaqin narsa.

        Bugun — There.
        There — bu “u yerda”. Va yana “bor” degan ma’nosi ham bor.
        Ya’ni uzoqroq narsa haqida yoki umuman bor-yo‘qligi haqida gapirganda ishlatiladi.

        Bitta narsa bo‘lsa — There is.
        Is — bu bitta narsa uchun.

        Xullas: bitta narsa bor desangiz — There is. Tamom.

        Va Here bilan farqi:
        • Here is — mana, qo‘limda, yaqin.
        • There is — u yerda bor, uzoqroq yoki umuman bor.

        Muhim: There is faqat bitta narsa uchun.
        Ko‘p narsa bo‘lsa — keyingi darsda ko‘ramiz.
    """.trimIndent()

    val DARS_17_MISOLLAR = listOf(
        LessonExampleSeed("There is a boy.", "There is", "boy", "Bitta bola bor.", "bola"),
        LessonExampleSeed("There is a girl.", "There is", "girl", "Bitta qiz bor.", "qiz"),
        LessonExampleSeed("There is a tiger.", "There is", "tiger", "Bitta yo‘lbars bor.", "yo‘lbars"),
        LessonExampleSeed("There is a cat.", "There is", "cat", "Bitta mushuk bor.", "mushuk"),
        LessonExampleSeed("There is an apple.", "There is", "apple", "Bitta olma bor.", "olma"),
        LessonExampleSeed("There is a book.", "There is", "book", "Bitta kitob bor.", "kitob"),
        LessonExampleSeed("There is a boy.", "There is", "boy", "Hayotdan — Onangiz: “Xonada kim bor?” → Bitta bola bor.", "bola"),
        LessonExampleSeed("There is a tiger!", "There is", "tiger", "Hayotdan — Do‘stingiz: “Bog‘da nima bor?” → Bitta yo‘lbars bor!", "yo‘lbars"),
        LessonExampleSeed("There is a cat.", "There is", "cat", "Hayotdan — Zoomda: Bitta mushuk bor.", "mushuk")
    )

    val DARS_17_WRAP_UP_UZBEK = """
        Bugun biz “u yerda bor” deyishni o‘rgandik.

        • There — u yerda / bor.
        • Bitta narsa bor desangiz — There is.
        • There is a boy. — Bitta bola bor.
        • There is an apple. — Bitta olma bor.

        Va farqi:
        • Here is a boy. — Mana bitta bola (qo‘limda).
        • There is a boy. — Bitta bola bor (u yerda).

        Ko‘p narsa bo‘lsa, There is emas — keyingi darsda ko‘ramiz.
        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun atrofingizdagi 3 ta narsani ko‘rsatib: “There is a ___” deb ayting.
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_18_EXPLANATION_UZBEK = """
        O‘tgan darsda There is ni o‘rgandik — bitta narsa bor.

        Bugun — ko‘p narsa.
        Ko‘p narsa bor desangiz — There are.
        Are — bu ko‘p narsa uchun.

        Va yana: ko‘p bo‘lsa, otning oxiriga -s qo‘shiladi.
        Boy → boys. Tiger → tigers. Tamom.

        Xullas: ko‘p narsa bor desangiz — There are. Tamom.

        Muhim: There are faqat ko‘p narsa uchun.
        Bitta narsa bo‘lsa — There is (o‘tgan dars).
    """.trimIndent()

    val DARS_18_MISOLLAR = listOf(
        LessonExampleSeed("There are two boys.", "There are", "boys", "Ikkita bola bor.", "ikkita bola"),
        LessonExampleSeed("There are three girls.", "There are", "girls", "Uchta qiz bor.", "uchta qiz"),
        LessonExampleSeed("There are five tigers.", "There are", "tigers", "Beshta yo‘lbars bor.", "beshta yo‘lbars"),
        LessonExampleSeed("There are four books.", "There are", "books", "To‘rtta kitob bor.", "to‘rtta kitob"),
        LessonExampleSeed("There are six apples.", "There are", "apples", "Oltita olma bor.", "oltita olma"),
        LessonExampleSeed("There are ten pens.", "There are", "pens", "O‘nta ruchka bor.", "o‘nta ruchka"),
        LessonExampleSeed("There are two boys.", "There are", "boys", "Hayotdan — Onangiz: “Xonada kim bor?” → Ikkita bola bor.", "ikkita bola"),
        LessonExampleSeed("There are five tigers!", "There are", "tigers", "Hayotdan — Do‘stingiz: “Bog‘da nima bor?” → Beshta yo‘lbars bor!", "beshta yo‘lbars"),
        LessonExampleSeed("There are three cats.", "There are", "cats", "Hayotdan — Zoomda: Uchta mushuk bor.", "uchta mushuk")
    )

    val DARS_18_WRAP_UP_UZBEK = """
        Bugun biz “u yerda ko‘p narsa bor” deyishni o‘rgandik.

        • There — u yerda / bor.
        • Ko‘p narsa bor desangiz — There are.
        • There are two boys. — Ikkita bola bor.
        • There are three girls. — Uchta qiz bor.

        Endi to‘rtta shaklni bilasiz:
        • Here is a boy. — Mana bitta bola (yaqin).
        • Here are two boys. — Mana ikkita bola (yaqin).
        • There is a boy. — Bitta bola bor (u yerda).
        • There are two boys. — Ikkita bola bor (u yerda).

        Bitta → is. Ko‘p → are.
        Yaqin → here. Uzoq / bor → there. Tamom.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun atrofingizdagi 3 ta narsani ko‘rsatib: “There are ___” deb ayting.
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_19_EXPLANATION_UZBEK = """
        Bugun biz xonamiz haqida gapiramiz.
        Room — xona. My room — mening xonam.

        Xonamizda nimalar bor? Mana beshta oddiy so‘z:
        Bed — karavot. Table — stol. Chair — stul. Door — eshik. Window — deraza.

        Endi eng muhim narsa. Biz narsalar xonamizda ekanini aytmoqchimiz.
        Buning uchun ingliz tilida in my room deymiz.
        In — “ichida”. My room — “mening xonam”.
        In my room — “mening xonamda”, ya’ni xonam ichida.

        O‘zbek tilida biz “xonamda” deymiz — oxirida “-da” qo‘shiladi.
        Ingliz tilida esa oldiga in qo‘yiladi: in my room.
        Ya’ni “ichida” degan so‘z oldinda turadi. Tamom.

        Endi — xonada nima borligini qanday aytamiz?
        O‘tgan darslardagi there is va there are ni ishlatamiz.
        Bitta narsa bo‘lsa — there is. Ko‘p narsa bo‘lsa — there are.

        Va yana uchta yangi fe’l qo‘shamiz:
        See — “ko‘rmoq”. Give me — “menga ber”. Show — “ko‘rsatmoq”.
        Xullas: my room, in my room, see, give me, show. Tamom.
    """.trimIndent()

    val DARS_19_MISOLLAR = listOf(
        LessonExampleSeed("I have a bed.", "have", "bed", "Menda karavot bor.", "karavot"),
        LessonExampleSeed("I have two chairs.", "have", "chairs", "Menda ikkita stul bor.", "ikkita stul"),
        LessonExampleSeed("There is a table in my room.", "There is", "table", "Xonamda bitta stol bor.", "bitta stol"),
        LessonExampleSeed("There are two windows in my room.", "There are", "windows", "Xonamda ikkita deraza bor.", "ikkita deraza"),
        LessonExampleSeed("There is a door in my room.", "There is", "door", "Xonamda bitta eshik bor.", "bitta eshik"),
        LessonExampleSeed("Here is my bed.", "Here is", "bed", "Mana mening karavotim.", "mening karavotim"),
        LessonExampleSeed("Here are my books.", "Here are", "books", "Mana mening kitoblarim.", "kitoblarim"),
        LessonExampleSeed("I see a bed.", "see", "bed", "Men karavotni ko‘ryapman.", "ko‘ryapman"),
        LessonExampleSeed("I see two chairs.", "see", "chairs", "Men ikkita stulni ko‘ryapman.", "stulni"),
        LessonExampleSeed("I see a window.", "see", "window", "Men derazani ko‘ryapman.", "derazani"),
        LessonExampleSeed("Give me a book.", "Give me", "book", "Menga kitob ber.", "menga ber"),
        LessonExampleSeed("Give me two pens.", "Give me", "pens", "Menga ikkita ruchka ber.", "menga ber"),
        LessonExampleSeed("Show me your room.", "Show", "room", "Menga xonangni ko‘rsat.", "ko‘rsat"),
        LessonExampleSeed("Show me a book.", "Show", "book", "Menga kitobni ko‘rsat.", "kitobni")
    )

    val DARS_19_WRAP_UP_UZBEK = """
        Bugun biz xonamiz haqida gapirishni o‘rgandik.

        My room — mening xonam. In my room — mening xonamda.
        In — “ichida”. Oldinda turadi. Tamom.

        Xonadagi narsalar: bed, table, chair, door, window.
        Va uchta yangi fe’l: see (ko‘rmoq), give me (menga ber), show (ko‘rsatmoq).

        Va o‘tgan darslardagi hamma narsa ishlatildi:
        I have a bed. There is a table in my room. There are two chairs in my room. Here is my room.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun xonangizdagi 3 ta narsani inglizcha ayting: “There is a bed in my room. There are two chairs in my room. I see a window.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_20_EXPLANATION_UZBEK = """
        Ingliz tilida “bo‘lmoq” fe’li bor. U to be deyiladi.
        Bu — eng muhim fe’l. Har kuni ishlatasiz.

        Endi o‘zbek tili bilan solishtiring.
        O‘zbek tilida: “Men o‘quvchiman”. “-man” — so‘z oxirida turadi.

        Ingliz tilida bunday emas. Ingliz tilida “am” — alohida so‘z.
        “I am a student” — men + bo‘laman + o‘quvchi.

        Bugun biz ikkita gapni o‘rganamiz.
        Birinchisi: I am a boy. — Men o‘g‘il bolaman.
        Ikkinchisi: You are a girl. — Sen qizsan.

        Ko‘rdingizmi? I bilan — am. You bilan — are.
        Boshqa hech narsa kerak emas. Tamom.
        He, she, it — keyingi darsda. Hozir shu ikkitasi yetarli.
    """.trimIndent()

    val DARS_20_MISOLLAR = listOf(
        LessonExampleSeed("I am a boy.", "am", "boy", "Men o‘g‘il bolaman.", "o‘g‘il bola"),
        LessonExampleSeed("I am a student.", "am", "student", "Men o‘quvchiman.", "o‘quvchi"),
        LessonExampleSeed("I am a tiger?", "am", "tiger", "Men yo‘lbarsman? (yo‘q, bu hazil!)", "yo‘lbars"),
        LessonExampleSeed("I am a cat?", "am", "cat", "Men mushukman? (yo‘q!)", "mushuk"),
        LessonExampleSeed("You are a boy.", "are", "boy", "Sen o‘g‘il bolasan.", "o‘g‘il bola"),
        LessonExampleSeed("You are a girl.", "are", "girl", "Sen qizsan.", "qiz"),
        LessonExampleSeed("You are a student.", "are", "student", "Sen o‘quvchisan.", "o‘quvchi")
    )

    val DARS_20_WRAP_UP_UZBEK = """
        Bugun biz “bo‘lmoq” fe’lini o‘rgandik.

        O‘zbek tilida “-man” — so‘z oxirida.
        Ingliz tilida “am / are” — alohida so‘z.
        • I am — men ...man.
        • You are — sen ...san.

        I am a boy. — Men o‘g‘il bolaman.
        You are a girl. — Sen qizsan.

        Va bitta narsa → oldiga a / an.
        He, she, it — keyingi darsda.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 2 ta gap tuzing: “I am a ___. You are a ___.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_21_EXPLANATION_UZBEK = """
        O‘tgan darsda I am va You are ni o‘rgandik.
        Bugun — u haqida gapiramiz.

        O‘zbek tilida biz “u” deymiz. Hammaga — “u”.
        O‘g‘il bolaga ham “u”, qizga ham “u”, mushukka ham “u”.

        Ingliz tilida bunday emas. Ingliz tilida uchta so‘z bor:
        • He — u (o‘g‘il bola, erkak).
        • She — u (qiz, ayol).
        • It — u (narsa yoki hayvon).

        Ya’ni kim haqida gapirsangiz — he, she yoki it ni tanlaysiz.
        Bu — o‘zbek tilidan farqi. O‘zbek tilida bitta “u”, ingliz tilida uchta.

        Va to be fe’li ular bilan is bo‘ladi.
        He is, she is, it is — hammasi is bilan. Tamom.
    """.trimIndent()

    val DARS_21_MISOLLAR = listOf(
        LessonExampleSeed("He is a boy.", "is", "boy", "U o‘g‘il bola.", "o‘g‘il bola"),
        LessonExampleSeed("He is a student.", "is", "student", "U o‘quvchi.", "o‘quvchi"),
        LessonExampleSeed("She is a girl.", "is", "girl", "U qiz.", "qiz"),
        LessonExampleSeed("She is a student.", "is", "student", "U o‘quvchi.", "o‘quvchi"),
        LessonExampleSeed("It is a tiger.", "is", "tiger", "U yo‘lbars (hayvon).", "yo‘lbars"),
        LessonExampleSeed("It is a cat.", "is", "cat", "U mushuk.", "mushuk"),
        LessonExampleSeed("It is a book.", "is", "book", "U kitob.", "kitob"),
        LessonExampleSeed("It is a table.", "is", "table", "U stol.", "stol")
    )

    val DARS_21_WRAP_UP_UZBEK = """
        Bugun biz “u” haqida gapirishni o‘rgandik.

        O‘zbek tilida bitta “u” — hammaga.
        Ingliz tilida uchta: he (o‘g‘il), she (qiz), it (narsa/hayvon).

        Va to be ular bilan is bo‘ladi:
        • He is a boy. — U o‘g‘il bola.
        • She is a girl. — U qiz.
        • It is a tiger. — U yo‘lbars.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 3 ta gap tuzing: “He is a ___. She is a ___. It is a ___.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_22_EXPLANATION_UZBEK = """
        O‘tgan darslarda darak shaklini o‘rgandik:
        I am a boy. You are a girl. He is a student.

        Bugun — savol. “Men bolamanmi?” deb so‘raymiz.

        O‘zbek tilida savol uchun so‘z oxiriga “-mi” qo‘shiladi:
        “Men o‘quvchimanmi?” — “-mi” — savol belgisi.

        Ingliz tilida bunday emas. Ingliz tilida savol uchun fe’l oldinga chiqadi:
        Am I a student? — Men o‘quvchimanmi?

        Ya’ni am birinchi o‘rinda turadi. Keyin I, keyin a student.
        O‘zbek tilida “-mi” oxirida. Ingliz tilida fe’l boshida. Tamom.

        Mana savol shakllari:
        • Am I ...? — men ...manmi?
        • Are you ...? — sen ...sanmi?
        • Is he ...? — u ...dirmi? (o‘g‘il)
        • Is she ...? — u ...dirmi? (qiz)
        • Is it ...? — u ...dirmi? (narsa)

        Javob: Yes, I am. / No, I am not.
        Xullas: fe’l oldinga — savol. Tamom.
    """.trimIndent()

    val DARS_22_MISOLLAR = listOf(
        LessonExampleSeed("Am I a boy?", "Am", "boy", "Men o‘g‘il bolamanmi?", "o‘g‘il bola"),
        LessonExampleSeed("Am I a student?", "Am", "student", "Men o‘quvchimanmi?", "o‘quvchi"),
        LessonExampleSeed("Are you a girl?", "Are", "girl", "Sen qizsanmi?", "qiz"),
        LessonExampleSeed("Are you a student?", "Are", "student", "Sen o‘quvchisanmi?", "o‘quvchi"),
        LessonExampleSeed("Is he a boy?", "Is", "boy", "U o‘g‘il bolami?", "o‘g‘il bola"),
        LessonExampleSeed("Is she a girl?", "Is", "girl", "U qizmi?", "qiz"),
        LessonExampleSeed("Is it a tiger?", "Is", "tiger", "U yo‘lbarsmi?", "yo‘lbars"),
        LessonExampleSeed("Yes, I am.", "Yes", "am", "Ha, men ...man.", "ha"),
        LessonExampleSeed("No, I am not.", "No", "not", "Yo‘q, men ... emasman.", "yo‘q")
    )

    val DARS_22_WRAP_UP_UZBEK = """
        Bugun biz savol berishni o‘rgandik.

        O‘zbek tilida “-mi” — oxirida.
        Ingliz tilida fe’l oldinda: Am I? Are you? Is he?

        • Am I a boy? — Men o‘g‘il bolamanmi?
        • Are you a student? — Sen o‘quvchisanmi?
        • Is she a girl? — U qizmi?

        Javob: Yes, I am. / No, I am not.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 2 ta savol bering: “Am I a ___? Are you a ___?”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_23_EXPLANATION_UZBEK = """
        O‘tgan darsda savol ni o‘rgandik: Am I a boy?
        Bugun — inkor. “Men bolamasman” deb aytamiz.

        O‘zbek tilida inkor uchun “emas” so‘zi ishlatiladi:
        “Men o‘quvchi emasman”. “Emas” — inkor so‘zi.

        Ingliz tilida ham xuddi shunday. Faqat “emas” o‘rniga “not” deymiz.
        Va not — fe’ldan keyin turadi:
        I am not a student. — Men o‘quvchi emasman.

        Mana inkor shakllari:
        • I am not — men ... emasman.
        • You are not — sen ... emassan.
        • He is not — u ... emas (o‘g‘il).
        • She is not — u ... emas (qiz).
        • It is not — u ... emas (narsa).

        Ya’ni: am / are / is + not. Tamom.
        Xullas: fe’l + not — inkor. Tamom.
    """.trimIndent()

    val DARS_23_MISOLLAR = listOf(
        LessonExampleSeed("I am not a boy.", "not", "boy", "Men o‘g‘il bola emasman.", "emas"),
        LessonExampleSeed("I am not a student.", "not", "student", "Men o‘quvchi emasman.", "emas"),
        LessonExampleSeed("You are not a girl.", "not", "girl", "Sen qiz emassan.", "emas"),
        LessonExampleSeed("You are not a student.", "not", "student", "Sen o‘quvchi emassan.", "emas"),
        LessonExampleSeed("He is not a boy.", "not", "boy", "U o‘g‘il bola emas.", "emas"),
        LessonExampleSeed("She is not a girl.", "not", "girl", "U qiz emas.", "emas"),
        LessonExampleSeed("It is not a tiger.", "not", "tiger", "U yo‘lbars emas.", "emas"),
        LessonExampleSeed("It is not a cat.", "not", "cat", "U mushuk emas.", "emas")
    )

    val DARS_23_WRAP_UP_UZBEK = """
        Bugun biz inkor qilishni o‘rgandik.

        O‘zbek tilida “emas”.
        Ingliz tilida “not” — fe’ldan keyin.

        • I am not a boy. — Men o‘g‘il bola emasman.
        • You are not a student. — Sen o‘quvchi emassan.
        • He is not a boy. — U o‘g‘il bola emas.
        • She is not a girl. — U qiz emas.
        • It is not a tiger. — U yo‘lbars emas.

        Endi uchta shaklni bilasiz:
        • Darak: I am a boy.
        • Savol: Am I a boy?
        • Inkor: I am not a boy.

        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun 3 ta inkor gap tuzing: “I am not a ___. He is not a ___. It is not a ___.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    val DARS_24_EXPLANATION_UZBEK = """
        Bugun yangi narsa yo‘q.
        Bugun hamma o‘tgan narsani birga ko‘ramiz.

        Siz juda ko‘p narsani o‘rgandingiz.
        Endi hammasini bitta joyda eslaymiz.
        Qo‘rqmang — hammasi oddiy. Tamom.
    """.trimIndent()

    val DARS_24_MISOLLAR = listOf(
        LessonExampleSeed("Hello! Hi!", "Hello", "Hi", "Salom!", "salom"),
        LessonExampleSeed("Welcome to TalkMe!", "Welcome", "TalkMe", "TalkMe’ga xush kelibsiz!", "xush kelibsiz"),
        LessonExampleSeed("What’s your name? — My name is Aziz.", "name", "Aziz", "Ismingiz nima? — Mening ismim Aziz.", "ism"),
        LessonExampleSeed("a book — an apple", "book", "apple", "bitta kitob — bitta olma", "kitob"),
        LessonExampleSeed("two books — three apples", "books", "apples", "ikkita kitob — uchta olma", "kitob"),
        LessonExampleSeed("this book — that cup", "this", "that", "bu kitob — o‘sha piyola", "bu"),
        LessonExampleSeed("these books — those cups", "these", "those", "bu kitoblar — o‘sha piyolalar", "bular"),
        LessonExampleSeed("I have a book.", "have", "book", "Menda kitob bor.", "menda bor"),
        LessonExampleSeed("Here is a boy. — There is a cat.", "Here", "There", "Mana bitta bola. — Bitta mushuk bor.", "mana"),
        LessonExampleSeed("one, two, three... twenty", "one", "twenty", "1, 2, 3... 20", "sonlar"),
        LessonExampleSeed("the first book — the third tiger", "first", "third", "birinchi kitob — uchinchi yo‘lbars", "tartib"),
        LessonExampleSeed("I am a boy. — You are a girl.", "am", "are", "Men o‘g‘il bolaman. — Sen qizsan.", "bo‘lmoq"),
        LessonExampleSeed("He is a student. — She is a girl.", "is", "is", "U o‘quvchi. — U qiz.", "u"),
        LessonExampleSeed("Am I a boy? — Are you a student?", "Am", "Are", "Men bolamanmi? — Sen o‘quvchisanmi?", "savol"),
        LessonExampleSeed("I am not a boy. — He is not a student.", "not", "not", "Men bola emasman. — U o‘quvchi emas.", "inkor"),
        LessonExampleSeed("There is a bed in my room.", "room", "bed", "Xonamda karavot bor.", "xona"),
        LessonExampleSeed("I see a table.", "see", "table", "Men stolni ko‘ryapman.", "ko‘rmoq"),
        LessonExampleSeed("Give me a book.", "Give me", "book", "Menga kitob ber.", "bermoq"),
        LessonExampleSeed("Show me your room.", "Show", "room", "Menga xonangni ko‘rsat.", "ko‘rsatmoq")
    )

    val DARS_24_WRAP_UP_UZBEK = """
        Bugun biz hamma o‘tgan narsani birga ko‘rdik.
        Mana siz nimalarni bilasiz:
        • Salomlashish — Hello, Hi, Good bye.
        • Tanishish — What’s your name? My name is ______.
        • a / an — bitta narsa.
        • Son + -s — ko‘p narsa.
        • This / that — bu / o‘sha.
        • These / those — bular / o‘shalar.
        • I have — menda bor.
        • Here / there — mana / u yerda.
        • Sonlar — one...twenty.
        • Tartib sonlar — first, second, third.
        • To be — I am, you are, he is.
        • Savol — Am I? Are you? Is he?
        • Inkor — I am not, he is not.
        • Xona — in my room, see, give me, show.

        Bu — juda ko‘p. Siz katta yo‘l bosib o‘tdingiz.
        Xato qilsangiz ham — hechqisi yo‘q.
        Bugun o‘zingizga ayting: “I am a student. I have a book. There is a table in my room.”
        Keyingi darslarda ko‘rishguncha, do‘stim! 👋
    """.trimIndent()

    fun buildTopics(): List<TopicEntity> = CurriculumLevel.entries.map { level ->
        TopicEntity(
            id = level.topicId,
            orderIndex = level.ordinal + 1,
            titleEnglish = level.name,
            titleUzbek = level.name,
            descriptionUzbek = if (level == CurriculumLevel.A0) {
                "DARS 1–9: Kirish, a/an, and, this/that, these/those, I have • DARS 10–24: Sonlar, Here/There, My room, To be, Hammasi birga (Wrap up)"
            } else {
                ""
            },
            isUnlocked = level == CurriculumLevel.A0
        )
    }

    fun buildSections(): List<LessonSectionEntity> = listOf(
        LessonSectionEntity(
            id = DARS_1_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 1,
            titleEnglish = "Welcome to TalkMe",
            titleUzbek = "1-Dars: Welcome to TalkMe",
            objectiveUzbek = "TalkMe ilovasi bilan tanishish va ingliz tilida ilk qadamlarni tashlash.",
            explanationUzbek = DARS_1_TALKME_TTS_TEXT,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Siz TalkMe bilan ilk darsni muvaffaqiyatli yakunladingiz. " +
                "Uyga vazifa: 'Welcome to TalkMe!' va 'Every mistake is part of learning.' iboralarini ovoz chiqarib takrorlang. " +
                "TalkMe yakuniy: Har bir xato — bu o‘rganishning bir qismi. Biz har bir darsda siz bilan birgamiz!",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_2_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 2,
            titleEnglish = "Lera and talkme",
            titleUzbek = "2-Dars: Lera and talkme",
            objectiveUzbek = "9 ta oddiy iborani TalkMe va Lera suhbati orqali o‘rganish va qo‘llash.",
            explanationUzbek = DARS_2_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Bugun Lera bilan birinchi suhbatni va 9 ta oddiy iborani o‘rgandingiz. " +
                "Uyga vazifa: Hello, Hi, Sorry, What’s your name?, My name is, Thank you, Good bye iboralarini 3 marta takrorlang. " +
                "TalkMe yakuniy: Xato qilsangiz, qo‘rqmang — Sorry deb davom eting!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_3_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 3,
            titleEnglish = "A cup, an apple",
            titleUzbek = "3-Dars: A cup, an apple",
            objectiveUzbek = "Bitta narsa oldidan a (undosh) va an (unli: a, e, i, o, u) artikllarini to‘g‘ri tanlash.",
            explanationUzbek = DARS_3_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Bitta narsa oldidan unli (a, e, i, o, u) bo‘lsa an, undosh bo‘lsa a ishlatishni o‘rgandingiz. " +
                "Uyga vazifa: Uyingizdagi 3 ta narsani a yoki an bilan ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_4_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 4,
            titleEnglish = "A book and a cup",
            titleUzbek = "4-Dars: A book and a cup",
            objectiveUzbek = "Ikkita narsani 'and' (va) bilan bog‘lash va har bir ot oldida a yoki an artiklini saqlash.",
            explanationUzbek = DARS_4_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Endi ikkita narsani 'and' bilan inglizcha ayta olasiz. " +
                "Uyga vazifa: Uyingizdagi 2 ta narsani 'a ___ and a ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_5_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 5,
            titleEnglish = "This and that — a book, a cup",
            titleUzbek = "5-Dars: This and that",
            objectiveUzbek = "Yaqindagi (this = bu) va uzoqroqdagi (that = o‘sha) bitta narsani a/an qo‘ymasdan ko‘rsatish.",
            explanationUzbek = DARS_5_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Endi yaqindagi narsaga 'this', uzoqroqdagi narsaga 'that' deb a/an qo‘ymasdan ayta olasiz. " +
                "Uyga vazifa: Yoningizdagi va uzoqdagi narsalarga 'this ___' va 'that ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_6_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 6,
            titleEnglish = "These and those — books, cups",
            titleUzbek = "6-Dars: These and those",
            objectiveUzbek = "Yaqindagi (these = bular) va uzoqroqdagi (those = o‘shalar) ko‘p narsalarni -s qo‘shimchasi bilan aytish.",
            explanationUzbek = DARS_6_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Bitta (this/that) va ko‘p (these/those + -s) narsalarni to‘liq farqlay olasiz. " +
                "Uyga vazifa: Ko‘p narsalarga 'these ___s' va 'those ___s' deb ayting. " +
                "TalkMe yakuniy: Keyingi darsda 'I have' bilan tanishamiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_7_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 7,
            titleEnglish = "I have a book",
            titleUzbek = "7-Dars: I have a book — Darak shakli",
            objectiveUzbek = "'have' (bor bo‘lmoq) va 'I have' (menda bor) darak shaklini a/an bilan qo‘llash.",
            explanationUzbek = DARS_7_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! 'Have' — bor bo‘lmoq, 'I have' — menda bor. " +
                "Uyga vazifa: Bugun 3 ta narsani 'I have a ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_8_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 8,
            titleEnglish = "Have I got a book?",
            titleUzbek = "8-Dars: Have I got a book? — Savol shakli",
            objectiveUzbek = "'Have I got + a/an + ot?' savolini tuzish va 'Yes, I have.' / 'No, I have not.' deb javob berish.",
            explanationUzbek = DARS_8_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Endi 'Have I got a ___?' savolini bera olasiz. " +
                "Uyga vazifa: Bugun 3 ta savol bering: 'Have I got a ___?'. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_9_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 9,
            titleEnglish = "I have not got a book",
            titleUzbek = "9-Dars: I have not got a book — Inkor shakli",
            objectiveUzbek = "'I have not got + a/an + ot' (menda yo‘q) inkor shaklini tuzish hamda darak, savol, inkorni farqlash.",
            explanationUzbek = DARS_9_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Siz 'I have' ning darak, savol va inkor shakllarini o‘rgandingiz. " +
                "Uyga vazifa: Bugun 3 ta narsa haqida 'I have not got a ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_10_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 10,
            titleEnglish = "One, two, three",
            titleUzbek = "10-Dars: One, two, three — 1 dan 10 gacha",
            objectiveUzbek = "1 dan 10 gacha sonlarni yodlash va ko‘p narsalar oxiriga -s qo‘shib sanash.",
            explanationUzbek = DARS_10_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Bugun 1 dan 10 gacha sanashni va ko‘p narsaga -s qo‘shishni o‘rgandingiz. " +
                "Uyga vazifa: Bugun uyingizdagi 3 ta narsani sanang: 'two books, five cups, three pens.' " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_11_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 11,
            titleEnglish = "I have three books",
            titleUzbek = "11-Dars: I have three books",
            objectiveUzbek = "I have va 1–10 sonlarni birlashtirish: bitta narsa (a/an/one + -s yo‘q) va ko‘p narsa (son + -s).",
            explanationUzbek = DARS_11_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Endi sonlar bilan 'I have three books' va 'I have one book' gaplarini tuza olasiz. " +
                "Uyga vazifa: Uyingizdagi 3 ta narsani sanab ayting: 'I have three books. I have two cups. I have one pen.' " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_12_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 12,
            titleEnglish = "I have a book, but I have no cups",
            titleUzbek = "12-Dars: I have a book, but I have no cups",
            objectiveUzbek = "'but' (lekin) va 'no' (yo‘q) so‘zlari bilan 'I have ___, but I have no ___' gaplarini tuzish.",
            explanationUzbek = DARS_12_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Bugun 'but' (lekin) va 'no' (yo‘q) so‘zlari bilan qarama-qarshi gaplar tuzishni o‘rgandingiz. " +
                "Uyga vazifa: Bugun 2 ta gap tuzing: 'I have ___, but I have no ___.' " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_13_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 13,
            titleEnglish = "Counting the Tigers",
            titleUzbek = "13-Dars: Counting the Tigers — Sanoq sonlar",
            objectiveUzbek = "Sanoq sonlar (“nechta?”) bilan hayvonlarni sanash: one tiger, two tigers .. ten tigers (-s ko‘plik).",
            explanationUzbek = DARS_13_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Bugun yo‘lbarslarni sanoq sonlar bilan sanashni (one tiger .. ten tigers) o‘rgandingiz. " +
                "Uyga vazifa: Uyingizdagi o‘yinchoqlarni sanang: 'one tiger, three tigers, five tigers.' " +
                "TalkMe yakuniy: Keyingi darsda tartib sonlar bilan tanishamiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_14_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 14,
            titleEnglish = "The first tiger",
            titleUzbek = "14-Dars: The first tiger — Tartib sonlar",
            objectiveUzbek = "Tartib sonlar (“nechanchi?”) bilan o‘rinni aytish: the first tiger .. the tenth tiger.",
            explanationUzbek = DARS_14_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Sanoq (nechta? → three tigers) va tartib (nechanchi? → the third tiger) sonlarni farqlay olasiz. " +
                "Uyga vazifa: 3 ta yo‘lbarsni tartib bilan ayting: 'the first tiger, the second tiger, the third tiger.' " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_15_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 15,
            titleEnglish = "Here is a boy",
            titleUzbek = "15-Dars: Here is a boy",
            objectiveUzbek = "'Here' (mana / mana bu) va 'Here is + a/an + ot' shaklini bitta yaqin narsa uchun qo‘llash.",
            explanationUzbek = DARS_15_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Bugun yaqindagi bitta narsani ko‘rsatishni ('Here is a/an ___') o‘rgandingiz. " +
                "Uyga vazifa: Yoningizdagi 3 ta narsani ko‘rsatib: 'Here is a ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darsda ko‘p narsalar uchun 'Here are' ni ko‘ramiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_16_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 16,
            titleEnglish = "Here are two boys",
            titleUzbek = "16-Dars: Here are two boys",
            objectiveUzbek = "Yaqindagi ko‘p narsalar uchun 'Here are + son + ot(-s)' shaklini ishlatish va 'Here is' bilan farqlash.",
            explanationUzbek = DARS_16_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Bitta (Here is) va ko‘p (Here are + -s) narsalarni ko‘rsatishni o‘rgandingiz. " +
                "Uyga vazifa: Yoningizdagi ko‘p narsalarni ko‘rsatib: 'Here are ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darsda 'There is' bilan tanishamiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_17_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 17,
            titleEnglish = "There is a boy",
            titleUzbek = "17-Dars: There is a boy",
            objectiveUzbek = "'There' (u yerda / bor) va 'There is + a/an + ot' shaklini bitta narsa borligini aytishda qo‘llash.",
            explanationUzbek = DARS_17_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! 'Here is' (mana, yaqin) va 'There is' (u yerda bor) farqini o‘zlashtirdingiz. " +
                "Uyga vazifa: Atrofingizdagi 3 ta narsani ko‘rsatib: 'There is a ___' deb ayting. " +
                "TalkMe yakuniy: Keyingi darsda 'There are' ni ko‘ramiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_18_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 18,
            titleEnglish = "There are two boys",
            titleUzbek = "18-Dars: There are two boys",
            objectiveUzbek = "Ko‘p narsa borligini 'There are + son + ot(-s)' bilan aytish va 4 ta shaklni (Here/There is/are) umumlashtirish.",
            explanationUzbek = DARS_18_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Ajoyib, [USER_NAME]! Endi to‘rtta shaklni to‘liq bilasiz: Here is, Here are, There is, There are. " +
                "Uyga vazifa: Atrofingizdagi ko‘p narsalarga 'There are ___' deb 3 ta gap ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_19_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 19,
            titleEnglish = "My room",
            titleUzbek = "19-Dars: My room",
            objectiveUzbek = "Xona jihozlari (bed, table, chair, door, window), in my room, see, give me, show va there is/are ni xona sharoitida ishlatish.",
            explanationUzbek = DARS_19_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Ajoyib, [USER_NAME]! Bugun o‘z xonangiz haqida (My room, in my room, bed, table, see, give me, show) gapirishni o‘rgandingiz. " +
                "Uyga vazifa: Xonangizdagi narsalar haqida 3 ta gap ayting. " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_20_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 20,
            titleEnglish = "I am a boy",
            titleUzbek = "20-Dars: I am a boy",
            objectiveUzbek = "To be fe’lining darak shaklini (I am, You are) ishlatish va shaxsni tanishtirish.",
            explanationUzbek = DARS_20_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Bugun ‘I am’ va ‘You are’ yordamida o‘zingiz va do‘stingiz haqida aytishni o‘rgandingiz. " +
                "Uyga vazifa: 2 ta gap tuzing: ‘I am a ___. You am... yo‘q, You are a ___.’ " +
                "TalkMe yakuniy: Keyingi darsda 'He, She, It' bilan tanishamiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_21_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 21,
            titleEnglish = "He is a boy, she is a girl",
            titleUzbek = "21-Dars: He is a boy, she is a girl",
            objectiveUzbek = "He, she, it olmoshlarini va ularga mos ‘is’ fe’lini farqlab ishlatish.",
            explanationUzbek = DARS_21_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Ajoyib, [USER_NAME]! ‘He’ (o‘g‘il), ‘She’ (qiz) va ‘It’ (narsa/hayvon) uchun ‘is’ ishlatishni o‘rgandingiz. " +
                "Uyga vazifa: 3 ta gap tuzing: ‘He is a ___. She is a ___. It is a ___.’ " +
                "TalkMe yakuniy: Keyingi darsda savol shakliga o‘tamiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_22_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 22,
            titleEnglish = "Am I a boy?",
            titleUzbek = "22-Dars: Am I a boy?",
            objectiveUzbek = "To be fe’lini egadan oldinga chiqarib savol berish (Am I?, Are you?, Is he/she/it?) va qisqa javoblar.",
            explanationUzbek = DARS_22_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Barakalla, [USER_NAME]! Fe’lni oldinga chiqarib savol berishni (Am I?, Are you?, Is he?) o‘rgandingiz. " +
                "Uyga vazifa: 2 ta savol bering: ‘Am I a ___? Are you a ___?’ " +
                "TalkMe yakuniy: Keyingi darsda inkor shaklini ko‘ramiz!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_23_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 23,
            titleEnglish = "I am not a boy",
            titleUzbek = "23-Dars: I am not a boy",
            objectiveUzbek = "To be fe’lidan keyin ‘not’ qo‘shib inkor gaplar tuzish (I am not, You are not, He/She/It is not).",
            explanationUzbek = DARS_23_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Darak, savol va inkor shakllarini to‘liq o‘rgandingiz. " +
                "Uyga vazifa: 3 ta inkor gap tuzing: ‘I am not a ___. He is not a ___. It is not a ___.’ " +
                "TalkMe yakuniy: Keyingi darslarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_24_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 24,
            titleEnglish = "Hammasi birga — Wrap up",
            titleUzbek = "24-Dars: Hammasi birga — Wrap up",
            objectiveUzbek = "A0 bosqichi bo‘yicha barcha o‘tilgan mavzularni (salomlashish, a/an, this/that, I have, here/there, sonlar, to be, savol, inkor, xona) yakuniy takrorlash va mustahkamlash.",
            explanationUzbek = DARS_24_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = "XULOSA: Tabriklaymiz, [USER_NAME]! Siz A0 bosqichidagi barcha 24 ta darsni muvaffaqiyatli yakunladingiz. " +
                "Uyga vazifa: O‘zingizga ayting: ‘I am a student. I have a book. There is a table in my room.’ " +
                "TalkMe yakuniy: Keyingi bosqichlarda ko‘rishguncha, do‘stim!",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_25_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 25,
            titleEnglish = "The shop",
            titleUzbek = "25-Dars: The shop — 'The' haqida",
            objectiveUzbek = "Aniq narsalar uchun 'the' artiklini, noma'lum narsalar uchun 'a/an' artiklini to'g'ri qo'llash.",
            explanationUzbek = DARS_25_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_25_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_26_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 26,
            titleEnglish = "Will",
            titleUzbek = "26-Dars: Will — Kelasi zamon",
            objectiveUzbek = "Kelasi zamon belgisi 'will' yordamida darak, inkor va savol gaplar tuzish.",
            explanationUzbek = DARS_26_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_26_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_27_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 27,
            titleEnglish = "Can",
            titleUzbek = "27-Dars: Can — Imkoniyat",
            objectiveUzbek = "Jismoniy va aqliy imkoniyatni 'can' modal fe'li bilan ifodalash.",
            explanationUzbek = DARS_27_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_27_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_28_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 28,
            titleEnglish = "Must",
            titleUzbek = "28-Dars: Must — Majburiyat va taqiq",
            objectiveUzbek = "Qat'iy majburiyat (must) va taqiq (must not) shakllarini qo'llash.",
            explanationUzbek = DARS_28_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_28_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_29_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 29,
            titleEnglish = "May",
            titleUzbek = "29-Dars: May — Ruxsat va odob",
            objectiveUzbek = "Odobli ruxsat so'rash (May I...?) va ruxsat berish modellarini o'rganish.",
            explanationUzbek = DARS_29_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_29_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_30_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 30,
            titleEnglish = "Have to",
            titleUzbek = "30-Dars: Have to — Tashqi majburiyat",
            objectiveUzbek = "Tashqi vaziyat taqozosi bilan bajariladigan majburiyat (have to / has to) va erkinlik (do not have to).",
            explanationUzbek = DARS_30_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_30_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_31_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 31,
            titleEnglish = "Big — Adjectives",
            titleUzbek = "31-Dars: Big — Sifat (Oddiy daraja)",
            objectiveUzbek = "Narsaning belgisini (katta, kichik, shirin, issiq, sovuq) sifat bilan ifodalash.",
            explanationUzbek = DARS_31_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_31_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_32_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 32,
            titleEnglish = "Bigger — Comparative",
            titleUzbek = "32-Dars: Bigger — Qiyosiy daraja",
            objectiveUzbek = "Ikki narsani solishtirish (-er + than) va qiyoslash modellarini qo'llash.",
            explanationUzbek = DARS_32_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_32_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_33_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 33,
            titleEnglish = "The biggest — Superlative",
            titleUzbek = "33-Dars: The biggest — Orttirma daraja",
            objectiveUzbek = "Ko'p narsalar orasidan eng ajralib turuvchisini (the + -est) aniqlash va ifodalash.",
            explanationUzbek = DARS_33_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_33_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_34_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 34,
            titleEnglish = "I do my homework — Do",
            titleUzbek = "34-Dars: I do my homework — “Do” (Qilmoq)",
            objectiveUzbek = "Do fe'lining asosiy ma'nosi (qilmoq) va he/she/it da does shaklini o'rganish.",
            explanationUzbek = DARS_34_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_34_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_35_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 35,
            titleEnglish = "Do you like...? — Do in questions",
            titleUzbek = "35-Dars: Do you like...? — “Do” savolda",
            objectiveUzbek = "Do/Does yordamchi fe'li bilan umumiy so'roq gaplar tuzish va javob berish.",
            explanationUzbek = DARS_35_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_35_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_36_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 36,
            titleEnglish = "I do not like... — Do in negatives",
            titleUzbek = "36-Dars: I do not like... — “Do” inkorda",
            objectiveUzbek = "Do not (don't) va does not (doesn't) inkor modellarini to'g'ri qo'llash.",
            explanationUzbek = DARS_36_EXPLANATION_UZBEK,
            wrapUpSummaryUzbek = DARS_36_WRAP_UP_UZBEK,
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // Dars 1 vocabulary
        VocabularyEntity(
            id = "v_a0_01_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "Welcome",
            uzbekTranslation = "Xush kelibsiz",
            exampleEnglish = "Welcome to TalkMe!",
            exampleUzbek = "TalkMe ilovasiga xush kelibsiz!"
        ),
        VocabularyEntity(
            id = "v_a0_01_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "Learn",
            uzbekTranslation = "O‘rganmoq",
            exampleEnglish = "Here you learn English.",
            exampleUzbek = "Bu yerda siz ingliz tilini o‘rganasiz."
        ),
        VocabularyEntity(
            id = "v_a0_01_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "Mistake",
            uzbekTranslation = "Xato",
            exampleEnglish = "Every mistake is part of learning.",
            exampleUzbek = "Har bir xato — bu o‘rganishning bir qismi."
        ),
        // Dars 2 vocabulary (9 phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_02_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "Hello",
            uzbekTranslation = "Salom (rasmiy)",
            exampleEnglish = "Hello, Lera!",
            exampleUzbek = "Salom, Lera!"
        ),
        VocabularyEntity(
            id = "v_a0_02_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "Hi",
            uzbekTranslation = "Salom (do‘stona)",
            exampleEnglish = "Hi! Thank you.",
            exampleUzbek = "Salom! Rahmat."
        ),
        VocabularyEntity(
            id = "v_a0_02_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "Welcome to TalkMe!",
            uzbekTranslation = "TalkMe’ga xush kelibsiz!",
            exampleEnglish = "Hello, Lera! Welcome to TalkMe!",
            exampleUzbek = "Salom, Lera! TalkMe’ga xush kelibsiz!"
        ),
        VocabularyEntity(
            id = "v_a0_02_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "What’s your name?",
            uzbekTranslation = "Ismingiz nima?",
            exampleEnglish = "What’s your name?",
            exampleUzbek = "Ismingiz nima?"
        ),
        VocabularyEntity(
            id = "v_a0_02_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "My name is",
            uzbekTranslation = "Mening ismim ...",
            exampleEnglish = "My name is Lera.",
            exampleUzbek = "Mening ismim Lera."
        ),
        VocabularyEntity(
            id = "v_a0_02_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "Sorry",
            uzbekTranslation = "Kechirasiz / tushunmadim",
            exampleEnglish = "Sorry, what?",
            exampleUzbek = "Kechirasiz, nima dedingiz?"
        ),
        VocabularyEntity(
            id = "v_a0_02_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "Thank you",
            uzbekTranslation = "Rahmat",
            exampleEnglish = "Oh, thank you!",
            exampleUzbek = "Oh, rahmat!"
        ),
        VocabularyEntity(
            id = "v_a0_02_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "I love English",
            uzbekTranslation = "Men ingliz tilini yaxshi ko‘raman",
            exampleEnglish = "Thank you! I love English!",
            exampleUzbek = "Rahmat! Men ingliz tilini yaxshi ko‘raman!"
        ),
        VocabularyEntity(
            id = "v_a0_02_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "Good bye",
            uzbekTranslation = "Xayr",
            exampleEnglish = "Great, Lera! Good bye!",
            exampleUzbek = "Ajoyib, Lera! Xayr!"
        ),
        // Lesson 3 vocabulary (10 article + noun phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_03_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "a cup",
            uzbekTranslation = "bitta piyola (finjon)",
            exampleEnglish = "This is a cup.",
            exampleUzbek = "Bu bitta piyola."
        ),
        VocabularyEntity(
            id = "v_a0_03_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "a book",
            uzbekTranslation = "bitta kitob",
            exampleEnglish = "I have a book.",
            exampleUzbek = "Menda bitta kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_03_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "a cat",
            uzbekTranslation = "bitta mushuk",
            exampleEnglish = "It is a cat.",
            exampleUzbek = "Bu bitta mushuk."
        ),
        VocabularyEntity(
            id = "v_a0_03_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "a pen",
            uzbekTranslation = "bitta ruchka",
            exampleEnglish = "Give me a pen.",
            exampleUzbek = "Menga bitta ruchka bering."
        ),
        VocabularyEntity(
            id = "v_a0_03_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "a dog",
            uzbekTranslation = "bitta kuchuk",
            exampleEnglish = "I see a dog.",
            exampleUzbek = "Men bitta kuchukni ko‘ryapman."
        ),
        VocabularyEntity(
            id = "v_a0_03_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "an apple",
            uzbekTranslation = "bitta olma",
            exampleEnglish = "I eat an apple.",
            exampleUzbek = "Men bitta olma yeyapman."
        ),
        VocabularyEntity(
            id = "v_a0_03_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "an egg",
            uzbekTranslation = "bitta tuxum",
            exampleEnglish = "This is an egg.",
            exampleUzbek = "Bu bitta tuxum."
        ),
        VocabularyEntity(
            id = "v_a0_03_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "an orange",
            uzbekTranslation = "bitta apelsin",
            exampleEnglish = "I want an orange.",
            exampleUzbek = "Men bitta apelsin xohlayman."
        ),
        VocabularyEntity(
            id = "v_a0_03_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "an umbrella",
            uzbekTranslation = "bitta soyabon",
            exampleEnglish = "Take an umbrella.",
            exampleUzbek = "Bitta soyabon oling."
        ),
        VocabularyEntity(
            id = "v_a0_03_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "an elephant",
            uzbekTranslation = "bitta fil",
            exampleEnglish = "Look at an elephant.",
            exampleUzbek = "Bitta filga qarang."
        ),
        // Lesson 4 vocabulary (new word 'and' + 7 two-object phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_04_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "and",
            uzbekTranslation = "va",
            exampleEnglish = "a book and a cup",
            exampleUzbek = "kitob va piyola"
        ),
        VocabularyEntity(
            id = "v_a0_04_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "a book and a cup",
            uzbekTranslation = "kitob va piyola",
            exampleEnglish = "a book and a cup",
            exampleUzbek = "bitta kitob va bitta piyola"
        ),
        VocabularyEntity(
            id = "v_a0_04_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "a cat and a dog",
            uzbekTranslation = "mushuk va kuchuk",
            exampleEnglish = "a cat and a dog",
            exampleUzbek = "bitta mushuk va bitta kuchuk"
        ),
        VocabularyEntity(
            id = "v_a0_04_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "a pen and a book",
            uzbekTranslation = "ruchka va kitob",
            exampleEnglish = "a pen and a book",
            exampleUzbek = "bitta ruchka va bitta kitob"
        ),
        VocabularyEntity(
            id = "v_a0_04_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "an apple and an egg",
            uzbekTranslation = "olma va tuxum",
            exampleEnglish = "an apple and an egg",
            exampleUzbek = "bitta olma va bitta tuxum"
        ),
        VocabularyEntity(
            id = "v_a0_04_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "an orange and an umbrella",
            uzbekTranslation = "apelsin va soyabon",
            exampleEnglish = "an orange and an umbrella",
            exampleUzbek = "bitta apelsin va bitta soyabon"
        ),
        VocabularyEntity(
            id = "v_a0_04_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "an elephant and a cat",
            uzbekTranslation = "fil va mushuk",
            exampleEnglish = "an elephant and a cat",
            exampleUzbek = "bitta fil va bitta mushuk"
        ),
        VocabularyEntity(
            id = "v_a0_04_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "a cup and an apple",
            uzbekTranslation = "piyola va olma",
            exampleEnglish = "a cup and an apple",
            exampleUzbek = "bitta piyola va bitta olma"
        ),
        // Lesson 5 vocabulary (this, that + 6 singular demonstrative phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_05_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "this",
            uzbekTranslation = "bu (yaqin)",
            exampleEnglish = "this book",
            exampleUzbek = "bu kitob (yaqin)"
        ),
        VocabularyEntity(
            id = "v_a0_05_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "that",
            uzbekTranslation = "o‘sha (uzoqroq)",
            exampleEnglish = "that cup",
            exampleUzbek = "o‘sha piyola (uzoqroq)"
        ),
        VocabularyEntity(
            id = "v_a0_05_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "this book",
            uzbekTranslation = "bu kitob (yaqin)",
            exampleEnglish = "This book is near you.",
            exampleUzbek = "Bu kitob yoningizda."
        ),
        VocabularyEntity(
            id = "v_a0_05_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "this cup",
            uzbekTranslation = "bu piyola (yaqin)",
            exampleEnglish = "This cup is in your hand.",
            exampleUzbek = "Bu piyola qo‘lingizda."
        ),
        VocabularyEntity(
            id = "v_a0_05_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "this apple",
            uzbekTranslation = "bu olma (yaqin)",
            exampleEnglish = "This apple is fresh.",
            exampleUzbek = "Bu olma yangi."
        ),
        VocabularyEntity(
            id = "v_a0_05_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "that book",
            uzbekTranslation = "o‘sha kitob (uzoqroq)",
            exampleEnglish = "That book is on the table.",
            exampleUzbek = "O‘sha kitob narigi stolda."
        ),
        VocabularyEntity(
            id = "v_a0_05_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "that cup",
            uzbekTranslation = "o‘sha piyola (uzoqroq)",
            exampleEnglish = "That cup is over there.",
            exampleUzbek = "O‘sha piyola uzoqroqda."
        ),
        VocabularyEntity(
            id = "v_a0_05_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "that apple",
            uzbekTranslation = "o‘sha olma (uzoqroq)",
            exampleEnglish = "That apple is red.",
            exampleUzbek = "O‘sha olma qizil."
        ),
        // Lesson 6 vocabulary (these, those + 6 plural demonstrative phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_06_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "these",
            uzbekTranslation = "bular (yaqin ko‘p narsa)",
            exampleEnglish = "these books",
            exampleUzbek = "bu kitoblar (yaqin)"
        ),
        VocabularyEntity(
            id = "v_a0_06_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "those",
            uzbekTranslation = "o‘shalar (uzoqroq ko‘p narsa)",
            exampleEnglish = "those cups",
            exampleUzbek = "o‘sha piyolalar (uzoqroq)"
        ),
        VocabularyEntity(
            id = "v_a0_06_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "these books",
            uzbekTranslation = "bu kitoblar (yaqin)",
            exampleEnglish = "These books are here.",
            exampleUzbek = "Bu kitoblar shu yerda."
        ),
        VocabularyEntity(
            id = "v_a0_06_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "these cups",
            uzbekTranslation = "bu piyolalar (yaqin)",
            exampleEnglish = "These cups are clean.",
            exampleUzbek = "Bu piyolalar toza."
        ),
        VocabularyEntity(
            id = "v_a0_06_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "these apples",
            uzbekTranslation = "bu olmalar (yaqin)",
            exampleEnglish = "These apples are sweet.",
            exampleUzbek = "Bu olmalar shirin."
        ),
        VocabularyEntity(
            id = "v_a0_06_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "those books",
            uzbekTranslation = "o‘sha kitoblar (uzoqroq)",
            exampleEnglish = "Those books are over there.",
            exampleUzbek = "O‘sha kitoblar narigi tomonda."
        ),
        VocabularyEntity(
            id = "v_a0_06_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "those cups",
            uzbekTranslation = "o‘sha piyolalar (uzoqroq)",
            exampleEnglish = "Those cups are on that table.",
            exampleUzbek = "O‘sha piyolalar narigi stolda."
        ),
        VocabularyEntity(
            id = "v_a0_06_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "those apples",
            uzbekTranslation = "o‘sha olmalar (uzoqroq)",
            exampleEnglish = "Those apples are big.",
            exampleUzbek = "O‘sha olmalar katta."
        ),
        // Lesson 7 vocabulary (8 items, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_07_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "have",
            uzbekTranslation = "bor bo‘lmoq / ega bo‘lmoq",
            exampleEnglish = "I have a book.",
            exampleUzbek = "Menda kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have",
            uzbekTranslation = "menda bor",
            exampleEnglish = "I have a pen.",
            exampleUzbek = "Menda ruchka bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have a book.",
            uzbekTranslation = "Menda kitob bor.",
            exampleEnglish = "I have a book.",
            exampleUzbek = "Menda bitta kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have a cup.",
            uzbekTranslation = "Menda piyola bor.",
            exampleEnglish = "I have a cup.",
            exampleUzbek = "Menda bitta piyola bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have a pen.",
            uzbekTranslation = "Menda ruchka bor.",
            exampleEnglish = "I have a pen.",
            exampleUzbek = "Menda bitta ruchka bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have a cat.",
            uzbekTranslation = "Menda mushuk bor.",
            exampleEnglish = "I have a cat.",
            exampleUzbek = "Menda bitta mushuk bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have an apple.",
            uzbekTranslation = "Menda olma bor.",
            exampleEnglish = "I have an apple.",
            exampleUzbek = "Menda bitta olma bor."
        ),
        VocabularyEntity(
            id = "v_a0_07_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "I have an egg.",
            uzbekTranslation = "Menda tuxum bor.",
            exampleEnglish = "I have an egg.",
            exampleUzbek = "Menda bitta tuxum bor."
        ),
        // Lesson 8 vocabulary (7 items, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_08_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "Have I got",
            uzbekTranslation = "Menda bormi?",
            exampleEnglish = "Have I got a book?",
            exampleUzbek = "Menda kitob bormi?"
        ),
        VocabularyEntity(
            id = "v_a0_08_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "Yes, I have.",
            uzbekTranslation = "Ha, bor.",
            exampleEnglish = "Yes, I have.",
            exampleUzbek = "Ha, menda bor."
        ),
        VocabularyEntity(
            id = "v_a0_08_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "No, I have not.",
            uzbekTranslation = "Yo‘q, yo‘q.",
            exampleEnglish = "No, I have not.",
            exampleUzbek = "Yo‘q, menda yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_08_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "Have I got a book?",
            uzbekTranslation = "Menda kitob bormi?",
            exampleEnglish = "Have I got a book?",
            exampleUzbek = "Sumkamda kitob bormi?"
        ),
        VocabularyEntity(
            id = "v_a0_08_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "Have I got a pen?",
            uzbekTranslation = "Menda ruchka bormi?",
            exampleEnglish = "Have I got a pen?",
            exampleUzbek = "Sumkamda ruchka bormi?"
        ),
        VocabularyEntity(
            id = "v_a0_08_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "Have I got an apple?",
            uzbekTranslation = "Menda olma bormi?",
            exampleEnglish = "Have I got an apple?",
            exampleUzbek = "Menda bitta olma bormi?"
        ),
        VocabularyEntity(
            id = "v_a0_08_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "Have I got an egg?",
            uzbekTranslation = "Menda tuxum bormi?",
            exampleEnglish = "Have I got an egg?",
            exampleUzbek = "Nonushtaga tuxum bormi?"
        ),
        // Lesson 9 vocabulary (7 items, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_09_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I have not got",
            uzbekTranslation = "Menda yo‘q",
            exampleEnglish = "I have not got a book.",
            exampleUzbek = "Menda kitob yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_09_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I haven’t got",
            uzbekTranslation = "Menda yo‘q (qisqa shakl)",
            exampleEnglish = "I haven’t got a book.",
            exampleUzbek = "Menda kitob yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_09_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I have not got a book.",
            uzbekTranslation = "Menda kitob yo‘q.",
            exampleEnglish = "I have not got a book.",
            exampleUzbek = "Menda kitob yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_09_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I have not got a cup.",
            uzbekTranslation = "Menda piyola yo‘q.",
            exampleEnglish = "I have not got a cup.",
            exampleUzbek = "Menda piyola yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_09_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I have not got a pen.",
            uzbekTranslation = "Menda ruchka yo‘q.",
            exampleEnglish = "I have not got a pen.",
            exampleUzbek = "Menda ruchka yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_09_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I have not got an apple.",
            uzbekTranslation = "Menda olma yo‘q.",
            exampleEnglish = "I have not got an apple.",
            exampleUzbek = "Menda olma yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_09_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "I have not got an egg.",
            uzbekTranslation = "Menda tuxum yo‘q.",
            exampleEnglish = "I have not got an egg.",
            exampleUzbek = "Menda tuxum yo‘q."
        ),
        // Lesson 10 vocabulary (10 numbers, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_10_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "one",
            uzbekTranslation = "1 (bir / bitta)",
            exampleEnglish = "One book.",
            exampleUzbek = "Bitta kitob."
        ),
        VocabularyEntity(
            id = "v_a0_10_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "two",
            uzbekTranslation = "2 (ikki / ikkita)",
            exampleEnglish = "Two books, please.",
            exampleUzbek = "Ikkita kitob, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_10_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "three",
            uzbekTranslation = "3 (uch / uchta)",
            exampleEnglish = "Three cups.",
            exampleUzbek = "Uchta piyola."
        ),
        VocabularyEntity(
            id = "v_a0_10_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "four",
            uzbekTranslation = "4 (to‘rt / to‘rtta)",
            exampleEnglish = "Four eggs.",
            exampleUzbek = "To‘rtta tuxum."
        ),
        VocabularyEntity(
            id = "v_a0_10_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "five",
            uzbekTranslation = "5 (besh / beshta)",
            exampleEnglish = "Five pens, please.",
            exampleUzbek = "Beshta ruchka, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_10_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "six",
            uzbekTranslation = "6 (olti / oltita)",
            exampleEnglish = "Six oranges.",
            exampleUzbek = "Oltita apelsin."
        ),
        VocabularyEntity(
            id = "v_a0_10_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "seven",
            uzbekTranslation = "7 (yetti / yettita)",
            exampleEnglish = "Seven apples.",
            exampleUzbek = "Yettita olma."
        ),
        VocabularyEntity(
            id = "v_a0_10_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "eight",
            uzbekTranslation = "8 (sakkiz / sakkizta)",
            exampleEnglish = "Eight pens.",
            exampleUzbek = "Sakkizta ruchka."
        ),
        VocabularyEntity(
            id = "v_a0_10_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "nine",
            uzbekTranslation = "9 (to‘qqiz / to‘qqizta)",
            exampleEnglish = "Nine cats?",
            exampleUzbek = "To‘qqizta mushuk?"
        ),
        VocabularyEntity(
            id = "v_a0_10_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "ten",
            uzbekTranslation = "10 (o‘n / o‘nta)",
            exampleEnglish = "Ten apples, please.",
            exampleUzbek = "O‘nta olma, iltimos."
        ),
        // Lesson 11 vocabulary (7 I have + number phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_11_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have one pen.",
            uzbekTranslation = "Menda bitta ruchka bor",
            exampleEnglish = "I have one pen.",
            exampleUzbek = "O‘qituvchi: Nechta ruchkang bor? — Menda bitta ruchka bor."
        ),
        VocabularyEntity(
            id = "v_a0_11_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have one book.",
            uzbekTranslation = "Menda bitta kitob bor (one bilan -s yo‘q)",
            exampleEnglish = "I have one book.",
            exampleUzbek = "Menda bitta kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_11_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have two books.",
            uzbekTranslation = "Menda ikkita kitob bor",
            exampleEnglish = "I have two books.",
            exampleUzbek = "Menda ikkita kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_11_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have three cups.",
            uzbekTranslation = "Menda uchta piyola bor",
            exampleEnglish = "I have three cups.",
            exampleUzbek = "Menda uchta piyola bor."
        ),
        VocabularyEntity(
            id = "v_a0_11_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have three apples.",
            uzbekTranslation = "Menda uchta olma bor",
            exampleEnglish = "I have three apples.",
            exampleUzbek = "Menda uchta olma bor."
        ),
        VocabularyEntity(
            id = "v_a0_11_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have five pens.",
            uzbekTranslation = "Menda beshta ruchka bor",
            exampleEnglish = "I have five pens.",
            exampleUzbek = "Menda beshta ruchka bor."
        ),
        VocabularyEntity(
            id = "v_a0_11_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "I have ten apples.",
            uzbekTranslation = "Menda o‘nta olma bor",
            exampleEnglish = "I have ten apples.",
            exampleUzbek = "Menda o‘nta olma bor."
        ),
        // Lesson 12 vocabulary (but, no + 5 'I have no ...' phrases, all unique englishWord)
        VocabularyEntity(
            id = "v_a0_12_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "but",
            uzbekTranslation = "lekin",
            exampleEnglish = "I have a book, but I have no cups.",
            exampleUzbek = "Menda kitob bor, lekin piyola yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_12_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "no",
            uzbekTranslation = "yo‘q (I have no ...)",
            exampleEnglish = "I have no cups.",
            exampleUzbek = "Menda piyola yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_12_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "I have no cups.",
            uzbekTranslation = "Menda piyola yo‘q",
            exampleEnglish = "I have a book, but I have no cups.",
            exampleUzbek = "Menda kitob bor, lekin piyola yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_12_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "I have no books.",
            uzbekTranslation = "Menda kitob yo‘q",
            exampleEnglish = "I have two pens, but I have no books.",
            exampleUzbek = "Menda ikkita ruchka bor, lekin kitob yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_12_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "I have no eggs.",
            uzbekTranslation = "Menda tuxum yo‘q",
            exampleEnglish = "I have three apples, but I have no eggs.",
            exampleUzbek = "Menda uchta olma bor, lekin tuxum yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_12_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "I have no dogs.",
            uzbekTranslation = "Menda it yo‘q",
            exampleEnglish = "I have a cat, but I have no dogs.",
            exampleUzbek = "Menda mushuk bor, lekin it yo‘q."
        ),
        VocabularyEntity(
            id = "v_a0_12_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "I have no pens.",
            uzbekTranslation = "Menda ruchka yo‘q",
            exampleEnglish = "I have five books, but I have no pens.",
            exampleUzbek = "Menda beshta kitob bor, lekin ruchka yo‘q."
        ),
        // Lesson 13 vocabulary (Counting the Tigers — cardinal numbers 1..10 with tiger/tigers)
        VocabularyEntity(
            id = "v_a0_13_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "tiger",
            uzbekTranslation = "yo‘lbars",
            exampleEnglish = "I have a tiger.",
            exampleUzbek = "Menda bitta yo‘lbars bor."
        ),
        VocabularyEntity(
            id = "v_a0_13_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "one tiger",
            uzbekTranslation = "bitta yo‘lbars",
            exampleEnglish = "I have one tiger.",
            exampleUzbek = "Menda bitta yo‘lbars bor."
        ),
        VocabularyEntity(
            id = "v_a0_13_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "two tigers",
            uzbekTranslation = "ikkita yo‘lbars",
            exampleEnglish = "Two tigers.",
            exampleUzbek = "Ikkita yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "three tigers",
            uzbekTranslation = "uchta yo‘lbars",
            exampleEnglish = "I see three tigers.",
            exampleUzbek = "Men uchta yo‘lbarsni ko‘ryapman."
        ),
        VocabularyEntity(
            id = "v_a0_13_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "four tigers",
            uzbekTranslation = "to‘rtta yo‘lbars",
            exampleEnglish = "Four tigers.",
            exampleUzbek = "To‘rtta yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "five tigers",
            uzbekTranslation = "beshta yo‘lbars",
            exampleEnglish = "Five tigers.",
            exampleUzbek = "Beshta yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "six tigers",
            uzbekTranslation = "oltita yo‘lbars",
            exampleEnglish = "Six tigers.",
            exampleUzbek = "Oltita yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "seven tigers",
            uzbekTranslation = "yettita yo‘lbars",
            exampleEnglish = "Seven tigers.",
            exampleUzbek = "Yettita yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "eight tigers",
            uzbekTranslation = "sakkizta yo‘lbars",
            exampleEnglish = "Eight tigers.",
            exampleUzbek = "Sakkizta yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "nine tigers",
            uzbekTranslation = "to‘qqizta yo‘lbars",
            exampleEnglish = "Nine tigers.",
            exampleUzbek = "To‘qqizta yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_13_11",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "ten tigers",
            uzbekTranslation = "o‘nta yo‘lbars",
            exampleEnglish = "Ten tigers.",
            exampleUzbek = "O‘nta yo‘lbars."
        ),
        // Lesson 14 vocabulary (The first tiger — ordinal numbers 1st..10th)
        VocabularyEntity(
            id = "v_a0_14_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the first tiger",
            uzbekTranslation = "birinchi yo‘lbars",
            exampleEnglish = "The first tiger is big.",
            exampleUzbek = "Birinchi yo‘lbars katta."
        ),
        VocabularyEntity(
            id = "v_a0_14_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the second tiger",
            uzbekTranslation = "ikkinchi yo‘lbars",
            exampleEnglish = "The second tiger is small.",
            exampleUzbek = "Ikkinchi yo‘lbars kichkina."
        ),
        VocabularyEntity(
            id = "v_a0_14_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the third tiger",
            uzbekTranslation = "uchinchi yo‘lbars",
            exampleEnglish = "The third tiger.",
            exampleUzbek = "Uchinchi yo‘lbars (nechanchi?)."
        ),
        VocabularyEntity(
            id = "v_a0_14_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the fourth tiger",
            uzbekTranslation = "to‘rtinchi yo‘lbars",
            exampleEnglish = "The fourth tiger.",
            exampleUzbek = "To‘rtinchi yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_14_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the fifth tiger",
            uzbekTranslation = "beshinchi yo‘lbars",
            exampleEnglish = "The fifth tiger is white.",
            exampleUzbek = "Beshinchi yo‘lbars oq."
        ),
        VocabularyEntity(
            id = "v_a0_14_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the sixth tiger",
            uzbekTranslation = "oltinchi yo‘lbars",
            exampleEnglish = "The sixth tiger.",
            exampleUzbek = "Oltinchi yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_14_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the seventh tiger",
            uzbekTranslation = "yettinchi yo‘lbars",
            exampleEnglish = "The seventh tiger.",
            exampleUzbek = "Yettinchi yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_14_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the eighth tiger",
            uzbekTranslation = "sakkizinchi yo‘lbars",
            exampleEnglish = "The eighth tiger.",
            exampleUzbek = "Sakkizinchi yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_14_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the ninth tiger",
            uzbekTranslation = "to‘qqizinchi yo‘lbars",
            exampleEnglish = "The ninth tiger.",
            exampleUzbek = "To‘qqizinchi yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_14_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "the tenth tiger",
            uzbekTranslation = "o‘ninchi yo‘lbars",
            exampleEnglish = "The tenth tiger.",
            exampleUzbek = "O‘ninchi yo‘lbars."
        ),
        // Lesson 15 vocabulary (Here is a boy — 7 items)
        VocabularyEntity(
            id = "v_a0_15_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "Here is",
            uzbekTranslation = "Mana / mana bu (bitta narsa)",
            exampleEnglish = "Here is a boy.",
            exampleUzbek = "Mana bitta bola."
        ),
        VocabularyEntity(
            id = "v_a0_15_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "a boy",
            uzbekTranslation = "bitta bola",
            exampleEnglish = "Here is a boy!",
            exampleUzbek = "Mana bola!"
        ),
        VocabularyEntity(
            id = "v_a0_15_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "a girl",
            uzbekTranslation = "bitta qiz",
            exampleEnglish = "Here is a girl.",
            exampleUzbek = "Mana bitta qiz."
        ),
        VocabularyEntity(
            id = "v_a0_15_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "Here is a tiger.",
            uzbekTranslation = "Mana bitta yo‘lbars.",
            exampleEnglish = "Here is a tiger.",
            exampleUzbek = "Zoomda: Mana yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_15_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "Here is a cat.",
            uzbekTranslation = "Mana bitta mushuk.",
            exampleEnglish = "Here is a cat.",
            exampleUzbek = "Mana bitta mushuk."
        ),
        VocabularyEntity(
            id = "v_a0_15_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "Here is an apple.",
            uzbekTranslation = "Mana bitta olma.",
            exampleEnglish = "Here is an apple.",
            exampleUzbek = "Mana bitta olma."
        ),
        VocabularyEntity(
            id = "v_a0_15_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "Here is a book.",
            uzbekTranslation = "Mana bitta kitob.",
            exampleEnglish = "Here is a book.",
            exampleUzbek = "Mana kitob."
        ),
        // Lesson 16 vocabulary (Here are two boys — 7 items)
        VocabularyEntity(
            id = "v_a0_16_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "Here are",
            uzbekTranslation = "Mana / mana bular (ko‘p narsa)",
            exampleEnglish = "Here are two boys.",
            exampleUzbek = "Mana ikkita bola."
        ),
        VocabularyEntity(
            id = "v_a0_16_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "boys",
            uzbekTranslation = "bolalar",
            exampleEnglish = "Here are two boys!",
            exampleUzbek = "Mana ikkita bola!"
        ),
        VocabularyEntity(
            id = "v_a0_16_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "girls",
            uzbekTranslation = "qizlar",
            exampleEnglish = "Here are three girls.",
            exampleUzbek = "Mana uchta qiz."
        ),
        VocabularyEntity(
            id = "v_a0_16_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "Here are five tigers.",
            uzbekTranslation = "Mana beshta yo‘lbars.",
            exampleEnglish = "Here are five tigers.",
            exampleUzbek = "Zoomda: Mana beshta yo‘lbars."
        ),
        VocabularyEntity(
            id = "v_a0_16_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "Here are four books.",
            uzbekTranslation = "Mana to‘rtta kitob.",
            exampleEnglish = "Here are four books.",
            exampleUzbek = "Mana to‘rtta kitob."
        ),
        VocabularyEntity(
            id = "v_a0_16_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "Here are six apples.",
            uzbekTranslation = "Mana oltita olma.",
            exampleEnglish = "Here are six apples.",
            exampleUzbek = "Mana oltita olma."
        ),
        VocabularyEntity(
            id = "v_a0_16_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "Here are ten pens.",
            uzbekTranslation = "Mana o‘nta ruchka.",
            exampleEnglish = "Here are ten pens.",
            exampleUzbek = "Mana o‘nta ruchka."
        ),
        // Lesson 17 vocabulary (There is a boy — 7 items)
        VocabularyEntity(
            id = "v_a0_17_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "there",
            uzbekTranslation = "u yerda / bor",
            exampleEnglish = "There is a boy.",
            exampleUzbek = "Bitta bola bor."
        ),
        VocabularyEntity(
            id = "v_a0_17_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "There is",
            uzbekTranslation = "u yerda ... bor (bitta)",
            exampleEnglish = "There is a boy.",
            exampleUzbek = "Xonada bitta bola bor."
        ),
        VocabularyEntity(
            id = "v_a0_17_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "There is a girl.",
            uzbekTranslation = "Bitta qiz bor.",
            exampleEnglish = "There is a girl.",
            exampleUzbek = "U yerda bitta qiz bor."
        ),
        VocabularyEntity(
            id = "v_a0_17_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "There is a tiger.",
            uzbekTranslation = "Bitta yo‘lbars bor.",
            exampleEnglish = "There is a tiger!",
            exampleUzbek = "Bog‘da: Bitta yo‘lbars bor!"
        ),
        VocabularyEntity(
            id = "v_a0_17_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "There is a cat.",
            uzbekTranslation = "Bitta mushuk bor.",
            exampleEnglish = "There is a cat.",
            exampleUzbek = "Zoomda: Bitta mushuk bor."
        ),
        VocabularyEntity(
            id = "v_a0_17_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "There is an apple.",
            uzbekTranslation = "Bitta olma bor.",
            exampleEnglish = "There is an apple.",
            exampleUzbek = "Bitta olma bor."
        ),
        VocabularyEntity(
            id = "v_a0_17_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "There is a book.",
            uzbekTranslation = "Bitta kitob bor.",
            exampleEnglish = "There is a book.",
            exampleUzbek = "Bitta kitob bor."
        ),
        // Lesson 18 vocabulary (There are two boys — 7 items)
        VocabularyEntity(
            id = "v_a0_18_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are",
            uzbekTranslation = "u yerda ... bor (ko‘p)",
            exampleEnglish = "There are two boys.",
            exampleUzbek = "Ikkita bola bor."
        ),
        VocabularyEntity(
            id = "v_a0_18_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are three girls.",
            uzbekTranslation = "Uchta qiz bor.",
            exampleEnglish = "There are three girls.",
            exampleUzbek = "U yerda uchta qiz bor."
        ),
        VocabularyEntity(
            id = "v_a0_18_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are five tigers.",
            uzbekTranslation = "Beshta yo‘lbars bor.",
            exampleEnglish = "There are five tigers!",
            exampleUzbek = "Bog‘da: Beshta yo‘lbars bor!"
        ),
        VocabularyEntity(
            id = "v_a0_18_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are four books.",
            uzbekTranslation = "To‘rtta kitob bor.",
            exampleEnglish = "There are four books.",
            exampleUzbek = "To‘rtta kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_18_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are six apples.",
            uzbekTranslation = "Oltita olma bor.",
            exampleEnglish = "There are six apples.",
            exampleUzbek = "Oltita olma bor."
        ),
        VocabularyEntity(
            id = "v_a0_18_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are ten pens.",
            uzbekTranslation = "O‘nta ruchka bor.",
            exampleEnglish = "There are ten pens.",
            exampleUzbek = "O‘nta ruchka bor."
        ),
        VocabularyEntity(
            id = "v_a0_18_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "There are three cats.",
            uzbekTranslation = "Uchta mushuk bor.",
            exampleEnglish = "There are three cats.",
            exampleUzbek = "Zoomda: Uchta mushuk bor."
        ),
        // Lesson 19 vocabulary (My room — 7 items)
        VocabularyEntity(
            id = "v_a0_19_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "room",
            uzbekTranslation = "xona",
            exampleEnglish = "My room.",
            exampleUzbek = "Mening xonam."
        ),
        VocabularyEntity(
            id = "v_a0_19_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "bed",
            uzbekTranslation = "karavot",
            exampleEnglish = "I have a bed.",
            exampleUzbek = "Menda karavot bor."
        ),
        VocabularyEntity(
            id = "v_a0_19_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "table",
            uzbekTranslation = "stol",
            exampleEnglish = "There is a table in my room.",
            exampleUzbek = "Xonamda bitta stol bor."
        ),
        VocabularyEntity(
            id = "v_a0_19_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "chair",
            uzbekTranslation = "stul",
            exampleEnglish = "I have two chairs.",
            exampleUzbek = "Menda ikkita stul bor."
        ),
        VocabularyEntity(
            id = "v_a0_19_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "door",
            uzbekTranslation = "eshik",
            exampleEnglish = "There is a door in my room.",
            exampleUzbek = "Xonamda bitta eshik bor."
        ),
        VocabularyEntity(
            id = "v_a0_19_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "window",
            uzbekTranslation = "deraza",
            exampleEnglish = "There are two windows in my room.",
            exampleUzbek = "Xonamda ikkita deraza bor."
        ),
        VocabularyEntity(
            id = "v_a0_19_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "see",
            uzbekTranslation = "ko‘rmoq",
            exampleEnglish = "I see a bed.",
            exampleUzbek = "Men karavotni ko‘ryapman."
        ),
        // Lesson 25 vocabulary (The shop)
        VocabularyEntity(id = "v_a0_25_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "the", uzbekTranslation = "o'sha", exampleEnglish = "The book is good.", exampleUzbek = "O'sha kitob yaxshi."),
        VocabularyEntity(id = "v_a0_25_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "shop", uzbekTranslation = "do'kon", exampleEnglish = "I will go to the shop.", exampleUzbek = "Men do'konga boraman."),
        VocabularyEntity(id = "v_a0_25_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "definite", uzbekTranslation = "aniq", exampleEnglish = "A definite shop.", exampleUzbek = "Aniq do'kon."),
        VocabularyEntity(id = "v_a0_25_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "known", uzbekTranslation = "ma'lum", exampleEnglish = "A known place.", exampleUzbek = "Ma'lum joy."),
        VocabularyEntity(id = "v_a0_25_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "specific", uzbekTranslation = "maxsus", exampleEnglish = "A specific item.", exampleUzbek = "Maxsus buyum."),
        VocabularyEntity(id = "v_a0_25_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "item", uzbekTranslation = "buyum", exampleEnglish = "An item on the table.", exampleUzbek = "Stoldagi buyum."),
        VocabularyEntity(id = "v_a0_25_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, englishWord = "object", uzbekTranslation = "ob'ekt", exampleEnglish = "An object in my room.", exampleUzbek = "Xonamdagi ob'ekt."),

        // Lesson 26 vocabulary (Will)
        VocabularyEntity(id = "v_a0_26_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "will", uzbekTranslation = "kelasi zamon", exampleEnglish = "I will go.", exampleUzbek = "Men boraman."),
        VocabularyEntity(id = "v_a0_26_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "future", uzbekTranslation = "kelajak", exampleEnglish = "In the future.", exampleUzbek = "Kelajakda."),
        VocabularyEntity(id = "v_a0_26_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "tomorrow", uzbekTranslation = "ertaga", exampleEnglish = "I will go tomorrow.", exampleUzbek = "Men ertaga boraman."),
        VocabularyEntity(id = "v_a0_26_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "soon", uzbekTranslation = "tez orada", exampleEnglish = "I will see you soon.", exampleUzbek = "Tez orada ko'rishamiz."),
        VocabularyEntity(id = "v_a0_26_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "later", uzbekTranslation = "keyinroq", exampleEnglish = "See you later.", exampleUzbek = "Keyinroq ko'rishguncha."),
        VocabularyEntity(id = "v_a0_26_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "action", uzbekTranslation = "harakat", exampleEnglish = "A future action.", exampleUzbek = "Kelgusi harakat."),
        VocabularyEntity(id = "v_a0_26_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, englishWord = "plan", uzbekTranslation = "reja", exampleEnglish = "I have a plan.", exampleUzbek = "Menda reja bor."),

        // Lesson 27 vocabulary (Can)
        VocabularyEntity(id = "v_a0_27_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "can", uzbekTranslation = "qila olmoq", exampleEnglish = "I can go.", exampleUzbek = "Men bora olaman."),
        VocabularyEntity(id = "v_a0_27_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "able", uzbekTranslation = "qodir", exampleEnglish = "I am able to learn.", exampleUzbek = "Men o'rganishga qodirman."),
        VocabularyEntity(id = "v_a0_27_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "ability", uzbekTranslation = "imkoniyat", exampleEnglish = "A great ability.", exampleUzbek = "Katta imkoniyat."),
        VocabularyEntity(id = "v_a0_27_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "skill", uzbekTranslation = "ko'nikma", exampleEnglish = "Speaking skill.", exampleUzbek = "Gapirish ko'nikmasi."),
        VocabularyEntity(id = "v_a0_27_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "power", uzbekTranslation = "kuch", exampleEnglish = "Knowledge is power.", exampleUzbek = "Bilim — kuch."),
        VocabularyEntity(id = "v_a0_27_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "talent", uzbekTranslation = "istedod", exampleEnglish = "A special talent.", exampleUzbek = "Maxsus iste'dod."),
        VocabularyEntity(id = "v_a0_27_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, englishWord = "possible", uzbekTranslation = "mumkin", exampleEnglish = "Everything is possible.", exampleUzbek = "Hammasi mumkin."),

        // Lesson 28 vocabulary (Must)
        VocabularyEntity(id = "v_a0_28_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "must", uzbekTranslation = "kerak / shart", exampleEnglish = "I must go.", exampleUzbek = "Borishim kerak."),
        VocabularyEntity(id = "v_a0_28_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "obligation", uzbekTranslation = "majburiyat", exampleEnglish = "A strong obligation.", exampleUzbek = "Kuchli majburiyat."),
        VocabularyEntity(id = "v_a0_28_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "duty", uzbekTranslation = "burch", exampleEnglish = "A daily duty.", exampleUzbek = "Kunlik burch."),
        VocabularyEntity(id = "v_a0_28_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "required", uzbekTranslation = "talab qilinadi", exampleEnglish = "Practice is required.", exampleUzbek = "Mashq talab qilinadi."),
        VocabularyEntity(id = "v_a0_28_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "forbidden", uzbekTranslation = "taqiqlangan", exampleEnglish = "It is forbidden.", exampleUzbek = "Bu taqiqlangan."),
        VocabularyEntity(id = "v_a0_28_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "rule", uzbekTranslation = "qoida", exampleEnglish = "A grammar rule.", exampleUzbek = "Grammatika qoidasi."),
        VocabularyEntity(id = "v_a0_28_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, englishWord = "strict", uzbekTranslation = "qattiq", exampleEnglish = "A strict rule.", exampleUzbek = "Qat'iy qoida."),

        // Lesson 29 vocabulary (May)
        VocabularyEntity(id = "v_a0_29_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "may", uzbekTranslation = "mumkin / ruxsat", exampleEnglish = "May I go?", exampleUzbek = "Borsam maylimi?"),
        VocabularyEntity(id = "v_a0_29_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "permission", uzbekTranslation = "ruxsat", exampleEnglish = "Ask for permission.", exampleUzbek = "Ruxsat so'ramoq."),
        VocabularyEntity(id = "v_a0_29_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "allowed", uzbekTranslation = "ruxsat etilgan", exampleEnglish = "It is allowed.", exampleUzbek = "Ruxsat etilgan."),
        VocabularyEntity(id = "v_a0_29_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "polite", uzbekTranslation = "odobli", exampleEnglish = "A polite question.", exampleUzbek = "Odobli savol."),
        VocabularyEntity(id = "v_a0_29_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "formal", uzbekTranslation = "rasmiy", exampleEnglish = "A formal talk.", exampleUzbek = "Rasmiy suhbat."),
        VocabularyEntity(id = "v_a0_29_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "ask", uzbekTranslation = "so'ramoq", exampleEnglish = "Ask a question.", exampleUzbek = "Savol so'ramoq."),
        VocabularyEntity(id = "v_a0_29_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, englishWord = "grant", uzbekTranslation = "bermoq", exampleEnglish = "Grant permission.", exampleUzbek = "Ruxsat bermoq."),

        // Lesson 30 vocabulary (Have to)
        VocabularyEntity(id = "v_a0_30_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "external", uzbekTranslation = "tashqi", exampleEnglish = "An external reason.", exampleUzbek = "Tashqi sabab."),
        VocabularyEntity(id = "v_a0_30_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "reason", uzbekTranslation = "sabab", exampleEnglish = "A good reason.", exampleUzbek = "Yaxshi sabab."),
        VocabularyEntity(id = "v_a0_30_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "doctor", uzbekTranslation = "shifokor", exampleEnglish = "See a doctor.", exampleUzbek = "Shifokor qabuliga bormoq."),
        VocabularyEntity(id = "v_a0_30_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "necessity", uzbekTranslation = "zaruriyat", exampleEnglish = "A real necessity.", exampleUzbek = "Haqiqiy zaruriyat."),
        VocabularyEntity(id = "v_a0_30_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "choice", uzbekTranslation = "tanlov", exampleEnglish = "Make a choice.", exampleUzbek = "Tanlov qilmoq."),
        VocabularyEntity(id = "v_a0_30_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "routine", uzbekTranslation = "kun tartibi", exampleEnglish = "A morning routine.", exampleUzbek = "Tonggi tartib."),
        VocabularyEntity(id = "v_a0_30_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, englishWord = "task", uzbekTranslation = "vazifa", exampleEnglish = "A learning task.", exampleUzbek = "O'rganish vazifasi."),

        // Lesson 31 vocabulary (Big — Sifat oddiy daraja)
        VocabularyEntity(id = "v_a0_31_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "big", uzbekTranslation = "katta", exampleEnglish = "The book is big.", exampleUzbek = "Kitob katta."),
        VocabularyEntity(id = "v_a0_31_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "small", uzbekTranslation = "kichik", exampleEnglish = "The cup is small.", exampleUzbek = "Piyola kichik."),
        VocabularyEntity(id = "v_a0_31_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "sweet", uzbekTranslation = "shirin", exampleEnglish = "The apple is sweet.", exampleUzbek = "Olma shirin."),
        VocabularyEntity(id = "v_a0_31_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "sour", uzbekTranslation = "nordon", exampleEnglish = "The lemon is sour.", exampleUzbek = "Limon nordon."),
        VocabularyEntity(id = "v_a0_31_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "salty", uzbekTranslation = "sho'r", exampleEnglish = "The soup is salty.", exampleUzbek = "Sho'rva sho'r."),
        VocabularyEntity(id = "v_a0_31_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "hot", uzbekTranslation = "issiq", exampleEnglish = "The tea is hot.", exampleUzbek = "Choy issiq."),
        VocabularyEntity(id = "v_a0_31_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, englishWord = "cold", uzbekTranslation = "sovuq", exampleEnglish = "The water is cold.", exampleUzbek = "Suv sovuq."),

        // Lesson 32 vocabulary (Bigger — Qiyosiy daraja)
        VocabularyEntity(id = "v_a0_32_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "bigger", uzbekTranslation = "kattaroq", exampleEnglish = "The book is bigger than the cup.", exampleUzbek = "Kitob piyoladan kattaroq."),
        VocabularyEntity(id = "v_a0_32_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "smaller", uzbekTranslation = "kichikroq", exampleEnglish = "The cup is smaller than the book.", exampleUzbek = "Piyola kitobdan kichikroq."),
        VocabularyEntity(id = "v_a0_32_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "more", uzbekTranslation = "ko'proq", exampleEnglish = "She is more beautiful than her sister.", exampleUzbek = "U singlisidan chiroyliroq."),
        VocabularyEntity(id = "v_a0_32_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "faster", uzbekTranslation = "tezroq", exampleEnglish = "The tiger is faster than the cat.", exampleUzbek = "Yo'lbars mushukdan tezroq."),
        VocabularyEntity(id = "v_a0_32_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "more beautiful", uzbekTranslation = "chiroyliroq", exampleEnglish = "She is more beautiful than her sister.", exampleUzbek = "U singlisidan chiroyliroq."),
        VocabularyEntity(id = "v_a0_32_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "more interesting", uzbekTranslation = "qiziqarliroq", exampleEnglish = "This book is more interesting than that book.", exampleUzbek = "Bu kitob o'sha kitobdan qiziqarliroq."),
        VocabularyEntity(id = "v_a0_32_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, englishWord = "than", uzbekTranslation = "-dan ko'ra", exampleEnglish = "The book is bigger than the cup.", exampleUzbek = "Kitob piyoladan kattaroq."),

        // Lesson 33 vocabulary (The biggest — Orttirma daraja)
        VocabularyEntity(id = "v_a0_33_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the biggest", uzbekTranslation = "eng katta", exampleEnglish = "The book is the biggest.", exampleUzbek = "Kitob eng katta."),
        VocabularyEntity(id = "v_a0_33_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the smallest", uzbekTranslation = "eng kichik", exampleEnglish = "The cup is the smallest.", exampleUzbek = "Piyola eng kichik."),
        VocabularyEntity(id = "v_a0_33_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the most", uzbekTranslation = "eng ko'p", exampleEnglish = "She is the most beautiful girl.", exampleUzbek = "U eng chiroyli qiz."),
        VocabularyEntity(id = "v_a0_33_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the fastest", uzbekTranslation = "eng tez", exampleEnglish = "The tiger is the fastest.", exampleUzbek = "Yo'lbars eng tez."),
        VocabularyEntity(id = "v_a0_33_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the most beautiful", uzbekTranslation = "eng chiroyli", exampleEnglish = "She is the most beautiful girl.", exampleUzbek = "U eng chiroyli qiz."),
        VocabularyEntity(id = "v_a0_33_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the most interesting", uzbekTranslation = "eng qiziqarli", exampleEnglish = "This book is the most interesting.", exampleUzbek = "Bu kitob eng qiziqarli."),
        VocabularyEntity(id = "v_a0_33_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, englishWord = "the sweetest", uzbekTranslation = "eng shirin", exampleEnglish = "The apple is the sweetest.", exampleUzbek = "Olma eng shirin."),

        // Lesson 34 vocabulary (I do my homework — “Do” qilmoq)
        VocabularyEntity(id = "v_a0_34_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "do", uzbekTranslation = "qilmoq", exampleEnglish = "I do my homework.", exampleUzbek = "Men uy vazifamni qilaman."),
        VocabularyEntity(id = "v_a0_34_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "does", uzbekTranslation = "qiladi (u)", exampleEnglish = "He does his homework.", exampleUzbek = "U uy vazifasini qiladi."),
        VocabularyEntity(id = "v_a0_34_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "homework", uzbekTranslation = "uy vazifasi", exampleEnglish = "I do my homework.", exampleUzbek = "Men uy vazifamni qilaman."),
        VocabularyEntity(id = "v_a0_34_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "my homework", uzbekTranslation = "mening uy vazifam", exampleEnglish = "I do my homework.", exampleUzbek = "Men uy vazifamni qilaman."),
        VocabularyEntity(id = "v_a0_34_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "his homework", uzbekTranslation = "uning uy vazifasi", exampleEnglish = "He does his homework.", exampleUzbek = "U uy vazifasini qiladi."),
        VocabularyEntity(id = "v_a0_34_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "her work", uzbekTranslation = "uning ishi (qiz)", exampleEnglish = "She does her work.", exampleUzbek = "U ishini qiladi."),
        VocabularyEntity(id = "v_a0_34_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, englishWord = "our homework", uzbekTranslation = "bizning uy vazifamiz", exampleEnglish = "We do our homework.", exampleUzbek = "Biz uy vazifamizni qilamiz."),

        // Lesson 35 vocabulary (Do you like...? — “Do” savolda)
        VocabularyEntity(id = "v_a0_35_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "like", uzbekTranslation = "yaxshi ko'rmoq", exampleEnglish = "Do you like apples?", exampleUzbek = "Olmalarni yaxshi ko'rasizmi?"),
        VocabularyEntity(id = "v_a0_35_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "do you like", uzbekTranslation = "yaxshi ko'rasizmi", exampleEnglish = "Do you like apples?", exampleUzbek = "Olmalarni yaxshi ko'rasizmi?"),
        VocabularyEntity(id = "v_a0_35_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "does he like", uzbekTranslation = "u yaxshi ko'radimi", exampleEnglish = "Does he like cats?", exampleUzbek = "U mushuklarni yaxshi ko'radimi?"),
        VocabularyEntity(id = "v_a0_35_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "yes, i do.", uzbekTranslation = "ha, shunday (yaxshi ko'raman)", exampleEnglish = "Yes, I do.", exampleUzbek = "Ha, yaxshi ko'raman."),
        VocabularyEntity(id = "v_a0_35_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "no, i do not.", uzbekTranslation = "yo'q, unday emas", exampleEnglish = "No, I do not.", exampleUzbek = "Yo'q, yaxshi ko'rmayman."),
        VocabularyEntity(id = "v_a0_35_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "yes, he does.", uzbekTranslation = "ha, u shunday", exampleEnglish = "Yes, he does.", exampleUzbek = "Ha, yaxshi ko'radi."),
        VocabularyEntity(id = "v_a0_35_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, englishWord = "no, he does not.", uzbekTranslation = "yo'q, u unday emas", exampleEnglish = "No, he does not.", exampleUzbek = "Yo'q, u yaxshi ko'rmaydi."),

        // Lesson 36 vocabulary (I do not like... — “Do” inkorda)
        VocabularyEntity(id = "v_a0_36_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "do not", uzbekTranslation = "qilmaslik / emas", exampleEnglish = "I do not like apples.", exampleUzbek = "Men olmalarni yaxshi ko'rmayman."),
        VocabularyEntity(id = "v_a0_36_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "does not", uzbekTranslation = "qilmaydi (u)", exampleEnglish = "He does not like cats.", exampleUzbek = "U mushuklarni yaxshi ko'rmaydi."),
        VocabularyEntity(id = "v_a0_36_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "don’t", uzbekTranslation = "qilmayman (qisqa)", exampleEnglish = "I don’t like apples.", exampleUzbek = "Men olmalarni yaxshi ko'rmayman."),
        VocabularyEntity(id = "v_a0_36_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "doesn’t", uzbekTranslation = "qilmaydi (qisqa)", exampleEnglish = "He doesn’t like cats.", exampleUzbek = "U mushuklarni yaxshi ko'rmaydi."),
        VocabularyEntity(id = "v_a0_36_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "i do not like", uzbekTranslation = "men yoqtirmayman", exampleEnglish = "I do not like apples.", exampleUzbek = "Men olmalarni yaxshi ko'rmayman."),
        VocabularyEntity(id = "v_a0_36_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "he does not like", uzbekTranslation = "u yoqtirmaydi", exampleEnglish = "He does not like cats.", exampleUzbek = "U mushuklarni yaxshi ko'rmaydi."),
        VocabularyEntity(id = "v_a0_36_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, englishWord = "they do not like", uzbekTranslation = "ular yoqtirmaydilar", exampleEnglish = "They do not like English.", exampleUzbek = "Ular ingliz tilini yaxshi ko'rmaydilar.")
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // Dars 1 rules
        GrammarRuleEntity(
            id = "g_a0_01_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            titleUzbek = "Xush kelibsiz iborasi — Welcome to",
            explanationUzbek = "Ilova, dars yoki joyga xush kelibsiz deyishda 'Welcome to ...' ishlatiladi.",
            exampleEnglish = "Welcome to TalkMe!",
            exampleUzbek = "TalkMe ilovasiga xush kelibsiz!",
            associatedVocabularyIdsCsv = "v_a0_01_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_01_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            titleUzbek = "Xato — o‘rganish qismi — Part of learning",
            explanationUzbek = "Xato qilishdan qo‘rqmang: ingliz tilida 'Every mistake is part of learning' deyiladi.",
            exampleEnglish = "Every mistake is part of learning.",
            exampleUzbek = "Har bir xato — bu o‘rganishning bir qismi.",
            associatedVocabularyIdsCsv = "v_a0_01_03",
            isStudied = false,
            isMastered = false
        ),
        // Dars 2 rules
        GrammarRuleEntity(
            id = "g_a0_02_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            titleUzbek = "Salomlashish — Hello va Hi",
            explanationUzbek = "'Hello' rasmiyroq salom, 'Hi' esa do‘stona salom. Suhbat oxirida 'Good bye!' deyiladi.",
            exampleEnglish = "Hello! Welcome to TalkMe!",
            exampleUzbek = "Salom! TalkMe’ga xush kelibsiz!",
            associatedVocabularyIdsCsv = "v_a0_02_01,v_a0_02_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_02_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            titleUzbek = "Ism so‘rash — What’s your name?",
            explanationUzbek = "Ism so‘rash uchun 'What’s your name?', javob uchun 'My name is ...' modeli ishlatiladi.",
            exampleEnglish = "My name is Lera.",
            exampleUzbek = "Mening ismim Lera.",
            associatedVocabularyIdsCsv = "v_a0_02_04,v_a0_02_05",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_02_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            titleUzbek = "Tushunmaganda — Sorry, what?",
            explanationUzbek = "Agar gapni tushunmasangiz yoki xato qilsangiz, 'Sorry, what?' deb bemalol qayta so‘rashingiz mumkin.",
            exampleEnglish = "Sorry, what?",
            exampleUzbek = "Kechirasiz, nima dedingiz?",
            associatedVocabularyIdsCsv = "v_a0_02_06,v_a0_02_07",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 3 rules
        GrammarRuleEntity(
            id = "g_a0_03_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            titleUzbek = "Undosh oldidan — a",
            explanationUzbek = "Bitta narsa undosh harf bilan boshlansa, oldiga 'a' qo‘yiladi: a cup, a book, a cat, a pen, a dog.",
            exampleEnglish = "A book.",
            exampleUzbek = "Kitob (bitta kitob).",
            associatedVocabularyIdsCsv = "v_a0_03_01,v_a0_03_02,v_a0_03_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_03_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            titleUzbek = "Unli (a, e, i, o, u) oldidan — an",
            explanationUzbek = "Bitta narsa unli harf (a, e, i, o, u) bilan boshlansa, oldiga 'an' qo‘yiladi: an apple, an egg, an orange.",
            exampleEnglish = "An apple.",
            exampleUzbek = "Olma (bitta olma).",
            associatedVocabularyIdsCsv = "v_a0_03_06,v_a0_03_07,v_a0_03_08",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 4 rules
        GrammarRuleEntity(
            id = "g_a0_04_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            titleUzbek = "“and” bog‘lovchisi — va",
            explanationUzbek = "Ikkita narsani bog‘lash uchun orasiga 'and' (va) qo‘yiladi: a book and a cup.",
            exampleEnglish = "a book and a cup",
            exampleUzbek = "kitob va piyola",
            associatedVocabularyIdsCsv = "v_a0_04_01,v_a0_04_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_04_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            titleUzbek = "Har bir narsa oldida o‘z a/an artikli",
            explanationUzbek = "Birinchi narsaga a/an, keyin and, keyin ikkinchi narsaga yana a/an qo‘yiladi. Hech qachon tushirib qoldirmang.",
            exampleEnglish = "an apple and an egg",
            exampleUzbek = "olma va tuxum",
            associatedVocabularyIdsCsv = "v_a0_04_05,v_a0_04_08",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 5 rules
        GrammarRuleEntity(
            id = "g_a0_05_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            titleUzbek = "This (bu) va That (o‘sha)",
            explanationUzbek = "Qo‘lingizdagi, yaqin joydagi bitta narsa uchun 'this', narigi stolda yoki uzoqroqdagi bitta narsa uchun 'that' ishlatiladi.",
            exampleEnglish = "this book and that cup",
            exampleUzbek = "bu kitob va o‘sha piyola",
            associatedVocabularyIdsCsv = "v_a0_05_01,v_a0_05_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_05_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            titleUzbek = "This/That bilan a/an qo‘yilmaydi",
            explanationUzbek = "'this' va 'that' o‘zi bittalikni bildiradi, shuning uchun oldidan yoki keyinidan a/an ishlatilmaydi: this book ✅ (this a book ❌).",
            exampleEnglish = "this apple, that egg",
            exampleUzbek = "bu olma, o‘sha tuxum",
            associatedVocabularyIdsCsv = "v_a0_05_05,v_a0_05_08",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 6 rules
        GrammarRuleEntity(
            id = "g_a0_06_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            titleUzbek = "These (bular) va Those (o‘shalar)",
            explanationUzbek = "Yaqin joydagi ko‘p narsalar uchun 'these', uzoqroqdagi ko‘p narsalar uchun 'those' ishlatiladi.",
            exampleEnglish = "these books, those cups",
            exampleUzbek = "bu kitoblar, o‘sha piyolalar",
            associatedVocabularyIdsCsv = "v_a0_06_01,v_a0_06_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_06_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            titleUzbek = "Ko‘plik -s va a/an qo‘yilmasligi",
            explanationUzbek = "Ko‘p narsada so‘z oxiriga -s qo‘shiladi (books, cups, apples) va these/those bilan a/an ishlatilmaydi.",
            exampleEnglish = "these apples, those books",
            exampleUzbek = "bu olmalar, o‘sha kitoblar",
            associatedVocabularyIdsCsv = "v_a0_06_05,v_a0_06_06",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 7 rules
        GrammarRuleEntity(
            id = "g_a0_07_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            titleUzbek = "Have (bor bo‘lmoq) va I have (menda bor)",
            explanationUzbek = "'Have' fe’li 'bor bo‘lmoq' degani. 'I have' birgalikda 'menda bor' ma’nosini beradi.",
            exampleEnglish = "I have a book.",
            exampleUzbek = "Menda kitob bor.",
            associatedVocabularyIdsCsv = "v_a0_07_01,v_a0_07_02,v_a0_07_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_07_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            titleUzbek = "I have + a/an + ot",
            explanationUzbek = "Bitta narsa haqida gapirganda 'I have' dan keyin unli uchun 'an', undosh uchun 'a' qo‘yiladi.",
            exampleEnglish = "I have an apple.",
            exampleUzbek = "Menda olma bor.",
            associatedVocabularyIdsCsv = "v_a0_07_07,v_a0_07_08",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 8 rules
        GrammarRuleEntity(
            id = "g_a0_08_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            titleUzbek = "Savol shakli — Have I got ...?",
            explanationUzbek = "Savol berishda 'have' oldinga chiqadi va 'got' qo‘shiladi: Have I got + a/an + ot?",
            exampleEnglish = "Have I got a book?",
            exampleUzbek = "Menda kitob bormi?",
            associatedVocabularyIdsCsv = "v_a0_08_01,v_a0_08_04",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_08_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            titleUzbek = "Qisqa javoblar — Yes, I have / No, I have not",
            explanationUzbek = "'Have I got ...?' savoliga 'Yes, I have.' (ha, bor) yoki 'No, I have not.' (yo‘q, yo‘q) deb javob beriladi.",
            exampleEnglish = "Yes, I have.",
            exampleUzbek = "Ha, bor.",
            associatedVocabularyIdsCsv = "v_a0_08_02,v_a0_08_03",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 9 rules
        GrammarRuleEntity(
            id = "g_a0_09_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            titleUzbek = "Inkor shakli — I have not got",
            explanationUzbek = "Inkor qilish uchun 'have' dan keyin 'not' va 'got' qo‘yiladi: I have not got + a/an + ot.",
            exampleEnglish = "I have not got a book.",
            exampleUzbek = "Menda kitob yo‘q.",
            associatedVocabularyIdsCsv = "v_a0_09_01,v_a0_09_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_09_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            titleUzbek = "Qisqa shakl — I haven’t got",
            explanationUzbek = "'I have not got' ning qisqa shakli 'I haven’t got' bo‘lib, ma’nosi bir xil (menda yo‘q).",
            exampleEnglish = "I haven’t got a book.",
            exampleUzbek = "Menda kitob yo‘q.",
            associatedVocabularyIdsCsv = "v_a0_09_02,v_a0_09_06",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 10 rules
        GrammarRuleEntity(
            id = "g_a0_10_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            titleUzbek = "1 dan 10 gacha sonlar — one .. ten",
            explanationUzbek = "Son — “nechta?” savoliga javob: one (1), two (2), three (3), four (4), five (5), six (6), seven (7), eight (8), nine (9), ten (10).",
            exampleEnglish = "One book, two books.",
            exampleUzbek = "Bitta kitob, ikkita kitob.",
            associatedVocabularyIdsCsv = "v_a0_10_01,v_a0_10_02,v_a0_10_03,v_a0_10_10",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_10_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            titleUzbek = "Ko‘p narsa bilan -s — two books, three apples",
            explanationUzbek = "Bitta narsa → a book, an apple. Ko‘p narsa → two books, three apples (oxiriga -s qo‘shiladi).",
            exampleEnglish = "Ten apples, please.",
            exampleUzbek = "O‘nta olma, iltimos.",
            associatedVocabularyIdsCsv = "v_a0_10_02,v_a0_10_05,v_a0_10_10",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 11 rules
        GrammarRuleEntity(
            id = "g_a0_11_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            titleUzbek = "I have + son + ko‘plik (-s)",
            explanationUzbek = "2 dan 10 gacha son bilan ot oxiriga -s qo‘shiladi: I have two books, I have three apples.",
            exampleEnglish = "I have two books.",
            exampleUzbek = "Menda ikkita kitob bor.",
            associatedVocabularyIdsCsv = "v_a0_11_03,v_a0_11_04,v_a0_11_05",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_11_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            titleUzbek = "Bitta narsa: a/an yoki one (-s yo‘q)",
            explanationUzbek = "Bitta narsa bilan -s qo‘shilmaydi: I have a book yoki I have one book ✅ (I have one books ❌).",
            exampleEnglish = "I have one book.",
            exampleUzbek = "Menda bitta kitob bor.",
            associatedVocabularyIdsCsv = "v_a0_11_01,v_a0_11_02",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 12 rules
        GrammarRuleEntity(
            id = "g_a0_12_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            titleUzbek = "But (“lekin”) va No (“yo‘q”)",
            explanationUzbek = "‘but’ ikkita gapni bog‘laydi (“lekin”). ‘I have no ...’ esa biror narsa yo‘qligini aytadi (no dan keyin a/an qo‘yilmaydi).",
            exampleEnglish = "I have a book, but I have no cups.",
            exampleUzbek = "Menda kitob bor, lekin piyola yo‘q.",
            associatedVocabularyIdsCsv = "v_a0_12_01,v_a0_12_02,v_a0_12_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_12_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            titleUzbek = "I have ___, but I have no ___",
            explanationUzbek = "Bitta yoki ko‘p narsani bor deb aytib, ‘but I have no + ot(-s)’ bilan yo‘q narsani bog‘laysiz.",
            exampleEnglish = "I have two pens, but I have no books.",
            exampleUzbek = "Menda ikkita ruchka bor, lekin kitob yo‘q.",
            associatedVocabularyIdsCsv = "v_a0_12_04,v_a0_12_05,v_a0_12_07",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 13 rules
        GrammarRuleEntity(
            id = "g_a0_13_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            titleUzbek = "Sanoq sonlar (“nechta?”) — one .. ten",
            explanationUzbek = "Sanoq — “nechta?” savoliga javob: one tiger, two tigers, three tigers .. ten tigers.",
            exampleEnglish = "I see three tigers.",
            exampleUzbek = "Men uchta yo‘lbarsni ko‘ryapman.",
            associatedVocabularyIdsCsv = "v_a0_13_02,v_a0_13_03,v_a0_13_04",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_13_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            titleUzbek = "Bitta (tiger) va Ko‘p (tigers)",
            explanationUzbek = "Bitta bo‘lsa a tiger yoki one tiger (-s yo‘q). 2 dan 10 gacha son bilan oxiriga -s qo‘shiladi: two tigers.",
            exampleEnglish = "I have a tiger. I have three tigers.",
            exampleUzbek = "Menda bitta yo‘lbars bor. Menda uchta yo‘lbars bor.",
            associatedVocabularyIdsCsv = "v_a0_13_01,v_a0_13_02,v_a0_13_04",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 14 rules
        GrammarRuleEntity(
            id = "g_a0_14_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            titleUzbek = "Tartib sonlar — the first .. the tenth",
            explanationUzbek = "Tartib — “nechanchi?” savoliga javob. First, second, third alohida yodlanadi, qolganlarida -th (fourth..tenth) va oldida ‘the’ keladi.",
            exampleEnglish = "the first tiger, the second tiger, the third tiger",
            exampleUzbek = "birinchi yo‘lbars, ikkinchi yo‘lbars, uchinchi yo‘lbars",
            associatedVocabularyIdsCsv = "v_a0_14_01,v_a0_14_02,v_a0_14_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_14_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            titleUzbek = "Sanoq (nechta?) vs Tartib (nechanchi?)",
            explanationUzbek = "Sanoq son (“nechta?”) → three tigers. Tartib son (“nechanchi?”) → the third tiger (oldida ‘the’).",
            exampleEnglish = "Three tigers — the third tiger.",
            exampleUzbek = "Uchta yo‘lbars — uchinchi yo‘lbars.",
            associatedVocabularyIdsCsv = "v_a0_14_03,v_a0_14_05",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 15 rules
        GrammarRuleEntity(
            id = "g_a0_15_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            titleUzbek = "Here — “mana / mana bu” (yaqin)",
            explanationUzbek = "‘Here’ qo‘lingizda, oldingizda yoki yoningizdagi yaqin narsani ko‘rsatganda ishlatiladi.",
            exampleEnglish = "Here is a book.",
            exampleUzbek = "Mana bitta kitob.",
            associatedVocabularyIdsCsv = "v_a0_15_01,v_a0_15_07",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_15_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            titleUzbek = "Here is + a/an + ot (bitta narsa)",
            explanationUzbek = "Bitta narsani ko‘rsatsangiz ‘Here is’ ishlatiladi va ot oldidan unli bilan ‘an’, undosh bilan ‘a’ qo‘yiladi.",
            exampleEnglish = "Here is a boy. Here is an apple.",
            exampleUzbek = "Mana bitta bola. Mana bitta olma.",
            associatedVocabularyIdsCsv = "v_a0_15_02,v_a0_15_06",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 16 rules
        GrammarRuleEntity(
            id = "g_a0_16_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            titleUzbek = "Here are + son + ot(-s) (ko‘p narsa)",
            explanationUzbek = "Yaqindagi ko‘p narsani ko‘rsatsangiz ‘Here are’ ishlatiladi va ot oxiriga -s qo‘shiladi: boys, girls, tigers.",
            exampleEnglish = "Here are two boys.",
            exampleUzbek = "Mana ikkita bola.",
            associatedVocabularyIdsCsv = "v_a0_16_01,v_a0_16_02,v_a0_16_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_16_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            titleUzbek = "Here is (bitta) vs Here are (ko‘p)",
            explanationUzbek = "Bitta narsa → Here is (a/an). Ko‘p narsa → Here are (son + -s).",
            exampleEnglish = "Here is a boy. Here are two boys.",
            exampleUzbek = "Mana bitta bola. Mana ikkita bola.",
            associatedVocabularyIdsCsv = "v_a0_16_01,v_a0_16_04",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 17 rules
        GrammarRuleEntity(
            id = "g_a0_17_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            titleUzbek = "There is + a/an + ot (bitta narsa bor)",
            explanationUzbek = "‘There’ — u yerda / bor. Uzoqroq narsa yoki umuman bor-yo‘qligi haqida bitta narsa uchun ‘There is’ ishlatiladi.",
            exampleEnglish = "There is a boy.",
            exampleUzbek = "Bitta bola bor.",
            associatedVocabularyIdsCsv = "v_a0_17_01,v_a0_17_02,v_a0_17_06",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_17_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            titleUzbek = "Here is (yaqin) vs There is (u yerda bor)",
            explanationUzbek = "‘Here is’ — mana, qo‘limda, yaqin. ‘There is’ — u yerda bor, uzoqroq yoki umuman bor.",
            exampleEnglish = "Here is a boy. There is a boy.",
            exampleUzbek = "Mana bitta bola. U yerda bitta bola bor.",
            associatedVocabularyIdsCsv = "v_a0_17_02,v_a0_17_04",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 18 rules
        GrammarRuleEntity(
            id = "g_a0_18_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            titleUzbek = "There are + son + ot(-s) (ko‘p narsa bor)",
            explanationUzbek = "Ko‘p narsa bor desangiz ‘There are’ ishlatiladi va ot oxiriga -s qo‘shiladi (boys, tigers).",
            exampleEnglish = "There are two boys.",
            exampleUzbek = "Ikkita bola bor.",
            associatedVocabularyIdsCsv = "v_a0_18_01,v_a0_18_02,v_a0_18_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_18_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            titleUzbek = "4 ta shakl: Here is/are va There is/are",
            explanationUzbek = "Bitta → is, ko‘p → are. Yaqin → here, uzoq/bor → there.",
            exampleEnglish = "There are five tigers.",
            exampleUzbek = "Beshta yo‘lbars bor.",
            associatedVocabularyIdsCsv = "v_a0_18_01,v_a0_18_03,v_a0_18_07",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 19 grammar rules (My room)
        GrammarRuleEntity(
            id = "g_a0_19_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            titleUzbek = "Xona jihozlari va in my room",
            explanationUzbek = "Bed, table, chair, door, window — xona jihozlari. ‘In my room’ esa ‘mening xonamda’ degani (in = ichida).",
            exampleEnglish = "There is a table in my room.",
            exampleUzbek = "Xonamda bitta stol bor.",
            associatedVocabularyIdsCsv = "v_a0_19_02,v_a0_19_03,v_a0_19_04",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_19_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            titleUzbek = "Yangi fe’llar: see, give me, show",
            explanationUzbek = "‘See’ (ko‘rmoq), ‘give me’ (menga ber), ‘show’ (ko‘rsatmoq) fe’llari xona haqida gapirganda va kundalik muloqotda ishlatiladi.",
            exampleEnglish = "Give me a book. Show me your room.",
            exampleUzbek = "Menga kitob ber. Menga xonangni ko‘rsat.",
            associatedVocabularyIdsCsv = "v_a0_19_07",
            isStudied = false,
            isMastered = false
        ),
        // Lesson 25 grammar rules (The shop)
        GrammarRuleEntity(
            id = "g_a0_25_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            titleUzbek = "Definite Article The",
            explanationUzbek = "The — aniqlik artikli. O'sha aniq, biz biladigan narsalar uchun ishlatiladi.",
            exampleEnglish = "The book is good.",
            exampleUzbek = "O'sha kitob yaxshi.",
            associatedVocabularyIdsCsv = "v_a0_25_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_25_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            titleUzbek = "The with Singular and Plural",
            explanationUzbek = "The ham birlik, ham ko'plikdagi otlar bilan ishlaydi.",
            exampleEnglish = "The books are on the table.",
            exampleUzbek = "Kitoblar stol ustida.",
            associatedVocabularyIdsCsv = "v_a0_25_01",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 26 grammar rules (Will)
        GrammarRuleEntity(
            id = "g_a0_26_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            titleUzbek = "Will Future",
            explanationUzbek = "Will — kelasi zamon belgisi. Darak, inkor, savol shakllari.",
            exampleEnglish = "I will go to the shop.",
            exampleUzbek = "Men do'konga boraman.",
            associatedVocabularyIdsCsv = "v_a0_26_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_26_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            titleUzbek = "Unchanging Verb after Will",
            explanationUzbek = "Will dan keyin fe'l o'zgarmaydi (-s, -ing qo'shilmaydi).",
            exampleEnglish = "He will go tomorrow.",
            exampleUzbek = "U ertaga boradi.",
            associatedVocabularyIdsCsv = "v_a0_26_01",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 27 grammar rules (Can)
        GrammarRuleEntity(
            id = "g_a0_27_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            titleUzbek = "Can Ability",
            explanationUzbek = "Can — imkoniyat bildiradi (qila olaman).",
            exampleEnglish = "I can go to the shop.",
            exampleUzbek = "Men do'konga bora olaman.",
            associatedVocabularyIdsCsv = "v_a0_27_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_27_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            titleUzbek = "Can Sentences",
            explanationUzbek = "Darak, inkor (cannot) va savol (Can I...?) shakllari.",
            exampleEnglish = "Can I see the tiger?",
            exampleUzbek = "Yo'lbarsni ko'ra olamanmi?",
            associatedVocabularyIdsCsv = "v_a0_27_01",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 28 grammar rules (Must)
        GrammarRuleEntity(
            id = "g_a0_28_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            titleUzbek = "Must Obligation",
            explanationUzbek = "Must — majburiyat (kerak/shart). Must not — taqiq (mumkin emas).",
            exampleEnglish = "I must go to the shop.",
            exampleUzbek = "Men do'konga borishim kerak.",
            associatedVocabularyIdsCsv = "v_a0_28_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_28_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            titleUzbek = "Must vs Must Not",
            explanationUzbek = "Must majburiyat, must not esa taqiqni bildiradi.",
            exampleEnglish = "You must not go there.",
            exampleUzbek = "U yerga borishingiz mumkin emas.",
            associatedVocabularyIdsCsv = "v_a0_28_01",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 29 grammar rules (May)
        GrammarRuleEntity(
            id = "g_a0_29_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            titleUzbek = "May Permission",
            explanationUzbek = "May — ruxsat so'rash va berish (odobliroq).",
            exampleEnglish = "May I go to the shop?",
            exampleUzbek = "Do'konga borsam maylimi?",
            associatedVocabularyIdsCsv = "v_a0_29_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_29_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            titleUzbek = "May vs Can",
            explanationUzbek = "May can ga nisbatan rasmiyroq va odobliroq.",
            exampleEnglish = "May I ask a question?",
            exampleUzbek = "Savol so'rasam maylimi?",
            associatedVocabularyIdsCsv = "v_a0_29_01",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 30 grammar rules (Have to)
        GrammarRuleEntity(
            id = "g_a0_30_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            titleUzbek = "Have to External Obligation",
            explanationUzbek = "Have to — tashqi sababga ko'ra majburiyat. He/she/it bilan has to.",
            exampleEnglish = "I have to go to the shop.",
            exampleUzbek = "Do'konga borishim kerak (tashqi sabab).",
            associatedVocabularyIdsCsv = "v_a0_30_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_30_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            titleUzbek = "Do not have to (No Obligation)",
            explanationUzbek = "Do not have to — majburiyat yo'q (shart emas).",
            exampleEnglish = "I do not have to go.",
            exampleUzbek = "Borishim shart emas.",
            associatedVocabularyIdsCsv = "v_a0_30_01",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 31 grammar rules (Big — Sifat oddiy daraja)
        GrammarRuleEntity(
            id = "g_a0_31_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            titleUzbek = "Sifat va Qanday (How) savoli",
            explanationUzbek = "Sifat narsaning belgisini bildiradi: 'Qanday?' (How?) savoliga javob beradi.",
            exampleEnglish = "The book is big.",
            exampleUzbek = "Kitob katta.",
            associatedVocabularyIdsCsv = "v_a0_31_01,v_a0_31_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_31_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            titleUzbek = "Sifat bilan gap modellari",
            explanationUzbek = "Narsa + is/are + sifat. Bitta narsa -> is, ko'p narsa -> are. Darak, inkor, savol.",
            exampleEnglish = "The apples are sweet.",
            exampleUzbek = "Olmalar shirin.",
            associatedVocabularyIdsCsv = "v_a0_31_03",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 32 grammar rules (Bigger — Qiyosiy daraja)
        GrammarRuleEntity(
            id = "g_a0_32_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            titleUzbek = "Qiyosiy daraja — -er va more",
            explanationUzbek = "Bir bo'g'inli sifatlarga -er qo'shiladi (bigger, faster). Ikki va undan ortiq bo'g'inli sifatlar oldiga more qo'yiladi (more beautiful, more interesting).",
            exampleEnglish = "She is more beautiful than her sister.",
            exampleUzbek = "U singlisidan chiroyliroq.",
            associatedVocabularyIdsCsv = "v_a0_32_01,v_a0_32_03,v_a0_32_05",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_32_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            titleUzbek = "Solishtirish bog'lovchisi — than",
            explanationUzbek = "Ikki narsani taqqoslashda 'than' (-dan ko'ra) ishlatiladi: bigger than, more beautiful than.",
            exampleEnglish = "The book is bigger than the cup.",
            exampleUzbek = "Kitob piyoladan kattaroq.",
            associatedVocabularyIdsCsv = "v_a0_32_01,v_a0_32_07",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 33 grammar rules (The biggest — Orttirma daraja)
        GrammarRuleEntity(
            id = "g_a0_33_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            titleUzbek = "Orttirma daraja — the + -est va the most",
            explanationUzbek = "Bir bo'g'inli sifatlarga the + -est (the biggest, the fastest), ko'p bo'g'inli sifatlar oldiga the most (the most beautiful) qo'yiladi.",
            exampleEnglish = "She is the most beautiful girl.",
            exampleUzbek = "U eng chiroyli qiz.",
            associatedVocabularyIdsCsv = "v_a0_33_01,v_a0_33_03,v_a0_33_05",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_33_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            titleUzbek = "Orttirma darajada 'the' artiklining shartligi",
            explanationUzbek = "Orttirma darajada 'the' har doim ishlatiladi: the biggest, the most beautiful. Darak, savol va inkor shakllari mavjud.",
            exampleEnglish = "The book is the biggest.",
            exampleUzbek = "Kitob eng katta.",
            associatedVocabularyIdsCsv = "v_a0_33_01,v_a0_33_07",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 34 grammar rules (I do my homework — “Do” qilmoq)
        GrammarRuleEntity(
            id = "g_a0_34_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            titleUzbek = "Do fe'li — qilmoq (asosiy fe'l)",
            explanationUzbek = "Do mustaqil ravishda 'qilmoq' ma'nosida ishlatiladi: I do my homework.",
            exampleEnglish = "I do my homework.",
            exampleUzbek = "Men uy vazifamni qilaman.",
            associatedVocabularyIdsCsv = "v_a0_34_01,v_a0_34_03,v_a0_34_04",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_34_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            titleUzbek = "He/she/it bilan — does",
            explanationUzbek = "Uchinchi shaxs birlikda (he, she, it) 'do' fe'li 'does' ga aylanadi: He does his homework.",
            exampleEnglish = "He does his homework.",
            exampleUzbek = "U uy vazifasini qiladi.",
            associatedVocabularyIdsCsv = "v_a0_34_02,v_a0_34_05",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 35 grammar rules (Do you like...? — “Do” savolda)
        GrammarRuleEntity(
            id = "g_a0_35_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            titleUzbek = "Do va Does yordamchi fe'llari so'roq gapda",
            explanationUzbek = "Savolda Do (I/you/we/they) yoki Does (he/she/it) gapning boshida keladi. Asosiy fe'l o'zgarmaydi.",
            exampleEnglish = "Do you like apples?",
            exampleUzbek = "Olmalarni yaxshi ko'rasizmi?",
            associatedVocabularyIdsCsv = "v_a0_35_01,v_a0_35_02,v_a0_35_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_35_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            titleUzbek = "Qisqa tasdiq va inkor javoblar",
            explanationUzbek = "Do you...? savoliga 'Yes, I do' yoki 'No, I do not' deb javob beriladi.",
            exampleEnglish = "Yes, I do.",
            exampleUzbek = "Ha, yaxshi ko'raman.",
            associatedVocabularyIdsCsv = "v_a0_35_04,v_a0_35_05",
            isStudied = false,
            isMastered = false
        ),

        // Lesson 36 grammar rules (I do not like... — “Do” inkorda)
        GrammarRuleEntity(
            id = "g_a0_36_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            titleUzbek = "Inkor shakli — do not va does not",
            explanationUzbek = "I/you/we/they uchun do not, he/she/it uchun does not ishlatiladi. Asosiy fe'l o'zgarmaydi.",
            exampleEnglish = "I do not like apples.",
            exampleUzbek = "Men olmalarni yaxshi ko'rmayman.",
            associatedVocabularyIdsCsv = "v_a0_36_01,v_a0_36_02,v_a0_36_05",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_36_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            titleUzbek = "Qisqartirilgan inkor shakli — don't va doesn't",
            explanationUzbek = "So'zlashuvda do not -> don't, does not -> doesn't ko'rinishida qisqaradi.",
            exampleEnglish = "He doesn’t like cats.",
            exampleUzbek = "U mushuklarni yaxshi ko'rmaydi.",
            associatedVocabularyIdsCsv = "v_a0_36_03,v_a0_36_04,v_a0_36_06",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // Dars 1 exercises
        ExerciseEntity(
            id = "ex_a0_01_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "___ to TalkMe!",
            subPromptText = "TalkMe ilovasiga xush kelibsiz! Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "Welcome to TalkMe!",
            optionsPipeSeparated = "Welcome|Stop|Wait",
            correctAnswer = "Welcome",
            explanationUzbek = "'Welcome to TalkMe!' — TalkMe ilovasiga xush kelibsiz!",
            vocabularyIdsCsv = "v_a0_01_01",
            grammarRuleIdsCsv = "g_a0_01_01"
        ),
        ExerciseEntity(
            id = "ex_a0_01_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Here you ___ English.",
            subPromptText = "Bu yerda siz ingliz tilini o‘rganasiz. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "Here you learn English.",
            optionsPipeSeparated = "close|learn|sleep",
            correctAnswer = "learn",
            explanationUzbek = "'learn' — o‘rganmoq degan ma’noni bildiradi.",
            vocabularyIdsCsv = "v_a0_01_02",
            grammarRuleIdsCsv = ""
        ),
        ExerciseEntity(
            id = "ex_a0_01_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Every ___ is part of learning.",
            subPromptText = "Har bir xato — bu o‘rganishning bir qismi. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "Every mistake is part of learning.",
            optionsPipeSeparated = "table|game|mistake",
            correctAnswer = "mistake",
            explanationUzbek = "'mistake' — xato. Xato qilsangiz, xafa bo‘lmang!",
            vocabularyIdsCsv = "v_a0_01_03",
            grammarRuleIdsCsv = "g_a0_01_02"
        ),
        // Dars 2 exercises (10 exercises)
        ExerciseEntity(
            id = "ex_a0_02_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "TalkMe kim bilan suhbatlashdi?",
            subPromptText = "1-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hello, Lera! Welcome to TalkMe!",
            optionsPipeSeparated = "Aziz|Lera|Teacher",
            correctAnswer = "Lera",
            explanationUzbek = "TalkMe Lera bilan suhbatlashdi: 'Hello, Lera!'",
            vocabularyIdsCsv = "v_a0_02_03",
            grammarRuleIdsCsv = "g_a0_02_01"
        ),
        ExerciseEntity(
            id = "ex_a0_02_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Lera ilovaga kirganda nima dedi?",
            subPromptText = "2-mashq. Suhbatdan eslang:",
            audioTextToSpeak = "Hi! Thank you.",
            optionsPipeSeparated = "Good bye|Hi! Thank you.|Sorry",
            correctAnswer = "Hi! Thank you.",
            explanationUzbek = "Lera salomlashishda 'Hi! Thank you.' dedi.",
            vocabularyIdsCsv = "v_a0_02_02,v_a0_02_07",
            grammarRuleIdsCsv = "g_a0_02_01"
        ),
        ExerciseEntity(
            id = "ex_a0_02_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Ismini aytish uchun Lera nima dedi?",
            subPromptText = "3-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My name is Lera.",
            optionsPipeSeparated = "I love English|My name is Lera|Welcome",
            correctAnswer = "My name is Lera",
            explanationUzbek = "Ismni aytish uchun 'My name is Lera' deyiladi.",
            vocabularyIdsCsv = "v_a0_02_05",
            grammarRuleIdsCsv = "g_a0_02_02"
        ),
        ExerciseEntity(
            id = "ex_a0_02_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Tushunmaganda Lera nima dedi?",
            subPromptText = "4-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sorry, what?",
            optionsPipeSeparated = "Hello|Sorry, what?|Good bye",
            correctAnswer = "Sorry, what?",
            explanationUzbek = "Tushunmaganda 'Sorry, what?' deb qayta so‘raladi.",
            vocabularyIdsCsv = "v_a0_02_06",
            grammarRuleIdsCsv = "g_a0_02_03"
        ),
        ExerciseEntity(
            id = "ex_a0_02_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "“___ to TalkMe!” · “___ your name?” · “My ___ is Lera.”\n“___ you!” · “I ___ English!” · “Good ___!”",
            subPromptText = "5-mashq. Bo‘sh joylar uchun to‘g‘ri so‘zlarni tanlang:",
            audioTextToSpeak = "Welcome to TalkMe! What’s your name? My name is Lera. Thank you! I love English! Good bye!",
            optionsPipeSeparated = "Welcome / What’s / name / Thank / love / bye|Hello / Hi / is / Sorry / like / day|Hi / Who / your / Nice / learn / night",
            correctAnswer = "Welcome / What’s / name / Thank / love / bye",
            explanationUzbek = "To‘g‘ri javob: Welcome / What’s / name / Thank / love / bye.",
            vocabularyIdsCsv = "v_a0_02_03,v_a0_02_04,v_a0_02_08,v_a0_02_09",
            grammarRuleIdsCsv = "g_a0_02_02"
        ),
        ExerciseEntity(
            id = "ex_a0_02_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) Hello   2) Sorry   3) Thank you   4) Good bye\na) xayr   b) salom (rasmiy)   c) kechirasiz   d) rahmat",
            subPromptText = "6-mashq. Juftlang (to‘g‘ri moslikni tanlang):",
            audioTextToSpeak = "Hello. Sorry. Thank you. Good bye.",
            optionsPipeSeparated = "1-b, 2-c, 3-d, 4-a|1-a, 2-d, 3-c, 4-b|1-c, 2-b, 3-a, 4-d",
            correctAnswer = "1-b, 2-c, 3-d, 4-a",
            explanationUzbek = "1-b (Hello = rasmiy salom), 2-c (Sorry = kechirasiz), 3-d (Thank you = rahmat), 4-a (Good bye = xayr).",
            vocabularyIdsCsv = "v_a0_02_01,v_a0_02_06,v_a0_02_07,v_a0_02_09",
            grammarRuleIdsCsv = "g_a0_02_01"
        ),
        ExerciseEntity(
            id = "ex_a0_02_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "TalkMe: ___! Welcome to TalkMe!\nLera: ___! Thank you.\nTalkMe: What’s your ___?\nLera: My ___ is Lera.\nTalkMe: Thank you! Good ___!",
            subPromptText = "7-mashq. Suhbatni to‘ldiring (Lera va TalkMe):",
            audioTextToSpeak = "Hello! Welcome to TalkMe! Hi! Thank you. What’s your name? My name is Lera. Thank you! Good bye!",
            optionsPipeSeparated = "Hello / Hi / name / name / bye|Bye / Sorry / English / hello / love|Hi / Good / Lera / sorry / what",
            correctAnswer = "Hello / Hi / name / name / bye",
            explanationUzbek = "Suhbat tartibi: Hello / Hi / name / name / bye.",
            vocabularyIdsCsv = "v_a0_02_01,v_a0_02_02,v_a0_02_04,v_a0_02_05,v_a0_02_09",
            grammarRuleIdsCsv = "g_a0_02_02"
        ),
        ExerciseEntity(
            id = "ex_a0_02_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Ismni aytishda qaysi gap tartibi to‘g‘ri?",
            subPromptText = "8-mashq. To‘g‘ri tartibni tanlang:",
            audioTextToSpeak = "My name is Lera.",
            optionsPipeSeparated = "Name my is Lera.|My name is Lera.|Is my name Lera.",
            correctAnswer = "My name is Lera.",
            explanationUzbek = "To‘g‘ri tartib: My + name + is + [ism].",
            vocabularyIdsCsv = "v_a0_02_05",
            grammarRuleIdsCsv = "g_a0_02_02"
        ),
        ExerciseEntity(
            id = "ex_a0_02_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "“Hello! Hi! What’s your name? My name is Lera. Sorry, what? Thank you! Good bye!”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "Hello! Hi! What’s your name? My name is Lera. Sorry, what? Thank you! Good bye!",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Ovoz chiqarib takrorlash talaffuzni mustahkamlaydi.",
            vocabularyIdsCsv = "v_a0_02_01,v_a0_02_04,v_a0_02_06",
            grammarRuleIdsCsv = "g_a0_02_03"
        ),
        ExerciseEntity(
            id = "ex_a0_02_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Ilovaga kirish: “___ to TalkMe!” · Tanishuv: “___ your name?” — “___ name is ___.” · Xayrlashuv: “Good ___!”",
            subPromptText = "10-mashq. O‘zingiz yozing (Lera o‘rnida o‘zingiz):",
            audioTextToSpeak = "Welcome to TalkMe! What’s your name? My name is Azamat. Good bye!",
            optionsPipeSeparated = "Welcome / What’s / My / bye|Hello / Who / Your / morning|Hi / What / I / night",
            correctAnswer = "Welcome / What’s / My / bye",
            explanationUzbek = "Ajoyib! Siz o‘zingizni ingliz tilida bemalol tanishtira olasiz.",
            vocabularyIdsCsv = "v_a0_02_03,v_a0_02_04,v_a0_02_05,v_a0_02_09",
            grammarRuleIdsCsv = "g_a0_02_02"
        ),
        // Lesson 3 exercises (10 exercises)
        ExerciseEntity(
            id = "ex_a0_03_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Apple” — unlimi yoki undoshmi?",
            subPromptText = "1-mashq. Birinchi harfga qarang (a, e, i, o, u):",
            audioTextToSpeak = "an apple",
            optionsPipeSeparated = "unli|undosh",
            correctAnswer = "unli",
            explanationUzbek = "'Apple' so‘zi 'a' (unli) harfi bilan boshlanadi, shuning uchun 'an apple' bo‘ladi.",
            vocabularyIdsCsv = "v_a0_03_06",
            grammarRuleIdsCsv = "g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Cup” — unlimi yoki undoshmi?",
            subPromptText = "2-mashq. Birinchi harfga qarang:",
            audioTextToSpeak = "a cup",
            optionsPipeSeparated = "unli|undosh",
            correctAnswer = "undosh",
            explanationUzbek = "'Cup' so‘zi 'c' (undosh) harfi bilan boshlanadi, shuning uchun 'a cup' bo‘ladi.",
            vocabularyIdsCsv = "v_a0_03_01",
            grammarRuleIdsCsv = "g_a0_03_01"
        ),
        ExerciseEntity(
            id = "ex_a0_03_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ apple · ___ cup · ___ egg · ___ book\n___ orange · ___ cat · ___ umbrella · ___ pen",
            subPromptText = "3-mashq. To‘g‘ri variantni (a yoki an) tanlang:",
            audioTextToSpeak = "an apple, a cup, an egg, a book, an orange, a cat, an umbrella, a pen",
            optionsPipeSeparated = "an / a / an / a / an / a / an / a|a / an / a / an / a / an / a / an|an / an / a / a / an / a / an / a",
            correctAnswer = "an / a / an / a / an / a / an / a",
            explanationUzbek = "apple/egg/orange/umbrella — unli bilan (an); cup/book/cat/pen — undosh bilan (a).",
            vocabularyIdsCsv = "v_a0_03_01,v_a0_03_02,v_a0_03_06,v_a0_03_07",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "apple · cup · egg · book\norange · dog · elephant · pen",
            subPromptText = "4-mashq. Unlimi yoki undoshmi?",
            audioTextToSpeak = "apple, cup, egg, book, orange, dog, elephant, pen",
            optionsPipeSeparated = "unli / undosh / unli / undosh / unli / undosh / unli / undosh|undosh / unli / undosh / unli / undosh / unli / undosh / unli|unli / unli / undosh / undosh / unli / undosh / unli / undosh",
            correctAnswer = "unli / undosh / unli / undosh / unli / undosh / unli / undosh",
            explanationUzbek = "Unlilar: apple, egg, orange, elephant. Undoshlar: cup, book, dog, pen.",
            vocabularyIdsCsv = "v_a0_03_05,v_a0_03_08,v_a0_03_10",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "dog · egg · cat · umbrella\nbook · orange · pen · apple",
            subPromptText = "5-mashq. To‘g‘ri variantni tanlang (a / an):",
            audioTextToSpeak = "a dog, an egg, a cat, an umbrella, a book, an orange, a pen, an apple",
            optionsPipeSeparated = "a / an / a / an / a / an / a / an|an / a / an / a / an / a / an / a|a / a / an / an / a / an / a / a",
            correctAnswer = "a / an / a / an / a / an / a / an",
            explanationUzbek = "a dog, an egg, a cat, an umbrella, a book, an orange, a pen, an apple.",
            vocabularyIdsCsv = "v_a0_03_03,v_a0_03_04,v_a0_03_05,v_a0_03_09",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "1) a       a) unli bilan\n2) an      b) undosh bilan",
            subPromptText = "6-mashq. Juftlang:",
            audioTextToSpeak = "A book. An apple.",
            optionsPipeSeparated = "1-b, 2-a|1-a, 2-b",
            correctAnswer = "1-b, 2-a",
            explanationUzbek = "1-b: 'a' undosh bilan boshlanadigan so‘zlar oldida; 2-a: 'an' unli bilan boshlanadigan so‘zlar oldida.",
            vocabularyIdsCsv = "v_a0_03_02,v_a0_03_06",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "an cup · a apple · an book\na egg · an pen · a orange",
            subPromptText = "7-mashq. Xatoni toping va to‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "a cup, an apple, a book, an egg, a pen, an orange",
            optionsPipeSeparated = "a cup / an apple / a book / an egg / a pen / an orange|an cup / a apple / a book / an egg / an pen / a orange|a cup / a apple / an book / a egg / a pen / an orange",
            correctAnswer = "a cup / an apple / a book / an egg / a pen / an orange",
            explanationUzbek = "To‘g‘rilangan shakllar: a cup, an apple, a book, an egg, a pen, an orange.",
            vocabularyIdsCsv = "v_a0_03_01,v_a0_03_02,v_a0_03_04,v_a0_03_06,v_a0_03_07,v_a0_03_08",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "cup, apple, egg, book, orange, cat, umbrella, pen, elephant, dog",
            subPromptText = "8-mashq. Faqat unli bilan boshlanadigan so‘zlarni tanlang:",
            audioTextToSpeak = "apple, egg, orange, umbrella, elephant",
            optionsPipeSeparated = "apple, egg, orange, umbrella, elephant|cup, book, cat, pen, dog|apple, cup, egg, umbrella, dog",
            correctAnswer = "apple, egg, orange, umbrella, elephant",
            explanationUzbek = "Unli (a, e, i, o, u) bilan boshlanadigan so‘zlar: apple, egg, orange, umbrella, elephant.",
            vocabularyIdsCsv = "v_a0_03_06,v_a0_03_07,v_a0_03_08,v_a0_03_09,v_a0_03_10",
            grammarRuleIdsCsv = "g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“A cup. An apple. A book. An egg. A cat. An orange.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "A cup. An apple. A book. An egg. A cat. An orange.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Ajoyib! 'a' va 'an' ni so‘zlar bilan ravon talaffuz qilyapsiz.",
            vocabularyIdsCsv = "v_a0_03_01,v_a0_03_02,v_a0_03_06,v_a0_03_07",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        ExerciseEntity(
            id = "ex_a0_03_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Uyingizdagi 3 ta narsani a yoki an bilan yozing:\nMasalan: a cup · an apple · a book",
            subPromptText = "10-mashq. O‘zingiz yozing (3 ta narsa):",
            audioTextToSpeak = "a cup, an apple, a book",
            optionsPipeSeparated = "a cup / an apple / a book|an cup / a apple / an book|cup / apple / book",
            correctAnswer = "a cup / an apple / a book",
            explanationUzbek = "Barakalla! Bitta narsa oldidan a (undosh) yoki an (unli) qo‘yishni to‘liq o‘zlashtirdingiz.",
            vocabularyIdsCsv = "v_a0_03_01,v_a0_03_02,v_a0_03_06",
            grammarRuleIdsCsv = "g_a0_03_01,g_a0_03_02"
        ),
        // Lesson 4 exercises (10 exercises)
        ExerciseEntity(
            id = "ex_a0_04_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“And” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "a book and a cup",
            optionsPipeSeparated = "yoki|va|bilan",
            correctAnswer = "va",
            explanationUzbek = "‘And’ — bu “va” degani: a book and a cup (kitob va piyola).",
            vocabularyIdsCsv = "v_a0_04_01",
            grammarRuleIdsCsv = "g_a0_04_01"
        ),
        ExerciseEntity(
            id = "ex_a0_04_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Book” — unlimi yoki undoshmi?",
            subPromptText = "2-mashq. Birinchi harfga qarang:",
            audioTextToSpeak = "a book",
            optionsPipeSeparated = "unli|undosh",
            correctAnswer = "undosh",
            explanationUzbek = "‘Book’ so‘zi ‘b’ (undosh) harfi bilan boshlanadi → a book.",
            vocabularyIdsCsv = "v_a0_04_02",
            grammarRuleIdsCsv = "g_a0_04_02"
        ),
        ExerciseEntity(
            id = "ex_a0_04_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Apple” — unlimi yoki undoshmi?",
            subPromptText = "3-mashq. Birinchi harfga qarang (a, e, i, o, u):",
            audioTextToSpeak = "an apple",
            optionsPipeSeparated = "unli|undosh",
            correctAnswer = "unli",
            explanationUzbek = "‘Apple’ so‘zi ‘a’ (unli) harfi bilan boshlanadi → an apple.",
            vocabularyIdsCsv = "v_a0_04_05",
            grammarRuleIdsCsv = "g_a0_04_02"
        ),
        ExerciseEntity(
            id = "ex_a0_04_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ book and ___ cup · ___ apple and ___ egg\n___ cat and ___ dog · ___ orange and ___ umbrella\n___ pen and ___ book · ___ elephant and ___ cat",
            subPromptText = "4-mashq. Har bir narsa oldiga to‘g‘ri a/an variantini tanlang:",
            audioTextToSpeak = "a book and a cup. an apple and an egg. a cat and a dog. an orange and an umbrella. a pen and a book. an elephant and a cat.",
            optionsPipeSeparated = "a / a · an / an · a / a · an / an · a / a · an / a|an / a · a / an · a / an · a / a · an / a · a / an|a / an · an / a · an / a · an / an · a / a · an / an",
            correctAnswer = "a / a · an / an · a / a · an / an · a / a · an / a",
            explanationUzbek = "Har bir narsaning o‘z a/an artikli bor: a book and a cup, an apple and an egg, a cat and a dog, an orange and an umbrella, a pen and a book, an elephant and a cat.",
            vocabularyIdsCsv = "v_a0_04_02,v_a0_04_03,v_a0_04_04,v_a0_04_05,v_a0_04_06,v_a0_04_07",
            grammarRuleIdsCsv = "g_a0_04_02"
        ),
        ExerciseEntity(
            id = "ex_a0_04_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "a book ___ a cup\nan apple ___ an egg\na cat ___ a dog",
            subPromptText = "5-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "a book and a cup. an apple and an egg. a cat and a dog.",
            optionsPipeSeparated = "and|or|with",
            correctAnswer = "and",
            explanationUzbek = "Uchala gapda ham ikkita narsani bog‘lash uchun ‘and’ (“va”) qo‘yiladi.",
            vocabularyIdsCsv = "v_a0_04_01,v_a0_04_02,v_a0_04_03,v_a0_04_05",
            grammarRuleIdsCsv = "g_a0_04_01"
        ),
        ExerciseEntity(
            id = "ex_a0_04_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "an book and a cup · a apple and a egg\na cat or a dog · an orange and a umbrella",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "a book and a cup, an apple and an egg, a cat and a dog, an orange and an umbrella",
            optionsPipeSeparated = "a book and a cup · an apple and an egg · a cat and a dog · an orange and an umbrella|an book and a cup · an apple and an egg · a cat and a dog · a orange and an umbrella|a book and an cup · a apple and an egg · a cat or a dog · an orange and an umbrella",
            correctAnswer = "a book and a cup · an apple and an egg · a cat and a dog · an orange and an umbrella",
            explanationUzbek = "To‘g‘rilangan shakllar: a book and a cup, an apple and an egg, a cat and a dog, an orange and an umbrella.",
            vocabularyIdsCsv = "v_a0_04_02,v_a0_04_03,v_a0_04_05,v_a0_04_06",
            grammarRuleIdsCsv = "g_a0_04_01,g_a0_04_02"
        ),
        ExerciseEntity(
            id = "ex_a0_04_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "1) a       a) unli bilan\n2) an      b) undosh bilan\n3) and     c) “va”",
            subPromptText = "7-mashq. Juftlang:",
            audioTextToSpeak = "A book and an apple.",
            optionsPipeSeparated = "1-b, 2-a, 3-c|1-a, 2-b, 3-c|1-c, 2-a, 3-b",
            correctAnswer = "1-b, 2-a, 3-c",
            explanationUzbek = "1-b (a → undosh bilan), 2-a (an → unli bilan), 3-c (and → “va”).",
            vocabularyIdsCsv = "v_a0_04_01",
            grammarRuleIdsCsv = "g_a0_04_01,g_a0_04_02"
        ),
        ExerciseEntity(
            id = "ex_a0_04_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "book, apple, egg, cup, orange, cat, umbrella, pen",
            subPromptText = "8-mashq. Faqat an bilan keladigan so‘zlarni belgilang:",
            audioTextToSpeak = "an apple, an egg, an orange, an umbrella",
            optionsPipeSeparated = "apple, egg, orange, umbrella|book, cup, cat, pen|apple, book, orange, umbrella",
            correctAnswer = "apple, egg, orange, umbrella",
            explanationUzbek = "Unli harflar (a, e, o, u) bilan boshlangani uchun: an apple, an egg, an orange, an umbrella.",
            vocabularyIdsCsv = "v_a0_04_05,v_a0_04_06",
            grammarRuleIdsCsv = "g_a0_04_02"
        ),
        ExerciseEntity(
            id = "ex_a0_04_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“A book and a cup. An apple and an egg. A cat and a dog.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "A book and a cup. An apple and an egg. A cat and a dog.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Har bir narsa oldida a/an ni aniq aytib bog‘layapsiz.",
            vocabularyIdsCsv = "v_a0_04_02,v_a0_04_03,v_a0_04_05",
            grammarRuleIdsCsv = "g_a0_04_01"
        ),
        ExerciseEntity(
            id = "ex_a0_04_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Uyingizdagi 2 ta narsani “and” bilan yozing:\n• ______ and ______\n• ______ and ______",
            subPromptText = "10-mashq. O‘zingiz yozing (uyingizdagi 2 ta narsa):",
            audioTextToSpeak = "a book and a cup. an apple and an egg.",
            optionsPipeSeparated = "a book and a cup · an apple and an egg|an book and a cup · a apple and an egg|book and cup · apple and egg",
            correctAnswer = "a book and a cup · an apple and an egg",
            explanationUzbek = "Ajoyib! Endi siz ikkita narsani o‘z a/an artikli va ‘and’ bilan bemalol bog‘lay olasiz.",
            vocabularyIdsCsv = "v_a0_04_02,v_a0_04_05",
            grammarRuleIdsCsv = "g_a0_04_01,g_a0_04_02"
        ),
        // Lesson 5 exercises (10 exercises)
        ExerciseEntity(
            id = "ex_a0_05_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“This” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "this book",
            optionsPipeSeparated = "o‘sha|bu",
            correctAnswer = "bu",
            explanationUzbek = "‘This’ — bu “bu” degani (qo‘lingizdagi, yaqin joydagi narsa).",
            vocabularyIdsCsv = "v_a0_05_01",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“That” nima degani?",
            subPromptText = "2-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "that cup",
            optionsPipeSeparated = "o‘sha|bu",
            correctAnswer = "o‘sha",
            explanationUzbek = "‘That’ — bu “o‘sha” degani (narigi stoldagi, uzoqroqdagi narsa).",
            vocabularyIdsCsv = "v_a0_05_02",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "___ book (qo‘lingizda) · ___ cup (narigi stolda)\n___ apple (qo‘lingizda) · ___ cat (uzoqroqda)",
            subPromptText = "3-mashq. To‘g‘ri variantni tanlang (this / that):",
            audioTextToSpeak = "this book, that cup, this apple, that cat",
            optionsPipeSeparated = "this / that / this / that|that / this / that / this|this / this / that / that",
            correctAnswer = "this / that / this / that",
            explanationUzbek = "Yaqin (qo‘lingizda) → this; uzoqroq (narigi stolda) → that.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_05,v_a0_05_07",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "this a book / this book · that an apple / that apple\nthis a cup / this cup · that a cat / that cat",
            subPromptText = "4-mashq. To‘g‘ri variantlarni tanlang (a/an qo‘yiladimi?):",
            audioTextToSpeak = "this book, that apple, this cup, that cat",
            optionsPipeSeparated = "this book · that apple · this cup · that cat|this a book · that an apple · this a cup · that a cat|this a book · that apple · this cup · that a cat",
            correctAnswer = "this book · that apple · this cup · that cat",
            explanationUzbek = "this va that kelganda a / an aytilmaydi: this book, that apple, this cup, that cat.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_04,v_a0_05_08",
            grammarRuleIdsCsv = "g_a0_05_02"
        ),
        ExerciseEntity(
            id = "ex_a0_05_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "___ book (bu) · ___ cup (o‘sha)\n___ apple (bu) · ___ egg (o‘sha)",
            subPromptText = "5-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "this book, that cup, this apple, that egg",
            optionsPipeSeparated = "this / that / this / that|that / this / that / this|a / an / a / an",
            correctAnswer = "this / that / this / that",
            explanationUzbek = "“bu” → this; “o‘sha” → that.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_05,v_a0_05_07",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "this a book · that an apple\na this cup · an that egg",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "this book, that apple, this cup, that egg",
            optionsPipeSeparated = "this book · that apple · this cup · that egg|this a book · that apple · a this cup · that egg|this book · that an apple · this cup · an that egg",
            correctAnswer = "this book · that apple · this cup · that egg",
            explanationUzbek = "this va that bilan a/an ishlatilmaydi: this book, that apple, this cup, that egg.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_04,v_a0_05_08",
            grammarRuleIdsCsv = "g_a0_05_02"
        ),
        ExerciseEntity(
            id = "ex_a0_05_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "1) this    a) o‘sha\n2) that    b) bu\n3) and     c) “va”",
            subPromptText = "7-mashq. Juftlang:",
            audioTextToSpeak = "this book and that cup",
            optionsPipeSeparated = "1-b, 2-a, 3-c|1-a, 2-b, 3-c|1-c, 2-a, 3-b",
            correctAnswer = "1-b, 2-a, 3-c",
            explanationUzbek = "1-b (this = bu), 2-a (that = o‘sha), 3-c (and = “va”).",
            vocabularyIdsCsv = "v_a0_05_01,v_a0_05_02",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ book and ___ cup (ikkisi ham yaqin)\n___ apple and ___ egg (ikkisi ham uzoqroq)",
            subPromptText = "8-mashq. Masofaga qarab to‘ldiring:",
            audioTextToSpeak = "this book and this cup. that apple and that egg.",
            optionsPipeSeparated = "this / this · that / that|that / that · this / this|this / that · this / that",
            correctAnswer = "this / this · that / that",
            explanationUzbek = "Ikkisi ham yaqin → this book and this cup; ikkisi ham uzoqroq → that apple and that egg.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_04,v_a0_05_08",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“This book. That cup. This apple. That egg.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "This book. That cup. This apple. That egg.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! This va that bilan a/an qo‘ymay ravon talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_05,v_a0_05_07",
            grammarRuleIdsCsv = "g_a0_05_01,g_a0_05_02"
        ),
        ExerciseEntity(
            id = "ex_a0_05_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Yoningizdagi va uzoqroqdagi 2 ta narsani yozing:\n• this ______ (yaqin)\n• that ______ (uzoqroq)",
            subPromptText = "10-mashq. O‘zingiz yozing (this / that):",
            audioTextToSpeak = "this book. that cup.",
            optionsPipeSeparated = "this book · that cup|this a book · that a cup|book · cup",
            correctAnswer = "this book · that cup",
            explanationUzbek = "Ajoyib! Endi yaqin (this) va uzoqroq (that) narsalarni bemalol ko‘rsata olasiz.",
            vocabularyIdsCsv = "v_a0_05_03,v_a0_05_07",
            grammarRuleIdsCsv = "g_a0_05_01,g_a0_05_02"
        ),
        // Lesson 6 exercises (11 exercises)
        ExerciseEntity(
            id = "ex_a0_06_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“These” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "these books",
            optionsPipeSeparated = "o‘shalar|bular",
            correctAnswer = "bular",
            explanationUzbek = "‘These’ — bu “bular” degani (yaqin joydagi ko‘p narsa).",
            vocabularyIdsCsv = "v_a0_06_01",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Those” nima degani?",
            subPromptText = "2-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "those cups",
            optionsPipeSeparated = "o‘shalar|bular",
            correctAnswer = "o‘shalar",
            explanationUzbek = "‘Those’ — bu “o‘shalar” degani (uzoqroqdagi ko‘p narsa).",
            vocabularyIdsCsv = "v_a0_06_02",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“These” qanday narsa uchun ishlatiladi?",
            subPromptText = "3-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "these apples",
            optionsPipeSeparated = "uzoqroqdagi ko‘p narsa|yaqin joydagi ko‘p narsa",
            correctAnswer = "yaqin joydagi ko‘p narsa",
            explanationUzbek = "‘These’ — qo‘lingizda yoki yaqin joydagi ko‘p narsalar uchun.",
            vocabularyIdsCsv = "v_a0_06_01,v_a0_06_05",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ books (qo‘lingizda) · ___ cups (narigi stolda)\n___ apples (qo‘lingizda) · ___ cats (uzoqroqda)",
            subPromptText = "4-mashq. To‘g‘ri variantni tanlang (these / those):",
            audioTextToSpeak = "these books, those cups, these apples, those cats",
            optionsPipeSeparated = "these / those / these / those|those / these / those / these|this / that / this / that",
            correctAnswer = "these / those / these / those",
            explanationUzbek = "Yaqin ko‘p narsa → these; uzoqroq ko‘p narsa → those.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_05,v_a0_06_07",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "book · cup · apple · cat · egg · pen",
            subPromptText = "5-mashq. Ko‘plik shaklini (-s) tanlang:",
            audioTextToSpeak = "books, cups, apples, cats, eggs, pens",
            optionsPipeSeparated = "books, cups, apples, cats, eggs, pens|book, cup, apple, cat, egg, pen|a books, a cups, an apples, a cats, an eggs, a pens",
            correctAnswer = "books, cups, apples, cats, eggs, pens",
            explanationUzbek = "Ko‘p narsada so‘z oxiriga -s qo‘shiladi: books, cups, apples, cats, eggs, pens.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_04,v_a0_06_05",
            grammarRuleIdsCsv = "g_a0_06_02"
        ),
        ExerciseEntity(
            id = "ex_a0_06_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "these a books / these books · those an apples / those apples\nthese a cups / these cups · those a cats / those cats",
            subPromptText = "6-mashq. To‘g‘ri variantni tanlang:",
            audioTextToSpeak = "these books, those apples, these cups, those cats",
            optionsPipeSeparated = "these books · those apples · these cups · those cats|these a books · those an apples · these a cups · those a cats|this books · that apples · this cups · that cats",
            correctAnswer = "these books · those apples · these cups · those cats",
            explanationUzbek = "these va those bilan a / an ishlatilmaydi, so‘z oxirida esa -s bo‘ladi.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_04,v_a0_06_08",
            grammarRuleIdsCsv = "g_a0_06_02"
        ),
        ExerciseEntity(
            id = "ex_a0_06_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "___ books (bular) · ___ cups (o‘shalar)\n___ apples (bular) · ___ eggs (o‘shalar)",
            subPromptText = "7-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "these books, those cups, these apples, those eggs",
            optionsPipeSeparated = "these / those / these / those|those / these / those / these|these / these / those / those",
            correctAnswer = "these / those / these / those",
            explanationUzbek = "“bular” → these; “o‘shalar” → those.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_05,v_a0_06_07",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "these a books · those an apples\nthis books · that cups",
            subPromptText = "8-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "these books, those apples, these books, those cups",
            optionsPipeSeparated = "these books · those apples · these books · those cups|these a books · those apples · this books · that cups|these books · those an apples · these book · those cup",
            correctAnswer = "these books · those apples · these books · those cups",
            explanationUzbek = "Ko‘p narsalar uchun this/that emas these/those, hamda a/an qo‘yilmaydi: these books, those apples, these books, those cups.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_06,v_a0_06_07,v_a0_06_08",
            grammarRuleIdsCsv = "g_a0_06_01,g_a0_06_02"
        ),
        ExerciseEntity(
            id = "ex_a0_06_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) this     a) o‘shalar\n2) that     b) bular\n3) these    c) bu\n4) those    d) o‘sha",
            subPromptText = "9-mashq. Barchasini juftlang:",
            audioTextToSpeak = "this, that, these, those",
            optionsPipeSeparated = "1-c, 2-d, 3-b, 4-a|1-b, 2-a, 3-c, 4-d|1-d, 2-c, 3-a, 4-b",
            correctAnswer = "1-c, 2-d, 3-b, 4-a",
            explanationUzbek = "1-c (this = bu), 2-d (that = o‘sha), 3-b (these = bular), 4-a (those = o‘shalar).",
            vocabularyIdsCsv = "v_a0_06_01,v_a0_06_02",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“These books. Those cups. These apples. Those eggs.”",
            subPromptText = "10-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "These books. Those cups. These apples. Those eggs.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Ko‘plik -s va these/those ni aniq talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_05,v_a0_06_07",
            grammarRuleIdsCsv = "g_a0_06_01,g_a0_06_02"
        ),
        ExerciseEntity(
            id = "ex_a0_06_11",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Yaqin va uzoqdagi ko‘p narsalarni -s bilan yozing:\n• these ______ (yaqin, ko‘p)\n• those ______ (uzoqroq, ko‘p)",
            subPromptText = "11-mashq. O‘zingiz yozing (these / those + -s):",
            audioTextToSpeak = "these books. those cups.",
            optionsPipeSeparated = "these books · those cups|these a books · those a cups|these book · those cup",
            correctAnswer = "these books · those cups",
            explanationUzbek = "Tabriklaymiz! Endi these/those bilan ko‘plikda erkin yozishingiz va gapirishingiz mumkin.",
            vocabularyIdsCsv = "v_a0_06_03,v_a0_06_07",
            grammarRuleIdsCsv = "g_a0_06_01,g_a0_06_02"
        ),
        // Lesson 7 exercises (9 exercises: I have a book — Darak)
        ExerciseEntity(
            id = "ex_a0_07_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Have” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri ma’nosini tanlang:",
            audioTextToSpeak = "have",
            optionsPipeSeparated = "bormoq|bor bo‘lmoq",
            correctAnswer = "bor bo‘lmoq",
            explanationUzbek = "‘Have’ o‘zi “bor bo‘lmoq” (ega bo‘lmoq) degani; “menda bor” esa ‘I have’.",
            vocabularyIdsCsv = "v_a0_07_01",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have” nima degani?",
            subPromptText = "2-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "I have",
            optionsPipeSeparated = "menda bor|sen ega bo‘lding",
            correctAnswer = "menda bor",
            explanationUzbek = "‘I’ (men) + ‘have’ (ega bo‘lmoq) → ‘I have’ = “menda bor”.",
            vocabularyIdsCsv = "v_a0_07_02",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "I ___ a book.",
            subPromptText = "3-mashq. To‘g‘ri variantni tanlang:",
            audioTextToSpeak = "I have a book.",
            optionsPipeSeparated = "have|has|had",
            correctAnswer = "have",
            explanationUzbek = "‘I’ bilan doim ‘have’ ishlatiladi: I have a book.",
            vocabularyIdsCsv = "v_a0_07_03",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have ___ book · I have ___ apple\nI have ___ cup · I have ___ egg",
            subPromptText = "4-mashq. a / an qo‘ying:",
            audioTextToSpeak = "I have a book. I have an apple. I have a cup. I have an egg.",
            optionsPipeSeparated = "a / an / a / an|an / a / an / a|a / a / an / an",
            correctAnswer = "a / an / a / an",
            explanationUzbek = "book/cup undosh → a; apple/egg unli → an.",
            vocabularyIdsCsv = "v_a0_07_03,v_a0_07_04,v_a0_07_07,v_a0_07_08",
            grammarRuleIdsCsv = "g_a0_07_02"
        ),
        ExerciseEntity(
            id = "ex_a0_07_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I has a book · I have a apple",
            subPromptText = "5-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "I have a book. I have an apple.",
            optionsPipeSeparated = "I have a book · I have an apple|I has a book · I have an apple|I have a book · I have a apple",
            correctAnswer = "I have a book · I have an apple",
            explanationUzbek = "‘I’ bilan ‘have’ (has emas), ‘apple’ oldidan esa ‘an’ keladi.",
            vocabularyIdsCsv = "v_a0_07_03,v_a0_07_07",
            grammarRuleIdsCsv = "g_a0_07_01,g_a0_07_02"
        ),
        ExerciseEntity(
            id = "ex_a0_07_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) have / I / a / book\n2) an / have / I / apple",
            subPromptText = "6-mashq. So‘zlardan to‘g‘ri gap tuzing:",
            audioTextToSpeak = "I have a book. I have an apple.",
            optionsPipeSeparated = "I have a book · I have an apple|Have I a book · I apple have an|I a have book · An I have apple",
            correctAnswer = "I have a book · I have an apple",
            explanationUzbek = "Darak gap tartibi: I + have + a/an + ot.",
            vocabularyIdsCsv = "v_a0_07_03,v_a0_07_07",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Do‘stingiz: “Senda ruchka bormi?”\n• Onangiz: “Sumkangda nima bor?” (kitob)",
            subPromptText = "7-mashq. Hayotiy vaziyat — javob bering:",
            audioTextToSpeak = "I have a pen. I have a book.",
            optionsPipeSeparated = "I have a pen. · I have a book.|Have I got a pen? · Book I have.|I has a pen. · I has a book.",
            correctAnswer = "I have a pen. · I have a book.",
            explanationUzbek = "Do‘stingizga: I have a pen. Onangizga: I have a book.",
            vocabularyIdsCsv = "v_a0_07_03,v_a0_07_05",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have a book. I have a cup. I have an apple.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "I have a book. I have a cup. I have an apple.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Endi “I have + a/an + ot” gaplarini ravon ayta olasiz.",
            vocabularyIdsCsv = "v_a0_07_03,v_a0_07_04,v_a0_07_07",
            grammarRuleIdsCsv = "g_a0_07_01,g_a0_07_02"
        ),
        ExerciseEntity(
            id = "ex_a0_07_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "O‘zingizda bor 3 ta narsa haqida gap yozing:\n• I have ______.\n• I have ______.\n• I have ______.",
            subPromptText = "9-mashq. O‘zingiz yozing (3 ta gap):",
            audioTextToSpeak = "I have a book. I have an apple. I have a cup.",
            optionsPipeSeparated = "I have a book · I have an apple · I have a cup|I has a book · I have a apple · I have cup|Have I a book · I an apple · a cup",
            correctAnswer = "I have a book · I have an apple · I have a cup",
            explanationUzbek = "Ajoyib! Siz 3 ta darak gapni a/an qoidasi bilan to‘g‘ri yozdingiz.",
            vocabularyIdsCsv = "v_a0_07_03,v_a0_07_04,v_a0_07_07",
            grammarRuleIdsCsv = "g_a0_07_01,g_a0_07_02"
        ),
        // Lesson 8 exercises (9 exercises: Have I got a book? — Savol)
        ExerciseEntity(
            id = "ex_a0_08_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Savol qanday boshlanadi?",
            subPromptText = "1-mashq. To‘g‘ri variantni tanlang:",
            audioTextToSpeak = "Have I got a book?",
            optionsPipeSeparated = "I have|Have I got",
            correctAnswer = "Have I got",
            explanationUzbek = "Savol uchun ‘have’ oldinga chiqadi va ‘got’ qo‘shiladi: Have I got ...",
            vocabularyIdsCsv = "v_a0_08_01",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Have I got a book?” — bu nima?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Have I got a book?",
            optionsPipeSeparated = "javob|savol",
            correctAnswer = "savol",
            explanationUzbek = "“Have I got a book?” — “Menda kitob bormi?” degan savol.",
            vocabularyIdsCsv = "v_a0_08_04",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "To‘g‘ri tartibni tanlang:",
            subPromptText = "3-mashq. Savol tartibini toping:",
            audioTextToSpeak = "Have I got a book?",
            optionsPipeSeparated = "I have got a book?|Have I got a book?|Got I have a book?",
            correctAnswer = "Have I got a book?",
            explanationUzbek = "Savol tartibi: Have + I + got + a/an + ot?",
            vocabularyIdsCsv = "v_a0_08_04",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Have I got ___ book? · Have I got ___ apple?\nHave I got ___ cup? · Have I got ___ egg?",
            subPromptText = "4-mashq. a / an qo‘ying:",
            audioTextToSpeak = "Have I got a book? Have I got an apple? Have I got a cup? Have I got an egg?",
            optionsPipeSeparated = "a / an / a / an|an / a / an / a|a / a / an / an",
            correctAnswer = "a / an / a / an",
            explanationUzbek = "book/cup → a; apple/egg → an.",
            vocabularyIdsCsv = "v_a0_08_04,v_a0_08_06,v_a0_08_07",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "• Have I got a book?\n• Have I got an apple?\n• Have I got a cup?",
            subPromptText = "5-mashq. Savolga tasdiq javobini bering:",
            audioTextToSpeak = "Have I got a book? Yes, I have.",
            optionsPipeSeparated = "Yes, I have.|No, I got.|I have got.",
            correctAnswer = "Yes, I have.",
            explanationUzbek = "Tasdiq javob: Yes, I have. (“Ha, bor.”)",
            vocabularyIdsCsv = "v_a0_08_02,v_a0_08_04,v_a0_08_06",
            grammarRuleIdsCsv = "g_a0_08_02"
        ),
        ExerciseEntity(
            id = "ex_a0_08_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have got a book? · Have I got a apple?",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "Have I got a book? Have I got an apple?",
            optionsPipeSeparated = "Have I got a book? · Have I got an apple?|I have got a book? · Have I got an apple?|Got I have a book? · Have I got a apple?",
            correctAnswer = "Have I got a book? · Have I got an apple?",
            explanationUzbek = "Savolda ‘Have I got ...?’ tartibi va ‘an apple’ ishlatiladi.",
            vocabularyIdsCsv = "v_a0_08_04,v_a0_08_06",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Sumkangizni tekshiryapsiz (ruchka)\n• Nonushtaga qarayapsiz (tuxum)",
            subPromptText = "7-mashq. Hayotiy vaziyat — savol bering:",
            audioTextToSpeak = "Have I got a pen? Have I got an egg?",
            optionsPipeSeparated = "Have I got a pen? · Have I got an egg?|I have a pen. · I have an egg.|Have I got an pen? · Have I got a egg?",
            correctAnswer = "Have I got a pen? · Have I got an egg?",
            explanationUzbek = "Sumkada: Have I got a pen? Nonushtada: Have I got an egg?",
            vocabularyIdsCsv = "v_a0_08_05,v_a0_08_07",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Have I got a book? Yes, I have. Have I got an apple? Yes, I have.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "Have I got a book? Yes, I have. Have I got an apple? Yes, I have.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Savol ohangi va javobni to‘g‘ri talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_08_02,v_a0_08_04,v_a0_08_06",
            grammarRuleIdsCsv = "g_a0_08_01,g_a0_08_02"
        ),
        ExerciseEntity(
            id = "ex_a0_08_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "2 ta savol yozing:\n• Have I got ______?\n• Have I got ______?",
            subPromptText = "9-mashq. O‘zingiz yozing (savol shakli):",
            audioTextToSpeak = "Have I got a book? Have I got an apple?",
            optionsPipeSeparated = "Have I got a book? · Have I got an apple?|I have got a book? · I have an apple?|Have I a book? · Have I got a apple?",
            correctAnswer = "Have I got a book? · Have I got an apple?",
            explanationUzbek = "Ajoyib! Endi ‘Have I got a/an ...?’ savollarini bemalol tuza olasiz.",
            vocabularyIdsCsv = "v_a0_08_04,v_a0_08_06",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        // Lesson 9 exercises (10 exercises: I have not got a book — Inkor)
        ExerciseEntity(
            id = "ex_a0_09_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Inkor uchun nima qo‘shiladi?",
            subPromptText = "1-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have not got a book.",
            optionsPipeSeparated = "got|not",
            correctAnswer = "not",
            explanationUzbek = "Inkor uchun ‘have’ dan keyin ‘not’ qo‘shiladi: I have not got ...",
            vocabularyIdsCsv = "v_a0_09_01",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have not got a book” — bu nima?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have not got a book.",
            optionsPipeSeparated = "savol|inkor",
            correctAnswer = "inkor",
            explanationUzbek = "“I have not got a book” — “Menda kitob yo‘q” degan inkor gap.",
            vocabularyIdsCsv = "v_a0_09_03",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "To‘g‘ri tartibni tanlang:",
            subPromptText = "3-mashq. Inkor gap tartibini toping:",
            audioTextToSpeak = "I have not got a book.",
            optionsPipeSeparated = "I not have got a book.|I have not got a book.|I have got not a book.",
            correctAnswer = "I have not got a book.",
            explanationUzbek = "To‘g‘ri tartib: I + have + not + got + a/an + ot.",
            vocabularyIdsCsv = "v_a0_09_03",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have not got ___ apple · I have not got ___ book\nI have not got ___ egg · I have not got ___ cup",
            subPromptText = "4-mashq. a / an qo‘ying:",
            audioTextToSpeak = "I have not got an apple. I have not got a book. I have not got an egg. I have not got a cup.",
            optionsPipeSeparated = "an / a / an / a|a / an / a / an|an / an / a / a",
            correctAnswer = "an / a / an / a",
            explanationUzbek = "apple/egg → an; book/cup → a.",
            vocabularyIdsCsv = "v_a0_09_03,v_a0_09_04,v_a0_09_06,v_a0_09_07",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have got a book · I have got an apple · I have got a cup",
            subPromptText = "5-mashq. Tasdiqni inkorga aylantiring:",
            audioTextToSpeak = "I have not got a book. I have not got an apple. I have not got a cup.",
            optionsPipeSeparated = "I have not got a book · I have not got an apple · I have not got a cup|I not have got a book · I not have got an apple · I not have got a cup|I have got not a book · I have got not an apple · I have got not a cup",
            correctAnswer = "I have not got a book · I have not got an apple · I have not got a cup",
            explanationUzbek = "Har bir gapda ‘have’ dan keyin ‘not’ qo‘yiladi.",
            vocabularyIdsCsv = "v_a0_09_03,v_a0_09_04,v_a0_09_06",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I not have got a book · I have not got a apple · I have got not a cup",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "I have not got a book. I have not got an apple. I have not got a cup.",
            optionsPipeSeparated = "I have not got a book · I have not got an apple · I have not got a cup|I not have got a book · I have not got an apple · I have not got a cup|I have not got a book · I have not got a apple · I have got not a cup",
            correctAnswer = "I have not got a book · I have not got an apple · I have not got a cup",
            explanationUzbek = "‘not’ doim ‘have’ dan keyin keladi va ‘apple’ oldidan ‘an’ qo‘yiladi.",
            vocabularyIdsCsv = "v_a0_09_03,v_a0_09_04,v_a0_09_06",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) I have a book           a) savol\n2) Have I got a book?      b) inkor\n3) I have not got a book   c) tasdiq",
            subPromptText = "7-mashq. Uchta shaklni juftlang:",
            audioTextToSpeak = "I have a book. Have I got a book? I have not got a book.",
            optionsPipeSeparated = "1-c, 2-a, 3-b|1-a, 2-b, 3-c|1-b, 2-c, 3-a",
            correctAnswer = "1-c, 2-a, 3-b",
            explanationUzbek = "1-c (darak/tasdiq), 2-a (savol), 3-b (inkor).",
            vocabularyIdsCsv = "v_a0_09_01,v_a0_09_03",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Do‘stingiz: “Senda ruchka bormi?”\n• Onangiz: “Sumkangda olma bormi?”",
            subPromptText = "8-mashq. Hayotiy vaziyat — inkor qiling:",
            audioTextToSpeak = "I have not got a pen. I have not got an apple.",
            optionsPipeSeparated = "I have not got a pen. · I have not got an apple.|Have I got a pen? · Have I got an apple?|I have a pen. · I have an apple.",
            correctAnswer = "I have not got a pen. · I have not got an apple.",
            explanationUzbek = "Yo‘q deyish uchun: I have not got a pen. / I have not got an apple.",
            vocabularyIdsCsv = "v_a0_09_05,v_a0_09_06",
            grammarRuleIdsCsv = "g_a0_09_01,g_a0_09_02"
        ),
        ExerciseEntity(
            id = "ex_a0_09_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have not got a book. I have not got an apple. I have not got a cup.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "I have not got a book. I have not got an apple. I have not got a cup.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Inkor gaplarni ishonch bilan talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_09_03,v_a0_09_04,v_a0_09_06",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "3 ta inkor gap yozing:\n• I have not got ______.\n• I have not got ______.\n• I have not got ______.",
            subPromptText = "10-mashq. O‘zingiz yozing (3 ta inkor gap):",
            audioTextToSpeak = "I have not got a book. I have not got an apple. I have not got a cup.",
            optionsPipeSeparated = "I have not got a book · I have not got an apple · I have not got a cup|I not have got a book · I not have got an apple · I not have got a cup|I have got not a book · I have not got a apple · I have not got cup",
            correctAnswer = "I have not got a book · I have not got an apple · I have not got a cup",
            explanationUzbek = "Tabriklaymiz! Darak, savol va inkor shakllarining uchalasini ham o‘zlashtirdingiz.",
            vocabularyIdsCsv = "v_a0_09_03,v_a0_09_04,v_a0_09_06",
            grammarRuleIdsCsv = "g_a0_09_01,g_a0_09_02"
        ),
        // Lesson 10 exercises (9 exercises: Numbers 1..10 & plural -s)
        ExerciseEntity(
            id = "ex_a0_10_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Three” nechchi?",
            subPromptText = "1-mashq. To‘g‘ri raqamni tanlang:",
            audioTextToSpeak = "three",
            optionsPipeSeparated = "2|3|4",
            correctAnswer = "3",
            explanationUzbek = "‘Three’ — bu 3 (uch).",
            vocabularyIdsCsv = "v_a0_10_03",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Seven” nechchi?",
            subPromptText = "2-mashq. To‘g‘ri raqamni tanlang:",
            audioTextToSpeak = "seven",
            optionsPipeSeparated = "6|7|8",
            correctAnswer = "7",
            explanationUzbek = "‘Seven’ — bu 7 (yetti).",
            vocabularyIdsCsv = "v_a0_10_07",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "one · five · nine · ten",
            subPromptText = "3-mashq. Mos raqamlarni tanlang:",
            audioTextToSpeak = "one, five, nine, ten",
            optionsPipeSeparated = "1 / 5 / 9 / 10|2 / 5 / 8 / 10|1 / 4 / 9 / 10",
            correctAnswer = "1 / 5 / 9 / 10",
            explanationUzbek = "one = 1, five = 5, nine = 9, ten = 10.",
            vocabularyIdsCsv = "v_a0_10_01,v_a0_10_05,v_a0_10_09,v_a0_10_10",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "2 · 4 · 6 · 8",
            subPromptText = "4-mashq. Mos inglizcha so‘zlarni tanlang:",
            audioTextToSpeak = "two, four, six, eight",
            optionsPipeSeparated = "two / four / six / eight|three / four / seven / eight|two / five / six / nine",
            correctAnswer = "two / four / six / eight",
            explanationUzbek = "2 = two, 4 = four, 6 = six, 8 = eight.",
            vocabularyIdsCsv = "v_a0_10_02,v_a0_10_04,v_a0_10_06,v_a0_10_08",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "two book / two books · three apple / three apples\nfive cup / five cups · ten pen / ten pens",
            subPromptText = "5-mashq. To‘g‘ri variantni tanlang (-s):",
            audioTextToSpeak = "two books, three apples, five cups, ten pens",
            optionsPipeSeparated = "two books · three apples · five cups · ten pens|two book · three apple · five cup · ten pen|two books · three apple · five cups · ten pen",
            correctAnswer = "two books · three apples · five cups · ten pens",
            explanationUzbek = "Ko‘p narsa (2+) bo‘lsa oxiriga -s qo‘shiladi: books, apples, cups, pens.",
            vocabularyIdsCsv = "v_a0_10_02,v_a0_10_03,v_a0_10_05,v_a0_10_10",
            grammarRuleIdsCsv = "g_a0_10_02"
        ),
        ExerciseEntity(
            id = "ex_a0_10_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ books (2) · ___ cats (4)\n___ eggs (9) · ___ apples (6)",
            subPromptText = "6-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "two books, four cats, nine eggs, six apples",
            optionsPipeSeparated = "two / four / nine / six|three / four / nine / seven|two / five / eight / six",
            correctAnswer = "two / four / nine / six",
            explanationUzbek = "2 = two, 4 = four, 9 = nine, 6 = six.",
            vocabularyIdsCsv = "v_a0_10_02,v_a0_10_04,v_a0_10_06,v_a0_10_09",
            grammarRuleIdsCsv = "g_a0_10_01,g_a0_10_02"
        ),
        ExerciseEntity(
            id = "ex_a0_10_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "two book · three apple · one books",
            subPromptText = "7-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "two books, three apples, one book",
            optionsPipeSeparated = "two books · three apples · one book|two book · three apples · one book|two books · three apple · one books",
            correctAnswer = "two books · three apples · one book",
            explanationUzbek = "Ko‘pida -s (two books, three apples), bittada -s yo‘q (one book).",
            vocabularyIdsCsv = "v_a0_10_01,v_a0_10_02,v_a0_10_03",
            grammarRuleIdsCsv = "g_a0_10_02"
        ),
        ExerciseEntity(
            id = "ex_a0_10_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“One, two, three, four, five, six, seven, eight, nine, ten.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "One, two, three, four, five, six, seven, eight, nine, ten.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Ajoyib! 1 dan 10 gacha sonlarni ravon talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_10_01,v_a0_10_05,v_a0_10_10",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Uyingizdagi narsalarni son bilan yozing:\n• ______ books\n• ______ cups\n• ______ pens",
            subPromptText = "9-mashq. O‘zingiz yozing (son bilan):",
            audioTextToSpeak = "two books, five cups, three pens",
            optionsPipeSeparated = "two / five / three|one / one / one|book / cup / pen",
            correctAnswer = "two / five / three",
            explanationUzbek = "Barakalla! Endi narsalarni 1 dan 10 gacha inglizcha sanay olasiz.",
            vocabularyIdsCsv = "v_a0_10_02,v_a0_10_03,v_a0_10_05",
            grammarRuleIdsCsv = "g_a0_10_01,g_a0_10_02"
        ),
        // Lesson 11 exercises (10 exercises: I have three books)
        ExerciseEntity(
            id = "ex_a0_11_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have a book” — nechta kitob?",
            subPromptText = "1-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have a book.",
            optionsPipeSeparated = "bitta|ikkita|uchta",
            correctAnswer = "bitta",
            explanationUzbek = "‘I have a book’ — menda bitta kitob bor.",
            vocabularyIdsCsv = "v_a0_11_02",
            grammarRuleIdsCsv = "g_a0_11_02"
        ),
        ExerciseEntity(
            id = "ex_a0_11_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have three books” — nechta kitob?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have three books.",
            optionsPipeSeparated = "bitta|ikkita|uchta",
            correctAnswer = "uchta",
            explanationUzbek = "‘three’ = 3 (uchta) → Menda uchta kitob bor.",
            vocabularyIdsCsv = "v_a0_11_03",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have one book / I have one books\nI have two book / I have two books\nI have a apples / I have five apples",
            subPromptText = "3-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "I have one book. I have two books. I have five apples.",
            optionsPipeSeparated = "one book · two books · five apples|one books · two book · a apples|one book · two book · five apples",
            correctAnswer = "one book · two books · five apples",
            explanationUzbek = "one bilan -s qo‘shilmaydi (one book), 2+ son bilan -s qo‘shiladi (two books, five apples).",
            vocabularyIdsCsv = "v_a0_11_02,v_a0_11_03,v_a0_11_05",
            grammarRuleIdsCsv = "g_a0_11_01,g_a0_11_02"
        ),
        ExerciseEntity(
            id = "ex_a0_11_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have ___ book. (1) · I have ___ books. (2)\nI have ___ cups. (3) · I have ___ apples. (5) · I have ___ pen. (1)",
            subPromptText = "4-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "I have one book. I have two books. I have three cups. I have five apples. I have a pen.",
            optionsPipeSeparated = "one / two / three / five / a|a / two / three / five / ones|one / two / three / a / an",
            correctAnswer = "one / two / three / five / a",
            explanationUzbek = "1 → one yoki a; 2 → two; 3 → three; 5 → five.",
            vocabularyIdsCsv = "v_a0_11_01,v_a0_11_02,v_a0_11_03,v_a0_11_04",
            grammarRuleIdsCsv = "g_a0_11_01,g_a0_11_02"
        ),
        ExerciseEntity(
            id = "ex_a0_11_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have one books · I have three book\nI have two apple · I have five cup",
            subPromptText = "5-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "I have one book. I have three books. I have two apples. I have five cups.",
            optionsPipeSeparated = "I have one book · I have three books · I have two apples · I have five cups|I have one books · I have three books · I have two apples · I have five cup|I have a books · I have three book · I have two apples · I have five cups",
            correctAnswer = "I have one book · I have three books · I have two apples · I have five cups",
            explanationUzbek = "one bilan -s yo‘q (one book), 2+ son bilan -s bor (three books, two apples, five cups).",
            vocabularyIdsCsv = "v_a0_11_02,v_a0_11_04,v_a0_11_06",
            grammarRuleIdsCsv = "g_a0_11_01,g_a0_11_02"
        ),
        ExerciseEntity(
            id = "ex_a0_11_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "have / I / two / books\nI / three / have / apples\na / have / I / pen",
            subPromptText = "6-mashq. So‘zlarni tartibga soling:",
            audioTextToSpeak = "I have two books. I have three apples. I have a pen.",
            optionsPipeSeparated = "I have two books · I have three apples · I have a pen|I two have books · Have I three apples · I a have pen|Two books I have · Apples three I have · Pen a I have",
            correctAnswer = "I have two books · I have three apples · I have a pen",
            explanationUzbek = "Tartib: I + have + son (yoki a/an) + ot.",
            vocabularyIdsCsv = "v_a0_11_03,v_a0_11_05",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "I have three books · I have five pens\nI have ten apples · I have two cups",
            subPromptText = "7-mashq. Sonlarni raqam bilan belgilang:",
            audioTextToSpeak = "I have three books. I have five pens. I have ten apples. I have two cups.",
            optionsPipeSeparated = "3 / 5 / 10 / 2|3 / 4 / 10 / 2|2 / 5 / 9 / 3",
            correctAnswer = "3 / 5 / 10 / 2",
            explanationUzbek = "three = 3, five = 5, ten = 10, two = 2.",
            vocabularyIdsCsv = "v_a0_11_03,v_a0_11_06,v_a0_11_07",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Onangiz: “Sumkangda nechta kitob bor?” (3)\n• Do‘stingiz: “Nechta olmang bor?” (2)\n• O‘qituvchi: “Nechta ruchkang bor?” (1)",
            subPromptText = "8-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "I have three books. I have two apples. I have one pen.",
            optionsPipeSeparated = "I have three books. · I have two apples. · I have one pen.|I have three book. · I have two apple. · I have one pens.|Have I three books? · I have two apples. · I have a pens.",
            correctAnswer = "I have three books. · I have two apples. · I have one pen.",
            explanationUzbek = "3 → I have three books; 2 → I have two apples; 1 → I have one pen.",
            vocabularyIdsCsv = "v_a0_11_01,v_a0_11_03,v_a0_11_05",
            grammarRuleIdsCsv = "g_a0_11_01,g_a0_11_02"
        ),
        ExerciseEntity(
            id = "ex_a0_11_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have a book. I have two books. I have three apples.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "I have a book. I have two books. I have three apples.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Bitta va ko‘p narsalarni ‘I have’ bilan ravon aytdingiz.",
            vocabularyIdsCsv = "v_a0_11_03,v_a0_11_05",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "3 ta gap yozing:\n• I have ______.\n• I have ______.\n• I have ______.",
            subPromptText = "10-mashq. O‘zingiz yozing (3 ta gap):",
            audioTextToSpeak = "I have three books. I have two cups. I have one pen.",
            optionsPipeSeparated = "I have three books · I have two cups · I have one pen|I have three book · I have two cup · I have one pens|I have a books · I have two apple · I have one cups",
            correctAnswer = "I have three books · I have two cups · I have one pen",
            explanationUzbek = "Ajoyib! Endi ‘I have + son + ot’ qoidasini amalda bemalol qo‘llay olasiz.",
            vocabularyIdsCsv = "v_a0_11_01,v_a0_11_03,v_a0_11_04",
            grammarRuleIdsCsv = "g_a0_11_01,g_a0_11_02"
        ),
        // Lesson 12 exercises (10 exercises: I have a book, but I have no cups)
        ExerciseEntity(
            id = "ex_a0_12_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“But” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "I have a book, but I have no cups.",
            optionsPipeSeparated = "va|lekin|yo‘q",
            correctAnswer = "lekin",
            explanationUzbek = "‘But’ — bu “lekin” degani. Ikki gapni bog‘laydi.",
            vocabularyIdsCsv = "v_a0_12_01",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        ExerciseEntity(
            id = "ex_a0_12_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“No” nima degani?",
            subPromptText = "2-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "I have no cups.",
            optionsPipeSeparated = "bor|ko‘p|yo‘q",
            correctAnswer = "yo‘q",
            explanationUzbek = "‘No’ — bu “yo‘q” degani. Biror narsa yo‘qligini aytadi.",
            vocabularyIdsCsv = "v_a0_12_02",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        ExerciseEntity(
            id = "ex_a0_12_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have a book, ___ I have no cups.\nI have two pens, but I have ___ books.\nI have three apples, but I have ___ eggs.",
            subPromptText = "3-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "I have a book, but I have no cups. I have two pens, but I have no books. I have three apples, but I have no eggs.",
            optionsPipeSeparated = "but / no / no|and / not / no|but / a / not",
            correctAnswer = "but / no / no",
            explanationUzbek = "Bog‘lash uchun ‘but’ (lekin), yo‘qlik uchun ‘no’ (yo‘q) ishlatiladi.",
            vocabularyIdsCsv = "v_a0_12_01,v_a0_12_02,v_a0_12_03,v_a0_12_04,v_a0_12_05",
            grammarRuleIdsCsv = "g_a0_12_01,g_a0_12_02"
        ),
        ExerciseEntity(
            id = "ex_a0_12_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) I have a book, but I have no cups.\n2) I have a book, but I have no a cups.\n3) I have two pens, but I have no books.\n4) I have two pens, but I have not books.",
            subPromptText = "4-mashq. To‘g‘ri gaplar qaysilar?",
            audioTextToSpeak = "I have a book, but I have no cups. I have two pens, but I have no books.",
            optionsPipeSeparated = "1 va 3 to‘g‘ri|2 va 4 to‘g‘ri|1 va 2 to‘g‘ri",
            correctAnswer = "1 va 3 to‘g‘ri",
            explanationUzbek = "1 va 3 to‘g‘ri! ‘no’ dan keyin a/an qo‘yilmaydi va ‘not books’ emas ‘no books’ deyiladi.",
            vocabularyIdsCsv = "v_a0_12_03,v_a0_12_04",
            grammarRuleIdsCsv = "g_a0_12_01,g_a0_12_02"
        ),
        ExerciseEntity(
            id = "ex_a0_12_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have a book and I have no pens\nI have no a book",
            subPromptText = "5-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "I have a book, but I have no pens. I have no books.",
            optionsPipeSeparated = "I have a book, but I have no pens · I have no books|I have a book and I have no pens · I have no a book|I have a book, but I have not pens · I have not a books",
            correctAnswer = "I have a book, but I have no pens · I have no books",
            explanationUzbek = "Biri bor, biri yo‘q bo‘lganda ‘but’ ishlatiladi; ‘no’ dan keyin a/an qo‘yilmaydi.",
            vocabularyIdsCsv = "v_a0_12_01,v_a0_12_04,v_a0_12_07",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        ExerciseEntity(
            id = "ex_a0_12_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "have / I / a / book / but / I / have / no / cups\ntwo / have / I / pens / but / I / have / no / books",
            subPromptText = "6-mashq. So‘zlarni tartibga solib gap tuzing:",
            audioTextToSpeak = "I have a book, but I have no cups. I have two pens, but I have no books.",
            optionsPipeSeparated = "I have a book, but I have no cups · I have two pens, but I have no books|I have a book, no but I have cups · Two pens I have, but no I have books|But I have a book, I have no cups · I have no two pens, but I have books",
            correctAnswer = "I have a book, but I have no cups · I have two pens, but I have no books",
            explanationUzbek = "Tartib: I have ___ , but I have no ___.",
            vocabularyIdsCsv = "v_a0_12_03,v_a0_12_04",
            grammarRuleIdsCsv = "g_a0_12_02"
        ),
        ExerciseEntity(
            id = "ex_a0_12_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Sizda kitob bor, lekin ruchka yo‘q\n• Sizda ikkita olma bor, lekin tuxum yo‘q",
            subPromptText = "7-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "I have a book, but I have no pens. I have two apples, but I have no eggs.",
            optionsPipeSeparated = "I have a book, but I have no pens. · I have two apples, but I have no eggs.|I have a book and I have no a pen. · I have two apple, but I have not eggs.|Have I got a book, but no pens? · I have two apples, no eggs.",
            correctAnswer = "I have a book, but I have no pens. · I have two apples, but I have no eggs.",
            explanationUzbek = "Bor → I have; lekin → but; yo‘q → I have no ...",
            vocabularyIdsCsv = "v_a0_12_05,v_a0_12_07",
            grammarRuleIdsCsv = "g_a0_12_02"
        ),
        ExerciseEntity(
            id = "ex_a0_12_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have a book, but I have no cups. I have two pens, but I have no books.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "I have a book, but I have no cups. I have two pens, but I have no books.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! ‘but’ va ‘no’ bilan bog‘langan gaplarni ravon talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_12_03,v_a0_12_04",
            grammarRuleIdsCsv = "g_a0_12_02"
        ),
        ExerciseEntity(
            id = "ex_a0_12_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "2 ta gap yozing:\n• I have ______, but I have no ______.\n• I have ______, but I have no ______.",
            subPromptText = "9-mashq. O‘zingiz yozing (2 ta gap):",
            audioTextToSpeak = "I have a book, but I have no cups. I have two pens, but I have no books.",
            optionsPipeSeparated = "I have a book, but I have no cups · I have two pens, but I have no books|I have a book, but I have no a cups · I have two pens, but I have not books|I have a book and I have no cups · I have two pen, but I have no book",
            correctAnswer = "I have a book, but I have no cups · I have two pens, but I have no books",
            explanationUzbek = "Ajoyib! Siz ‘I have ___, but I have no ___’ qolipida erkin gap tuza olasiz.",
            vocabularyIdsCsv = "v_a0_12_03,v_a0_12_04",
            grammarRuleIdsCsv = "g_a0_12_01,g_a0_12_02"
        ),
        ExerciseEntity(
            id = "ex_a0_12_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I have three cups → I have ___ cups\nI have two books → I have ___ books\nI have five pens → I have ___ pens",
            subPromptText = "10-mashq. “No” bilan yozing:",
            audioTextToSpeak = "I have no cups. I have no books. I have no pens.",
            optionsPipeSeparated = "I have no cups · I have no books · I have no pens|I have no a cups · I have not books · I have no a pens|I have cups no · I have books no · I have pens no",
            correctAnswer = "I have no cups · I have no books · I have no pens",
            explanationUzbek = "Son o‘rniga ‘no’ qo‘yiladi: I have no cups, I have no books, I have no pens.",
            vocabularyIdsCsv = "v_a0_12_03,v_a0_12_04,v_a0_12_07",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        // Lesson 13 exercises (10 exercises: Counting the Tigers — Sanoq sonlar)
        ExerciseEntity(
            id = "ex_a0_13_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Tiger” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "tiger",
            optionsPipeSeparated = "sher|yo‘lbars|mushuk",
            correctAnswer = "yo‘lbars",
            explanationUzbek = "‘Tiger’ — bu “yo‘lbars” degani.",
            vocabularyIdsCsv = "v_a0_13_01",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Two tigers” nechta?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "two tigers",
            optionsPipeSeparated = "bitta|ikkita|uchta",
            correctAnswer = "ikkita",
            explanationUzbek = "‘Two tigers’ — ikkita yo‘lbars.",
            vocabularyIdsCsv = "v_a0_13_03",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "three tigers · five tigers · seven tigers · ten tigers",
            subPromptText = "3-mashq. Mos raqamlarni tanlang:",
            audioTextToSpeak = "three tigers, five tigers, seven tigers, ten tigers",
            optionsPipeSeparated = "3 / 5 / 7 / 10|3 / 4 / 7 / 10|2 / 5 / 8 / 10",
            correctAnswer = "3 / 5 / 7 / 10",
            explanationUzbek = "three = 3, five = 5, seven = 7, ten = 10.",
            vocabularyIdsCsv = "v_a0_13_04,v_a0_13_06,v_a0_13_08,v_a0_13_11",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "2 tigers · 4 tigers · 6 tigers · 8 tigers",
            subPromptText = "4-mashq. Mos son so‘zlarini tanlang:",
            audioTextToSpeak = "two tigers, four tigers, six tigers, eight tigers",
            optionsPipeSeparated = "two / four / six / eight|two / five / six / nine|three / four / seven / eight",
            correctAnswer = "two / four / six / eight",
            explanationUzbek = "2 = two, 4 = four, 6 = six, 8 = eight.",
            vocabularyIdsCsv = "v_a0_13_03,v_a0_13_05,v_a0_13_07,v_a0_13_09",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "two tiger / two tigers\nthree tiger / three tigers\none tiger / one tigers",
            subPromptText = "5-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "two tigers, three tigers, one tiger",
            optionsPipeSeparated = "two tigers · three tigers · one tiger|two tiger · three tigers · one tigers|two tigers · three tiger · one tiger",
            correctAnswer = "two tigers · three tigers · one tiger",
            explanationUzbek = "2+ bo‘lsa tigers (-s bilan), bitta (one) bo‘lsa tiger (-s yo‘q).",
            vocabularyIdsCsv = "v_a0_13_02,v_a0_13_03,v_a0_13_04",
            grammarRuleIdsCsv = "g_a0_13_02"
        ),
        ExerciseEntity(
            id = "ex_a0_13_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ tigers (3) · ___ tigers (5)\n___ tigers (9) · ___ tiger (1)",
            subPromptText = "6-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "three tigers, five tigers, nine tigers, one tiger",
            optionsPipeSeparated = "three / five / nine / one|two / five / nine / one|three / four / eight / a",
            correctAnswer = "three / five / nine / one",
            explanationUzbek = "3 = three, 5 = five, 9 = nine, 1 = one.",
            vocabularyIdsCsv = "v_a0_13_02,v_a0_13_04,v_a0_13_06,v_a0_13_10",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "two tiger · three tigers · one tigers",
            subPromptText = "7-mashq. Xatoni toping va to‘g‘rilang:",
            audioTextToSpeak = "two tigers, three tigers, one tiger",
            optionsPipeSeparated = "two tigers · to‘g‘ri · one tiger|two tigers · to‘g‘ri · one tigers|two tiger · to‘g‘ri · one tiger",
            correctAnswer = "two tigers · to‘g‘ri · one tiger",
            explanationUzbek = "two tigers (-s bor), three tigers (to‘g‘ri), one tiger (-s yo‘q).",
            vocabularyIdsCsv = "v_a0_13_02,v_a0_13_03,v_a0_13_04",
            grammarRuleIdsCsv = "g_a0_13_02"
        ),
        ExerciseEntity(
            id = "ex_a0_13_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“One tiger. Two tigers. Three tigers. Four tigers. Five tigers.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "One tiger. Two tigers. Three tigers. Four tigers. Five tigers.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Yo‘lbarslarni ingliz tilida ravon sanadingiz.",
            vocabularyIdsCsv = "v_a0_13_02,v_a0_13_04,v_a0_13_06",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Rasmda ko‘rgan yo‘lbarslaringizni yozing:\n• ______ tigers\n• ______ tigers\n• ______ tiger",
            subPromptText = "9-mashq. O‘zingiz yozing (son):",
            audioTextToSpeak = "three tigers, five tigers, one tiger",
            optionsPipeSeparated = "three tigers · five tigers · one tiger|one tigers · two tigers · two tiger|three tiger · five tiger · one tigers",
            correctAnswer = "three tigers · five tigers · one tiger",
            explanationUzbek = "Ajoyib! Ko‘p yo‘lbarslarga 2..10 + tigers, bittaga one + tiger deb yoziladi.",
            vocabularyIdsCsv = "v_a0_13_02,v_a0_13_04,v_a0_13_06",
            grammarRuleIdsCsv = "g_a0_13_02"
        ),
        ExerciseEntity(
            id = "ex_a0_13_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Nechta yo‘lbars ko‘ryapsiz? (3)\n• Nechta yo‘lbars ko‘ryapsiz? (5)",
            subPromptText = "10-mashq. Savolga javob bering:",
            audioTextToSpeak = "I see three tigers. I see five tigers.",
            optionsPipeSeparated = "I see three tigers. · I see five tigers.|I see three tiger. · I see five tiger.|I see the third tiger. · I see the fifth tiger.",
            correctAnswer = "I see three tigers. · I see five tigers.",
            explanationUzbek = "Nechta? → Sanoq son + -s: I see three tigers. I see five tigers.",
            vocabularyIdsCsv = "v_a0_13_04,v_a0_13_06",
            grammarRuleIdsCsv = "g_a0_13_01,g_a0_13_02"
        ),
        // Lesson 14 exercises (10 exercises: The first tiger — Tartib sonlar)
        ExerciseEntity(
            id = "ex_a0_14_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“First” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "the first tiger",
            optionsPipeSeparated = "birinchi|bitta|ikkinchi",
            correctAnswer = "birinchi",
            explanationUzbek = "‘First’ — bu “birinchi” degani (nechanchi?).",
            vocabularyIdsCsv = "v_a0_14_01",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Third” nima degani?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "the third tiger",
            optionsPipeSeparated = "uchta|uchinchi|o‘ninchi",
            correctAnswer = "uchinchi",
            explanationUzbek = "‘Third’ — bu “uchinchi” degani (nechanchi?).",
            vocabularyIdsCsv = "v_a0_14_03",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "three · third · one · first",
            subPromptText = "3-mashq. Sanoqmi yoki tartibmi?",
            audioTextToSpeak = "three, third, one, first",
            optionsPipeSeparated = "sanoq / tartib / sanoq / tartib|tartib / sanoq / tartib / sanoq|sanoq / sanoq / tartib / tartib",
            correctAnswer = "sanoq / tartib / sanoq / tartib",
            explanationUzbek = "three, one — sanoq (nechta?); third, first — tartib (nechanchi?).",
            vocabularyIdsCsv = "v_a0_14_01,v_a0_14_03",
            grammarRuleIdsCsv = "g_a0_14_02"
        ),
        ExerciseEntity(
            id = "ex_a0_14_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) Nechta? (3 ta) → three tigers / the third tiger\n2) Nechanchi? (3-chi) → three tigers / the third tiger",
            subPromptText = "4-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "three tigers, the third tiger",
            optionsPipeSeparated = "three tigers · the third tiger|the third tiger · three tigers|three tiger · third tiger",
            correctAnswer = "three tigers · the third tiger",
            explanationUzbek = "Nechta? → three tigers. Nechanchi? → the third tiger.",
            vocabularyIdsCsv = "v_a0_14_03",
            grammarRuleIdsCsv = "g_a0_14_02"
        ),
        ExerciseEntity(
            id = "ex_a0_14_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ tiger (birinchi) · ___ tiger (ikkinchi)\n___ tiger (uchinchi) · ___ tiger (beshinchi)",
            subPromptText = "5-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "the first tiger, the second tiger, the third tiger, the fifth tiger",
            optionsPipeSeparated = "the first / the second / the third / the fifth|first / second / third / fifth|the one / the two / the three / the five",
            correctAnswer = "the first / the second / the third / the fifth",
            explanationUzbek = "Tartib sondan oldin doim ‘the’ qo‘yiladi: the first, the second, the third, the fifth.",
            vocabularyIdsCsv = "v_a0_14_01,v_a0_14_02,v_a0_14_03,v_a0_14_05",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "first tiger · the three tiger · the threeth tiger",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘ri variantni tanlang:",
            audioTextToSpeak = "the first tiger, the third tiger",
            optionsPipeSeparated = "the first tiger · the third tiger · the third tiger|a first tiger · the third tiger · the threeth tiger|the first tiger · the three tiger · third tiger",
            correctAnswer = "the first tiger · the third tiger · the third tiger",
            explanationUzbek = "‘the’ qo‘shiladi (the first tiger) va 3-chi tartib soni ‘third’ bo‘ladi (the third tiger).",
            vocabularyIdsCsv = "v_a0_14_01,v_a0_14_03",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "1) one    a) first\n2) two    b) second\n3) three  c) third",
            subPromptText = "7-mashq. Sanoq va tartib sonlarni juftlang:",
            audioTextToSpeak = "one, first, two, second, three, third",
            optionsPipeSeparated = "1-a, 2-b, 3-c|1-b, 2-a, 3-c|1-c, 2-b, 3-a",
            correctAnswer = "1-a, 2-b, 3-c",
            explanationUzbek = "one → first (1-chi), two → second (2-chi), three → third (3-chi).",
            vocabularyIdsCsv = "v_a0_14_01,v_a0_14_02,v_a0_14_03",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“The first tiger. The second tiger. The third tiger. The fourth tiger. The fifth tiger.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "The first tiger. The second tiger. The third tiger. The fourth tiger. The fifth tiger.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Ajoyib! Tartib sonlarni ‘the’ bilan ravon talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_14_01,v_a0_14_02,v_a0_14_03,v_a0_14_04,v_a0_14_05",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Tartib sonlarni yozing:\n• the ______ tiger (1)\n• the ______ tiger (2)\n• the ______ tiger (3)",
            subPromptText = "9-mashq. O‘zingiz yozing:",
            audioTextToSpeak = "the first tiger, the second tiger, the third tiger",
            optionsPipeSeparated = "first / second / third|one / two / three|oneth / twoth / threeth",
            correctAnswer = "first / second / third",
            explanationUzbek = "Barakalla! Birinchi → first, ikkinchi → second, uchinchi → third.",
            vocabularyIdsCsv = "v_a0_14_01,v_a0_14_02,v_a0_14_03",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "1) two tigers — bu nima?\n2) the second tiger — bu nima?",
            subPromptText = "10-mashq. Farqni ayting:",
            audioTextToSpeak = "two tigers. the second tiger.",
            optionsPipeSeparated = "ikkita yo‘lbars (nechta?) · ikkinchi yo‘lbars (nechanchi?)|ikkinchi yo‘lbars (nechanchi?) · ikkita yo‘lbars (nechta?)|ikkita yo‘lbars (nechanchi?) · ikkinchi yo‘lbars (nechta?)",
            correctAnswer = "ikkita yo‘lbars (nechta?) · ikkinchi yo‘lbars (nechanchi?)",
            explanationUzbek = "two tigers → ikkita yo‘lbars (nechta?); the second tiger → ikkinchi yo‘lbars (nechanchi?).",
            vocabularyIdsCsv = "v_a0_14_02",
            grammarRuleIdsCsv = "g_a0_14_02"
        ),
        // Lesson 15 exercises (9 exercises: Here is a boy)
        ExerciseEntity(
            id = "ex_a0_15_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Here” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "Here is a boy.",
            optionsPipeSeparated = "u yerda|mana / mana bu|yo‘q",
            correctAnswer = "mana / mana bu",
            explanationUzbek = "‘Here’ — “mana” va “mana bu” (yaqin narsa uchun).",
            vocabularyIdsCsv = "v_a0_15_01",
            grammarRuleIdsCsv = "g_a0_15_01"
        ),
        ExerciseEntity(
            id = "ex_a0_15_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Here is” nechta narsa uchun?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Here is a book.",
            optionsPipeSeparated = "bitta|ko‘p|hech qaysi",
            correctAnswer = "bitta",
            explanationUzbek = "‘is’ bitta narsa uchun — shuning uchun ‘Here is’ faqat bitta narsaga ishlatiladi.",
            vocabularyIdsCsv = "v_a0_15_01",
            grammarRuleIdsCsv = "g_a0_15_02"
        ),
        ExerciseEntity(
            id = "ex_a0_15_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "• Here is a boy / Here is two boys\n• Here is a girl / Here is three girls\n• Here is an apple / Here is a apple",
            subPromptText = "3-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "Here is a boy. Here is a girl. Here is an apple.",
            optionsPipeSeparated = "a boy · a girl · an apple|two boys · three girls · a apple|a boy · three girls · an apple",
            correctAnswer = "a boy · a girl · an apple",
            explanationUzbek = "Here is faqat bitta narsa (a/an) bilan keladi: a boy, a girl, an apple.",
            vocabularyIdsCsv = "v_a0_15_02,v_a0_15_03,v_a0_15_06",
            grammarRuleIdsCsv = "g_a0_15_02"
        ),
        ExerciseEntity(
            id = "ex_a0_15_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Here is ___ boy · Here is ___ girl\nHere is ___ apple · Here is ___ tiger",
            subPromptText = "4-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "Here is a boy. Here is a girl. Here is an apple. Here is a tiger.",
            optionsPipeSeparated = "a / a / an / a|an / a / a / a|a / an / an / a",
            correctAnswer = "a / a / an / a",
            explanationUzbek = "boy/girl/tiger undosh bilan (a), apple unli bilan (an).",
            vocabularyIdsCsv = "v_a0_15_02,v_a0_15_03,v_a0_15_04,v_a0_15_06",
            grammarRuleIdsCsv = "g_a0_15_02"
        ),
        ExerciseEntity(
            id = "ex_a0_15_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Here are a boy · Here is two boys · Here is an book",
            subPromptText = "5-mashq. Xatoni toping va to‘g‘rilang:",
            audioTextToSpeak = "Here is a boy. Here is a book.",
            optionsPipeSeparated = "Here is a boy · Here is a boy · Here is a book|Here are two boys · Here is a boy · Here is an book|Here is a boy · Here are a boy · Here is a book",
            correctAnswer = "Here is a boy · Here is a boy · Here is a book",
            explanationUzbek = "Bitta narsaga Here is (are emas, two emas) va book oldidan a (an emas) qo‘yiladi.",
            vocabularyIdsCsv = "v_a0_15_02,v_a0_15_07",
            grammarRuleIdsCsv = "g_a0_15_02"
        ),
        ExerciseEntity(
            id = "ex_a0_15_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• is / here / a / boy\n• here / a / is / girl",
            subPromptText = "6-mashq. Gap tuzing:",
            audioTextToSpeak = "Here is a boy. Here is a girl.",
            optionsPipeSeparated = "Here is a boy · Here is a girl|Is here a boy · A girl is here|Here a is boy · Girl here is a",
            correctAnswer = "Here is a boy · Here is a girl",
            explanationUzbek = "To‘g‘ri tartib: Here + is + a/an + ot → Here is a boy. Here is a girl.",
            vocabularyIdsCsv = "v_a0_15_02,v_a0_15_03",
            grammarRuleIdsCsv = "g_a0_15_01"
        ),
        ExerciseEntity(
            id = "ex_a0_15_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Onangiz “Bolam qani?”\n• Do‘stingiz “Kitobing qani?”",
            subPromptText = "7-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "Here is a boy. Here is a book.",
            optionsPipeSeparated = "Here is a boy. · Here is a book.|There are boys. · Here are books.|Here is boys. · Here is an book.",
            correctAnswer = "Here is a boy. · Here is a book.",
            explanationUzbek = "Yaqindagi bitta bola va bitta kitobni ko‘rsatib: Here is a boy. / Here is a book.",
            vocabularyIdsCsv = "v_a0_15_02,v_a0_15_07",
            grammarRuleIdsCsv = "g_a0_15_01"
        ),
        ExerciseEntity(
            id = "ex_a0_15_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Here is a boy. Here is a girl. Here is an apple.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "Here is a boy. Here is a girl. Here is an apple.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Yaqindagi bitta narsani ko‘rsatishni ravon talaffuz qildingiz.",
            vocabularyIdsCsv = "v_a0_15_02,v_a0_15_03,v_a0_15_06",
            grammarRuleIdsCsv = "g_a0_15_02"
        ),
        ExerciseEntity(
            id = "ex_a0_15_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "3 ta gap yozing:\n• Here is ______.\n• Here is ______.\n• Here is ______.",
            subPromptText = "9-mashq. O‘zingiz yozing (3 ta gap):",
            audioTextToSpeak = "Here is a boy. Here is a girl. Here is an apple.",
            optionsPipeSeparated = "Here is a boy · Here is a girl · Here is an apple|Here is two boys · Here are a girl · Here is an book|Here a boy · Here is girls · Here is apple",
            correctAnswer = "Here is a boy · Here is a girl · Here is an apple",
            explanationUzbek = "Ajoyib! Bitta yaqin narsa uchun ‘Here is a/an ___’ deb yoza olasiz.",
            vocabularyIdsCsv = "v_a0_15_01,v_a0_15_02,v_a0_15_06",
            grammarRuleIdsCsv = "g_a0_15_02"
        ),
        // Lesson 16 exercises (10 exercises: Here are two boys)
        ExerciseEntity(
            id = "ex_a0_16_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Here are” nechta narsa uchun?",
            subPromptText = "1-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Here are two boys.",
            optionsPipeSeparated = "bitta|ko‘p|hech qaysi",
            correctAnswer = "ko‘p",
            explanationUzbek = "‘are’ ko‘p narsa uchun — shuning uchun ‘Here are’ ikkita va undan ko‘p narsaga ishlatiladi.",
            vocabularyIdsCsv = "v_a0_16_01",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Here are two boys” — nechta bola?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Here are two boys.",
            optionsPipeSeparated = "bitta|ikkita|uchta",
            correctAnswer = "ikkita",
            explanationUzbek = "‘two boys’ — ikkita bola.",
            vocabularyIdsCsv = "v_a0_16_02",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "• Here are two boys / Here are a boys\n• Here are three girls / Here are a girls\n• Here are five tigers / Here are a tigers",
            subPromptText = "3-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "Here are two boys. Here are three girls. Here are five tigers.",
            optionsPipeSeparated = "two boys · three girls · five tigers|a boys · a girls · a tigers|two boys · a girls · five tigers",
            correctAnswer = "two boys · three girls · five tigers",
            explanationUzbek = "Here are bilan son + -s ishlatiladi (a qo‘yilmaydi).",
            vocabularyIdsCsv = "v_a0_16_02,v_a0_16_03,v_a0_16_04",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Here are ___ boys (2) · Here are ___ girls (3)\nHere are ___ tigers (5) · Here are ___ books (4)",
            subPromptText = "4-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "Here are two boys. Here are three girls. Here are five tigers. Here are four books.",
            optionsPipeSeparated = "two / three / five / four|two / four / five / six|one / three / five / four",
            correctAnswer = "two / three / five / four",
            explanationUzbek = "2 = two, 3 = three, 5 = five, 4 = four.",
            vocabularyIdsCsv = "v_a0_16_02,v_a0_16_03,v_a0_16_04,v_a0_16_05",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "Here is two boys · Here are a girls · Here are two boy",
            subPromptText = "5-mashq. Xatoni toping va to‘g‘rilang:",
            audioTextToSpeak = "Here are two boys. Here are three girls. Here are two boys.",
            optionsPipeSeparated = "Here are two boys · Here are three girls · Here are two boys|Here is two boys · Here are a girls · Here are two boy|Here are two boy · Here are three girl · Here are two boys",
            correctAnswer = "Here are two boys · Here are three girls · Here are two boys",
            explanationUzbek = "Ko‘p narsada ‘Here are’, son va ot oxirida ‘-s’ (boys, girls) bo‘ladi.",
            vocabularyIdsCsv = "v_a0_16_02,v_a0_16_03",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• are / here / two / boys\n• three / here / are / girls",
            subPromptText = "6-mashq. Gap tuzing:",
            audioTextToSpeak = "Here are two boys. Here are three girls.",
            optionsPipeSeparated = "Here are two boys · Here are three girls|Are here two boys · Girls here are three|Two boys here are · Three here are girls",
            correctAnswer = "Here are two boys · Here are three girls",
            explanationUzbek = "Tartib: Here + are + son + ot(-s) → Here are two boys. Here are three girls.",
            vocabularyIdsCsv = "v_a0_16_02,v_a0_16_03",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Onangiz “Bolalar qani?”\n• Do‘stingiz “Kitoblaring qani?”",
            subPromptText = "7-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "Here are two boys. Here are three books.",
            optionsPipeSeparated = "Here are two boys. · Here are three books.|Here is two boys. · Here is three books.|There is a boy. · Here is a book.",
            correctAnswer = "Here are two boys. · Here are three books.",
            explanationUzbek = "Yaqindagi ko‘p bola va kitoblar uchun: Here are two boys. / Here are three books.",
            vocabularyIdsCsv = "v_a0_16_02,v_a0_16_05",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Here are two boys. Here are three girls. Here are five tigers.”",
            subPromptText = "8-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "Here are two boys. Here are three girls. Here are five tigers.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Ko‘p narsalarni ‘Here are’ bilan ravon aytyapsiz.",
            vocabularyIdsCsv = "v_a0_16_02,v_a0_16_03,v_a0_16_04",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "3 ta gap yozing:\n• Here are ______.\n• Here are ______.\n• Here are ______.",
            subPromptText = "9-mashq. O‘zingiz yozing (3 ta gap):",
            audioTextToSpeak = "Here are two boys. Here are three girls. Here are five tigers.",
            optionsPipeSeparated = "Here are two boys · Here are three girls · Here are five tigers|Here are a boy · Here is two girls · Here are five tiger|Here is two boys · Here are a girls · Here are tiger",
            correctAnswer = "Here are two boys · Here are three girls · Here are five tigers",
            explanationUzbek = "Ajoyib! Ko‘p narsa uchun ‘Here are + son + ot(-s)’ deb yozishni o‘rgandingiz.",
            vocabularyIdsCsv = "v_a0_16_01,v_a0_16_02,v_a0_16_04",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ a boy · ___ two boys · ___ an apple · ___ five apples",
            subPromptText = "10-mashq. Here is yoki here are?",
            audioTextToSpeak = "Here is a boy. Here are two boys. Here is an apple. Here are five apples.",
            optionsPipeSeparated = "Here is / Here are / Here is / Here are|Here are / Here is / Here are / Here is|Here is / Here is / Here are / Here are",
            correctAnswer = "Here is / Here are / Here is / Here are",
            explanationUzbek = "Bitta → Here is (a boy, an apple). Ko‘p → Here are (two boys, five apples).",
            vocabularyIdsCsv = "v_a0_16_01,v_a0_16_06",
            grammarRuleIdsCsv = "g_a0_16_02"
        ),
        // Lesson 17 exercises (10 exercises: There is a boy)
        ExerciseEntity(
            id = "ex_a0_17_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“There” nima degani?",
            subPromptText = "1-mashq. To‘g‘ri tarjimani tanlang:",
            audioTextToSpeak = "There is a boy.",
            optionsPipeSeparated = "mana|u yerda / bor|yo‘q",
            correctAnswer = "u yerda / bor",
            explanationUzbek = "‘There’ — “u yerda” va “bor” degan ma’nolarni bildiradi.",
            vocabularyIdsCsv = "v_a0_17_01",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“There is” nechta narsa uchun?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "There is a book.",
            optionsPipeSeparated = "bitta|ko‘p|hech qaysi",
            correctAnswer = "bitta",
            explanationUzbek = "‘is’ bitta narsa uchun — shuning uchun ‘There is’ faqat bitta narsaga ishlatiladi.",
            vocabularyIdsCsv = "v_a0_17_02",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• ______ is a boy. (qo‘limda)\n• ______ is a boy. (u yerda)",
            subPromptText = "3-mashq. Here yoki there?",
            audioTextToSpeak = "Here is a boy. There is a boy.",
            optionsPipeSeparated = "Here / There|There / Here|Here / Here",
            correctAnswer = "Here / There",
            explanationUzbek = "Qo‘limda (yaqin) → Here is a boy. U yerda (uzoq/bor) → There is a boy.",
            vocabularyIdsCsv = "v_a0_17_01,v_a0_17_02",
            grammarRuleIdsCsv = "g_a0_17_02"
        ),
        ExerciseEntity(
            id = "ex_a0_17_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "• There is a boy / There is two boys\n• There is a girl / There is three girls\n• There is an apple / There is a apple",
            subPromptText = "4-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "There is a boy. There is a girl. There is an apple.",
            optionsPipeSeparated = "a boy · a girl · an apple|two boys · three girls · a apple|a boy · three girls · an apple",
            correctAnswer = "a boy · a girl · an apple",
            explanationUzbek = "There is bitta narsa uchun: a boy, a girl, an apple.",
            vocabularyIdsCsv = "v_a0_17_02,v_a0_17_03,v_a0_17_06",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "There is ___ boy · There is ___ girl\nThere is ___ apple · There is ___ tiger",
            subPromptText = "5-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "There is a boy. There is a girl. There is an apple. There is a tiger.",
            optionsPipeSeparated = "a / a / an / a|an / a / a / a|a / an / an / a",
            correctAnswer = "a / a / an / a",
            explanationUzbek = "boy/girl/tiger undosh bilan (a), apple unli bilan (an).",
            vocabularyIdsCsv = "v_a0_17_03,v_a0_17_04,v_a0_17_06",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "There are a boy · There is two boys · There is an book",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘rilang:",
            audioTextToSpeak = "There is a boy. There is a book.",
            optionsPipeSeparated = "There is a boy · There is a boy · There is a book|There are two boys · There is a boy · There is an book|There is a boy · There are a boy · There is a book",
            correctAnswer = "There is a boy · There is a boy · There is a book",
            explanationUzbek = "Bitta narsada There is (are emas, two emas) va book oldidan a (an emas) ishlatiladi.",
            vocabularyIdsCsv = "v_a0_17_02,v_a0_17_07",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• is / there / a / boy\n• there / a / is / girl",
            subPromptText = "7-mashq. Gap tuzing:",
            audioTextToSpeak = "There is a boy. There is a girl.",
            optionsPipeSeparated = "There is a boy · There is a girl|Is there a boy · A girl is there|There a is boy · Girl there is a",
            correctAnswer = "There is a boy · There is a girl",
            explanationUzbek = "Tartib: There + is + a/an + ot → There is a boy. There is a girl.",
            vocabularyIdsCsv = "v_a0_17_02,v_a0_17_03",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Onangiz “Xonada kim bor?”\n• Do‘stingiz “Bog‘da nima bor?”",
            subPromptText = "8-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "There is a boy. There is a tiger.",
            optionsPipeSeparated = "There is a boy. · There is a tiger.|Here are two boys. · Here are five tigers.|There is boys. · There are a tiger.",
            correctAnswer = "There is a boy. · There is a tiger.",
            explanationUzbek = "Xonada bitta bola va bog‘da bitta yo‘lbars borligini aytish: There is a boy. / There is a tiger.",
            vocabularyIdsCsv = "v_a0_17_02,v_a0_17_04",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“There is a boy. There is a girl. There is an apple.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "There is a boy. There is a girl. There is an apple.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! ‘There is’ iborasini ravon talaffuz qilyapsiz.",
            vocabularyIdsCsv = "v_a0_17_02,v_a0_17_03,v_a0_17_06",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "3 ta gap yozing:\n• There is ______.\n• There is ______.\n• There is ______.",
            subPromptText = "10-mashq. O‘zingiz yozing (3 ta gap):",
            audioTextToSpeak = "There is a boy. There is a tiger. There is an apple.",
            optionsPipeSeparated = "There is a boy · There is a tiger · There is an apple|There is two boys · There are a tiger · There is an book|There a boy · There is tigers · There is apple",
            correctAnswer = "There is a boy · There is a tiger · There is an apple",
            explanationUzbek = "Ajoyib! Bitta narsa borligini ‘There is a/an ___’ bilan yoza olasiz.",
            vocabularyIdsCsv = "v_a0_17_02,v_a0_17_04,v_a0_17_06",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        // Lesson 18 exercises (10 exercises: There are two boys)
        ExerciseEntity(
            id = "ex_a0_18_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“There are” nechta narsa uchun?",
            subPromptText = "1-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "There are two boys.",
            optionsPipeSeparated = "bitta|ko‘p|hech qaysi",
            correctAnswer = "ko‘p",
            explanationUzbek = "‘are’ ko‘p narsa uchun — shuning uchun ‘There are’ ko‘p narsalar borligini bildiradi.",
            vocabularyIdsCsv = "v_a0_18_01",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“There are two boys” — nechta bola?",
            subPromptText = "2-mashq. To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "There are two boys.",
            optionsPipeSeparated = "bitta|ikkita|uchta",
            correctAnswer = "ikkita",
            explanationUzbek = "‘two boys’ — ikkita bola.",
            vocabularyIdsCsv = "v_a0_18_01",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• ______ two boys. (qo‘limda)\n• ______ two boys. (u yerda)",
            subPromptText = "3-mashq. Here are yoki there are?",
            audioTextToSpeak = "Here are two boys. There are two boys.",
            optionsPipeSeparated = "Here are / There are|There are / Here are|Here is / There is",
            correctAnswer = "Here are / There are",
            explanationUzbek = "Qo‘limda (yaqin) → Here are two boys. U yerda → There are two boys.",
            vocabularyIdsCsv = "v_a0_18_01",
            grammarRuleIdsCsv = "g_a0_18_02"
        ),
        ExerciseEntity(
            id = "ex_a0_18_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "• There are two boys / There are a boys\n• There are three girls / There are a girls\n• There are five tigers / There are a tigers",
            subPromptText = "4-mashq. To‘g‘ri variantlarni tanlang:",
            audioTextToSpeak = "There are two boys. There are three girls. There are five tigers.",
            optionsPipeSeparated = "two boys · three girls · five tigers|a boys · a girls · a tigers|two boys · a girls · five tigers",
            correctAnswer = "two boys · three girls · five tigers",
            explanationUzbek = "There are bilan son + -s ishlatiladi (a qo‘yilmaydi).",
            vocabularyIdsCsv = "v_a0_18_02,v_a0_18_03",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "There are ___ boys (2) · There are ___ girls (3)\nThere are ___ tigers (5) · There are ___ books (4)",
            subPromptText = "5-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "There are two boys. There are three girls. There are five tigers. There are four books.",
            optionsPipeSeparated = "two / three / five / four|two / four / five / six|one / three / five / four",
            correctAnswer = "two / three / five / four",
            explanationUzbek = "2 = two, 3 = three, 5 = five, 4 = four.",
            vocabularyIdsCsv = "v_a0_18_02,v_a0_18_03,v_a0_18_04",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "There is two boys · There are a girls · There are two boy",
            subPromptText = "6-mashq. Xatoni toping va to‘g‘rilang:",
            audioTextToSpeak = "There are two boys. There are three girls. There are two boys.",
            optionsPipeSeparated = "There are two boys · There are three girls · There are two boys|There is two boys · There are a girls · There are two boy|There are two boy · There are three girl · There are two boys",
            correctAnswer = "There are two boys · There are three girls · There are two boys",
            explanationUzbek = "Ko‘p narsada ‘There are’, son va ot oxirida ‘-s’ (boys, girls) bo‘ladi.",
            vocabularyIdsCsv = "v_a0_18_01,v_a0_18_02",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• are / there / two / boys\n• three / there / are / girls",
            subPromptText = "7-mashq. Gap tuzing:",
            audioTextToSpeak = "There are two boys. There are three girls.",
            optionsPipeSeparated = "There are two boys · There are three girls|Are there two boys · Girls there are three|Two boys there are · Three there are girls",
            correctAnswer = "There are two boys · There are three girls",
            explanationUzbek = "Tartib: There + are + son + ot(-s) → There are two boys. There are three girls.",
            vocabularyIdsCsv = "v_a0_18_01,v_a0_18_02",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Onangiz “Xonada kim bor?”\n• Do‘stingiz “Bog‘da nima bor?”",
            subPromptText = "8-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "There are two boys. There are five tigers.",
            optionsPipeSeparated = "There are two boys. · There are five tigers.|There is two boys. · There is five tigers.|There is a boy. · Here is a tiger.",
            correctAnswer = "There are two boys. · There are five tigers.",
            explanationUzbek = "Xonada ikkita bola va bog‘da beshta yo‘lbars bor: There are two boys. / There are five tigers.",
            vocabularyIdsCsv = "v_a0_18_01,v_a0_18_03",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“There are two boys. There are three girls. There are five tigers.”",
            subPromptText = "9-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "There are two boys. There are three girls. There are five tigers.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! ‘There are’ bilan ko‘p narsalarni ravon aytdingiz.",
            vocabularyIdsCsv = "v_a0_18_01,v_a0_18_02,v_a0_18_03",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "___ a boy · ___ two boys · ___ an apple · ___ five apples",
            subPromptText = "10-mashq. There is yoki there are?",
            audioTextToSpeak = "There is a boy. There are two boys. There is an apple. There are five apples.",
            optionsPipeSeparated = "There is / There are / There is / There are|There are / There is / There are / There is|Here is / There are / Here is / There are",
            correctAnswer = "There is / There are / There is / There are",
            explanationUzbek = "Bitta → There is (a boy, an apple). Ko‘p → There are (two boys, five apples).",
            vocabularyIdsCsv = "v_a0_18_01,v_a0_18_05",
            grammarRuleIdsCsv = "g_a0_18_02"
        ),
        // Lesson 19 exercises (My room — 12 exercises)
        ExerciseEntity(
            id = "ex_a0_19_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Room”",
            subPromptText = "1-mashq. “Room” nima degani?",
            audioTextToSpeak = "Room",
            optionsPipeSeparated = "xona|uy|eshik",
            correctAnswer = "xona",
            explanationUzbek = "Room — xona. My room — mening xonam.",
            vocabularyIdsCsv = "v_a0_19_01",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“In my room”",
            subPromptText = "2-mashq. “In my room” nima degani?",
            audioTextToSpeak = "In my room",
            optionsPipeSeparated = "mening xonamda|mening xonam|xonam yo‘q",
            correctAnswer = "mening xonamda",
            explanationUzbek = "In — ichida. In my room — mening xonamda.",
            vocabularyIdsCsv = "v_a0_19_01",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“See”",
            subPromptText = "3-mashq. “See” nima degani?",
            audioTextToSpeak = "See",
            optionsPipeSeparated = "ko‘rmoq|bermoq|ko‘rsatmoq",
            correctAnswer = "ko‘rmoq",
            explanationUzbek = "See — ko‘rmoq. I see a bed.",
            vocabularyIdsCsv = "v_a0_19_07",
            grammarRuleIdsCsv = "g_a0_19_02"
        ),
        ExerciseEntity(
            id = "ex_a0_19_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Give me”",
            subPromptText = "4-mashq. “Give me” nima degani?",
            audioTextToSpeak = "Give me",
            optionsPipeSeparated = "menga ber|ko‘rmoq|ko‘rsatmoq",
            correctAnswer = "menga ber",
            explanationUzbek = "Give me — menga ber.",
            vocabularyIdsCsv = "v_a0_19_02",
            grammarRuleIdsCsv = "g_a0_19_02"
        ),
        ExerciseEntity(
            id = "ex_a0_19_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Show”",
            subPromptText = "5-mashq. “Show” nima degani?",
            audioTextToSpeak = "Show",
            optionsPipeSeparated = "ko‘rsatmoq|ko‘rmoq|bermoq",
            correctAnswer = "ko‘rsatmoq",
            explanationUzbek = "Show — ko‘rsatmoq. Show me your room.",
            vocabularyIdsCsv = "v_a0_19_01",
            grammarRuleIdsCsv = "g_a0_19_02"
        ),
        ExerciseEntity(
            id = "ex_a0_19_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "I ___ a bed · There ___ a table · There ___ two chairs",
            subPromptText = "6-mashq. Bo‘sh joyni to‘ldiring:",
            audioTextToSpeak = "I have a bed. There is a table. There are two chairs.",
            optionsPipeSeparated = "have / is / are|is / are / have|have / are / is",
            correctAnswer = "have / is / are",
            explanationUzbek = "I have (menda bor), There is (bitta bor), There are (ko‘p bor).",
            vocabularyIdsCsv = "v_a0_19_02,v_a0_19_03,v_a0_19_04",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• I see a bed / I give me a bed\n• Give me a book / Show me a book\n• Show me your room / See me your room",
            subPromptText = "7-mashq. To‘g‘ri variantni tanlang:",
            audioTextToSpeak = "I see a bed. Give me a book. Show me your room.",
            optionsPipeSeparated = "I see a bed · ikkisi ham to‘g‘ri · Show me your room|I give me a bed · Give me a book · See me your room|I see a bed · Show me a book · See me your room",
            correctAnswer = "I see a bed · ikkisi ham to‘g‘ri · Show me your room",
            explanationUzbek = "I see a bed, Give me a book (yoki Show me a book), Show me your room to‘g‘ri.",
            vocabularyIdsCsv = "v_a0_19_02,v_a0_19_07",
            grammarRuleIdsCsv = "g_a0_19_02"
        ),
        ExerciseEntity(
            id = "ex_a0_19_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• See me a book\n• I see two chairs\n• There is two chairs",
            subPromptText = "8-mashq. Xatoni toping:",
            audioTextToSpeak = "Give me a book. I see two chairs. There are two chairs.",
            optionsPipeSeparated = "Give me a book or Show me a book · to‘g‘ri · There are two chairs|To‘g‘ri · See me a book · There is two chairs|Give me a book · I see two chairs · There is two chairs",
            correctAnswer = "Give me a book or Show me a book · to‘g‘ri · There are two chairs",
            explanationUzbek = "‘See me’ emas ‘Give me / Show me’ bo‘ladi. Ko‘p narsada ‘There are two chairs’.",
            vocabularyIdsCsv = "v_a0_19_04,v_a0_19_07",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• have / I / a / bed\n• is / there / a / table / in / my / room\n• me / give / a / book",
            subPromptText = "9-mashq. Gap tuzing (so‘zlarni tartibga soling):",
            audioTextToSpeak = "I have a bed. There is a table in my room. Give me a book.",
            optionsPipeSeparated = "I have a bed · There is a table in my room · Give me a book|Have I a bed · There a table is in my room · Give a book me|I a bed have · Is there a table in my room · Me give a book",
            correctAnswer = "I have a bed · There is a table in my room · Give me a book",
            explanationUzbek = "Tartib: I have a bed. There is a table in my room. Give me a book.",
            vocabularyIdsCsv = "v_a0_19_02,v_a0_19_03",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 2,
            promptText = "• Onangiz “Xonangda nima bor?”\n• Do‘stingiz “Ruchkang bormi?”\n• O‘qituvchi “Xonangni ko‘rsat.”",
            subPromptText = "10-mashq. Hayotiy vaziyat — nima deysiz?",
            audioTextToSpeak = "There is a bed and a table in my room. Give me a pen. Here is my room.",
            optionsPipeSeparated = "There is a bed and a table in my room. · Give me a pen. · Here is my room.|There is a bed. · I have a book. · Show me your room.|I see a bed. · Give me a book. · Here is my bed.",
            correctAnswer = "There is a bed and a table in my room. · Give me a pen. · Here is my room.",
            explanationUzbek = "Xonada nima bor → There is a bed and a table in my room. Ruchka so‘rash → Give me a pen. Xonani ko‘rsatish → Here is my room.",
            vocabularyIdsCsv = "v_a0_19_01,v_a0_19_03",
            grammarRuleIdsCsv = "g_a0_19_02"
        ),
        ExerciseEntity(
            id = "ex_a0_19_11",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I see a bed. Give me a book. Show me your room.”",
            subPromptText = "11-mashq. Ovoz chiqarib o‘qing (3 marta):",
            audioTextToSpeak = "I see a bed. Give me a book. Show me your room.",
            optionsPipeSeparated = "O‘qidim (3 marta)|Keyinroq o‘qiyman|Yana eshitish",
            correctAnswer = "O‘qidim (3 marta)",
            explanationUzbek = "Barakalla! Xona va fe’llar haqidagi gaplarni ravon o‘qidingiz.",
            vocabularyIdsCsv = "v_a0_19_02,v_a0_19_07",
            grammarRuleIdsCsv = "g_a0_19_02"
        ),
        ExerciseEntity(
            id = "ex_a0_19_12",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "SELF_WRITE",
            difficulty = 2,
            promptText = "Xonangiz haqida 3 ta gap yozing:\n1) There is ______ in my room.\n2) There are ______ in my room.\n3) I see ______.",
            subPromptText = "12-mashq. O‘zingiz yozing:",
            audioTextToSpeak = "There is a bed in my room. There are two chairs in my room. I see a window.",
            optionsPipeSeparated = "Submit",
            correctAnswer = "There is a bed in my room. · There are two chairs in my room. · I see a window.",
            explanationUzbek = "Ajoyib! O‘z xonangizni ingliz tilida mukammal tasvirladingiz.",
            vocabularyIdsCsv = "v_a0_19_02,v_a0_19_04,v_a0_19_06",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        // Lesson 25: The shop
        ExerciseEntity(id = "ex_a0_25_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Qaysi artikl 'o'sha aniq' narsani bildiradi?", subPromptText = "1-mashq. Tanlang:", audioTextToSpeak = "The shop", optionsPipeSeparated = "a · an · the", correctAnswer = "the", explanationUzbek = "The — aniq artikl.", vocabularyIdsCsv = "v_a0_25_01", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Qaysi artikl noma'lum narsani bildiradi?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "A shop", optionsPipeSeparated = "the · a / an · none", correctAnswer = "a / an", explanationUzbek = "A / an — noma'lum.", vocabularyIdsCsv = "v_a0_25_02", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I have ______ book. (birinchi marta)", subPromptText = "3-mashq. To'ldiring:", audioTextToSpeak = "I have a book.", optionsPipeSeparated = "a · the", correctAnswer = "a", explanationUzbek = "Birinchi marta — a.", vocabularyIdsCsv = "v_a0_25_01", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "______ book is good. (o'sha kitob)", subPromptText = "4-mashq. To'ldiring:", audioTextToSpeak = "The book is good.", optionsPipeSeparated = "A · The", correctAnswer = "The", explanationUzbek = "Aniq kitob — The.", vocabularyIdsCsv = "v_a0_25_01", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "The artikli ko'plikdagi otlar bilan ishlaydimi?", subPromptText = "5-mashq. Tanlang:", audioTextToSpeak = "The books", optionsPipeSeparated = "Ha · Yo'q", correctAnswer = "Ha", explanationUzbek = "The ham birlik, ham ko'plik bilan ishlaydi.", vocabularyIdsCsv = "v_a0_25_01", grammarRuleIdsCsv = "g_a0_25_02"),
        ExerciseEntity(id = "ex_a0_25_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "I will go to ______ shop. (aniq do'kon)", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "I will go to the shop.", optionsPipeSeparated = "a · the", correctAnswer = "the", explanationUzbek = "Aniq do'kon — the shop.", vocabularyIdsCsv = "v_a0_25_02", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "TRUE_FALSE", difficulty = 1, promptText = "'The' faqat bitta narsa bilan ishlatiladi.", subPromptText = "7-mashq. Ha / Yo'q:", audioTextToSpeak = "The book and books", optionsPipeSeparated = "To'g'ri · Noto'g'ri", correctAnswer = "Noto'g'ri", explanationUzbek = "The ham bitta, ham ko'p narsa bilan ishlaydi.", vocabularyIdsCsv = "v_a0_25_01", grammarRuleIdsCsv = "g_a0_25_02"),
        ExerciseEntity(id = "ex_a0_25_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MATCHING", difficulty = 2, promptText = "Ma'noni moslashtiring:", subPromptText = "8-mashq. Moslashtiring:", audioTextToSpeak = "A book / The book", optionsPipeSeparated = "a book (noma'lum) · the book (aniq)", correctAnswer = "a book (noma'lum) · the book (aniq)", explanationUzbek = "A/an va the farqi shunda.", vocabularyIdsCsv = "v_a0_25_01", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I will go to the shop. The book is on the table.", optionsPipeSeparated = "O'qish", correctAnswer = "O'qish", explanationUzbek = "Ajoyib talaffuz!", vocabularyIdsCsv = "v_a0_25_01,v_a0_25_02", grammarRuleIdsCsv = "g_a0_25_01"),
        ExerciseEntity(id = "ex_a0_25_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_25_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "Gap yozing:\n1) I will go to the ___.\n2) The ___ is on the table.", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I will go to the shop. The book is on the table.", optionsPipeSeparated = "Submit", correctAnswer = "I will go to the shop. · The book is on the table.", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_25_01,v_a0_25_02", grammarRuleIdsCsv = "g_a0_25_01"),

        // Lesson 26: Will
        ExerciseEntity(id = "ex_a0_26_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Kelasi zamon belgisi qaysi?", subPromptText = "1-mashq. Tanlang:", audioTextToSpeak = "Will", optionsPipeSeparated = "am · will · is", correctAnswer = "will", explanationUzbek = "Will — kelasi zamon.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I ______ go to the shop. (boraman)", subPromptText = "2-mashq. To'ldiring:", audioTextToSpeak = "I will go.", optionsPipeSeparated = "will · am", correctAnswer = "will", explanationUzbek = "Darak shaklida will fe'ldan oldin keladi.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I will ______ go to the shop. (bormayman)", subPromptText = "3-mashq. To'ldiring:", audioTextToSpeak = "I will not go.", optionsPipeSeparated = "not · no", correctAnswer = "not", explanationUzbek = "Inkor uchun will dan keyin not keladi.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol shaklida will qayerda turadi?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "Will I go?", optionsPipeSeparated = "Oldinda · O'rtada · Oxirida", correctAnswer = "Oldinda", explanationUzbek = "Savol uchun will boshiga chiqadi.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "TRUE_FALSE", difficulty = 1, promptText = "Will dan keyin fe'l o'zgaradi (-s qo'shiladi).", subPromptText = "5-mashq. Ha / Yo'q:", audioTextToSpeak = "He will go", optionsPipeSeparated = "To'g'ri · Noto'g'ri", correctAnswer = "Noto'g'ri", explanationUzbek = "Will dan keyin fe'l o'zgarmaydi.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_02"),
        ExerciseEntity(id = "ex_a0_26_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "To'g'ri shaklni tanlang: He will ______.", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "He will go", optionsPipeSeparated = "goes · go · going", correctAnswer = "go", explanationUzbek = "Faqat go.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_02"),
        ExerciseEntity(id = "ex_a0_26_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MATCHING", difficulty = 2, promptText = "Shakllarni moslashtiring:", subPromptText = "7-mashq. Moslashtiring:", audioTextToSpeak = "I will go / I will not go / Will I go?", optionsPipeSeparated = "darak · inkor · savol", correctAnswer = "darak · inkor · savol", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 2, promptText = "______ I go to the shop? (boramanmi)", subPromptText = "8-mashq. To'ldiring:", audioTextToSpeak = "Will I go?", optionsPipeSeparated = "Will · Do", correctAnswer = "Will", explanationUzbek = "Will oldinda.", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I will go. I will not go. Will I go?", optionsPipeSeparated = "O'qish", correctAnswer = "O'qish", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),
        ExerciseEntity(id = "ex_a0_26_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_26_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing (darak, inkor, savol):", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I will go. I will not go. Will I go?", optionsPipeSeparated = "Submit", correctAnswer = "I will go. · I will not go. · Will I go?", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_26_01", grammarRuleIdsCsv = "g_a0_26_01"),

        // Lesson 27: Can
        ExerciseEntity(id = "ex_a0_27_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "'Can' nimani bildiradi?", subPromptText = "1-mashq. Tanlang:", audioTextToSpeak = "Can", optionsPipeSeparated = "imkoniyat (qila olaman) · majburiyat · ruxsat", correctAnswer = "imkoniyat (qila olaman)", explanationUzbek = "Can imkoniyatni bildiradi.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_01"),
        ExerciseEntity(id = "ex_a0_27_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I ______ go to the shop. (bora olaman)", subPromptText = "2-mashq. To'ldiring:", audioTextToSpeak = "I can go.", optionsPipeSeparated = "can · will", correctAnswer = "can", explanationUzbek = "Bora olaman — can.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_01"),
        ExerciseEntity(id = "ex_a0_27_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I can ______ go. (bora olmayman)", subPromptText = "3-mashq. To'ldiring:", audioTextToSpeak = "I can not go.", optionsPipeSeparated = "not · no", correctAnswer = "not", explanationUzbek = "Inkor uchun can not.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_02"),
        ExerciseEntity(id = "ex_a0_27_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol uchun can qayerga chiqadi?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "Can I go?", optionsPipeSeparated = "Oldinda · Oxirida", correctAnswer = "Oldinda", explanationUzbek = "Can oldinga chiqadi.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_02"),
        ExerciseEntity(id = "ex_a0_27_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "TRUE_FALSE", difficulty = 1, promptText = "Can dan keyin fe'lga -s qo'shiladi.", subPromptText = "5-mashq. Ha / Yo'q:", audioTextToSpeak = "He can go", optionsPipeSeparated = "To'g'ri · Noto'g'ri", correctAnswer = "Noto'g'ri", explanationUzbek = "Fe'l o'zgarmaydi.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_01"),
        ExerciseEntity(id = "ex_a0_27_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "To'g'ri gapni tanlang:", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "I can go", optionsPipeSeparated = "I can go · I can goes", correctAnswer = "I can go", explanationUzbek = "I can go.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_01"),
        ExerciseEntity(id = "ex_a0_27_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MATCHING", difficulty = 2, promptText = "Moslashtiring:", subPromptText = "7-mashq. Moslashtiring:", audioTextToSpeak = "I can go / I can not go / Can I go?", optionsPipeSeparated = "darak · inkor · savol", correctAnswer = "darak · inkor · savol", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_02"),
        ExerciseEntity(id = "ex_a0_27_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 2, promptText = "______ I see a tiger? (ko'ra olamanmi?)", subPromptText = "8-mashq. To'ldiring:", audioTextToSpeak = "Can I see a tiger?", optionsPipeSeparated = "Can · Will", correctAnswer = "Can", explanationUzbek = "Can oldinda.", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_02"),
        ExerciseEntity(id = "ex_a0_27_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I can go. I can not go. Can I go? Yes, I can.", optionsPipeSeparated = "O'qish", correctAnswer = "O'qish", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_01"),
        ExerciseEntity(id = "ex_a0_27_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_27_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I can go. I can not go. Can I go?", optionsPipeSeparated = "Submit", correctAnswer = "I can go. · I can not go. · Can I go?", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_27_01", grammarRuleIdsCsv = "g_a0_27_01"),

        // Lesson 28: Must
        ExerciseEntity(id = "ex_a0_28_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "'Must' nimani bildiradi?", subPromptText = "1-mashq. Tanlang:", audioTextToSpeak = "Must", optionsPipeSeparated = "majburiyat (kerak/shart) · imkoniyat · ruxsat", correctAnswer = "majburiyat (kerak/shart)", explanationUzbek = "Must majburiyat bildiradi.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I ______ go to the shop. (borishim kerak)", subPromptText = "2-mashq. To'ldiring:", audioTextToSpeak = "I must go.", optionsPipeSeparated = "must · can", correctAnswer = "must", explanationUzbek = "Borishim kerak — must.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "'Must not' nimani bildiradi?", subPromptText = "3-mashq. Tanlang:", audioTextToSpeak = "Must not", optionsPipeSeparated = "taqiq (mumkin emas) · shart emas", correctAnswer = "taqiq (mumkin emas)", explanationUzbek = "Must not — qat'iy taqiq.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_02"),
        ExerciseEntity(id = "ex_a0_28_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I must ______ go. (bormasligim kerak / taqiq)", subPromptText = "4-mashq. To'ldiring:", audioTextToSpeak = "I must not go.", optionsPipeSeparated = "not · no", correctAnswer = "not", explanationUzbek = "Must not.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_02"),
        ExerciseEntity(id = "ex_a0_28_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "TRUE_FALSE", difficulty = 1, promptText = "Must dan keyin fe'l o'zgarmaydi.", subPromptText = "5-mashq. Ha / Yo'q:", audioTextToSpeak = "He must go", optionsPipeSeparated = "To'g'ri · Noto'g'ri", correctAnswer = "To'g'ri", explanationUzbek = "To'g'ri, fe'l o'zgarmaydi.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol shaklida must qayerda turadi?", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "Must I go?", optionsPipeSeparated = "Oldinda · O'rtada", correctAnswer = "Oldinda", explanationUzbek = "Must oldinda.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MATCHING", difficulty = 2, promptText = "Moslashtiring:", subPromptText = "7-mashq. Moslashtiring:", audioTextToSpeak = "I must go / I must not go / Must I go?", optionsPipeSeparated = "darak · inkor (taqiq) · savol", correctAnswer = "darak · inkor (taqiq) · savol", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 2, promptText = "______ I see a doctor? (ko'rishishim kerakmi?)", subPromptText = "8-mashq. To'ldiring:", audioTextToSpeak = "Must I see a doctor?", optionsPipeSeparated = "Must · Can", correctAnswer = "Must", explanationUzbek = "Must oldinda.", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I must go. I must not go. Must I go?", optionsPipeSeparated = "O'qish", correctAnswer = "O'qish", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),
        ExerciseEntity(id = "ex_a0_28_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_28_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I must go. I must not go. Must I go?", optionsPipeSeparated = "Submit", correctAnswer = "I must go. · I must not go. · Must I go?", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_28_01", grammarRuleIdsCsv = "g_a0_28_01"),

        // Lesson 29: May
        ExerciseEntity(id = "ex_a0_29_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "'May' nimani bildiradi?", subPromptText = "1-mashq. Tanlang:", audioTextToSpeak = "May", optionsPipeSeparated = "ruxsat / ijozat · majburiyat · imkoniyat", correctAnswer = "ruxsat / ijozat", explanationUzbek = "May ruxsatni bildiradi.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "______ I go to the shop? (borsam maylimi / odobli)", subPromptText = "2-mashq. To'ldiring:", audioTextToSpeak = "May I go?", optionsPipeSeparated = "May · Must", correctAnswer = "May", explanationUzbek = "May I go? — odobli ruxsat.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "You may ______ go. (mumkin emas)", subPromptText = "3-mashq. To'ldiring:", audioTextToSpeak = "You may not go.", optionsPipeSeparated = "not · no", correctAnswer = "not", explanationUzbek = "May not.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Can va May orasida qanday farq bor?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "May vs Can", optionsPipeSeparated = "May odobliroq va rasmiyroq · Farqi yo'q", correctAnswer = "May odobliroq va rasmiyroq", explanationUzbek = "May odobliroq.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_02"),
        ExerciseEntity(id = "ex_a0_29_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "TRUE_FALSE", difficulty = 1, promptText = "May dan keyin fe'l o'zgarmaydi.", subPromptText = "5-mashq. Ha / Yo'q:", audioTextToSpeak = "You may go", optionsPipeSeparated = "To'g'ri · Noto'g'ri", correctAnswer = "To'g'ri", explanationUzbek = "To'g'ri.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "To'g'ri javobni tanlang: May I go? -> ______", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "Yes, you may", optionsPipeSeparated = "Yes, you may. · Yes, you can go.", correctAnswer = "Yes, you may.", explanationUzbek = "Yes, you may.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MATCHING", difficulty = 2, promptText = "Moslashtiring:", subPromptText = "7-mashq. Moslashtiring:", audioTextToSpeak = "You may go / You may not go / May I go?", optionsPipeSeparated = "darak · inkor · savol", correctAnswer = "darak · inkor · savol", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 2, promptText = "______ I go? (borsam maylimi)", subPromptText = "8-mashq. To'ldiring:", audioTextToSpeak = "May I go?", optionsPipeSeparated = "May · Do", correctAnswer = "May", explanationUzbek = "May oldinda.", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I may go. I may not go. May I go?", optionsPipeSeparated = "O'qish", correctAnswer = "O'qish", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),
        ExerciseEntity(id = "ex_a0_29_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_29_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I may go. I may not go. May I go?", optionsPipeSeparated = "Submit", correctAnswer = "I may go. · I may not go. · May I go?", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_29_01", grammarRuleIdsCsv = "g_a0_29_01"),

        // Lesson 30: Have to
        ExerciseEntity(id = "ex_a0_30_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "'Have to' nimani bildiradi?", subPromptText = "1-mashq. Tanlang:", audioTextToSpeak = "Have to", optionsPipeSeparated = "tashqi majburiyat (kerak) · imkoniyat · ruxsat", correctAnswer = "tashqi majburiyat (kerak)", explanationUzbek = "Have to tashqi majburiyat.", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),
        ExerciseEntity(id = "ex_a0_30_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "He ______ to go. (borishi kerak)", subPromptText = "2-mashq. To'ldiring:", audioTextToSpeak = "He has to go.", optionsPipeSeparated = "has · have", correctAnswer = "has", explanationUzbek = "He, she, it bilan has to ishlatiladi.", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),
        ExerciseEntity(id = "ex_a0_30_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I ______ not have to go. (shart emas)", subPromptText = "3-mashq. To'ldiring:", audioTextToSpeak = "I do not have to go.", optionsPipeSeparated = "do · does", correctAnswer = "do", explanationUzbek = "I bilan do not have to.", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_02"),
        ExerciseEntity(id = "ex_a0_30_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "He uchun inkor qanday qilinadi?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "He does not have to", optionsPipeSeparated = "He does not have to · He do not have to", correctAnswer = "He does not have to", explanationUzbek = "He uchun does not.", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_02"),
        ExerciseEntity(id = "ex_a0_30_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "TRUE_FALSE", difficulty = 1, promptText = "'Do not have to' — bu taqiq (mumkin emas) emas, balki majburiyat yo'qligi (shart emas).", subPromptText = "5-mashq. Ha / Yo'q:", audioTextToSpeak = "Do not have to", optionsPipeSeparated = "To'g'ri · Noto'g'ri", correctAnswer = "To'g'ri", explanationUzbek = "To'g'ri, bu shart emas degani.", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_02"),
        ExerciseEntity(id = "ex_a0_30_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol uchun qaysi so'z ishlatiladi (I bilan)?", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "Do I have to go?", optionsPipeSeparated = "Do · Does", correctAnswer = "Do", explanationUzbek = "Do I have to...", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),
        ExerciseEntity(id = "ex_a0_30_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MATCHING", difficulty = 2, promptText = "Moslashtiring:", subPromptText = "7-mashq. Moslashtiring:", audioTextToSpeak = "I have to / I do not have to / Do I have to?", optionsPipeSeparated = "darak · inkor (shart emas) · savol", correctAnswer = "darak · inkor (shart emas) · savol", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),
        ExerciseEntity(id = "ex_a0_30_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 2, promptText = "______ he have to go? (shartmi)", subPromptText = "8-mashq. To'ldiring:", audioTextToSpeak = "Does he have to go?", optionsPipeSeparated = "Does · Do", correctAnswer = "Does", explanationUzbek = "He uchun Does.", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),
        ExerciseEntity(id = "ex_a0_30_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I have to go. I do not have to go. Do I have to go? Yes, I do.", optionsPipeSeparated = "O'qish", correctAnswer = "O'qish", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),
        ExerciseEntity(id = "ex_a0_30_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_30_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I have to go. I do not have to go. Do I have to go?", optionsPipeSeparated = "Submit", correctAnswer = "I have to go. · I do not have to go. · Do I have to go?", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_30_01", grammarRuleIdsCsv = "g_a0_30_01"),

        // Lesson 31: Big (Sifat oddiy daraja)
        ExerciseEntity(id = "ex_a0_31_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Sifat qanday savolga javob beradi?", subPromptText = "1-mashq. To'g'ri javobni tanlang:", audioTextToSpeak = "How", optionsPipeSeparated = "kim? · qanday? · nechta?", correctAnswer = "qanday?", explanationUzbek = "Sifat narsaning belgisini bildiradi va 'qanday?' savoliga javob beradi.", vocabularyIdsCsv = "v_a0_31_01", grammarRuleIdsCsv = "g_a0_31_01"),
        ExerciseEntity(id = "ex_a0_31_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Big” so'zining ma'nosi nima?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "Big", optionsPipeSeparated = "kichik · katta · shirin", correctAnswer = "katta", explanationUzbek = "'big' — katta degani.", vocabularyIdsCsv = "v_a0_31_01", grammarRuleIdsCsv = "g_a0_31_01"),
        ExerciseEntity(id = "ex_a0_31_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Sweet” so'zining ma'nosi nima?", subPromptText = "3-mashq. Tanlang:", audioTextToSpeak = "Sweet", optionsPipeSeparated = "shirin · nordon · issiq", correctAnswer = "shirin", explanationUzbek = "'sweet' — shirin degani.", vocabularyIdsCsv = "v_a0_31_03", grammarRuleIdsCsv = "g_a0_31_01"),
        ExerciseEntity(id = "ex_a0_31_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Soft” so'zining ma'nosi nima?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "Soft", optionsPipeSeparated = "qattiq · yumshoq · sovuq", correctAnswer = "yumshoq", explanationUzbek = "'soft' — yumshoq degani.", vocabularyIdsCsv = "v_a0_31_06", grammarRuleIdsCsv = "g_a0_31_01"),
        ExerciseEntity(id = "ex_a0_31_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "The book ______ big.", subPromptText = "5-mashq. Bitta kitob uchun to'g'ri fe'lni tanlang:", audioTextToSpeak = "The book is big.", optionsPipeSeparated = "is · are", correctAnswer = "is", explanationUzbek = "Bitta narsa uchun 'is' ishlatiladi: The book is big.", vocabularyIdsCsv = "v_a0_31_01", grammarRuleIdsCsv = "g_a0_31_02"),
        ExerciseEntity(id = "ex_a0_31_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "The apples ______ sweet.", subPromptText = "6-mashq. Ko'p olmalar uchun to'g'ri fe'lni tanlang:", audioTextToSpeak = "The apples are sweet.", optionsPipeSeparated = "are · is", correctAnswer = "are", explanationUzbek = "Ko'p narsa uchun 'are' ishlatiladi: The apples are sweet.", vocabularyIdsCsv = "v_a0_31_03", grammarRuleIdsCsv = "g_a0_31_02"),
        ExerciseEntity(id = "ex_a0_31_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol shaklini toping: “The book is big.”", subPromptText = "7-mashq. Savolga aylantiring:", audioTextToSpeak = "Is the book big?", optionsPipeSeparated = "Is the book big? · Are the book big?", correctAnswer = "Is the book big?", explanationUzbek = "Savol berishda 'is' egadan oldinga chiqadi.", vocabularyIdsCsv = "v_a0_31_01", grammarRuleIdsCsv = "g_a0_31_02"),
        ExerciseEntity(id = "ex_a0_31_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Inkor shaklini toping: “The apple is sweet.”", subPromptText = "8-mashq. Inkorga aylantiring:", audioTextToSpeak = "The apple is not sweet.", optionsPipeSeparated = "The apple is not sweet. · The apple no sweet.", correctAnswer = "The apple is not sweet.", explanationUzbek = "Inkor uchun 'not' qo'shiladi: The apple is not sweet.", vocabularyIdsCsv = "v_a0_31_03", grammarRuleIdsCsv = "g_a0_31_02"),
        ExerciseEntity(id = "ex_a0_31_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. Ovoz chiqarib o'qing (3 marta):", audioTextToSpeak = "The book is big. The apple is sweet. The tea is hot.", optionsPipeSeparated = "O'qidim · Keyinroq", correctAnswer = "O'qidim", explanationUzbek = "Ajoyib talaffuz!", vocabularyIdsCsv = "v_a0_31_01,v_a0_31_03", grammarRuleIdsCsv = "g_a0_31_02"),
        ExerciseEntity(id = "ex_a0_31_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_31_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:\n1) The book is big.\n2) Is the apple sweet?\n3) The tea is not hot.", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "The book is big. Is the apple sweet? The tea is not hot.", optionsPipeSeparated = "Submit", correctAnswer = "The book is big. · Is the apple sweet? · The tea is not hot.", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_31_01,v_a0_31_03", grammarRuleIdsCsv = "g_a0_31_02"),

        // Lesson 32: Bigger (Qiyosiy daraja)
        ExerciseEntity(id = "ex_a0_32_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Qiyosiy daraja qanday savolga javob beradi?", subPromptText = "1-mashq. To'g'ri javobni tanlang:", audioTextToSpeak = "Which is more?", optionsPipeSeparated = "qanday? · qandayroq? · eng qanday?", correctAnswer = "qandayroq?", explanationUzbek = "Qiyosiy daraja 'qandayroq?' savoliga javob beradi.", vocabularyIdsCsv = "v_a0_32_01", grammarRuleIdsCsv = "g_a0_32_01"),
        ExerciseEntity(id = "ex_a0_32_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Bigger” nima degani?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "Bigger", optionsPipeSeparated = "katta · kattaroq · eng katta", correctAnswer = "kattaroq", explanationUzbek = "'bigger' — kattaroq degani.", vocabularyIdsCsv = "v_a0_32_01", grammarRuleIdsCsv = "g_a0_32_01"),
        ExerciseEntity(id = "ex_a0_32_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Small” sifatiga -er qo'shing:", subPromptText = "3-mashq. Tanlang:", audioTextToSpeak = "Smaller", optionsPipeSeparated = "smaller · smalling · smallest", correctAnswer = "smaller", explanationUzbek = "small -> smaller (kichikroq).", vocabularyIdsCsv = "v_a0_32_02", grammarRuleIdsCsv = "g_a0_32_01"),
        ExerciseEntity(id = "ex_a0_32_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Uzun sifatlar (beautiful) oldiga nima qo'yiladi?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "More beautiful", optionsPipeSeparated = "more · -er · -est", correctAnswer = "more", explanationUzbek = "Uzun so'zlar oldiga 'more' qo'yiladi.", vocabularyIdsCsv = "v_a0_32_03,v_a0_32_05", grammarRuleIdsCsv = "g_a0_32_01"),
        ExerciseEntity(id = "ex_a0_32_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "The book is bigger ______ the cup.", subPromptText = "5-mashq. Solishtirish bog'lovchisini qo'ying:", audioTextToSpeak = "The book is bigger than the cup.", optionsPipeSeparated = "than · that", correctAnswer = "than", explanationUzbek = "Solishtirishda 'than' (-dan ko'ra) ishlatiladi.", vocabularyIdsCsv = "v_a0_32_01,v_a0_32_07", grammarRuleIdsCsv = "g_a0_32_02"),
        ExerciseEntity(id = "ex_a0_32_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "She is ______ beautiful than her sister.", subPromptText = "6-mashq. To'ldiring:", audioTextToSpeak = "She is more beautiful than her sister.", optionsPipeSeparated = "more · most", correctAnswer = "more", explanationUzbek = "Uzun sifat bilan qiyoslashda 'more' ishlatiladi.", vocabularyIdsCsv = "v_a0_32_03,v_a0_32_05,v_a0_32_07", grammarRuleIdsCsv = "g_a0_32_01"),
        ExerciseEntity(id = "ex_a0_32_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol shaklini toping: “She is more beautiful than her sister.”", subPromptText = "7-mashq. Savolga aylantiring:", audioTextToSpeak = "Is she more beautiful than her sister?", optionsPipeSeparated = "Is she more beautiful than her sister? · Are she more beautiful than her sister?", correctAnswer = "Is she more beautiful than her sister?", explanationUzbek = "Is egadan oldinga chiqadi.", vocabularyIdsCsv = "v_a0_32_05,v_a0_32_07", grammarRuleIdsCsv = "g_a0_32_02"),
        ExerciseEntity(id = "ex_a0_32_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Inkor shaklini toping: “The book is bigger than the cup.”", subPromptText = "8-mashq. Inkorga aylantiring:", audioTextToSpeak = "The book is not bigger than the cup.", optionsPipeSeparated = "The book is not bigger than the cup. · The book no bigger than the cup.", correctAnswer = "The book is not bigger than the cup.", explanationUzbek = "Inkor: The book is not bigger than the cup.", vocabularyIdsCsv = "v_a0_32_01,v_a0_32_07", grammarRuleIdsCsv = "g_a0_32_02"),
        ExerciseEntity(id = "ex_a0_32_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "The book is bigger than the cup. She is more beautiful than her sister.", optionsPipeSeparated = "O'qidim · Keyinroq", correctAnswer = "O'qidim", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_32_01,v_a0_32_05", grammarRuleIdsCsv = "g_a0_32_01"),
        ExerciseEntity(id = "ex_a0_32_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_32_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:\n1) The book is bigger than the cup.\n2) She is more beautiful than her sister.\n3) Is the tiger faster than the cat?", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "The book is bigger than the cup. She is more beautiful than her sister. Is the tiger faster than the cat?", optionsPipeSeparated = "Submit", correctAnswer = "The book is bigger than the cup. · She is more beautiful than her sister. · Is the tiger faster than the cat?", explanationUzbek = "Zo'r!", vocabularyIdsCsv = "v_a0_32_01,v_a0_32_05", grammarRuleIdsCsv = "g_a0_32_01"),

        // Lesson 33: The biggest (Orttirma daraja)
        ExerciseEntity(id = "ex_a0_33_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Orttirma daraja qanday savolga javob beradi?", subPromptText = "1-mashq. To'g'ri javobni tanlang:", audioTextToSpeak = "Which is the most?", optionsPipeSeparated = "qanday? · qandayroq? · eng qanday?", correctAnswer = "eng qanday?", explanationUzbek = "Orttirma daraja 'eng qanday?' savoliga javob beradi.", vocabularyIdsCsv = "v_a0_33_01", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“The biggest” nima degani?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "The biggest", optionsPipeSeparated = "katta · kattaroq · eng katta", correctAnswer = "eng katta", explanationUzbek = "'the biggest' — eng katta degani.", vocabularyIdsCsv = "v_a0_33_01", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Fast” sifatining orttirma darajasi qaysi?", subPromptText = "3-mashq. Tanlang:", audioTextToSpeak = "The fastest", optionsPipeSeparated = "the fastest · faster · fastest", correctAnswer = "the fastest", explanationUzbek = "the fastest — eng tez.", vocabularyIdsCsv = "v_a0_33_04", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Uzun sifatlar (beautiful) oldiga nima qo'yiladi?", subPromptText = "4-mashq. Tanlang:", audioTextToSpeak = "The most beautiful", optionsPipeSeparated = "the most · the more · most", correctAnswer = "the most", explanationUzbek = "Uzun sifatlar oldiga 'the most' qo'yiladi.", vocabularyIdsCsv = "v_a0_33_03,v_a0_33_05", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "______ biggest book in the room.", subPromptText = "5-mashq. Orttirma darajada qaysi artikl har doim qo'yiladi?", audioTextToSpeak = "The biggest", optionsPipeSeparated = "The · A", correctAnswer = "The", explanationUzbek = "Orttirma darajada har doim 'the' qo'yiladi.", vocabularyIdsCsv = "v_a0_33_01", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "She is ______ beautiful girl in the class.", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "She is the most beautiful girl in the class.", optionsPipeSeparated = "the most · more", correctAnswer = "the most", explanationUzbek = "Ortirma darajada 'the most' qo'yiladi.", vocabularyIdsCsv = "v_a0_33_03,v_a0_33_05", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol shaklini toping: “She is the most beautiful girl.”", subPromptText = "7-mashq. Savolga aylantiring:", audioTextToSpeak = "Is she the most beautiful girl?", optionsPipeSeparated = "Is she the most beautiful girl? · Are she the most beautiful girl?", correctAnswer = "Is she the most beautiful girl?", explanationUzbek = "Is egadan oldinga chiqadi.", vocabularyIdsCsv = "v_a0_33_05", grammarRuleIdsCsv = "g_a0_33_02"),
        ExerciseEntity(id = "ex_a0_33_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Inkor shaklini toping: “The book is the biggest.”", subPromptText = "8-mashq. Inkorga aylantiring:", audioTextToSpeak = "The book is not the biggest.", optionsPipeSeparated = "The book is not the biggest. · The book no the biggest.", correctAnswer = "The book is not the biggest.", explanationUzbek = "Inkor: The book is not the biggest.", vocabularyIdsCsv = "v_a0_33_01", grammarRuleIdsCsv = "g_a0_33_02"),
        ExerciseEntity(id = "ex_a0_33_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "The book is the biggest. She is the most beautiful girl.", optionsPipeSeparated = "O'qidim · Keyinroq", correctAnswer = "O'qidim", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_33_01,v_a0_33_05", grammarRuleIdsCsv = "g_a0_33_01"),
        ExerciseEntity(id = "ex_a0_33_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_33_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:\n1) The book is the biggest.\n2) She is the most beautiful girl.\n3) Is the tiger the fastest?", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "The book is the biggest. She is the most beautiful girl. Is the tiger the fastest?", optionsPipeSeparated = "Submit", correctAnswer = "The book is the biggest. · She is the most beautiful girl. · Is the tiger the fastest?", explanationUzbek = "Tabriklaymiz! Sifatlarning barcha 3 ta darajasini to'liq o'zlashtirdingiz!", vocabularyIdsCsv = "v_a0_33_01,v_a0_33_05", grammarRuleIdsCsv = "g_a0_33_01"),

        // Lesson 34: I do my homework (“Do” qilmoq)
        ExerciseEntity(id = "ex_a0_34_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Do” nima degani?", subPromptText = "1-mashq. To'g'ri javobni tanlang:", audioTextToSpeak = "Do", optionsPipeSeparated = "bormoq · qilmoq · ko'rmoq", correctAnswer = "qilmoq", explanationUzbek = "'do' — qilmoq degani.", vocabularyIdsCsv = "v_a0_34_01", grammarRuleIdsCsv = "g_a0_34_01"),
        ExerciseEntity(id = "ex_a0_34_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“He” bilan do fe'lining qaysi shakli ishlatiladi?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "He does", optionsPipeSeparated = "do · does", correctAnswer = "does", explanationUzbek = "he, she, it bilan 'does' bo'ladi.", vocabularyIdsCsv = "v_a0_34_02", grammarRuleIdsCsv = "g_a0_34_02"),
        ExerciseEntity(id = "ex_a0_34_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I ______ my homework.", subPromptText = "3-mashq. Bo'sh joyni to'ldiring:", audioTextToSpeak = "I do my homework.", optionsPipeSeparated = "do · does", correctAnswer = "do", explanationUzbek = "I bilan 'do' ishlatiladi.", vocabularyIdsCsv = "v_a0_34_01,v_a0_34_04", grammarRuleIdsCsv = "g_a0_34_01"),
        ExerciseEntity(id = "ex_a0_34_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "He ______ his homework.", subPromptText = "4-mashq. Bo'sh joyni to'ldiring:", audioTextToSpeak = "He does his homework.", optionsPipeSeparated = "do · does", correctAnswer = "does", explanationUzbek = "He bilan 'does' ishlatiladi.", vocabularyIdsCsv = "v_a0_34_02,v_a0_34_05", grammarRuleIdsCsv = "g_a0_34_02"),
        ExerciseEntity(id = "ex_a0_34_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Darak gap tuzing: (I / do / my homework)", subPromptText = "5-mashq. Tanlang:", audioTextToSpeak = "I do my homework.", optionsPipeSeparated = "I do my homework. · I does my homework.", correctAnswer = "I do my homework.", explanationUzbek = "To'g'ri shakl: I do my homework.", vocabularyIdsCsv = "v_a0_34_01,v_a0_34_04", grammarRuleIdsCsv = "g_a0_34_01"),
        ExerciseEntity(id = "ex_a0_34_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Savol shaklini toping: “He does his homework.”", subPromptText = "6-mashq. Savolga aylantiring:", audioTextToSpeak = "Does he do his homework?", optionsPipeSeparated = "Does he do his homework? · Do he do his homework?", correctAnswer = "Does he do his homework?", explanationUzbek = "He bilan savolda 'Does' ishlatiladi.", vocabularyIdsCsv = "v_a0_34_02,v_a0_34_05", grammarRuleIdsCsv = "g_a0_34_02"),
        ExerciseEntity(id = "ex_a0_34_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Inkor shaklini toping: “I do my homework.”", subPromptText = "7-mashq. Inkorga aylantiring:", audioTextToSpeak = "I do not do my homework.", optionsPipeSeparated = "I do not do my homework. · I not do my homework.", correctAnswer = "I do not do my homework.", explanationUzbek = "Inkor shakl: I do not do my homework.", vocabularyIdsCsv = "v_a0_34_01,v_a0_34_04", grammarRuleIdsCsv = "g_a0_34_01"),
        ExerciseEntity(id = "ex_a0_34_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Xatoni toping: “He do his homework.”", subPromptText = "8-mashq. To'g'ri variantni tanlang:", audioTextToSpeak = "He does his homework.", optionsPipeSeparated = "He does his homework · He doing his homework", correctAnswer = "He does his homework", explanationUzbek = "He bilan 'does' bo'ladi.", vocabularyIdsCsv = "v_a0_34_02,v_a0_34_05", grammarRuleIdsCsv = "g_a0_34_02"),
        ExerciseEntity(id = "ex_a0_34_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I do my homework. He does his homework. We do our homework.", optionsPipeSeparated = "O'qidim · Keyinroq", correctAnswer = "O'qidim", explanationUzbek = "Ajoyib talaffuz!", vocabularyIdsCsv = "v_a0_34_01,v_a0_34_02,v_a0_34_07", grammarRuleIdsCsv = "g_a0_34_01"),
        ExerciseEntity(id = "ex_a0_34_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_34_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta gap yozing:\n1) I do my homework.\n2) He does his homework.\n3) Do I do my homework?", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I do my homework. He does his homework. Do I do my homework?", optionsPipeSeparated = "Submit", correctAnswer = "I do my homework. · He does his homework. · Do I do my homework?", explanationUzbek = "Barakalla!", vocabularyIdsCsv = "v_a0_34_01,v_a0_34_02", grammarRuleIdsCsv = "g_a0_34_01"),

        // Lesson 35: Do you like...? (“Do” savolda)
        ExerciseEntity(id = "ex_a0_35_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Savolda “do” qayerda turadi?", subPromptText = "1-mashq. To'g'ri javobni tanlang:", audioTextToSpeak = "Do you like apples?", optionsPipeSeparated = "oxirida · o'rtada · boshida", correctAnswer = "boshida", explanationUzbek = "Savolda yordamchi fe'l (Do/Does) gap boshida keladi.", vocabularyIdsCsv = "v_a0_35_01,v_a0_35_02", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“He” bilan savolda qaysi shakl ishlatiladi?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "Does he like cats?", optionsPipeSeparated = "do · does", correctAnswer = "does", explanationUzbek = "he, she, it bilan savolda 'does' ishlatiladi.", vocabularyIdsCsv = "v_a0_35_03", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Do” dan keyin asosiy fe'l qanday bo'ladi?", subPromptText = "3-mashq. Tanlang:", audioTextToSpeak = "Do you like apples?", optionsPipeSeparated = "-s qo'shiladi · o'zgarmaydi", correctAnswer = "o'zgarmaydi", explanationUzbek = "Do/Does dan keyin asosiy fe'l o'zgarmaydi.", vocabularyIdsCsv = "v_a0_35_01", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "______ you like apples?", subPromptText = "4-mashq. Bo'sh joyni to'ldiring:", audioTextToSpeak = "Do you like apples?", optionsPipeSeparated = "Do · Does", correctAnswer = "Do", explanationUzbek = "You bilan 'Do' ishlatiladi.", vocabularyIdsCsv = "v_a0_35_02", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "______ he like cats?", subPromptText = "5-mashq. Bo'sh joyni to'ldiring:", audioTextToSpeak = "Does he like cats?", optionsPipeSeparated = "Do · Does", correctAnswer = "Does", explanationUzbek = "He bilan 'Does' ishlatiladi.", vocabularyIdsCsv = "v_a0_35_03", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "Do you ______ apples?", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "Do you like apples?", optionsPipeSeparated = "like · likes", correctAnswer = "like", explanationUzbek = "Do dan keyin faqat 'like' bo'ladi.", vocabularyIdsCsv = "v_a0_35_01", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Do you like apples?” savoliga qisqa ijobiy javob:", subPromptText = "7-mashq. Tanlang:", audioTextToSpeak = "Yes, I do.", optionsPipeSeparated = "Yes, I do. · Yes, I am.", correctAnswer = "Yes, I do.", explanationUzbek = "Do bilan berilgan savolga 'Yes, I do' deb javob beriladi.", vocabularyIdsCsv = "v_a0_35_04", grammarRuleIdsCsv = "g_a0_35_02"),
        ExerciseEntity(id = "ex_a0_35_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Xatoni toping: “Do you likes apples?”", subPromptText = "8-mashq. To'g'ri variantni tanlang:", audioTextToSpeak = "Do you like apples?", optionsPipeSeparated = "Do you like apples? · Does you like apples?", correctAnswer = "Do you like apples?", explanationUzbek = "Do dan keyin fe'lga -s qo'shilmaydi.", vocabularyIdsCsv = "v_a0_35_01,v_a0_35_02", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "Do you like apples? Yes, I do. Does he like cats? No, he does not.", optionsPipeSeparated = "O'qidim · Keyinroq", correctAnswer = "O'qidim", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_35_02,v_a0_35_04,v_a0_35_07", grammarRuleIdsCsv = "g_a0_35_01"),
        ExerciseEntity(id = "ex_a0_35_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_35_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta savol yozing:\n1) Do you like apples?\n2) Does he like cats?\n3) Do they like English?", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "Do you like apples? Does he like cats? Do they like English?", optionsPipeSeparated = "Submit", correctAnswer = "Do you like apples? · Does he like cats? · Do they like English?", explanationUzbek = "Ajoyib savollar!", vocabularyIdsCsv = "v_a0_35_01,v_a0_35_02,v_a0_35_03", grammarRuleIdsCsv = "g_a0_35_01"),

        // Lesson 36: I do not like... (“Do” inkorda)
        ExerciseEntity(id = "ex_a0_36_01", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "Inkor qilish uchun fe'l oldiga nima qo'shiladi?", subPromptText = "1-mashq. To'g'ri javobni tanlang:", audioTextToSpeak = "I do not like apples.", optionsPipeSeparated = "not · no · do not", correctAnswer = "do not", explanationUzbek = "Inkor uchun 'do not' yoki 'does not' ishlatiladi.", vocabularyIdsCsv = "v_a0_36_01", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_02", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“He” bilan inkorning to'g'ri shakli qaysi?", subPromptText = "2-mashq. Tanlang:", audioTextToSpeak = "He does not like cats.", optionsPipeSeparated = "do not · does not", correctAnswer = "does not", explanationUzbek = "he, she, it bilan 'does not' ishlatiladi.", vocabularyIdsCsv = "v_a0_36_02", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_03", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“Do not” dan keyin fe'l qanday bo'ladi?", subPromptText = "3-mashq. Tanlang:", audioTextToSpeak = "I do not like apples.", optionsPipeSeparated = "-s qo'shiladi · o'zgarmaydi", correctAnswer = "o'zgarmaydi", explanationUzbek = "Do not / does not dan keyin fe'l o'zgarmaydi.", vocabularyIdsCsv = "v_a0_36_01", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_04", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "I ______ not like apples.", subPromptText = "4-mashq. Bo'sh joyni to'ldiring:", audioTextToSpeak = "I do not like apples.", optionsPipeSeparated = "do · does", correctAnswer = "do", explanationUzbek = "I bilan 'do not' bo'ladi.", vocabularyIdsCsv = "v_a0_36_01,v_a0_36_05", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_05", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "FILL_BLANK", difficulty = 1, promptText = "He ______ not like cats.", subPromptText = "5-mashq. Bo'sh joyni to'ldiring:", audioTextToSpeak = "He does not like cats.", optionsPipeSeparated = "do · does", correctAnswer = "does", explanationUzbek = "He bilan 'does not' bo'ladi.", vocabularyIdsCsv = "v_a0_36_02,v_a0_36_06", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_06", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“do not” ning qisqa shakli qaysi?", subPromptText = "6-mashq. Tanlang:", audioTextToSpeak = "Don't", optionsPipeSeparated = "don’t · doesn’t", correctAnswer = "don’t", explanationUzbek = "do not -> don't.", vocabularyIdsCsv = "v_a0_36_03", grammarRuleIdsCsv = "g_a0_36_02"),
        ExerciseEntity(id = "ex_a0_36_07", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 1, promptText = "“does not” ning qisqa shakli qaysi?", subPromptText = "7-mashq. Tanlang:", audioTextToSpeak = "Doesn't", optionsPipeSeparated = "don’t · doesn’t", correctAnswer = "doesn’t", explanationUzbek = "does not -> doesn't.", vocabularyIdsCsv = "v_a0_36_04", grammarRuleIdsCsv = "g_a0_36_02"),
        ExerciseEntity(id = "ex_a0_36_08", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "MULTIPLE_CHOICE", difficulty = 2, promptText = "Xatoni toping: “He do not like cats.”", subPromptText = "8-mashq. To'g'ri variantni tanlang:", audioTextToSpeak = "He does not like cats.", optionsPipeSeparated = "He does not like cats · He not likes cats", correctAnswer = "He does not like cats", explanationUzbek = "He bilan 'does not' bo'ladi.", vocabularyIdsCsv = "v_a0_36_02,v_a0_36_06", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_09", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "READ_ALOUD", difficulty = 1, promptText = "Ovoz chiqarib o'qing:", subPromptText = "9-mashq. O'qing:", audioTextToSpeak = "I do not like apples. He does not like cats. They do not like English.", optionsPipeSeparated = "O'qidim · Keyinroq", correctAnswer = "O'qidim", explanationUzbek = "Ajoyib!", vocabularyIdsCsv = "v_a0_36_05,v_a0_36_06,v_a0_36_07", grammarRuleIdsCsv = "g_a0_36_01"),
        ExerciseEntity(id = "ex_a0_36_10", topicId = CurriculumLevel.A0.topicId, sectionId = DARS_36_SECTION_ID, practiceCategory = "SECTION_QUIZ", exerciseType = "SELF_WRITE", difficulty = 2, promptText = "3 ta inkor gap yozing:\n1) I do not like apples.\n2) He does not like cats.\n3) They do not like English.", subPromptText = "10-mashq. O'zingiz yozing:", audioTextToSpeak = "I do not like apples. He does not like cats. They do not like English.", optionsPipeSeparated = "Submit", correctAnswer = "I do not like apples. · He does not like cats. · They do not like English.", explanationUzbek = "Tabriklaymiz! Do fe'lining barcha asosiy qoidalarini muvaffaqiyatli o'zlashtirdingiz!", vocabularyIdsCsv = "v_a0_36_01,v_a0_36_02", grammarRuleIdsCsv = "g_a0_36_01")
    )

    fun buildAchievements(): List<AchievementEntity> = listOf(
        AchievementEntity(
            id = "ach_first_section",
            title = "First Step Taken",
            description = "Complete your first lesson section in TalkMe",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_vocab_5",
            title = "Word Collector",
            description = "Memorize at least 5 English vocabulary words via Flip Cards",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_grammar_3",
            title = "Grammar Builder",
            description = "Master grammar rules through interactive practice",
            isUnlocked = false,
            unlockedTimestamp = 0L
        ),
        AchievementEntity(
            id = "ach_first_topic",
            title = "Topic Conqueror",
            description = "Complete all sections in an entire Topic to unlock the next level",
            isUnlocked = false,
            unlockedTimestamp = 0L
        )
    )
}
