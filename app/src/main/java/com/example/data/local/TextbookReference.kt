package com.example.data.local

/** Supplemental book references attached to existing lessons by stable section ID. */
internal object TextbookReference {
    fun explanation(sectionId: String, lessonText: String): String {
        val reference = when (sectionId) {
            "sec_a2_06" -> pastForms
            "sec_a2_13" -> participles
            "sec_a2_44" -> place
            "sec_b1_57" -> time
            "sec_b2_46" -> relationships
            "sec_c1_52" -> formalPhrases
            else -> return lessonText
        }
        return "$lessonText\n\n$reference"
    }

    private val pastForms = """
        Kitobdagi noto‘g‘ri fe’llar · 331–332-betlar
        Birinchi shakl → kechagi ishni aytadigan shakl.
        beat → beat · begin → began · bind → bound · bite → bit
        blow → blew · break → broke · bring → brought · build → built
        buy → bought · catch → caught · choose → chose · do → did
        drink → drank · drive → drove · eat → ate · fall → fell
        feel → felt · find → found · fly → flew · forget → forgot
        get → got · give → gave · hear → heard · hide → hid
        keep → kept · know → knew · lead → led · leave → left
        lose → lost · make → made · meet → met · pay → paid
        ride → rode · run → ran · say → said · see → saw
        sell → sold · send → sent · sing → sang · sink → sank
        sit → sat · speak → spoke · spend → spent · stand → stood
        steal → stole · strive → strove/strived · swim → swam · take → took
        teach → taught · tear → tore · tell → told · think → thought
        throw → threw · understand → understood
        Shakli o‘zgarmaydiganlar: bet, bid, cost, cut, fit, hit, put, quit, read, shut, spread.
        Read ning yozilishi bir xil, talaffuzi o‘zgaradi. Masalan, “I read your message yesterday.”
    """.trimIndent()

    private val participles = """
        Kitobdagi noto‘g‘ri fe’llar · 331–332-betlar
        Birinchi shakl → have yoki has bilan keladigan shakl.
        beat → beaten · begin → begun · bind → bound · bite → bitten
        blow → blown · break → broken · bring → brought · build → built
        buy → bought · catch → caught · choose → chosen · do → done
        drink → drunk · drive → driven · eat → eaten · fall → fallen
        feel → felt · find → found · fly → flown · forget → forgotten
        get → got/gotten · give → given · hear → heard · hide → hidden
        keep → kept · know → known · lead → led · leave → left
        lose → lost · make → made · meet → met · pay → paid
        ride → ridden · run → run · say → said · see → seen
        sell → sold · send → sent · sing → sung · sink → sunk
        sit → sat · speak → spoken · spend → spent · stand → stood
        steal → stolen · strive → striven/strived · swim → swum · take → taken
        teach → taught · tear → torn · tell → told · think → thought
        throw → thrown · understand → understood
        Shakli o‘zgarmaydiganlar: bet, bid, cost, cut, fit, hit, put, quit, read, shut, spread.
        Gotten asosan Amerika inglizchasida ishlatiladi. Uchta fe’l tanlab, “I've already...” bilan gap tuzing.

        -ing shaklida imlo
        Ko‘p fe’llarga -ing qo‘shiladi: fly → flying, read → reading.
        Oxiridagi talaffuz qilinmaydigan e odatda tushadi: drive → driving, write → writing.
        Ayrim qisqa fe’llarda oxirgi undosh ikki marta yoziladi:
        begin → beginning · bet → betting · bid → bidding · cut → cutting
        fit → fitting · get → getting · hit → hitting · put → putting
        quit → quitting · run → running · shut → shutting · sit → sitting · swim → swimming.
    """.trimIndent()

    private val place = """
        Yo‘l va joy uchun qo‘shimcha misollar
        above the shelf — tokchadan yuqorida · below the window — derazadan pastda
        across the road — yo‘lning narigi tomonida · along the river — daryo bo‘ylab
        behind the counter — peshtaxta orqasida · beside the bench — skameyka yonida
        between the shops — ikki do‘kon orasida · among friends — do‘stlar orasida
        beyond the gate — darvozadan narida · against the wall — devorga tiralgan holda
        in the box — quti ichida · into the box — quti ichiga · out of the box — quti ichidan tashqariga
        inside the station — bekat ichida · outside the station — bekat tashqarisida
        on the table — stol ustida · off the table — stol ustidan · over the fence — panjara tepasidan
        past the bakery — nonvoyxona yonidan o‘tib · through the tunnel — tunnel ichidan o‘tib
        under the bridge — ko‘prik tagida · down the steps — zinadan pastga · up the steps — zinadan tepaga
        to the gate — darvozaga · towards the gate — darvoza tomon
        in front of the school — maktab oldida · at the gate — darvoza yonida yoki kirish joyida
        Mashq: “Your bag is under the bench” gapida under ni beside bilan almashtiring. Sumka joyi qanday o‘zgardi?
    """.trimIndent()

    private val time = """
        Vaqt va muddatni aniq aytish
        at five — soat beshda · on Monday — dushanba kuni · in June — iyunda
        before lunch — tushlikdan oldin · after lunch — tushlikdan keyin
        during the show — tomosha paytida · since Monday — dushanbadan beri
        for a week — bir hafta davomida · from Monday to Friday — dushanbadan jumagacha
        by Friday — kechi bilan jumagacha · until Friday — juma kunigacha davom etadi
        within a week — bir hafta ichida · about five — taxminan beshda
        Mashq: Topshiriqning oxirgi muddati juma. “Send it by Friday.” Idora jumagacha ishlaydi. “It stays open until Friday.”
    """.trimIndent()

    private val relationships = """
        Predlogning ma’nosi yonidagi so‘zga bog‘liq
        talk about a trip — safar haqida gaplashmoq · about fifty — taxminan ellikta
        walk about the garden — bog‘ atrofida yurmoq · be about to leave — ketay deb turmoq
        hear of a writer — yozuvchi haqida eshitgan bo‘lmoq · a cup of tea — bir piyola choy
        a message from a friend — do‘stdan xabar · protect it from rain — yomg‘irdan himoya qilmoq
        a ticket for Maya — Maya uchun chipta · pay for a ticket — chipta uchun to‘lamoq
        go by bus — avtobusda bormoq · written by Maya — Maya yozgan
        cut with scissors — qaychi bilan kesmoq · go with Maya — Maya bilan bormoq
        leave without a coat — paltosiz chiqmoq · wait without complaining — shikoyat qilmasdan kutmoq
        everyone except Maya — Mayadan tashqari hamma · besides Maya, two friends — Mayadan tashqari yana ikki do‘st
        on arrival yoki upon arrival — yetib kelganda · work on a report — hisobot ustida ishlamoq
        Mashq: Qaychini with bilan, avtobusni by bilan gapga qo‘shing.
    """.trimIndent()

    private val formalPhrases = """
        Kitobdagi guruh predloglari · 245–247-betlar
        according to the timetable — jadvalga ko‘ra · as for the venue / as to the venue — joyga kelsak
        apart from the fee — to‘lovdan tashqari · because of the storm / owing to the storm — bo‘ron tufayli
        but for your help — yordamingiz bo‘lmaganida · by means of a valve — klapan yordamida
        in accordance with the rules / in conformity with the rules — qoidalarga muvofiq
        in addition to the fee — to‘lovga qo‘shimcha · in case of fire — yong‘in bo‘lsa
        in the event of cancellation — bekor qilinsa · in consequence of the delay — kechikish oqibatida
        as compared with July / in comparison with July — iyulga taqqoslaganda
        in favour of the proposal — taklif foydasiga · in front of the hall — zal oldida
        in spite of the delay — kechikishga qaramay · instead of the old form — eski blanka o‘rniga
        in view of the forecast — prognozni hisobga olib · thanks to your help — yordamingiz tufayli
        on behalf of the team / in the name of the team — jamoa nomidan
        subject to approval — tasdiqlanish sharti bilan · with a view to reducing costs — xarajatlarni kamaytirish maqsadida
        with regard to the schedule / in respect of the schedule — jadval masalasiga doir
        Mashq: “In the event of cancellation, we'll email you.” Shu xabarni boshqa vaziyatga moslab ayting.
    """.trimIndent()
}
