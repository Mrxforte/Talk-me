package com.example.data.local

internal val b2Part1 = listOf(
    ContinuationLesson(
        "Weighing Two Proposals", "Ikki taklifni taroziga solish",
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
        "Writing a Recommendation", "Asoslangan tavsiya yozish",
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
    ),
    ContinuationLesson("The Route We Should Have Taken", "The Route We Should Have Taken",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Safardan keyin xatoni ayting. Should have + uchinchi shakl o‘tmishda qilish ma’qul bo‘lgan ishni bildiradi.",
        "We should have taken the shorter route. — Qisqaroq yo‘ldan borishimiz kerak edi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("We should have taken the shorter route", "Qisqaroq yo‘ldan borishimiz kerak edi", "We should have taken the shorter route.", "Qisqaroq yo‘ldan borishimiz kerak edi."),
            LessonPhrase("I should have checked the map", "Xaritani tekshirishim kerak edi", "I should have checked the map.", "Xaritani tekshirishim kerak edi."),
            LessonPhrase("“Could you give me an example?” “I should have checked the map.”", "“Bir misol ayta olasizmi?” “Xaritani tekshirishim kerak edi.”", "“Could you give me an example?” “I should have checked the map.”", "“Bir misol ayta olasizmi?” “Xaritani tekshirishim kerak edi.”")
        ),
        listOf(
            question("We should have ___ the map.", "checked", "check", "checking", "Safardan keyin xatoni ayting. Should have + uchinchi shakl o‘tmishda qilish ma’qul bo‘lgan ishni bildiradi."),
            question("«Qisqaroq yo‘ldan borishimiz kerak edi.» gapiga mos jumlani tanlang.", "We should have taken the shorter route.", "I should have checked the map.", "“Could you give me an example?” “I should have checked the map.”", "Qisqaroq yo‘ldan borishimiz kerak edi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Xaritani tekshirishim kerak edi.", "Qisqaroq yo‘ldan borishimiz kerak edi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Xaritani tekshirishim kerak edi.", "“Could you give me an example?” “I should have checked the map.”")
        )
    ),
    ContinuationLesson(
        "Past Choices and Their Consequences", "O‘tgan qaror va uning oqibati",
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
    ContinuationLesson("If We’d Moved Closer", "If We’d Moved Closer",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Oldingi qaror hozirga ta’sir qilganini ayting. If + had + uchinchi shakl, natijada would + oddiy fe’l.",
        "If we'd moved closer, we'd walk to work now. — Yaqinroqqa ko‘chganimizda, hozir ishga piyoda borardik. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("If we'd moved closer, we'd walk to work now", "Yaqinroqqa ko‘chganimizda, hozir ishga piyoda borardik", "If we'd moved closer, we'd walk to work now.", "Yaqinroqqa ko‘chganimizda, hozir ishga piyoda borardik."),
            LessonPhrase("If I'd kept the key, I could open the door now", "Kalitni saqlaganimda, hozir eshikni ocha olardim", "If I'd kept the key, I could open the door now.", "Kalitni saqlaganimda, hozir eshikni ocha olardim."),
            LessonPhrase("“Could you give me an example?” “If I'd kept the key, I could open the door now.”", "“Bir misol ayta olasizmi?” “Kalitni saqlaganimda, hozir eshikni ocha olardim.”", "“Could you give me an example?” “If I'd kept the key, I could open the door now.”", "“Bir misol ayta olasizmi?” “Kalitni saqlaganimda, hozir eshikni ocha olardim.”")
        ),
        listOf(
            question("If we'd moved closer, we ___ less time commuting now.", "would spend", "would have spend", "had spend", "Oldingi qaror hozirga ta’sir qilganini ayting. If + had + uchinchi shakl, natijada would + oddiy fe’l."),
            question("«Yaqinroqqa ko‘chganimizda, hozir ishga piyoda borardik.» gapiga mos jumlani tanlang.", "If we'd moved closer, we'd walk to work now.", "If I'd kept the key, I could open the door now.", "“Could you give me an example?” “If I'd kept the key, I could open the door now.”", "Yaqinroqqa ko‘chganimizda, hozir ishga piyoda borardik."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Kalitni saqlaganimda, hozir eshikni ocha olardim.", "Yaqinroqqa ko‘chganimizda, hozir ishga piyoda borardik.", "Gapiruvchi mavzuni o‘zgartirdi.", "Kalitni saqlaganimda, hozir eshikni ocha olardim.", "“Could you give me an example?” “If I'd kept the key, I could open the door now.”")
        )
    ),
    ContinuationLesson("I Wish I’d Checked the Address", "I Wish I’d Checked the Address",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "O‘tgan xatodan afsuslanishda wish yoki if only dan keyin had + uchinchi shakl keladi.",
        "I wish I'd checked the address. — Qaniydi manzilni tekshirgan bo‘lsam. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("I wish I'd checked the address", "Qaniydi manzilni tekshirgan bo‘lsam", "I wish I'd checked the address.", "Qaniydi manzilni tekshirgan bo‘lsam."),
            LessonPhrase("If only we'd booked earlier", "Qaniydi ertaroq band qilgan bo‘lsak", "If only we'd booked earlier.", "Qaniydi ertaroq band qilgan bo‘lsak."),
            LessonPhrase("“Could you give me an example?” “If only we'd booked earlier.”", "“Bir misol ayta olasizmi?” “Qaniydi ertaroq band qilgan bo‘lsak.”", "“Could you give me an example?” “If only we'd booked earlier.”", "“Bir misol ayta olasizmi?” “Qaniydi ertaroq band qilgan bo‘lsak.”")
        ),
        listOf(
            question("I wish I had ___ the address.", "checked", "check", "checking", "O‘tgan xatodan afsuslanishda wish yoki if only dan keyin had + uchinchi shakl keladi."),
            question("«Qaniydi manzilni tekshirgan bo‘lsam.» gapiga mos jumlani tanlang.", "I wish I'd checked the address.", "If only we'd booked earlier.", "“Could you give me an example?” “If only we'd booked earlier.”", "Qaniydi manzilni tekshirgan bo‘lsam."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Qaniydi ertaroq band qilgan bo‘lsak.", "Qaniydi manzilni tekshirgan bo‘lsam.", "Gapiruvchi mavzuni o‘zgartirdi.", "Qaniydi ertaroq band qilgan bo‘lsak.", "“Could you give me an example?” “If only we'd booked earlier.”")
        )
    ),
    ContinuationLesson(
        "Making Deductions", "Dalildan xulosa chiqarish",
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
    ContinuationLesson("You Needn’t Have Printed It", "You Needn’t Have Printed It",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Needn’t have done ish bajarilganini va keraksiz bo‘lganini bildiradi. Didn’t need to faqat zarurat bo‘lmaganini aytadi.",
        "You needn't have printed the ticket. — Chiptani bosib chiqaribsiz, aslida shart emas edi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("You needn't have printed the ticket", "Chiptani bosib chiqaribsiz, aslida shart emas edi", "You needn't have printed the ticket.", "Chiptani bosib chiqaribsiz, aslida shart emas edi."),
            LessonPhrase("I didn't need to print it, so I didn't", "Uni bosib chiqarish shart emas edi, shuning uchun chiqarmadim", "I didn't need to print it, so I didn't.", "Uni bosib chiqarish shart emas edi, shuning uchun chiqarmadim."),
            LessonPhrase("“Could you give me an example?” “I didn't need to print it, so I didn't.”", "“Bir misol ayta olasizmi?” “Uni bosib chiqarish shart emas edi, shuning uchun chiqarmadim.”", "“Could you give me an example?” “I didn't need to print it, so I didn't.”", "“Bir misol ayta olasizmi?” “Uni bosib chiqarish shart emas edi, shuning uchun chiqarmadim.”")
        ),
        listOf(
            question("You printed it, but it was unnecessary. You ___ printed it.", "needn't have", "must have", "should have", "Needn’t have done ish bajarilganini va keraksiz bo‘lganini bildiradi. Didn’t need to faqat zarurat bo‘lmaganini aytadi."),
            question("«Chiptani bosib chiqaribsiz, aslida shart emas edi.» gapiga mos jumlani tanlang.", "You needn't have printed the ticket.", "I didn't need to print it, so I didn't.", "“Could you give me an example?” “I didn't need to print it, so I didn't.”", "Chiptani bosib chiqaribsiz, aslida shart emas edi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Uni bosib chiqarish shart emas edi, shuning uchun chiqarmadim.", "Chiptani bosib chiqaribsiz, aslida shart emas edi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Uni bosib chiqarish shart emas edi, shuning uchun chiqarmadim.", "“Could you give me an example?” “I didn't need to print it, so I didn't.”")
        )
    ),
    ContinuationLesson("We Ought to Tell the Team", "We Ought to Tell the Team",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Jamoa oldidagi mas’uliyatni ought to yoki should bilan ayting. Ought dan keyin to keladi.",
        "We ought to tell the team about the delay. — Kechikish haqida jamoaga aytishimiz kerak. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("We ought to tell the team about the delay", "Kechikish haqida jamoaga aytishimiz kerak", "We ought to tell the team about the delay.", "Kechikish haqida jamoaga aytishimiz kerak."),
            LessonPhrase("We should share the updated schedule today", "Bugun yangi jadvalni ulashishimiz ma’qul", "We should share the updated schedule today.", "Bugun yangi jadvalni ulashishimiz ma’qul."),
            LessonPhrase("“Could you give me an example?” “We should share the updated schedule today.”", "“Bir misol ayta olasizmi?” “Bugun yangi jadvalni ulashishimiz ma’qul.”", "“Could you give me an example?” “We should share the updated schedule today.”", "“Bir misol ayta olasizmi?” “Bugun yangi jadvalni ulashishimiz ma’qul.”")
        ),
        listOf(
            question("We ought ___ the team today.", "to tell", "telling", "told", "Jamoa oldidagi mas’uliyatni ought to yoki should bilan ayting. Ought dan keyin to keladi."),
            question("«Kechikish haqida jamoaga aytishimiz kerak.» gapiga mos jumlani tanlang.", "We ought to tell the team about the delay.", "We should share the updated schedule today.", "“Could you give me an example?” “We should share the updated schedule today.”", "Kechikish haqida jamoaga aytishimiz kerak."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Bugun yangi jadvalni ulashishimiz ma’qul.", "Kechikish haqida jamoaga aytishimiz kerak.", "Gapiruvchi mavzuni o‘zgartirdi.", "Bugun yangi jadvalni ulashishimiz ma’qul.", "“Could you give me an example?” “We should share the updated schedule today.”")
        )
    ),
    ContinuationLesson("The Museum May Be Closed", "The Museum May Be Closed",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Hozirgi holat haqida may yoki might ehtimolni, must kuchli xulosani, can't esa imkonsizlikni bildiradi.",
        "The museum may be closed today. — Muzey bugun yopiq bo‘lishi mumkin. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The museum may be closed today", "Muzey bugun yopiq bo‘lishi mumkin", "The museum may be closed today.", "Muzey bugun yopiq bo‘lishi mumkin."),
            LessonPhrase("The lights are on. Someone must be inside", "Chiroqlar yoniq. Ichkarida kimdir bo‘lsa kerak", "The lights are on. Someone must be inside.", "Chiroqlar yoniq. Ichkarida kimdir bo‘lsa kerak."),
            LessonPhrase("“Could you give me an example?” “The lights are on. Someone must be inside.”", "“Bir misol ayta olasizmi?” “Chiroqlar yoniq. Ichkarida kimdir bo‘lsa kerak.”", "“Could you give me an example?” “The lights are on. Someone must be inside.”", "“Bir misol ayta olasizmi?” “Chiroqlar yoniq. Ichkarida kimdir bo‘lsa kerak.”")
        ),
        listOf(
            question("I’m not sure. The museum ___ be closed.", "may", "has", "does", "Hozirgi holat haqida may yoki might ehtimolni, must kuchli xulosani, can't esa imkonsizlikni bildiradi."),
            question("«Muzey bugun yopiq bo‘lishi mumkin.» gapiga mos jumlani tanlang.", "The museum may be closed today.", "The lights are on. Someone must be inside.", "“Could you give me an example?” “The lights are on. Someone must be inside.”", "Muzey bugun yopiq bo‘lishi mumkin."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Chiroqlar yoniq. Ichkarida kimdir bo‘lsa kerak.", "Muzey bugun yopiq bo‘lishi mumkin.", "Gapiruvchi mavzuni o‘zgartirdi.", "Chiroqlar yoniq. Ichkarida kimdir bo‘lsa kerak.", "“Could you give me an example?” “The lights are on. Someone must be inside.”")
        )
    ),
    ContinuationLesson("You Might Want to Call First", "You Might Want to Call First",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Maslahatni yumshatish uchun You might want to + fe’l ishlating. Bu maslahatni tanlov sifatida beradi.",
        "You might want to call first. — Avval telefon qilib ko‘rsangiz yaxshi bo‘lardi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("You might want to call first", "Avval telefon qilib ko‘rsangiz yaxshi bo‘lardi", "You might want to call first.", "Avval telefon qilib ko‘rsangiz yaxshi bo‘lardi."),
            LessonPhrase("You could try another entrance", "Boshqa kirish joyini sinab ko‘rsangiz bo‘ladi", "You could try another entrance.", "Boshqa kirish joyini sinab ko‘rsangiz bo‘ladi."),
            LessonPhrase("“Could you give me an example?” “You could try another entrance.”", "“Bir misol ayta olasizmi?” “Boshqa kirish joyini sinab ko‘rsangiz bo‘ladi.”", "“Could you give me an example?” “You could try another entrance.”", "“Bir misol ayta olasizmi?” “Boshqa kirish joyini sinab ko‘rsangiz bo‘ladi.”")
        ),
        listOf(
            question("You might want ___ before leaving.", "to call", "calling", "called", "Maslahatni yumshatish uchun You might want to + fe’l ishlating. Bu maslahatni tanlov sifatida beradi."),
            question("«Avval telefon qilib ko‘rsangiz yaxshi bo‘lardi.» gapiga mos jumlani tanlang.", "You might want to call first.", "You could try another entrance.", "“Could you give me an example?” “You could try another entrance.”", "Avval telefon qilib ko‘rsangiz yaxshi bo‘lardi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Boshqa kirish joyini sinab ko‘rsangiz bo‘ladi.", "Avval telefon qilib ko‘rsangiz yaxshi bo‘lardi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Boshqa kirish joyini sinab ko‘rsangiz bo‘ladi.", "“Could you give me an example?” “You could try another entrance.”")
        )
    ),
    ContinuationLesson("The Documents Are to Be Signed", "The Documents Are to Be Signed",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Rasmiy ko‘rsatmada be to + fe’l ishlatiladi. Are to be signed shakli imzolash talabini bildiradi.",
        "The documents are to be signed by Friday. — Hujjatlar jumagacha imzolanishi kerak. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The documents are to be signed by Friday", "Hujjatlar jumagacha imzolanishi kerak", "The documents are to be signed by Friday.", "Hujjatlar jumagacha imzolanishi kerak."),
            LessonPhrase("Visitors are to report to reception", "Mehmonlar qabul bo‘limiga murojaat qilishi kerak", "Visitors are to report to reception.", "Mehmonlar qabul bo‘limiga murojaat qilishi kerak."),
            LessonPhrase("“Could you give me an example?” “Visitors are to report to reception.”", "“Bir misol ayta olasizmi?” “Mehmonlar qabul bo‘limiga murojaat qilishi kerak.”", "“Could you give me an example?” “Visitors are to report to reception.”", "“Bir misol ayta olasizmi?” “Mehmonlar qabul bo‘limiga murojaat qilishi kerak.”")
        ),
        listOf(
            question("The form is to be ___ before noon.", "signed", "sign", "signing", "Rasmiy ko‘rsatmada be to + fe’l ishlatiladi. Are to be signed shakli imzolash talabini bildiradi."),
            question("«Hujjatlar jumagacha imzolanishi kerak.» gapiga mos jumlani tanlang.", "The documents are to be signed by Friday.", "Visitors are to report to reception.", "“Could you give me an example?” “Visitors are to report to reception.”", "Hujjatlar jumagacha imzolanishi kerak."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Mehmonlar qabul bo‘limiga murojaat qilishi kerak.", "Hujjatlar jumagacha imzolanishi kerak.", "Gapiruvchi mavzuni o‘zgartirdi.", "Mehmonlar qabul bo‘limiga murojaat qilishi kerak.", "“Could you give me an example?” “Visitors are to report to reception.”")
        )
    ),
    ContinuationLesson("I Had My Bike Repaired", "I Had My Bike Repaired",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Xizmatni boshqa odam bajarganida have yoki get + narsa + uchinchi shakl ishlating.",
        "I had my bike repaired yesterday. — Kecha velosipedimni ustaga tuzattirdim. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("I had my bike repaired yesterday", "Kecha velosipedimni ustaga tuzattirdim", "I had my bike repaired yesterday.", "Kecha velosipedimni ustaga tuzattirdim."),
            LessonPhrase("We got the windows cleaned before the visit", "Tashrifdan oldin derazalarni tozalatdik", "We got the windows cleaned before the visit.", "Tashrifdan oldin derazalarni tozalatdik."),
            LessonPhrase("“Could you give me an example?” “We got the windows cleaned before the visit.”", "“Bir misol ayta olasizmi?” “Tashrifdan oldin derazalarni tozalatdik.”", "“Could you give me an example?” “We got the windows cleaned before the visit.”", "“Bir misol ayta olasizmi?” “Tashrifdan oldin derazalarni tozalatdik.”")
        ),
        listOf(
            question("She had her phone ___ at the shop.", "fixed", "fix", "fixing", "Xizmatni boshqa odam bajarganida have yoki get + narsa + uchinchi shakl ishlating."),
            question("«Kecha velosipedimni ustaga tuzattirdim.» gapiga mos jumlani tanlang.", "I had my bike repaired yesterday.", "We got the windows cleaned before the visit.", "“Could you give me an example?” “We got the windows cleaned before the visit.”", "Kecha velosipedimni ustaga tuzattirdim."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Tashrifdan oldin derazalarni tozalatdik.", "Kecha velosipedimni ustaga tuzattirdim.", "Gapiruvchi mavzuni o‘zgartirdi.", "Tashrifdan oldin derazalarni tozalatdik.", "“Could you give me an example?” “We got the windows cleaned before the visit.”")
        )
    ),
    ContinuationLesson("The File Got Deleted", "The File Got Deleted",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Kundalik suhbatda get + uchinchi shakl hodisani ta’kidlaydi. Rasmiyroq bayonda be bilan majhul shakl ham ishlatiladi.",
        "The file got deleted during the update. — Fayl yangilanish paytida o‘chib ketdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The file got deleted during the update", "Fayl yangilanish paytida o‘chib ketdi", "The file got deleted during the update.", "Fayl yangilanish paytida o‘chib ketdi."),
            LessonPhrase("My bag got damaged on the flight", "Sumkam parvoz paytida shikastlandi", "My bag got damaged on the flight.", "Sumkam parvoz paytida shikastlandi."),
            LessonPhrase("“Could you give me an example?” “My bag got damaged on the flight.”", "“Bir misol ayta olasizmi?” “Sumkam parvoz paytida shikastlandi.”", "“Could you give me an example?” “My bag got damaged on the flight.”", "“Bir misol ayta olasizmi?” “Sumkam parvoz paytida shikastlandi.”")
        ),
        listOf(
            question("The file got ___ during the update.", "deleted", "delete", "deleting", "Kundalik suhbatda get + uchinchi shakl hodisani ta’kidlaydi. Rasmiyroq bayonda be bilan majhul shakl ham ishlatiladi."),
            question("«Fayl yangilanish paytida o‘chib ketdi.» gapiga mos jumlani tanlang.", "The file got deleted during the update.", "My bag got damaged on the flight.", "“Could you give me an example?” “My bag got damaged on the flight.”", "Fayl yangilanish paytida o‘chib ketdi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Sumkam parvoz paytida shikastlandi.", "Fayl yangilanish paytida o‘chib ketdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Sumkam parvoz paytida shikastlandi.", "“Could you give me an example?” “My bag got damaged on the flight.”")
        )
    ),
    ContinuationLesson("Payment Must Be Confirmed", "Payment Must Be Confirmed",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Talabni bajaruvchisini aytmasdan modal + be + uchinchi shakl bilan ifodalang.",
        "Payment must be confirmed today. — To‘lov bugun tasdiqlanishi shart. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("Payment must be confirmed today", "To‘lov bugun tasdiqlanishi shart", "Payment must be confirmed today.", "To‘lov bugun tasdiqlanishi shart."),
            LessonPhrase("The receipt can be sent by email", "Chekni email orqali yuborish mumkin", "The receipt can be sent by email.", "Chekni email orqali yuborish mumkin."),
            LessonPhrase("“Could you give me an example?” “The receipt can be sent by email.”", "“Bir misol ayta olasizmi?” “Chekni email orqali yuborish mumkin.”", "“Could you give me an example?” “The receipt can be sent by email.”", "“Bir misol ayta olasizmi?” “Chekni email orqali yuborish mumkin.”")
        ),
        listOf(
            question("The payment must ___ confirmed.", "be", "been", "being", "Talabni bajaruvchisini aytmasdan modal + be + uchinchi shakl bilan ifodalang."),
            question("«To‘lov bugun tasdiqlanishi shart.» gapiga mos jumlani tanlang.", "Payment must be confirmed today.", "The receipt can be sent by email.", "“Could you give me an example?” “The receipt can be sent by email.”", "To‘lov bugun tasdiqlanishi shart."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Chekni email orqali yuborish mumkin.", "To‘lov bugun tasdiqlanishi shart.", "Gapiruvchi mavzuni o‘zgartirdi.", "Chekni email orqali yuborish mumkin.", "“Could you give me an example?” “The receipt can be sent by email.”")
        )
    )
)
