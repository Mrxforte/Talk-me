package com.example.data.local

internal val b1Part1 = listOf(
    ContinuationLesson(
        "The Day the Lights Went Out", "Chiroq o‘chgan kun",
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
    ContinuationLesson("The Film Had Already Started", "The Film Had Already Started",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Kinoga kechikdik. Ikki o‘tgan ishning oldin bo‘lganini had + fe’lning uchinchi shakli bilan ayting.",
        "The film had already started when we arrived. — Biz kelganimizda film boshlanib bo‘lgan edi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The film had already started when we arrived", "Biz kelganimizda film boshlanib bo‘lgan edi", "The film had already started when we arrived.", "Biz kelganimizda film boshlanib bo‘lgan edi."),
            LessonPhrase("The staff had closed the doors before we got there", "Biz yetib borguncha xodimlar eshiklarni yopib bo‘lgan edi", "The staff had closed the doors before we got there.", "Biz yetib borguncha xodimlar eshiklarni yopib bo‘lgan edi."),
            LessonPhrase("“Could you give me an example?” “The staff had closed the doors before we got there.”", "“Bir misol ayta olasizmi?” “Biz yetib borguncha xodimlar eshiklarni yopib bo‘lgan edi.”", "“Could you give me an example?” “The staff had closed the doors before we got there.”", "“Bir misol ayta olasizmi?” “Biz yetib borguncha xodimlar eshiklarni yopib bo‘lgan edi.”")
        ),
        listOf(
            question("When I reached the cinema, the film ___ already started.", "had", "has", "was", "Kinoga kechikdik. Ikki o‘tgan ishning oldin bo‘lganini had + fe’lning uchinchi shakli bilan ayting."),
            question("«Biz kelganimizda film boshlanib bo‘lgan edi.» gapiga mos jumlani tanlang.", "The film had already started when we arrived.", "The staff had closed the doors before we got there.", "“Could you give me an example?” “The staff had closed the doors before we got there.”", "Biz kelganimizda film boshlanib bo‘lgan edi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Biz yetib borguncha xodimlar eshiklarni yopib bo‘lgan edi.", "Biz kelganimizda film boshlanib bo‘lgan edi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Biz yetib borguncha xodimlar eshiklarni yopib bo‘lgan edi.", "“Could you give me an example?” “The staff had closed the doors before we got there.”")
        )
    ),
    ContinuationLesson("An Hour at the Wrong Stop", "An Hour at the Wrong Stop",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Avtobus kelguncha qancha kutganingizni ayting. Had been + -ing o‘tgan bir paytgacha davom etgan ishni ko‘rsatadi.",
        "I had been waiting for an hour when I checked the map. — Xaritaga qaraganimda bir soatdan beri kutayotgan edim. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("I had been waiting for an hour when I checked the map", "Xaritaga qaraganimda bir soatdan beri kutayotgan edim", "I had been waiting for an hour when I checked the map.", "Xaritaga qaraganimda bir soatdan beri kutayotgan edim."),
            LessonPhrase("We had been standing at the wrong stop before the driver called", "Haydovchi telefon qilguncha noto‘g‘ri bekatda turgan ekanmiz", "We had been standing at the wrong stop before the driver called.", "Haydovchi telefon qilguncha noto‘g‘ri bekatda turgan ekanmiz."),
            LessonPhrase("“Could you give me an example?” “We had been standing at the wrong stop before the driver called.”", "“Bir misol ayta olasizmi?” “Haydovchi telefon qilguncha noto‘g‘ri bekatda turgan ekanmiz.”", "“Could you give me an example?” “We had been standing at the wrong stop before the driver called.”", "“Bir misol ayta olasizmi?” “Haydovchi telefon qilguncha noto‘g‘ri bekatda turgan ekanmiz.”")
        ),
        listOf(
            question("He had been ___ for forty minutes when the bus arrived.", "waiting", "wait", "waited", "Avtobus kelguncha qancha kutganingizni ayting. Had been + -ing o‘tgan bir paytgacha davom etgan ishni ko‘rsatadi."),
            question("«Xaritaga qaraganimda bir soatdan beri kutayotgan edim.» gapiga mos jumlani tanlang.", "I had been waiting for an hour when I checked the map.", "We had been standing at the wrong stop before the driver called.", "“Could you give me an example?” “We had been standing at the wrong stop before the driver called.”", "Xaritaga qaraganimda bir soatdan beri kutayotgan edim."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Haydovchi telefon qilguncha noto‘g‘ri bekatda turgan ekanmiz.", "Xaritaga qaraganimda bir soatdan beri kutayotgan edim.", "Gapiruvchi mavzuni o‘zgartirdi.", "Haydovchi telefon qilguncha noto‘g‘ri bekatda turgan ekanmiz.", "“Could you give me an example?” “We had been standing at the wrong stop before the driver called.”")
        )
    ),
    ContinuationLesson("I’ve Been Waiting Since Nine", "I’ve Been Waiting Since Nine",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Hali ham kutyapsiz. Have yoki has been + -ing ish davom etayotganini ko‘rsatadi. Since boshlanish vaqtini beradi.",
        "I've been waiting since nine. — Soat to‘qqizdan beri kutyapman. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("I've been waiting since nine", "Soat to‘qqizdan beri kutyapman", "I've been waiting since nine.", "Soat to‘qqizdan beri kutyapman."),
            LessonPhrase("She's been calling the office all morning", "U ertalabdan beri idoraga telefon qilyapti", "She's been calling the office all morning.", "U ertalabdan beri idoraga telefon qilyapti."),
            LessonPhrase("“Could you give me an example?” “She's been calling the office all morning.”", "“Bir misol ayta olasizmi?” “U ertalabdan beri idoraga telefon qilyapti.”", "“Could you give me an example?” “She's been calling the office all morning.”", "“Bir misol ayta olasizmi?” “U ertalabdan beri idoraga telefon qilyapti.”")
        ),
        listOf(
            question("We ___ been waiting since noon.", "have", "are", "did", "Hali ham kutyapsiz. Have yoki has been + -ing ish davom etayotganini ko‘rsatadi. Since boshlanish vaqtini beradi."),
            question("«Soat to‘qqizdan beri kutyapman.» gapiga mos jumlani tanlang.", "I've been waiting since nine.", "She's been calling the office all morning.", "“Could you give me an example?” “She's been calling the office all morning.”", "Soat to‘qqizdan beri kutyapman."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "U ertalabdan beri idoraga telefon qilyapti.", "Soat to‘qqizdan beri kutyapman.", "Gapiruvchi mavzuni o‘zgartirdi.", "U ertalabdan beri idoraga telefon qilyapti.", "“Could you give me an example?” “She's been calling the office all morning.”")
        )
    ),
    ContinuationLesson("A Neighbour for Ten Years", "A Neighbour for Ten Years",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Hozir ham davom etadigan tanishlikni ayting. Since bilan boshlanish nuqtasi, for bilan davomiylik keladi.",
        "I've known my neighbour for ten years. — Qo‘shnimni o‘n yildan beri taniyman. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("I've known my neighbour for ten years", "Qo‘shnimni o‘n yildan beri taniyman", "I've known my neighbour for ten years.", "Qo‘shnimni o‘n yildan beri taniyman."),
            LessonPhrase("She's lived next door since 2016", "U 2016-yildan beri qo‘shni uyda yashaydi", "She's lived next door since 2016.", "U 2016-yildan beri qo‘shni uyda yashaydi."),
            LessonPhrase("“Could you give me an example?” “She's lived next door since 2016.”", "“Bir misol ayta olasizmi?” “U 2016-yildan beri qo‘shni uyda yashaydi.”", "“Could you give me an example?” “She's lived next door since 2016.”", "“Bir misol ayta olasizmi?” “U 2016-yildan beri qo‘shni uyda yashaydi.”")
        ),
        listOf(
            question("We've known each other ___ ten years.", "for", "since", "from", "Hozir ham davom etadigan tanishlikni ayting. Since bilan boshlanish nuqtasi, for bilan davomiylik keladi."),
            question("«Qo‘shnimni o‘n yildan beri taniyman.» gapiga mos jumlani tanlang.", "I've known my neighbour for ten years.", "She's lived next door since 2016.", "“Could you give me an example?” “She's lived next door since 2016.”", "Qo‘shnimni o‘n yildan beri taniyman."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "U 2016-yildan beri qo‘shni uyda yashaydi.", "Qo‘shnimni o‘n yildan beri taniyman.", "Gapiruvchi mavzuni o‘zgartirdi.", "U 2016-yildan beri qo‘shni uyda yashaydi.", "“Could you give me an example?” “She's lived next door since 2016.”")
        )
    ),
    ContinuationLesson("Life Before the New Road", "Life Before the New Road",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Eski yo‘l haqida gapiring. Used to + oddiy fe’l ilgari bo‘lgan odat yoki holatni bildiradi.",
        "We used to take the long road to town. — Ilgari shaharga uzoq yo‘ldan borardik. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("We used to take the long road to town", "Ilgari shaharga uzoq yo‘ldan borardik", "We used to take the long road to town.", "Ilgari shaharga uzoq yo‘ldan borardik."),
            LessonPhrase("There used to be a field here", "Ilgari bu yerda dala bor edi", "There used to be a field here.", "Ilgari bu yerda dala bor edi."),
            LessonPhrase("“Could you give me an example?” “There used to be a field here.”", "“Bir misol ayta olasizmi?” “Ilgari bu yerda dala bor edi.”", "“Could you give me an example?” “There used to be a field here.”", "“Bir misol ayta olasizmi?” “Ilgari bu yerda dala bor edi.”")
        ),
        listOf(
            question("We used to ___ through the village.", "drive", "driving", "drove", "Eski yo‘l haqida gapiring. Used to + oddiy fe’l ilgari bo‘lgan odat yoki holatni bildiradi."),
            question("«Ilgari shaharga uzoq yo‘ldan borardik.» gapiga mos jumlani tanlang.", "We used to take the long road to town.", "There used to be a field here.", "“Could you give me an example?” “There used to be a field here.”", "Ilgari shaharga uzoq yo‘ldan borardik."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Ilgari bu yerda dala bor edi.", "Ilgari shaharga uzoq yo‘ldan borardik.", "Gapiruvchi mavzuni o‘zgartirdi.", "Ilgari bu yerda dala bor edi.", "“Could you give me an example?” “There used to be a field here.”")
        )
    ),
    ContinuationLesson("Our Old Weekend Routine", "Our Old Weekend Routine",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Bolalikdagi dam olish kunlarini eslang. O‘tgan vaqt aniq bo‘lsa would + fe’l takroriy harakatni aytadi. Eski holat uchun used to mos.",
        "On Saturdays, Dad would make pancakes. — Shanba kunlari dadam quymoq pishirardi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("On Saturdays, Dad would make pancakes", "Shanba kunlari dadam quymoq pishirardi", "On Saturdays, Dad would make pancakes.", "Shanba kunlari dadam quymoq pishirardi."),
            LessonPhrase("We used to live near the park", "Ilgari bog‘ yaqinida yashardik", "We used to live near the park.", "Ilgari bog‘ yaqinida yashardik."),
            LessonPhrase("“Could you give me an example?” “We used to live near the park.”", "“Bir misol ayta olasizmi?” “Ilgari bog‘ yaqinida yashardik.”", "“Could you give me an example?” “We used to live near the park.”", "“Bir misol ayta olasizmi?” “Ilgari bog‘ yaqinida yashardik.”")
        ),
        listOf(
            question("When we were children, we would ___ in the park every Sunday.", "play", "played", "playing", "Bolalikdagi dam olish kunlarini eslang. O‘tgan vaqt aniq bo‘lsa would + fe’l takroriy harakatni aytadi. Eski holat uchun used to mos."),
            question("«Shanba kunlari dadam quymoq pishirardi.» gapiga mos jumlani tanlang.", "On Saturdays, Dad would make pancakes.", "We used to live near the park.", "“Could you give me an example?” “We used to live near the park.”", "Shanba kunlari dadam quymoq pishirardi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Ilgari bog‘ yaqinida yashardik.", "Shanba kunlari dadam quymoq pishirardi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Ilgari bog‘ yaqinida yashardik.", "“Could you give me an example?” “We used to live near the park.”")
        )
    ),
    ContinuationLesson("I’m Getting Used to Night Shifts", "I’m Getting Used to Night Shifts",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Tungi smenaga moslashyapsiz. Get used to moslashish jarayonini, be used to ko‘nikkan holatni bildiradi. Keyin ot yoki -ing keladi.",
        "I'm getting used to working nights. — Tunda ishlashga ko‘nikyapman. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("I'm getting used to working nights", "Tunda ishlashga ko‘nikyapman", "I'm getting used to working nights.", "Tunda ishlashga ko‘nikyapman."),
            LessonPhrase("I'm used to sleeping during the day now", "Endi kunduzi uxlashga o‘rganib qoldim", "I'm used to sleeping during the day now.", "Endi kunduzi uxlashga o‘rganib qoldim."),
            LessonPhrase("“Could you give me an example?” “I'm used to sleeping during the day now.”", "“Bir misol ayta olasizmi?” “Endi kunduzi uxlashga o‘rganib qoldim.”", "“Could you give me an example?” “I'm used to sleeping during the day now.”", "“Bir misol ayta olasizmi?” “Endi kunduzi uxlashga o‘rganib qoldim.”")
        ),
        listOf(
            question("She's getting used to ___ at night.", "working", "work", "worked", "Tungi smenaga moslashyapsiz. Get used to moslashish jarayonini, be used to ko‘nikkan holatni bildiradi. Keyin ot yoki -ing keladi."),
            question("«Tunda ishlashga ko‘nikyapman.» gapiga mos jumlani tanlang.", "I'm getting used to working nights.", "I'm used to sleeping during the day now.", "“Could you give me an example?” “I'm used to sleeping during the day now.”", "Tunda ishlashga ko‘nikyapman."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Endi kunduzi uxlashga o‘rganib qoldim.", "Tunda ishlashga ko‘nikyapman.", "Gapiruvchi mavzuni o‘zgartirdi.", "Endi kunduzi uxlashga o‘rganib qoldim.", "“Could you give me an example?” “I'm used to sleeping during the day now.”")
        )
    ),
    ContinuationLesson("Where Will You Be at Noon?", "Where Will You Be at Noon?",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Ertaga tushda nima qilayotgan bo‘lishingizni ayting. Will be + -ing kelajakdagi aniq paytda davom etadigan ishni bildiradi.",
        "At noon tomorrow, I'll be meeting a client. — Ertaga tushda mijoz bilan uchrashayotgan bo‘laman. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("At noon tomorrow, I'll be meeting a client", "Ertaga tushda mijoz bilan uchrashayotgan bo‘laman", "At noon tomorrow, I'll be meeting a client.", "Ertaga tushda mijoz bilan uchrashayotgan bo‘laman."),
            LessonPhrase("Don't call at six. We'll be driving home", "Oltida telefon qilmang. Uyga mashinada ketayotgan bo‘lamiz", "Don't call at six. We'll be driving home.", "Oltida telefon qilmang. Uyga mashinada ketayotgan bo‘lamiz."),
            LessonPhrase("“Could you give me an example?” “Don't call at six. We'll be driving home.”", "“Bir misol ayta olasizmi?” “Oltida telefon qilmang. Uyga mashinada ketayotgan bo‘lamiz.”", "“Could you give me an example?” “Don't call at six. We'll be driving home.”", "“Bir misol ayta olasizmi?” “Oltida telefon qilmang. Uyga mashinada ketayotgan bo‘lamiz.”")
        ),
        listOf(
            question("At six, I'll be ___ home.", "driving", "drive", "driven", "Ertaga tushda nima qilayotgan bo‘lishingizni ayting. Will be + -ing kelajakdagi aniq paytda davom etadigan ishni bildiradi."),
            question("«Ertaga tushda mijoz bilan uchrashayotgan bo‘laman.» gapiga mos jumlani tanlang.", "At noon tomorrow, I'll be meeting a client.", "Don't call at six. We'll be driving home.", "“Could you give me an example?” “Don't call at six. We'll be driving home.”", "Ertaga tushda mijoz bilan uchrashayotgan bo‘laman."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Oltida telefon qilmang. Uyga mashinada ketayotgan bo‘lamiz.", "Ertaga tushda mijoz bilan uchrashayotgan bo‘laman.", "Gapiruvchi mavzuni o‘zgartirdi.", "Oltida telefon qilmang. Uyga mashinada ketayotgan bo‘lamiz.", "“Could you give me an example?” “Don't call at six. We'll be driving home.”")
        )
    ),
    ContinuationLesson("The Library Will Have Closed", "The Library Will Have Closed",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Kutubxonaga kech qolishingiz mumkin. Will have + uchinchi shakl kelajakdagi vaqtgacha tugagan ishni bildiradi.",
        "By eight, the library will have closed. — Soat sakkizgacha kutubxona yopilib bo‘ladi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("By eight, the library will have closed", "Soat sakkizgacha kutubxona yopilib bo‘ladi", "By eight, the library will have closed.", "Soat sakkizgacha kutubxona yopilib bo‘ladi."),
            LessonPhrase("By Friday, we'll have returned all the books", "Jumagacha barcha kitoblarni qaytarib bo‘lamiz", "By Friday, we'll have returned all the books.", "Jumagacha barcha kitoblarni qaytarib bo‘lamiz."),
            LessonPhrase("“Could you give me an example?” “By Friday, we'll have returned all the books.”", "“Bir misol ayta olasizmi?” “Jumagacha barcha kitoblarni qaytarib bo‘lamiz.”", "“Could you give me an example?” “By Friday, we'll have returned all the books.”", "“Bir misol ayta olasizmi?” “Jumagacha barcha kitoblarni qaytarib bo‘lamiz.”")
        ),
        listOf(
            question("By eight, the library will ___ closed.", "have", "has", "be", "Kutubxonaga kech qolishingiz mumkin. Will have + uchinchi shakl kelajakdagi vaqtgacha tugagan ishni bildiradi."),
            question("«Soat sakkizgacha kutubxona yopilib bo‘ladi.» gapiga mos jumlani tanlang.", "By eight, the library will have closed.", "By Friday, we'll have returned all the books.", "“Could you give me an example?” “By Friday, we'll have returned all the books.”", "Soat sakkizgacha kutubxona yopilib bo‘ladi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Jumagacha barcha kitoblarni qaytarib bo‘lamiz.", "Soat sakkizgacha kutubxona yopilib bo‘ladi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Jumagacha barcha kitoblarni qaytarib bo‘lamiz.", "“Could you give me an example?” “By Friday, we'll have returned all the books.”")
        )
    ),
    ContinuationLesson("He Was Going to Call", "He Was Going to Call",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Eski rejani eslang. Was yoki were going to + fe’l o‘tmishdagi niyatni bildiradi. Keyin reja nima bo‘lganini aytish mumkin.",
        "He was going to call, but his phone died. — U telefon qilmoqchi edi, lekin telefoni o‘chib qoldi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("He was going to call, but his phone died", "U telefon qilmoqchi edi, lekin telefoni o‘chib qoldi", "He was going to call, but his phone died.", "U telefon qilmoqchi edi, lekin telefoni o‘chib qoldi."),
            LessonPhrase("We were going to leave early, but it snowed", "Erta ketmoqchi edik, lekin qor yog‘di", "We were going to leave early, but it snowed.", "Erta ketmoqchi edik, lekin qor yog‘di."),
            LessonPhrase("“Could you give me an example?” “We were going to leave early, but it snowed.”", "“Bir misol ayta olasizmi?” “Erta ketmoqchi edik, lekin qor yog‘di.”", "“Could you give me an example?” “We were going to leave early, but it snowed.”", "“Bir misol ayta olasizmi?” “Erta ketmoqchi edik, lekin qor yog‘di.”")
        ),
        listOf(
            question("I was going to ___ you yesterday.", "call", "calling", "called", "Eski rejani eslang. Was yoki were going to + fe’l o‘tmishdagi niyatni bildiradi. Keyin reja nima bo‘lganini aytish mumkin."),
            question("«U telefon qilmoqchi edi, lekin telefoni o‘chib qoldi.» gapiga mos jumlani tanlang.", "He was going to call, but his phone died.", "We were going to leave early, but it snowed.", "“Could you give me an example?” “We were going to leave early, but it snowed.”", "U telefon qilmoqchi edi, lekin telefoni o‘chib qoldi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "Erta ketmoqchi edik, lekin qor yog‘di.", "U telefon qilmoqchi edi, lekin telefoni o‘chib qoldi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Erta ketmoqchi edik, lekin qor yog‘di.", "“Could you give me an example?” “We were going to leave early, but it snowed.”")
        )
    ),
    ContinuationLesson(
        "Reporting a Message", "Xabarni boshqaga yetkazish",
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
    ContinuationLesson("The Driver Asked Us to Wait", "The Driver Asked Us to Wait",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Haydovchi iltimosini yetkazing. Ask + kim + to + fe’l. Qilmaslik iltimosida not to ishlatiladi.",
        "The driver asked us to wait outside. — Haydovchi bizdan tashqarida kutishimizni so‘radi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The driver asked us to wait outside", "Haydovchi bizdan tashqarida kutishimizni so‘radi", "The driver asked us to wait outside.", "Haydovchi bizdan tashqarida kutishimizni so‘radi."),
            LessonPhrase("She told me not to block the door", "U eshikni to‘sib qo‘ymasligimni aytdi", "She told me not to block the door.", "U eshikni to‘sib qo‘ymasligimni aytdi."),
            LessonPhrase("“Could you give me an example?” “She told me not to block the door.”", "“Bir misol ayta olasizmi?” “U eshikni to‘sib qo‘ymasligimni aytdi.”", "“Could you give me an example?” “She told me not to block the door.”", "“Bir misol ayta olasizmi?” “U eshikni to‘sib qo‘ymasligimni aytdi.”")
        ),
        listOf(
            question("They asked us ___ outside.", "to wait", "waited", "waiting", "Haydovchi iltimosini yetkazing. Ask + kim + to + fe’l. Qilmaslik iltimosida not to ishlatiladi."),
            question("«Haydovchi bizdan tashqarida kutishimizni so‘radi.» gapiga mos jumlani tanlang.", "The driver asked us to wait outside.", "She told me not to block the door.", "“Could you give me an example?” “She told me not to block the door.”", "Haydovchi bizdan tashqarida kutishimizni so‘radi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "U eshikni to‘sib qo‘ymasligimni aytdi.", "Haydovchi bizdan tashqarida kutishimizni so‘radi.", "Gapiruvchi mavzuni o‘zgartirdi.", "U eshikni to‘sib qo‘ymasligimni aytdi.", "“Could you give me an example?” “She told me not to block the door.”")
        )
    ),
    ContinuationLesson("She Asked Where I Lived", "She Asked Where I Lived",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Birovning savolini yetkazing. Asked where dan keyin ega, keyin fe’l keladi. Ha yoki yo‘q savoli if yoki whether bilan boshlanishi mumkin.",
        "She asked where I lived. — U qayerda yashashimni so‘radi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("She asked where I lived", "U qayerda yashashimni so‘radi", "She asked where I lived.", "U qayerda yashashimni so‘radi."),
            LessonPhrase("He asked if I needed a taxi", "U menga taksi kerakmi deb so‘radi", "He asked if I needed a taxi.", "U menga taksi kerakmi deb so‘radi."),
            LessonPhrase("“Could you give me an example?” “He asked if I needed a taxi.”", "“Bir misol ayta olasizmi?” “U menga taksi kerakmi deb so‘radi.”", "“Could you give me an example?” “He asked if I needed a taxi.”", "“Bir misol ayta olasizmi?” “U menga taksi kerakmi deb so‘radi.”")
        ),
        listOf(
            question("She asked where ___.", "I lived", "did I live", "live did I", "Birovning savolini yetkazing. Asked where dan keyin ega, keyin fe’l keladi. Ha yoki yo‘q savoli if yoki whether bilan boshlanishi mumkin."),
            question("«U qayerda yashashimni so‘radi.» gapiga mos jumlani tanlang.", "She asked where I lived.", "He asked if I needed a taxi.", "“Could you give me an example?” “He asked if I needed a taxi.”", "U qayerda yashashimni so‘radi."),
            question("Audio orqali ikkinchi gapning ma’nosini toping.", "U menga taksi kerakmi deb so‘radi.", "U qayerda yashashimni so‘radi.", "Gapiruvchi mavzuni o‘zgartirdi.", "U menga taksi kerakmi deb so‘radi.", "“Could you give me an example?” “He asked if I needed a taxi.”")
        )
    ),
    ContinuationLesson(
        "Writing About a Booking Problem", "Bron bo‘yicha xat",
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
