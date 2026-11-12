package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 12 — DARS 221–240 (TO'LIQ):
 * - B1 FE'L (Perfect + Majhul), KO'RSATKICHLAR (little, few, all, both), ARTIKL (kasalliklar, school/bed)
 *   inside topic_11_b1_perfect_passive (orderIndex 31..50)
 *
 * PARTIYA 13 — DARS 241–260 (TO'LIQ):
 * - B1 PREDLOG (according to, because of, in spite of, instead of), BOG'LOVCHI (that, if, whether),
 *   GAP TUZILISHI (ega, kesim, to'ldiruvchi, aniqlovchi ergash gaplar, It, There is, It is...that)
 *   inside topic_11_b1_perfect_passive (orderIndex 51..70)
 */
object CurriculumPartiya12And13SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 12 — DARS 221–240 (topic_11_b1_perfect_passive, orderIndex 31..50)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_11_221",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 31,
            titleEnglish = "221-Dars: Present Perfect — just, already, yet",
            titleUzbek = "B1 Fe'l — Present Perfect: just, already, yet",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun Present Perfect ni just, already, yet bilan o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Just (jast) — \"hozirgina\" (have/has va V3 orasida): I have just finished.\n• Already (olredi) — \"allaqachon\" (have/has va V3 orasida): He has already left.\n• Yet (yet) — \"hali\" (faqat bo'lishsiz va so'roq gaplar oxirida): She hasn't come yet. Have you finished yet?\n\n💬 MISOLLAR:\n• I have just finished my work. — Men hozirgina ishimni tugatdim.\n• He has already left. — U allaqachon ketdi.\n• She hasn't come yet. — U hali kelmadi.\n• Have you finished yet? — Hali tugatdingizmi?\n\n✍️ MASHQ:\n1. \"Men hozirgina ishimni tugatdim\" → ✅ I have just finished my work.\n2. \"U hali kelmadi\" → ✅ She hasn't come yet.\n3. Yet qaysi gaplarda ishlatiladi? → ✅ Bo'lishsiz va so'roq gaplarda.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Just — hozirgina, Already — allaqachon (have/has + just/already + V3). Yet — hali (bo'lishsiz/so'roq oxirida).\n\n🤖 TalkMe yakuniy: Zo'r! Endi just/already/yet ni bilasiz! Ertaga: since va for. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_222",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 32,
            titleEnglish = "222-Dars: Present Perfect — since & for",
            titleUzbek = "B1 Fe'l — Present Perfect: since va for",
            objectiveUzbek = "🤖 TalkMe: Bugun since (-dan buyon) va for (davomida) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Since (sins) — \"-dan buyon\" (harakatning boshlanish nuqtasi): since 2020, since Monday, since childhood.\n• For (fo) — \"davomida\" (vaqt davomiyligi): for two hours, for five years, for a week.\n\n💬 MISOLLAR:\n• I have lived here since 2020. — Men 2020-yildan buyon shu yerda yashayman.\n• I have lived here for five years. — Men besh yildan buyon shu yerda yashayman.\n• We have known each other since childhood. — Biz bolalikdan buyon bir-birimizni taniymiz.\n\n✍️ MASHQ:\n1. \"Men 2020-yildan buyon shu yerda yashayman\" → ✅ I have lived here since 2020.\n2. \"Men besh yildan buyon shu yerda yashayman\" → ✅ I have lived here for five years.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Since — boshlanish nuqtasi (since 2020). For — vaqt davomiyligi (for five years).\n\n🤖 TalkMe yakuniy: Zo'r! Endi since/for ni bilasiz! Ertaga: Past Perfect — had done. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_223",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 33,
            titleEnglish = "223-Dars: Past Perfect — I had done",
            titleUzbek = "B1 Fe'l — Past Perfect: I had done",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Perfect — \"o'tgan zamondagi o'tgan\" zamonni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPast Perfect — o'tgan zamondagi biror vaqtdan yoki ikkinchi harakatdan oldin tugagan harakat.\nYasalishi: had + Past Participle (V3) — barcha shaxslar uchun bir xil!\n\n💬 MISOLLAR:\n• I had finished my work before he came. — U kelishidan oldin men ishimni tugatgan edim.\n• We had translated the article by five o'clock. — Biz soat beshgacha maqolani tarjima qilib bo'lgan edik.\n• The train had left when we got to the station. — Biz stansiyaga yetib kelganimizda poyezd ketgan edi.\n\n✍️ MASHQ:\n1. \"U kelishidan oldin men ishimni tugatgan edim\" → ✅ I had finished my work before he came.\n2. Past Perfect formulasi qanday? → ✅ had + V3 (P.P.)",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Perfect = had + P.P. O'tgan zamondagi biror vaqtdan yoki harakatdan oldin tugagan ish.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Past Perfect ni bilasiz! Ertaga: Past Perfect — after va before. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_224",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 34,
            titleEnglish = "224-Dars: Past Perfect with After & Before",
            titleUzbek = "B1 Fe'l — Past Perfect: after va before",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Perfect ni after va before bilan o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBirinchi sodir bo'lgan harakat — Past Perfect (had + V3), ikkinchi sodir bo'lgan harakat — Simple Past (V2):\n• After the sun had set, we decided to return home.\n• I had finished my work before he returned.\n\n💬 MISOLLAR:\n• After the sun had set, we decided to return home. — Quyosh botgandan keyin biz uyga qaytishga qaror qildik.\n• I had finished my work before he returned. — U qaytib kelishidan oldin men ishimni tugatgan edim.\n• After she had read the book, she gave it to me. — Kitobni o'qib bo'lgandan keyin u uni menga berdi.\n\n✍️ MASHQ:\n1. \"Quyosh botgandan keyin biz uyga qaytishga qaror qildik\" → ✅ After the sun had set, we decided to return home.\n2. \"U qaytib kelishidan oldin men ishimni tugatgan edim\" → ✅ I had finished my work before he returned.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: After + Past Perfect, Simple Past. Past Perfect + before + Simple Past.\n\n🤖 TalkMe yakuniy: Zo'r! Endi after/before ni bilasiz! Ertaga: hardly...when va no sooner...than. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_225",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 35,
            titleEnglish = "225-Dars: Hardly...when & No sooner...than",
            titleUzbek = "Past Perfect: Hardly ... when va No sooner ... than",
            objectiveUzbek = "🤖 TalkMe: Bugun hardly/scarcely...when va no sooner...than ni Past Perfect bilan o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Hardly / Scarcely ... when — \"...-masdan / ...ar-...mas\": Hardly had he entered the house when it started to rain.\n• No sooner ... than — \"...-masdan\": No sooner had he arrived than he fell ill.\nMuhim: Gap boshida kelsa inversiya bo'ladi (had egadan oldinga o'tadi): Hardly had he...\n\n💬 MISOLLAR:\n• Hardly had he entered the house when it started to rain. — U uyga kirar-kirmas yomg'ir yog'a boshladi.\n• No sooner had he arrived than he fell ill. — U yetib kelar-kelmas kasal bo'lib qoldi.\n• Scarcely had she sat down when the phone rang. — U o'tirar-o'tirmas telefon jiringladi.\n\n✍️ MASHQ:\n1. Hardly bilan qaysi bog'lovchi keladi? → ✅ when\n2. No sooner bilan qaysi bog'lovchi keladi? → ✅ than",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Hardly/Scarcely had + ega + V3 ... when; No sooner had + ega + V3 ... than.\n\n🤖 TalkMe yakuniy: Zo'r! Endi hardly/no sooner ni bilasiz! Ertaga: Future Perfect — will have done. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_226",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 36,
            titleEnglish = "226-Dars: Future Perfect — will have done",
            titleUzbek = "B1 Fe'l — Future Perfect: I will have done",
            objectiveUzbek = "🤖 TalkMe: Bugun Future Perfect — kelasi zamondagi biror vaqtdan oldin tugaydigan harakatni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFuture Perfect = shall/will have + Past Participle (V3).\nKelasi zamondagi biror vaqtdan oldin (by 5 o'clock, by tomorrow) tugaydigan harakatni bildiradi.\n\n💬 MISOLLAR:\n• I will have finished my work by 5 o'clock. — Men soat beshgacha ishimni tugatgan bo'laman.\n• She will have arrived by noon. — U peshingacha yetib kelgan bo'ladi.\n• He will have completed the project by the end of the month. — U loyihani oyning oxirigacha tugatgan bo'ladi.\n\n✍️ MASHQ:\n1. \"Men soat beshgacha ishimni tugatgan bo'laman\" → ✅ I will have finished my work by 5 o'clock.\n2. Future Perfect formulasi qanday? → ✅ will have + P.P.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Future Perfect = will have + P.P. Kelasi zamondagi biror muddatgacha (by...) tugaydigan harakat.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Future Perfect ni bilasiz! Ertaga: Perfect zamonlar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_227",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 37,
            titleEnglish = "227-Dars: Perfect Tenses Review",
            titleUzbek = "B1 Fe'l — Perfect zamonlar takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun uchala Perfect zamonni (Present, Past, Future Perfect) takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Present Perfect: have/has + P.P. — natija hozir (I have just finished).\n• Past Perfect: had + P.P. — o'tgan zamondagi o'tgan (I had finished before he came).\n• Future Perfect: will have + P.P. — kelasi vaqtdan oldin (I will have finished by 5).\n\n💬 MISOLLAR:\n• I have just finished. — Men hozirgina tugatdim.\n• I had finished before he came. — U kelishidan oldin men tugatgan edim.\n• I will have finished by 5. — Men soat 5 gacha tugatgan bo'laman.\n\n✍️ MASHQ:\n1. Present Perfect formulasi? → ✅ have/has + P.P.\n2. Past Perfect formulasi? → ✅ had + P.P.\n3. Future Perfect formulasi? → ✅ will have + P.P.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Present (have/has + V3), Past (had + V3), Future (will have + V3). Perfect zamonlar tugadi!\n\n🤖 TalkMe yakuniy: Ajoyib! Perfect zamonlar mustahkamlandi! Ertaga: majhul nisbat — is written. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_228",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 38,
            titleEnglish = "228-Dars: Present Simple Passive (is written)",
            titleUzbek = "B1 Fe'l — Majhul nisbat: The book is written",
            objectiveUzbek = "🤖 TalkMe: Endi majhul nisbatni (Passive Voice) boshlaymiz! The book is written — \"kitob yozilgan\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nMajhul nisbat (Passive Voice) ish-harakat kim tomonidan bajarilgani muhim bo'lmaganda ishlatiladi.\nYasalishi: to be + Past Participle (V3).\nPresent Simple Passive: am / is / are + P.P.\n\n💬 MISOLLAR:\n• The book is written in English. — Kitob ingliz tilida yozilgan.\n• The house is built of brick. — Uy g'ishtdan qurilgan.\n• English is spoken all over the world. — Ingliz tili butun dunyoda gapiriladi.\n• The letter is sent every day. — Xat har kuni yuboriladi.\n\n✍️ MASHQ:\n1. \"Kitob ingliz tilida yozilgan\" → ✅ The book is written in English.\n2. \"Ingliz tili butun dunyoda gapiriladi\" → ✅ English is spoken all over the world.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Majhul nisbat = to be + P.P. Present Simple: am/is/are + P.P.\n\n🤖 TalkMe yakuniy: Zo'r! Endi majhul nisbatni bilasiz! Ertaga: was written — Past majhul. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_229",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 39,
            titleEnglish = "229-Dars: Past Simple Passive (was/were written)",
            titleUzbek = "B1 Fe'l — Majhul: The book was written",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Simple majhul nisbatni o'rganamiz: was/were + P.P.! 🚀",
            explanationUzbek = "📖 QOIDA:\nPast Simple majhul: was / were + P.P.\n• Birlik: was + P.P. (The book was written).\n• Ko'plik: were + P.P. (The books were written).\n\n💬 MISOLLAR:\n• The book was written by Pushkin. — Kitob Pushkin tomonidan yozilgan.\n• The house was built in 1990. — Uy 1990-yilda qurilgan.\n• The letters were sent yesterday. — Xatlar kecha yuborilgan.\n• The window was broken by the boy. — Deraza bola tomonidan sindirilgan.\n\n✍️ MASHQ:\n1. \"Uy 1990-yilda qurilgan\" → ✅ The house was built in 1990.\n2. \"Xatlar kecha yuborilgan\" → ✅ The letters were sent yesterday.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Simple majhul = was/were + P.P. Was (birlik), were (ko'plik).\n\n🤖 TalkMe yakuniy: Zo'r! Endi Past majhulni bilasiz! Ertaga: by + kim — tomonidan. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_230",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 40,
            titleEnglish = "230-Dars: Passive Agent — by + person",
            titleUzbek = "B1 Fe'l — Majhul: by + kim (tomonidan)",
            objectiveUzbek = "🤖 TalkMe: Bugun majhul nisbatda bajaruvchini ko'rsatuvchi 'by + kim' (tomonidan) qoidasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMajhul nisbatda ish-harakatni bajaruvchi ko'rsatilsa — by predlogi bilan keladi:\n• The book was written by Pushkin. — Kitob Pushkin tomonidan yozilgan.\n• The house was built by my father. — Uy otam tomonidan qurilgan.\n\n💬 MISOLLAR:\n• The letter was sent by the secretary. — Xat kotiba tomonidan yuborilgan.\n• The song was sung by a famous singer. — Qo'shiq mashhur qo'shiqchi tomonidan kuylangan.\n• The cake was made by my mother. — Tort onam tomonidan qilingan.\n\n✍️ MASHQ:\n1. \"Uy otam tomonidan qurilgan\" → ✅ The house was built by my father.\n2. \"Xat kotiba tomonidan yuborilgan\" → ✅ The letter was sent by the secretary.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: By + kim — majhul nisbatda bajaruvchi (agent).\n\n🤖 TalkMe yakuniy: Zo'r! Endi by + kim ni bilasiz! Ertaga: majhul nisbat takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_231",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 41,
            titleEnglish = "231-Dars: Passive Voice Tenses Review",
            titleUzbek = "B1 Fe'l — Majhul nisbat takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun majhul nisbatning barcha asosiy zamonlarini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Present Passive: am/is/are + P.P. (The book is written)\n• Past Passive: was/were + P.P. (The book was written)\n• Future Passive: will be + P.P. (The book will be written)\n• Perfect Passive: have/has been + P.P. (The book has been written)\n\n💬 MISOLLAR:\n• The book is written. — Kitob yoziladi.\n• The book was written by Pushkin. — Kitob Pushkin tomonidan yozilgan.\n• The book will be written tomorrow. — Kitob ertaga yoziladi.\n• The book has been written. — Kitob yozib bo'lindi.\n\n✍️ MASHQ:\n1. \"Kitob ertaga yoziladi\" → ✅ The book will be written tomorrow.\n2. \"Kitob yozib bo'lindi\" (natija) → ✅ The book has been written.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Majhul: is/was/will be/has been + P.P. By + kim.\n\n🤖 TalkMe yakuniy: Ajoyib! Majhul nisbat mustahkamlandi! Ertaga: Passive Infinitive (to be done). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_232",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 42,
            titleEnglish = "232-Dars: Passive Infinitive (to be done / must be done)",
            titleUzbek = "B1 Fe'l — Passive Infinitive (to be done)",
            objectiveUzbek = "🤖 TalkMe: Bugun majhul infinitivni o'rganamiz: to be done / must be done! 🚀",
            explanationUzbek = "📖 QOIDA:\nMajhul infinitiv (Passive Infinitive) — to be + P.P. Modal fe'llardan keyin to tushib qoladi: modal + be + P.P.\n• The work must be done at once. — Ish darhol bajarilishi kerak.\n• The letter can be sent tomorrow. — Xat ertaga yuborilishi mumkin.\n\n💬 MISOLLAR:\n• The work must be done at once. — Ish darhol bajarilishi kerak.\n• The letter can be sent tomorrow. — Xat ertaga yuborilishi mumkin.\n• The house should be built next year. — Uy kelasi yil qurilishi kerak.\n• I want to be invited to the party. — Men ziyofatga taklif qilinishimni istayman.\n\n✍️ MASHQ:\n1. \"Ish darhol bajarilishi kerak\" → ✅ The work must be done at once.\n2. \"Xat ertaga yuborilishi mumkin\" → ✅ The letter can be sent tomorrow.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Majhul infinitiv = to be + P.P. Modal + be + P.P. (must be done).\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B1 fe'llarni (Perfect + Majhul) to'liq o'zlashtirdingiz! Ertaga: B1 ko'rsatkichlar — little/few. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_233",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 43,
            titleEnglish = "233-Dars: Determiners — little vs a little",
            titleUzbek = "B1 Ko'rsatkich — little va a little (sanalmaydigan)",
            objectiveUzbek = "🤖 TalkMe: Endi B1 ko'rsatkichlarni boshlaymiz! Little (oz, yetarli emas) va a little (ozroq, yetarli)! 🚀",
            explanationUzbek = "📖 QOIDA:\nFaqat sanalmaydigan otlar bilan:\n• Little — \"oz, kam\" (yetarli emas): I have little time.\n• A little — \"ozroq, biroz\" (yetarli): I have a little time.\n\n💬 MISOLLAR:\n• I have little time. — Mening oz vaqtim bor (yetarli emas).\n• She has little money. — Uning oz puli bor.\n• I have a little time. — Menda ozroq vaqt bor (yetarli).\n• Give me a little water. — Menga ozroq suv bering.\n\n✍️ MASHQ:\n1. \"Mening oz vaqtim bor (yetarli emas)\" → ✅ I have little time.\n2. \"Menga ozroq suv bering\" → ✅ Give me a little water.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Little — oz (sanalmaydigan, yetarli emas). A little — ozroq (yetarli).\n\n🤖 TalkMe yakuniy: Zo'r! Endi little ni bilasiz! Ertaga: few va a few. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_234",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 44,
            titleEnglish = "234-Dars: Determiners — few vs a few",
            titleUzbek = "B1 Ko'rsatkich — few va a few (sanaladigan)",
            objectiveUzbek = "🤖 TalkMe: Bugun few (kam) va a few (bir nechta) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFaqat ko'plikdagi sanaladigan otlar bilan:\n• Few — \"kam, oz\" (yetarli emas): I have few friends.\n• A few — \"bir nechta\" (yetarli): I have a few friends.\n\n💬 MISOLLAR:\n• I have few friends. — Mening kam do'stlarim bor (yetarli emas).\n• There are few students in the class. — Sinfda kam talabalar bor.\n• I have a few friends. — Mening bir nechta do'stlarim bor.\n• Give me a few books. — Menga bir nechta kitob bering.\n\n✍️ MASHQ:\n1. \"Mening kam do'stlarim bor\" → ✅ I have few friends.\n2. \"Menga bir nechta kitob bering\" → ✅ Give me a few books.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Few — kam (sanaladigan, yetarli emas). A few — bir nechta (yetarli).\n\n🤖 TalkMe yakuniy: Zo'r! Endi few ni bilasiz! Ertaga: all — hamma. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_235",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 45,
            titleEnglish = "235-Dars: Determiner — All (hamma)",
            titleUzbek = "B1 Ko'rsatkich — All (hamma, barcha)",
            objectiveUzbek = "🤖 TalkMe: Bugun all — \"hamma\" ko'rsatkichini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAll — \"hamma, barcha\". Ko'plikdagi va sanalmaydigan otlar bilan keladi: all students, all water. All dan keyin the, my, his kelishi mumkin: all the students, all my books.\n\n💬 MISOLLAR:\n• All students passed the exam. — Hamma talabalar imtihondan o'tdi.\n• All my books are new. — Mening hamma kitoblarim yangi.\n• I have read all the books. — Men hamma kitoblarni o'qidim.\n• He spent all his money. — U hamma pulini sarfladi.\n\n✍️ MASHQ:\n1. \"Hamma talabalar imtihondan o'tdi\" → ✅ All students passed the exam.\n2. \"Mening hamma kitoblarim yangi\" → ✅ All my books are new.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: All — hamma. Ko'plik va sanalmaydigan otlar bilan: All + the/my/his + ot.\n\n🤖 TalkMe yakuniy: Zo'r! Endi all ni bilasiz! Ertaga: both — ikkalasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_236",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 46,
            titleEnglish = "236-Dars: Determiner — Both (ikkalasi)",
            titleUzbek = "B1 Ko'rsatkich — Both (ikkalasi) + Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun both — \"ikkalasi\" ni o'rganamiz va B1 ko'rsatkichlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBoth — \"ikkalasi, har ikkalasi\". Ikki shaxs yoki narsa haqida, ko'plik fe'l bilan: Both brothers are doctors. Both my books are new.\n\n💬 MISOLLAR:\n• Both brothers are doctors. — Ikkala aka-uka ham shifokor.\n• Both my books are new. — Mening ikkala kitobim ham yangi.\n• Both the students passed. — Ikkala talaba ham o'tdi.\n• Both of them are here. — Ularning ikkalasi ham bu yerda.\n\n✍️ MASHQ:\n1. \"Ikkala aka-uka ham shifokor\" → ✅ Both brothers are doctors.\n2. Both bilan fe'l birlikdami yoki ko'plikda? → ✅ Ko'plikda (are).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Both — ikkalasi (ko'plik fe'l bilan). Little, few, all, both tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B1 ko'rsatkichlarni to'liq o'zlashtirdingiz! Ertaga: B1 artikl — kasalliklar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_237",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 47,
            titleEnglish = "237-Dars: Articles with Diseases (flu, pneumonia)",
            titleUzbek = "B1 Artikl — Kasalliklar: flu, pneumonia",
            objectiveUzbek = "🤖 TalkMe: Endi B1 artiklni boshlaymiz! Kasallik nomlari oldida artikl! 🚀",
            explanationUzbek = "📖 QOIDA:\nKasallik nomlari ko'pincha artiklsiz ishlatiladi: flu (gripp), pneumonia (pnevmoniya), measles (qizamiq), cholera (vabo), diabetes (qand kasalligi), cancer.\n• He has flu. — U gripp bilan kasal.\n\n💬 MISOLLAR:\n• He has flu. — U gripp bilan kasal.\n• She has measles. — U qizamiq bilan kasal.\n• He had pneumonia last year. — U o'tgan yili pnevmoniya bilan kasal edi.\n• She is suffering from diabetes. — U qand kasalligidan azob chekadi.\n\n✍️ MASHQ:\n1. \"U gripp bilan kasal\" → ✅ He has flu.\n2. \"U qizamiq bilan kasal\" → ✅ She has measles.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Kasalliklar ko'pincha artiklsiz: flu, measles, pneumonia, diabetes.\n\n🤖 TalkMe yakuniy: Zo'r! Endi kasalliklar oldida artiklni bilasiz! Ertaga: a cold, a headache. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_238",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 48,
            titleEnglish = "238-Dars: Articles — measles vs a cold / a headache",
            titleUzbek = "B1 Artikl — Kasalliklar: measles, a cold, a headache",
            objectiveUzbek = "🤖 TalkMe: Bugun measles va oddiy negizlik holatlari (a bad cold, a headache) ni mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Ilmiy va tibbiy kasallik nomlari — artiklsiz: flu, measles, mumps (tepki), tuberculosis.\n• Oddiy so'zlashuvdagi holatlar — a/an bilan: a bad cold (qattiq shamollash), a headache (bosh og'rig'i).\n\n💬 MISOLLAR:\n• He has measles. — U qizamiq bilan kasal.\n• She has mumps. — U tepki bilan kasal.\n• He had a bad cold. — U qattiq shamolladi.\n• She has a headache. — Uning boshi og'riyapti.\n\n✍️ MASHQ:\n1. \"U qattiq shamolladi\" → ✅ He had a bad cold.\n2. \"Uning boshi og'riyapti\" → ✅ She has a headache.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Kasalliklar — artiklsiz (flu, measles, mumps). Oddiy holatlar — a/an (a cold, a headache).\n\n🤖 TalkMe yakuniy: Zo'r! Kasalliklar mustahkamlandi! Ertaga: go to school / go to bed. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_239",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 49,
            titleEnglish = "239-Dars: Zero Article — school, bed, hospital",
            titleUzbek = "B1 Artikl — go to school, go to bed, in hospital",
            objectiveUzbek = "🤖 TalkMe: Bugun school, bed, hospital, prison so'zlari bilan artikl qoidasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSchool, bed, prison, hospital, church — o'sha joyning asosiy vazifasi (mashg'ulot) ma'nosida ARTIKLSIZ ishlatiladi:\n• go to school (o'qishga bormoq), go to bed (uxlashga yotmoq), in hospital (davolanishda).\nAgar BINO yoki MEBEL ma'nosida bo'lsa — the qo'yiladi: go to the school (maktab binosiga).\n\n💬 MISOLLAR:\n• She goes to school. — U maktabga (o'qishga) boradi.\n• I go to bed at 10. — Men soat 10 da yotaman.\n• She went to the school to meet the teacher. — U o'qituvchi bilan uchrashish uchun maktab binosiga bordi.\n\n✍️ MASHQ:\n1. \"U maktabga (o'qishga) boradi\" → ✅ She goes to school.\n2. \"Men soat 10 da yotaman\" → ✅ I go to bed at 10.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: School, bed, prison, hospital — mashg'ulot ma'nosida artiklsiz, bino ma'nosida the bilan.\n\n🤖 TalkMe yakuniy: Zo'r! Endi har xil holatlarni bilasiz! Ertaga: in bed vs on the bed + Partiya 12 yakuni. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_240",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 50,
            titleEnglish = "240-Dars: In bed vs On the bed & B1 Articles Review",
            titleUzbek = "B1 Artikl — in bed + Partiya 12 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun in bed va on the bed farqini mustahkamlaymiz hamda Partiya 12 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• In bed — \"uxlab yotmoq\" (artiklsiz): He is in bed.\n• On the bed — \"karavot ustida\" (mebel, the bilan): The cat is on the bed.\n\n💬 MISOLLAR:\n• He is in bed. — U uxlab yotibdi.\n• The cat is on the bed. — Mushuk karavotda.\n• She has flu. — U gripp bilan kasal.\n• I go to school. — Men maktabga boraman.\n\n✍️ MASHQ:\n1. \"U uxlab yotibdi\" → ✅ He is in bed.\n2. \"Mushuk karavotda\" → ✅ The cat is on the bed.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: In bed (uxlash) vs on the bed (mebel). Partiya 12 (221–240-darslar) to'liq tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz Partiya 12 ni to'liq o'zlashtirdingiz! Ertaga: Partiya 13 — B1 guruh predloglar! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 13 — DARS 241–260 (topic_11_b1_perfect_passive, orderIndex 51..70)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_11_241",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 51,
            titleEnglish = "241-Dars: Group Preposition — According to",
            titleUzbek = "B1 Predlog — According to (-ga ko'ra)",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun B1 guruh predloglarni boshlaymiz! According to — \"-ga ko'ra, -ga muvofiq\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nAccording to — \"-ga ko'ra, -ga muvofiq, -ning fikricha\". Biror manbaga, hujjatga yoki boshqa shaxsga asoslanib gapirganda ishlatiladi (o'z fikrimiz uchun ishlatilmaydi!).\n\n💬 MISOLLAR:\n• According to the plan, we start on Monday. — Rejaga ko'ra, biz dushanba kuni boshlaymiz.\n• According to the newspaper, it will rain tomorrow. — Gazetaga ko'ra, ertaga yomg'ir yog'adi.\n• According to the weather forecast, it will be cold. — Ob-havo ma'lumotiga ko'ra, sovuq bo'ladi.\n\n✍️ MASHQ:\n1. \"Rejaga ko'ra, biz dushanba kuni boshlaymiz\" → ✅ According to the plan, we start on Monday.\n2. According to nimaga asoslanadi? → ✅ Boshqa manbaga yoki hujjatga.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: According to — \"-ga ko'ra\" (manba, hujjat, shaxsga havola).\n\n🤖 TalkMe yakuniy: Zo'r! Endi according to ni bilasiz! Ertaga: because of — tufayli. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_242",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 52,
            titleEnglish = "242-Dars: Group Preposition — Because of",
            titleUzbek = "B1 Predlog — Because of (tufayli, sababli)",
            objectiveUzbek = "🤖 TalkMe: Bugun because of — \"tufayli, sababli\" predlogini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Because of + OT / OLMOSH: because of the rain (yomg'ir tufayli), because of the traffic.\n• Because + GAP (ega + kesim): because it rained.\n\n💬 MISOLLAR:\n• I stayed at home because of the rain. — Yomg'ir tufayli uyda qoldim.\n• We were late because of the traffic. — Yo'l tirbandligi tufayli kechikdik.\n• He was absent because of illness. — U kasallik tufayli yo'q edi.\n\n✍️ MASHQ:\n1. \"Yomg'ir tufayli uyda qoldim\" → ✅ I stayed at home because of the rain.\n2. Because of dan keyin ot keladimi yoki gap? → ✅ Ot yoki olmosh.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Because of — \"tufayli\" (ot/olmosh bilan). Because — \"chunki\" (gap bilan).\n\n🤖 TalkMe yakuniy: Zo'r! Endi because of ni bilasiz! Ertaga: in spite of — qaramasdan. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_243",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 53,
            titleEnglish = "243-Dars: Group Preposition — In spite of",
            titleUzbek = "B1 Predlog — In spite of (-ga qaramasdan)",
            objectiveUzbek = "🤖 TalkMe: Bugun in spite of — \"-ga qaramasdan\" predlogini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nIn spite of — \"-ga qaramasdan, -ga qaramay\". Ot, olmosh yoki gerund (-ing) bilan keladi: In spite of the rain, we went out.\n\n💬 MISOLLAR:\n• In spite of the rain, we went out. — Yomg'irga qaramasdan, biz tashqariga chiqdik.\n• In spite of his age, he works hard. — Yoshi katta bo'lishiga qaramasdan, u qattiq ishlaydi.\n• In spite of all difficulties, they finished the work. — Barcha qiyinchiliklarga qaramasdan, ular ishni tugatdilar.\n\n✍️ MASHQ:\n1. \"Yomg'irga qaramasdan, biz tashqariga chiqdik\" → ✅ In spite of the rain, we went out.\n2. \"Yoshi katta bo'lishiga qaramasdan, u qattiq ishlaydi\" → ✅ In spite of his age, he works hard.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: In spite of — \"-ga qaramasdan\" (ot/olmosh/gerund bilan).\n\n🤖 TalkMe yakuniy: Zo'r! Endi in spite of ni bilasiz! Ertaga: instead of — o'rniga. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_244",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 54,
            titleEnglish = "244-Dars: Group Preposition — Instead of",
            titleUzbek = "B1 Predlog — Instead of (-ning o'rniga)",
            objectiveUzbek = "🤖 TalkMe: Bugun instead of — \"-ning o'rniga\" predlogini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nInstead of — \"-ning o'rniga\". Ot yoki gerund (-ing) bilan keladi:\n• I drank tea instead of coffee. — Kofe o'rniga choy ichdim.\n• Let's walk instead of taking a bus. — Avtobusga o'tirish o'rniga piyoda yuramiz.\n\n💬 MISOLLAR:\n• I drank tea instead of coffee. — Kofe o'rniga choy ichdim.\n• She wore a dress instead of jeans. — Jinsi o'rniga ko'ylak kiydi.\n• I stayed at home instead of going out. — Tashqariga chiqish o'rniga uyda qoldim.\n\n✍️ MASHQ:\n1. \"Kofe o'rniga choy ichdim\" → ✅ I drank tea instead of coffee.\n2. \"Avtobusga o'tirish o'rniga piyoda yuramiz\" → ✅ Let's walk instead of taking a bus.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Instead of — \"-ning o'rniga\" (ot va gerund bilan).\n\n🤖 TalkMe yakuniy: Zo'r! Endi instead of ni bilasiz! Ertaga: guruh predloglar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_245",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 55,
            titleEnglish = "245-Dars: Group Prepositions Review",
            titleUzbek = "B1 Predlog — Guruh predloglar takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun 4 ta guruh predlogni (according to, because of, in spite of, instead of) takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• According to — -ga ko'ra (manba)\n• Because of — tufayli (sabab)\n• In spite of — -ga qaramasdan (to'siq)\n• Instead of — -ning o'rniga (almashtirish)\n\n💬 MISOLLAR:\n• According to the plan, we start on Monday.\n• We stayed home because of the rain.\n• In spite of the rain, we went out.\n• I drank tea instead of coffee.\n\n✍️ MASHQ:\n1. \"Yomg'ir tufayli\" → ✅ because of the rain\n2. \"Yomg'irga qaramasdan\" → ✅ in spite of the rain\n3. \"Kofe o'rniga\" → ✅ instead of coffee",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: According to, because of, in spite of, instead of — B1 guruh predloglar mustahkamlandi!\n\n🤖 TalkMe yakuniy: Ajoyib! Guruh predloglar mustahkamlandi! Ertaga: of va by takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_246",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 56,
            titleEnglish = "246-Dars: Prepositions Of & By Review",
            titleUzbek = "B1 Predlog — of va by takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun of va by predloglarining barcha ma'nolarini mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Of — \"-ning\" (egalik, miqdor, mavzu): the leg of the table, a cup of tea, a map of Uzbekistan.\n• By — \"tomonidan\" (majhul), \"bilan\" (transport: by bus), \"-gacha\" (vaqt: by 5 o'clock).\n\n💬 MISOLLAR:\n• The leg of the table is broken. — Stolning oyog'i siniq.\n• The book was written by Pushkin. — Kitob Pushkin tomonidan yozilgan.\n• We will finish by 5 o'clock. — Biz soat 5 gacha tugatamiz.\n\n✍️ MASHQ:\n1. \"Bir piyola choy\" → ✅ a cup of tea\n2. \"Biz soat 5 gacha tugatamiz\" → ✅ We will finish by 5 o'clock.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Of — egalik/miqdor. By — bajaruvchi/transport/vaqt (-gacha).\n\n🤖 TalkMe yakuniy: Zo'r! Of/by mustahkamlandi! Ertaga: after va before. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_247",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 57,
            titleEnglish = "247-Dars: Prepositions After & Before Review",
            titleUzbek = "B1 Predlog — after va before takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun after va before ni predlog hamda bog'lovchi sifatida mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• After — \"keyin\": after dinner (predlog), after I finished my work (bog'lovchi).\n• Before — \"oldin\": before dinner (predlog), before I go to bed (bog'lovchi).\n\n💬 MISOLLAR:\n• After dinner, we went for a walk. — Tushlikdan keyin sayrga chiqdik.\n• Before dinner, wash your hands. — Tushlikdan oldin qo'lingizni yuving.\n• After I finished my work, I went home. — Ishimni tugatganimdan keyin uyga ketdim.\n\n✍️ MASHQ:\n1. \"Tushlikdan keyin\" → ✅ after dinner\n2. \"Soat 5 dan oldin keling\" → ✅ Come before 5 o'clock.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: After (keyin) va before (oldin) ot bilan predlog, gap bilan bog'lovchi bo'ladi.\n\n🤖 TalkMe yakuniy: Zo'r! After/before mustahkamlandi! Ertaga: between va among. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_248",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 58,
            titleEnglish = "248-Dars: Prepositions Between & Among Review",
            titleUzbek = "B1 Predlog — between va among takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun between (ikki o'rtasida) va among (ko'pchilik orasida) ni mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Between — \"o'rtasida\" (ikki shaxs yoki narsa): between the two chairs, between you and me.\n• Among — \"orasida\" (ko'p shaxs yoki narsa): among the students, among friends.\n\n💬 MISOLLAR:\n• The ball is between the two chairs. — To'p ikki stul o'rtasida.\n• He is among the best students. — U eng yaxshi talabalar orasida.\n• The house is between the school and the park. — Uy maktab va park o'rtasida.\n\n✍️ MASHQ:\n1. \"To'p ikki stul o'rtasida\" → ✅ The ball is between the two chairs.\n2. \"U eng yaxshi talabalar orasida\" → ✅ He is among the best students.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Between — ikki o'rtasida. Among — ko'p orasida. B1 predloglar tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B1 predloglarni to'liq o'zlashtirdingiz! Ertaga: B1 bog'lovchilar — that. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_249",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 59,
            titleEnglish = "249-Dars: Conjunction — That",
            titleUzbek = "B1 Bog'lovchi — That (...ligi, ...ganligi)",
            objectiveUzbek = "🤖 TalkMe: Endi B1 bog'lovchilarni boshlaymiz! That — \"...ligi, ...ganligi\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nThat — ergash gapni bosh gapga bog'laydi: I know that he came (Men uning kelganini bilaman). To'ldiruvchi ergash gapda ko'pincha tushirib qoldiriladi: I know (that) he came.\n\n💬 MISOLLAR:\n• I know that he came. — Men uning kelganini bilaman.\n• She said that she was busy. — U band ekanligini aytdi.\n• He thinks that it will rain. — U yomg'ir yog'adi deb o'ylaydi.\n• The problem is that we have no time. — Muammo shundaki, bizda vaqt yo'q.\n\n✍️ MASHQ:\n1. \"Men uning kelganini bilaman\" → ✅ I know that he came.\n2. \"U band ekanligini aytdi\" → ✅ She said that she was busy.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: That — ergash gapni bog'laydi (\"...ligi, ...ganligi\").\n\n🤖 TalkMe yakuniy: Zo'r! Endi that bog'lovchisini bilasiz! Ertaga: if — agar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_250",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 60,
            titleEnglish = "250-Dars: Conjunction — If",
            titleUzbek = "B1 Bog'lovchi — If (agar / ...mi)",
            objectiveUzbek = "🤖 TalkMe: Bugun if — \"agar\" (shart) va \"...mi yo'qmi\" bog'lovchisini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1) Shart ergash gapda \"agar\": If it rains, we will stay at home (If dan keyin hozirgi zamon!).\n2) To'ldiruvchi ergash gapda \"...mi yo'qmi\": I don't know if he will come.\n\n💬 MISOLLAR:\n• If it rains, we will stay at home. — Agar yomg'ir yog'sa, biz uyda qolamiz.\n• If you come, I will help you. — Agar kelsangiz, men sizga yordam beraman.\n• I don't know if he will come. — U keladimi yo'qmi bilmayman.\n\n✍️ MASHQ:\n1. \"Agar yomg'ir yog'sa, biz uyda qolamiz\" → ✅ If it rains, we will stay at home.\n2. Shart ergash gapda If dan keyin qaysi zamon keladi? → ✅ Hozirgi zamon (Present Simple).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: If — \"agar\" (shartda hozirgi zamon bilan) va \"...mi yo'qmi\" (to'ldiruvchi ergash gapda).\n\n🤖 TalkMe yakuniy: Zo'r! Endi if ni bilasiz! Ertaga: whether — ...mi yo'qmi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_251",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 61,
            titleEnglish = "251-Dars: Conjunction — Whether (...or not)",
            titleUzbek = "B1 Bog'lovchi — Whether (...mi yo'qmi)",
            objectiveUzbek = "🤖 TalkMe: Bugun whether — \"...mi yo'qmi\" bog'lovchisini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nWhether — \"...mi yo'qmi\". Rasmiy uslubda, predloglardan keyin va 'or not' birikmasi bilan ishlatiladi:\n• I don't know whether he will come.\n• Whether he comes or not, I will go.\n\n💬 MISOLLAR:\n• I don't know whether he will come. — U keladimi yo'qmi bilmayman.\n• She asked whether I was busy. — U mendan bandmanmi deb so'radi.\n• Whether he comes or not, I will go. — U kelsa ham, kelmasa ham, men boraman.\n\n✍️ MASHQ:\n1. \"U keladimi yo'qmi bilmayman\" → ✅ I don't know whether he will come.\n2. \"U kelsa ham, kelmasa ham, men boraman\" → ✅ Whether he comes or not, I will go.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Whether — \"...mi yo'qmi\". Whether ... or not.\n\n🤖 TalkMe yakuniy: Zo'r! Endi whether ni bilasiz! Ertaga: B1 bog'lovchilar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_252",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 62,
            titleEnglish = "252-Dars: B1 Conjunctions Review (that, if, whether)",
            titleUzbek = "B1 Bog'lovchilar — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun B1 bog'lovchilarni (that, if, whether) takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• That — \"...ligi\" (ergash gap).\n• If — \"agar\" (shart) yoki \"...mi\" (savol).\n• Whether — \"...mi yo'qmi\" (rasmiy, whether ... or not).\n\n💬 MISOLLAR:\n• I know that he came. — Men uning kelganini bilaman.\n• If it rains, we will stay at home. — Agar yomg'ir yog'sa, uyda qolamiz.\n• I don't know whether he will come. — U keladimi yo'qmi bilmayman.\n\n✍️ MASHQ:\n1. \"Men uning kelganini bilaman\" → ✅ I know that he came.\n2. \"U keladimi yo'qmi bilmayman\" → ✅ I don't know whether he will come.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: That, if, whether — B1 bog'lovchilar tugadi!\n\n🤖 TalkMe yakuniy: Ajoyib! Bog'lovchilar mustahkamlandi! Ertaga: B1 gap tuzilishi — ega ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_253",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 63,
            titleEnglish = "253-Dars: Subject Clauses (That he came is strange)",
            titleUzbek = "B1 Gap — Ega ergash gap (Subject Clause)",
            objectiveUzbek = "🤖 TalkMe: Endi B1 gap tuzilishini boshlaymiz! Ega ergash gap — qo'shma gapda ega vazifasida! 🚀",
            explanationUzbek = "📖 QOIDA:\nEga ergash gap (Subject Clause) qo'shma gapda ega vazifasida keladi va that, whether, what, when, how bilan bog'lanadi:\n• That he came is strange. — Uning kelgani g'alati.\n• What he said is true. — U aytgan narsa haqiqat.\n\n💬 MISOLLAR:\n• That he came is strange. — Uning kelgani g'alati.\n• Whether they will come is not known. — Ular keladimi yo'qmi noma'lum.\n• What he said is true. — U aytgan narsa haqiqat.\n• It is strange that he came. — Uning kelgani g'alati.\n\n✍️ MASHQ:\n1. \"Uning kelgani g'alati\" → ✅ That he came is strange.\n2. \"U aytgan narsa haqiqat\" → ✅ What he said is true.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ega ergash gap — gapda ega vazifasida (That he came is strange / What he said is true).\n\n🤖 TalkMe yakuniy: Zo'r! Endi ega ergash gapni bilasiz! Ertaga: kesim ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_254",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 64,
            titleEnglish = "254-Dars: Predicative Clauses (The trouble is that...)",
            titleUzbek = "B1 Gap — Kesim ergash gap (Predicative Clause)",
            objectiveUzbek = "🤖 TalkMe: Bugun kesim ergash gapni (The trouble is that...) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nKesim ergash gap bog'lovchi fe'ldan (is, was, seems) keyin kelib, ot-kesim vazifasini bajaradi:\n• The trouble is that I have lost his address.\n• The question is whether they will help us.\n\n💬 MISOLLAR:\n• The trouble is that I have lost his address. — Muammo shundaki, men uning manzilini yo'qotib qo'ydim.\n• The question is whether they will help us. — Masala ular bizga yordam bera oladilarmi yo'qmi shunda.\n• That is what I want. — Bu men istagan narsa.\n\n✍️ MASHQ:\n1. \"Muammo shundaki, bizda vaqt yo'q\" → ✅ The problem is that we have no time.\n2. \"Bu men istagan narsa\" → ✅ That is what I want.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Kesim ergash gap — bog'lovchi fe'ldan (is/was) keyin keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi kesim ergash gapni bilasiz! Ertaga: to'ldiruvchi ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_255",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 65,
            titleEnglish = "255-Dars: Object Clauses (He told us that...)",
            titleUzbek = "B1 Gap — To'ldiruvchi ergash gap (Object Clause)",
            objectiveUzbek = "🤖 TalkMe: Bugun to'ldiruvchi ergash gapni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo'ldiruvchi ergash gap fe'ldan keyin to'ldiruvchi vazifasida keladi (that, if, whether, what, where, when bilan):\n• He told us that he felt ill. — U kasal bo'lib qolganligini aytdi.\n• He asked us what we thought of it. — U bizdan bu haqda qanday fikrda ekanligimizni so'radi.\n\n💬 MISOLLAR:\n• He told us that he felt ill. — U kasal bo'lib qolganligini aytdi.\n• I know that he will come. — Men uning kelishini bilaman.\n• I don't know whether he will come. — U keladimi yo'qmi bilmayman.\n\n✍️ MASHQ:\n1. \"U kasal bo'lib qolganligini aytdi\" → ✅ He told us that he felt ill.\n2. \"Men uning kelishini bilaman\" → ✅ I know that he will come.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To'ldiruvchi ergash gap — fe'ldan keyin to'ldiruvchi vazifasida (that ko'pincha tushiriladi).\n\n🤖 TalkMe yakuniy: Zo'r! Endi to'ldiruvchi ergash gapni bilasiz! Ertaga: aniqlovchi ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_256",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 66,
            titleEnglish = "256-Dars: Attributive Clauses (The man who...)",
            titleUzbek = "B1 Gap — Aniqlovchi ergash gap (Attributive Clause)",
            objectiveUzbek = "🤖 TalkMe: Bugun aniqlovchi ergash gapni (who, which, that, where, when) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAniqlovchi ergash gap otdan keyin kelib, o'sha otni izohlaydi:\n• The man who was here yesterday is a painter. — Kecha bu yerda bo'lgan kishi rassom.\n• The book which I read is interesting. — Men o'qigan kitob qiziq.\n\n💬 MISOLLAR:\n• The man who was here yesterday is a painter. — Kecha bu yerda bo'lgan kishi rassom.\n• The book which I read is interesting. — Men o'qigan kitob qiziq.\n• I remember the day when I first came to London. — Men Londonga birinchi kelgan kunimni eslayman.\n\n✍️ MASHQ:\n1. \"Kecha bu yerda bo'lgan kishi rassom\" → ✅ The man who was here yesterday is a painter.\n2. \"Men o'qigan kitob qiziq\" → ✅ The book which I read is interesting.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Aniqlovchi ergash gap — otdan keyin kelib uni aniqlaydi (who, which, that, where, when).\n\n🤖 TalkMe yakuniy: Zo'r! Endi aniqlovchi ergash gapni bilasiz! Ertaga: It soxta egasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_257",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 67,
            titleEnglish = "257-Dars: Formal Subject It (It is cold / It is clear that...)",
            titleUzbek = "B1 Gap — It soxta egasi",
            objectiveUzbek = "🤖 TalkMe: Bugun It soxta egasini (ob-havo, vaqt, infinitiv va ergash gaplar bilan) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nIt soxta egasi (Formal Subject) ob-havo, vaqt, masofa hamda infinitiv/gerund/ergash gaplar bilan keladi va o'zbek tiliga tarjima qilinmaydi:\n• It is cold. — Sovuq.\n• It is difficult to find a suitable steamer. — Mos paroxod topish qiyin.\n• It is clear that he will not come. — Uning kelmasligi aniq.\n\n💬 MISOLLAR:\n• It is raining. — Yomg'ir yog'ayapti.\n• It is 5 o'clock. — Soat 5.\n• It is no use telling him about it. — Bu haqda unga aytishning foydasi yo'q.\n• It is clear that he will not come. — Uning kelmasligi aniq.\n\n✍️ MASHQ:\n1. \"Uning kelmasligi aniq\" → ✅ It is clear that he will not come.\n2. \"Bu haqda unga aytishning foydasi yo'q\" → ✅ It is no use telling him about it.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: It soxta egasi — ob-havo, vaqt, masofa va It is ... to / that qurilmalarida.\n\n🤖 TalkMe yakuniy: Zo'r! Endi it soxta egasini bilasiz! Ertaga: There is/are birikmasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_258",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 68,
            titleEnglish = "258-Dars: There is / There are Construction",
            titleUzbek = "B1 Gap — There is / There are birikmasi",
            objectiveUzbek = "🤖 TalkMe: Bugun There is/are birikmasining barcha shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nThere is (birlik) / There are (ko'plik) — biror joyda noma'lum shaxs yoki narsaning mavjudligini (\"bor\") bildiradi.\n• So'roq: Is there a book? Are there any books?\n• Bo'lishsiz: There isn't a book. There aren't any books.\n\n💬 MISOLLAR:\n• There is a telephone in the room. — Xonada telefon bor.\n• There are many students in the class. — Sinfda ko'p talabalar bor.\n• Are there any books on the table? — Stolda biror kitob bormi?\n\n✍️ MASHQ:\n1. \"Xonada telefon bor\" → ✅ There is a telephone in the room.\n2. \"Stolda biror kitob bormi?\" → ✅ Are there any books on the table?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: There is (birlik), there are (ko'plik) — \"bor\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi there is/are ni bilasiz! Ertaga: It is...that... urg'u qurilmasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_259",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 69,
            titleEnglish = "259-Dars: Emphatic It is ... that/who ...",
            titleUzbek = "B1 Gap — It is … that … (Urg'u qurilmasi)",
            objectiveUzbek = "🤖 TalkMe: Bugun It is...that... urg'u qurilmasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBiror gap bo'lagiga kuchli urg'u berish uchun It is/was + [urg'u bo'lagi] + that/who ishlatiladi:\n• It was I that met his sister in the park. — Uning singlisini parkda uchratgan aynan menman.\n• It was in the park that I met his sister. — Men uning singlisini aynan parkda uchratdim.\n\n💬 MISOLLAR:\n• It was yesterday that he came. — U aynan kecha keldi.\n• It is Ali who helps me. — Menga yordam beradigan aynan Ali.\n• It was the book that he bought. — U aynan kitobni sotib oldi.\n\n✍️ MASHQ:\n1. \"Menga yordam beradigan aynan Ali\" → ✅ It is Ali who helps me.\n2. \"U aynan kecha keldi\" → ✅ It was yesterday that he came.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: It is/was + urg'u bo'lagi + that/who — gap bo'lagiga urg'u beradi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi it is...that... ni bilasiz! Ertaga: 260-dars — Partiya 13 yakuni! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_260",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 70,
            titleEnglish = "260-Dars: B1 Sentence Structure Mastery Review",
            titleUzbek = "B1 Gap tuzilishi — Takrorlash va Partiya 13 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun B1 gap tuzilishini (ergash gaplar, It, There is, It is...that) takrorlaymiz va Partiya 13 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Ega ergash gap: That he came is strange.\n• Kesim ergash gap: The trouble is that I have lost his address.\n• To'ldiruvchi ergash gap: I know that he will come.\n• Aniqlovchi ergash gap: The man who came is my brother.\n• It soxta egasi: It is difficult to find a steamer.\n• Urg'u: It was I that met her.\n\n💬 MISOLLAR:\n• That he came is strange.\n• The trouble is that I have lost his address.\n• The man who came is my brother.\n• It was I that met her.\n\n✍️ MASHQ:\n1. \"Uning kelgani g'alati\" → ✅ That he came is strange.\n2. \"Kelgan odam mening akam\" → ✅ The man who came is my brother.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Partiya 13 (241–260-darslar) to'liq yakunlandi! B1 ergash gaplar va urg'u qurilmalarini o'zlashtirdingiz!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz Partiya 13 ni to'liq tugatdingiz! Ertaga: Partiya 14 — B1 Qo'shma gaplar, Ko'chirma gap va B2 Davom zamonlar! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity("voc_p12_01", "topic_11_b1_perfect_passive", "sec_11_222", "Childhood", "bolalik", "We have known each other since childhood.", "Biz bolalikdan buyon bir-birimizni taniymiz.", isStudied = false),
        VocabularyEntity("voc_p12_02", "topic_11_b1_perfect_passive", "sec_11_223", "Telegram", "telegramma", "They had shipped the goods when the telegram arrived.", "Telegramma kelganda ular mollarni yuklab bo'lgan edilar.", isStudied = false),
        VocabularyEntity("voc_p12_03", "topic_11_b1_perfect_passive", "sec_11_225", "Scarcely", "zo'rg'a, ...-masdan", "Scarcely had she sat down when the phone rang.", "U o'tirar-o'tirmas telefon jiringladi.", isStudied = false),
        VocabularyEntity("voc_p12_04", "topic_11_b1_perfect_passive", "sec_11_228", "Brick", "g'isht", "The house is built of brick.", "Uy g'ishtdan qurilgan.", isStudied = false),
        VocabularyEntity("voc_p12_05", "topic_11_b1_perfect_passive", "sec_11_230", "Secretary", "kotiba, kotib", "The letter was sent by the secretary.", "Xat kotiba tomonidan yuborilgan.", isStudied = false),
        VocabularyEntity("voc_p12_06", "topic_11_b1_perfect_passive", "sec_11_234", "Opportunity", "imkoniyat", "He has a few good opportunities.", "Uning bir nechta yaxshi imkoniyatlari bor.", isStudied = false),
        VocabularyEntity("voc_p12_07", "topic_11_b1_perfect_passive", "sec_11_237", "Flu", "gripp", "He has flu.", "U gripp bilan kasal.", isStudied = false),
        VocabularyEntity("voc_p12_08", "topic_11_b1_perfect_passive", "sec_11_238", "Headache", "bosh og'rig'i", "She has a headache.", "Uning boshi og'riyapti.", isStudied = false),
        VocabularyEntity("voc_p12_09", "topic_11_b1_perfect_passive", "sec_11_239", "Prison", "qamoqxona", "He went to prison.", "U qamoqqa tushdi.", isStudied = false),
        VocabularyEntity("voc_p13_01", "topic_11_b1_perfect_passive", "sec_11_241", "According to", "-ga ko'ra, -ga muvofiq", "According to the plan, we start on Monday.", "Rejaga ko'ra, biz dushanba kuni boshlaymiz.", isStudied = false),
        VocabularyEntity("voc_p13_02", "topic_11_b1_perfect_passive", "sec_11_241", "Helmet", "dubulg'a, kaska", "You must wear a helmet.", "Siz dubulg'a kiyishingiz kerak.", isStudied = false),
        VocabularyEntity("voc_p13_03", "topic_11_b1_perfect_passive", "sec_11_242", "Illness", "kasallik, betoblik", "He was absent because of illness.", "U kasallik tufayli yo'q edi.", isStudied = false),
        VocabularyEntity("voc_p13_04", "topic_11_b1_perfect_passive", "sec_11_243", "In spite of", "-ga qaramasdan", "In spite of the rain, we went out.", "Yomg'irga qaramasdan, biz tashqariga chiqdik.", isStudied = false),
        VocabularyEntity("voc_p13_05", "topic_11_b1_perfect_passive", "sec_11_244", "Instead of", "-ning o'rniga", "I drank tea instead of coffee.", "Kofe o'rniga choy ichdim.", isStudied = false),
        VocabularyEntity("voc_p13_06", "topic_11_b1_perfect_passive", "sec_11_251", "Whether", "...mi yo'qmi", "I don't know whether he will come.", "U keladimi yo'qmi bilmayman.", isStudied = false),
        VocabularyEntity("voc_p13_07", "topic_11_b1_perfect_passive", "sec_11_254", "Address", "manzil", "I have lost his address.", "Men uning manzilini yo'qotib qo'ydim.", isStudied = false),
        VocabularyEntity("voc_p13_08", "topic_11_b1_perfect_passive", "sec_11_256", "Painter", "rassom", "The man who was here yesterday is a painter.", "Kecha bu yerda bo'lgan kishi rassom.", isStudied = false),
        VocabularyEntity("voc_p13_09", "topic_11_b1_perfect_passive", "sec_11_257", "Steamer", "paroxod", "It is difficult to find a suitable steamer.", "Mos paroxod topish qiyin.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "gr_11_223",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_223",
            titleUzbek = "Past Perfect va Future Perfect (had + V3 / will have + V3)",
            explanationUzbek = "Past Perfect (had + V3) o'tgan zamondagi biror vaqtdan oldin, Future Perfect (will have + V3) kelasi zamondagi biror vaqtdan oldin tugaydigan harakatni bildiradi.",
            exampleEnglish = "I had finished my work before he came. I will have finished by 5.",
            exampleUzbek = "U kelishidan oldin men ishimni tugatgan edim. Soat 5 gacha tugatgan bo'laman.",
            associatedVocabularyIdsCsv = "voc_p12_02,voc_p12_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_11_228",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_228",
            titleUzbek = "Majhul nisbat (Passive Voice: to be + V3)",
            explanationUzbek = "Present: am/is/are + V3. Past: was/were + V3. Future: will be + V3. Bajaruvchi by predlogi bilan ko'rsatiladi.",
            exampleEnglish = "The book was written by Pushkin.",
            exampleUzbek = "Kitob Pushkin tomonidan yozilgan.",
            associatedVocabularyIdsCsv = "voc_p12_04,voc_p12_05",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_11_241",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_241",
            titleUzbek = "Guruh predloglar (according to, because of, in spite of, instead of)",
            explanationUzbek = "Ushbu guruh predloglardan keyin har doim ot, olmosh yoki gerund (-ing) keladi.",
            exampleEnglish = "In spite of the rain, we went out. I drank tea instead of coffee.",
            exampleUzbek = "Yomg'irga qaramasdan, tashqariga chiqdik. Kofe o'rniga choy ichdim.",
            associatedVocabularyIdsCsv = "voc_p13_01,voc_p13_04,voc_p13_05",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_11_222",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_222",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "Bo'sh joyni to'ldiring: \"I have lived here ___ 2020.\"",
            subPromptText = "222-Dars: since (boshlanish nuqtasi) vs for (davomiylik)",
            audioTextToSpeak = "I have lived here since 2020.",
            optionsPipeSeparated = "since|for|from|in",
            correctAnswer = "since",
            explanationUzbek = "2020-yil harakatning boshlanish nuqtasi bo'lgani uchun 'since' ishlatiladi.",
            vocabularyIdsCsv = "voc_p12_01",
            grammarRuleIdsCsv = "gr_11_223"
        ),
        ExerciseEntity(
            id = "ex_11_223",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_223",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"U kelishidan oldin men ishimni tugatgan edim\" gapini tanlang:",
            subPromptText = "223-Dars: Past Perfect (had + V3)",
            audioTextToSpeak = "I had finished my work before he came.",
            optionsPipeSeparated = "I had finished my work before he came.|I have finished my work before he came.|I finished my work before he had come.|I will have finished my work before he came.",
            correctAnswer = "I had finished my work before he came.",
            explanationUzbek = "O'tgan zamondagi ikkinchi harakatdan oldin tugagan harakat Past Perfect (had finished) bo'ladi.",
            vocabularyIdsCsv = "voc_p12_02",
            grammarRuleIdsCsv = "gr_11_223"
        ),
        ExerciseEntity(
            id = "ex_11_229",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_229",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"Kitob Pushkin tomonidan yozilgan\" gapining to'g'ri tarjimasini tanlang:",
            subPromptText = "229-Dars: Past Simple Passive (was/were + V3 + by)",
            audioTextToSpeak = "The book was written by Pushkin.",
            optionsPipeSeparated = "The book was written by Pushkin.|The book wrote by Pushkin.|The book were written by Pushkin.|The book is wrote by Pushkin.",
            correctAnswer = "The book was written by Pushkin.",
            explanationUzbek = "Birlikdagi ot o'tgan zamon majhul nisbatda 'was written by' bo'ladi.",
            vocabularyIdsCsv = "voc_p12_05",
            grammarRuleIdsCsv = "gr_11_228"
        ),
        ExerciseEntity(
            id = "ex_11_242",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_242",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"Yomg'ir tufayli uyda qoldim\" gapini toping:",
            subPromptText = "242-Dars: because of + ot",
            audioTextToSpeak = "I stayed at home because of the rain.",
            optionsPipeSeparated = "I stayed at home because of the rain.|I stayed at home because the rain.|I stayed at home according to the rain.|I stayed at home instead of the rain.",
            correctAnswer = "I stayed at home because of the rain.",
            explanationUzbek = "Ot (the rain) bilan 'because of' (tufayli) ishlatiladi.",
            vocabularyIdsCsv = "voc_p13_03",
            grammarRuleIdsCsv = "gr_11_241"
        ),
        ExerciseEntity(
            id = "ex_11_259",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_259",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"Uning singlisini parkda uchratgan aynan menman\" (urg'u qurilmasi) gapini tanlang:",
            subPromptText = "259-Dars: It is/was ... that/who ...",
            audioTextToSpeak = "It was I that met his sister in the park.",
            optionsPipeSeparated = "It was I that met his sister in the park.|There was I that met his sister in the park.|What I was met his sister in the park.|Whether I met his sister in the park.",
            correctAnswer = "It was I that met his sister in the park.",
            explanationUzbek = "Urg'u berish uchun 'It was ... that/who ...' qurilmasi ishlatiladi.",
            vocabularyIdsCsv = "voc_p13_08",
            grammarRuleIdsCsv = "gr_11_241"
        )
    )
}
