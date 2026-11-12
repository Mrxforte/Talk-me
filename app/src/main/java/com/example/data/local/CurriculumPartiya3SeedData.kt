package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * Full Partiya 3 (Lessons 41–60) seed data for TalkMe:
 * - Topic 5 (`topic_05_pronouns`): Lessons 41–50 (A1 Pronouns: I, you, he, she, it, we, they; my, your, his, her, our, their; this, that, these, those)
 * - Topic 6 (`topic_06_adverbs`): Lessons 51–60 (A1 Adverbs: here, there, inside, outside; now, yesterday, tomorrow; always, never, often, usually)
 */
object CurriculumPartiya3SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 3 — A1 OLMOSHLAR (DARS 41–50) inside topic_05_pronouns
        // =========================================================================
        LessonSectionEntity(
            id = "sec_05_41",
            topicId = "topic_05_pronouns",
            orderIndex = 1,
            titleEnglish = "41-Dars: I — Men",
            titleUzbek = "Olmosh: I — Men",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun olmoshlarni boshlaymiz. Olmosh — otning o'rnida ishlatiladigan so'z. Ingliz tilida birinchi olmosh: I — \"men\". 🚀",
            explanationUzbek = "📖 QOIDA:\n• I (ay) — \"men\". Birinchi shaxs birlik.\n• Muhim: I har doim bosh harf bilan yoziladi! \"I am a student\" — \"I\" katta harf.\n• Qanday ishlatamiz?\n  - I am a student. (to be bilan → am)\n  - I have a book. (boshqa fe'llar bilan → asosiy shakl)\n  - I like tea.\n\n💬 MISOLLAR:\n• I am Ali. — Men Aliman.\n• I am from Tashkent. — Men Toshkentdanman.\n• I have two books. — Menda ikkita kitob bor.\n• I like apples. — Men olmalarni yaxshi ko'raman.\n• I live in Uzbekistan. — Men O'zbekistonda yashayman.\n\n✍️ MASHQ:\n1. \"Men talabaman\" → ✅ I am a student.\n2. \"Menda kitob bor\" → ✅ I have a book.\n3. \"I\" qanday yoziladi? a) i b) I → ✅ b) I (bosh harf!)\n4. Ovoz chiqarib: \"I am a student. I have a book. I like tea.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: I — \"men\" (birinchi shaxs birlik, har doim bosh harf bilan). Formula: I am... / I have... / I like... Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Ajoyib, [USER_NAME]! Endi siz \"men\" deb aytasiz! Ertaga: You — \"sen/siz\". 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_42",
            topicId = "topic_05_pronouns",
            orderIndex = 2,
            titleEnglish = "42-Dars: You — Sen / Siz",
            titleUzbek = "Olmosh: You — Sen va Siz",
            objectiveUzbek = "🤖 TalkMe: Bugun You — \"sen\" yoki \"siz\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• You (yu) — \"sen\" yoki \"siz\". Ingliz tilida birlik va ko'plik uchun bir xil so'z ishlatiladi (ma'nosi kontekstdan bilinadi).\n• Muhim: \"You\" dan keyin fe'l are (to be bilan) yoki asosiy shakl (boshqa fe'llar bilan) keladi:\n  - You are a student.\n  - You have a book.\n  - You like tea.\n\n💬 MISOLLAR:\n• You are Ali. — Siz Alisiz.\n• You are from Tashkent. — Siz Toshkentdansiz.\n• You have two books. — Sizda ikkita kitob bor.\n• You like apples. — Siz olmalarni yaxshi ko'rasiz.\n• You live in Uzbekistan. — Siz O'zbekistonda yashaysiz.\n\n✍️ MASHQ:\n1. \"Siz talabasiz\" → ✅ You are a student.\n2. \"Sizda kitob bor\" → ✅ You have a book.\n3. \"You\" gap boshida qanday yoziladi? a) you b) You → ✅ b) You\n4. Ovoz chiqarib: \"You are a student. You have a book. You like tea.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: You — \"sen/siz\" (birlik va ko'plik uchun bir xil). Qanday ishlatamiz: You are... / You have... / You like... Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"sen/siz\" deb aytasiz! Ertaga: He — \"u\" (erkak). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_43",
            topicId = "topic_05_pronouns",
            orderIndex = 3,
            titleEnglish = "43-Dars: He — U (Erkak)",
            titleUzbek = "Olmosh: He — U (erkak)",
            objectiveUzbek = "🤖 TalkMe: Bugun He — \"u\" (erkak) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• He (hi) — \"u\" (faqat erkaklar va o'g'il bolalar uchun).\n• Muhim: \"He\" (3-shaxs birlik) dan keyin:\n  - to be fe'li → is: He is a doctor.\n  - to have fe'li → has: He has a car.\n  - oddiy fe'llarga -s qo'shiladi: He works here. He likes tea.\n\n💬 MISOLLAR:\n• He is Ali. — U Ali.\n• He is a doctor. — U shifokor.\n• He has a car. — Uning mashinasi bor.\n• He likes apples. — U olmalarni yaxshi ko'radi.\n• He lives in Tashkent. — U Toshkentda yashaydi.\n\n✍️ MASHQ:\n1. \"U (erkak) shifokor\" → ✅ He is a doctor.\n2. \"Uning mashinasi bor\" → ✅ He has a car.\n3. \"U olmalarni yaxshi ko'radi\" → ✅ He likes apples.\n4. Ovoz chiqarib: \"He is a doctor. He has a car. He likes tea.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: He — \"u\" (erkak, 3-shaxs birlik). Fe'l is / has yoki -s bilan keladi. Uyga vazifa: \"He is...\", \"He has...\", \"He likes...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"u\" (erkak) deb aytasiz! Ertaga: She — \"u\" (ayol). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_44",
            topicId = "topic_05_pronouns",
            orderIndex = 4,
            titleEnglish = "44-Dars: She — U (Ayol)",
            titleUzbek = "Olmosh: She — U (ayol)",
            objectiveUzbek = "🤖 TalkMe: Bugun She — \"u\" (ayol) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• She (shi) — \"u\" (faqat ayollar va qizlar uchun).\n• Muhim: \"She\" (3-shaxs birlik) dan keyin:\n  - to be fe'li → is: She is a doctor.\n  - to have fe'li → has: She has a car.\n  - oddiy fe'llarga -s qo'shiladi: She works here. She likes tea.\n\n💬 MISOLLAR:\n• She is Mary. — U Meri.\n• She is a doctor. — U shifokor.\n• She has a car. — Uning mashinasi bor.\n• She likes apples. — U olmalarni yaxshi ko'radi.\n• She lives in Tashkent. — U Toshkentda yashaydi.\n\n✍️ MASHQ:\n1. \"U (ayol) shifokor\" → ✅ She is a doctor.\n2. \"Uning mashinasi bor\" → ✅ She has a car.\n3. \"U olmalarni yaxshi ko'radi\" → ✅ She likes apples.\n4. Ovoz chiqarib: \"She is a doctor. She has a car. She likes tea.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: She — \"u\" (ayol, 3-shaxs birlik). Fe'l is / has yoki -s bilan keladi. Uyga vazifa: \"She is...\", \"She has...\", \"She likes...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"u\" (ayol) deb aytasiz! Ertaga: It — \"u\" (narsa). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_45",
            topicId = "topic_05_pronouns",
            orderIndex = 5,
            titleEnglish = "45-Dars: It — U (Narsa va Hayvon)",
            titleUzbek = "Olmosh: It — U (narsa)",
            objectiveUzbek = "🤖 TalkMe: Bugun It — \"u\" (narsa yoki hayvon) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• It (it) — \"u\" (jonsiz narsalar va hayvonlar uchun).\n• \"It\" dan keyin fe'l is / has yoki -s qo'shilgan asosiy fe'l keladi:\n  - It is a cat.\n  - It has a tail.\n  - It likes fish.\n• Muhim: \"It\" ob-havo va vaqtni aytishda soxta ega ham bo'ladi:\n  - It is cold. (Sovuq.)\n  - It is 5 o'clock. (Soat 5.)\n\n💬 MISOLLAR:\n• It is a cat. — Bu mushuk.\n• It is a book. — Bu kitob.\n• It has a tail. — Uning dumi bor.\n• It likes fish. — U baliqni yaxshi ko'radi.\n• It is cold. — Sovuq.\n• It is 5 o'clock. — Soat 5.\n\n✍️ MASHQ:\n1. \"Bu mushuk\" → ✅ It is a cat.\n2. \"Bu kitob\" → ✅ It is a book.\n3. \"Sovuq\" → ✅ It is cold.\n4. Ovoz chiqarib: \"It is a cat. It is a book. It is cold.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: It — \"u\" (narsa/hayvon) yoki soxta ega (ob-havo, vaqt). Fe'l is / has yoki -s bilan. Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"u\" (narsa) deb aytasiz! Ertaga: We — \"biz\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_46",
            topicId = "topic_05_pronouns",
            orderIndex = 6,
            titleEnglish = "46-Dars: We — Biz",
            titleUzbek = "Olmosh: We — Biz",
            objectiveUzbek = "🤖 TalkMe: Bugun We — \"biz\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• We (vi) — \"biz\". Birinchi shaxs ko'plik.\n• \"We\" dan keyin fe'l are (to be bilan) yoki asosiy shakl (boshqa fe'llar bilan) keladi:\n  - We are students.\n  - We have books.\n  - We like tea.\n\n💬 MISOLLAR:\n• We are students. — Biz talabalarmiz.\n• We are from Tashkent. — Biz Toshkentdanmiz.\n• We have two books. — Bizda ikkita kitob bor.\n• We like apples. — Biz olmalarni yaxshi ko'ramiz.\n• We live in Uzbekistan. — Biz O'zbekistonda yashaymiz.\n\n✍️ MASHQ:\n1. \"Biz talabalarmiz\" → ✅ We are students.\n2. \"Bizda kitob bor\" → ✅ We have a book.\n3. \"Biz olmalarni yaxshi ko'ramiz\" → ✅ We like apples.\n4. Ovoz chiqarib: \"We are students. We have books. We like tea.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: We — \"biz\" (birinchi shaxs ko'plik). Fe'l are yoki asosiy shakl bilan. Uyga vazifa: \"We are...\", \"We have...\", \"We like...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"biz\" deb aytasiz! Ertaga: They — \"ular\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_47",
            topicId = "topic_05_pronouns",
            orderIndex = 7,
            titleEnglish = "47-Dars: They — Ular",
            titleUzbek = "Olmosh: They — Ular",
            objectiveUzbek = "🤖 TalkMe: Bugun They — \"ular\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• They (they) — \"ular\". Uchinchi shaxs ko'plik (odamlar, hayvonlar va narsalar ko'pligi uchun).\n• \"They\" dan keyin fe'l are (to be bilan) yoki asosiy shakl (boshqa fe'llar bilan) keladi:\n  - They are students.\n  - They have books.\n  - They like tea.\n\n💬 MISOLLAR:\n• They are students. — Ular talabalar.\n• They are from Tashkent. — Ular Toshkentdan.\n• They have two books. — Ularda ikkita kitob bor.\n• They like apples. — Ular olmalarni yaxshi ko'radilar.\n• They live in Uzbekistan. — Ular O'zbekistonda yashaydilar.\n\n✍️ MASHQ:\n1. \"Ular talabalar\" → ✅ They are students.\n2. \"Ularda kitob bor\" → ✅ They have a book.\n3. \"Ular olmalarni yaxshi ko'radilar\" → ✅ They like apples.\n4. Ovoz chiqarib: \"They are students. They have books. They like tea.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: They — \"ular\" (uchinchi shaxs ko'plik). Fe'l are yoki asosiy shakl bilan. Uyga vazifa: \"They are...\", \"They have...\", \"They like...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"ular\" deb aytasiz! Ertaga: Egalik — my, your. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_48",
            topicId = "topic_05_pronouns",
            orderIndex = 8,
            titleEnglish = "48-Dars: Possessives: My, Your",
            titleUzbek = "Egalik olmoshlari: My, Your",
            objectiveUzbek = "🤖 TalkMe: Bugun egalik olmoshlarini boshlaymiz: my (mening) va your (sening/sizning)! 🚀",
            explanationUzbek = "📖 QOIDA:\n• My (may) — \"mening\"\n• Your (yo) — \"sening / sizning\"\n• Egalik olmoshi otdan oldin keladi: my book (mening kitobim), your book (sening kitobing).\n• Muhim: Egalik olmoshidan keyin ot hech qachon artikl (a/an/the) olmaydi! ✅ my book (my a book emas).\n\n💬 MISOLLAR:\n• My book is on the table. — Mening kitobim stolda.\n• Your pen is red. — Sening ruchkang qizil.\n• My name is Ali. — Mening ismim Ali.\n• Your name is Mary. — Sening isming Meri.\n• My car is new. — Mening mashinam yangi.\n• Your house is big. — Sening uying katta.\n\n✍️ MASHQ:\n1. \"Mening kitobim\" → ✅ my book\n2. \"Sening ruchkang\" → ✅ your pen\n3. \"Mening ismim Ali\" → ✅ My name is Ali.\n4. To'g'ri shaklni tanlang: \"___ book is new.\" a) my b) my a → ✅ a) my",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: My — mening, Your — sening/sizning. Otdan oldin keladi va artikl olmaydi. Uyga vazifa: \"My...\", \"Your...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"mening\" va \"sening\" deb aytasiz! Ertaga: his, her, our, their. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_49",
            topicId = "topic_05_pronouns",
            orderIndex = 9,
            titleEnglish = "49-Dars: Possessives: His, Her, Our, Their",
            titleUzbek = "Egalik: His, Her, Our, Their",
            objectiveUzbek = "🤖 TalkMe: Bugun qolgan egalik olmoshlarini o'rganamiz: his, her, our, their! 🚀",
            explanationUzbek = "📖 QOIDA:\n• His (hiz) — \"uning\" (erkak)\n• Her (hyo) — \"uning\" (ayol)\n• Our (aua) — \"bizning\"\n• Their (thea) — \"ularning\"\nOtdan oldin keladi va artikl olmaydi: his book, her pen, our house, their car.\n\n💬 MISOLLAR:\n• His book is on the table. — Uning (erkak) kitobi stolda.\n• Her pen is red. — Uning (ayol) ruchkasi qizil.\n• Our house is big. — Bizning uyimiz katta.\n• Their car is new. — Ularning mashinasi yangi.\n• His name is Ali. — Uning ismi Ali.\n• Her name is Mary. — Uning ismi Meri.\n\n✍️ MASHQ:\n1. \"Uning (erkak) kitobi\" → ✅ his book\n2. \"Uning (ayol) ruchkasi\" → ✅ her pen\n3. \"Bizning uyimiz\" → ✅ our house\n4. \"Ularning mashinasi\" → ✅ their car",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: His — uning (erkak), Her — uning (ayol), Our — bizning, Their — ularning. Otdan oldin, artikl yo'q. Uyga vazifa: 4 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi barcha egalik olmoshlarini bilasiz! Ertaga: this, that, these, those. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_05_50",
            topicId = "topic_05_pronouns",
            orderIndex = 10,
            titleEnglish = "50-Dars: Demonstratives: This, That, These, Those",
            titleUzbek = "Ko'rsatish: This, That, These, Those + Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun ko'rsatish olmoshlarini o'rganamiz: this, that, these, those! Va barcha A1 olmoshlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• This (zis) — \"bu\" (birlik, yaqin): this book — bu kitob.\n• That (zat) — \"u / ana u\" (birlik, uzoq): that book — u kitob.\n• These (ziz) — \"bular\" (ko'plik, yaqin): these books — bular kitoblar.\n• Those (zouz) — \"ular / ana ular\" (ko'plik, uzoq): those books — ular kitoblar.\nMuhim: This / That + birlik ot. These / Those + ko'plik ot.\n\n💬 MISOLLAR:\n• This book is mine. — Bu kitob meniki.\n• That house is old. — U uy eski.\n• These books are new. — Bu kitoblar yangi.\n• Those houses are old. — U uylar eski.\n• This is my pen. — Bu mening ruchkam.\n• These are my books. — Bular mening kitoblarim.\n\n✍️ MASHQ:\n1. \"Bu kitob\" → ✅ this book\n2. \"U uy\" → ✅ that house\n3. \"Bu kitoblar\" → ✅ these books\n4. \"U uylar\" → ✅ those houses\n5. \"___ books are new.\" a) this b) these → ✅ b) these",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: This (bu, birlik), that (u, birlik), these (bular, ko'plik), those (ular, ko'plik). A1 Olmoshlar tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz barcha A1 olmoshlarni o'zlashtirdingiz! Keyingi bo'lim: ravishlar — here, there. 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 3 — A1 RAVISHLAR (DARS 51–60) inside topic_06_adverbs
        // =========================================================================
        LessonSectionEntity(
            id = "sec_06_51",
            topicId = "topic_06_adverbs",
            orderIndex = 1,
            titleEnglish = "51-Dars: Here — Bu yerda",
            titleUzbek = "Ravish: Here — Bu yerda",
            objectiveUzbek = "🤖 TalkMe: Endi ravishlarni boshlaymiz! Ravish — ish-harakatning qanday, qayerda, qachon sodir bo'lishini bildiruvchi so'z. Birinchi ravish: here — \"bu yerda\". 🚀",
            explanationUzbek = "📖 QOIDA:\n• Here (hiya) — \"bu yerda\" / \"bu yoqqa\" (o'rin ravishi).\n• Odatda fe'ldan keyin keladi va gapiruvchining yaqinidagi joyni bildiradi:\n  - Come here. (Bu yoqqa kel.)\n  - My book is here. (Mening kitobim bu yerda.)\n\n💬 MISOLLAR:\n• Come here. — Bu yoqqa kel.\n• My book is here. — Mening kitobim bu yerda.\n• Sit here, please. — Bu yerga o'tiring, iltimos.\n• Here is your pen. — Mana ruchkangiz.\n• I am here. — Men bu yerdaman.\n\n✍️ MASHQ:\n1. \"Bu yoqqa kel\" → ✅ Come here.\n2. \"Mening kitobim bu yerda\" → ✅ My book is here.\n3. \"Bu yerga o'tiring\" → ✅ Sit here.\n4. Ovoz chiqarib: \"Come here. My book is here. I am here.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Here — \"bu yerda / bu yoqqa\" (o'rin ravishi, fe'ldan keyin). Uyga vazifa: \"Come here.\", \"My ... is here.\", \"I am here.\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"bu yerda\" deb aytasiz! Ertaga: there — \"u yerda\". 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_52",
            topicId = "topic_06_adverbs",
            orderIndex = 2,
            titleEnglish = "52-Dars: There — U yerda",
            titleUzbek = "Ravish: There — U yerda",
            objectiveUzbek = "🤖 TalkMe: Bugun there — \"u yerda\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• There (thea) — \"u yerda\" / \"u yoqqa\" (o'rin ravishi).\n• Gapiruvchidan uzoqdagi joyni bildiradi va fe'ldan keyin keladi:\n  - Go there. (U yoqqa bor.)\n  - My book is there. (Mening kitobim u yerda.)\n\n💬 MISOLLAR:\n• Go there. — U yoqqa bor.\n• My book is there. — Mening kitobim u yerda.\n• Sit there, please. — U yerga o'tiring, iltimos.\n• There is your pen. — Ana ruchkangiz.\n• I am there. — Men u yerdaman.\n\n✍️ MASHQ:\n1. \"U yoqqa bor\" → ✅ Go there.\n2. \"Mening kitobim u yerda\" → ✅ My book is there.\n3. \"U yerga o'tiring\" → ✅ Sit there.\n4. Ovoz chiqarib: \"Go there. My book is there. I am there.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: There — \"u yerda / u yoqqa\" (o'rin ravishi, fe'ldan keyin). Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"u yerda\" deb aytasiz! Ertaga: inside, outside. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_53",
            topicId = "topic_06_adverbs",
            orderIndex = 3,
            titleEnglish = "53-Dars: Inside & Outside",
            titleUzbek = "Ravishlar: Inside, Outside",
            objectiveUzbek = "🤖 TalkMe: Bugun inside (ichkarida) va outside (tashqarida) ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Inside (insayd) — \"ichkarida / ichkariga\"\n• Outside (autsayd) — \"tashqarida / tashqariga\"\nFe'ldan keyin keladi: The cat is inside. The children are outside.\n\n💬 MISOLLAR:\n• The cat is inside. — Mushuk ichkarida.\n• The children are outside. — Bolalar tashqarida.\n• Come inside. — Ichkariga kel.\n• Go outside. — Tashqariga chiq.\n• It is cold outside. — Tashqarida sovuq.\n\n✍️ MASHQ:\n1. \"Mushuk ichkarida\" → ✅ The cat is inside.\n2. \"Bolalar tashqarida\" → ✅ The children are outside.\n3. \"Ichkariga kel\" → ✅ Come inside.\n4. \"Tashqarida sovuq\" → ✅ It is cold outside.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Inside — ichkarida/ichkariga, Outside — tashqarida/tashqariga. Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"ichkarida\" va \"tashqarida\" deb aytasiz! Ertaga: now — \"hozir\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_54",
            topicId = "topic_06_adverbs",
            orderIndex = 4,
            titleEnglish = "54-Dars: Now — Hozir",
            titleUzbek = "Payt ravishi: Now — Hozir",
            objectiveUzbek = "🤖 TalkMe: Endi payt ravishlarini boshlaymiz! Birinchi: now — \"hozir\". 🚀",
            explanationUzbek = "📖 QOIDA:\n• Now (nau) — \"hozir\" (payt ravishi).\n• Odatda gap oxirida yoki gap boshida keladi:\n  - I am busy now. (Men hozir bandman.)\n  - Now I am busy. (Hozir men bandman.)\n\n💬 MISOLLAR:\n• I am busy now. — Men hozir bandman.\n• Now I am busy. — Hozir men bandman.\n• Come now. — Hozir kel.\n• He is reading now. — U hozir o'qiyapti.\n• We are eating now. — Biz hozir ovqatlanayapmiz.\n\n✍️ MASHQ:\n1. \"Men hozir bandman\" → ✅ I am busy now.\n2. \"Hozir kel\" → ✅ Come now.\n3. \"U hozir o'qiyapti\" → ✅ He is reading now.\n4. Ovoz chiqarib: \"I am busy now. Come now. He is reading now.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Now — \"hozir\" (payt ravishi, gap oxirida yoki boshida). Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"hozir\" deb aytasiz! Ertaga: yesterday — \"kecha\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_55",
            topicId = "topic_06_adverbs",
            orderIndex = 5,
            titleEnglish = "55-Dars: Yesterday — Kecha",
            titleUzbek = "Payt ravishi: Yesterday — Kecha",
            objectiveUzbek = "🤖 TalkMe: Bugun yesterday — \"kecha\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Yesterday (yestadey) — \"kecha\" (payt ravishi).\n• Gap boshida yoki oxirida keladi va o'tgan zamon bilan ishlatiladi:\n  - Yesterday I went to school.\n  - I went to school yesterday.\n\n💬 MISOLLAR:\n• Yesterday I went to school. — Kecha men maktabga bordim.\n• I saw him yesterday. — Men uni kecha ko'rdim.\n• Yesterday was Sunday. — Kecha yakshanba edi.\n• She came yesterday. — U kecha keldi.\n• We worked yesterday. — Biz kecha ishladik.\n\n✍️ MASHQ:\n1. \"Kecha men maktabga bordim\" → ✅ Yesterday I went to school.\n2. \"Men uni kecha ko'rdim\" → ✅ I saw him yesterday.\n3. \"U kecha keldi\" → ✅ She came yesterday.\n4. Ovoz chiqarib: \"Yesterday I went to school. I saw him yesterday.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Yesterday — \"kecha\" (o'tgan zamon bilan, gap boshida yoki oxirida). Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"kecha\" deb aytasiz! Ertaga: tomorrow — \"ertaga\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_56",
            topicId = "topic_06_adverbs",
            orderIndex = 6,
            titleEnglish = "56-Dars: Tomorrow — Ertaga",
            titleUzbek = "Payt ravishi: Tomorrow — Ertaga",
            objectiveUzbek = "🤖 TalkMe: Bugun tomorrow — \"ertaga\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Tomorrow (tomorou) — \"ertaga\" (payt ravishi).\n• Gap boshida yoki oxirida keladi va kelasi zamon (will) bilan ishlatiladi:\n  - Tomorrow I will go to school.\n  - I will go to school tomorrow.\n\n💬 MISOLLAR:\n• Tomorrow I will go to school. — Ertaga men maktabga boraman.\n• I will see him tomorrow. — Men uni ertaga ko'raman.\n• Tomorrow is Monday. — Ertaga dushanba.\n• She will come tomorrow. — U ertaga keladi.\n• We will work tomorrow. — Biz ertaga ishlaymiz.\n\n✍️ MASHQ:\n1. \"Ertaga men maktabga boraman\" → ✅ Tomorrow I will go to school.\n2. \"Men uni ertaga ko'raman\" → ✅ I will see him tomorrow.\n3. \"U ertaga keladi\" → ✅ She will come tomorrow.\n4. Ovoz chiqarib: \"Tomorrow I will go to school. I will see him tomorrow.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Tomorrow — \"ertaga\" (kelasi zamon bilan, gap boshida yoki oxirida). Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"ertaga\" deb aytasiz! Ertaga: always — \"har doim\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_57",
            topicId = "topic_06_adverbs",
            orderIndex = 7,
            titleEnglish = "57-Dars: Always — Har doim",
            titleUzbek = "Chastota ravishi: Always — Har doim",
            objectiveUzbek = "🤖 TalkMe: Endi chastota ravishlarini boshlaymiz! Birinchi: always — \"har doim\". 🚀",
            explanationUzbek = "📖 QOIDA:\n• Always (olveyz) — \"har doim\" (100% chastota ravishi).\n• Oddiy fe'ldan OLDIN keladi: I always get up early.\n• To be (am/is/are) fe'lidan KEYIN keladi: He is always late.\n\n💬 MISOLLAR:\n• I always get up early. — Men har doim erta turaman.\n• He always helps me. — U menga har doim yordam beradi.\n• She always comes on time. — U har doim vaqtida keladi.\n• He is always late. — U har doim kechikadi. (to be dan keyin)\n• They always eat breakfast. — Ular har doim nonushta qilishadi.\n\n✍️ MASHQ:\n1. \"Men har doim erta turaman\" → ✅ I always get up early.\n2. \"U menga har doim yordam beradi\" → ✅ He always helps me.\n3. \"U har doim kechikadi\" → ✅ He is always late.\n4. Ovoz chiqarib: \"I always get up early. He always helps me.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Always — \"har doim\" (oddiy fe'ldan oldin, to be dan keyin). Uyga vazifa: \"I always...\", \"He is always...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"har doim\" deb aytasiz! Ertaga: never — \"hech qachon\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_58",
            topicId = "topic_06_adverbs",
            orderIndex = 8,
            titleEnglish = "58-Dars: Never — Hech qachon",
            titleUzbek = "Chastota ravishi: Never — Hech qachon",
            objectiveUzbek = "🤖 TalkMe: Bugun never — \"hech qachon\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Never (neva) — \"hech qachon\" (0% chastota ravishi).\n• Oddiy fe'ldan oldin, to be fe'lidan keyin keladi.\n• Muhim: \"Never\" o'zi inkor ma'nosini beradi, shuning uchun gapda don't / doesn't / not ishlatilmaydi!\n  ✅ I never smoke. (I don't never smoke emas!)\n\n💬 MISOLLAR:\n• I never smoke. — Men hech qachon chekmayman.\n• He never eats meat. — U hech qachon go'sht yemaydi.\n• She never comes late. — U hech qachon kech kelmaydi.\n• He is never late. — U hech qachon kechikmaydi.\n• They never watch TV. — Ular hech qachon televizor ko'rishmaydi.\n\n✍️ MASHQ:\n1. \"Men hech qachon chekmayman\" → ✅ I never smoke.\n2. \"U hech qachon go'sht yemaydi\" → ✅ He never eats meat.\n3. \"U hech qachon kechikmaydi\" → ✅ He is never late.\n4. Xatoni toping: \"I don't never smoke.\" → ✅ I never smoke.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Never — \"hech qachon\" (fe'ldan oldin, to be dan keyin, ikkinchi inkor qo'shilmaydi!). Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"hech qachon\" deb aytasiz! Ertaga: often — \"tez-tez\". 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_59",
            topicId = "topic_06_adverbs",
            orderIndex = 9,
            titleEnglish = "59-Dars: Often — Tez-tez",
            titleUzbek = "Chastota ravishi: Often — Tez-tez",
            objectiveUzbek = "🤖 TalkMe: Bugun often — \"tez-tez\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Often (ofan) — \"tez-tez\" (70% chastota ravishi).\n• Oddiy fe'ldan oldin keladi: I often read books.\n• To be fe'lidan keyin keladi: He is often busy.\n\n💬 MISOLLAR:\n• I often read books. — Men tez-tez kitob o'qiyman.\n• He often visits us. — U biznikiga tez-tez kelib turadi.\n• She often goes to the park. — U tez-tez parkga boradi.\n• He is often busy. — U tez-tez band bo'ladi.\n• They often play football. — Ular tez-tez futbol o'ynashadi.\n\n✍️ MASHQ:\n1. \"Men tez-tez kitob o'qiyman\" → ✅ I often read books.\n2. \"U biznikiga tez-tez kelib turadi\" → ✅ He often visits us.\n3. \"U tez-tez band bo'ladi\" → ✅ He is often busy.\n4. Ovoz chiqarib: \"I often read books. He often visits us.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Often — \"tez-tez\" (fe'ldan oldin, to be dan keyin). Uyga vazifa: \"I often...\", \"He is often...\" bilan 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz \"tez-tez\" deb aytasiz! Ertaga: usually + takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_06_60",
            topicId = "topic_06_adverbs",
            orderIndex = 10,
            titleEnglish = "60-Dars: Usually & Part 3 Review",
            titleUzbek = "Usually — Odatda + Partiya 3 Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun usually — \"odatda\" ni o'rganamiz va Partiya 3 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Usually (yujuali) — \"odatda\" (90% chastota ravishi).\n• Oddiy fe'ldan oldin: I usually get up at 7.\n• To be dan keyin: He is usually busy.\n• Chastota ravishlari shkalasi (ko'pdan kamga):\n  always (100%) → usually (90%) → often (70%) → sometimes (50%) → seldom (30%) → never (0%).\n\n💬 MISOLLAR:\n• I usually get up at 7 o'clock. — Men odatda soat 7 da turaman.\n• He usually goes to work by bus. — U odatda ishga avtobusda boradi.\n• She usually cooks dinner. — U odatda kechki ovqat qiladi.\n• He is usually busy. — U odatda band bo'ladi.\n• They usually play football on Sundays. — Ular odatda yakshanba kunlari futbol o'ynashadi.\n\n✍️ MASHQ:\n1. \"Men odatda soat 7 da turaman\" → ✅ I usually get up at 7 o'clock.\n2. \"U odatda ishga avtobusda boradi\" → ✅ He usually goes to work by bus.\n3. \"U odatda band bo'ladi\" → ✅ He is usually busy.\n4. A1 Ravishlarni takrorlang: here, there, inside, outside, now, yesterday, tomorrow, always, never, often, usually.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Usually — \"odatda\". Chastota tartibi: always → usually → often → sometimes → seldom → never. A1 Ravishlar va Partiya 3 tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz A1 Olmoshlar va A1 Ravishlarni to'liq o'zlashtirdingiz! Partiya 3 (41–60 darslar) tugadi! Keyingi bo'lim — Partiya 4: Fe'llar (to be, to have, to do)! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 3 — DARS 41–50: A1 OLMOSHLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_05_i", "topic_05_pronouns", "sec_05_41", "I", "men", "I am a student.", "Men talabaman.", isStudied = false),
        VocabularyEntity("voc_05_you", "topic_05_pronouns", "sec_05_42", "You", "sen yoki siz", "You are a student.", "Siz talabasiz.", isStudied = false),
        VocabularyEntity("voc_05_he", "topic_05_pronouns", "sec_05_43", "He", "u (erkak)", "He is a doctor.", "U shifokor.", isStudied = false),
        VocabularyEntity("voc_05_she", "topic_05_pronouns", "sec_05_44", "She", "u (ayol)", "She is a doctor.", "U shifokor.", isStudied = false),
        VocabularyEntity("voc_05_it", "topic_05_pronouns", "sec_05_45", "It", "u (narsa yoki hayvon)", "It is a cat.", "Bu mushuk.", isStudied = false),
        VocabularyEntity("voc_05_we", "topic_05_pronouns", "sec_05_46", "We", "biz", "We are students.", "Biz talabalarmiz.", isStudied = false),
        VocabularyEntity("voc_05_they", "topic_05_pronouns", "sec_05_47", "They", "ular", "They are students.", "Ular talabalar.", isStudied = false),
        VocabularyEntity("voc_05_his", "topic_05_pronouns", "sec_05_49", "His", "uning (erkak)", "His book is on the table.", "Uning kitobi stolda.", isStudied = false),
        VocabularyEntity("voc_05_her", "topic_05_pronouns", "sec_05_49", "Her", "uning (ayol)", "Her pen is red.", "Uning ruchkasi qizil.", isStudied = false),
        VocabularyEntity("voc_05_our", "topic_05_pronouns", "sec_05_49", "Our", "bizning", "Our house is big.", "Bizning uyimiz katta.", isStudied = false),
        VocabularyEntity("voc_05_their", "topic_05_pronouns", "sec_05_49", "Their", "ularning", "Their car is new.", "Ularning mashinasi yangi.", isStudied = false),
        VocabularyEntity("voc_05_this", "topic_05_pronouns", "sec_05_50", "This", "bu (birlik, yaqin)", "This book is mine.", "Bu kitob meniki.", isStudied = false),
        VocabularyEntity("voc_05_that", "topic_05_pronouns", "sec_05_50", "That", "u, ana u (birlik, uzoq)", "That house is old.", "U uy eski.", isStudied = false),
        VocabularyEntity("voc_05_these", "topic_05_pronouns", "sec_05_50", "These", "bular (ko'plik, yaqin)", "These books are new.", "Bular yangi kitoblar.", isStudied = false),
        VocabularyEntity("voc_05_those", "topic_05_pronouns", "sec_05_50", "Those", "ular, ana ular (ko'plik, uzoq)", "Those houses are old.", "Ular eski uylar.", isStudied = false),

        // =========================================================================
        // PARTIYA 3 — DARS 51–60: A1 RAVISHLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_06_here", "topic_06_adverbs", "sec_06_51", "Here", "bu yerda, bu yoqqa", "My book is here.", "Mening kitobim bu yerda.", isStudied = false),
        VocabularyEntity("voc_06_there", "topic_06_adverbs", "sec_06_52", "There", "u yerda, u yoqqa", "My book is there.", "Mening kitobim u yerda.", isStudied = false),
        VocabularyEntity("voc_06_inside", "topic_06_adverbs", "sec_06_53", "Inside", "ichkarida, ichkariga", "The cat is inside.", "Mushuk ichkarida.", isStudied = false),
        VocabularyEntity("voc_06_outside", "topic_06_adverbs", "sec_06_53", "Outside", "tashqarida, tashqariga", "The children are outside.", "Bolalar tashqarida.", isStudied = false),
        VocabularyEntity("voc_06_now", "topic_06_adverbs", "sec_06_54", "Now", "hozir", "I am busy now.", "Men hozir bandman.", isStudied = false),
        VocabularyEntity("voc_06_yesterday", "topic_06_adverbs", "sec_06_55", "Yesterday", "kecha", "Yesterday I went to school.", "Kecha men maktabga bordim.", isStudied = false),
        VocabularyEntity("voc_06_tomorrow", "topic_06_adverbs", "sec_06_56", "Tomorrow", "ertaga", "Tomorrow I will go to school.", "Ertaga men maktabga boraman.", isStudied = false),
        VocabularyEntity("voc_06_always", "topic_06_adverbs", "sec_06_57", "Always", "har doim", "I always get up early.", "Men har doim erta turaman.", isStudied = false),
        VocabularyEntity("voc_06_never", "topic_06_adverbs", "sec_06_58", "Never", "hech qachon", "I never smoke.", "Men hech qachon chekmayman.", isStudied = false),
        VocabularyEntity("voc_06_often", "topic_06_adverbs", "sec_06_59", "Often", "tez-tez", "I often read books.", "Men tez-tez kitob o'qiyman.", isStudied = false),
        VocabularyEntity("voc_06_usually", "topic_06_adverbs", "sec_06_60", "Usually", "odatda", "I usually get up at 7 o'clock.", "Men odatda soat 7 da turaman.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // =========================================================================
        // PARTIYA 3 — DARS 41–50 GRAMMAR RULES (A1 OLMOSHLAR)
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_05_41",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_41",
            titleUzbek = "Kishilik olmoshlari: I, You, We, They",
            explanationUzbek = "I (men — har doim bosh harf bilan) + am / asosiy fe'l. You (sen/siz), We (biz), They (ular) + are / asosiy fe'l.",
            exampleEnglish = "I am a student. We are students. They like apples.",
            exampleUzbek = "Men talabaman. Biz talabalarmiz. Ular olmalarni yaxshi ko'radilar.",
            associatedVocabularyIdsCsv = "voc_05_i,voc_05_you,voc_05_we,voc_05_they",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_05_43",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_43",
            titleUzbek = "3-shaxs birlik olmoshlari: He, She, It",
            explanationUzbek = "He (erkak), She (ayol), It (narsa/hayvon va ob-havo/vaqt uchun soxta ega) dan keyin is / has yoki -s qo'shilgan fe'l keladi.",
            exampleEnglish = "He is a doctor. She likes apples. It is cold.",
            exampleUzbek = "U shifokor. U olmalarni yaxshi ko'radi. Sovuq.",
            associatedVocabularyIdsCsv = "voc_05_he,voc_05_she,voc_05_it",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_05_48",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_48",
            titleUzbek = "Egalik olmoshlari: my, your, his, her, our, their",
            explanationUzbek = "Egalik olmoshlari otdan oldin keladi va ulardan keyin hech qachon a/an/the artikli qo'yilmaydi: my book, your pen, his car, her house, our family, their school.",
            exampleEnglish = "My book, his pen, our house, and their car.",
            exampleUzbek = "Mening kitobim, uning ruchkasi, bizning uyimiz va ularning mashinasi.",
            associatedVocabularyIdsCsv = "voc_01_my,voc_01_your,voc_05_his,voc_05_her,voc_05_our,voc_05_their",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_05_50",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_50",
            titleUzbek = "Ko'rsatish olmoshlari: this, that, these, those",
            explanationUzbek = "This (bu — birlik, yaqin) va That (u — birlik, uzoq) birlik ot bilan; These (bular — ko'plik, yaqin) va Those (ular — ko'plik, uzoq) ko'plik ot bilan ishlatiladi.",
            exampleEnglish = "This book is new. These books are new. Those houses are old.",
            exampleUzbek = "Bu kitob yangi. Bu kitoblar yangi. U uylar eski.",
            associatedVocabularyIdsCsv = "voc_05_this,voc_05_that,voc_05_these,voc_05_those",
            isStudied = false,
            isMastered = false
        ),

        // =========================================================================
        // PARTIYA 3 — DARS 51–60 GRAMMAR RULES (A1 RAVISHLAR)
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_06_51",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_51",
            titleUzbek = "O'rin ravishlari: here, there, inside, outside",
            explanationUzbek = "Here (bu yerda — yaqin), there (u yerda — uzoq), inside (ichkarida), outside (tashqarida) odatda fe'ldan keyin keladi.",
            exampleEnglish = "Come here. Go there. The cat is inside. The children are outside.",
            exampleUzbek = "Bu yoqqa kel. U yoqqa bor. Mushuk ichkarida. Bolalar tashqarida.",
            associatedVocabularyIdsCsv = "voc_06_here,voc_06_there,voc_06_inside,voc_06_outside",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_06_54",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_54",
            titleUzbek = "Payt ravishlari: now, yesterday, tomorrow",
            explanationUzbek = "Now (hozir — hozirgi zamon), yesterday (kecha — o'tgan zamon), tomorrow (ertaga — kelasi zamon) gap boshida yoki oxirida keladi.",
            exampleEnglish = "I am busy now. Yesterday I went to school. Tomorrow I will work.",
            exampleUzbek = "Men hozir bandman. Kecha maktabga bordim. Ertaga ishlayman.",
            associatedVocabularyIdsCsv = "voc_06_now,voc_06_yesterday,voc_06_tomorrow",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_06_57",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_57",
            titleUzbek = "Chastota ravishlari: always, usually, often, never",
            explanationUzbek = "Chastota ravishlari oddiy fe'ldan OLDIN (I always get up early), lekin to be fe'lidan KEYIN (He is always late) keladi. Never bilan ikkinchi inkor (don't) ishlatilmaydi!",
            exampleEnglish = "I always get up early. I never smoke. He is usually busy.",
            exampleUzbek = "Men har doim erta turaman. Men hech qachon chekmayman. U odatda band bo'ladi.",
            associatedVocabularyIdsCsv = "voc_06_always,voc_06_never,voc_06_often,voc_06_usually",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // =========================================================================
        // PARTIYA 3 — DARS 41–50 EXERCISES (topic_05_pronouns)
        // =========================================================================
        ExerciseEntity(
            id = "ex_05_41",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_41",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Men talabaman\" gapini ingliz tilida to'g'ri toping:",
            subPromptText = "41-Dars: I har doim bosh harf bilan yoziladi",
            audioTextToSpeak = "I am a student.",
            optionsPipeSeparated = "I am a student.|i am a student.|I is a student.|I are a student.",
            correctAnswer = "I am a student.",
            explanationUzbek = "I + am: \"I am a student.\"",
            vocabularyIdsCsv = "voc_05_i",
            grammarRuleIdsCsv = "gram_05_41"
        ),
        ExerciseEntity(
            id = "ex_05_43",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_43",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"U (erkak) olmalarni yaxshi ko'radi\" gapini toping:",
            subPromptText = "43-Dars: He + fe'l-s",
            audioTextToSpeak = "He likes apples.",
            optionsPipeSeparated = "He likes apples.|He like apples.|She likes apples.|They likes apples.",
            correctAnswer = "He likes apples.",
            explanationUzbek = "3-shaxs birlikda (He) fe'lga -s qo'shiladi: \"He likes apples.\"",
            vocabularyIdsCsv = "voc_05_he",
            grammarRuleIdsCsv = "gram_05_43"
        ),
        ExerciseEntity(
            id = "ex_05_48",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_48",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"___ book is new.\" (Mening kitobim yangi)",
            subPromptText = "48-Dars: Egalik olmoshidan keyin artikl qo'yilmaydi",
            audioTextToSpeak = "My book is new.",
            optionsPipeSeparated = "My|My a|My the|I",
            correctAnswer = "My",
            explanationUzbek = "Egalik olmoshidan keyin a/the artikli kelmaydi: \"My book is new.\"",
            vocabularyIdsCsv = "voc_01_my",
            grammarRuleIdsCsv = "gram_05_48"
        ),
        ExerciseEntity(
            id = "ex_05_49",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_49",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Bizning uyimiz\" va \"ularning mashinasi\" birikmalarini tanlang:",
            subPromptText = "49-Dars: his, her, our, their",
            audioTextToSpeak = "our house, their car",
            optionsPipeSeparated = "our house, their car|his house, her car|my house, your car|we house, they car",
            correctAnswer = "our house, their car",
            explanationUzbek = "Our — bizning, their — ularning: \"our house, their car\".",
            vocabularyIdsCsv = "voc_05_our,voc_05_their",
            grammarRuleIdsCsv = "gram_05_48"
        ),
        ExerciseEntity(
            id = "ex_05_50",
            topicId = "topic_05_pronouns",
            sectionId = "sec_05_50",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "To'g'ri ko'rsatish olmoshini tanlang: \"___ books are new.\"",
            subPromptText = "50-Dars: Ko'plikdagi yaqin narsalar uchun",
            audioTextToSpeak = "These books are new.",
            optionsPipeSeparated = "These|This|That|It",
            correctAnswer = "These",
            explanationUzbek = "Books ko'plikda bo'lgani uchun \"These\" ishlatiladi: \"These books are new.\"",
            vocabularyIdsCsv = "voc_05_these",
            grammarRuleIdsCsv = "gram_05_50"
        ),

        // =========================================================================
        // PARTIYA 3 — DARS 51–60 EXERCISES (topic_06_adverbs)
        // =========================================================================
        ExerciseEntity(
            id = "ex_06_51",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_51",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Bu yoqqa kel\" va \"U yoqqa bor\" gaplarini ingliz tilida toping:",
            subPromptText = "51–52 Darslar: here va there",
            audioTextToSpeak = "Come here. Go there.",
            optionsPipeSeparated = "Come here. Go there.|Come there. Go here.|Sit inside. Go outside.|Come now. Go yesterday.",
            correctAnswer = "Come here. Go there.",
            explanationUzbek = "Here — bu yerda/bu yoqqa, there — u yerda/u yoqqa.",
            vocabularyIdsCsv = "voc_06_here,voc_06_there",
            grammarRuleIdsCsv = "gram_06_51"
        ),
        ExerciseEntity(
            id = "ex_06_53",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_53",
            practiceCategory = PracticeCategory.VOCABULARY.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Mushuk ichkarida\" va \"Bolalar tashqarida\" gaplarini toping:",
            subPromptText = "53-Dars: inside va outside",
            audioTextToSpeak = "The cat is inside. The children are outside.",
            optionsPipeSeparated = "The cat is inside. The children are outside.|The cat is outside. The children are inside.|The cat is here. The children are there.|The cat is now. The children are tomorrow.",
            correctAnswer = "The cat is inside. The children are outside.",
            explanationUzbek = "Inside — ichkarida, outside — tashqarida.",
            vocabularyIdsCsv = "voc_06_inside,voc_06_outside",
            grammarRuleIdsCsv = "gram_06_51"
        ),
        ExerciseEntity(
            id = "ex_06_55",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_55",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Kecha men maktabga bordim\" gapini ingliz tilida tanlang:",
            subPromptText = "55-Dars: Yesterday (kecha)",
            audioTextToSpeak = "Yesterday I went to school.",
            optionsPipeSeparated = "Yesterday I went to school.|Tomorrow I went to school.|Now I went to school.|Always I went to school.",
            correctAnswer = "Yesterday I went to school.",
            explanationUzbek = "Yesterday — kecha (o'tgan zamon bilan ishlatiladi).",
            vocabularyIdsCsv = "voc_06_yesterday",
            grammarRuleIdsCsv = "gram_06_54"
        ),
        ExerciseEntity(
            id = "ex_06_58",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_58",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Men hech qachon chekmayman\" gapining to'g'ri shaklini tanlang:",
            subPromptText = "58-Dars: Never bilan ikkinchi inkor ishlatilmaydi!",
            audioTextToSpeak = "I never smoke.",
            optionsPipeSeparated = "I never smoke.|I don't never smoke.|I not never smoke.|Never I don't smoke.",
            correctAnswer = "I never smoke.",
            explanationUzbek = "Never o'zi inkor bo'lgani uchun don't qo'shilmaydi: \"I never smoke.\"",
            vocabularyIdsCsv = "voc_06_never",
            grammarRuleIdsCsv = "gram_06_57"
        ),
        ExerciseEntity(
            id = "ex_06_60",
            topicId = "topic_06_adverbs",
            sectionId = "sec_06_60",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Men odatda soat 7 da turaman\" gapini toping:",
            subPromptText = "60-Dars: Usually (odatda)",
            audioTextToSpeak = "I usually get up at 7 o'clock.",
            optionsPipeSeparated = "I usually get up at 7 o'clock.|I never get up at 7 o'clock.|I get up usually at 7 o'clock.|Usually I am get up at 7 o'clock.",
            correctAnswer = "I usually get up at 7 o'clock.",
            explanationUzbek = "Usually oddiy fe'ldan oldin keladi: \"I usually get up at 7 o'clock.\"",
            vocabularyIdsCsv = "voc_06_usually",
            grammarRuleIdsCsv = "gram_06_57"
        )
    )
}
