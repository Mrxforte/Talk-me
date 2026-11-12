package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 16 — DARS 301–320 (TO'LIQ):
 * - B2 GERUND (301–308), SIFATDOSH (309–314), COMPLEX OBJECT (315–320)
 *   inside topic_12_b2_modals_nonfinite (orderIndex 31..50)
 *
 * PARTIYA 17 — DARS 321–340 (TO'LIQ):
 * - B2 ERGASH GAPLAR (321–330) va B2 KO'CHIRMA GAP TO'LIQ (331–336)
 *   inside topic_12_b2_modals_nonfinite (orderIndex 51..66)
 * - C1 INFINITIVLI NOMINATIV VA MUSTAQIL BIRIKMA (337–340)
 *   inside topic_13_c1_c2_mastery (orderIndex 1..4)
 */
object CurriculumPartiya16And17SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 16 — B2 GERUND, SIFATDOSH, COMPLEX OBJECT (Dars 301–320, orderIndex 31..50)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_12_301",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 31,
            titleEnglish = "301-Dars: What is a Gerund? (Reading is good)",
            titleUzbek = "B2 Gerund — Nima? (Harakat nomi: -ing)",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun gerundni boshlaymiz! Reading — \"o'qish\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nGerund (fe'l + -ing) — fe'lning ham fe'llik, ham otlik xususiyatiga ega bo'lgan shakli. O'zbek tilidagi harakat nomiga (\"o'qish, yozish, suzish\") to'g'ri keladi:\n• Reading is good. — O'qish yaxshi.\n• I like reading. — Men o'qishni yaxshi ko'raman.\n\n💬 MISOLLAR:\n• Reading is good. — O'qish yaxshi.\n• Swimming is a good exercise. — Suzish yaxshi mashq.\n• Learning English is important. — Ingliz tilini o'rganish muhim.\n• He finished writing the letter. — U xat yozishni tugatdi.\n\n✍️ MASHQ:\n1. \"Suzish yaxshi mashq\" → ✅ Swimming is a good exercise.\n2. \"U xat yozishni tugatdi\" → ✅ He finished writing the letter.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Gerund = fe'l + -ing (harakat nomi, ot va fe'l xususiyatiga ega).\n\n🤖 TalkMe yakuniy: Zo'r! Endi gerund nima ekanligini bilasiz! Ertaga: predloglardan keyin gerund. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_302",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 32,
            titleEnglish = "302-Dars: Gerund after Prepositions",
            titleUzbek = "B2 Gerund — Predloglardan keyin (fond of, without...)",
            objectiveUzbek = "🤖 TalkMe: Bugun oltin qoidani o'rganamiz: har qanday predlogdan keyin fe'l kelsa, u GERUND (-ing) shaklida bo'ladi! 🚀",
            explanationUzbek = "📖 QOIDA:\nPredloglardan keyin (of, in, on, at, for, without, after, before, by, instead of, in spite of) har doim gerund (-ing) keladi:\n• I am fond of reading. — Men o'qishni yaxshi ko'raman.\n• He left without saying goodbye. — U xayrlashmasdan ketdi.\n\n💬 MISOLLAR:\n• I am fond of reading. — Men o'qishni yaxshi ko'raman.\n• He left without saying goodbye. — U xayrlashmasdan ketdi.\n• She is interested in learning English. — U ingliz tilini o'rganishga qiziqadi.\n• Thank you for coming. — Kelganingiz uchun rahmat.\n\n✍️ MASHQ:\n1. \"U xayrlashmasdan ketdi\" → ✅ He left without saying goodbye.\n2. \"Kelganingiz uchun rahmat\" → ✅ Thank you for coming.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Predlog + gerund (of reading, without saying, for coming).\n\n🤖 TalkMe yakuniy: Zo'r! Endi predlogdan keyin gerundni bilasiz! Ertaga: predlogsiz gerund. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_303",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 33,
            titleEnglish = "303-Dars: Gerund without Prepositions",
            titleUzbek = "B2 Gerund — Predlogsiz (ega, kesim, to'ldiruvchi)",
            objectiveUzbek = "🤖 TalkMe: Bugun predlogsiz gerundni (enjoy listening, Reading is...) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPredlogsiz gerund:\n1) Fe'ldan keyin to'ldiruvchi: I enjoy listening to music.\n2) Ega sifatida: Reading is her favourite occupation.\n3) Ot-kesim tarkibida: Her greatest pleasure is reading.\n\n💬 MISOLLAR:\n• I enjoy listening to music. — Men musiqa tinglashdan rohatlanaman.\n• Reading is her favourite occupation. — O'qish uning sevimli mashg'uloti.\n• She avoided answering my question. — U savolimga javob berishdan qochdi.\n\n✍️ MASHQ:\n1. \"Men musiqa tinglashdan rohatlanaman\" → ✅ I enjoy listening to music.\n2. \"U savolimga javob berishdan qochdi\" → ✅ She avoided answering my question.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Predlogsiz gerund ega, ot-kesim va fe'ldan keyin to'ldiruvchi bo'lib keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi predlogsiz gerundni bilasiz! Ertaga: fe'l + gerund ro'yxati. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_304",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 34,
            titleEnglish = "304-Dars: Verbs Followed by Gerund (enjoy, finish, mind)",
            titleUzbek = "B2 Fe'l + Gerund — To'liq ro'yxat",
            objectiveUzbek = "🤖 TalkMe: Bugun o'zidan keyin faqat gerund (-ing) oladigan muhim fe'llar ro'yxatini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nQuyidagi fe'llardan keyin to + V emas, faqat GERUND (-ing) keladi:\n• enjoy, finish, stop, avoid, keep (on), mind, suggest, recommend, can't help.\n\n💬 MISOLLAR:\n• I enjoy reading. — Men o'qishdan rohatlanaman.\n• She stopped smoking. — U chekishni to'xtatdi.\n• I don't mind waiting. — Men kutishga e'tiroz bildirmayman.\n• She suggested going to a movie. — U kinoga borishni taklif qildi.\n• I can't help worrying. — Men tashvishlanmasdan turolmayman.\n\n✍️ MASHQ:\n1. \"Men kutishga e'tiroz bildirmayman\" → ✅ I don't mind waiting.\n2. \"U kinoga borishni taklif qildi\" → ✅ She suggested going to a movie.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: enjoy, finish, stop, avoid, keep, mind, suggest, can't help + V-ing!\n\n🤖 TalkMe yakuniy: Zo'r! Endi fe'l + gerund ro'yxatini bilasiz! Ertaga: simple vs perfect gerund. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_305",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 35,
            titleEnglish = "305-Dars: Simple vs Perfect Gerund (having done)",
            titleUzbek = "B2 Gerund — Simple vs Perfect (having + P.P.)",
            objectiveUzbek = "🤖 TalkMe: Bugun Simple Gerund (hearing) va Perfect Gerund (having seen) farqini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Simple Gerund (V-ing) — kesimdagi harakat bilan bir vaqtda: I am surprised at hearing this.\n• Perfect Gerund (having + P.P.) — kesimdagi harakatdan oldin sodir bo'lgan ish: I don't remember having seen him before.\n\n💬 MISOLLAR:\n• I am surprised at hearing this. — Buni eshitib hayron qoldim.\n• I don't remember having seen him before. — Men uni ilgari ko'rganimni eslamayman.\n• He mentioned having read it in the paper. — U buni gazetada o'qiganini eslatib o'tdi.\n\n✍️ MASHQ:\n1. \"Men uni ilgari ko'rganimni eslamayman\" → ✅ I don't remember having seen him before.\n2. Perfect Gerund formulasi? → ✅ having + P.P.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Simple gerund (V-ing) — bir vaqtda; Perfect gerund (having + P.P.) — oldin.\n\n🤖 TalkMe yakuniy: Zo'r! Endi simple/perfect gerundni bilasiz! Ertaga: gerundning gapdagi 7 vazifasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_306",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 36,
            titleEnglish = "306-Dars: Syntactic Functions of the Gerund",
            titleUzbek = "B2 Gerund — Gapdagi vazifalari",
            objectiveUzbek = "🤖 TalkMe: Bugun gerundning gapdagi barcha 7 vazifasini ko'rib chiqamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nGerund gapda: 1) Ega (Reading is good), 2) Ot-kesim (Her pleasure is reading), 3) Fe'l kesim (He finished reading), 4) Vositasiz to'ldiruvchi (I remember reading), 5) Predlogli to'ldiruvchi (fond of reading), 6) Aniqlovchi (the pleasure of reading), 7) Hol (After reading the letter) bo'lib keladi.\n\n💬 MISOLLAR:\n• Reading is good. (Ega)\n• He finished reading the newspaper. (Fe'l kesim)\n• I am fond of reading. (Predlogli to'ldiruvchi)\n• After reading the letter, I put it into the drawer. (Hol)\n\n✍️ MASHQ:\n1. \"After reading the letter\" gapda qanday bo'lak? → ✅ Hol (payt holi).\n2. \"Reading is good\" gapida Reading qanday bo'lak? → ✅ Ega.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Gerund gapda ega, kesim, to'ldiruvchi, aniqlovchi va hol bo'lib keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi gerundning vazifalarini bilasiz! Ertaga: oldidan ot/olmosh kelgan gerund. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_307",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 37,
            titleEnglish = "307-Dars: Gerund with Possessive / Noun (on their delivering)",
            titleUzbek = "B2 Gerund — Oldidan ot yoki egalik olmoshi kelishi",
            objectiveUzbek = "🤖 TalkMe: Bugun oldidan qaratqich kelishigidagi ot yoki egalik olmoshi kelgan gerundni (on their delivering) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nGerund ifodalagan harakat boshqa shaxs/buyumga tegishli bo'lsa, oldidan egalik olmoshi (my, his, their) yoki qaratqich kelishigidagi ot ('s) keladi:\n• We insisted on their delivering the goods immediately.\n• Excuse my (me) interrupting you.\n\n💬 MISOLLAR:\n• We insisted on their delivering the goods immediately. — Biz ulardan mollarni zudlik bilan yetkazib berishlarini talab qildik.\n• We insisted on the contract being signed immediately. — Biz shartnomaning zudlik bilan imzolanishini talab qildik.\n• Excuse me interrupting you. — Sizning so'zingizni bo'lganim uchun kechirasiz.\n\n✍️ MASHQ:\n1. \"Sizning so'zingizni bo'lganim uchun kechirasiz\" → ✅ Excuse me interrupting you.\n2. \"Biz ulardan mollarni darhol yetkazishlarini talab qildik\" → ✅ We insisted on their delivering the goods immediately.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Egalik olmoshi / ot + gerund (on their delivering, on the contract being signed).\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: gerund umumiy takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_308",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 38,
            titleEnglish = "308-Dars: B2 Gerund Mastery Review",
            titleUzbek = "B2 Gerund — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun B2 Gerund bo'limini to'liq takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Predlog + gerund: fond of reading, without saying.\n• Fe'l + gerund: enjoy, finish, stop, avoid, mind, suggest.\n• Simple (V-ing) va Perfect (having + P.P.).\n• Egalik olmoshi + gerund (on their delivering).\n\n💬 MISOLLAR:\n• I am fond of reading.\n• He finished writing the letter.\n• I don't remember having seen him before.\n• We insisted on their delivering.\n\n✍️ MASHQ:\n1. \"Men o'qishni yaxshi ko'raman\" → ✅ I am fond of reading.\n2. \"U xat yozishni tugatdi\" → ✅ He finished writing the letter.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B2 Gerund bo'limi to'liq yakunlandi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B2 gerundni to'liq o'zlashtirdingiz! Ertaga: B2 Sifatdosh (Participle)! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_309",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 39,
            titleEnglish = "309-Dars: What is a Participle? (rising sun, broken cup)",
            titleUzbek = "B2 Sifatdosh (Participle) — Nima?",
            objectiveUzbek = "🤖 TalkMe: Endi sifatdoshni (Participle) boshlaymiz! Fe'lning sifatlik va ravishlik shakli! 🚀",
            explanationUzbek = "📖 QOIDA:\nSifatdosh (Participle) — fe'lning sifatlik va ravishlik xususiyatiga ega shakli. Uch turi bor:\n1) Present Participle: V-ing (the rising sun — chiqayotgan quyosh)\n2) Past Participle: V-ed / V3 (a broken cup — siniq piyola)\n3) Perfect Participle: having + P.P. (having finished — tugatgach).\n\n💬 MISOLLAR:\n• A broken cup lay on the table. — Siniq piyola stolda yotardi.\n• The rising sun was hidden by the clouds. — Chiqayotgan quyoshni bulutlar yashirdi.\n• He sat at the table thinking. — U stolda o'ychan o'tirar edi.\n\n✍️ MASHQ:\n1. \"Siniq piyola stolda yotardi\" → ✅ A broken cup lay on the table.\n2. Sifatdoshning nechta turi bor? → ✅ 3 ta (Present, Past, Perfect).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sifatdosh: Present (-ing), Past (-ed/V3), Perfect (having + P.P.).\n\n🤖 TalkMe yakuniy: Zo'r! Endi sifatdosh nima ekanligini bilasiz! Ertaga: Present Participle Active. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_310",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 40,
            titleEnglish = "310-Dars: Present Participle Active (sitting, knowing)",
            titleUzbek = "B2 Sifatdosh — Present Participle Active (-ing)",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Participle Active (-ing) va uning aniqlovchi hamda hol vazifalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPresent Participle Active (V-ing) kesimdagi harakat bilan bir vaqtda sodir bo'lgan harakatni bildiradi:\n• Aniqlovchi: The man sitting at the window is my brother.\n• Payt holi: Arriving at the station, I called a porter.\n• Sabab holi: Knowing English well, he translated the article.\n\n💬 MISOLLAR:\n• The man sitting at the window is my brother. — Deraza yonida o'tirgan kishi mening akam.\n• Arriving at the station, I called a porter. — Stansiyaga yetib kelib, men hammol chaqirdim.\n• Knowing English well, he translated the article. — Ingliz tilini yaxshi bilgani uchun u maqolani tarjima qildi.\n\n✍️ MASHQ:\n1. \"Deraza yonida o'tirgan kishi mening akam\" → ✅ The man sitting at the window is my brother.\n2. \"Ingliz tilini yaxshi bilgani uchun u maqolani tarjima qildi\" → ✅ Knowing English well, he translated the article.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Present Participle (-ing) aniqlovchi va hol (payt, sabab, ravish) bo'lib keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi present participleni bilasiz! Ertaga: Past Participle. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_311",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 41,
            titleEnglish = "311-Dars: Past Participle Passive (broken, produced)",
            titleUzbek = "B2 Sifatdosh — Past Participle (-ed / 3-shakl)",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Participle Passive (-ed / 3-shakl: broken, written, produced) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPast Participle Passive majhul ma'noga ega (siniq, yozilgan, ishlab chiqarilgan):\n• Otdan oldin: a broken cup, a torn sleeve.\n• Otdan keyin: automobiles produced by our plants.\n• Hol vazifasida: Squeezed by ice, the steamer could not continue her way.\n\n💬 MISOLLAR:\n• A broken cup lay on the table. — Siniq piyola stolda yotardi.\n• All books taken from the library must be returned next week. — Kutubxonadan olingan barcha kitoblar kelasi hafta qaytarilishi kerak.\n• Squeezed by ice, the steamer could not continue her way. — Muzda qisilib qolib, paroxod yo'lini davom ettira olmadi.\n\n✍️ MASHQ:\n1. \"Kutubxonadan olingan barcha kitoblar\" → ✅ All books taken from the library\n2. Past Participle aniq nisbatdami yoki majhul? → ✅ Majhul (Passive).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Participle (-ed/V3) majhul ma'nodagi aniqlovchi va hol bo'lib keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi past participleni bilasiz! Ertaga: Perfect Participle. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_312",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 42,
            titleEnglish = "312-Dars: Perfect Participle (Having finished...)",
            titleUzbek = "B2 Sifatdosh — Perfect Participle (having + P.P.)",
            objectiveUzbek = "🤖 TalkMe: Bugun Perfect Participle — having + P.P. (kesimdan oldin tugagan harakat) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPerfect Participle Active (having + P.P.) kesimdagi harakatdan OLDIN sodir bo'lgan harakatni bildiradi va sabab yoki payt holi bo'lib keladi:\n• Having finished his work, he went home. — Ishlarini tugatgach, u uyga ketdi.\n• Having lost the key, he could not enter the house. — Kalitni yo'qotib, u uyga kira olmadi.\n\n💬 MISOLLAR:\n• Having lived in London for many years, he knew that city very well. — Londonda ko'p yillar yashaganligi sababli, u bu shaharni juda yaxshi bilardi.\n• Having finished his work, he went home. — Ishlarini tugatgach, u uyga ketdi.\n• Having lost the key, he could not enter the house. — Kalitni yo'qotib, u uyga kira olmadi.\n\n✍️ MASHQ:\n1. \"Ishlarini tugatgach, u uyga ketdi\" → ✅ Having finished his work, he went home.\n2. \"Kalitni yo'qotib, u uyga kira olmadi\" → ✅ Having lost the key, he could not enter the house.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Perfect Participle = having + P.P. (oldin sodir bo'lgan harakat, payt va sabab holi).\n\n🤖 TalkMe yakuniy: Zo'r! Endi perfect participleni bilasiz! Ertaga: sifatdosh vazifalari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_313",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 43,
            titleEnglish = "313-Dars: Syntactic Functions of Participles",
            titleUzbek = "B2 Sifatdosh — Gapdagi vazifalari",
            objectiveUzbek = "🤖 TalkMe: Bugun sifatdoshning gapdagi barcha vazifalarini (aniqlovchi, hol, mustaqil birikma) tizimlashtiramiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1) Aniqlovchi (oldin/keyin): a broken cup, the man sitting at the window.\n2) Payt holi: Arriving at the station, I called a porter.\n3) Sabab holi: Knowing English well, he translated the article.\n4) Ravish holi: He sat reading a newspaper.\n\n💬 MISOLLAR:\n• A broken cup lay on the table.\n• The man sitting at the window is my brother.\n• Arriving at the station, I called a porter.\n• He sat in the armchair reading a newspaper.\n\n✍️ MASHQ:\n1. \"U kresloda gazeta o'qib o'tirar edi\" → ✅ He sat in the armchair reading a newspaper.\n2. \"Stansiyaga yetib kelib, men hammol chaqirdim\" → ✅ Arriving at the station, I called a porter.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sifatdosh aniqlovchi va hol (payt, sabab, ravish) vazifalarida keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: mustaqil sifatdoshli birikmalar (Absolute Participial Constructions). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_314",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 44,
            titleEnglish = "314-Dars: Absolute Participial Constructions",
            titleUzbek = "B2 Sifatdosh — Mustaqil sifatdoshli birikmalar",
            objectiveUzbek = "🤖 TalkMe: Bugun mustaqil sifatdoshli birikmalarni (The sun having risen...) o'rganamiz va sifatdoshni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMustaqil sifatdoshli birikmada (Absolute Participial Construction) sifatdoshning o'z alohida egasi bo'ladi:\n• The sun having risen, they continued their way. — Quyosh chiqqandan keyin ular yo'llarini davom ettirishdi.\n• The professor being ill, the lecture was put off. — Professor kasalligi sababli leksiya qoldirildi.\n• It being Sunday, the library was closed. — Yakshanba bo'lganligi sababli kutubxona yopiq edi.\n\n💬 MISOLLAR:\n• The student knowing English well, the examination did not last long.\n• My sister having lost the key, we could not enter the house.\n• The sun having risen, they continued their way.\n• It being Sunday, the library was closed.\n\n✍️ MASHQ:\n1. \"Quyosh chiqqandan keyin ular yo'llarini davom ettirishdi\" → ✅ The sun having risen, they continued their way.\n2. \"Yakshanba bo'lganligi sababli kutubxona yopiq edi\" → ✅ It being Sunday, the library was closed.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Mustaqil sifatdoshli birikma: o'z egasi + sifatdosh (The sun having risen...). B2 sifatdosh tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B2 sifatdoshni to'liq o'zlashtirdingiz! Ertaga: B2 Complex Object! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_315",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 45,
            titleEnglish = "315-Dars: Complex Object — Introduction (I want you to go)",
            titleUzbek = "B2 Complex Object — Murakkab to'ldiruvchi nima?",
            objectiveUzbek = "🤖 TalkMe: Endi Complex Object — \"murakkab to'ldiruvchi\" (I want you to go) ni boshlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nComplex Object ikki qismdan iborat: 1) Ot yoki obyekt olmoshi (me, you, him, her, us, them) + 2) Infinitiv yoki sifatdosh.\n• I want you to go. — Men sizning borishingizni istayman.\n• I saw him leave. — Men uning ketganini ko'rdim.\n\n💬 MISOLLAR:\n• I want you to go. — Men sizning borishingizni istayman.\n• She wants me to help her. — U mening unga yordam berishimni istaydi.\n• We expect you to come early. — Biz sizning erta kelishingizni kutamiz.\n\n✍️ MASHQ:\n1. \"Men sizning borishingizni istayman\" → ✅ I want you to go.\n2. \"U mening unga yordam berishimni istaydi\" → ✅ She wants me to help her.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Complex Object = fe'l + ot/obyekt olmosh + (to) V / V-ing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Complex Object nima ekanligini bilasiz! Ertaga: want / expect + to V. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_316",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 46,
            titleEnglish = "316-Dars: Complex Object with Want, Expect, Would Like",
            titleUzbek = "B2 Complex Object — want, expect, would like + to V",
            objectiveUzbek = "🤖 TalkMe: Bugun want, expect, would like fe'llari bilan Complex Objectni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nWant, expect, would like fe'llaridan keyin Complex Objectda infinitiv har doim 'to' bilan keladi:\n• I want my brother to begin learning French.\n• We expect the contract to be signed today.\n\n💬 MISOLLAR:\n• I want my brother to begin learning French. — Men ukamning fransuz tilini o'rganishni boshlashini istayman.\n• I expect you to be in the office earlier tomorrow. — Ertaga ofisga ertaroq kelishingizni kutaman.\n• They want the goods to be delivered in May. — Ular mollarning may oyida yetkazib berilishini istaydilar.\n\n✍️ MASHQ:\n1. \"Biz shartnomaning bugun imzolanishini kutamiz\" → ✅ We expect the contract to be signed today.\n2. Want va expect dan keyin 'to' ishlatiladimi? → ✅ Ha, albatta.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: want / expect / would like + kim/nima + to V (yoki to be + P.P.).\n\n🤖 TalkMe yakuniy: Zo'r! Endi want/expect ni bilasiz! Ertaga: see/watch/hear bilan Complex Object. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_317",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 47,
            titleEnglish = "317-Dars: Complex Object with See, Watch, Hear",
            titleUzbek = "B2 Complex Object — see, watch, hear + V / V-ing",
            objectiveUzbek = "🤖 TalkMe: Bugun sezgi fe'llari bilan Complex Objectni mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• See/watch/hear + kim + V (to siz) — to'liq, tugallangan harakat: I saw him leave the room.\n• See/watch/hear + kim + V-ing — davom etayotgan jarayon: I saw him leaving the room.\n• Majhul nisbatda esa 'to' tiklanadi: He was seen to leave the room.\n\n💬 MISOLLAR:\n• I saw him leave the room. — Men uning xonadan chiqqanini ko'rdim.\n• I heard somebody calling my name. — Men kimdir ismimni aytib chaqirayotganini eshitdim.\n• He was seen to leave the room. — Uning xonadan chiqqanini ko'rishgan.\n\n✍️ MASHQ:\n1. \"Men kimdir ismimni aytib chaqirayotganini eshitdim\" → ✅ I heard somebody calling my name.\n2. \"Uning xonadan chiqqanini ko'rishgan\" (majhul) → ✅ He was seen to leave the room.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: See/watch/hear + kim + V (to siz) yoki V-ing. Majhulda: was seen to V.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: have + narsa + P.P. (Causative). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_318",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 48,
            titleEnglish = "318-Dars: Causative Have — have something done",
            titleUzbek = "B2 Complex Object — have + narsa + P.P. (Qildirmoq)",
            objectiveUzbek = "🤖 TalkMe: Bugun 'I have my hair cut' (Sochimni oldiraman — boshqa birovga qildirmoq) qurilmasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nHave + ot + Past Participle (V3) — ish-harakat ega tomonidan emas, ega uchun boshqa usta/mutaxassis tomonidan bajarilishini bildiradi:\n• I have my hair cut. — Men sochimni oldiraman.\n• I had my watch repaired yesterday. — Kecha soatimni tuzattirdim.\n\n💬 MISOLLAR:\n• I have my hair cut at this hairdresser's. — Men shu sartaroshxonada soch oldiraman.\n• I had my watch repaired yesterday. — Kecha men soatimni tuzattirdim.\n• I want to have the walls of my room painted. — Men xonamning devorini bo'yattirmoqchiman.\n\n✍️ MASHQ:\n1. \"Kecha men soatimni tuzattirdim\" → ✅ I had my watch repaired yesterday.\n2. \"Men sochimni oldirmoqchiman\" → ✅ I am going to have my hair cut.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Have + narsa + P.P. — biror ishni boshqa kishiga bajartirmoq (tuzattirmoq, oldirmoq).\n\n🤖 TalkMe yakuniy: Zo'r! Endi have + P.P. ni bilasiz! Ertaga: Complex Object passive. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_319",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 49,
            titleEnglish = "319-Dars: Complex Object with Passive Infinitive",
            titleUzbek = "B2 Complex Object — Passive (want the work to be done)",
            objectiveUzbek = "🤖 TalkMe: Bugun Complex Object passive (to be + P.P.) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nWant, expect, wish, would like fe'llaridan keyin narsa + to be + P.P. (yoki faqat P.P.) keladi:\n• He wants the work to be done immediately. = He wants the work done immediately.\n\n💬 MISOLLAR:\n• They want the goods to be delivered in May. — Ular mollarning may oyida yetkazib berilishini istaydilar.\n• We expect the contract to be signed today. — Biz shartnomaning bugun imzolanishini kutamiz.\n• He wants the work done immediately. — U ishning darhol bajarilishini istaydi.\n\n✍️ MASHQ:\n1. \"U ishning darhol bajarilishini istaydi\" → ✅ He wants the work to be done immediately.\n2. \"Ular mollarning may oyida yetkazib berilishini istaydilar\" → ✅ They want the goods to be delivered in May.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Complex Object passive: want/expect + narsa + (to be) + P.P.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: 320-dars — Partiya 16 yakuni! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_320",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 50,
            titleEnglish = "320-Dars: B2 Non-Finite Forms Mastery Review",
            titleUzbek = "320-Dars: Gerund, Sifatdosh va Complex Object Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun Partiya 16 ni (Gerund, Sifatdosh va Complex Object) umumiy takrorlaymiz va yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Gerund: fond of reading, enjoy reading, having seen.\n• Sifatdosh: a broken cup, knowing English well, having finished his work, the sun having risen.\n• Complex Object: want you to go, saw him leave, had my watch repaired.\n\n💬 MISOLLAR:\n• I am fond of reading.\n• Having finished his work, he went home.\n• I want you to go.\n• I had my watch repaired yesterday.\n\n✍️ MASHQ:\n1. \"Ishlarini tugatgach, u uyga ketdi\" → ✅ Having finished his work, he went home.\n2. \"Kecha men soatimni tuzattirdim\" → ✅ I had my watch repaired yesterday.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Partiya 16 (301–320-darslar: Gerund, Sifatdosh, Complex Object) to'liq yakunlandi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz Partiya 16 ni to'liq tugatdingiz! Ertaga: Partiya 17 — B2 Ergash gaplar, Ko'chirma gap va C1 Infinitivli nominativ! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 17 — B2 ERGASH GAPLAR VA KO'CHIRMA GAP (Dars 321–336, orderIndex 51..66)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_12_321",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 51,
            titleEnglish = "321-Dars: Adverbial Clauses of Time — When",
            titleUzbek = "B2 Ergash gap — Payt: when",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Partiya 17 ni boshlaymiz! Payt ergash gap — when! 🚀",
            explanationUzbek = "📖 QOIDA:\nPayt ergash gaplarda (when, while, after, before, as soon as, until) kelasi zamon o'rnida HOZIRGI ZAMON (Present Simple yoki Present Perfect) ishlatiladi:\n• When he comes, I will tell him. (when he will come EMAS!).\n\n💬 MISOLLAR:\n• When we arrived at the port, the steamer was being loaded. — Biz portga kelganimizda paroxodga yuk ortilayotgan edi.\n• When he comes, I will tell him. — U kelganida, men unga aytaman.\n• When you have finished your work, call me. — Ishingizni tugatganingizda, menga qo'ng'iroq qiling.\n\n✍️ MASHQ:\n1. \"U kelganida, men unga aytaman\" → ✅ When he comes, I will tell him.\n2. Payt ergash gapda will ishlatiladimi? → ✅ Yo'q, hozirgi zamon ishlatiladi.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Payt ergash gapda kelasi zamon o'rnida hozirgi zamon keladi (When he comes...).\n\n🤖 TalkMe yakuniy: Zo'r! Endi when ni bilasiz! Ertaga: while va after. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_322",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 52,
            titleEnglish = "322-Dars: Time Clauses — While & After",
            titleUzbek = "B2 Ergash gap — Payt: while va after",
            objectiveUzbek = "🤖 TalkMe: Bugun while (davomida) va after (keyin) payt ergash gaplarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• While — bir vaqtda davom etayotgan harakat (Continuous bilan): While I was reading, he was watching TV.\n• After — ketma-ket harakat (Past Perfect yoki Present Perfect bilan): After I had finished my work, I went home.\n\n💬 MISOLLAR:\n• While we were discharging the vessel, we discovered a few bags.\n• While I was reading, he was watching TV.\n• After the agreement had been signed, the delegation left London.\n\n✍️ MASHQ:\n1. \"Men o'qiyotganimda u televizor ko'rayotgan edi\" → ✅ While I was reading, he was watching TV.\n2. \"Ishimni tugatganimdan keyin uyga ketdim\" → ✅ After I had finished my work, I went home.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: While — jarayon davomida; After — harakat tugagandan keyin.\n\n🤖 TalkMe yakuniy: Zo'r! Endi while/after ni bilasiz! Ertaga: before va as soon as. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_323",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 53,
            titleEnglish = "323-Dars: Time Clauses — Before & As Soon As",
            titleUzbek = "B2 Ergash gap — Payt: before va as soon as",
            objectiveUzbek = "🤖 TalkMe: Bugun before (oldin) va as soon as (-ishi bilanoq) bog'lovchilarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Before — \"oldin\": I had finished my work before he returned.\n• As soon as — \"-ishi bilanoq\" (kelasi zamon o'rnida Present Simple/Perfect keladi): As soon as he comes, I will tell him.\n\n💬 MISOLLAR:\n• I had finished my work before he returned. — U qaytishidan oldin ishimni tugatgan edim.\n• As soon as he comes, I will tell him. — U kelishi bilanoq, men unga aytaman.\n• I shall go to the country as soon as I have passed my examinations. — Imtihonlarimni topshiriboq qishloqqa boraman.\n\n✍️ MASHQ:\n1. \"U kelishi bilanoq, men unga aytaman\" → ✅ As soon as he comes, I will tell him.\n2. \"U qaytishidan oldin ishimni tugatgan edim\" → ✅ I had finished my work before he returned.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Before — oldin; As soon as — bilanoq (hozirgi zamon bilan).\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: till, until va since. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_324",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 54,
            titleEnglish = "324-Dars: Time Clauses — Till, Until & Since",
            titleUzbek = "B2 Ergash gap — Payt: till, until va since",
            objectiveUzbek = "🤖 TalkMe: Bugun till/until (-gacha) va since (-dan buyon) ergash gaplarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Till / Until — \"-gacha\" (tugash nuqtasi, bo'lishli fe'l bilan!): Let us wait until the rain stops.\n• Since — \"-dan buyon\" (boshlanish nuqtasi, bosh gapda Present Perfect): We haven't had any news from him since he left Tashkent.\n\n💬 MISOLLAR:\n• I shall stay here until you return. — Siz qaytib kelguningizcha shu yerda bo'laman.\n• Let us wait until the rain stops. — Yomg'ir to'xtaguncha kutib turaylik.\n• We haven't had any news from him since he left Tashkent. — U Toshkentdan ketganidan buyon undan xabar olganimiz yo'q.\n\n✍️ MASHQ:\n1. \"Yomg'ir to'xtaguncha kutib turaylik\" → ✅ Let us wait until the rain stops.\n2. \"Siz qaytib kelguningizcha shu yerda bo'laman\" → ✅ I shall stay here until you return.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Until — tugash nuqtasi; Since — boshlanish nuqtasi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi till/until/since ni bilasiz! Ertaga: sabab ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_325",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 55,
            titleEnglish = "325-Dars: Clauses of Cause — Because, As, Since, Now that",
            titleUzbek = "B2 Ergash gap — Sabab: because, as, since, now that",
            objectiveUzbek = "🤖 TalkMe: Bugun sabab ergash gap bog'lovchilarini (because, as, since, now that) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Because — eng kuchli sabab (ko'pincha gapning ikkinchi qismida).\n• As / Since — \"-ganligi sababli\" (ko'pincha gap boshida).\n• Now that — \"modomiki endi...\".\n\n💬 MISOLLAR:\n• I went away because there was no one there.\n• As there were no porters, we had to carry the luggage ourselves.\n• Since you have finished your work, you may go home.\n• Now that he is here, he can help you.\n\n✍️ MASHQ:\n1. \"Ishingizni tugatgan bo'lsangiz, uyingizga ketishingiz mumkin\" → ✅ Since you have finished your work, you may go home.\n2. \"U shu yerda ekan, sizga yordam bera oladi\" → ✅ Now that he is here, he can help you.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Sabab ergash gap: because, as, since, for, now that.\n\n🤖 TalkMe yakuniy: Zo'r! Endi sabab ergash gapni bilasiz! Ertaga: shart ergash gap (real). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_326",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 56,
            titleEnglish = "326-Dars: First Conditional — Real Condition with If",
            titleUzbek = "B2 Ergash gap — Shart: If (Real shart)",
            objectiveUzbek = "🤖 TalkMe: Bugun real shart ergash gapni (If + Present Simple, Future Simple) mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nReal shart (amalga oshishi mumkin bo'lgan shart):\n• If + Present Simple, ... will + V.\n• If the weather is fine tomorrow, we shall go to the country.\n\n💬 MISOLLAR:\n• If the weather is fine tomorrow, we shall go to the country. — Agar ertaga havo yaxshi bo'lsa, qishloqqa boramiz.\n• If he comes, I shall ask him about it. — Agar u kelsa, undan bu haqda so'rayman.\n• If you see him, ask him to ring me up. — Agar uni ko'rsangiz, menga qo'ng'iroq qilishini ayting.\n\n✍️ MASHQ:\n1. \"Agar ertaga havo yaxshi bo'lsa, qishloqqa boramiz\" → ✅ If the weather is fine tomorrow, we shall go to the country.\n2. \"Agar u kelsa, undan bu haqda so'rayman\" → ✅ If he comes, I shall ask him about it.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Real shart: If + Present Simple, ... Future Simple / Imperative.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: unless, provided that, on condition that. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_327",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 57,
            titleEnglish = "327-Dars: Conditionals with Unless, Provided That, On Condition That",
            titleUzbek = "B2 Ergash gap — Shart: unless, provided that, on condition that",
            objectiveUzbek = "🤖 TalkMe: Bugun unless (agar ...-masa), provided that va on condition that (shu shart bilanki) bog'lovchilarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Unless = if ... not (agar ... -masa): Unless he is invited, he won't go there.\n• Provided (that) / On condition (that) — \"shu shart bilanki, agar\": I'll lend you the book on condition that you return it on Monday.\n• Supposing (that) — \"faraz qilaylik\".\n\n💬 MISOLLAR:\n• Unless he is invited, he won't go there. — Agar uni taklif qilishmasa, u yerga bormaydi.\n• I'll lend you the book on condition that you return it on Monday. — Dushanba kuni qaytarish sharti bilan kitobni berib turaman.\n• Unless you study hard, you will fail the exam. — Agar qattiq o'qimasangiz, imtihondan yiqilasiz.\n\n✍️ MASHQ:\n1. \"Agar uni taklif qilishmasa, u yerga bormaydi\" → ✅ Unless he is invited, he won't go there.\n2. Unless nimaga teng? → ✅ if ... not",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Unless (agar ...-masa), provided that, on condition that, supposing that.\n\n🤖 TalkMe yakuniy: Zo'r! Endi shart bog'lovchilarini to'liq bilasiz! Ertaga: maqsad ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_328",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 58,
            titleEnglish = "328-Dars: Clauses of Purpose — So that, In order that",
            titleUzbek = "B2 Ergash gap — Maqsad: so that, in order that",
            objectiveUzbek = "🤖 TalkMe: Bugun maqsad ergash gapni (so that ... may/might/should) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMaqsad ergash gap so that yoki in order that bilan bog'lanadi, kesimida may (hozirgi/kelasi), might (o'tgan) yoki should keladi:\n• The teacher speaks slowly so that his pupils may understand him.\n• I gave him the text-book so that he might learn his lesson.\n\n💬 MISOLLAR:\n• I gave him the text-book so that he might learn his lesson. — U darsini o'rganishi uchun unga darslikni berdim.\n• The teacher speaks slowly so that his pupils may understand him. — O'quvchilari tushunishi uchun o'qituvchi sekin gapiradi.\n• I am going early so as to get a good seat. — Yaxshi joy olish uchun erta boryapman.\n\n✍️ MASHQ:\n1. \"U darsini o'rganishi uchun unga darslikni berdim\" → ✅ I gave him the text-book so that he might learn his lesson.\n2. \"Yaxshi joy olish uchun erta boryapman\" → ✅ I am going early so as to get a good seat.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Maqsad ergash gap: so that / in order that + may/might/should + V.\n\n🤖 TalkMe yakuniy: Zo'r! Endi maqsad ergash gapni bilasiz! Ertaga: natija ergash gap. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_329",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 59,
            titleEnglish = "329-Dars: Clauses of Result — So ... that & Such ... that",
            titleUzbek = "B2 Ergash gap — Natija: so...that va such...that",
            objectiveUzbek = "🤖 TalkMe: Bugun natija ergash gapni (so + sifat/ravish + that, such + ot + that) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• so + sifat/ravish + that: The sea was so stormy that the vessel could not leave the port.\n• such + (a/an) + sifat + ot + that: It was such an interesting book that I read it twice.\n\n💬 MISOLLAR:\n• The sea was so stormy that the vessel could not leave the port. — Dengiz shunday bo'ronli ediki, kema portdan chiqa olmadi.\n• He was so tired that he fell asleep immediately. — U shunday charchagan ediki, darhol uxlab qoldi.\n• It was such an interesting book that I read it twice. — Bu shunday qiziq kitob ediki, uni ikki marta o'qidim.\n\n✍️ MASHQ:\n1. \"U shunday charchagan ediki, darhol uxlab qoldi\" → ✅ He was so tired that he fell asleep immediately.\n2. \"Bu shunday qiziq kitob ediki, uni ikki marta o'qidim\" → ✅ It was such an interesting book that I read it twice.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Natija ergash gap: so + sifat/ravish + that; such + (a/an) + sifat + ot + that.\n\n🤖 TalkMe yakuniy: Zo'r! Endi natija ergash gapni bilasiz! Ertaga: B2 ergash gaplar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_330",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 60,
            titleEnglish = "330-Dars: B2 Adverbial Clauses Review",
            titleUzbek = "B2 Ergash gaplar — Umumiy takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun B2 ergash gaplarning barcha 5 turini (payt, sabab, shart, maqsad, natija) takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Payt: when, while, after, before, as soon as, until, since.\n• Sabab: because, as, since, now that.\n• Shart: if, unless, provided that, on condition that.\n• Maqsad: so that, in order that + may/might/should.\n• Natija: so ... that, such ... that.\n\n💬 MISOLLAR:\n• When he comes, I will tell him.\n• Unless he is invited, he won't go.\n• I gave him the book so that he might study.\n• He was so tired that he fell asleep.\n\n✍️ MASHQ:\n1. \"U kelganida, men unga aytaman\" → ✅ When he comes, I will tell him.\n2. \"U shunday charchagan ediki, darhol uxlab qoldi\" → ✅ He was so tired that he fell asleep.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B2 ergash gaplar to'liq tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B2 ergash gaplarni to'liq o'zlashtirdingiz! Ertaga: B2 ko'chirma gap — zamonlar moslashuvi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_331",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 61,
            titleEnglish = "331-Dars: Sequence of Tenses in Reported Speech",
            titleUzbek = "B2 Ko'chirma gap — Zamon o'zgarishi (Sequence of Tenses)",
            objectiveUzbek = "🤖 TalkMe: Endi ko'chirma gapda zamonlar moslashuvini (Sequence of Tenses) to'liq o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBosh gap o'tgan zamonda (He said) bo'lsa:\n• Present Simple → Past Simple (am → was)\n• Present Continuous → Past Continuous (am reading → was reading)\n• Present Perfect → Past Perfect (have finished → had finished)\n• Past Simple → Past Perfect (bought → had bought)\n• Future Simple → Future-in-the-Past (will come → would come).\n\n💬 MISOLLAR:\n• \"I have finished.\" → He said (that) he had finished.\n• \"I bought a book.\" → He said (that) he had bought a book.\n• \"I will come.\" → He said (that) he would come.\n\n✍️ MASHQ:\n1. \"I bought a book.\" → ✅ He said (that) he had bought a book.\n2. \"I have finished.\" → ✅ He said (that) he had finished.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Present → Past; Past / Present Perfect → Past Perfect; will → would.\n\n🤖 TalkMe yakuniy: Zo'r! Endi zamon o'zgarishini to'liq bilasiz! Ertaga: ravishlar va ko'rsatish olmoshlari o'zgarishi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_332",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 62,
            titleEnglish = "332-Dars: Time & Place Shifts in Reported Speech",
            titleUzbek = "B2 Ko'chirma gap — Olmosh va Ravishlar o'zgarishi",
            objectiveUzbek = "🤖 TalkMe: Bugun o'zlashtirma gapda vaqt va joy so'zlarining o'zgarishini (tomorrow → the next day) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• this → that | these → those | here → there | now → then\n• today → that day | tomorrow → the next day\n• yesterday → the day before | ago → before.\n\n💬 MISOLLAR:\n• \"I shall write the letter tomorrow.\" → He said he would write the letter the next day.\n• \"I saw Nancy yesterday.\" → He said he had seen Nancy the day before.\n• \"I am busy now.\" → He said he was busy then.\n\n✍️ MASHQ:\n1. \"Tomorrow\" o'zlashtirma gapda qanday o'zgaradi? → ✅ the next day\n2. \"Yesterday\" qanday o'zgaradi? → ✅ the day before",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: this → that, here → there, now → then, tomorrow → the next day, yesterday → the day before.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: o'zlashtirma so'roq gap — to'liq. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_333",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 63,
            titleEnglish = "333-Dars: Advanced Reported Questions",
            titleUzbek = "B2 Ko'chirma gap — So'roq: To'liq",
            objectiveUzbek = "🤖 TalkMe: Bugun murakkab so'roq gaplarni o'zlashtirma gapga aylantirishni mashq qilamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• He asked me, \"Where were you yesterday?\" → He asked me where I had been the day before.\n• He asked me, \"When will they send the letter?\" → He asked me when they would send the letter.\n\n💬 MISOLLAR:\n• He asked me where I had been the day before.\n• He asked me when they would send the letter.\n• He asked me who had showed me his work.\n\n✍️ MASHQ:\n1. He asked me, \"Where were you yesterday?\" → ✅ He asked me where I had been the day before.\n2. He asked me, \"When will they send the letter?\" → ✅ He asked me when they would send the letter.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'zlashtirma so'roqda zamon, ravishlar va so'z tartibi (ega + kesim) o'zgaradi.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: buyruq gap — to'liq. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_334",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 64,
            titleEnglish = "334-Dars: Advanced Reported Commands & Requests",
            titleUzbek = "B2 Ko'chirma gap — Buyruq va Iltimos: To'liq",
            objectiveUzbek = "🤖 TalkMe: Bugun buyruq va iltimos gaplarning barcha turlarini o'zlashtirma gapda mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• tell / order / ask + kim + to + V (bo'lishli)\n• tell / order / ask + kim + not to + V (bo'lishsiz).\n\n💬 MISOLLAR:\n• She told Tom to come at five o'clock.\n• He told me not to go there.\n• She told him not to be late.\n• The teacher told us to read that book.\n\n✍️ MASHQ:\n1. She said to him, \"Don't be late.\" → ✅ She told him not to be late.\n2. He said, \"Wait for me.\" → ✅ He told me to wait for him.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Buyruq o'zlashtirma gap: told/asked + kim + (not) to + V.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: B2 ko'chirma gap takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_335",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 65,
            titleEnglish = "335-Dars: B2 Reported Speech Comprehensive Review",
            titleUzbek = "B2 Ko'chirma gap — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun B2 ko'chirma gapning barcha qoidalarini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Darak: He said (that) he had bought a book.\n• Maxsus so'roq: He asked where I had been the day before.\n• Umumiy so'roq: He asked if I spoke English.\n• Buyruq: He told me not to go there.\n\n💬 MISOLLAR:\n• He said that he was busy.\n• He asked where they lived.\n• He asked if I spoke English.\n• He told me not to go there.\n\n✍️ MASHQ:\n1. He asked, \"Do you speak English?\" → ✅ He asked if I spoke English.\n2. He said, \"Don't go there.\" → ✅ He told me not to go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B2 ko'chirma gap qoidalari mustahkamlandi!\n\n🤖 TalkMe yakuniy: Ajoyib! Ertaga: 336-dars — B2 darajasining to'liq yakuni! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_12_336",
            topicId = "topic_12_b2_modals_nonfinite",
            orderIndex = 66,
            titleEnglish = "336-Dars: B2 Level Complete Mastery!",
            titleUzbek = "336-Dars: B2 Darajasi To'liq Yakuni!",
            objectiveUzbek = "🤖 TalkMe: TABRIKLAYMIZ! Bugun 336-darsda B2 (Upper-Intermediate) darajasini to'liq yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSiz B2 darajasining barcha mavzularini o'zlashtirdingiz:\n• Barcha Continuous va Perfect Continuous zamonlar\n• Modal fe'llar va Modal + have + P.P.\n• Infinitiv, Gerund, Sifatdosh va Complex Object\n• Ergash gaplar va Ko'chirma gap (Sequence of Tenses).\n\n💬 MISOLLAR:\n• \"I bought a book.\" → He said he had bought a book.\n• \"I will come tomorrow.\" → He said he would come the next day.\n\n✍️ MASHQ:\n1. \"I will come tomorrow.\" → ✅ He said he would come the next day.\n2. \"I bought a book.\" → ✅ He said he had bought a book.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B2 DARAJASI TO'LIQ TUGADI! Endi C1 (Advanced) darajasiga o'tamiz!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz B2 darajasini to'liq zabt etdingiz! Keyingi darsdan C1 darajasini boshlaymiz! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 17 — C1 INFINITIVLI NOMINATIV (Dars 337–340, topic_13_c1_c2_mastery, orderIndex 1..4)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_13_337",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 1,
            titleEnglish = "337-Dars: C1 Nominative with the Infinitive (He is said to...)",
            titleUzbek = "C1 Infinitivli nominativ — He is said to...",
            objectiveUzbek = "🤖 TalkMe: C1 (Advanced) darajasiga xush kelibsiz! Bugun 337-darsda akademik ingliz tilining durdonasi — Infinitivli nominativ (He is said to...) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nInfinitivli nominativ (Complex Subject): Ega + majhul kesim (is said / is reported / is known / is expected) + to + V.\n• He is said to live in London. = It is said that he lives in London (Aytishlaricha, u Londonda yashaydi).\n\n💬 MISOLLAR:\n• He is said to live in London. — Aytishlaricha u Londonda yashaydi.\n• The delegation is reported to have left Tashkent. — Xabar berilishicha delegatsiya Toshkentdan jo'nab ketgan.\n• This plant is known to produce tractors. — Bu zavod traktor ishlab chiqarishi ma'lum.\n• He is expected to arrive in the evening. — Uning kechqurun kelishi kutilmoqda.\n\n✍️ MASHQ:\n1. \"Aytishlaricha u Londonda yashaydi\" → ✅ He is said to live in London.\n2. \"Uning kechqurun kelishi kutilmoqda\" → ✅ He is expected to arrive in the evening.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Infinitivli nominativ = Ega + majhul fe'l (is said / is known) + infinitiv.\n\n🤖 TalkMe yakuniy: Zo'r! C1 darajasini ajoyib boshladingiz! Ertaga: They are believed to... va infinitiv shakllari. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_338",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 2,
            titleEnglish = "338-Dars: C1 Complex Subject with All Infinitive Forms",
            titleUzbek = "C1 Infinitivli nominativ — They are believed to...",
            objectiveUzbek = "🤖 TalkMe: Bugun infinitivli nominativda Simple, Continuous va Perfect infinitiv shakllarini qo'llashni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bir vaqtda: He is said to live in London.\n• Hozir davom etayotgan: He is said to be writing a new play.\n• Oldin sodir bo'lgan: He is said to have lived in Brighton.\n• Majhul: He is said to have been appointed director.\n\n💬 MISOLLAR:\n• They are believed to be on their way to Samarkand. — Ularni Samarqandga ketyapti deb hisoblashadi.\n• He is said to be writing a new play. — Aytishlaricha u yangi pyesa yozayotgan ekan.\n• The water seems to be boiling. — Suv qaynayotganga o'xshaydi.\n• He is said to have been appointed director. — Aytishlaricha u direktor etib tayinlangan.\n\n✍️ MASHQ:\n1. \"Aytishlaricha u yangi pyesa yozayotgan ekan\" → ✅ He is said to be writing a new play.\n2. \"Suv qaynayotganga o'xshaydi\" → ✅ The water seems to be boiling.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Complex Subject barcha infinitiv shakllari (to live, to be writing, to have lived) bilan ishlatiladi.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: C1 mustaqil infinitivli birikma. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_339",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 3,
            titleEnglish = "339-Dars: C1 Absolute Infinitive Construction",
            titleUzbek = "C1 Mustaqil infinitivli birikma (delivery to be made...)",
            objectiveUzbek = "🤖 TalkMe: Bugun rasmiy va tijorat hujjatlarida uchraydigan Mustaqil infinitivli birikmani o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMustaqil infinitivli birikma (Absolute Infinitive Construction): Bosh kelishikdagi ot + to + V (yoki to be + P.P.). Gap oxirida verguldan keyin keladi:\n• The sellers offered 5000 tons of gas oil, delivery to be made in October.\nShuningdek, ot + to be + P.P. aniqlovchi vazifasida ham keladi: The work to be done is very difficult.\n\n💬 MISOLLAR:\n• The sellers offered the buyers 5000 tons of gas oil, delivery to be made in October.\n• The work to be done is very difficult. — Bajarilishi kerak bo'lgan ish juda qiyin.\n• The goods to be shipped are in the warehouse. — Yuklanishi kerak bo'lgan mollar omborda.\n• The contract to be signed is on the table. — Imzolanishi kerak bo'lgan shartnoma stolda.\n\n✍️ MASHQ:\n1. \"Bajarilishi kerak bo'lgan ish juda qiyin\" → ✅ The work to be done is very difficult.\n2. \"Imzolanishi kerak bo'lgan shartnoma stolda\" → ✅ The contract to be signed is on the table.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Mustaqil infinitivli birikma: ot + to be + P.P. (delivery to be made in October).\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: 340-dars — C1 infinitivli nominativ takrorlash va Partiya 17 yakuni! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_340",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 4,
            titleEnglish = "340-Dars: C1 Nominative & Infinitive Constructions Review",
            titleUzbek = "340-Dars: C1 Infinitivli nominativ — Takrorlash va Yakun",
            objectiveUzbek = "🤖 TalkMe: Bugun 340-dars! C1 infinitivli nominativ va mustaqil birikmani takrorlaymiz hamda Partiya 17 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• He is said to live in London. (= It is said that he lives in London.)\n• They are believed to be on their way.\n• The delegation is reported to have left Tashkent.\n• Delivery to be made in October.\n\n💬 MISOLLAR:\n• He is said to live in London.\n• They are believed to be on their way.\n• The delegation is reported to have left Tashkent.\n• The goods are reported to have been awaiting shipment for several days.\n\n✍️ MASHQ:\n1. \"Aytishlaricha u Londonda yashaydi\" → ✅ He is said to live in London.\n2. \"Xabar berilishicha delegatsiya Toshkentdan jo'nab ketgan\" → ✅ The delegation is reported to have left Tashkent.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Partiya 17 (321–340-darslar) to'liq yakunlandi! Siz 340 ta darsni tugatdingiz!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz 340 ta darsni zabt etdingiz! Faqat 60 ta dars (Partiya 18–20: C1 va C2 cho'qqisi) qoldi! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity("voc_p16_01", "topic_12_b2_modals_nonfinite", "sec_12_302", "Drawer", "g'aladon, tortma", "I put the letter into the drawer.", "Men xatni g'aladonga solib qo'ydim.", isStudied = false),
        VocabularyEntity("voc_p16_02", "topic_12_b2_modals_nonfinite", "sec_12_303", "Occupation", "mashg'ulot, kasb", "Reading is her favourite occupation.", "O'qish uning sevimli mashg'uloti.", isStudied = false),
        VocabularyEntity("voc_p16_03", "topic_12_b2_modals_nonfinite", "sec_12_307", "Cargo", "yuk (kema yuki)", "The buyer insured the cargo.", "Xaridor yukni sug'urta qildi.", isStudied = false),
        VocabularyEntity("voc_p16_04", "topic_12_b2_modals_nonfinite", "sec_12_310", "Porter", "hammol, yuk tashuvchi", "Arriving at the station, I called a porter.", "Stansiyaga yetib kelib, men hammol chaqirdim.", isStudied = false),
        VocabularyEntity("voc_p16_05", "topic_12_b2_modals_nonfinite", "sec_12_311", "Sleeve", "yeng (ko'ylak yengi)", "She mended the torn sleeve.", "U yirtiq yengni yamadi.", isStudied = false),
        VocabularyEntity("voc_p16_06", "topic_12_b2_modals_nonfinite", "sec_12_312", "Claim", "da'vo, talab", "We refused to admit the claim.", "Biz da'voni qabul qilishni rad etdik.", isStudied = false),
        VocabularyEntity("voc_p16_07", "topic_12_b2_modals_nonfinite", "sec_12_314", "Lecture", "leksiya, ma'ruza", "The lecture was put off.", "Ma'ruza qoldirildi.", isStudied = false),
        VocabularyEntity("voc_p16_08", "topic_12_b2_modals_nonfinite", "sec_12_318", "Luggage", "yuk, bagaj", "I must have my luggage sent to the station.", "Men yuklarimni stansiyaga yubortirishim kerak.", isStudied = false),
        VocabularyEntity("voc_p17_01", "topic_12_b2_modals_nonfinite", "sec_12_322", "Vessel", "kema", "While we were discharging the vessel, we found bags.", "Kemaning yukini tushirayotganimizda qoplarni topdik.", isStudied = false),
        VocabularyEntity("voc_p17_02", "topic_12_b2_modals_nonfinite", "sec_12_327", "Provided that", "shu shart bilanki, agar", "We can deliver provided that we receive your order.", "Buyurtmangizni olsak yetkazib bera olamiz.", isStudied = false),
        VocabularyEntity("voc_p17_03", "topic_12_b2_modals_nonfinite", "sec_12_329", "Stormy", "bo'ronli", "The sea was so stormy.", "Dengiz juda bo'ronli edi.", isStudied = false),
        VocabularyEntity("voc_p17_04", "topic_13_c1_c2_mastery", "sec_13_337", "Tractor", "traktor", "This plant is known to produce tractors.", "Bu zavod traktor ishlab chiqarishi ma'lum.", isStudied = false),
        VocabularyEntity("voc_p17_05", "topic_13_c1_c2_mastery", "sec_13_337", "Shipment", "yuklash, jo'natma", "The goods are awaiting shipment.", "Mollar yuklanishni kutmoqda.", isStudied = false),
        VocabularyEntity("voc_p17_06", "topic_13_c1_c2_mastery", "sec_13_339", "Warehouse", "ombor, omborxona", "The goods to be shipped are in the warehouse.", "Yuklanishi kerak bo'lgan mollar omborda.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "gr_12_302",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_302",
            titleUzbek = "Gerund (V-ing) — Predlog va maxsus fe'llardan keyin",
            explanationUzbek = "Barcha predloglardan keyin (of, without, after, instead of) va enjoy, finish, stop, avoid, mind, suggest fe'llaridan keyin gerund (-ing) keladi.",
            exampleEnglish = "I am fond of reading. He left without saying goodbye.",
            exampleUzbek = "Men o'qishni yaxshi ko'raman. U xayrlashmasdan ketdi.",
            associatedVocabularyIdsCsv = "voc_p16_01,voc_p16_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_12_318",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_318",
            titleUzbek = "Complex Object va Causative (have something done)",
            explanationUzbek = "want/expect + kim + to V; see/hear + kim + V/V-ing; have + narsa + P.P. (boshqa birovga qildirmoq).",
            exampleEnglish = "I want you to go. I had my watch repaired yesterday.",
            exampleUzbek = "Sizning borishingizni istayman. Kecha soatimni tuzattirdim.",
            associatedVocabularyIdsCsv = "voc_p16_08",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_13_337",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_337",
            titleUzbek = "C1 Infinitivli nominativ (He is said / reported / believed to...)",
            explanationUzbek = "Ega + majhul fe'l (is said, is known, is reported, is expected) + infinitiv (to V / to be V-ing / to have V3).",
            exampleEnglish = "He is said to live in London. The delegation is reported to have left.",
            exampleUzbek = "Aytishlaricha u Londonda yashaydi. Xabar berilishicha delegatsiya jo'nab ketgan.",
            associatedVocabularyIdsCsv = "voc_p17_04,voc_p17_05,voc_p17_06",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_12_302",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_302",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"U xayrlashmasdan ketdi\" gapining to'g'ri tarjimasini tanlang:",
            subPromptText = "302-Dars: Predlog (without) + Gerund (-ing)",
            audioTextToSpeak = "He left without saying goodbye.",
            optionsPipeSeparated = "He left without saying goodbye.|He left without say goodbye.|He left without to say goodbye.|He left without said goodbye.",
            correctAnswer = "He left without saying goodbye.",
            explanationUzbek = "Without predlogidan keyin gerund (saying) keladi.",
            vocabularyIdsCsv = "voc_p16_01",
            grammarRuleIdsCsv = "gr_12_302"
        ),
        ExerciseEntity(
            id = "ex_12_312",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_312",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"Ishlarini tugatgach, u uyga ketdi\" (Perfect Participle) gapini tanlang:",
            subPromptText = "312-Dars: Having + P.P.",
            audioTextToSpeak = "Having finished his work, he went home.",
            optionsPipeSeparated = "Having finished his work, he went home.|Finished his work, he went home.|Being finished his work, he went home.|To finish his work, he went home.",
            correctAnswer = "Having finished his work, he went home.",
            explanationUzbek = "Kesimdagi harakatdan oldin tugagan harakat Perfect Participle (Having finished) bilan ifodalanadi.",
            vocabularyIdsCsv = "voc_p16_06",
            grammarRuleIdsCsv = "gr_12_302"
        ),
        ExerciseEntity(
            id = "ex_12_318",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_318",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"Kecha men soatimni tuzattirdim\" (Causative) gapini toping:",
            subPromptText = "318-Dars: have/had + narsa + P.P.",
            audioTextToSpeak = "I had my watch repaired yesterday.",
            optionsPipeSeparated = "I had my watch repaired yesterday.|I had repaired my watch yesterday.|I had my watch repair yesterday.|I had my watch to repair yesterday.",
            correctAnswer = "I had my watch repaired yesterday.",
            explanationUzbek = "Boshqa birovga qildirish: had + my watch + repaired (P.P.).",
            vocabularyIdsCsv = "voc_p16_08",
            grammarRuleIdsCsv = "gr_12_318"
        ),
        ExerciseEntity(
            id = "ex_12_327",
            topicId = "topic_12_b2_modals_nonfinite",
            sectionId = "sec_12_327",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"Agar uni taklif qilishmasa, u u yerga bormaydi\" gapini tanlang:",
            subPromptText = "327-Dars: Unless (= if ... not)",
            audioTextToSpeak = "Unless he is invited, he won't go there.",
            optionsPipeSeparated = "Unless he is invited, he won't go there.|Unless he isn't invited, he won't go there.|Unless he will be invited, he won't go there.|If he is invited, he won't go there.",
            correctAnswer = "Unless he is invited, he won't go there.",
            explanationUzbek = "Unless o'zi inkor ma'noni ('agar ...-masa') bildiradi, shuning uchun fe'l bo'lishli (is invited) bo'ladi.",
            vocabularyIdsCsv = "voc_p17_02",
            grammarRuleIdsCsv = "gr_12_318"
        ),
        ExerciseEntity(
            id = "ex_13_337",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_337",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"Xabar berilishicha delegatsiya Toshkentdan jo'nab ketgan\" (C1 Nominative with Perfect Infinitive) gapini tanlang:",
            subPromptText = "337-Dars: is reported + to have + P.P.",
            audioTextToSpeak = "The delegation is reported to have left Tashkent.",
            optionsPipeSeparated = "The delegation is reported to have left Tashkent.|The delegation is reported left Tashkent.|The delegation reports to have left Tashkent.|The delegation is reported to leaving Tashkent.",
            correctAnswer = "The delegation is reported to have left Tashkent.",
            explanationUzbek = "Oldin sodir bo'lgan harakat uchun majhul kesimdan keyin Perfect Infinitive (to have left) keladi.",
            vocabularyIdsCsv = "voc_p17_05",
            grammarRuleIdsCsv = "gr_13_337"
        )
    )
}
