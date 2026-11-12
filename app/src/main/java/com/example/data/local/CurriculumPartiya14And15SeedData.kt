package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 14 — DARS 261–280 (TO'LIQ):
 * - B1 QO'SHMA GAPLAR VA KO'CHIRMA GAP (Dars 261–270, topic_11_b1_perfect_passive, orderIndex 71..80)
 * - B2 FE'L: DAVOM ZAMONLAR (Dars 271–280, topic_12_b2_modals_nonfinite, orderIndex 1..10)
 *
 * PARTIYA 15 — DARS 281–300 (TO'LIQ):
 * - B2 MODAL FE'LLAR VA INFINITIV (Dars 281–300, topic_12_b2_modals_nonfinite, orderIndex 11..30)
 */
object CurriculumPartiya14And15SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 14 — B1 QO'SHMA GAPLAR VA KO'CHIRMA GAP (Dars 261–270, topic_11_b1_perfect_passive)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_11_261",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 71,
            titleEnglish = "261-Dars: Compound Sentences — And & But",
            titleUzbek = "B1 Qo'shma gap — Bog'langan: and va but",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun B1 qo'shma gaplarni boshlaymiz! Bog'langan qo'shma gap — teng huquqli gaplar! 🚀",
            explanationUzbek = "📖 QOIDA:\nBog'langan qo'shma gap (Compound Sentence) ikki yoki undan ortiq teng huquqli sodda gaplardan iborat bo'lib, and (va), but (lekin), or (yoki), so (shuning uchun) bilan bog'lanadi.\n\n💬 MISOLLAR:\n• The signal was given, and the steamer moved slowly from the dock. — Signal berildi va paroxod dokdan sekin jo'nadi.\n• I came home early, but he remained to the end of the concert. — Men uyga erta qaytdim, ammo u konsertning oxirigacha qoldi.\n• I am tired, but I am happy. — Men charchadim, lekin baxtliman.\n\n✍️ MASHQ:\n1. \"Men charchadim, lekin baxtliman\" → ✅ I am tired, but I am happy.\n2. \"U qattiq o'qidi va imtihondan o'tdi\" → ✅ She studied hard, and she passed the exam.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bog'langan qo'shma gap — 2+ teng huquqli sodda gap (and, but, or, so).\n\n🤖 TalkMe yakuniy: Zo'r! Endi bog'langan qo'shma gapni bilasiz! Ertaga: or va so. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_262",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 72,
            titleEnglish = "262-Dars: Compound Sentences — Or & So",
            titleUzbek = "B1 Qo'shma gap — Bog'langan: or va so",
            objectiveUzbek = "🤖 TalkMe: Bugun or (yoki, aks holda) va so (shuning uchun) bilan bog'langan qo'shma gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Or — \"yoki / aks holda\": Hurry up, or you will miss the train (Shoshiling, aks holda poyezdga kechikasiz).\n• So — \"shuning uchun\" (natija): It was raining, so we stayed at home.\n\n💬 MISOLLAR:\n• Give me a pen, or I can't write. — Menga ruchka bering, aks holda yoza olmayman.\n• Hurry up, or you will miss the train. — Shoshiling, aks holda poyezdga kechikasiz.\n• He studied hard, so he passed the exam. — U qattiq o'qidi, shuning uchun imtihondan o'tdi.\n\n✍️ MASHQ:\n1. \"Shoshiling, aks holda poyezdga kechikasiz\" → ✅ Hurry up, or you will miss the train.\n2. \"U qattiq o'qidi, shuning uchun imtihondan o'tdi\" → ✅ He studied hard, so he passed the exam.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Or — yoki / aks holda. So — shuning uchun.\n\n🤖 TalkMe yakuniy: Zo'r! Endi or/so ni bilasiz! Ertaga: ergashgan qo'shma gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_263",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 73,
            titleEnglish = "263-Dars: Complex Sentences (Principal + Subordinate)",
            titleUzbek = "B1 Qo'shma gap — Ergashgan: Bosh + Ergash gap",
            objectiveUzbek = "🤖 TalkMe: Endi ergashgan qo'shma gapni (Complex Sentence) o'rganamiz! Bosh gap + ergash gap! 🚀",
            explanationUzbek = "📖 QOIDA:\nErgashgan qo'shma gap Bosh gap (Principal Clause) va Ergash gapdan (Subordinate Clause) iborat. Ergash gap bosh gapni izohlab keladi (that, if, when, because, after bilan).\n\n💬 MISOLLAR:\n• He thought that the train arrived at 6.15. — U poyezd 6.15 da kelgan deb o'yladi.\n• He will get the letter tomorrow if you send it off now. — Agar uni hozir jo'natsangiz, u xatni ertaga oladi.\n• After the agreement had been signed, the delegation left London. — Bitim imzolangandan keyin delegatsiya Londonni tark etdi.\n\n✍️ MASHQ:\n1. \"Men uning band ekanligini bilaman\" → ✅ I know that he is busy.\n2. \"U kelganida men o'qiyotgan edim\" → ✅ When he came, I was reading.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ergashgan qo'shma gap = Bosh gap + Ergash gap (that, if, when, after).\n\n🤖 TalkMe yakuniy: Zo'r! Endi ergashgan qo'shma gapni bilasiz! Ertaga: bog'lovchisiz ergashgan gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_264",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 74,
            titleEnglish = "264-Dars: Contact Clauses (Omitting Conjunctions)",
            titleUzbek = "B1 Qo'shma gap — Bog'lovchisiz ergashgan gap",
            objectiveUzbek = "🤖 TalkMe: Bugun bog'lovchisiz ergashgan qo'shma gapni (that/which/whom tushirilishi) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo'ldiruvchi va aniqlovchi ergash gaplarda that, which, whom bog'lovchilari ko'pincha tushirib qoldiriladi:\n• He said (that) he would come in the evening.\n• The book (which) you gave me is very interesting.\n\n💬 MISOLLAR:\n• He said he would come in the evening. — U kechqurun kelishini aytdi.\n• The book you gave me is very interesting. — Siz menga bergan kitob juda qiziq.\n• The man we met yesterday is my uncle. — Kecha biz uchratgan kishi mening amakim.\n\n✍️ MASHQ:\n1. \"Siz menga bergan kitob juda qiziq\" → ✅ The book you gave me is very interesting.\n2. \"Men uning haqligini bilaman\" → ✅ I know he is right.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To'ldiruvchi va aniqlovchi ergash gapda that/which/whom tushirilishi mumkin.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bog'lovchisiz ergashganni bilasiz! Ertaga: qo'shma gaplar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_265",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 75,
            titleEnglish = "265-Dars: Compound & Complex Sentences Review",
            titleUzbek = "B1 Qo'shma gaplar — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun B1 qo'shma gaplarning barcha turlarini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bog'langan: and, but, or, so (I am tired, but I am happy).\n• Ergashgan: bosh + ergash (I know that he will come).\n• Bog'lovchisiz: that/which/whom tushiriladi (The book you gave me is interesting).\n\n💬 MISOLLAR:\n• I am tired, but I am happy.\n• I know that he will come.\n• The book you gave me is very interesting.\n\n✍️ MASHQ:\n1. \"Men charchadim, lekin baxtliman\" → ✅ I am tired, but I am happy.\n2. \"Siz menga bergan kitob juda qiziq\" → ✅ The book you gave me is very interesting.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bog'langan, ergashgan va bog'lovchisiz qo'shma gaplar mustahkamlandi!\n\n🤖 TalkMe yakuniy: Ajoyib! Qo'shma gaplar mustahkamlandi! Ertaga: qo'shma gap yakuniy. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_266",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 76,
            titleEnglish = "266-Dars: Mixed Compound-Complex Sentences",
            titleUzbek = "B1 Qo'shma gap — Yakuniy amaliyot",
            objectiveUzbek = "🤖 TalkMe: Bugun aralash qo'shma gaplarni o'rganamiz va qo'shma gaplar bo'limini yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBitta gapda ham ergash gap, ham bog'langan gap qatnashishi mumkin:\n• He said (that) he would come, but he didn't. — U kelishini aytdi, lekin kelmadi.\n\n💬 MISOLLAR:\n• I came home, and she cooked dinner.\n• I know that he will come.\n• He said he would come, but he didn't.\n\n✍️ MASHQ:\n1. \"U kelishini aytdi, lekin kelmadi\" → ✅ He said he would come, but he didn't.\n2. \"Men uyga keldim va u kechki ovqat pishirdi\" → ✅ I came home, and she cooked dinner.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B1 qo'shma gaplar to'liq tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B1 qo'shma gaplarni to'liq o'zlashtirdingiz! Ertaga: B1 ko'chirma gap — darak gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_267",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 77,
            titleEnglish = "267-Dars: Reported Speech — Statements",
            titleUzbek = "B1 Ko'chirma gap — Darak gap (He said that...)",
            objectiveUzbek = "🤖 TalkMe: Endi ko'chirma va o'zlashtirma gapni (Reported Speech) boshlaymiz! Darak gap: He said that... 🚀",
            explanationUzbek = "📖 QOIDA:\nKo'chirma gapni o'zlashtirma gapga aylantirishda:\n1) Vergul va qo'shtirnoq tushiriladi, that qo'shiladi.\n2) Olmoshlar ma'noga qarab o'zgaradi (I → he/she).\n3) Bosh gap o'tgan zamonda (said) bo'lsa, zamon bir pog'ona o'tgan zamonga suriladi (am → was, will → would, have → had).\n\n💬 MISOLLAR:\n• He said, \"I am busy.\" → He said (that) he was busy.\n• She said, \"I have finished.\" → She said (that) she had finished.\n• He said, \"I will come.\" → He said (that) he would come.\n\n✍️ MASHQ:\n1. He said, \"I am busy.\" → ✅ He said (that) he was busy.\n2. He said, \"I will come.\" → ✅ He said (that) he would come.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Darak o'zlashtirma gap: He said (that) + olmosh va zamon o'zgarishi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi darak gapni o'zlashtirmaga aylantirishni bilasiz! Ertaga: so'roq gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_268",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 78,
            titleEnglish = "268-Dars: Reported Speech — Questions",
            titleUzbek = "B1 Ko'chirma gap — So'roq gap (He asked if / where...)",
            objectiveUzbek = "🤖 TalkMe: Bugun so'roq gapni o'zlashtirma gapga aylantirishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSo'roq gap o'zlashtirmaga aylanganda SO'ROQ TARTIBI DARAK TARTIBIGA (ega + kesim) aylanadi:\n• Maxsus so'roq: so'roq so'z saqlanadi: He asked, \"Where do they live?\" → He asked where they lived.\n• Umumiy so'roq: if yoki whether qo'shiladi: He asked, \"Do you speak English?\" → He asked if I spoke English.\n\n💬 MISOLLAR:\n• He asked me, \"Where do they live?\" → He asked me where they lived.\n• He asked me, \"Do you speak English?\" → He asked me if I spoke English.\n• He asked me, \"Are you busy?\" → He asked me if I was busy.\n\n✍️ MASHQ:\n1. He asked me, \"Where do they live?\" → ✅ He asked me where they lived.\n2. He asked me, \"Are you busy?\" → ✅ He asked me if I was busy.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'zlashtirma so'roqda tartib darak bo'ladi (ega + kesim), umumiy so'roqda if/whether qo'shiladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi so'roqni o'zlashtirmaga aylantirishni bilasiz! Ertaga: buyruq gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_269",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 79,
            titleEnglish = "269-Dars: Reported Speech — Commands & Requests",
            titleUzbek = "B1 Ko'chirma gap — Buyruq va Iltimos (told/asked to...)",
            objectiveUzbek = "🤖 TalkMe: Bugun buyruq va iltimos gaplarni o'zlashtirma gapga aylantirishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBuyruq gapda say fe'li tell/order (buyruq) yoki ask (iltimos) ga o'zgaradi, buyruq fe'li esa to + V (bo'lishli) yoki not to + V (bo'lishsiz) shakliga kiradi:\n• She said to Tom, \"Come at five.\" → She told Tom to come at five.\n• He said to me, \"Don't go there.\" → He told me not to go there.\n\n💬 MISOLLAR:\n• She told Tom to come at five o'clock. — U Tomga soat beshda kelishni aytdi.\n• He told me not to go there. — U menga u yerga bormaslikni aytdi.\n• I asked Ann to bring me a glass of water. — Men Anndan bir stakan suv olib kelishni iltimos qildim.\n\n✍️ MASHQ:\n1. He said to me, \"Open the door.\" → ✅ He told me to open the door.\n2. He said to me, \"Don't go there.\" → ✅ He told me not to go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Buyruq o'zlashtirma gap: told/asked + kim + (not) to + V.\n\n🤖 TalkMe yakuniy: Zo'r! Endi buyruqni o'zlashtirmaga aylantirishni bilasiz! Ertaga: B1 ko'chirma gap takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_270",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 80,
            titleEnglish = "270-Dars: B1 Reported Speech Review & B1 Completion",
            titleUzbek = "B1 Ko'chirma gap — Takrorlash va B1 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun B1 ko'chirma gapni takrorlaymiz va B1 bo'limini to'liq yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Darak: He said (that) he was busy.\n• Maxsus so'roq: He asked where they lived.\n• Umumiy so'roq: He asked if I spoke English.\n• Buyruq: He told me to come at five / not to go there.\n\n💬 MISOLLAR:\n• He said that he was busy.\n• He asked where they lived.\n• He asked if I spoke English.\n• He told me not to go there.\n\n✍️ MASHQ:\n1. He said, \"I am busy.\" → ✅ He said (that) he was busy.\n2. He said, \"Don't go there.\" → ✅ He told me not to go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B1 ko'chirma gap va B1 darajasi (191–270-darslar) to'liq yakunlandi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B1 darajasini to'liq o'zlashtirdingiz! Keyingi darsdan B2 (Upper-Intermediate) darajasini boshlaymiz! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 14 & 15 — B2 DAVOM ZAMONLAR, MODALLAR VA INFINITIV (Dars 271–300)
        // inside topic_12_b2_modals_nonfinite (orderIndex 1..30)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_12_271",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 1,
            titleEnglish = "271-Dars: B2 Past Continuous — I was reading",
            titleUzbek = "B2 Fe'l — Past Continuous: I was reading",
            objectiveUzbek = "🤖 TalkMe: B2 darajasiga xush kelibsiz! Bugun Past Continuous — o'tgan davom zamonni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPast Continuous — o'tgan zamondagi aniq bir vaqtda davom etayotgan harakat.\nYasalishi: was / were + fe'l-ing.\n• I/He/She/It → was reading | We/You/They → were reading.\n\n💬 MISOLLAR:\n• I was reading at five yesterday. — Men kecha soat beshda o'qiyotgan edim.\n• He was working when I called. — Men qo'ng'iroq qilganimda u ishlayotgan edi.\n• They were playing football at noon. — Ular peshinda futbol o'ynashayotgan edi.\n\n✍️ MASHQ:\n1. \"Men kecha soat beshda o'qiyotgan edim\" → ✅ I was reading at five yesterday.\n2. \"Men qo'ng'iroq qilganimda u ishlayotgan edi\" → ✅ He was working when I called.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Continuous = was/were + fe'l-ing. O'tgan zamondagi davom etgan harakat.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Past Continuous ni bilasiz! Ertaga: Past Continuous bo'lishsiz va so'roq. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_272",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 2,
            titleEnglish = "272-Dars: Past Continuous Negative & Questions",
            titleUzbek = "Past Continuous: Bo'lishsiz va So'roq (wasn't / Was he...?)",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Continuous ning bo'lishsiz va so'roq shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bo'lishsiz: wasn't / weren't + fe'l-ing (I wasn't reading).\n• So'roq: Was / Were + ega + fe'l-ing? (Was he reading? Were they playing?).\n\n💬 MISOLLAR:\n• I wasn't reading at 5. — Men soat 5 da o'qimayotgan edim.\n• They weren't playing football. — Ular futbol o'ynashmayotgan edi.\n• Was he reading? — U o'qiyotgan edimi? — Yes, he was.\n\n✍️ MASHQ:\n1. \"U ishlamayotgan edi\" → ✅ He wasn't working.\n2. \"U o'qiyotgan edimi?\" → ✅ Was he reading?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: wasn't/weren't + V-ing. So'roq: Was/Were + ega + V-ing?\n\n🤖 TalkMe yakuniy: Zo'r! Past Continuous ni to'liq bilasiz! Ertaga: Future Continuous. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_273",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 3,
            titleEnglish = "273-Dars: Future Continuous — will be reading",
            titleUzbek = "B2 Fe'l — Future Continuous: I will be reading",
            objectiveUzbek = "🤖 TalkMe: Bugun Future Continuous — kelasi davom zamonni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFuture Continuous — kelasi zamondagi aniq bir vaqtda davom etayotgan harakat.\nYasalishi: shall / will be + fe'l-ing.\n\n💬 MISOLLAR:\n• I shall be reading at five tomorrow. — Men ertaga soat beshda o'qiyotgan bo'laman.\n• He will be working when you come. — Siz kelganingizda u ishlayotgan bo'ladi.\n• They will be playing football at noon. — Ular peshinda futbol o'ynashayotgan bo'lishadi.\n\n✍️ MASHQ:\n1. \"Siz kelganingizda u ishlayotgan bo'ladi\" → ✅ He will be working when you come.\n2. Future Continuous formulasi? → ✅ will be + fe'l-ing",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Future Continuous = shall/will be + fe'l-ing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Future Continuous ni bilasiz! Ertaga: Future Continuous bo'lishsiz/so'roq. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_274",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 4,
            titleEnglish = "274-Dars: Future Continuous Negative & Questions",
            titleUzbek = "Future Continuous: Bo'lishsiz va So'roq (won't be / Will he be...?)",
            objectiveUzbek = "🤖 TalkMe: Bugun Future Continuous ning bo'lishsiz va so'roq shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bo'lishsiz: shan't / won't be + fe'l-ing (He won't be working).\n• So'roq: Shall / Will + ega + be + fe'l-ing? (Will he be working?).\n\n💬 MISOLLAR:\n• He won't be working when you come. — Siz kelganingizda u ishlayotgan bo'lmaydi.\n• Will he be working? — U ishlayotgan bo'ladimi? — Yes, he will.\n\n✍️ MASHQ:\n1. \"U ishlayotgan bo'lmaydi\" → ✅ He won't be working.\n2. \"U ishlayotgan bo'ladimi?\" → ✅ Will he be working?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: won't be + V-ing. So'roq: Will + ega + be + V-ing?\n\n🤖 TalkMe yakuniy: Zo'r! Future Continuous ni o'zlashtirdingiz! Ertaga: Present Perfect Continuous. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_275",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 5,
            titleEnglish = "275-Dars: Present Perfect Continuous (have been reading)",
            titleUzbek = "B2 Fe'l — Present Perfect Continuous: have/has been + V-ing",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Perfect Continuous — o'tgan zamonda boshlanib, hozir ham davom etayotgan zamonni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPresent Perfect Continuous = have / has been + fe'l-ing.\nO'tgan zamonda boshlanib, hozir ham davom etayotgan harakat (for va since bilan):\n• I have been waiting for two hours. — Men ikki soatdan buyon kutyapman.\n\n💬 MISOLLAR:\n• I have been waiting for two hours. — Men ikki soatdan buyon kutyapman.\n• He has been working since morning. — U ertalabdan buyon ishlayapti.\n• It has been raining since morning. — Ertalabdan buyon yomg'ir yog'ayapti.\n\n✍️ MASHQ:\n1. \"Men ikki soatdan buyon kutyapman\" → ✅ I have been waiting for two hours.\n2. \"U ertalabdan buyon ishlayapti\" → ✅ He has been working since morning.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Present Perfect Continuous = have/has been + fe'l-ing (for / since bilan).\n\n🤖 TalkMe yakuniy: Zo'r! Present Perfect Continuous ni bilasiz! Ertaga: bo'lishsiz va so'roq shakli. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_276",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 6,
            titleEnglish = "276-Dars: Present Perfect Continuous Negative & Questions",
            titleUzbek = "Present Perfect Continuous: Bo'lishsiz va So'roq",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Perfect Continuous ning bo'lishsiz va so'roq shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bo'lishsiz: haven't / hasn't been + fe'l-ing (I haven't been waiting long).\n• So'roq: Have / Has + ega + been + fe'l-ing? (Have you been waiting long?).\n\n💬 MISOLLAR:\n• I haven't been waiting long. — Men uzoq kutayotganim yo'q.\n• Have you been waiting long? — Uzoq kutayapsizmi? — Yes, I have.\n• Has he been working? — U ishlayaptimi?\n\n✍️ MASHQ:\n1. \"Uzoq kutayapsizmi?\" → ✅ Have you been waiting long?\n2. \"Men uzoq kutayotganim yo'q\" → ✅ I haven't been waiting long.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: haven't/hasn't been + V-ing. So'roq: Have/Has + ega + been + V-ing?\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: Past Perfect Continuous. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_277",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 7,
            titleEnglish = "277-Dars: Past Perfect Continuous (had been reading)",
            titleUzbek = "B2 Fe'l — Past Perfect Continuous: had been + V-ing",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Perfect Continuous — o'tgan zamondagi biror vaqtdan oldin boshlanib, o'sha paytgacha davom etgan zamonni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPast Perfect Continuous = had been + fe'l-ing (barcha shaxslar uchun bir xil).\nO'tgan zamondagi ikkinchi harakatdan oldin ma'lum vaqt davom etgan ishni bildiradi:\n• I had been working for a long time when my brother came.\n\n💬 MISOLLAR:\n• I had been working for a long time when my brother came. — Akam kelganida men uzoq vaqt ishlayotgan edim.\n• It had been raining for two hours when I left home. — Men uydan chiqqanimda yomg'ir ikki soatdan beri yog'ayotgan edi.\n• He felt tired because he had been playing football. — U futbol o'ynagani uchun charchagan edi.\n\n✍️ MASHQ:\n1. \"Akam kelganida men uzoq vaqt ishlayotgan edim\" → ✅ I had been working for a long time when my brother came.\n2. Past Perfect Continuous formulasi? → ✅ had been + fe'l-ing",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Perfect Continuous = had been + fe'l-ing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Past Perfect Continuous ni bilasiz! Ertaga: bo'lishsiz va so'roq. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_278",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 8,
            titleEnglish = "278-Dars: Past Perfect Continuous Negative & Questions",
            titleUzbek = "Past Perfect Continuous: Bo'lishsiz va So'roq",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Perfect Continuous ning bo'lishsiz va so'roq shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bo'lishsiz: hadn't been + fe'l-ing (I hadn't been waiting long).\n• So'roq: Had + ega + been + fe'l-ing? (Had you been waiting long?).\n\n💬 MISOLLAR:\n• I hadn't been waiting long. — Men uzoq kutayotganim yo'q edi.\n• Had you been waiting long? — Uzoq kutayotgan edingizmi? — Yes, I had.\n\n✍️ MASHQ:\n1. \"Uzoq kutayotgan edingizmi?\" → ✅ Had you been waiting long?\n2. \"Men uzoq kutayotganim yo'q edi\" → ✅ I hadn't been waiting long.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: hadn't been + V-ing. So'roq: Had + ega + been + V-ing?\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: barcha davom zamonlar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_279",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 9,
            titleEnglish = "279-Dars: All Continuous Tenses Review",
            titleUzbek = "B2 Fe'l — Davom zamonlar takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun 6 ta davom zamonni birgalikda takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Present Continuous: am/is/are + V-ing\n• Past Continuous: was/were + V-ing\n• Future Continuous: will be + V-ing\n• Present Perfect Continuous: have/has been + V-ing\n• Past Perfect Continuous: had been + V-ing\n• Future Perfect Continuous: will have been + V-ing\n\n💬 MISOLLAR:\n• I am reading now.\n• I was reading at 5.\n• I will be reading at 5.\n• I have been reading for an hour.\n• I had been reading for an hour when he came.\n\n✍️ MASHQ:\n1. \"Men bir soatdan buyon o'qiyapman\" → ✅ I have been reading for an hour.\n2. \"Men soat 5 da o'qiyotgan edim\" → ✅ I was reading at 5.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 6 ta Continuous zamon tizimi mustahkamlandi!\n\n🤖 TalkMe yakuniy: Ajoyib! Davom zamonlar mustahkamlandi! Ertaga: Future Perfect Continuous + Partiya 14 yakuni. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_280",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 10,
            titleEnglish = "280-Dars: Future Perfect Continuous (will have been working)",
            titleUzbek = "Future Perfect Continuous + Partiya 14 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun Future Perfect Continuous ni o'rganamiz va Partiya 14 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFuture Perfect Continuous = shall/will have been + fe'l-ing.\nKelasi zamondagi biror vaqtga kelib ma'lum muddat davom etayotgan bo'ladigan harakat:\n• They will have been playing for two hours by 5 o'clock.\n\n💬 MISOLLAR:\n• I shall have been working for seven hours when you return. — Siz qaytganingizda men yetti soat ishlayotgan bo'laman.\n• They will have been playing for two hours by 5 o'clock. — Soat 5 gacha ular ikki soat o'ynayotgan bo'lishadi.\n\n✍️ MASHQ:\n1. \"Soat 5 gacha ular ikki soat o'ynayotgan bo'lishadi\" → ✅ They will have been playing for two hours by 5 o'clock.\n2. Future Perfect Continuous formulasi? → ✅ will have been + fe'l-ing",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Future Perfect Continuous = will have been + V-ing. Partiya 14 (261–280-darslar) tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B2 davom zamonlarni to'liq o'zlashtirdingiz! Ertaga: Partiya 15 — B2 Modal fe'llar! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 15 — B2 MODAL FE'LLAR VA INFINITIV (Dars 281–300, orderIndex 11..30)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_12_281",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 11,
            titleEnglish = "281-Dars: Modal Verb Can (Ability)",
            titleUzbek = "B2 Modal — Can: Qobiliyat va Imkoniyat",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun modal fe'llarni boshlaymiz! Can — \"qila olmoq\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nModal fe'llar asosiy fe'l bilan 'to' siz ishlatiladi va -s qo'shimchasini olmaydi.\n• Can — qobiliyat, imkoniyat: I can swim. He can speak English.\n• Bo'lishsiz: cannot (can't). So'roq: Can you help me?\n\n💬 MISOLLAR:\n• I can swim. — Men suza olaman.\n• He can speak English. — U inglizcha gapira oladi.\n• Can you come tomorrow? — Ertaga kela olasizmi?\n• I can't come today. — Men bugun kela olmayman.\n\n✍️ MASHQ:\n1. \"U inglizcha gapira oladi\" → ✅ He can speak English.\n2. Can dan keyin 'to' qo'yiladimi? → ✅ Yo'q, to siz keladi.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Can — qobiliyat va imkoniyat (to siz, -s siz).\n\n🤖 TalkMe yakuniy: Zo'r! Endi can ni bilasiz! Ertaga: could va was/were able to. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_282",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 12,
            titleEnglish = "282-Dars: Could vs Was/Were Able To",
            titleUzbek = "B2 Modal — Could va Was/Were able to",
            objectiveUzbek = "🤖 TalkMe: Bugun could (umumiy o'tgan qobiliyat) va was/were able to (amalda bajarilgan ish) farqini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Could — o'tgan zamondagi umumiy qobiliyat: I could swim when I was young.\n• Was/were able to — aniq bir vaziyatda muvaffaqiyatli bajarilgan ish: We were able to finish the work yesterday.\n\n💬 MISOLLAR:\n• I could swim when I was young. — Men yoshligimda suza olardim.\n• We were able to finish the work yesterday. — Biz kecha ishni tugata oldik.\n• He was able to translate the article without a dictionary. — U maqolani lug'atsiz tarjima qila oldi.\n\n✍️ MASHQ:\n1. \"Men yoshligimda suza olardim\" → ✅ I could swim when I was young.\n2. \"Biz kecha ishni tugata oldik\" → ✅ We were able to finish the work yesterday.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Could — umumiy qobiliyat. Was/were able to — aniq bir marta amalga oshirilgan harakat.\n\n🤖 TalkMe yakuniy: Zo'r! Endi could va was able to farqini bilasiz! Ertaga: may — ruxsat. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_283",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 13,
            titleEnglish = "283-Dars: Modal Verb May & Allowed To",
            titleUzbek = "B2 Modal — May (Ruxsat) va Was allowed to",
            objectiveUzbek = "🤖 TalkMe: Bugun may — rasmiy ruxsat va was/were allowed to ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• May — rasmiy ruxsat: May I come in? You may take my dictionary.\n• O'tgan zamonda ruxsat berilganini aytish uchun was/were allowed to ishlatiladi: He was allowed to go there.\n\n💬 MISOLLAR:\n• May I come in? — Kirsam mumkinmi?\n• You may take my dictionary. — Siz mening lug'atimni olishingiz mumkin.\n• You may not smoke here. — Bu yerda chekishingiz mumkin emas.\n• He was allowed to go there. — Unga u yerga borishga ruxsat berishdi.\n\n✍️ MASHQ:\n1. \"Kirsam mumkinmi?\" → ✅ May I come in?\n2. \"Unga u yerga borishga ruxsat berishdi\" → ✅ He was allowed to go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: May — ruxsat (hozirgi zamon). O'tgan zamonda — was/were allowed to.\n\n🤖 TalkMe yakuniy: Zo'r! Endi may ni bilasiz! Ertaga: might — ehtimollik. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_284",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 14,
            titleEnglish = "284-Dars: Modal Verb Might (Possibility)",
            titleUzbek = "B2 Modal — Might: Ehtimollik",
            objectiveUzbek = "🤖 TalkMe: Bugun might — kamroq ehtimollik va taxmin modal fe'lini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMight — hozirgi yoki kelasi zamondagi ehtimollikni (taxminni) bildiradi:\n• He might come tomorrow. — U ertaga kelishi mumkin (ehtimol).\n• It might rain today. — Bugun yomg'ir yog'ishi mumkin.\n\n💬 MISOLLAR:\n• He might come tomorrow. — U ertaga kelishi mumkin.\n• She might know his address. — U uning manzilini bilishi mumkin.\n• It might rain today. — Bugun yomg'ir yog'ishi mumkin.\n\n✍️ MASHQ:\n1. \"U ertaga kelishi mumkin\" → ✅ He might come tomorrow.\n2. \"Bugun yomg'ir yog'ishi mumkin\" → ✅ It might rain today.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Might — ehtimollik (taxmin). Bo'lishsiz: might not.\n\n🤖 TalkMe yakuniy: Zo'r! Endi might ni bilasiz! Ertaga: must — zarurat. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_285",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 15,
            titleEnglish = "285-Dars: Modal Verb Must (Obligation & Prohibition)",
            titleUzbek = "B2 Modal — Must: Zarurat va Taqiq (mustn't)",
            objectiveUzbek = "🤖 TalkMe: Bugun must — qat'iy zarurat va mustn't — taqiqni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Must — kuchli zarurat, majburiyat: I must go now.\n• Mustn't — qat'iy taqiq (mumkin emas!): You mustn't smoke here.\n• \"Must I go?\" savoliga inkor javob: \"No, you needn't\" (Shart emas).\n\n💬 MISOLLAR:\n• I must go now. — Men hozir ketishim kerak.\n• You must do your homework. — Siz uy vazifangizni qilishingiz kerak.\n• You mustn't smoke here. — Bu yerda chekishingiz mumkin emas.\n• Must I go there? — No, you needn't.\n\n✍️ MASHQ:\n1. \"Bu yerda chekishingiz mumkin emas\" → ✅ You mustn't smoke here.\n2. \"Must I go?\" ga bo'lishsiz javob qanday? → ✅ No, you needn't.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Must — zarurat. Mustn't — taqiq. Inkor javob: No, you needn't.\n\n🤖 TalkMe yakuniy: Zo'r! Endi must ni bilasiz! Ertaga: should — maslahat. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_286",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 16,
            titleEnglish = "286-Dars: Modal Verb Should (Advice)",
            titleUzbek = "B2 Modal — Should: Maslahat va Tavsiya",
            objectiveUzbek = "🤖 TalkMe: Bugun should — maslahat va tavsiya modal fe'lini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nShould — maslahat, tavsiya (\"...kerak\"):\n• You should rest. — Siz dam olishingiz kerak.\n• You shouldn't go there. — Siz u yerga bormasligingiz kerak.\n• Should have + V3 — o'tgan zamonda bajarilmagan maslahat (afsus): He should have helped them.\n\n💬 MISOLLAR:\n• You should rest. — Siz dam olishingiz kerak.\n• You shouldn't go there. — Siz u yerga bormasligingiz kerak.\n• Should I call him? — Unga qo'ng'iroq qilishim kerakmi?\n• He should have helped them. — U ularga yordam berishi kerak edi.\n\n✍️ MASHQ:\n1. \"Siz dam olishingiz kerak\" → ✅ You should rest.\n2. \"Siz u yerga bormasligingiz kerak\" → ✅ You shouldn't go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Should — maslahat. Should have + P.P. — o'tmishda bajarilmagan ish.\n\n🤖 TalkMe yakuniy: Zo'r! Endi should ni bilasiz! Ertaga: ought to — axloqiy burch. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_287",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 17,
            titleEnglish = "287-Dars: Modal Verb Ought To (Moral Duty)",
            titleUzbek = "B2 Modal — Ought to: Axloqiy burch",
            objectiveUzbek = "🤖 TalkMe: Bugun ought to — axloqiy burch modal fe'lini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOught to — axloqiy burch va maslahat ('to' yuklamasi bilan ishlatiladi!):\n• You ought to help him. — Siz unga yordam berishingiz kerak.\n• You ought to have done it yesterday. — Siz buni kecha qilishingiz kerak edi.\n\n💬 MISOLLAR:\n• You ought to help him. — Siz unga yordam berishingiz kerak.\n• He ought to be more careful. — U ehtiyotkorroq bo'lishi kerak.\n• You ought to have done it yesterday. — Siz buni kecha qilishingiz kerak edi.\n\n✍️ MASHQ:\n1. \"Siz unga yordam berishingiz kerak\" → ✅ You ought to help him.\n2. Ought fe'li to bilan keladimi yoki to siz? → ✅ to bilan (ought to).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ought to — axloqiy burch (to bilan). Ought to have + P.P. — bajarilmagan burch.\n\n🤖 TalkMe yakuniy: Zo'r! Endi ought to ni bilasiz! Ertaga: need / needn't. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_288",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 18,
            titleEnglish = "288-Dars: Modal Need (needn't vs need to)",
            titleUzbek = "B2 Modal — Need (needn't va needn't have done)",
            objectiveUzbek = "🤖 TalkMe: Bugun need fe'lining modal (needn't) va asosiy fe'l (need to) shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Modal need (to siz, bo'lishsiz/so'roqda): You needn't go there (Borishingiz shart emas).\n• Asosiy fe'l need (to bilan): I need to go now.\n• Needn't have + P.P. — o'tgan zamonda kerak bo'lmasa ham qilingan ish: You needn't have come so early.\n\n💬 MISOLLAR:\n• You needn't go there. — U yerga borishingiz shart emas.\n• I need to go now. — Men hozir borishim kerak.\n• You needn't have come so early. — Bunchalik erta kelmasangiz ham bo'lardi.\n\n✍️ MASHQ:\n1. \"U yerga borishingiz shart emas\" → ✅ You needn't go there.\n2. \"Bunchalik erta kelmasangiz ham bo'lardi\" → ✅ You needn't have come so early.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Needn't + V (shart emas). Needn't have + P.P. (bekorga qilindi).\n\n🤖 TalkMe yakuniy: Zo'r! Endi need ni bilasiz! Ertaga: modal fe'llar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_289",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 19,
            titleEnglish = "289-Dars: Modal Verbs Review",
            titleUzbek = "B2 Modal fe'llar — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun barcha 8 ta modal fe'lni (can, could, may, might, must, should, ought to, need) takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Can / Could — qobiliyat\n• May / Might — ruxsat va ehtimollik\n• Must — zarurat va taqiq (mustn't)\n• Should / Ought to — maslahat va burch\n• Needn't — shart emaslik\n\n💬 MISOLLAR:\n• I can speak English.\n• May I come in?\n• I must go.\n• You should rest.\n• You needn't go.\n\n✍️ MASHQ:\n1. \"Siz dam olishingiz kerak\" (maslahat) → ✅ You should rest.\n2. \"Borishingiz shart emas\" → ✅ You needn't go.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Can, could, may, might, must, should, ought to, need takrorlandi!\n\n🤖 TalkMe yakuniy: Ajoyib! Modal fe'llar mustahkamlandi! Ertaga: modal fe'llar yakuniy. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_290",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 20,
            titleEnglish = "290-Dars: Modals Mastery & Perfect Infinitive Preview",
            titleUzbek = "B2 Modal fe'llar — Yakuniy",
            objectiveUzbek = "🤖 TalkMe: Bugun modal fe'llarni Perfect Infinitive (must have done, should have done) bilan yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nModal + have + P.P. (o'tgan zamonga nisbatan):\n• He must have done it. — U buni qilgan bo'lishi kerak.\n• You should have done it. — Siz buni qilishingiz kerak edi.\n• You needn't have come. — Kelmasangiz ham bo'lardi.\n\n💬 MISOLLAR:\n• He must have done it.\n• You should have done it.\n• You needn't have come.\n\n✍️ MASHQ:\n1. \"U buni qilgan bo'lishi kerak\" → ✅ He must have done it.\n2. \"Siz buni qilishingiz kerak edi\" → ✅ You should have done it.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Modal fe'llar bo'limi to'liq tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B2 modal fe'llarni to'liq o'zlashtirdingiz! Ertaga: B2 Infinitiv! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_291",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 21,
            titleEnglish = "291-Dars: Infinitive of Purpose (to go, to help)",
            titleUzbek = "B2 Infinitiv — to go (Maqsad va Fe'ldan keyin)",
            objectiveUzbek = "🤖 TalkMe: Endi infinitivni boshlaymiz! To go — \"bormoq\", I came to help — \"yordam bergani keldim\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nInfinitiv (to + V) — fe'lning shaxsi noma'lum shakli.\n1) Maqsad: I came to help you.\n2) Fe'ldan keyin: I want to go home. She decided to study.\n3) Sifatdan keyin: I am happy to see you.\n\n💬 MISOLLAR:\n• I came to help you. — Men sizga yordam bergani keldim.\n• I want to go home. — Men uyga bormoqchiman.\n• She decided to study English. — U ingliz tilini o'rganishga qaror qildi.\n• To learn English is important. — Ingliz tilini o'rganish muhim.\n\n✍️ MASHQ:\n1. \"Men sizga yordam bergani keldim\" → ✅ I came to help you.\n2. \"U ingliz tilini o'rganishga qaror qildi\" → ✅ She decided to study English.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Infinitiv = to + V (maqsad, fe'l va sifatdan keyin).\n\n🤖 TalkMe yakuniy: Zo'r! Endi infinitivni bilasiz! Ertaga: to siz infinitiv (make, let). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_292",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 22,
            titleEnglish = "292-Dars: Bare Infinitive after Make & Let",
            titleUzbek = "B2 Infinitiv — To siz (make, let)",
            objectiveUzbek = "🤖 TalkMe: Bugun make (majbur qilmoq) va let (ruxsat bermoq) dan keyin to siz infinitivni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMake, let, see, watch, hear, feel fe'llaridan keyin to'ldiruvchi kelganda infinitiv 'to' siz ishlatiladi:\n• He made me read this book. — U meni bu kitobni o'qishga majbur qildi.\n• I let him go there. — Men unga u yerga borishga ruxsat berdim.\n\n💬 MISOLLAR:\n• He made me read this book. — U meni bu kitobni o'qishga majbur qildi.\n• I let him go there. — Men unga u yerga borishga ruxsat berdim.\n• Help me (to) do it. — Buni qilishga menga yordam bering.\n\n✍️ MASHQ:\n1. \"U meni bu kitobni o'qishga majbur qildi\" → ✅ He made me read this book.\n2. \"Men unga u yerga borishga ruxsat berdim\" → ✅ I let him go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Make va let + kim + V (to siz!).\n\n🤖 TalkMe yakuniy: Zo'r! Endi to siz infinitivni bilasiz! Ertaga: see/watch/hear sezgi fe'llari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_293",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 23,
            titleEnglish = "293-Dars: Bare Infinitive vs -ing after See, Watch, Hear",
            titleUzbek = "B2 Infinitiv — see / watch / hear",
            objectiveUzbek = "🤖 TalkMe: Bugun sezgi fe'llari (see, watch, hear) bilan to siz infinitiv va -ing farqini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Sezgi fe'li + kim + V (to siz) — tugallangan harakat: I saw her leave the room.\n• Sezgi fe'li + kim + V-ing — davom etayotgan jarayon: I saw her leaving the room.\n\n💬 MISOLLAR:\n• I saw her leave the room. — Men uni xonadan chiqqanini ko'rdim (tugallangan).\n• I saw her leaving the room. — Men uni xonadan chiqayotganini ko'rdim (jarayon).\n• I heard him sing a song. — Men uning qo'shiq kuylaganini eshitdim.\n\n✍️ MASHQ:\n1. \"Men uni xonadan chiqqanini ko'rdim\" (tugallangan) → ✅ I saw her leave the room.\n2. \"Men uni xonadan chiqayotganini ko'rdim\" (jarayon) → ✅ I saw her leaving the room.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: See/watch/hear + kim + V (tugallangan) yoki V-ing (jarayon).\n\n🤖 TalkMe yakuniy: Zo'r! Endi sezgi fe'llarini bilasiz! Ertaga: infinitiv shakllari — to ask. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_294",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 24,
            titleEnglish = "294-Dars: Simple Infinitive Active (to ask)",
            titleUzbek = "B2 Infinitiv shakllari — to ask (Simple)",
            objectiveUzbek = "🤖 TalkMe: Endi infinitivning 6 ta shaklini boshlaymiz! Birinchisi: Simple Infinitive Active (to ask)! 🚀",
            explanationUzbek = "📖 QOIDA:\nInfinitivning 6 shakli bor:\n1) Simple Active: to ask\n2) Continuous Active: to be asking\n3) Perfect Active: to have asked\n4) Perfect Continuous: to have been asking\n5) Simple Passive: to be asked\n6) Perfect Passive: to have been asked.\nSimple Infinitive (to ask) kesimdagi harakat bilan bir vaqtda yoki kelgusida bo'ladigan harakatni bildiradi.\n\n💬 MISOLLAR:\n• I am glad to see you. — Sizni ko'rishdan xursandman.\n• I have a lot of work to do. — Menda qiladigan ko'p ishim bor.\n\n✍️ MASHQ:\n1. \"Sizni ko'rishdan xursandman\" → ✅ I am glad to see you.\n2. \"Menda qiladigan ko'p ishim bor\" → ✅ I have a lot of work to do.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Simple Infinitive = to + V (bir vaqtda sodir bo'luvchi harakat).\n\n🤖 TalkMe yakuniy: Zo'r! Endi simple infinitivni bilasiz! Ertaga: to be asking — continuous infinitiv. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_295",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 25,
            titleEnglish = "295-Dars: Continuous Infinitive (to be asking)",
            titleUzbek = "B2 Infinitiv shakllari — to be asking (Continuous)",
            objectiveUzbek = "🤖 TalkMe: Bugun Continuous Infinitive — to be asking shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nContinuous Infinitive Active = to be + V-ing.\nGapning kesimidagi harakat bilan bir vaqtda davom etayotgan jarayonni bildiradi:\n• The weather seems to be improving. — Ob-havo yaxshilanayotganga o'xshaydi.\n\n💬 MISOLLAR:\n• The weather seems to be improving. — Ob-havo yaxshilanayotganga o'xshaydi.\n• He is said to be writing a new play. — Aytishlaricha u yangi pyesa yozayotgan ekan.\n• The water seems to be boiling. — Suv qaynayotganga o'xshaydi.\n\n✍️ MASHQ:\n1. \"Ob-havo yaxshilanayotganga o'xshaydi\" → ✅ The weather seems to be improving.\n2. Continuous Infinitive formulasi? → ✅ to be + fe'l-ing",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Continuous Infinitive = to be + V-ing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi continuous infinitivni bilasiz! Ertaga: to have asked — perfect infinitiv. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_296",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 26,
            titleEnglish = "296-Dars: Perfect Infinitive (to have asked)",
            titleUzbek = "B2 Infinitiv shakllari — to have asked (Perfect)",
            objectiveUzbek = "🤖 TalkMe: Bugun Perfect Infinitive — to have asked shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPerfect Infinitive Active = to have + Past Participle (V3).\nGapning kesimidagi harakatdan OLDIN sodir bo'lgan harakatni bildiradi:\n• He seems to have finished his work. — U ishini tugatganga o'xshaydi.\n\n💬 MISOLLAR:\n• He seems to have finished his work. — U ishini tugatganga o'xshaydi.\n• This writer is said to have written a new novel. — Aytishlaricha bu yozuvchi yangi roman yozgan.\n• He must have forgotten about it. — U buni unutgan bo'lishi kerak.\n\n✍️ MASHQ:\n1. \"U ishini tugatganga o'xshaydi\" → ✅ He seems to have finished his work.\n2. Perfect Infinitive formulasi? → ✅ to have + P.P.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Perfect Infinitive = to have + P.P. (kesimdan oldin sodir bo'lgan harakat).\n\n🤖 TalkMe yakuniy: Zo'r! Endi perfect infinitivni bilasiz! Ertaga: modal + perfect infinitiv. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_297",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 27,
            titleEnglish = "297-Dars: Modals with Perfect Infinitive (must have done)",
            titleUzbek = "B2 Perfect Infinitiv — Modal fe'llar bilan",
            objectiveUzbek = "🤖 TalkMe: Bugun modal fe'llar + have + P.P. (o'tgan zamon taxmini va afsusi) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• must have done — \"...gan bo'lishi kerak\" (kuchli taxmin)\n• may/might have done — \"...gan bo'lishi mumkin\"\n• should/ought to have done — \"...ishi kerak edi\" (bajarilmagan)\n• could have done — \"...a olardi\" (bajarilmagan imkoniyat)\n• needn't have done — \"...masa ham bo'lardi\" (bekorga qilindi)\n\n💬 MISOLLAR:\n• He must have forgotten it. — U buni unutgan bo'lishi kerak.\n• He may have gone to London. — U Londonga ketgan bo'lishi mumkin.\n• You should have done it. — Siz buni qilishingiz kerak edi.\n• You could have helped him. — Siz unga yordam bera olardingiz.\n\n✍️ MASHQ:\n1. \"U buni unutgan bo'lishi kerak\" → ✅ He must have forgotten it.\n2. \"Siz unga yordam bera olardingiz\" → ✅ You could have helped him.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Modal + have + P.P. — o'tgan zamondagi taxmin yoki amalga oshmagan ish.\n\n🤖 TalkMe yakuniy: Zo'r! Endi modal + perfect infinitivni bilasiz! Ertaga: passive infinitiv — to be asked. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_298",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 28,
            titleEnglish = "298-Dars: Passive Infinitive (to be asked / to have been asked)",
            titleUzbek = "B2 Infinitiv Passive — to be asked",
            objectiveUzbek = "🤖 TalkMe: Bugun Passive Infinitive — to be asked va to have been asked ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Simple Infinitive Passive = to be + P.P. (to be invited — taklif qilinmoq).\n• Perfect Infinitive Passive = to have been + P.P. (to have been appointed — tayinlangan bo'lmoq).\n\n💬 MISOLLAR:\n• He has a great desire to be invited to the party. — U o'zini ziyofatga taklif qilishlarini juda istaydi.\n• I want to be informed of her arrival. — Menga uning kelganini xabar qilishlarini istayman.\n• He is said to have been appointed director. — Aytishlaricha u direktor etib tayinlangan.\n\n✍️ MASHQ:\n1. \"Menga uning kelganini xabar qilishlarini istayman\" → ✅ I want to be informed of her arrival.\n2. \"Aytishlaricha u direktor etib tayinlangan\" → ✅ He is said to have been appointed director.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Passive Infinitive = to be + P.P.; Perfect Passive = to have been + P.P.\n\n🤖 TalkMe yakuniy: Zo'r! Endi passive infinitivni bilasiz! Ertaga: infinitiv takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_299",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 29,
            titleEnglish = "299-Dars: All Infinitive Forms Review",
            titleUzbek = "B2 Infinitiv — Barcha shakllar takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun infinitivning barcha 6 shaklini, to siz infinitiv va modal + have + P.P. ni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Simple: to ask | Continuous: to be asking | Perfect: to have asked\n• Perfect Continuous: to have been asking\n• Simple Passive: to be asked | Perfect Passive: to have been asked\n• To siz: make, let, see, watch, hear, feel.\n\n💬 MISOLLAR:\n• I am glad to see you.\n• The weather seems to be improving.\n• He seems to have finished his work.\n• He wants to be invited.\n• He made me read this book.\n\n✍️ MASHQ:\n1. \"Ob-havo yaxshilanayotganga o'xshaydi\" → ✅ The weather seems to be improving.\n2. \"U ishini tugatganga o'xshaydi\" → ✅ He seems to have finished his work.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Infinitivning 6 shakli va to siz infinitiv mustahkamlandi!\n\n🤖 TalkMe yakuniy: Ajoyib! Infinitiv mustahkamlandi! Ertaga: 300-dars — yubiley dars! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_300",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 30,
            titleEnglish = "300-Dars: Milestone 300! Infinitive & Modals Mastery",
            titleUzbek = "300-Dars: B2 Infinitiv va Modallar Yakuni",
            objectiveUzbek = "🤖 TalkMe: TABRIKLAYMIZ! Bugun 300-DARS! Siz 400 ta darsning to'rtdan uch qismini (75%) zabt etdingiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPartiya 15 yakuni:\n• 8 ta modal fe'l (can, could, may, might, must, should, ought to, need)\n• Modal + have + P.P. (must have forgotten, should have done, needn't have come)\n• Infinitivning 6 shakli va make/let/see dan keyin to siz infinitiv.\n\n💬 MISOLLAR:\n• He must have forgotten it.\n• He seems to have finished his work.\n• He is said to have been appointed director.\n• He made me read this book.\n\n✍️ MASHQ:\n1. \"U buni unutgan bo'lishi kerak\" → ✅ He must have forgotten it.\n2. \"U meni bu kitobni o'qishga majbur qildi\" → ✅ He made me read this book.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 300-DARS MUBORAK! Partiya 15 (281–300-darslar) to'liq yakunlandi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! 300 ta dars ortda qoldi! Ertaga: Partiya 16 — Gerund, Sifatdosh va Complex Object! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity("voc_p14_01", "topic_11_b1_perfect_passive", "sec_11_261", "Signal", "signal, belgi", "The signal was given.", "Signal berildi.", isStudied = false),
        VocabularyEntity("voc_p14_02", "topic_11_b1_perfect_passive", "sec_11_261", "Dock", "dok, bandargoh", "The steamer moved slowly from the dock.", "Paroxod dokdan sekin jo'nadi.", isStudied = false),
        VocabularyEntity("voc_p14_03", "topic_11_b1_perfect_passive", "sec_11_263", "Agreement", "bitim, kelishuv", "After the agreement had been signed, they left.", "Bitim imzolangandan keyin ular jo'nab ketishdi.", isStudied = false),
        VocabularyEntity("voc_p14_04", "topic_11_b1_perfect_passive", "sec_11_263", "Delegation", "delegatsiya", "The delegation left London.", "Delegatsiya Londonni tark etdi.", isStudied = false),
        VocabularyEntity("voc_p15_01", "topic_12_b2_modals_nonfinite", "sec_12_282", "Dictionary", "lug'at", "He translated the article without a dictionary.", "U maqolani lug'atsiz tarjima qildi.", isStudied = false),
        VocabularyEntity("voc_p15_02", "topic_12_b2_modals_nonfinite", "sec_12_283", "Allowed", "ruxsat berilgan", "He was allowed to go there.", "Unga u yerga borishga ruxsat berishdi.", isStudied = false),
        VocabularyEntity("voc_p15_03", "topic_12_b2_modals_nonfinite", "sec_12_287", "Ought to", "kerak, lozim (burch)", "You ought to help him.", "Siz unga yordam berishingiz kerak.", isStudied = false),
        VocabularyEntity("voc_p15_04", "topic_12_b2_modals_nonfinite", "sec_12_292", "Shoulder", "yelka", "I felt him put his hand on my shoulder.", "Men uni qo'lini yelkamga qo'yganini his qildim.", isStudied = false),
        VocabularyEntity("voc_p15_05", "topic_12_b2_modals_nonfinite", "sec_12_295", "Negotiation", "muzokara", "The firm is conducting negotiations.", "Firma muzokaralar olib bormoqda.", isStudied = false),
        VocabularyEntity("voc_p15_06", "topic_12_b2_modals_nonfinite", "sec_12_296", "Novel", "roman", "This writer has written a new novel.", "Bu yozuvchi yangi roman yozdi.", isStudied = false),
        VocabularyEntity("voc_p15_07", "topic_12_b2_modals_nonfinite", "sec_12_298", "Desire", "uchqun istak, xohish", "He has a great desire to be invited.", "U taklif qilinishni juda istaydi.", isStudied = false),
        VocabularyEntity("voc_p15_08", "topic_12_b2_modals_nonfinite", "sec_12_298", "Director", "direktor, rahbar", "He was appointed director.", "U direktor etib tayinlandi.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "gr_11_267",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_267",
            titleUzbek = "O'zlashtirma gap (Reported Speech: Darak, So'roq, Buyruq)",
            explanationUzbek = "Darakda that, umumiy so'roqda if/whether (darak tartibda), buyruqda told/asked + (not) to + V ishlatiladi.",
            exampleEnglish = "He said he was busy. He asked if I spoke English. He told me not to go.",
            exampleUzbek = "U bandligini aytdi. U inglizcha gapirishimni so'radi. U bormasligimni aytdi.",
            associatedVocabularyIdsCsv = "voc_p14_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_12_275",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_275",
            titleUzbek = "Perfect Continuous Zamonlar (have/has/had/will have been + V-ing)",
            explanationUzbek = "Biror vaqtgacha ma'lum muddat davom etgan harakatni ifodalaydi (for va since bilan).",
            exampleEnglish = "I have been waiting for two hours. I had been working when he came.",
            exampleUzbek = "Men ikki soatdan buyon kutyapman. U kelganida men ishlayotgan edim.",
            associatedVocabularyIdsCsv = "voc_p15_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_12_297",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_297",
            titleUzbek = "Modal + Perfect Infinitive (must/should/could/needn't have + V3)",
            explanationUzbek = "must have V3 (taxmin), should have V3 (afsus/tanbeh), needn't have V3 (zarur bo'lmagan ish).",
            exampleEnglish = "He must have forgotten it. You should have helped him.",
            exampleUzbek = "U buni unutgan bo'lishi kerak. Siz unga yordam berishingiz kerak edi.",
            associatedVocabularyIdsCsv = "voc_p15_03,voc_p15_06",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_11_268",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_268",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "He asked me, \"Where do they live?\" gapining o'zlashtirma shaklini toping:",
            subPromptText = "268-Dars: O'zlashtirma so'roqda darak tartibi",
            audioTextToSpeak = "He asked me where they lived.",
            optionsPipeSeparated = "He asked me where they lived.|He asked me where did they live.|He asked me where do they live.|He asked me that where they lived.",
            correctAnswer = "He asked me where they lived.",
            explanationUzbek = "O'zlashtirma so'roqda yordamchi fe'l (do/did) tushib, darak tartibi (where they lived) bo'ladi.",
            vocabularyIdsCsv = "voc_p14_03",
            grammarRuleIdsCsv = "gr_11_267"
        ),
        ExerciseEntity(
            id = "ex_12_275",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_275",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"Men ikki soatdan buyon kutyapman\" (Present Perfect Continuous) gapini tanlang:",
            subPromptText = "275-Dars: have/has been + V-ing",
            audioTextToSpeak = "I have been waiting for two hours.",
            optionsPipeSeparated = "I have been waiting for two hours.|I am waiting for two hours.|I had been waiting since two hours.|I will be waiting for two hours.",
            correctAnswer = "I have been waiting for two hours.",
            explanationUzbek = "O'tmishda boshlanib hozirgacha davom etayotgan ish: have been waiting.",
            vocabularyIdsCsv = "voc_p15_01",
            grammarRuleIdsCsv = "gr_12_275"
        ),
        ExerciseEntity(
            id = "ex_12_292",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_292",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"U meni bu kitobni o'qishga majbur qildi\" gapining to'g'ri tarjimasini tanlang:",
            subPromptText = "292-Dars: make + kim + V (to siz)",
            audioTextToSpeak = "He made me read this book.",
            optionsPipeSeparated = "He made me read this book.|He made me to read this book.|He made to me read this book.|He let me to read this book.",
            correctAnswer = "He made me read this book.",
            explanationUzbek = "Make va let fe'llaridan keyin infinitiv 'to' siz keladi: made me read.",
            vocabularyIdsCsv = "voc_p15_04",
            grammarRuleIdsCsv = "gr_12_297"
        ),
        ExerciseEntity(
            id = "ex_12_297",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_297",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"U buni unutgan bo'lishi kerak\" (o'tgan zamondagi kuchli taxmin) gapini toping:",
            subPromptText = "297-Dars: must have + P.P.",
            audioTextToSpeak = "He must have forgotten it.",
            optionsPipeSeparated = "He must have forgotten it.|He should forget it.|He needn't have forgotten it.|He must forget it yesterday.",
            correctAnswer = "He must have forgotten it.",
            explanationUzbek = "O'tgan zamondagi kuchli taxmin 'must have + V3' bilan ifodalanadi.",
            vocabularyIdsCsv = "voc_p15_06",
            grammarRuleIdsCsv = "gr_12_297"
        )
    )
}
