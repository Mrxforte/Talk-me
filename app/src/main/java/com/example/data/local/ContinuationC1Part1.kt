package com.example.data.local

internal val c1Part1 = listOf(
    ContinuationLesson(
        "Interpreting Indirect Feedback", "Bilvosita fikrni tushunish",
        "Odobli ifodalangan tanqid yoki ikkilanishni kontekstdan aniqlang va aniqlashtiring.",
        "Could be clearer takomillashtirish so‘rovini yumshatadi. I'm not entirely convinced e’tirozga ishora qiladi. Noaniq fikrni qat’iy rad javobiga aylantirishdan oldin Could you clarify...? bilan muammoni aniqlang.",
        "Hamkasbingiz «The timeline might be a little ambitious» dedi. U nimadan xavotir olayotganini taxmin qiling va bitta aniq savol bilan tekshiring.",
        listOf(
            LessonPhrase("could be clearer", "aniqroq ifodalasa bo‘ladi", "The conclusion could be clearer about who will implement the plan.", "Xulosada rejani kim amalga oshirishi aniqroq ifodalansa yaxshi bo‘lardi."),
            LessonPhrase("not entirely convinced", "to‘la ishonch hosil qilmagan", "I'm not entirely convinced that we have allowed enough time for testing.", "Sinovga yetarli vaqt ajratganimizga to‘la ishonchim komil emas."),
            LessonPhrase("clarify the concern", "e’tirozni aniqlashtirmoq", "Could you clarify the concern about the schedule?", "Jadval bo‘yicha nimadan xavotir olayotganingizni aniqlashtira olasizmi?")
        ),
        listOf(
            question("The design looks promising, though the controls could be easier to find. Asosiy e’tiroz nima?", "Boshqaruv elementlarini topish qiyin.", "Dizayn bo‘yicha hamma qaror bekor qilingan.", "Boshqaruv elementlari umuman ishlamaydi.", "Could be easier to find elementlarni topishdagi qiyinchilikni muloyim bildiradi."),
            question("«I'm not entirely convinced by the budget.» Mazmunni aniqlashtiruvchi javobni tanlang.", "Which estimate concerns you most?", "Glad you approve the budget.", "Shall we cancel the whole project?", "Savol byudjetning aynan qaysi bandi muammo ekanini aniqlashga yordam beradi."),
            question("Audiodagi muloyim javob amalda nimani so‘rayapti?", "Hisobotga dalil va raqamlar qo‘shishni", "Hisobotni qisqartirishni", "Hisobotni darhol kengashga yuborishni", "Before we send it va useful to see where the figures came from iboralari yuborishdan oldin raqamlar manbasini ko‘rsatishni so‘raydi.", "This is a useful start. Before we send it to the board, it would be helpful to see where the figures came from.")
        )
    ),
    ContinuationLesson(
        "Qualifying a Research Claim", "Dalilga mos ehtiyotkor xulosa",
        "Tadqiqot natijasida dalil qanchalik kuchli ekanini aniq ifodalang.",
        "The findings suggest kuzatuvga tayangan ehtiyotkor fikrni bildiradi. Is associated with ikki ko‘rsatkich o‘rtasidagi bog‘liqlikni ifodalaydi. May reflect muqobil izoh beradi. Taqqoslash guruhi yoki boshqa zarur dalil bo‘lmasa, sabab haqida qat’iy xulosa chiqarish erta.",
        "Yigirma ko‘ngillida yangi jadvaldan so‘ng kayfiyat yaxshilandi. Natijani bir gapda yozing va sababni aniqlashga xalaqit beradigan bitta cheklovni qo‘shing.",
        listOf(
            LessonPhrase("the findings suggest", "natijalar shundan darak beradi", "The findings suggest that the change may have improved response times.", "Natijalar o‘zgarish javob berish vaqtini qisqartirgan bo‘lishi mumkinligidan darak beradi."),
            LessonPhrase("is associated with", "bilan bog‘liq", "Regular attendance is associated with better outcomes in this sample.", "Ushbu tanlanmada muntazam qatnashish yaxshiroq natijalar bilan bog‘liq."),
            LessonPhrase("may reflect", "aks ettirishi mumkin", "The rise may reflect seasonal demand rather than a policy effect.", "O‘sish siyosat ta’siridan ko‘ra mavsumiy talabni aks ettirishi mumkin.")
        ),
        listOf(
            question("Nazorat guruhi yo‘q. Qaysi jumla dalilga mos?", "Scores rose after the change, but the cause remains unclear.", "The change caused the entire improvement.", "The results apply to every learner.", "Kuzatilgan o‘sish va uning sababi haqidagi noaniqlik alohida berilgan."),
            question("One small pilot found fewer missed appointments. Qaysi xulosa ehtiyotkor?", "The findings suggest the reminders may help.", "Reminders will eliminate missed appointments.", "The pilot establishes the same effect in every clinic.", "Bo‘lishi mumkin bo‘lgan foyda aytiladi, tatbiq doirasi esa kengaytirilmaydi."),
            question("Audio bo‘yicha sababni aniqlashni nima qiyinlashtiradi?", "Xodimlar soni bir vaqtda o‘zgargan.", "Javob vaqtlari o‘lchanmagan.", "Hech kim tizimdan foydalanmagan.", "Qo‘shimcha xodimlar va yangi tizim ta’sirini bir-biridan ajratish qiyin.", "Response times improved after the new system went live. Staffing increased that same week, so we can't attribute the improvement to software alone.")
        )
    ),
    ContinuationLesson(
        "Adapting Register to the Audience", "Uslubni tinglovchiga moslash",
        "Bir mazmunni jamoa, mijoz va rasmiy manzil uchun tabiiy shaklda ifodalang.",
        "Uslubni tanlashda kim o‘qishi, munosabat va murojaat maqsadini hisobga oling. Jamoa chatida qisqa xabar yetarli bo‘lishi mumkin. Mijozga ta’sir, yechim va muddatni tushunarli yozing. Rasmiylik uchun ortiqcha murakkab so‘z ishlatish shart emas.",
        "Uchrashuv bir kunga surildi. Avval jamoa chatiga bir gap yozing, so‘ng mijozga yangi sana, sabab va javob so‘rovini o‘z ichiga olgan xat tayyorlang.",
        listOf(
            LessonPhrase("quick heads-up", "qisqa ogohlantirish", "Quick heads-up: tomorrow's check-in has moved to Friday.", "Qisqacha xabar: ertangi qisqa yig‘ilish jumaga ko‘chirildi."),
            LessonPhrase("apologies for the inconvenience", "noqulaylik uchun uzr", "Apologies for the inconvenience. Your appointment has been moved to 3 p.m.", "Noqulaylik uchun uzr. Qabul vaqtingiz soat 15:00 ga ko‘chirildi."),
            LessonPhrase("please let us know", "iltimos, bizga xabar bering", "Please let us know by Thursday if the revised date is unsuitable.", "Yangi sana sizga to‘g‘ri kelmasa, iltimos, payshanbagacha xabar bering.")
        ),
        listOf(
            question("Mijozga ta’mir uchun band qilingan vaqt o‘zgarganini xabar qilyapsiz. Qaysi matn mos?", "Your repair is now scheduled for Friday at ten. Please let us know if that time is unsuitable.", "Quick heads-up, we bumped you. See you whenever.", "The temporal parameters of the intervention have been administratively recalibrated.", "Mijoz yangi vaqtni va kerak bo‘lsa, nima qilishini darhol biladi."),
            question("Qaysi jumla jamoa chatiga tabiiy va yetarlicha aniq?", "Quick heads-up: the team check-in is at three today.", "Further to the aforementioned scheduling notice, an adjustment has been effected.", "Something changed. You know where to look.", "Chat uchun ibora tabiiy va yangi vaqt aniq berilgan."),
            question("Audio qaysi vaziyatga mos?", "Xodimlar jamoasiga tezkor ichki xabar", "Mijozga rasmiy hisobot", "Hamkorga yuborilgan e’tiroz xati", "Quick heads-up va we've moved our call jamoa ichidagi qisqa xabarni anglatadi.", "Quick heads-up: we've moved our call to four so Maya can join. Same link as before.")
        )
    ),
    ContinuationLesson(
        "Structuring a Briefing", "Murakkab xabarni tartibga solish",
        "Qisqa brifingda maqsadni, dalilni va qarorni aniq tartibda taqdim eting.",
        "The key point is bilan asosiy fikrni belgilang. That said cheklov yoki e’tirozni kiritadi. What this means for us is... dalilni amaliy qarorga bog‘laydi. Bir daqiqalik xabarda tinglovchi nima qilishi kerakligini oxirida aniq ayting.",
        "Yangi kutubxona jadvali bo‘yicha bir daqiqalik brifing tayyorlang. Maqsad, bitta dalil, bitta cheklov va bitta keyingi harakatni kiriting.",
        listOf(
            LessonPhrase("the key point is", "asosiy fikr shuki", "The key point is that weekday demand has shifted to the evenings.", "Asosiy fikr shuki, ish kunlaridagi talab kechki vaqtga siljigan."),
            LessonPhrase("that said", "shuni ham hisobga olib", "That said, the survey excluded weekend users.", "Shuni ham hisobga olish kerakki, so‘rovga dam olish kunlari keladiganlar kiritilmagan."),
            LessonPhrase("what this means for us", "buning biz uchun amaliy ma’nosi", "What this means for us is that we need a separate weekend trial.", "Buning biz uchun amaliy ma’nosi shuki, dam olish kunlari uchun alohida sinov kerak.")
        ),
        listOf(
            question("Tavsiya to‘g‘ri, ammo ma’lumot faqat bahorda yig‘ilgan. Cheklovni kiriting.", "That said, we only collected data in spring.", "In other words, the survey proved the same result year-round.", "As a result, no further checks are possible.", "That said oldingi fikrni saqlagan holda muhim cheklovni kiritadi."),
            question("Brifing yakunida qaysi gap keyingi harakatni aniq belgilaydi?", "Please test the new hours for four weeks and report attendance.", "That's all the information we have so far.", "This remains a challenging topic for everyone.", "Sinov harakati, muddati va natijani qanday qayd etish ko‘rsatilgan."),
            question("Audio bo‘yicha ma’lumot yig‘ishdagi cheklov nima?", "Ro‘yxatga olish paytida auditoriyaning bir qismi qamrab olinmagan.", "Natijalar faqat uch yil avvalga tegishli.", "Ma’lumotlarni tahlil qilish boshlanmagan.", "Elektron ro‘yxatdan foydalanmagan odamlar hisobga kirmagan.", "Online registrations rose after the workshop moved. That said, the count excludes people who signed up in person, so we need another source before changing the venue.")
        )
    ),
    ContinuationLesson("He Said He’d Be Working Late", "He Said He’d Be Working Late",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Hamkasbingiz kechqurun nima qilishi rejalanganini aytgandi. Will be working o‘tgan xabarda would be working shakliga o‘tishi mumkin.",
        "He said he would be working late. — U kechgacha ishlab o‘tirishini aytdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("He said he would be working late", "U kechgacha ishlab o‘tirishini aytdi", "He said he would be working late.", "U kechgacha ishlab o‘tirishini aytdi."),
            LessonPhrase("She told us she would be travelling all day", "U kun bo‘yi safarda bo‘lishini aytdi", "She told us she would be travelling all day.", "U kun bo‘yi safarda bo‘lishini aytdi."),
            LessonPhrase("“Could you give me an example?” “She told us she would be travelling all day.”", "“Bir misol ayta olasizmi?” “U kun bo‘yi safarda bo‘lishini aytdi.”", "“Could you give me an example?” “She told us she would be travelling all day.”", "“Bir misol ayta olasizmi?” “U kun bo‘yi safarda bo‘lishini aytdi.”")
        ),
        listOf(
            question("He said he ___ be working at nine.", "would", "has", "had", "Hamkasbingiz kechqurun nima qilishi rejalanganini aytgandi. Will be working o‘tgan xabarda would be working shakliga o‘tishi mumkin."),
            question("«U kechgacha ishlab o‘tirishini aytdi.» gapiga mos jumlani tanlang.", "He said he would be working late.", "She told us she would be travelling all day.", "“Could you give me an example?” “She told us she would be travelling all day.”", "U kechgacha ishlab o‘tirishini aytdi."),
            question("Audiodagi javobning ma’nosini toping.", "U kun bo‘yi safarda bo‘lishini aytdi.", "U kechgacha ishlab o‘tirishini aytdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "U kun bo‘yi safarda bo‘lishini aytdi.", "“Could you give me an example?” “She told us she would be travelling all day.”")
        )
    ),
    ContinuationLesson("They Expected It Would Be Done", "They Expected It Would Be Done",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Oldingi kutuvga ko‘ra ish bir muddatgacha bitishi kerak edi. Would have + uchinchi shakl o‘sha kelajakdagi yakunni bildiradi.",
        "They expected we would have finished by noon. — Ular tushgacha tugatib bo‘lishimizni kutishdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("They expected we would have finished by noon", "Ular tushgacha tugatib bo‘lishimizni kutishdi", "They expected we would have finished by noon.", "Ular tushgacha tugatib bo‘lishimizni kutishdi."),
            LessonPhrase("She thought the repairs would have been completed by Friday", "U jumagacha ta’mir bitgan bo‘ladi deb o‘yladi", "She thought the repairs would have been completed by Friday.", "U jumagacha ta’mir bitgan bo‘ladi deb o‘yladi."),
            LessonPhrase("“Could you give me an example?” “She thought the repairs would have been completed by Friday.”", "“Bir misol ayta olasizmi?” “U jumagacha ta’mir bitgan bo‘ladi deb o‘yladi.”", "“Could you give me an example?” “She thought the repairs would have been completed by Friday.”", "“Bir misol ayta olasizmi?” “U jumagacha ta’mir bitgan bo‘ladi deb o‘yladi.”")
        ),
        listOf(
            question("We expected they would have ___ by noon.", "finished", "finish", "finishing", "Oldingi kutuvga ko‘ra ish bir muddatgacha bitishi kerak edi. Would have + uchinchi shakl o‘sha kelajakdagi yakunni bildiradi."),
            question("«Ular tushgacha tugatib bo‘lishimizni kutishdi.» gapiga mos jumlani tanlang.", "They expected we would have finished by noon.", "She thought the repairs would have been completed by Friday.", "“Could you give me an example?” “She thought the repairs would have been completed by Friday.”", "Ular tushgacha tugatib bo‘lishimizni kutishdi."),
            question("Audiodagi javobning ma’nosini toping.", "U jumagacha ta’mir bitgan bo‘ladi deb o‘yladi.", "Ular tushgacha tugatib bo‘lishimizni kutishdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "U jumagacha ta’mir bitgan bo‘ladi deb o‘yladi.", "“Could you give me an example?” “She thought the repairs would have been completed by Friday.”")
        )
    ),
    ContinuationLesson("By Spring, We’ll Have Been Running for a Year", "By Spring, We’ll Have Been Running for a Year",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Bahorda xizmat ochilganiga bir yil bo‘ladi. Will have been + -ing kelajakdagi nuqtada ish qancha davom etganini bildiradi.",
        "By spring, we'll have been running this service for a year. — Bahorga borib bu xizmatni yuritayotganimizga bir yil bo‘ladi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("By spring, we'll have been running this service for a year", "Bahorga borib bu xizmatni yuritayotganimizga bir yil bo‘ladi", "By spring, we'll have been running this service for a year.", "Bahorga borib bu xizmatni yuritayotganimizga bir yil bo‘ladi."),
            LessonPhrase("By noon, she'll have been driving for six hours", "Tushga borib u olti soatdan beri mashina haydayotgan bo‘ladi", "By noon, she'll have been driving for six hours.", "Tushga borib u olti soatdan beri mashina haydayotgan bo‘ladi."),
            LessonPhrase("“Could you give me an example?” “By noon, she'll have been driving for six hours.”", "“Bir misol ayta olasizmi?” “Tushga borib u olti soatdan beri mashina haydayotgan bo‘ladi.”", "“Could you give me an example?” “By noon, she'll have been driving for six hours.”", "“Bir misol ayta olasizmi?” “Tushga borib u olti soatdan beri mashina haydayotgan bo‘ladi.”")
        ),
        listOf(
            question("By noon, she'll have been ___ for six hours.", "driving", "drive", "driven", "Bahorda xizmat ochilganiga bir yil bo‘ladi. Will have been + -ing kelajakdagi nuqtada ish qancha davom etganini bildiradi."),
            question("«Bahorga borib bu xizmatni yuritayotganimizga bir yil bo‘ladi.» gapiga mos jumlani tanlang.", "By spring, we'll have been running this service for a year.", "By noon, she'll have been driving for six hours.", "“Could you give me an example?” “By noon, she'll have been driving for six hours.”", "Bahorga borib bu xizmatni yuritayotganimizga bir yil bo‘ladi."),
            question("Audiodagi javobning ma’nosini toping.", "Tushga borib u olti soatdan beri mashina haydayotgan bo‘ladi.", "Bahorga borib bu xizmatni yuritayotganimizga bir yil bo‘ladi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Tushga borib u olti soatdan beri mashina haydayotgan bo‘ladi.", "“Could you give me an example?” “By noon, she'll have been driving for six hours.”")
        )
    ),
    ContinuationLesson("A Plan That Was Never Carried Out", "A Plan That Was Never Carried Out",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Ertalab ochilishi kerak bo‘lgan ko‘rgazma qoldirildi. Was to have + uchinchi shakl bajarilmay qolgan rejani ifodalashi mumkin.",
        "The exhibition was to have opened on Monday, but the roof leaked. — Ko‘rgazma dushanba kuni ochilishi kerak edi, lekin tomdan suv o‘tdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The exhibition was to have opened on Monday, but the roof leaked", "Ko‘rgazma dushanba kuni ochilishi kerak edi, lekin tomdan suv o‘tdi", "The exhibition was to have opened on Monday, but the roof leaked.", "Ko‘rgazma dushanba kuni ochilishi kerak edi, lekin tomdan suv o‘tdi."),
            LessonPhrase("We were to have met yesterday, but the meeting was cancelled", "Kecha uchrashishimiz kerak edi, lekin yig‘ilish bekor qilindi", "We were to have met yesterday, but the meeting was cancelled.", "Kecha uchrashishimiz kerak edi, lekin yig‘ilish bekor qilindi."),
            LessonPhrase("“Could you give me an example?” “We were to have met yesterday, but the meeting was cancelled.”", "“Bir misol ayta olasizmi?” “Kecha uchrashishimiz kerak edi, lekin yig‘ilish bekor qilindi.”", "“Could you give me an example?” “We were to have met yesterday, but the meeting was cancelled.”", "“Bir misol ayta olasizmi?” “Kecha uchrashishimiz kerak edi, lekin yig‘ilish bekor qilindi.”")
        ),
        listOf(
            question("We were to have ___ on Monday, but the venue closed.", "met", "meet", "meeting", "Ertalab ochilishi kerak bo‘lgan ko‘rgazma qoldirildi. Was to have + uchinchi shakl bajarilmay qolgan rejani ifodalashi mumkin."),
            question("«Ko‘rgazma dushanba kuni ochilishi kerak edi, lekin tomdan suv o‘tdi.» gapiga mos jumlani tanlang.", "The exhibition was to have opened on Monday, but the roof leaked.", "We were to have met yesterday, but the meeting was cancelled.", "“Could you give me an example?” “We were to have met yesterday, but the meeting was cancelled.”", "Ko‘rgazma dushanba kuni ochilishi kerak edi, lekin tomdan suv o‘tdi."),
            question("Audiodagi javobning ma’nosini toping.", "Kecha uchrashishimiz kerak edi, lekin yig‘ilish bekor qilindi.", "Ko‘rgazma dushanba kuni ochilishi kerak edi, lekin tomdan suv o‘tdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Kecha uchrashishimiz kerak edi, lekin yig‘ilish bekor qilindi.", "“Could you give me an example?” “We were to have met yesterday, but the meeting was cancelled.”")
        )
    ),
    ContinuationLesson("Why the Witness Keeps Changing Tense", "Why the Witness Keeps Changing Tense",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Hisobot eski bo‘lsa ham fakt hozir amal qilishi mumkin. Reported speechda o‘tgan shakl odatiy. Fakt hali aniq amal qilsa, hozirgi shakl ham mos.",
        "The witness said the road was narrow. — Guvoh yo‘l tor ekanini aytdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The witness said the road was narrow", "Guvoh yo‘l tor ekanini aytdi", "The witness said the road was narrow.", "Guvoh yo‘l tor ekanini aytdi."),
            LessonPhrase("The engineer said the bridge still needs repairs", "Muhandis ko‘prik hali ham ta’mirga muhtojligini aytdi", "The engineer said the bridge still needs repairs.", "Muhandis ko‘prik hali ham ta’mirga muhtojligini aytdi."),
            LessonPhrase("“Could you give me an example?” “The engineer said the bridge still needs repairs.”", "“Bir misol ayta olasizmi?” “Muhandis ko‘prik hali ham ta’mirga muhtojligini aytdi.”", "“Could you give me an example?” “The engineer said the bridge still needs repairs.”", "“Bir misol ayta olasizmi?” “Muhandis ko‘prik hali ham ta’mirga muhtojligini aytdi.”")
        ),
        listOf(
            question("Qaysi gap muammo hozir ham borligini ochiq ko‘rsatadi?", "She said the bridge still needs repairs.", "She said it had once needed repairs.", "She said the repairs had already ended.", "Hisobot eski bo‘lsa ham fakt hozir amal qilishi mumkin. Reported speechda o‘tgan shakl odatiy. Fakt hali aniq amal qilsa, hozirgi shakl ham mos."),
            question("«Guvoh yo‘l tor ekanini aytdi.» gapiga mos jumlani tanlang.", "The witness said the road was narrow.", "The engineer said the bridge still needs repairs.", "“Could you give me an example?” “The engineer said the bridge still needs repairs.”", "Guvoh yo‘l tor ekanini aytdi."),
            question("Audiodagi javobning ma’nosini toping.", "Muhandis ko‘prik hali ham ta’mirga muhtojligini aytdi.", "Guvoh yo‘l tor ekanini aytdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Muhandis ko‘prik hali ham ta’mirga muhtojligini aytdi.", "“Could you give me an example?” “The engineer said the bridge still needs repairs.”")
        )
    ),
    ContinuationLesson("Tomorrow, According to Whom?", "Tomorrow, According to Whom?",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Birovning tomorrow so‘zini vaqtga qarab qayta ayting. Xabar keyin yetkazilsa the next day asl gapdan keyingi kunni bildiradi.",
        "On Monday he said he would come the next day. — Dushanba kuni u ertasi kuni kelishini aytdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("On Monday he said he would come the next day", "Dushanba kuni u ertasi kuni kelishini aytdi", "On Monday he said he would come the next day.", "Dushanba kuni u ertasi kuni kelishini aytdi."),
            LessonPhrase("She said that she had left the previous evening", "U oldingi oqshom ketganini aytdi", "She said that she had left the previous evening.", "U oldingi oqshom ketganini aytdi."),
            LessonPhrase("“Could you give me an example?” “She said that she had left the previous evening.”", "“Bir misol ayta olasizmi?” “U oldingi oqshom ketganini aytdi.”", "“Could you give me an example?” “She said that she had left the previous evening.”", "“Bir misol ayta olasizmi?” “U oldingi oqshom ketganini aytdi.”")
        ),
        listOf(
            question("Dushanbadagi «tomorrow»ni payshanba kuni aytganda qaysi ibora mos?", "the next day", "tomorrow", "right now", "Birovning tomorrow so‘zini vaqtga qarab qayta ayting. Xabar keyin yetkazilsa the next day asl gapdan keyingi kunni bildiradi."),
            question("«Dushanba kuni u ertasi kuni kelishini aytdi.» gapiga mos jumlani tanlang.", "On Monday he said he would come the next day.", "She said that she had left the previous evening.", "“Could you give me an example?” “She said that she had left the previous evening.”", "Dushanba kuni u ertasi kuni kelishini aytdi."),
            question("Audiodagi javobning ma’nosini toping.", "U oldingi oqshom ketganini aytdi.", "Dushanba kuni u ertasi kuni kelishini aytdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "U oldingi oqshom ketganini aytdi.", "“Could you give me an example?” “She said that she had left the previous evening.”")
        )
    ),
    ContinuationLesson("A Statement the Director Refused to Give", "A Statement the Director Refused to Give",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Reporting fe’l bilan keladigan qolipni birga o‘rganing. Refuse + to do, deny + doing, insist + that.",
        "The director refused to comment on the figures. — Direktor raqamlar haqida izoh berishdan bosh tortdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The director refused to comment on the figures", "Direktor raqamlar haqida izoh berishdan bosh tortdi", "The director refused to comment on the figures.", "Direktor raqamlar haqida izoh berishdan bosh tortdi."),
            LessonPhrase("She denied sharing the draft", "U qoralamani ulashganini inkor etdi", "She denied sharing the draft.", "U qoralamani ulashganini inkor etdi."),
            LessonPhrase("“Could you give me an example?” “She denied sharing the draft.”", "“Bir misol ayta olasizmi?” “U qoralamani ulashganini inkor etdi.”", "“Could you give me an example?” “She denied sharing the draft.”", "“Bir misol ayta olasizmi?” “U qoralamani ulashganini inkor etdi.”")
        ),
        listOf(
            question("She refused ___ until Friday.", "to comment", "commenting", "commented", "Reporting fe’l bilan keladigan qolipni birga o‘rganing. Refuse + to do, deny + doing, insist + that."),
            question("«Direktor raqamlar haqida izoh berishdan bosh tortdi.» gapiga mos jumlani tanlang.", "The director refused to comment on the figures.", "She denied sharing the draft.", "“Could you give me an example?” “She denied sharing the draft.”", "Direktor raqamlar haqida izoh berishdan bosh tortdi."),
            question("Audiodagi javobning ma’nosini toping.", "U qoralamani ulashganini inkor etdi.", "Direktor raqamlar haqida izoh berishdan bosh tortdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "U qoralamani ulashganini inkor etdi.", "“Could you give me an example?” “She denied sharing the draft.”")
        )
    ),
    ContinuationLesson("How the Team Recalled the Order", "How the Team Recalled the Order",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Buyruq yoki ogohlantirishni yetkazishda warn yoki order + kim + to ishlating. Taqiq uchun not to.",
        "Security warned us not to use the lift. — Xavfsizlik xodimlari liftga chiqmasligimizni ogohlantirdi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("Security warned us not to use the lift", "Xavfsizlik xodimlari liftga chiqmasligimizni ogohlantirdi", "Security warned us not to use the lift.", "Xavfsizlik xodimlari liftga chiqmasligimizni ogohlantirdi."),
            LessonPhrase("They ordered the team to leave through the side door", "Ular jamoaga yon eshikdan chiqishni buyurishdi", "They ordered the team to leave through the side door.", "Ular jamoaga yon eshikdan chiqishni buyurishdi."),
            LessonPhrase("“Could you give me an example?” “They ordered the team to leave through the side door.”", "“Bir misol ayta olasizmi?” “Ular jamoaga yon eshikdan chiqishni buyurishdi.”", "“Could you give me an example?” “They ordered the team to leave through the side door.”", "“Bir misol ayta olasizmi?” “Ular jamoaga yon eshikdan chiqishni buyurishdi.”")
        ),
        listOf(
            question("They warned us ___ the marked area.", "not to enter", "don't enter", "to not entering", "Buyruq yoki ogohlantirishni yetkazishda warn yoki order + kim + to ishlating. Taqiq uchun not to."),
            question("«Xavfsizlik xodimlari liftga chiqmasligimizni ogohlantirdi.» gapiga mos jumlani tanlang.", "Security warned us not to use the lift.", "They ordered the team to leave through the side door.", "“Could you give me an example?” “They ordered the team to leave through the side door.”", "Xavfsizlik xodimlari liftga chiqmasligimizni ogohlantirdi."),
            question("Audiodagi javobning ma’nosini toping.", "Ular jamoaga yon eshikdan chiqishni buyurishdi.", "Xavfsizlik xodimlari liftga chiqmasligimizni ogohlantirdi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Ular jamoaga yon eshikdan chiqishni buyurishdi.", "“Could you give me an example?” “They ordered the team to leave through the side door.”")
        )
    ),
    ContinuationLesson("The Report Is Said to Be Reliable", "The Report Is Said to Be Reliable",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Xabar manbasi umumiy qolsa, ega + is said + to shakli ishlatiladi. Is said to be — shunday deyiladi.",
        "The report is said to be reliable. — Hisobot ishonchli deb aytiladi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The report is said to be reliable", "Hisobot ishonchli deb aytiladi", "The report is said to be reliable.", "Hisobot ishonchli deb aytiladi."),
            LessonPhrase("The site is said to hold old records", "Saytda eski hujjatlar bor deb aytishadi", "The site is said to hold old records.", "Saytda eski hujjatlar bor deb aytishadi."),
            LessonPhrase("“Could you give me an example?” “The site is said to hold old records.”", "“Bir misol ayta olasizmi?” “Saytda eski hujjatlar bor deb aytishadi.”", "“Could you give me an example?” “The site is said to hold old records.”", "“Bir misol ayta olasizmi?” “Saytda eski hujjatlar bor deb aytishadi.”")
        ),
        listOf(
            question("The report is said ___ accurate.", "to be", "being", "be", "Xabar manbasi umumiy qolsa, ega + is said + to shakli ishlatiladi. Is said to be — shunday deyiladi."),
            question("«Hisobot ishonchli deb aytiladi.» gapiga mos jumlani tanlang.", "The report is said to be reliable.", "The site is said to hold old records.", "“Could you give me an example?” “The site is said to hold old records.”", "Hisobot ishonchli deb aytiladi."),
            question("Audiodagi javobning ma’nosini toping.", "Saytda eski hujjatlar bor deb aytishadi.", "Hisobot ishonchli deb aytiladi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Saytda eski hujjatlar bor deb aytishadi.", "“Could you give me an example?” “The site is said to hold old records.”")
        )
    ),
    ContinuationLesson("The Company Is Believed to Have Moved", "The Company Is Believed to Have Moved",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Oldin ro‘y bergan ish haqida xabar berishda is believed + to have + uchinchi shakl ishlatiladi.",
        "The company is believed to have moved last month. — Kompaniya o‘tgan oy ko‘chgan deb hisoblanadi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The company is believed to have moved last month", "Kompaniya o‘tgan oy ko‘chgan deb hisoblanadi", "The company is believed to have moved last month.", "Kompaniya o‘tgan oy ko‘chgan deb hisoblanadi."),
            LessonPhrase("The file is thought to have disappeared on Tuesday", "Fayl seshanba kuni yo‘qolgan deb o‘ylashadi", "The file is thought to have disappeared on Tuesday.", "Fayl seshanba kuni yo‘qolgan deb o‘ylashadi."),
            LessonPhrase("“Could you give me an example?” “The file is thought to have disappeared on Tuesday.”", "“Bir misol ayta olasizmi?” “Fayl seshanba kuni yo‘qolgan deb o‘ylashadi.”", "“Could you give me an example?” “The file is thought to have disappeared on Tuesday.”", "“Bir misol ayta olasizmi?” “Fayl seshanba kuni yo‘qolgan deb o‘ylashadi.”")
        ),
        listOf(
            question("The company is believed to have ___ last month.", "moved", "move", "moving", "Oldin ro‘y bergan ish haqida xabar berishda is believed + to have + uchinchi shakl ishlatiladi."),
            question("«Kompaniya o‘tgan oy ko‘chgan deb hisoblanadi.» gapiga mos jumlani tanlang.", "The company is believed to have moved last month.", "The file is thought to have disappeared on Tuesday.", "“Could you give me an example?” “The file is thought to have disappeared on Tuesday.”", "Kompaniya o‘tgan oy ko‘chgan deb hisoblanadi."),
            question("Audiodagi javobning ma’nosini toping.", "Fayl seshanba kuni yo‘qolgan deb o‘ylashadi.", "Kompaniya o‘tgan oy ko‘chgan deb hisoblanadi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Fayl seshanba kuni yo‘qolgan deb o‘ylashadi.", "“Could you give me an example?” “The file is thought to have disappeared on Tuesday.”")
        )
    ),
    ContinuationLesson("The Site Appears to Be Operating", "The Site Appears to Be Operating",
        "Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting.",
        "Sayt ishlayotganga o‘xshasa appear yoki seem + to be + -ing bilan kuzatuvga tayangan taxmin bering.",
        "The site appears to be operating normally. — Sayt odatdagidek ishlayotganga o‘xshaydi. Vaziyatni o‘zingizga moslab, shu qolip bilan yana bitta gap ayting. Bitta tushunarli gapdan boshlash kifoya.",
        listOf(
            LessonPhrase("The site appears to be operating normally", "Sayt odatdagidek ishlayotganga o‘xshaydi", "The site appears to be operating normally.", "Sayt odatdagidek ishlayotganga o‘xshaydi."),
            LessonPhrase("The queue seems to be moving faster", "Navbat tezroq siljiyotganga o‘xshaydi", "The queue seems to be moving faster.", "Navbat tezroq siljiyotganga o‘xshaydi."),
            LessonPhrase("“Could you give me an example?” “The queue seems to be moving faster.”", "“Bir misol ayta olasizmi?” “Navbat tezroq siljiyotganga o‘xshaydi.”", "“Could you give me an example?” “The queue seems to be moving faster.”", "“Bir misol ayta olasizmi?” “Navbat tezroq siljiyotganga o‘xshaydi.”")
        ),
        listOf(
            question("The site appears ___ operating normally.", "to be", "be", "been", "Sayt ishlayotganga o‘xshasa appear yoki seem + to be + -ing bilan kuzatuvga tayangan taxmin bering."),
            question("«Sayt odatdagidek ishlayotganga o‘xshaydi.» gapiga mos jumlani tanlang.", "The site appears to be operating normally.", "The queue seems to be moving faster.", "“Could you give me an example?” “The queue seems to be moving faster.”", "Sayt odatdagidek ishlayotganga o‘xshaydi."),
            question("Audiodagi javobning ma’nosini toping.", "Navbat tezroq siljiyotganga o‘xshaydi.", "Sayt odatdagidek ishlayotganga o‘xshaydi.", "Gapiruvchi mavzuni o‘zgartirdi.", "Navbat tezroq siljiyotganga o‘xshaydi.", "“Could you give me an example?” “The queue seems to be moving faster.”")
        )
    )
)
