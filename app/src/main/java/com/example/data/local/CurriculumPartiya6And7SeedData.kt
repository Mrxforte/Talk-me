package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 6 — DARS 101–120 (TO'LIQ):
 * - A1 GAP TUZILISHI (Dars 101–110, topic_09_sentences_a1)
 * - A2 SONLAR (Dars 111–120, topic_10_a2_grammar, orderIndex 1..10)
 *
 * PARTIYA 7 — DARS 121–140 (TO'LIQ):
 * - A2 OTLAR (Dars 121–128, topic_10_a2_grammar, orderIndex 11..18)
 * - A2 SIFATLAR (Dars 129–140, topic_10_a2_grammar, orderIndex 19..30)
 */
object CurriculumPartiya6And7SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 6 — A1 GAP TUZILISHI (DARS 101–110) inside topic_09_sentences_a1
        // =========================================================================
        LessonSectionEntity(
            id = "sec_09_101",
            topicId = "topic_09_sentences_a1",
            orderIndex = 1,
            titleEnglish = "101-Dars: Subject + Predicate",
            titleUzbek = "Gap tuzilishi: Ega + Kesim",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun gap tuzilishini boshlaymiz. Gap nima? Gap — bu tugallangan fikr. Uning ikki asosiy bo'lagi bor: ega va kesim. 🚀",
            explanationUzbek = "📖 QOIDA:\nGap — tugallangan fikr. \"Men talabaman\" — gap.\nIkki asosiy bo'lak:\n• Ega (Subject) — kim? nima? → I, you, he, she, it, we, they, the boy, the book.\n• Kesim (Predicate) — nima qiladi? qanday? → am, is, are, work, live, go, have, like.\nIngliz tilida so'z tartibi: EGA + KESIM + (TO'LDIRUVCHI) + (HOL).\nMuhim: Ega har doim kesimdan oldin! O'zbek tilidan farq qiladi.\n\n💬 MISOLLAR:\n• I (ega) am (kesim) a student. — Men talabaman.\n• He (ega) works (kesim) in a factory. — U zavodda ishlaydi.\n• She (ega) reads (kesim) a book. — U kitob o'qiydi.\n• We (ega) live (kesim) in Tashkent. — Biz Toshkentda yashaymiz.\n• They (ega) play (kesim) football. — Ular futbol o'ynashadi.\n\n✍️ MASHQ:\n1. \"Men talabaman\" — ega va kesimni ajrating. → ✅ I (ega) am (kesim) a student.\n2. \"U kitob o'qiydi\" → ✅ He reads a book.\n3. \"Biz Toshkentda yashaymiz\" → ✅ We live in Tashkent.\n4. Gapda ega qayerda turadi? a) kesimdan keyin b) kesimdan oldin → ✅ b) kesimdan oldin",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Gap — tugallangan fikr. Ega (kim? nima?) + Kesim (nima qiladi?). Tartib: EGA + KESIM + TO'LDIRUVCHI + HOL. Ega har doim kesimdan oldin!\n\n🤖 TalkMe yakuniy: Ajoyib! Endi siz gap tuzilishini bilasiz! Ertaga: darak gap. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_102",
            topicId = "topic_09_sentences_a1",
            orderIndex = 2,
            titleEnglish = "102-Dars: Declarative Sentences",
            titleUzbek = "Darak gap — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun darak gapni o'rganamiz! Darak gap nima? Darak gap — bu xabar beruvchi gap. Faktni aytadi. Nuqta bilan tugaydi. 🚀",
            explanationUzbek = "📖 QOIDA:\nDarak gap (Declarative) — xabar beradi.\n• So'z tartibi: Ega + Kesim + (To'ldiruvchi) + (Hol).\n• Nuqta (.) bilan tugaydi.\n• Bo'lishli yoki bo'lishsiz bo'ladi.\n\n💬 MISOLLAR:\n• I am a student. — Men talabaman.\n• She works in a hospital. — U kasalxonada ishlaydi.\n• They live in Tashkent. — Ular Toshkentda yashaydilar.\n• He reads a book every day. — U har kuni kitob o'qiydi.\n• We play football on Sundays. — Biz yakshanba kunlari futbol o'ynaymiz.\n\n✍️ MASHQ:\n1. \"Men talabaman\" (darak) → ✅ I am a student.\n2. \"U kasalxonada ishlaydi\" → ✅ She works in a hospital.\n3. \"Ular Toshkentda yashaydilar\" → ✅ They live in Tashkent.\n4. Darak gap qanday tinish belgisi bilan tugaydi? a) ? b) . → ✅ b) .",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Darak gap — xabar beradi. Ega + Kesim. Nuqta bilan tugaydi. Uyga vazifa: 5 ta darak gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi darak gapni bilasiz! Ertaga: darak gap bo'lishsiz. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_103",
            topicId = "topic_09_sentences_a1",
            orderIndex = 3,
            titleEnglish = "103-Dars: Negative Declarative Sentences",
            titleUzbek = "Darak gap — Bo'lishsiz",
            objectiveUzbek = "🤖 TalkMe: Bugun bo'lishsiz darak gapni o'rganamiz! \"Men ... emasman\" yoki \"Men ... qilmayman\". 🚀",
            explanationUzbek = "📖 QOIDA:\nBo'lishsiz darak gap — inkor.\n• To be bilan: to be + not: I am not a teacher. He isn't here.\n• Oddiy fe'llar bilan: don't / doesn't + fe'l: I don't work. He doesn't work.\nMuhim: \"Not\" — to be fe'lidan keyin yoki yordamchi fe'ldan keyin turadi.\n\n💬 MISOLLAR:\n• I am not a teacher. — Men o'qituvchi emasman.\n• He isn't here. — U bu yerda emas.\n• They aren't ready. — Ular tayyor emas.\n• I don't work here. — Men bu yerda ishlamayman.\n• She doesn't like coffee. — U kofeni yoqtirmaydi.\n• We don't have a car. — Bizning mashinamiz yo'q.\n\n✍️ MASHQ:\n1. \"Men o'qituvchi emasman\" → ✅ I am not a teacher.\n2. \"U bu yerda emas\" → ✅ He isn't here.\n3. \"Men bu yerda ishlamayman\" → ✅ I don't work here.\n4. \"U kofeni yoqtirmaydi\" → ✅ She doesn't like coffee.\n5. Bo'lishsizda nima ishlatiladi? a) not b) and → ✅ a) not",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: to be + not (isn't, aren't). Oddiy fe'l: don't/doesn't + fe'l. Uyga vazifa: 3 ta bo'lishsiz gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsiz darak gapni bilasiz! Ertaga: so'roq gap — Do you? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_104",
            topicId = "topic_09_sentences_a1",
            orderIndex = 4,
            titleEnglish = "104-Dars: General Questions — Do you?",
            titleUzbek = "So'roq gap — Do you?",
            objectiveUzbek = "🤖 TalkMe: Bugun umumiy so'roq gapni o'rganamiz! \"Siz ... qilasizmi?\" — \"Do you...?\" 🚀",
            explanationUzbek = "📖 QOIDA:\nUmumiy so'roq — ha/yo'q javobli.\nYordamchi fe'l eganing oldiga o'tadi:\n• Oddiy fe'llar bilan: Do / Does + ega + fe'l?\n• Do (I / you / we / they), Does (he / she / it).\n• Javob: Yes, I do. / No, I don't. Yes, he does. / No, he doesn't.\n\n💬 MISOLLAR:\n• Do you like tea? — Siz choyni yoqtirasizmi? (Yes, I do. / No, I don't.)\n• Do they play football? — Ular futbol o'ynashadimi? (Yes, they do. / No, they don't.)\n• Does he work here? — U bu yerda ishlaydimi? (Yes, he does. / No, he doesn't.)\n• Does she like coffee? — U kofeni yoqtiradimi? (Yes, she does. / No, she doesn't.)\n\n✍️ MASHQ:\n1. \"Siz choyni yoqtirasizmi?\" → ✅ Do you like tea?\n2. \"Ular futbol o'ynashadimi?\" → ✅ Do they play football?\n3. \"U bu yerda ishlaydimi?\" → ✅ Does he work here?\n4. Javob bering: \"Do you like tea?\" → ✅ Yes, I do. / No, I don't.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Umumiy so'roq: Do/Does + ega + fe'l? Javob: Yes, I do. / No, he doesn't. Uyga vazifa: 3 ta so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi \"Do you?\" ni bilasiz! Ertaga: so'roq gap — Is he? Are you? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_105",
            topicId = "topic_09_sentences_a1",
            orderIndex = 5,
            titleEnglish = "105-Dars: General Questions — Is he? Are you?",
            titleUzbek = "So'roq gap — Is he? Are you?",
            objectiveUzbek = "🤖 TalkMe: Bugun to be bilan so'roqni o'rganamiz! \"U ... mi?\" — \"Is he...?\" 🚀",
            explanationUzbek = "📖 QOIDA:\nTo be bilan so'roq: to be eganing oldiga o'tadi.\n• Am I? | Is he / she / it? | Are we / you / they?\n• Javob: Yes, I am. / No, I'm not. Yes, he is. / No, he isn't. Yes, they are. / No, they aren't.\n\n💬 MISOLLAR:\n• Am I late? — Men kechikdimmi? (Yes, I am. / No, I'm not.)\n• Is he a doctor? — U shifokormi? (Yes, he is. / No, he isn't.)\n• Is she at home? — U uydami? (Yes, she is. / No, she isn't.)\n• Are you ready? — Siz tayyormisiz? (Yes, I am. / No, I'm not.)\n• Are they students? — Ular talabalarmi? (Yes, they are. / No, they aren't.)\n\n✍️ MASHQ:\n1. \"U shifokormi?\" → ✅ Is he a doctor?\n2. \"U uydami?\" → ✅ Is she at home?\n3. \"Siz tayyormisiz?\" → ✅ Are you ready?\n4. \"Ular talabalarmi?\" → ✅ Are they students?\n5. Javob bering: \"Is he a doctor?\" → ✅ Yes, he is. / No, he isn't.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To be so'roq: Am I? Is he? Are they? Javob: Yes, I am. / No, he isn't. Uyga vazifa: 3 ta so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi to be so'roqni bilasiz! Ertaga: maxsus so'roq — Where? What? When? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_106",
            topicId = "topic_09_sentences_a1",
            orderIndex = 6,
            titleEnglish = "106-Dars: Special Questions — Where? What? When?",
            titleUzbek = "Maxsus so'roq — Where? What? When?",
            objectiveUzbek = "🤖 TalkMe: Bugun maxsus so'roqni o'rganamiz! \"Qayerda? Nima? Qachon?\" 🚀",
            explanationUzbek = "📖 QOIDA:\nMaxsus so'roq — so'roq so'z bilan boshlanadi.\n• So'z tartibi: So'roq so'z + yordamchi fe'l + ega + fe'l?\n• Asosiy so'roq so'zlar: Where (qayerda?), What (nima?), When (qachon?).\nMisollar: Where do you live? What is this? When do you come?\n\n💬 MISOLLAR:\n• Where do you live? — Siz qayerda yashaysiz? (I live in Tashkent.)\n• What is this? — Bu nima? (This is a book.)\n• When do you come? — Siz qachon kelasiz? (I come on Monday.)\n• Where is he? — U qayerda? (He is at home.)\n• What is your name? — Sizning ismingiz nima? (My name is Ali.)\n\n✍️ MASHQ:\n1. \"Siz qayerda yashaysiz?\" → ✅ Where do you live?\n2. \"Bu nima?\" → ✅ What is this?\n3. \"Siz qachon kelasiz?\" → ✅ When do you come?\n4. \"U qayerda?\" → ✅ Where is he?\n5. So'roq so'zlar: a) Where, What, When b) and, but, or → ✅ a) Where, What, When",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Maxsus so'roq: so'roq so'z + yordamchi fe'l + ega + fe'l. Where (qayerda), What (nima), When (qachon). Uyga vazifa: 3 ta maxsus so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi maxsus so'roqni bilasiz! Ertaga: buyruq gap — bo'lishli. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_107",
            topicId = "topic_09_sentences_a1",
            orderIndex = 7,
            titleEnglish = "107-Dars: Imperative Sentences — Affirmative",
            titleUzbek = "Buyruq gap — Bo'lishli",
            objectiveUzbek = "🤖 TalkMe: Bugun buyruq gapni o'rganamiz! \"Oching! Keling! O'tiring!\" 🚀",
            explanationUzbek = "📖 QOIDA:\nBuyruq gap (Imperative) — buyruq, iltimos, maslahat.\n• Ega (you) tushiriladi. Gap kesimdan boshlanadi.\n• Bo'lishli: fe'lning asosiy shakli: Open the door! Come here! Sit down!\n• Nuqta yoki undov belgisi bilan tugaydi.\n\n💬 MISOLLAR:\n• Open the door! — Eshikni oching!\n• Come here! — Bu yoqqa keling!\n• Sit down, please. — O'tiring, iltimos.\n• Read this book. — Bu kitobni o'qing.\n• Listen to me. — Meni tinglang.\n• Please, help me. — Iltimos, menga yordam bering.\n\n✍️ MASHQ:\n1. \"Eshikni oching\" → ✅ Open the door!\n2. \"Bu yoqqa keling\" → ✅ Come here!\n3. \"O'tiring\" → ✅ Sit down.\n4. \"Meni tinglang\" → ✅ Listen to me.\n5. Buyruq gapda ega bormi? a) ha b) yo'q → ✅ b) yo'q (you tushiriladi)",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Buyruq gap — kesimdan boshlanadi. Ega (you) tushiriladi. Fe'lning asosiy shakli: Open! Come! Sit! Uyga vazifa: 3 ta buyruq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi buyruq gapni bilasiz! Ertaga: buyruq gap — bo'lishsiz. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_108",
            topicId = "topic_09_sentences_a1",
            orderIndex = 8,
            titleEnglish = "108-Dars: Imperative Sentences — Negative",
            titleUzbek = "Buyruq gap — Bo'lishsiz",
            objectiveUzbek = "🤖 TalkMe: Bugun bo'lishsiz buyruqni o'rganamiz! \"Bormang! Ochmang! Kechikmang!\" 🚀",
            explanationUzbek = "📖 QOIDA:\nBo'lishsiz buyruq: Don't + fe'lning asosiy shakli:\n• Don't go! | Don't open! | Don't be late!\nMuhim: \"Don't\" — do not qisqartmasi. Buyruqda ishlatiladi.\n\n💬 MISOLLAR:\n• Don't go there! — U yerga bormang!\n• Don't open the window! — Derazani ochmang!\n• Don't be late! — Kechikmang!\n• Don't smoke here! — Bu yerda chekmang!\n• Don't eat my food! — Mening ovqatimni yemang!\n• Please, don't cry. — Iltimos, yig'lamang.\n\n✍️ MASHQ:\n1. \"U yerga bormang\" → ✅ Don't go there!\n2. \"Derazani ochmang\" → ✅ Don't open the window!\n3. \"Kechikmang\" → ✅ Don't be late!\n4. \"Bu yerda chekmang\" → ✅ Don't smoke here!\n5. Bo'lishsiz buyruqda nima ishlatiladi? a) don't b) not → ✅ a) don't",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz buyruq: Don't + fe'l. Don't go! Don't open! Don't be late! Uyga vazifa: 3 ta bo'lishsiz buyruq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsiz buyruqni bilasiz! Ertaga: undov gap — What a...! How...! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_109",
            topicId = "topic_09_sentences_a1",
            orderIndex = 9,
            titleEnglish = "109-Dars: Exclamatory Sentences — What a...! How...!",
            titleUzbek = "Undov gap — What a...! How...!",
            objectiveUzbek = "🤖 TalkMe: Bugun undov gapni o'rganamiz! \"Qanday chiroyli! Qanchalik katta!\" 🚀",
            explanationUzbek = "📖 QOIDA:\nUndov gap (Exclamatory) — kuchli his-hayajon. Undov belgisi (!) bilan tugaydi.\nIkki turi:\n1) What a + sifat + ot! — What a beautiful day! (Qanday chiroyli kun!)\n2) How + sifat / ravish! — How beautiful! (Qanday chiroyli!)\nMuhim: \"What a\" — birlikdagi sanaladigan ot oldida. \"How\" — sifat yoki ravish oldida.\n\n💬 MISOLLAR:\n• What a beautiful day! — Qanday chiroyli kun!\n• What a big house! — Qanday katta uy!\n• What a clever boy! — Qanday aqlli bola!\n• How beautiful! — Qanday chiroyli!\n• How big! — Qanday katta!\n• How fast he runs! — U qanchalik tez yuguradi!\n\n✍️ MASHQ:\n1. \"Qanday chiroyli kun!\" → ✅ What a beautiful day!\n2. \"Qanday katta uy!\" → ✅ What a big house!\n3. \"Qanday chiroyli!\" → ✅ How beautiful!\n4. \"U qanchalik tez yuguradi!\" → ✅ How fast he runs!\n5. Undov gap qanday belgi bilan tugaydi? a) . b) ! → ✅ b) !",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Undov gap: What a + sifat + ot! yoki How + sifat/ravish! Undov belgisi (!). Uyga vazifa: 3 ta undov gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi undov gapni bilasiz! Ertaga: A1 gap tuzilishi yakuniy takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_09_110",
            topicId = "topic_09_sentences_a1",
            orderIndex = 10,
            titleEnglish = "110-Dars: A1 Sentence Structure Mastery",
            titleUzbek = "A1 Gap tuzilishi — Yakuniy takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun A1 gap tuzilishini yakunlaymiz! Hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA1 gap tuzilishi — hammasi:\n• Ega + Kesim: I am a student.\n• Darak gap: I am a student. She works.\n• Darak bo'lishsiz: I'm not a teacher. I don't work.\n• Umumiy so'roq: Do you like tea? Is he a doctor?\n• Maxsus so'roq: Where do you live? What is this? When do you come?\n• Buyruq (bo'lishli): Open the door!\n• Buyruq (bo'lishsiz): Don't go there!\n• Undov: What a beautiful day! How beautiful!\n\n💬 MISOLLAR:\n• Darak: I am a student. → Bo'lishsiz: I'm not a teacher.\n• So'roq (umumiy): Do you like tea? → Javob: Yes, I do.\n• So'roq (maxsus): Where do you live? → Javob: I live in Tashkent.\n• Buyruq: Open the door! → Bo'lishsiz: Don't open the door!\n• Undov: What a beautiful day!\n\n✍️ MASHQ:\n1. \"Men talabaman\" (darak) → ✅ I am a student.\n2. \"Men talaba emasman\" (bo'lishsiz) → ✅ I'm not a student.\n3. \"Siz choyni yoqtirasizmi?\" (umumiy so'roq) → ✅ Do you like tea?\n4. \"Siz qayerda yashaysiz?\" (maxsus so'roq) → ✅ Where do you live?\n5. \"Eshikni oching!\" (buyruq) → ✅ Open the door!\n6. \"U yerga bormang!\" (bo'lishsiz buyruq) → ✅ Don't go there!\n7. \"Qanday chiroyli kun!\" (undov) → ✅ What a beautiful day!",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A1 gap tuzilishi tugadi! Ega+kesim, darak, bo'lishsiz, so'roq (umumiy/maxsus), buyruq (bo'lishli/bo'lishsiz), undov. A1 DARAJASI TO'LIQ TUGADI!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz A1 darajasini to'liq tugatdingiz! Endi siz A2 darajasiga o'tdingiz! Ertaga: A2 sonlar — 20 dan milliongacha. 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 6 — A2 SONLAR (DARS 111–120) inside topic_10_a2_grammar (orderIndex 1..10)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_10_111",
            topicId = "topic_10_a2_grammar",
            orderIndex = 1,
            titleEnglish = "111-Dars: A2 Numbers — 20, 30, 40",
            titleUzbek = "A2 Son: 20–40 (twenty, thirty, forty)",
            objectiveUzbek = "🤖 TalkMe: Endi A2 sonlarni boshlaymiz! Twenty — 20, Thirty — 30, Forty — 40. 🚀",
            explanationUzbek = "📖 QOIDA:\n• Twenty (tventi) — 20.\n• Thirty (theti) — 30.\n• Forty (foti) — 40.\nMuhim: O'nliklar oxiriga -ty qo'shiladi. Lekin three → thirty, four → forty da shakl o'zgaradi ('u' tushadi: forty, fourty emas!).\n\n💬 MISOLLAR:\n• I have twenty books. — Menda yigirmata kitob bor.\n• There are thirty students. — O'ttizta talaba bor.\n• He is forty years old. — U qirq yoshda.\n• Twenty + thirty = fifty. — Yigirma + o'ttiz = ellik.\n\n✍️ MASHQ:\n1. \"Yigirma\" → ✅ twenty\n2. \"O'ttizta talaba\" → ✅ thirty students\n3. \"Qirq\" → ✅ forty\n4. Ovoz chiqarib: \"Twenty, thirty, forty.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'nliklar: twenty (20), thirty (30), forty (40). Oxiriga -ty. Three→thirty, four→forty. Uyga vazifa: 20 dan 40 gacha o'nliklab sanang.\n\n🤖 TalkMe yakuniy: Zo'r! Endi 20–40 bilasiz! Ertaga: 50–90. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_112",
            topicId = "topic_10_a2_grammar",
            orderIndex = 2,
            titleEnglish = "112-Dars: A2 Numbers — 50 to 90",
            titleUzbek = "A2 Son: 50–90 (fifty... ninety)",
            objectiveUzbek = "🤖 TalkMe: Bugun 50–90 o'nliklarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Fifty (fifti) — 50 (five → fifty, shakl o'zgaradi).\n• Sixty (siksti) — 60.\n• Seventy (sevanti) — 70.\n• Eighty (eyti) — 80.\n• Ninety (naynti) — 90.\n\n💬 MISOLLAR:\n• I have fifty books. — Menda ellikta kitob bor.\n• There are sixty students. — Oltmishta talaba bor.\n• He is seventy years old. — U yetmish yoshda.\n• She is eighty years old. — U sakson yoshda.\n• There are ninety pages. — To'qson bet bor.\n\n✍️ MASHQ:\n1. \"Ellik\" → ✅ fifty\n2. \"Oltmishta talaba\" → ✅ sixty students\n3. \"Yetmish yoshda\" → ✅ seventy years old\n4. \"To'qson bet\" → ✅ ninety pages\n5. Ovoz chiqarib: \"Fifty, sixty, seventy, eighty, ninety.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'nliklar: fifty (50), sixty (60), seventy (70), eighty (80), ninety (90). Uyga vazifa: 50 dan 90 gacha o'nliklab sanang.\n\n🤖 TalkMe yakuniy: Zo'r! Endi 50–90 bilasiz! Ertaga: 21, 22, 35, 47 — o'nlik + birlik. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_113",
            topicId = "topic_10_a2_grammar",
            orderIndex = 3,
            titleEnglish = "113-Dars: A2 Numbers — Compound Numbers (21, 35, 47)",
            titleUzbek = "A2 Son: O'nlik + Birlik (21, 22, 35, 47)",
            objectiveUzbek = "🤖 TalkMe: Bugun o'nlik + birlik sonlarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'nlik + chiziqcha (-) + birlik:\n• twenty-one (21), twenty-two (22), thirty-five (35), forty-seven (47).\nMuhim: O'nlik va birlik orasiga chiziqcha (-) qo'yiladi!\n\n💬 MISOLLAR:\n• I have twenty-one books. — Menda yigirma bitta kitob bor.\n• There are thirty-five students. — O'ttiz beshta talaba bor.\n• He is forty-seven years old. — U qirq yetti yoshda.\n• She has fifty-two pens. — Uning ellik ikkita ruchkasi bor.\n• There are ninety-nine pages. — To'qson to'qqiz bet bor.\n\n✍️ MASHQ:\n1. \"Yigirma bir\" → ✅ twenty-one\n2. \"O'ttiz besh\" → ✅ thirty-five\n3. \"Qirq yetti\" → ✅ forty-seven\n4. \"Ellik ikki\" → ✅ fifty-two\n5. O'nlik va birlik orasida nima qo'yiladi? a) nuqta b) chiziqcha → ✅ b) chiziqcha",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'nlik + chiziqcha + birlik: twenty-one, thirty-five, forty-seven. Uyga vazifa: 5 ta sonni yozing: 21, 33, 45, 58, 67.\n\n🤖 TalkMe yakuniy: Zo'r! Endi o'nlik+birlikni bilasiz! Ertaga: 100, 200, 500 — yuzliklar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_114",
            topicId = "topic_10_a2_grammar",
            orderIndex = 4,
            titleEnglish = "114-Dars: A2 Numbers — Hundreds (100, 200, 500)",
            titleUzbek = "A2 Son: 100, 200, 500 (Yuzliklar)",
            objectiveUzbek = "🤖 TalkMe: Bugun yuzliklarni o'rganamiz! A hundred — 100! 🚀",
            explanationUzbek = "📖 QOIDA:\n• (One / A) hundred (handrad) — 100.\n• Two hundred — 200. | Three hundred — 300. | Five hundred — 500.\nMuhim: Aniq son oldida hundred -s olmaydi! ✅ Two hundred (not: two hundreds).\n\"Yuzlab\" ma'nosida -s va of bilan: hundreds of books (yuzlab kitoblar).\n\n💬 MISOLLAR:\n• A hundred books. — Yuzta kitob.\n• Two hundred students. — Ikki yuzta talaba.\n• Three hundred pages. — Uch yuz bet.\n• Five hundred people. — Besh yuz odam.\n• Hundreds of books. — Yuzlab kitoblar. (-s + of)\n\n✍️ MASHQ:\n1. \"Yuzta kitob\" → ✅ a hundred books\n2. \"Ikki yuzta talaba\" → ✅ two hundred students\n3. \"Besh yuz odam\" → ✅ five hundred people\n4. \"Yuzlab kitoblar\" → ✅ hundreds of books\n5. To'g'ri shaklni tanlang: \"Two ___ students.\" a) hundreds b) hundred → ✅ b) hundred",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Hundred — 100. Two hundred — 200. Aniq son oldida -s yo'q. \"Yuzlab\" → hundreds of. Uyga vazifa: 5 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi yuzliklarni bilasiz! Ertaga: 1,000 va 1,000,000. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_115",
            topicId = "topic_10_a2_grammar",
            orderIndex = 5,
            titleEnglish = "115-Dars: A2 Numbers — Thousand & Million",
            titleUzbek = "A2 Son: 1,000 va 1,000,000 (Ming, Million)",
            objectiveUzbek = "🤖 TalkMe: Bugun ming va million ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• (One / A) thousand (thauzand) — 1,000. | Two thousand — 2,000.\n• (One / A) million (milyan) — 1,000,000.\nMuhim: Aniq son oldida -s yo'q: two thousand (not: two thousands).\n• \"Minglab\" → thousands of books. | \"Millionlab\" → millions of people.\n• Murakkab sonlarda: 2,500 — two thousand five hundred.\n\n💬 MISOLLAR:\n• A thousand books. — Mingta kitob.\n• Two thousand students. — Ikki mingta talaba.\n• A million people. — Million odam.\n• Thousands of books. — Minglab kitoblar.\n• Millions of people. — Millionlab odamlar.\n• Two thousand five hundred — 2,500.\n\n✍️ MASHQ:\n1. \"Mingta kitob\" → ✅ a thousand books\n2. \"Ikki mingta talaba\" → ✅ two thousand students\n3. \"Million odam\" → ✅ a million people\n4. \"Minglab kitoblar\" → ✅ thousands of books\n5. To'g'ri shaklni tanlang: \"Two ___ students.\" a) thousands b) thousand → ✅ b) thousand",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Thousand — 1,000. Million — 1,000,000. Aniq son oldida -s yo'q. \"Minglab\" → thousands of, \"Millionlab\" → millions of.\n\n🤖 TalkMe yakuniy: Zo'r! Endi ming va millionni bilasiz! Ertaga: tartib sonlar 21st, 100th. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_116",
            topicId = "topic_10_a2_grammar",
            orderIndex = 6,
            titleEnglish = "116-Dars: A2 Ordinals — 21st, 100th",
            titleUzbek = "A2 Tartib sonlar: 21st, 100th",
            objectiveUzbek = "🤖 TalkMe: Bugun katta tartib sonlarni o'rganamiz: 21-chi, 100-chi! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 21st — twenty-first | 22nd — twenty-second | 35th — thirty-fifth\n• 100th — hundredth | 1,000th — thousandth | 1,000,000th — millionth\nMuhim: Faqat oxirgi son tartib bo'ladi: twenty-first, thirty-fifth. Oldidan the: the twenty-first page.\n\n💬 MISOLLAR:\n• The twenty-first page. — Yigirma birinchi bet.\n• The thirty-fifth student. — O'ttiz beshinchi talaba.\n• The hundredth year. — Yuzinchi yil.\n• The thousandth day. — Minginchi kun.\n• This is the twenty-first century. — Bu yigirma birinchi asr.\n\n✍️ MASHQ:\n1. \"Yigirma birinchi bet\" → ✅ the twenty-first page\n2. \"O'ttiz beshinchi talaba\" → ✅ the thirty-fifth student\n3. \"Yuzinchi yil\" → ✅ the hundredth year\n4. \"Minginchi kun\" → ✅ the thousandth day\n5. Tartib son oldidan nima qo'yiladi? a) a b) the → ✅ b) the",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Tartib: 21st — twenty-first, 35th — thirty-fifth, 100th — hundredth, 1,000th — thousandth. Faqat oxirgi son tartib. Oldidan the.\n\n🤖 TalkMe yakuniy: Zo'r! Endi katta tartib sonlarni bilasiz! Ertaga: A2 sonlar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_117",
            topicId = "topic_10_a2_grammar",
            orderIndex = 7,
            titleEnglish = "117-Dars: A2 Numbers Review",
            titleUzbek = "A2 Sonlar — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun A2 sonlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA2 sonlar — hammasi:\n• O'nliklar: twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety.\n• O'nlik + birlik: twenty-one, thirty-five, forty-seven.\n• Yuzliklar: a hundred, two hundred, five hundred.\n• Minglar va millionlar: a thousand, a million.\n• Tartib: 21st — twenty-first, 100th — hundredth.\n• -s + of: hundreds of, thousands of, millions of.\n\n💬 MISOLLAR:\n• I have twenty-five books. — Menda yigirma beshta kitob bor.\n• There are three hundred students. — Uch yuzta talaba bor.\n• A thousand people came. — Ming kishi keldi.\n• Hundreds of books. — Yuzlab kitoblar.\n• The twenty-first page. — Yigirma birinchi bet.\n\n✍️ MASHQ:\n1. \"Yigirma besh\" → ✅ twenty-five\n2. \"Uch yuzta talaba\" → ✅ three hundred students\n3. \"Ming kishi\" → ✅ a thousand people\n4. \"Yuzlab kitoblar\" → ✅ hundreds of books\n5. \"Yigirma birinchi bet\" → ✅ the twenty-first page",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A2 sonlar: o'nliklar, o'nlik+birlik, yuzlik, ming, million. Tartib: 21st, 100th. -s + of: hundreds of.\n\n🤖 TalkMe yakuniy: Ajoyib! A2 sonlar mustahkamlandi! Ertaga: kasr sonlar (a half, a third). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_118",
            topicId = "topic_10_a2_grammar",
            orderIndex = 8,
            titleEnglish = "118-Dars: A2 Fractions — A half, A third",
            titleUzbek = "A2 Kasr sonlar: a half, a third, a quarter",
            objectiveUzbek = "🤖 TalkMe: Bugun kasr sonlarni boshlaymiz: a half (yarim), a third (uchdan bir)! 🚀",
            explanationUzbek = "📖 QOIDA:\nKasr sonlar:\n• 1/2 — a half (a haf) (one second emas!)\n• 1/3 — a third (a thyod)\n• 1/4 — a quarter (a kvota) yoki a fourth\n• 2/3 — two thirds (maxraj -s)\n• 3/4 — three quarters\n• 1/5 — a fifth | 2/5 — two fifths\nMuhim: Surat — sanoq son (one, two). Maxraj — tartib son (third, fifth). Surat 1 dan katta bo'lsa, maxrajga -s qo'shiladi.\n\n💬 MISOLLAR:\n• A half of the apple. — Olmaning yarmi.\n• A third of the cake. — Tortning uchdan biri.\n• Two thirds of the students. — Talabalarning uchdan ikki qismi.\n• A quarter of an hour. — Chorak soat (15 daqiqa).\n• Three quarters of the day. — Kunning to'rtdan uch qismi.\n\n✍️ MASHQ:\n1. \"Yarim\" → ✅ a half\n2. \"Uchdan bir\" → ✅ a third\n3. \"Chorak\" → ✅ a quarter\n4. \"Uchdan ikki\" → ✅ two thirds\n5. \"To'rtdan uch\" → ✅ three quarters",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Kasr sonlar: a half, a third, a quarter, two thirds, three quarters. Surat — sanoq, maxraj — tartib (-s agar >1).\n\n🤖 TalkMe yakuniy: Zo'r! Endi kasr sonlarni bilasiz! Ertaga: pul — dollar, funt. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_119",
            topicId = "topic_10_a2_grammar",
            orderIndex = 9,
            titleEnglish = "119-Dars: A2 Money — Dollars & Pounds",
            titleUzbek = "A2 Pul: dollar, funt, sent",
            objectiveUzbek = "🤖 TalkMe: Bugun pul haqida gaplashamiz: dollar, funt! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Dollar: $1 — one dollar | $25 — twenty-five dollars | $25.50 — twenty-five dollars and fifty cents.\n• Funt (britancha): £1 — one pound | £25 — twenty-five pounds.\nMuhim: Son 1 dan katta bo'lsa, dollar/pound -s oladi! Cent (sent) va penny (pens) — 1/100 ulush.\n\n💬 MISOLLAR:\n• $1 — one dollar.\n• $25 — twenty-five dollars.\n• $25.50 — twenty-five dollars and fifty cents.\n• £1 — one pound.\n• £25 — twenty-five pounds.\n• This book is ten dollars. — Bu kitob o'n dollar.\n• I have five pounds. — Menda besh funt bor.\n\n✍️ MASHQ:\n1. $10 → ✅ ten dollars\n2. $50 → ✅ fifty dollars\n3. £20 → ✅ twenty pounds\n4. $15.25 → ✅ fifteen dollars and twenty-five cents\n5. Son 1 dan katta bo'lsa \"dollar\" -s oladimi? a) ha b) yo'q → ✅ a) ha",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Pul: dollar ($), pound (£). Son 1 dan katta → dollars/pounds. Cent, penny — 1/100.\n\n🤖 TalkMe yakuniy: Zo'r! Endi pulni bilasiz! Ertaga: telefon va sanalar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_120",
            topicId = "topic_10_a2_grammar",
            orderIndex = 10,
            titleEnglish = "120-Dars: A2 Phone Numbers & Dates",
            titleUzbek = "A2 Telefon va Sanalar + Partiya 6 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun telefon raqamlari va sanalarni o'rganamiz, va A2 sonlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Telefon raqamlari: Har bir raqam alohida o'qiladi! 1235 — one two three five. 0 — \"ou\" deb o'qiladi. Bir xil raqamlar — double: 6634 — double six three four.\n2. Sanalar: 15th May, 1948 — the fifteenth of May, nineteen forty-eight.\n3. Yillar: 1900 — nineteen hundred | 1998 — nineteen ninety-eight | 2024 — twenty twenty-four.\n\n💬 MISOLLAR:\n• My phone number is one two three five. — Mening raqamim bir ikki uch besh.\n• Double six three four. — 6634.\n• My birthday is the fifteenth of May. — Mening tug'ilgan kunim o'n beshinchi may.\n• I was born in nineteen ninety-eight. — Men 1998-yilda tug'ilganman.\n• This year is twenty twenty-four. — Bu yil 2024.\n\n✍️ MASHQ:\n1. Telefon 1235 → ✅ one two three five\n2. Telefon 6634 → ✅ double six three four\n3. \"O'n beshinchi may\" → ✅ the fifteenth of May\n4. 1998 yil → ✅ nineteen ninety-eight\n5. 2024 yil → ✅ twenty twenty-four",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Telefon: har raqam alohida, double bir xil raqamlar. Sanalar: the fifteenth of May. Yillar: nineteen ninety-eight, twenty twenty-four. A2 sonlar va Partiya 6 tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz A2 sonlarni to'liq o'zlashtirdingiz! Partiya 6 (101–120 darslar) tugadi! Keyingi darslar — Partiya 7: A2 Otlar va A2 Sifatlar! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 7 — A2 OTLAR VA A2 SIFATLAR (DARS 121–140) inside topic_10_a2_grammar (orderIndex 11..30)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_10_121",
            topicId = "topic_10_a2_grammar",
            orderIndex = 11,
            titleEnglish = "121-Dars: Proper Nouns — Names & Places",
            titleUzbek = "Atoqli otlar — Ism, shahar, davlat",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun A2 otlarni boshlaymiz! Atoqli otlar — bu ismlar, shaharlar, davlatlar! 🚀",
            explanationUzbek = "📖 QOIDA:\nAtoqli otlar (Proper Nouns) — alohida shaxs, joy yoki narsaning nomi. Ular har doim bosh harf bilan yoziladi!\nTurlari:\n1) Shaxs ismlari: Ali, Mary, John, Aziz.\n2) Shahar nomlari: Tashkent, London, Samarkand.\n3) Davlat nomlari: Uzbekistan, England, America.\n4) Ko'cha nomlari: Oxford Street, Navoi Street.\nMuhim: Atoqli otlar oldida odatda artikl ishlatilmaydi: ✅ Tashkent (not: the Tashkent).\n\n💬 MISOLLAR:\n• Ali is my friend. — Ali mening do'stim.\n• Mary is a doctor. — Meri shifokor.\n• I live in Tashkent. — Men Toshkentda yashayman.\n• He is from Uzbekistan. — U O'zbekistondan.\n• We visited London. — Biz Londonga bordik.\n\n✍️ MASHQ:\n1. \"Ali mening do'stim\" → ✅ Ali is my friend.\n2. \"Men Toshkentda yashayman\" → ✅ I live in Tashkent.\n3. \"U O'zbekistondan\" → ✅ He is from Uzbekistan.\n4. Atoqli otlar qanday yoziladi? a) kichik harf b) bosh harf → ✅ b) bosh harf",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Atoqli otlar — ism, shahar, davlat, ko'cha. Bosh harf bilan. Artikl ishlatilmaydi. Uyga vazifa: 5 ta atoqli otni yozing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi atoqli otlarni bilasiz! Ertaga: turdosh otlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_122",
            topicId = "topic_10_a2_grammar",
            orderIndex = 12,
            titleEnglish = "122-Dars: Common Nouns",
            titleUzbek = "Turdosh otlar",
            objectiveUzbek = "🤖 TalkMe: Bugun turdosh otlarni o'rganamiz! Bular — umumiy nomlar! 🚀",
            explanationUzbek = "📖 QOIDA:\nTurdosh otlar (Common Nouns) — bir xil turdagi narsalarning umumiy nomi. Ular kichik harf bilan yoziladi.\nMisollar: boy (bola), girl (qiz), teacher (o'qituvchi), city (shahar), country (davlat), book (kitob), car (mashina).\nMuhim: Turdosh otlar birlikda a/an/the olishi mumkin: a boy, the boy.\n\n💬 MISOLLAR:\n• A boy is playing. — Bir bola o'ynayapti. (turdosh)\n• Ali is playing. — Ali o'ynayapti. (atoqli)\n• The city is big. — Shahar katta. (turdosh)\n• Tashkent is big. — Toshkent katta. (atoqli)\n• A teacher works here. — Bir o'qituvchi bu yerda ishlaydi. (turdosh)\n\n✍️ MASHQ:\n1. \"Bir bola o'ynayapti\" → ✅ A boy is playing.\n2. \"Ali o'ynayapti\" → ✅ Ali is playing.\n3. \"Shahar katta\" → ✅ The city is big.\n4. Qaysi biri turdosh ot? a) Ali b) boy → ✅ b) boy",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Turdosh otlar — umumiy nom. Kichik harf. A/an/the olishi mumkin. Atoqli — aniq ism, bosh harf.\n\n🤖 TalkMe yakuniy: Zo'r! Endi turdosh otlarni bilasiz! Ertaga: qaratqich kelishigi 's. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_123",
            topicId = "topic_10_a2_grammar",
            orderIndex = 13,
            titleEnglish = "123-Dars: Possessive Case — Singular 's",
            titleUzbek = "Qaratqich kelishigi — 's (birlik)",
            objectiveUzbek = "🤖 TalkMe: Bugun qaratqich kelishigini boshlaymiz: \"kimning?\" — 's! 🚀",
            explanationUzbek = "📖 QOIDA:\nQaratqich kelishigi (Possessive Case) — \"kimning?\" savoliga javob beradi.\nBirlikdagi otlarga 's qo'shiladi:\n• Ali's book (Alining kitobi), the boy's car (bolaning mashinasi), my mother's bag (onamning sumkasi).\nMuhim: Qaratqich kelishigidagi otdan keyin kelgan ot artikl olmaydi: ✅ Ali's book (not: Ali's the book).\n\n💬 MISOLLAR:\n• Ali's book is on the table. — Alining kitobi stolda.\n• The boy's car is red. — Bolaning mashinasi qizil.\n• My mother's bag is new. — Onamning sumkasi yangi.\n• The teacher's pen is blue. — O'qituvchining ruchkasi ko'k.\n• John's house is big. — Jonning uyi katta.\n\n✍️ MASHQ:\n1. \"Alining kitobi\" → ✅ Ali's book\n2. \"Bolaning mashinasi\" → ✅ the boy's car\n3. \"Onamning sumkasi\" → ✅ my mother's bag\n4. Qaratqich kelishigi qanday savolga javob beradi? a) kim? b) kimning? → ✅ b) kimning?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Qaratqich kelishigi — 's (birlik). \"Kimning?\" savoliga javob. Otdan keyin ot artikl olmaydi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi 's ni bilasiz! Ertaga: 's — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_124",
            topicId = "topic_10_a2_grammar",
            orderIndex = 14,
            titleEnglish = "124-Dars: Possessive 's with Time & Places",
            titleUzbek = "Qaratqich kelishigi — 's (5 ta misol)",
            objectiveUzbek = "🤖 TalkMe: Bugun 's ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n's — birlikdagi otlarga:\n• Jonli otlar: the student's book, the doctor's advice.\n• Vaqt: a month's holiday, today's newspaper.\n• Mamlakat/shahar: London's theatres, England's capital.\n• Kema: the ship's crew.\n\n💬 MISOLLAR:\n• The student's book is new. — Talabaning kitobi yangi.\n• The doctor's advice is good. — Shifokorning maslahati yaxshi.\n• I had a month's holiday. — Men bir oylik ta'til oldim.\n• Today's newspaper is interesting. — Bugungi gazeta qiziq.\n• London's theatres are famous. — Londonning teatrlari mashhur.\n\n✍️ MASHQ:\n1. \"Talabaning kitobi\" → ✅ the student's book\n2. \"Shifokorning maslahati\" → ✅ the doctor's advice\n3. \"Bir oylik ta'til\" → ✅ a month's holiday\n4. \"Bugungi gazeta\" → ✅ today's newspaper\n5. \"Londonning teatrlari\" → ✅ London's theatres",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 's — jonli otlar, vaqt (today's, a month's), mamlakat/shahar, kema. Uyga vazifa: 5 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! 's mustahkamlandi! Ertaga: ko'plik ' — the boys' books. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_125",
            topicId = "topic_10_a2_grammar",
            orderIndex = 15,
            titleEnglish = "125-Dars: Possessive Case — Regular Plural s'",
            titleUzbek = "Qaratqich kelishigi — Ko'plik ' (apostrof)",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'plikdagi qaratqich kelishigini o'rganamiz: ' (faqat apostrof)! 🚀",
            explanationUzbek = "📖 QOIDA:\nKo'plikdagi otlarga (agar -s bilan tugagan bo'lsa) — faqat ' (apostrof) qo'shiladi:\n• the boys' books (bolalarning kitoblari), the students' pens (talabalarning ruchkalari), my parents' house (ota-onamning uyi).\nMuhim: -s qayta qo'shilmaydi! ✅ the boys' books (not: the boys's books).\n\n💬 MISOLLAR:\n• The boys' books are new. — Bolalarning kitoblari yangi.\n• The students' pens are red. — Talabalarning ruchkalari qizil.\n• The workers' tools are here. — Ishchilarning asboblari shu yerda.\n• My parents' house is big. — Ota-onamning uyi katta.\n• The teachers' room is on the second floor. — O'qituvchilarning xonasi ikkinchi qavatda.\n\n✍️ MASHQ:\n1. \"Bolalarning kitoblari\" → ✅ the boys' books\n2. \"Talabalarning ruchkalari\" → ✅ the students' pens\n3. \"Ishchilarning asboblari\" → ✅ the workers' tools\n4. \"Ota-onamning uyi\" → ✅ my parents' house\n5. Ko'plikdagi qaratqich kelishigi qanday yoziladi? a) 's b) ' → ✅ b) '",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ko'plik (-s bilan tugasa) + ' (faqat apostrof). -s qayta qo'shilmaydi: the boys' books.\n\n🤖 TalkMe yakuniy: Zo'r! Endi ko'plik ' ni bilasiz! Ertaga: noto'g'ri ko'plik 's. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_126",
            topicId = "topic_10_a2_grammar",
            orderIndex = 16,
            titleEnglish = "126-Dars: Possessive Case — Irregular Plural 's",
            titleUzbek = "Qaratqich kelishigi — Noto'g'ri ko'plik 's",
            objectiveUzbek = "🤖 TalkMe: Bugun noto'g'ri ko'plikdagi qaratqich kelishigini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nNoto'g'ri ko'plikdagi otlarga (children, men, women, people) — 's qo'shiladi! Chunki ular -s bilan tugamaydi:\n• the children's toys (bolalarning o'yinchoqlari)\n• the men's clothes (erkaklarning kiyimlari)\n• the women's bags (ayollarning sumkalari)\nMuhim: Agar ko'plik -s bilan tugamasa → 's. Agar -s bilan tugasa → '.\n\n💬 MISOLLAR:\n• The children's toys are new. — Bolalarning o'yinchoqlari yangi.\n• The men's clothes are here. — Erkaklarning kiyimlari shu yerda.\n• The women's bags are red. — Ayollarning sumkalari qizil.\n• The people's choice is clear. — Xalqning tanlovi aniq.\n\n✍️ MASHQ:\n1. \"Bolalarning o'yinchoqlari\" → ✅ the children's toys\n2. \"Erkaklarning kiyimlari\" → ✅ the men's clothes\n3. \"Ayollarning sumkalari\" → ✅ the women's bags\n4. Noto'g'ri ko'plikda nima qo'shiladi? a) ' b) 's → ✅ b) 's",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Noto'g'ri ko'plik (children, men, women) + 's. -s bilan tugamasa → 's. Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi noto'g'ri ko'plik 's ni bilasiz! Ertaga: of predlogi — the leg of the table. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_127",
            topicId = "topic_10_a2_grammar",
            orderIndex = 17,
            titleEnglish = "127-Dars: Preposition Of — The leg of the table",
            titleUzbek = "Of predlogi — The leg of the table",
            objectiveUzbek = "🤖 TalkMe: Bugun of predlogi bilan egalikni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOf (ov) — \"-ning\".\nJonsiz otlar uchun ko'pincha of ishlatiladi ('s o'rniga):\n• the leg of the table (stolning oyog'i)\n• the capital of Uzbekistan (O'zbekistonning poytaxti)\n• the roof of the house (uyning tomi)\n\n💬 MISOLLAR:\n• The leg of the table is broken. — Stolning oyog'i siniq.\n• The capital of Uzbekistan is Tashkent. — O'zbekistonning poytaxti — Toshkent.\n• The roof of the house is red. — Uyning tomi qizil.\n• The color of the car is blue. — Mashinaning rangi ko'k.\n• The door of the room is open. — Xonaning eshigi ochiq.\n\n✍️ MASHQ:\n1. \"Stolning oyog'i\" → ✅ the leg of the table\n2. \"O'zbekistonning poytaxti\" → ✅ the capital of Uzbekistan\n3. \"Uyning tomi\" → ✅ the roof of the house\n4. Jonsiz otlar uchun nima ishlatiladi? a) 's b) of → ✅ b) of",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Of — \"-ning\". Jonsiz otlar uchun: the leg of the table, the roof of the house.\n\n🤖 TalkMe yakuniy: Zo'r! Endi of predlogini bilasiz! Ertaga: of + takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_128",
            topicId = "topic_10_a2_grammar",
            orderIndex = 18,
            titleEnglish = "128-Dars: A2 Nouns Mastery",
            titleUzbek = "Of + Takrorlash (A2 Otlar yakuniy)",
            objectiveUzbek = "🤖 TalkMe: Bugun A2 otlarni yakunlaymiz! Hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA2 otlar — hammasi:\n• Atoqli otlar: Ali, Tashkent, Uzbekistan (bosh harf, artikl yo'q).\n• Turdosh otlar: boy, city, teacher (kichik harf, a/an/the mumkin).\n• Qaratqich 's: Ali's book (birlik), the boys' books (ko'plik), the children's toys (noto'g'ri ko'plik).\n• Of predlogi: the leg of the table (jonsiz otlar).\n\n💬 MISOLLAR:\n• Atoqli: Ali lives in Tashkent.\n• Turdosh: The boy has a book.\n• 's (birlik): Ali's book is new.\n• ' (ko'plik): The boys' books are new.\n• 's (noto'g'ri): The children's toys are new.\n• Of: The leg of the table is broken.\n\n✍️ MASHQ:\n1. \"Ali Toshkentda yashaydi\" → ✅ Ali lives in Tashkent.\n2. \"Bolaning kitobi\" → ✅ the boy's book\n3. \"Bolalarning kitoblari\" → ✅ the boys' books\n4. \"Bolalarning o'yinchoqlari\" → ✅ the children's toys\n5. \"Stolning oyog'i\" → ✅ the leg of the table",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A2 otlar tugadi! Atoqli (bosh harf), turdosh (kichik harf), 's (birlik), ' (ko'plik), 's (noto'g'ri ko'plik), of (jonsiz).\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A2 otlarni to'liq o'zlashtirdingiz! Ertaga: A2 sifatlar — qiyosiy daraja. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_129",
            topicId = "topic_10_a2_grammar",
            orderIndex = 19,
            titleEnglish = "129-Dars: Comparative Adjectives — -er (bigger)",
            titleUzbek = "A2 Sifat — Qiyosiy daraja: -er (bigger)",
            objectiveUzbek = "🤖 TalkMe: Endi A2 sifatlarni boshlaymiz! Qiyosiy daraja — \"kattaroq\", \"kichikroq\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nQiyosiy daraja (Comparative) — ikki narsani qiyoslaydi.\nBir bo'g'inli sifatlar — -er qo'shiladi: big → bigger (kattaroq), small → smaller (kichikroq), old → older (kattaroq/qariroq), tall → taller (balandroq).\nImlo qoidalari:\n1) Qisqa unlidan keyin bitta undosh ikkilanadi: big → bigger.\n2) O'qilmaydigan e tushadi: large → larger.\n3) Undosh + y → y→i: happy → happier.\nQiyosiy daraja bilan than: He is taller than me.\n\n💬 MISOLLAR:\n• He is taller than me. — U mendan balandroq.\n• This book is bigger than that one. — Bu kitob unisidan kattaroq.\n• My car is smaller than yours. — Mening mashinam siznikidan kichikroq.\n• She is older than her brother. — U akasidan kattaroq.\n• I am happier today. — Men bugun baxtliroqman.\n\n✍️ MASHQ:\n1. \"U mendan balandroq\" → ✅ He is taller than me.\n2. \"Bu kitob unisidan kattaroq\" → ✅ This book is bigger than that one.\n3. \"Mening mashinam siznikidan kichikroq\" → ✅ My car is smaller than yours.\n4. \"big\" qiyosiy darajasi? → ✅ bigger\n5. \"happy\" qiyosiy darajasi? → ✅ happier",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Qiyosiy daraja: -er (bir bo'g'inli). Big→bigger, small→smaller, happy→happier. Than bilan.\n\n🤖 TalkMe yakuniy: Zo'r! Endi qiyosiy darajani bilasiz! Ertaga: -er (smaller) — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_130",
            topicId = "topic_10_a2_grammar",
            orderIndex = 20,
            titleEnglish = "130-Dars: Comparative Adjectives — -er Practice",
            titleUzbek = "A2 Sifat — Qiyosiy daraja: -er (smaller) — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun -er ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n-er qo'shimchasi: small → smaller, old → older, young → younger, long → longer, short → shorter, cheap → cheaper, rich → richer, poor → poorer.\nImlo: big→bigger, hot→hotter, thin→thinner (undosh ikkilanadi); large→larger, nice→nicer (e tushadi); happy→happier (y→i).\n\n💬 MISOLLAR:\n• This bag is smaller than that one. — Bu sumka unisidan kichikroq.\n• He is older than me. — U mendan kattaroq.\n• My hair is longer than yours. — Mening sochim siznikidan uzunroq.\n• This pen is cheaper than that one. — Bu ruchka unisidan arzonroq.\n• Today is hotter than yesterday. — Bugun kechagidan issiqroq.\n\n✍️ MASHQ:\n1. \"Bu sumka unisidan kichikroq\" → ✅ This bag is smaller than that one.\n2. \"U mendan kattaroq\" → ✅ He is older than me.\n3. \"Bugun kechagidan issiqroq\" → ✅ Today is hotter than yesterday.\n4. \"hot\" qiyosiy darajasi? → ✅ hotter\n5. \"large\" qiyosiy darajasi? → ✅ larger",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -er: smaller, older, longer, cheaper, younger, hotter. Imlo: undosh ikkilanadi, e tushadi, y→i.\n\n🤖 TalkMe yakuniy: Zo'r! -er mustahkamlandi! Ertaga: qiyosiy — than bilan. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_131",
            topicId = "topic_10_a2_grammar",
            orderIndex = 21,
            titleEnglish = "131-Dars: Comparatives with Than",
            titleUzbek = "A2 Sifat — Qiyosiy: than bilan",
            objectiveUzbek = "🤖 TalkMe: Bugun than bog'lovchisini o'rganamiz! Qiyosiy daraja bilan ishlatiladi! 🚀",
            explanationUzbek = "📖 QOIDA:\nThan (zan) — \"-ga qaraganda\", \"-dan\".\nQiyosiy daraja bilan har doim ishlatiladi: taller than me (mendan balandroq), smaller than that (undan kichikroq), better than before (avvalgidan yaxshiroq).\nTuzilish: [sifat + -er / more + sifat] + than + [ikkinchi narsa].\n\n💬 MISOLLAR:\n• He is taller than me. — U mendan balandroq.\n• This book is more interesting than that one. — Bu kitob unisidan qiziqroq.\n• My car is smaller than yours. — Mening mashinam siznikidan kichikroq.\n• Today is hotter than yesterday. — Bugun kechagidan issiqroq.\n• London is larger than Bristol. — London Bristoldan kattaroq.\n\n✍️ MASHQ:\n1. \"U mendan balandroq\" → ✅ He is taller than me.\n2. \"Bu kitob unisidan qiziqroq\" → ✅ This book is more interesting than that one.\n3. \"London Bristoldan kattaroq\" → ✅ London is larger than Bristol.\n4. Qiyosiy daraja bilan nima ishlatiladi? a) than b) the → ✅ a) than",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Than — \"-ga qaraganda\". Qiyosiy daraja bilan har doim ishlatiladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi than ni bilasiz! Ertaga: orttirma daraja — -est. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_132",
            topicId = "topic_10_a2_grammar",
            orderIndex = 22,
            titleEnglish = "132-Dars: Superlative Adjectives — -est (biggest)",
            titleUzbek = "A2 Sifat — Orttirma daraja: -est (biggest)",
            objectiveUzbek = "🤖 TalkMe: Bugun orttirma darajani o'rganamiz: \"eng katta\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nOrttirma daraja (Superlative) — uch yoki undan ortiq narsadan eng yuqorisi.\nBir bo'g'inli sifatlar — -est qo'shiladi: big → the biggest (eng katta), small → the smallest (eng kichik), old → the oldest (eng katta/qari).\nMuhim: Orttirma daraja oldidan har doim the qo'yiladi!\n\n💬 MISOLLAR:\n• He is the tallest in the class. — U sinfdagi eng baland.\n• This is the biggest house. — Bu eng katta uy.\n• She is the oldest in the family. — U oiladagi eng katta.\n• This is the smallest room. — Bu eng kichik xona.\n• Everest is the highest mountain. — Everest eng baland tog'.\n\n✍️ MASHQ:\n1. \"U sinfdagi eng baland\" → ✅ He is the tallest in the class.\n2. \"Bu eng katta uy\" → ✅ This is the biggest house.\n3. \"Bu eng kichik xona\" → ✅ This is the smallest room.\n4. Orttirma daraja oldidan nima qo'yiladi? a) a b) the → ✅ b) the\n5. \"big\" orttirma darajasi? → ✅ the biggest",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Orttirma daraja: the + sifat-est. Big→the biggest, small→the smallest. Oldidan the.\n\n🤖 TalkMe yakuniy: Zo'r! Endi orttirma darajani bilasiz! Ertaga: -est (smallest) — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_133",
            topicId = "topic_10_a2_grammar",
            orderIndex = 23,
            titleEnglish = "133-Dars: Superlative Adjectives — -est Practice",
            titleUzbek = "A2 Sifat — Orttirma: -est (smallest) — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun -est ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n-est qo'shimchasi: small → the smallest, old → the oldest, young → the youngest, long → the longest, short → the shortest, cheap → the cheapest, rich → the richest.\nImlo: big→biggest, hot→hottest (undosh ikkilanadi); large→largest (e tushadi); happy→happiest (y→i).\n\n💬 MISOLLAR:\n• This is the smallest bag. — Bu eng kichik sumka.\n• He is the oldest man here. — U bu yerdagi eng qari odam.\n• She is the youngest student. — U eng yosh talaba.\n• This is the longest road. — Bu eng uzun yo'l.\n• This is the cheapest pen. — Bu eng arzon ruchka.\n\n✍️ MASHQ:\n1. \"Bu eng kichik sumka\" → ✅ This is the smallest bag.\n2. \"U eng qari odam\" → ✅ He is the oldest man.\n3. \"Bu eng uzun yo'l\" → ✅ This is the longest road.\n4. \"cheap\" orttirma darajasi? → ✅ the cheapest\n5. \"hot\" orttirma darajasi? → ✅ the hottest",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -est: smallest, oldest, youngest, longest, cheapest, hottest. Oldidan the.\n\n🤖 TalkMe yakuniy: Zo'r! -est mustahkamlandi! Ertaga: orttirma — the bilan. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_134",
            topicId = "topic_10_a2_grammar",
            orderIndex = 24,
            titleEnglish = "134-Dars: Superlatives with The, In & Of",
            titleUzbek = "A2 Sifat — Orttirma: the bilan (in / of)",
            objectiveUzbek = "🤖 TalkMe: Bugun the ni orttirma daraja bilan mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOrttirma daraja oldidan har doim the qo'yiladi!\n• Joy va guruh oldida in: in the class, in the world, in the family.\n• Ko'plik yoki all oldida of: of all, of the three.\n\n💬 MISOLLAR:\n• He is the best student. — U eng yaxshi talaba.\n• This is the biggest house. — Bu eng katta uy.\n• She is the most beautiful girl in the class. — U sinfdagi eng chiroyli qiz.\n• Everest is the highest mountain in the world. — Everest dunyodagi eng baland tog'.\n• This is the most interesting book of all. — Bu hammadan qiziqroq kitob.\n\n✍️ MASHQ:\n1. \"U eng yaxshi talaba\" → ✅ He is the best student.\n2. \"Bu eng katta uy\" → ✅ This is the biggest house.\n3. \"U sinfdagi eng chiroyli qiz\" → ✅ She is the most beautiful girl in the class.\n4. Orttirma daraja oldidan nima qo'yiladi? a) a b) the → ✅ b) the",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Orttirma + the: the best, the biggest, the most beautiful. Joy bilan in, guruh bilan of.\n\n🤖 TalkMe yakuniy: Zo'r! Endi the ni orttirma bilan bilasiz! Ertaga: ko'p bo'g'inli — more/most. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_135",
            topicId = "topic_10_a2_grammar",
            orderIndex = 25,
            titleEnglish = "135-Dars: Multi-Syllable Adjectives — More & Most",
            titleUzbek = "A2 Sifat — Ko'p bo'g'inli: more va most",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'p bo'g'inli sifatlarni o'rganamiz: more va most! 🚀",
            explanationUzbek = "📖 QOIDA:\nKo'p bo'g'inli sifatlar (2+ bo'g'in) — -er/-est o'rniga more/most ishlatiladi!\n• Qiyosiy: more + sifat: interesting → more interesting (qiziqroq), beautiful → more beautiful (chiroyliroq), difficult → more difficult (qiyinroq).\n• Orttirma: the most + sifat: interesting → the most interesting (eng qiziq), beautiful → the most beautiful (eng chiroyli).\n\n💬 MISOLLAR:\n• This book is more interesting than that one. — Bu kitob unisidan qiziqroq.\n• She is more beautiful than her sister. — U opasidan chiroyliroq.\n• This is the most interesting book. — Bu eng qiziq kitob.\n• She is the most beautiful girl. — U eng chiroyli qiz.\n• This exercise is more difficult than that one. — Bu mashq unisidan qiyinroq.\n\n✍️ MASHQ:\n1. \"Bu kitob unisidan qiziqroq\" → ✅ This book is more interesting than that one.\n2. \"Bu eng qiziq kitob\" → ✅ This is the most interesting book.\n3. \"U eng chiroyli qiz\" → ✅ She is the most beautiful girl.\n4. Ko'p bo'g'inli sifatlarda nima ishlatiladi? a) -er b) more → ✅ b) more",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ko'p bo'g'inli: more (qiyosiy), the most (orttirma). More interesting, the most interesting.\n\n🤖 TalkMe yakuniy: Zo'r! Endi more/most ni bilasiz! Ertaga: ko'p bo'g'inli — most. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_136",
            topicId = "topic_10_a2_grammar",
            orderIndex = 26,
            titleEnglish = "136-Dars: Superlatives with The Most",
            titleUzbek = "A2 Sifat — Ko'p bo'g'inli: the most",
            objectiveUzbek = "🤖 TalkMe: Bugun the most ni mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nThe most + sifat = eng ... :\n• the most interesting (eng qiziq)\n• the most beautiful (eng chiroyli)\n• the most difficult (eng qiyin)\n• the most expensive (eng qimmat)\n• the most important (eng muhim)\n\n💬 MISOLLAR:\n• This is the most interesting book. — Bu eng qiziq kitob.\n• She is the most beautiful girl in the class. — U sinfdagi eng chiroyli qiz.\n• It is the most difficult exercise. — Bu eng qiyin mashq.\n• This is the most expensive car. — Bu eng qimmat mashina.\n• It is the most important question. — Bu eng muhim savol.\n\n✍️ MASHQ:\n1. \"Bu eng qiziq kitob\" → ✅ This is the most interesting book.\n2. \"U sinfdagi eng chiroyli qiz\" → ✅ She is the most beautiful girl in the class.\n3. \"Bu eng qimmat mashina\" → ✅ This is the most expensive car.\n4. \"most\" oldidan nima qo'yiladi? a) a b) the → ✅ b) the",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The most + sifat = eng ... (the most interesting, the most beautiful, the most expensive).\n\n🤖 TalkMe yakuniy: Zo'r! Most mustahkamlandi! Ertaga: noto'g'ri darajalar — good/better/best. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_137",
            topicId = "topic_10_a2_grammar",
            orderIndex = 27,
            titleEnglish = "137-Dars: Irregular Adjectives — Good, Better, Best",
            titleUzbek = "A2 Sifat — Noto'g'ri: good / better / the best",
            objectiveUzbek = "🤖 TalkMe: Bugun noto'g'ri darajalarni o'rganamiz: good, better, the best! 🚀",
            explanationUzbek = "📖 QOIDA:\nNoto'g'ri darajalar — qoidaga bo'ysunmaydi, yodlash kerak!\n• Good (yaxshi) → better (yaxshiroq) → the best (eng yaxshi).\nMuhim: \"Good\" — -er/-est olmaydi! ✅ better (not: gooder). ✅ the best (not: the goodest).\n\n💬 MISOLLAR:\n• This book is good. — Bu kitob yaxshi.\n• This book is better than that one. — Bu kitob unisidan yaxshiroq.\n• This is the best book. — Bu eng yaxshi kitob.\n• He is a good student. — U yaxshi talaba.\n• He is better than me. — U mendan yaxshiroq.\n• He is the best student. — U eng yaxshi talaba.\n\n✍️ MASHQ:\n1. \"Bu kitob yaxshi\" → ✅ This book is good.\n2. \"Bu kitob unisidan yaxshiroq\" → ✅ This book is better than that one.\n3. \"Bu eng yaxshi kitob\" → ✅ This is the best book.\n4. \"good\" qiyosiy darajasi? → ✅ better\n5. \"good\" orttirma darajasi? → ✅ the best",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Good → better → the best. Noto'g'ri daraja! Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi good/better/best ni bilasiz! Ertaga: bad/worse/worst. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_138",
            topicId = "topic_10_a2_grammar",
            orderIndex = 28,
            titleEnglish = "138-Dars: Irregular Adjectives — Bad, Worse, Worst",
            titleUzbek = "A2 Sifat — Noto'g'ri: bad / worse / the worst",
            objectiveUzbek = "🤖 TalkMe: Bugun bad, worse, the worst ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bad (yomon) → worse (yomonroq) → the worst (eng yomon).\nMuhim: \"Bad\" — -er/-est olmaydi! ✅ worse (not: badder). ✅ the worst (not: the baddest).\n\n💬 MISOLLAR:\n• This book is bad. — Bu kitob yomon.\n• This book is worse than that one. — Bu kitob unisidan yomonroq.\n• This is the worst book. — Bu eng yomon kitob.\n• The weather is bad today. — Bugun havo yomon.\n• The weather is worse than yesterday. — Havo kechagidan yomonroq.\n• This is the worst weather. — Bu eng yomon havo.\n\n✍️ MASHQ:\n1. \"Bu kitob yomon\" → ✅ This book is bad.\n2. \"Bu kitob unisidan yomonroq\" → ✅ This book is worse than that one.\n3. \"Bu eng yomon kitob\" → ✅ This is the worst book.\n4. \"bad\" qiyosiy darajasi? → ✅ worse\n5. \"bad\" orttirma darajasi? → ✅ the worst",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bad → worse → the worst. Noto'g'ri daraja! Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bad/worse/worst ni bilasiz! Ertaga: noto'g'ri — far, little, much. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_139",
            topicId = "topic_10_a2_grammar",
            orderIndex = 29,
            titleEnglish = "139-Dars: Irregular Adjectives — Far, Little, Much",
            titleUzbek = "A2 Sifat — Noto'g'ri: far, little, much/many",
            objectiveUzbek = "🤖 TalkMe: Bugun qolgan noto'g'ri darajalarni o'rganamiz: far, little, much! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Far (uzoq) → farther / further (uzoqroq) → the farthest / furthest (eng uzoq).\n• Little (oz) → less (ozroq) → the least (eng oz).\n• Much / many (ko'p) → more (ko'proq) → the most (eng ko'p).\n\n💬 MISOLLAR:\n• Far: This shop is far. That shop is farther. This is the farthest shop.\n• Little: I have little time. I have less time than you. I have the least time.\n• Much: I have much work. I have more work than you. I have the most work.\n• Many: I have many books. I have more books than you. I have the most books.\n\n✍️ MASHQ:\n1. \"Uzoq\" → ✅ far | \"Uzoqroq\" → ✅ farther\n2. \"Eng uzoq\" → ✅ the farthest\n3. \"Oz\" → ✅ little | \"Ozroq\" → ✅ less\n4. \"Ko'p\" → ✅ much / many | \"Ko'proq\" → ✅ more",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Far → farther → the farthest. Little → less → the least. Much/many → more → the most.\n\n🤖 TalkMe yakuniy: Zo'r! Endi noto'g'ri darajalarni bilasiz! Ertaga: A2 sifatlar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_140",
            topicId = "topic_10_a2_grammar",
            orderIndex = 30,
            titleEnglish = "140-Dars: A2 Adjectives Mastery & Part 7 Review",
            titleUzbek = "A2 Sifatlar — Takrorlash va Partiya 7 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun A2 sifatlarni yakunlaymiz! Hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA2 sifatlar — hammasi:\n• Qiyosiy: -er (bigger, smaller, happier), more (more interesting).\n• Orttirma: the -est (the biggest), the most (the most interesting).\n• Noto'g'ri: good→better→the best, bad→worse→the worst, far→farther→the farthest, little→less→the least, much→more→the most.\n• Than: qiyosiy bilan. | The: orttirma bilan.\n\n💬 MISOLLAR:\n• Qiyosiy: He is taller than me. This book is more interesting than that one.\n• Orttirma: He is the tallest. This is the most interesting book.\n• Noto'g'ri: This is better. This is the best. This is worse. This is the worst.\n\n✍️ MASHQ:\n1. \"U mendan balandroq\" → ✅ He is taller than me.\n2. \"Bu eng katta uy\" → ✅ This is the biggest house.\n3. \"Bu kitob unisidan qiziqroq\" → ✅ This book is more interesting than that one.\n4. \"Bu eng qiziq kitob\" → ✅ This is the most interesting book.\n5. \"Bu unisidan yaxshiroq\" → ✅ This is better than that one.\n6. \"Bu eng yaxshi kitob\" → ✅ This is the best book.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A2 sifatlar va Partiya 7 (121–140 darslar) tugadi! Qiyosiy (-er/more), orttirma (the -est/the most), noto'g'ri (good/better/best, bad/worse/worst).\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz A2 sifatlarni to'liq o'zlashtirdingiz! Partiya 7 tugadi! Keyingi bo'lim — Partiya 8: A2 Olmoshlar, Ravishlar va Fe'llar! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 6 — DARS 101–120: GAP TUZILISHI VA A2 SONLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_09_where", "topic_09_sentences_a1", "sec_09_106", "Where", "qayerda, qayerga", "Where do you live?", "Siz qayerda yashaysiz?", isStudied = false),
        VocabularyEntity("voc_09_when", "topic_09_sentences_a1", "sec_09_106", "When", "qachon", "When do you come?", "Siz qachon kelasiz?", isStudied = false),
        VocabularyEntity("voc_09_open", "topic_09_sentences_a1", "sec_09_107", "Open", "ochmoq", "Open the door, please!", "Iltimos, eshikni oching!", isStudied = false),
        VocabularyEntity("voc_09_sit", "topic_09_sentences_a1", "sec_09_107", "Sit", "o'tirmoq", "Sit down, please.", "Iltimos, o'tiring.", isStudied = false),
        VocabularyEntity("voc_09_listen", "topic_09_sentences_a1", "sec_09_107", "Listen", "tinglamoq", "Listen to me.", "Meni tinglang.", isStudied = false),
        VocabularyEntity("voc_09_how", "topic_09_sentences_a1", "sec_09_109", "How", "qanday, qanchalik", "How beautiful!", "Qanday chiroyli!", isStudied = false),
        VocabularyEntity("voc_10_thirty", "topic_10_a2_grammar", "sec_10_111", "Thirty", "o'ttiz (30)", "There are thirty students.", "O'ttizta talaba bor.", isStudied = false),
        VocabularyEntity("voc_10_forty", "topic_10_a2_grammar", "sec_10_111", "Forty", "qirq (40)", "He is forty years old.", "U qirq yoshda.", isStudied = false),
        VocabularyEntity("voc_10_fifty", "topic_10_a2_grammar", "sec_10_112", "Fifty", "ellik (50)", "I have fifty books.", "Menda ellikta kitob bor.", isStudied = false),
        VocabularyEntity("voc_10_sixty", "topic_10_a2_grammar", "sec_10_112", "Sixty", "oltmish (60)", "There are sixty minutes in an hour.", "Bir soatda oltmish daqiqa bor.", isStudied = false),
        VocabularyEntity("voc_10_seventy", "topic_10_a2_grammar", "sec_10_112", "Seventy", "yetmish (70)", "He is seventy years old.", "U yetmish yoshda.", isStudied = false),
        VocabularyEntity("voc_10_eighty", "topic_10_a2_grammar", "sec_10_112", "Eighty", "sakson (80)", "She is eighty years old.", "U sakson yoshda.", isStudied = false),
        VocabularyEntity("voc_10_ninety", "topic_10_a2_grammar", "sec_10_112", "Ninety", "to'qson (90)", "There are ninety pages.", "To'qson bet bor.", isStudied = false),
        VocabularyEntity("voc_10_hundred", "topic_10_a2_grammar", "sec_10_114", "Hundred", "yuz (100)", "Two hundred students study here.", "Bu yerda ikki yuzta talaba o'qiydi.", isStudied = false),
        VocabularyEntity("voc_10_thousand", "topic_10_a2_grammar", "sec_10_115", "Thousand", "ming (1,000)", "A thousand people came.", "Ming kishi keldi.", isStudied = false),
        VocabularyEntity("voc_10_million", "topic_10_a2_grammar", "sec_10_115", "Million", "million (1,000,000)", "Millions of people speak English.", "Millionlab odamlar inglizcha gapiradi.", isStudied = false),
        VocabularyEntity("voc_10_half", "topic_10_a2_grammar", "sec_10_118", "Half", "yarim", "A half of the apple.", "Olmaning yarmi.", isStudied = false),
        VocabularyEntity("voc_10_quarter", "topic_10_a2_grammar", "sec_10_118", "Quarter", "chorak (to'rtdan bir)", "A quarter of an hour.", "Chorak soat.", isStudied = false),
        VocabularyEntity("voc_10_dollar", "topic_10_a2_grammar", "sec_10_119", "Dollar", "dollar", "This book is ten dollars.", "Bu kitob o'n dollar.", isStudied = false),
        VocabularyEntity("voc_10_pound", "topic_10_a2_grammar", "sec_10_119", "Pound", "funt sterling", "I have twenty pounds.", "Menda yigirma funt bor.", isStudied = false),

        // =========================================================================
        // PARTIYA 7 — DARS 121–140: A2 OTLAR VA A2 SIFATLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_10_capital", "topic_10_a2_grammar", "sec_10_127", "Capital", "poytaxt", "The capital of Uzbekistan is Tashkent.", "O'zbekistonning poytaxti — Toshkent.", isStudied = false),
        VocabularyEntity("voc_10_roof", "topic_10_a2_grammar", "sec_10_127", "Roof", "tom", "The roof of the house is red.", "Uyning tomi qizil.", isStudied = false),
        VocabularyEntity("voc_10_than", "topic_10_a2_grammar", "sec_10_131", "Than", "-ga qaraganda, -dan", "He is taller than me.", "U mendan balandroq.", isStudied = false),
        VocabularyEntity("voc_10_interesting", "topic_10_a2_grammar", "sec_10_135", "Interesting", "qiziq, qiziqarli", "This is the most interesting book.", "Bu eng qiziq kitob.", isStudied = false),
        VocabularyEntity("voc_10_beautiful", "topic_10_a2_grammar", "sec_10_135", "Beautiful", "chiroyli, go'zal", "She is more beautiful than her sister.", "U opasidan chiroyliroq.", isStudied = false),
        VocabularyEntity("voc_10_difficult", "topic_10_a2_grammar", "sec_10_135", "Difficult", "qiyin", "This exercise is more difficult.", "Bu mashq qiyinroq.", isStudied = false),
        VocabularyEntity("voc_10_expensive", "topic_10_a2_grammar", "sec_10_136", "Expensive", "qimmat", "This is the most expensive car.", "Bu eng qimmat mashina.", isStudied = false),
        VocabularyEntity("voc_10_better", "topic_10_a2_grammar", "sec_10_137", "Better", "yaxshiroq (good qiyosiy shakli)", "This book is better than that one.", "Bu kitob unisidan yaxshiroq.", isStudied = false),
        VocabularyEntity("voc_10_best", "topic_10_a2_grammar", "sec_10_137", "Best", "eng yaxshi (good orttirma shakli)", "He is the best student.", "U eng yaxshi talaba.", isStudied = false),
        VocabularyEntity("voc_10_worse", "topic_10_a2_grammar", "sec_10_138", "Worse", "yomonroq (bad qiyosiy shakli)", "The weather is worse today.", "Bugun ob-havo yomonroq.", isStudied = false),
        VocabularyEntity("voc_10_worst", "topic_10_a2_grammar", "sec_10_138", "Worst", "eng yomon (bad orttirma shakli)", "This is the worst weather.", "Bu eng yomon havo.", isStudied = false),
        VocabularyEntity("voc_10_far", "topic_10_a2_grammar", "sec_10_139", "Far", "uzoq (farther, the farthest)", "This shop is farther than that one.", "Bu do'kon unisidan uzoqroq.", isStudied = false),
        VocabularyEntity("voc_10_less", "topic_10_a2_grammar", "sec_10_139", "Less", "ozroq, kamroq (little qiyosiy shakli)", "I have less time than you.", "Menda sizdan kamroq vaqt bor.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // =========================================================================
        // PARTIYA 6 — DARS 101–120 GRAMMAR RULES
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_09_101",
            topicId = "topic_09_sentences_a1",
            sectionId = "sec_09_101",
            titleUzbek = "Ingliz tilida gap turlari: Darak, So'roq, Buyruq va Undov",
            explanationUzbek = "Darak gap: Ega + Kesim + To'ldiruvchi + Hol (I live in Tashkent). Umumiy so'roq: Do/Does/Am/Is/Are + ega? Maxsus so'roq: Where/What/When + yordamchi fe'l + ega? Buyruq: Open! / Don't open! Undov: What a beautiful day! / How beautiful!",
            exampleEnglish = "Where do you live? Open the door! What a beautiful day!",
            exampleUzbek = "Siz qayerda yashaysiz? Eshikni oching! Qanday chiroyli kun!",
            associatedVocabularyIdsCsv = "voc_09_where,voc_09_when,voc_09_open,voc_09_sit,voc_09_listen,voc_09_how",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_10_111",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_111",
            titleUzbek = "A2 Sonlar: O'nliklar (-ty), Yuz/Ming/Million, Kasrlar va Sanalar",
            explanationUzbek = "O'nlik va birlik orasiga chiziqcha qo'yiladi (twenty-one). Aniq son oldida hundred, thousand, million -s olmaydi (two hundred, three thousand), faqat 'yuzlab/minglab' ma'nosida hundreds of / thousands of bo'ladi. Kasrlar: a half, a third, a quarter, two thirds.",
            exampleEnglish = "Two hundred students. Thousands of books. The twenty-first of May.",
            exampleUzbek = "Ikki yuzta talaba. Minglab kitoblar. Yigirma birinchi may.",
            associatedVocabularyIdsCsv = "voc_10_thirty,voc_10_forty,voc_10_fifty,voc_10_hundred,voc_10_thousand,voc_10_million,voc_10_half,voc_10_quarter",
            isStudied = false,
            isMastered = false
        ),

        // =========================================================================
        // PARTIYA 7 — DARS 121–140 GRAMMAR RULES
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_10_121",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_121",
            titleUzbek = "A2 Otlar: Qaratqich kelishigi ('s, ') va of predlogi",
            explanationUzbek = "Birlikdagi otlar va noto'g'ri ko'pliklar (children, men, women) ga 's qo'shiladi (Ali's book, the children's toys). -s bilan tugagan ko'pliklarga faqat apostrof (') qo'shiladi (the boys' books). Jonsiz otlar uchun of ishlatiladi (the leg of the table).",
            exampleEnglish = "Ali's book, the boys' books, the children's toys, the capital of Uzbekistan.",
            exampleUzbek = "Alining kitobi, bolalarning kitoblari, bolalarning o'yinchoqlari, O'zbekistonning poytaxti.",
            associatedVocabularyIdsCsv = "voc_10_capital,voc_10_roof",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_10_129",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_129",
            titleUzbek = "A2 Sifat darajalari: Qiyosiy (-er / more), Orttirma (the -est / the most) va Noto'g'ri sifatlar",
            explanationUzbek = "Qisqa sifatlar: -er than / the -est (bigger than, the biggest). Ko'p bo'g'inli sifatlar: more ... than / the most ... (more interesting, the most beautiful). Noto'g'ri sifatlar: good → better → the best, bad → worse → the worst, far → farther → the farthest, little → less → the least.",
            exampleEnglish = "He is taller than me. This is the most interesting book. She is the best student.",
            exampleUzbek = "U mendan balandroq. Bu eng qiziq kitob. U eng yaxshi talaba.",
            associatedVocabularyIdsCsv = "voc_10_than,voc_10_interesting,voc_10_beautiful,voc_10_better,voc_10_best,voc_10_worse,voc_10_worst",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // =========================================================================
        // PARTIYA 6 — DARS 101–120 EXERCISES
        // =========================================================================
        ExerciseEntity(
            id = "ex_09_106",
            topicId = "topic_09_sentences_a1",
            sectionId = "sec_09_106",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Siz qayerda yashaysiz?\" maxsus so'roq gapini to'g'ri tuzing:",
            subPromptText = "106-Dars: So'roq so'z + do/does + ega + fe'l?",
            audioTextToSpeak = "Where do you live?",
            optionsPipeSeparated = "Where do you live?|Where you live?|Where are you live?|Do where you live?",
            correctAnswer = "Where do you live?",
            explanationUzbek = "Maxsus so'roqda so'roq so'zdan (Where) keyin yordamchi fe'l (do), ega (you) va fe'l (live) keladi.",
            vocabularyIdsCsv = "voc_09_where",
            grammarRuleIdsCsv = "gram_09_101"
        ),
        ExerciseEntity(
            id = "ex_09_109",
            topicId = "topic_09_sentences_a1",
            sectionId = "sec_09_109",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Qanday chiroyli kun!\" undov gapini ingliz tilida tanlang:",
            subPromptText = "109-Dars: What a + sifat + birlikdagi ot!",
            audioTextToSpeak = "What a beautiful day!",
            optionsPipeSeparated = "What a beautiful day!|How a beautiful day!|What beautiful day!|How day beautiful!",
            correctAnswer = "What a beautiful day!",
            explanationUzbek = "Birlikdagi sanaladigan ot (day) qatnashgan undov gapda \"What a + sifat + ot!\" ishlatiladi.",
            vocabularyIdsCsv = "voc_09_how",
            grammarRuleIdsCsv = "gram_09_101"
        ),
        ExerciseEntity(
            id = "ex_10_114",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_114",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Ikki yuzta talaba\" va \"yuzlab kitoblar\" birikmalarini to'g'ri tanlang:",
            subPromptText = "114-Dars: Aniq son oldida hundred -s olmaydi",
            audioTextToSpeak = "two hundred students, hundreds of books",
            optionsPipeSeparated = "two hundred students, hundreds of books|two hundreds students, hundred of books|two hundreds of students, two hundred books|two hundred student, hundreds books",
            correctAnswer = "two hundred students, hundreds of books",
            explanationUzbek = "Aniq son bilan \"two hundred students\" (-s yo'q), \"yuzlab\" ma'nosida \"hundreds of books\" ishlatiladi.",
            vocabularyIdsCsv = "voc_10_hundred",
            grammarRuleIdsCsv = "gram_10_111"
        ),
        ExerciseEntity(
            id = "ex_10_118",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_118",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Yarim\" (1/2) va \"uchdan ikki\" (2/3) kasr sonlarini toping:",
            subPromptText = "118-Dars: Kasr sonlar",
            audioTextToSpeak = "a half, two thirds",
            optionsPipeSeparated = "a half, two thirds|one second, two third|a quarter, three seconds|a half, two three",
            correctAnswer = "a half, two thirds",
            explanationUzbek = "1/2 — a half, 2/3 — two thirds (surat 1 dan katta bo'lsa maxrajga -s qo'shiladi).",
            vocabularyIdsCsv = "voc_10_half",
            grammarRuleIdsCsv = "gram_10_111"
        ),

        // =========================================================================
        // PARTIYA 7 — DARS 121–140 EXERCISES
        // =========================================================================
        ExerciseEntity(
            id = "ex_10_126",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_126",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"O'g'il bolalarning kitoblari\" va \"bolalarning o'yinchoqlari\" birikmalarini tanlang:",
            subPromptText = "125–126 Darslar: Ko'plik s' va noto'g'ri ko'plik 's",
            audioTextToSpeak = "the boys' books, the children's toys",
            optionsPipeSeparated = "the boys' books, the children's toys|the boys's books, the childrens' toys|the boy's books, the children toys|the boys books, the childrens's toys",
            correctAnswer = "the boys' books, the children's toys",
            explanationUzbek = "-s bilan tugagan ko'plikka faqat apostrof (boys'), noto'g'ri ko'plikka 's (children's) qo'shiladi.",
            vocabularyIdsCsv = "voc_10_capital",
            grammarRuleIdsCsv = "gram_10_121"
        ),
        ExerciseEntity(
            id = "ex_10_135",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_135",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Bu kitob unisidan qiziqroq\" gapining to'g'ri tarjimasini toping:",
            subPromptText = "135-Dars: Ko'p bo'g'inli sifatlar qiyosiy darajasi",
            audioTextToSpeak = "This book is more interesting than that one.",
            optionsPipeSeparated = "This book is more interesting than that one.|This book is interestinger than that one.|This book is the most interesting than that one.|This book is more interesting that one.",
            correctAnswer = "This book is more interesting than that one.",
            explanationUzbek = "Ko'p bo'g'inli sifatlarda \"more + sifat + than\" ishlatiladi: \"more interesting than\".",
            vocabularyIdsCsv = "voc_10_interesting,voc_10_than",
            grammarRuleIdsCsv = "gram_10_129"
        ),
        ExerciseEntity(
            id = "ex_10_140",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_140",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"good\" va \"bad\" sifatlarining qiyosiy va orttirma darajalarini tanlang:",
            subPromptText = "137–140 Darslar: Noto'g'ri sifat darajalari",
            audioTextToSpeak = "good, better, the best; bad, worse, the worst",
            optionsPipeSeparated = "better / the best, worse / the worst|gooder / the goodest, badder / the baddest|more good / the most good, more bad / the most bad|better / the goodest, worser / the worst",
            correctAnswer = "better / the best, worse / the worst",
            explanationUzbek = "good → better → the best; bad → worse → the worst.",
            vocabularyIdsCsv = "voc_10_better,voc_10_best,voc_10_worse,voc_10_worst",
            grammarRuleIdsCsv = "gram_10_129"
        )
    )
}
