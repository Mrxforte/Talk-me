package com.example.data.local

import com.example.core.database.AchievementEntity
import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.TopicEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * Complete offline-first seed data for the TalkMe 400-Lesson Curriculum (A1 to C2).
 *
 * Includes full Partiya 1 (Lessons 1–20: Introduction, Greetings, Self-Introduction,
 * Full Alphabet A–Z, Cardinal Numbers 1–20, and Ordinal Numbers 1st–20th with 75 vocabulary words)
 * plus structured modules for Partiya 2–20 (Lessons 21–400 across A1, A2, B1, B2, C1, C2).
 *
 * Every lesson follows the 6-part TalkMe book format:
 * 1. 🤖 TalkMe (TTS kirish va rag'bat)
 * 2. 📖 QOIDA (katta text, oddiy tilda, formulalar)
 * 3. 💬 MISOLLAR (hayotiy misollar va yangi so'zlar)
 * 4. ✍️ MASHQ (interaktiv savollar va javoblar)
 * 5. ✅ XULOSA (bugungi xulosa, yangi so'zlar, uyga vazifa)
 * 6. 🤖 TalkMe YAKUNIY (rag'bat va keyingi darsga yo'llanma)
 */
object CurriculumSeedData {

    fun buildTopics(): List<TopicEntity> = listOf(
        TopicEntity(
            id = "topic_01_introduction",
            orderIndex = 1,
            titleEnglish = "Introduction",
            titleUzbek = "Kirish",
            descriptionUzbek = "1–10 Darslar • Misollar: \"Hello, teacher!\", \"My name is Ali.\", \"What is your name?\" va A–Z (26 ta harf: Apple, Book, Cat).",
            isUnlocked = true,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_02_numbers",
            orderIndex = 2,
            titleEnglish = "A1.1 • Numbers 1–20 & Ordinals",
            titleUzbek = "A1 • Sonlar 1–20 va Tartib sonlar (11–20 dars)",
            descriptionUzbek = "Misollar: \"one book, two books\", \"thirteen students\", \"the first lesson, the second day, the twentieth century\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_03_nouns",
            orderIndex = 3,
            titleEnglish = "A1.2 • Nouns & Plurals",
            titleUzbek = "A1 • Otlar va Ko'plik (21–32 dars)",
            descriptionUzbek = "Misollar: \"a book → two books\", \"a box → boxes\", \"a baby → babies\", \"man → men, child → children\", \"water, milk\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_04_adjectives",
            orderIndex = 4,
            titleEnglish = "A1.3 • Adjectives, Colors & Size",
            titleUzbek = "A1 • Sifatlar, Ranglar va Hajm (33–40 dars)",
            descriptionUzbek = "Misollar: \"a red car\", \"a big house\", \"hot tea, cold water\", \"The book is new.\" (ot oldida va to be dan keyin).",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_05_pronouns",
            orderIndex = 5,
            titleEnglish = "A1.4 • Pronouns & Demonstratives",
            titleUzbek = "A1 • Olmoshlar va Ko'rsatish (41–50 dars)",
            descriptionUzbek = "Misollar: \"I am a student\", \"Give me the book\", \"my car, your house\", \"This is a book / Those are trees\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_06_adverbs",
            orderIndex = 6,
            titleEnglish = "A1.5 • Adverbs & Frequency",
            titleUzbek = "A1 • Ravishlar va Takrorlanish (51–60 dars)",
            descriptionUzbek = "Misollar: \"He walks slowly\", \"She speaks English well\", \"I always get up early\", \"He is never late\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_07_verbs_a1",
            orderIndex = 7,
            titleEnglish = "A1.6 • To Be, Have & Simple Tenses",
            titleUzbek = "A1 • Fe'llar va Oddiy Zamonlar (61–80 dars)",
            descriptionUzbek = "Misollar: \"I am / He is / They are\", \"She has a car\", \"He works every day\", \"I went yesterday\", \"I will help you\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_08_articles_prepositions",
            orderIndex = 8,
            titleEnglish = "A1.7 • Articles, Prepositions & Conjunctions",
            titleUzbek = "A1 • Artikl, Predlog va Bog'lovchi (81–100 dars)",
            descriptionUzbek = "Misollar: \"a book, an apple, the sun\", \"in the room, on the table, at school\", \"tea and coffee, tired but happy\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_09_sentences_a1",
            orderIndex = 9,
            titleEnglish = "A1.8 • Sentence Structure & There Is/Are",
            titleUzbek = "A1 • Gap Tuzilishi va So'roqlar (101–110 dars)",
            descriptionUzbek = "Misollar: \"The boy reads a book\", \"Where do you live?\", \"Open the book, please!\", \"There is a book on the table\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_10_a2_grammar",
            orderIndex = 10,
            titleEnglish = "A2 • Continuous, Degrees & Quantifiers",
            titleUzbek = "A2 • Grammatika va Hozirgi Davom (111–190 dars)",
            descriptionUzbek = "Misollar: \"bigger than, the most interesting\", \"some water, any questions\", \"I am reading now\", \"When I came home...\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_11_b1_perfect_passive",
            orderIndex = 11,
            titleEnglish = "B1 • Perfect Tenses, Passive & Reported",
            titleUzbek = "B1 • Tugallangan Zamonlar va Majhul Nisbat (191–270 dars)",
            descriptionUzbek = "Misollar: \"I have finished\", \"She had already left\", \"The book was written\", \"He said that he was busy\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_12_b2_modals_nonfinite",
            orderIndex = 12,
            titleEnglish = "B2 • Modals, Infinitive, Gerund & Participle",
            titleUzbek = "B2 • Davom Zamonlar, Modallar va Gerund (271–336 dars)",
            descriptionUzbek = "Misollar: \"I have been waiting\", \"You should study\", \"I enjoy reading\", \"I want you to know\", \"Having finished...\".",
            isUnlocked = false,
            isCompleted = false
        ),
        TopicEntity(
            id = "topic_13_c1_c2_mastery",
            orderIndex = 13,
            titleEnglish = "C1–C2 • Nominative, Conditionals & Inversion",
            titleUzbek = "C1–C2 • Shart, Inversiya, Stilistika va Bitiruv (337–400 dars)",
            descriptionUzbek = "Misollar: \"He is said to live in London\", \"If I had known...\", \"I wish I knew\", \"Never have I seen such a thing\".",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 1 — KIRISH (DARS 1–10) inside topic_01_introduction
        // =========================================================================
        LessonSectionEntity(
            id = "sec_01_01",
            topicId = "topic_01_introduction",
            orderIndex = 1,
            titleEnglish = "1-Dars: Welcome to TalkMe",
            titleUzbek = "Xush kelibsiz — TalkMe bilan tanishuv",
            objectiveUzbek = "🤖 TalkMe: Salom! Men TalkMe — yordamchingizman. Men sizni hukm qilmayman, xafa bo'lmayman. Xato qilsangiz — bu o'rganish. Men har doim siz bilan bo'laman. 🚀",
            explanationUzbek = "📖 QOIDA:\nTalkMe — sizning shaxsiy ingliz tili yordamchingiz. U sizga yo'l ko'rsatadi, savollaringizga javob beradi va xato qilsangiz xafa bo'lmaydi.\n\nUchta oltin qoida:\n1) \"Men qila olmayman\" demang.\n2) Xato qilishdan qo'rqmang — har bir xato bu o'rganish.\n3) Har kuni ozgina bo'lsa ham o'qing.\n\n💬 MISOLLAR:\n• Siz: \"Men qo'rqaman.\" → TalkMe: \"Qo'rqmang! Men siz bilan bo'laman.\"\n• Siz: \"Men ingliz tilini o'rganmoqchiman.\" → TalkMe: \"Ajoyib! Boshlaymiz!\"\n\n✍️ MASHQ:\n1-mashq: \"Men ingliz tilini o'rgana olaman!\" — 3 marta ovoz chiqarib ayting.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: TalkMe — yordamchingiz. 3 ta qoida. Yangi so'z yo'q. Uyga vazifa: o'zingizga \"Men o'rgana olaman!\" deb 3 marta ayting.\n\n🤖 TalkMe yakuniy: Siz birinchi darsni o'qidingiz, [USER_NAME] — bu katta qadam! Ertaga: app qanday ishlaydi. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_02",
            topicId = "topic_01_introduction",
            orderIndex = 2,
            titleEnglish = "2-Dars: How TalkMe Works",
            titleUzbek = "App qanday ishlaymiz?",
            objectiveUzbek = "🤖 TalkMe: Bugun appdagi tugmalarni o'rganamiz. To'rtta asosiy tugma bor. Juda oddiy! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo'rtta asosiy tugma:\n• 🔊 Eshit — so'z va qoidalarni ovoz bilan eshitish.\n• ▶️ Davom — darsni tugatib keyingi darsga o'tish.\n• ✍️ Mashq — interaktiv mashqlarni bajarish.\n• 🤖 TalkMe — ovozli AI yordamchi bilan inglizcha gaplashish.\n\nHar bir dars 5 qismdan iborat:\nTalkMe → Qoida → Misollar → Mashq → Xulosa + TalkMe.\n\n💬 MISOLLAR:\n• So'zni eshitish uchun → 🔊 Eshit\n• Keyingi darsga o'tish uchun → ▶️ Davom\n• Savol berish va gaplashish uchun → 🤖 TalkMe\n\n✍️ MASHQ:\n1. \"🔊 Eshit\" nima qiladi? → ✅ Ovoz bilan eshitish.\n2. \"▶️ Davom\" nima qiladi? → ✅ Keyingi darsga o'tish.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 4 ta tugma: Eshit, Davom, Mashq, TalkMe. 5 qism: TalkMe → Qoida → Misollar → Mashq → Xulosa. Uyga vazifa: har bir tugmani bir marta bosing.\n\n🤖 TalkMe yakuniy: Endi siz appni bilasiz, [USER_NAME]! Ertaga: birinchi so'zlar — Hello! Hi! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_03",
            topicId = "topic_01_introduction",
            orderIndex = 3,
            titleEnglish = "3-Dars: Hello! Hi!",
            titleUzbek = "Salomlashish: Hello va Hi",
            objectiveUzbek = "🤖 TalkMe: Bugun birinchi inglizcha so'zlarni o'rganamiz: Hello! va Hi! Ikkisi ham \"Salom!\" degani. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Hello (talaffuzi: helou) — rasmiy salom (o'qituvchi, katta yoshli inson, notanish odam).\n• Hi (talaffuzi: hay) — do'stona salom (do'st, oila, tengdosh).\nIkkisi ham bir xil \"Salom!\" ma'nosini beradi.\n\n💬 MISOLLAR:\n• Maktabda: \"Hello, teacher!\" — Salom, ustoz!\n• Do'st bilan: \"Hi, Vali!\" — Salom, Vali!\n• Uyda: \"Hi, mom!\" — Salom, oyi!\n\n✍️ MASHQ:\n1. O'qituvchiga nima deysiz? a) Hi b) Hello → ✅ b) Hello\n2. Do'stingizga nima deysiz? a) Hello b) Hi → ✅ b) Hi\n3. Ovoz chiqarib o'qing: \"Hello! Hi! Hello! Hi!\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Hello — rasmiy. Hi — do'stona. Ikkisi ham \"Salom!\" Yangi so'zlar: Hello, Hi. Uyga vazifa: 3 kishiga \"Hello!\" yoki \"Hi!\" deb ayting.\n\n🤖 TalkMe yakuniy: Ajoyib, [USER_NAME]! Birinchi inglizcha so'zlaringiz! Ertaga: Good morning! Good bye! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_04",
            topicId = "topic_01_introduction",
            orderIndex = 4,
            titleEnglish = "4-Dars: Good morning! Good bye!",
            titleUzbek = "Xayrli tong va Xayr",
            objectiveUzbek = "🤖 TalkMe: Bugun yana ikki muhim so'z: Good morning! va Good bye! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Good morning (talaffuzi: gud morning) — \"Xayrli tong!\" Ertalab soat 12:00 gacha ishlatiladi.\n• Good bye (talaffuzi: gud bay) — \"Xayr!\" Har doim xayrlashganda aytiladi.\n• Qisqartma: Bye! (do'stona \"Xayr!\").\n\n💬 MISOLLAR:\n• Ertalab uyda: \"Good morning, mom!\"\n• Maktabga kelganda: \"Good morning, teacher!\"\n• Maktabdan ketayotganda: \"Good bye, teacher!\"\n• Do'st bilan: \"Bye, Vali!\"\n\n✍️ MASHQ:\n1. Ertalab onangizga nima deysiz? a) Good bye b) Good morning → ✅ b) Good morning\n2. Maktabdan ketayotganda? a) Good morning b) Good bye → ✅ b) Good bye\n3. Ovoz chiqarib o'qing: \"Good morning! Good bye! Bye!\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Good morning — ertalab. Good bye — xayrlashganda. Qisqartma: Bye. Yangi so'zlar: Good morning, Good bye, Bye. Uyga vazifa: ertalab \"Good morning!\", kechqurun \"Good bye!\" deb ayting.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz 5 ta iborani bilasiz! Ertaga: Thank you! Please! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_05",
            topicId = "topic_01_introduction",
            orderIndex = 5,
            titleEnglish = "5-Dars: Thank you! Please!",
            titleUzbek = "Odob so'zlari: Thank you va Please",
            objectiveUzbek = "🤖 TalkMe: Bugun ikki oltin odob so'zini o'rganamiz: Thank you! va Please! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Thank you (talaffuzi: thenk yu) — \"Rahmat!\" (kimdir yordam berganda yoki narsa uzatganda). Qisqartmasi: Thanks!\n• Please (talaffuzi: pliz) — \"Iltimos!\" (kimdandir narsa so'raganda). \"Please\" gap boshida yoki oxirida kelishi mumkin.\n\n💬 MISOLLAR:\n• Kimdir kitob berganda: \"Thank you!\"\n• Suv so'raganda: \"Water, please.\" — Iltimos, suv bering.\n• Yordam so'raganda: \"Help me, please.\" — Menga yordam bering, iltimos.\n• \"Please, sit down.\" = \"Sit down, please.\"\n\n✍️ MASHQ:\n1. Kimdir kitob berganda? a) Good bye b) Thank you → ✅ b) Thank you\n2. Suv so'raganda? a) Water, please b) Water, thank you → ✅ a) Water, please\n3. Ovoz chiqarib: \"Thank you! Please! Thank you! Please!\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Thank you — rahmat. Please — iltimos. Qisqartma: Thanks. Uyga vazifa: 5 marta \"Thank you!\", 3 marta \"Please!\" deb ayting.\n\n🤖 TalkMe yakuniy: Ajoyib, [USER_NAME]! Odob so'zlarini ham o'rgandingiz! Ertaga: o'zimizni tanishtiramiz — My name is... 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_06",
            topicId = "topic_01_introduction",
            orderIndex = 6,
            titleEnglish = "6-Dars: My name is...",
            titleUzbek = "O'zimizni tanishtiramiz",
            objectiveUzbek = "🤖 TalkMe: Bugun o'zimizni ingliz tilida tanishtiramiz! \"Mening ismim Ali\" — \"My name is Ali\". 🚀",
            explanationUzbek = "📖 QOIDA:\nFormula: My name is + Ismingiz.\nSo'zma-so'z: My (mening) + name (ism) + is (...dir) + ism.\nQisqartma: My name's.\nMuhim: Ismingizni har doim bosh harf bilan yozing!\n\n💬 MISOLLAR:\n• My name is Ali. — Mening ismim Ali.\n• My name is Mary. — Mening ismim Meri.\n• My name's Aziz. = My name is Aziz.\n\n✍️ MASHQ:\n1. Tartibga qo'ying: \"is / name / my / Ali\" → ✅ My name is Ali.\n2. Bo'sh joyni to'ldiring: \"___ name is Mary.\" → ✅ My\n3. Tarjima qiling: \"Mening ismim Aziz.\" → ✅ My name is Aziz.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Formula: My name is + ism. Qisqartma: My name's. Yangi so'zlar: My, name, is. Uyga vazifa: 3 kishiga \"My name is [USER_NAME]\" deb ayting.\n\n🤖 TalkMe yakuniy: Zo'r! Endi o'zingizni tanishtira olasiz! Ertaga: What is your name? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_07",
            topicId = "topic_01_introduction",
            orderIndex = 7,
            titleEnglish = "7-Dars: What is your name?",
            titleUzbek = "Sening isming nima?",
            objectiveUzbek = "🤖 TalkMe: Bugun suhbatdoshimizning ismini so'raymiz: \"What is your name?\" — \"Sening isming nima?\" 🚀",
            explanationUzbek = "📖 QOIDA:\nFormula: What is your name?\nSo'zma-so'z: What (nima) + is (...dir) + your (sening/sizning) + name (ism).\nQisqartma: What's your name?\nJavob: \"My name is...\" yoki \"I am...\"\n\n💬 MISOLLAR:\n• Siz: \"Hello! What is your name?\"\n• U: \"My name is Vali. What is your name?\"\n• Siz: \"My name is Ali.\"\n\n✍️ MASHQ:\n1. Tartibga qo'ying: \"your / what / name / is\" → ✅ What is your name?\n2. Tarjima qiling: \"Sening isming nima?\" → ✅ What is your name?\n3. Javob bering: \"What is your name?\" → ✅ My name is [USER_NAME].",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Formula: What is your name? Javob: My name is... Yangi so'zlar: What, your. Uyga vazifa: 3 kishidan \"What is your name?\" deb so'rang.\n\n🤖 TalkMe yakuniy: Ajoyib! Endi siz to'liq tanishuv suhbatini bilasiz! Ertaga: alifbo — A B C D E. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_08",
            topicId = "topic_01_introduction",
            orderIndex = 8,
            titleEnglish = "8-Dars: Alphabet A B C D E",
            titleUzbek = "Alifbo: A, B, C, D, E",
            objectiveUzbek = "🤖 TalkMe: Bugun ingliz alifbosini boshlaymiz! Birinchi 5 ta harf: A, B, C, D, E. 🚀",
            explanationUzbek = "📖 QOIDA:\nIngliz alifbosida 26 ta harf bor. Birinchi 5 tasi:\n• A a (ey) — apple (olma)\n• B b (bi) — book (kitob)\n• C c (si) — cat (mushuk)\n• D d (di) — dog (it)\n• E e (i) — egg (tuxum)\n\nMuhim: Harfning alifbodagi nomi bilan so'z ichidagi tovushi farq qiladi! Katta va kichik shakllar: A-a, B-b, C-c, D-d, E-e.\n\n💬 MISOLLAR:\n• A — Ali, apple\n• B — Bob, book\n• C — Charlie, cat\n• D — David, dog\n• E — Emma, egg\n\n✍️ MASHQ:\n1. Harflarni to'ldiring: A, __, C, __, E → ✅ A, B, C, D, E\n2. \"B\" ning kichik shakli nima? → ✅ b\n3. Qaysi so'z \"A\" bilan boshlanadi? a) book b) apple → ✅ b) apple\n4. Ovoz chiqarib o'qing: \"A, B, C, D, E.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 5 ta harf: A, B, C, D, E. Yangi so'zlar: apple, book, cat, dog, egg. Uyga vazifa: A–E ni 5 marta ovoz chiqarib o'qing.\n\n🤖 TalkMe yakuniy: Zo'r! 5 ta harf va 5 ta yangi so'z! Ertaga: F, G, H, I, J, K, L, M. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_09",
            topicId = "topic_01_introduction",
            orderIndex = 9,
            titleEnglish = "9-Dars: Alphabet F G H I J K L M",
            titleUzbek = "Alifbo: F dan M gacha",
            objectiveUzbek = "🤖 TalkMe: Bugun keyingi 8 ta harfni o'rganamiz: F, G, H, I, J, K, L, M. 🚀",
            explanationUzbek = "📖 QOIDA:\n• F f (ef) — fish (baliq)\n• G g (ji) — girl (qiz bola)\n• H h (eych) — house (uy)\n• I i (ay) — ice (muz)\n• J j (jey) — juice (sharbat)\n• K k (key) — key (kalit)\n• L l (el) — lion (sher)\n• M m (em) — moon (oy)\n\nMuhim: \"I\" (men) olmoshi gapda har doim katta harf bilan yoziladi: \"I am a student.\"\n\n💬 MISOLLAR:\n• F — Friday, fish\n• G — George, girl\n• H — Helen, house\n• I — Ivan, ice; I am a student.\n• J — John, juice\n• K — Kate, key\n• L — Lucy, lion\n• M — Mary, moon\n\n✍️ MASHQ:\n1. F, __, H, __, J, __, L, __ → ✅ F, G, H, I, J, K, L, M\n2. \"G\" ning kichigi? → ✅ g\n3. Qaysi so'z \"M\" bilan boshlanadi? a) moon b) book → ✅ a) moon",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 8 ta harf: F–M. \"I\" (men) — har doim bosh harf. Yangi so'zlar: fish, girl, house, ice, juice, key, lion, moon. Uyga vazifa: F–M ni 5 marta o'qing.\n\n🤖 TalkMe yakuniy: Ajoyib! Jami 13 ta harfni bilasiz! Ertaga: N dan Z gacha. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_01_10",
            topicId = "topic_01_introduction",
            orderIndex = 10,
            titleEnglish = "10-Dars: Alphabet N–Z & Full Review",
            titleUzbek = "Alifbo: N dan Z gacha (To'liq 26 harf)",
            objectiveUzbek = "🤖 TalkMe: Bugun qolgan 13 ta harf! Shundan keyin siz butun ingliz alifbosini to'liq bilasiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• N n (en) — name (ism)\n• O o (ou) — orange (apelsin)\n• P p (pi) — pen (ruchka)\n• Q q (kyu) — queen (qirolicha)\n• R r (ar) — red (qizil)\n• S s (es) — sun (quyosh)\n• T t (ti) — table (stol)\n• U u (yu) — umbrella (soyabon)\n• V v (vi) — van (furgon)\n• W w (dabl yu) — water (suv)\n• X x (eks) — box (quti)\n• Y y (way) — yellow (sariq)\n• Z z (zed) — zebra (zebra)\n\n💬 MISOLLAR:\n• N — Nancy, name | O — Oliver, orange | P — Peter, pen\n• R — Robert, red | S — Sarah, sun | T — Tom, table\n• W — William, water | Y — Yusuf, yellow | Z — Zebra\n\n✍️ MASHQ:\n1. N, __, P, __, R, __, T, __, V, __, X, __, Z → ✅ N, O, P, Q, R, S, T, U, V, W, X, Y, Z\n2. \"Q\" ning kichigi? → ✅ q\n3. Qaysi so'z \"W\" bilan boshlanadi? a) water b) van → ✅ a) water\n4. To'liq alifboni o'qing: A–Z.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To'liq alifbo: 26 ta harf! Yangi so'zlar: name, orange, pen, queen, red, sun, table, umbrella, van, water, box, yellow, zebra. Uyga vazifa: A–Z alifboni 5 marta o'qing.\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz butun ingliz alifbosini o'zlashtirdingiz! Keyingi darsda sonlarni (one, two, three) boshlaymiz! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 1 — SONLAR (DARS 11–20) inside topic_02_numbers
        // =========================================================================
        LessonSectionEntity(
            id = "sec_02_11",
            topicId = "topic_02_numbers",
            orderIndex = 1,
            titleEnglish = "11-Dars: 1–3 — One, Two, Three",
            titleUzbek = "Sonlar 1–3: One, Two, Three",
            objectiveUzbek = "🤖 TalkMe: Endi sonlarni boshlaymiz! One — bir. Two — ikki. Three — uch. Faqat 3 ta so'z! 🚀",
            explanationUzbek = "📖 QOIDA:\n• One (uan) — 1 (bir)\n• Two (tu) — 2 (ikki)\n• Three (thri) — 3 (uch)\n\nMuhim qoida:\n• One + birlik ot: one apple (bitta olma), one book (bitta kitob).\n• Two / Three + ko'plik ot (-s qo'shiladi): two apples (ikkita olma), three books (uchta kitob).\n\n💬 MISOLLAR:\n• I have one book. — Menda bitta kitob bor.\n• I have two books. — Menda ikkita kitob bor.\n• She has three cats. — Uning uchta mushugi bor.\n\n✍️ MASHQ:\n1. \"Menda ikkita kitob bor\" → ✅ I have two books.\n2. \"Uchta olma\" → ✅ three apples\n3. Bo'sh joy: \"I have ___ apple.\" (1 ta) → ✅ one\n4. Ovoz chiqarib: \"One, two, three.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta son: one (1), two (2), three (3). One + birlik, Two/three + ko'plik (-s). Uyga vazifa: uydagi 3 ta narsani sanang: \"One book, two books, three books.\"\n\n🤖 TalkMe yakuniy: Ajoyib! 1 dan 3 gacha sanay olasiz! Ertaga: 4, 5, 6. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_12",
            topicId = "topic_02_numbers",
            orderIndex = 2,
            titleEnglish = "12-Dars: 4–6 — Four, Five, Six",
            titleUzbek = "Sonlar 4–6: Four, Five, Six",
            objectiveUzbek = "🤖 TalkMe: Bugun keyingi 3 ta son: four, five, six. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Four (fo) — 4 (to'rt)\n• Five (fayv) — 5 (besh)\n• Six (siks) — 6 (olti)\n\nQoida: Four, five, six dan keyin ot ko'plikda (-s bilan) keladi: four apples, five books, six cats.\n\n💬 MISOLLAR:\n• I have four pens. — Menda to'rtta ruchka bor.\n• She has five books. — Uning beshta kitobi bor.\n• There are six students. — Oltita talaba bor.\n\n✍️ MASHQ:\n1. \"To'rtta olma\" → ✅ four apples\n2. \"Beshta kitob\" → ✅ five books\n3. Bo'sh joy: \"I have ___ cats.\" (6 ta) → ✅ six\n4. Ovoz chiqarib: \"Four, five, six.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta son: four (4), five (5), six (6). Ko'plik bilan: four apples. Uyga vazifa: 1 dan 6 gacha inglizcha sanang.\n\n🤖 TalkMe yakuniy: Zo'r! Endi 1 dan 6 gacha bilasiz! Ertaga: 7, 8, 9, 10. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_13",
            topicId = "topic_02_numbers",
            orderIndex = 3,
            titleEnglish = "13-Dars: 7–10 — Seven, Eight, Nine, Ten",
            titleUzbek = "Sonlar 7–10: Seven dan Ten gacha",
            objectiveUzbek = "🤖 TalkMe: Bugun 4 ta son: seven, eight, nine, ten. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Seven (sevan) — 7 (yetti)\n• Eight (eyt) — 8 (sakkiz)\n• Nine (nayn) — 9 (to'qqiz)\n• Ten (ten) — 10 (o'n)\n\nQoida: Ko'plik bilan ishlatiladi: seven apples, eight years old, nine students, ten books.\n\n💬 MISOLLAR:\n• I have seven apples. — Menda yettita olma bor.\n• She is eight years old. — U sakkiz yoshda.\n• There are nine students. — To'qqizta talaba bor.\n• I have ten books. — Menda o'nta kitob bor.\n\n✍️ MASHQ:\n1. \"Yettita olma\" → ✅ seven apples\n2. \"Sakkiz yoshda\" → ✅ eight years old\n3. Bo'sh joy: \"I have ___ books.\" (10 ta) → ✅ ten\n4. Ovoz chiqarib: \"Seven, eight, nine, ten.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 4 ta son: seven (7), eight (8), nine (9), ten (10). Uyga vazifa: 1 dan 10 gacha ovoz chiqarib sanang.\n\n🤖 TalkMe yakuniy: Ajoyib! Endi 1–10 sonlarni to'liq bilasiz! Ertaga: 11–15. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_14",
            topicId = "topic_02_numbers",
            orderIndex = 4,
            titleEnglish = "14-Dars: 11–15 — Eleven to Fifteen",
            titleUzbek = "Sonlar 11–15: Eleven... Fifteen",
            objectiveUzbek = "🤖 TalkMe: Bugun 5 ta son: eleven, twelve, thirteen, fourteen, fifteen. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Eleven (ilevan) — 11 (o'n bir)\n• Twelve (tvelv) — 12 (o'n ikki)\n• Thirteen (thertin) — 13 (o'n uch)\n• Fourteen (fortin) — 14 (o'n to'rt)\n• Fifteen (fiftin) — 15 (o'n besh)\n\nMuhim: 13–15 da oxiriga -teen qo'shiladi. Lekin three → thirteen, five → fifteen da o'zak shakli biroz o'zgaradi!\n\n💬 MISOLLAR:\n• I have eleven books. — Menda o'n bitta kitob bor.\n• She is twelve years old. — U o'n ikki yoshda.\n• There are thirteen students. — O'n uchta talaba bor.\n• He is fourteen. — U o'n to'rt yoshda.\n• My sister is fifteen. — Opam o'n besh yoshda.\n\n✍️ MASHQ:\n1. \"O'n ikkita kitob\" → ✅ twelve books\n2. \"O'n besh yoshda\" → ✅ fifteen years old\n3. Bo'sh joy: \"I have ___ apples.\" (13 ta) → ✅ thirteen",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 5 ta son: eleven (11) – fifteen (15). -teen qo'shimchasi. Uyga vazifa: 11 dan 15 gacha sanang.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: 16–20. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_15",
            topicId = "topic_02_numbers",
            orderIndex = 5,
            titleEnglish = "15-Dars: 16–20 — Sixteen to Twenty",
            titleUzbek = "Sonlar 16–20: Sixteen... Twenty",
            objectiveUzbek = "🤖 TalkMe: Bugun qolgan 5 ta son: sixteen, seventeen, eighteen, nineteen, twenty. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Sixteen (sikstin) — 16 (o'n olti)\n• Seventeen (sevantin) — 17 (o'n yetti)\n• Eighteen (eytin) — 18 (o'n sakkiz — bitta 't' yoziladi)\n• Nineteen (nayntin) — 19 (o'n to'qqiz)\n• Twenty (tventi) — 20 (yigirma)\n\nMuhim: 16–19 sonlarida -teen, 20 sonida esa -ty (o'nlik) qo'shimchasi ishlatiladi.\n\n💬 MISOLLAR:\n• I have sixteen books. — Menda o'n oltita kitob bor.\n• She is seventeen. — U o'n yetti yoshda.\n• There are eighteen students. — O'n sakkizta talaba bor.\n• He is nineteen. — U o'n to'qqiz yoshda.\n• My brother is twenty. — Akam yigirma yoshda.\n\n✍️ MASHQ:\n1. \"O'n yetti yoshda\" → ✅ seventeen years old\n2. \"Yigirmata kitob\" → ✅ twenty books\n3. Bo'sh joy: \"I have ___ apples.\" (19 ta) → ✅ nineteen",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 5 ta son: sixteen (16) – twenty (20). 16–19: -teen, 20: -ty. Uyga vazifa: 1 dan 20 gacha sanang.\n\n🤖 TalkMe yakuniy: MUKAMMAL! Endi 1–20 sonlarini bilasiz! Ertaga: 1–20 takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_16",
            topicId = "topic_02_numbers",
            orderIndex = 6,
            titleEnglish = "16-Dars: Numbers 1–20 Review",
            titleUzbek = "1–20 Sonlarni Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun 1–20 sonlarini takrorlaymiz! Hech narsa yangi emas — faqat mustahkamlash! 🚀",
            explanationUzbek = "📖 QOIDA:\n1–20 sanoq sonlar:\n• 1–5: one, two, three, four, five\n• 6–10: six, seven, eight, nine, ten\n• 11–15: eleven, twelve, thirteen, fourteen, fifteen\n• 16–20: sixteen, seventeen, eighteen, nineteen, twenty\n\nMuhim eslatma: 11 (eleven) va 12 (twelve) — maxsus shakllar; 13–19 — -teen; 20 — -ty.\n\n💬 MISOLLAR:\n• I have seventeen books. — Menda o'n yettita kitob bor.\n• fourteen apples — o'n to'rtta olma.\n• twenty students — yigirmata talaba.\n\n✍️ MASHQ:\n1. 1–10 ni ovoz chiqarib sanang.\n2. 11–20 ni ovoz chiqarib sanang.\n3. Bo'sh joy: \"I have ___ books.\" (17 ta) → ✅ seventeen\n4. \"O'n to'rtta olma\" → ✅ fourteen apples",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 1–20 to'liq takrorlandi. -teen (13–19), -ty (20). Uyga vazifa: 1–20 ni 5 marta ovoz chiqarib sanang.\n\n🤖 TalkMe yakuniy: Ajoyib! 1–20 ni suvdek bilasiz! Ertaga: tartib sonlar — first, second, third. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_17",
            topicId = "topic_02_numbers",
            orderIndex = 7,
            titleEnglish = "17-Dars: Ordinals: First, Second, Third",
            titleUzbek = "Tartib sonlar: First, Second, Third",
            objectiveUzbek = "🤖 TalkMe: Endi tartib sonlarni boshlaymiz: \"birinchi, ikkinchi, uchinchi\". 🚀",
            explanationUzbek = "📖 QOIDA:\n• First (fyost) — 1-chi (birinchi)\n• Second (sekand) — 2-chi (ikkinchi)\n• Third (thyod) — 3-chi (uchinchi)\n\nMuhim: Birinchi uchta tartib son istisno — ularga -th qo'shilmaydi! Tartib sonlar oldidan har doim \"the\" artikli qo'yiladi.\n\n💬 MISOLLAR:\n• The first lesson — birinchi dars.\n• The second day — ikkinchi kun.\n• The third month — uchinchi oy.\n• This is the first book. — Bu birinchi kitob.\n\n✍️ MASHQ:\n1. \"Birinchi dars\" → ✅ the first lesson\n2. \"Ikkinchi kun\" → ✅ the second day\n3. \"Uchinchi oy\" → ✅ the third month\n4. Ovoz chiqarib: \"First, second, third.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta tartib son: first (1-chi), second (2-chi), third (3-chi). Oldidan the qo'yiladi. Uyga vazifa: \"the first\", \"the second\", \"the third\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: fourth, fifth, sixth. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_18",
            topicId = "topic_02_numbers",
            orderIndex = 8,
            titleEnglish = "18-Dars: Ordinals: Fourth, Fifth, Sixth",
            titleUzbek = "Tartib sonlar: Fourth, Fifth, Sixth",
            objectiveUzbek = "🤖 TalkMe: Bugun keyingi 3 ta tartib son: fourth, fifth, sixth. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Fourth (foth) — 4-chi (to'rtinchi)\n• Fifth (fifth) — 5-chi (beshinchi: five → fifth, \"ve\" → \"f\" ga aylanadi)\n• Sixth (siksth) — 6-chi (oltinchi)\n\nMuhim: 4-chidan boshlab son oxiriga -th qo'shiladi va oldidan \"the\" keladi.\n\n💬 MISOLLAR:\n• The fourth floor — to'rtinchi qavat.\n• The fifth grade — beshinchi sinf.\n• The sixth month — oltinchi oy.\n\n✍️ MASHQ:\n1. \"To'rtinchi qavat\" → ✅ the fourth floor\n2. \"Beshinchi sinf\" → ✅ the fifth grade\n3. \"Oltinchi oy\" → ✅ the sixth month\n4. Ovoz chiqarib: \"Fourth, fifth, sixth.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta tartib son: fourth (4-chi), fifth (5-chi), sixth (6-chi). Five → fifth. Uyga vazifa: \"the fourth\", \"the fifth\", \"the sixth\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Ajoyib! Ertaga: seventh–tenth. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_19",
            topicId = "topic_02_numbers",
            orderIndex = 9,
            titleEnglish = "19-Dars: Ordinals: Seventh to Tenth",
            titleUzbek = "Tartib sonlar: Seventh–Tenth",
            objectiveUzbek = "🤖 TalkMe: Bugun 4 ta tartib son: seventh, eighth, ninth, tenth. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Seventh (sevanth) — 7-chi (yettinchi)\n• Eighth (eyth) — 8-chi (sakkizinchi: eight → eighth, faqat \"h\" qo'shiladi)\n• Ninth (naynth) — 9-chi (to'qqizinchi: nine → ninth, \"e\" tushib qoladi)\n• Tenth (tenth) — 10-chi (o'ninchi)\nOldidan \"the\" qo'yiladi.\n\n💬 MISOLLAR:\n• The seventh week — yettinchi hafta.\n• The eighth year — sakkizinchi yil.\n• The ninth chapter — to'qqizinchi bob.\n• The tenth student — o'ninchi talaba.\n\n✍️ MASHQ:\n1. \"Yettinchi hafta\" → ✅ the seventh week\n2. \"Sakkizinchi yil\" → ✅ the eighth year\n3. \"To'qqizinchi bob\" → ✅ the ninth chapter\n4. Ovoz chiqarib: \"Seventh, eighth, ninth, tenth.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 4 ta tartib son: seventh (7-chi) – tenth (10-chi). Eighth (+h), Ninth (e tushadi). Uyga vazifa: \"the seventh\" dan \"the tenth\" gacha 4 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi 1–10 tartib sonlarini bilasiz! Ertaga: 11–20 tartib sonlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_02_20",
            topicId = "topic_02_numbers",
            orderIndex = 10,
            titleEnglish = "20-Dars: Ordinals 11–20 & Part 1 Review",
            titleUzbek = "Tartib sonlar 11–20 va Partiya 1 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun qolgan tartib sonlarni o'rganamiz va Partiya 1 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Eleventh — 11-chi\n• Twelfth — 12-chi (twelve → twelfth: \"ve\" → \"f\")\n• Thirteenth — 13-chi | Fourteenth — 14-chi | Fifteenth — 15-chi\n• Sixteenth — 16-chi | Seventeenth — 17-chi | Eighteenth — 18-chi | Nineteenth — 19-chi\n• Twentieth — 20-chi (twenty → twentieth: \"y\" → \"ie\" + th)\nOldidan har doim \"the\" ishlatiladi.\n\n💬 MISOLLAR:\n• The eleventh page — o'n birinchi bet.\n• The twelfth night — o'n ikkinchi kecha.\n• The twentieth century — yigirmanchi asr.\n\n✍️ MASHQ:\n1. \"O'n ikkinchi bet\" → ✅ the twelfth page\n2. \"Yigirmanchi asr\" → ✅ the twentieth century\n3. \"O'n beshinchi may\" → ✅ the fifteenth of May\n4. 1–20 tartib sonlarni takrorlang: first .. twentieth.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 10 ta tartib son: 11-chi – 20-chi. Muhim: twelfth (ve→f), twentieth (y→ie). Uyga vazifa: 1 dan 20 gacha tartib sonlarni 3 marta ovoz chiqarib o'qing.\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz 1–20 sanoq va 1–20 tartib sonlarini bilasiz! Partiya 1 (20 ta dars va 75 ta so'z) tugadi! Keyingi bo'lim — Partiya 2: Otlar! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 2–20 (DARS 21–400) ACROSS TOPICS 3–13
        // =========================================================================
    ) + CurriculumPartiya2SeedData.buildSections() +
        CurriculumPartiya3SeedData.buildSections() +
        CurriculumPartiya4SeedData.buildSections() +
        CurriculumPartiya5SeedData.buildSections() +
        CurriculumPartiya6And7SeedData.buildSections() +
        CurriculumPartiya8And9SeedData.buildSections() +
        CurriculumPartiya10And11SeedData.buildSections() +
        CurriculumPartiya12And13SeedData.buildSections() +
        CurriculumPartiya14And15SeedData.buildSections() +
        CurriculumPartiya16And17SeedData.buildSections() +
        CurriculumPartiya18To20SeedData.buildSections()

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 1 — DARS 1: WELCOME TO TALKME (3 core words)
        // =========================================================================
        VocabularyEntity("voc_01_welcome", "topic_01_introduction", "sec_01_01", "Welcome", "xush kelibsiz", "Welcome to TalkMe!", "TalkMe ga xush kelibsiz!", isStudied = true),
        VocabularyEntity("voc_01_learn", "topic_01_introduction", "sec_01_01", "Learn", "o'rganish", "I learn English step by step.", "Men ingliz tilini bosqichma-bosqich o'rganaman.", isStudied = true),
        VocabularyEntity("voc_01_start", "topic_01_introduction", "sec_01_01", "Start", "boshlash", "Let's start now!", "Hozir boshlaymiz!", isStudied = true),

        // =========================================================================
        // PARTIYA 1 — DARS 2: HOW TALKME WORKS (3 core words)
        // =========================================================================
        VocabularyEntity("voc_01_listen", "topic_01_introduction", "sec_01_02", "Listen", "eshitish", "Listen to audio.", "Audioni eshiting.", isStudied = true),
        VocabularyEntity("voc_01_practice", "topic_01_introduction", "sec_01_02", "Practice", "mashq qilish", "Practice every day.", "Har kuni mashq qiling.", isStudied = true),
        VocabularyEntity("voc_01_speak", "topic_01_introduction", "sec_01_02", "Speak", "gapirish", "Speak English with TalkMe.", "TalkMe bilan inglizcha gapiring.", isStudied = true),

        // =========================================================================
        // PARTIYA 1 — DARS 3–7: SALOMLASHISH VA TANISHUV (13 core words)
        // =========================================================================
        VocabularyEntity("voc_01_hello", "topic_01_introduction", "sec_01_03", "Hello", "salom (rasmiy)", "Hello, teacher!", "Salom, ustoz!", isStudied = true),
        VocabularyEntity("voc_01_hi", "topic_01_introduction", "sec_01_03", "Hi", "salom (do'stona)", "Hi, Vali!", "Salom, Vali!", isStudied = true),
        VocabularyEntity("voc_01_teacher", "topic_01_introduction", "sec_01_03", "Teacher", "o'qituvchi", "Hello, teacher!", "Salom, ustoz!", isStudied = true),
        VocabularyEntity("voc_01_good_morning", "topic_01_introduction", "sec_01_04", "Good morning", "xayrli tong", "Good morning, mom!", "Xayrli tong, oyi!", isStudied = true),
        VocabularyEntity("voc_01_good_bye", "topic_01_introduction", "sec_01_04", "Good bye", "xayr", "Good bye, teacher!", "Xayr, ustoz!", isStudied = true),
        VocabularyEntity("voc_01_bye", "topic_01_introduction", "sec_01_04", "Bye", "xayr (do'stona)", "Bye, Vali!", "Xayr, Vali!", isStudied = true),
        VocabularyEntity("voc_01_thank_you", "topic_01_introduction", "sec_01_05", "Thank you", "rahmat", "Thank you very much!", "Katta rahmat!", isStudied = true),
        VocabularyEntity("voc_01_thanks", "topic_01_introduction", "sec_01_05", "Thanks", "rahmat (do'stona)", "Thanks a lot!", "Katta rahmat!", isStudied = true),
        VocabularyEntity("voc_01_please", "topic_01_introduction", "sec_01_05", "Please", "iltimos", "Water, please.", "Iltimos, suv bering.", isStudied = true),
        VocabularyEntity("voc_01_my", "topic_01_introduction", "sec_01_06", "My", "mening", "My name is Ali.", "Mening ismim Ali.", isStudied = true),
        VocabularyEntity("voc_01_name", "topic_01_introduction", "sec_01_06", "Name", "ism", "My name is Mary.", "Mening ismim Meri.", isStudied = true),
        VocabularyEntity("voc_01_nice", "topic_01_introduction", "sec_01_06", "Nice", "yoqimli, yaxshi", "Nice to meet you.", "Tanishganimdan xursandman.", isStudied = true),
        VocabularyEntity("voc_01_what", "topic_01_introduction", "sec_01_07", "What", "nima", "What is your name?", "Sening isming nima?", isStudied = true),
        VocabularyEntity("voc_01_your", "topic_01_introduction", "sec_01_07", "Your", "sening yoki sizning", "What is your name?", "Sizning ismingiz nima?", isStudied = true),
        VocabularyEntity("voc_01_meet", "topic_01_introduction", "sec_01_07", "Meet", "tanishish", "Nice to meet you.", "Tanishganimdan xursandman.", isStudied = true),

        // =========================================================================
        // PARTIYA 1 — DARS 8: ALIFBO A–E (5 words)
        // =========================================================================
        VocabularyEntity("voc_01_apple", "topic_01_introduction", "sec_01_08", "Apple", "olma", "A is for apple.", "A harfi — apple (olma).", isStudied = true),
        VocabularyEntity("voc_01_book", "topic_01_introduction", "sec_01_08", "Book", "kitob", "B is for book.", "B harfi — book (kitob).", isStudied = true),
        VocabularyEntity("voc_01_cat", "topic_01_introduction", "sec_01_08", "Cat", "mushuk", "C is for cat.", "C harfi — cat (mushuk).", isStudied = true),
        VocabularyEntity("voc_01_dog", "topic_01_introduction", "sec_01_08", "Dog", "it, kuchuk", "D is for dog.", "D harfi — dog (it).", isStudied = true),
        VocabularyEntity("voc_01_egg", "topic_01_introduction", "sec_01_08", "Egg", "tuxum", "E is for egg.", "E harfi — egg (tuxum).", isStudied = true),

        // =========================================================================
        // PARTIYA 1 — DARS 9: ALIFBO F–M (8 words)
        // =========================================================================
        VocabularyEntity("voc_01_fish", "topic_01_introduction", "sec_01_09", "Fish", "baliq", "F is for fish.", "F harfi — fish (baliq).", isStudied = true),
        VocabularyEntity("voc_01_girl", "topic_01_introduction", "sec_01_09", "Girl", "qiz bola", "G is for girl.", "G harfi — girl (qiz bola).", isStudied = true),
        VocabularyEntity("voc_01_house", "topic_01_introduction", "sec_01_09", "House", "uy", "H is for house.", "H harfi — house (uy).", isStudied = true),
        VocabularyEntity("voc_01_ice", "topic_01_introduction", "sec_01_09", "Ice", "muz", "I is for ice.", "I harfi — ice (muz).", isStudied = true),
        VocabularyEntity("voc_01_juice", "topic_01_introduction", "sec_01_09", "Juice", "sharbat", "J is for juice.", "J harfi — juice (sharbat).", isStudied = true),
        VocabularyEntity("voc_01_key", "topic_01_introduction", "sec_01_09", "Key", "kalit", "K is for key.", "K harfi — key (kalit).", isStudied = true),
        VocabularyEntity("voc_01_lion", "topic_01_introduction", "sec_01_09", "Lion", "sher", "L is for lion.", "L harfi — lion (sher).", isStudied = true),
        VocabularyEntity("voc_01_moon", "topic_01_introduction", "sec_01_09", "Moon", "oy", "M is for moon.", "M harfi — moon (oy).", isStudied = true),

        // =========================================================================
        // PARTIYA 1 — DARS 10: ALIFBO N–Z (12 words; 'name' already above + friend)
        // =========================================================================
        VocabularyEntity("voc_01_orange", "topic_01_introduction", "sec_01_10", "Orange", "apelsin", "O is for orange.", "O harfi — orange (apelsin).", isStudied = true),
        VocabularyEntity("voc_01_pen", "topic_01_introduction", "sec_01_10", "Pen", "ruchka", "P is for pen.", "P harfi — pen (ruchka).", isStudied = true),
        VocabularyEntity("voc_01_queen", "topic_01_introduction", "sec_01_10", "Queen", "qirolicha", "Q is for queen.", "Q harfi — queen (qirolicha).", isStudied = true),
        VocabularyEntity("voc_01_red", "topic_01_introduction", "sec_01_10", "Red", "qizil", "R is for red.", "R harfi — red (qizil).", isStudied = true),
        VocabularyEntity("voc_01_sun", "topic_01_introduction", "sec_01_10", "Sun", "quyosh", "S is for sun.", "S harfi — sun (quyosh).", isStudied = true),
        VocabularyEntity("voc_01_table", "topic_01_introduction", "sec_01_10", "Table", "stol", "T is for table.", "T harfi — table (stol).", isStudied = true),
        VocabularyEntity("voc_01_umbrella", "topic_01_introduction", "sec_01_10", "Umbrella", "soyabon", "U is for umbrella.", "U harfi — umbrella (soyabon).", isStudied = true),
        VocabularyEntity("voc_01_van", "topic_01_introduction", "sec_01_10", "Van", "furgon mashina", "V is for van.", "V harfi — van (furgon).", isStudied = true),
        VocabularyEntity("voc_01_water", "topic_01_introduction", "sec_01_10", "Water", "suv", "W is for water.", "W harfi — water (suv).", isStudied = true),
        VocabularyEntity("voc_01_box", "topic_01_introduction", "sec_01_10", "Box", "quti", "X is in box.", "X harfi — box (quti).", isStudied = true),
        VocabularyEntity("voc_01_yellow", "topic_01_introduction", "sec_01_10", "Yellow", "sariq", "Y is for yellow.", "Y harfi — yellow (sariq).", isStudied = true),
        VocabularyEntity("voc_01_zebra", "topic_01_introduction", "sec_01_10", "Zebra", "zebra", "Z is for zebra.", "Z harfi — zebra.", isStudied = true),

        // =========================================================================
        // PARTIYA 1 — DARS 11–16: SANOQ SONLAR 1–20 (20 words)
        // =========================================================================
        VocabularyEntity("voc_02_one", "topic_02_numbers", "sec_02_11", "One", "bir (1)", "I have one book.", "Menda bitta kitob bor.", isStudied = true),
        VocabularyEntity("voc_02_two", "topic_02_numbers", "sec_02_11", "Two", "ikki (2)", "I have two books.", "Menda ikkita kitob bor.", isStudied = true),
        VocabularyEntity("voc_02_three", "topic_02_numbers", "sec_02_11", "Three", "uch (3)", "She has three cats.", "Uning uchta mushugi bor.", isStudied = true),
        VocabularyEntity("voc_02_four", "topic_02_numbers", "sec_02_12", "Four", "to'rt (4)", "I have four pens.", "Menda to'rtta ruchka bor.", isStudied = false),
        VocabularyEntity("voc_02_five", "topic_02_numbers", "sec_02_12", "Five", "besh (5)", "She has five books.", "Uning beshta kitobi bor.", isStudied = false),
        VocabularyEntity("voc_02_six", "topic_02_numbers", "sec_02_12", "Six", "olti (6)", "There are six students.", "Oltita talaba bor.", isStudied = false),
        VocabularyEntity("voc_02_seven", "topic_02_numbers", "sec_02_13", "Seven", "yetti (7)", "I have seven apples.", "Menda yettita olma bor.", isStudied = false),
        VocabularyEntity("voc_02_eight", "topic_02_numbers", "sec_02_13", "Eight", "sakkiz (8)", "She is eight years old.", "U sakkiz yoshda.", isStudied = false),
        VocabularyEntity("voc_02_nine", "topic_02_numbers", "sec_02_13", "Nine", "to'qqiz (9)", "There are nine students.", "To'qqizta talaba bor.", isStudied = false),
        VocabularyEntity("voc_02_ten", "topic_02_numbers", "sec_02_13", "Ten", "o'n (10)", "I have ten books.", "Menda o'nta kitob bor.", isStudied = false),
        VocabularyEntity("voc_02_eleven", "topic_02_numbers", "sec_02_14", "Eleven", "o'n bir (11)", "I have eleven books.", "Menda o'n bitta kitob bor.", isStudied = false),
        VocabularyEntity("voc_02_twelve", "topic_02_numbers", "sec_02_14", "Twelve", "o'n ikki (12)", "She is twelve years old.", "U o'n ikki yoshda.", isStudied = false),
        VocabularyEntity("voc_02_thirteen", "topic_02_numbers", "sec_02_14", "Thirteen", "o'n uch (13)", "There are thirteen students.", "O'n uchta talaba bor.", isStudied = false),
        VocabularyEntity("voc_02_fourteen", "topic_02_numbers", "sec_02_14", "Fourteen", "o'n to'rt (14)", "He is fourteen years old.", "U o'n to'rt yoshda.", isStudied = false),
        VocabularyEntity("voc_02_fifteen", "topic_02_numbers", "sec_02_14", "Fifteen", "o'n besh (15)", "My sister is fifteen.", "Opam o'n besh yoshda.", isStudied = false),
        VocabularyEntity("voc_02_sixteen", "topic_02_numbers", "sec_02_15", "Sixteen", "o'n olti (16)", "I have sixteen books.", "Menda o'n oltita kitob bor.", isStudied = false),
        VocabularyEntity("voc_02_seventeen", "topic_02_numbers", "sec_02_15", "Seventeen", "o'n yetti (17)", "She is seventeen years old.", "U o'n yetti yoshda.", isStudied = false),
        VocabularyEntity("voc_02_eighteen", "topic_02_numbers", "sec_02_15", "Eighteen", "o'n sakkiz (18)", "There are eighteen students.", "O'n sakkizta talaba bor.", isStudied = false),
        VocabularyEntity("voc_02_nineteen", "topic_02_numbers", "sec_02_15", "Nineteen", "o'n to'qqiz (19)", "He is nineteen years old.", "U o'n to'qqiz yoshda.", isStudied = false),
        VocabularyEntity("voc_02_twenty", "topic_02_numbers", "sec_02_15", "Twenty", "yigirma (20)", "My brother is twenty.", "Akam yigirma yoshda.", isStudied = false),

        // =========================================================================
        // PARTIYA 1 — DARS 17–20: TARTIB SONLAR 1–20 (20 words)
        // =========================================================================
        VocabularyEntity("voc_02_first", "topic_02_numbers", "sec_02_17", "First", "birinchi (1-chi)", "This is the first lesson.", "Bu birinchi dars.", isStudied = false),
        VocabularyEntity("voc_02_second", "topic_02_numbers", "sec_02_17", "Second", "ikkinchi (2-chi)", "The second day is easy.", "Ikkinchi kun oson.", isStudied = false),
        VocabularyEntity("voc_02_third", "topic_02_numbers", "sec_02_17", "Third", "uchinchi (3-chi)", "The third month is March.", "Uchinchi oy — mart.", isStudied = false),
        VocabularyEntity("voc_02_fourth", "topic_02_numbers", "sec_02_18", "Fourth", "to'rtinchi (4-chi)", "The fourth floor is high.", "To'rtinchi qavat baland.", isStudied = false),
        VocabularyEntity("voc_02_fifth", "topic_02_numbers", "sec_02_18", "Fifth", "beshinchi (5-chi)", "She is in the fifth grade.", "U beshinchi sinfda o'qiydi.", isStudied = false),
        VocabularyEntity("voc_02_sixth", "topic_02_numbers", "sec_02_18", "Sixth", "oltinchi (6-chi)", "June is the sixth month.", "Iyun — oltinchi oy.", isStudied = false),
        VocabularyEntity("voc_02_seventh", "topic_02_numbers", "sec_02_19", "Seventh", "yettinchi (7-chi)", "This is the seventh week.", "Bu yettinchi hafta.", isStudied = false),
        VocabularyEntity("voc_02_eighth", "topic_02_numbers", "sec_02_19", "Eighth", "sakkizinchi (8-chi)", "The eighth year was great.", "Sakkizinchi yil ajoyib bo'ldi.", isStudied = false),
        VocabularyEntity("voc_02_ninth", "topic_02_numbers", "sec_02_19", "Ninth", "to'qqizinchi (9-chi)", "Read the ninth chapter.", "To'qqizinchi bobni o'qing.", isStudied = false),
        VocabularyEntity("voc_02_tenth", "topic_02_numbers", "sec_02_19", "Tenth", "o'ninchi (10-chi)", "He is the tenth student.", "U o'ninchi talaba.", isStudied = false),
        VocabularyEntity("voc_02_eleventh", "topic_02_numbers", "sec_02_20", "Eleventh", "o'n birinchi (11-chi)", "Open the eleventh page.", "O'n birinchi betni oching.", isStudied = false),
        VocabularyEntity("voc_02_twelfth", "topic_02_numbers", "sec_02_20", "Twelfth", "o'n ikkinchi (12-chi)", "December is the twelfth month.", "Dekabr — o'n ikkinchi oy.", isStudied = false),
        VocabularyEntity("voc_02_thirteenth", "topic_02_numbers", "sec_02_20", "Thirteenth", "o'n uchinchi (13-chi)", "The thirteenth question is easy.", "O'n uchinchi savol oson.", isStudied = false),
        VocabularyEntity("voc_02_fourteenth", "topic_02_numbers", "sec_02_20", "Fourteenth", "o'n to'rtinchi (14-chi)", "Today is the fourteenth day.", "Bugun o'n to'rtinchi kun.", isStudied = false),
        VocabularyEntity("voc_02_fifteenth", "topic_02_numbers", "sec_02_20", "Fifteenth", "o'n beshinchi (15-chi)", "The fifteenth of May is sunny.", "O'n beshinchi may quyoshli.", isStudied = false),
        VocabularyEntity("voc_02_sixteenth", "topic_02_numbers", "sec_02_20", "Sixteenth", "o'n oltinchi (16-chi)", "This is the sixteenth lesson.", "Bu o'n oltinchi dars.", isStudied = false),
        VocabularyEntity("voc_02_seventeenth", "topic_02_numbers", "sec_02_20", "Seventeenth", "o'n yettinchi (17-chi)", "The seventeenth page is new.", "O'n yettinchi bet yangi.", isStudied = false),
        VocabularyEntity("voc_02_eighteenth", "topic_02_numbers", "sec_02_20", "Eighteenth", "o'n sakkizinchi (18-chi)", "Her eighteenth birthday is today.", "Bugun uning o'n sakkiz yoshga to'lgan kuni.", isStudied = false),
        VocabularyEntity("voc_02_nineteenth", "topic_02_numbers", "sec_02_20", "Nineteenth", "o'n to'qqizinchi (19-chi)", "This is the nineteenth lesson.", "Bu o'n to'qqizinchi dars.", isStudied = false),
        VocabularyEntity("voc_02_twentieth", "topic_02_numbers", "sec_02_20", "Twentieth", "yigirmanchi (20-chi)", "The twentieth lesson is complete.", "Yigirmanchi dars yakunlandi.", isStudied = false)
    ) + CurriculumPartiya2SeedData.buildVocabulary() +
        CurriculumPartiya3SeedData.buildVocabulary() +
        CurriculumPartiya4SeedData.buildVocabulary() +
        CurriculumPartiya5SeedData.buildVocabulary() +
        CurriculumPartiya6And7SeedData.buildVocabulary() +
        CurriculumPartiya8And9SeedData.buildVocabulary() +
        CurriculumPartiya10And11SeedData.buildVocabulary() +
        CurriculumPartiya12And13SeedData.buildVocabulary() +
        CurriculumPartiya14And15SeedData.buildVocabulary() +
        CurriculumPartiya16And17SeedData.buildVocabulary() +
        CurriculumPartiya18To20SeedData.buildVocabulary()

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // =========================================================================
        // PARTIYA 1 — DARS 1–10 GRAMMAR RULES
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_01_01",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_01",
            titleUzbek = "TalkMe 3 Oltin Qoidasi",
            explanationUzbek = "1) \"Men qila olmayman\" demang. 2) Xato qilishdan qo'rqmang — har bir xato bu o'rganish. 3) Har kuni ozgina o'qing.",
            exampleEnglish = "I can learn English step by step!",
            exampleUzbek = "Men ingliz tilini bosqichma-bosqich o'rgana olaman!",
            associatedVocabularyIdsCsv = "",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_02",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_02",
            titleUzbek = "4 ta tugma va 5 qismli dars tizimi",
            explanationUzbek = "Har bir darsda 4 ta tugma ishlaydi: 🔊 Eshit (audio), ▶️ Davom (keyingi dars), ✍️ Mashq (test), 🤖 TalkMe (AI suhbat). Dars 5 qismdan iborat: TalkMe → Qoida → Misollar → Mashq → Xulosa.",
            exampleEnglish = "Listen, practice, and speak with TalkMe!",
            exampleUzbek = "Eshiting, mashq qiling va TalkMe bilan gaplashing!",
            associatedVocabularyIdsCsv = "",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_03",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_03",
            titleUzbek = "Hello (rasmiy) va Hi (do'stona) farqi",
            explanationUzbek = "Hello — o'qituvchi, katta yoshli yoki notanish odamga rasmiy salom. Hi — do'st va oila a'zolariga do'stona salom.",
            exampleEnglish = "Hello, teacher! Hi, Vali!",
            exampleUzbek = "Salom, ustoz! Salom, Vali!",
            associatedVocabularyIdsCsv = "voc_01_hello,voc_01_hi",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_04",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_04",
            titleUzbek = "Good morning va Good bye / Bye",
            explanationUzbek = "Good morning — ertalab soat 12:00 gacha aytiladigan salom. Good bye (yoki qisqacha Bye) — xayrlashganda aytiladi.",
            exampleEnglish = "Good morning, mom! Good bye, teacher!",
            exampleUzbek = "Xayrli tong, oyi! Xayr, ustoz!",
            associatedVocabularyIdsCsv = "voc_01_good_morning,voc_01_good_bye,voc_01_bye",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_05",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_05",
            titleUzbek = "Odob so'zlari: Thank you va Please",
            explanationUzbek = "Thank you (qisqartmasi Thanks) — minnatdorchilik bildirganda. Please — iltimos qilganda gap boshida yoki oxirida ishlatiladi.",
            exampleEnglish = "Water, please. Thank you!",
            exampleUzbek = "Iltimos, suv bering. Rahmat!",
            associatedVocabularyIdsCsv = "voc_01_thank_you,voc_01_please",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_06",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_06",
            titleUzbek = "Formula: My name is + Ism",
            explanationUzbek = "O'z ismingizni aytish uchun \"My name is ...\" (qisqartmasi \"My name's ...\") formulasidan foydalanasiz. Ism har doim bosh harf bilan yoziladi.",
            exampleEnglish = "My name is Ali.",
            exampleUzbek = "Mening ismim Ali.",
            associatedVocabularyIdsCsv = "voc_01_my,voc_01_name",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_07",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_07",
            titleUzbek = "Formula: What is your name?",
            explanationUzbek = "Suhbatdoshning ismini so'rash uchun \"What is your name?\" (qisqartmasi \"What's your name?\") deyiladi.",
            exampleEnglish = "What is your name? My name is Vali.",
            exampleUzbek = "Sening isming nima? Mening ismim Vali.",
            associatedVocabularyIdsCsv = "voc_01_what,voc_01_your,voc_01_name",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_08",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_08",
            titleUzbek = "Alifbo A–E: Harf nomi va tovushi",
            explanationUzbek = "Ingliz tilida harfning alifbodagi nomi (A — ey, B — bi, C — si, D — di, E — i) so'z ichidagi tovushidan farq qiladi.",
            exampleEnglish = "A is for apple, B is for book, C is for cat.",
            exampleUzbek = "A — olma, B — kitob, C — mushuk.",
            associatedVocabularyIdsCsv = "voc_01_apple,voc_01_book,voc_01_cat,voc_01_dog,voc_01_egg",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_09",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_09",
            titleUzbek = "Alifbo F–M va 'I' olmoshi qoidasi",
            explanationUzbek = "F, G, H, I, J, K, L, M harflari. Muhim qoida: \"I\" (men) olmoshi gapda har doim katta harf bilan yoziladi!",
            exampleEnglish = "I am a student. H is for house.",
            exampleUzbek = "Men talabaman. H harfi — uy.",
            associatedVocabularyIdsCsv = "voc_01_fish,voc_01_girl,voc_01_house,voc_01_ice,voc_01_juice,voc_01_key,voc_01_lion,voc_01_moon",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_01_10",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_10",
            titleUzbek = "Alifbo N–Z: To'liq 26 ta harf",
            explanationUzbek = "N, O, P, Q, R, S, T, U, V, W, X, Y, Z bilan ingliz alifbosidagi barcha 26 ta harf to'liq yakunlanadi.",
            exampleEnglish = "W is for water, S is for sun, Z is for zebra.",
            exampleUzbek = "W — suv, S — quyosh, Z — zebra.",
            associatedVocabularyIdsCsv = "voc_01_orange,voc_01_pen,voc_01_queen,voc_01_red,voc_01_sun,voc_01_table,voc_01_umbrella,voc_01_van,voc_01_water,voc_01_box,voc_01_yellow,voc_01_zebra",
            isStudied = true,
            isMastered = false
        ),

        // =========================================================================
        // PARTIYA 1 — DARS 11–20 GRAMMAR RULES (SONLAR VA TARTIB SONLAR)
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_02_11",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_11",
            titleUzbek = "One + birlik, Two/Three + ko'plik (-s)",
            explanationUzbek = "\"One\" sonidan keyin ot birlikda keladi (one apple). \"Two\", \"three\" va undan katta sonlardan keyin otga ko'plik -s qo'shimchasi qo'shiladi (two apples, three books).",
            exampleEnglish = "I have one book. I have two books.",
            exampleUzbek = "Menda bitta kitob bor. Menda ikkita kitob bor.",
            associatedVocabularyIdsCsv = "voc_02_one,voc_02_two,voc_02_three",
            isStudied = true,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_12",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_12",
            titleUzbek = "Four, Five, Six + ko'plikdagi ot",
            explanationUzbek = "4 (four), 5 (five), 6 (six) sonlaridan keyin sanaladigan otlar doimo -s ko'plik qo'shimchasi bilan keladi.",
            exampleEnglish = "I have four pens and five books.",
            exampleUzbek = "Menda to'rtta ruchka va beshta kitob bor.",
            associatedVocabularyIdsCsv = "voc_02_four,voc_02_five,voc_02_six",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_13",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_13",
            titleUzbek = "Seven, Eight, Nine, Ten va yoshni aytish",
            explanationUzbek = "7–10 sonlari ko'plik otlar bilan hamda yoshni aytishda \"years old\" birikmasi bilan ishlatiladi.",
            exampleEnglish = "She is eight years old. I have ten books.",
            exampleUzbek = "U sakkiz yoshda. Menda o'nta kitob bor.",
            associatedVocabularyIdsCsv = "voc_02_seven,voc_02_eight,voc_02_nine,voc_02_ten",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_14",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_14",
            titleUzbek = "11–15 sonlari va -teen qo'shimchasi",
            explanationUzbek = "11 (eleven) va 12 (twelve) — maxsus so'zlar. 13 dan 19 gacha sonlar oxiriga -teen qo'shiladi. E'tibor bering: three → thirteen, five → fifteen.",
            exampleEnglish = "There are thirteen students and fifteen books.",
            exampleUzbek = "O'n uchta talaba va o'n beshta kitob bor.",
            associatedVocabularyIdsCsv = "voc_02_eleven,voc_02_twelve,voc_02_thirteen,voc_02_fourteen,voc_02_fifteen",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_15",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_15",
            titleUzbek = "16–19 (-teen) va 20 (-ty: twenty)",
            explanationUzbek = "16–19 sonlari -teen bilan yasaladi (eighteen da bitta 't'). 20 soni o'nlik bo'lgani uchun -ty bilan twenty deb yasaladi.",
            exampleEnglish = "She is seventeen. My brother is twenty.",
            exampleUzbek = "U o'n yetti yoshda. Akam yigirma yoshda.",
            associatedVocabularyIdsCsv = "voc_02_sixteen,voc_02_seventeen,voc_02_eighteen,voc_02_nineteen,voc_02_twenty",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_16",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_16",
            titleUzbek = "1–20 Sanoq sonlar tizimi",
            explanationUzbek = "1–10 asosiy sonlar, 11–12 istisno (eleven, twelve), 13–19 -teen, 20 esa twenty.",
            exampleEnglish = "I have fourteen apples and seventeen books.",
            exampleUzbek = "Menda o'n to'rtta olma va o'n yettita kitob bor.",
            associatedVocabularyIdsCsv = "voc_02_fourteen,voc_02_seventeen,voc_02_twenty",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_17",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_17",
            titleUzbek = "Istisno tartib sonlar: the first, the second, the third",
            explanationUzbek = "Birinchi uchta tartib son istisno shaklda bo'ladi: 1 — first, 2 — second, 3 — third. Tartib sonlar oldidan doimo \"the\" artikli qo'yiladi.",
            exampleEnglish = "This is the first lesson and the second day.",
            exampleUzbek = "Bu birinchi dars va ikkinchi kun.",
            associatedVocabularyIdsCsv = "voc_02_first,voc_02_second,voc_02_third",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_18",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_18",
            titleUzbek = "Tartib sonlarda -th va five → fifth o'zgarishi",
            explanationUzbek = "4-chidan boshlab son oxiriga -th qo'shiladi: fourth, fifth, sixth. E'tibor bering: five so'zida 've' harflari 'f' ga aylanib fifth bo'ladi.",
            exampleEnglish = "The fourth floor and the fifth grade.",
            exampleUzbek = "To'rtinchi qavat va beshinchi sinf.",
            associatedVocabularyIdsCsv = "voc_02_fourth,voc_02_fifth,voc_02_sixth",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_19",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_19",
            titleUzbek = "Eighth va Ninth imlo qoidasi",
            explanationUzbek = "Eight oxirida 't' bo'lgani uchun faqat 'h' qo'shiladi (eighth). Nine so'zida esa oxirgi 'e' tushib qoladi (ninth).",
            exampleEnglish = "The eighth year and the ninth chapter.",
            exampleUzbek = "Sakkizinchi yil va to'qqizinchi bob.",
            associatedVocabularyIdsCsv = "voc_02_seventh,voc_02_eighth,voc_02_ninth,voc_02_tenth",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_02_20",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_20",
            titleUzbek = "Twelfth (ve→f) va Twentieth (y→ie) qoidasi",
            explanationUzbek = "Twelve → twelfth ('ve' harflari 'f' ga o'zgaradi). Twenty → twentieth ('y' harfi 'ie' ga o'zgarib -th qo'shiladi).",
            exampleEnglish = "The twelfth page in the twentieth century.",
            exampleUzbek = "Yigirmanchi asrdagi o'n ikkinchi bet.",
            associatedVocabularyIdsCsv = "voc_02_eleventh,voc_02_twelfth,voc_02_fifteenth,voc_02_twentieth",
            isStudied = false,
            isMastered = false
        )
    ) + CurriculumPartiya2SeedData.buildGrammarRules() +
        CurriculumPartiya3SeedData.buildGrammarRules() +
        CurriculumPartiya4SeedData.buildGrammarRules() +
        CurriculumPartiya5SeedData.buildGrammarRules() +
        CurriculumPartiya6And7SeedData.buildGrammarRules() +
        CurriculumPartiya8And9SeedData.buildGrammarRules() +
        CurriculumPartiya10And11SeedData.buildGrammarRules() +
        CurriculumPartiya12And13SeedData.buildGrammarRules() +
        CurriculumPartiya14And15SeedData.buildGrammarRules() +
        CurriculumPartiya16And17SeedData.buildGrammarRules() +
        CurriculumPartiya18To20SeedData.buildGrammarRules()

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // =========================================================================
        // PARTIYA 1 — DARS 1–10 EXERCISES (topic_01_introduction)
        // =========================================================================
        ExerciseEntity(
            id = "ex_01_00a",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_01",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"Welcome ___ TalkMe!\"",
            subPromptText = "1-Dars: Welcome to TalkMe",
            audioTextToSpeak = "Welcome to TalkMe!",
            optionsPipeSeparated = "to|at|in",
            correctAnswer = "to",
            explanationUzbek = "\"Welcome to TalkMe!\" — TalkMe ga xush kelibsiz!",
            vocabularyIdsCsv = "voc_01_welcome",
            grammarRuleIdsCsv = "gram_01_01"
        ),
        ExerciseEntity(
            id = "ex_01_00b",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_01",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"I can ___ English!\"",
            subPromptText = "1-Dars: Welcome to TalkMe",
            audioTextToSpeak = "I can learn English!",
            optionsPipeSeparated = "learn|sleep|stop",
            correctAnswer = "learn",
            explanationUzbek = "\"I can learn English!\" — Men ingliz tilini o'rgana olaman!",
            vocabularyIdsCsv = "voc_01_learn",
            grammarRuleIdsCsv = "gram_01_01"
        ),
        ExerciseEntity(
            id = "ex_01_00c",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_01",
            practiceCategory = PracticeCategory.READING.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"Let's ___ now!\"",
            subPromptText = "1-Dars: Welcome to TalkMe",
            audioTextToSpeak = "Let's start now!",
            optionsPipeSeparated = "start|wait|stop",
            correctAnswer = "start",
            explanationUzbek = "\"Let's start now!\" — Keling, hozir boshlaymiz!",
            vocabularyIdsCsv = "voc_01_start",
            grammarRuleIdsCsv = "gram_01_01"
        ),
        ExerciseEntity(
            id = "ex_01_00d",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_02",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"___ to audio lessons.\"",
            subPromptText = "2-Dars: How TalkMe Works",
            audioTextToSpeak = "Listen to audio lessons.",
            optionsPipeSeparated = "Listen|Stop|Wait",
            correctAnswer = "Listen",
            explanationUzbek = "Ovozni tinglash uchun: \"Listen\".",
            vocabularyIdsCsv = "voc_01_listen",
            grammarRuleIdsCsv = "gram_01_02"
        ),
        ExerciseEntity(
            id = "ex_01_00e",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_02",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"___ every single day.\"",
            subPromptText = "2-Dars: How TalkMe Works",
            audioTextToSpeak = "Practice every single day.",
            optionsPipeSeparated = "Practice|Sleep|Forget",
            correctAnswer = "Practice",
            explanationUzbek = "Mashq qilish: \"Practice\".",
            vocabularyIdsCsv = "voc_01_practice",
            grammarRuleIdsCsv = "gram_01_02"
        ),
        ExerciseEntity(
            id = "ex_01_00f",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_02",
            practiceCategory = PracticeCategory.SPEAKING.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"___ English with TalkMe.\"",
            subPromptText = "2-Dars: How TalkMe Works",
            audioTextToSpeak = "Speak English with TalkMe.",
            optionsPipeSeparated = "Speak|Close|Leave",
            correctAnswer = "Speak",
            explanationUzbek = "TalkMe bilan inglizcha gapiring: \"Speak\".",
            vocabularyIdsCsv = "voc_01_speak",
            grammarRuleIdsCsv = "gram_01_02"
        ),
        ExerciseEntity(
            id = "ex_01_01",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_03",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "O'qituvchingizga rasmiy salom berish uchun nima deysiz?",
            subPromptText = "3-Dars: Hello (rasmiy) va Hi (do'stona)",
            audioTextToSpeak = "Hello, teacher!",
            optionsPipeSeparated = "Hello|Good bye|Please|Thank you",
            correctAnswer = "Hello",
            explanationUzbek = "\"Hello\" — rasmiy salomlashish so'zi. O'qituvchiga \"Hello, teacher!\" deyiladi.",
            vocabularyIdsCsv = "voc_01_hello",
            grammarRuleIdsCsv = "gram_01_03"
        ),
        ExerciseEntity(
            id = "ex_01_02",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_04",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Ertalab onangizga \"Xayrli tong\" deb aytish uchun qaysi iborani tanlaysiz?",
            subPromptText = "4-Dars: Good morning! Good bye!",
            audioTextToSpeak = "Good morning, mom!",
            optionsPipeSeparated = "Good morning|Good bye|Bye|Please",
            correctAnswer = "Good morning",
            explanationUzbek = "\"Good morning\" — \"Xayrli tong!\" Ertalab soat 12 gacha ishlatiladi.",
            vocabularyIdsCsv = "voc_01_good_morning",
            grammarRuleIdsCsv = "gram_01_04"
        ),
        ExerciseEntity(
            id = "ex_01_03",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_05",
            practiceCategory = PracticeCategory.READING.id,
            exerciseType = ExerciseType.READING_COMPREHENSION.name,
            difficulty = 1,
            promptText = "Suv so'raganda qaysi odobli jumlani ishlatasiz?",
            subPromptText = "5-Dars: Thank you! Please!",
            audioTextToSpeak = "Water, please.",
            optionsPipeSeparated = "Water, please.|Water, good bye.|Hello, water.|My name is water.",
            correctAnswer = "Water, please.",
            explanationUzbek = "Narsa so'raganda \"please\" (iltimos) ishlatiladi: \"Water, please.\"",
            vocabularyIdsCsv = "voc_01_please,voc_01_water",
            grammarRuleIdsCsv = "gram_01_05"
        ),
        ExerciseEntity(
            id = "ex_01_04",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_06",
            practiceCategory = PracticeCategory.WRITING.id,
            exerciseType = ExerciseType.SENTENCE_BUILDING.name,
            difficulty = 1,
            promptText = "Tartibga qo'yib \"Mening ismim Ali\" gapini tuzing:",
            subPromptText = "6-Dars: is / name / My / Ali",
            audioTextToSpeak = "My name is Ali.",
            optionsPipeSeparated = "My name is Ali.|Name my is Ali.|Is my name Ali.",
            correctAnswer = "My name is Ali.",
            explanationUzbek = "Formula: My name is + Ism → \"My name is Ali.\"",
            vocabularyIdsCsv = "voc_01_my,voc_01_name",
            grammarRuleIdsCsv = "gram_01_06"
        ),
        ExerciseEntity(
            id = "ex_01_05",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_07",
            practiceCategory = PracticeCategory.LISTENING.id,
            exerciseType = ExerciseType.LISTENING_COMPREHENSION.name,
            difficulty = 1,
            promptText = "Audioni eshiting va \"Sening isming nima?\" savolini toping:",
            subPromptText = "7-Dars: What is your name?",
            audioTextToSpeak = "What is your name?",
            optionsPipeSeparated = "What is your name?|My name is Ali.|Good morning, teacher!|Thank you very much!",
            correctAnswer = "What is your name?",
            explanationUzbek = "\"What is your name?\" — \"Sening isming nima?\"",
            vocabularyIdsCsv = "voc_01_what,voc_01_your,voc_01_name",
            grammarRuleIdsCsv = "gram_01_07"
        ),
        ExerciseEntity(
            id = "ex_01_06",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_08",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Qaysi so'z \"A\" harfi bilan boshlanadi?",
            subPromptText = "8-Dars: A B C D E",
            audioTextToSpeak = "apple",
            optionsPipeSeparated = "apple|book|cat|dog",
            correctAnswer = "apple",
            explanationUzbek = "A — apple (olma).",
            vocabularyIdsCsv = "voc_01_apple",
            grammarRuleIdsCsv = "gram_01_08"
        ),
        ExerciseEntity(
            id = "ex_01_07",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_09",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Qaysi so'z \"M\" harfi bilan boshlanadi va \"oy\" ma'nosini bildiradi?",
            subPromptText = "9-Dars: F G H I J K L M",
            audioTextToSpeak = "moon",
            optionsPipeSeparated = "moon|book|fish|lion",
            correctAnswer = "moon",
            explanationUzbek = "M — moon (oy).",
            vocabularyIdsCsv = "voc_01_moon",
            grammarRuleIdsCsv = "gram_01_09"
        ),
        ExerciseEntity(
            id = "ex_01_08",
            topicId = "topic_01_introduction",
            sectionId = "sec_01_10",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Qaysi so'z \"W\" harfi bilan boshlanadi va \"suv\" degan ma'noni beradi?",
            subPromptText = "10-Dars: N O P Q R S T U V W X Y Z",
            audioTextToSpeak = "water",
            optionsPipeSeparated = "water|van|sun|zebra",
            correctAnswer = "water",
            explanationUzbek = "W — water (suv).",
            vocabularyIdsCsv = "voc_01_water",
            grammarRuleIdsCsv = "gram_01_10"
        ),

        // =========================================================================
        // PARTIYA 1 — DARS 11–20 EXERCISES (topic_02_numbers)
        // =========================================================================
        ExerciseEntity(
            id = "ex_02_11",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_11",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Menda ikkita kitob bor\" gapini ingliz tilida toping:",
            subPromptText = "11-Dars: One, Two, Three",
            audioTextToSpeak = "I have two books.",
            optionsPipeSeparated = "I have two books.|I have one book.|I have two book.|She has three cats.",
            correctAnswer = "I have two books.",
            explanationUzbek = "Two dan keyin otga ko'plik -s qo'shiladi: \"I have two books.\"",
            vocabularyIdsCsv = "voc_02_two",
            grammarRuleIdsCsv = "gram_02_11"
        ),
        ExerciseEntity(
            id = "ex_02_12",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_12",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"I have ___ cats.\" (6 ta)",
            subPromptText = "12-Dars: Four, Five, Six",
            audioTextToSpeak = "I have six cats.",
            optionsPipeSeparated = "six|four|five|three",
            correctAnswer = "six",
            explanationUzbek = "Six — 6 (olti): \"I have six cats.\"",
            vocabularyIdsCsv = "voc_02_six",
            grammarRuleIdsCsv = "gram_02_12"
        ),
        ExerciseEntity(
            id = "ex_02_13",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_13",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"I have ___ books.\" (10 ta)",
            subPromptText = "13-Dars: Seven, Eight, Nine, Ten",
            audioTextToSpeak = "I have ten books.",
            optionsPipeSeparated = "ten|seven|eight|nine",
            correctAnswer = "ten",
            explanationUzbek = "Ten — 10 (o'n): \"I have ten books.\"",
            vocabularyIdsCsv = "voc_02_ten",
            grammarRuleIdsCsv = "gram_02_13"
        ),
        ExerciseEntity(
            id = "ex_02_14",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_14",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"I have ___ apples.\" (13 ta)",
            subPromptText = "14-Dars: Eleven to Fifteen",
            audioTextToSpeak = "I have thirteen apples.",
            optionsPipeSeparated = "thirteen|eleven|twelve|fifteen",
            correctAnswer = "thirteen",
            explanationUzbek = "Thirteen — 13 (o'n uch).",
            vocabularyIdsCsv = "voc_02_thirteen",
            grammarRuleIdsCsv = "gram_02_14"
        ),
        ExerciseEntity(
            id = "ex_02_15",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_15",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Yigirmata kitob\" birikmasini ingliz tilida toping:",
            subPromptText = "15-Dars: Sixteen to Twenty",
            audioTextToSpeak = "twenty books",
            optionsPipeSeparated = "twenty books|twelve books|nineteen books|sixteen books",
            correctAnswer = "twenty books",
            explanationUzbek = "Twenty — 20 (yigirma): \"twenty books\".",
            vocabularyIdsCsv = "voc_02_twenty",
            grammarRuleIdsCsv = "gram_02_15"
        ),
        ExerciseEntity(
            id = "ex_02_17",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_17",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Birinchi dars\" birikmasini ingliz tilida tanlang:",
            subPromptText = "17-Dars: First, Second, Third",
            audioTextToSpeak = "the first lesson",
            optionsPipeSeparated = "the first lesson|the one lesson|the second lesson|the third lesson",
            correctAnswer = "the first lesson",
            explanationUzbek = "First — birinchi, oldidan \"the\" qo'yiladi: \"the first lesson\".",
            vocabularyIdsCsv = "voc_02_first",
            grammarRuleIdsCsv = "gram_02_17"
        ),
        ExerciseEntity(
            id = "ex_02_18",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_18",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Beshinchi sinf\" birikmasini ingliz tilida toping:",
            subPromptText = "18-Dars: Fourth, Fifth, Sixth",
            audioTextToSpeak = "the fifth grade",
            optionsPipeSeparated = "the fifth grade|the fiveth grade|the fourth grade|the sixth grade",
            correctAnswer = "the fifth grade",
            explanationUzbek = "Five → fifth ('ve' → 'f'): \"the fifth grade\".",
            vocabularyIdsCsv = "voc_02_fifth",
            grammarRuleIdsCsv = "gram_02_18"
        ),
        ExerciseEntity(
            id = "ex_02_19",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_19",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"To'qqizinchi bob\" birikmasida to'g'ri yozilgan tartib sonni tanlang:",
            subPromptText = "19-Dars: Seventh to Tenth",
            audioTextToSpeak = "the ninth chapter",
            optionsPipeSeparated = "the ninth chapter|the nineth chapter|the eighth chapter|the tenth chapter",
            correctAnswer = "the ninth chapter",
            explanationUzbek = "Nine → ninth ('e' harfi tushib qoladi): \"the ninth chapter\".",
            vocabularyIdsCsv = "voc_02_ninth",
            grammarRuleIdsCsv = "gram_02_19"
        ),
        ExerciseEntity(
            id = "ex_02_20",
            topicId = "topic_02_numbers",
            sectionId = "sec_02_20",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Yigirmanchi asr\" birikmasini ingliz tilida toping:",
            subPromptText = "20-Dars: Ordinals 11–20",
            audioTextToSpeak = "the twentieth century",
            optionsPipeSeparated = "the twentieth century|the twentyth century|the twelfth century|the fifteenth century",
            correctAnswer = "the twentieth century",
            explanationUzbek = "Twenty → twentieth ('y' → 'ie' + th): \"the twentieth century\".",
            vocabularyIdsCsv = "voc_02_twentieth",
            grammarRuleIdsCsv = "gram_02_20"
        )
    ) + CurriculumPartiya2SeedData.buildExercises() +
        CurriculumPartiya3SeedData.buildExercises() +
        CurriculumPartiya4SeedData.buildExercises() +
        CurriculumPartiya5SeedData.buildExercises() +
        CurriculumPartiya6And7SeedData.buildExercises() +
        CurriculumPartiya8And9SeedData.buildExercises() +
        CurriculumPartiya10And11SeedData.buildExercises() +
        CurriculumPartiya12And13SeedData.buildExercises() +
        CurriculumPartiya14And15SeedData.buildExercises() +
        CurriculumPartiya16And17SeedData.buildExercises() +
        CurriculumPartiya18To20SeedData.buildExercises()

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
