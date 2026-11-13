package com.example.data.local

internal val b1Lessons by lazy { b1Part1 + b1Part2 + b1Part3 + b1Part4 }

internal val b2Lessons = listOf(
    ContinuationLesson(
        "Weighing two proposals", "Ikki taklifni taroziga solish",
        "Ikki taklifning afzalligi va cheklovini muhokama qilib, tanlovingizni asoslang.",
        "Whereas yoki while ikki variantni bir gapda qarama-qarshi qo‘yadi. On balance dalillarni ko‘rib chiqqandan keyingi umumiy xulosani bildiradi. Justify qilish uchun tanlovni mezonga bog‘lang: xarajat, vaqt yoki kimga ta’siri.",
        "Jamoat transportini yaxshilash uchun ikki taklif bering. Har birining bitta foydasi va bitta cheklovini ayting. Tanlovingizni narx yoki foydalanuvchilar soniga bog‘lang.",
        listOf(
            LessonPhrase("initial cost", "boshlang‘ich xarajat", "The initial cost is higher, whereas maintenance is cheaper.", "Boshlang‘ich xarajat yuqoriroq, xizmat ko‘rsatish esa arzonroq."),
            LessonPhrase("reach more residents", "ko‘proq aholiga yetib bormoq", "A later bus could reach more residents who work shifts.", "Kechroq qatnaydigan avtobus smenada ishlaydigan ko‘proq aholiga xizmat qilishi mumkin."),
            LessonPhrase("on balance", "hammasini hisobga olganda", "On balance, I'd choose the option with lower running costs.", "Hammasini hisobga olganda, yuritish xarajati pastroq variantni tanlardim.")
        ),
        listOf(
            question("The app is cheap to launch, ___ a help desk costs more at first.", "whereas", "as a result", "because of", "Whereas ikkita to‘liq fikr orasida farqni ko‘rsatadi."),
            question("Qaysi gap tanlovning ham foydasini, ham narxini hisobga oladi?", "The service costs more, but it would reach more people.", "The service is perfect for everyone.", "The service starts on Monday.", "But bilan kamchilik va foyda bitta fikrda ko‘rsatilgan."),
            question("Audio bo‘yicha taklif nimaga tayangan?", "Kattaroq boshlang‘ich xarajatni uzoq muddatli tejash qoplashiga", "Birinchi yilda xarajat bo‘lmasligiga", "Xizmat ko‘rsatish qimmatlashishiga", "Gapiruvchi dastlabki xarajatga qaramay, keyingi uch yilda tejaladigan mablag‘ni asos qiladi.", "The new system costs more to install. Over three years, though, it should save us enough in maintenance to justify the investment.")
        )
    ),
    ContinuationLesson(
        "Past choices and their consequences", "O‘tgan qaror va uning oqibati",
        "O‘tmishda boshqacha qaror qilinganda qanday natija bo‘lishi mumkinligini ayting.",
        "If + had + fe’lning uchinchi shakli o‘tgan zamondagi ro‘y bermagan shartni bildiradi. Would have + uchinchi shakl tasavvurdagi natija. Might have ehtimoliy natijani ehtiyotkorroq ifodalaydi.",
        "Kech qolish, faylni yo‘qotish yoki noto‘g‘ri reysga chiqish holatidan birini tanlang. Nima bo‘lganini va boshqacha harakat qilinganda nima o‘zgarishini ayting.",
        listOf(
            LessonPhrase("had left earlier", "ertaroq chiqqanimizda", "If we had left earlier, we would have caught the train.", "Ertaroq chiqqanimizda, poyezdga ulgurgan bo‘lardik."),
            LessonPhrase("would have noticed", "payqagan bo‘lardi", "She would have noticed the error if she had checked the totals.", "U jami summalarni tekshirganida, xatoni payqagan bo‘lardi."),
            LessonPhrase("might have avoided", "ehtimol oldini olgan bo‘lardi", "With a backup, we might have avoided the delay.", "Zaxira nusxa bo‘lganida, ehtimol, kechikishning oldini olardik.")
        ),
        listOf(
            question("If they ___ the address, they would have found us.", "had checked", "would have checked", "check", "O‘tgan zamondagi ro‘y bermagan shartda if dan keyin had checked ishlatiladi."),
            question("If I'd seen your message, I would have called. Aslida nima bo‘ldi?", "Xabarni ko‘rmadim va telefon qilmadim.", "Xabarni ko‘rdim va telefon qildim.", "Xabarni ko‘rdim, keyin javob yozdim.", "If I'd seen bu o‘tgan vaziyatda xabar ko‘rilmaganini anglatadi."),
            question("Audio bo‘yicha kechikishning oldini nima olishi mumkin edi?", "Faylning nusxasini saqlash", "Yetkazib beruvchini almashtirish", "Ro‘yxatni qog‘ozga bosish", "Gapiruvchi o‘tgan kechikish sababini zaxira nusxa yo‘qligiga bog‘laydi.", "The file was lost when the laptop failed. If we'd saved a copy online, we might have finished on time.")
        )
    ),
    ContinuationLesson(
        "Making deductions", "Dalildan xulosa chiqarish",
        "Dalilga qarab kuchli xulosa, ehtimol va imkonsizlikni farqlang.",
        "Must have + uchinchi shakl o‘tgan hodisa haqida kuchli taxminni bildiradi. Might have — ehtimol. Can't have — mavjud dalilga ko‘ra o‘sha hodisa bo‘lmaganiga kuchli ishonch. Taxmin darajasini dalilga mos tanlang.",
        "Bo‘sh ofis, yonib turgan chiroq yoki javobsiz xabar misolida ikki izoh bering. Birini kuchli dalil bilan, boshqasini ehtimol sifatida ifodalang.",
        listOf(
            LessonPhrase("must have missed", "o‘tkazib yuborgan bo‘lsa kerak", "She must have missed the announcement. She went to the old office.", "U e’lonni eshitmay qolgan bo‘lsa kerak. U eski idoraga borgan."),
            LessonPhrase("might have taken", "olgan bo‘lishi mumkin", "I might have taken the wrong folder.", "Noto‘g‘ri papkani olgan bo‘lishim mumkin."),
            LessonPhrase("can't have arrived", "yetib kelgan bo‘lishi mumkin emas", "He can't have arrived yet. His train has just left.", "U hali yetib kelgan bo‘lishi mumkin emas. Poyezdi hozirgina jo‘nadi.")
        ),
        listOf(
            question("Faylni ochgan bo‘lishi mumkin, ammo dalil kam. Qaysi ibora mos?", "She might have opened it.", "She must have opened it.", "She can't have opened it.", "Might have dalil kam bo‘lgandagi imkoniyatni bildiradi."),
            question("His passport is here. He ___ it with him.", "can't have taken", "must have taken", "will have taken", "Pasport shu yerda ekanligi uni olib ketgan degan taxminga zid."),
            question("Audio bo‘yicha gapiruvchi bitta sababga qat’iy ishonadimi?", "Yo‘q, ikki ehtimolni ochiq qoldiryapti.", "Ha, internet uzilganini tasdiqlayapti.", "Ha, xabar jo‘natilmaganini tasdiqlayapti.", "Might have va or bilan ikkita ehtimol beriladi.", "They might have missed our email, or their connection might have dropped. We don't have enough information yet.")
        )
    ),
    ContinuationLesson(
        "Writing a recommendation", "Asoslangan tavsiya yozish",
        "O‘quvchi uchun variantlarni mezonlar asosida solishtirib, yozma tavsiya bering.",
        "Tavsiyani vazifa va mezon bilan boshlang. Although ijobiy jihat bilan cheklovni bog‘laydi. I recommend + -ing yoki I recommend that... harakatni aniq ko‘rsatadi. Sababni amaliy oqibatga bog‘lang.",
        "Til klubi uchun onlayn va yuzma-yuz formatni solishtiring. Vaqt, qatnashish imkoniyati va mashq sifatidan ikkitasini mezon qilib, yakunda bitta tavsiya yozing.",
        listOf(
            LessonPhrase("meet the deadline", "muddatga ulgurmoq", "If we need to meet the deadline, the online option is easier to arrange.", "Muddatga ulgurishimiz kerak bo‘lsa, onlayn variantni tashkil qilish osonroq."),
            LessonPhrase("easy to reach", "borish oson", "Although the venue is easy to reach, it only seats twelve.", "Joyga borish oson, ammo u yerda faqat o‘n ikki kishilik o‘rin bor."),
            LessonPhrase("recommend starting", "boshlashni tavsiya qilmoq", "I recommend starting online and reviewing attendance after a month.", "Onlayn boshlashni va bir oydan keyin qatnashuvni ko‘rib chiqishni tavsiya qilaman.")
        ),
        listOf(
            question("I recommend ___ the cheaper plan for one month.", "testing", "to testing", "tested", "Recommend ta’siridagi harakat testing kabi -ing shaklida ifodalanadi."),
            question("Qaysi tavsiya vazifani ham, sababni ham aniq yozadi?", "Start online so evening-shift staff can attend.", "Choose a platform as soon as possible.", "The online survey ended on Friday.", "Start online harakatni, so dan keyingi gap sabab bo‘lgan maqsadni ko‘rsatadi."),
            question("Audio bo‘yicha tavsiya qaysi guruhning muammosiga javob beradi?", "Yig‘ilish vaqti ish smenasiga to‘g‘ri keladiganlarga", "Onlayn darslarni o‘tkazadigan murabbiylarga", "Ro‘yxatdan erta o‘tgan yangi a’zolarga", "People on the late shift can't attend live — kechki smenadagilar jonli qatnasha olmaydi.", "People on the late shift can't attend live. I'd record the meetings and give everyone a way to send comments afterwards.")
        )
    )
)
