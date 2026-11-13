package com.example.data.local

import com.example.core.database.ExerciseEntity
import com.example.domain.model.CurriculumLevel
import com.example.domain.model.ExerciseType
import com.example.domain.model.PracticeCategory

/** Extra practice unlocked after completing the corresponding lesson. */
internal object TextbookPractice {
    private data class Scenario(
        val level: CurriculumLevel,
        val sectionId: String,
        val title: String,
        val readingQuestion: String,
        val reading: String,
        val readingOptions: List<String>,
        val listeningQuestion: String,
        val listening: String,
        val listeningOptions: List<String>,
        val writing: String,
        val answer: String,
        val hint: String,
        val speaking: String
    )

    private val scenarios = listOf(
        Scenario(
            CurriculumLevel.A1, "sec_a1_04", "At the Library",
            "Where can you leave your bag?",
            "You can leave your bag under the desk. You must show your card at the door.",
            listOf("Stol tagida", "Eshik orqasida", "Deraza oldida"),
            "Kitobni qayerga qo‘yish kerak?",
            "You can sit by the window. Please put the book on the desk.",
            listOf("Stol ustiga", "Deraza yoniga", "Stol tagiga"),
            "Inglizcha yozing: Kitobni stol ustiga qo‘ying.",
            "Put the book on the desk.", "Stol ustiga — on the desk.",
            "You are in a library. Ask if you can sit by the window and where you can leave your bag."
        ),
        Scenario(
            CurriculumLevel.A2, "sec_a2_44", "A Meeting Near the Pharmacy",
            "Kirish joyi qayerda?",
            "The café is across the road from the pharmacy. The entrance is beside the bakery, not behind it.",
            listOf("Nonvoyxona yonida", "Nonvoyxona orqasida", "Dorixona ichida"),
            "Uchrashuv uchun qayerda kutish kerak?",
            "Walk past the pharmacy. Wait for me under the clock, just outside the station.",
            listOf("Vokzal tashqarisidagi soat tagida", "Dorixona ichida", "Vokzal orqasida"),
            "Inglizcha yozing: Kafe yo‘lning narigi tomonida.",
            "The café is across the road.", "Yo‘lning narigi tomonida — across the road.",
            "You are meeting a friend near the pharmacy. Explain where the café is. Where should your friend wait?"
        ),
        Scenario(
            CurriculumLevel.B1, "sec_b1_57", "A Change to the Theatre Schedule",
            "Tomoshadan oldin javob olish uchun qachongacha yozish kerak?",
            "The theatre accepts ticket changes until Friday. Email your request by Thursday if you want a reply before the show.",
            listOf("Payshanbagacha", "Jumadan keyin", "Tomosha paytida"),
            "Sumkani qachon olish mumkin?",
            "The theatre opens at six. During the show, please keep your phone silent. You can collect your bag after the show.",
            listOf("Tomoshadan keyin", "Tomosha paytida", "Teatr ochilishidan oldin"),
            "Chiptani almashtirish uchun payshanbagacha javob berishni so‘rang. Reply fe’lini ishlating.",
            "Please reply by Thursday.", "Oxirgi muddat uchun by ishlating.",
            "You need to change a theatre booking. Ask when the office closes, then explain the deadline to a friend using by and until."
        ),
        Scenario(
            CurriculumLevel.B2, "sec_b2_46", "A Workshop for Different Needs",
            "Asosiy xonadan tashqari yana nima bor?",
            "The venue caters for visitors with mobility needs. Besides the main room, two quiet rooms are available. The workshop fee covers materials but not transport.",
            listOf("Ikkita tinch xona", "Bepul transport", "Alohida transport chiptasi"),
            "Belgilarni nima bilan mahkamlashdi?",
            "Maria designed the signs by hand. We attached them with removable clips so the venue can reuse the boards.",
            listOf("Yechib olinadigan qisqichlar bilan", "Yelim bilan", "Mix bilan"),
            "Inglizcha yozing: Markaz turli ehtiyojlarga mos xizmat ko‘rsatadi. Cater fe’lini ishlating.",
            "The centre caters for different needs.", "Cater bilan for ishlatiladi.",
            "You are planning an accessible workshop. Explain what the venue caters for, what comes besides the main room, and how you will attach signs."
        ),
        Scenario(
            CurriculumLevel.C1, "sec_c1_52", "An Update on Behalf of the Board",
            "Joy bo‘yicha tasdiq qaysi shartga bog‘liq?",
            "On behalf of the board, I can confirm the venue is available, subject to a final safety inspection. In the event of cancellation, the fee will be refunded.",
            listOf("Yakuniy xavfsizlik tekshiruvi", "Qo‘shimcha chipta xaridi", "Kengash yig‘ilishining bekor qilinishi"),
            "Kuchli yomg‘ir yog‘sa, ishtirokchilarga nima yuboriladi?",
            "In view of the forecast, we are preparing an indoor venue. In the event of heavy rain, we will send the new address to all attendees.",
            listOf("Yangi manzilni yuborishadi", "Tadbirni boshqa kunga qoldirishadi", "Chipta narxini oshirishadi"),
            "Inglizcha yozing: Men kengash nomidan yozyapman.",
            "I am writing on behalf of the board.", "Kimning nomidan — on behalf of.",
            "You are briefing attendees on behalf of the board. Give a conditional confirmation, then explain what happens in the event of cancellation."
        ),
        Scenario(
            CurriculumLevel.C2, "sec_c2_60", "One Update for Three Audiences",
            "Ish vaqtini doimiy o‘zgartirish uchun nima kerak?",
            "The board approved a four-week trial, subject to weekly reviews. In accordance with the agreement, permanent adoption requires a separate vote.",
            listOf("Doimiy joriy etish uchun alohida ovoz kerak", "Qaror darhol doimiy kuchga kirgan", "Haftalik tekshiruvlar bekor qilingan"),
            "Qo‘shimcha mablag‘ sinovga qanday ta’sir qildi?",
            "With a view to reducing crowding, the council will trial later closing times. But for the extra funding, the pilot would not have been possible.",
            listOf("Qo‘shimcha mablag‘ sinovni amalga oshirishga imkon berdi", "Mablag‘ faqat reklama uchun ajratilgan", "Sinov doimiy joriy etildi"),
            "Inglizcha yozing: Reja yakuniy tasdiq sharti bilan amal qiladi. Subject to iborasini ishlating.",
            "The plan is subject to final approval.", "Shartni saqlang: subject to final approval.",
            "Explain the pilot to a visitor, a staff member and the board. Preserve the same duration, approval condition and next decision in all three versions."
        )
    )

    fun exercises(): List<ExerciseEntity> = scenarios.flatMap { unit ->
        listOf(
            unit.exercise(PracticeCategory.READING, ExerciseType.READING_COMPREHENSION,
                unit.readingQuestion, unit.reading, null,
                unit.readingOptions, unit.readingOptions.first()),
            unit.exercise(PracticeCategory.LISTENING, ExerciseType.LISTENING_COMPREHENSION,
                unit.listeningQuestion, "", unit.listening,
                unit.listeningOptions, unit.listeningOptions.first()),
            unit.exercise(PracticeCategory.WRITING, ExerciseType.WRITING_ANSWERS,
                unit.writing, unit.hint, null, emptyList(), unit.answer),
            unit.exercise(PracticeCategory.SPEAKING, ExerciseType.MULTIPLE_CHOICE,
                unit.speaking, "", null, emptyList(), "")
        )
    }

    private fun Scenario.exercise(
        category: PracticeCategory,
        type: ExerciseType,
        prompt: String,
        context: String,
        audio: String?,
        options: List<String>,
        answer: String
    ) = ExerciseEntity(
        id = "extra_${sectionId}_${category.id.lowercase()}",
        topicId = level.topicId,
        sectionId = sectionId,
        practiceCategory = category.id,
        exerciseType = type.name,
        difficulty = (level.ordinal - 1).coerceIn(1, 5),
        promptText = "$title\n$prompt",
        subPromptText = context,
        audioTextToSpeak = audio,
        optionsPipeSeparated = options.joinToString("|"),
        correctAnswer = answer,
        explanationUzbek = if (category == PracticeCategory.WRITING) hint else answer,
        vocabularyIdsCsv = "",
        grammarRuleIdsCsv = ""
    )
}
