package com.example.data.local

internal val c1Lessons = listOf(
    ContinuationLesson(
        "Interpreting indirect feedback", "Bilvosita fikrni tushunish",
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
        "Qualifying a research claim", "Dalilga mos ehtiyotkor xulosa",
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
        "Adapting register to the audience", "Uslubni tinglovchiga moslash",
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
        "Structuring a briefing", "Murakkab xabarni tartibga solish",
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
    )
)

internal val c2Lessons = listOf(
    ContinuationLesson(
        "Synthesising two accounts", "Ikki manbadan yaxlit xulosa",
        "Yozma va og‘zaki manbadagi dalillarni birlashtirib, ularning ko‘lami va cheklovini saqlang.",
        "Manbalar bir xil hodisaning turli tomonlarini o‘lchashi mumkin. Taken together bilan bog‘liqlikni ko‘rsating. Each tells us something different bilan natijalar qayerda farqlanishini ayting. Vaqt, hudud yoki guruh farqini xulosaga kiriting.",
        "Bir manba markazdagi avtomobil qatnovi kamayganini, ikkinchisi chekka hududda o‘zgarmaganini aytdi. Har ikki doirani saqlagan holda ikki gapli xulosa tayyorlang.",
        listOf(
            LessonPhrase("taken together", "birgalikda ko‘rib chiqilganda", "Taken together, the figures indicate a change in the centre and little movement in outer districts.", "Raqamlar birgalikda ko‘rib chiqilganda, markazda o‘zgarish, chekka tumanlarda esa deyarli siljish yo‘qligi ko‘rinadi."),
            LessonPhrase("covers a different period", "boshqa davrni qamrab oladi", "The survey covers a different period, so the figures cannot be directly compared.", "So‘rov boshqa davrni qamrab olgan, shu bois raqamlarni to‘g‘ridan to‘g‘ri solishtirib bo‘lmaydi."),
            LessonPhrase("the evidence is limited to", "dalil faqat shu doiraga tegishli", "The evidence is limited to weekday trips in the city centre.", "Dalil faqat shahar markazidagi ish kunlari qatnoviga tegishli.")
        ),
        listOf(
            question("A manba: Weekend visits rose downtown. B manba: Weekday visits were stable across the city. Qaysi xulosa doiralarni saqlaydi?", "Downtown weekend visits rose, while weekday figures across the city stayed flat.", "Citywide visitor numbers rose throughout the week.", "The two sources disagree about downtown weekends.", "Xulosa har bir manbaning hududi va vaqtini alohida saqlaydi."),
            question("Bir hisobot uchrashuv tezligini, boshqasi qoniqishni o‘lchaydi. Ularni qanday bog‘lash mumkin?", "They describe different outcomes and can inform the same decision.", "Average the two numbers into a single improvement score.", "The satisfaction result automatically explains the faster meetings.", "O‘lchovlar turli natijalarga tegishli, ular qarorning har xil tomoniga yordam beradi."),
            question("Matn: «Average waiting time fell citywide.» Audio qaysi tafsilotni qo‘shadi?", "Umumiy ko‘rsatkich ayrim hududlardagi yomonlashuvni yashirishi mumkin.", "Shahar o‘rtachasi amalda o‘smagan ham, tushmagan ham.", "Chekkadagi bemorlar kutish vaqtini hisoblamagan.", "Keltirilgan ikkita chekka klinikada kutish ortgan. Bu shahar o‘rtachasining tushishiga zid bo‘lmasligi mumkin.", "The citywide average did fall, but the two clinics on the outskirts saw longer waits, especially on Mondays.")
        )
    ),
    ContinuationLesson(
        "Preserving nuance in paraphrase", "Qayta ifodalashda ma’no nozikligi",
        "Matnni qayta yozishda ehtimol darajasi, istisno va vaqt doirasini saqlang.",
        "Unlikely, possible va cannot be ruled out turlicha ishonchni bildiradi. Only if zarur shartni ko‘rsatadi. For now qaror vaqtinchalik ekanini bildiradi. Qayta ifodalashdan keyin asl jumlada bor ma’lumot va cheklovlarni birma-bir tekshiring.",
        "«Approval is likely, provided costs do not rise» fikrini boshqa so‘zlar bilan ayting. Istalgan shart va ishonch darajasi saqlanganini tekshiring.",
        listOf(
            LessonPhrase("cannot be ruled out", "ehtimoli butunlay istisno etilmaydi", "A second delay cannot be ruled out while the supplier is still testing the parts.", "Yetkazib beruvchi qismlarni hali sinayotgan ekan, yana kechikish ehtimolini istisno qilib bo‘lmaydi."),
            LessonPhrase("only if funding is secured", "faqat mablag‘ ta’minlansa", "The extension will proceed only if funding is secured.", "Uzaytirish faqat mablag‘ ta’minlansa amalga oshiriladi."),
            LessonPhrase("for the time being", "hozircha", "For the time being, the existing rules will stay in place.", "Hozircha amaldagi qoidalar saqlanadi.")
        ),
        listOf(
            question("«A delay is unlikely, but cannot be ruled out.» Qaysi qayta ifoda mos?", "We probably won't be delayed, though it remains possible.", "We should expect a delay.", "There is no possibility of a delay.", "Probably won't va remains possible ikkala ma’no bo‘lagini ham saqlaydi."),
            question("«We will extend the service only if funding is secured.» Qaysi talqin kafolat qo‘shmaydi?", "Securing funding is necessary for the extension.", "Securing funding by itself guarantees an extension.", "Funding has already been secured.", "Only if zarur shartni beradi. Qolgan shartlar haqida ma’lumot yo‘q."),
            question("Audio bo‘yicha qaror qanday xususiyatga ega?", "Vaqtinchalik va yangi dalil chiqqanda qayta ko‘rilishi mumkin.", "Yakuniy va doimiy.", "Keyingi haftadan avtomatik bekor bo‘ladi.", "For now va revisit it hozirgi qaror qayta ko‘rilishi mumkinligini bildiradi.", "The restriction stays in place for now. We'll revisit it if new evidence becomes available.")
        )
    ),
    ContinuationLesson(
        "Recognising irony and framing", "Kinoya va talqin usulini anglash",
        "Kontekst orqali kinoyani aniqlang va so‘z tanlovi o‘quvchi talqiniga ta’sirini tushuntiring.",
        "Baho bildiruvchi ibora faktlarga zid kelganda kinoya bo‘lishi mumkin. «A minor adjustment» kabi ibora katta o‘zgarishni kichikdek taqdim etishi mumkin. Gapning yashirin ma’nosini aniqlash uchun ohangni, kontekstni va aniq dalilni birga tekshiring.",
        "Tashkilot to‘rt soatlik qisqarishni «a small adjustment» deb e’lon qildi. O‘zgarishni bitta xolis jumlada yozing, so‘ng dastlabki ibora taassurotga qanday ta’sir qilganini tushuntiring.",
        listOf(
            LessonPhrase("a resounding success", "katta muvaffaqiyat", "A resounding success, then: nobody could log in.", "Demak, katta muvaffaqiyat: hech kim tizimga kira olmadi."),
            LessonPhrase("a minor adjustment", "kichik o‘zgartirish", "The memo calls the loss of four opening hours a minor adjustment.", "Xabarnomada ish vaqtining to‘rt soatga qisqarishi kichik o‘zgartirish deb atalgan."),
            LessonPhrase("casts doubt on", "shubha uyg‘otadi", "The missing data casts doubt on the claim that everyone benefited.", "Yetishmayotgan ma’lumot hamma foyda ko‘rgan degan da’voga shubha uyg‘otadi.")
        ),
        listOf(
            question("«Excellent timing. The doors shut just as we arrived.» Excellent bu yerda nimani anglatadi?", "Vaqt juda noqulay kelganini kinoya bilan aytyapti.", "Eshiklar o‘z vaqtida ochilganini maqtayapti.", "Eshikni kim yopganini taxmin qilyapti.", "Excellent ijobiy so‘z, lekin keyingi jumlada ular kira olmagani anglashiladi."),
            question("«A minor adjustment» aslida ish vaqtini to‘rt soatga qisqartirgan. Qaysi bayon xolisroq?", "Opening hours have been reduced by four hours.", "The change is too small to affect anyone.", "The organisation has abandoned its users.", "Xolis bayon aniq o‘zgarishni ko‘rsatadi va unga baho qo‘shmaydi."),
            question("Audio nimani ohang orqali yoki keyingi fakt yordamida anglatadi?", "Navigatsiya aslida tushunarsiz bo‘lgan.", "Yo‘nalish ko‘rsatkichlarini hamma tushungan.", "Sayohat vaqtida avtobus buzilgan.", "Oradan yarim soat o‘tib o‘sha belgining oldiga qaytish aniqlik haqidagi maqtovni kinoyaga aylantiradi.", "Very clear directions. Half an hour later, we were standing in front of the same sign.")
        )
    ),
    ContinuationLesson(
        "Mediating a tense negotiation", "Keskin muzokarada vositachilik",
        "Qarama-qarshi pozitsiyalar ortidagi ehtiyojlarni ajrating va ikkala tomonga tekshiriladigan yechim taklif qiling.",
        "If I understand correctly... pozitsiyani qayta ifodalab, uni tomon bilan tekshirishga yordam beradi. Your concern is... talab ortidagi ehtiyojni ko‘rsatadi. Would it help if...? yakuniy kelishuvni e’lon qilmasdan, shartli yechimni sinab ko‘rishga imkon beradi.",
        "Bir tomon juma kuni nashr qilishni, ikkinchisi esa sinovni tugatishni istayapti. Har birining asosiy ehtiyojini ayting, keyin qaror shartlari aniq bo‘lgan taklif bering.",
        listOf(
            LessonPhrase("if I understand correctly", "to‘g‘ri tushungan bo‘lsam", "If I understand correctly, the date matters because the partners have booked their staff.", "To‘g‘ri tushungan bo‘lsam, sana muhim, chunki hamkorlar o‘sha kunga xodim ajratib qo‘ygan."),
            LessonPhrase("your concern is", "sizni xavotirga solayotgan narsa", "Your concern is that support may be unable to handle a full release.", "Sizni yordam xizmati to‘liq ishga tushirishga tayyor bo‘lmasligi xavotirga solyapti."),
            LessonPhrase("would it help if", "shunday qilsak yordam beradimi", "Would it help if we released the update to one team first and reviewed the results on Monday?", "Yangilanishni avval bitta jamoaga chiqarib, natijani dushanba kuni ko‘rsak yordam beradimi?")
        ),
        listOf(
            question("A jamoa juma muddatini, B jamoa foydalanuvchi ma’lumotlarini himoya qilishni ustun qo‘ydi. Qaysi yechim ikkala ehtiyojga mos?", "Show a test-data demo on Friday and keep the public launch conditional on the security checks.", "Launch publicly on Friday and assess the risk afterwards.", "Postpone every activity without updating the partners.", "Sinov ma’lumotlari bilan namoyish vaqt talabini qoplaydi. Ochiq nashr xavfsizlik tekshiruviga bog‘liq."),
            question("Qaysi savol taxmin qilingan ehtiyojni taraf bilan tekshiradi?", "Is your main concern losing staffed support over the weekend?", "Why are you trying to block the team?", "So you've already agreed to delay the project?", "Savol ayblovsiz tarzda qaysi ehtiyoj muhimligini tekshiradi."),
            question("Audio bo‘yicha tomonning asosiy sharti nima?", "Kim telefonlarni qabul qilishi oldindan belgilanishi.", "Sana har qanday holatda o‘zgarmasligi.", "Hisobot kechqurun tarjima qilinishi.", "Can live with Tuesday sanaga moslashishni, provided we know who's covering the phones esa haqiqiy shartni bildiradi.", "We could live with Tuesday instead of Monday, provided we know who's covering the phones while the rest of the team is in training.")
        )
    )
)
