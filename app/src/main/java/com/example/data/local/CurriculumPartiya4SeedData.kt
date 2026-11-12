package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.core.database.GrammarRuleEntity
import com.example.core.database.LessonSectionEntity
import com.example.core.database.VocabularyEntity
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/**
 * PARTIYA 4 — DARS 61–80 (TO'LIQ):
 * - A1 FE'L — TO BE, TO HAVE, TO DO, ODDIY FE'LLAR (Dars 61–80, topic_07_verbs_a1)
 */
object CurriculumPartiya4SeedData {

    fun buildSections(): List<LessonSectionEntity> = listOf(
        // =========================================================================
        // PARTIYA 4 — A1 FE'LLAR (DARS 61–80) inside topic_07_verbs_a1
        // =========================================================================
        LessonSectionEntity(
            id = "sec_07_61",
            topicId = "topic_07_verbs_a1",
            orderIndex = 1,
            titleEnglish = "61-Dars: To Be — I am, He is, They are",
            titleUzbek = "To be fe'li: I am, He is, They are",
            objectiveUzbek = "🤖 TalkMe: Salom yana! Bugun eng muhim fe'lni o'rganamiz: to be — \"bo'lmoq\". Bu fe'l uch shaklda bo'ladi: am (I bilan), is (he/she/it bilan), are (we/you/they bilan). Qo'rqmang — bu juda oson! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo be — \"bo'lmoq\". Uch shakl:\n• I → am: \"I am a student.\" — Men talabaman.\n• He / She / It → is: \"He is a doctor.\" — U shifokor.\n• We / You / They → are: \"They are teachers.\" — Ular o'qituvchilar.\n\nQisqartmalar: I am = I'm, He is = he's, She is = she's, It is = it's, We are = we're, You are = you're, They are = they're.\nQachon ishlatamiz? Kim ekanligini (I am a student), qanday ekanligini (She is beautiful), qayerda ekanligini (He is at home) aytishda.\n\n💬 MISOLLAR:\n• I am Ali. — Men Aliman.\n• I am a student. — Men talabaman.\n• He is my brother. — U mening akam.\n• She is a doctor. — U shifokor.\n• It is a cat. — Bu mushuk.\n• We are friends. — Biz do'stlarmiz.\n• You are a teacher. — Siz o'qituvchisiz.\n• They are students. — Ular talabalar.\n\n✍️ MASHQ:\n1. \"Men talabaman\" → ✅ I am a student.\n2. \"U (qiz) shifokor\" → ✅ She is a doctor.\n3. \"Ular o'qituvchilar\" → ✅ They are teachers.\n4. Bo'sh joyni to'ldiring: \"He ___ my brother.\" → ✅ is\n5. Ovoz chiqarib: \"I am. He is. She is. It is. We are. You are. They are.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To be — \"bo'lmoq\". Uch shakl: am (I), is (he/she/it), are (we/you/they). Qisqartmalar: I'm, he's, she's, it's, we're, you're, they're. Uyga vazifa: 3 ta gap tuzing: \"I am...\", \"He is...\", \"They are...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi siz to be fe'lini bilasiz! Ertaga: to be takrorlash — qisqartmalar bilan. 👋",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_62",
            topicId = "topic_07_verbs_a1",
            orderIndex = 2,
            titleEnglish = "62-Dars: To Be — Contractions Review",
            titleUzbek = "To be — Takrorlash (Qisqartmalar)",
            objectiveUzbek = "🤖 TalkMe: Bugun to be ni qisqartmalar bilan mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo'liq shakl → Qisqartma:\n• I am → I'm\n• You are → you're\n• He is → he's\n• She is → she's\n• It is → it's\n• We are → we're\n• They are → they're\nMuhim: Qisqartmalar og'zaki nutqda ko'p ishlatiladi. Yozma nutqda ham (norasmiy) ishlatiladi.\n\n💬 MISOLLAR:\n• I'm a student. = I am a student.\n• He's a doctor. = He is a doctor.\n• She's my sister. = She is my sister.\n• It's a cat. = It is a cat.\n• We're friends. = We are friends.\n• You're a teacher. = You are a teacher.\n• They're students. = They are students.\n\n✍️ MASHQ:\n1. \"I am\" qisqartmasi? → ✅ I'm\n2. \"He is\" qisqartmasi? → ✅ He's\n3. \"They are\" qisqartmasi? → ✅ They're\n4. \"We're\" to'liq shakli? → ✅ We are\n5. Ovoz chiqarib: \"I'm. He's. She's. It's. We're. You're. They're.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Qisqartmalar: I'm, you're, he's, she's, it's, we're, they're. Og'zaki nutqda ko'p ishlatiladi. Uyga vazifa: 3 ta gapni qisqartma bilan yozing: \"I'm...\", \"He's...\", \"They're...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi qisqartmalarni bilasiz! Ertaga: to be bo'lishsiz — I'm not. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_63",
            topicId = "topic_07_verbs_a1",
            orderIndex = 3,
            titleEnglish = "63-Dars: To Be — Negative (I'm not)",
            titleUzbek = "To be — Bo'lishsiz (I'm not)",
            objectiveUzbek = "🤖 TalkMe: Bugun to be ning bo'lishsiz shaklini o'rganamiz: \"men ... emasman\". 🚀",
            explanationUzbek = "📖 QOIDA:\nBo'lishsiz: to be + not.\n• I am not → I'm not\n• He is not → he isn't yoki he's not\n• She is not → she isn't yoki she's not\n• It is not → it isn't yoki it's not\n• We are not → we aren't yoki we're not\n• You are not → you aren't yoki you're not\n• They are not → they aren't yoki they're not\nMuhim: \"I am not\" ning qisqartmasi \"I'm not\" — \"I amn't\" yo'q!\n\n💬 MISOLLAR:\n• I am not a teacher. — Men o'qituvchi emasman. (I'm not)\n• He is not here. — U bu yerda emas. (He isn't)\n• She is not a doctor. — U shifokor emas. (She isn't)\n• It is not a cat. — Bu mushuk emas. (It isn't)\n• We are not students. — Biz talabalar emas. (We aren't)\n• They are not friends. — Ular do'stlar emas. (They aren't)\n\n✍️ MASHQ:\n1. \"Men o'qituvchi emasman\" → ✅ I am not a teacher. (I'm not)\n2. \"U bu yerda emas\" → ✅ He is not here. (He isn't)\n3. \"Ular do'stlar emas\" → ✅ They are not friends. (They aren't)\n4. \"I am not\" qisqartmasi? → ✅ I'm not\n5. Xatoni toping: \"I amn't a student.\" → ✅ I'm not a student.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: to be + not. I'm not, he isn't (he's not), she isn't, it isn't, we aren't, you aren't, they aren't. \"I amn't\" yo'q! Uyga vazifa: 3 ta bo'lishsiz gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsizni bilasiz! Ertaga: to be bo'lishsiz takrorlash — barcha shakllar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_64",
            topicId = "topic_07_verbs_a1",
            orderIndex = 4,
            titleEnglish = "64-Dars: To Be — Negative Review (isn't, aren't)",
            titleUzbek = "To be — Bo'lishsiz takrorlash (isn't, aren't)",
            objectiveUzbek = "🤖 TalkMe: Bugun bo'lishsiz shakllarni mustahkamlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBo'lishsiz qisqartmalar:\n• I am not → I'm not\n• He / She / It is not → isn't (yoki he's / she's / it's not)\n• We / You / They are not → aren't (yoki we're / you're / they're not)\nMuhim: \"isn't\" va \"aren't\" — og'zaki nutqda eng ko'p ishlatiladi.\n\n💬 MISOLLAR:\n• I am not tired. — Men charchagan emasman. (I'm not)\n• He isn't at home. — U uyda emas.\n• She isn't a teacher. — U o'qituvchi emas.\n• It isn't cold today. — Bugun sovuq emas.\n• We aren't ready. — Biz tayyor emasmiz.\n• You aren't late. — Siz kechikmadingiz.\n• They aren't here. — Ular bu yerda emas.\n\n✍️ MASHQ:\n1. \"Men charchagan emasman\" → ✅ I'm not tired.\n2. \"U uyda emas\" → ✅ He isn't at home.\n3. \"Biz tayyor emasmiz\" → ✅ We aren't ready.\n4. \"They are not\" qisqartmasi? → ✅ They aren't / They're not\n5. Ovoz chiqarib: \"I'm not. He isn't. She isn't. It isn't. We aren't. You aren't. They aren't.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz qisqartmalar: I'm not, isn't (he/she/it), aren't (we/you/they). Uyga vazifa: 5 ta bo'lishsiz gap tuzing.\n\n🤖 TalkMe yakuniy: Ajoyib! Bo'lishsiz mustahkamlandi! Ertaga: to be so'roq — Am I? Is he? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_65",
            topicId = "topic_07_verbs_a1",
            orderIndex = 5,
            titleEnglish = "65-Dars: To Be — Questions (Am I? Is he?)",
            titleUzbek = "To be — So'roq (Am I? Is he?)",
            objectiveUzbek = "🤖 TalkMe: Bugun to be ning so'roq shaklini o'rganamiz: \"Men ... manmi?\" 🚀",
            explanationUzbek = "📖 QOIDA:\nSo'roq: To be eganing oldiga o'tadi.\n• Am I...? — Men ... manmi?\n• Is he / she / it...? — U ... mi?\n• Are we / you / they...? — Biz / siz / ular ... mi?\nJavob: Yes, I am. / No, I'm not. Yes, he is. / No, he isn't. Yes, they are. / No, they aren't.\n\n💬 MISOLLAR:\n• Am I a student? — Men talabamanmi? (Yes, I am. / No, I'm not.)\n• Is he a doctor? — U shifokormi? (Yes, he is. / No, he isn't.)\n• Is she at home? — U uydami? (Yes, she is. / No, she isn't.)\n• Are you ready? — Siz tayyormisiz? (Yes, I am. / No, I'm not.)\n• Are they students? — Ular talabalarmi? (Yes, they are. / No, they aren't.)\n\n✍️ MASHQ:\n1. \"Men talabamanmi?\" → ✅ Am I a student?\n2. \"U shifokormi?\" → ✅ Is he a doctor?\n3. \"Ular talabalarmi?\" → ✅ Are they students?\n4. Javob bering: \"Is she at home?\" → ✅ Yes, she is. / No, she isn't.\n5. Ovoz chiqarib: \"Am I? Is he? Is she? Is it? Are we? Are you? Are they?\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: So'roq: to be eganing oldiga o'tadi (Am I? Is he? Are they?). Javob: Yes, I am. / No, I'm not. Uyga vazifa: 3 ta so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi so'roqni bilasiz! Ertaga: to be so'roq takrorlash — barcha shakllar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_66",
            topicId = "topic_07_verbs_a1",
            orderIndex = 6,
            titleEnglish = "66-Dars: To Be — Complete Mastery",
            titleUzbek = "To be — So'roq takrorlash va To be yakuniy",
            objectiveUzbek = "🤖 TalkMe: Bugun to be ni to'liq takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo be — uch shakl:\n• Darak: I am. He is. They are.\n• Bo'lishsiz: I'm not. He isn't. They aren't.\n• So'roq: Am I? Is he? Are they?\nJavoblar: Yes, I am. / No, I'm not. Yes, he is. / No, he isn't. Yes, they are. / No, they aren't.\n\n💬 MISOLLAR:\n• Darak: I am a student. He is a doctor. They are teachers.\n• Bo'lishsiz: I'm not a teacher. He isn't here. They aren't ready.\n• So'roq: Am I late? Is he at home? Are they students?\n• Javob: Yes, I am. No, he isn't. Yes, they are.\n\n✍️ MASHQ:\n1. \"Men talabaman\" (darak) → ✅ I am a student.\n2. \"Men talaba emasman\" (bo'lishsiz) → ✅ I'm not a student.\n3. \"Men talabamanmi?\" (so'roq) → ✅ Am I a student?\n4. Javob bering: \"Is he a doctor?\" → ✅ Yes, he is. / No, he isn't.\n5. Ovoz chiqarib: \"I am. I'm not. Am I? He is. He isn't. Is he? They are. They aren't. Are they?\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To be to'liq: Darak (I am, he is, they are), Bo'lishsiz (I'm not, isn't, aren't), So'roq (Am I? Is he? Are they?). To be tugadi! Uyga vazifa: 3 ta darak, 3 ta bo'lishsiz, 3 ta so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz to be ni to'liq o'zlashtirdingiz! Ertaga: to have — I have, he has. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_67",
            topicId = "topic_07_verbs_a1",
            orderIndex = 7,
            titleEnglish = "67-Dars: To Have — I have, He has",
            titleUzbek = "To have fe'li: I have, He has",
            objectiveUzbek = "🤖 TalkMe: Bugun to have — \"ega bo'lmoq\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo have — \"ega bo'lmoq\", \"bor bo'lmoq\". Ikki shakl:\n• I / You / We / They → have: \"I have a book.\" — Menda kitob bor.\n• He / She / It → has: \"He has a car.\" — Uning mashinasi bor.\nMuhim: 3-shaxs birlikda has (have emas!).\nQachon ishlatamiz? Egalikni (I have a book), oilani (I have two brothers), xususiyatni (She has beautiful eyes) aytishda.\n\n💬 MISOLLAR:\n• I have a book. — Menda kitob bor.\n• You have a car. — Sizning mashinangiz bor.\n• We have a house. — Bizning uyimiz bor.\n• They have two children. — Ularning ikkita bolasi bor.\n• He has a dog. — Uning iti bor.\n• She has a cat. — Uning mushugi bor.\n• It has a tail. — Uning dumi bor.\n\n✍️ MASHQ:\n1. \"Menda kitob bor\" → ✅ I have a book.\n2. \"Uning iti bor\" → ✅ He has a dog.\n3. \"Bizning uyimiz bor\" → ✅ We have a house.\n4. Bo'sh joyni to'ldiring: \"She ___ a cat.\" → ✅ has\n5. Ovoz chiqarib: \"I have. You have. We have. They have. He has. She has. It has.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To have — \"ega bo'lmoq\". Have (I/you/we/they), has (he/she/it). Uyga vazifa: 3 ta gap tuzing: \"I have...\", \"He has...\", \"They have...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi to have ni bilasiz! Ertaga: to have bo'lishsiz/so'roq — I don't have. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_68",
            topicId = "topic_07_verbs_a1",
            orderIndex = 8,
            titleEnglish = "68-Dars: To Have — Don't have & Do you have?",
            titleUzbek = "To have — Bo'lishsiz va so'roq (don't have / Do you have?)",
            objectiveUzbek = "🤖 TalkMe: Bugun to have ning bo'lishsiz va so'roq shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nZamonaviy ingliz tilida to have do/does bilan ishlatiladi.\n• Bo'lishsiz: I don't have a car. He doesn't have a car.\n• So'roq: Do you have a car? Does he have a car?\n• Javob: Yes, I do. / No, I don't. Yes, he does. / No, he doesn't.\nMuhim: \"Does\" va \"doesn't\" bilan har doim have (has emas!): ✅ Does he have? (not: Does he has?).\n\n💬 MISOLLAR:\n• I don't have a car. — Mening mashinam yo'q.\n• He doesn't have a car. — Uning mashinasi yo'q.\n• Do you have a pen? — Sizda ruchka bormi? (Yes, I do. / No, I don't.)\n• Does she have a cat? — Uning mushugi bormi? (Yes, she does. / No, she doesn't.)\n• We don't have time. — Bizning vaqtimiz yo'q.\n\n✍️ MASHQ:\n1. \"Mening mashinam yo'q\" → ✅ I don't have a car.\n2. \"Uning mushugi bormi?\" → ✅ Does she have a cat?\n3. \"Sizda ruchka bormi?\" → ✅ Do you have a pen?\n4. Javob bering: \"Does he have a dog?\" → ✅ Yes, he does. / No, he doesn't.\n5. Xatoni toping: \"Does he has a car?\" → ✅ Does he have a car?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: don't have / doesn't have. So'roq: Do you have? / Does he have? Javob: Yes, I do. / No, he doesn't. Uyga vazifa: 3 ta so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsiz va so'roqni bilasiz! Ertaga: to have takrorlash — eski va yangi usul. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_69",
            topicId = "topic_07_verbs_a1",
            orderIndex = 9,
            titleEnglish = "69-Dars: To Have — Modern vs Literary Forms",
            titleUzbek = "To have — Takrorlash (eski va yangi usul)",
            objectiveUzbek = "🤖 TalkMe: Bugun to have ni ikki usulda takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. Yangi usul (do/does bilan — eng ko'p ishlatiladi):\n• Bo'lishsiz: I don't have, he doesn't have.\n• So'roq: Do you have? Does he have?\n2. Eski usul (kitobiy, kam ishlatiladi):\n• Bo'lishsiz: I haven't a car. He hasn't a car.\n• So'roq: Have you a car? Has he a car?\n3. \"No\" bilan bo'lishsiz (ham ishlatiladi):\n• I have no car. He has no car.\nMuhim: Og'zaki nutqda yangi usul ko'proq ishlatiladi!\n\n💬 MISOLLAR:\n• Yangi: I don't have a car. / He doesn't have a car. / Do you have a car? / Does he have a car?\n• Eski: I haven't a car. / He hasn't a car. / Have you a car? / Has he a car?\n• No bilan: I have no car. / He has no car.\n\n✍️ MASHQ:\n1. \"Mening mashinam yo'q\" (yangi usul) → ✅ I don't have a car.\n2. \"Mening mashinam yo'q\" (no bilan) → ✅ I have no car.\n3. \"Uning mashinasi bormi?\" (yangi usul) → ✅ Does he have a car?\n4. \"Uning mashinasi bormi?\" (eski usul) → ✅ Has he a car?\n5. Ovoz chiqarib: \"I don't have. He doesn't have. Do you have? Does he have?\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Yangi usul: don't/doesn't have, Do/Does...have? Eski usul: haven't/hasn't, Have/Has...? No bilan: have no/has no. Yangi usul ko'proq ishlatiladi. Uyga vazifa: 3 ta gap tuzing.\n\n🤖 TalkMe yakuniy: Ajoyib! To have mustahkamlandi! Ertaga: to have yakuniy + to be takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_70",
            topicId = "topic_07_verbs_a1",
            orderIndex = 10,
            titleEnglish = "70-Dars: To Have & To Be Review",
            titleUzbek = "To have — Yakuniy + To be takrorlash",
            objectiveUzbek = "🤖 TalkMe: Bugun to have ni yakunlaymiz va to be ni takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo be (takrorlash): \"bo'lmoq\" (kim? qanday? qayerda?)\n• Darak: I am. He is. They are.\n• Bo'lishsiz: I'm not. He isn't. They aren't.\n• So'roq: Am I? Is he? Are they?\n\nTo have (yakuniy): \"ega bo'lmoq\" (nima bor?)\n• Darak: I have. He has.\n• Bo'lishsiz: I don't have. He doesn't have.\n• So'roq: Do you have? Does he have?\n\n💬 MISOLLAR:\n• To be: I am a student. I am not a teacher. Am I late?\n• To have: I have a book. I don't have a car. Do you have a pen?\n• Farq: I am a student. (Men talabaman.) / I have a book. (Menda kitob bor.)\n\n✍️ MASHQ:\n1. \"Men talabaman\" (to be) → ✅ I am a student.\n2. \"Menda kitob bor\" (to have) → ✅ I have a book.\n3. \"Men talaba emasman\" (to be) → ✅ I'm not a student.\n4. \"Menda mashina yo'q\" (to have) → ✅ I don't have a car.\n5. To be va to have takrorlang: am, is, are, have, has.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To be: am/is/are (bo'lmoq). To have: have/has (ega bo'lmoq). Darak, bo'lishsiz, so'roq. To have tugadi! Uyga vazifa: 4 ta gap tuzing — 2 tasi to be, 2 tasi to have.\n\n🤖 TalkMe yakuniy: MUKAMMAL! Siz to be va to have ni to'liq bilasiz! Ertaga: to do — I do, he does. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_71",
            topicId = "topic_07_verbs_a1",
            orderIndex = 11,
            titleEnglish = "71-Dars: To Do — I do, He does",
            titleUzbek = "To do fe'li: I do, He does",
            objectiveUzbek = "🤖 TalkMe: Bugun to do — \"qilmoq\" ni o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nTo do — \"qilmoq, bajarmoq\". Ikki shakl:\n• I / You / We / They → do: \"I do my homework.\" — Men uy vazifamni qilaman.\n• He / She / It → does: \"He does his work.\" — U ishini qiladi.\nMuhim: 3-shaxs birlikda does (do emas!).\nQachon ishlatamiz? Asosiy fe'l sifatida (I do my work) va yordamchi fe'l sifatida (Do you like tea?).\n\n💬 MISOLLAR:\n• I do my homework. — Men uy vazifamni qilaman.\n• You do your work. — Siz ishingizni qilasiz.\n• We do exercises. — Biz mashqlarni qilamiz.\n• They do their jobs. — Ular ishlarini qilishadi.\n• He does his work. — U ishini qiladi.\n• She does her homework. — U uy vazifasini qiladi.\n• It does good. — Bu foyda qiladi.\n\n✍️ MASHQ:\n1. \"Men uy vazifamni qilaman\" → ✅ I do my homework.\n2. \"U ishini qiladi\" → ✅ He does his work.\n3. \"Ular ishlarini qilishadi\" → ✅ They do their jobs.\n4. Bo'sh joyni to'ldiring: \"She ___ her homework.\" → ✅ does\n5. Ovoz chiqarib: \"I do. You do. We do. They do. He does. She does. It does.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To do — \"qilmoq\". Do (I/you/we/they), does (he/she/it). Uyga vazifa: 3 ta gap tuzing: \"I do...\", \"He does...\", \"They do...\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi to do ni bilasiz! Ertaga: to do bo'lishsiz — I don't do. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_72",
            topicId = "topic_07_verbs_a1",
            orderIndex = 12,
            titleEnglish = "72-Dars: To Do — Negative (I don't do)",
            titleUzbek = "To do — Bo'lishsiz (I don't do)",
            objectiveUzbek = "🤖 TalkMe: Bugun to do ning bo'lishsiz shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nBo'lishsiz: don't / doesn't + fe'lning asosiy shakli.\n• I / You / We / They → don't do: \"I don't do my homework.\" — Men uy vazifamni qilmayman.\n• He / She / It → doesn't do: \"He doesn't do his work.\" — U ishini qilmaydi.\nMuhim: \"Doesn't\" dan keyin do (does emas!): ✅ He doesn't do (not: doesn't does).\n\n💬 MISOLLAR:\n• I don't do my homework. — Men uy vazifamni qilmayman.\n• You don't do your work. — Siz ishingizni qilmaysiz.\n• We don't do exercises. — Biz mashqlarni qilmaymiz.\n• They don't do their jobs. — Ular ishlarini qilishmaydi.\n• He doesn't do his work. — U ishini qilmaydi.\n• She doesn't do her homework. — U uy vazifasini qilmaydi.\n\n✍️ MASHQ:\n1. \"Men uy vazifamni qilmayman\" → ✅ I don't do my homework.\n2. \"U ishini qilmaydi\" → ✅ He doesn't do his work.\n3. \"Ular ishlarini qilishmaydi\" → ✅ They don't do their jobs.\n4. Xatoni toping: \"He doesn't does his work.\" → ✅ He doesn't do his work.\n5. Ovoz chiqarib: \"I don't do. He doesn't do. They don't do.\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: don't do (I/you/we/they), doesn't do (he/she/it). Doesn't dan keyin do (does emas!). Uyga vazifa: 3 ta bo'lishsiz gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsizni bilasiz! Ertaga: to do so'roq — Do you do? 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_73",
            topicId = "topic_07_verbs_a1",
            orderIndex = 13,
            titleEnglish = "73-Dars: To Do — Questions (Do you do?)",
            titleUzbek = "To do — So'roq (Do you do?)",
            objectiveUzbek = "🤖 TalkMe: Bugun to do ning so'roq shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nSo'roq: Do / Does + ega + fe'lning asosiy shakli.\n• Do (I / you / we / they): \"Do you do your homework?\" — Siz uy vazifangizni qilasizmi?\n• Does (he / she / it): \"Does he do his work?\" — U ishini qiladimi?\nJavob: Yes, I do. / No, I don't. Yes, he does. / No, he doesn't.\nMuhim: \"Does\" dan keyin do (does emas!): ✅ Does he do? (not: Does he does?).\n\n💬 MISOLLAR:\n• Do you do your homework? — Siz uy vazifangizni qilasizmi? (Yes, I do. / No, I don't.)\n• Does he do his work? — U ishini qiladimi? (Yes, he does. / No, he doesn't.)\n• Do they do their jobs? — Ular ishlarini qilishadimi? (Yes, they do. / No, they don't.)\n• Does she do her homework? — U uy vazifasini qiladimi? (Yes, she does. / No, she doesn't.)\n\n✍️ MASHQ:\n1. \"Siz uy vazifangizni qilasizmi?\" → ✅ Do you do your homework?\n2. \"U ishini qiladimi?\" → ✅ Does he do his work?\n3. \"Ular ishlarini qilishadimi?\" → ✅ Do they do their jobs?\n4. Javob bering: \"Does she do her homework?\" → ✅ Yes, she does. / No, she doesn't.\n5. Xatoni toping: \"Does he does his work?\" → ✅ Does he do his work?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: So'roq: Do you do? / Does he do? Javob: Yes, I do. / No, he doesn't. Does dan keyin do (does emas!). Uyga vazifa: 3 ta so'roq gap tuzing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi so'roqni bilasiz! Ertaga: to do buyruq — Don't do! 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_74",
            topicId = "topic_07_verbs_a1",
            orderIndex = 14,
            titleEnglish = "74-Dars: To Do — Imperatives (Don't do!)",
            titleUzbek = "To do — Buyruq (Don't do!)",
            objectiveUzbek = "🤖 TalkMe: Bugun to do ning buyruq shaklini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Buyruq (bo'lishli): fe'lning asosiy shakli: Do it! — Buni qil!\n• Buyruq (bo'lishsiz): Don't + fe'l: Don't do it! — Buni qilma!\nMuhim: Buyruqda ega (you) tushiriladi.\nBoshqa fe'llar bilan ham: Don't go! (Borma!), Don't eat! (Yema!), Don't be late! (Kechikma!).\n\n💬 MISOLLAR:\n• Do it! — Buni qil!\n• Don't do it! — Buni qilma!\n• Do your homework! — Uy vazifangizni qiling!\n• Don't do your homework now. — Hozir uy vazifangizni qilmang.\n• Don't go there! — U yerga bormang!\n• Don't be late! — Kechikmang!\n\n✍️ MASHQ:\n1. \"Buni qil!\" → ✅ Do it!\n2. \"Buni qilma!\" → ✅ Don't do it!\n3. \"U yerga bormang!\" → ✅ Don't go there!\n4. \"Kechikmang!\" → ✅ Don't be late!\n5. Ovoz chiqarib: \"Do it! Don't do it! Don't go! Don't be late!\"",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Buyruq (bo'lishli): Do it! Buyruq (bo'lishsiz): Don't do it! Ega (you) tushiriladi. Uyga vazifa: 3 ta buyruq gap tuzing: \"Do...!\", \"Don't...!\".\n\n🤖 TalkMe yakuniy: Zo'r! Endi buyruqni bilasiz! Ertaga: oddiy fe'llar — 3-shaxs -s. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_75",
            topicId = "topic_07_verbs_a1",
            orderIndex = 15,
            titleEnglish = "75-Dars: Regular Verbs — 3rd Person -s",
            titleUzbek = "Oddiy fe'llar — 3-shaxs birlik: -s qoidasi",
            objectiveUzbek = "🤖 TalkMe: Endi oddiy fe'llarni o'rganamiz! 3-shaxs birlikda -s qo'shiladi! 🚀",
            explanationUzbek = "📖 QOIDA:\nOddiy fe'llar — ish-harakatni bildiradi: work, read, go, come, like, live.\n3-shaxs birlik (he / she / it) da fe'lga -s qo'shiladi:\n• work → works\n• read → reads\n• like → likes\n• live → lives\nMuhim: I / you / we / they → -s yo'q! He / She / It → -s bor!\n\n💬 MISOLLAR:\n• I work. → He works. — Men ishlayman. → U ishlaydi.\n• I read. → She reads. — Men o'qiyman. → U o'qiydi.\n• I like. → He likes. — Men yaxshi ko'raman. → U yaxshi ko'radi.\n• I live. → She lives. — Men yashayman. → U yashaydi.\n• We play. → He plays. — Biz o'ynaymiz. → U o'ynaydi.\n\n✍️ MASHQ:\n1. \"U ishlaydi\" (work) → ✅ He works.\n2. \"U o'qiydi\" (read) → ✅ She reads.\n3. \"U yaxshi ko'radi\" (like) → ✅ He likes.\n4. \"U yashaydi\" (live) → ✅ She lives.\n5. Bo'sh joyni to'ldiring: \"He ___ (work) here.\" → ✅ works",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: 3-shaxs birlik (he/she/it) → fe'l + -s. I/you/we/they → -s yo'q. Uyga vazifa: 3 ta fe'lni 3-shaxs birlikda yozing: work, read, like.\n\n🤖 TalkMe yakuniy: Zo'r! Endi -s qoidasini bilasiz! Ertaga: -es qoidasi — go→goes. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_76",
            topicId = "topic_07_verbs_a1",
            orderIndex = 16,
            titleEnglish = "76-Dars: Regular Verbs — -es and y→ies",
            titleUzbek = "Oddiy fe'llar — 3-shaxs: -es, y→ies",
            objectiveUzbek = "🤖 TalkMe: Bugun -es va y→ies qoidalarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. -es qoidasi: Fe'l -s, -ss, -sh, -ch, -x, -o bilan tugasa → -es:\n• go → goes, do → does, watch → watches, finish → finishes, box → boxes.\n2. y→ies qoidasi:\n• Undosh + y → y→ies: study → studies, carry → carries, cry → cries.\n• Unli + y → faqat -s: play → plays, say → says, buy → buys.\n\n💬 MISOLLAR:\n• He goes to school. — U maktabga boradi.\n• She does her homework. — U uy vazifasini qiladi.\n• He watches TV. — U televizor ko'radi.\n• She finishes her work. — U ishini tugatadi.\n• He studies English. — U ingliz tilini o'rganadi.\n• She carries a bag. — U sumka ko'taradi.\n• He plays football. — U futbol o'ynaydi. (unli + y → -s)\n• She says hello. — U salom deydi. (unli + y → -s)\n\n✍️ MASHQ:\n1. \"U maktabga boradi\" (go) → ✅ He goes to school.\n2. \"U televizor ko'radi\" (watch) → ✅ He watches TV.\n3. \"U ingliz tilini o'rganadi\" (study) → ✅ He studies English.\n4. \"U futbol o'ynaydi\" (play) → ✅ He plays football.\n5. Bo'sh joyni to'ldiring: \"She ___ (finish) her work.\" → ✅ finishes",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: -es: go→goes, do→does, watch→watches, finish→finishes. y→ies: study→studies, carry→carries. Unli + y → -s: play→plays, say→says. Uyga vazifa: 5 ta fe'lni 3-shaxs birlikda yozing.\n\n🤖 TalkMe yakuniy: Zo'r! Endi -es va y→ies ni bilasiz! Ertaga: oddiy fe'llar takrorlash — barcha qoidalar. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_77",
            topicId = "topic_07_verbs_a1",
            orderIndex = 17,
            titleEnglish = "77-Dars: Regular Verbs — Spelling Rules Review",
            titleUzbek = "Oddiy fe'llar — Takrorlash (barcha qoidalar)",
            objectiveUzbek = "🤖 TalkMe: Bugun oddiy fe'llarni to'liq takrorlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n3-shaxs birlik (he / she / it) — uch qoida:\n1) -s: work→works, read→reads, like→likes, live→lives, play→plays (unli+y).\n2) -es: go→goes, do→does, watch→watches, finish→finishes, box→boxes.\n3) y→ies: study→studies, carry→carries, cry→cries.\nMuhim: I / you / we / they → -s yo'q: I work, you read, we like, they live.\n\n💬 MISOLLAR:\n• I work. He works.\n• I go. He goes.\n• I study. He studies.\n• I play. He plays.\n• I watch. He watches.\n• I read. She reads.\n• I like. She likes.\n\n✍️ MASHQ:\n1. \"U ishlaydi\" → ✅ He works.\n2. \"U boradi\" → ✅ He goes.\n3. \"U o'rganadi\" → ✅ He studies.\n4. \"U o'ynaydi\" → ✅ He plays.\n5. \"U ko'radi\" → ✅ He watches.\n6. Xatoni toping: \"He study English.\" → ✅ He studies English.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Uch qoida: -s, -es, y→ies. I/you/we/they → -s yo'q. Uyga vazifa: 6 ta fe'lni 3-shaxs birlikda yozing: work, go, study, play, watch, read.\n\n🤖 TalkMe yakuniy: Ajoyib! Oddiy fe'llar mustahkamlandi! Ertaga: oddiy fe'llar — bo'lishsiz va so'roq. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_78",
            topicId = "topic_07_verbs_a1",
            orderIndex = 18,
            titleEnglish = "78-Dars: Regular Verbs — Negative & Questions",
            titleUzbek = "Oddiy fe'llar — Bo'lishsiz va so'roq (don't/doesn't, Do/Does)",
            objectiveUzbek = "🤖 TalkMe: Bugun oddiy fe'llarning bo'lishsiz va so'roq shakllarini o'rganamiz! 🚀",
            explanationUzbek = "📖 QOIDA:\n• Bo'lishsiz: don't / doesn't + fe'lning asosiy shakli.\n• So'roq: Do / Does + ega + fe'lning asosiy shakli.\nMuhim: \"Doesn't\" va \"Does\" dan keyin fe'l -s siz (asosiy shakl)!\n✅ He doesn't work (not: works). ✅ Does he work? (not: works).\n\n💬 MISOLLAR:\n• I don't work here. — Men bu yerda ishlamayman.\n• He doesn't work here. — U bu yerda ishlamaydi.\n• Do you work here? — Siz bu yerda ishlaysizmi? (Yes, I do. / No, I don't.)\n• Does he work here? — U bu yerda ishlaydimi? (Yes, he does. / No, he doesn't.)\n• She doesn't like coffee. — U kofeni yoqtirmaydi.\n• Does she like tea? — U choyni yoqtiradimi?\n\n✍️ MASHQ:\n1. \"U bu yerda ishlamaydi\" → ✅ He doesn't work here.\n2. \"Siz bu yerda ishlaysizmi?\" → ✅ Do you work here?\n3. \"U kofeni yoqtirmaydi\" → ✅ She doesn't like coffee.\n4. \"U choyni yoqtiradimi?\" → ✅ Does she like tea?\n5. Xatoni toping: \"Does he works here?\" → ✅ Does he work here?",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: Bo'lishsiz: don't/doesn't + fe'l (asosiy shakl). So'roq: Do/Does + ega + fe'l (asosiy shakl)? -s faqat darak gapda! Uyga vazifa: 3 ta gap tuzing: 1 darak, 1 bo'lishsiz, 1 so'roq.\n\n🤖 TalkMe yakuniy: Zo'r! Endi bo'lishsiz va so'roqni bilasiz! Ertaga: A1 fe'llar takrorlash 1 — to be, to have, to do. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_79",
            topicId = "topic_07_verbs_a1",
            orderIndex = 19,
            titleEnglish = "79-Dars: A1 Verbs Review 1 — Be, Have, Do",
            titleUzbek = "A1 Fe'llar takrorlash 1 — To be, To have, To do",
            objectiveUzbek = "🤖 TalkMe: Bugun uchta asosiy fe'lni takrorlaymiz: to be, to have, to do! 🚀",
            explanationUzbek = "📖 QOIDA:\n1. To be — \"bo'lmoq\":\n• Darak: I am, he is, they are. | Bo'lishsiz: I'm not, he isn't, they aren't. | So'roq: Am I? Is he? Are they?\n2. To have — \"ega bo'lmoq\":\n• Darak: I have, he has. | Bo'lishsiz: I don't have, he doesn't have. | So'roq: Do you have? Does he have?\n3. To do — \"qilmoq\":\n• Darak: I do, he does. | Bo'lishsiz: I don't do, he doesn't do. | So'roq: Do you do? Does he do?\n\n💬 MISOLLAR:\n• To be: I am a student. I am not a teacher. Am I late?\n• To have: I have a book. I don't have a car. Do you have a pen?\n• To do: I do my homework. I don't do it now. Do you do your homework?\n\n✍️ MASHQ:\n1. \"Men talabaman\" (to be) → ✅ I am a student.\n2. \"Menda kitob bor\" (to have) → ✅ I have a book.\n3. \"Men uy vazifamni qilaman\" (to do) → ✅ I do my homework.\n4. \"U ishlamaydi\" (oddiy fe'l) → ✅ He doesn't work.\n5. Uchta fe'lni takrorlang: am, is, are, have, has, do, does.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: To be: am/is/are. To have: have/has. To do: do/does. Har biri darak, bo'lishsiz, so'roq. Uyga vazifa: 6 ta gap tuzing — 2 tasi to be, 2 tasi to have, 2 tasi to do.\n\n🤖 TalkMe yakuniy: Ajoyib! Uchta asosiy fe'l mustahkamlandi! Ertaga: A1 fe'llar yakuniy takrorlash. 👋",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = "sec_07_80",
            topicId = "topic_07_verbs_a1",
            orderIndex = 20,
            titleEnglish = "80-Dars: A1 Verbs Final Mastery",
            titleUzbek = "A1 Fe'llar yakuniy takrorlash va Partiya 4 Yakuni",
            objectiveUzbek = "🤖 TalkMe: Bugun A1 fe'llarni to'liq yakunlaymiz! 🚀",
            explanationUzbek = "📖 QOIDA:\nA1 fe'llar — hammasi:\n• To be: am, is, are (bo'lmoq).\n• To have: have, has (ega bo'lmoq).\n• To do: do, does (qilmoq).\n• Oddiy fe'llar: work, read, go, come, like, live, play, watch, study...\n• 3-shaxs -s: works, reads, goes, likes, studies...\n• Bo'lishsiz: don't / doesn't + fe'l.\n• So'roq: Do / Does + ega + fe'l?\n\n💬 MISOLLAR:\n• To be: I am happy. He is a doctor. They are students.\n• To have: I have a car. She has a cat.\n• To do: I do my work. He does his work.\n• Oddiy: I work. He works. I don't work. He doesn't work. Do you work? Does he work?\n\n✍️ MASHQ:\n1. \"Men talabaman\" → ✅ I am a student.\n2. \"Uning iti bor\" → ✅ He has a dog.\n3. \"Men uy vazifamni qilaman\" → ✅ I do my homework.\n4. \"U ishlaydi\" → ✅ He works.\n5. \"U ishlamaydi\" → ✅ He doesn't work.\n6. \"U ishlaydimi?\" → ✅ Does he work?\n7. A1 fe'llarni takrorlang: am, is, are, have, has, do, does, work, read, go, like, live.",
            wrapUpSummaryUzbek = "Wrap-up: ✅ XULOSA: A1 fe'llar tugadi! To be (am/is/are), to have (have/has), to do (do/does), oddiy fe'llar (3-shaxs -s, bo'lishsiz/so'roq do/does). Uyga vazifa: 5 ta gap tuzing — har xil fe'llar bilan.\n\n🤖 TalkMe yakuniy: MUKAMMAL, [USER_NAME]! Siz A1 fe'llarni to'liq o'zlashtirdingiz! Partiya 4 (61–80 darslar) tugadi! Ertaga — Partiya 5: Artikl (a/an/the), Predlog (in/on/at), Bog'lovchi (and/but/or/so). 👋",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        // =========================================================================
        // PARTIYA 4 — DARS 61–80: A1 FE'LLAR VOCABULARY
        // =========================================================================
        VocabularyEntity("voc_07_am", "topic_07_verbs_a1", "sec_07_61", "Am", "bo'lmoq (I bilan)", "I am a student.", "Men talabaman.", isStudied = false),
        VocabularyEntity("voc_07_is", "topic_07_verbs_a1", "sec_07_61", "Is", "bo'lmoq (he, she, it bilan)", "He is a doctor.", "U shifokor.", isStudied = false),
        VocabularyEntity("voc_07_are", "topic_07_verbs_a1", "sec_07_61", "Are", "bo'lmoq (we, you, they bilan)", "They are teachers.", "Ular o'qituvchilar.", isStudied = false),
        VocabularyEntity("voc_07_have", "topic_07_verbs_a1", "sec_07_67", "Have", "ega bo'lmoq, bor bo'lmoq", "I have a book.", "Menda kitob bor.", isStudied = false),
        VocabularyEntity("voc_07_has", "topic_07_verbs_a1", "sec_07_67", "Has", "ega bo'lmoq (he, she, it bilan)", "He has a dog.", "Uning iti bor.", isStudied = false),
        VocabularyEntity("voc_07_do", "topic_07_verbs_a1", "sec_07_71", "Do", "qilmoq, bajarmoq", "I do my homework.", "Men uy vazifamni qilaman.", isStudied = false),
        VocabularyEntity("voc_07_does", "topic_07_verbs_a1", "sec_07_71", "Does", "qilmoq (he, she, it bilan)", "He does his work.", "U ishini qiladi.", isStudied = false),
        VocabularyEntity("voc_07_read", "topic_07_verbs_a1", "sec_07_75", "Read", "o'qimoq", "She reads a book every day.", "U har kuni kitob o'qiydi.", isStudied = false),
        VocabularyEntity("voc_07_like", "topic_07_verbs_a1", "sec_07_75", "Like", "yaxshi ko'rmoq, yoqtirmoq", "He likes apples.", "U olmalarni yaxshi ko'radi.", isStudied = false),
        VocabularyEntity("voc_07_live", "topic_07_verbs_a1", "sec_07_75", "Live", "yashamoq", "She lives in Tashkent.", "U Toshkentda yashaydi.", isStudied = false),
        VocabularyEntity("voc_07_play", "topic_07_verbs_a1", "sec_07_75", "Play", "o'ynamoq", "He plays football.", "U futbol o'ynaydi.", isStudied = false),
        VocabularyEntity("voc_07_go", "topic_07_verbs_a1", "sec_07_76", "Go", "bormoq", "He goes to school.", "U maktabga boradi.", isStudied = false),
        VocabularyEntity("voc_07_come", "topic_07_verbs_a1", "sec_07_75", "Come", "kelmoq", "She comes home early.", "U uyga erta keladi.", isStudied = false),
        VocabularyEntity("voc_07_finish", "topic_07_verbs_a1", "sec_07_76", "Finish", "tugatmoq", "She finishes her work.", "U ishini tugatadi.", isStudied = false),
        VocabularyEntity("voc_07_study", "topic_07_verbs_a1", "sec_07_76", "Study", "o'qimoq, o'rganmoq", "He studies English.", "U ingliz tilini o'rganadi.", isStudied = false),
        VocabularyEntity("voc_07_carry", "topic_07_verbs_a1", "sec_07_76", "Carry", "ko'tarmoq, tashimoq", "She carries a bag.", "U sumka ko'taradi.", isStudied = false),
        VocabularyEntity("voc_07_cry", "topic_07_verbs_a1", "sec_07_76", "Cry", "yig'lamoq", "The baby cries.", "Chaqaloq yig'laydi.", isStudied = false),
        VocabularyEntity("voc_07_say", "topic_07_verbs_a1", "sec_07_76", "Say", "aytmoq, demoq", "She says hello.", "U salom deydi.", isStudied = false),
        VocabularyEntity("voc_07_buy", "topic_07_verbs_a1", "sec_07_76", "Buy", "sotib olmoq", "He buys a new book.", "U yangi kitob sotib oladi.", isStudied = false)
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        // =========================================================================
        // PARTIYA 4 — DARS 61–80 GRAMMAR RULES (A1 FE'LLAR)
        // =========================================================================
        GrammarRuleEntity(
            id = "gram_07_61",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_61",
            titleUzbek = "To be fe'li: am, is, are (darak, bo'lishsiz, so'roq)",
            explanationUzbek = "I → am (I'm not / Am I?), He/She/It → is (isn't / Is he?), We/You/They → are (aren't / Are they?). \"I amn't\" shakli yo'q — faqat \"I'm not\" ishlatiladi.",
            exampleEnglish = "I am a student. He isn't at home. Are they ready?",
            exampleUzbek = "Men talabaman. U uyda emas. Ular tayyormi?",
            associatedVocabularyIdsCsv = "voc_07_am,voc_07_is,voc_07_are",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_07_67",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_67",
            titleUzbek = "To have fe'li: have / has va do/does bilan bo'lishsiz-so'roq",
            explanationUzbek = "I/You/We/They → have, He/She/It → has. Bo'lishsiz va so'roqda do/does + have ishlatiladi: I don't have, He doesn't have, Do you have?, Does he have? (has emas!).",
            exampleEnglish = "He has a dog. He doesn't have a car. Does she have a cat?",
            exampleUzbek = "Uning iti bor. Uning mashinasi yo'q. Uning mushugi bormi?",
            associatedVocabularyIdsCsv = "voc_07_have,voc_07_has",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_07_71",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_71",
            titleUzbek = "To do fe'li va Buyruq mayli (Do / Don't)",
            explanationUzbek = "I/You/We/They → do, He/She/It → does. Bo'lishsiz: don't do / doesn't do. So'roq: Do you do? / Does he do? Buyruq: Do it! (Qil!) / Don't do it! (Qilma!).",
            exampleEnglish = "I do my homework. He doesn't do his work. Don't be late!",
            exampleUzbek = "Men uy vazifamni qilaman. U ishini qilmaydi. Kechikmang!",
            associatedVocabularyIdsCsv = "voc_07_do,voc_07_does",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "gram_07_75",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_75",
            titleUzbek = "Oddiy fe'llar: 3-shaxs birlik (-s, -es, y→ies) va don't/doesn't",
            explanationUzbek = "He/She/It bilan darak gapda fe'lga -s (works, reads, plays), -es (goes, watches, finishes) yoki y→ies (studies, carries) qo'shiladi. Doesn't va Does dan keyin esa -s tushib qoladi!",
            exampleEnglish = "He works here. He goes to school. He doesn't work here. Does he study English?",
            exampleUzbek = "U bu yerda ishlaydi. U maktabga boradi. U bu yerda ishlamaydi. U ingliz tilini o'rganadimi?",
            associatedVocabularyIdsCsv = "voc_07_read,voc_07_like,voc_07_live,voc_07_play,voc_07_go,voc_07_finish,voc_07_study",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        // =========================================================================
        // PARTIYA 4 — DARS 61–80 EXERCISES (topic_07_verbs_a1)
        // =========================================================================
        ExerciseEntity(
            id = "ex_07_61",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_61",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"He ___ my brother. They ___ students.\"",
            subPromptText = "61-Dars: To be — am, is, are",
            audioTextToSpeak = "He is my brother. They are students.",
            optionsPipeSeparated = "is / are|am / is|are / is|is / am",
            correctAnswer = "is / are",
            explanationUzbek = "He bilan \"is\", They bilan \"are\" ishlatiladi.",
            vocabularyIdsCsv = "voc_07_is,voc_07_are",
            grammarRuleIdsCsv = "gram_07_61"
        ),
        ExerciseEntity(
            id = "ex_07_63",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_63",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"I am not a student\" gapining to'g'ri qisqartmasini toping:",
            subPromptText = "63-Dars: I am not qisqartmasi",
            audioTextToSpeak = "I'm not a student.",
            optionsPipeSeparated = "I'm not a student.|I amn't a student.|I isn't a student.|I aren't a student.",
            correctAnswer = "I'm not a student.",
            explanationUzbek = "\"I am not\" ning yagona to'g'ri qisqartmasi — \"I'm not\" (\"I amn't\" yo'q!).",
            vocabularyIdsCsv = "voc_07_am",
            grammarRuleIdsCsv = "gram_07_61"
        ),
        ExerciseEntity(
            id = "ex_07_65",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_65",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"U shifokormi?\" savolini ingliz tilida to'g'ri tuzing:",
            subPromptText = "65-Dars: To be so'roq shakli",
            audioTextToSpeak = "Is he a doctor?",
            optionsPipeSeparated = "Is he a doctor?|He is a doctor?|Are he a doctor?|Does he a doctor?",
            correctAnswer = "Is he a doctor?",
            explanationUzbek = "So'roqda \"Is\" egadan (he) oldinga o'tadi: \"Is he a doctor?\"",
            vocabularyIdsCsv = "voc_07_is",
            grammarRuleIdsCsv = "gram_07_61"
        ),
        ExerciseEntity(
            id = "ex_07_67",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_67",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Bo'sh joyni to'ldiring: \"I ___ a book, and she ___ a cat.\"",
            subPromptText = "67-Dars: To have — have va has",
            audioTextToSpeak = "I have a book, and she has a cat.",
            optionsPipeSeparated = "have / has|has / have|have / have|is / has",
            correctAnswer = "have / has",
            explanationUzbek = "I bilan \"have\", 3-shaxs birlik (she) bilan \"has\" ishlatiladi.",
            vocabularyIdsCsv = "voc_07_have,voc_07_has",
            grammarRuleIdsCsv = "gram_07_67"
        ),
        ExerciseEntity(
            id = "ex_07_68",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_68",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"Uning mashinasi bormi?\" savolining to'g'ri shaklini tanlang:",
            subPromptText = "68-Dars: Does bilan har doim have keladi!",
            audioTextToSpeak = "Does he have a car?",
            optionsPipeSeparated = "Does he have a car?|Does he has a car?|Do he have a car?|Is he have a car?",
            correctAnswer = "Does he have a car?",
            explanationUzbek = "\"Does\" dan keyin asosiy shakl \"have\" ishlatiladi (\"has\" emas!): \"Does he have a car?\"",
            vocabularyIdsCsv = "voc_07_have,voc_07_does",
            grammarRuleIdsCsv = "gram_07_67"
        ),
        ExerciseEntity(
            id = "ex_07_72",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_72",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"U ishini qilmaydi\" gapining to'g'ri inglizcha tarjimasini toping:",
            subPromptText = "72-Dars: To do — bo'lishsiz",
            audioTextToSpeak = "He doesn't do his work.",
            optionsPipeSeparated = "He doesn't do his work.|He doesn't does his work.|He don't do his work.|He isn't do his work.",
            correctAnswer = "He doesn't do his work.",
            explanationUzbek = "3-shaxs birlikda \"doesn't\" + asosiy fe'l \"do\" keladi: \"He doesn't do his work.\"",
            vocabularyIdsCsv = "voc_07_do,voc_07_does",
            grammarRuleIdsCsv = "gram_07_71"
        ),
        ExerciseEntity(
            id = "ex_07_74",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_74",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "\"U yerga bormang!\" va \"Kechikmang!\" buyruq gaplarini toping:",
            subPromptText = "74-Dars: Bo'lishsiz buyruq — Don't + fe'l",
            audioTextToSpeak = "Don't go there! Don't be late!",
            optionsPipeSeparated = "Don't go there! Don't be late!|Not go there! Not be late!|Doesn't go there! Isn't late!|No go there! No be late!",
            correctAnswer = "Don't go there! Don't be late!",
            explanationUzbek = "Bo'lishsiz buyruq \"Don't + fe'lning asosiy shakli\" bilan yasaladi.",
            vocabularyIdsCsv = "voc_07_do,voc_07_go",
            grammarRuleIdsCsv = "gram_07_71"
        ),
        ExerciseEntity(
            id = "ex_07_76",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_76",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "3-shaxs birlikda (He) \"go\", \"study\" va \"play\" fe'llari qanday yoziladi?",
            subPromptText = "76-Dars: -es, y→ies va unli+y→-s",
            audioTextToSpeak = "He goes, he studies, he plays.",
            optionsPipeSeparated = "goes, studies, plays|gos, studys, plaies|goes, studys, plays|gos, studies, plays",
            correctAnswer = "goes, studies, plays",
            explanationUzbek = "go → goes (-es), study → studies (undosh+y → ies), play → plays (unli+y → -s).",
            vocabularyIdsCsv = "voc_07_go,voc_07_study,voc_07_play",
            grammarRuleIdsCsv = "gram_07_75"
        ),
        ExerciseEntity(
            id = "ex_07_78",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_78",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "Xatoni tuzating: \"Does he works here?\"",
            subPromptText = "78-Dars: Does dan keyin fe'lga -s qo'shilmaydi!",
            audioTextToSpeak = "Does he work here?",
            optionsPipeSeparated = "Does he work here?|Do he works here?|Is he work here?|Does he working here?",
            correctAnswer = "Does he work here?",
            explanationUzbek = "So'roq va bo'lishsizda \"Does / doesn't\" bo'lgani uchun fe'lning o'ziga -s qo'shilmaydi: \"Does he work here?\"",
            vocabularyIdsCsv = "voc_07_does",
            grammarRuleIdsCsv = "gram_07_75"
        ),
        ExerciseEntity(
            id = "ex_07_80",
            topicId = "topic_07_verbs_a1",
            sectionId = "sec_07_80",
            practiceCategory = PracticeCategory.GRAMMAR.id,
            exerciseType = ExerciseType.MULTIPLE_CHOICE.name,
            difficulty = 1,
            promptText = "To'g'ri gaplar qatorini tanlang (To be, To have, Oddiy fe'l):",
            subPromptText = "80-Dars: A1 Fe'llar yakuniy takrorlash",
            audioTextToSpeak = "I am a student. He has a dog. She works here.",
            optionsPipeSeparated = "I am a student. He has a dog. She works here.|I is a student. He have a dog. She work here.|I am a student. He has a dog. She doesn't works here.|I be a student. He has a dog. Does she works here?",
            correctAnswer = "I am a student. He has a dog. She works here.",
            explanationUzbek = "I am (to be), He has (to have 3-shaxs), She works (oddiy fe'l 3-shaxs -s).",
            vocabularyIdsCsv = "voc_07_am,voc_07_has",
            grammarRuleIdsCsv = "gram_07_61,gram_07_67,gram_07_75"
        )
    )
}
