package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 10 — DARS 181–200 (TO'LIQ):
 * - A2 GAP BO'LAKLARI VA QO'SHMA GAPLAR (Dars 181–190, topic_10_a2_grammar, orderIndex 71..80)
 * - B1 SONLAR VA OTLAR (Dars 191–200, topic_11_b1_perfect_passive, orderIndex 1..10)
 *
 * PARTIYA 11 — DARS 201–220 (TO'LIQ):
 * - B1 SIFAT, OLMOSH, RAVISH VA PRESENT PERFECT (Dars 201–220, topic_11_b1_perfect_passive, orderIndex 11..30)
 */
object CurriculumPartiya10And11SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 10 — A2 GAP BO'LAKLARI VA QO'SHMA GAPLAR (Dars 181–190)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_10_181",
            topicId = "topic_10_a2_grammar",
            orderIndex = 71,
            titleEnglish = "181-Dars: Direct Object",
            titleUzbek = "Vositasiz to'ldiruvchi (Direct Object)",
            objectiveUzbek = "🤖 TalkMe: Salom! Bugun gap bo'laklaridan vositasiz to'ldiruvchini (Direct Object) o'rganamiz. U fe'ldan keyin kelib, kimni yoki nimani savoliga javob beradi! 🚀",
            explanationUzbek = "📖 QOIDA:\nVositasiz to'ldiruvchi (Direct Object) o'timli fe'ldan keyin keladi va Kimni? (Whom?) yoki Nimani? (What?) savoliga javob beradi.\n• I read a book. (Nimani? — kitobni)\n• She loves him. (Kimni? — uni)\n\n💬 MISOLLAR:\n• I read a book. — Men kitob o'qiyman.\n• She loves him. — U uni sevadi.\n• We eat apples. — Biz olma yeymiz.\n• He bought a car. — U mashina sotib oldi.\n• They saw us. — Ular bizni ko'rishdi.\n\n✍️ MASHQ:\n1. \"Men kitob o'qiyman\" gapida to'ldiruvchi qaysi? → ✅ a book\n2. \"U mashina sotib oldi\" → ✅ He bought a car.\n3. Vositasiz to'ldiruvchi qaysi savolga javob beradi? → ✅ Kimni? Nimani?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Vositasiz to'ldiruvchi fe'ldan keyin keladi va Kimni? Nimani? savoliga javob beradi. Uyga vazifa: 5 ta vositasiz to'ldiruvchili gap yozing.\n\n🤖 TalkMe yakuniy: Ajoyib! Vositasiz to'ldiruvchini o'zlashtirdingiz! Ertaga: vositali to'ldiruvchi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_182",
            topicId = "topic_10_a2_grammar",
            orderIndex = 72,
            titleEnglish = "182-Dars: Indirect Object",
            titleUzbek = "Vositali to'ldiruvchi (Indirect Object)",
            objectiveUzbek = "🤖 TalkMe: Bugun vositali to'ldiruvchini (Indirect Object) o'rganamiz: Give me the book! 🚀",
            explanationUzbek = "📖 QOIDA:\nVositali to'ldiruvchi (Indirect Object) Kimga? (To whom?) savoliga javob beradi. U ikki xil joylashadi:\n1) Fe'l + Kimga + Nimani (predlogsiz): Give me the book.\n2) Fe'l + Nimani + to/for + Kimga: Give the book to me.\n\n💬 MISOLLAR:\n• Give me the book. — Menga kitobni bering.\n• She sent him a letter. — U unga xat yubordi.\n• Tell us a story. — Bizga hikoya aytib bering.\n• He bought her a gift. — U unga sovg'a sotib oldi.\n• Show me your photo. — Menga rasmingizni ko'rsating.\n\n✍️ MASHQ:\n1. \"Menga kitobni bering\" → ✅ Give me the book.\n2. \"U unga xat yubordi\" → ✅ She sent him a letter.\n3. \"Bizga hikoya aytib bering\" → ✅ Tell us a story.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Vositali to'ldiruvchi Kimga? savoliga javob beradi: Give me a book yoki Give a book to me.\n\n🤖 TalkMe yakuniy: Zo'r! Vositali to'ldiruvchini bilib oldingiz! Ertaga: predlogli to'ldiruvchi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_183",
            topicId = "topic_10_a2_grammar",
            orderIndex = 73,
            titleEnglish = "183-Dars: Prepositional Object",
            titleUzbek = "Predlogli to'ldiruvchi (listen to, look at, wait for)",
            objectiveUzbek = "🤖 TalkMe: Bugun predlogli to'ldiruvchini o'rganamiz: listen to, look at, wait for! 🚀",
            explanationUzbek = "📖 QOIDA:\nBa'zi fe'llardan keyin har doim maxsus predlog keladi:\n• listen to — tinglamoq\n• look at — qaramoq\n• wait for — kutmoq\n• talk about — haqida gaplashmoq\n• depend on — bog'liq bo'lmoq\n\n💬 MISOLLAR:\n• I listen to music. — Men musiqa tinglayman.\n• Look at the board! — Doskaga qarang!\n• Wait for me! — Meni kuting!\n• We talked about the film. — Biz film haqida gaplashdik.\n• He agrees with you. — U sizning fikringizga qo'shiladi.\n\n✍️ MASHQ:\n1. \"Men musiqa tinglayman\" → ✅ I listen to music.\n2. \"Doskaga qarang!\" → ✅ Look at the board!\n3. \"Meni kuting!\" → ✅ Wait for me!",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Predlogli to'ldiruvchi: listen to, look at, wait for, talk about. Uyga vazifa: shu fe'llar bilan 5 ta gap yozing.\n\n🤖 TalkMe yakuniy: Barakalla! Predlogli fe'llarni o'rgandingiz! Ertaga: aniqlovchi — sifat. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_184",
            topicId = "topic_10_a2_grammar",
            orderIndex = 74,
            titleEnglish = "184-Dars: Attribute — Adjectives & Possessives",
            titleUzbek = "Aniqlovchi — Sifat va Qaratqich kelishigi",
            objectiveUzbek = "🤖 TalkMe: Bugun aniqlovchi (Attribute) gap bo'lagini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAniqlovchi (Attribute) otni ta'riflaydi va Qanday? Qaysi? Kimning? savollariga javob beradi. Otdan oldin keladi: a red car (qanday mashina?), my friend's house (kimning uyi?).\n\n💬 MISOLLAR:\n• It is a beautiful day. — Bu chiroyli kun.\n• She wears a warm coat. — U issiq palto kiyadi.\n• This is my brother's bike. — Bu akamning velosipedi.\n• I like hot tea. — Men issiq choyni yoqtiraman.\n\n✍️ MASHQ:\n1. \"U issiq palto kiyadi\" → ✅ She wears a warm coat.\n2. \"Bu akamning velosipedi\" → ✅ This is my brother's bike.\n3. Aniqlovchi qaysi so'z turkumini ta'riflaydi? → ✅ Otni (Noun).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Aniqlovchi otdan oldin kelib, Qanday? Kimning? savollariga javob beradi.\n\n🤖 TalkMe yakuniy: Ajoyib! Aniqlovchini o'zlashtirdingiz! Ertaga: aniqlovchi — son va ko'rsatish olmoshi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_185",
            topicId = "topic_10_a2_grammar",
            orderIndex = 75,
            titleEnglish = "185-Dars: Attribute — Numerals & Demonstratives",
            titleUzbek = "Aniqlovchi — Son va Ko'rsatish olmoshi",
            objectiveUzbek = "🤖 TalkMe: Bugun son va ko'rsatish olmoshlarining aniqlovchi vazifasini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSonlar (three books, the first lesson) va ko'rsatish olmoshlari (this, that, these, those) ham gapda aniqlovchi bo'lib, otdan oldin keladi va Nechta? Nechanchi? Qaysi? savollariga javob beradi.\n\n💬 MISOLLAR:\n• I have three sisters. — Mening uchta opam bor.\n• This is the second floor. — Bu ikkinchi qavat.\n• These apples are sweet. — Bu olmalar shirin.\n• That building is very tall. — Anavi bino juda baland.\n\n✍️ MASHQ:\n1. \"Mening uchta opam bor\" → ✅ I have three sisters.\n2. \"Bu ikkinchi qavat\" → ✅ This is the second floor.\n3. \"Bu olmalar shirin\" → ✅ These apples are sweet.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Son va ko'rsatish olmoshlari otdan oldin aniqlovchi bo'lib keladi.\n\n🤖 TalkMe yakuniy: Zo'r! Aniqlovchilar mavzusini tugatdik! Ertaga: payt va o'rin hollari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_186",
            topicId = "topic_10_a2_grammar",
            orderIndex = 76,
            titleEnglish = "186-Dars: Adverbials of Place & Time",
            titleUzbek = "Hol — O'rin va Payt holi (Place → Time)",
            objectiveUzbek = "🤖 TalkMe: Bugun gapda o'rin va payt hollarining joylashish tartibini o'rganamiz: Place → Time! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'rin holi (Qayerda? Qayerga? — at home, to school) va payt holi (Qachon? — yesterday, every day) odatda gapning oxirida keladi. Agar ikkalasi birga kelsa, avval o'rin, keyin payt keladi: Place → Time.\n\n💬 MISOLLAR:\n• I go to school every day. — Men har kuni maktabga boraman.\n• She worked at the office yesterday. — U kecha ofisda ishladi.\n• We met in the park at 5 o'clock. — Biz soat 5 da bog'da uchrashdik.\n• Tomorrow I will stay at home. — Ertaga men uyda qolaman.\n\n✍️ MASHQ:\n1. \"Men har kuni maktabga boraman\" → ✅ I go to school every day.\n2. \"U kecha ofisda ishladi\" → ✅ She worked at the office yesterday.\n3. Gap oxirida qaysi hol birinchi keladi? a) O'rin b) Payt → ✅ a) O'rin (Place → Time)",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Gap oxirida avval O'rin (Place), keyin Payt (Time) keladi: go to school every day.\n\n🤖 TalkMe yakuniy: Ajoyib! O'rin va payt holini o'rgandingiz! Ertaga: ravish holi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_187",
            topicId = "topic_10_a2_grammar",
            orderIndex = 77,
            titleEnglish = "187-Dars: Adverbial of Manner",
            titleUzbek = "Hol — Ravish holi (Qanday qilib?)",
            objectiveUzbek = "🤖 TalkMe: Bugun ravish holini (Qanday qilib?) va uning gapdagi o'rnini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nRavish holi (Manner) Qanday qilib? (How?) savoliga javob beradi: quickly, slowly, well, carefully. U fe'ldan yoki to'ldiruvchidan keyin keladi: She speaks English well (speaks well English EMAS!).\n\n💬 MISOLLAR:\n• She speaks English well. — U inglizcha yaxshi gapiradi.\n• He drives carefully. — U ehtiyotkorlik bilan haydaydi.\n• The children played happily. — Bolalar xursand bo'lib o'ynashdi.\n• Please read the text slowly. — Iltimos, matnni sekin o'qing.\n\n✍️ MASHQ:\n1. \"U inglizcha yaxshi gapiradi\" → ✅ She speaks English well.\n2. \"U ehtiyotkorlik bilan haydaydi\" → ✅ He drives carefully.\n3. Fe'l va to'ldiruvchi orasiga ravish qo'yiladimi? → ✅ Yo'q, to'ldiruvchidan keyin keladi.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ravish holi fe'l yoki to'ldiruvchidan keyin keladi (speaks English well).\n\n🤖 TalkMe yakuniy: Zo'r! Ravish holini bilib oldingiz! Ertaga: gap bo'laklarining oltin tartibi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_188",
            topicId = "topic_10_a2_grammar",
            orderIndex = 78,
            titleEnglish = "188-Dars: Word Order S+V+O+M+P+T",
            titleUzbek = "Gap bo'laklari tartibi: S + V + O + M + P + T",
            objectiveUzbek = "🤖 TalkMe: Bugun ingliz tilidagi gap bo'laklarining to'liq oltin formulasini o'rganamiz: S + V + O + M + P + T! 🚀",
            explanationUzbek = "📖 QOIDA:\nIngliz gapining oltin formulasi:\nSubject (Ega) + Verb (Kesim) + Object (To'ldiruvchi) + Manner (Qanday?) + Place (Qayerda?) + Time (Qachon?).\nMasalan: She (S) read (V) the book (O) quietly (M) in the library (P) yesterday (T).\n\n💬 MISOLLAR:\n• She read the book quietly in the library yesterday. — U kecha kutubxonada kitobni tinchgina o'qidi.\n• He played football well at the stadium last Sunday. — U o'tgan yakshanba stadionda futbolni yaxshi o'ynadi.\n• I ate breakfast quickly in the kitchen this morning. — Men bugun ertalab oshxonada nonushtani tez yedim.\n\n✍️ MASHQ:\n1. \"U kecha kutubxonada kitobni tinchgina o'qidi\" → ✅ She read the book quietly in the library yesterday.\n2. Hollarning tartibi qanday? → ✅ Manner (Qanday) → Place (Qayerda) → Time (Qachon).",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Tartib: S + V + O + Manner + Place + Time. Uyga vazifa: shu oltin formula bo'yicha 3 ta to'liq gap yozing.\n\n🤖 TalkMe yakuniy: Barakalla! Endi har qanday gapni to'g'ri tartibda tuza olasiz! Ertaga: qo'shma gaplar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_189",
            topicId = "topic_10_a2_grammar",
            orderIndex = 79,
            titleEnglish = "189-Dars: Compound Sentences",
            titleUzbek = "Bog'langan qo'shma gap (and, but, or, so)",
            objectiveUzbek = "🤖 TalkMe: Bugun ikkita sodda gapdan bitta chiroyli qo'shma gap tuzishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBog'langan qo'shma gap ikkita mustaqil sodda gapning and (va), but (lekin), or (yoki), so (shuning uchun) bog'lovchilari bilan birikishidan hosil bo'ladi. Bog'lovchidan oldin vergul qo'yiladi.\n\n💬 MISOLLAR:\n• I called him, but he did not answer. — Men unga qo'ng'iroq qildim, lekin u javob bermadi.\n• It was raining, so we took an umbrella. — Yomg'ir yog'ayotgan edi, shuning uchun soyabon oldik.\n• She cooked dinner, and I washed the dishes. — U kechki ovqat pishirdi va men idishlarni yuvdim.\n\n✍️ MASHQ:\n1. \"Men unga qo'ng'iroq qildim, lekin u javob bermadi\" → ✅ I called him, but he did not answer.\n2. \"Yomg'ir yog'ayotgan edi, shuning uchun soyabon oldik\" → ✅ It was raining, so we took an umbrella.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Qo'shma gap: Gap 1, + and/but/or/so + Gap 2. Uyga vazifa: 4 ta bog'langan qo'shma gap yozing.\n\n🤖 TalkMe yakuniy: Ajoyib! Qo'shma gaplarni ham o'zlashtirdingiz! Ertaga: A2 daraja yakuni — 190-dars! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_190",
            topicId = "topic_10_a2_grammar",
            orderIndex = 80,
            titleEnglish = "190-Dars: A2 Level Mastery Review",
            titleUzbek = "A2 Daraja Yakuni — Katta Takrorlash!",
            objectiveUzbek = "🤖 TalkMe: TABRIKLAYMIZ! Bugun 190-dars — A2 darajasining katta yakuni! Keling, barcha o'rganganlarimizni birlashtiramiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSiz A2 darajasini (111–190-darslar) to'liq tugatdingiz! Siz minglab sonlar, sanaladigan va sanalmaydigan otlar, sifat darajalari (bigger, the biggest), Present Continuous, Past Simple, some/any/much/many, predloglar va qo'shma gaplarni bilasiz!\n\n💬 MISOLLAR:\n• Yesterday I bought a new book because it was very interesting. — Kecha men yangi kitob sotib oldim, chunki u juda qiziqarli edi.\n• Look! She is speaking English more fluently than before. — Qarang! U ingliz tilida avvalgidan ravonroq gapiryapti.\n• There isn't much milk in the fridge, so I will buy some. — Muzlatkichda ko'p sut yo'q, shuning uchun biroz sotib olaman.\n\n✍️ MASHQ:\n1. \"Kecha men yangi kitob sotib oldim, chunki u juda qiziqarli edi\" → ✅ Yesterday I bought a new book because it was very interesting.\n2. \"Muzlatkichda ko'p sut yo'q\" → ✅ There isn't much milk in the fridge.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A2 darajasi (80 ta dars) muvaffaqiyatli yakunlandi! Endi B1 (O'rta daraja) ga o'tamiz!\n\n🤖 TalkMe yakuniy: QOYILMAQOM! Siz A1 va A2 darajalarini (190 ta darsni!) to'liq tugatdingiz! Keyingi darsdan B1 darajasini boshlaymiz! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 10 & 11 — B1 SONLAR, OTLAR, SIFAT, OLMOSH, RAVISH, PRESENT PERFECT (191–220)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_11_191",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 1,
            titleEnglish = "191-Dars: B1 Fractions (a half, two thirds)",
            titleUzbek = "B1 Boshlandi! Kasr sonlar (a half, a quarter, two thirds)",
            objectiveUzbek = "🤖 TalkMe: Salom! B1 (Intermediate) darajasiga xush kelibsiz! Bugun 191-darsda kasr sonlarni (a half, a quarter, two thirds) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nKasr sonlarda surat (tepasi) sanoq son (one, two), maxraj (pasti) esa tartib son (third, fifth) bilan aytiladi:\n• 1/3 — a third (one third)\n• 2/3 — two thirds (-s qo'shiladi!)\n• 1/2 — a half | 1/4 — a quarter | 3/4 — three quarters\n\n💬 MISOLLAR:\n• Half of the students are here. — Talabalarning yarmi shu yerda.\n• Wait a quarter of an hour. — Chorak soat (15 daqiqa) kuting.\n• Two thirds of the Earth is water. — Yerning uchdan ikki qismi suv.\n• I ate a third of the pizza. — Men pitsaning uchdan birini yedim.\n\n✍️ MASHQ:\n1. \"Uchdan ikki (2/3)\" inglizcha? → ✅ two thirds\n2. \"Chorak soat\" → ✅ a quarter of an hour\n3. \"To'rtdan uch (3/4)\" → ✅ three quarters",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Kasrlar: a half (1/2), a quarter (1/4), a third (1/3), two thirds (2/3).\n\n🤖 TalkMe yakuniy: Ajoyib! B1 darajani muvaffaqiyatli boshladingiz! Ertaga: o'nlik kasrlar va foizlar. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_192",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 2,
            titleEnglish = "192-Dars: Decimals & Percentages",
            titleUzbek = "O'nlik kasrlar va Foizlar (point, percent)",
            objectiveUzbek = "🤖 TalkMe: Bugun o'nlik kasrlar (point) va foizlarni (percent) inglizcha aytishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nO'nlik kasrlarda nuqta \"point\" deb o'qiladi va nuqtadan keyingi raqamlar bittalab aytiladi:\n• 0.5 — zero point five (point five)\n• 3.14 — three point one four\n• Foiz (%) — percent (ko'plikda -s olmaydi: 50 percent).\n\n💬 MISOLLAR:\n• Zero point five is equal to a half. — 0.5 yarmiga teng.\n• Pi is about three point one four. — Pi taxminan 3.14 ga teng.\n• Fifty percent of the class passed. — Sinfning 50 foizi o'tdi.\n• My battery is at twenty percent. — Batareyam 20 foizda.\n\n✍️ MASHQ:\n1. \"3.14\" qanday o'qiladi? → ✅ three point one four\n2. \"50 foiz\" → ✅ fifty percent",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Nuqta — point (3.14 = three point one four). Foiz — percent (50 percent).\n\n🤖 TalkMe yakuniy: Zo'r! O'nlik kasr va foizlarni bilib oldingiz! Ertaga: pul va narxlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_193",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 3,
            titleEnglish = "193-Dars: Money & Prices",
            titleUzbek = "Pul va Narxlarni aytish ($25.50)",
            objectiveUzbek = "🤖 TalkMe: Bugun do'konda va bozorda pul hamda narxlarni inglizcha aytishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPul birliklari: dollar ($), cent, pound, euro, soum (so'm).\n• $25.50 — twenty-five dollars and fifty cents (yoki oddiygina: twenty-five fifty).\n• Narx so'rash: How much does it cost? / How much is this?\n\n💬 MISOLLAR:\n• How much does this shirt cost? — Bu ko'ylak qancha turadi?\n• It costs twenty-five dollars and fifty cents. — U 25 dollaru 50 sent turadi.\n• That is fifteen ninety-nine ($15.99). — Bu 15.99 turadi.\n• I paid fifty thousand soums. — Men ellik ming so'm to'ladim.\n\n✍️ MASHQ:\n1. \"Bu ko'ylak qancha turadi?\" → ✅ How much does this shirt cost?\n2. \"$25.50\" → ✅ twenty-five dollars and fifty cents",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: $25.50 — twenty-five dollars fifty cents (yoki twenty-five fifty).\n\n🤖 TalkMe yakuniy: Barakalla! Endi narxlarni bemalol ayta olasiz! Ertaga: telefon raqamlari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_194",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 4,
            titleEnglish = "194-Dars: Phone Numbers (oh, double, triple)",
            titleUzbek = "Telefon raqamlari va Kodlar (double, triple)",
            objectiveUzbek = "🤖 TalkMe: Bugun telefon raqamlarini (oh, double, triple) inglizcha aytishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTelefon raqamlari bittalab o'qiladi. \"0\" raqami \"oh\" (ou) yoki \"zero\" deb o'qiladi. Ketma-ket bir xil raqam kelsa \"double\" (2 marta) yoki \"triple\" (3 marta) deyiladi: 55 — double five, 007 — double oh seven.\n\n💬 MISOLLAR:\n• My number is nine-oh-one, double five, two-three. — Raqamim: 901-55-23.\n• Call double nine, eight, triple seven. — 99-8-777 ga qo'ng'iroq qiling.\n• What is your phone number? — Telefon raqamingiz nechchi?\n\n✍️ MASHQ:\n1. \"55\" telefon raqamida qanday aytiladi? → ✅ double five\n2. \"777\" qanday aytiladi? → ✅ triple seven",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Telefon raqamlari bittalab o'qiladi: 0 = oh/zero, 55 = double five, 777 = triple seven.\n\n🤖 TalkMe yakuniy: Ajoyib! Telefon raqamlarini tez ayta olasiz! Ertaga: yillar va sanalar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_195",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 5,
            titleEnglish = "195-Dars: Years & Dates (1998, the 15th of May)",
            titleUzbek = "Yillar va Sanalarni o'qish (nineteen ninety-eight)",
            objectiveUzbek = "🤖 TalkMe: Bugun yillarni (1998, 2026) va sanalarni (the fifteenth of May) to'g'ri o'qishni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n2000-yilgacha bo'lgan yillar ikkiga bo'lib o'qiladi:\n• 1998 — nineteen ninety-eight | 1905 — nineteen oh five\n• 2000 — two thousand | 2026 — twenty twenty-six\n• Sanalar: 15 May — the fifteenth of May (May the fifteenth).\n\n💬 MISOLLAR:\n• I was born in nineteen ninety-eight (1998). — Men 1998-yilda tug'ilganman.\n• Today is the fifteenth of May. — Bugun 15-may.\n• We met in twenty twenty (2020). — Biz 2020-yilda tanishganmiz.\n\n✍️ MASHQ:\n1. \"1998-yil\" → ✅ nineteen ninety-eight\n2. \"15-may\" → ✅ the fifteenth of May",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Yillar ikkiga bo'linadi: 1998 = nineteen ninety-eight, 2026 = twenty twenty-six. Sana: the fifteenth of May.\n\n🤖 TalkMe yakuniy: Zo'r! Yil va sanalarni xatosiz o'qiysiz! Ertaga: B1 sonlar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_196",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 6,
            titleEnglish = "196-Dars: B1 Numbers Practice",
            titleUzbek = "B1 Sonlar — Umumiy Amaliyot",
            objectiveUzbek = "🤖 TalkMe: Bugun B1 sonlar bo'limini (kasr, foiz, pul, yillar) amaliy mashqlar bilan mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nB1 sonlar xulosasi:\n1) Kasr: a half, two thirds.\n2) O'nlik: three point five, 80 percent.\n3) Pul: twenty dollars fifty.\n4) Telefon: double five, oh.\n5) Yil va sana: nineteen ninety-nine, the tenth of June.\n\n💬 MISOLLAR:\n• Almost seventy percent of people use smartphones. — Odamlarning deyarli 70 foizi smartfon ishlatadi.\n• The ticket costs twelve dollars and fifty cents. — Chipta 12.50 dollar turadi.\n• My grandfather was born in nineteen fifty-four. — Bobom 1954-yilda tug'ilganlar.\n\n✍️ MASHQ:\n1. \"70 foiz\" → ✅ seventy percent\n2. \"1954-yil\" → ✅ nineteen fifty-four",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Kasr, o'nlik, foiz, pul, telefon va sanalarni takrorladik.\n\n🤖 TalkMe yakuniy: Barakalla! B1 sonlarni to'liq tugatdingiz! Ertaga: jamlama otlar (Collective Nouns). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_197",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 7,
            titleEnglish = "197-Dars: Collective Nouns (family, team)",
            titleUzbek = "Jamlama otlar (family, team, group, class)",
            objectiveUzbek = "🤖 TalkMe: Bugun jamlama otlarni (family, team, group) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nJamlama otlar (family — oila, team — jamoa, group — guruh, class — sinf, government — hukumat) bitta butun deb qaralganda birlik fe'l (is/has) oladi: My family is big.\n\n💬 MISOLLAR:\n• My family is very friendly. — Mening oilam juda ahil.\n• Our team is winning the match! — Jamoamiz o'yinda yutyapti!\n• The class has twenty students. — Sinfda 20 ta o'quvchi bor.\n• A group of tourists is waiting outside. — Bir guruh sayyohlar tashqarida kutyapti.\n\n✍️ MASHQ:\n1. \"Mening oilam juda ahil\" → ✅ My family is very friendly.\n2. \"Jamoamiz o'yinda yutyapti\" → ✅ Our team is winning the match!",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Jamlama otlar (family, team, group, class) bitta butun guruh sifatida birlikda (is/has) keladi.\n\n🤖 TalkMe yakuniy: Ajoyib! Jamlama otlarni bilib oldingiz! Ertaga: doim ko'plikda bo'ladigan jamlama otlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_198",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 8,
            titleEnglish = "198-Dars: Plural-Only Nouns (police, people)",
            titleUzbek = "Doim ko'plikdagi otlar (police, people)",
            objectiveUzbek = "🤖 TalkMe: Bugun muhim qoida: 'The police ARE' — doim ko'plikda keladigan otlarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nPolice (politsiya), people (odamlar) so'zlari shaklan '-s' olmasa ham, har doim KO'PLIK fe'l (are, have, work) bilan ishlatiladi! The police are coming (The police is EMAS!). Bitta politsiyachi: a police officer.\n\n💬 MISOLLAR:\n• The police are looking for the thief. — Politsiya o'g'rini qidiryapti.\n• Many people like coffee. — Ko'p odamlar qahvani yoqtiradi.\n• These people are my neighbours. — Bu odamlar mening qo'shnilarim.\n• He is a brave police officer. — U jasur politsiya xodimi.\n\n✍️ MASHQ:\n1. \"Politsiya kelyapti\" → ✅ The police are coming.\n2. Police bilan is yoki are keladi? → ✅ are",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Police va people har doim ko'plikda: The police are here!\n\n🤖 TalkMe yakuniy: Zo'r! Endi 'The police are' deb bexato aytasiz! Ertaga: mavhum otlar (Abstract Nouns). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_199",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 9,
            titleEnglish = "199-Dars: Abstract Uncountable Nouns",
            titleUzbek = "Mavhum otlar (advice, information, news, progress)",
            objectiveUzbek = "🤖 TalkMe: Bugun ingliz tilidagi eng ko'p xato qilinadigan mavhum otlarni (advice, information, news) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMavhum otlar (advice — maslahat, information — ma'lumot, news — yangilik, knowledge — bilim, progress — o'sish, work — ish) SANALMAYDI! Ularga 'a/an' yoki '-s' qo'shilmaydi va fe'l BIRLIKDA (is) bo'ladi: The news is good. Bitta maslahat: a piece of advice.\n\n💬 MISOLLAR:\n• Can you give me some advice? — Menga maslahat bera olasizmi?\n• This information is very useful. — Bu ma'lumot juda foydali.\n• The news is surprising! — Yangilik hayratlanarli!\n• Let me give you a piece of advice. — Sizga bitta maslahat beray.\n\n✍️ MASHQ:\n1. \"Bu ma'lumot juda foydali\" → ✅ This information is very useful.\n2. \"Yangilik yaxshi\" → ✅ The news is good.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: advice, information, news, knowledge, progress — sanalmaydi, birlikda keladi (is).\n\n🤖 TalkMe yakuniy: Barakalla! Endi 'some advice' va 'The news is' deb to'g'ri gapirasiz! Ertaga: 200-dars — yubiley dars! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_200",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 10,
            titleEnglish = "200-Dars: Milestone 200! Compound Nouns",
            titleUzbek = "Yubiley 200-Dars! Qo'shma otlar (bus stop, shoe shop)",
            objectiveUzbek = "🤖 TalkMe: TABRIKLAYMIZ! Bugun 200-DARS — 400 ta darslik katta kitobimizning naq YARMI! Bugun qo'shma otlarni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nQo'shma otlar (Compound Nouns) ikkita so'zdan yasaladi: toothbrush (tish cho'tkasi), bus stop (avtobus bekati), bedroom (yotoqxona), traffic jam (tirbandlik). Birinchi ot aniqlovchi vazifasida keladi va birlikda turadi (a shoe shop — poyabzal do'koni).\n\n💬 MISOLLAR:\n• I am waiting at the bus stop. — Men avtobus bekatida kutyapman.\n• We were stuck in a traffic jam. — Biz tirbandlikda qolib ketdik.\n• Where is the shoe shop? — Poyabzal do'koni qayerda?\n• She bought a new toothbrush. — U yangi tish cho'tkasi sotib oldi.\n\n✍️ MASHQ:\n1. \"Avtobus bekati\" → ✅ bus stop\n2. \"Poyabzal do'koni\" → ✅ a shoe shop",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 200-dars muborak! Siz 400 ta darsning naq YARMINI tugatdingiz! Qo'shma otlar: bus stop, shoe shop, traffic jam.\n\n🤖 TalkMe yakuniy: QOYIL! 200 ta dars ortda qoldi! Siz haqiqiy qahramonsiz! Ertaga: Partiya 11 — B1 sifatlar! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_201",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 11,
            titleEnglish = "201-Dars: Substantivized Adjectives (the rich, the poor)",
            titleUzbek = "Otlashgan sifatlar (the rich, the poor, the young)",
            objectiveUzbek = "🤖 TalkMe: Salom! Partiya 11 ni boshlaymiz! Bugun 201-darsda otlashgan sifatlarni (the rich, the poor, the young) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n'The + sifat' birikmasi butun bir insonlar guruhini bildiradi va KO'PLIK ma'nosida keladi: the rich (boylar), the poor (kambag'allar), the young (yoshlar), the old (keksalar), the blind (ko'zi ojizlar). Fe'l har doim ko'plikda (are/have) bo'ladi!\n\n💬 MISOLLAR:\n• The rich are not always happy. — Boylar har doim ham baxtli emas.\n• We should help the poor. — Biz kambag'allarga yordam berishimiz kerak.\n• The young learn technology quickly. — Yoshlar texnologiyani tez o'rganadilar.\n• This seat is for the elderly. — Bu o'rindiq keksalar uchun.\n\n✍️ MASHQ:\n1. \"Boylar har doim ham baxtli emas\" → ✅ The rich are not always happy.\n2. \"Biz kambag'allarga yordam berishimiz kerak\" → ✅ We should help the poor.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The + sifat = odamlar guruhi (ko'plikda: The rich are, the young like).\n\n🤖 TalkMe yakuniy: Ajoyib! 'The + sifat' qoidasini bilib oldingiz! Ertaga: -ed va -ing sifatlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_202",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 12,
            titleEnglish = "202-Dars: -ed vs -ing Adjectives",
            titleUzbek = "-ed va -ing Sifatlar (bored vs boring)",
            objectiveUzbek = "🤖 TalkMe: Bugun juda muhim farqni o'rganamiz: 'I am bored' (men zerikdim) va 'It is boring' (u zerikarli)! 🚀",
            explanationUzbek = "📖 QOIDA:\n• '-ed' bilan tugagan sifat insonning ICHKI HIS-TUYG'USINI bildiradi (I am bored — men zerikdim, I am interested — men qiziqaman).\n• '-ing' bilan tugagan sifat esa narsa yoki vaziyatning XUSUSIYATINI bildiradi (The film is boring — film zerikarli, The book is interesting — kitob qiziqarli).\n\n💬 MISOLLAR:\n• The lesson is interesting. I am interested in it. — Dars qiziqarli. Men unga qiziqaman.\n• The long trip was tiring, so we were tired. — Uzoq safar charchatadigan edi, shuning uchun biz charchadik.\n• This news is surprising! — Bu yangilik hayratlanarli!\n\n✍️ MASHQ:\n1. \"Kitob qiziqarli\" → ✅ The book is interesting.\n2. \"Men unga qiziqaman\" → ✅ I am interested in it.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -ed = inson his-tuyg'usi (tired, bored, interested). -ing = narsa/vaziyat (tiring, boring, interesting).\n\n🤖 TalkMe yakuniy: Zo'r! Endi -ed va -ing sifatlarni hech qachon adashtirmaysiz! Ertaga: sifatlar tartibi (2 ta sifat). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_203",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 13,
            titleEnglish = "203-Dars: Order of Two Adjectives (Opinion + Fact)",
            titleUzbek = "Ikkita sifat tartibi (Fikr + Fakt)",
            objectiveUzbek = "🤖 TalkMe: Bugun otdan oldin ikkita sifat kelganda qaysi biri birinchi turishini o'rganamiz: Opinion → Fact! 🚀",
            explanationUzbek = "📖 QOIDA:\nBitta otdan oldin ikkita sifat kelsa, avval FIKR (Opinion: nice, beautiful, delicious), keyin FAKT (hajm, yosh, rang, material: big, new, red, wooden) keladi: a beautiful red dress (red beautiful EMAS!).\n\n💬 MISOLLAR:\n• She bought a beautiful red dress. — U chiroyli qizil ko'ylak sotib oldi.\n• It is a delicious hot soup. — Bu mazali issiq sho'rva.\n• He lives in a nice big house. — U yaxshi katta uyda yashaydi.\n• That is an interesting old book. — Anavi qiziqarli eski kitob.\n\n✍️ MASHQ:\n1. \"Chiroyli qizil ko'ylak\" → ✅ a beautiful red dress\n2. \"Yaxshi katta uy\" → ✅ a nice big house",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Avval Fikr (Opinion), keyin Fakt (Fact): a beautiful red dress.\n\n🤖 TalkMe yakuniy: Barakalla! Ikki sifat tartibini o'zlashtirdingiz! Ertaga: 3 va 4 ta sifat tartibi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_204",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 14,
            titleEnglish = "204-Dars: Full Adjective Order (OSASCOMP)",
            titleUzbek = "Ko'p sifatlar tartibi (Fikr → Hajm → Yosh → Rang → Davlat → Material)",
            objectiveUzbek = "🤖 TalkMe: Bugun sifatlarning to'liq oltin tartibini (Fikr → Hajm → Yosh → Rang → Davlat → Material) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSifatlar tartibi:\n1) Opinion (fikr: lovely) → 2) Size (hajm: small) → 3) Age (yosh: old) → 4) Color (rang: brown) → 5) Origin (kelib chiqish: Italian) → 6) Material (material: wooden) + Ot.\nMasalan: a lovely small old brown wooden table.\n\n💬 MISOLLAR:\n• She has a beautiful big new black German car. — Uning chiroyli, katta, yangi, qora nemis mashinasi bor.\n• I bought a nice warm woollen sweater. — Men chiroyli issiq jun sviter sotib oldim.\n• Look at that small round wooden table. — Anavi kichkina dumaloq yog'och stolga qarang.\n\n✍️ MASHQ:\n1. \"Kichkina eski yog'och stol\" → ✅ a small old wooden table\n2. \"Chiroyli issiq jun sviter\" → ✅ a nice warm woollen sweater",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Tartib: Fikr → Hajm → Yosh → Rang → Davlat → Material + Ot.\n\n🤖 TalkMe yakuniy: Ajoyib! Endi 3-4 ta sifatni ham bexato joylashtirasiz! Ertaga: so va such. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_205",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 15,
            titleEnglish = "205-Dars: So vs Such",
            titleUzbek = "So va Such (so big vs such a big house)",
            objectiveUzbek = "🤖 TalkMe: Bugun 'so' va 'such' farqini o'rganamiz: 'so beautiful' va 'such a beautiful day'! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'So + sifat/ravish' (otsiz): The weather is so nice!\n• 'Such + (a/an) + sifat + ot': It is such a nice day!\n• Natija bildirganda 'that' qo'shiladi: He was so tired that he fell asleep.\n\n💬 MISOLLAR:\n• This coffee is so hot! — Bu qahva shunaqa issiq!\n• It is such a good movie! — Bu shunaqa yaxshi kino!\n• The box was so heavy that I could not lift it. — Quti shunchalik og'ir ediki, uni ko'tara olmadim.\n• She is such a kind person. — U shunaqa mehribon inson.\n\n✍️ MASHQ:\n1. \"Bu shunaqa yaxshi kino!\" → ✅ It is such a good movie!\n2. \"Bu qahva shunaqa issiq!\" → ✅ This coffee is so hot!",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: So + sifat (so nice). Such + a/an + sifat + ot (such a nice day).\n\n🤖 TalkMe yakuniy: Zo'r! So va such farqini aniq bilib oldingiz! Ertaga: too va enough. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_206",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 16,
            titleEnglish = "206-Dars: Too vs Enough",
            titleUzbek = "Too va Enough (too cold vs warm enough)",
            objectiveUzbek = "🤖 TalkMe: Bugun 'too' (haddan ortiq) va 'enough' (yetarli) so'zlarining joylashuvini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'Too + sifat' — haddan tashqari (salbiy ma'noda): too hot (juda issiq).\n• 'Sifat + enough' — yetarli darajada (sifatdan KEYIN keladi!): warm enough (yetarlicha iliq), old enough (yetarlicha katta).\n• Ot bilan esa enough otdan oldin keladi: enough money.\n\n💬 MISOLLAR:\n• This tea is too hot to drink. — Bu choy ichish uchun haddan tashqari issiq.\n• Is the room warm enough for you? — Xona siz uchun yetarlicha iliqmi?\n• He is not old enough to drive. — U mashina haydash uchun yetarlicha katta emas.\n• I have enough time. — Menda yetarli vaqt bor.\n\n✍️ MASHQ:\n1. \"Xona yetarlicha iliqmi?\" → ✅ Is the room warm enough?\n2. \"Menda yetarli vaqt bor\" → ✅ I have enough time.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Too + sifat (too cold). Sifat + enough (warm enough). Enough + ot (enough money).\n\n🤖 TalkMe yakuniy: Barakalla! B1 sifatlar bo'limini tugatdik! Ertaga: nisbiy olmoshlar — who. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_207",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 17,
            titleEnglish = "207-Dars: Relative Pronoun Who",
            titleUzbek = "Nisbiy olmoshlar: Who (odamlar uchun)",
            objectiveUzbek = "🤖 TalkMe: Bugun nisbiy olmoshlarni boshlaymiz! Odamlar haqida qo'shimcha ma'lumot berish uchun 'who' ishlatamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n'Who' gap o'rtasida bog'lovchi-olmosh bo'lib kelganda odamlarga nisbatan ishlatiladi va '...bo'lgan / ...-gan' deb tarjima qilinadi: The man who lives next door is a doctor (Qo'shni uyda yashaydigan kishi — shifokor).\n\n💬 MISOLLAR:\n• I know a girl who speaks four languages. — Men to'rtta tilda gapiradigan qizni taniyman.\n• The man who called you is my uncle. — Sizga qo'ng'iroq qilgan kishi mening amakim.\n• People who exercise regularly are healthier. — Muntazam mashq qiladigan odamlar sog'lomroq bo'ladi.\n\n✍️ MASHQ:\n1. \"Men to'rtta tilda gapiradigan qizni taniyman\" → ✅ I know a girl who speaks four languages.\n2. \"Sizga qo'ng'iroq qilgan kishi mening amakim\" → ✅ The man who called you is my uncle.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Who — odamlar uchun nisbiy olmosh (a girl who speaks...).\n\n🤖 TalkMe yakuniy: Ajoyib! 'Who' bilan aniqlovchi ergash gap tuzishni o'rgandingiz! Ertaga: which va that. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_208",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 18,
            titleEnglish = "208-Dars: Relative Pronouns Which & That",
            titleUzbek = "Nisbiy olmoshlar: Which va That",
            objectiveUzbek = "🤖 TalkMe: Bugun narsalar uchun 'which' va universal 'that' nisbiy olmoshlarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'Which' narsa va hayvonlar uchun ishlatiladi: The book which is on the table is mine.\n• 'That' esa universal bo'lib, ham odamlar (who o'rniga), ham narsalar (which o'rniga) uchun ishlatilishi mumkin!\n\n💬 MISOLLAR:\n• I lost the watch which my father gave me. — Dadam bergan soatni yo'qotib qo'ydim.\n• This is the bus that goes to the airport. — Bu aeroportga boradigan avtobus.\n• The movie that we watched yesterday was great. — Kecha biz ko'rgan kino ajoyib edi.\n\n✍️ MASHQ:\n1. \"Dadam bergan soatni yo'qotib qo'ydim\" → ✅ I lost the watch which my father gave me.\n2. \"Bu aeroportga boradigan avtobus\" → ✅ This is the bus that goes to the airport.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Which — narsa/hayvonlar uchun. That — odam va narsalar uchun.\n\n🤖 TalkMe yakuniy: Zo'r! Who, which va that farqini bilib oldingiz! Ertaga: whose va where. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_209",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 19,
            titleEnglish = "209-Dars: Relative Whose & Where",
            titleUzbek = "Nisbiy olmoshlar: Whose va Where",
            objectiveUzbek = "🤖 TalkMe: Bugun 'whose' (uning ...-si) va 'where' (joy uchun) nisbiy so'zlarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'Whose' — egalik bildiradi (...-ning): a woman whose son is a pilot (o'g'li uchuvchi bo'lgan ayol).\n• 'Where' — joylar uchun (...u yerda): the hotel where we stayed (biz to'xtagan mehmonxona).\n\n💬 MISOLLAR:\n• I met a boy whose father is a famous writer. — Otasi mashhur yozuvchi bo'lgan bolani uchratdim.\n• This is the school where I studied. — Bu men o'qigan maktab.\n• Do you know the cafe where they serve Uzbek plov? — O'zbek palovi beriladigan kafeni bilasizmi?\n\n✍️ MASHQ:\n1. \"Bu men o'qigan maktab\" → ✅ This is the school where I studied.\n2. \"Otasi mashhur yozuvchi bo'lgan bola\" → ✅ a boy whose father is a famous writer",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Whose — egalik (whose father). Where — joy (the school where I studied).\n\n🤖 TalkMe yakuniy: Barakalla! Nisbiy olmoshlarni to'liq o'zlashtirdingiz! Ertaga: what vs which so'roq olmoshlari. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_210",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 20,
            titleEnglish = "210-Dars: Interrogative What vs Which",
            titleUzbek = "So'roq olmoshlari: What vs Which",
            objectiveUzbek = "🤖 TalkMe: Bugun savollarda 'What' va 'Which' qachon ishlatilishini aniq ajratib olamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'What' — cheklanmagan, umumiy tanlovda (What music do you like? — Qanday musiqa yoqtirasiz?).\n• 'Which' — aniq, cheklangan 2-3 ta narsa orasidan tanlaganda (Which color do you prefer, red or blue? — Qaysi rangni afzal ko'rasiz, qizilmi yoki ko'k?).\n\n💬 MISOLLAR:\n• What books do you usually read? — Odatda qanday kitoblar o'qiysiz?\n• Which book is yours, this one or that one? — Qaysi kitob sizniki, bunisimi yoki unisi?\n• Which way should we go, left or right? — Qaysi yo'ldan boraylik, chapgami yoki o'ngga?\n\n✍️ MASHQ:\n1. \"Qaysi rangni afzal ko'rasiz, qizilmi yoki ko'k?\" → ✅ Which color do you prefer, red or blue?\n2. Cheklangan tanlovda qaysi so'z keladi? → ✅ Which",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: What — umumiy tanlov. Which — cheklangan tanlov (red or blue).\n\n🤖 TalkMe yakuniy: Ajoyib! What va Which farqini bilasiz! Ertaga: ega so'roq gaplar (Subject Questions). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_211",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 21,
            titleEnglish = "211-Dars: Subject Questions (Who called you?)",
            titleUzbek = "Ega savollar (Who called you? vs Who did you call?)",
            objectiveUzbek = "🤖 TalkMe: Bugun B1 darajadagi eng muhim savol qoidasi: 'Who called you?' va 'Who did you call?' farqini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Agar 'Who' yoki 'What' gapning EGASI bo'lsa, do/does/did ISHLATILMAYDI: Who broke the window? (Oynani kim sindirdi?).\n• Agar to'ldiruvchi bo'lsa, yordamchi fe'l kerak: Who did you call? (Siz kimga qo'ng'iroq qildingiz?).\n\n💬 MISOLLAR:\n• Who called you? — Sizga kim qo'ng'iroq qildi? (Ega savol)\n• Who did you call? — Siz kimga qo'ng'iroq qildingiz? (To'ldiruvchi savol)\n• What happened yesterday? — Kecha nima bo'ldi?\n• Who knows the answer? — Javobni kim biladi?\n\n✍️ MASHQ:\n1. \"Sizga kim qo'ng'iroq qildi?\" → ✅ Who called you?\n2. \"Kecha nima bo'ldi?\" → ✅ What happened yesterday?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ega savolda do/did yo'q: Who called you? What happened? To'ldiruvchi savolda did bor: Who did you call?\n\n🤖 TalkMe yakuniy: Zo'r! Ega savollarni xatosiz tuza olasiz! Ertaga: ourselves vs each other. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_212",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 22,
            titleEnglish = "212-Dars: Reflexive vs Reciprocal (Each Other)",
            titleUzbek = "Ourselves vs Each Other (O'zimiz va Bir-birimiz)",
            objectiveUzbek = "🤖 TalkMe: Bugun 'themselves' (o'zlariga) va 'each other' (bir-biriga) farqini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'Ourselves / themselves' — harakat o'ziga qaytadi (We looked at ourselves in the mirror — Biz ko'zguda o'zimizga qaradik).\n• 'Each other / one another' — bir-biriga (We looked at each other — Biz bir-birimizga qaradik).\n\n💬 MISOLLAR:\n• Ali and Vali help each other. — Ali va Vali bir-biriga yordam beradi.\n• They bought themselves some coffee. — Ular o'zlariga qahva sotib olishdi.\n• We know each other very well. — Biz bir-birimizni juda yaxshi taniymiz.\n\n✍️ MASHQ:\n1. \"Ali va Vali bir-biriga yordam beradi\" → ✅ Ali and Vali help each other.\n2. \"Biz bir-birimizni juda yaxshi taniymiz\" → ✅ We know each other very well.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Themselves = o'zlariga. Each other = bir-biriga.\n\n🤖 TalkMe yakuniy: Barakalla! B1 olmoshlar bo'limini tugatdik! Ertaga: ravish darajalari (faster, more quickly). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_213",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 23,
            titleEnglish = "213-Dars: Comparative Adverbs (faster, more slowly)",
            titleUzbek = "Ravishlarning qiyosiy darajasi (faster, more slowly)",
            objectiveUzbek = "🤖 TalkMe: Bugun ravishlarning qiyosiy darajasini (faster, more slowly, better) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Qisqa ravishlarga '-er' qo'shiladi: fast → faster, hard → harder, early → earlier.\n• '-ly' bilan tugagan ravishlar oldiga 'more' qo'yiladi: quickly → more quickly, slowly → more slowly.\n• Noto'g'ri ravishlar: well → better, badly → worse.\n\n💬 MISOLLAR:\n• Can you speak more slowly, please? — Iltimos, sekinroq gapira olasizmi?\n• He runs faster than me. — U mendan tezroq yuguradi.\n• She speaks English better now. — U hozir inglizcha yaxshiroq gapiradi.\n• Drive more carefully at night! — Kechasi ehtiyotkorroq haydang!\n\n✍️ MASHQ:\n1. \"Iltimos, sekinroq gapira olasizmi?\" → ✅ Can you speak more slowly, please?\n2. \"U hozir inglizcha yaxshiroq gapiradi\" → ✅ She speaks English better now.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Qisqa ravish + -er (faster, harder). -ly ravish → more + ravish (more slowly). well → better.\n\n🤖 TalkMe yakuniy: Ajoyib! Ravishlarni qiyoslashni bilib oldingiz! Ertaga: ravishlarning orttirma darajasi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_214",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 24,
            titleEnglish = "214-Dars: Superlative Adverbs (fastest, most fluently)",
            titleUzbek = "Ravishlarning orttirma darajasi (fastest, most fluently)",
            objectiveUzbek = "🤖 TalkMe: Bugun ravishlarning orttirma darajasini (the fastest, the most fluently, the best) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Qisqa ravishlar: fastest, hardest, earliest.\n• '-ly' ravishlar: most quickly, most fluently.\n• Noto'g'ri: well → best, badly → worst, far → furthest.\n\n💬 MISOLLAR:\n• Who runs the fastest in your team? — Jamoangizda kim eng tez yuguradi?\n• She speaks English the most fluently in our class. — Sinfimizda u inglizcha eng ravon gapiradi.\n• He worked the hardest of all. — U hammadan eng ko'p mehnat qildi.\n\n✍️ MASHQ:\n1. \"Jamoangizda kim eng tez yuguradi?\" → ✅ Who runs the fastest in your team?\n2. \"Well\" ravishining orttirma shakli nima? → ✅ best",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Orttirma ravishlar: fastest, hardest, most fluently, best.\n\n🤖 TalkMe yakuniy: Zo'r! Ravish darajalarini o'zlashtirdingiz! Ertaga: The... the... qo'sh qiyoslash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_215",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 25,
            titleEnglish = "215-Dars: Double Comparatives (The sooner, the better)",
            titleUzbek = "Qo'sh qiyoslash: The ... the ... (The sooner, the better)",
            objectiveUzbek = "🤖 TalkMe: Bugun ingliz tilidagi juda chiroyli ifodani o'rganamiz: 'The sooner, the better!' (Qanchalik tez bo'lsa, shunchalik yaxshi!) 🚀",
            explanationUzbek = "📖 QOIDA:\n'Qanchalik ... bo'lsa, shunchalik ...' ma'nosini berish uchun ikki qismda ham 'The + qiyosiy daraja' ishlatiladi:\n• The sooner, the better. — Qanchalik tez bo'lsa, shunchalik yaxshi.\n• The more you read, the more you know. — Qancha ko'p o'qisangiz, shuncha ko'p bilasiz.\n\n💬 MISOLLAR:\n• The sooner, the better! — Qanchalik tez bo'lsa, shunchalik yaxshi!\n• The more you practice, the better you speak. — Qancha ko'p mashq qilsangiz, shuncha yaxshi gapirasiz.\n• The higher we climbed, the colder it got. — Qancha baland ko'tarilganimiz sari, shuncha sovib bordi.\n\n✍️ MASHQ:\n1. \"Qanchalik tez bo'lsa, shunchalik yaxshi!\" → ✅ The sooner, the better!\n2. \"Qancha ko'p mashq qilsangiz, shuncha yaxshi gapirasiz\" → ✅ The more you practice, the better you speak.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The + qiyosiy..., the + qiyosiy... (The more you practice, the better you speak).\n\n🤖 TalkMe yakuniy: Barakalla! 'The... the...' iborasini nutqingizda ishlata olasiz! Ertaga: still, already, yet. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_216",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 26,
            titleEnglish = "216-Dars: Adverbs Still, Already, Yet",
            titleUzbek = "Still, Already, Yet ravishlari",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Perfect oldidan juda kerakli 3 ta ravishni o'rganamiz: still, already, yet! 🚀",
            explanationUzbek = "📖 QOIDA:\n• 'Still' — hali ham (davom etyapti, asosiy fe'ldan oldin: He is still sleeping).\n• 'Already' — allaqachon (kutilganidan erta: I have already eaten).\n• 'Yet' — hali (inkor va so'roq gap oxirida: Have you finished yet? No, not yet).\n\n💬 MISOLLAR:\n• It is still raining outside. — Tashqarida hali ham yomg'ir yog'yapti.\n• I already know the answer. — Men javobni allaqachon bilaman.\n• Are you ready yet? — Tayyor bo'ldingizmi?\n• He hasn't arrived yet. — U hali yetib kelmadi.\n\n✍️ MASHQ:\n1. \"Tashqarida hali ham yomg'ir yog'yapti\" → ✅ It is still raining outside.\n2. \"U hali yetib kelmadi\" → ✅ He hasn't arrived yet.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Still — hali ham (o'rtada). Already — allaqachon (darakda). Yet — hali (inkor/so'roq oxirida).\n\n🤖 TalkMe yakuniy: Ajoyib! Still, already va yet so'zlarini bilib oldingiz! Ertaga: B1 ning eng muhim zamoni — Present Perfect! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_217",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 27,
            titleEnglish = "217-Dars: Present Perfect — I have done",
            titleUzbek = "Present Perfect nima? (I have done)",
            objectiveUzbek = "🤖 TalkMe: Salom! Bugun B1 darajaning yuragi — Present Perfect (have + V3) zamonini boshlaymiz! Qo'rqmang, u juda mantiqiy! 🚀",
            explanationUzbek = "📖 QOIDA:\nPresent Perfect (Hozirgi tugallangan zamon) o'tmishda sodir bo'lgan, lekin NATIJASI HOZIR KO'RINIB TURGAN ish-harakatni bildiradi.\nFormula: I/you/we/they + have + V3 (-ed yoki 3-shakl).\nMasalan: I have washed my hands (Qo'limni yuvdim — hozir qo'lim toza!).\n\n💬 MISOLLAR:\n• I have finished my homework. — Men uy vazifamni tugatdim (hozir tayyor).\n• We have bought a new car. — Biz yangi mashina sotib oldik.\n• They have cleaned the room. — Ular xonani tozalashdi (xona hozir toza).\n• I have lost my keys! — Kalitlarimni yo'qotib qo'ydim (hozir uyga kira olmayapman).\n\n✍️ MASHQ:\n1. \"Men uy vazifamni tugatdim\" → ✅ I have finished my homework.\n2. \"Kalitlarimni yo'qotib qo'ydim\" → ✅ I have lost my keys!",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Present Perfect: have + V3. O'tmishda bo'lgan ishning hozirgi natijasi!\n\n🤖 TalkMe yakuniy: Zo'r! Present Perfect ma'nosini tushunib oldingiz! Ertaga: He/She/It has done. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_218",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 28,
            titleEnglish = "218-Dars: Present Perfect — He/She/It has done",
            titleUzbek = "Present Perfect: He / She / It has + V3",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Perfect zamonida He, She, It bilan 'has + V3' va noto'g'ri fe'llarning 3-shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nHe, She, It bilan 'has + V3' ishlatiladi. Qisqartmasi: He's / She's / It's + V3.\nNoto'g'ri fe'llarning 3-shakli (Past Participle): go → gone, see → seen, eat → eaten, write → written, do → done, take → taken.\n\n💬 MISOLLAR:\n• She has written three emails today. — U bugun uchta xat yozdi.\n• He has gone to the supermarket. — U supermarketga ketdi (hozir u yerda).\n• The rain has stopped! — Yomg'ir to'xtadi!\n• My brother has broken his glasses. — Akam ko'zoynagini sindirib qo'ydi.\n\n✍️ MASHQ:\n1. \"U bugun uchta xat yozdi\" → ✅ She has written three emails today.\n2. \"Yomg'ir to'xtadi!\" → ✅ The rain has stopped!",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: He/She/It + has + V3 (gone, seen, eaten, written, done).\n\n🤖 TalkMe yakuniy: Barakalla! 'Has + V3' ni o'zlashtirdingiz! Ertaga: Present Perfect inkor shakli (haven't / hasn't). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_219",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 29,
            titleEnglish = "219-Dars: Present Perfect Negative (haven't / hasn't)",
            titleUzbek = "Present Perfect Inkor (haven't / hasn't + V3)",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Perfect inkor shaklini (haven't / hasn't done, have never done) o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nInkor shakli: have not (haven't) + V3 va has not (hasn't) + V3. Fe'l baribir 3-shaklda (V3) qoladi! Ko'pincha gap oxirida 'yet' (hali) yoki V3 oldidan 'never' (hech qachon) keladi: I haven't seen this film yet. I have never been to London.\n\n💬 MISOLLAR:\n• I haven't finished my breakfast yet. — Men hali nonushtamni tugatmadim.\n• She hasn't called me today. — U bugun menga qo'ng'iroq qilmadi.\n• We have never seen snow. — Biz hech qachon qor ko'rmaganmiz.\n• He hasn't read that book yet. — U hali u kitobni o'qimagan.\n\n✍️ MASHQ:\n1. \"Men hali nonushtamni tugatmadim\" → ✅ I haven't finished my breakfast yet.\n2. \"Biz hech qachon qor ko'rmaganmiz\" → ✅ We have never seen snow.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Inkor: haven't / hasn't + V3 (+ yet) yoki have/has never + V3.\n\n🤖 TalkMe yakuniy: Ajoyib! Present Perfect inkorini bilib oldingiz! Ertaga: 220-dars — Have you ever...? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_11_220",
            topicId = "topic_11_b1_perfect_passive",
            orderIndex = 30,
            titleEnglish = "220-Dars: Present Perfect Questions (Have you ever...?)",
            titleUzbek = "Present Perfect So'roq: Have you ever ...?",
            objectiveUzbek = "🤖 TalkMe: Bugun 220-dars! Present Perfect so'roq shakli va 'Have you ever...?' savolini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSo'roq shakli: Have (I/you/we/they) + V3? va Has (he/she/it) + V3?\nHayotiy tajribani so'rashda 'ever' (umringizda biror marta) ishlatiladi: Have you ever been to Samarkand? Javob: Yes, I have. / No, I haven't.\n\n💬 MISOLLAR:\n• Have you ever been to Samarkand? — Siz biror marta Samarqandda bo'lganmisiz?\n• Yes, I have. / No, I haven't. — Ha, bo'lganman. / Yo'q, bo'lmaganman.\n• Has she finished the report? — U hisobotni tugatdimi?\n• What have you done today? — Bugun nima qildingiz?\n\n✍️ MASHQ:\n1. \"Siz biror marta Samarqandda bo'lganmisiz?\" → ✅ Have you ever been to Samarkand?\n2. \"U hisobotni tugatdimi?\" → ✅ Has she finished the report?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: So'roq: Have/Has + ega + V3? Hayotiy tajriba: Have you ever + V3? Partiya 11 (201–220-darslar) to'liq yakunlandi!\n\n🤖 TalkMe yakuniy: TABRIKLAYMIZ! Siz Partiya 11 ni (220 ta darsni!) muvaffaqiyatli tugatdingiz! Keyingi darslarda Present Perfect davomini o'rganamiz! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity("voc_p10_01", "topic_10_a2_grammar", "sec_10_182", "Letter", "xat, maktub", "She sent him a letter.", "U unga xat yubordi.", isStudied = false),
        VocabularyEntity("voc_p10_02", "topic_10_a2_grammar", "sec_10_183", "Wait for", "kutmoq", "Please wait for me.", "Iltimos, meni kuting.", isStudied = false),
        VocabularyEntity("voc_p10_03", "topic_10_a2_grammar", "sec_10_184", "Coat", "palto", "She wears a warm coat.", "U issiq palto kiyadi.", isStudied = false),
        VocabularyEntity("voc_p10_04", "topic_10_a2_grammar", "sec_10_188", "Stadium", "stadion", "He played football at the stadium.", "U stadionda futbol o'ynadi.", isStudied = false),
        VocabularyEntity("voc_p10_05", "topic_11_b1_perfect_passive", "sec_11_191", "One third", "uchdan bir (1/3)", "One third of the students are here.", "Talabalarning uchdan biri shu yerda.", isStudied = false),
        VocabularyEntity("voc_p10_06", "topic_11_b1_perfect_passive", "sec_11_192", "Percent", "foiz", "Fifty percent of the class passed.", "Sinfning ellik foizi o'tdi.", isStudied = false),
        VocabularyEntity("voc_p10_07", "topic_11_b1_perfect_passive", "sec_11_197", "Team", "jamoa", "Our team is winning.", "Jamoamiz yutyapti.", isStudied = false),
        VocabularyEntity("voc_p10_08", "topic_11_b1_perfect_passive", "sec_11_198", "Thief", "o'g'ri", "The police are looking for the thief.", "Politsiya o'g'rini qidiryapti.", isStudied = false),
        VocabularyEntity("voc_p10_09", "topic_11_b1_perfect_passive", "sec_11_199", "Piece of advice", "bitta maslahat", "Let me give you a piece of advice.", "Sizga bitta maslahat beray.", isStudied = false),
        VocabularyEntity("voc_p10_10", "topic_11_b1_perfect_passive", "sec_11_200", "Traffic jam", "tirbandlik", "We were stuck in a traffic jam.", "Biz tirbandlikda qolib ketdik.", isStudied = false),
        VocabularyEntity("voc_p11_01", "topic_11_b1_perfect_passive", "sec_11_201", "Elderly", "keksalar, yoshi ulug'lar", "This seat is for the elderly.", "Bu o'rindiq keksalar uchun.", isStudied = false),
        VocabularyEntity("voc_p11_02", "topic_11_b1_perfect_passive", "sec_11_202", "Surprising", "hayratlanarli", "This news is surprising.", "Bu yangilik hayratlanarli.", isStudied = false),
        VocabularyEntity("voc_p11_03", "topic_11_b1_perfect_passive", "sec_11_204", "Woollen", "jundan to'qilgan", "I bought a warm woollen sweater.", "Men issiq jun sviter sotib oldim.", isStudied = false),
        VocabularyEntity("voc_p11_04", "topic_11_b1_perfect_passive", "sec_11_206", "Enough", "yetarli", "Is the room warm enough?", "Xona yetarlicha iliqmi?", isStudied = false),
        VocabularyEntity("voc_p11_05", "topic_11_b1_perfect_passive", "sec_11_214", "Fluently", "ravon, erkin", "She speaks English the most fluently.", "U inglizcha eng ravon gapiradi.", isStudied = false),
        VocabularyEntity("voc_p11_06", "topic_11_b1_perfect_passive", "sec_11_216", "Already", "allaqachon", "I have already finished.", "Men allaqachon tugatdim.", isStudied = false),
        VocabularyEntity("voc_p11_07", "topic_11_b1_perfect_passive", "sec_11_218", "Written", "yozilgan (write 3-shakli)", "She has written three emails.", "U uchta xat yozdi.", isStudied = false),
        VocabularyEntity("voc_p11_08", "topic_11_b1_perfect_passive", "sec_11_220", "Report", "hisobot", "Has she finished the report?", "U hisobotni tugatdimi?", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "gr_10_188",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_188",
            titleUzbek = "Ingliz gapida so'z tartibi: S + V + O + M + P + T",
            explanationUzbek = "Ega + Kesim + To'ldiruvchi + Ravish holi (Manner) + O'rin holi (Place) + Payt holi (Time).",
            exampleEnglish = "She read the book quietly in the library yesterday.",
            exampleUzbek = "U kecha kutubxonada kitobni tinchgina o'qidi.",
            associatedVocabularyIdsCsv = "voc_p10_01,voc_p10_04",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_11_199",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_199",
            titleUzbek = "Sanalmaydigan mavhum otlar (advice, information, news)",
            explanationUzbek = "advice, information, news, knowledge, progress sanalmaydi, -s olmaydi va birlikdagi fe'l (is) bilan ishlatiladi.",
            exampleEnglish = "This information is useful. The news is good.",
            exampleUzbek = "Bu ma'lumot foydali. Yangilik yaxshi.",
            associatedVocabularyIdsCsv = "voc_p10_09",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gr_11_217",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_217",
            titleUzbek = "Present Perfect (have / has + V3)",
            explanationUzbek = "O'tgan zamonda sodir bo'lib, natijasi hozir ko'rinib turgan harakat: have/has + Past Participle (V3).",
            exampleEnglish = "I have finished my homework. She has written three emails.",
            exampleUzbek = "Men uy vazifamni tugatdim. U uchta xat yozdi.",
            associatedVocabularyIdsCsv = "voc_p11_06,voc_p11_07,voc_p11_08",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_10_182",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_182",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "Vositali to'ldiruvchi to'g'ri joylashgan gapni tanlang:",
            subPromptText = "182-Dars: Fe'l + kimga + nimani",
            audioTextToSpeak = "Give me the book.",
            optionsPipeSeparated = "Give me the book.|Give to me the book.|Give the book me.|Me give the book.",
            correctAnswer = "Give me the book.",
            explanationUzbek = "Fe'l + kimga + nimani: Give me the book (yoki Give the book to me).",
            vocabularyIdsCsv = "voc_p10_01",
            grammarRuleIdsCsv = "gr_10_188"
        ),
        ExerciseEntity(
            id = "ex_10_188",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_188",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "O'rin va payt holi to'g'ri tartibda joylashgan gapni tanlang:",
            subPromptText = "188-Dars: Place → Time",
            audioTextToSpeak = "I go to school every day.",
            optionsPipeSeparated = "I go to school every day.|I go every day to school.|I every day to school go.|To school I every day go.",
            correctAnswer = "I go to school every day.",
            explanationUzbek = "Gap oxirida avval o'rin (to school), keyin payt (every day) keladi.",
            vocabularyIdsCsv = "voc_p10_04",
            grammarRuleIdsCsv = "gr_10_188"
        ),
        ExerciseEntity(
            id = "ex_11_191",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_191",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"2/3\" (uchdan ikki) kasri ingliz tilida qanday aytiladi?",
            subPromptText = "191-Dars: Kasr sonlar",
            audioTextToSpeak = "two thirds",
            optionsPipeSeparated = "two thirds|two third|second thirds|two three",
            correctAnswer = "two thirds",
            explanationUzbek = "Surat 1 dan katta bo'lsa maxrajga -s qo'shiladi: two thirds.",
            vocabularyIdsCsv = "voc_p10_05",
            grammarRuleIdsCsv = "gr_11_199"
        ),
        ExerciseEntity(
            id = "ex_11_199",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_199",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "Qaysi gap grammatik jihatdan TO'G'RI?",
            subPromptText = "199-Dars: Mavhum otlar (news, advice, information)",
            audioTextToSpeak = "The news is very good.",
            optionsPipeSeparated = "The news is very good.|He gave me an advice.|These informations are useful.|The news are good.",
            correctAnswer = "The news is very good.",
            explanationUzbek = "News, advice, information sanalmaydi va birlik fe'l (is) oladi.",
            vocabularyIdsCsv = "voc_p10_09",
            grammarRuleIdsCsv = "gr_11_199"
        ),
        ExerciseEntity(
            id = "ex_11_206",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_206",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"Xona yetarlicha iliqmi?\" gapining to'g'ri tarjimasini tanlang:",
            subPromptText = "206-Dars: Sifat + enough",
            audioTextToSpeak = "Is the room warm enough?",
            optionsPipeSeparated = "Is the room warm enough?|Is the room enough warm?|Is enough warm the room?|Does the room warm enough?",
            correctAnswer = "Is the room warm enough?",
            explanationUzbek = "Enough sifatdan KEYIN keladi: warm enough.",
            vocabularyIdsCsv = "voc_p11_04",
            grammarRuleIdsCsv = "gr_11_199"
        ),
        ExerciseEntity(
            id = "ex_11_218",
            topicId = "topic_11_b1_perfect_passive",
            sectionId = "sec_11_218",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 2,
            promptText = "\"U bugun uchta xat yozdi\" (Present Perfect) gapini toping:",
            subPromptText = "218-Dars: has + V3",
            audioTextToSpeak = "She has written three emails today.",
            optionsPipeSeparated = "She has written three emails today.|She has wrote three emails today.|She have written three emails today.|She written three emails today.",
            correctAnswer = "She has written three emails today.",
            explanationUzbek = "She + has + written (write fe'lining 3-shakli).",
            vocabularyIdsCsv = "voc_p11_07",
            grammarRuleIdsCsv = "gr_11_217"
        )
    )
}
