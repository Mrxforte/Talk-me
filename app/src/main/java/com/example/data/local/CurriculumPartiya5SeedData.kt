package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 5 — DARS 81–100 (TO'LIQ):
 * - A1 ARTIKL + PREDLOG + BOG'LOVCHI (Dars 81–100, topic_08_articles_prepositions)
 */
object CurriculumPartiya5SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 5 — A1 ARTIKL, PREDLOG VA BOG'LOVCHI (DARS 81–100) inside topic_08_articles_prepositions
        // =========================================================================
        LessonSectionEntity(
            id = "sec_08_81",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 1,
            titleEnglish = "81-Dars: Indefinite Article A",
            titleUzbek = "Artikl: A — Undosh oldida",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun artikllarni boshlaymiz. Artikl nima? Artikl — bu ot oldida keladigan kichkina so'z. O'zbek tilida artikl yo'q, lekin ingliz tilida juda muhim! Birinchi artikl: a. 🚀",
            explanationUzbek = "📖 QOIDA:\nA (e) — noaniq artikl. \"Bir\" ma'nosida.\n• Faqat birlikdagi sanaladigan otlar oldida.\n• Undosh tovush bilan boshlangan otlar oldida: a book, a pen, a car, a dog, a table.\nMuhim: \"A\" — suhbatdoshga noma'lum narsa haqida birinchi marta gapirganda ishlatiladi.\n\n💬 MISOLLAR:\n• I have a book. — Menda (bir) kitob bor.\n• She has a cat. — Uning (bir) mushugi bor.\n• This is a pen. — Bu (bir) ruchka.\n• He is a doctor. — U (bir) shifokor.\n• I see a car. — Men (bir) mashinani ko'raman.\n\n✍️ MASHQ:\n1. \"Menda kitob bor\" → ✅ I have a book.\n2. \"Bu ruchka\" → ✅ This is a pen.\n3. \"U shifokor\" → ✅ He is a doctor.\n4. \"A\" qachon ishlatiladi? a) unli oldida b) undosh oldida → ✅ b) undosh oldida\n5. Ovoz chiqarib: \"A book. A pen. A car. A dog.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A — noaniq artikl, \"bir\" ma'nosi. Undosh oldida. Birlikdagi sanaladigan otlar. Uyga vazifa: 3 ta gap tuzing: \"I have a...\", \"This is a...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi a ni bilasiz! Ertaga: an — unli oldida. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_82",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 2,
            titleEnglish = "82-Dars: Indefinite Article An",
            titleUzbek = "Artikl: An — Unli oldida",
            objectiveUzbek = "🤖 TalkMe: Bugun an — unli tovush oldida ishlatiladigan noaniq artikl! 🚀",
            explanationUzbek = "📖 QOIDA:\nAn (en) — noaniq artikl. \"Bir\" ma'nosida.\n• Unli tovush bilan boshlangan otlar oldida: an apple, an egg, an orange, an umbrella, an hour (h o'qilmaydi!).\nMuhim: \"A\" va \"an\" — bir xil ma'no. Faqat tovushga qarab tanlanadi: undosh → a, unli → an.\n\n💬 MISOLLAR:\n• I have an apple. — Menda (bir) olma bor.\n• She eats an egg. — U (bir) tuxum yeydi.\n• This is an orange. — Bu (bir) apelsin.\n• He is an engineer. — U (bir) muhandis.\n• I see an umbrella. — Men (bir) soyabonni ko'raman.\n• Wait an hour. — (Bir) soat kuting. (h o'qilmaydi!)\n\n✍️ MASHQ:\n1. \"Menda olma bor\" → ✅ I have an apple.\n2. \"Bu apelsin\" → ✅ This is an orange.\n3. \"U muhandis\" → ✅ He is an engineer.\n4. To'g'ri tanlang: \"___ egg\" a) a b) an → ✅ b) an\n5. Ovoz chiqarib: \"An apple. An egg. An orange. An umbrella.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: An — unli oldida. A — undosh oldida. Ikkisi ham \"bir\" ma'nosi. Uyga vazifa: 3 ta gap tuzing: \"I have an...\", \"This is an...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi an ni bilasiz! Ertaga: a/an takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_83",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 3,
            titleEnglish = "83-Dars: A and An Review",
            titleUzbek = "A va An — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun a va an ni mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• A — undosh tovush oldida: a book, a pen, a cat, a dog, a car, a table, a teacher, a doctor.\n• An — unli tovush oldida: an apple, an egg, an orange, an umbrella, an hour, an engineer, an actor, an old man.\nMuhim: Tovushga qarang, harfga emas! \"An hour\" — \"h\" harfi bor, lekin \"h\" o'qilmaydi — tovush \"au\" (unli) → an.\n\n💬 MISOLLAR:\n• A: a book, a pen, a cat, a dog, a car, a table.\n• An: an apple, an egg, an orange, an umbrella, an hour.\n• I have a book and an apple. — Menda kitob va olma bor.\n• She is a teacher, he is an engineer.\n\n✍️ MASHQ:\n1. \"___ book\" → ✅ a book\n2. \"___ apple\" → ✅ an apple\n3. \"___ hour\" → ✅ an hour (h o'qilmaydi!)\n4. \"___ car\" → ✅ a car\n5. \"___ orange\" → ✅ an orange\n6. \"___ dog\" → ✅ a dog",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A — undosh. An — unli. Tovushga qarang! Uyga vazifa: 5 ta otni a/an bilan yozing.\n\n🤖 TalkMe yakuniy: Ajoyib! A/an mustahkamlandi! Ertaga: the — aniq artikl. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_84",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 4,
            titleEnglish = "84-Dars: Definite Article The",
            titleUzbek = "Artikl: The — Ma'lum narsa",
            objectiveUzbek = "🤖 TalkMe: Bugun the — aniq artikl! \"O'sha\", \"shu\" ma'nosida! 🚀",
            explanationUzbek = "📖 QOIDA:\nThe (ze) — aniq artikl. \"O'sha\", \"shu\" ma'nosida. Birlik va ko'plikdagi otlar oldida.\nQachon ishlatamiz?\n1) Oldin aytilgan narsa qayta kelganda: I have a book. The book is interesting.\n2) Aniq, ma'lum narsa: The book on the table is mine.\n3) Dunyoda yagona narsa: the sun, the moon, the earth.\n\n💬 MISOLLAR:\n• I have a book. The book is interesting. — Menda bir kitob bor. O'sha kitob qiziq.\n• The sun is bright. — Quyosh yorqin.\n• The moon is beautiful. — Oy chiroyli.\n• The book on the table is mine. — Stoldagi kitob meniki.\n• Please close the door. — Iltimos, eshikni yoping. (shu eshik)\n\n✍️ MASHQ:\n1. \"O'sha kitob qiziq\" → ✅ The book is interesting.\n2. \"Quyosh yorqin\" → ✅ The sun is bright.\n3. \"Stoldagi kitob meniki\" → ✅ The book on the table is mine.\n4. \"The\" qachon ishlatiladi? a) noma'lum narsa b) ma'lum narsa → ✅ b) ma'lum narsa\n5. Ovoz chiqarib: \"The book. The sun. The moon.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The — \"o'sha\", \"shu\". Ma'lum narsa, yagona narsa, qayta aytilgan narsa. Uyga vazifa: 3 ta gap tuzing: \"The ... is...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi the ni bilasiz! Ertaga: the yagona narsa — sun, moon, earth. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_85",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 5,
            titleEnglish = "85-Dars: The with Unique Things",
            titleUzbek = "The — Yagona narsa (sun, moon, earth)",
            objectiveUzbek = "🤖 TalkMe: Bugun the ni yagona narsalar bilan mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nDunyoda yagona bo'lgan narsalar oldida the ishlatiladi:\n• the sun (quyosh)\n• the moon (oy)\n• the earth (yer)\n• the sky (osmon)\n• the sea (dengiz)\n• the world (dunyo)\nMuhim: \"The\" — yagona narsa, boshqasi yo'q.\n\n💬 MISOLLAR:\n• The sun is bright. — Quyosh yorqin.\n• The moon is beautiful. — Oy chiroyli.\n• The earth is round. — Yer dumaloq.\n• The sky is blue. — Osmon ko'k.\n• The sea is deep. — Dengiz chuqur.\n• The world is big. — Dunyo katta.\n\n✍️ MASHQ:\n1. \"Quyosh yorqin\" → ✅ The sun is bright.\n2. \"Oy chiroyli\" → ✅ The moon is beautiful.\n3. \"Yer dumaloq\" → ✅ The earth is round.\n4. \"Osmon ko'k\" → ✅ The sky is blue.\n5. Ovoz chiqarib: \"The sun. The moon. The earth. The sky.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The — yagona narsalar: sun, moon, earth, sky, sea, world. Uyga vazifa: 3 ta gap tuzing: \"The sun...\", \"The moon...\", \"The sky...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi yagona narsalarni bilasiz! Ertaga: artiklsiz holatlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_86",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 6,
            titleEnglish = "86-Dars: Zero Article & Articles Review",
            titleUzbek = "Artiklsiz holatlar va Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun artikl ishlatilmaydigan holatlarni o'rganamiz va hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nArtikl ishlatilmaydi:\n1) Ko'plikdagi otlar umumiy ma'noda: Books are useful.\n2) Sanalmaydigan otlar umumiy ma'noda: Water is important.\n3) Atoqli otlar (ism, shahar, davlat, ko'cha): Tashkent, Ali, Uzbekistan.\n4) Til nomlari: English, Uzbek, French.\n5) Sport o'yinlari: play football, tennis.\n6) Ovqat nomlari: have breakfast, lunch, dinner.\n7) Egalik olmoshlari oldida: my book (not: the my book).\n\n💬 MISOLLAR:\n• Books are useful. — Kitoblar foydali. (ko'plik, umumiy)\n• Water is important. — Suv muhim. (sanalmaydigan, umumiy)\n• I live in Tashkent. — Men Toshkentda yashayman. (atoqli)\n• I speak English. — Men inglizcha gapiraman. (til)\n• We play football. — Biz futbol o'ynaymiz. (sport)\n• I have breakfast at 7. — Men nonushtani 7 da qilaman. (ovqat)\n• This is my book. — Bu mening kitobim. (egalik)\n\n✍️ MASHQ:\n1. \"Kitoblar foydali\" → ✅ Books are useful.\n2. \"Suv muhim\" → ✅ Water is important.\n3. \"Men Toshkentda yashayman\" → ✅ I live in Tashkent.\n4. \"Men inglizcha gapiraman\" → ✅ I speak English.\n5. \"Biz futbol o'ynaymiz\" → ✅ We play football.\n6. Artikllarni takrorlang: a (undosh), an (unli), the (ma'lum/yagona), artiklsiz.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A — undosh, an — unli, the — ma'lum/yagona. Artiklsiz: ko'plik, sanalmaydigan, atoqli, til, sport, ovqat, egalik olmoshlari. A1 Artikl tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A1 Artiklni to'liq o'zlashtirdingiz! Ertaga: predloglar — in, on, at. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_87",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 7,
            titleEnglish = "87-Dars: Preposition In — Inside",
            titleUzbek = "Predlog: In — Ichida",
            objectiveUzbek = "🤖 TalkMe: Endi predloglarni boshlaymiz! Predlog nima? Predlog — bu otning boshqa so'zlarga munosabatini ko'rsatuvchi so'z. O'zbek tilidagi \"-da\", \"-ga\", \"-dan\" kabi. Birinchi predlog: in — \"ichida\". 🚀",
            explanationUzbek = "📖 QOIDA:\nIn (in) — \"ichida\", \"-da\".\nQachon ishlatamiz?\n1) Ichki qism: in the box (qutining ichida), in the bag (sumkaning ichida).\n2) Katta joylar: in Tashkent (Toshkentda), in London (Londonda), in the room (xonada).\n3) Oylar, yillar, fasllar: in May (mayda), in 2024 (2024-yilda), in summer (yozda).\n\n💬 MISOLLAR:\n• The pen is in the box. — Ruchka qutining ichida.\n• I live in Tashkent. — Men Toshkentda yashayman.\n• We will meet in May. — Biz may oyida uchrashamiz.\n• He was born in 1990. — U 1990-yilda tug'ilgan.\n• In summer it is hot. — Yozda issiq bo'ladi.\n\n✍️ MASHQ:\n1. \"Ruchka qutining ichida\" → ✅ The pen is in the box.\n2. \"Men Toshkentda yashayman\" → ✅ I live in Tashkent.\n3. \"Biz may oyida uchrashamiz\" → ✅ We will meet in May.\n4. \"In\" qachon ishlatiladi? a) ustida b) ichida → ✅ b) ichida\n5. Ovoz chiqarib: \"In the box. In Tashkent. In May.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: In — \"ichida\", \"-da\". Ichki qism, katta joylar, oylar, yillar, fasllar. Uyga vazifa: 3 ta gap tuzing: \"... in the box.\", \"... in Tashkent.\", \"... in May.\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi in ni bilasiz! Ertaga: in — katta joy. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_88",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 8,
            titleEnglish = "88-Dars: Preposition In — Large Places",
            titleUzbek = "Predlog: In — Katta joy",
            objectiveUzbek = "🤖 TalkMe: Bugun in ni katta joylar bilan mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nIn — katta joylar uchun: shahar, davlat, xona, bino, quti, sumka.\nKatta joy = atrofingiz o'ralgan joy.\nMisollar: in Tashkent, in Uzbekistan, in London, in the room, in the house, in the car, in the box.\n\n💬 MISOLLAR:\n• I live in Tashkent. — Men Toshkentda yashayman.\n• She is in Uzbekistan. — U O'zbekistonda.\n• We are in the room. — Biz xonadamiz.\n• He is in the car. — U mashinada.\n• The cat is in the house. — Mushuk uyda.\n\n✍️ MASHQ:\n1. \"Men Toshkentda yashayman\" → ✅ I live in Tashkent.\n2. \"U O'zbekistonda\" → ✅ She is in Uzbekistan.\n3. \"Biz xonadamiz\" → ✅ We are in the room.\n4. \"U mashinada\" → ✅ He is in the car.\n5. Ovoz chiqarib: \"In Tashkent. In Uzbekistan. In the room.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: In — katta joylar: shahar, davlat, xona, bino, quti. Uyga vazifa: 3 ta gap tuzing: \"I live in...\", \"She is in...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi katta joylarni bilasiz! Ertaga: on — ustida. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_89",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 9,
            titleEnglish = "89-Dars: Preposition On — On a Surface",
            titleUzbek = "Predlog: On — Ustida",
            objectiveUzbek = "🤖 TalkMe: Bugun on — \"ustida\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOn (on) — \"ustida\", \"-da\".\nQachon ishlatamiz?\n1) Sirt, ustki qism: on the table (stol ustida), on the wall (devorda), on the floor (polda).\n2) Hafta kunlari, sanalar: on Monday (dushanba kuni), on the 5th of May (5-mayda).\n3) Transport (ochiq/jamoat): on the bus (avtobusda), on the train (poyezdda).\n\n💬 MISOLLAR:\n• The book is on the table. — Kitob stol ustida.\n• The picture is on the wall. — Rasm devorda.\n• We will meet on Monday. — Biz dushanba kuni uchrashamiz.\n• My birthday is on the 5th of May. — Mening tug'ilgan kunim 5-mayda.\n• He is on the bus. — U avtobusda.\n\n✍️ MASHQ:\n1. \"Kitob stol ustida\" → ✅ The book is on the table.\n2. \"Rasm devorda\" → ✅ The picture is on the wall.\n3. \"Biz dushanba kuni uchrashamiz\" → ✅ We will meet on Monday.\n4. \"On\" qachon ishlatiladi? a) ichida b) ustida → ✅ b) ustida\n5. Ovoz chiqarib: \"On the table. On the wall. On Monday.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: On — \"ustida\", \"-da\". Sirt, hafta kunlari, sanalar, transport. Uyga vazifa: 3 ta gap tuzing: \"... on the table.\", \"... on Monday.\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi on ni bilasiz! Ertaga: at — aniq nuqta. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_90",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 10,
            titleEnglish = "90-Dars: Preposition At — Exact Point",
            titleUzbek = "Predlog: At — Aniq nuqta",
            objectiveUzbek = "🤖 TalkMe: Bugun at — \"aniq nuqta\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAt (et) — \"-da\", \"yonida\".\nQachon ishlatamiz?\n1) Aniq nuqta, kichik joy: at the door (eshik oldida), at the bus stop (avtobus bekatida), at the window (deraza yonida).\n2) Aniq vaqt: at 5 o'clock (soat 5 da), at noon (peshinda), at midnight (yarim tunda).\n3) Manzil, joy: at home (uyda), at school (maktabda), at work (ishda).\n\n💬 MISOLLAR:\n• He is at the door. — U eshik oldida.\n• We will meet at 5 o'clock. — Biz soat 5 da uchrashamiz.\n• She is at home. — U uyda.\n• He is at school. — U maktabda.\n• I am at work. — Men ishda.\n• Look at me. — Menga qara. (fe'l bilan birikma)\n\n✍️ MASHQ:\n1. \"U eshik oldida\" → ✅ He is at the door.\n2. \"Biz soat 5 da uchrashamiz\" → ✅ We will meet at 5 o'clock.\n3. \"U uyda\" → ✅ She is at home.\n4. \"At\" qachon ishlatiladi? a) katta joy b) aniq nuqta → ✅ b) aniq nuqta\n5. Ovoz chiqarib: \"At the door. At 5 o'clock. At home.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: At — aniq nuqta, kichik joy, aniq vaqt, manzil. Uyga vazifa: 3 ta gap tuzing: \"... at the door.\", \"... at 5 o'clock.\", \"... at home.\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi at ni bilasiz! Ertaga: in/on/at farqi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_91",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 11,
            titleEnglish = "91-Dars: In, On, At Comparison",
            titleUzbek = "In, On, At — Farqi",
            objectiveUzbek = "🤖 TalkMe: Bugun in, on, at ning farqini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• IN — qutining ichida (atrofingiz o'ralgan, katta joy) | Vaqt: in May (oy), in 2024 (yil).\n• ON — stolning ustida (sirtda) | Vaqt: on Monday (kun), on the 5th of May (sana).\n• AT — eshikning oldida (aniq nuqta) | Vaqt: at 5 o'clock (aniq soat).\n\n💬 MISOLLAR:\n• In the box — qutining ichida.\n• On the table — stol ustida.\n• At the door — eshik oldida.\n• In May — mayda (oy).\n• On Monday — dushanba (kun).\n• At 5 o'clock — soat 5 da (aniq vaqt).\n\n✍️ MASHQ:\n1. \"Kitob qutining ichida\" → ✅ The book is in the box.\n2. \"Kitob stol ustida\" → ✅ The book is on the table.\n3. \"Kitob eshik oldida\" → ✅ The book is at the door.\n4. \"May oyida\" → ✅ in May\n5. \"Dushanba kuni\" → ✅ on Monday\n6. \"Soat 5 da\" → ✅ at 5 o'clock",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: IN — ichida, katta joy, oy. ON — ustida, sirt, kun. AT — aniq nuqta, aniq vaqt. Uyga vazifa: 6 ta gap tuzing — 2 tasi in, 2 tasi on, 2 tasi at.\n\n🤖 TalkMe yakuniy: Ajoyib! In/on/at farqini bilasiz! Ertaga: with — bilan. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_92",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 12,
            titleEnglish = "92-Dars: Preposition With",
            titleUzbek = "Predlog: With — Bilan",
            objectiveUzbek = "🤖 TalkMe: Bugun with — \"bilan\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nWith (wiz) — \"bilan\".\nQachon ishlatamiz?\n1) Kimdir bilan birga: with my friend (do'stim bilan), with my mother (onam bilan).\n2) Narsa bilan: with a pen (ruchka bilan), with water (suv bilan).\n3) Xususiyat: a man with a bag (sumkali erkak).\n\n💬 MISOLLAR:\n• I go with my friend. — Men do'stim bilan boraman.\n• She lives with her mother. — U onasi bilan yashaydi.\n• Write with a pen. — Ruchka bilan yozing.\n• I drink tea with milk. — Men sut bilan choy ichaman.\n• A man with a bag came. — Sumkali bir erkak keldi.\n\n✍️ MASHQ:\n1. \"Men do'stim bilan boraman\" → ✅ I go with my friend.\n2. \"U onasi bilan yashaydi\" → ✅ She lives with her mother.\n3. \"Ruchka bilan yozing\" → ✅ Write with a pen.\n4. \"With\" qanday ma'no beradi? a) bilan b) uchun → ✅ a) bilan\n5. Ovoz chiqarib: \"With my friend. With a pen. With milk.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: With — \"bilan\". Kimdir bilan, narsa bilan, xususiyat. Uyga vazifa: 3 ta gap tuzing: \"I go with...\", \"Write with...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi with ni bilasiz! Ertaga: for — uchun. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_93",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 13,
            titleEnglish = "93-Dars: Preposition For",
            titleUzbek = "Predlog: For — Uchun",
            objectiveUzbek = "🤖 TalkMe: Bugun for — \"uchun\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFor (fo) — \"uchun\", \"-ga\".\nQachon ishlatamiz?\n1) Kimdir uchun: for you (siz uchun), for my mother (onam uchun).\n2) Maqsad uchun: for school (maktab uchun), for lunch (tushlik uchun).\n3) Vaqt davomiyligi: for two hours (ikki soat davomida), for a week (bir hafta davomida).\n\n💬 MISOLLAR:\n• This is for you. — Bu siz uchun.\n• I bought a gift for my mother. — Men onam uchun sovg'a sotib oldim.\n• This money is for school. — Bu pul maktab uchun.\n• I studied for two hours. — Men ikki soat davomida o'qidim.\n• Wait for me. — Meni kuting. (fe'l bilan birikma)\n\n✍️ MASHQ:\n1. \"Bu siz uchun\" → ✅ This is for you.\n2. \"Men onam uchun sovg'a sotib oldim\" → ✅ I bought a gift for my mother.\n3. \"Men ikki soat o'qidim\" → ✅ I studied for two hours.\n4. \"For\" qanday ma'no beradi? a) uchun b) bilan → ✅ a) uchun\n5. Ovoz chiqarib: \"For you. For my mother. For two hours.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: For — \"uchun\", \"-ga\". Kimdir uchun, maqsad uchun, vaqt davomiyligi. Uyga vazifa: 3 ta gap tuzing: \"This is for...\", \"I studied for...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi for ni bilasiz! Ertaga: predloglar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_94",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 14,
            titleEnglish = "94-Dars: A1 Prepositions Review",
            titleUzbek = "Predloglar — Takrorlash (in, on, at, with, for)",
            objectiveUzbek = "🤖 TalkMe: Bugun barcha A1 predloglarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA1 predloglari:\n• In — ichida, katta joy, oy/yil.\n• On — ustida, sirt, hafta kuni/sana.\n• At — aniq nuqta, aniq vaqt, manzil.\n• With — bilan.\n• For — uchun.\nMuhim: Har bir predlog fe'l bilan birikib ham keladi: look at (qaramoq), wait for (kutmoq), listen to (tinglamoq).\n\n💬 MISOLLAR:\n• In the box — qutining ichida.\n• On the table — stol ustida.\n• At the door — eshik oldida.\n• With my friend — do'stim bilan.\n• For you — siz uchun.\n• The book is on the table in the room at home. — Kitob uydagi xonada stol ustida.\n\n✍️ MASHQ:\n1. \"Kitob qutining ichida\" → ✅ The book is in the box.\n2. \"Ruchka stol ustida\" → ✅ The pen is on the table.\n3. \"U eshik oldida\" → ✅ He is at the door.\n4. \"Men do'stim bilan boraman\" → ✅ I go with my friend.\n5. \"Bu siz uchun\" → ✅ This is for you.\n6. Predloglarni takrorlang: in, on, at, with, for.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: In, on, at, with, for. A1 predloglar tugadi! Uyga vazifa: 5 ta gap tuzing — har bir predlog bilan.\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A1 predloglarni to'liq o'zlashtirdingiz! Ertaga: bog'lovchilar — and, but, or, so. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_95",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 15,
            titleEnglish = "95-Dars: Conjunction And",
            titleUzbek = "Bog'lovchi: And — Va",
            objectiveUzbek = "🤖 TalkMe: Endi bog'lovchilarni boshlaymiz! Bog'lovchi nima? Bog'lovchi — bu so'zlarni yoki gaplarni bir-biriga bog'lovchi so'z. Birinchi bog'lovchi: and — \"va\". 🚀",
            explanationUzbek = "📖 QOIDA:\nAnd (end) — \"va\".\nQachon ishlatamiz? Qo'shish, birgalik uchun.\n• So'zlarni bog'laydi: tea and coffee, you and I.\n• Gaplarni bog'laydi: I came and she cooked.\n\n💬 MISOLLAR:\n• I have a pen and a book. — Menda ruchka va kitob bor.\n• She likes tea and coffee. — U choy va kofeni yaxshi ko'radi.\n• You and I are friends. — Siz va men do'stlarmiz.\n• I came home and she cooked dinner. — Men uyga keldim va u kechki ovqat qildi.\n• He is tall and strong. — U baland va kuchli.\n\n✍️ MASHQ:\n1. \"Menda ruchka va kitob bor\" → ✅ I have a pen and a book.\n2. \"U choy va kofeni yaxshi ko'radi\" → ✅ She likes tea and coffee.\n3. \"Siz va men do'stlarmiz\" → ✅ You and I are friends.\n4. \"And\" qanday ma'no beradi? a) va b) lekin → ✅ a) va\n5. Ovoz chiqarib: \"A pen and a book. Tea and coffee. You and I.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: And — \"va\". So'zlar va gaplarni bog'laydi. Uyga vazifa: 3 ta gap tuzing: \"I have ... and ...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi and ni bilasiz! Ertaga: but — lekin. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_96",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 16,
            titleEnglish = "96-Dars: Conjunction But",
            titleUzbek = "Bog'lovchi: But — Lekin",
            objectiveUzbek = "🤖 TalkMe: Bugun but — \"lekin\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBut (bat) — \"lekin\", \"ammo\".\nQachon ishlatamiz? Qarama-qarshilik uchun: birinchi fikr bo'lishli, ikkinchisi qarama-qarshi.\nMisollar: I am tired but happy. She is old but strong. I want to go but I can't.\n\n💬 MISOLLAR:\n• I am tired but happy. — Men charchadim, lekin baxtliman.\n• She is old but strong. — U qari, lekin kuchli.\n• I want to go but I can't. — Men bormoqchiman, lekin borolmayman.\n• He is small but clever. — U kichik, lekin aqlli.\n• The book is old but interesting. — Kitob eski, lekin qiziq.\n\n✍️ MASHQ:\n1. \"Men charchadim, lekin baxtliman\" → ✅ I am tired but happy.\n2. \"U qari, lekin kuchli\" → ✅ She is old but strong.\n3. \"Men bormoqchiman, lekin borolmayman\" → ✅ I want to go but I can't.\n4. \"But\" qanday ma'no beradi? a) va b) lekin → ✅ b) lekin\n5. Ovoz chiqarib: \"Tired but happy. Old but strong. Small but clever.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: But — \"lekin\", \"ammo\". Qarama-qarshilik. Uyga vazifa: 3 ta gap tuzing: \"I am ... but ...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi but ni bilasiz! Ertaga: or — yoki. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_97",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 17,
            titleEnglish = "97-Dars: Conjunction Or",
            titleUzbek = "Bog'lovchi: Or — Yoki",
            objectiveUzbek = "🤖 TalkMe: Bugun or — \"yoki\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOr (o) — \"yoki\", \"yoxud\".\nQachon ishlatamiz? Tanlov, alternativa uchun: tea or coffee?, red or blue?\nBuyruq gapda ham \"aks holda\" ma'nosida: Hurry up, or you will be late. (Shoshiling, aks holda kechikasiz.)\n\n💬 MISOLLAR:\n• Tea or coffee? — Choy yoki kofe?\n• Do you want red or blue? — Qizil yoki ko'kni xohlaysizmi?\n• Are you a student or a teacher? — Siz talabamisiz yoki o'qituvchimisiz?\n• Hurry up, or you will be late. — Shoshiling, aks holda kechikasiz.\n• You can take a bus or a taxi. — Siz avtobus yoki taksi olishingiz mumkin.\n\n✍️ MASHQ:\n1. \"Choy yoki kofe?\" → ✅ Tea or coffee?\n2. \"Siz talabamisiz yoki o'qituvchimisiz?\" → ✅ Are you a student or a teacher?\n3. \"Shoshiling, aks holda kechikasiz\" → ✅ Hurry up, or you will be late.\n4. \"Or\" qanday ma'no beradi? a) va b) yoki → ✅ b) yoki\n5. Ovoz chiqarib: \"Tea or coffee? Red or blue? Now or later?\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Or — \"yoki\", \"aks holda\". Tanlov, alternativa, buyruq gapda. Uyga vazifa: 3 ta gap tuzing: \"... or ...?\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi or ni bilasiz! Ertaga: so — shuning uchun. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_98",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 18,
            titleEnglish = "98-Dars: Conjunction So",
            titleUzbek = "Bog'lovchi: So — Shuning uchun",
            objectiveUzbek = "🤖 TalkMe: Bugun so — \"shuning uchun\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSo (sou) — \"shuning uchun\".\nQachon ishlatamiz? Natija uchun: birinchi fikr — sabab, ikkinchisi — natija.\nMisollar: It was cold, so I put on a coat. I was tired, so I went to bed.\n\n💬 MISOLLAR:\n• It was cold, so I put on a coat. — Sovuq edi, shuning uchun palto kiydim.\n• I was tired, so I went to bed. — Men charchadim, shuning uchun yotdim.\n• It was raining, so we stayed at home. — Yomg'ir yog'ayotgan edi, shuning uchun biz uyda qoldik.\n• He studied hard, so he passed the exam. — U qattiq o'qidi, shuning uchun imtihondan o'tdi.\n• I was hungry, so I ate an apple. — Men och edim, shuning uchun olma yedim.\n\n✍️ MASHQ:\n1. \"Sovuq edi, shuning uchun palto kiydim\" → ✅ It was cold, so I put on a coat.\n2. \"Men charchadim, shuning uchun yotdim\" → ✅ I was tired, so I went to bed.\n3. \"Yomg'ir yog'ayotgan edi, shuning uchun uyda qoldik\" → ✅ It was raining, so we stayed at home.\n4. \"So\" qanday ma'no beradi? a) va b) shuning uchun → ✅ b) shuning uchun\n5. Ovoz chiqarib: \"Cold, so I put on a coat. Tired, so I went to bed.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: So — \"shuning uchun\". Sabab → natija. Uyga vazifa: 3 ta gap tuzing: \"... so ...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi so ni bilasiz! Ertaga: bog'lovchilar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_99",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 19,
            titleEnglish = "99-Dars: Conjunctions Review",
            titleUzbek = "Bog'lovchilar — Takrorlash (and, but, or, so)",
            objectiveUzbek = "🤖 TalkMe: Bugun barcha A1 bog'lovchilarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA1 bog'lovchilari:\n• And — \"va\" (qo'shish, birgalik).\n• But — \"lekin\" (qarama-qarshilik).\n• Or — \"yoki\" (tanlov).\n• So — \"shuning uchun\" (natija).\nMuhim: Vergul — but va so oldidan ko'pincha qo'yiladi: I am tired, but happy. It was cold, so I stayed.\n\n💬 MISOLLAR:\n• And: I have a pen and a book.\n• But: I am tired, but happy.\n• Or: Tea or coffee?\n• So: It was cold, so I stayed home.\n\n✍️ MASHQ:\n1. \"Menda ruchka va kitob bor\" → ✅ I have a pen and a book.\n2. \"Men charchadim, lekin baxtliman\" → ✅ I am tired but happy.\n3. \"Choy yoki kofe?\" → ✅ Tea or coffee?\n4. \"Sovuq edi, shuning uchun uyda qoldim\" → ✅ It was cold, so I stayed home.\n5. Bog'lovchilarni takrorlang: and, but, or, so.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: And, but, or, so. Uyga vazifa: 4 ta gap tuzing — har bir bog'lovchi bilan.\n\n🤖 TalkMe yakuniy: Ajoyib! Bog'lovchilar mustahkamlandi! Ertaga: A1 yakuniy takrorlash — barcha mavzular. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_08_100",
            topicId = "topic_08_articles_prepositions",
            orderIndex = 20,
            titleEnglish = "100-Dars: A1 Grammar Review & Part 5 Mastery",
            titleUzbek = "100-Dars: A1 Yakuniy Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun 100-dars! A1 asosiy so'z turkumlarini yakunlaymiz va hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA1 — nima o'rgandik?\n• Sonlar: 1–20, tartib 1–20.\n• Otlar: birlik/ko'plik, -s, -es, y→ies, noto'g'ri ko'plik, sanaladigan/sanalmaydigan.\n• Sifatlar: rang, hajm, sifat, harorat, o'rni.\n• Olmoshlar: I, you, he, she, it, we, they, my, your, his, her, our, their, this, that, these, those.\n• Ravishlar: here, there, now, yesterday, tomorrow, always, never, often, usually.\n• Fe'llar: to be, to have, to do, oddiy fe'llar (3-shaxs -s).\n• Artikllar: a, an, the, artiklsiz.\n• Predloglar: in, on, at, with, for.\n• Bog'lovchilar: and, but, or, so.\n\n💬 MISOLLAR:\n• Son + Sifat + Ot: I have two big books.\n• Olmosh + Ravish + Fe'l: I always read them here.\n• Artikl: I have a book, an apple, the book.\n• Predlog: In the box, on the table, at home, with my friend, for you.\n• Bog'lovchi: Tea and coffee, tired but happy, tea or coffee, cold so I stayed.\n\n✍️ MASHQ:\n1. Son va Sifat: \"Ikkita katta kitob\" → ✅ two big books\n2. Olmosh: \"Ular meniki\" → ✅ They are mine.\n3. Ravish: \"Men har doim bu yerda o'qiyman\" → ✅ I always read here.\n4. Fe'l: \"U ishlaydi\" → ✅ He works.\n5. Artikl: \"Menda kitob va olma bor\" → ✅ I have a book and an apple.\n6. Predlog: \"Kitob stol ustida\" → ✅ The book is on the table.\n7. Bog'lovchi: \"Men charchadim, lekin baxtliman\" → ✅ I am tired but happy.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 100 ta dars yakunlandi! Sonlar, otlar, sifatlar, olmoshlar, ravishlar, fe'llar, artikllar, predloglar, bog'lovchilar. Partiya 5 tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz 100 ta darsni to'liq tugatdingiz! Bu — ulkan g'alaba! Keyingi bo'lim — Partiya 6: Gap tuzilishi va A2 Sonlar! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 5 — DARS 81–100: ARTIKL, PREDLOG, BOG'LOVCHI VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_08_a", "topic_08_articles_prepositions", "sec_08_81", "A", "bir (noaniq artikl, undosh oldida)", "I have a book.", "Menda kitob bor.", isStudied = false),
        VocabularyEntity("voc_08_an", "topic_08_articles_prepositions", "sec_08_82", "An", "bir (noaniq artikl, unli oldida)", "I have an apple.", "Menda olma bor.", isStudied = false),
        VocabularyEntity("voc_08_the", "topic_08_articles_prepositions", "sec_08_84", "The", "o'sha, shu (aniq artikl)", "The sun is bright.", "Quyosh yorqin.", isStudied = false),
        VocabularyEntity("voc_08_earth", "topic_08_articles_prepositions", "sec_08_85", "Earth", "Yer sayyorasi", "The earth is round.", "Yer dumaloq.", isStudied = false),
        VocabularyEntity("voc_08_sky", "topic_08_articles_prepositions", "sec_08_85", "Sky", "osmon", "The sky is blue.", "Osmon ko'k.", isStudied = false),
        VocabularyEntity("voc_08_sea", "topic_08_articles_prepositions", "sec_08_85", "Sea", "dengiz", "The sea is deep.", "Dengiz chuqur.", isStudied = false),
        VocabularyEntity("voc_08_world", "topic_08_articles_prepositions", "sec_08_85", "World", "dunyo", "The world is big.", "Dunyo katta.", isStudied = false),
        VocabularyEntity("voc_08_in", "topic_08_articles_prepositions", "sec_08_87", "In", "ichida, -da (katta joy va oy)", "The pen is in the box.", "Ruchka qutining ichida.", isStudied = false),
        VocabularyEntity("voc_08_on", "topic_08_articles_prepositions", "sec_08_89", "On", "ustida, -da (sirt va hafta kuni)", "The book is on the table.", "Kitob stol ustida.", isStudied = false),
        VocabularyEntity("voc_08_at", "topic_08_articles_prepositions", "sec_08_90", "At", "-da, yonida (aniq nuqta va soat)", "He is at the door.", "U eshik oldida.", isStudied = false),
        VocabularyEntity("voc_08_with", "topic_08_articles_prepositions", "sec_08_92", "With", "bilan", "I go with my friend.", "Men do'stim bilan boraman.", isStudied = false),
        VocabularyEntity("voc_08_for", "topic_08_articles_prepositions", "sec_08_93", "For", "uchun", "This gift is for you.", "Bu sovg'a siz uchun.", isStudied = false),
        VocabularyEntity("voc_08_and", "topic_08_articles_prepositions", "sec_08_95", "And", "va", "I have a pen and a book.", "Menda ruchka va kitob bor.", isStudied = false),
        VocabularyEntity("voc_08_but", "topic_08_articles_prepositions", "sec_08_96", "But", "lekin, ammo", "I am tired but happy.", "Men charchadim, lekin baxtliman.", isStudied = false),
        VocabularyEntity("voc_08_or", "topic_08_articles_prepositions", "sec_08_97", "Or", "yoki, aks holda", "Do you want tea or coffee?", "Choy yoki kofe xohlaysizmi?", isStudied = false),
        VocabularyEntity("voc_08_so", "topic_08_articles_prepositions", "sec_08_98", "So", "shuning uchun", "It was cold, so I stayed home.", "Sovuq edi, shuning uchun uyda qoldim.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // =========================================================================
        // PARTIYA 5 — DARS 81–100 GRAMMAR RULES
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_08_81",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_81",
            titleUzbek = "Noaniq (a, an), Aniq (the) va Nol artikl qoidalari",
            explanationUzbek = "A — undosh tovush oldida (a book), An — unli tovush oldida (an apple, an hour). The — ma'lum yoki dunyoda yagona narsalar oldida (the sun, the moon). Ko'plik umumiy otlar, sanalmaydigan otlar, atoqli otlar, tillar, sport va ovqat nomlari oldida artikl qo'yilmaydi.",
            exampleEnglish = "I have a book and an apple. The sun is bright. I speak English.",
            exampleUzbek = "Menda kitob va olma bor. Quyosh yorqin. Men inglizcha gapiraman.",
            associatedVocabularyIdsCsv = "voc_08_a,voc_08_an,voc_08_the,voc_08_earth,voc_08_sky",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_08_87",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_87",
            titleUzbek = "Predloglar: in, on, at, with, for",
            explanationUzbek = "In — ichida, shahar/davlat, oy/yil (in the box, in Tashkent, in May). On — sirt ustida, hafta kuni/sana (on the table, on Monday). At — aniq nuqta va soat (at the door, at 5 o'clock, at home). With — bilan. For — uchun.",
            exampleEnglish = "The book is on the table in the room at home. This is for you.",
            exampleUzbek = "Kitob uydagi xonada stol ustida. Bu siz uchun.",
            associatedVocabularyIdsCsv = "voc_08_in,voc_08_on,voc_08_at,voc_08_with,voc_08_for",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_08_95",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_95",
            titleUzbek = "Bog'lovchilar: and, but, or, so",
            explanationUzbek = "And — va (qo'shish), But — lekin (qarama-qarshilik), Or — yoki (tanlov), So — shuning uchun (sabab va natija).",
            exampleEnglish = "I am tired, but happy. It was cold, so I stayed at home.",
            exampleUzbek = "Men charchadim, lekin baxtliman. Sovuq edi, shuning uchun uyda qoldim.",
            associatedVocabularyIdsCsv = "voc_08_and,voc_08_but,voc_08_or,voc_08_so",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // =========================================================================
        // PARTIYA 5 — DARS 81–100 EXERCISES (topic_08_articles_prepositions)
        // =========================================================================
        ExerciseEntity(
            id = "ex_08_82",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_82",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joylarni to'ldiring: \"I have ___ book, ___ apple, and wait ___ hour.\"",
            subPromptText = "81–83 Darslar: a (undosh tovush) va an (unli tovush)",
            audioTextToSpeak = "I have a book, an apple, and wait an hour.",
            optionsPipeSeparated = "a / an / an|a / a / a|an / a / a|a / an / a",
            correctAnswer = "a / an / an",
            explanationUzbek = "book — undosh (a), apple — unli (an), hour — 'h' o'qilmaydi, unli tovush bilan boshlanadi (an hour).",
            vocabularyIdsCsv = "voc_08_a,voc_08_an",
            grammarRuleIdsCsv = "gram_08_81"
        ),
        ExerciseEntity(
            id = "ex_08_85",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_85",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Dunyoda yagona narsalar (sun, moon, earth, sky) oldidan qaysi artikl qo'yiladi?",
            subPromptText = "85-Dars: The — yagona narsalar",
            audioTextToSpeak = "The sun is bright and the sky is blue.",
            optionsPipeSeparated = "the|a|an|artikl qo'yilmaydi",
            correctAnswer = "the",
            explanationUzbek = "Dunyoda yagona narsalar oldidan har doim \"the\" ishlatiladi: the sun, the moon, the earth, the sky.",
            vocabularyIdsCsv = "voc_08_the,voc_08_earth,voc_08_sky",
            grammarRuleIdsCsv = "gram_08_81"
        ),
        ExerciseEntity(
            id = "ex_08_86",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_86",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Men Toshkentda yashayman va inglizcha gapiraman\" gapining to'g'ri shaklini tanlang:",
            subPromptText = "86-Dars: Atoqli otlar va til nomlari oldida artikl ishlatilmaydi",
            audioTextToSpeak = "I live in Tashkent and I speak English.",
            optionsPipeSeparated = "I live in Tashkent and I speak English.|I live in the Tashkent and I speak the English.|I live in a Tashkent and I speak an English.|I live in Tashkent and I speak an English.",
            correctAnswer = "I live in Tashkent and I speak English.",
            explanationUzbek = "Shahar nomlari (Tashkent) va til nomlari (English) oldida artikl ishlatilmaydi.",
            vocabularyIdsCsv = "voc_08_in",
            grammarRuleIdsCsv = "gram_08_81"
        ),
        ExerciseEntity(
            id = "ex_08_91",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_91",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Vaqt predloglarini to'g'ri tanlang: \"___ May, ___ Monday, ___ 5 o'clock.\"",
            subPromptText = "91-Dars: In (oy), On (kun), At (aniq vaqt)",
            audioTextToSpeak = "In May, on Monday, at 5 o'clock.",
            optionsPipeSeparated = "in / on / at|on / in / at|at / on / in|in / at / on",
            correctAnswer = "in / on / at",
            explanationUzbek = "Oylar bilan \"in\" (in May), hafta kunlari bilan \"on\" (on Monday), aniq soat bilan \"at\" (at 5 o'clock) ishlatiladi.",
            vocabularyIdsCsv = "voc_08_in,voc_08_on,voc_08_at",
            grammarRuleIdsCsv = "gram_08_87"
        ),
        ExerciseEntity(
            id = "ex_08_94",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_94",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Men do'stim bilan boraman. Bu sovg'a siz uchun.\" gaplarini ingliz tilida toping:",
            subPromptText = "92–94 Darslar: with (bilan) va for (uchun)",
            audioTextToSpeak = "I go with my friend. This gift is for you.",
            optionsPipeSeparated = "I go with my friend. This gift is for you.|I go for my friend. This gift is with you.|I go at my friend. This gift is on you.|I go in my friend. This gift is at you.",
            correctAnswer = "I go with my friend. This gift is for you.",
            explanationUzbek = "With — bilan (with my friend), for — uchun (for you).",
            vocabularyIdsCsv = "voc_08_with,voc_08_for",
            grammarRuleIdsCsv = "gram_08_87"
        ),
        ExerciseEntity(
            id = "ex_08_99",
            topicId = "topic_08_articles_prepositions",
            sectionId = "sec_08_99",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joylarni to'ldiring: \"I am tired, ___ happy. It was cold, ___ I stayed at home.\"",
            subPromptText = "96–99 Darslar: but (lekin) va so (shuning uchun)",
            audioTextToSpeak = "I am tired, but happy. It was cold, so I stayed at home.",
            optionsPipeSeparated = "but / so|so / but|or / and|and / or",
            correctAnswer = "but / so",
            explanationUzbek = "Qarama-qarshilik uchun \"but\" (lekin), natija uchun \"so\" (shuning uchun) ishlatiladi.",
            vocabularyIdsCsv = "voc_08_but,voc_08_so",
            grammarRuleIdsCsv = "gram_08_95"
        )
    )
}
