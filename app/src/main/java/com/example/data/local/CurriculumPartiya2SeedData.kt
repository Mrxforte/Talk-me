package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * Full Partiya 2 (Lessons 21–40) seed data for TalkMe:
 * - Topic 3 (`topic_03_nouns`): Lessons 21–32 (A1 Nouns: singular/plural -s, -es, y->ies, irregular plurals, countable & uncountable nouns)
 * - Topic 4 (`topic_04_adjectives`): Lessons 33–40 (A1 Adjectives: colors, size, quality, temperature, position before noun & after verb)
 */
object CurriculumPartiya2SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 2 — A1 OTLAR (DARS 21–32) inside topic_03_nouns
        // =========================================================================
        LessonSectionEntity(
            id = "sec_03_21",
            topicId = "topic_03_nouns",
            orderIndex = 1,
            titleEnglish = "21-Dars: What is a Noun?",
            titleUzbek = "Ot nima?",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun otlarni boshlaymiz. Ot nima? Ot — bu shaxs, narsa, joy yoki tushunchaning nomi. \"Stol\", \"bola\", \"Toshkent\" — hammasi ot. Ingliz tilida: table, boy, Tashkent. Qo'rqmang — bu juda oson! 🚀",
            explanationUzbek = "📖 QOIDA:\nOt (Noun) — bu nom. U kim? yoki nima? savoliga javob beradi.\n• Shaxs: boy (bola), girl (qiz), teacher (o'qituvchi)\n• Narsa: book (kitob), table (stol), car (mashina)\n• Joy: Tashkent, school (maktab), park\n• Tushuncha: love (sevgi), happiness (baxt)\nGapda ot ega yoki to'ldiruvchi bo'ladi: The book is on the table. I read a book.\n\n💬 MISOLLAR:\n• Book — kitob. I have a book.\n• Cat — mushuk. The cat is black.\n• Table — stol. The table is big.\n• Car — mashina. My car is red.\n• House — uy. Our house is new.\n\n✍️ MASHQ:\n1. \"Kitob\" inglizcha? → ✅ book\n2. \"Mushuk\" inglizcha? → ✅ cat\n3. Qaysi biri ot? a) red b) book c) run → ✅ b) book\n4. Ovoz chiqarib: \"Book, cat, table, car, house.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ot — nom. Kim? Nima? savoliga javob beradi. Turlari: shaxs, narsa, joy, tushuncha. Yangi so'zlar: book, cat, table, car, house. Uyga vazifa: uydagi 5 ta narsani inglizcha ayting.\n\n🤖 TalkMe yakuniy: Zo'r, [USER_NAME]! Endi siz ot nima ekanligini bilasiz! Ertaga: 5 ta oddiy ot. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_22",
            topicId = "topic_03_nouns",
            orderIndex = 2,
            titleEnglish = "22-Dars: 5 Basic Nouns",
            titleUzbek = "5 ta oddiy ot",
            objectiveUzbek = "🤖 TalkMe: Bugun 5 ta eng oddiy otni o'rganamiz. Bular — har kuni ishlatadigan so'zlar! 🚀",
            explanationUzbek = "📖 QOIDA:\n5 ta ot (birlikda):\n• Book (buk) — kitob\n• Pen (pen) — ruchka\n• Table (teybl) — stol\n• Cat (kat) — mushuk\n• Dog (dog) — it\n\n💬 MISOLLAR:\n• This is a book. — Bu kitob.\n• I have a pen. — Menda ruchka bor.\n• The table is big. — Stol katta.\n• The cat is small. — Mushuk kichik.\n• The dog is black. — It qora.\n\n✍️ MASHQ:\n1. \"Kitob\" → ✅ book | 2. \"Ruchka\" → ✅ pen | 3. \"Stol\" → ✅ table\n4. \"Mushuk\" → ✅ cat | 5. \"It\" → ✅ dog\n6. Ovoz chiqarib: \"Book, pen, table, cat, dog.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 5 ta ot: book, pen, table, cat, dog. Bular — birlikda. Uyga vazifa: 5 ta otni 5 marta ovoz chiqarib o'qing.\n\n🤖 TalkMe yakuniy: Ajoyib! 5 ta ot! Ertaga: yana 5 ta ot. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_23",
            topicId = "topic_03_nouns",
            orderIndex = 3,
            titleEnglish = "23-Dars: 5 More Nouns",
            titleUzbek = "Yana 5 ta ot",
            objectiveUzbek = "🤖 TalkMe: Bugun yana 5 ta ot o'rganamiz: car, house, tree, sun, moon! 🚀",
            explanationUzbek = "📖 QOIDA:\n5 ta ot (birlikda):\n• Car (ka) — mashina\n• House (haus) — uy\n• Tree (tri) — daraxt\n• Sun (san) — quyosh\n• Moon (mun) — oy\n\n💬 MISOLLAR:\n• My car is red. — Mening mashinam qizil.\n• The house is big. — Uy katta.\n• The tree is tall. — Daraxt baland.\n• The sun is bright. — Quyosh yorqin.\n• The moon is beautiful. — Oy chiroyli.\n\n✍️ MASHQ:\n1. \"Mashina\" → ✅ car | 2. \"Uy\" → ✅ house | 3. \"Daraxt\" → ✅ tree\n4. \"Quyosh\" → ✅ sun | 5. \"Oy\" → ✅ moon",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 5 ta ot: car, house, tree, sun, moon. Uyga vazifa: 10 ta otni (21–23 darslardan) takrorlang.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz 10 ta ot bilasiz! Ertaga: birlik va ko'plik — -s. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_24",
            topicId = "topic_03_nouns",
            orderIndex = 4,
            titleEnglish = "24-Dars: Singular & Plural: -s",
            titleUzbek = "Birlik va Ko'plik: -s",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'plikni o'rganamiz! \"Bitta kitob\" — a book. \"Ikkita kitob\" — two books. Oxiriga -s qo'shamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Birlik — bitta narsa: a book.\n• Ko'plik — bir nechta: two books.\n• Formula: Ot + -s.\nbook → books, pen → pens, table → tables, cat → cats, dog → dogs.\nMuhim: \"-s\" jarangsiz undoshdan keyin (s), jarangli va unlidan keyin (z) deb o'qiladi.\n\n💬 MISOLLAR:\n• One book → two books. — Bitta kitob → ikkita kitob.\n• A pen → three pens. — Bitta ruchka → uchta ruchka.\n• A table → four tables. — Bitta stol → to'rtta stol.\n• A cat → five cats. — Bitta mushuk → beshta mushuk.\n• A dog → six dogs. — Bitta it → oltita it.\n\n✍️ MASHQ:\n1. \"Ikkita kitob\" → ✅ two books\n2. \"Uchta ruchka\" → ✅ three pens\n3. \"To'rtta stol\" → ✅ four tables\n4. \"Beshta mushuk\" → ✅ five cats\n5. \"Oltita it\" → ✅ six dogs",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ko'plik = ot + -s. One book → two books. Uyga vazifa: uydagi 3 ta narsani ko'plikda ayting: \"two books, three pens, four tables.\"\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz ko'plikni bilasiz! Ertaga: 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_25",
            topicId = "topic_03_nouns",
            orderIndex = 5,
            titleEnglish = "25-Dars: Plurals — 5 Examples",
            titleUzbek = "Ko'plik — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'plikni mustahkamlaymiz! 5 ta hayotiy misol! 🚀",
            explanationUzbek = "📖 QOIDA:\nOt + -s: car → cars, house → houses, tree → trees, star → stars.\nMuhim: Quyosh (the sun) va oy (the moon) dunyoda yagona bo'lgani uchun odatda birlikda ishlatiladi.\n\n💬 MISOLLAR:\n• I have two cars. — Menda ikkita mashina bor.\n• There are three houses. — Uchta uy bor.\n• The trees are green. — Daraxtlar yashil.\n• I see many stars. — Men ko'p yulduzlarni ko'raman.\n• The cats are playing. — Mushuklar o'ynashmoqda.\n\n✍️ MASHQ:\n1. \"Ikkita mashina\" → ✅ two cars\n2. \"Uchta uy\" → ✅ three houses\n3. \"Daraxtlar\" → ✅ trees\n4. \"Mushuklar o'ynashmoqda\" → ✅ The cats are playing.\n5. \"Menda ikkita it bor\" → ✅ I have two dogs.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ko'plik: car→cars, house→houses, tree→trees, cat→cats, dog→dogs. Uyga vazifa: 5 ta otni ko'plikda yozing.\n\n🤖 TalkMe yakuniy: Ajoyib! Ko'plik mustahkamlandi! Ertaga: -es qoidasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_26",
            topicId = "topic_03_nouns",
            orderIndex = 6,
            titleEnglish = "26-Dars: Plural -es Rule",
            titleUzbek = "-es qoidasi",
            objectiveUzbek = "🤖 TalkMe: Bugun -es qoidasini o'rganamiz. Ba'zi otlar -s emas, -es oladi! 🚀",
            explanationUzbek = "📖 QOIDA:\nAgar ot -s, -ss, -sh, -ch, -x, -z bilan tugasa — -es qo'shamiz (-iz deb o'qiladi).\nMisollar:\n• bus → buses (avtobuslar)\n• class → classes (sinflar)\n• dish → dishes (idishlar)\n• box → boxes (qutilar)\n• watch → watches (soatlar)\n\n💬 MISOLLAR:\n• A bus → two buses. — Bitta avtobus → ikkita avtobus.\n• A class → three classes. — Bitta sinf → uchta sinf.\n• A dish → four dishes. — Bitta idish → to'rtta idish.\n• A box → five boxes. — Bitta quti → beshta quti.\n• A watch → six watches. — Bitta soat → oltita soat.\n\n✍️ MASHQ:\n1. \"Ikkita avtobus\" → ✅ two buses\n2. \"Uchta sinf\" → ✅ three classes\n3. \"To'rtta quti\" → ✅ four boxes\n4. \"Beshta soat\" → ✅ five watches\n5. \"Idishlar\" → ✅ dishes",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -s, -ss, -sh, -ch, -x, -z → -es. Bus→buses, box→boxes. Uyga vazifa: 5 ta so'zni -es bilan ko'plikda yozing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi -es qoidasini bilasiz! Ertaga: 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_27",
            topicId = "topic_03_nouns",
            orderIndex = 7,
            titleEnglish = "27-Dars: -es — 5 More Examples",
            titleUzbek = "-es — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun -es qoidasini yangi so'zlar bilan mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n-s, -ss, -sh, -ch, -x, -z → -es:\n• glass → glasses (stakanlar)\n• brush → brushes (cho'tkalar)\n• church → churches (cherkovlar)\n• fox → foxes (tulkilar)\n• box → boxes (qutilar)\n\n💬 MISOLLAR:\n• I have two glasses. — Menda ikkita stakan bor.\n• There are three brushes. — Uchta cho'tka bor.\n• The churches are old. — Cherkovlar qadimiy.\n• I see two foxes. — Men ikkita tulkini ko'raman.\n• The boxes are heavy. — Qutilar og'ir.\n\n✍️ MASHQ:\n1. \"Ikkita stakan\" → ✅ two glasses\n2. \"Uchta cho'tka\" → ✅ three brushes\n3. \"Cherkovlar\" → ✅ churches\n4. \"Ikkita tulki\" → ✅ two foxes\n5. \"Qutilar\" → ✅ boxes",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -es: glass→glasses, brush→brushes, church→churches, fox→foxes, box→boxes. Uyga vazifa: 5 ta -es so'zni yodlang.\n\n🤖 TalkMe yakuniy: Ajoyib! -es mustahkamlandi! Ertaga: y → ies qoidasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_28",
            topicId = "topic_03_nouns",
            orderIndex = 8,
            titleEnglish = "28-Dars: y → ies Rule & Examples",
            titleUzbek = "y → ies qoidasi + misollar",
            objectiveUzbek = "🤖 TalkMe: Bugun y → ies qoidasini o'rganamiz: city → cities, lekin boy → boys! 🚀",
            explanationUzbek = "📖 QOIDA:\n1) Agar ot undosh + y bilan tugasa — y harfini i ga aylantirib -es qo'shamiz:\n• city → cities (shaharlar)\n• baby → babies (chaqaloqlar)\n• family → families (oilalar)\n• country → countries (mamlakatlar)\n• story → stories (hikoyalar)\n2) Agar unli + y bo'lsa (boy, day, key) — faqat -s qo'shiladi: boys, days, keys.\n\n💬 MISOLLAR:\n• A city → two cities. — Bitta shahar → ikkita shahar.\n• A baby → three babies. — Bitta chaqaloq → uchta chaqaloq.\n• A family → four families. — Bitta oila → to'rtta oila.\n• A boy → two boys (unli + y).\n• A day → seven days (unli + y).\n\n✍️ MASHQ:\n1. \"Ikkita shahar\" → ✅ two cities\n2. \"Uchta chaqaloq\" → ✅ three babies\n3. \"To'rtta oila\" → ✅ four families\n4. \"Ko'p mamlakatlar\" → ✅ many countries\n5. \"Ikkita bola\" (boy) → ✅ two boys",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Undosh + y → ies. Unli + y → faqat -s. Uyga vazifa: city, baby, family, country, story so'zlarini ko'plikda yozing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi y→ies qoidasini bilasiz! Ertaga: noto'g'ri ko'plik — man→men. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_29",
            topicId = "topic_03_nouns",
            orderIndex = 9,
            titleEnglish = "29-Dars: Irregular Plurals: man → men",
            titleUzbek = "Noto'g'ri ko'plik: man→men, foot→feet",
            objectiveUzbek = "🤖 TalkMe: Bugun noto'g'ri ko'plikni o'rganamiz. Ba'zi otlar -s olmaydi — ular ichki unlisi bilan o'zgaradi! 🚀",
            explanationUzbek = "📖 QOIDA:\nNoto'g'ri ko'plik — yodlash kerak bo'lgan otlar (-s olmaydi):\n• man → men (erkak → erkaklar)\n• woman → women (ayol → ayollar)\n• foot → feet (oyoq → oyoqlar)\n• tooth → teeth (tish → tishlar)\n• mouse → mice (sichqon → sichqonlar)\n• goose → geese (g'oz → g'ozlar)\n\n💬 MISOLLAR:\n• A man → two men. — Bitta erkak → ikkita erkak.\n• A woman → three women. — Bitta ayol → uchta ayol.\n• A foot → two feet. — Bitta oyoq → ikkita oyoq.\n• A tooth → many teeth. — Bitta tish → ko'p tishlar.\n• A mouse → two mice. — Bitta sichqon → ikkita sichqon.\n\n✍️ MASHQ:\n1. \"Ikkita erkak\" → ✅ two men\n2. \"Uchta ayol\" → ✅ three women\n3. \"Ikkita oyoq\" → ✅ two feet\n4. \"Ko'p tishlar\" → ✅ many teeth\n5. \"Ikkita sichqon\" → ✅ two mice",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Noto'g'ri ko'plik: man→men, woman→women, foot→feet, tooth→teeth, mouse→mice, goose→geese. Uyga vazifa: 6 ta so'zni yodlang.\n\n🤖 TalkMe yakuniy: Zo'r! Endi noto'g'ri ko'plikni bilasiz! Ertaga: child→children, fish→fish. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_30",
            topicId = "topic_03_nouns",
            orderIndex = 10,
            titleEnglish = "30-Dars: Irregular Plurals: child → children",
            titleUzbek = "Noto'g'ri ko'plik: child→children, fish→fish",
            objectiveUzbek = "🤖 TalkMe: Bugun yana muhim noto'g'ri ko'pliklarni o'rganamiz: child → children, person → people, fish → fish! 🚀",
            explanationUzbek = "📖 QOIDA:\n• child → children (bola → bolalar)\n• person → people (shaxs → odamlar)\n• fish → fish (baliq → baliqlar — bir xil qoladi)\n• sheep → sheep (qo'y → qo'ylar — bir xil qoladi)\n• deer → deer (kiyik → kiyiklar — bir xil qoladi)\nMuhim: \"People\" doimo ko'plik ma'nosida ishlatiladi!\n\n💬 MISOLLAR:\n• A child → two children. — Bitta bola → ikkita bola.\n• A person → many people. — Bitta shaxs → ko'p odamlar.\n• A fish → two fish. — Bitta baliq → ikkita baliq.\n• A sheep → three sheep. — Bitta qo'y → uchta qo'y.\n\n✍️ MASHQ:\n1. \"Ikkita bola\" → ✅ two children\n2. \"Ko'p odamlar\" → ✅ many people\n3. \"Ikkita baliq\" → ✅ two fish\n4. \"Uchta qo'y\" → ✅ three sheep\n5. \"Ikkita kiyik\" → ✅ two deer",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Child→children, person→people, fish→fish, sheep→sheep, deer→deer. Uyga vazifa: 5 ta so'zni yodlang.\n\n🤖 TalkMe yakuniy: Ajoyib! Noto'g'ri ko'plik mustahkamlandi! Ertaga: sanaladigan otlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_31",
            topicId = "topic_03_nouns",
            orderIndex = 11,
            titleEnglish = "31-Dars: Countable Nouns",
            titleUzbek = "Sanaladigan otlar",
            objectiveUzbek = "🤖 TalkMe: Bugun sanaladigan otlarni o'rganamiz. Bularni donalab sanash mumkin! 🚀",
            explanationUzbek = "📖 QOIDA:\nSanaladigan otlar (Countable) — sanash mumkin bo'lgan narsalar: one apple, two apples, three apples. Ular birlikda ham, ko'plikda ham bo'ladi.\nMisollar: apple, book, pen, cat, dog, car, table, chair (stul).\nMuhim: Birlikda a/an yoki the kerak: a book, an apple.\n\n💬 MISOLLAR:\n• I have an apple. — Menda bitta olma bor.\n• I have two apples. — Menda ikkita olma bor.\n• There are three books on the table. — Stolda uchta kitob bor.\n• She has a cat. — Uning bitta mushugi bor.\n\n✍️ MASHQ:\n1. \"Ikkita olma\" → ✅ two apples\n2. \"Uchta kitob\" → ✅ three books\n3. \"Bitta mushuk\" → ✅ a cat\n4. \"To'rtta stul\" → ✅ four chairs\n5. Qaysi biri sanaladi? a) water b) book → ✅ b) book",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sanaladigan otlar: apple, book, pen, cat, dog, car, table, chair. Birlikda a/an, ko'plikda -s. Uyga vazifa: 5 ta sanaladigan otni birlik va ko'plikda yozing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi sanaladigan otlarni bilasiz! Ertaga: sanalmaydigan otlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_03_32",
            topicId = "topic_03_nouns",
            orderIndex = 12,
            titleEnglish = "32-Dars: Uncountable Nouns & Review",
            titleUzbek = "Sanalmaydigan otlar + Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun sanalmaydigan otlarni o'rganamiz va barcha A1 otlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSanalmaydigan otlar (Uncountable) — donalab sanab bo'lmaydigan narsalar. Ular faqat birlikda ishlatiladi, -s olmaydi va oldidan a/an qo'yilmaydi.\nMisollar: water (suv), milk (sut), sugar (shakar), money (pul), information (ma'lumot), advice (maslahat), news (yangilik), work (ish).\nMuhim: \"some\" yoki \"much\" bilan ishlatiladi: some water, much money.\n\n💬 MISOLLAR:\n• I drink water every day. — Men har kuni suv ichaman.\n• She has money in her bag. — Uning sumkasida pul bor.\n• Give me some advice. — Menga maslahat bering.\n• I have much work. — Menda ko'p ish bor.\n• The news is good. — Yangilik yaxshi.\n\n✍️ MASHQ:\n1. \"Suv\" → ✅ water | 2. \"Pul\" → ✅ money | 3. \"Maslahat\" → ✅ advice\n4. Qaysi biri sanalmaydi? a) book b) water → ✅ b) water\n5. \"I have ___ money.\" a) many b) much → ✅ b) much",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sanalmaydigan otlar: water, milk, sugar, money, information, advice, news, work. Faqat birlikda, -s yo'q, a/an yo'q. A1 Otlar tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz barcha A1 otlarni o'zlashtirdingiz! Keyingi bo'lim: sifatlar — red, blue, green. 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 2 — A1 SIFATLAR (DARS 33–40) inside topic_04_adjectives
        // =========================================================================
        LessonSectionEntity(
            id = "sec_04_33",
            topicId = "topic_04_adjectives",
            orderIndex = 1,
            titleEnglish = "33-Dars: What is an Adjective?",
            titleUzbek = "Sifat nima?",
            objectiveUzbek = "🤖 TalkMe: Endi sifatlarni boshlaymiz! Sifat — bu narsaning belgisi. \"Qizil olma\", \"katta uy\", \"yaxshi bola\" — red, big, good. 🚀",
            explanationUzbek = "📖 QOIDA:\nSifat (Adjective) — bu belgi. U qanday? savoliga javob beradi.\nSifatning gapdagi o'rni:\n1) Otdan oldin: a red apple.\n2) Bog'lovchi fe'ldan keyin: The apple is red.\nMuhim: Sifat hech qachon ko'plik qo'shimchasi (-s) olmaydi! ✅ two big houses.\n\n💬 MISOLLAR:\n• A red apple — qizil olma.\n• A big house — katta uy.\n• A good book — yaxshi kitob.\n• The apple is red. — Olma qizil.\n• The house is big. — Uy katta.\n\n✍️ MASHQ:\n1. \"Qizil olma\" → ✅ a red apple\n2. \"Katta uy\" → ✅ a big house\n3. \"Yaxshi kitob\" → ✅ a good book\n4. \"Two ___ houses.\" a) bigs b) big → ✅ b) big",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sifat — belgi (Qanday?). Otdan oldin yoki fe'ldan keyin keladi, ko'plikda o'zgarmaydi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz sifat nima ekanligini bilasiz! Ertaga: ranglar — red, blue, green. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_34",
            topicId = "topic_04_adjectives",
            orderIndex = 2,
            titleEnglish = "34-Dars: Colors: Red, Blue, Green",
            titleUzbek = "Ranglar — Red, Blue, Green",
            objectiveUzbek = "🤖 TalkMe: Bugun ranglarni o'rganamiz! 3 ta rang: red, blue, green. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Red (red) — qizil\n• Blue (blu) — ko'k\n• Green (grin) — yashil\nOtdan oldin: a red apple. Fe'ldan keyin: The apple is red.\n\n💬 MISOLLAR:\n• A red apple — qizil olma.\n• A blue car — ko'k mashina.\n• A green tree — yashil daraxt.\n• The apple is red. — Olma qizil.\n• The car is blue. — Mashina ko'k.\n• The tree is green. — Daraxt yashil.\n\n✍️ MASHQ:\n1. \"Qizil olma\" → ✅ a red apple\n2. \"Ko'k mashina\" → ✅ a blue car\n3. \"Yashil daraxt\" → ✅ a green tree\n4. \"Olma qizil\" → ✅ The apple is red.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta rang: red (qizil), blue (ko'k), green (yashil). Uyga vazifa: \"a red book, a blue pen, a green tree\" deb ayting.\n\n🤖 TalkMe yakuniy: Ajoyib! 3 ta rang! Ertaga: yellow, black, white. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_35",
            topicId = "topic_04_adjectives",
            orderIndex = 3,
            titleEnglish = "35-Dars: Colors: Yellow, Black, White",
            titleUzbek = "Ranglar — Yellow, Black, White",
            objectiveUzbek = "🤖 TalkMe: Bugun yana 3 ta rang: yellow, black, white. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Yellow (yelou) — sariq\n• Black (blek) — qora\n• White (vayt) — oq\nOtdan oldin yoki fe'ldan keyin ishlatiladi.\n\n💬 MISOLLAR:\n• A yellow sun — sariq quyosh.\n• A black cat — qora mushuk.\n• White snow — oq qor.\n• The sun is yellow. — Quyosh sariq.\n• The cat is black. — Mushuk qora.\n• The snow is white. — Qor oq.\n\n✍️ MASHQ:\n1. \"Sariq quyosh\" → ✅ a yellow sun\n2. \"Qora mushuk\" → ✅ a black cat\n3. \"Oq qor\" → ✅ white snow\n4. \"Mushuk qora\" → ✅ The cat is black.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta rang: yellow (sariq), black (qora), white (oq). Jami 6 ta rang. Uyga vazifa: 6 ta rangni takrorlang.\n\n🤖 TalkMe yakuniy: Zo'r! Endi 6 ta rang bilasiz! Ertaga: hajm — big, small. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_36",
            topicId = "topic_04_adjectives",
            orderIndex = 4,
            titleEnglish = "36-Dars: Size: Big, Small, Long, Short, Tall",
            titleUzbek = "Hajm — Big, Small, Long, Short, Tall",
            objectiveUzbek = "🤖 TalkMe: Bugun hajmni o'rganamiz! 5 ta sifat: big, small, long, short, tall. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Big (big) — katta\n• Small (smol) — kichik\n• Long (long) — uzun (yo'l, daryo, narsa uchun)\n• Short (shot) — qisqa yoki past bo'yli\n• Tall (tol) — baland (odam, daraxt, bino uchun)\n\n💬 MISOLLAR:\n• A big house — katta uy.\n• A small cat — kichik mushuk.\n• A long road — uzun yo'l.\n• A short pen — qisqa ruchka.\n• A tall man — baland bo'yli erkak.\n\n✍️ MASHQ:\n1. \"Katta uy\" → ✅ a big house\n2. \"Kichik mushuk\" → ✅ a small cat\n3. \"Uzun yo'l\" → ✅ a long road\n4. \"Qisqa ruchka\" → ✅ a short pen\n5. \"Baland erkak\" → ✅ a tall man",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 5 ta hajm sifat: big, small, long, short, tall. Uyga vazifa: \"a big book, a small pen, a long road\" deb ayting.\n\n🤖 TalkMe yakuniy: Zo'r! Endi hajmni bilasiz! Ertaga: sifat — good, bad, nice. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_37",
            topicId = "topic_04_adjectives",
            orderIndex = 5,
            titleEnglish = "37-Dars: Quality: Good, Bad, Nice",
            titleUzbek = "Sifat — Good, Bad, Nice",
            objectiveUzbek = "🤖 TalkMe: Bugun baholash sifatlarini o'rganamiz: good, bad, nice. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Good (gud) — yaxshi\n• Bad (bad) — yomon\n• Nice (nays) — chiroyli, yoqimli\nOtdan oldin yoki fe'ldan keyin keladi.\n\n💬 MISOLLAR:\n• A good book — yaxshi kitob.\n• A bad dog — yomon it.\n• A nice house — chiroyli uy.\n• The book is good. — Kitob yaxshi.\n• The house is nice. — Uy chiroyli.\n\n✍️ MASHQ:\n1. \"Yaxshi kitob\" → ✅ a good book\n2. \"Yomon it\" → ✅ a bad dog\n3. \"Chiroyli uy\" → ✅ a nice house\n4. \"Kitob yaxshi\" → ✅ The book is good.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta sifat: good (yaxshi), bad (yomon), nice (chiroyli). Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Ajoyib! Ertaga: harorat — hot, cold, warm. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_38",
            topicId = "topic_04_adjectives",
            orderIndex = 6,
            titleEnglish = "38-Dars: Temperature: Hot, Cold, Warm",
            titleUzbek = "Harorat — Hot, Cold, Warm",
            objectiveUzbek = "🤖 TalkMe: Bugun harorat sifatlarini o'rganamiz: hot, cold, warm. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Hot (hot) — issiq\n• Cold (kould) — sovuq\n• Warm (vom) — iliq\nOtdan oldin yoki fe'ldan keyin keladi.\n\n💬 MISOLLAR:\n• Hot water — issiq suv.\n• Cold water — sovuq suv.\n• Warm water — iliq suv.\n• The water is hot. — Suv issiq.\n• The water is cold. — Suv sovuq.\n• The water is warm. — Suv iliq.\n\n✍️ MASHQ:\n1. \"Issiq suv\" → ✅ hot water\n2. \"Sovuq suv\" → ✅ cold water\n3. \"Iliq suv\" → ✅ warm water\n4. \"Suv issiq\" → ✅ The water is hot.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3 ta sifat: hot (issiq), cold (sovuq), warm (iliq). Uyga vazifa: \"hot water, cold water, warm water\" deb 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi haroratni bilasiz! Ertaga: sifatning o'rni — otdan oldin. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_39",
            topicId = "topic_04_adjectives",
            orderIndex = 7,
            titleEnglish = "39-Dars: Adjective Before Noun",
            titleUzbek = "Sifatning o'rni — Otdan oldin",
            objectiveUzbek = "🤖 TalkMe: Bugun sifatning birinchi o'rnini mustahkamlaymiz: Sifat + Ot! 🚀",
            explanationUzbek = "📖 QOIDA:\nFormula: Sifat + Ot → a red apple, a big house, a good book.\nMuhim: Ko'plikda ham sifat o'zgarmaydi: two big houses (bigs emas!).\nAgar bir nechta sifat kelsa: fikr → hajm → yosh → rang tartibida keladi (a nice big red car).\n\n💬 MISOLLAR:\n• A red apple — qizil olma.\n• A big house — katta uy.\n• A good book — yaxshi kitob.\n• A nice car — chiroyli mashina.\n• Two big houses — ikkita katta uy.\n\n✍️ MASHQ:\n1. \"Qizil olma\" → ✅ a red apple\n2. \"Katta uy\" → ✅ a big house\n3. \"Yaxshi kitob\" → ✅ a good book\n4. \"Ikkita katta uy\" → ✅ two big houses",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sifat otdan oldin keladi (a red apple) va ko'plikda o'zgarmaydi (two big houses).\n\n🤖 TalkMe yakuniy: Ajoyib! Sifatning o'rnini bilasiz! Ertaga: sifat fe'ldan keyin + takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_04_40",
            topicId = "topic_04_adjectives",
            orderIndex = 8,
            titleEnglish = "40-Dars: Adjective After Verb & Part 2 Review",
            titleUzbek = "Sifatning o'rni — Fe'ldan keyin + Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun sifatning ikkinchi o'rnini o'rganamiz: fe'ldan keyin! Va Partiya 2 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFormula: Ega + to be (am/is/are) + Sifat.\n• The apple is red. — Olma qizil.\n• The house is big. — Uy katta.\n• The cars are blue. — Mashinalar ko'k (blues emas!).\nIkki asosiy o'rin:\n1) Otdan oldin: a red apple.\n2) Fe'ldan keyin: The apple is red.\n\n💬 MISOLLAR:\n• The apple is red. — Olma qizil.\n• The house is big. — Uy katta.\n• The book is good. — Kitob yaxshi.\n• The cars are blue. — Mashinalar ko'k.\n• The trees are green. — Daraxtlar yashil.\n\n✍️ MASHQ:\n1. \"Olma qizil\" → ✅ The apple is red.\n2. \"Uy katta\" → ✅ The house is big.\n3. \"Kitob yaxshi\" → ✅ The book is good.\n4. \"Mashinalar ko'k\" → ✅ The cars are blue.\n5. A1 Sifatlarni takrorlang: red, blue, green, yellow, black, white, big, small, long, short, tall, good, bad, nice, hot, cold, warm.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sifat ikki joyda keladi: otdan oldin (a red apple) yoki fe'ldan keyin (The apple is red). Ko'plikda o'zgarmaydi. A1 Sifatlar va Partiya 2 tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz A1 Otlar va A1 Sifatlarni to'liq o'zlashtirdingiz! Partiya 2 (21–40 darslar) tugadi! Keyingi bo'lim — Partiya 3: Olmoshlar va Ravishlar! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 2 — DARS 21–32: A1 OTLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_03_car", "topic_03_nouns", "sec_03_23", "Car", "mashina", "My car is red.", "Mening mashinam qizil.", isStudied = false),
        VocabularyEntity("voc_03_tree", "topic_03_nouns", "sec_03_23", "Tree", "daraxt", "The tree is tall.", "Daraxt baland.", isStudied = false),
        VocabularyEntity("voc_03_bus", "topic_03_nouns", "sec_03_26", "Bus", "avtobus", "Two buses are on the road.", "Yo'lda ikkita avtobus bor.", isStudied = false),
        VocabularyEntity("voc_03_class", "topic_03_nouns", "sec_03_26", "Class", "sinf yoki dars", "There are three classes.", "Uchta sinf bor.", isStudied = false),
        VocabularyEntity("voc_03_dish", "topic_03_nouns", "sec_03_26", "Dish", "idish, likopcha", "Wash four dishes.", "To'rtta idishni yuving.", isStudied = false),
        VocabularyEntity("voc_03_watch", "topic_03_nouns", "sec_03_26", "Watch", "qo'l soati", "He has two watches.", "Uning ikkita soati bor.", isStudied = false),
        VocabularyEntity("voc_03_glass", "topic_03_nouns", "sec_03_27", "Glass", "stakan", "I have two glasses of water.", "Menda ikki stakan suv bor.", isStudied = false),
        VocabularyEntity("voc_03_brush", "topic_03_nouns", "sec_03_27", "Brush", "cho'tka", "There are three brushes.", "Uchta cho'tka bor.", isStudied = false),
        VocabularyEntity("voc_03_church", "topic_03_nouns", "sec_03_27", "Church", "cherkov", "The churches are old.", "Cherkovlar qadimiy.", isStudied = false),
        VocabularyEntity("voc_03_fox", "topic_03_nouns", "sec_03_27", "Fox", "tulki", "I see two foxes.", "Men ikkita tulkini ko'raman.", isStudied = false),
        VocabularyEntity("voc_03_city", "topic_03_nouns", "sec_03_28", "City", "shahar", "Tashkent and Samarkand are big cities.", "Toshkent va Samarqand — katta shaharlar.", isStudied = false),
        VocabularyEntity("voc_03_baby", "topic_03_nouns", "sec_03_28", "Baby", "chaqaloq", "Three babies are sleeping.", "Uchta chaqaloq uxlayapti.", isStudied = false),
        VocabularyEntity("voc_03_family", "topic_03_nouns", "sec_03_28", "Family", "oila", "Four families live here.", "Bu yerda to'rtta oila yashaydi.", isStudied = false),
        VocabularyEntity("voc_03_country", "topic_03_nouns", "sec_03_28", "Country", "mamlakat", "There are many countries.", "Ko'p mamlakatlar bor.", isStudied = false),
        VocabularyEntity("voc_03_story", "topic_03_nouns", "sec_03_28", "Story", "hikoya", "She knows five stories.", "U beshta hikoya biladi.", isStudied = false),
        VocabularyEntity("voc_03_boy", "topic_03_nouns", "sec_03_28", "Boy", "o'g'il bola", "Two boys are playing.", "Ikkita bola o'ynayapti.", isStudied = false),
        VocabularyEntity("voc_03_day", "topic_03_nouns", "sec_03_28", "Day", "kun", "Seven days make a week.", "Yetti kun bir haftani tashkil qiladi.", isStudied = false),
        VocabularyEntity("voc_03_man", "topic_03_nouns", "sec_03_29", "Man", "erkak (ko'pligi: men)", "Two men are talking.", "Ikkita erkak gaplashyapti.", isStudied = false),
        VocabularyEntity("voc_03_woman", "topic_03_nouns", "sec_03_29", "Woman", "ayol (ko'pligi: women)", "Three women are teachers.", "Uchta ayol — o'qituvchi.", isStudied = false),
        VocabularyEntity("voc_03_foot", "topic_03_nouns", "sec_03_29", "Foot", "oyoq panjasi (ko'pligi: feet)", "We walk with two feet.", "Biz ikki oyoqda yuramiz.", isStudied = false),
        VocabularyEntity("voc_03_tooth", "topic_03_nouns", "sec_03_29", "Tooth", "tish (ko'pligi: teeth)", "Brush your teeth every day.", "Har kuni tishlaringizni yuving.", isStudied = false),
        VocabularyEntity("voc_03_mouse", "topic_03_nouns", "sec_03_29", "Mouse", "sichqon (ko'pligi: mice)", "The cat saw two mice.", "Mushuk ikkita sichqonni ko'rdi.", isStudied = false),
        VocabularyEntity("voc_03_goose", "topic_03_nouns", "sec_03_29", "Goose", "g'oz (ko'pligi: geese)", "Three geese are swimming.", "Uchta g'oz suzyapti.", isStudied = false),
        VocabularyEntity("voc_03_child", "topic_03_nouns", "sec_03_30", "Child", "bola (ko'pligi: children)", "Two children are reading.", "Ikkita bola kitob o'qiyapti.", isStudied = false),
        VocabularyEntity("voc_03_people", "topic_03_nouns", "sec_03_30", "People", "odamlar (birligi: person)", "Many people learn English.", "Ko'p odamlar ingliz tilini o'rganadi.", isStudied = false),
        VocabularyEntity("voc_03_sheep", "topic_03_nouns", "sec_03_30", "Sheep", "qo'y va qo'ylar", "Three sheep are in the field.", "Dalada uchta qo'y bor.", isStudied = false),
        VocabularyEntity("voc_03_deer", "topic_03_nouns", "sec_03_30", "Deer", "kiyik va kiyiklar", "Two deer are in the forest.", "O'rmonda ikkita kiyik bor.", isStudied = false),
        VocabularyEntity("voc_03_chair", "topic_03_nouns", "sec_03_31", "Chair", "stul", "There are four chairs.", "To'rtta stul bor.", isStudied = false),
        VocabularyEntity("voc_03_milk", "topic_03_nouns", "sec_03_32", "Milk", "sut (sanalmaydi)", "I drink warm milk.", "Men iliq sut ichaman.", isStudied = false),
        VocabularyEntity("voc_03_sugar", "topic_03_nouns", "sec_03_32", "Sugar", "shakar (sanalmaydi)", "Put some sugar in the tea.", "Choyga ozgina shakar soling.", isStudied = false),
        VocabularyEntity("voc_03_money", "topic_03_nouns", "sec_03_32", "Money", "pul (sanalmaydi)", "I have much money.", "Menda ko'p pul bor.", isStudied = false),
        VocabularyEntity("voc_03_information", "topic_03_nouns", "sec_03_32", "Information", "ma'lumot (sanalmaydi)", "This information is useful.", "Bu ma'lumot foydali.", isStudied = false),
        VocabularyEntity("voc_03_advice", "topic_03_nouns", "sec_03_32", "Advice", "maslahat (sanalmaydi)", "Give me some advice.", "Menga maslahat bering.", isStudied = false),
        VocabularyEntity("voc_03_news", "topic_03_nouns", "sec_03_32", "News", "yangilik (sanalmaydi)", "The news is good.", "Yangilik yaxshi.", isStudied = false),
        VocabularyEntity("voc_03_work", "topic_03_nouns", "sec_03_32", "Work", "ish (sanalmaydi)", "I have much work today.", "Bugun menda ko'p ish bor.", isStudied = false),

        // =========================================================================
        // PARTIYA 2 — DARS 33–40: A1 SIFATLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_04_blue", "topic_04_adjectives", "sec_04_34", "Blue", "ko'k", "A blue car is outside.", "Tashqarida ko'k mashina turibdi.", isStudied = false),
        VocabularyEntity("voc_04_green", "topic_04_adjectives", "sec_04_34", "Green", "yashil", "The tree is green.", "Daraxt yashil.", isStudied = false),
        VocabularyEntity("voc_04_black", "topic_04_adjectives", "sec_04_35", "Black", "qora", "The cat is black.", "Mushuk qora.", isStudied = false),
        VocabularyEntity("voc_04_white", "topic_04_adjectives", "sec_04_35", "White", "oq", "The snow is white.", "Qor oq.", isStudied = false),
        VocabularyEntity("voc_04_big", "topic_04_adjectives", "sec_04_36", "Big", "katta", "They live in a big house.", "Ular katta uyda yashaydilar.", isStudied = false),
        VocabularyEntity("voc_04_small", "topic_04_adjectives", "sec_04_36", "Small", "kichik", "A small cat is on the chair.", "Kichik mushuk stul ustida.", isStudied = false),
        VocabularyEntity("voc_04_long", "topic_04_adjectives", "sec_04_36", "Long", "uzun", "This is a long road.", "Bu uzun yo'l.", isStudied = false),
        VocabularyEntity("voc_04_short", "topic_04_adjectives", "sec_04_36", "Short", "qisqa", "I have a short pen.", "Menda qisqa ruchka bor.", isStudied = false),
        VocabularyEntity("voc_04_tall", "topic_04_adjectives", "sec_04_36", "Tall", "baland bo'yli", "He is a tall man.", "U baland bo'yli erkak.", isStudied = false),
        VocabularyEntity("voc_04_good", "topic_04_adjectives", "sec_04_37", "Good", "yaxshi", "This is a good book.", "Bu yaxshi kitob.", isStudied = false),
        VocabularyEntity("voc_04_bad", "topic_04_adjectives", "sec_04_37", "Bad", "yomon", "The weather is not bad.", "Ob-havo yomon emas.", isStudied = false),
        VocabularyEntity("voc_04_nice", "topic_04_adjectives", "sec_04_37", "Nice", "chiroyli, yoqimli", "They have a nice house.", "Ularning chiroyli uyi bor.", isStudied = false),
        VocabularyEntity("voc_04_hot", "topic_04_adjectives", "sec_04_38", "Hot", "issiq", "The water is hot.", "Suv issiq.", isStudied = false),
        VocabularyEntity("voc_04_cold", "topic_04_adjectives", "sec_04_38", "Cold", "sovuq", "I drink cold water.", "Men sovuq suv ichaman.", isStudied = false),
        VocabularyEntity("voc_04_warm", "topic_04_adjectives", "sec_04_38", "Warm", "iliq", "Warm water is good.", "Iliq suv foydali.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // =========================================================================
        // PARTIYA 2 — DARS 21–32 GRAMMAR RULES (A1 OTLAR)
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_03_21",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_21",
            titleUzbek = "Ot (Noun) — Kim? va Nima?",
            explanationUzbek = "Ot — shaxs (boy, teacher), narsa (book, table, car), joy (Tashkent, school) yoki tushuncha (love) nomini bildiradi.",
            exampleEnglish = "The book is on the table.",
            exampleUzbek = "Kitob stol ustida.",
            associatedVocabularyIdsCsv = "voc_03_car",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_03_24",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_24",
            titleUzbek = "Ko'plik qo'shimchasi: Ot + -s",
            explanationUzbek = "Ko'pchilik ingliz otlarining ko'pligi oxiriga -s qo'shish orqali yasaladi: book → books, pen → pens, car → cars.",
            exampleEnglish = "One book — two books. One car — two cars.",
            exampleUzbek = "Bitta kitob — ikkita kitob. Bitta mashina — ikkita mashina.",
            associatedVocabularyIdsCsv = "voc_03_car,voc_03_tree",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_03_26",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_26",
            titleUzbek = "-s, -ss, -sh, -ch, -x, -z → -es qoidasi",
            explanationUzbek = "Agar ot -s, -ss, -sh, -ch, -x yoki -z bilan tugasa, ko'plikda -es qo'shiladi: bus → buses, class → classes, dish → dishes, box → boxes, watch → watches.",
            exampleEnglish = "Two buses, three classes, four boxes, five watches.",
            exampleUzbek = "Ikkita avtobus, uchta sinf, to'rtta quti, beshta soat.",
            associatedVocabularyIdsCsv = "voc_03_bus,voc_03_class,voc_03_dish,voc_03_watch,voc_03_glass,voc_03_brush,voc_03_church,voc_03_fox",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_03_28",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_28",
            titleUzbek = "Undosh + y → ies va Unli + y → -s",
            explanationUzbek = "Undosh + y bilan tugagan otlarda y → ies bo'ladi (city → cities, baby → babies, family → families). Unli + y bo'lsa faqat -s qo'shiladi (boy → boys, day → days).",
            exampleEnglish = "Two cities and three babies. Two boys and seven days.",
            exampleUzbek = "Ikkita shahar va uchta chaqaloq. Ikkita bola va yetti kun.",
            associatedVocabularyIdsCsv = "voc_03_city,voc_03_baby,voc_03_family,voc_03_country,voc_03_story,voc_03_boy,voc_03_day",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_03_29",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_29",
            titleUzbek = "Noto'g'ri ko'pliklar: man→men, child→children, fish→fish",
            explanationUzbek = "Ba'zi otlar -s olmaydi: man→men, woman→women, foot→feet, tooth→teeth, mouse→mice, goose→geese, child→children, person→people. Fish, sheep, deer birlik va ko'plikda bir xil qoladi.",
            exampleEnglish = "Two men, three women, two children, and many people.",
            exampleUzbek = "Ikkita erkak, uchta ayol, ikkita bola va ko'p odamlar.",
            associatedVocabularyIdsCsv = "voc_03_man,voc_03_woman,voc_03_foot,voc_03_tooth,voc_03_mouse,voc_03_goose,voc_03_child,voc_03_people,voc_03_sheep,voc_03_deer",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_03_32",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_32",
            titleUzbek = "Sanaladigan (Countable) va Sanalmaydigan (Uncountable) otlar",
            explanationUzbek = "Sanaladigan otlar (apple, book, chair) birlik va ko'plikda bo'ladi. Sanalmaydigan otlar (water, milk, sugar, money, advice, news, work) faqat birlikda keladi, -s va a/an olmaydi, much/some bilan ishlatiladi.",
            exampleEnglish = "I have much work and some water. The news is good.",
            exampleUzbek = "Menda ko'p ish va ozgina suv bor. Yangilik yaxshi.",
            associatedVocabularyIdsCsv = "voc_03_chair,voc_03_milk,voc_03_sugar,voc_03_money,voc_03_information,voc_03_advice,voc_03_news,voc_03_work",
            isStudied = false,
            isMastered = false
        ),

        // =========================================================================
        // PARTIYA 2 — DARS 33–40 GRAMMAR RULES (A1 SIFATLAR)
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_04_33",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_33",
            titleUzbek = "Sifat (Adjective) ko'plikda o'zgarmaydi",
            explanationUzbek = "Sifat narsaning belgisini (rang, hajm, harorat) bildiradi. Ingliz tilida sifat hech qachon -s ko'plik qo'shimchasini olmaydi: a big house → two big houses.",
            exampleEnglish = "One big house — two big houses.",
            exampleUzbek = "Bitta katta uy — ikkita katta uy.",
            associatedVocabularyIdsCsv = "voc_04_big,voc_04_small",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_04_34",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_34",
            titleUzbek = "Rang, hajm, sifat va harorat sifatlari",
            explanationUzbek = "Ranglar: red, blue, green, yellow, black, white. Hajm: big, small, long, short, tall. Baho: good, bad, nice. Harorat: hot, cold, warm.",
            exampleEnglish = "A blue car, a tall man, a good book, and warm water.",
            exampleUzbek = "Ko'k mashina, baland erkak, yaxshi kitob va iliq suv.",
            associatedVocabularyIdsCsv = "voc_04_blue,voc_04_green,voc_04_black,voc_04_white,voc_04_long,voc_04_short,voc_04_tall,voc_04_good,voc_04_bad,voc_04_nice,voc_04_hot,voc_04_cold,voc_04_warm",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_04_40",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_40",
            titleUzbek = "Sifatning gapdagi 2 ta o'rni",
            explanationUzbek = "1) Otdan oldin: a red apple, a big house. 2) Bog'lovchi fe'ldan (am/is/are) keyin: The apple is red. The cars are blue.",
            exampleEnglish = "A red apple. The apple is red. The cars are blue.",
            exampleUzbek = "Qizil olma. Olma qizil. Mashinalar ko'k.",
            associatedVocabularyIdsCsv = "voc_04_blue,voc_04_green,voc_04_big,voc_04_good",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // =========================================================================
        // PARTIYA 2 — DARS 21–32 EXERCISES (topic_03_nouns)
        // =========================================================================
        ExerciseEntity(
            id = "ex_03_21",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_21",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Quyidagi so'zlardan qaysi biri OT (Noun) hisoblanadi?",
            subPromptText = "21-Dars: Ot — kim? yoki nima?",
            audioTextToSpeak = "book",
            optionsPipeSeparated = "book|red|run|big",
            correctAnswer = "book",
            explanationUzbek = "\"Book\" (kitob) — narsa nomi, ya'ni ot (Noun).",
            vocabularyIdsCsv = "voc_01_book",
            grammarRuleIdsCsv = "gram_03_21"
        ),
        ExerciseEntity(
            id = "ex_03_24",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_24",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"To'rtta stol\" birikmasini ingliz tilida toping:",
            subPromptText = "24-Dars: Birlik va ko'plik (-s)",
            audioTextToSpeak = "four tables",
            optionsPipeSeparated = "four tables|four table|one tables|four tablies",
            correctAnswer = "four tables",
            explanationUzbek = "Table + -s → \"four tables\".",
            vocabularyIdsCsv = "voc_01_table",
            grammarRuleIdsCsv = "gram_03_24"
        ),
        ExerciseEntity(
            id = "ex_03_26",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_26",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Ikkita avtobus\" va \"beshta quti\" ingliz tilida qanday yoziladi?",
            subPromptText = "26-Dars: -es qoidasi (-s, -x)",
            audioTextToSpeak = "two buses, five boxes",
            optionsPipeSeparated = "two buses, five boxes|two buss, five boxs|two busies, five boxies|two bus, five box",
            correctAnswer = "two buses, five boxes",
            explanationUzbek = "-s va -x bilan tugagan otlarga -es qo'shiladi: buses, boxes.",
            vocabularyIdsCsv = "voc_03_bus,voc_01_box",
            grammarRuleIdsCsv = "gram_03_26"
        ),
        ExerciseEntity(
            id = "ex_03_28",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_28",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Ikkita shahar\" (city) va \"ikkita bola\" (boy) ko'plikda qanday bo'ladi?",
            subPromptText = "28-Dars: undosh+y → ies, unli+y → -s",
            audioTextToSpeak = "two cities, two boys",
            optionsPipeSeparated = "two cities, two boys|two citys, two boies|two cities, two boies|two citys, two boys",
            correctAnswer = "two cities, two boys",
            explanationUzbek = "City (undosh+y) → cities; boy (unli+y) → boys.",
            vocabularyIdsCsv = "voc_03_city,voc_03_boy",
            grammarRuleIdsCsv = "gram_03_28"
        ),
        ExerciseEntity(
            id = "ex_03_29",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_29",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Ikkita erkak\" va \"ikkita oyoq\" noto'g'ri ko'plikda qanday yoziladi?",
            subPromptText = "29-Dars: man → men, foot → feet",
            audioTextToSpeak = "two men, two feet",
            optionsPipeSeparated = "two men, two feet|two mans, two foots|two men, two foots|two mans, two feet",
            correctAnswer = "two men, two feet",
            explanationUzbek = "Man → men, foot → feet.",
            vocabularyIdsCsv = "voc_03_man,voc_03_foot",
            grammarRuleIdsCsv = "gram_03_29"
        ),
        ExerciseEntity(
            id = "ex_03_30",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_30",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Ikkita bola\" (child) va \"ko'p odamlar\" (person) ingliz tilida qanday bo'ladi?",
            subPromptText = "30-Dars: child → children, person → people",
            audioTextToSpeak = "two children, many people",
            optionsPipeSeparated = "two children, many people|two childs, many peoples|two childrens, many person|two child, many people",
            correctAnswer = "two children, many people",
            explanationUzbek = "Child → children, person → people.",
            vocabularyIdsCsv = "voc_03_child,voc_03_people",
            grammarRuleIdsCsv = "gram_03_29"
        ),
        ExerciseEntity(
            id = "ex_03_32",
            topicId = "topic_03_nouns",
            sectionId = "sec_03_32",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "To'g'ri shaklni tanlang: \"I have ___ money.\"",
            subPromptText = "32-Dars: Money — sanalmaydigan ot",
            audioTextToSpeak = "I have much money.",
            optionsPipeSeparated = "much|many|a|two",
            correctAnswer = "much",
            explanationUzbek = "Money — sanalmaydigan ot, shuning uchun \"much\" bilan ishlatiladi: \"I have much money.\"",
            vocabularyIdsCsv = "voc_03_money",
            grammarRuleIdsCsv = "gram_03_32"
        ),

        // =========================================================================
        // PARTIYA 2 — DARS 33–40 EXERCISES (topic_04_adjectives)
        // =========================================================================
        ExerciseEntity(
            id = "ex_04_33",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_33",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "To'g'ri shaklni tanlang: \"Two ___ houses.\"",
            subPromptText = "33-Dars: Sifat ko'plikda o'zgarmaydi!",
            audioTextToSpeak = "Two big houses.",
            optionsPipeSeparated = "big|bigs|biges|a big",
            correctAnswer = "big",
            explanationUzbek = "Ingliz tilida sifat ko'plikda -s olmaydi: \"two big houses\".",
            vocabularyIdsCsv = "voc_04_big",
            grammarRuleIdsCsv = "gram_04_33"
        ),
        ExerciseEntity(
            id = "ex_04_34",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_34",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Ko'k mashina\" va \"yashil daraxt\" birikmalarini toping:",
            subPromptText = "34-Dars: Ranglar — red, blue, green",
            audioTextToSpeak = "a blue car, a green tree",
            optionsPipeSeparated = "a blue car, a green tree|a red car, a yellow tree|a black car, a white tree|a car blue, a tree green",
            correctAnswer = "a blue car, a green tree",
            explanationUzbek = "Blue — ko'k, green — yashil; sifat otdan oldin keladi.",
            vocabularyIdsCsv = "voc_04_blue,voc_04_green",
            grammarRuleIdsCsv = "gram_04_34"
        ),
        ExerciseEntity(
            id = "ex_04_36",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_36",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Uzun yo'l\" va \"baland erkak\" ingliz tilida qanday aytiladi?",
            subPromptText = "36-Dars: Hajm — big, small, long, short, tall",
            audioTextToSpeak = "a long road, a tall man",
            optionsPipeSeparated = "a long road, a tall man|a tall road, a long man|a short road, a small man|a road long, a man tall",
            correctAnswer = "a long road, a tall man",
            explanationUzbek = "Yo'l uchun \"long\" (uzun), odam bo'yi uchun \"tall\" (baland) ishlatiladi.",
            vocabularyIdsCsv = "voc_04_long,voc_04_tall",
            grammarRuleIdsCsv = "gram_04_34"
        ),
        ExerciseEntity(
            id = "ex_04_38",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_38",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Issiq suv\", \"sovuq suv\", \"iliq suv\" ketma-ketligini toping:",
            subPromptText = "38-Dars: Harorat — hot, cold, warm",
            audioTextToSpeak = "hot water, cold water, warm water",
            optionsPipeSeparated = "hot water, cold water, warm water|cold water, hot water, warm water|warm water, hot water, cold water|good water, bad water, nice water",
            correctAnswer = "hot water, cold water, warm water",
            explanationUzbek = "Hot — issiq, cold — sovuq, warm — iliq.",
            vocabularyIdsCsv = "voc_04_hot,voc_04_cold,voc_04_warm",
            grammarRuleIdsCsv = "gram_04_34"
        ),
        ExerciseEntity(
            id = "ex_04_40",
            topicId = "topic_04_adjectives",
            sectionId = "sec_04_40",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Mashinalar ko'k\" gapining to'g'ri inglizcha tarjimasini tanlang:",
            subPromptText = "40-Dars: Sifat fe'ldan keyin (ko'plikda o'zgarmaydi)",
            audioTextToSpeak = "The cars are blue.",
            optionsPipeSeparated = "The cars are blue.|The cars are blues.|The blue are cars.|The cars is blue.",
            correctAnswer = "The cars are blue.",
            explanationUzbek = "To be fe'lidan keyin sifat ko'plikda ham o'zgarmaydi: \"The cars are blue.\"",
            vocabularyIdsCsv = "voc_04_blue",
            grammarRuleIdsCsv = "gram_04_40"
        )
    )
}
