package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 8 — DARS 141–160 (TO'LIQ):
 * - A2 OLMOSHLAR (Dars 141–148, topic_10_a2_grammar, orderIndex 31..38)
 * - A2 RAVISHLAR (Dars 149–152, topic_10_a2_grammar, orderIndex 39..42)
 * - A2 FE'LLAR: Present Continuous & Past Simple (Dars 153–160, topic_10_a2_grammar, orderIndex 43..50)
 *
 * PARTIYA 9 — DARS 161–180 (TO'LIQ):
 * - A2 KO'RSATKICHLAR: some, any, no, much, many (Dars 161–166, topic_10_a2_grammar, orderIndex 51..56)
 * - A2 ARTIKL KENGAYTIRILGAN (Dars 167–170, topic_10_a2_grammar, orderIndex 57..60)
 * - A2 PREDLOG KENGAYTIRILGAN (Dars 171–176, topic_10_a2_grammar, orderIndex 61..66)
 * - A2 BOG'LOVCHI: because va takrorlash (Dars 177–180, topic_10_a2_grammar, orderIndex 67..70)
 */
object CurriculumPartiya8And9SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 8 — DARS 141–160 inside topic_10_a2_grammar (orderIndex 31..50)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_10_141",
            topicId = "topic_10_a2_grammar",
            orderIndex = 31,
            titleEnglish = "141-Dars: Demonstratives — This & That",
            titleUzbek = "This va That — Birlik ko'rsatish olmoshlari",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun ko'rsatish olmoshlarini o'rganamiz: this va that! 🚀",
            explanationUzbek = "📖 QOIDA:\n• This (zis) — \"bu\" (yaqin, birlik): qo'lingizdagi, oldingizdagi narsa: this book.\n• That (zat) — \"u\" (uzoq, birlik): naridagi, uzoqdagi narsa: that house.\nMuhim: This/that + birlikdagi ot. Fe'l is: This is my book. That is your car.\n\n💬 MISOLLAR:\n• This book is mine. — Bu kitob meniki. (qo'limda)\n• That house is old. — U uy eski. (narida)\n• This is my pen. — Bu mening ruchkam.\n• That is your car. — U sizning mashinangiz.\n\n✍️ MASHQ:\n1. \"Bu kitob meniki\" → ✅ This book is mine.\n2. \"U uy eski\" → ✅ That house is old.\n3. \"Bu mening ruchkam\" → ✅ This is my pen.\n4. Qo'limdagi narsa uchun nima ishlatamiz? a) this b) that → ✅ a) this",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: This — bu (yaqin, birlik). That — u (uzoq, birlik). Birlikdagi ot va is bilan.\n\n🤖 TalkMe yakuniy: Zo'r! Endi this/that ni bilasiz! Ertaga: these/those — ko'plik. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_142",
            topicId = "topic_10_a2_grammar",
            orderIndex = 32,
            titleEnglish = "142-Dars: Demonstratives — These & Those",
            titleUzbek = "These va Those — Ko'plik ko'rsatish olmoshlari",
            objectiveUzbek = "🤖 TalkMe: Bugun these va those ni o'rganamiz: \"bular\" va \"ular\"! 🚀",
            explanationUzbek = "📖 QOIDA:\n• These (ziz) — \"bular\" (yaqin, ko'plik): qo'lingizdagi bir nechta narsa: these books.\n• Those (zouz) — \"ular\" (uzoq, ko'plik): naridagi bir nechta narsa: those houses.\nMuhim: These/those + ko'plikdagi ot. Fe'l are: These are my books. Those are your cars.\n\n💬 MISOLLAR:\n• These books are mine. — Bu kitoblar meniki.\n• Those houses are old. — U uylar eski.\n• These are my pens. — Bular mening ruchkalarim.\n• Those are your cars. — Ular sizning mashinalaringiz.\n\n✍️ MASHQ:\n1. \"Bu kitoblar meniki\" → ✅ These books are mine.\n2. \"U uylar eski\" → ✅ Those houses are old.\n3. \"Bular mening ruchkalarim\" → ✅ These are my pens.\n4. Naridagi ko'plik narsa uchun nima ishlatamiz? a) these b) those → ✅ b) those",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: These — bular (yaqin, ko'plik). Those — ular (uzoq, ko'plik). Ko'plikdagi ot va are bilan.\n\n🤖 TalkMe yakuniy: Zo'r! Endi these/those ni bilasiz! Ertaga: this/these — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_143",
            topicId = "topic_10_a2_grammar",
            orderIndex = 33,
            titleEnglish = "143-Dars: This vs These Practice",
            titleUzbek = "This va These — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun this va these ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n• This (birlik, yaqin) + birlikdagi ot + is.\n• These (ko'plik, yaqin) + ko'plikdagi ot + are.\n\n💬 MISOLLAR:\n• This is my book. → These are my books.\n• This apple is red. → These apples are red.\n• This child is my son. → These children are my sons.\n\n✍️ MASHQ:\n1. \"Bu mening kitobim\" → ✅ This is my book.\n2. \"Bular mening kitoblarim\" → ✅ These are my books.\n3. \"Bu olma qizil\" → ✅ This apple is red.\n4. To'g'ri tanlang: \"___ children are my sons.\" a) this b) these → ✅ b) these",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: This — birlik, yaqin (is). These — ko'plik, yaqin (are).\n\n🤖 TalkMe yakuniy: Zo'r! This/these mustahkamlandi! Ertaga: that/those — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_144",
            topicId = "topic_10_a2_grammar",
            orderIndex = 34,
            titleEnglish = "144-Dars: That vs Those Practice",
            titleUzbek = "That va Those — 5 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun that va those ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n• That (birlik, uzoq) + birlikdagi ot + is.\n• Those (ko'plik, uzoq) + ko'plikdagi ot + are.\n\n💬 MISOLLAR:\n• That is my house. → Those are my houses.\n• That car is red. → Those cars are red.\n• That man is my father. → Those men are my brothers.\n\n✍️ MASHQ:\n1. \"U mening uyim\" → ✅ That is my house.\n2. \"Ular mening uylarim\" → ✅ Those are my houses.\n3. \"U mashina qizil\" → ✅ That car is red.\n4. To'g'ri tanlang: \"___ men are my brothers.\" a) that b) those → ✅ b) those",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: That — birlik, uzoq (is). Those — ko'plik, uzoq (are).\n\n🤖 TalkMe yakuniy: Zo'r! That/those mustahkamlandi! Ertaga: myself — o'zim. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_145",
            topicId = "topic_10_a2_grammar",
            orderIndex = 35,
            titleEnglish = "145-Dars: Reflexive Pronoun — Myself",
            titleUzbek = "Myself — O'zim",
            objectiveUzbek = "🤖 TalkMe: Endi o'zlik olmoshlarini boshlaymiz: myself — \"o'zim\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nMyself (mayself) — \"o'zim\" / \"o'zini\" (faqat I bilan!).\nQachon ishlatamiz?\n1) Harakat egasining o'ziga qaytganda: I hurt myself. (Men o'zimni lat yedirdim.)\n2) Egani ta'kidlash uchun: I did it myself. (Men buni o'zim qildim.)\n\n💬 MISOLLAR:\n• I hurt myself. — Men o'zimni lat yedirdim.\n• I did it myself. — Men buni o'zim qildim.\n• I saw myself in the mirror. — Men oynada o'zimni ko'rdim.\n• I made this cake myself. — Men bu tortni o'zim qildim.\n\n✍️ MASHQ:\n1. \"Men o'zimni lat yedirdim\" → ✅ I hurt myself.\n2. \"Men buni o'zim qildim\" → ✅ I did it myself.\n3. \"Men oynada o'zimni ko'rdim\" → ✅ I saw myself in the mirror.\n4. \"Myself\" qaysi olmosh bilan ishlatiladi? a) I b) he → ✅ a) I",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Myself — \"o'zim\", faqat I bilan ishlatiladi.\n\n🤖 TalkMe yakuniy: Zo'r! Endi myself ni bilasiz! Ertaga: yourself, himself, herself. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_146",
            topicId = "topic_10_a2_grammar",
            orderIndex = 36,
            titleEnglish = "146-Dars: Reflexive Pronouns — Yourself, Himself, Herself",
            titleUzbek = "Yourself, Himself, Herself — O'zlik olmoshlari",
            objectiveUzbek = "🤖 TalkMe: Bugun qolgan o'zlik olmoshlarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• You → yourself (birlik: o'zing/o'zingiz), yourselves (ko'plik: o'zlaringiz).\n• He → himself (o'zi — erkak).\n• She → herself (o'zi — ayol).\n• It → itself | We → ourselves | They → themselves.\n\n💬 MISOLLAR:\n• You hurt yourself. — Siz o'zingizni lat yedirdingiz.\n• He hurt himself. — U o'zini lat yedirdi.\n• She did it herself. — U buni o'zi qildi.\n• Be careful! Don't cut yourself! — Ehtiyot bo'ling! O'zingizni kesib olmang!\n\n✍️ MASHQ:\n1. \"Siz o'zingizni lat yedirdingiz\" → ✅ You hurt yourself.\n2. \"U (erkak) o'zini lat yedirdi\" → ✅ He hurt himself.\n3. \"U (ayol) buni o'zi qildi\" → ✅ She did it herself.\n4. \"He\" qaysi o'zlik olmoshi bilan? a) himself b) herself → ✅ a) himself",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Yourself — o'zing, himself — o'zi (erkak), herself — o'zi (ayol).\n\n🤖 TalkMe yakuniy: Zo'r! Endi yourself/himself/herself ni bilasiz! Ertaga: each other — bir-biri. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_147",
            topicId = "topic_10_a2_grammar",
            orderIndex = 37,
            titleEnglish = "147-Dars: Reciprocal Pronoun — Each Other",
            titleUzbek = "Each other — Bir-biri",
            objectiveUzbek = "🤖 TalkMe: Bugun each other — \"bir-biri\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nEach other (ich aza) — \"bir-biri\" (birgalik olmoshi).\nIkki yoki undan ortiq shaxs bir-biriga harakat qilganda ishlatiladi:\n• They love each other. — Ular bir-birlarini sevishadi.\n• We help each other. — Biz bir-birimizga yordam beramiz.\n\n💬 MISOLLAR:\n• They love each other. — Ular bir-birlarini sevishadi.\n• We help each other. — Biz bir-birimizga yordam beramiz.\n• They often see each other. — Ular tez-tez bir-birlarini ko'rishadi.\n• We write to each other. — Biz bir-birimizga xat yozamiz.\n\n✍️ MASHQ:\n1. \"Ular bir-birlarini sevishadi\" → ✅ They love each other.\n2. \"Biz bir-birimizga yordam beramiz\" → ✅ We help each other.\n3. \"Ular tez-tez bir-birlarini ko'rishadi\" → ✅ They often see each other.\n4. \"Each other\" qanday ma'no beradi? a) bir-biri b) o'zim → ✅ a) bir-biri",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Each other — \"bir-biri\". Ikki yoki ko'p shaxs o'rtasidagi o'zaro harakat.\n\n🤖 TalkMe yakuniy: Zo'r! Endi each other ni bilasiz! Ertaga: A2 olmoshlar takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_148",
            topicId = "topic_10_a2_grammar",
            orderIndex = 38,
            titleEnglish = "148-Dars: A2 Pronouns Review",
            titleUzbek = "A2 Olmoshlar — Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun A2 olmoshlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA2 olmoshlar — hammasi:\n• This / that — birlik (yaqin / uzoq).\n• These / those — ko'plik (yaqin / uzoq).\n• Myself (I), yourself (you), himself (he), herself (she) — o'zlik olmoshlari.\n• Each other — bir-biri.\n\n💬 MISOLLAR:\n• This is my book. These are my books.\n• That is your car. Those are your cars.\n• I hurt myself. He hurt himself. She hurt herself.\n• They love each other.\n\n✍️ MASHQ:\n1. \"Bu mening kitobim\" → ✅ This is my book.\n2. \"Bular mening kitoblarim\" → ✅ These are my books.\n3. \"Men o'zimni lat yedirdim\" → ✅ I hurt myself.\n4. \"Ular bir-birlarini sevishadi\" → ✅ They love each other.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A2 olmoshlar tugadi! This/that, these/those, myself/yourself/himself/herself, each other.\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A2 olmoshlarni to'liq o'zlashtirdingiz! Ertaga: A2 ravishlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_149",
            topicId = "topic_10_a2_grammar",
            orderIndex = 39,
            titleEnglish = "149-Dars: A2 Adverbs — Types of Adverbs",
            titleUzbek = "A2 Ravish — Turlari (o'rin, payt, chastota, holat, daraja)",
            objectiveUzbek = "🤖 TalkMe: Bugun A2 ravishlarni boshlaymiz! Ravish turlarini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nRavish turlari:\n• O'rin-joy: here, there, inside, outside.\n• Payt: now, yesterday, tomorrow, before, after.\n• Chastota: always, often, usually, sometimes, seldom, never.\n• Holat: quickly, slowly, well, easily.\n• Daraja: very, too, so, enough, almost, nearly.\n\n💬 MISOLLAR:\n• Holat: He runs quickly. She speaks slowly. He works well.\n• Daraja: He is very clever. It is too hot. He is almost ready.\n\n✍️ MASHQ:\n1. \"Tez\" → ✅ quickly\n2. \"Sekin\" → ✅ slowly\n3. \"Yaxshi\" (ravish) → ✅ well\n4. \"Juda\" → ✅ very\n5. \"O'ta, haddan tashqari\" → ✅ too",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ravish turlari: o'rin, payt, chastota, holat (quickly, slowly, well), daraja (very, too, almost).\n\n🤖 TalkMe yakuniy: Zo'r! Ravish turlarini bilasiz! Ertaga: quickly, slowly, well. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_150",
            topicId = "topic_10_a2_grammar",
            orderIndex = 40,
            titleEnglish = "150-Dars: Adverbs of Manner — Quickly, Slowly, Well",
            titleUzbek = "A2 Ravish — Holat ravishlari: quickly, slowly, well",
            objectiveUzbek = "🤖 TalkMe: Bugun holat ravishlarini o'rganamiz: quickly, slowly, well! 🚀",
            explanationUzbek = "📖 QOIDA:\nKo'pchilik holat ravishlari sifat + -ly bilan yasaladi:\n• quick → quickly (tez), slow → slowly (sekin), easy → easily (oson), careful → carefully (ehtiyotkorlik bilan).\nMuhim istisno: Good (sifat) → well (ravish)!\n✅ He is a good student. ✅ He works well. (not: He works good.)\n\n💬 MISOLLAR:\n• He runs quickly. — U tez yuguradi.\n• She speaks slowly. — U sekin gapiradi.\n• He works well. — U yaxshi ishlaydi.\n• She sings beautifully. — U chiroyli kuylaydi.\n• He drives carefully. — U ehtiyotkorlik bilan mashina haydaydi.\n\n✍️ MASHQ:\n1. \"U tez yuguradi\" → ✅ He runs quickly.\n2. \"U sekin gapiradi\" → ✅ She speaks slowly.\n3. \"U yaxshi ishlaydi\" → ✅ He works well.\n4. \"Good\" sifatining ravishi nima? → ✅ well",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Holat ravishlari: sifat + -ly (quickly, slowly, carefully). Good → well.\n\n🤖 TalkMe yakuniy: Zo'r! Endi holat ravishlarini bilasiz! Ertaga: ravish darajalari — faster. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_151",
            topicId = "topic_10_a2_grammar",
            orderIndex = 41,
            titleEnglish = "151-Dars: Comparative Adverbs — Faster & More Quickly",
            titleUzbek = "A2 Ravish — Qiyosiy daraja: faster, more quickly",
            objectiveUzbek = "🤖 TalkMe: Bugun ravish darajalarini boshlaymiz: faster — \"tezroq\"! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bir bo'g'inli ravishlar: fast → faster (tezroq), hard → harder (qattiqroq), late → later (kechroq), early → earlier (ertaroq).\n• -ly bilan tugagan ravishlar: quickly → more quickly (tezroq), slowly → more slowly (sekinroq).\nQiyosiy daraja bilan than ishlatiladi: He runs faster than me.\n\n💬 MISOLLAR:\n• He runs faster than me. — U mendan tezroq yuguradi.\n• She works harder than him. — U undan qattiqroq ishlaydi.\n• I get up earlier than you. — Men sizdan erta turaman.\n• He speaks more quickly than her. — U undan tezroq gapiradi.\n\n✍️ MASHQ:\n1. \"U mendan tezroq yuguradi\" → ✅ He runs faster than me.\n2. \"Men sizdan erta turaman\" → ✅ I get up earlier than you.\n3. \"fast\" qiyosiy darajasi? → ✅ faster\n4. \"quickly\" qiyosiy darajasi? → ✅ more quickly",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ravish qiyosiy: -er (faster, harder, earlier) yoki more (more quickly) + than.\n\n🤖 TalkMe yakuniy: Zo'r! Endi ravish qiyosiy darajasini bilasiz! Ertaga: ravish orttirma — fastest. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_152",
            topicId = "topic_10_a2_grammar",
            orderIndex = 42,
            titleEnglish = "152-Dars: Superlative Adverbs — Fastest & Most Quickly",
            titleUzbek = "A2 Ravish — Orttirma daraja: fastest va Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun ravish orttirma darajasini o'rganamiz: fastest — \"eng tez\"! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bir bo'g'inli: fast → the fastest (eng tez), hard → the hardest (eng qattiq), early → the earliest (eng erta).\n• -ly bilan: quickly → the most quickly (eng tez).\n\n💬 MISOLLAR:\n• He runs the fastest of all. — U hammadan tez yuguradi.\n• She works the hardest. — U eng qattiq ishlaydi.\n• I get up the earliest. — Men eng erta turaman.\n• He speaks the most quickly. — U eng tez gapiradi.\n\n✍️ MASHQ:\n1. \"U hammadan tez yuguradi\" → ✅ He runs the fastest of all.\n2. \"U eng qattiq ishlaydi\" → ✅ She works the hardest.\n3. \"Men eng erta turaman\" → ✅ I get up the earliest.\n4. \"fast\" orttirma darajasi? → ✅ the fastest",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Ravish orttirma: the -est yoki the most. A2 ravishlar tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A2 ravishlarni to'liq o'zlashtirdingiz! Ertaga: A2 fe'llar — Present Continuous. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_153",
            topicId = "topic_10_a2_grammar",
            orderIndex = 43,
            titleEnglish = "153-Dars: Present Continuous — I am reading",
            titleUzbek = "Present Continuous: I am reading (Hozir davom zamon)",
            objectiveUzbek = "🤖 TalkMe: Endi A2 fe'llarni boshlaymiz! Present Continuous — \"hozir davom\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nPresent Continuous — hozir, gapirilayotgan paytda sodir bo'layotgan harakat.\nYasalishi: am / is / are + fe'l + -ing.\n• I → am reading (men o'qiyapman).\n• He / She / It → is working (u ishlayapti).\n• We / You / They → are playing (ular o'ynashmoqda).\n\n💬 MISOLLAR:\n• I am reading a book now. — Men hozir kitob o'qiyapman.\n• He is working in the garden. — U bog'da ishlayapti.\n• She is writing a letter. — U xat yozyapti.\n• We are playing football. — Biz futbol o'ynayapmiz.\n• Look! It is raining. — Qara! Yomg'ir yog'ayapti.\n\n✍️ MASHQ:\n1. \"Men o'qiyapman\" → ✅ I am reading.\n2. \"U ishlayapti\" → ✅ He is working.\n3. \"Ular o'ynashmoqda\" → ✅ They are playing.\n4. Present Continuous yasalishi? a) am/is/are + ing b) will + fe'l → ✅ a) am/is/are + ing",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Present Continuous = am/is/are + fe'l-ing. Hozir sodir bo'layotgan harakat.\n\n🤖 TalkMe yakuniy: Zo'r! Endi Present Continuous ni bilasiz! Ertaga: he is working — 3-shaxs. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_154",
            topicId = "topic_10_a2_grammar",
            orderIndex = 44,
            titleEnglish = "154-Dars: Present Continuous — He is working & -ing Rules",
            titleUzbek = "Present Continuous: He is working va -ing imlo qoidalari",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Continuous ning 3-shaxs shaklini va -ing qoidalarini mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nHe / She / It → is + fe'l + -ing.\n-ing qo'shish imlo qoidalari:\n1) O'qilmaydigan e tushadi: make → making, write → writing.\n2) Qisqa unlidan keyin bitta undosh ikkilanadi: sit → sitting, get → getting.\n3) -ie → -y: die → dying, lie → lying.\n\n💬 MISOLLAR:\n• He is working now. — U hozir ishlayapti.\n• She is reading a book. — U kitob o'qiyapti.\n• He is making a cake. — U tort qilyapti. (make → making)\n• She is writing a letter. — U xat yozyapti. (write → writing)\n• He is sitting on the chair. — U stulda o'tiribdi. (sit → sitting)\n\n✍️ MASHQ:\n1. \"U ishlayapti\" → ✅ He is working.\n2. \"U kitob o'qiyapti\" → ✅ She is reading a book.\n3. \"make\" ning -ing shakli? → ✅ making\n4. \"sit\" ning -ing shakli? → ✅ sitting",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: He/She/It → is + fe'l-ing. -ing qoidalari: e tushadi (making), undosh ikkilanadi (sitting), ie→y (lying).\n\n🤖 TalkMe yakuniy: Zo'r! Endi 3-shaxsni bilasiz! Ertaga: they are playing — ko'plik. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_155",
            topicId = "topic_10_a2_grammar",
            orderIndex = 45,
            titleEnglish = "155-Dars: Present Continuous — They are playing",
            titleUzbek = "Present Continuous: They are playing (Ko'plik)",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Continuous ning ko'plik shaklini mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nWe / You / They → are + fe'l + -ing:\n• We are playing (biz o'ynayapmiz), You are reading (siz o'qiyapsiz), They are working (ular ishlashmoqda).\n\n💬 MISOLLAR:\n• We are playing football. — Biz futbol o'ynayapmiz.\n• You are reading a book. — Siz kitob o'qiyapsiz.\n• They are working in the garden. — Ular bog'da ishlashmoqda.\n• You are learning English. — Siz ingliz tilini o'rganyapsiz.\n\n✍️ MASHQ:\n1. \"Biz futbol o'ynayapmiz\" → ✅ We are playing football.\n2. \"Siz kitob o'qiyapsiz\" → ✅ You are reading a book.\n3. \"Ular ishlashmoqda\" → ✅ They are working.\n4. Ko'plikda qaysi fe'l? a) is b) are → ✅ b) are",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: We/You/They → are + fe'l-ing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi ko'plikni bilasiz! Ertaga: Present Continuous — bo'lishsiz. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_156",
            topicId = "topic_10_a2_grammar",
            orderIndex = 46,
            titleEnglish = "156-Dars: Present Continuous — Negative",
            titleUzbek = "Present Continuous — Bo'lishsiz",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Continuous ning bo'lishsiz shaklini o'rganamiz: \"Men ... qilmayapman\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nBo'lishsiz: am / is / are + not + fe'l-ing.\n• I → am not reading (I'm not reading)\n• He / She / It → isn't working\n• We / You / They → aren't playing\n\n💬 MISOLLAR:\n• I am not reading now. — Men hozir o'qimayapman.\n• He isn't working today. — U bugun ishlamayapti.\n• She isn't sleeping. — U uxlamayapti.\n• They aren't watching TV. — Ular televizor ko'rishmayapti.\n\n✍️ MASHQ:\n1. \"Men hozir o'qimayapman\" → ✅ I am not reading now.\n2. \"U bugun ishlamayapti\" → ✅ He isn't working today.\n3. \"Ular televizor ko'rishmayapti\" → ✅ They aren't watching TV.\n4. Bo'lishsizda \"not\" qayerda? a) to be dan keyin b) fe'ldan keyin → ✅ a) to be dan keyin",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: am/is/are + not + fe'l-ing (I'm not, isn't, aren't).\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsizni bilasiz! Ertaga: Present Continuous — so'roq. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_157",
            topicId = "topic_10_a2_grammar",
            orderIndex = 47,
            titleEnglish = "157-Dars: Present Continuous — Questions",
            titleUzbek = "Present Continuous — So'roq",
            objectiveUzbek = "🤖 TalkMe: Bugun Present Continuous ning so'roq shaklini o'rganamiz: \"Siz ... qilyapsizmi?\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nSo'roq: Am / Is / Are + ega + fe'l-ing?\n• Am I reading? | Is he working? | Are they playing?\n• Javob: Yes, I am. / No, I'm not. Yes, he is. / No, he isn't. Yes, they are. / No, they aren't.\n\n💬 MISOLLAR:\n• Am I reading? — Men o'qiyapmanmi?\n• Is he working? — U ishlayaptimi?\n• Are they playing? — Ular o'ynashmoqdami?\n• Are you watching TV? — Siz televizor ko'ryapsizmi?\n\n✍️ MASHQ:\n1. \"Men o'qiyapmanmi?\" → ✅ Am I reading?\n2. \"U ishlayaptimi?\" → ✅ Is he working?\n3. \"Ular o'ynashmoqdami?\" → ✅ Are they playing?\n4. Javob bering: \"Is he working?\" → ✅ Yes, he is. / No, he isn't.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: So'roq: Am/Is/Are + ega + fe'l-ing? Javob: Yes, I am. / No, he isn't.\n\n🤖 TalkMe yakuniy: Zo'r! Endi so'roqni bilasiz! Ertaga: Past Simple — to'g'ri fe'llar (-ed). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_158",
            topicId = "topic_10_a2_grammar",
            orderIndex = 48,
            titleEnglish = "158-Dars: Past Simple — Regular Verbs (-ed: worked)",
            titleUzbek = "Past Simple — To'g'ri fe'llar (-ed: worked)",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Simple — \"o'tgan zamon\" ni boshlaymiz! To'g'ri fe'llar — -ed! 🚀",
            explanationUzbek = "📖 QOIDA:\nPast Simple — o'tgan zamonda sodir bo'lgan va tugagan harakat.\nTo'g'ri fe'llar: fe'l + -ed:\n• work → worked (ishladim), live → lived (yashadim), study → studied (o'rgandim), play → played (o'ynadim), stop → stopped (to'xtatdim).\nVaqt ko'rsatkichlari: yesterday, last week, two days ago, in 2020.\n\n💬 MISOLLAR:\n• I worked yesterday. — Men kecha ishladim.\n• She lived in London. — U Londonda yashadi.\n• He studied English. — U ingliz tilini o'rgandi.\n• We played football. — Biz futbol o'ynadik.\n• They stopped the car. — Ular mashinani to'xtatdilar.\n\n✍️ MASHQ:\n1. \"Men kecha ishladim\" → ✅ I worked yesterday.\n2. \"U Londonda yashadi\" → ✅ She lived in London.\n3. \"U ingliz tilini o'rgandi\" → ✅ He studied English.\n4. \"study\" ning Past Simple shakli? → ✅ studied",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Simple (to'g'ri): fe'l + -ed (worked, lived, studied, stopped). Vaqt: yesterday, last week, ago.\n\n🤖 TalkMe yakuniy: Zo'r! Endi to'g'ri fe'llarni bilasiz! Ertaga: -ed (lived) — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_159",
            topicId = "topic_10_a2_grammar",
            orderIndex = 49,
            titleEnglish = "159-Dars: Past Simple — Regular Verbs Practice",
            titleUzbek = "Past Simple — To'g'ri fe'llar (visited, watched, finished)",
            objectiveUzbek = "🤖 TalkMe: Bugun -ed ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n-ed qo'shimchasi: worked, lived, played, visited, wanted, helped, asked, watched, finished, stopped.\n\n💬 MISOLLAR:\n• I visited my friend yesterday. — Men kecha do'stimnikiga bordim.\n• She watched TV last night. — U kecha televizor ko'rdi.\n• He finished his work. — U ishini tugatdi.\n• We helped our mother. — Biz onamizga yordam berdik.\n• They stopped at the shop. — Ular do'konda to'xtashdi.\n\n✍️ MASHQ:\n1. \"Men kecha do'stimnikiga bordim\" → ✅ I visited my friend yesterday.\n2. \"U kecha televizor ko'rdi\" → ✅ She watched TV last night.\n3. \"U ishini tugatdi\" → ✅ He finished his work.\n4. \"stop\" ning Past Simple shakli? → ✅ stopped",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -ed: visited, watched, finished, helped, stopped, asked.\n\n🤖 TalkMe yakuniy: Zo'r! -ed mustahkamlandi! Ertaga: Past Simple — noto'g'ri fe'llar (went, saw, ate). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_160",
            topicId = "topic_10_a2_grammar",
            orderIndex = 50,
            titleEnglish = "160-Dars: Past Simple — Irregular Verbs (went, saw, ate)",
            titleUzbek = "Past Simple — Noto'g'ri fe'llar (went, saw, ate) va Partiya 8 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun Past Simple ning noto'g'ri fe'llarini o'rganamiz va Partiya 8 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nNoto'g'ri fe'llar -ed olmaydi — maxsus shaklga o'zgaradi:\n• go → went (bordim) | see → saw (ko'rdim) | eat → ate (yedim)\n• come → came (keldim) | have → had (bor edi) | do → did (qildim)\n• say → said (aytdim) | make → made (qildim) | take → took (oldim) | give → gave (berdim)\n\n💬 MISOLLAR:\n• I went to London. — Men Londonga bordim.\n• She saw a film. — U film ko'rdi.\n• He ate an apple. — U olma yedi.\n• We came home. — Biz uyga keldik.\n• She made a cake. — U tort qildi.\n\n✍️ MASHQ:\n1. \"Men Londonga bordim\" → ✅ I went to London.\n2. \"U film ko'rdi\" → ✅ She saw a film.\n3. \"U olma yedi\" → ✅ He ate an apple.\n4. \"go\", \"see\", \"eat\" Past Simple shakllari? → ✅ went, saw, ate",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Past Simple: to'g'ri fe'llar -ed, noto'g'ri fe'llar maxsus shakl (went, saw, ate, came, had, did, said, made). Partiya 8 tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz Partiya 8 (141–160 darslar) ni to'liq o'zlashtirdingiz! Keyingi darslar — Partiya 9: A2 Ko'rsatkichlar, Artikl, Predlog va Bog'lovchi! 👋",
            isUnlocked = false,
            isCompleted = false
        ),

        // =========================================================================
        // PARTIYA 9 — DARS 161–180 inside topic_10_a2_grammar (orderIndex 51..70)
        // =========================================================================
        LessonSectionEntity(
            id = "sec_10_161",
            topicId = "topic_10_a2_grammar",
            orderIndex = 51,
            titleEnglish = "161-Dars: Quantifiers — Some",
            titleUzbek = "Some — Bir qancha, bir nechta, ozroq",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun ko'rsatkichlarni boshlaymiz! Some — \"bir qancha\", \"bir nechta\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nSome (sam) — \"bir qancha\", \"bir nechta\", \"ozroq\".\nQachon ishlatamiz?\n1) Bo'lishli gaplarda: I have some books. / Give me some water.\n2) Taklif va iltimosda: Would you like some tea? / Can I have some milk?\nKo'plikdagi sanaladigan va sanalmaydigan otlar bilan ishlatiladi.\n\n💬 MISOLLAR:\n• I have some books. — Menda bir nechta kitob bor.\n• Give me some water, please. — Menga ozroq suv bering, iltimos.\n• Would you like some tea? — Choy ichasizmi?\n\n✍️ MASHQ:\n1. \"Menda bir nechta kitob bor\" → ✅ I have some books.\n2. \"Menga ozroq suv bering\" → ✅ Give me some water.\n3. Some qaysi gaplarda ko'proq ishlatiladi? a) bo'lishli b) bo'lishsiz → ✅ a) bo'lishli",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Some — \"bir qancha\", \"ozroq\". Bo'lishli gaplar hamda taklif va iltimosda.\n\n🤖 TalkMe yakuniy: Zo'r! Endi some ni bilasiz! Ertaga: any — bo'lishsiz va so'roqda. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_162",
            topicId = "topic_10_a2_grammar",
            orderIndex = 52,
            titleEnglish = "162-Dars: Quantifiers — Any",
            titleUzbek = "Any — Hech qanday, biror, istagan",
            objectiveUzbek = "🤖 TalkMe: Bugun any ni o'rganamiz: \"hech qanday\" yoki \"istagan\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nAny (eni):\n1) Bo'lishsiz gaplarda: \"hech qanday\" — I don't have any books.\n2) So'roq gaplarda: \"biror\" — Do you have any books?\n3) Bo'lishli gaplarda \"istagan\" ma'nosida: You can take any book.\n\n💬 MISOLLAR:\n• I don't have any books. — Menda hech qanday kitob yo'q.\n• Do you have any pens? — Sizda biror ruchka bormi?\n• There isn't any water in the bottle. — Shishada suv yo'q.\n• You can take any book. — Istagan kitobingizni oling.\n\n✍️ MASHQ:\n1. \"Menda hech qanday kitob yo'q\" → ✅ I don't have any books.\n2. \"Sizda biror ruchka bormi?\" → ✅ Do you have any pens.\n3. Any qaysi gaplarda ko'proq ishlatiladi? a) bo'lishsiz/so'roq b) bo'lishli → ✅ a) bo'lishsiz/so'roq",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Any — bo'lishsiz va so'roqda \"hech/biror\", bo'lishlida \"istagan\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi any ni bilasiz! Ertaga: some/any — 10 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_163",
            topicId = "topic_10_a2_grammar",
            orderIndex = 53,
            titleEnglish = "163-Dars: Some vs Any — 10 Examples",
            titleUzbek = "Some va Any — 10 ta misol",
            objectiveUzbek = "🤖 TalkMe: Bugun some va any ni mustahkamlaymiz! 10 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Some — bo'lishli, taklif, iltimos (Would you like some tea?).\n• Any — bo'lishsiz, so'roq, \"istagan\" (I don't have any books. Do you have any books?).\n\n💬 MISOLLAR:\n• I have some books. / I don't have any books. / Do you have any books?\n• She has some money. / She doesn't have any money.\n• Would you like some water? / Can I have some milk?\n\n✍️ MASHQ:\n1. \"Menda bir nechta kitob bor\" → ✅ I have some books.\n2. \"Menda hech qanday kitob yo'q\" → ✅ I don't have any books.\n3. \"Choy ichasizmi?\" (taklif) → ✅ Would you like some tea?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Some — bo'lishli/taklif. Any — bo'lishsiz/so'roq/istagan.\n\n🤖 TalkMe yakuniy: Zo'r! Some/any mustahkamlandi! Ertaga: no — hech qanday. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_164",
            topicId = "topic_10_a2_grammar",
            orderIndex = 54,
            titleEnglish = "164-Dars: Quantifiers — No",
            titleUzbek = "No — Hech qanday (not + any)",
            objectiveUzbek = "🤖 TalkMe: Bugun no ni o'rganamiz: \"hech qanday\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nNo (nou) — ot oldida \"hech qanday\":\n• I have no books. = I don't have any books.\n• She has no money. = She doesn't have any money.\nMuhim: \"No\" bilan gapda boshqa inkor ishlatilmaydi! ✅ I have no books. (not: I don't have no books.)\n\n💬 MISOLLAR:\n• I have no books. — Menda hech qanday kitob yo'q.\n• She has no money. — Uning hech qanday puli yo'q.\n• There is no water in the bottle. — Shishada suv yo'q.\n• He has no time. — Uning vaqti yo'q.\n\n✍️ MASHQ:\n1. \"Menda hech qanday kitob yo'q\" → ✅ I have no books.\n2. \"Uning vaqti yo'q\" → ✅ He has no time.\n3. Xatoni toping: \"I don't have no books.\" → ✅ I have no books.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: No — \"hech qanday\" (no = not + any). Boshqa inkor qo'shilmaydi!\n\n🤖 TalkMe yakuniy: Zo'r! Endi no ni bilasiz! Ertaga: much — ko'p (sanalmaydigan). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_165",
            topicId = "topic_10_a2_grammar",
            orderIndex = 55,
            titleEnglish = "165-Dars: Quantifiers — Much (Uncountable)",
            titleUzbek = "Much — Ko'p (sanalmaydigan otlar bilan)",
            objectiveUzbek = "🤖 TalkMe: Bugun much ni o'rganamiz: \"ko'p\" — sanalmaydigan otlar bilan! 🚀",
            explanationUzbek = "📖 QOIDA:\nMuch (mach) — \"ko'p\", faqat sanalmaydigan otlar bilan:\n• much water (ko'p suv), much money (ko'p pul), much work (ko'p ish), much time (ko'p vaqt).\nSo'roq va bo'lishsizda ko'proq ishlatiladi; bo'lishlida ko'pincha a lot of ishlatiladi.\n\n💬 MISOLLAR:\n• How much water do you need? — Qancha suv kerak?\n• I don't have much time. — Mening ko'p vaqtim yo'q.\n• There isn't much milk. — Ko'p sut yo'q.\n• I have a lot of work. — Menda ko'p ish bor.\n\n✍️ MASHQ:\n1. \"Qancha suv kerak?\" → ✅ How much water do you need?\n2. \"Mening ko'p vaqtim yo'q\" → ✅ I don't have much time.\n3. Much qanday otlar bilan? a) sanaladigan b) sanalmaydigan → ✅ b) sanalmaydigan",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Much — \"ko'p\", sanalmaydigan otlar bilan (much water, much time, much money).\n\n🤖 TalkMe yakuniy: Zo'r! Endi much ni bilasiz! Ertaga: many — ko'p (sanaladigan). 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_166",
            topicId = "topic_10_a2_grammar",
            orderIndex = 56,
            titleEnglish = "166-Dars: Quantifiers — Many (Countable) & Review",
            titleUzbek = "Many — Ko'p (sanaladigan otlar) va Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun many ni o'rganamiz va barcha ko'rsatkichlarni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nMany (meni) — \"ko'p\", faqat ko'plikdagi sanaladigan otlar bilan:\n• many books (ko'p kitoblar), many students (ko'p talabalar), many cars (ko'p mashinalar).\nTakrorlash: some, any, no, much (sanalmaydigan), many (sanaladigan).\n\n💬 MISOLLAR:\n• How many books do you have? — Sizda nechta kitob bor?\n• I don't have many books. — Mening ko'p kitobim yo'q.\n• There are many students in the class. — Sinfda ko'p talabalar bor.\n\n✍️ MASHQ:\n1. \"Sizda nechta kitob bor?\" → ✅ How many books do you have?\n2. \"Mening ko'p kitobim yo'q\" → ✅ I don't have many books.\n3. Many qanday otlar bilan? a) sanaladigan b) sanalmaydigan → ✅ a) sanaladigan",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Many — \"ko'p\", sanaladigan ko'plik otlar bilan. A2 ko'rsatkichlar tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A2 ko'rsatkichlarni to'liq o'zlashtirdingiz! Ertaga: A2 artikl. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_167",
            topicId = "topic_10_a2_grammar",
            orderIndex = 57,
            titleEnglish = "167-Dars: A2 Articles — A/An with Professions",
            titleUzbek = "A2 Artikl — A/An: kasb bilan",
            objectiveUzbek = "🤖 TalkMe: Endi A2 artiklni boshlaymiz! A/an — kasb bilan! 🚀",
            explanationUzbek = "📖 QOIDA:\nBirlikdagi kasb va mansab oldida har doim a/an ishlatiladi:\n• He is a doctor. — U shifokor.\n• She is an engineer. — U muhandis.\n• I am a teacher. — Men o'qituvchiman.\n\n💬 MISOLLAR:\n• He is a doctor. | She is a teacher. | I am a student.\n• He is an engineer. | She is an actress. | My father is a driver.\n\n✍️ MASHQ:\n1. \"U shifokor\" → ✅ He is a doctor.\n2. \"U muhandis\" → ✅ He is an engineer.\n3. Kasb oldida nima ishlatiladi? a) a/an b) the → ✅ a) a/an",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A/an — birlikdagi kasb oldida (a doctor, an engineer).\n\n🤖 TalkMe yakuniy: Zo'r! Endi kasb bilan a/an ni bilasiz! Ertaga: a — \"bir\" ma'nosi. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_168",
            topicId = "topic_10_a2_grammar",
            orderIndex = 58,
            titleEnglish = "168-Dars: A2 Articles — A/An meaning One",
            titleUzbek = "A2 Artikl — A: \"bir\" ma'nosi",
            objectiveUzbek = "🤖 TalkMe: Bugun a ning \"bir\" ma'nosini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA/an — \"bir\" (one) ma'nosida:\n• I need a pen. (Menga bitta ruchka kerak.)\n• A hundred — bir yuz (100). | A thousand — bir ming (1,000).\n\n💬 MISOLLAR:\n• I need a pen. — Menga bitta ruchka kerak.\n• He ate an apple. — U bitta olma yedi.\n• A hundred students came. — Yuzta talaba keldi.\n\n✍️ MASHQ:\n1. \"Menga bitta ruchka kerak\" → ✅ I need a pen.\n2. \"Yuzta talaba keldi\" → ✅ A hundred students came.\n3. \"A\" qanday ma'no beradi? a) bir b) ko'p → ✅ a) bir",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A/an — \"bir\" ma'nosi (I need a pen, a hundred, a thousand).\n\n🤖 TalkMe yakuniy: Zo'r! Endi a ning \"bir\" ma'nosini bilasiz! Ertaga: the — orttirma va tartib. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_169",
            topicId = "topic_10_a2_grammar",
            orderIndex = 59,
            titleEnglish = "169-Dars: A2 Articles — The with Superlatives & Ordinals",
            titleUzbek = "A2 Artikl — The: orttirma daraja va tartib sonlar",
            objectiveUzbek = "🤖 TalkMe: Bugun the ni orttirma daraja va tartib sonlar bilan o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• The — orttirma daraja oldida: the best, the biggest, the most interesting.\n• The — tartib sonlar oldida: the first, the second, the third.\n\n💬 MISOLLAR:\n• He is the best student. — U eng yaxshi talaba.\n• This is the biggest house. — Bu eng katta uy.\n• The first lesson is easy. — Birinchi dars oson.\n• February is the second month. — Fevral ikkinchi oy.\n\n✍️ MASHQ:\n1. \"U eng yaxshi talaba\" → ✅ He is the best student.\n2. \"Birinchi dars oson\" → ✅ The first lesson is easy.\n3. Orttirma va tartib sonlar oldida nima qo'yiladi? a) a b) the → ✅ b) the",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The — orttirma daraja va tartib sonlar oldida (the best, the first).\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: the — geografik nomlar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_170",
            topicId = "topic_10_a2_grammar",
            orderIndex = 60,
            titleEnglish = "170-Dars: A2 Articles — Geographical Names & Review",
            titleUzbek = "A2 Artikl — The: geografik nomlar va Takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun the ni geografik nomlar bilan o'rganamiz va hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nThe bilan ishlatiladigan geografik nomlar:\n1) Daryolar: the Nile, the Amazon.\n2) Dengizlar va okeanlar: the Pacific, the Black Sea.\n3) Tog' tizmalari: the Alps.\n4) Orollar guruhi: the British Isles.\nShahar, davlat (ko'pchiligi) va ko'cha nomlari oldida the yo'q: Tashkent, Uzbekistan, London.\n\n💬 MISOLLAR:\n• The Nile is long. — Nil uzun.\n• The Pacific is big. — Tinch okeani katta.\n• The Alps are high. — Alp tog'lari baland.\n• I live in Tashkent. — Men Toshkentda yashayman. (the yo'q!)\n\n✍️ MASHQ:\n1. \"Nil uzun\" → ✅ The Nile is long.\n2. \"Tinch okeani katta\" → ✅ The Pacific is big.\n3. \"Men Toshkentda yashayman\" → ✅ I live in Tashkent.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: The — daryolar, dengiz/okeanlar, tog' tizmalari, orollar guruhi. Shahar/davlat oldida the yo'q. A2 artikl tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A2 artiklni to'liq o'zlashtirdingiz! Ertaga: A2 predloglar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_171",
            topicId = "topic_10_a2_grammar",
            orderIndex = 61,
            titleEnglish = "171-Dars: A2 Prepositions — Without",
            titleUzbek = "A2 Predlog — Without (-siz, -masdan)",
            objectiveUzbek = "🤖 TalkMe: Endi A2 predloglarni boshlaymiz! Without — \"-siz\"! 🚀",
            explanationUzbek = "📖 QOIDA:\nWithout (wizaut) — \"-siz\", \"-masdan\" (with ning antonimi).\n• Ot/olmosh bilan: without you (sizsiz), without his bag (sumkasiz).\n• Gerund (-ing) bilan: without saying goodbye (xayrlashmasdan).\n\n💬 MISOLLAR:\n• I can't live without you. — Men sizsiz yashay olmayman.\n• He went out without his hat. — U shlapasiz chiqdi.\n• She left without saying goodbye. — U xayrlashmasdan ketdi.\n\n✍️ MASHQ:\n1. \"Men sizsiz yashay olmayman\" → ✅ I can't live without you.\n2. \"U xayrlashmasdan ketdi\" → ✅ She left without saying goodbye.\n3. Without qanday ma'no beradi? a) bilan b) -siz → ✅ b) -siz",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Without — \"-siz\", \"-masdan\" (with ning teskarisi).\n\n🤖 TalkMe yakuniy: Zo'r! Endi without ni bilasiz! Ertaga: about — haqida. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_172",
            topicId = "topic_10_a2_grammar",
            orderIndex = 62,
            titleEnglish = "172-Dars: A2 Prepositions — About",
            titleUzbek = "A2 Predlog — About (haqida, taxminan)",
            objectiveUzbek = "🤖 TalkMe: Bugun about — \"haqida\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nAbout (abaut) — \"haqida\", \"to'g'risida\" yoki \"taxminan\":\n• a book about animals (hayvonlar haqida kitob), talk about (haqida gaplashmoq), think about (haqida o'ylamoq).\n• about 5 o'clock (soat 5 atrofida).\n\n💬 MISOLLAR:\n• I read a book about animals. — Men hayvonlar haqida kitob o'qidim.\n• We talked about the film. — Biz film haqida gaplashdik.\n• She is thinking about her family. — U oilasi haqida o'ylayapti.\n\n✍️ MASHQ:\n1. \"Men hayvonlar haqida kitob o'qidim\" → ✅ I read a book about animals.\n2. \"Biz film haqida gaplashdik\" → ✅ We talked about the film.\n3. About qanday ma'no beradi? a) haqida b) ustida → ✅ a) haqida",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: About — \"haqida\", \"to'g'risida\" yoki \"taxminan\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi about ni bilasiz! Ertaga: of — -ning. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_173",
            topicId = "topic_10_a2_grammar",
            orderIndex = 63,
            titleEnglish = "173-Dars: A2 Prepositions — Of",
            titleUzbek = "A2 Predlog — Of (-ning, miqdor va tarkib)",
            objectiveUzbek = "🤖 TalkMe: Bugun of — \"-ning\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nOf (ov) — \"-ning\":\n1) Egalik (jonsiz otlar): the leg of the table.\n2) Miqdor: a cup of tea (bir piyola choy), a glass of water (bir stakan suv).\n3) Mavzu/Tarkib: a map of Uzbekistan, a group of students.\n\n💬 MISOLLAR:\n• A cup of tea, please. — Bir piyola choy, iltimos.\n• A glass of water. — Bir stakan suv.\n• A map of Uzbekistan. — O'zbekiston xaritasi.\n• A group of students. — Talabalar guruhi.\n\n✍️ MASHQ:\n1. \"Bir piyola choy\" → ✅ a cup of tea\n2. \"Bir stakan suv\" → ✅ a glass of water\n3. \"O'zbekiston xaritasi\" → ✅ a map of Uzbekistan",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Of — \"-ning\" (egalik, miqdor: a cup of tea, tarkib: a group of students).\n\n🤖 TalkMe yakuniy: Zo'r! Endi of ni bilasiz! Ertaga: by — tomonidan/bilan. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_174",
            topicId = "topic_10_a2_grammar",
            orderIndex = 64,
            titleEnglish = "174-Dars: A2 Prepositions — By",
            titleUzbek = "A2 Predlog — By (tomonidan, transportda, -gacha)",
            objectiveUzbek = "🤖 TalkMe: Bugun by — \"tomonidan\" va \"bilan\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBy (bay):\n1) Tomonidan (muallif/bajaruvchi): The book was written by Pushkin.\n2) Transportda (artiklsiz!): by bus (avtobusda), by car (mashinada), by train (poyezdda).\n3) Vaqt (-gacha): by 5 o'clock (soat 5 gacha).\n\n💬 MISOLLAR:\n• The book was written by Pushkin. — Kitob Pushkin tomonidan yozilgan.\n• I go to school by bus. — Men maktabga avtobusda boraman.\n• We will finish by 5 o'clock. — Biz soat 5 gacha tugatamiz.\n\n✍️ MASHQ:\n1. \"Kitob Pushkin tomonidan yozilgan\" → ✅ The book was written by Pushkin.\n2. \"Men maktabga avtobusda boraman\" → ✅ I go to school by bus.\n3. \"By bus\" nima degani? → ✅ avtobusda",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: By — tomonidan (by Pushkin), transport (by bus, by car), vaqt (-gacha: by 5 o'clock).\n\n🤖 TalkMe yakuniy: Zo'r! Endi by ni bilasiz! Ertaga: after/before — keyin/oldin. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_175",
            topicId = "topic_10_a2_grammar",
            orderIndex = 65,
            titleEnglish = "175-Dars: A2 Prepositions — After & Before",
            titleUzbek = "A2 Predlog — After (keyin) va Before (oldin)",
            objectiveUzbek = "🤖 TalkMe: Bugun after va before ni o'rganamiz: \"keyin\" va \"oldin\"! 🚀",
            explanationUzbek = "📖 QOIDA:\n• After (afta) — \"keyin\", \"so'ng\": after dinner (tushlikdan keyin), after 5 o'clock.\n• Before (bifo) — \"oldin\", \"avval\": before dinner (tushlikdan oldin), before 5 o'clock.\n\n💬 MISOLLAR:\n• After dinner, we went for a walk. — Tushlikdan keyin biz sayrga chiqdik.\n• Before dinner, wash your hands. — Tushlikdan oldin qo'lingizni yuving.\n• I will call you after 5 o'clock. — Men sizga soat 5 dan keyin qo'ng'iroq qilaman.\n\n✍️ MASHQ:\n1. \"Tushlikdan keyin\" → ✅ after dinner\n2. \"Tushlikdan oldin\" → ✅ before dinner\n3. After qanday ma'no beradi? a) keyin b) oldin → ✅ a) keyin",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: After — keyin. Before — oldin.\n\n🤖 TalkMe yakuniy: Zo'r! Endi after/before ni bilasiz! Ertaga: between/among — o'rtasida. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_176",
            topicId = "topic_10_a2_grammar",
            orderIndex = 66,
            titleEnglish = "176-Dars: A2 Prepositions — Between & Among",
            titleUzbek = "A2 Predlog — Between (ikki o'rtasida) va Among (ko'p orasida)",
            objectiveUzbek = "🤖 TalkMe: Bugun between va among ni o'rganamiz va hammasini takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Between (bitvin) — \"o'rtasida\" (ikki shaxs yoki narsa o'rtasida): between the two chairs, between you and me.\n• Among (amang) — \"orasida\" (uch va undan ko'p shaxs/narsa orasida): among the students, among friends.\n\n💬 MISOLLAR:\n• The ball is between the two chairs. — To'p ikki stul o'rtasida.\n• The house is between the school and the park. — Uy maktab va park o'rtasida.\n• He is among the best students. — U eng yaxshi talabalar orasida.\n• She is popular among her friends. — U do'stlari orasida mashhur.\n\n✍️ MASHQ:\n1. \"To'p ikki stul o'rtasida\" → ✅ The ball is between the two chairs.\n2. \"U eng yaxshi talabalar orasida\" → ✅ He is among the best students.\n3. Between qachon ishlatiladi? a) ikki o'rtasida b) ko'p orasida → ✅ a) ikki o'rtasida",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Between — ikki o'rtasida. Among — ko'p orasida. A2 predloglar tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz A2 predloglarni to'liq o'zlashtirdingiz! Ertaga: A2 bog'lovchilar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_177",
            topicId = "topic_10_a2_grammar",
            orderIndex = 67,
            titleEnglish = "177-Dars: A2 Conjunctions — And, But, Or, So Review",
            titleUzbek = "A2 Bog'lovchi — and, but, or, so (Takrorlash)",
            objectiveUzbek = "🤖 TalkMe: Endi A2 bog'lovchilarni boshlaymiz! And, but, or, so ni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• And — \"va\" (qo'shish).\n• But — \"lekin\" (qarama-qarshilik).\n• Or — \"yoki\" (tanlov).\n• So — \"shuning uchun\" (natija).\n\n💬 MISOLLAR:\n• I have a pen and a book.\n• I am tired, but happy.\n• Tea or coffee?\n• It was cold, so I put on a coat.\n\n✍️ MASHQ:\n1. \"Menda ruchka va kitob bor\" → ✅ I have a pen and a book.\n2. \"Men charchadim, lekin baxtliman\" → ✅ I am tired, but happy.\n3. \"Sovuq edi, shuning uchun palto kiydim\" → ✅ It was cold, so I put on a coat.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: And — va, But — lekin, Or — yoki, So — shuning uchun.\n\n🤖 TalkMe yakuniy: Zo'r! Ertaga: because — chunki. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_178",
            topicId = "topic_10_a2_grammar",
            orderIndex = 68,
            titleEnglish = "178-Dars: A2 Conjunctions — Because",
            titleUzbek = "A2 Bog'lovchi — Because (chunki)",
            objectiveUzbek = "🤖 TalkMe: Bugun because — \"chunki\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBecause (bikoz) — \"chunki\" (sabab bog'lovchisi).\nTuzilish: [Natija] + because + [sabab].\nFarqi: Because — sababni ko'rsatadi (I stayed because it rained); So — natijani ko'rsatadi (It rained, so I stayed).\n\n💬 MISOLLAR:\n• I stayed at home because it was raining. — Uyda qoldim, chunki yomg'ir yog'ayotgan edi.\n• She was late because she missed the bus. — U kechikdi, chunki avtobusga kech qoldi.\n• He is happy because he passed the exam. — U baxtli, chunki imtihondan o'tdi.\n\n✍️ MASHQ:\n1. \"Uyda qoldim, chunki yomg'ir yog'ayotgan edi\" → ✅ I stayed at home because it was raining.\n2. \"U baxtli, chunki imtihondan o'tdi\" → ✅ He is happy because he passed the exam.\n3. Because qanday ma'no beradi? a) chunki b) shuning uchun → ✅ a) chunki",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Because — \"chunki\" (sabab bog'lovchisi).\n\n🤖 TalkMe yakuniy: Zo'r! Endi because ni bilasiz! Ertaga: because — 5 ta misol. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_179",
            topicId = "topic_10_a2_grammar",
            orderIndex = 69,
            titleEnglish = "179-Dars: Because — 5 Examples",
            titleUzbek = "A2 Bog'lovchi — Because (5 ta misol)",
            objectiveUzbek = "🤖 TalkMe: Bugun because ni mustahkamlaymiz! 5 ta misol! 🚀",
            explanationUzbek = "📖 QOIDA:\nBecause gap o'rtasida ham, gap boshida ham kelishi mumkin:\n• I stayed because it rained.\n• Because it rained, I stayed.\n\n💬 MISOLLAR:\n• I am tired because I worked all day. — Men charchadim, chunki kun bo'yi ishladim.\n• She is happy because it is her birthday. — U baxtli, chunki bugun uning tug'ilgan kuni.\n• Because it is cold, I wear a coat. — Sovuq bo'lgani uchun palto kiyaman.\n\n✍️ MASHQ:\n1. \"Men charchadim, chunki kun bo'yi ishladim\" → ✅ I am tired because I worked all day.\n2. \"Sovuq bo'lgani uchun palto kiyaman\" → ✅ Because it is cold, I wear a coat.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Because — chunki (gap o'rtasida va boshida).\n\n🤖 TalkMe yakuniy: Zo'r! Because mustahkamlandi! Ertaga: Partiya 9 yakuni. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_10_180",
            topicId = "topic_10_a2_grammar",
            orderIndex = 70,
            titleEnglish = "180-Dars: A2 Conjunctions & Part 9 Review",
            titleUzbek = "A2 Bog'lovchilar — Takrorlash va Partiya 9 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun A2 bog'lovchilarni takrorlaymiz va Partiya 9 ni yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA2 bog'lovchilar:\n• And — va | But — lekin | Or — yoki | So — shuning uchun | Because — chunki.\n\n💬 MISOLLAR:\n• And: I have a pen and a book.\n• But: I am tired, but happy.\n• Or: Tea or coffee?\n• So: It was cold, so I stayed.\n• Because: I stayed because it was cold.\n\n✍️ MASHQ:\n1. \"Menda ruchka va kitob bor\" → ✅ I have a pen and a book.\n2. \"Sovuq edi, shuning uchun uyda qoldim\" → ✅ It was cold, so I stayed.\n3. \"Uyda qoldim, chunki sovuq edi\" → ✅ I stayed because it was cold.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: And, but, or, so, because. Partiya 9 (161–180 darslar) tugadi!\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz Partiya 9 ni to'liq tugatdingiz! Keyingi darslar — Partiya 10: A2 Gap bo'laklari, Qo'shma gaplar hamda B1 Son va Otlar! 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 8 & 9 VOCABULARY (DARS 141–180)
        // =========================================================================
        VocabularyEntity("voc_10_myself", "topic_10_a2_grammar", "sec_10_145", "Myself", "o'zim", "I did it myself.", "Men buni o'zim qildim.", isStudied = false),
        VocabularyEntity("voc_10_yourself", "topic_10_a2_grammar", "sec_10_146", "Yourself", "o'zing, o'zingiz", "Be careful, don't hurt yourself!", "Ehtiyot bo'ling, o'zingizni lat yedirmang!", isStudied = false),
        VocabularyEntity("voc_10_himself", "topic_10_a2_grammar", "sec_10_146", "Himself", "o'zi (erkak)", "He did it himself.", "U buni o'zi qildi.", isStudied = false),
        VocabularyEntity("voc_10_herself", "topic_10_a2_grammar", "sec_10_146", "Herself", "o'zi (ayol)", "She made the cake herself.", "U tortni o'zi qildi.", isStudied = false),
        VocabularyEntity("voc_10_each_other", "topic_10_a2_grammar", "sec_10_147", "Each other", "bir-biri, bir-birini", "We help each other.", "Biz bir-birimizga yordam beramiz.", isStudied = false),
        VocabularyEntity("voc_10_quickly", "topic_10_a2_grammar", "sec_10_150", "Quickly", "tez (ravish)", "He runs quickly.", "U tez yuguradi.", isStudied = false),
        VocabularyEntity("voc_10_slowly", "topic_10_a2_grammar", "sec_10_150", "Slowly", "sekin (ravish)", "She speaks slowly.", "U sekin gapiradi.", isStudied = false),
        VocabularyEntity("voc_10_well", "topic_10_a2_grammar", "sec_10_150", "Well", "yaxshi (ravish)", "He works well.", "U yaxshi ishlaydi.", isStudied = false),
        VocabularyEntity("voc_10_carefully", "topic_10_a2_grammar", "sec_10_150", "Carefully", "ehtiyotkorlik bilan", "He drives carefully.", "U ehtiyotkorlik bilan haydaydi.", isStudied = false),
        VocabularyEntity("voc_10_faster", "topic_10_a2_grammar", "sec_10_151", "Faster", "tezroq", "He runs faster than me.", "U mendan tezroq yuguradi.", isStudied = false),
        VocabularyEntity("voc_10_went", "topic_10_a2_grammar", "sec_10_160", "Went", "bordi (go o'tgan zamon shakli)", "I went to London.", "Men Londonga bordim.", isStudied = false),
        VocabularyEntity("voc_10_saw", "topic_10_a2_grammar", "sec_10_160", "Saw", "ko'rdi (see o'tgan zamon shakli)", "She saw a film.", "U film ko'rdi.", isStudied = false),
        VocabularyEntity("voc_10_ate", "topic_10_a2_grammar", "sec_10_160", "Ate", "yedi (eat o'tgan zamon shakli)", "He ate an apple.", "U olma yedi.", isStudied = false),
        VocabularyEntity("voc_10_some", "topic_10_a2_grammar", "sec_10_161", "Some", "bir qancha, bir nechta, ozroq", "I have some books.", "Menda bir nechta kitob bor.", isStudied = false),
        VocabularyEntity("voc_10_any", "topic_10_a2_grammar", "sec_10_162", "Any", "hech qanday, biror, istagan", "Do you have any pens?", "Sizda biror ruchka bormi?", isStudied = false),
        VocabularyEntity("voc_10_much", "topic_10_a2_grammar", "sec_10_165", "Much", "ko'p (sanalmaydigan otlar bilan)", "I don't have much time.", "Mening ko'p vaqtim yo'q.", isStudied = false),
        VocabularyEntity("voc_10_many", "topic_10_a2_grammar", "sec_10_166", "Many", "ko'p (sanaladigan otlar bilan)", "How many books do you have?", "Sizda nechta kitob bor?", isStudied = false),
        VocabularyEntity("voc_10_without", "topic_10_a2_grammar", "sec_10_171", "Without", "-siz, -masdan", "She left without saying goodbye.", "U xayrlashmasdan ketdi.", isStudied = false),
        VocabularyEntity("voc_10_about", "topic_10_a2_grammar", "sec_10_172", "About", "haqida, to'g'risida", "I read a book about animals.", "Men hayvonlar haqida kitob o'qidim.", isStudied = false),
        VocabularyEntity("voc_10_between", "topic_10_a2_grammar", "sec_10_176", "Between", "o'rtasida (ikki narsa o'rtasida)", "The ball is between the two chairs.", "To'p ikki stul o'rtasida.", isStudied = false),
        VocabularyEntity("voc_10_among", "topic_10_a2_grammar", "sec_10_176", "Among", "orasida (ko'p narsa orasida)", "He is among the best students.", "U eng yaxshi talabalar orasida.", isStudied = false),
        VocabularyEntity("voc_10_because", "topic_10_a2_grammar", "sec_10_178", "Because", "chunki", "I stayed at home because it was raining.", "Uyda qoldim, chunki yomg'ir yog'ayotgan edi.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "gram_10_145",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_145",
            titleUzbek = "O'zlik olmoshlari (myself, yourself, himself, herself) va Each other",
            explanationUzbek = "O'zlik olmoshlari harakat egasining o'ziga qaytganda yoki egani ta'kidlaganda ishlatiladi (I did it myself). Each other — ikki yoki undan ko'p shaxsning bir-biriga harakati (They help each other).",
            exampleEnglish = "I did it myself. She hurt herself. We help each other.",
            exampleUzbek = "Men buni o'zim qildim. U o'zini lat yedirdi. Biz bir-birimizga yordam beramiz.",
            associatedVocabularyIdsCsv = "voc_10_myself,voc_10_yourself,voc_10_himself,voc_10_herself,voc_10_each_other",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_10_153",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_153",
            titleUzbek = "Present Continuous (am/is/are + V-ing) va Past Simple (-ed / V2)",
            explanationUzbek = "Present Continuous hozir sodir bo'layotgan harakatni bildiradi: I am reading, He isn't working, Are they playing? Past Simple o'tgan zamonda tugagan harakatni bildiradi: worked, lived, went, saw, ate.",
            exampleEnglish = "I am reading a book now. Yesterday I went to London and saw a film.",
            exampleUzbek = "Men hozir kitob o'qiyapman. Kecha men Londonga bordim va film ko'rdim.",
            associatedVocabularyIdsCsv = "voc_10_went,voc_10_saw,voc_10_ate",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_10_161",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_161",
            titleUzbek = "A2 Ko'rsatkichlar (some, any, no, much, many), Predloglar (without, between, among) va Because",
            explanationUzbek = "Some — bo'lishli va taklifda; Any — bo'lishsiz va so'roqda; No = not any (boshqa inkor qo'shilmaydi). Much — sanalmaydigan, Many — sanaladigan ko'plik. Between — ikki o'rtasida, Among — ko'p orasida. Because — chunki (sabab).",
            exampleEnglish = "I have some books, but I don't have much time because I work hard.",
            exampleUzbek = "Menda bir nechta kitob bor, lekin ko'p vaqtim yo'q, chunki qattiq ishlayman.",
            associatedVocabularyIdsCsv = "voc_10_some,voc_10_any,voc_10_much,voc_10_many,voc_10_without,voc_10_between,voc_10_among,voc_10_because",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_10_147",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_147",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Men buni o'zim qildim\" va \"Biz bir-birimizga yordam beramiz\" gaplarini toping:",
            subPromptText = "145–147 Darslar: myself va each other",
            audioTextToSpeak = "I did it myself. We help each other.",
            optionsPipeSeparated = "I did it myself. We help each other.|I did it himself. We help myself.|I did it each other. We help yourself.|I did it me. We help themselves.",
            correctAnswer = "I did it myself. We help each other.",
            explanationUzbek = "I bilan \"myself\" (o'zim), o'zaro harakat uchun \"each other\" (bir-birimizga) ishlatiladi.",
            vocabularyIdsCsv = "voc_10_myself,voc_10_each_other",
            grammarRuleIdsCsv = "gram_10_145"
        ),
        ExerciseEntity(
            id = "ex_10_160",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_160",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"go\", \"see\" va \"eat\" fe'llarining Past Simple (o'tgan zamon) shaklini tanlang:",
            subPromptText = "160-Dars: Noto'g'ri fe'llar -ed olmaydi!",
            audioTextToSpeak = "went, saw, ate",
            optionsPipeSeparated = "went, saw, ate|goed, seed, eated|went, seen, eaten|goes, sees, eats",
            correctAnswer = "went, saw, ate",
            explanationUzbek = "go → went, see → saw, eat → ate.",
            vocabularyIdsCsv = "voc_10_went,voc_10_saw,voc_10_ate",
            grammarRuleIdsCsv = "gram_10_153"
        ),
        ExerciseEntity(
            id = "ex_10_166",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_166",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joylarni to'ldiring: \"How ___ water do you need? How ___ books do you have?\"",
            subPromptText = "165–166 Darslar: much (sanalmaydigan) va many (sanaladigan)",
            audioTextToSpeak = "How much water do you need? How many books do you have?",
            optionsPipeSeparated = "much / many|many / much|some / no|any / much",
            correctAnswer = "much / many",
            explanationUzbek = "Water sanalmaydi (much), books sanaladigan ko'plik (many).",
            vocabularyIdsCsv = "voc_10_much,voc_10_many",
            grammarRuleIdsCsv = "gram_10_161"
        ),
        ExerciseEntity(
            id = "ex_10_178",
            topicId = "topic_10_a2_grammar",
            sectionId = "sec_10_178",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Uyda qoldim, chunki yomg'ir yog'ayotgan edi\" gapini tanlang:",
            subPromptText = "178-Dars: Because — chunki (sabab)",
            audioTextToSpeak = "I stayed at home because it was raining.",
            optionsPipeSeparated = "I stayed at home because it was raining.|I stayed at home so it was raining.|I stayed at home between it was raining.|I stayed at home without it was raining.",
            correctAnswer = "I stayed at home because it was raining.",
            explanationUzbek = "Sababni bildirish uchun \"because\" (chunki) bog'lovchisi ishlatiladi.",
            vocabularyIdsCsv = "voc_10_because",
            grammarRuleIdsCsv = "gram_10_161"
        )
    )
}
