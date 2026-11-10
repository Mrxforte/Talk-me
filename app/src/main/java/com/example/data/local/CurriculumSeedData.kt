package com.example.data.local

import com.example.core.common.AppConstants
import com.example.core.database.AchievementEntity
import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.TopicEntity
import com.example.core.database.VocabularyEntity

/**
 * Data-driven curriculum repository containing the TalkMe curriculum topics:
 * 1. Introduction — Kirish
 * 2. Grammar Fundamentals — Grammatika asoslari
 * 3. Numbers — Sonlar
 * 4. Nouns — Otlar
 * 5. Demonstratives — Ko'rsatkichlar
 * 6. Adjectives — Sifatlar
 * 7. Articles — Artikllar
 * 8. Prepositions — Predloglar
 * 9. Pronouns — Olmoshlar
 * 10. Adverbs — Ravishlar
 * 11. Adverbials — Hol
 * 12. Verbs — Fe'llar
 * 13. Irregular Verbs — Noto'g'ri fe'llar
 *
 * Follows the core teaching philosophy:
 * - Explains concepts in simple Uzbek first.
 * - Minimal vocabulary acquisition per lesson without phonetic/IPA transcriptions.
 * - Reuses learned vocabulary across grammar examples and exercises.
 */
object CurriculumSeedData {

    private data class TopicSeedSpec(
        val id: String,
        val order: Int,
        val titleEn: String,
        val titleUz: String,
        val descUz: String,
        val sec1TitleEn: String,
        val sec1TitleUz: String,
        val sec1ObjectiveUz: String,
        val sec1ExplanationUz: String,
        val sec1WrapUpUz: String,
        val sec2TitleEn: String,
        val sec2TitleUz: String,
        val sec2ObjectiveUz: String,
        val sec2ExplanationUz: String,
        val sec2WrapUpUz: String,
        val wordsSec1: List<Triple<String, String, Pair<String, String>>>,
        val wordsSec2: List<Triple<String, String, Pair<String, String>>>,
        val grammarSec1: Triple<String, String, Pair<String, String>>,
        val grammarSec2: Triple<String, String, Pair<String, String>>
    )

    private val specs: List<TopicSeedSpec> = listOf(
        TopicSeedSpec(
            id = "topic_01_introduction",
            order = 1,
            titleEn = "Introduction",
            titleUz = "Kirish",
            descUz = "Ingliz tiliga kirish, salomlashish, tanishish va eng zarur dastlabki iboralarni audio orqali o'rganish.",
            sec1TitleEn = "Greetings & First Words",
            sec1TitleUz = "Salomlashish va ilk so'zlar",
            sec1ObjectiveUz = "Maqsad: Ingliz tilida salomlashish va eng asosiy dastlabki so'zlarni audio yordamida to'g'ri eshitib o'rganish.",
            sec1ExplanationUz = "Ingliz tilini o'rganish eng oddiy muloqot so'zlaridan boshlanadi. Biz transkripsiya belgilarini yodlamaymiz — har bir so'zni karnay tugmasini bosib eshitamiz va qaytaramiz.",
            sec1WrapUpUz = "Wrap-up: Siz birinchi inglizcha salomlashish va kundalik so'zlarni audio orqali o'rgandingiz!",
            sec2TitleEn = "Simple Introductions",
            sec2TitleUz = "O'zini tanishtirish",
            sec2ObjectiveUz = "Maqsad: O'zingizni tanishtirish va xushmuomala iboralarni tabiiy ohangda aytish.",
            sec2ExplanationUz = "Suhbat boshida 'Good morning' (Xayrli tong) va 'Thank you' / 'Please' kabi xushmuomala so'zlarni ishlatish juda muhim. Audio tugmani bosib ohangga e'tibor bering.",
            sec2WrapUpUz = "Wrap-up: Introduction (Kirish) bo'limi yakunlandi! Endi Grammar Fundamentals (Grammatika asoslari) mavzusi ochildi.",
            wordsSec1 = listOf(
                Triple("hello", "salom", "Hello, my friend." to "Salom, mening do'stim."),
                Triple("apple", "olma", "This is an apple." to "Bu olma."),
                Triple("cat", "mushuk", "A small cat." to "Kichkina mushuk.")
            ),
            wordsSec2 = listOf(
                Triple("water", "suv", "Clean water." to "Toza suv."),
                Triple("morning", "tong", "Good morning!" to "Xayrli tong!")
            ),
            grammarSec1 = Triple(
                "Ingliz tilida ilk muloqot",
                "Har bir yangi so'zni ko'rish bilan birga karnay belgisi orqali eshitib o'rganish shart.",
                "Hello! Apple and cat." to "Salom! Olma va mushuk."
            ),
            grammarSec2 = Triple(
                "Xushmuomala iboralar",
                "Suhbatda 'Good morning' va 'please' so'zlari fikrni chiroyli ifodalashga yordam beradi.",
                "Good morning! Water please." to "Xayrli tong! Iltimos, suv."
            )
        ),
        TopicSeedSpec(
            id = "topic_02_grammar_fundamentals",
            order = 2,
            titleEn = "Grammar Fundamentals",
            titleUz = "Grammatika asoslari",
            descUz = "Ingliz tilida gap qurilishi asoslari: Ega + Kesim tartibi va bog'lovchi so'zlar.",
            sec1TitleEn = "Sentence Structure Basics",
            sec1TitleUz = "Gap tuzilishi asoslari",
            sec1ObjectiveUz = "Maqsad: Ingliz tilida so'zlarning qat'iy tartibini (Kim/Nima + Harakat/Holat) tushunish.",
            sec1ExplanationUz = "O'zbek tilida kesim gap oxirida kelsa, ingliz tilida avval Ega (kim yoki nima), keyin esa fe'l/bog'lama (is/are) keladi: I am a student (Men talabaman).",
            sec1WrapUpUz = "Wrap-up: Inglizcha gap tuzilishining oltin qoidasini o'zlashtirdingiz!",
            sec2TitleEn = "To Be & Connecting Ideas",
            sec2TitleUz = "Am / Is / Are bog'lamalari",
            sec2ObjectiveUz = "Maqsad: Gapda shaxs va predmetni bog'lovchi 'am, is, are' shakllarini to'g'ri qo'llash.",
            sec2ExplanationUz = "Ingliz tilida ot kesimli gaplarda 'is' (birlikda) va 'are' (ko'plikda) bog'lamasi tushib qolmaydi: The friend is kind.",
            sec2WrapUpUz = "Wrap-up: Grammar Fundamentals mavzusi yakunlandi! Keyingi mavzu: Numbers (Sonlar).",
            wordsSec1 = listOf(
                Triple("friend", "do'st", "My good friend." to "Mening yaxshi do'stim."),
                Triple("teacher", "o'qituvchi", "She is a teacher." to "U o'qituvchi.")
            ),
            wordsSec2 = listOf(
                Triple("lesson", "dars", "This lesson is easy." to "Bu dars oson."),
                Triple("english", "ingliz tili", "I learn English." to "Men ingliz tilini o'rganaman.")
            ),
            grammarSec1 = Triple(
                "Ega + Kesim tartibi (Subject + Verb)",
                "Inglizcha darak gap har doim ega bilan boshlanadi va undan keyin fe'l keladi.",
                "My friend learns English." to "Mening do'stim ingliz tilini o'rganadi."
            ),
            grammarSec2 = Triple(
                "Is va Are bog'lamasi",
                "Birlikdagi ega bilan 'is', ko'plikdagi ega bilan 'are' ishlatiladi.",
                "The teacher is here and the lesson is good." to "O'qituvchi shu yerda va dars yaxshi."
            )
        ),
        TopicSeedSpec(
            id = "topic_03_numbers",
            order = 3,
            titleEn = "Numbers",
            titleUz = "Sonlar",
            descUz = "Sanash sonlari (1-10) va tartib sonlarini kundalik predmetlar bilan birga qo'llash.",
            sec1TitleEn = "Cardinal Numbers 1–10",
            sec1TitleUz = "Sanoq sonlar (1 dan 10 gacha)",
            sec1ObjectiveUz = "Maqsad: Predmetlar sonini ingliz tilida aniq aytishni o'rganish.",
            sec1ExplanationUz = "Ingliz tilida sanoq sonlar predmetdan oldin qo'yiladi: one apple (bitta olma), two cats (ikkita mushuk).",
            sec1WrapUpUz = "Wrap-up: Siz sanoq sonlarni predmetlar bilan bog'lab aytishni o'zlashtirdingiz.",
            sec2TitleEn = "Ordinal & Tens Numbers",
            sec2TitleUz = "Tartib sonlar va o'nliklar",
            sec2ObjectiveUz = "Maqsad: First (birinchi), second (ikkinchi) va ten (o'n) sonlarini gapda qo'llash.",
            sec2ExplanationUz = "Tartibni bildirish uchun maxsus shakllar ishlatiladi: first — birinchi, second — ikkinchi.",
            sec2WrapUpUz = "Wrap-up: Sonlar mavzusi tugatildi! Keyingi mavzu: Otlar (Nouns).",
            wordsSec1 = listOf(
                Triple("one", "bir", "One apple." to "Bitta olma."),
                Triple("two", "ikki", "Two cats." to "Ikkita mushuk."),
                Triple("three", "uch", "Three friends." to "Uchta do'st.")
            ),
            wordsSec2 = listOf(
                Triple("first", "birinchi", "First lesson." to "Birinchi dars."),
                Triple("ten", "o'n", "Ten apples." to "O'nta olma.")
            ),
            grammarSec1 = Triple(
                "Son + Ot tartibi",
                "Ingliz tilida son doim otdan oldin keladi. Agar son 1 dan katta bo'lsa, ot ko'plikda (-s) bo'ladi.",
                "One cat, two cats." to "Bitta mushuk, ikkita mushuk."
            ),
            grammarSec2 = Triple(
                "Tartib sonlar",
                "Birinchi va ikkinchi so'zlari 'first' va 'second' shaklida aytiladi.",
                "The first morning." to "Birinchi tong."
            )
        ),
        TopicSeedSpec(
            id = "topic_04_nouns",
            order = 4,
            titleEn = "Nouns",
            titleUz = "Otlar",
            descUz = "Shaxs va narsa-buyum nomlari, birlik va ko'plik shakllari.",
            sec1TitleEn = "Singular & Plural Nouns",
            sec1TitleUz = "Birlik va ko'plikdagi otlar",
            sec1ObjectiveUz = "Maqsad: Narsa nomlarini birlikda va '-s' qo'shimchasi orqali ko'plikda aytish.",
            sec1ExplanationUz = "Ot (Noun) — 'kim?' yoki 'nima?' savoliga javob bo'luvchi so'z (book, table, student). Ko'plik yasash uchun so'z oxiriga '-s' qo'shiladi.",
            sec1WrapUpUz = "Wrap-up: Birlik va ko'plikdagi otlar qoidasi mustahkamlandi.",
            sec2TitleEn = "Countable & Uncountable Nouns",
            sec2TitleUz = "Sanaladigan va sanalmaydigan otlar",
            sec2ObjectiveUz = "Maqsad: Sanaladigan (book) va sanalmaydigan (water, milk) otlarni farqlash.",
            sec2ExplanationUz = "Kitob va olmani sanash mumkin (one book, two books). Suv va sutni esa donalab sanab bo'lmaydi, ularga '-s' qo'shilmaydi.",
            sec2WrapUpUz = "Wrap-up: Otlar mavzusi muvaffaqiyatli yakunlandi!",
            wordsSec1 = listOf(
                Triple("book", "kitob", "One book, two books." to "Bitta kitob, ikkita kitob."),
                Triple("table", "stol", "Three tables." to "Uchta stol."),
                Triple("student", "talaba", "Two students." to "Ikkita talaba.")
            ),
            wordsSec2 = listOf(
                Triple("milk", "sut", "Fresh milk and water." to "Yangi sut va suv."),
                Triple("bread", "non", "Warm bread." to "Issiq non.")
            ),
            grammarSec1 = Triple(
                "Ko'plik qo'shimchasi (-s)",
                "Sanaladigan otlar ko'plikda '-s' oladi: book -> books, student -> students.",
                "Two books on the table." to "Stolda ikkita kitob."
            ),
            grammarSec2 = Triple(
                "Sanalmaydigan otlar",
                "Water, milk, bread kabi so'zlar faqat birlik shaklida ishlatiladi.",
                "Water and bread." to "Suv va non."
            )
        ),
        TopicSeedSpec(
            id = "topic_05_demonstratives",
            order = 5,
            titleEn = "Demonstratives",
            titleUz = "Ko'rsatkichlar",
            descUz = "Yaqin va uzoqdagi narsalarni ko'rsatish: this, that, these, those.",
            sec1TitleEn = "This and That",
            sec1TitleUz = "This (bu) va That (u / anavi)",
            sec1ObjectiveUz = "Maqsad: Yaqindagi bitta narsa uchun 'this', uzoqdagi bitta narsa uchun 'that' so'zini qo'llash.",
            sec1ExplanationUz = "Qo'lingizga yaqin turgan 1 ta narsaga 'This is...' deymiz. Uzoqroqdagi 1 ta narsaga 'That is...' deymiz.",
            sec1WrapUpUz = "Wrap-up: This va That farqini misollar orqali o'rgandingiz.",
            sec2TitleEn = "These and Those",
            sec2TitleUz = "These (bular) va Those (ular)",
            sec2ObjectiveUz = "Maqsad: Ko'plikdagi yaqin va uzoq predmetlarni ko'rsatish.",
            sec2ExplanationUz = "Yaqindagi ko'p narsalar uchun 'These are...', uzoqdagi ko'p narsalar uchun 'Those are...' ishlatiladi.",
            sec2WrapUpUz = "Wrap-up: Ko'rsatkichlar mavzusi to'liq o'zlashtirildi!",
            wordsSec1 = listOf(
                Triple("this", "bu (yaqindagi birlik)", "This is a book." to "Bu — kitob."),
                Triple("that", "u / anavi (uzoqdagi birlik)", "That is a table." to "Anavi — stol.")
            ),
            wordsSec2 = listOf(
                Triple("these", "bular (yaqindagi ko'plik)", "These are books." to "Bular — kitoblar."),
                Triple("those", "ular / anavilar (uzoqdagi ko'plik)", "Those are apples." to "Anavilar — olmalar.")
            ),
            grammarSec1 = Triple(
                "Birlikdagi ko'rsatkichlar (This / That)",
                "This + is + birlik ot (yaqin); That + is + birlik ot (uzoq).",
                "This is an apple and that is a book." to "Bu olma va anavi kitob."
            ),
            grammarSec2 = Triple(
                "Ko'plikdagi ko'rsatkichlar (These / Those)",
                "These + are + ko'plik ot; Those + are + ko'plik ot.",
                "These are cats and those are tables." to "Bular mushuklar va anavilar stollar."
            )
        ),
        TopicSeedSpec(
            id = "topic_06_adjectives",
            order = 6,
            titleEn = "Adjectives",
            titleUz = "Sifatlar",
            descUz = "Narsa va shaxslarning belgisi, hajmi va xususiyatini ifodalash.",
            sec1TitleEn = "Descriptive Adjectives",
            sec1TitleUz = "Asosiy sifatlar (big, small, new, good)",
            sec1ObjectiveUz = "Maqsad: Otlardan oldin sifat qo'yib, ularning belgisini aytish.",
            sec1ExplanationUz = "Ingliz tilida sifat (Adjective) o'zbek tilidagidek otdan oldin keladi: a big book (katta kitob), a good student (yaxshi talaba).",
            sec1WrapUpUz = "Wrap-up: Sifat + Ot birikmalarini tuzishni o'rgandingiz.",
            sec2TitleEn = "Colors & Everyday Qualities",
            sec2TitleUz = "Ranglar va kundalik sifatlar",
            sec2ObjectiveUz = "Maqsad: Rang va holat sifatlarini gapda qo'llash.",
            sec2ExplanationUz = "Sifatlar birlik va ko'plikda o'zgarmaydi (masalan: red apple, red apples).",
            sec2WrapUpUz = "Wrap-up: Sifatlar mavzusi yakunlandi!",
            wordsSec1 = listOf(
                Triple("big", "katta", "A big table." to "Katta stol."),
                Triple("small", "kichik", "A small cat." to "Kichik mushuk."),
                Triple("good", "yaxshi", "A good book." to "Yaxshi kitob.")
            ),
            wordsSec2 = listOf(
                Triple("new", "yangi", "Two new books." to "Ikkita yangi kitob."),
                Triple("red", "qizil", "A red apple." to "Qizil olma.")
            ),
            grammarSec1 = Triple(
                "Sifatning gapdagi o'rni",
                "Sifat har doim o'zi ta'riflayotgan otdan oldin qo'yiladi.",
                "This is a good student." to "Bu — yaxshi talaba."
            ),
            grammarSec2 = Triple(
                "Sifatlar ko'plikda o'zgarmaydi",
                "Ot ko'plikka o'tsa ham sifatga '-s' qo'shilmaydi.",
                "These are red apples." to "Bular qizil olmalar."
            )
        ),
        TopicSeedSpec(
            id = "topic_07_articles",
            order = 7,
            titleEn = "Articles",
            titleUz = "Artikllar",
            descUz = "Noaniq (a / an) va aniq (the) artikllarni to'g'ri tanlash qoidalari.",
            sec1TitleEn = "Indefinite Articles: a / an",
            sec1TitleUz = "Noaniq artikllar: a va an",
            sec1ObjectiveUz = "Maqsad: Undosh tovushdan oldin 'a', unli tovushdan oldin 'an' qo'yishni o'rganish.",
            sec1ExplanationUz = "Birlikdagi sanaladigan ot oldidan 'bitta / qandaydir' ma'nosida 'a' yoki 'an' keladi: a book (undosh bilan boshlangan), an apple (unli bilan boshlangan).",
            sec1WrapUpUz = "Wrap-up: a va an artikllarini farqlashni o'zlashtirdingiz.",
            sec2TitleEn = "Definite Article: the",
            sec2TitleUz = "Aniq artikl: the",
            sec2ObjectiveUz = "Maqsad: Suhbatdoshga ma'lum bo'lgan aniq narsa oldidan 'the' ishlatish.",
            sec2ExplanationUz = "Agar narsa haqida ikkinchi marta gapirsak yoki u aniq bitta bo'lsa, 'the' ishlatamiz: I see a book. The book is good.",
            sec2WrapUpUz = "Wrap-up: Artikllar mavzusi muvaffaqiyatli tugallandi!",
            wordsSec1 = listOf(
                Triple("orange", "apelsin", "An orange and a banana." to "Bitta apelsin va bitta banan."),
                Triple("pen", "ruchka", "This is a new pen." to "Bu yangi ruchka.")
            ),
            wordsSec2 = listOf(
                Triple("sun", "quyosh", "The sun is bright." to "Quyosh yorqin."),
                Triple("room", "xona", "The room is big." to "Xona katta.")
            ),
            grammarSec1 = Triple(
                "a va an qoidasi",
                "Undosh tovush oldidan 'a' (a pen, a big apple), unli tovush oldidan 'an' (an apple, an orange) ishlatiladi.",
                "A pen and an orange." to "Bitta ruchka va bitta apelsin."
            ),
            grammarSec2 = Triple(
                "The aniq artikli",
                "'The' birlik va ko'plikdagi aniq predmetlar uchun birdek qo'llaniladi.",
                "The book on the table is new." to "Stoldagi kitob yangi."
            )
        ),
        TopicSeedSpec(
            id = "topic_08_prepositions",
            order = 8,
            titleEn = "Prepositions",
            titleUz = "Predloglar",
            descUz = "Joylashuv va vaqt predloglari: in, on, under, at.",
            sec1TitleEn = "Prepositions of Place",
            sec1TitleUz = "Joylashuv predloglari (in, on, under)",
            sec1ObjectiveUz = "Maqsad: Narsalarning qayerda turganini (ichida, ustida, tagida) ifodalash.",
            sec1ExplanationUz = "O'zbek tilidagi '-da / ichida / ustida' qo'shimchalari ingliz tilida otdan oldin keladigan predloglar bilan aytiladi: in the room (xona ichida), on the table (stol ustida).",
            sec1WrapUpUz = "Wrap-up: Joylashuv predloglarini gapda qo'llashni o'rgandingiz.",
            sec2TitleEn = "Prepositions of Time & Direction",
            sec2TitleUz = "Vaqt va yo'nalish predloglari (at, to)",
            sec2ObjectiveUz = "Maqsad: Vaqt va yo'nalishni predloglar yordamida ko'rsatish.",
            sec2ExplanationUz = "'At' aniq joy yoki vaqt uchun, 'to' esa '-ga' yo'nalishi uchun ishlatiladi.",
            sec2WrapUpUz = "Wrap-up: Predloglar mavzusi yakunlandi!",
            wordsSec1 = listOf(
                Triple("in", "ichida (-da)", "The cat is in the room." to "Mushuk xona ichida."),
                Triple("on", "ustida", "The book is on the table." to "Kitob stol ustida."),
                Triple("under", "tagida / ostida", "The cat is under the table." to "Mushuk stol tagida.")
            ),
            wordsSec2 = listOf(
                Triple("bag", "sumka", "The pen is in the bag." to "Ruchka sumka ichida."),
                Triple("school", "maktab", "At school." to "Maktabda.")
            ),
            grammarSec1 = Triple(
                "In, On, Under joylashuvi",
                "Predlog har doim otdan (va artikldan) oldin qo'yiladi: on + the + table.",
                "An apple is on the table." to "Olma stol ustida."
            ),
            grammarSec2 = Triple(
                "At va In farqi",
                "'In' yopiq joy ichini (in the bag), 'at' esa maskan yoki nuqtani (at school) bildiradi.",
                "The student is at school." to "Talaba maktabda."
            )
        ),
        TopicSeedSpec(
            id = "topic_09_pronouns",
            order = 9,
            titleEn = "Pronouns",
            titleUz = "Olmoshlar",
            descUz = "Kishilik va egalik olmoshlari: I, you, he, she, it, we, they, my, your.",
            sec1TitleEn = "Subject Pronouns",
            sec1TitleUz = "Kishilik olmoshlari (I, you, he, she, it, we, they)",
            sec1ObjectiveUz = "Maqsad: Gap egasi sifatida olmoshlarni to'g'ri qo'llash.",
            sec1ExplanationUz = "O'zbek tilida 'u' bitta so'z bo'lsa, ingliz tilida o'g'il bolaga 'he', qiz bolaga 'she', jonsiz narsa yoki hayvonga 'it' deyiladi.",
            sec1WrapUpUz = "Wrap-up: Kishilik olmoshlari o'zlashtirildi.",
            sec2TitleEn = "Possessive Determiners",
            sec2TitleUz = "Egalik shakllari (my, your, his, her)",
            sec2ObjectiveUz = "Maqsad: Narsa kimga tegishli ekanini (my book, your pen) aytish.",
            sec2ExplanationUz = "Otdan oldin kelib egalikni bildiradi: my book (mening kitobim), your bag (sening sumkang).",
            sec2WrapUpUz = "Wrap-up: Olmoshlar mavzusi tugallandi!",
            wordsSec1 = listOf(
                Triple("he", "u (erkak)", "He is a good student." to "U yaxshi talaba."),
                Triple("she", "u (ayol)", "She is at school." to "U maktabda."),
                Triple("they", "ular", "They are students." to "Ular talabalar.")
            ),
            wordsSec2 = listOf(
                Triple("my", "mening", "This is my book." to "Bu mening kitobim."),
                Triple("your", "sening / sizning", "That is your bag." to "Anavi sizning sumkangiz.")
            ),
            grammarSec1 = Triple(
                "He, She, It farqi",
                "Insonlarga nisbatan 'he/she', buyum va hayvonlarga (a book, a cat) birlikda 'it', ko'plikda 'they' ishlatiladi.",
                "It is a big room." to "U katta xona."
            ),
            grammarSec2 = Triple(
                "Egalik olmoshlari + Ot",
                "My, your, his, her so'zlaridan keyin albatta ot keladi.",
                "My pen is in your bag." to "Mening ruchkam sizning sumkangizda."
            )
        ),
        TopicSeedSpec(
            id = "topic_10_adverbs",
            order = 10,
            titleEn = "Adverbs",
            titleUz = "Ravishlar",
            descUz = "Harakatning qanday va qanchalik tez-tez bajarilishini ifodalovchi so'zlar.",
            sec1TitleEn = "Adverbs of Manner",
            sec1TitleUz = "Holat ravishlari (quickly, slowly, well)",
            sec1ObjectiveUz = "Maqsad: Harakat qanday bajarilganini ravishlar bilan tushuntirish.",
            sec1ExplanationUz = "Sifat otga bog'lansa, ravish (Adverb) harakatga bog'lanadi. Ko'p ravishlar sifatga '-ly' qo'shib yasaladi: quick -> quickly, slow -> slowly. 'Good' sifatining ravishi — 'well'.",
            sec1WrapUpUz = "Wrap-up: Holat ravishlarini farqlashni o'rgandingiz.",
            sec2TitleEn = "Adverbs of Frequency",
            sec2TitleUz = "Payt va takroriylik ravishlari (always, often)",
            sec2ObjectiveUz = "Maqsad: Ish-harakat qanchalik tez-tez sodir bo'lishini aytish.",
            sec2ExplanationUz = "Always (har doim) va often (tez-tez) so'zlari asosiy fe'ldan oldin keladi.",
            sec2WrapUpUz = "Wrap-up: Ravishlar mavzusi yakunlandi!",
            wordsSec1 = listOf(
                Triple("slowly", "sekin", "Speak slowly, please." to "Iltimos, sekin gapiring."),
                Triple("quickly", "tez", "He reads quickly." to "U tez o'qiydi."),
                Triple("well", "yaxshi (ravish)", "She speaks English well." to "U inglizcha yaxshi gapiradi.")
            ),
            wordsSec2 = listOf(
                Triple("always", "har doim", "I always read books." to "Men har doim kitob o'qiyman."),
                Triple("today", "bugun", "Today is a good morning." to "Bugun xayrli tong.")
            ),
            grammarSec1 = Triple(
                "Good (sifat) va Well (ravish)",
                "'Good' otdan oldin keladi (a good book), 'well' esa fe'ldan keyin keladi (speaks well).",
                "She is a good student and she reads well." to "U yaxshi talaba va u yaxshi o'qiydi."
            ),
            grammarSec2 = Triple(
                "Always ravishining o'rni",
                "'Always' odatda egadan keyin, harakat fe'lidan oldin qo'yiladi.",
                "They always read at school." to "Ular har doim maktabda o'qiydilar."
            )
        ),
        TopicSeedSpec(
            id = "topic_11_adverbials",
            order = 11,
            titleEn = "Adverbials",
            titleUz = "Hol",
            descUz = "Gapda o'rin, payt va sabab holi bo'lib keluvchi so'z birikmalari.",
            sec1TitleEn = "Adverbials of Place & Time",
            sec1TitleUz = "O'rin va payt hollari",
            sec1ObjectiveUz = "Maqsad: Gap oxirida joy va vaqt birikmalarini to'g'ri tartibda joylashtirish.",
            sec1ExplanationUz = "Hol (Adverbial) — harakatning qayerda va qachon bo'lganini bildiruvchi birikma: in the morning (ertalab), at home (uyda).",
            sec1WrapUpUz = "Wrap-up: O'rin va payt hollari tartibini o'rgandingiz.",
            sec2TitleEn = "Word Order with Adverbials",
            sec2TitleUz = "Hol qatnashgan gaplarda so'z tartibi",
            sec2ObjectiveUz = "Maqsad: Joy + Vaqt tartibida tabiiy inglizcha gap tuzish.",
            sec2ExplanationUz = "Agar gapda ham joy, ham vaqt qatnashsa, avval JOY (at school), keyin VAQT (every day / in the morning) aytiladi.",
            sec2WrapUpUz = "Wrap-up: Hol (Adverbials) mavzusi yakunlandi!",
            wordsSec1 = listOf(
                Triple("home", "uy", "I am at home." to "Men uydaman."),
                Triple("every", "har", "Every morning." to "Har tong.")
            ),
            wordsSec2 = listOf(
                Triple("here", "bu yerda", "The book is here." to "Kitob bu yerda."),
                Triple("now", "hozir", "We are at school now." to "Biz hozir maktabdamiz.")
            ),
            grammarSec1 = Triple(
                "Payt holi birikmalari",
                "'In the morning' va 'every day' gapning oxirida yoki boshida kelib vaqtni ko'rsatadi.",
                "I read books in the morning." to "Men ertalab kitob o'qiyman."
            ),
            grammarSec2 = Triple(
                "Joy + Vaqt tartibi (Place before Time)",
                "Ingliz tilida avval 'qayerda?' (at school), undan keyin 'qachon?' (now / every morning) keladi.",
                "She is at home now." to "U hozir uyda."
            )
        ),
        TopicSeedSpec(
            id = "topic_12_verbs",
            order = 12,
            titleEn = "Verbs",
            titleUz = "Fe'llar",
            descUz = "Harakat fe'llari, Present Simple va to'g'ri fe'llarning o'tgan zamon (-ed) shakli.",
            sec1TitleEn = "Action Verbs in Present Simple",
            sec1TitleUz = "Hozirgi zamon harakat fe'llari",
            sec1ObjectiveUz = "Maqsad: Kundalik harakatlarni (work, play, open, help) hozirgi zamonda aytish.",
            sec1ExplanationUz = "I, you, we, they bilan fe'l o'z holicha keladi (I work). He, she, it bilan fe'l oxiriga '-s' qo'shiladi (He works).",
            sec1WrapUpUz = "Wrap-up: Hozirgi zamonda fe'llarni tuslashni o'rgandingiz.",
            sec2TitleEn = "Regular Verbs in Past Simple (-ed)",
            sec2TitleUz = "To'g'ri fe'llarning o'tgan zamoni (-ed)",
            sec2ObjectiveUz = "Maqsad: To'g'ri fe'llarga '-ed' qo'shib o'tgan zamon hosil qilish.",
            sec2ExplanationUz = "To'g'ri fe'llar (Regular verbs) o'tgan zamonda barcha shaxslar uchun '-ed' qo'shimchasini oladi: work -> worked, open -> opened.",
            sec2WrapUpUz = "Wrap-up: Fe'llar mavzusi yakunlandi! Oxirgi mavzu: Noto'g'ri fe'llar.",
            wordsSec1 = listOf(
                Triple("work", "ishlamoq", "I work every day." to "Men har kuni ishlayman."),
                Triple("open", "ochmoq", "Open the book, please." to "Iltimos, kitobni oching."),
                Triple("help", "yordam bermoq", "She helps my friend." to "U do'stimga yordam beradi.")
            ),
            wordsSec2 = listOf(
                Triple("play", "o'ynamoq", "They played at school." to "Ular maktabda o'ynadilar."),
                Triple("clean", "tozalamoq", "I cleaned the room." to "Men xonani tozaladim.")
            ),
            grammarSec1 = Triple(
                "He / She / It + Fe'l(-s)",
                "Hozirgi zamonda 3-shaxs birlikda fe'lga '-s' qo'shiladi: I open -> She opens.",
                "He works at school every day." to "U har kuni maktabda ishlaydi."
            ),
            grammarSec2 = Triple(
                "To'g'ri fe'llarda -ed qoidasi",
                "O'tgan zamonda to'g'ri fe'l oxiriga '-ed' qo'shiladi: clean -> cleaned, help -> helped.",
                "We cleaned the big room today." to "Biz bugun katta xonani tozaladik."
            )
        ),
        TopicSeedSpec(
            id = "topic_13_irregular_verbs",
            order = 13,
            titleEn = "Irregular Verbs",
            titleUz = "Noto'g'ri fe'llar",
            descUz = "O'tgan zamonda '-ed' olmay, o'zagi o'zgaradigan eng muhim fe'llar.",
            sec1TitleEn = "Core Irregular Verbs (go/went, see/saw)",
            sec1TitleUz = "Asosiy noto'g'ri fe'llar (go - went, see - saw)",
            sec1ObjectiveUz = "Maqsad: Eng ko'p ishlatiladigan noto'g'ri fe'llarning o'tgan zamon shaklini qo'llash.",
            sec1ExplanationUz = "Noto'g'ri fe'llar o'tgan zamonda '-ed' olmaydi, balki maxsus shaklga o'zgaradi: go (bormoq) -> went (bordi), see (ko'rmoq) -> saw (ko'rdi).",
            sec1WrapUpUz = "Wrap-up: Go/went va see/saw juftliklarini o'zlashtirdingiz.",
            sec2TitleEn = "Everyday Irregular Verbs (read, eat, take)",
            sec2TitleUz = "Kundalik noto'g'ri fe'llar (eat - ate, take - took)",
            sec2ObjectiveUz = "Maqsad: O'tgan zamonda ovqatlanish va olish harakatlarini ifodalash.",
            sec2ExplanationUz = "Eat (yemoq) -> ate (yedi), take (olmoq) -> took (oldi). Ushbu shakllar barcha olmoshlar (I, he, they) bilan bir xil ishlatiladi.",
            sec2WrapUpUz = "Wrap-up: Tabriklaymiz! Siz TalkMe dasturining barcha mavzularini muvaffaqiyatli yakunladingiz!",
            wordsSec1 = listOf(
                Triple("go", "bormoq", "I go to school every morning." to "Men har tong maktabga boraman."),
                Triple("went", "bordi (go ning o'tgan zamoni)", "She went home." to "U uyga bordi."),
                Triple("saw", "ko'rdi (see ning o'tgan zamoni)", "I saw two cats." to "Men ikkita mushukni ko'rdim.")
            ),
            wordsSec2 = listOf(
                Triple("ate", "yedi (eat ning o'tgan zamoni)", "He ate an apple." to "U bitta olma yedi."),
                Triple("took", "oldi (take ning o'tgan zamoni)", "She took my book." to "U mening kitobimni oldi.")
            ),
            grammarSec1 = Triple(
                "Go -> Went va See -> Saw",
                "Noto'g'ri fe'llarga '-ed' qo'shilmaydi: I went to school (Men maktabga bordim).",
                "They went to school and saw the teacher." to "Ular maktabga bordilar va o'qituvchini ko'rdilar."
            ),
            grammarSec2 = Triple(
                "Eat -> Ate va Take -> Took",
                "O'tgan zamon noto'g'ri fe'llari he/she/it bilan ham o'zgarmaydi.",
                "He ate bread and took the bag." to "U non yedi va sumkani oldi."
            )
        )
    )

    fun buildTopics(): List<TopicEntity> = specs.map { spec ->
        TopicEntity(
            id = spec.id,
            courseId = AppConstants.DEFAULT_COURSE_ID,
            orderIndex = spec.order,
            titleEnglish = spec.titleEn,
            titleUzbek = spec.titleUz,
            descriptionUzbek = spec.descUz,
            isUnlocked = spec.order == 1,
            isCompleted = false
        )
    }

    fun buildSections(): List<LessonSectionEntity> {
        val list = mutableListOf<LessonSectionEntity>()
        specs.forEach { spec ->
            val sec1Id = String.format("sec_%02d_01", spec.order)
            val sec2Id = String.format("sec_%02d_02", spec.order)
            list.add(
                LessonSectionEntity(
                    id = sec1Id,
                    topicId = spec.id,
                    orderIndex = 1,
                    titleEnglish = spec.sec1TitleEn,
                    titleUzbek = spec.sec1TitleUz,
                    objectiveUzbek = spec.sec1ObjectiveUz,
                    explanationUzbek = spec.sec1ExplanationUz,
                    wrapUpSummaryUzbek = spec.sec1WrapUpUz,
                    isUnlocked = spec.order == 1,
                    isCompleted = false
                )
            )
            list.add(
                LessonSectionEntity(
                    id = sec2Id,
                    topicId = spec.id,
                    orderIndex = 2,
                    titleEnglish = spec.sec2TitleEn,
                    titleUzbek = spec.sec2TitleUz,
                    objectiveUzbek = spec.sec2ObjectiveUz,
                    explanationUzbek = spec.sec2ExplanationUz,
                    wrapUpSummaryUzbek = spec.sec2WrapUpUz,
                    isUnlocked = false,
                    isCompleted = false
                )
            )
        }
        return list
    }

    fun buildVocabulary(): List<VocabularyEntity> {
        val list = mutableListOf<VocabularyEntity>()
        specs.forEach { spec ->
            val sec1Id = String.format("sec_%02d_01", spec.order)
            val sec2Id = String.format("sec_%02d_02", spec.order)

            spec.wordsSec1.forEachIndexed { idx, triple ->
                list.add(
                    VocabularyEntity(
                        id = "voc_${spec.order}_1_${idx + 1}",
                        topicId = spec.id,
                        sectionId = sec1Id,
                        englishWord = triple.first,
                        uzbekTranslation = triple.second,
                        exampleEnglish = triple.third.first,
                        exampleUzbek = triple.third.second,
                        isStudied = spec.order == 1,
                        isMemorized = false,
                        reviewCount = 0,
                        nextReviewTimestamp = 0L
                    )
                )
            }

            spec.wordsSec2.forEachIndexed { idx, triple ->
                list.add(
                    VocabularyEntity(
                        id = "voc_${spec.order}_2_${idx + 1}",
                        topicId = spec.id,
                        sectionId = sec2Id,
                        englishWord = triple.first,
                        uzbekTranslation = triple.second,
                        exampleEnglish = triple.third.first,
                        exampleUzbek = triple.third.second,
                        isStudied = false,
                        isMemorized = false,
                        reviewCount = 0,
                        nextReviewTimestamp = 0L
                    )
                )
            }
        }
        return list
    }

    fun buildGrammarRules(): List<GrammarRuleEntity> {
        val list = mutableListOf<GrammarRuleEntity>()
        specs.forEach { spec ->
            val sec1Id = String.format("sec_%02d_01", spec.order)
            val sec2Id = String.format("sec_%02d_02", spec.order)

            list.add(
                GrammarRuleEntity(
                    id = "gram_${spec.order}_01",
                    topicId = spec.id,
                    sectionId = sec1Id,
                    titleUzbek = spec.grammarSec1.first,
                    explanationUzbek = spec.grammarSec1.second,
                    exampleEnglish = spec.grammarSec1.third.first,
                    exampleUzbek = spec.grammarSec1.third.second,
                    associatedVocabularyIdsCsv = "voc_${spec.order}_1_1,voc_${spec.order}_1_2",
                    isStudied = spec.order == 1,
                    isMastered = false
                )
            )

            list.add(
                GrammarRuleEntity(
                    id = "gram_${spec.order}_02",
                    topicId = spec.id,
                    sectionId = sec2Id,
                    titleUzbek = spec.grammarSec2.first,
                    explanationUzbek = spec.grammarSec2.second,
                    exampleEnglish = spec.grammarSec2.third.first,
                    exampleUzbek = spec.grammarSec2.third.second,
                    associatedVocabularyIdsCsv = "voc_${spec.order}_2_1,voc_${spec.order}_2_2",
                    isStudied = false,
                    isMastered = false
                )
            )
        }
        return list
    }

    fun buildExercises(): List<ExerciseEntity> {
        val list = mutableListOf<ExerciseEntity>()
        specs.forEach { spec ->
            val sec1Id = String.format("sec_%02d_01", spec.order)
            val sec2Id = String.format("sec_%02d_02", spec.order)
            val firstWord = spec.wordsSec1.first()
            val secondWord = spec.wordsSec2.first()

            // 1. Vocabulary Recognition / Multiple Choice
            list.add(
                ExerciseEntity(
                    id = "ex_${spec.order}_vocab_mc",
                    topicId = spec.id,
                    sectionId = sec1Id,
                    practiceCategory = "VOCABULARY",
                    exerciseType = "MULTIPLE_CHOICE",
                    difficulty = 1,
                    promptText = "'${firstWord.first}' so'zining o'zbekcha tarjimasini toping:",
                    subPromptText = "Misol: ${firstWord.third.first}",
                    audioTextToSpeak = firstWord.first,
                    optionsPipeSeparated = "${firstWord.second}|${secondWord.second}|kitob|suv",
                    correctAnswer = firstWord.second,
                    explanationUzbek = "'${firstWord.first}' o'zbek tilida '${firstWord.second}' degan ma'noni bildiradi.",
                    vocabularyIdsCsv = "voc_${spec.order}_1_1",
                    grammarRuleIdsCsv = "gram_${spec.order}_01"
                )
            )

            // 2. Grammar Practice
            list.add(
                ExerciseEntity(
                    id = "ex_${spec.order}_grammar_mc",
                    topicId = spec.id,
                    sectionId = sec1Id,
                    practiceCategory = "GRAMMAR",
                    exerciseType = "GRAMMAR_CORRECTION",
                    difficulty = 1,
                    promptText = "${spec.titleUz}: Quyidagi o'zbekcha gapga mos inglizcha jumlani tanlang:",
                    subPromptText = spec.grammarSec1.third.second,
                    audioTextToSpeak = spec.grammarSec1.third.first,
                    optionsPipeSeparated = "${spec.grammarSec1.third.first}|${spec.grammarSec2.third.first}|Hello apple cat.",
                    correctAnswer = spec.grammarSec1.third.first,
                    explanationUzbek = spec.grammarSec1.second,
                    vocabularyIdsCsv = "voc_${spec.order}_1_1",
                    grammarRuleIdsCsv = "gram_${spec.order}_01"
                )
            )

            // 3. Listening Comprehension
            list.add(
                ExerciseEntity(
                    id = "ex_${spec.order}_listening",
                    topicId = spec.id,
                    sectionId = sec2Id,
                    practiceCategory = "LISTENING",
                    exerciseType = "LISTENING_COMPREHENSION",
                    difficulty = 1,
                    promptText = "Audioni tinglang va aytilgan inglizcha jumlani toping:",
                    subPromptText = "Ma'nosi: ${secondWord.third.second}",
                    audioTextToSpeak = secondWord.third.first,
                    optionsPipeSeparated = "${secondWord.third.first}|${firstWord.third.first}|Good morning!",
                    correctAnswer = secondWord.third.first,
                    explanationUzbek = "Eshitilgan jumla: '${secondWord.third.first}' (${secondWord.third.second}).",
                    vocabularyIdsCsv = "voc_${spec.order}_2_1",
                    grammarRuleIdsCsv = "gram_${spec.order}_02"
                )
            )

            // 4. Reading Comprehension
            list.add(
                ExerciseEntity(
                    id = "ex_${spec.order}_reading",
                    topicId = spec.id,
                    sectionId = sec2Id,
                    practiceCategory = "READING",
                    exerciseType = "READING_COMPREHENSION",
                    difficulty = 1,
                    promptText = "Matnni o'qing: \"${firstWord.third.first} ${secondWord.third.first}\"",
                    subPromptText = "Matnda '${secondWord.first}' so'zi qanday ma'noda qatnashgan?",
                    audioTextToSpeak = "${firstWord.third.first} ${secondWord.third.first}",
                    optionsPipeSeparated = "${secondWord.second}|${firstWord.second}|maktab",
                    correctAnswer = secondWord.second,
                    explanationUzbek = "'${secondWord.first}' — '${secondWord.second}' ma'nosini bildiradi.",
                    vocabularyIdsCsv = "voc_${spec.order}_2_1",
                    grammarRuleIdsCsv = "gram_${spec.order}_02"
                )
            )

            // 5. Writing / Translation
            list.add(
                ExerciseEntity(
                    id = "ex_${spec.order}_writing",
                    topicId = spec.id,
                    sectionId = sec1Id,
                    practiceCategory = "WRITING",
                    exerciseType = "TRANSLATION",
                    difficulty = 2,
                    promptText = "'${firstWord.second}' so'zini ingliz tilida yozing:",
                    subPromptText = "Maslahat: Audio tugmani bosib eshitib ko'rishingiz mumkin.",
                    audioTextToSpeak = firstWord.first,
                    optionsPipeSeparated = "",
                    correctAnswer = firstWord.first,
                    explanationUzbek = "To'g'ri yozilishi: ${firstWord.first} (${firstWord.second}).",
                    vocabularyIdsCsv = "voc_${spec.order}_1_1",
                    grammarRuleIdsCsv = "gram_${spec.order}_01"
                )
            )
        }
        return list
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
            description = "Completed an entire curriculum topic and unlocked the next",
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
