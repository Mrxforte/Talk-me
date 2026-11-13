package com.example.data.local

internal val b1Lessons = listOf(
    ContinuationLesson(
        "The day the lights went out", "Chiroq o‘chgan kun",
        "Voqeani boshi, burilish nuqtasi va yakuni bilan tartibli hikoya qiling.",
        "Davom etayotgan fon uchun Past Continuous, uni bo‘lgan qisqa hodisa uchun Past Simple ishlatiladi. While ko‘pincha davom etayotgan ishni, when esa burilish nuqtasini tanishtiradi. Then va in the end hikoya davomiyligini ko‘rsatadi.",
        "Kutilmagan vaziyatni uch gapda ayting. Avval nima qilayotganingizni, keyin nima bo‘lganini, oxirida ish qanday tugaganini tushuntiring.",
        listOf(
            LessonPhrase("the lights went out", "chiroqlar o‘chdi", "I was making dinner when the lights went out.", "Kechki ovqat tayyorlayotganimda chiroqlar o‘chdi."),
            LessonPhrase("while we were waiting", "biz kutayotgan paytda", "While we were waiting, our neighbour brought a torch.", "Kutayotganimizda qo‘shnimiz fonar olib keldi."),
            LessonPhrase("in the end", "oxir-oqibat", "In the end, we finished dinner by candlelight.", "Oxir-oqibat, kechki ovqatni sham yorug‘ida yedik.")
        ),
        listOf(
            question("I ___ home when my phone rang.", "was walking", "walk", "have walked", "Was walking fonni, rang esa qisqa hodisani beradi."),
            question("I was packing when the taxi arrived. Qaysi ish oldin boshlangan?", "Yuklarni yig‘ish", "Taksining kelishi", "Yuk yig‘ish taksi ketganidan keyin boshlangan", "Was packing taksi kelishidan oldin boshlangan ishni bildiradi."),
            question("Audio bo‘yicha ular nima sababdan piyoda ketishdi?", "Avtobus kutilganidan ancha kech kelishi sababli", "Yo‘lkira qimmatligi sababli", "Bekatga yo‘l yopilgani sababli", "Another forty minutes deyilganidan keyin ular piyoda ketishga qaror qiladi.", "We were waiting for the bus when a driver told us it would be another forty minutes. In the end, we walked.")
        )
    ),
    ContinuationLesson(
        "A plan for bad weather", "Ob-havo o‘zgarsa, rejamiz qanday?",
        "Safar uchun muqobil reja taklif qiling va qachon uni ishlatishni ayting.",
        "Kelajakdagi haqiqiy ehtimol uchun if + Present Simple, asosiy gapda will yoki boshqa mos modal fe’l ishlatiladi. Unless — if ... not, ya’ni «agar ... bo‘lmasa». Sababni because bilan, natijani so bilan bog‘lang.",
        "Ochiq havodagi tadbir uchun asosiy va zaxira reja tuzing. If bilan shartni va because bilan zaxira rejaning sababini ayting.",
        listOf(
            LessonPhrase("if the rain stops", "yomg‘ir tinsa", "If the rain stops, we'll eat outside.", "Yomg‘ir tinsa, tashqarida ovqatlanamiz."),
            LessonPhrase("unless it clears up", "havo ochilmasa", "We'll move the picnic indoors unless it clears up by noon.", "Tushgacha havo ochilmasa, piknikni bino ichiga ko‘chiramiz."),
            LessonPhrase("a backup plan", "zaxira reja", "Let's agree on a backup plan before we leave.", "Yo‘lga chiqishdan oldin zaxira rejani kelishib olaylik.")
        ),
        listOf(
            question("If the wind ___ stronger, we'll stay here.", "gets", "will get", "got", "Haqiqiy kelajak shartining if qismida Present Simple ishlatiladi."),
            question("We won't leave unless the road opens. Qaysi ma’no mos?", "Yo‘l ochilmasa, ketmaymiz.", "Yo‘l yopilsa, ketamiz.", "Yo‘l ochilishidan oldin ketamiz.", "Unless the road opens — yo‘l ochilmasa."),
            question("Audio bo‘yicha ular soy bo‘yiga qachon borishadi?", "Yomg‘ir ertalabgacha to‘xtasa", "Yo‘l kechgacha qurisa", "Yomg‘ir kuchaysa", "Soy bo‘yiga borish yomg‘ir ertalabgacha to‘xtashiga bog‘liq.", "If the rain stops by morning, we'll walk by the river. Otherwise, the museum is our backup plan.")
        )
    ),
    ContinuationLesson(
        "Reporting a message", "Xabarni boshqaga yetkazish",
        "Birovning gapini mazmunini saqlagan holda uchinchi kishiga yetkazing.",
        "Say dan keyin xabarni, tell dan keyin esa kimga aytilganini kiriting: she said that... va she told us that.... O‘tgan paytda berilgan va keyin yetkazilayotgan xabarda will ko‘pincha would ga o‘tadi. Tomorrow ham vaqtga qarab the next day bo‘lishi mumkin.",
        "Hamkasbingiz «I'll call tomorrow» dedi. Xabarni ertasi kuni boshqa kishiga aytib bering. Kim gapirganini va qo‘ng‘iroq qaysi kunga rejalanganini aniq qiling.",
        listOf(
            LessonPhrase("said she was busy", "bandligini aytdi", "Nina said she was busy, so I offered to call later.", "Nina bandligini aytdi, shuning uchun keyinroq qo‘ng‘iroq qilishni taklif qildim."),
            LessonPhrase("told us to wait", "kutishimizni aytdi", "The driver told us to wait by the entrance.", "Haydovchi kirish joyi yonida kutishimizni aytdi."),
            LessonPhrase("would call the next day", "ertasi kuni qo‘ng‘iroq qilmoqchi edi", "He said he would call the next day.", "U ertasi kuni qo‘ng‘iroq qilishini aytdi.")
        ),
        listOf(
            question("Lola ___ me that she was running late.", "told", "said", "spoke", "Told dan keyin xabarni olgan odam bevosita keladi: told me."),
            question("Dushanba kuni Ali «I'll send it tomorrow» dedi. Chorshanba kuni buni qanday yetkazasiz?", "Ali said he would send it the next day.", "Ali said he will send it tomorrow.", "Ali said I would send it the next day.", "Would va the next day dushanba kungi xabarni chorshanbadan turib to‘g‘ri yetkazadi."),
            question("Audio bo‘yicha boshliq ulardan nima qilishni so‘ragan?", "Yangilangan jadvalni chorshanbagacha yuborishni", "Chorshanba kuni uchrashuvni o‘tkazishni", "Jadvalni payshanba kuni tekshirishni", "By Wednesday yuborishning oxirgi muddatini bildiradi.", "I spoke to our manager. She told us to send the updated timetable by Wednesday.")
        )
    ),
    ContinuationLesson(
        "Writing about a booking problem", "Bron bo‘yicha xat",
        "Bron muammosi haqida aniq va muloyim elektron xat yozing.",
        "Xatni I'm writing about... bilan boshlang. Keyin bron qilingan xizmat va haqiqiy vaziyatni fakt bilan tushuntiring. Could you please...? bilan so‘ralayotgan yechimni aniq yozing. Iltimosdan keyin muddat aytish yordam beradi.",
        "Mehmonxona bronida sana xato kiritilgan. Mavzu, bron sanasi, to‘g‘ri sana va aniq iltimosni o‘z ichiga olgan qisqa xat yozing.",
        listOf(
            LessonPhrase("booking reference", "bron raqami", "My booking reference is K84.", "Bron raqamim K84."),
            LessonPhrase("a room for two", "ikki kishilik xona", "I booked a room for two, but the confirmation lists one guest.", "Ikki kishilik xonani bron qilgandim, lekin tasdiqnomada bir mehmon ko‘rsatilgan."),
            LessonPhrase("confirm the change", "o‘zgarishni tasdiqlamoq", "Could you please confirm the change by Friday?", "Iltimos, o‘zgarishni jumagacha tasdiqlab bera olasizmi?")
        ),
        listOf(
            question("Xatning qaysi kirishi muammoni tez aniqlashga yordam beradi?", "I'm writing about booking K84 for 12 June.", "I saw your hotel online last year.", "I hope your week is going well.", "Bron raqami va sana qabul qiluvchiga kerakli yozuvni tez topishga yordam beradi."),
            question("Qaysi gapda muloyim va tekshirib bo‘ladigan iltimos bor?", "Could you please change the booking to 13 June?", "Your system is always a problem.", "I might come to the hotel another day.", "Iltimosda aniq harakat va yangi sana bor."),
            question("Audio bo‘yicha mehmonxona qaysi yechimni taklif qildi?", "Bron sanasini qo‘shimcha to‘lovsiz o‘zgartirish", "Pulni qaytarish", "Kattaroq xonaga o‘tkazish", "Change the date at no extra charge — sanani qo‘shimcha to‘lovsiz o‘zgartirish.", "We can change your booking date at no extra charge. Please reply before noon to confirm.")
        )
    )
)

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
