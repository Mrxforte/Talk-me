package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * Complete seed data for Partiya 18 (Lessons 341–360), Partiya 19 (Lessons 361–380),
 * and Partiya 20 (Lessons 381–400, Final Book Completion):
 * - C1 Mustaqil sifatdoshli birikma (Absolute Participial Construction, 341–342)
 * - C1 Majhul nisbat — barcha zamonlar tizimi (343)
 * - C1 Noreal shart gaplar (Present, Past, Mixed Conditionals, 344–346)
 * - C1 Wish — hozirgi va o'tgan zamon (347–348)
 * - C1 Ravish ergash gaplar (as, so...that, as if/as though, than, as...as, the...the, 349–352)
 * - C1 Bog'lovchisiz shart gaplar (Had I known, Were he here, Could he come, Should need arise, 353–356)
 * - C1 Mustaqil birikmalar va To'siqsiz ergash gaplar (though, in spite of, despite, 357–360)
 * - C1 Ko'chirma gap — chuqur tizim (361–368)
 * - C2 Inversiya, Cleft Sentences (It is...that...), Not until, Ellipsis, Fe'l takrorlash (369–374)
 * - C2 Artikl — idiomatik ma'no farqlari (a hair vs hair, a paper vs paper, a glass vs glass, etc., 375–380)
 * - C2 Fe'l va Gap tuzilishi stilistikasi (Formal vs Informal, Academic, Literary, 381–392)
 * - C2 Qo'shma va Ko'chirma gap istisnolari (in the affirmative/negative, 393–397)
 * - YAKUNIY TAKRORLASH: A1+A2 (398), B1+B2 (399), C1+C2 va 400 darslik kitob yakuni (400)
 */
object CurriculumPartiya18To20SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 18 — C1 MUSTAQIL BIRIKMA, NOREAL SHART, WISH VA INVERSION (DARS 341–360)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_13_341",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 5,
            titleEnglish = "341-Dars: Absolute Participial Construction (1)",
            titleUzbek = "C1 Mustaqil sifatdoshli birikma: The weather being fine...",
            objectiveUzbek = "🤖 TalkMe: Bugun C1 darajasining eng adabiy va ilmiy tuzilmasi — mustaqil sifatdoshli birikmani o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOddiy sifatdoshli oborotda ikkala qismning egasi BIR XIL bo'ladi:\n• Walking along the street, I met my friend. (Men yurdim va men uchratdim).\nMustaqil sifatdoshli birikmada esa sifatdoshning O'Z EGASI bo'ladi! U vergul bilan ajratiladi va ko'pincha sabab, payt yoki shart ma'nosini beradi:\n• The weather being fine, we went for a walk. — Ob-havo yaxshi bo'lgani uchun (sabab) sayrga chiqdik.\n• My sister having come, we had dinner. — Singlim kelgach (payt), kechki ovqat yedik.\n• Weather permitting, the steamer will leave tomorrow. — Ob-havo imkon bersa (shart), paroxod ertaga jo'naydi.\n\n💬 MISOLLAR:\n• The lesson being over, the students left the room. — Dars tugagach, talabalar xonadan chiqishdi.\n• The sun having risen, we continued our way. — Quyosh chiqqach, yo'limizni davom ettirdik.\n• It being very late, we decided to stay at home. — Juda kech bo'lgani uchun uyda qolishga qaror qildik.\n\n✍️ MASHQ:\n1. \"Because the weather was cold, we stayed home.\" → ✅ The weather being cold, we stayed home.\n2. \"After the work had been done, they rested.\" → ✅ The work having been done, they rested.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Mustaqil sifatdoshli birikma: O'z egasi + Participle I/II, asosiy gap. Yangi so'zlar: steamer, permit.\n\n🤖 TalkMe yakuniy: Ajoyib, [USER_NAME]! C1 adabiy sintaksisini o'zlashtiryapsiz! Ertaga: with predlogi bilan mustaqil birikma. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_342",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 6,
            titleEnglish = "342-Dars: Absolute Construction with 'with' & Review",
            titleUzbek = "C1 Mustaqil sifatdoshli birikma (2): With va amaliyot",
            objectiveUzbek = "🤖 TalkMe: Bugun mustaqil sifatdoshli birikmaning 'with' predlogli shaklini va barcha turlarini mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nYo'ldosh holatni (hamrohlikdagi holatni) ifodalash uchun ko'pincha 'with + ot + sifatdosh / predlogli birikma' ishlatiladi:\n• She was sitting on the sofa, with her eyes closed. — U ko'zlarini yumgan holda divanda o'tirardi.\n• He stood at the window, with his hands in his pockets. — U qo'llarini cho'ntagiga solgan holda deraza oldida turardi.\n• The dog was running beside him, its tail wagging. — It dumini likillatib uning yonida yugurib borardi.\n\n💬 MISOLLAR:\n• All the questions having been settled, the meeting ended. — Barcha masalalar hal qilingach, yig'ilish tugadi.\n• Time permitting, we shall visit the museum. — Vaqt imkon bersa, muzeyga boramiz.\n• She walked in, with a smile on her face. — U yuzida tabassum bilan kirib keldi.\n\n✍️ MASHQ:\n1. \"She slept and the window was open.\" (with bilan) → ✅ She slept with the window open.\n2. \"There being no time left, we hurried.\" — O'zbekchaga tarjima qiling.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: With + ot + Participle yo'ldosh holatni bildiradi. Mustaqil birikma C1 darajada rasmiy va badiiy uslub ko'zgusidir.\n\n🤖 TalkMe yakuniy: Zo'r! Mustaqil sifatdoshli birikma to'liq tushunarli! Ertaga: Majhul nisbat — barcha zamonlar jadvali. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_343",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 7,
            titleEnglish = "343-Dars: Passive Voice — All Tenses System",
            titleUzbek = "C1 Majhul nisbat — barcha zamonlar to'liq tizimi",
            objectiveUzbek = "🤖 TalkMe: Bugun majhul nisbatning (Passive Voice) barcha zamonlardagi yagona oltin jadvalini tuzamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMajhul nisbatda 8 ta asosiy zamon ishlatiladi:\n1. Present Simple: am/is/are + V3 (The letter is written)\n2. Past Simple: was/were + V3 (The letter was written)\n3. Future Simple: will be + V3 (The letter will be written)\n4. Present Continuous: am/is/are being + V3 (is being written)\n5. Past Continuous: was/were being + V3 (was being written)\n6. Present Perfect: have/has been + V3 (has been written)\n7. Past Perfect: had been + V3 (had been written)\n8. Future Perfect: will have been + V3 (will have been written)\nModal fe'llar bilan: can/must/should be + V3; Perfect modal: must/should have been + V3.\n\n💬 MISOLLAR:\n• A new bridge is being built now. — Hozir yangi ko'prik qurilyapti.\n• The report had been finished before the director arrived. — Direktor kelishidan oldin hisobot tugatilgan edi.\n• By next month the project will have been completed. — Keyingi oygacha loyiha yakunlangan bo'ladi.\n\n✍️ MASHQ:\n1. \"They are repairing the road.\" → ✅ The road is being repaired.\n2. \"Someone had locked the door.\" → ✅ The door had been locked.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Passive Voice: 8 zamon + Modal + Perfect Modal. Future Continuous va Perfect Continuous zamonlarida Passive ishlatilmaydi.\n\n🤖 TalkMe yakuniy: Mukammal! Majhul nisbatning barcha qirralarini egalladingiz! Ertaga: Noreal shart gap (hozirgi zamon). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_344",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 8,
            titleEnglish = "344-Dars: Unreal Conditional — Present & Future",
            titleUzbek = "C1 Noreal shart (hozirgi/kelasi): If I knew, I would tell",
            objectiveUzbek = "🤖 TalkMe: Bugun hozirgi yoki kelasi zamonga oid NOREAL (xayoliy) shart gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nHaqiqatga zid bo'lgan, hozir yoki kelajakda amalga oshishi dargumon shartni ifodalash uchun:\n• Formula: If + Past Simple (V2 / were), ... would / could / might + V1\n• Muhim: 'to be' fe'li barcha shaxslar (I, he, she, it) uchun 'were' shaklida keladi!\n\n💬 MISOLLAR:\n• If I knew his address, I would write to him. — Agar uning manzilini bilsam edim (hozir bilmayman), unga xat yozardim.\n• If I were you, I would accept this offer. — Sizning o'rningizda bo'lsam, bu taklifni qabul qilardim.\n• If he had more time now, he could help us. — Hozir vaqti ko'proq bo'lsa edi, bizga yordam bera olardi.\n\n✍️ MASHQ:\n1. \"If I ___ (be) a bird, I would fly to you.\" → ✅ were\n2. \"If she ___ (know) English well, she would translate it now.\" → ✅ knew",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 2-tur noreal shart: If + V2 (were), would + V1. Hozirgi yoki kelasi zamondagi xayoliy shartni bildiradi.\n\n🤖 TalkMe yakuniy: Ajoyib! Xayoliy shart gaplarni tuzishni o'rgandingiz! Ertaga: O'tgan zamondagi noreal shart (If I had known...). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_345",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 9,
            titleEnglish = "345-Dars: Unreal Conditional — Past (Third Conditional)",
            titleUzbek = "C1 Noreal shart (o'tgan zamon): If I had known, I would have...",
            objectiveUzbek = "🤖 TalkMe: Bugun o'tmishda bo'lib o'tgan ishning aksini tasavvur qilishni — 3-tur shart gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'tgan zamonda amalga oshmay qolgan shart va uning natijasini (afsus yoki tahlilni) ifodalaydi:\n• Formula: If + Past Perfect (had + V3), ... would / could / might + have + V3\n\n💬 MISOLLAR:\n• If I had known about it yesterday, I would have told you. — Agar kecha bu haqda bilganimda edi, sizga aytgan bo'lardim (lekin bilmadim va aytmadim).\n• If he had not missed the train, he wouldn't have been late. — Agar u poyezddan kechikmaganida edi, kech qolmagan bo'lardi.\n• If you had asked me, I could have helped you. — Agar mendan so'raganingizda, yordam bera olgan bo'lardim.\n\n✍️ MASHQ:\n1. \"If I ___ (see) him yesterday, I would have spoken to him.\" → ✅ had seen\n2. \"If they had studied harder, they ___ (pass) the exam.\" → ✅ would have passed",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3-tur noreal shart: If + had V3, would have V3. O'tmishdagi o'zgartirib bo'lmaydigan holatni bildiradi.\n\n🤖 TalkMe yakuniy: Zo'r! 3-tur shart gaplarni ham zabt etdingiz! Ertaga: Aralash shart gaplar (Mixed Conditionals). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_346",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 10,
            titleEnglish = "346-Dars: Mixed Conditionals",
            titleUzbek = "C1 Aralash shart gaplar (Mixed Conditionals)",
            objectiveUzbek = "🤖 TalkMe: Bugun o'tmishdagi shartning hozirgi natijasini (va aksincha) ifodalovchi aralash shart gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1-tur aralash (Shart o'tmishda → Natija hozirda):\n• Formula: If + had V3 (o'tmish), ... would + V1 (now / today)\n• If you had taken the medicine yesterday, you would feel better now. — Kecha dorini ichganingizda edi, hozir o'zingizni yaxshiroq his qilardingiz.\n\n2-tur aralash (Doimiy/hozirgi shart → Natija o'tmishda):\n• Formula: If + V2 / were (doimiy), ... would have + V3 (o'tmishda)\n• If he were more careful, he wouldn't have made that mistake yesterday. — Agar u e'tiborliroq odam bo'lganida, kecha o'sha xatoni qilmagan bo'lardi.\n\n💬 MISOLLAR:\n• If I had gone to bed early last night, I wouldn't be so tired now.\n• If I knew French, I would have translated the article yesterday.\n\n✍️ MASHQ:\n1. \"If he had worked harder at school, he ___ (have) a better job now.\" → ✅ would have\n2. \"If she weren't afraid of flying, she ___ (fly) to London last week.\" → ✅ would have flown",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Mixed 1: If + had V3, would V1 (now). Mixed 2: If + V2/were, would have V3 (yesterday).\n\n🤖 TalkMe yakuniy: Barakalla! Aralash shart gaplar — haqiqiy C1 mahorati! Ertaga: I wish — hozirgi zamon. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_347",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 11,
            titleEnglish = "347-Dars: Wish — Present & Future",
            titleUzbek = "C1 Wish — hozirgi zamon: I wish I knew...",
            objectiveUzbek = "🤖 TalkMe: Bugun 'Qani endi hozir shunday bo'lsa edi!' degan istak va afsusni ifodalovchi 'I wish' tuzilmasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nHozirgi holatdan qoniqmaslik va uning boshqacha bo'lishini orzu qilish:\n• Formula 1 (Hozirgi holat): I wish + Past Simple (V2 / were)\n• I wish I knew his phone number. — Qani endi uning telefon raqamini bilsam (lekin hozir bilmayman).\n• I wish it were summer now. — Qani endi hozir yoz bo'lsa.\n\n• Formula 2 (Kelajakda o'zgarish yoki shikoyat): I wish + would + V1\n• I wish it would stop raining. — Qani endi yomg'ir to'xtasa.\n\n💬 MISOLLAR:\n• I wish I could speak Spanish fluently. — Qani endi ispan tilida ravon gapira olsam.\n• I wish I had more free time. — Qani endi bo'sh vaqtim ko'proq bo'lsa.\n• She wishes she lived near the sea. — U dengiz yaqinida yashashni orzu qiladi.\n\n✍️ MASHQ:\n1. \"I don't know the answer. → I wish I ___ the answer.\" → ✅ knew\n2. \"He is not here now. → I wish he ___ here now.\" → ✅ were",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Hozirgi zamondagi noreal istak: I wish + V2 / were. Kelajakdagi o'zgarish istagi: I wish + would + V1.\n\n🤖 TalkMe yakuniy: Ajoyib! 'I wish' bilan hozirgi orzularni ayta olasiz! Ertaga: I wish — o'tgan zamon (I wish I had known...). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_348",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 12,
            titleEnglish = "348-Dars: Wish — Past Regrets",
            titleUzbek = "C1 Wish — o'tgan zamon: I wish I had known...",
            objectiveUzbek = "🤖 TalkMe: Bugun o'tmishda qilingan yoki qilinmay qolgan ishdan afsuslanishni ('I wish + had V3') o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'tgan zamondagi voqea haqida afsuslanish ('Attang, koshki o'shanda shunday qilgan bo'lsam edi'):\n• Formula: I wish + Past Perfect (had + V3)\n• 'If only' ham xuddi shu qoida bilan ishlatiladi va yanada kuchliroq his-hayajon beradi.\n\n💬 MISOLLAR:\n• I wish I had known about the meeting yesterday. — Koshki kechagi yig'ilish haqida bilgan bo'lsam edi.\n• I wish I hadn't said those words. — Koshki o'sha so'zlarni aytmagan bo'lsam edi.\n• She wishes she had studied harder at university. — U universitetda qattiqroq o'qimaganiga afsuslanadi.\n• If only I had listened to your advice! — Koshki maslahatingizga quloq solgan bo'lsam edi!\n\n✍️ MASHQ:\n1. \"I didn't buy that book. → I wish I ___ that book.\" → ✅ had bought\n2. \"We came late. → I wish we ___ (not come) late.\" → ✅ hadn't come",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'tmishdagi afsus: I wish (yoki If only) + had + V3. Hozirgi istak: V2/were, o'tgan afsus: had V3.\n\n🤖 TalkMe yakuniy: Zo'r! Wish mavzusini mukammal egalladingiz! Ertaga: Ravish ergash gaplar (as, so...that). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_349",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 13,
            titleEnglish = "349-Dars: Adverbial Clauses of Manner — as, so...that",
            titleUzbek = "C1 Ravish ergash gaplar (1): as, so...that, such...that",
            objectiveUzbek = "🤖 TalkMe: Bugun harakatning qanday va qay darajada bajarilishini ko'rsatuvchi ravish ergash gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Tarz (Manner) — 'as' (kabi, qanday bo'lsa shunday):\n• Do it as I showed you. — Buni men ko'rsatganimdek bajaring.\n2. Daraja va natija (Degree & Result) — 'so + sifat/ravish + that' va 'such + (a/an) + sifat + ot + that':\n• He spoke so fast that nobody could understand him. — U shunchalik tez gapirdiki, hech kim tushuna olmadi.\n• It was such an interesting book that I read it in one night. — Bu shunchalik qiziq kitob ediki, uni bir kechada o'qib chiqdim.\n\n💬 MISOLLAR:\n• Leave everything as it is. — Hamma narsani qanday bo'lsa, shunday qoldiring.\n• The box was so heavy that I couldn't lift it. — Quti shunchalik og'ir ediki, ko'tara olmadim.\n• She is such a kind person that everyone loves her. — U shunday mehribon insonki, hamma uni yaxshi ko'radi.\n\n✍️ MASHQ:\n1. \"The coffee was ___ hot that I couldn't drink it.\" (so / such) → ✅ so\n2. \"It was ___ cold weather that we stayed indoors.\" (so / such) → ✅ such",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: as — kabi/dek; so + sifat/ravish + that; such + (a/an) sifat + ot + that.\n\n🤖 TalkMe yakuniy: Ajoyib! So...that va such...that farqini aniq bilib oldingiz! Ertaga: as if va as though. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_350",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 14,
            titleEnglish = "350-Dars: Clauses of Manner — as if / as though",
            titleUzbek = "C1 Ravish ergash gaplar (2): as if / as though (xuddi ...dek)",
            objectiveUzbek = "🤖 TalkMe: Bugun 'xuddi ...dek' ma'nosini beruvchi 'as if' va 'as though' bog'lovchilarining noreal zamon qoidasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n'As if' va 'as though' (xuddi ...dek, go'yo ...dek) dan keyin noreal o'xshatishda zamon bir pog'ona orqaga suriladi:\n1. Asosiy gap bilan BIR VAQTDA sodir bo'lsa → Past Simple (V2 / were):\n• He talks as if he knew everything. — U xuddi hamma narsani biladigandek gapiradi (aslida bilmaydi).\n• She looked at me as though she were my boss. — U menga xuddi boshlig'imdek qaradi.\n\n2. Asosiy gapdan OLDIN sodir bo'lgan bo'lsa → Past Perfect (had + V3):\n• He looks as if he had seen a ghost. — U xuddi arvohni ko'rgandek ko'rinadi.\n\n💬 MISOLLAR:\n• Why are you looking at me as if you didn't know me? — Nega menga xuddi meni tanimaydigandek qarayapsan?\n• She spoke about Paris as though she had lived there for years. — U Parij haqida go'yo u yerda yillab yashagandek gapirdi.\n\n✍️ MASHQ:\n1. \"He behaves as if he ___ (be) a child.\" → ✅ were\n2. \"She looked tired as if she ___ (not sleep) all night.\" → ✅ hadn't slept",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: As if / as though + V2/were (bir vaqtda); + had V3 (oldin sodir bo'lgan harakat).\n\n🤖 TalkMe yakuniy: Zo'r! As if / as though C1 testlarining eng muhim qoidalaridan biri! Ertaga: than va as...as. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_351",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 15,
            titleEnglish = "351-Dars: Clauses of Comparison — than, as...as",
            titleUzbek = "C1 Qiyos ergash gaplar: than, as...as, not so...as",
            objectiveUzbek = "🤖 TalkMe: Bugun qiyoslash ergash gaplarining to'liq C1 tuzilmasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nQiyos ergash gaplar 'than' (...ga qaraganda), 'as...as' (...dek) va 'not so...as / not as...as' bilan bog'lanadi. Ikkinchi qismda ko'pincha yordamchi fe'l qoldirilib, takroriy fe'l tushiriladi:\n• He knows more than I do (know). — U mendan ko'ra ko'proq biladi.\n• The task was more difficult than we had expected. — Vazifa biz kutganimizdan qiyinroq chiqdi.\n• She speaks English as fluently as a native speaker does. — U ingliz tilida xuddi ona tili sohibidek ravon gapiradi.\n\n💬 MISOLLAR:\n• You came earlier than I thought you would. — Siz men o'ylaganimdan ertaroq keldingiz.\n• It is not so cold today as it was yesterday. — Bugun kechagidek sovuq emas.\n• He worked harder than anyone else had ever done. — U boshqa har kim qilganidan ko'ra qattiqroq ishladi.\n\n✍️ MASHQ:\n1. \"She runs faster than I ___ (run).\" → ✅ do\n2. \"The exam was easier than we ___ (expect) before.\" → ✅ had expected",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Than va as...as ergash gaplarida ikkinchi qismda yordamchi fe'l (do/did/was/had) saqlanib, takrorning oldi olinadi.\n\n🤖 TalkMe yakuniy: Ajoyib! Qiyoslash ergash gaplarini ravon tuzasiz! Ertaga: The...the... (Qanchalik... shunchalik...). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_352",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 16,
            titleEnglish = "352-Dars: Proportional Comparison — The... the...",
            titleUzbek = "C1 Mutanosib qiyos: The more..., the better...",
            objectiveUzbek = "🤖 TalkMe: Bugun 'Qanchalik ..., shunchalik ...' ma'nosidagi 'The + qiyosiy daraja ..., the + qiyosiy daraja' qolipini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nIkki holatning bir-biriga bog'liq ravishda o'zgarishini ko'rsatadi:\n• Formula: The + comparative + ega + kesim, the + comparative + ega + kesim.\n• The more you read, the more you know. — Qanchalik ko'p o'qisangiz, shunchalik ko'p bilasiz.\n• The sooner we start, the earlier we shall finish. — Qanchalik erta boshlasak, shunchalik erta tugatamiz.\n• Qisqa hikmatli shakl: The sooner, the better! — Qanchalik tez bo'lsa, shunchalik yaxshi!\n\n💬 MISOLLAR:\n• The higher we climbed, the colder it became. — Biz qanchalik balandga chiqsak, havo shunchalik sovidi.\n• The more you practice speaking, the more confident you become. — Gapirishni qanchalik ko'p mashq qilsangiz, o'zingizga ishonchingiz shunchalik ortadi.\n• The longer I waited, the more impatient I grew. — Qanchalik uzoq kutsam, shunchalik toqatsizlanib bordim.\n\n✍️ MASHQ:\n1. \"___ (much) you study, ___ (good) your results will be.\" → ✅ The more / the better\n2. \"___ (early) you leave, ___ (soon) you will arrive.\" → ✅ The earlier / the sooner",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The + qiyosiy daraja ..., the + qiyosiy daraja ... = Qanchalik ..., shunchalik ...\n\n🤖 TalkMe yakuniy: Zo'r! 'The more you learn, the better you speak!' Ertaga: Bog'lovchisiz shart gaplar (Had I known...). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_353",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 17,
            titleEnglish = "353-Dars: Inverted Conditional — Had I known...",
            titleUzbek = "C1 Bog'lovchisiz shart (1): Had I known...",
            objectiveUzbek = "🤖 TalkMe: Bugun 'if' bog'lovchisini tushirib, inversiya orqali 'Had I known...' tuzishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n3-tur noreal shart gaplarda 'if' tushirilsa, 'had' yordamchi fe'li egadan oldinga chiqadi (inversiya):\n• Oddiy: If I had known about it, I would have helped you.\n• Inversiyali (C1): Had I known about it, I would have helped you. — Agar bu haqda bilganimda edi, sizga yordam bergan bo'lardim.\n• Inkor shaklda 'not' egadan keyin turadi: Had he not helped us... (qisqartma ishlatilmaydi).\n\n💬 MISOLLAR:\n• Had you come five minutes earlier, you would have met him. — Besh daqiqa ertaroq kelganingizda, uni uchratgan bo'lardingiz.\n• Had they followed my advice, they would have succeeded. — Maslahatimga amal qilishganida, muvaffaqiyatga erishgan bo'lishardi.\n• Had it not been for your help, I would have failed. — Sizning yordamingiz bo'lmaganida, muvaffaqiyatsizlikka uchragan bo'lardim.\n\n✍️ MASHQ:\n1. \"If I had seen him, I would have told him.\" (If siz yozing) → ✅ Had I seen him, I would have told him.\n2. \"If she had not called me, I would have forgotten.\" → ✅ Had she not called me, I would have forgotten.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: If + Ega + had V3 = Had + Ega + V3. Rasmiy va akademik nutqda keng qo'llaniladi.\n\n🤖 TalkMe yakuniy: Ajoyib! 'Had I known...' — haqiqiy C1 darajasi! Ertaga: Were he here... 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_354",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 18,
            titleEnglish = "354-Dars: Inverted Conditional — Were he here...",
            titleUzbek = "C1 Bog'lovchisiz shart (2): Were he here...",
            objectiveUzbek = "🤖 TalkMe: Bugun 2-tur noreal shart gaplarda 'if' o'rniga 'Were' ni oldinga chiqarishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n2-tur noreal shart gaplarda 'if' tushirilsa, 'Were' egadan oldinga chiqadi:\n1. Ot yoki sifat bilan:\n• If he were here now → Were he here now, he would help us. — Agar u hozir shu yerda bo'lsa edi, bizga yordam berardi.\n2. Boshqa fe'llar bilan (Were + ega + to + V1):\n• If I knew / If I were to know → Were I to know the truth, I would tell you.\n\n💬 MISOLLAR:\n• Were I in your place, I would accept the proposal. — Sizning o'rningizda bo'lsam, taklifni qabul qilardim.\n• Were she to ask me, I would gladly explain everything. — Agar u mendan so'rasa edi, mamnuniyat bilan tushuntirardim.\n• Were it not for the rain, we would go to the park. — Yomg'ir bo'lmaganida, bog'ga borardik.\n\n✍️ MASHQ:\n1. \"If I were younger, I would learn skiing.\" (If siz) → ✅ Were I younger, I would learn skiing.\n2. \"If he were to lose his key, what would he do?\" → ✅ Were he to lose his key, what would he do?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: If + Ega + were = Were + Ega ... (yoki Were + Ega + to + V1).\n\n🤖 TalkMe yakuniy: Zo'r! Were bilan inversiyali shart gaplarni ham o'zlashtirdingiz! Ertaga: Could he come... 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_355",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 19,
            titleEnglish = "355-Dars: Inverted Conditional — Could & Modal Inversion",
            titleUzbek = "C1 Bog'lovchisiz shart (3): Could he come...",
            objectiveUzbek = "🤖 TalkMe: Bugun modal fe'lli shart gaplarning bog'lovchisiz (inversiyali) shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAdabiy va yuqori rasmiy uslubda 'If + Ega + could + V1' o'rniga 'Could + Ega + V1' yoki 'Were + Ega + able to + V1' ishlatiladi:\n• Oddiy: If he could come tomorrow, we would discuss the plan.\n• Inversiyali: Could he come tomorrow, we would discuss the plan. — Agar u ertaga kela olsa edi, rejani muhokama qilardik.\n\n💬 MISOLLAR:\n• Could I speak to the director now, I would settle the matter. — Hozir direktor bilan gaplasha olsam edi, masalani hal qilardim.\n• Could they see us now, they would be very proud. — Ular hozir bizni ko'ra olishsa edi, juda faxrlanishardi.\n• Were we able to stay longer, we would visit Samarkand. — Uzoqroq qola olganimizda, Samarqandga borardik.\n\n✍️ MASHQ:\n1. \"If I could help you, I would do it gladly.\" (Inversiya bilan) → ✅ Could I help you, I would do it gladly.\n2. \"Could she ___ (hear) you, she would be happy.\" → ✅ hear",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Could + Ega + V1 (yoki Were + Ega + able to V1) — modal ma'noli bog'lovchisiz shart gap.\n\n🤖 TalkMe yakuniy: Barakalla! Bog'lovchisiz shartlarning 3-turini ham o'rgandingiz! Ertaga: Should need arise... 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_356",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 20,
            titleEnglish = "356-Dars: Inverted Conditional — Should need arise...",
            titleUzbek = "C1 Bog'lovchisiz shart (4): Should you need help...",
            objectiveUzbek = "🤖 TalkMe: Bugun ishbilarmonlik va rasmiy xatlarda eng ko'p uchraydigan 'Should + Ega + V1' shart tuzilmasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1-tur va 2-tur shart gaplarda 'mabodo ... bo'lib qolsa' ma'nosini berish uchun 'Should' egadan oldinga chiqariladi:\n• Formula: Should + Ega + V1, ... will / would / buyruq mayli\n• Oddiy: If you should need any help, please call me.\n• Inversiyali: Should you need any help, please call me. — Mabodo yordam kerak bo'lib qolsa, menga qo'ng'iroq qiling.\n• Barqaror ibora: Should need arise — Mabodo zarurat tug'ilsa.\n\n💬 MISOLLAR:\n• Should he call while I am out, tell him to wait. — Mabodo men yo'qligimda u qo'ng'iroq qilsa, kutib turishini ayting.\n• Should the weather change, we will stay at the hotel. — Mabodo ob-havo o'zgarsa, mehmonxonada qolamiz.\n• Should you have any questions, do not hesitate to ask. — Savollaringiz bo'lsa, tortinmay so'rang.\n\n✍️ MASHQ:\n1. \"If you should see Ali, give him my regards.\" (If siz) → ✅ Should you see Ali, give him my regards.\n2. \"Should need ___ (arise), we shall send another team.\" → ✅ arise",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Should + Ega + V1 = 'Mabodo ... bo'lib qolsa'. To'rtta inversiyali shart: Had, Were, Could, Should.\n\n🤖 TalkMe yakuniy: Mukammal! Barcha 4 ta bog'lovchisiz shart qoliplarini egalladingiz! Ertaga: Mustaqil birikmalar umumlashtiruvi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_357",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 21,
            titleEnglish = "357-Dars: Independent Constructions Synthesis",
            titleUzbek = "C1 Mustaqil birikmalar — to'liq umumlashtirish",
            objectiveUzbek = "🤖 TalkMe: Bugun C1 darajasidagi barcha mustaqil va murakkab oborotlarni bir tizimga keltiramiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nC1 sintaktik birikmalarining 4 ustuni:\n1. Complex Object: I saw him cross the street. / I want you to succeed.\n2. Complex Subject (Nominative with Infinitive): He is known to be a great scientist.\n3. For-to-Infinitive Construction: It is necessary for you to sign the document.\n4. Absolute Participial Construction: The work having been finished, we went home.\n\n💬 MISOLLAR:\n• Weather permitting, the expedition is expected to start at dawn. — Ob-havo imkon bersa, ekspeditsiya tongda boshlanishi kutilmoqda.\n• With all the documents signed, the delegation left the hall. — Barcha hujjatlar imzolangach, delegatsiya zaldan chiqdi.\n• There being no further questions, the chairman closed the session. — Boshqa savollar bo'lmagani uchun rais majlisni yopdi.\n\n✍️ MASHQ:\n1. \"Everyone knows that she is talented.\" (Complex Subject) → ✅ She is known to be talented.\n2. \"As the sun had set, it became dark.\" (Absolute Construction) → ✅ The sun having set, it became dark.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 4 ta C1 oboroti: Complex Object, Complex Subject, For-phrase va Absolute Participial Construction. Yangi so'z: delegation.\n\n🤖 TalkMe yakuniy: Zo'r! Sintaktik oborotlar bo'yicha chinakam ustasiz! Ertaga: To'siqsiz ergash gaplar (though, although). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_358",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 22,
            titleEnglish = "358-Dars: Clauses of Concession — though, although, even though",
            titleUzbek = "C1 To'siqsiz ergash gaplar (1): though, although",
            objectiveUzbek = "🤖 TalkMe: Bugun '...ga qaramay, garchi ... bo'lsa ham' ma'nosidagi to'siqsiz ergash gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n'Although', 'though' va 'even though' (garchi ... bo'lsa ham, ...ga qaramay) dan keyin TO'LIQ GAP (Ega + Kesim) keladi:\n• Although it was raining heavily, they went out. — Qattiq yomg'ir yog'ayotganiga qaramay, ular ko'chaga chiqishdi.\n• Muhim: Ingliz tilida 'although / though' qatnashgan gapning ikkinchi qismida 'but' ISHLATILMAYDI!\n• C1 inversiyali shakl: Sifat/ravish + as / though + Ega + Kesim:\n  Cold though it was, we went swimming. — Garchi sovuq bo'lsa ham, cho'milishga bordik.\n\n💬 MISOLLAR:\n• Even though he was very tired, he finished the report. — Juda charchagan bo'lsa ham, u hisobotni tugatdi.\n• Tired as he was, he continued working. — Garchi charchagan bo'lsa ham, ishlashda davom etdi.\n• Though she is young, she is very experienced. — Garchi yosh bo'lsa ham, u juda tajribali.\n\n✍️ MASHQ:\n1. \"Although he was ill, ___ he came to work.\" (but kerakmi?) → ✅ Yo'q, 'but' qo'yilmaydi.\n2. \"___ it was late, nobody wanted to leave.\" (Although / Despite) → ✅ Although",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Although / though / even though + Ega + Kesim. C1 shakl: Sifat + as/though + Ega + Kesim.\n\n🤖 TalkMe yakuniy: Ajoyib! To'siqsiz ergash gaplarning birinchi qismini o'zlashtirdingiz! Ertaga: in spite of the fact that. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_359",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 23,
            titleEnglish = "359-Dars: Concession — in spite of the fact that, however",
            titleUzbek = "C1 To'siqsiz ergash gaplar (2): in spite of the fact that",
            objectiveUzbek = "🤖 TalkMe: Bugun 'in spite of the fact that' va 'however / no matter how' birikmalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. 'In spite of the fact that' / 'Despite the fact that' + Ega + Kesim (...ligiga qaramay):\n• In spite of the fact that he had little time, he helped us. — Vaqti kam bo'lishiga qaramay, u bizga yordam berdi.\n2. 'However + sifat/ravish + Ega + Kesim' (= No matter how ... — qanchalik ... bo'lmasin):\n• However difficult the task may be, we must fulfill it. — Vazifa qanchalik qiyin bo'lmasin, uni bajarishimiz shart.\n• Whatever happens, keep calm. — Nima bo'lishidan qat'i nazar, xotirjamlikni saqlang.\n\n💬 MISOLLAR:\n• Despite the fact that the prices were high, the tickets were sold out.\n• However hard he tried, he couldn't open the door. — U qanchalik qattiq urinmasin, eshikni ocha olmadi.\n• Wherever you go, I will remember you. — Qayerga borsangiz ham, sizni eslayman.\n\n✍️ MASHQ:\n1. \"___ rich he may be, he cannot buy health.\" (However / Whatever) → ✅ However\n2. \"In spite of the ___ that it was snowing, the plane took off.\" → ✅ fact",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: In spite of the fact that + gap; However + sifat/ravish + ega + kesim (qanchalik ... bo'lmasin).\n\n🤖 TalkMe yakuniy: Zo'r! Murakkab to'siqsiz bog'lovchilarni ham bilib oldingiz! Ertaga: despite / in spite of + ot/gerund va Partiya 18 yakuni. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_360",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 24,
            titleEnglish = "360-Dars: Despite / In spite of & Part 18 Review",
            titleUzbek = "C1 To'siqsiz ergash gaplar (3): despite, in spite of va Partiya 18 yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun 'although' va 'despite / in spite of' o'rtasidagi oltin farqni o'rganib, Partiya 18 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Although / Even though + EGA + KESIM (to'liq gap):\n  Although it was raining, we went out.\n• Despite / In spite of + OT / OLMOSH / GERUND (V-ing) (gap emas!):\n  Despite the rain, we went out. — Yomg'irga qaramay, ko'chaga chiqdik.\n  In spite of being tired, he continued his work. — Charchagan bo'lishiga qaramay, ishini davom ettirdi.\n• Muhim xato: 'despite of' DEYILMAYDI! Faqat 'despite' yoki 'in spite of'.\n\n💬 MISOLLAR:\n• Despite his age, he runs every morning. — Yoshiga qaramay, u har kuni ertalab yuguradi.\n• In spite of having a headache, she attended the lecture. — Boshi og'riyotganiga qaramay, u ma'ruzaga qatnashdi.\n• Despite all difficulties, they achieved their goal. — Barcha qiyinchiliklarga qaramay, ular maqsadiga erishdilar.\n\n✍️ MASHQ:\n1. \"___ the cold weather, they went swimming.\" (Although / Despite) → ✅ Despite\n2. \"___ it was cold, they went swimming.\" (Although / Despite) → ✅ Although",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Although + Ega + Kesim; Despite / In spite of + Ot / V-ing. Partiya 18 (341–360 darslar) to'liq yakunlandi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz 360 ta darsni — kitobning 90 foizini tugatdingiz! Keyingi bo'lim — Partiya 19: C1 Ko'chirma gap va C2 Inversiya! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 19 — C1 KO'CHIRMA GAP, C2 INVERSIYA, ELLIPSIS VA ARTIKL (DARS 361–380)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_13_361",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 25,
            titleEnglish = "361-Dars: Advanced Reported Speech — Statements",
            titleUzbek = "C1 Ko'chirma gap — chuqur (1): Darak gaplar va istisnolar",
            objectiveUzbek = "🤖 TalkMe: Partiya 19 ga xush kelibsiz! Bugun ko'chirma darak gaplarning C1 qoidalari va zamon moslashuvidagi istisnolarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAsosiy qoida: Bosh gap o'tgan zamonda (said, told) bo'lsa, ergash gap zamoni bir pog'ona orqaga suriladi (Present → Past, Past → Past Perfect, will → would).\nC1 Istisnolar (zamon O'ZGARMAYDI):\n1. Umumiy haqiqat va tabiat qonunlari: The teacher said that the Earth moves round the Sun.\n2. Aniq tarixiy sana ko'rsatilgan Past Simple: He said that he was born in 1995.\n3. 2- va 3-tur noreal shart gaplar hamda 'I wish / had better / would rather': She said, \"If I knew, I would tell you\" → She said that if she knew, she would tell me.\n\n💬 MISOLLAR:\n• He promised that he would return the book the next day.\n• She boasted that she had never lost a single match.\n• The scientist explained that water boils at 100 degrees Celsius.\n\n✍️ MASHQ:\n1. He said, \"If I had time, I would help you.\" → ✅ He said that if he had time, he would help me.\n2. She said, \"Water freezes at zero degrees.\" → ✅ She said that water freezes at zero degrees.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Umumiy haqiqat, tarixiy sana va 2-3-tur shart gaplar o'zlashtirma gapda zamonini o'zgartirmaydi.\n\n🤖 TalkMe yakuniy: Ajoyib! C1 ko'chirma gap istisnolarini bilib oldingiz! Ertaga: So'roq gaplarni chuqur o'zlashtirish. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_362",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 26,
            titleEnglish = "362-Dars: Advanced Reported Speech — Questions",
            titleUzbek = "C1 Ko'chirma gap — chuqur (2): So'roq gaplar",
            objectiveUzbek = "🤖 TalkMe: Bugun o'zlashtirma so'roq gaplarda 'inquired, wondered, wanted to know' fe'llari va to'g'ri so'z tartibini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'zlashtirma so'roqda yordamchi fe'l (do/does/did) tushib qoladi va TO'G'RI SO'Z TARTIBI (Ega + Kesim) tiklanadi:\n1. Umumiy so'roq: asked / wondered / inquired + if / whether + Ega + Kesim.\n• \"Have you seen the film?\" → He wondered whether I had seen the film (or not).\n2. Maxsus so'roq: asked / wanted to know + Wh-so'z + Ega + Kesim.\n• \"Where did they go?\" → She wanted to know where they had gone.\n\n💬 MISOLLAR:\n• The customs officer inquired how long I intended to stay. — Bojxona xodimi qancha vaqt qolmoqchi ekanligimni so'radi.\n• He asked me whether I had ever been to London. — U mendan Londonda bo'lgan-bo'lmaganligimni so'radi.\n• She wondered why he hadn't called her. — U nega qo'ng'iroq qilmaganiga hayron bo'ldi.\n\n✍️ MASHQ:\n1. \"When will the train arrive?\" → He inquired when ___ → ✅ the train would arrive.\n2. \"Do you know the way?\" → She asked whether ___ → ✅ I knew the way.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: O'zlashtirma so'roqda so'roq belgisi qo'yilmaydi va Ega + Kesim tartibi saqlanadi. 'Whether ... or not' C1 uslubda afzal ko'riladi.\n\n🤖 TalkMe yakuniy: Zo'r! O'zlashtirma so'roq gaplarda xato qilmaysiz! Ertaga: Buyruq, maslahat va taklif fe'llari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_363",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 27,
            titleEnglish = "363-Dars: Advanced Reported Speech — Commands & Reporting Verbs",
            titleUzbek = "C1 Ko'chirma gap — chuqur (3): Buyruq va Reporting Verbs",
            objectiveUzbek = "🤖 TalkMe: Bugun buyruq, iltimos, maslahat va takliflarni C1 fe'llari (advise, warn, suggest, insist) bilan ifodalashni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Fe'l + to'ldiruvchi + (not) to + V1: tell, ask, order, command, advise, warn, remind, invite, beg.\n• \"Don't touch the wire!\" → He warned me not to touch the wire.\n2. Fe'l + to + V1: promise, agree, refuse, offer, threaten.\n• \"I'll help you.\" → He offered to help me.\n3. Fe'l + V-ing (Gerund): suggest, admit, deny, apologize for, insist on.\n• \"Let's go to the park.\" → She suggested going to the park (yoki suggested that we should go).\n\n💬 MISOLLAR:\n• The doctor advised him to give up smoking. — Shifokor unga chekishni tashlashni maslahat berdi.\n• He denied taking the documents. — U hujjatlarni olganini inkor etdi.\n• She reminded me to lock the door. — U menga eshikni qulflashni eslatdi.\n\n✍️ MASHQ:\n1. \"Let's start early.\" → He suggested ___ (start) early. → ✅ starting\n2. \"Don't be late!\" → She warned us ___ (not be) late. → ✅ not to be",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: warn/advise/remind + sb + (not) to V1; offer/refuse/promise + to V1; suggest/deny/admit + V-ing.\n\n🤖 TalkMe yakuniy: Barakalla! Reporting verbs — C1 yozma nutqning bezagi! Ertaga: Aralash ko'chirma gaplar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_364",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 28,
            titleEnglish = "364-Dars: Advanced Reported Speech — Mixed Types",
            titleUzbek = "C1 Ko'chirma gap — chuqur (4): Aralash dialoglar",
            objectiveUzbek = "🤖 TalkMe: Bugun bitta gapda darak, so'roq va buyruq birga kelganda ularni qanday bog'lab o'zlashtirishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAgar ko'chirma nutqda bir nechta turli gaplar ketma-ket kelsa, har bir qism o'ziga mos kirish fe'li va 'and added that / and asked / and told' yordamida bog'lanadi:\n• Ko'chirma: \"It is late. Where are you going? Stay at home!\" she said.\n• O'zlashtirma: She said that it was late, asked where I was going, and told me to stay at home.\n\n💬 MISOLLAR:\n• \"I have lost my key. Can you help me find it?\" he said. → He said that he had lost his key and asked if I could help him find it.\n• \"Be quiet! The baby is sleeping,\" mother said. → Mother told us to be quiet and added that the baby was sleeping.\n• \"Thank you! You have helped me a lot,\" she said. → She thanked me and said that I had helped her a lot.\n\n✍️ MASHQ:\n1. \"I am busy now. Call me tomorrow,\" he said. → ✅ He said that he was busy then and told me to call him the next day.\n2. \"What are you doing? Come here!\" he said. → ✅ He asked what I was doing and ordered me to go there.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Aralash dialoglarda har bir gap turiga mos kirish fe'li (said that ... and asked ... and told) tanlanadi.\n\n🤖 TalkMe yakuniy: Ajoyib! Murakkab dialoglarni bitta ravon gapga aylantira olasiz! Ertaga: Modal fe'llar ko'chirma gapda. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_365",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 29,
            titleEnglish = "365-Dars: Reported Speech — Modal Verbs",
            titleUzbek = "C1 Ko'chirma gap — chuqur (5): Modal fe'llar o'zgarishi",
            objectiveUzbek = "🤖 TalkMe: Bugun o'zlashtirma gapda modal fe'llarning qaysilari o'zgarishi va qaysilari o'zgarmasligini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. O'zgaradigan modallar:\n• can → could (kelasi ma'noda: would be able to)\n• may → might (ruxsat ma'nosida: was/were allowed to)\n• must (majburiyat) → had to (kelasi majburiyat: would have to)\n• shall → should / would | will → would\n\n2. O'ZGARMAYDIGAN modallar:\n• could, would, should, might, ought to, needn't, used to, had better.\n• Doimiy qoida yoki taxmin ma'nosidagi 'must' ham o'zgarmaydi: She said that children must obey their parents.\n\n💬 MISOLLAR:\n• He said, \"I must leave now.\" → He said that he had to leave then.\n• She said, \"You should read this book.\" → She said that I should read that book.\n• They said, \"We may be late.\" → They said that they might be late.\n\n✍️ MASHQ:\n1. He said, \"I can swim well.\" → He said that he ___ swim well. → ✅ could\n2. She said, \"I must finish it today.\" → She said that she ___ finish it that day. → ✅ had to",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: can→could, may→might, must→had to, will→would. Could, should, might, ought to o'zgarmaydi.\n\n🤖 TalkMe yakuniy: Zo'r! Modal fe'llarning ko'chirma gapdagi barcha sirlarini bilasiz! Ertaga: Vaqt va joy ravishlari o'zgarishi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_366",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 30,
            titleEnglish = "366-Dars: Reported Speech — Time & Place Shifts",
            titleUzbek = "C1 Ko'chirma gap — chuqur (6): Vaqt va joy o'zgarishi",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'chirma gap o'zlashtirma gapga aylanganda vaqt va joy so'zlarining to'liq o'zgarish jadvalini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nVaqt va ko'rsatish so'zlarining almashinuvi:\n• now → then / at that time\n• today / tonight → that day / that night\n• yesterday → the day before / the previous day\n• the day before yesterday → two days before\n• tomorrow → the next day / the following day\n• the day after tomorrow → in two days' time\n• last week → the week before / the previous week\n• next month → the following month\n• ago → before | here → there | this → that | these → those\n\n💬 MISOLLAR:\n• \"I met him two days ago here.\" → He said that he had met him two days before there.\n• \"We will leave tomorrow.\" → They said that they would leave the next day.\n• \"I saw her yesterday.\" → She said that she had seen her the day before.\n\n✍️ MASHQ:\n1. \"yesterday\" o'zlashtirma gapda nima bo'ladi? → ✅ the day before (the previous day)\n2. \"tomorrow\" o'zlashtirma gapda nima bo'ladi? → ✅ the next day (the following day)",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: now→then, today→that day, yesterday→the day before, tomorrow→the next day, ago→before, here→there.\n\n🤖 TalkMe yakuniy: Barakalla! Vaqt va joy ravishlari jadvali xotirangizga muhrlandi! Ertaga: C1 Ko'chirma gap amaliyoti. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_367",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 31,
            titleEnglish = "367-Dars: Advanced Reported Speech — Practice",
            titleUzbek = "C1 Ko'chirma gap — murakkab amaliyot",
            objectiveUzbek = "🤖 TalkMe: Bugun C1 darajasidagi murakkab ko'chirma gaplarni o'zlashtirma gapga va aksincha aylantirishni mashq qilamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nHis-hayajon va salomlashuv/xayrlashuv gaplarini o'zlashtirish:\n• \"What a lovely day!\" → She exclaimed what a lovely day it was / that it was a lovely day.\n• \"Good morning, Ali!\" → He wished Ali a good morning / He greeted Ali.\n• \"Happy New Year!\" → They wished us a Happy New Year.\n• \"Yes, I will come.\" → He agreed to come / He answered in the affirmative.\n• \"No, I haven't seen it.\" → He answered in the negative.\n\n💬 MISOLLAR:\n• \"How beautifully she sings!\" he said. → He exclaimed with admiration that she sang very beautifully.\n• \"Congratulations on passing your exam!\" → He congratulated me on passing my exam.\n• \"Sorry I'm late,\" she said. → She apologized for being late.\n\n✍️ MASHQ:\n1. \"Thank you for your help,\" she said to me. → ✅ She thanked me for my help.\n2. \"I'm sorry I broke the cup,\" the boy said. → ✅ The boy apologized for breaking the cup.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Exclaimed that..., congratulated sb on V-ing, apologized for V-ing, thanked sb for... C1 nutqni jonlantiradi.\n\n🤖 TalkMe yakuniy: Ajoyib! Ko'chirma gapning eng nozik shakllarini ham bilasiz! Ertaga: Ko'chirma gap yakuniy takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_368",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 32,
            titleEnglish = "368-Dars: Reported Speech — Mastery Review",
            titleUzbek = "C1 Ko'chirma gap — to'liq umumlashtirish",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'chirma gapning barcha 8 darsini bitta tizimga jamlaymiz va C2 darajasiga qadam qo'yamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'zlashtirma gapning 5 oltin qoidasi:\n1. Olmoshlarni ma'noga qarab o'zgartirish (I → he/she, my → his/her).\n2. Bosh gap o'tgan zamonda bo'lsa, zamonni bir pog'ona orqaga surish (istisnolardan tashqari).\n3. Vaqt va joy ravishlarini almashtirish (yesterday → the day before).\n4. So'roq gaplarda to'g'ri so'z tartibini (Ega + Kesim) tiklash.\n5. Buyruq, taklif va his-hayajon uchun mos Reporting Verb tanlash.\n\n💬 MISOLLAR:\n• The professor asked the students whether they had completed their research and reminded them that the deadline was the following day.\n• She assured us that everything would be ready before the guests arrived.\n\n✍️ MASHQ:\n1. \"Have you finished the report? Hand it in now!\" the manager said. → ✅ The manager asked whether I had finished the report and told me to hand it in then.\n2. \"If I were you, I would study C2 grammar,\" TalkMe said. → ✅ TalkMe said that if it were me, it would study C2 grammar.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: C1 Ko'chirma gap bo'limi to'liq yakunlandi! Endi eng yuqori cho'qqi — C2 (Mastery) darajasini boshlaymiz!\n\n🤖 TalkMe yakuniy: TABRIKLAYMAN, [USER_NAME]! C1 darajasi yakunlandi! Ertaga C2 darajasining birinchi darsi: Inversiya — Never have I seen! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_369",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 33,
            titleEnglish = "369-Dars: C2 Negative Inversion — Never have I seen...",
            titleUzbek = "C2 Inversiya (1): Never have I seen, Seldom do we meet",
            objectiveUzbek = "🤖 TalkMe: Xush kelibsiz C2 darajasiga! Bugun kuchli uslubiy urg'u beruvchi Inversiya (so'z tartibining o'zgarishi) qoidasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAgar inkor yoki cheklovchi ravish gapning BOSHIGA chiqarilsa, undan keyin so'roq gapdagi kabi Yordamchi fe'l + Ega + Asosiy fe'l (inversiya) keladi:\n• Formula: Never / Seldom / Rarely / Little / Under no circumstances / In no way + Yordamchi fe'l + Ega + Fe'l.\n• Oddiy: I have never seen such a magnificent view.\n• C2 Inversiya: Never have I seen such a magnificent view! — Men hech qachon bunday muhtasham manzarani ko'rmaganman!\n\n💬 MISOLLAR:\n• Seldom do we meet such honest people. — Biz bunday halol insonlarni kamdan-kam uchratamiz.\n• Little did he know what was waiting for him. — U o'zini nima kutayotganini xayoliga ham keltirmasdi.\n• Under no circumstances should you open this door. — Hech qanday holatda bu eshikni ochmasligingiz kerak.\n• Not only did he pass the exam, but he also got the highest score.\n\n✍️ MASHQ:\n1. \"I had never heard such music before.\" (Never bilan boshlang) → ✅ Never had I heard such music before.\n2. \"Little ___ she realize the danger.\" (did / was) → ✅ did",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Inkor/cheklovchi ravish gap boshida kelsa → Yordamchi fe'l + Ega + Fe'l. Yangi so'zlar: inversion, magnificent.\n\n🤖 TalkMe yakuniy: Ajoyib! C2 Inversiya nutqingizga akademik salobat bag'ishlaydi! Ertaga: No sooner...than va Hardly...when. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_370",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 34,
            titleEnglish = "370-Dars: C2 Inversion — No sooner...than, Hardly...when",
            titleUzbek = "C2 Inversiya (2): No sooner had he... than, Hardly... when",
            objectiveUzbek = "🤖 TalkMe: Bugun 'ketma-ket, ...ib ulgurmasdan' ma'nosidagi C2 inversiyali bog'lovchilarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBir harakat tugashi bilanoq ikkinchisi boshlanganini urg'ulash uchun:\n1. No sooner + had + Ega + V3 ... than + Past Simple (V2):\n• No sooner had I closed my eyes than the phone rang. — Ko'zimni yumib ulgurmasimdan telefon jiringladi.\n\n2. Hardly / Scarcely + had + Ega + V3 ... when + Past Simple (V2):\n• Hardly had we left the house when it began to pour. — Uydan chiqib ulgurmasimizdan chelaklab yomg'ir quyib yubordi.\n• Eslab qoling: No sooner → THAN; Hardly / Scarcely → WHEN!\n\n💬 MISOLLAR:\n• Scarcely had the concert begun when the lights went out. — Konsert boshlanib ulgurmasdan chiroq o'chib qoldi.\n• No sooner had he arrived at the station than the train left.\n• Only then did I understand the meaning of his words. — Faqat shundagina uning so'zlari ma'nosini tushundim.\n\n✍️ MASHQ:\n1. \"No sooner had we sat down ___ (than / when) the bell rang.\" → ✅ than\n2. \"Hardly had she finished speaking ___ (than / when) everyone applauded.\" → ✅ when",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: No sooner had + Ega + V3 ... THAN + V2; Hardly / Scarcely had + Ega + V3 ... WHEN + V2.\n\n🤖 TalkMe yakuniy: Zo'r! No sooner...than va Hardly...when xalqaro imtihonlarning eng sevimli savoli! Ertaga: It is...that... urg'u tuzilmasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_371",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 35,
            titleEnglish = "371-Dars: C2 Cleft Sentences — It is/was ... that/who",
            titleUzbek = "C2 Urg'u tuzilmasi: It is... that... (Aynan ...)",
            objectiveUzbek = "🤖 TalkMe: Bugun gapning istalgan bo'lagiga mantiqiy urg'u beruvchi 'It is / was ... that / who' (Cleft Sentence) tuzilmasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nGapdagi bitta bo'lakni ('Aynan ...') ajratib ko'rsatish uchun:\n• Formula: It is / was + [urg'ulanadigan bo'lak] + that / who + [gapning qolgan qismi].\n• Asl gap: John bought a new car yesterday.\n1. Egaga urg'u: It was John who/that bought a new car yesterday. — Kecha yangi mashinani AYNAN Jon sotib oldi.\n2. To'ldiruvchiga urg'u: It was a new car that John bought yesterday. — Jon kecha AYNAN yangi mashina sotib oldi.\n3. Payt holiga urg'u: It was yesterday that John bought a new car. — Jon yangi mashinani AYNAN kecha sotib oldi.\n\n💬 MISOLLAR:\n• It is practice that makes you fluent. — Sizni ravon gapirtiradigan narsa AYNAN mashqdir.\n• It was in Tashkent that they first met. — Ular ilk bor AYNAN Toshkentda uchrashgan edilar.\n• What I need now is a cup of hot tea. (Wh-cleft) — Hozir menga kerak narsa — bir piyola issiq choy.\n\n✍️ MASHQ:\n1. \"Ali helped me yesterday.\" ('Ali' ga urg'u bering) → ✅ It was Ali who helped me yesterday.\n2. \"We met on Monday.\" ('on Monday' ga urg'u bering) → ✅ It was on Monday that we met.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: It is/was + [bo'lak] + that/who ... gapning istalgan qismiga 'aynan' deb urg'u beradi.\n\n🤖 TalkMe yakuniy: Ajoyib! Cleft sentences yordamida fikringizga aniq urg'u bera olasiz! Ertaga: Not until + inversiya. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_372",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 36,
            titleEnglish = "372-Dars: C2 'It was not until... that' & 'Not until' Inversion",
            titleUzbek = "C2 Not until: It was not until... that va Inversiya",
            objectiveUzbek = "🤖 TalkMe: Bugun 'Faqatgina ...dan keyingina' ma'nosini beruvchi ikkita yuqori darajali C2 qolipni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBir xil ma'noni ('faqatgina ...ganda / ...dan keyingina') beruvchi 2 ta C2 qolip:\n1. Cleft shakl (inversiyasiz): It was not until + [vaqt / ergash gap] + that + Ega + Kesim.\n• It was not until midnight that the rain stopped. — Faqat yarim tunga boribgina yomg'ir to'xtadi.\n\n2. Gap boshida 'Not until' (BOSH GAPDA inversiya bo'ladi!):\n• Formula: Not until + [vaqt / ergash gap] + Yordamchi fe'l + Ega + Fe'l.\n• Not until midnight did the rain stop.\n• Not until he came home did he realize that he had lost his wallet.\n\n💬 MISOLLAR:\n• It was not until I read the letter that I understood the truth.\n• Not until I read the letter did I understand the truth. — Xatni o'qimagunimcha haqiqatni tushunmadim.\n• Only after the lesson ended did the students leave.\n\n✍️ MASHQ:\n1. \"It was not until Monday ___ he called me.\" → ✅ that\n2. \"Not until Monday ___ (he / call) me.\" → ✅ did he call",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: It was not until X that + Ega + Kesim = Not until X + did + Ega + V1.\n\n🤖 TalkMe yakuniy: Barakalla! Not until qoliplari C2 yozma uslubning oltin namunasi! Ertaga: Ellipsis (gap bo'laklarini tushirish). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_373",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 37,
            titleEnglish = "373-Dars: C2 Ellipsis — Omitting Redundant Words",
            titleUzbek = "C2 Ellipsis — takroriy bo'laklarni tushirib qoldirish",
            objectiveUzbek = "🤖 TalkMe: Bugun nutqni ixcham va tabiiy qiluvchi C2 Ellipsis (so'zlarni qisqartirib tushirish) san'atini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nEllipsis — kontekstdan aniq bo'lgan so'zlarni takrorlamaslik uchun tushirib qoldirish:\n1. When / while / if / though / unless dan keyin 'ega + be' tushirilishi:\n• While (I was) waiting for the bus, I read the newspaper.\n• If (it is) necessary, I will come again tomorrow.\n2. Qo'shma gapning ikkinchi qismida takroriy ega yoki kesimni tushirish:\n• John went to the library and (John) borrowed two books.\n• Mary is fond of music, and her brother (is fond) of painting.\n3. Infinitivdan keyin faqat 'to' ni qoldirish:\n• \"Will you join us?\" — \"I'd love to (join you).\"\n\n💬 MISOLLAR:\n• Though (he was) exhausted, he continued his journey. — Holdan toygan bo'lsa ham, safarini davom ettirdi.\n• Correct the mistakes, if (there are) any. — Xatolar bo'lsa, tuzating.\n• He didn't come, though he had promised to (come).\n\n✍️ MASHQ:\n1. \"When you are in Rome, do as the Romans do.\" (Ellipsis bilan) → ✅ When in Rome, do as the Romans do.\n2. \"Would you like to go?\" — \"Yes, I'd like ___.\" → ✅ to",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ellipsis takrorni yo'qotib, jumlani ixchamlashtiradi: While waiting..., If necessary..., I'd love to. Yangi so'z: ellipsis.\n\n🤖 TalkMe yakuniy: Zo'r! Endi ingliz tilida ortiqcha takrorlarsiz tabiiy jumla tuzasiz! Ertaga: Fe'l takrorlashning oldini olish (Pro-forms). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_374",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 38,
            titleEnglish = "374-Dars: C2 Avoiding Verb Repetition — Pro-forms (do so, so/neither)",
            titleUzbek = "C2 Fe'l takrorlashdan qochish: do so, I think so, So do I",
            objectiveUzbek = "🤖 TalkMe: Bugun fe'l va butun gapni qayta takrorlamaslik uchun 'do so, I hope so / I'm afraid not, So do I / Neither do I' tuzilmalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. 'do so / did so' — oldingi gapdagi fe'l va uning to'ldiruvchilarini almashtiradi:\n• He promised to help us, and he did so. — U bizga yordam berishga va'da berdi va shunday qildi.\n2. 'so / not' — think, hope, believe, suppose, be afraid fe'llaridan keyin butun ergash gap o'rniga keladi:\n• \"Will it rain?\" — \"I hope not / I am afraid so.\"\n3. 'So + yordamchi fe'l + Ega' (tasdiqqa qo'shilish) va 'Neither / Nor + yordamchi fe'l + Ega' (inkorga qo'shilish):\n• \"I like classical music.\" — \"So do I.\" (Men ham).\n• \"I haven't seen that film.\" — \"Neither have I.\" (Men ham ko'rmaganman).\n\n💬 MISOLLAR:\n• She asked me to wait, and I did so without hesitation.\n• \"Is he coming today?\" — \"I think so.\" / \"I don't think so.\"\n• \"He can speak three languages.\" — \"So can his sister.\"\n\n✍️ MASHQ:\n1. \"I went to the library yesterday.\" — \"So ___ I.\" → ✅ did\n2. \"I cannot swim.\" — \"Neither ___ he.\" → ✅ can",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: do so — fe'l birikmasini almashtiradi; I think so / I hope not — gapni almashtiradi; So do I / Neither do I — qo'shilishni bildiradi.\n\n🤖 TalkMe yakuniy: Ajoyib! Endi C2 darajasidagi eng nozik mavzu — Idiomatik artikllarni (375–380 darslar) boshlaymiz! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_375",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 39,
            titleEnglish = "375-Dars: C2 Idiomatic Articles (1) — a hair vs hair, a paper vs paper",
            titleUzbek = "C2 Artikl — idiomatik (1): a hair vs hair, a paper vs paper",
            objectiveUzbek = "🤖 TalkMe: Bugun 'a/an' artikli qo'shilishi bilan ma'nosi butunlay o'zgaradigan otlarning birinchi juftliklarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nKo'pgina sanalmaydigan otlar 'a/an' artikli bilan kelganda bitta aniq buyum yoki boshqa ma'noni bildiradi:\n1. hair (umuman soch — sanalmaydi) vs a hair (bitta tola soch):\n• Her hair is dark and long. — Uning sochi qora va uzun.\n• Waiter, there is a hair in my soup! — Ofitsiant, sho'rvamda bitta soch tolasi bor!\n\n2. paper (qog'oz materiali — sanalmaydi) vs a paper (gazeta yoki ilmiy maqola):\n• Books are made of paper. — Kitoblar qog'ozdan tayyorlanadi.\n• He bought a morning paper at the kiosk. — U do'kondan ertalabki gazeta sotib oldi.\n\n💬 MISOLLAR:\n• She has gray hairs on her head. (bir nechta oq soch tolalari)\n• I need some paper to write a letter. (qog'oz)\n• Professor Smith presented a brilliant paper at the conference. (ilmiy ma'ruza/maqola)\n\n✍️ MASHQ:\n1. \"He read the news in ___ local paper.\" (a / —) → ✅ a\n2. \"She brushes her ___ every morning.\" (a hair / hair) → ✅ hair",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: hair (soch) vs a hair (bitta soch tolasi); paper (qog'oz) vs a paper (gazeta / ilmiy maqola).\n\n🤖 TalkMe yakuniy: Zo'r! Artikllarning ma'no o'zgartirish kuchini ko'rdingiz! Ertaga: a glass vs glass, a coffee vs coffee. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_376",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 40,
            titleEnglish = "376-Dars: C2 Idiomatic Articles (2) — a glass vs glass, a coffee vs coffee",
            titleUzbek = "C2 Artikl — idiomatik (2): a glass vs glass, a coffee vs coffee",
            objectiveUzbek = "🤖 TalkMe: Bugun modda nomi va idish/porsiya o'rtasidagi artikl farqini ('glass / a glass', 'coffee / a coffee', 'iron / an iron') o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. glass (shisha, oyna materiali) vs a glass (stakan, qadah) vs glasses (ko'zoynak):\n• The window is made of thick glass. — Deraza qalin shishadan yasalgan.\n• Bring me a glass of water, please. — Iltimos, menga bir stakan suv keltiring.\n\n2. coffee / tea / ice-cream (ichimlik/taom) vs a coffee / two coffees (bir finjon qahva / bir porsiya):\n• I prefer tea to coffee. — Men qahvadan choyni afzal ko'raman.\n• Two coffees and a tea, please! — Ikkita (finjon) qahva va bitta choy, iltimos!\n\n3. iron (temir moddasi) vs an iron (dazmol):\n• Iron is a heavy metal. / She bought an electric iron.\n\n💬 MISOLLAR:\n• Be careful, there is broken glass on the floor! (shisha siniqlari)\n• He drank a glass of fresh milk. (bir stakan)\n• Would you like an ice-cream? (bitta muzqaymoq porsiyasi)\n\n✍️ MASHQ:\n1. \"She ironed her dress with ___ hot iron.\" (an / —) → ✅ an\n2. \"Windows are made of ___. \" (a glass / glass) → ✅ glass",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: glass (shisha) vs a glass (stakan); coffee (ichimlik) vs a coffee (bir finjon qahva); iron (temir) vs an iron (dazmol).\n\n🤖 TalkMe yakuniy: Ajoyib! C2 artikl nozikliklarini oson tushunyapsiz! Ertaga: a room vs room, a noise vs noise. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_377",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 41,
            titleEnglish = "377-Dars: C2 Idiomatic Articles (3) — a room vs room, a noise vs noise",
            titleUzbek = "C2 Artikl — idiomatik (3): a room vs room, a noise vs noise",
            objectiveUzbek = "🤖 TalkMe: Bugun 'a room' (xona) va 'room' (bo'sh joy) hamda 'noise / a noise', 'light / a light' farqlarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. a room (xona — sanaladi) vs room (bo'sh joy, sig'im — sanalmaydi):\n• Their flat has three rooms. / I booked a single room. — xona.\n• There is no room for this table in the corner. — Burchakda bu stol uchun bo'sh joy yo'q.\n• Make room for the guest! — Mehmon uchun joy oching!\n\n2. noise (umumiy shovqin) vs a noise (aniq bitta tovush/shovqin):\n• Don't make so much noise! — Bunchalik shovqin qilmang!\n• Suddenly I heard a strange noise outside. — To'satdan tashqarida g'alati bir tovush eshitdim.\n\n3. light (yorug'lik) vs a light (chiroq, fonar):\n• Light travels faster than sound. / We saw a distant light in the village.\n\n💬 MISOLLAR:\n• Is there enough room for me in the car? — Mashinada men uchun yetarli joy bormi?\n• We stayed in a comfortable room at the hotel. — Mehmonxonada qulay xonada turdik.\n• Did you hear a noise downstairs? — Pastda biror tovush eshitdingizmi?\n\n✍️ MASHQ:\n1. \"There is no ___ in my bag for another book.\" (a room / room) → ✅ room\n2. \"Suddenly we heard ___ loud noise.\" (a / —) → ✅ a",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: a room (xona) vs room (bo'sh joy); noise (umumiy shovqin) vs a noise (bitta aniq tovush); light (yorug'lik) vs a light (chiroq).\n\n🤖 TalkMe yakuniy: Zo'r! 'Make room for new knowledge!' Ertaga: an experience vs experience, a time vs time. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_378",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 42,
            titleEnglish = "378-Dars: C2 Idiomatic Articles (4) — experience, time, work",
            titleUzbek = "C2 Artikl — idiomatik (4): an experience vs experience, a time vs time",
            objectiveUzbek = "🤖 TalkMe: Bugun 'experience' (malaka vs sarguzasht), 'time' (vaqt vs safar/vaqt oralig'i) va 'work' (mehnat vs asar) farqlarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. experience (hayotiy/ish tajribasi — sanalmaydi) vs an experience (boshdan kechirilgan voqea, sarguzasht):\n• He has ten years of experience in teaching. — Uning o'qituvchilikda 10 yillik tajribasi bor.\n• Our trip to the mountains was an unforgettable experience. — Tog'ga safarimiz unutilmas voqea bo'ldi.\n\n2. time (umumiy vaqt) vs a good time (yoqimli vaqt o'tkazish) / times (marta):\n• Time is money. — Vaqt — bu pul.\n• We had a wonderful time at the party. — Ziyofatda ajoyib vaqt o'tkazdik.\n\n3. work (ish, mehnat — sanalmaydi) vs a work (san'at/adabiyot asari):\n• I have a lot of work today. / \"Hamlet\" is a great work by Shakespeare.\n\n💬 MISOLLAR:\n• Experience is the best teacher. — Tajriba — eng yaxshi ustoz.\n• Meeting him was a pleasant experience. — Uni uchratish yoqimli voqea bo'ldi.\n• Have a good time! — Vaqtingizni chog' o'tkazing!\n\n✍️ MASHQ:\n1. \"She has ___ great experience in medicine.\" (a / —) → ✅ — (artikl qo'yilmaydi)\n2. \"The journey was ___ exciting experience.\" (an / —) → ✅ an",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: experience (tajriba) vs an experience (voqea/taassurot); time (vaqt) vs have a good time; work (mehnat) vs a work of art (asar).\n\n🤖 TalkMe yakuniy: Barakalla! Bu farqlar C2 testlarida eng ko'p tekshiriladi! Ertaga: a thought vs thought, a beauty vs beauty. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_379",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 43,
            titleEnglish = "379-Dars: C2 Idiomatic Articles (5) — a thought vs thought, a beauty vs beauty",
            titleUzbek = "C2 Artikl — idiomatik (5): a thought vs thought, a beauty vs beauty",
            objectiveUzbek = "🤖 TalkMe: Bugun mavhum otlarning ('thought, beauty, youth, pity') artikl bilan qanday aniq ma'no kasb etishini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. thought (tafakkur, o'ylash jarayoni — sanalmaydi) vs a thought (bitta aniq fikr, g'oya):\n• He was deep in thought. — U chuqur o'yga cho'mgan edi.\n• A sudden thought struck him. — Birdan uning miyasiga bir fikr keldi.\n\n2. beauty (go'zallik — mavhum) vs a beauty (sohibjamol inson yoki go'zal narsa):\n• We admired the beauty of nature. — Tabiat go'zalligiga mahliyo bo'ldik.\n• Her sister is a real beauty. — Uning opasi haqiqiy sohibjamol.\n\n3. Idiomatik iboralar: What a pity! (Attang!), It is a pleasure (Mazza/yoqimli), in a hurry (shoshilinchda), at a loss (dovdirab).\n\n💬 MISOLLAR:\n• Speech is the expression of thought. — Nutq tafakkur ifodasidir.\n• That's a good thought! — Bu yaxshi fikr!\n• What a shame that you couldn't come! — Kela olmaganingiz qanday achinarli!\n\n✍️ MASHQ:\n1. \"He sat silent, lost in ___ thought.\" (a / —) → ✅ —\n2. \"___ happy thought came into her mind.\" (A / —) → ✅ A",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: thought (tafakkur) vs a thought (fikr); beauty (go'zallik) vs a beauty (sohibjamol); in a hurry, at a loss, what a pity.\n\n🤖 TalkMe yakuniy: Ajoyib! Mavhum otlarning C2 artikl qoidasini ham egalladingiz! Ertaga: Idiomatik artikllar umumlashtiruvi va Partiya 19 yakuni. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_380",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 44,
            titleEnglish = "380-Dars: C2 Idiomatic Articles Synthesis & Part 19 Review",
            titleUzbek = "C2 Artikl — idiomatik (6): Umumlashtirish va Partiya 19 yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun C2 idiomatik artikllar (bed/school/hospital/prison artiklsiz va 'the' bilan) hamda Partiya 19 ni to'liq yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMuassasa va joy otlarida artikl ma'no farqlaydi:\n• school / university / college / hospital / prison / bed / church:\n1. O'zining ASOSIY VAZIFASI uchun borilsa — ARTIKLSIZ:\n• go to school (o'qish uchun), be in hospital (davolanish uchun), go to bed (uxlash uchun).\n2. BINO sifatida yoki ko'rishga borilsa — 'THE' bilan:\n• Mother went to the school to speak to the teacher. — Onam o'qituvchi bilan gaplashish uchun maktab binosiga bordi.\n• She went to the hospital to visit her friend. — U do'stini ko'rish uchun kasalxonaga bordi.\n• Sit down on the bed. — Karavot ustiga o'tir.\n\n💬 MISOLLAR:\n• After leaving school, he entered university. (o'quvchi/talaba sifatida)\n• The workmen went to the church to repair the roof. (binoni ta'mirlash uchun)\n• He is still in bed. (uxlayapti)\n\n✍️ MASHQ:\n1. \"Ali is ill; he is in ___ hospital.\" (the / —) → ✅ — (davolanyapti)\n2. \"I went to ___ hospital to see Ali.\" (the / —) → ✅ the (ko'rishga bordim)",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Asosiy maqsadda: go to school, in hospital, in bed (artiklsiz); bino/mebel sifatida: the school, the hospital, the bed. Partiya 19 (361–380) tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! 380 ta dars ortda qoldi! Oldinda so'nggi 20 ta dars — Partiya 20 (C2 Stilistika va 400 darslik kitob yakuni)! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 20 (YAKUNIY) — C2 STILISTIKA VA 400 DARSLIK KITOB YAKUNI (DARS 381–400)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_13_381",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 45,
            titleEnglish = "381-Dars: C2 Verb Stylistics — Formal vs Informal",
            titleUzbek = "C2 Fe'l stilistikasi (1): Rasmiy va norasmiy fe'llar",
            objectiveUzbek = "🤖 TalkMe: Yakuniy Partiya 20 ga xush kelibsiz! Bugun C2 darajasida rasmiy (Formal) va jonli (Informal) uslubda fe'l tanlash san'atini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nIngliz tilida bir xil ma'noni og'zaki nutqda Phrasal Verb (qo'shma fe'l) bilan, ilmiy va rasmiy uslubda esa lotin o'zakli bitta fe'l bilan ifodalash C2 mahoratidir:\n• put off (informal) → postpone (formal — keyinga qoldirmoq)\n• look into (informal) → investigate (formal — tekshirmoq, tadqiq qilmoq)\n• find out (informal) → discover / ascertain (formal — aniqlamoq)\n• make up (informal) → invent / constitute (formal — o'ylab topmoq / tashkil etmoq)\n• give up (informal) → abandon / renounce (formal — voz kechmoq)\n\n💬 MISOLLAR:\n• Informal: They put off the meeting. → Formal: The committee postponed the session.\n• Informal: The police are looking into the case. → Formal: The authorities are investigating the incident.\n• Informal: We need to find out the cause. → Formal: We must ascertain the cause.\n\n✍️ MASHQ:\n1. \"put off\" fe'lining rasmiy (C2) muqobili qaysi? → ✅ postpone\n2. \"look into\" fe'lining rasmiy (C2) muqobili qaysi? → ✅ investigate",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Informal (phrasal verbs: put off, look into, find out) vs Formal (postpone, investigate, ascertain).\n\n🤖 TalkMe yakuniy: Ajoyib! Rasmiy va norasmiy lug'atni o'rinli farqlay olasiz! Ertaga: Passiv va Aktiv uslubiy tanlovi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_382",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 46,
            titleEnglish = "382-Dars: C2 Verb Stylistics — Passive vs Active Choice",
            titleUzbek = "C2 Fe'l stilistikasi (2): Passiv va Aktiv uslubiy tanlovi",
            objectiveUzbek = "🤖 TalkMe: Bugun qachon Active Voice va qachon Impersonal Passive (shaxssiz majhul) ishlatish uslubiyatini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Jonli suhbat va badiiy hikoyada Active Voice (aniq nisbat) dinamika va kuch beradi:\n• The storm destroyed the old bridge. — Bo'ron eski ko'prikni vayron qildi.\n\n2. Ilmiy, akademik va rasmiy hisobotlarda xolislik (objectivity) uchun Impersonal Passive ishlatiladi:\n• It is generally assumed that... — Odatda shunday taxmin qilinadiki...\n• A series of experiments was conducted to verify the hypothesis. — Gipotezani tekshirish uchun bir qator tajribalar o'tkazildi.\n• Mistakes were made, and measures have been taken. — Xatolar qilindi va choralar ko'rildi.\n\n💬 MISOLLAR:\n• Active (jonli): We analyzed the samples. → Academic Passive: The samples were analyzed thoroughly.\n• Active: People think this method is effective. → Formal: This method is considered to be effective.\n\n✍️ MASHQ:\n1. \"We carried out the research.\" (Ilmiy passiv uslubda) → ✅ The research was carried out.\n2. \"People believe that...\" (Akademik kirish) → ✅ It is believed that...",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Active Voice — jonli va dinamik; Impersonal Passive (It is assumed that..., was conducted) — xolis va ilmiy. Yangi so'z: hypothesis.\n\n🤖 TalkMe yakuniy: Zo'r! Akademik yozuv (IELTS/TOEFL Essay) uslubini egallayapsiz! Ertaga: Qisqartmalar stilistikasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_383",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 47,
            titleEnglish = "383-Dars: C2 Verb Stylistics — Contractions & Modality",
            titleUzbek = "C2 Fe'l stilistikasi (3): Qisqartmalar va yumshoq modallik",
            objectiveUzbek = "🤖 TalkMe: Bugun rasmiy matnda qisqartmalardan (don't, can't) qochish va 'hedging' (ehtiyotkor ilmiy modallik) usulini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Rasmiy hujjat, ilmiy maqola va akademik esseda qisqartmalar (don't, can't, won't, it's) ISHLATILMAYDI — do not, cannot (qo'shib yoziladi!), will not, it is yoziladi.\n2. Hedging (keskinlikni yumshatish): C2 akademik uslubda 'This is wrong' o'rniga modal fe'llar va ravishlar bilan ehtiyotkor fikr bildiriladi:\n• This may suggest that... — Bu shuni ko'rsatishi mumkinki...\n• It would appear that the data is incomplete. — Ma'lumotlar to'liq emasdek ko'rinadi.\n• One might argue that... — Kimdir shunday dalil keltirishi mumkinki...\n\n💬 MISOLLAR:\n• Keskin: Coffee causes insomnia. → C2 Academic: Excessive coffee consumption may contribute to insomnia.\n• Norasmiy: We can't accept this. → Rasmiy: We cannot accept this proposal.\n\n✍️ MASHQ:\n1. Rasmiy yozuvda \"can't\" qanday yoziladi? (can not / cannot) → ✅ cannot (bitta so'z)\n2. \"It seems\" ning C2 akademik yumshoq shakli: \"It ___ appear that...\" → ✅ would",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Rasmiy yozuvda qisqartma ishlatilmaydi (cannot, do not). Hedging: may suggest, would appear, might argue.\n\n🤖 TalkMe yakuniy: Barakalla! Hedging — C2 akademik yozuvning eng yuqori belgisi! Ertaga: Fe'l inversiyasi stilistikasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_384",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 48,
            titleEnglish = "384-Dars: C2 Verb Stylistics — Stylistic Inversion",
            titleUzbek = "C2 Fe'l stilistikasi (4): Badiiy va o'rin inversiyasi",
            objectiveUzbek = "🤖 TalkMe: Bugun o'rin-joy holi gap boshiga chiqqanda fe'lning egadan oldin kelishi (Here comes..., On the hill stood...) qoidasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'rin-joy yoki yo'nalish ravishi/predlogli birikmasi gap boshiga chiqsa va ega OT bilan ifodalansa, to'liq fe'l egadan oldinga o'tadi:\n• On the top of the hill stood an ancient castle. — Tepalik ustida qadimiy qal'a qad rostlab turardi.\n• Down fell the rain! — Yomg'ir sharros quyib yubordi!\n• Here comes the bus! — Mana avtobus kelyapti!\n• Muhim: Agar ega kishilik olmoshi (I, he, she, they) bo'lsa, inversiya BO'LMAYDI:\n  Here he comes! (Here comes he EMAS!).\n\n💬 MISOLLAR:\n• In the center of the square rises a tall monument. — Maydon markazida baland haykal qad rostlagan.\n• Out rushed the children! — Bolalar yugurib chiqishdi!\n• There goes our train! / There it goes! (olmosh bilan inversiyasiz).\n\n✍️ MASHQ:\n1. \"Here ___ (come) the teacher!\" → ✅ comes\n2. \"Here ___ (he / come)!\" → ✅ he comes",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Joy holi + Fe'l + Ot-ega (On the hill stood a castle). Olmosh ega bo'lsa inversiya bo'lmaydi (Here he comes).\n\n🤖 TalkMe yakuniy: Ajoyib! Badiiy inversiyani ham suvdek tushundingiz! Ertaga: Fe'l ellipsisi va nominativ uslub. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_385",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 49,
            titleEnglish = "385-Dars: C2 Verb Stylistics — Elliptical & Nominal Patterns",
            titleUzbek = "C2 Fe'l stilistikasi (5): Fe'l ellipsisi va Nominalization",
            objectiveUzbek = "🤖 TalkMe: Bugun fe'lli gapni otlashgan birikmaga aylantirish (Nominalization) va ixcham C2 tuzilmalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Nominalization (Fe'lni otga aylantirish) — ilmiy va rasmiy matnni zich hamda salobatli qiladi:\n• Oddiy (fe'lli): The city grew rapidly, and this created new jobs.\n• C2 Nominal: The rapid growth of the city led to the creation of new jobs.\n\n2. Gapping (Murakkab gapda takroriy fe'lni tushirish):\n• Some scientists supported the theory; others (supported) the opposite view.\n• History teaches us wisdom; literature, eloquence. — Tarix bizga donolikni, adabiyot esa notiqlikni o'rgatadi.\n\n💬 MISOLLAR:\n• Oddiy: They decided to expand the factory. → C2: Their decision to expand the factory was approved.\n• One brother became a doctor; the other, an engineer.\n\n✍️ MASHQ:\n1. \"They analyzed the problem carefully.\" ('analysis' oti bilan boshlang) → ✅ A careful analysis of the problem was made.\n2. \"Ali speaks English; Vali, French.\" — Tushirilgan fe'l qaysi? → ✅ speaks",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Nominalization (fe'l → ot: grow → growth, decide → decision) va Gapping nutqni C2 darajasiga olib chiqadi.\n\n🤖 TalkMe yakuniy: Zo'r! Nominalization — akademik ingliz tilining kaliti! Ertaga: Fe'l stilistikasi umumlashtiruvi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_386",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 50,
            titleEnglish = "386-Dars: C2 Verb Stylistics — Synthesis",
            titleUzbek = "C2 Fe'l stilistikasi (6): Umumlashtirish va amaliyot",
            objectiveUzbek = "🤖 TalkMe: Bugun fe'l stilistikasining barcha 5 yo'nalishini (Formal, Passive, Hedging, Inversion, Nominalization) birgalikda mashq qilamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBir xil fikrni 3 xil uslubda ifodalash:\n• Oddiy og'zaki (B1): We looked into the problem and found out that we can't put off the work.\n• Rasmiy (C1): We investigated the issue and discovered that we cannot postpone the project.\n• Oliy akademik (C2): A thorough investigation of the issue revealed that under no circumstances could the project be postponed.\n\n💬 MISOLLAR:\n• Never before had such a profound transformation been observed. — Ilgari hech qachon bunday chuqur o'zgarish kuzatilmagan edi.\n• Should further evidence be discovered, the conclusion will be revised.\n\n✍️ MASHQ:\n1. \"We never saw such results.\" (C2 rasmiy inversiya + passiv) → ✅ Never were such results seen.\n2. \"look into\" + \"find out\" ning rasmiy juftligi: → ✅ investigate + ascertain/discover",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: C2 fe'l stilistikasi vaziyatga qarab og'zaki, rasmiy yoki akademik ohangni erkin boshqarish imkonini beradi. Yangi so'z: profound.\n\n🤖 TalkMe yakuniy: Mukammal! Fe'l stilistikasi bo'limini tugatdingiz! Ertaga: Gap tuzilishi stilistikasi (So'z tartibi va Fronting). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_387",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 51,
            titleEnglish = "387-Dars: C2 Sentence Stylistics — Fronting & Word Order",
            titleUzbek = "C2 Gap tuzilishi stilistikasi (1): So'z tartibi va Fronting",
            objectiveUzbek = "🤖 TalkMe: Bugun urg'u berish uchun to'ldiruvchi yoki kesim qismini gap boshiga chiqarish (Fronting) san'atini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nFronting — odatda gap oxirida turadigan bo'lakni urg'u uchun gap boshiga olib chiqish:\n1. To'ldiruvchini oldinga chiqarish (inversiya bo'lmaydi):\n• Oddiy: I can never forget this kindness.\n• Fronting: This kindness I can never forget. — Bu yaxshilikni men hech qachon unuta olmayman.\n\n2. Sifat yoki sifatdoshli kesimni oldinga chiqarish (inversiya bo'ladi):\n• Oddiy: Those days were happy indeed.\n• Fronting: Happy indeed were those days! — O'sha kunlar chindan ham baxtli edi!\n• Standing in the doorway was a tall stranger. — Eshik oldida baland bo'yli notanish kishi turardi.\n\n💬 MISOLLAR:\n• Such a mistake he will never make again. — Bunday xatoni u boshqa hech qachon qilmaydi.\n• Gone are the days when we travelled by horse. — Otda safar qilinadigan kunlar o'tib ketdi.\n• Particularly important is the question of safety. — Xavfsizlik masalasi ayniqsa muhimdir.\n\n✍️ MASHQ:\n1. \"The results were so impressive that...\" ('So impressive' bilan boshlang) → ✅ So impressive were the results that...\n2. \"A quiet river flowed through the valley.\" ('Flowing' bilan boshlang) → ✅ Flowing through the valley was a quiet river.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Fronting — urg'ulanadigan bo'lakni gap boshiga chiqarish (This book I have read; Gone are the days; So great was his joy).\n\n🤖 TalkMe yakuniy: Ajoyib! Fronting yordamida badiiy va notiqlik nutqini qura olasiz! Ertaga: Passiv gap stilistikasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_388",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 52,
            titleEnglish = "388-Dars: C2 Sentence Stylistics — End-Weight & Passive Flow",
            titleUzbek = "C2 Gap tuzilishi stilistikasi (2): Axborot oqimi va Passiv",
            objectiveUzbek = "🤖 TalkMe: Bugun ingliz jumlasida 'ma'lum axborotdan yangi va uzun axborotga o'tish' (End-Weight Principle) qonuniyatini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nIngliz tilida jumla ravon o'qilishi uchun:\n1. Ma'lum va qisqa bo'lak (Theme) gap BOSHIDA, yangi va uzun bo'lak (Rheme / End-Weight) esa gap OXIRIDA keladi.\n2. Passive Voice ko'pincha aynan shu tartibni saqlash uchun tanlanadi:\n• Noqulay (Active): A young scientist who had recently graduated from Oxford University discovered this element.\n• Ravon C2 (Passive): This element was discovered by a young scientist who had recently graduated from Oxford University.\n\n💬 MISOLLAR:\n• The book was written by one of the greatest historians of the twentieth century.\n• This decision was supported by all the members who attended the conference.\n\n✍️ MASHQ:\n1. Uzun va yangi ma'lumot ingliz gapining qayerida turishi uslubiy jihatdan afzal? → ✅ Gap oxirida (End-Weight)\n2. Qisqa egani oldinga olib chiqish uchun qaysi nisbat yordam beradi? → ✅ Passive Voice",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Theme (ma'lum/qisqa) → Rheme (yangi/uzun). Passive Voice gap oxiridagi og'ir bo'lakni muvozanatda ushlaydi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi nafaqat grammatik to'g'ri, balki ohangdor jumla tuzasiz! Ertaga: Murakkab davriy gaplar (Periodic Sentences). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_389",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 53,
            titleEnglish = "389-Dars: C2 Sentence Stylistics — Complex & Periodic Sentences",
            titleUzbek = "C2 Gap tuzilishi stilistikasi (3): Murakkab va parallel gaplar",
            objectiveUzbek = "🤖 TalkMe: Bugun C2 darajasidagi parallel tuzilmalar (Parallelism) va davriy murakkab gaplarni (Periodic Sentences) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Parallelism (Uyushiq bo'laklarning bir xil grammatik shaklda kelishi):\n• Noto'g'ri: She likes reading, swimming, and to ride a bicycle.\n• C2 Parallel: She likes reading, swimming, and riding a bicycle. (yoki: to read, to swim, and to ride).\n\n2. Periodic Sentence (Asosiy fikrning gap oxirida ochilishi):\nErgash gaplar va sifatdoshli oborotlar oldin kelib, bosh gap eng oxirida kuchli xulosa sifatida beriladi:\n• Having overcome numerous obstacles, having learned from every mistake, and having worked tirelessly for years, he finally achieved his dream.\n\n💬 MISOLLAR:\n• Not only by words, but also by deeds did he prove his loyalty.\n• To know a language is to understand its culture, its history, and its spirit.\n\n✍️ MASHQ:\n1. \"The job requires accuracy, speed, and ___ (be) patient.\" (Parallel qiling) → ✅ patience\n2. \"He came, he saw, he ___.\" (Rim parallelizmi) → ✅ conquered",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Parallelism uyushiq bo'laklarda bir xil grammatik qolipni talab qiladi; Periodic Sentence asosiy xulosani oxirida beradi.\n\n🤖 TalkMe yakuniy: Barakalla! Notiqlik san'ati (Rhetoric) sirlari sizga ochildi! Ertaga: Akademik uslub (Academic Style). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_390",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 54,
            titleEnglish = "390-Dars: C2 Sentence Stylistics — Academic Style",
            titleUzbek = "C2 Gap tuzilishi stilistikasi (4): Akademik va ilmiy uslub",
            objectiveUzbek = "🤖 TalkMe: Bugun xalqaro ilmiy maqolalar, диссертацияlar va IELTS/C2 esselarida qo'llaniladigan Akademik uslub qoidalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nC2 Akademik uslubning 4 oltin belgisi:\n1. Mantiqiy bog'lovchilar (Discourse Markers): Furthermore, Moreover, Nevertheless, Consequently, Whereas, Notwithstanding.\n2. Shaxssiz tuzilmalar: It is evident that..., There appears to be a correlation between...\n3. Otlashgan birikmalar (Nominalization) va sifatdoshli oborotlar.\n4. Hissiy bo'yoqdor va so'zlashuv so'zlaridan ('a lot of, kids, stuff, get') voz kechish → ('numerous/substantial, children, materials, obtain/acquire').\n\n💬 MISOLLAR:\n• So'zlashuv: A lot of kids get good results. → Academic: A substantial number of students achieve high academic performance.\n• So'zlashuv: Still, this didn't solve the problem. → Academic: Nevertheless, this measure failed to resolve the issue.\n\n✍️ MASHQ:\n1. \"a lot of\" birikmasining akademik muqobili: (substantial number of / lots of) → ✅ a substantial number of\n2. \"get\" fe'lining akademik muqobili ('bilim olmoq'): → ✅ acquire / obtain",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Akademik uslub: Discourse markers (moreover, nevertheless), shaxssiz qoliplar va rasmiy leksika (acquire, substantial).\n\n🤖 TalkMe yakuniy: Ajoyib! Akademik ingliz tilingiz eng yuqori darajada! Ertaga: Adabiy va badiiy uslub (Literary Style). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_391",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 55,
            titleEnglish = "391-Dars: C2 Sentence Stylistics — Literary Style",
            titleUzbek = "C2 Gap tuzilishi stilistikasi (5): Adabiy va badiiy uslub",
            objectiveUzbek = "🤖 TalkMe: Bugun ingliz klassik va zamonaviy adabiyotida uchraydigan badiiy grammatik vositalarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAdabiy uslubda (Literary Style) tasviriy kuch va ritm yaratish uchun quyidagilar qo'llaniladi:\n1. Sifatning otdan keyin kelishi (Postpositive Adjectives — kengaytirilgan aniqlovchi bilan):\n• A sky dark with approaching storm clouds. — Yaqinlashayotgan bo'ron bulutlari bilan qoraygan osmon.\n2. Subjunctive I (Eski va yuqori badiiy istak/shart mayli: be, come, suffice):\n• Be that as it may... — Nima bo'lganda ham...\n• Suffice it to say that... — Shuni aytish kifoyaki...\n• Come what may, we shall stand firm. — Nima bo'lsa ham, qat'iy turamiz.\n\n💬 MISOLLAR:\n• Long live peace and friendship! — Yashasin tinchlik va do'stlik!\n• Far be it from me to criticize your work. — Ishingizni tanqid qilish fikridan yiroqman.\n• Silent and majestic stood the mountains in the moonlight.\n\n✍️ MASHQ:\n1. \"Come what ___ (may / will), I will support you.\" → ✅ may\n2. \"Suffice it ___ (say) that the mission succeeded.\" → ✅ to say",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Adabiy C2 iboralari: Be that as it may, Come what may, Suffice it to say, Far be it from me.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Shekspir va zamonaviy klassiklarni aslida o'qiy olasiz! Ertaga: Gap tuzilishi stilistikasi umumlashtiruvi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_392",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 56,
            titleEnglish = "392-Dars: C2 Sentence Stylistics — Synthesis",
            titleUzbek = "C2 Gap tuzilishi stilistikasi (6): Umumlashtirish",
            objectiveUzbek = "🤖 TalkMe: Bugun C2 gap tuzilishi stilistikasining barcha 5 yo'nalishini (Fronting, End-Weight, Parallelism, Academic, Literary) umumlashtiramiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nC2 Gap tuzilishi mahorati:\n1. Fronting: Most striking was his calm voice. (Eng hayratlanarlisi uning vazmin ovozi edi).\n2. End-Weight: Qisqa ma'lum bo'lak oldinda, uzun yangi ma'lumot oxirida.\n3. Parallelism: Bir xil grammatik qolip ketma-ketligi.\n4. Academic Discourse: Nevertheless, Furthermore, Consequently.\n5. Literary Subjunctive: Be that as it may, Come what may, Suffice it to say.\n\n💬 MISOLLAR:\n• Not only did the research confirm the hypothesis, but it also opened new horizons for medicine.\n• Be that as it may, the decision remains unchanged.\n\n✍️ MASHQ:\n1. \"His calm voice was most striking.\" (Fronting bilan) → ✅ Most striking was his calm voice.\n2. \"Nima bo'lganda ham\" (C2 adabiy ibora) → ✅ Be that as it may",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: C2 Gap tuzilishi stilistikasi to'liq yakunlandi. Endi qo'shma va ko'chirma gaplarning C2 istisnolariga o'tamiz!\n\n🤖 TalkMe yakuniy: Barakalla! 392 ta dars ortda qoldi! Ertaga: Bog'lovchisiz (Asyndetic) qo'shma gaplar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_393",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 57,
            titleEnglish = "393-Dars: C2 Compound Sentences — Asyndetic Coordination (1)",
            titleUzbek = "C2 Qo'shma gaplar istisnolari (1): Bog'lovchisiz (Asyndetic) gaplar",
            objectiveUzbek = "🤖 TalkMe: Bugun hech qanday bog'lovchisiz (and, but, because siz), faqat nuqtali vergul yoki vergul bilan bog'lanadigan C2 qo'shma gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAsyndetic Coordination — bog'lovchilarni tushirib, gaplarni ohang va tinish belgilari (nuqtali vergul `;` yoki ikki nuqta `:`) bilan bog'lash. Bu uslub gapga tezlik va keskinlik beradi:\n1. Zidlov ma'nosida (`;` = whereas / but):\n• Some people work with their hands; others work with their minds.\n2. Sabab va izoh ma'nosida (`:` = because / namely):\n• He could not go on: his strength had completely left him.\n3. Ketma-ketlik (mashhur Sezar uslubi):\n• I came, I saw, I conquered. — Keldim, ko'rdim, g'alaba qozondim.\n\n💬 MISOLLAR:\n• The wind dropped; the sea became calm and mirror-like.\n• Do not delay: time is running out.\n• Speech is silver; silence is golden. — So'z — kumush, sukut — oltin.\n\n✍️ MASHQ:\n1. \"Speech is silver ___ silence is golden.\" (Bog'lovchisiz qaysi tinish belgisi qo'yiladi?) → ✅ ; (nuqtali vergul)\n2. Bog'lovchisiz bog'lanish ilmiy tilda nima deyiladi? → ✅ Asyndetic coordination",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Asyndetic (bog'lovchisiz) qo'shma gaplarda `;` zidlik yoki ketma-ketlikni, `:` esa sabab va izohni ifodalaydi. Yangi so'z: asyndetic.\n\n🤖 TalkMe yakuniy: Ajoyib! Tinish belgilari bilan ma'no boshqarish — C2 cho'qqisi! Ertaga: Bog'lovchisiz shart-natija gaplar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_394",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 58,
            titleEnglish = "394-Dars: C2 Compound Sentences — Asyndetic Conditionals (2)",
            titleUzbek = "C2 Qo'shma gaplar istisnolari (2): Bog'lovchisiz shart-natija",
            objectiveUzbek = "🤖 TalkMe: Bugun 'if' siz, buyruq mayli yoki maqol qolipida tuziladigan C2 bog'lovchisiz shart-natija gaplarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Buyruq mayli + and / or + Future Simple:\n• Take one step closer, and I will call the guard. (= If you take...) — Yana bir qadam yaqinlashsang, soqchini chaqiraman.\n• Hurry up, or you will miss the train. (= If you don't hurry...) — Shoshiling, bo'lmasa poyezddan qolib ketasiz.\n\n2. Hikmatli va ixcham bog'lovchisiz qoliplar:\n• Easy come, easy go. — Oson kelgan oson ketadi.\n• First come, first served. — Oldin kelganga birinchi xizmat ko'rsatiladi.\n• Nothing ventured, nothing gained. — Tavakkal qilmasang, hech narsaga erishmaysan.\n\n💬 MISOLLAR:\n• Give him an inch and he will take a mile.\n• Waste not, want not. — Isrof qilmasang, muhtoj bo'lmaysan.\n• Out of sight, out of mind. — Ko'zdan yiroq — ko'ngildan yiroq.\n\n✍️ MASHQ:\n1. \"Work hard, ___ you will succeed.\" (and / or) → ✅ and\n2. \"Start now, ___ you will be late.\" (and / or) → ✅ or",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: V1 + and + will (agar ...sang); V1 + or + will (agar ...masang). Maqol qoliplari: First come, first served.\n\n🤖 TalkMe yakuniy: Zo'r! Ingliz maqollari va ixcham konstruksiyalarini mukammal tushunasiz! Ertaga: Murakkab qo'shma gap istisnolari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_395",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 59,
            titleEnglish = "395-Dars: C2 Complex Sentences — Exceptions (for, so that, lest)",
            titleUzbek = "C2 Qo'shma gaplar istisnolari (3): for, lest, Provided that",
            objectiveUzbek = "🤖 TalkMe: Bugun C2 darajasidagi kamyob, ammo badiiy va huquqiy matnlarda muhim bo'lgan 'for', 'lest', 'provided that', 'on condition that' bog'lovchilarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. 'for' (chunki — verguldan keyin keladi, gap boshida HECH QACHON kelmaydi!):\n• We stayed indoors, for it was raining heavily. — Biz ichkarida qoldik, chunki qattiq yomg'ir yog'ayotgan edi.\n\n2. 'lest' (...masligi uchun, ...dan qo'rqib — ichida inkor bor, undan keyin 'should + V1' yoki 'V1' keladi, 'not' qo'yilmaydi!):\n• He walked quietly lest he should wake the baby. — U chaqaloqni uyg'otib yubormaslik uchun sekin yurdi.\n\n3. Rasmiy shart bog'lovchilari: provided (that), providing (that), on condition that (shu shart bilanki):\n• You may borrow the book provided that you return it on Monday.\n\n💬 MISOLLAR:\n• She wrote down the number lest she should forget it. — Unutib qo'ymaslik uchun raqamni yozib oldi.\n• He said nothing, for he had nothing to say.\n• I will sign the contract on condition that all terms are met.\n\n✍️ MASHQ:\n1. \"She spoke softly lest someone ___ (hear) her.\" → ✅ should hear (yoki hear)\n2. Qaysi 'chunki' bog'lovchisi gap boshida kela olmaydi? (Because / For) → ✅ For",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: ', for' — chunki (faqat gap o'rtasida); 'lest + should V1' — ...masligi uchun (not qo'yilmaydi); 'provided that' — shu shart bilanki.\n\n🤖 TalkMe yakuniy: Barakalla! 'Lest' va 'for' — C2 imtihonlarining eng nozik nuqtalari! Ertaga: Murakkab nisbiy ergash gap istisnolari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_396",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 60,
            titleEnglish = "396-Dars: C2 Complex Sentences — Sentential Relative & Double Bind",
            titleUzbek = "C2 Qo'shma gaplar istisnolari (4): ', which' va murakkab nisbiy gaplar",
            objectiveUzbek = "🤖 TalkMe: Bugun bitta otga emas, balki BUTUN OLDINGI GAPGA tegishli bo'lgan ', which' ergash gapini va 'of which / of whom' tuzilmalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Sentential Relative Clause (', which' butun oldingi gapni izohlaydi — 'bu esa'):\n• He passed all his exams with top marks, which surprised everyone. — U barcha imtihonlardan a'lo bahoga o'tdi, bu esa hammani hayratda qoldirdi.\n• Muhim: Bu holatda verguldan keyin FAQAT 'which' ishlatiladi ('that' yoki 'what' EMAS!).\n\n2. Miqdor so'zlari + of whom / of which:\n• She has three brothers, all of whom are doctors. — Uning uchta akasi bor, ularning barchasi shifokor.\n• I read two books, neither of which was interesting. — Ikkita kitob o'qidim, ularning hech biri qiziq emas edi.\n\n💬 MISOLLAR:\n• The train arrived on time, which was a great relief. — Poyezd vaqtida keldi, bu esa katta yengillik bo'ldi.\n• There were twenty applicants, most of whom had university degrees.\n• The house, the roof of which (= whose roof) was red, stood on the hill.\n\n✍️ MASHQ:\n1. \"He was late again, ___ made the teacher angry.\" (which / what / that) → ✅ which\n2. \"I met two friends, both of ___ (who / whom) studied with me.\" → ✅ whom",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: ', which' butun oldingi gapga ishora qiladi ('bu esa'); all/most/both/neither + of whom (inson) / of which (narsa).\n\n🤖 TalkMe yakuniy: Ajoyib! C2 nisbiy ergash gaplarning eng murakkab qoidasini ham egalladingiz! Ertaga: in the affirmative / in the negative. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_397",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 61,
            titleEnglish = "397-Dars: C2 Reported Speech Exceptions — in the affirmative / negative",
            titleUzbek = "C2 Ko'chirma gap istisnolari: in the affirmative / in the negative",
            objectiveUzbek = "🤖 TalkMe: Bugun C2 darajasida 'Yes' va 'No' javoblarini rasmiy o'zlashtirish ('in the affirmative / in the negative') hamda Free Indirect Speech uslubini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. 'Yes' va 'No' javoblarini rasmiy/adabiy o'zlashtirish:\n• \"Yes,\" he said. → He answered in the affirmative. — U tasdiq ma'nosida ('ha' deb) javob berdi.\n• \"No,\" she replied. → She replied in the negative. — U inkor ma'nosida ('yo'q' deb) javob berdi.\n\n2. Free Indirect Speech (Erkin o'zlashtirma gap — badiiy adabiyotda):\nSo'roq yoki his-hayajon ohangini saqlab, zamon va olmoshni o'zgartirish:\n• She paused at the door. Where could he have gone at this hour? — U eshik oldida to'xtadi. Bu paytda u qayerga ham ketgan bo'lishi mumkin edi?\n\n💬 MISOLLAR:\n• When asked whether he agreed to the terms, the witness answered in the affirmative.\n• I asked him if he needed assistance, and he replied in the negative.\n• The committee responded in the affirmative to our proposal.\n\n✍️ MASHQ:\n1. \"He said, 'Yes.'\" (C2 rasmiy uslubda) → ✅ He answered in the affirmative.\n2. \"She said, 'No.'\" (C2 rasmiy uslubda) → ✅ She answered (replied) in the negative.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: answer/reply in the affirmative ('ha' deb javob bermoq); answer/reply in the negative ('yo'q' deb javob bermoq). Yangi so'z: affirmative.\n\n🤖 TalkMe yakuniy: Zo'r! Barcha yangi mavzular (1–397 darslar) to'liq yakunlandi! Endi so'nggi 3 ta Oltin Takrorlash darsimiz qoldi! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_398",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 62,
            titleEnglish = "398-Dars: Grand Review 1 — A1 + A2 Foundation (Lessons 1–190)",
            titleUzbek = "Yakuniy takrorlash (1): A1 + A2 poydevori (1–190 darslar)",
            objectiveUzbek = "🤖 TalkMe: Bugun 400 darslik sayohatimizning birinchi yarmini — A1 va A2 darajalarining oltin poydevorini birgalikda takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA1 + A2 darajalarida biz nimalarni o'rgandik (1–190 darslar):\n1. Alifbo (A–Z), Sanoq va Tartib sonlar (1–1000+, kasrlar, sanalar).\n2. Otlar: sanaladigan/sanalmaydigan, ko'plik (-s/-es, men, children, sheep), Qaratqich kelishigi ('s va of).\n3. Sifatlar: tartibi va qiyosiy/orttirma darajalari (bigger, the biggest, more interesting, good–better–best).\n4. Olmoshlar: I/me/my/mine, myself, this/that/these/those, some/any/no, much/many/little/few.\n5. Asosiy zamonlar: Present Simple, Past Simple, Future Simple, Present Continuous, Past Continuous, Future Continuous va 'to be going to'.\n6. Artikllar (a/an, the, zero), Predloglar (in/on/at/by/with) va Gap tuzilishi (5 tur so'roq gap, There is/are).\n\n💬 MISOLLAR:\n• There are twenty students in our classroom, aren't there?\n• She was reading an interesting book when her brother came home.\n• This is the best lesson I have ever had!\n\n✍️ MASHQ:\n1. \"child\" otining ko'pligi va \"good\" sifatining orttirma darajasi? → ✅ children / the best\n2. \"When I ___ (enter) the room, she ___ (write) a letter.\" → ✅ entered / was writing",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A1 + A2 (1–190 darslar) — ingliz tilining mustahkam poydevori: barcha so'z turkumlari, Simple va Continuous zamonlari.\n\n🤖 TalkMe yakuniy: Ajoyib, [USER_NAME]! Siz Hello va A-B-C dan boshlab qanchalik uzoq yo'l bosib o'tganingizni ko'ring! Ertaga: B1 + B2 takrorlash! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_399",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 63,
            titleEnglish = "399-Dars: Grand Review 2 — B1 + B2 Mastery (Lessons 191–336)",
            titleUzbek = "Yakuniy takrorlash (2): B1 + B2 tizimi (191–336 darslar)",
            objectiveUzbek = "🤖 TalkMe: Bugun o'rta va yuqori-o'rta bosqich — B1 va B2 darajalarining barcha qoidalarini (191–336 darslar) takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nB1 + B2 darajalarida biz nimalarni egalladik (191–336 darslar):\n1. Perfect Tenses: Present Perfect (have/has V3), Past Perfect (had V3), Future Perfect (will have V3).\n2. Perfect Continuous: Present, Past va Future Perfect Continuous (have/had/will have been V-ing).\n3. Passive Voice: Barcha zamonlarda majhul nisbat (is done, was being done, has been done).\n4. Sequence of Tenses & Reported Speech: Zamonlar moslashuvi, Future-in-the-Past (would V1), o'zlashtirma gaplar.\n5. Modal Verbs: can/could, may/might, must/have to/need, should/ought to + Perfect Modals (must have V3, should have V3).\n6. Non-Finite Verbs: Infinitive (6 shakl + Complex Object), Gerund (V-ing) va Participle (Participle I, II, Perfect Participle).\n\n💬 MISOLLAR:\n• I have been learning English with TalkMe, and I have already completed 399 lessons!\n• Having finished his work, he realized that the letter had already been sent.\n• You should have told me earlier; I could have helped you.\n\n✍️ MASHQ:\n1. \"By next week we ___ (complete) the whole book.\" (Future Perfect) → ✅ will have completed\n2. \"He seems ___ (read) a lot when he was young.\" (Perfect Infinitive) → ✅ to have read",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: B1 + B2 (191–336 darslar) — 12 ta faol zamon, Majhul nisbat, Zamonlar moslashuvi, Modallar va Fe'lning shaxssiz shakllari.\n\n🤖 TalkMe yakuniy: MUKAMMAL! Ertaga — 400-DARS! C1 + C2 takrorlash va butun 400 darslik kitobning tantanali yakuni! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_13_400",
            topicId = "topic_13_c1_c2_mastery",
            orderIndex = 64,
            titleEnglish = "400-Dars: FINAL — C1 + C2 & Complete 400-Lesson Book Graduation!",
            titleUzbek = "400-DARS (YAKUNIY): C1 + C2 va Butun 400 darslik kitob yakuni! 🏆",
            objectiveUzbek = "🤖 TalkMe: TABRIKLAYMAN, [USER_NAME]! Siz 400-darsga — A1 dan C2 gacha bo'lgan ulkan kitobning eng yuqori cho'qqisiga yetib keldingiz! 🎉🏆🚀",
            explanationUzbek = "📖 QOIDA:\nC1 + C2 darajalarida (337–400 darslar) va butun 400 darslik kitobda siz egallagan mahorat:\n1. C1 Sintaktik oborotlar: Nominative with the Infinitive (He is said to...), For-to-Infinitive va Absolute Participial Construction (The weather being fine...).\n2. C1 Subjunctive & Conditionals: 2-tur, 3-tur va Mixed Conditionals, I wish (knew / had known), as if / as though, The more... the better, Bog'lovchisiz shartlar (Had I known, Were he here, Should need arise).\n3. C1 To'siqsiz va Chuqur Ko'chirma gap: Although vs Despite, Reporting Verbs, in the affirmative / in the negative.\n4. C2 Mastery: Inversiya (Never have I seen, No sooner...than, Hardly...when, Not until), Cleft Sentences (It is...that...), Ellipsis, Idiomatik Artikllar (a hair vs hair, a paper vs paper, a room vs room, an experience vs experience) hamda Akademik va Adabiy Stilistika!\n\n💬 MISOLLAR:\n• Never before had I imagined that I could master all 400 lessons from A1 to C2!\n• It is perseverance and daily practice that lead to true mastery.\n• Having completed the entire book, you are now ready to speak, read, and write English with confidence!\n\n✍️ MASHQ:\n1. Ovoz chiqarib faxr bilan ayting: \"I have completed all 400 lessons from A1 to C2!\"\n2. \"No sooner had I finished the 400th lesson ___ I felt proud of myself!\" → ✅ than",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 📚 INGLIZ TILI GRAMMATIKASI — 400 TA DARS (A1 DAN C2 GACHA) TO'LIQ YAKUNLANDI! Siz 20 ta partiya, 400 ta dars va barcha 6 ta CEFR darajasini (A1, A2, B1, B2, C1, C2) muvaffaqiyatli tamomladingiz!\n\n🤖 TalkMe yakuniy: [USER_NAME], SIZ BUNI UDDALADINGIZ! 1-darsda \"Men ingliz tilini o'rgana olaman!\" degan edingiz — bugun 400-darsni yakunlab, C2 cho'qqisiga chiqdingiz! Men siz bilan cheksiz faxrlanaman! Ingliz tili yo'lingiz doimo yorug' bo'lsin! 🏆🎉👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity("voc_p18_01", "topic_13_c1_c2_mastery", "sec_13_341", "Steamer", "paroxod, bug' kemasi", "Weather permitting, the steamer will leave tomorrow.", "Ob-havo imkon bersa, paroxod ertaga jo'naydi.", isStudied = false),
        VocabularyEntity("voc_p18_02", "topic_13_c1_c2_mastery", "sec_13_346", "Medicine", "dori, tibbiyot", "If you had taken the medicine, you would feel better now.", "Agar dorini ichganingizda edi, hozir o'zingizni yaxshiroq his qilardingiz.", isStudied = false),
        VocabularyEntity("voc_p18_03", "topic_13_c1_c2_mastery", "sec_13_357", "Delegation", "delegatsiya, vakillar guruhi", "With all documents signed, the delegation left the hall.", "Barcha hujjatlar imzolangach, delegatsiya zaldan chiqdi.", isStudied = false),
        VocabularyEntity("voc_p18_04", "topic_13_c1_c2_mastery", "sec_13_358", "Concession", "yon berish, to'siqsizlik", "Clauses of concession express contrast.", "To'siqsiz ergash gaplar zidlikni ifodalaydi.", isStudied = false),
        VocabularyEntity("voc_p19_01", "topic_13_c1_c2_mastery", "sec_13_362", "Customs officer", "bojxona xodimi", "The customs officer inquired how long I intended to stay.", "Bojxona xodimi qancha vaqt qolmoqchi ekanligimni so'radi.", isStudied = false),
        VocabularyEntity("voc_p19_02", "topic_13_c1_c2_mastery", "sec_13_369", "Inversion", "inversiya (teskari so'z tartibi)", "Negative inversion adds strong emphasis.", "Inkor inversiyasi kuchli urg'u beradi.", isStudied = false),
        VocabularyEntity("voc_p19_03", "topic_13_c1_c2_mastery", "sec_13_369", "Magnificent", "muhtasham, ajoyib", "Never have I seen such a magnificent view!", "Men hech qachon bunday muhtasham manzarani ko'rmaganman!", isStudied = false),
        VocabularyEntity("voc_p19_04", "topic_13_c1_c2_mastery", "sec_13_373", "Ellipsis", "ellipsis (bo'laklarni tushirish)", "Ellipsis helps avoid unnecessary repetition.", "Ellipsis keraksiz takrordan qochishga yordam beradi.", isStudied = false),
        VocabularyEntity("voc_p19_05", "topic_13_c1_c2_mastery", "sec_13_377", "Spare room", "bo'sh xona yoki bo'sh joy", "There is plenty of room in the hall.", "Zalda bo'sh joy juda ko'p.", isStudied = false),
        VocabularyEntity("voc_p19_06", "topic_13_c1_c2_mastery", "sec_13_379", "Striking thought", "hayratlanarli fikr", "A sudden striking thought came into his mind.", "Uning xayoliga to'satdan hayratlanarli bir fikr keldi.", isStudied = false),
        VocabularyEntity("voc_p20_01", "topic_13_c1_c2_mastery", "sec_13_381", "Postpone", "keyinga qoldirmoq (rasmiy)", "The committee decided to postpone the session.", "Qo'mita majlisni keyinga qoldirishga qaror qildi.", isStudied = false),
        VocabularyEntity("voc_p20_02", "topic_13_c1_c2_mastery", "sec_13_381", "Ascertain", "aniqlamoq, ishonch hosil qilmoq", "We must ascertain the true cause of the event.", "Biz hodisaning asl sababini aniqlashimiz shart.", isStudied = false),
        VocabularyEntity("voc_p20_03", "topic_13_c1_c2_mastery", "sec_13_382", "Hypothesis", "gipoteza, ilmiy faraz", "The experiment confirmed our initial hypothesis.", "Tajriba bizning dastlabki ilmiy farazimizni tasdiqladi.", isStudied = false),
        VocabularyEntity("voc_p20_04", "topic_13_c1_c2_mastery", "sec_13_386", "Profound", "chuqur, teran", "The book had a profound influence on his life.", "Kitob uning hayotiga teran ta'sir ko'rsatdi.", isStudied = false),
        VocabularyEntity("voc_p20_05", "topic_13_c1_c2_mastery", "sec_13_393", "Asyndetic", "bog'lovchisiz (sintaktik aloqa)", "Asyndetic clauses are joined without conjunctions.", "Bog'lovchisiz ergash gaplar bog'lovchilarsiz birikadi.", isStudied = false),
        VocabularyEntity("voc_p20_06", "topic_13_c1_c2_mastery", "sec_13_397", "Affirmative", "tasdiq javob, ijobiy", "The witness answered in the affirmative.", "Guvoh tasdiq ma'nosida javob berdi.", isStudied = false),
        VocabularyEntity("voc_p20_07", "topic_13_c1_c2_mastery", "sec_13_400", "Perseverance", "matonat, qat'iyat", "Perseverance leads to true mastery of English.", "Matonat ingliz tilini haqiqiy egallashga olib boradi.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "gram_p18_01",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_341",
            titleUzbek = "C1 Mustaqil sifatdoshli birikma (Absolute Participial Construction)",
            explanationUzbek = "Sifatdoshning o'z egasi bo'lib, bosh gapdan vergul bilan ajraladi: The weather being fine, we went for a walk. Weather permitting, we shall start tomorrow.",
            exampleEnglish = "The lesson being over, the students left the classroom.",
            exampleUzbek = "Dars tugagach, talabalar sinfxonadan chiqishdi.",
            associatedVocabularyIdsCsv = "voc_p18_01",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_p18_02",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_344",
            titleUzbek = "C1 Noreal shart gaplar (2-tur, 3-tur va Mixed) hamda Wish",
            explanationUzbek = "Hozirgi noreal: If + V2/were, would + V1 (I wish I knew). O'tgan noreal: If + had V3, would have V3 (I wish I had known). Mixed: If + had V3, would + V1 (now).",
            exampleEnglish = "If I had known about it yesterday, I would have told you.",
            exampleUzbek = "Agar kecha bu haqda bilganimda edi, sizga aytgan bo'lardim.",
            associatedVocabularyIdsCsv = "voc_p18_02",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_p18_03",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_353",
            titleUzbek = "C1 Bog'lovchisiz (Inversiyali) shart gaplar: Had, Were, Could, Should",
            explanationUzbek = "'If' tushirilsa, Had / Were / Could / Should egadan oldinga chiqadi: Had I known..., Were he here..., Should need arise...",
            exampleEnglish = "Had I known your address, I would have visited you.",
            exampleUzbek = "Manzilingizni bilganimda edi, siznikiga borgan bo'lardim.",
            associatedVocabularyIdsCsv = "voc_p18_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_p18_04",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_360",
            titleUzbek = "C1 To'siqsiz ergash gaplar: Although vs Despite / In spite of",
            explanationUzbek = "Although / Even though + Ega + Kesim (to'liq gap); Despite / In spite of + Ot yoki V-ing (gerund). Gapga to'g'ridan-to'g'ri bog'lash uchun: in spite of the fact that.",
            exampleEnglish = "Although it was raining, we went out. / Despite the rain, we went out.",
            exampleUzbek = "Yomg'ir yog'ayotganiga qaramay, ko'chaga chiqdik.",
            associatedVocabularyIdsCsv = "voc_p18_04",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_p19_01",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_369",
            titleUzbek = "C2 Inversiya va Cleft Sentences (It is...that...)",
            explanationUzbek = "Never / Seldom / Little / No sooner...than / Hardly...when / Not until gap boshida kelsa, yordamchi fe'l egadan oldinga o'tadi. 'It is/was ... that/who' esa gapning istalgan bo'lagiga 'aynan' deb urg'u beradi.",
            exampleEnglish = "Never have I seen such beauty! No sooner had he arrived than it started to rain.",
            exampleUzbek = "Men hech qachon bunday go'zallikni ko'rmaganman! U yetib kelib ulgurmasdan yomg'ir boshlandi.",
            associatedVocabularyIdsCsv = "voc_p19_02,voc_p19_03",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_p19_02",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_375",
            titleUzbek = "C2 Idiomatik Artikllar: Sanalmaydigan va Sanaladigan ma'no farqi",
            explanationUzbek = "hair (soch) vs a hair (soch tolasi); paper (qog'oz) vs a paper (gazeta/maqola); glass (shisha) vs a glass (stakan); room (bo'sh joy) vs a room (xona); experience (tajriba) vs an experience (voqea).",
            exampleEnglish = "There is no room here. / I booked a room. / It was an unforgettable experience.",
            exampleUzbek = "Bu yerda bo'sh joy yo'q. / Men xona band qildim. / Bu unutilmas voqea bo'ldi.",
            associatedVocabularyIdsCsv = "voc_p19_05,voc_p19_06",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_p20_01",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_381",
            titleUzbek = "C2 Fe'l va Gap Stilistikasi (Formal, Academic, Literary, Asyndetic)",
            explanationUzbek = "C2 uslubda phrasal fe'llar o'rniga rasmiy fe'llar (postpone, investigate, ascertain), Hedging (may suggest, would appear), Fronting (Gone are the days), lest + should V1, ', which' va 'in the affirmative / in the negative' qo'llaniladi.",
            exampleEnglish = "Be that as it may, the witness answered in the affirmative.",
            exampleUzbek = "Nima bo'lganda ham, guvoh tasdiq ma'nosida javob berdi.",
            associatedVocabularyIdsCsv = "voc_p20_01,voc_p20_02,voc_p20_03,voc_p20_06,voc_p20_07",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_p18_01",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_341",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "Bo'sh joyni to'ldiring (Mustaqil sifatdoshli birikma): \"The weather ___ fine, we went for a walk.\"",
            subPromptText = "341-Dars: Absolute Participial Construction",
            audioTextToSpeak = "The weather being fine, we went for a walk.",
            optionsPipeSeparated = "being|was|is|be",
            correctAnswer = "being",
            explanationUzbek = "Mustaqil sifatdoshli birikmada kesim o'rnida Participle I (being) ishlatiladi: \"The weather being fine, we went for a walk.\"",
            vocabularyIdsCsv = "voc_p18_01",
            grammarRuleIdsCsv = "gram_p18_01"
        ),
        ExerciseEntity(
            id = "ex_p18_02",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_345",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "Bo'sh joyni to'ldiring (3-tur noreal shart): \"If I ___ about it yesterday, I would have told you.\"",
            subPromptText = "345-Dars: Unreal Past Conditional",
            audioTextToSpeak = "If I had known about it yesterday, I would have told you.",
            optionsPipeSeparated = "had known|knew|know|would know",
            correctAnswer = "had known",
            explanationUzbek = "O'tgan zamondagi noreal shartning 'If' qismida Past Perfect (had + V3) ishlatiladi: \"If I had known...\"",
            vocabularyIdsCsv = "voc_p18_02",
            grammarRuleIdsCsv = "gram_p18_02"
        ),
        ExerciseEntity(
            id = "ex_p18_03",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_353",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "Bog'lovchisiz (inversiyali) shart gapni tanlang: \"___ I known the truth, I would have helped him.\"",
            subPromptText = "353-Dars: Had I known...",
            audioTextToSpeak = "Had I known the truth, I would have helped him.",
            optionsPipeSeparated = "Had|Have|Did|Were",
            correctAnswer = "Had",
            explanationUzbek = "'If I had known' birikmasida 'if' tushirilsa, 'Had' egadan oldinga chiqadi: \"Had I known...\"",
            vocabularyIdsCsv = "voc_p18_03",
            grammarRuleIdsCsv = "gram_p18_03"
        ),
        ExerciseEntity(
            id = "ex_p18_04",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_360",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "Bo'sh joyni to'ldiring: \"___ the heavy rain, the match continued.\"",
            subPromptText = "360-Dars: Despite vs Although",
            audioTextToSpeak = "Despite the heavy rain, the match continued.",
            optionsPipeSeparated = "Despite|Although|Even though|However",
            correctAnswer = "Despite",
            explanationUzbek = "'the heavy rain' — otli birikma (gap emas), shuning uchun 'Despite' ishlatiladi.",
            vocabularyIdsCsv = "voc_p18_04",
            grammarRuleIdsCsv = "gram_p18_04"
        ),
        ExerciseEntity(
            id = "ex_p19_01",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_370",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "Bo'sh joyni to'ldiring (C2 Inversiya): \"No sooner had we left the house ___ it began to rain.\"",
            subPromptText = "370-Dars: No sooner... than",
            audioTextToSpeak = "No sooner had we left the house than it began to rain.",
            optionsPipeSeparated = "than|when|that|then",
            correctAnswer = "than",
            explanationUzbek = "'No sooner' qolipi har doim 'than' bilan bog'lanadi (Hardly/Scarcely esa 'when' bilan).",
            vocabularyIdsCsv = "voc_p19_02,voc_p19_03",
            grammarRuleIdsCsv = "gram_p19_01"
        ),
        ExerciseEntity(
            id = "ex_p19_02",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_377",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"Mashinada men uchun bo'sh joy yo'q\" gapida to'g'ri artiklni tanlang:",
            subPromptText = "377-Dars: a room (xona) vs room (bo'sh joy)",
            audioTextToSpeak = "There is no room for me in the car.",
            optionsPipeSeparated = "There is no room for me in the car.|There is no a room for me in the car.|There are no rooms for me in the car.|There is not the room for me in the car.",
            correctAnswer = "There is no room for me in the car.",
            explanationUzbek = "'room' so'zi 'bo'sh joy, sig'im' ma'nosida sanalmaydigan ot bo'lib, artiklsiz keladi.",
            vocabularyIdsCsv = "voc_p19_05",
            grammarRuleIdsCsv = "gram_p19_02"
        ),
        ExerciseEntity(
            id = "ex_p20_01",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_397",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "\"U 'ha' (tasdiq ma'nosida) javob berdi\" gapining C2 rasmiy shaklini toping:",
            subPromptText = "397-Dars: in the affirmative / in the negative",
            audioTextToSpeak = "He answered in the affirmative.",
            optionsPipeSeparated = "He answered in the affirmative.|He answered in the negative.|He answered with yes.|He replied in the positive.",
            correctAnswer = "He answered in the affirmative.",
            explanationUzbek = "C2 rasmiy uslubda 'Yes' deb javob berish — 'answer/reply in the affirmative' deyiladi.",
            vocabularyIdsCsv = "voc_p20_06",
            grammarRuleIdsCsv = "gram_p20_01"
        ),
        ExerciseEntity(
            id = "ex_p20_02",
            topicId = "topic_13_c1_c2_mastery",
            sectionId = "sec_13_400",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 3,
            promptText = "400-Dars Yakuniy Savol: \"Men ilgari hech qachon 400 ta darsni tugataman deb o'ylamagan edim!\" (C2 Inversiya bilan):",
            subPromptText = "400-Dars: C1 + C2 Mastery & Book Completion",
            audioTextToSpeak = "Never before had I imagined completing all four hundred lessons!",
            optionsPipeSeparated = "Never before had I imagined completing all 400 lessons!|Never before I had imagined completing all 400 lessons!|Never before did I had imagined completing all 400 lessons!|Never had before I imagined completing all 400 lessons!",
            correctAnswer = "Never before had I imagined completing all 400 lessons!",
            explanationUzbek = "'Never before' gap boshiga chiqqanda yordamchi fe'l egadan oldinga o'tadi: \"Never before had I imagined...\"",
            vocabularyIdsCsv = "voc_p20_07",
            grammarRuleIdsCsv = "gram_p19_01,gram_p20_01"
        )
    )
}
