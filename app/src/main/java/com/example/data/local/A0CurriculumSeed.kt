package com.example.data.local

import com.example.core.database.*
import com.example.domain.model.CurriculumLevel

object A0CurriculumSeed {

    const val DARS_1_SECTION_ID = "sec_a0_01"
    const val DARS_2_SECTION_ID = "sec_a0_02"
    const val DARS_3_SECTION_ID = "sec_a0_03"
    const val DARS_4_SECTION_ID = "sec_a0_04"
    const val DARS_5_SECTION_ID = "sec_a0_05"
    const val DARS_6_SECTION_ID = "sec_a0_06"
    const val DARS_7_SECTION_ID = "sec_a0_07"
    const val DARS_8_SECTION_ID = "sec_a0_08"
    const val DARS_9_SECTION_ID = "sec_a0_09"
    const val DARS_10_SECTION_ID = "sec_a0_10"
    const val DARS_11_SECTION_ID = "sec_a0_11"
    const val DARS_12_SECTION_ID = "sec_a0_12"
    const val DARS_13_SECTION_ID = "sec_a0_13"
    const val DARS_14_SECTION_ID = "sec_a0_14"
    const val DARS_15_SECTION_ID = "sec_a0_15"
    const val DARS_16_SECTION_ID = "sec_a0_16"
    const val DARS_17_SECTION_ID = "sec_a0_17"
    const val DARS_18_SECTION_ID = "sec_a0_18"
    const val DARS_19_SECTION_ID = "sec_a0_19"
    const val DARS_20_SECTION_ID = "sec_a0_20"
    const val DARS_21_SECTION_ID = "sec_a0_21"
    const val DARS_22_SECTION_ID = "sec_a0_22"
    const val DARS_23_SECTION_ID = "sec_a0_23"
    const val DARS_24_SECTION_ID = "sec_a0_24"
    const val DARS_25_SECTION_ID = "sec_a0_25"
    const val DARS_26_SECTION_ID = "sec_a0_26"
    const val DARS_27_SECTION_ID = "sec_a0_27"
    const val DARS_28_SECTION_ID = "sec_a0_28"
    const val DARS_29_SECTION_ID = "sec_a0_29"
    const val DARS_30_SECTION_ID = "sec_a0_30"
    const val DARS_31_SECTION_ID = "sec_a0_31"
    const val DARS_32_SECTION_ID = "sec_a0_32"
    const val DARS_33_SECTION_ID = "sec_a0_33"
    const val DARS_34_SECTION_ID = "sec_a0_34"
    const val DARS_35_SECTION_ID = "sec_a0_35"
    const val DARS_36_SECTION_ID = "sec_a0_36"
    const val DARS_37_SECTION_ID = "sec_a0_37"
    const val DARS_38_SECTION_ID = "sec_a0_38"
    const val DARS_39_SECTION_ID = "sec_a0_39"
    const val DARS_40_SECTION_ID = "sec_a0_40"
    const val DARS_41_SECTION_ID = "sec_a0_41"
    const val DARS_42_SECTION_ID = "sec_a0_42"
    const val DARS_43_SECTION_ID = "sec_a0_43"
    const val DARS_44_SECTION_ID = "sec_a0_44"
    const val DARS_45_SECTION_ID = "sec_a0_45"
    const val DARS_46_SECTION_ID = "sec_a0_46"
    const val DARS_47_SECTION_ID = "sec_a0_47"
    const val DARS_48_SECTION_ID = "sec_a0_48"
    const val DARS_49_SECTION_ID = "sec_a0_49"
    const val DARS_50_SECTION_ID = "sec_a0_50"
    const val DARS_51_SECTION_ID = "sec_a0_51"
    const val DARS_52_SECTION_ID = "sec_a0_52"
    const val DARS_53_SECTION_ID = "sec_a0_53"

    fun buildSections(): List<LessonSectionEntity> = listOf(
        LessonSectionEntity(
            id = DARS_1_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 1,
            titleEnglish = "Hello!",
            titleUzbek = "1-Dars: Hello!",
            objectiveUzbek = "Salomlashish mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Sinfga kirdingiz va do‘stingizni ko‘rdingiz. “Hello!” deb salom bering.

Kimga murojaat qilayotganingizni aytsangiz, nomini qo‘shing: “Hello, Dad!”
Misollarni tinglang va o‘zingiz takrorlang.""",
            wrapUpSummaryUzbek = """Hello! — Salom!
Bugun bir kishiga “Hello!” deb salom bering. Suhbatni boshlash uchun shu bitta so‘z yetadi.""",
            isUnlocked = true,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_2_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 2,
            titleEnglish = "My name is...",
            titleUzbek = "2-Dars: My name is...",
            objectiveUzbek = "Ism aytish mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """My name is... — bu “Mening ismim...” degani.
O‘zingizni tanishtirmoqchi bo‘lsangiz — shunday deysiz.

My name is Aziz. — Mening ismim Aziz.
My name is Laylo. — Mening ismim Laylo.

My name is dan keyin ismingizni ayting.

Misolni tinglang, keyin Aziz o‘rniga o‘z ismingizni ayting.""",
            wrapUpSummaryUzbek = """My name is... — mening ismim...
Bugun siz ismingizni aytishni o‘rgandingiz.
Onangizga ayting: “My name is ___.”
Keyingi dars — ranglar! 🎨""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_3_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 3,
            titleEnglish = "Colors",
            titleUzbek = "3-Dars: Colors",
            objectiveUzbek = "Ranglar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Color — bu rang.
Atrofimizda hamma narsa rangli.
Bugun 5 ta rang o‘rganamiz:

🔴 Red — qizil.
🔵 Blue — ko‘k.
🟢 Green — yashil.
🟡 Yellow — sariq.
⚫ Black — qora.

Ovoz chiqarib: RED, BLUE, GREEN, YELLOW, BLACK. Tamom.""",
            wrapUpSummaryUzbek = """5 ta rang: red, blue, green, yellow, black.
Bugun siz ranglarni o‘rgandingiz.
Uyda 3 ta narsani ko‘rsatib, rangini ayting! 🎨""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_4_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 4,
            titleEnglish = "Numbers 1–5",
            titleUzbek = "4-Dars: Numbers 1–5",
            objectiveUzbek = "Raqamlar 1–5 mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Number — bu son.
Bugun 1 dan 5 gacha sanaymiz.

1️⃣ One — bir.
2️⃣ Two — ikki.
3️⃣ Three — uch.
4️⃣ Four — to‘rt.
5️⃣ Five — besh.

Ovoz chiqarib: ONE, TWO, THREE, FOUR, FIVE. Tamom.""",
            wrapUpSummaryUzbek = """One, two, three, four, five.
Bugun siz 1–5 gacha sanashni o‘rgandingiz.
Barmoqlaringizni sanang! 🖐️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_5_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 5,
            titleEnglish = "Numbers 6–10",
            titleUzbek = "5-Dars: Numbers 6–10",
            objectiveUzbek = "Raqamlar 6–10 mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """O‘tgan darsda 1–5 o‘rgandik.
Bugun — 6 dan 10 gacha.

6️⃣ Six — olti.
7️⃣ Seven — yetti.
8️⃣ Eight — sakkiz.
9️⃣ Nine — to‘qqiz.
🔟 Ten — o‘n.

Ovoz chiqarib: SIX, SEVEN, EIGHT, NINE, TEN. Tamom.""",
            wrapUpSummaryUzbek = """Six, seven, eight, nine, ten.
Endi siz 1 dan 10 gacha sanaysiz! 🎉
Ovoz chiqarib sanang!""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_6_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 6,
            titleEnglish = "Animals",
            titleUzbek = "6-Dars: Animals",
            objectiveUzbek = "Hayvonlar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Animal — bu hayvon.
Bugun 5 ta hayvon o‘rganamiz:

🐱 Cat — mushuk.
🐶 Dog — it.
🐟 Fish — baliq.
🐦 Bird — qush.
🐘 Elephant — fil.

Ovoz chiqarib: CAT, DOG, FISH, BIRD, ELEPHANT. Tamom.""",
            wrapUpSummaryUzbek = """Cat, dog, fish, bird, elephant.
Bugun siz hayvonlarni o‘rgandingiz.
Uyda mushukka “Hello, cat!” deb ayting! 🐱""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_7_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 7,
            titleEnglish = "My Family",
            titleUzbek = "7-Dars: My Family",
            objectiveUzbek = "Oila mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Family — bu oila.
Oilangizda kimlar bor?

👩 Mom — ona.
👨 Dad — dada.
👦 Brother — aka/uka.
👧 Sister — opa/singil.
👶 Baby — chaqaloq.

Ovoz chiqarib: MOM, DAD, BROTHER, SISTER, BABY. Tamom.""",
            wrapUpSummaryUzbek = """Mom, dad, brother, sister, baby.
Bugun siz oilangizni o‘rgandingiz.
Onangizga “Hello, mom!” deb ayting! 👩""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_8_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 8,
            titleEnglish = "My Body",
            titleUzbek = "8-Dars: My Body",
            objectiveUzbek = "Tana a’zolari mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Body — bu tana.
Bugun 5 ta tana a’zosini o‘rganamiz:

👁️ Eyes — ko‘zlar.
👂 Ears — quloqlar.
👃 Nose — burun.
👄 Mouth — og‘iz.
🤚 Hands — qo‘llar.

Ovoz chiqarib: EYES, EARS, NOSE, MOUTH, HANDS. Tamom.""",
            wrapUpSummaryUzbek = """Eyes, ears, nose, mouth, hands.
Bugun siz tana a’zolarini o‘rgandingiz.
Ko‘zingizni ko‘rsatib: “My eyes!” deb ayting! 👁️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_9_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 9,
            titleEnglish = "Toys",
            titleUzbek = "9-Dars: Toys",
            objectiveUzbek = "O‘yinchoqlar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Toy — bu o‘yinchoq.
Bugun 5 ta o‘yinchoq o‘rganamiz:

⚽ Ball — to‘p.
🧸 Teddy — ayiqcha.
🚗 Car — mashina.
🧱 Blocks — kubiklar.
🪀 Yo-yo — yo-yo.

Ovoz chiqarib: BALL, TEDDY, CAR, BLOCKS, YO-YO. Tamom.""",
            wrapUpSummaryUzbek = """Ball, teddy, car, blocks, yo-yo.
Bugun siz o‘yinchoqlarni o‘rgandingiz.
Sevimli o‘yinchoqingizni ko‘tarib: “My ball!” deb ayting! ⚽""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_10_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 10,
            titleEnglish = "Food",
            titleUzbek = "10-Dars: Food",
            objectiveUzbek = "Ovqat mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Food — bu ovqat.
Bugun 5 ta ovqat o‘rganamiz:

🍞 Bread — non.
🍚 Rice — guruch.
🥚 Egg — tuxum.
🧀 Cheese — pishloq.
🍖 Meat — go‘sht.

Ovoz chiqarib: BREAD, RICE, EGG, CHEESE, MEAT. Tamom.""",
            wrapUpSummaryUzbek = """Bread, rice, egg, cheese, meat.
Bugun siz ovqatlarni o‘rgandingiz.
Nonni ko‘rsatib: “Bread!” deb ayting! 🍞""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_11_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 11,
            titleEnglish = "Drinks",
            titleUzbek = "11-Dars: Drinks",
            objectiveUzbek = "Ichimliklar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Drink — bu ichimlik.
Bugun 4 ta ichimlik o‘rganamiz:

💧 Water — suv.
🥛 Milk — sut.
🧃 Juice — sharbat.
🍵 Tea — choy.

Ovoz chiqarib: WATER, MILK, JUICE, TEA. Tamom.""",
            wrapUpSummaryUzbek = """Water, milk, juice, tea.
Bugun siz ichimliklarni o‘rgandingiz.
Suvni ko‘rsatib: “Water!” deb ayting! 💧""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_12_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 12,
            titleEnglish = "Fruits",
            titleUzbek = "12-Dars: Fruits",
            objectiveUzbek = "Mevalar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Fruit — bu meva.
Bugun 5 ta meva o‘rganamiz:

🍎 Apple — olma.
🍌 Banana — banan.
🍇 Grapes — uzum.
🍊 Orange — apelsin.
🍓 Strawberry — qulupnay.

Ovoz chiqarib: APPLE, BANANA, GRAPES, ORANGE, STRAWBERRY. Tamom.""",
            wrapUpSummaryUzbek = """Apple, banana, grapes, orange, strawberry.
Bugun siz mevalarni o‘rgandingiz.
Sevimli mevangizni ayting: “I like ___!” 🍎""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_13_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 13,
            titleEnglish = "Vegetables",
            titleUzbek = "13-Dars: Vegetables",
            objectiveUzbek = "Sabzavotlar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Vegetable — bu sabzavot.
Bugun 4 ta sabzavot o‘rganamiz:

🥕 Carrot — sabzi.
🍅 Tomato — pomidor.
🥒 Cucumber — bodring.
🧅 Onion — piyoz.

Ovoz chiqarib: CARROT, TOMATO, CUCUMBER, ONION. Tamom.""",
            wrapUpSummaryUzbek = """Carrot, tomato, cucumber, onion.
Bugun siz sabzavotlarni o‘rgandingiz.
Sabzini ko‘rsatib: “Carrot!” deb ayting! 🥕""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_14_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 14,
            titleEnglish = "Clothes",
            titleUzbek = "14-Dars: Clothes",
            objectiveUzbek = "Kiyimlar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Clothes — bu kiyimlar.
Bugun 5 ta kiyim o‘rganamiz:

👕 T-shirt — futbolka.
👖 Pants — shim.
👗 Dress — ko‘ylak.
🧦 Socks — paypoq.
👟 Shoes — poyabzal.

Ovoz chiqarib: T-SHIRT, PANTS, DRESS, SOCKS, SHOES. Tamom.""",
            wrapUpSummaryUzbek = """T-shirt, pants, dress, socks, shoes.
Bugun siz kiyimlarni o‘rgandingiz.
Futbolkangizni ko‘rsatib: “My T-shirt!” deb ayting! 👕""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_15_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 15,
            titleEnglish = "My House",
            titleUzbek = "15-Dars: My House",
            objectiveUzbek = "Uy mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """House — bu uy.
Uyingizda nimalar bor?

🚪 Door — eshik.
🪟 Window — deraza.
🛏️ Bed — karavot.
🪑 Chair — stul.
🪵 Table — stol.

Ovoz chiqarib: DOOR, WINDOW, BED, CHAIR, TABLE. Tamom.""",
            wrapUpSummaryUzbek = """Door, window, bed, chair, table.
Bugun siz uydagi narsalarni o‘rgandingiz.
Eshikni ko‘rsatib: “Door!” deb ayting! 🚪""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_16_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 16,
            titleEnglish = "My Room",
            titleUzbek = "16-Dars: My Room",
            objectiveUzbek = "Xona mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Room — bu xona.
Sizning xonangizda nimalar bor?

🛏️ Bed — karavot.
🧸 Teddy — ayiqcha.
📚 Books — kitoblar.
⚽ Ball — to‘p.
🎨 Picture — rasm.

Ovoz chiqarib: BED, TEDDY, BOOKS, BALL, PICTURE. Tamom.""",
            wrapUpSummaryUzbek = """Bed, teddy, books, ball, picture.
Bugun siz xonangizni o‘rgandingiz.
Xonangizni ko‘rsatib: “My room!” deb ayting! 🏠""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_17_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 17,
            titleEnglish = "School",
            titleUzbek = "17-Dars: School",
            objectiveUzbek = "Maktab mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """School — bu maktab.
Maktabda nimalar bor?

📖 Book — kitob.
✏️ Pen — ruchka.
✏️ Pencil — qalam.
🎒 Bag — sumka.
👩‍🏫 Teacher — o‘qituvchi.

Ovoz chiqarib: BOOK, PEN, PENCIL, BAG, TEACHER. Tamom.""",
            wrapUpSummaryUzbek = """Book, pen, pencil, bag, teacher.
Bugun siz maktabni o‘rgandingiz.
Kitobingizni ko‘rsatib: “My book!” deb ayting! 📖""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_18_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 18,
            titleEnglish = "Weather",
            titleUzbek = "18-Dars: Weather",
            objectiveUzbek = "Ob-havo mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Weather — bu ob-havo.
Bugun 4 ta ob-havo o‘rganamiz:

☀️ Sunny — quyoshli.
🌧️ Rainy — yomg‘irli.
❄️ Snowy — qorli.
💨 Windy — shamolli.

Ovoz chiqarib: SUNNY, RAINY, SNOWY, WINDY. Tamom.""",
            wrapUpSummaryUzbek = """Sunny, rainy, snowy, windy.
Bugun siz ob-havoni o‘rgandingiz.
Derazadan qarab: “It is sunny!” deb ayting! ☀️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_19_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 19,
            titleEnglish = "Big and Small",
            titleUzbek = "19-Dars: Big and Small",
            objectiveUzbek = "Katta va kichik mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z o‘rganamiz:

🐘 Big — katta.
🐭 Small — kichik.

Big elephant — katta fil.
Small mouse — kichik sichqon.

Big — katta. Small — kichik. Tamom.""",
            wrapUpSummaryUzbek = """Big — katta. Small — kichik.
Bugun siz katta va kichikni o‘rgandingiz.
Qo‘lingizni ko‘rsatib: “Big!” deb ayting! 🐘""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_20_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 20,
            titleEnglish = "Shapes",
            titleUzbek = "20-Dars: Shapes",
            objectiveUzbek = "Shakllar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Shape — bu shakl.
Bugun 4 ta shakl o‘rganamiz:

⭕ Circle — doira.
⬜ Square — kvadrat.
🔺 Triangle — uchburchak.
⭐ Star — yulduz.

Ovoz chiqarib: CIRCLE, SQUARE, TRIANGLE, STAR. Tamom.""",
            wrapUpSummaryUzbek = """Circle, square, triangle, star.
Bugun siz shakllarni o‘rgandingiz.
Derazani ko‘rsatib: “Square!” deb ayting! ⬜""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_21_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 21,
            titleEnglish = "I like...",
            titleUzbek = "21-Dars: I like...",
            objectiveUzbek = "Men yaxshi ko‘raman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I like... — bu “Men ... yaxshi ko‘raman” degani.

I like apples. — Men olmalarni yaxshi ko‘raman.
I like milk. — Men sutni yaxshi ko‘raman.
I like cats. — Men mushuklarni yaxshi ko‘raman.

I — men. Like — yaxshi ko‘raman. Tamom.""",
            wrapUpSummaryUzbek = """I like... — men yaxshi ko‘raman.
Bugun siz “yaxshi ko‘raman” deb aytishni o‘rgandingiz.
Onangizga ayting: “I like you!” ❤️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_22_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 22,
            titleEnglish = "I can...",
            titleUzbek = "22-Dars: I can...",
            objectiveUzbek = "Men qila olaman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I can... — bu “Men ... qila olaman” degani.

I can run. — Men yugura olaman.
I can jump. — Men sakray olaman.
I can sing. — Men qo‘shiq aytaman.

I — men. Can — qila olaman. Tamom.""",
            wrapUpSummaryUzbek = """I can... — men qila olaman.
Bugun siz “qila olaman” deb aytishni o‘rgandingiz.
Sakrab: “I can jump!” deb ayting! 🦘""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_23_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 23,
            titleEnglish = "I have...",
            titleUzbek = "23-Dars: I have...",
            objectiveUzbek = "Menda bor mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I have... — bu “Menda ... bor” degani.

I have a ball. — Menda to‘p bor.
I have a book. — Menda kitob bor.
I have a cat. — Menda mushuk bor.

I — men. Have — menda bor. Tamom.""",
            wrapUpSummaryUzbek = """I have... — menda bor.
Bugun siz “menda bor” deb aytishni o‘rgandingiz.
To‘pingizni ko‘tarib: “I have a ball!” deb ayting! ⚽""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_24_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 24,
            titleEnglish = "I see...",
            titleUzbek = "24-Dars: I see...",
            objectiveUzbek = "Men ko‘ryapman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I see... — bu “Men ... ko‘ryapman” degani.

I see a cat. — Men mushukni ko‘ryapman.
I see a ball. — Men to‘pni ko‘ryapman.
I see a bird. — Men qushni ko‘ryapman.

I — men. See — ko‘ryapman. Tamom.""",
            wrapUpSummaryUzbek = """I see... — men ko‘ryapman.
Bugun siz “ko‘ryapman” deb aytishni o‘rgandingiz.
Atrofga qarab: “I see a ___!” deb ayting! 👀""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_25_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 25,
            titleEnglish = "I want...",
            titleUzbek = "25-Dars: I want...",
            objectiveUzbek = "Men xohlayman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I want... — bu “Men ... xohlayman” degani.

I want water. — Men suv xohlayman.
I want milk. — Men sut xohlayman.
I want a ball. — Men to‘p xohlayman.

I — men. Want — xohlayman. Tamom.""",
            wrapUpSummaryUzbek = """I want... — men xohlayman.
Bugun siz “xohlayman” deb aytishni o‘rgandingiz.
Suv so‘rab: “I want water!” deb ayting! 💧""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_26_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 26,
            titleEnglish = "Sit down / Stand up",
            titleUzbek = "26-Dars: Sit down / Stand up",
            objectiveUzbek = "O‘tir / Tur mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki harakat o‘rganamiz:

🪑 Sit down — o‘tir.
🧍 Stand up — tur.

Sit down, please. — O‘tiring, iltimos.
Stand up, please. — Turing, iltimos.

Please — iltimos. Tamom.""",
            wrapUpSummaryUzbek = """Sit down — o‘tir. Stand up — tur.
Bugun siz ikkita harakatni o‘rgandingiz.
O‘tirib: “I sit down!” Turing: “I stand up!” 🪑""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_27_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 27,
            titleEnglish = "Run / Jump / Walk",
            titleUzbek = "27-Dars: Run / Jump / Walk",
            objectiveUzbek = "Yugur / Sakra / Yur mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun uchta harakat o‘rganamiz:

🏃 Run — yugur.
🦘 Jump — sakra.
🚶 Walk — yur.

I run. — Men yuguraman.
I jump. — Men sakrayman.
I walk. — Men yuraman. Tamom.""",
            wrapUpSummaryUzbek = """Run, jump, walk. Bugun siz harakatlarni o‘rgandingiz. Yugurib: “I run!” deb ayting! 🏃""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_28_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 28,
            titleEnglish = "Eat / Drink / Sleep",
            titleUzbek = "28-Dars: Eat / Drink / Sleep",
            objectiveUzbek = "Ye / Ich / Uxla mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun uchta harakat:

🍽️ Eat — ye.
🥤 Drink — ich.
😴 Sleep — uxla.

I eat. — Men yeyman.
I drink. — Men ichaman.
I sleep. — Men uxlayman. Tamom.""",
            wrapUpSummaryUzbek = """Eat, drink, sleep. Ovqat yeb: “I eat!” Sut ichib: “I drink!” 😴""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_29_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 29,
            titleEnglish = "Open / Close",
            titleUzbek = "29-Dars: Open / Close",
            objectiveUzbek = "Och / Yop mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki harakat:

🚪 Open — och.
🚪 Close — yop.

Open the door. — Eshikni och.
Close the door. — Eshikni yop. Tamom.""",
            wrapUpSummaryUzbek = """Open, close. Eshikni ochib: “Open!” Yopib: “Close!” 🚪""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_30_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 30,
            titleEnglish = "Give me / Show me",
            titleUzbek = "30-Dars: Give me / Show me",
            objectiveUzbek = "Menga ber / Ko‘rsat mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki ibora:

🤲 Give me — menga ber.
👀 Show me — menga ko‘rsat.

Give me a ball. — Menga to‘p ber.
Show me a ball. — Menga to‘pni ko‘rsat. Tamom.""",
            wrapUpSummaryUzbek = """Give me, show me. Onangizga: “Give me water!” Do‘stingizga: “Show me a ball!” 🤲""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_31_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 31,
            titleEnglish = "Hot / Cold",
            titleUzbek = "31-Dars: Hot / Cold",
            objectiveUzbek = "Issiq / Sovuq mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

🔥 Hot — issiq.
❄️ Cold — sovuq.

Hot tea — issiq choy.
Cold water — sovuq suv. Tamom.""",
            wrapUpSummaryUzbek = """Hot, cold. Choyga qarab: “Hot!” Muzga qarab: “Cold!” 🔥❄️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_32_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 32,
            titleEnglish = "Happy / Sad",
            titleUzbek = "32-Dars: Happy / Sad",
            objectiveUzbek = "Baxtli / Xafa mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki his:

😊 Happy — baxtli.
😢 Sad — xafa.

I am happy. — Men baxtliman.
I am sad. — Men xafaman. Tamom.""",
            wrapUpSummaryUzbek = """Happy, sad. Kulib: “I am happy!” Xafa bo‘lib: “I am sad.” 😊😢""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_33_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 33,
            titleEnglish = "Clean / Dirty",
            titleUzbek = "33-Dars: Clean / Dirty",
            objectiveUzbek = "Toza / Iflos mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

✨ Clean — toza.
💩 Dirty — iflos.

Clean hands — toza qo‘llar.
Dirty hands — iflos qo‘llar. Tamom.""",
            wrapUpSummaryUzbek = """Clean, dirty. Qo‘lingizni yuvib: “Clean!” Iflos bo‘lsa: “Dirty!” ✨💩""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_34_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 34,
            titleEnglish = "Fast / Slow",
            titleUzbek = "34-Dars: Fast / Slow",
            objectiveUzbek = "Tez / Sekin mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

🐆 Fast — tez.
🐢 Slow — sekin.

Fast car — tez mashina.
Slow turtle — sekin toshbaqa. Tamom.""",
            wrapUpSummaryUzbek = """Fast, slow. Yugurib: “I am fast!” Sekin yurib: “I am slow.” 🐆🐢""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_35_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 35,
            titleEnglish = "High / Low",
            titleUzbek = "35-Dars: High / Low",
            objectiveUzbek = "Baland / Past mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

⬆️ High — baland.
⬇️ Low — past.

High tree — baland daraxt.
Low chair — past stul. Tamom.""",
            wrapUpSummaryUzbek = """High, low. Osmonga qarab: “High!” Yerga qarab: “Low!” ⬆️⬇️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_36_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 36,
            titleEnglish = "Hungry / Full",
            titleUzbek = "36-Dars: Hungry / Full",
            objectiveUzbek = "Och / To‘q mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

🍽️ Hungry — och.
😋 Full — to‘q.

I am hungry. — Men ochman.
I am full. — Men to‘qman. Tamom.""",
            wrapUpSummaryUzbek = """Hungry, full. Ovqatdan oldin: “I am hungry!” Ovqatdan keyin: “I am full!” 🍽️😋""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_37_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 37,
            titleEnglish = "Day and Night",
            titleUzbek = "37-Dars: Day and Night",
            objectiveUzbek = "Kun va tun mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

☀️ Day — kun.
🌙 Night — tun.

It is day. — Kun.
It is night. — Tun. Tamom.""",
            wrapUpSummaryUzbek = """Day, night. Quyoshni ko‘rib: “Day!” Yulduzni ko‘rib: “Night!” ☀️🌙""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_38_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 38,
            titleEnglish = "Morning / Night",
            titleUzbek = "38-Dars: Morning / Night",
            objectiveUzbek = "Ertalab / Kechqurun mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki vaqt:

🌅 Morning — ertalab.
🌙 Night — kechqurun.

Good morning! — Xayrli tong!
Good night! — Xayrli tun! Tamom.""",
            wrapUpSummaryUzbek = """Morning, night. Uyg‘onib: “Good morning!” Uxlashdan oldin: “Good night!” 🌅🌙""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_39_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 39,
            titleEnglish = "Days",
            titleUzbek = "39-Dars: Days",
            objectiveUzbek = "Kunlar mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun hafta kunlari.
7 ta kun bor. Bugun 3 tasini o‘rganamiz:

📅 Monday — dushanba.
📅 Tuesday — seshanba.
📅 Sunday — yakshanba.

Today is Monday. — Bugun dushanba. Tamom.""",
            wrapUpSummaryUzbek = """Monday, Tuesday, Sunday. Bugun qaysi kun? “Today is ___!” 📅""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_40_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 40,
            titleEnglish = "Transport",
            titleUzbek = "40-Dars: Transport",
            objectiveUzbek = "Transport mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Transport — bu transport.
Bugun 4 ta transport o‘rganamiz:

🚗 Car — mashina.
🚌 Bus — avtobus.
🚲 Bike — velosiped.
✈️ Plane — samolyot.

Ovoz chiqarib: CAR, BUS, BIKE, PLANE. Tamom.""",
            wrapUpSummaryUzbek = """Car, bus, bike, plane. Mashinani ko‘rsatib: “Car!” Samolyotni ko‘rsatib: “Plane!” 🚗✈️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_41_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 41,
            titleEnglish = "In / On / Under",
            titleUzbek = "41-Dars: In / On / Under",
            objectiveUzbek = "Ichida / Ustida / Ostida mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun uchta so‘z — qayerda?

📦 In — ichida.
📖 On — ustida.
🐱 Under — ostida.

In the box — qutida.
On the table — stolda.
Under the bed — karavot ostida. Tamom.""",
            wrapUpSummaryUzbek = """In, on, under. Olmani qutiga solib: “In!” Stolga qo‘yib: “On!” Karavot ostiga: “Under!” 📦📖🐱""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_42_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 42,
            titleEnglish = "My Toy Box",
            titleUzbek = "42-Dars: My Toy Box",
            objectiveUzbek = "O‘yinchoq qutisi mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Toy box — bu o‘yinchoq qutisi.
Ichida o‘yinchoqlar bor.

My toy box — mening o‘yinchoq qutim.
In my toy box — o‘yinchoq qutimda. Tamom.""",
            wrapUpSummaryUzbek = """My toy box. O‘yinchoq qutingizni ko‘rsatib: “My toy box!” Ichidan to‘p olib: “A ball in my toy box!” 🧸""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_43_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 43,
            titleEnglish = "One / Many",
            titleUzbek = "43-Dars: One / Many",
            objectiveUzbek = "Bitta / Ko‘p mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

1️⃣ One — bitta.
🔢 Many — ko‘p.

One ball — bitta to‘p.
Many balls — ko‘p to‘plar. Tamom.""",
            wrapUpSummaryUzbek = """One, many. Bitta to‘p: “One ball!” Ko‘p to‘p: “Many balls!” 1️⃣🔢""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_44_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 44,
            titleEnglish = "Yes / No",
            titleUzbek = "44-Dars: Yes / No",
            objectiveUzbek = "Ha / Yo‘q mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki so‘z:

✅ Yes — ha.
❌ No — yo‘q.

Yes, I am. — Ha, men.
No, I am not. — Yo‘q, men emas. Tamom.""",
            wrapUpSummaryUzbek = """Yes, no. Rozi bo‘lib: “Yes!” Rozi bo‘lmay: “No!” ✅❌""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_45_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 45,
            titleEnglish = "Please / Thank you",
            titleUzbek = "45-Dars: Please / Thank you",
            objectiveUzbek = "Iltimos / Rahmat mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun ikki odob so‘zi:

🙏 Please — iltimos.
🙏 Thank you — rahmat.

Please, give me water. — Iltimos, menga suv ber.
Thank you! — Rahmat! Tamom.""",
            wrapUpSummaryUzbek = """Please, thank you. Biror narsa so‘rab: “Please!” Olgandan keyin: “Thank you!” 🙏""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_46_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 46,
            titleEnglish = "I am sorry",
            titleUzbek = "46-Dars: I am sorry",
            objectiveUzbek = "Kechirasiz mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I am sorry — bu “Kechirasiz” degani.

Xato qilsangiz — I am sorry.
Kimnidir urib yuborsangiz — I am sorry. Tamom.""",
            wrapUpSummaryUzbek = """I am sorry. Xato qilsangiz: “I am sorry!” 🙏""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_47_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 47,
            titleEnglish = "Goodbye",
            titleUzbek = "47-Dars: Goodbye",
            objectiveUzbek = "Xayr mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Goodbye — bu “Xayr” degani.

Uydan chiqayotganda — Goodbye!
Do‘st bilan xayrlashganda — Goodbye! Tamom.""",
            wrapUpSummaryUzbek = """Goodbye. Uydan chiqib: “Goodbye, mom!” 👋""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_48_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 48,
            titleEnglish = "My Pet",
            titleUzbek = "48-Dars: My Pet",
            objectiveUzbek = "Uy hayvonim mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Pet — bu uy hayvoni.
Sizning uy hayvoningiz bormi?

🐱 Cat — mushuk.
🐶 Dog — it.
🐟 Fish — baliq.
🐦 Bird — qush.

My pet — mening uy hayvonim. Tamom.""",
            wrapUpSummaryUzbek = """My pet. Uy hayvoningizni ko‘rsatib: “My pet is a ___!” 🐱🐶""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_49_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 49,
            titleEnglish = "I Love Mom",
            titleUzbek = "49-Dars: I Love Mom",
            objectiveUzbek = "Onamni yaxshi ko‘raman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """I love... — bu “Men ... yaxshi ko‘raman” degani.
Love — juda kuchli yaxshi ko‘rish.

I love mom. — Men onamni juda yaxshi ko‘raman.
I love dad. — Men dadamni juda yaxshi ko‘raman. Tamom.""",
            wrapUpSummaryUzbek = """I love... Onangizni quchoqlab: “I love mom!” ❤️""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_50_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 50,
            titleEnglish = "I am a boy / girl",
            titleUzbek = "50-Dars: I am a boy / girl",
            objectiveUzbek = "Men bolaman / qizman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun o‘zingiz haqingizda:

👦 I am a boy. — Men bolaman.
👧 I am a girl. — Men qizman.

Boy — bola. Girl — qiz. Tamom.""",
            wrapUpSummaryUzbek = """I am a boy / girl. O‘zingizni ko‘rsatib: “I am a ___!” 👦👧""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_51_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 51,
            titleEnglish = "I am happy",
            titleUzbek = "51-Dars: I am happy",
            objectiveUzbek = "Men baxtliman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun o‘zingiz haqingizda:

😊 I am happy. — Men baxtliman.
😢 I am sad. — Men xafaman.
😴 I am tired. — Men charchadim.

I am — men ...man. Tamom.""",
            wrapUpSummaryUzbek = """I am... Kulib: “I am happy!” Esnab: “I am tired!” 😊😴""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_52_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 52,
            titleEnglish = "I go",
            titleUzbek = "52-Dars: I go",
            objectiveUzbek = "Men boraman mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Go — bu “bormoq” degani.

I go to school. — Men maktabga boraman.
I go to the park. — Men parkga boraman.
I go home. — Men uyga boraman.

Go — harakat. Bir joydan boshqa joyga. Tamom.""",
            wrapUpSummaryUzbek = """I go. Maktabga: “I go to school!” Uyga: “I go home!” 🚶""",
            isUnlocked = false,
            isCompleted = false
        ),
        LessonSectionEntity(
            id = DARS_53_SECTION_ID,
            topicId = CurriculumLevel.A0.topicId,
            orderIndex = 53,
            titleEnglish = "Hammasi birga — Wrap up",
            titleUzbek = "53-Dars: Hammasi birga — Wrap up",
            objectiveUzbek = "Hammasi birga mavzusini o‘rganish va amalda qo‘llash.",
            explanationUzbek = """Bugun yangi narsa yo‘q.
Bugun A0 ning hamma darsini birga ko‘ramiz.

Siz 52 ta dars o‘rgandingiz.
Endi hammasini bitta joyda eslaymiz. Tamom.""",
            wrapUpSummaryUzbek = """Bugun biz 52 ta darsni birga ko‘rdik.

Siz juda ko'p narsani bilasiz: Salomlashish, ism aytish, ranglar, sonlar, hayvonlar, oila, tana, o'yinchoqlar, ovqat, kiyim, uy, maktab, ob-havo, sifatlar, harakatlar va odob so'zlari!

Tabriklaymiz! A0 kursini a'lo darajada yakunladingiz! 🎉""",
            isUnlocked = false,
            isCompleted = false
        )
    )

    fun buildVocabulary(): List<VocabularyEntity> = listOf(
        VocabularyEntity(
            id = "v_a0_01_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "hello",
            uzbekTranslation = "salom",
            exampleEnglish = "Hello, friend!",
            exampleUzbek = "Salom, do‘stim!"
        ),
        VocabularyEntity(
            id = "v_a0_01_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "hello, mom!",
            uzbekTranslation = "salom, ona!",
            exampleEnglish = "Hello, mom!",
            exampleUzbek = "Salom, oyijon!"
        ),
        VocabularyEntity(
            id = "v_a0_01_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "hello, dad!",
            uzbekTranslation = "salom, dada!",
            exampleEnglish = "Hello, dad!",
            exampleUzbek = "Salom, dada!"
        ),
        VocabularyEntity(
            id = "v_a0_01_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            englishWord = "hello, friend!",
            uzbekTranslation = "salom, do'st!",
            exampleEnglish = "Hello, friend!",
            exampleUzbek = "Salom, do‘stim!"
        ),
        VocabularyEntity(
            id = "v_a0_02_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "my name is...",
            uzbekTranslation = "mening ismim...",
            exampleEnglish = "My name is Aziz.",
            exampleUzbek = "Mening ismim Aziz."
        ),
        VocabularyEntity(
            id = "v_a0_02_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "name",
            uzbekTranslation = "ism",
            exampleEnglish = "My name is Tom.",
            exampleUzbek = "Mening ismim Tom."
        ),
        VocabularyEntity(
            id = "v_a0_02_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "my name",
            uzbekTranslation = "mening ismim",
            exampleEnglish = "My name is Anna.",
            exampleUzbek = "Mening ismim Anna."
        ),
        VocabularyEntity(
            id = "v_a0_02_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            englishWord = "what's your name?",
            uzbekTranslation = "ismingiz nima?",
            exampleEnglish = "What's your name?",
            exampleUzbek = "Ismingiz nima?"
        ),
        VocabularyEntity(
            id = "v_a0_03_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "red",
            uzbekTranslation = "qizil",
            exampleEnglish = "Red apple.",
            exampleUzbek = "Qizil olma."
        ),
        VocabularyEntity(
            id = "v_a0_03_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "blue",
            uzbekTranslation = "ko'k",
            exampleEnglish = "Blue sky.",
            exampleUzbek = "Ko'k osmon."
        ),
        VocabularyEntity(
            id = "v_a0_03_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "green",
            uzbekTranslation = "yashil",
            exampleEnglish = "Green tree.",
            exampleUzbek = "Yashil daraxt."
        ),
        VocabularyEntity(
            id = "v_a0_03_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "yellow",
            uzbekTranslation = "sariq",
            exampleEnglish = "Yellow sun.",
            exampleUzbek = "Sariq quyosh."
        ),
        VocabularyEntity(
            id = "v_a0_03_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            englishWord = "black",
            uzbekTranslation = "qora",
            exampleEnglish = "Black cat.",
            exampleUzbek = "Qora mushuk."
        ),
        VocabularyEntity(
            id = "v_a0_04_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "one",
            uzbekTranslation = "bir, bitta",
            exampleEnglish = "One book.",
            exampleUzbek = "Bitta kitob."
        ),
        VocabularyEntity(
            id = "v_a0_04_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "two",
            uzbekTranslation = "ikki, ikkita",
            exampleEnglish = "Two cats.",
            exampleUzbek = "Ikkita mushuk."
        ),
        VocabularyEntity(
            id = "v_a0_04_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "three",
            uzbekTranslation = "uch, uchta",
            exampleEnglish = "Three apples.",
            exampleUzbek = "Uchta olma."
        ),
        VocabularyEntity(
            id = "v_a0_04_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "four",
            uzbekTranslation = "to'rt, to'rtta",
            exampleEnglish = "Four balls.",
            exampleUzbek = "To'rtta to'p."
        ),
        VocabularyEntity(
            id = "v_a0_04_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            englishWord = "five",
            uzbekTranslation = "besh, beshta",
            exampleEnglish = "Five stars.",
            exampleUzbek = "Beshta yulduz."
        ),
        VocabularyEntity(
            id = "v_a0_05_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "six",
            uzbekTranslation = "olti",
            exampleEnglish = "Six pens.",
            exampleUzbek = "Oltita ruchka."
        ),
        VocabularyEntity(
            id = "v_a0_05_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "seven",
            uzbekTranslation = "yetti",
            exampleEnglish = "Seven dogs.",
            exampleUzbek = "Yettita it."
        ),
        VocabularyEntity(
            id = "v_a0_05_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "eight",
            uzbekTranslation = "sakkiz",
            exampleEnglish = "Eight birds.",
            exampleUzbek = "Sakkizta qush."
        ),
        VocabularyEntity(
            id = "v_a0_05_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "nine",
            uzbekTranslation = "to'qqiz",
            exampleEnglish = "Nine cups.",
            exampleUzbek = "To'qqizta piyola."
        ),
        VocabularyEntity(
            id = "v_a0_05_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            englishWord = "ten",
            uzbekTranslation = "o'n",
            exampleEnglish = "Ten fingers.",
            exampleUzbek = "O'nta barmoq."
        ),
        VocabularyEntity(
            id = "v_a0_06_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "cat",
            uzbekTranslation = "mushuk",
            exampleEnglish = "Hello, cat!",
            exampleUzbek = "Salom, mushuk!"
        ),
        VocabularyEntity(
            id = "v_a0_06_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "dog",
            uzbekTranslation = "it",
            exampleEnglish = "Hello, dog!",
            exampleUzbek = "Salom, kuchuk!"
        ),
        VocabularyEntity(
            id = "v_a0_06_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "fish",
            uzbekTranslation = "baliq",
            exampleEnglish = "One fish.",
            exampleUzbek = "Bitta baliq."
        ),
        VocabularyEntity(
            id = "v_a0_06_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "bird",
            uzbekTranslation = "qush",
            exampleEnglish = "Blue bird.",
            exampleUzbek = "Ko'k qush."
        ),
        VocabularyEntity(
            id = "v_a0_06_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            englishWord = "elephant",
            uzbekTranslation = "fil",
            exampleEnglish = "Big elephant.",
            exampleUzbek = "Katta fil."
        ),
        VocabularyEntity(
            id = "v_a0_07_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "mom",
            uzbekTranslation = "ona",
            exampleEnglish = "Hello, mom!",
            exampleUzbek = "Salom, oyijon!"
        ),
        VocabularyEntity(
            id = "v_a0_07_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "dad",
            uzbekTranslation = "dada",
            exampleEnglish = "Hello, dad!",
            exampleUzbek = "Salom, dada!"
        ),
        VocabularyEntity(
            id = "v_a0_07_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "brother",
            uzbekTranslation = "aka/uka",
            exampleEnglish = "My brother.",
            exampleUzbek = "Mening ukam."
        ),
        VocabularyEntity(
            id = "v_a0_07_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "sister",
            uzbekTranslation = "opa/singil",
            exampleEnglish = "My sister.",
            exampleUzbek = "Mening singlim."
        ),
        VocabularyEntity(
            id = "v_a0_07_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            englishWord = "baby",
            uzbekTranslation = "chaqaloq",
            exampleEnglish = "Sweet baby.",
            exampleUzbek = "Shirin chaqaloq."
        ),
        VocabularyEntity(
            id = "v_a0_08_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "eyes",
            uzbekTranslation = "ko'zlar",
            exampleEnglish = "Two eyes.",
            exampleUzbek = "Ikkita ko'z."
        ),
        VocabularyEntity(
            id = "v_a0_08_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "ears",
            uzbekTranslation = "quloqlar",
            exampleEnglish = "Two ears.",
            exampleUzbek = "Ikkita quloq."
        ),
        VocabularyEntity(
            id = "v_a0_08_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "nose",
            uzbekTranslation = "burun",
            exampleEnglish = "One nose.",
            exampleUzbek = "Bitta burun."
        ),
        VocabularyEntity(
            id = "v_a0_08_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "mouth",
            uzbekTranslation = "og'iz",
            exampleEnglish = "One mouth.",
            exampleUzbek = "Bitta og'iz."
        ),
        VocabularyEntity(
            id = "v_a0_08_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            englishWord = "hands",
            uzbekTranslation = "qo'llar",
            exampleEnglish = "Two hands.",
            exampleUzbek = "Ikkita qo'l."
        ),
        VocabularyEntity(
            id = "v_a0_09_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "ball",
            uzbekTranslation = "to'p",
            exampleEnglish = "Red ball.",
            exampleUzbek = "Qizil to'p."
        ),
        VocabularyEntity(
            id = "v_a0_09_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "teddy",
            uzbekTranslation = "ayiqcha",
            exampleEnglish = "Soft teddy.",
            exampleUzbek = "Yumshoq ayiqcha."
        ),
        VocabularyEntity(
            id = "v_a0_09_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "car",
            uzbekTranslation = "mashina",
            exampleEnglish = "Fast car.",
            exampleUzbek = "Tez mashina."
        ),
        VocabularyEntity(
            id = "v_a0_09_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "blocks",
            uzbekTranslation = "kubiklar",
            exampleEnglish = "Toy blocks.",
            exampleUzbek = "O'yinchoq kubiklar."
        ),
        VocabularyEntity(
            id = "v_a0_09_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            englishWord = "yo-yo",
            uzbekTranslation = "yo-yo o'yinchog'i",
            exampleEnglish = "Blue yo-yo.",
            exampleUzbek = "Ko'k yo-yo."
        ),
        VocabularyEntity(
            id = "v_a0_10_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "bread",
            uzbekTranslation = "non",
            exampleEnglish = "Fresh bread.",
            exampleUzbek = "Yangi non."
        ),
        VocabularyEntity(
            id = "v_a0_10_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "rice",
            uzbekTranslation = "guruch",
            exampleEnglish = "White rice.",
            exampleUzbek = "Oq guruch."
        ),
        VocabularyEntity(
            id = "v_a0_10_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "egg",
            uzbekTranslation = "tuxum",
            exampleEnglish = "One egg.",
            exampleUzbek = "Bitta tuxum."
        ),
        VocabularyEntity(
            id = "v_a0_10_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "cheese",
            uzbekTranslation = "pishloq",
            exampleEnglish = "Yellow cheese.",
            exampleUzbek = "Sariq pishloq."
        ),
        VocabularyEntity(
            id = "v_a0_10_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            englishWord = "meat",
            uzbekTranslation = "go'sht",
            exampleEnglish = "Tasty meat.",
            exampleUzbek = "Mazali go'sht."
        ),
        VocabularyEntity(
            id = "v_a0_11_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "water",
            uzbekTranslation = "suv",
            exampleEnglish = "Cold water.",
            exampleUzbek = "Sovuq suv."
        ),
        VocabularyEntity(
            id = "v_a0_11_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "milk",
            uzbekTranslation = "sut",
            exampleEnglish = "White milk.",
            exampleUzbek = "Oq sut."
        ),
        VocabularyEntity(
            id = "v_a0_11_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "juice",
            uzbekTranslation = "sharbat",
            exampleEnglish = "Sweet juice.",
            exampleUzbek = "Shirin sharbat."
        ),
        VocabularyEntity(
            id = "v_a0_11_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            englishWord = "tea",
            uzbekTranslation = "choy",
            exampleEnglish = "Hot tea.",
            exampleUzbek = "Issiq choy."
        ),
        VocabularyEntity(
            id = "v_a0_12_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "apple",
            uzbekTranslation = "olma",
            exampleEnglish = "Red apple.",
            exampleUzbek = "Qizil olma."
        ),
        VocabularyEntity(
            id = "v_a0_12_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "banana",
            uzbekTranslation = "banan",
            exampleEnglish = "Yellow banana.",
            exampleUzbek = "Sariq banan."
        ),
        VocabularyEntity(
            id = "v_a0_12_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "grapes",
            uzbekTranslation = "uzum",
            exampleEnglish = "Sweet grapes.",
            exampleUzbek = "Shirin uzum."
        ),
        VocabularyEntity(
            id = "v_a0_12_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "orange",
            uzbekTranslation = "apelsin",
            exampleEnglish = "Juicy orange.",
            exampleUzbek = "Sershira apelsin."
        ),
        VocabularyEntity(
            id = "v_a0_12_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            englishWord = "strawberry",
            uzbekTranslation = "qulupnay",
            exampleEnglish = "Red strawberry.",
            exampleUzbek = "Qizil qulupnay."
        ),
        VocabularyEntity(
            id = "v_a0_13_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "carrot",
            uzbekTranslation = "sabzi",
            exampleEnglish = "Orange carrot.",
            exampleUzbek = "To'q sariq sabzi."
        ),
        VocabularyEntity(
            id = "v_a0_13_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "tomato",
            uzbekTranslation = "pomidor",
            exampleEnglish = "Red tomato.",
            exampleUzbek = "Qizil pomidor."
        ),
        VocabularyEntity(
            id = "v_a0_13_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "cucumber",
            uzbekTranslation = "bodring",
            exampleEnglish = "Green cucumber.",
            exampleUzbek = "Yashil bodring."
        ),
        VocabularyEntity(
            id = "v_a0_13_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            englishWord = "onion",
            uzbekTranslation = "piyoz",
            exampleEnglish = "White onion.",
            exampleUzbek = "Oq piyoz."
        ),
        VocabularyEntity(
            id = "v_a0_14_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "t-shirt",
            uzbekTranslation = "futbolka",
            exampleEnglish = "My T-shirt.",
            exampleUzbek = "Mening futbolkam."
        ),
        VocabularyEntity(
            id = "v_a0_14_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "pants",
            uzbekTranslation = "shim",
            exampleEnglish = "Blue pants.",
            exampleUzbek = "Ko'k shim."
        ),
        VocabularyEntity(
            id = "v_a0_14_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "dress",
            uzbekTranslation = "ko'ylak",
            exampleEnglish = "Pretty dress.",
            exampleUzbek = "Chiroyli ko'ylak."
        ),
        VocabularyEntity(
            id = "v_a0_14_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "socks",
            uzbekTranslation = "paypoq",
            exampleEnglish = "Warm socks.",
            exampleUzbek = "Issiq paypoq."
        ),
        VocabularyEntity(
            id = "v_a0_14_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            englishWord = "shoes",
            uzbekTranslation = "poyabzal",
            exampleEnglish = "New shoes.",
            exampleUzbek = "Yangi poyabzal."
        ),
        VocabularyEntity(
            id = "v_a0_15_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "house",
            uzbekTranslation = "uy",
            exampleEnglish = "My house.",
            exampleUzbek = "Mening uyim."
        ),
        VocabularyEntity(
            id = "v_a0_15_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "door",
            uzbekTranslation = "eshik",
            exampleEnglish = "Big door.",
            exampleUzbek = "Katta eshik."
        ),
        VocabularyEntity(
            id = "v_a0_15_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "window",
            uzbekTranslation = "deraza",
            exampleEnglish = "Open window.",
            exampleUzbek = "Ochiq deraza."
        ),
        VocabularyEntity(
            id = "v_a0_15_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "bed",
            uzbekTranslation = "karavot",
            exampleEnglish = "Soft bed.",
            exampleUzbek = "Yumshoq karavot."
        ),
        VocabularyEntity(
            id = "v_a0_15_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "chair",
            uzbekTranslation = "stul",
            exampleEnglish = "Small chair.",
            exampleUzbek = "Kichik stul."
        ),
        VocabularyEntity(
            id = "v_a0_15_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            englishWord = "table",
            uzbekTranslation = "stol",
            exampleEnglish = "Big table.",
            exampleUzbek = "Katta stol."
        ),
        VocabularyEntity(
            id = "v_a0_16_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "room",
            uzbekTranslation = "xona",
            exampleEnglish = "My room.",
            exampleUzbek = "Mening xonam."
        ),
        VocabularyEntity(
            id = "v_a0_16_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "my room",
            uzbekTranslation = "mening xonam",
            exampleEnglish = "This is my room.",
            exampleUzbek = "Bu mening xonam."
        ),
        VocabularyEntity(
            id = "v_a0_16_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "picture",
            uzbekTranslation = "rasm",
            exampleEnglish = "Nice picture.",
            exampleUzbek = "Chiroyli rasm."
        ),
        VocabularyEntity(
            id = "v_a0_16_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            englishWord = "books",
            uzbekTranslation = "kitoblar",
            exampleEnglish = "Many books.",
            exampleUzbek = "Ko'p kitoblar."
        ),
        VocabularyEntity(
            id = "v_a0_17_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "school",
            uzbekTranslation = "maktab",
            exampleEnglish = "My school.",
            exampleUzbek = "Mening maktabim."
        ),
        VocabularyEntity(
            id = "v_a0_17_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "book",
            uzbekTranslation = "kitob",
            exampleEnglish = "New book.",
            exampleUzbek = "Yangi kitob."
        ),
        VocabularyEntity(
            id = "v_a0_17_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "pen",
            uzbekTranslation = "ruchka",
            exampleEnglish = "Blue pen.",
            exampleUzbek = "Ko'k ruchka."
        ),
        VocabularyEntity(
            id = "v_a0_17_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "pencil",
            uzbekTranslation = "qalam",
            exampleEnglish = "Red pencil.",
            exampleUzbek = "Qizil qalam."
        ),
        VocabularyEntity(
            id = "v_a0_17_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "bag",
            uzbekTranslation = "sumka",
            exampleEnglish = "School bag.",
            exampleUzbek = "Maktab sumkasi."
        ),
        VocabularyEntity(
            id = "v_a0_17_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            englishWord = "teacher",
            uzbekTranslation = "o'qituvchi",
            exampleEnglish = "Hello, teacher!",
            exampleUzbek = "Assalomu alaykum, ustoz!"
        ),
        VocabularyEntity(
            id = "v_a0_18_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "weather",
            uzbekTranslation = "ob-havo",
            exampleEnglish = "Good weather.",
            exampleUzbek = "Yaxshi ob-havo."
        ),
        VocabularyEntity(
            id = "v_a0_18_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "sunny",
            uzbekTranslation = "quyoshli",
            exampleEnglish = "Sunny day.",
            exampleUzbek = "Quyoshli kun."
        ),
        VocabularyEntity(
            id = "v_a0_18_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "rainy",
            uzbekTranslation = "yomg'irli",
            exampleEnglish = "Rainy day.",
            exampleUzbek = "Yomg'irli kun."
        ),
        VocabularyEntity(
            id = "v_a0_18_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "snowy",
            uzbekTranslation = "qorli",
            exampleEnglish = "Snowy day.",
            exampleUzbek = "Qorli kun."
        ),
        VocabularyEntity(
            id = "v_a0_18_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            englishWord = "windy",
            uzbekTranslation = "shamolli",
            exampleEnglish = "Windy day.",
            exampleUzbek = "Shamolli kun."
        ),
        VocabularyEntity(
            id = "v_a0_19_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "big",
            uzbekTranslation = "katta",
            exampleEnglish = "Big elephant.",
            exampleUzbek = "Katta fil."
        ),
        VocabularyEntity(
            id = "v_a0_19_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "small",
            uzbekTranslation = "kichik",
            exampleEnglish = "Small mouse.",
            exampleUzbek = "Kichik sichqon."
        ),
        VocabularyEntity(
            id = "v_a0_19_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "big elephant",
            uzbekTranslation = "katta fil",
            exampleEnglish = "Look at the big elephant.",
            exampleUzbek = "Katta filga qarang."
        ),
        VocabularyEntity(
            id = "v_a0_19_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            englishWord = "small mouse",
            uzbekTranslation = "kichik sichqon",
            exampleEnglish = "A small mouse runs.",
            exampleUzbek = "Kichik sichqon yuguradi."
        ),
        VocabularyEntity(
            id = "v_a0_20_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            englishWord = "shape",
            uzbekTranslation = "shakl",
            exampleEnglish = "Nice shape.",
            exampleUzbek = "Chiroyli shakl."
        ),
        VocabularyEntity(
            id = "v_a0_20_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            englishWord = "circle",
            uzbekTranslation = "doira",
            exampleEnglish = "Round circle.",
            exampleUzbek = "Dumaloq doira."
        ),
        VocabularyEntity(
            id = "v_a0_20_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            englishWord = "square",
            uzbekTranslation = "kvadrat",
            exampleEnglish = "Four-sided square.",
            exampleUzbek = "To'rt burchakli kvadrat."
        ),
        VocabularyEntity(
            id = "v_a0_20_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            englishWord = "triangle",
            uzbekTranslation = "uchburchak",
            exampleEnglish = "Three-sided triangle.",
            exampleUzbek = "Uch burchakli uchburchak."
        ),
        VocabularyEntity(
            id = "v_a0_20_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            englishWord = "star",
            uzbekTranslation = "yulduz shakli",
            exampleEnglish = "Bright star.",
            exampleUzbek = "Yorqin yulduz."
        ),
        VocabularyEntity(
            id = "v_a0_21_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            englishWord = "i like",
            uzbekTranslation = "men yaxshi ko'raman",
            exampleEnglish = "I like toys.",
            exampleUzbek = "Men o'yinchoqlarni yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_21_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            englishWord = "i like apples",
            uzbekTranslation = "men olmalarni yaxshi ko'raman",
            exampleEnglish = "I like apples very much.",
            exampleUzbek = "Men olmalarni juda yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_21_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            englishWord = "i like milk",
            uzbekTranslation = "men sutni yaxshi ko'raman",
            exampleEnglish = "I like milk in the morning.",
            exampleUzbek = "Men ertalab sutni yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_22_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            englishWord = "i can",
            uzbekTranslation = "men qila olaman",
            exampleEnglish = "I can swim.",
            exampleUzbek = "Men suza olaman."
        ),
        VocabularyEntity(
            id = "v_a0_22_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            englishWord = "i can run",
            uzbekTranslation = "men yugura olaman",
            exampleEnglish = "I can run fast.",
            exampleUzbek = "Men tez yugura olaman."
        ),
        VocabularyEntity(
            id = "v_a0_22_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            englishWord = "i can jump",
            uzbekTranslation = "men sakray olaman",
            exampleEnglish = "I can jump high.",
            exampleUzbek = "Men baland sakray olaman."
        ),
        VocabularyEntity(
            id = "v_a0_22_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            englishWord = "i can sing",
            uzbekTranslation = "men qo'shiq aytaman",
            exampleEnglish = "I can sing well.",
            exampleUzbek = "Men yaxshi qo'shiq aytaman."
        ),
        VocabularyEntity(
            id = "v_a0_23_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            englishWord = "i have",
            uzbekTranslation = "menda bor",
            exampleEnglish = "I have a pen.",
            exampleUzbek = "Menda ruchka bor."
        ),
        VocabularyEntity(
            id = "v_a0_23_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            englishWord = "i have a ball",
            uzbekTranslation = "menda to'p bor",
            exampleEnglish = "I have a ball to play.",
            exampleUzbek = "Menda o'ynashga to'p bor."
        ),
        VocabularyEntity(
            id = "v_a0_23_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            englishWord = "i have a book",
            uzbekTranslation = "menda kitob bor",
            exampleEnglish = "I have a book to read.",
            exampleUzbek = "Menda o'qishga kitob bor."
        ),
        VocabularyEntity(
            id = "v_a0_24_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            englishWord = "i see",
            uzbekTranslation = "men ko'ryapman",
            exampleEnglish = "I see a dog.",
            exampleUzbek = "Men itni ko'ryapman."
        ),
        VocabularyEntity(
            id = "v_a0_24_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            englishWord = "i see a cat",
            uzbekTranslation = "men mushukni ko'ryapman",
            exampleEnglish = "I see a cat in the room.",
            exampleUzbek = "Men xonada mushukni ko'ryapman."
        ),
        VocabularyEntity(
            id = "v_a0_24_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            englishWord = "i see a ball",
            uzbekTranslation = "men to'pni ko'ryapman",
            exampleEnglish = "I see a ball on the grass.",
            exampleUzbek = "Men o't ustida to'pni ko'ryapman."
        ),
        VocabularyEntity(
            id = "v_a0_25_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            englishWord = "i want",
            uzbekTranslation = "men xohlayman",
            exampleEnglish = "I want an apple.",
            exampleUzbek = "Men olma xohlayman."
        ),
        VocabularyEntity(
            id = "v_a0_25_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            englishWord = "i want water",
            uzbekTranslation = "men suv xohlayman",
            exampleEnglish = "I want water, please.",
            exampleUzbek = "Men suv xohlayman, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_25_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            englishWord = "i want milk",
            uzbekTranslation = "men sut xohlayman",
            exampleEnglish = "I want milk now.",
            exampleUzbek = "Men hozir sut xohlayman."
        ),
        VocabularyEntity(
            id = "v_a0_26_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            englishWord = "sit down",
            uzbekTranslation = "o'tir",
            exampleEnglish = "Please sit down.",
            exampleUzbek = "Iltimos o'tir."
        ),
        VocabularyEntity(
            id = "v_a0_26_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            englishWord = "stand up",
            uzbekTranslation = "tur",
            exampleEnglish = "Please stand up.",
            exampleUzbek = "Iltimos tur."
        ),
        VocabularyEntity(
            id = "v_a0_26_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            englishWord = "sit down, please",
            uzbekTranslation = "o'tiring, iltimos",
            exampleEnglish = "Sit down, please.",
            exampleUzbek = "O'tiring, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_26_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            englishWord = "stand up, please",
            uzbekTranslation = "turing, iltimos",
            exampleEnglish = "Stand up, please.",
            exampleUzbek = "Turing, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_27_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            englishWord = "run",
            uzbekTranslation = "yugur",
            exampleEnglish = "Run fast.",
            exampleUzbek = "Tez yugur."
        ),
        VocabularyEntity(
            id = "v_a0_27_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            englishWord = "jump",
            uzbekTranslation = "sakra",
            exampleEnglish = "Jump high.",
            exampleUzbek = "Baland sakra."
        ),
        VocabularyEntity(
            id = "v_a0_27_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            englishWord = "walk",
            uzbekTranslation = "yur",
            exampleEnglish = "Walk slowly.",
            exampleUzbek = "Sekin yur."
        ),
        VocabularyEntity(
            id = "v_a0_27_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            englishWord = "i run",
            uzbekTranslation = "men yuguraman",
            exampleEnglish = "I run every day.",
            exampleUzbek = "Men har kuni yuguraman."
        ),
        VocabularyEntity(
            id = "v_a0_27_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            englishWord = "i walk",
            uzbekTranslation = "men yuraman",
            exampleEnglish = "I walk to school.",
            exampleUzbek = "Men maktabga yurib boraman."
        ),
        VocabularyEntity(
            id = "v_a0_28_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            englishWord = "eat",
            uzbekTranslation = "ye, yemoq",
            exampleEnglish = "Eat an apple.",
            exampleUzbek = "Olma ye."
        ),
        VocabularyEntity(
            id = "v_a0_28_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            englishWord = "drink",
            uzbekTranslation = "ich, ichmoq",
            exampleEnglish = "Drink water.",
            exampleUzbek = "Suv ich."
        ),
        VocabularyEntity(
            id = "v_a0_28_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            englishWord = "sleep",
            uzbekTranslation = "uxla, uxlamoq",
            exampleEnglish = "Sleep well.",
            exampleUzbek = "Yaxshi uxla."
        ),
        VocabularyEntity(
            id = "v_a0_28_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            englishWord = "i eat",
            uzbekTranslation = "men yeyman",
            exampleEnglish = "I eat an apple.",
            exampleUzbek = "Men olma yeyman."
        ),
        VocabularyEntity(
            id = "v_a0_28_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            englishWord = "i sleep",
            uzbekTranslation = "men uxlayman",
            exampleEnglish = "I sleep at night.",
            exampleUzbek = "Men kechasi uxlayman."
        ),
        VocabularyEntity(
            id = "v_a0_29_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            englishWord = "open",
            uzbekTranslation = "och, ochmoq",
            exampleEnglish = "Open your book.",
            exampleUzbek = "Kitobingni och."
        ),
        VocabularyEntity(
            id = "v_a0_29_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            englishWord = "close",
            uzbekTranslation = "yop, yopmoq",
            exampleEnglish = "Close the window.",
            exampleUzbek = "Derazani yop."
        ),
        VocabularyEntity(
            id = "v_a0_29_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            englishWord = "open the door",
            uzbekTranslation = "eshikni och",
            exampleEnglish = "Please open the door.",
            exampleUzbek = "Iltimos eshikni och."
        ),
        VocabularyEntity(
            id = "v_a0_29_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            englishWord = "close the door",
            uzbekTranslation = "eshikni yop",
            exampleEnglish = "Please close the door.",
            exampleUzbek = "Iltimos eshikni yop."
        ),
        VocabularyEntity(
            id = "v_a0_30_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            englishWord = "give me",
            uzbekTranslation = "menga ber",
            exampleEnglish = "Give me water.",
            exampleUzbek = "Menga suv ber."
        ),
        VocabularyEntity(
            id = "v_a0_30_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            englishWord = "show me",
            uzbekTranslation = "menga ko'rsat",
            exampleEnglish = "Show me a cat.",
            exampleUzbek = "Menga mushukni ko'rsat."
        ),
        VocabularyEntity(
            id = "v_a0_30_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            englishWord = "give me a ball",
            uzbekTranslation = "menga to'p ber",
            exampleEnglish = "Give me a ball to play.",
            exampleUzbek = "Menga o'ynashga to'p ber."
        ),
        VocabularyEntity(
            id = "v_a0_30_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            englishWord = "show me a ball",
            uzbekTranslation = "menga to'pni ko'rsat",
            exampleEnglish = "Show me a ball in the box.",
            exampleUzbek = "Menga qutidagi to'pni ko'rsat."
        ),
        VocabularyEntity(
            id = "v_a0_31_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            englishWord = "hot",
            uzbekTranslation = "issiq",
            exampleEnglish = "Hot sun.",
            exampleUzbek = "Issiq quyosh."
        ),
        VocabularyEntity(
            id = "v_a0_31_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            englishWord = "cold",
            uzbekTranslation = "sovuq",
            exampleEnglish = "Cold snow.",
            exampleUzbek = "Sovuq qor."
        ),
        VocabularyEntity(
            id = "v_a0_31_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            englishWord = "hot tea",
            uzbekTranslation = "issiq choy",
            exampleEnglish = "Drink hot tea.",
            exampleUzbek = "Issiq choy ich."
        ),
        VocabularyEntity(
            id = "v_a0_31_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            englishWord = "cold water",
            uzbekTranslation = "sovuq suv",
            exampleEnglish = "Drink cold water.",
            exampleUzbek = "Sovuq suv ich."
        ),
        VocabularyEntity(
            id = "v_a0_32_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            englishWord = "happy",
            uzbekTranslation = "baxtli, xursand",
            exampleEnglish = "Happy child.",
            exampleUzbek = "Baxtli bola."
        ),
        VocabularyEntity(
            id = "v_a0_32_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            englishWord = "sad",
            uzbekTranslation = "xafa",
            exampleEnglish = "Don't be sad.",
            exampleUzbek = "Xafa bo'lma."
        ),
        VocabularyEntity(
            id = "v_a0_32_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            englishWord = "i am happy",
            uzbekTranslation = "men baxtliman",
            exampleEnglish = "I am happy today.",
            exampleUzbek = "Men bugun baxtliman."
        ),
        VocabularyEntity(
            id = "v_a0_32_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            englishWord = "i am sad",
            uzbekTranslation = "men xafaman",
            exampleEnglish = "I am sad now.",
            exampleUzbek = "Men hozir xafaman."
        ),
        VocabularyEntity(
            id = "v_a0_33_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            englishWord = "clean",
            uzbekTranslation = "toza",
            exampleEnglish = "Clean room.",
            exampleUzbek = "Toza xona."
        ),
        VocabularyEntity(
            id = "v_a0_33_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            englishWord = "dirty",
            uzbekTranslation = "iflos",
            exampleEnglish = "Dirty shoes.",
            exampleUzbek = "Iflos poyabzal."
        ),
        VocabularyEntity(
            id = "v_a0_33_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            englishWord = "clean hands",
            uzbekTranslation = "toza qo'llar",
            exampleEnglish = "Wash for clean hands.",
            exampleUzbek = "Toza qo'llar uchun yuving."
        ),
        VocabularyEntity(
            id = "v_a0_33_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            englishWord = "dirty hands",
            uzbekTranslation = "iflos qo'llar",
            exampleEnglish = "Wash dirty hands.",
            exampleUzbek = "Iflos qo'llarni yuving."
        ),
        VocabularyEntity(
            id = "v_a0_34_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            englishWord = "fast",
            uzbekTranslation = "tez",
            exampleEnglish = "Fast tiger.",
            exampleUzbek = "Tez yo'lbars."
        ),
        VocabularyEntity(
            id = "v_a0_34_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            englishWord = "slow",
            uzbekTranslation = "sekin",
            exampleEnglish = "Slow walk.",
            exampleUzbek = "Sekin yurish."
        ),
        VocabularyEntity(
            id = "v_a0_34_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            englishWord = "fast car",
            uzbekTranslation = "tez mashina",
            exampleEnglish = "A very fast car.",
            exampleUzbek = "Juda tez mashina."
        ),
        VocabularyEntity(
            id = "v_a0_34_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            englishWord = "slow turtle",
            uzbekTranslation = "sekin toshbaqa",
            exampleEnglish = "A slow turtle walks.",
            exampleUzbek = "Sekin toshbaqa yuradi."
        ),
        VocabularyEntity(
            id = "v_a0_35_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            englishWord = "high",
            uzbekTranslation = "baland",
            exampleEnglish = "High sky.",
            exampleUzbek = "Baland osmon."
        ),
        VocabularyEntity(
            id = "v_a0_35_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            englishWord = "low",
            uzbekTranslation = "past",
            exampleEnglish = "Low table.",
            exampleUzbek = "Past stol."
        ),
        VocabularyEntity(
            id = "v_a0_35_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            englishWord = "high tree",
            uzbekTranslation = "baland daraxt",
            exampleEnglish = "Green high tree.",
            exampleUzbek = "Yashil baland daraxt."
        ),
        VocabularyEntity(
            id = "v_a0_35_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            englishWord = "low chair",
            uzbekTranslation = "past stul",
            exampleEnglish = "Sit on the low chair.",
            exampleUzbek = "Past stulga o'tir."
        ),
        VocabularyEntity(
            id = "v_a0_36_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            englishWord = "hungry",
            uzbekTranslation = "och",
            exampleEnglish = "I feel hungry.",
            exampleUzbek = "Men och qoldim."
        ),
        VocabularyEntity(
            id = "v_a0_36_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            englishWord = "full",
            uzbekTranslation = "to'q",
            exampleEnglish = "I am full now.",
            exampleUzbek = "Men endi to'qman."
        ),
        VocabularyEntity(
            id = "v_a0_36_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            englishWord = "i am hungry",
            uzbekTranslation = "men ochman",
            exampleEnglish = "I am hungry, give me bread.",
            exampleUzbek = "Men ochman, menga non bering."
        ),
        VocabularyEntity(
            id = "v_a0_36_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            englishWord = "i am full",
            uzbekTranslation = "men to'qman",
            exampleEnglish = "I am full, thank you.",
            exampleUzbek = "Men to'qman, rahmat."
        ),
        VocabularyEntity(
            id = "v_a0_37_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            englishWord = "day",
            uzbekTranslation = "kun",
            exampleEnglish = "Bright day.",
            exampleUzbek = "Yorug' kun."
        ),
        VocabularyEntity(
            id = "v_a0_37_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            englishWord = "night",
            uzbekTranslation = "tun",
            exampleEnglish = "Dark night.",
            exampleUzbek = "Qorong'u tun."
        ),
        VocabularyEntity(
            id = "v_a0_37_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            englishWord = "it is day",
            uzbekTranslation = "kun",
            exampleEnglish = "It is day now.",
            exampleUzbek = "Hozir kun."
        ),
        VocabularyEntity(
            id = "v_a0_37_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            englishWord = "it is night",
            uzbekTranslation = "tun",
            exampleEnglish = "It is night now.",
            exampleUzbek = "Hozir tun."
        ),
        VocabularyEntity(
            id = "v_a0_38_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            englishWord = "morning",
            uzbekTranslation = "ertalab, tong",
            exampleEnglish = "Early morning.",
            exampleUzbek = "Erta tong."
        ),
        VocabularyEntity(
            id = "v_a0_38_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            englishWord = "good morning!",
            uzbekTranslation = "xayrli tong!",
            exampleEnglish = "Good morning, mom!",
            exampleUzbek = "Xayrli tong, ona!"
        ),
        VocabularyEntity(
            id = "v_a0_38_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            englishWord = "good night!",
            uzbekTranslation = "xayrli tun!",
            exampleEnglish = "Good night, dad!",
            exampleUzbek = "Xayrli tun, dada!"
        ),
        VocabularyEntity(
            id = "v_a0_39_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            englishWord = "monday",
            uzbekTranslation = "dushanba",
            exampleEnglish = "Monday is first.",
            exampleUzbek = "Dushanba birinchi."
        ),
        VocabularyEntity(
            id = "v_a0_39_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            englishWord = "tuesday",
            uzbekTranslation = "seshanba",
            exampleEnglish = "Tuesday comes next.",
            exampleUzbek = "Keyin seshanba keladi."
        ),
        VocabularyEntity(
            id = "v_a0_39_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            englishWord = "sunday",
            uzbekTranslation = "yakshanba",
            exampleEnglish = "Sunday is fun.",
            exampleUzbek = "Yakshanba qiziqarli."
        ),
        VocabularyEntity(
            id = "v_a0_39_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            englishWord = "today is monday",
            uzbekTranslation = "bugun dushanba",
            exampleEnglish = "Today is Monday, school time.",
            exampleUzbek = "Bugun dushanba, maktab vaqti."
        ),
        VocabularyEntity(
            id = "v_a0_40_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            englishWord = "transport",
            uzbekTranslation = "transport",
            exampleEnglish = "City transport.",
            exampleUzbek = "Shahar transporti."
        ),
        VocabularyEntity(
            id = "v_a0_40_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            englishWord = "bus",
            uzbekTranslation = "avtobus",
            exampleEnglish = "Yellow bus.",
            exampleUzbek = "Sariq avtobus."
        ),
        VocabularyEntity(
            id = "v_a0_40_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            englishWord = "bike",
            uzbekTranslation = "velosiped",
            exampleEnglish = "Ride a bike.",
            exampleUzbek = "Velosiped minish."
        ),
        VocabularyEntity(
            id = "v_a0_40_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            englishWord = "plane",
            uzbekTranslation = "samolyot",
            exampleEnglish = "Fly in a plane.",
            exampleUzbek = "Samolyotda uchish."
        ),
        VocabularyEntity(
            id = "v_a0_41_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            englishWord = "in",
            uzbekTranslation = "ichida",
            exampleEnglish = "In the room.",
            exampleUzbek = "Xona ichida."
        ),
        VocabularyEntity(
            id = "v_a0_41_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            englishWord = "on",
            uzbekTranslation = "ustida",
            exampleEnglish = "On the chair.",
            exampleUzbek = "Stul ustida."
        ),
        VocabularyEntity(
            id = "v_a0_41_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            englishWord = "under",
            uzbekTranslation = "ostida",
            exampleEnglish = "Under the table.",
            exampleUzbek = "Stol ostida."
        ),
        VocabularyEntity(
            id = "v_a0_41_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            englishWord = "in the box",
            uzbekTranslation = "qutida",
            exampleEnglish = "Apple in the box.",
            exampleUzbek = "Qutidagi olma."
        ),
        VocabularyEntity(
            id = "v_a0_41_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            englishWord = "on the table",
            uzbekTranslation = "stolda",
            exampleEnglish = "Book on the table.",
            exampleUzbek = "Stoldagi kitob."
        ),
        VocabularyEntity(
            id = "v_a0_41_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            englishWord = "under the bed",
            uzbekTranslation = "karavot ostida",
            exampleEnglish = "Cat under the bed.",
            exampleUzbek = "Karavot ostidagi mushuk."
        ),
        VocabularyEntity(
            id = "v_a0_42_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            englishWord = "toy box",
            uzbekTranslation = "o'yinchoq qutisi",
            exampleEnglish = "Wooden toy box.",
            exampleUzbek = "Yog'och o'yinchoq qutisi."
        ),
        VocabularyEntity(
            id = "v_a0_42_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            englishWord = "my toy box",
            uzbekTranslation = "mening o'yinchoq qutim",
            exampleEnglish = "Open my toy box.",
            exampleUzbek = "Mening o'yinchoq qutimni och."
        ),
        VocabularyEntity(
            id = "v_a0_42_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            englishWord = "in my toy box",
            uzbekTranslation = "o'yinchoq qutimda",
            exampleEnglish = "Toys in my toy box.",
            exampleUzbek = "O'yinchoq qutimdagi o'yinchoqlar."
        ),
        VocabularyEntity(
            id = "v_a0_43_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            englishWord = "many",
            uzbekTranslation = "ko'p",
            exampleEnglish = "Many stars.",
            exampleUzbek = "Ko'p yulduzlar."
        ),
        VocabularyEntity(
            id = "v_a0_43_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            englishWord = "many balls",
            uzbekTranslation = "ko'p to'plar",
            exampleEnglish = "I have many balls.",
            exampleUzbek = "Menda ko'p to'plar bor."
        ),
        VocabularyEntity(
            id = "v_a0_43_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            englishWord = "many cats",
            uzbekTranslation = "ko'p mushuklar",
            exampleEnglish = "I see many cats.",
            exampleUzbek = "Men ko'p mushuklarni ko'ryapman."
        ),
        VocabularyEntity(
            id = "v_a0_44_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            englishWord = "yes",
            uzbekTranslation = "ha",
            exampleEnglish = "Yes, please.",
            exampleUzbek = "Ha, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_44_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            englishWord = "no",
            uzbekTranslation = "yo'q",
            exampleEnglish = "No, thank you.",
            exampleUzbek = "Yo'q, rahmat."
        ),
        VocabularyEntity(
            id = "v_a0_44_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            englishWord = "yes, i am.",
            uzbekTranslation = "ha, men.",
            exampleEnglish = "Yes, I am happy.",
            exampleUzbek = "Ha, men baxtliman."
        ),
        VocabularyEntity(
            id = "v_a0_44_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            englishWord = "no, i am not.",
            uzbekTranslation = "yo'q, men emas.",
            exampleEnglish = "No, I am not sad.",
            exampleUzbek = "Yo'q, men xafa emasman."
        ),
        VocabularyEntity(
            id = "v_a0_45_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            englishWord = "please",
            uzbekTranslation = "iltimos",
            exampleEnglish = "Come in, please.",
            exampleUzbek = "Kiring, iltimos."
        ),
        VocabularyEntity(
            id = "v_a0_45_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            englishWord = "thank you",
            uzbekTranslation = "rahmat",
            exampleEnglish = "Thank you very much.",
            exampleUzbek = "Katta rahmat."
        ),
        VocabularyEntity(
            id = "v_a0_45_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            englishWord = "please, sit down",
            uzbekTranslation = "iltimos, o'tir",
            exampleEnglish = "Please, sit down here.",
            exampleUzbek = "Iltimos, bu yerga o'tiring."
        ),
        VocabularyEntity(
            id = "v_a0_46_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            englishWord = "i am sorry",
            uzbekTranslation = "kechirasiz",
            exampleEnglish = "I am sorry, my mistake.",
            exampleUzbek = "Kechirasiz, mening xatoim."
        ),
        VocabularyEntity(
            id = "v_a0_46_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            englishWord = "sorry",
            uzbekTranslation = "kechirasiz (qisqa)",
            exampleEnglish = "Sorry, I am late.",
            exampleUzbek = "Kechirasiz, men kechikdim."
        ),
        VocabularyEntity(
            id = "v_a0_46_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            englishWord = "sorry, mom",
            uzbekTranslation = "kechirasiz, ona",
            exampleEnglish = "Sorry, mom, I spilled it.",
            exampleUzbek = "Kechirasiz, ona, to'kib yubordim."
        ),
        VocabularyEntity(
            id = "v_a0_47_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            englishWord = "goodbye",
            uzbekTranslation = "xayr",
            exampleEnglish = "Goodbye, see you!",
            exampleUzbek = "Xayr, ko'rishguncha!"
        ),
        VocabularyEntity(
            id = "v_a0_47_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            englishWord = "goodbye, mom!",
            uzbekTranslation = "xayr, ona!",
            exampleEnglish = "Goodbye, mom, have a nice day!",
            exampleUzbek = "Xayr, ona, yaxshi kun tilayman!"
        ),
        VocabularyEntity(
            id = "v_a0_47_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            englishWord = "goodbye, friend!",
            uzbekTranslation = "xayr, do'st!",
            exampleEnglish = "Goodbye, friend, see you tomorrow!",
            exampleUzbek = "Xayr, do'st, ertagacha!"
        ),
        VocabularyEntity(
            id = "v_a0_48_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            englishWord = "pet",
            uzbekTranslation = "uy hayvoni",
            exampleEnglish = "Cute pet.",
            exampleUzbek = "Yoqimli uy hayvoni."
        ),
        VocabularyEntity(
            id = "v_a0_48_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            englishWord = "my pet",
            uzbekTranslation = "mening uy hayvonim",
            exampleEnglish = "I love my pet.",
            exampleUzbek = "Men uy hayvonimni yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_48_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            englishWord = "my pet is a cat",
            uzbekTranslation = "mening uy hayvonim mushuk",
            exampleEnglish = "My pet is a cat named Tom.",
            exampleUzbek = "Mening uy hayvonim Tom ismli mushuk."
        ),
        VocabularyEntity(
            id = "v_a0_49_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            englishWord = "love",
            uzbekTranslation = "sevmoq, juda yaxshi ko'rmoq",
            exampleEnglish = "Love is strong.",
            exampleUzbek = "Sevgi kuchli."
        ),
        VocabularyEntity(
            id = "v_a0_49_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            englishWord = "i love mom",
            uzbekTranslation = "men onamni yaxshi ko'raman",
            exampleEnglish = "I love mom with all my heart.",
            exampleUzbek = "Men onamni chin dildan yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_49_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            englishWord = "i love dad",
            uzbekTranslation = "men dadamni yaxshi ko'raman",
            exampleEnglish = "I love dad very much.",
            exampleUzbek = "Men dadamni juda yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_49_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            englishWord = "i love my family",
            uzbekTranslation = "men oilamni yaxshi ko'raman",
            exampleEnglish = "I love my family happily.",
            exampleUzbek = "Men oilamni baxtli yaxshi ko'raman."
        ),
        VocabularyEntity(
            id = "v_a0_50_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            englishWord = "boy",
            uzbekTranslation = "o'g'il bola",
            exampleEnglish = "Good boy.",
            exampleUzbek = "Yaxshi bola."
        ),
        VocabularyEntity(
            id = "v_a0_50_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            englishWord = "girl",
            uzbekTranslation = "qiz bola",
            exampleEnglish = "Pretty girl.",
            exampleUzbek = "Chiroyli qiz."
        ),
        VocabularyEntity(
            id = "v_a0_50_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            englishWord = "i am a boy",
            uzbekTranslation = "men bolaman",
            exampleEnglish = "I am a boy, my name is Aziz.",
            exampleUzbek = "Men bolaman, ismim Aziz."
        ),
        VocabularyEntity(
            id = "v_a0_50_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            englishWord = "i am a girl",
            uzbekTranslation = "men qizman",
            exampleEnglish = "I am a girl, my name is Laylo.",
            exampleUzbek = "Men qizman, ismim Laylo."
        ),
        VocabularyEntity(
            id = "v_a0_51_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            englishWord = "tired",
            uzbekTranslation = "charchagan",
            exampleEnglish = "I am very tired.",
            exampleUzbek = "Men juda charchadim."
        ),
        VocabularyEntity(
            id = "v_a0_51_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            englishWord = "i am tired",
            uzbekTranslation = "men charchadim",
            exampleEnglish = "I am tired, time to sleep.",
            exampleUzbek = "Men charchadim, uxlash vaqti."
        ),
        VocabularyEntity(
            id = "v_a0_51_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            englishWord = "he is happy",
            uzbekTranslation = "u baxtli",
            exampleEnglish = "He is happy with his toy.",
            exampleUzbek = "U o'yinchog'idan baxtli."
        ),
        VocabularyEntity(
            id = "v_a0_52_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            englishWord = "go",
            uzbekTranslation = "bormoq",
            exampleEnglish = "Go now.",
            exampleUzbek = "Hozir bor."
        ),
        VocabularyEntity(
            id = "v_a0_52_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            englishWord = "i go",
            uzbekTranslation = "men boraman",
            exampleEnglish = "I go every morning.",
            exampleUzbek = "Men har tong boraman."
        ),
        VocabularyEntity(
            id = "v_a0_52_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            englishWord = "i go to school",
            uzbekTranslation = "men maktabga boraman",
            exampleEnglish = "I go to school with friends.",
            exampleUzbek = "Men do'stlarim bilan maktabga boraman."
        ),
        VocabularyEntity(
            id = "v_a0_52_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            englishWord = "i go to the park",
            uzbekTranslation = "men parkga boraman",
            exampleEnglish = "I go to the park to play.",
            exampleUzbek = "Men o'ynashga parkga boraman."
        ),
        VocabularyEntity(
            id = "v_a0_52_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            englishWord = "i go home",
            uzbekTranslation = "men uyga boraman",
            exampleEnglish = "I go home after school.",
            exampleUzbek = "Men maktabdan keyin uyga boraman."
        ),
        VocabularyEntity(
            id = "v_a0_53_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            englishWord = "review",
            uzbekTranslation = "takrorlash",
            exampleEnglish = "Big review lesson.",
            exampleUzbek = "Katta takrorlash darsi."
        ),
        VocabularyEntity(
            id = "v_a0_53_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            englishWord = "congratulations",
            uzbekTranslation = "tabriklaymiz",
            exampleEnglish = "Congratulations, you did it!",
            exampleUzbek = "Tabriklaymiz, siz bajardingiz!"
        ),
        VocabularyEntity(
            id = "v_a0_53_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            englishWord = "good job",
            uzbekTranslation = "barakalla",
            exampleEnglish = "Good job on completing A0!",
            exampleUzbek = "A0 ni tugatganingiz uchun barakalla!"
        )
    )

    fun buildGrammarRules(): List<GrammarRuleEntity> = listOf(
        GrammarRuleEntity(
            id = "g_a0_01_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            titleUzbek = "Hello salomlashish so'zi",
            explanationUzbek = """Hello — uchrashganda salom berish uchun ishlatiladi.""",
            exampleEnglish = "Hello!",
            exampleUzbek = "Salom!",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_02_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            titleUzbek = "My name is iborasi",
            explanationUzbek = """My name is + ism o'z ismini tanishtirish uchun ishlatiladi.""",
            exampleEnglish = "My name is Laylo.",
            exampleUzbek = "Mening ismim Laylo.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_03_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            titleUzbek = "Ranglar sifat sifatida",
            explanationUzbek = """Ranglar otning oldida keladi: red apple, blue sky.""",
            exampleEnglish = "Red apple.",
            exampleUzbek = "Qizil olma.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_04_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            titleUzbek = "1 dan 5 gacha sanash",
            explanationUzbek = """Sonlar otning oldida keladi: two cats, three apples.""",
            exampleEnglish = "Two cats.",
            exampleUzbek = "Ikkita mushuk.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_05_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            titleUzbek = "6 dan 10 gacha sanash",
            explanationUzbek = """Ko'p narsalar oxiriga -s qo'shiladi: seven dogs, ten fingers.""",
            exampleEnglish = "Seven dogs.",
            exampleUzbek = "Yettita it.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_06_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            titleUzbek = "Hayvonlar nomlari",
            explanationUzbek = """Hayvonlarni aytishda to'g'ridan-to'g'ri nomini aytamiz: cat, dog, elephant.""",
            exampleEnglish = "Hello, cat!",
            exampleUzbek = "Salom, mushuk!",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_07_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            titleUzbek = "Oila a'zolari",
            explanationUzbek = """Oila a'zolarini aytganda: mom, dad, brother, sister, baby.""",
            exampleEnglish = "My mom.",
            exampleUzbek = "Mening onam.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_08_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            titleUzbek = "Tana a'zolari",
            explanationUzbek = """My + tana a'zosi: my eyes, my nose, my hands.""",
            exampleEnglish = "My eyes.",
            exampleUzbek = "Mening ko'zlarim.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_09_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            titleUzbek = "O'yinchoqlar",
            explanationUzbek = """O'yinchoqlar oldiga My qo'shib aytish: My ball, my teddy.""",
            exampleEnglish = "My ball.",
            exampleUzbek = "Mening to'pim.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_10_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            titleUzbek = "Ovqat nomlari",
            explanationUzbek = """Kundalik taomlar: bread, rice, egg, cheese, meat.""",
            exampleEnglish = "Bread.",
            exampleUzbek = "Non.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_11_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            titleUzbek = "Ichimliklar nomlari",
            explanationUzbek = """Ichimliklar: water, milk, juice, tea.""",
            exampleEnglish = "Water.",
            exampleUzbek = "Suv.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_12_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            titleUzbek = "Mevalar nomlari",
            explanationUzbek = """Mevalarni aytganda: apple, banana, grapes, orange, strawberry.""",
            exampleEnglish = "Apple.",
            exampleUzbek = "Olma.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_13_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            titleUzbek = "Sabzavotlar",
            explanationUzbek = """Sabzavot nomlari: carrot, tomato, cucumber, onion.""",
            exampleEnglish = "Carrot.",
            exampleUzbek = "Sabzi.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_14_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            titleUzbek = "Kiyimlar",
            explanationUzbek = """Kiyimlar nomlari: T-shirt, pants, dress, socks, shoes.""",
            exampleEnglish = "My T-shirt.",
            exampleUzbek = "Mening futbolkam.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_15_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            titleUzbek = "Uydagi buyumlar",
            explanationUzbek = """Uydagi narsalar: door, window, bed, chair, table.""",
            exampleEnglish = "My house.",
            exampleUzbek = "Mening uyim.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_16_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            titleUzbek = "Xona tasviri",
            explanationUzbek = """Xonadagi buyumlar: bed, teddy, books, ball, picture.""",
            exampleEnglish = "My room.",
            exampleUzbek = "Mening xonam.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_17_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            titleUzbek = "Maktab jihozlari",
            explanationUzbek = """Maktabdagi narsalar: book, pen, pencil, bag, teacher.""",
            exampleEnglish = "My school.",
            exampleUzbek = "Mening maktabim.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_18_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            titleUzbek = "Ob-havoni ifodalash",
            explanationUzbek = """It is + ob-havo sifati: It is sunny, It is rainy.""",
            exampleEnglish = "It is sunny.",
            exampleUzbek = "Havo quyoshli.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_19_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            titleUzbek = "Hajm sifatlari",
            explanationUzbek = """Big (katta) va Small (kichik) otning oldida keladi: big ball, small cat.""",
            exampleEnglish = "Big ball.",
            exampleUzbek = "Katta to'p.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_20_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            titleUzbek = "Shakllar",
            explanationUzbek = """Shakllarni aytganda: circle, square, triangle, star.""",
            exampleEnglish = "A star.",
            exampleUzbek = "Bitta yulduz.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_21_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            titleUzbek = "I like ifodasi",
            explanationUzbek = """I like + ot: Men biror narsani yaxshi ko'rishimni bildiradi.""",
            exampleEnglish = "I like cats.",
            exampleUzbek = "Men mushuklarni yaxshi ko'raman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_22_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            titleUzbek = "I can fe'li",
            explanationUzbek = """I can + harakat fe'li imkoniyatni bildiradi: I can run, I can jump.""",
            exampleEnglish = "I can run.",
            exampleUzbek = "Men yugura olaman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_23_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            titleUzbek = "I have ifodasi",
            explanationUzbek = """I have + narsa: menda biror narsa borligini aytadi: I have a cat.""",
            exampleEnglish = "I have a cat.",
            exampleUzbek = "Menda mushuk bor.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_24_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            titleUzbek = "I see fe'li",
            explanationUzbek = """I see + buyum/hayvon: ko'rib turgan narsani aytish uchun: I see a bird.""",
            exampleEnglish = "I see a bird.",
            exampleUzbek = "Men qushni ko'ryapman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_25_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            titleUzbek = "I want fe'li",
            explanationUzbek = """I want + narsa istakni bildiradi: I want water, I want a toy.""",
            exampleEnglish = "I want water.",
            exampleUzbek = "Men suv xohlayman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_26_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            titleUzbek = "Buyruq va iltimos",
            explanationUzbek = """Buyruq berishda: Sit down, Stand up. Muloyimlik uchun please qo'shiladi.""",
            exampleEnglish = "Sit down, please.",
            exampleUzbek = "O'tiring, iltimos.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_27_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            titleUzbek = "Harakat fe'llari",
            explanationUzbek = """I + fe'l: I run (men yuguraman), I jump (men sakrayman), I walk (men yuraman).""",
            exampleEnglish = "I run.",
            exampleUzbek = "Men yuguraman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_28_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            titleUzbek = "Kundalik fe'llar",
            explanationUzbek = """I eat (men yeyman), I drink (men ichaman), I sleep (men uxlayman).""",
            exampleEnglish = "I eat bread.",
            exampleUzbek = "Men non yeyman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_29_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            titleUzbek = "Open va Close",
            explanationUzbek = """Open + narsa (och), Close + narsa (yop): Open the door, Close the book.""",
            exampleEnglish = "Open the door.",
            exampleUzbek = "Eshikni och.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_30_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            titleUzbek = "Give me va Show me",
            explanationUzbek = """Give me + narsa (menga ber), Show me + narsa (menga ko'rsat).""",
            exampleEnglish = "Give me a ball.",
            exampleUzbek = "Menga to'p ber.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_31_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            titleUzbek = "Harorat sifatlari",
            explanationUzbek = """Hot (issiq) va Cold (sovuq): Hot tea, Cold water.""",
            exampleEnglish = "Hot tea.",
            exampleUzbek = "Issiq choy.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_32_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            titleUzbek = "His-tuyg'ular",
            explanationUzbek = """I am happy (men baxtliman), I am sad (men xafaman).""",
            exampleEnglish = "I am happy.",
            exampleUzbek = "Men baxtliman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_33_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            titleUzbek = "Tozalik sifatlari",
            explanationUzbek = """Clean (toza) va Dirty (iflos): Clean hands, Dirty hands.""",
            exampleEnglish = "Clean hands.",
            exampleUzbek = "Toza qo'llar.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_34_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            titleUzbek = "Tezlik sifatlari",
            explanationUzbek = """Fast (tez) va Slow (sekin): Fast car, Slow turtle.""",
            exampleEnglish = "Fast car.",
            exampleUzbek = "Tez mashina.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_35_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            titleUzbek = "Balandlik sifatlari",
            explanationUzbek = """High (baland) va Low (past): High tree, Low chair.""",
            exampleEnglish = "High tree.",
            exampleUzbek = "Baland daraxt.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_36_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            titleUzbek = "Ochlik va to'qlik",
            explanationUzbek = """I am hungry (men ochman), I am full (men to'qman).""",
            exampleEnglish = "I am hungry.",
            exampleUzbek = "Men ochman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_37_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            titleUzbek = "Day va Night",
            explanationUzbek = """It is day (kunduz kuni), It is night (kechasi, tun).""",
            exampleEnglish = "It is day.",
            exampleUzbek = "Kun.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_38_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            titleUzbek = "Salomlashish va xayrlashish vaqtlari",
            explanationUzbek = """Good morning (xayrli tong), Good night (xayrli tun).""",
            exampleEnglish = "Good morning!",
            exampleUzbek = "Xayrli tong!",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_39_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            titleUzbek = "Hafta kunlari",
            explanationUzbek = """Today is + hafta kuni: Today is Monday, Today is Sunday.""",
            exampleEnglish = "Today is Monday.",
            exampleUzbek = "Bugun dushanba.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_40_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            titleUzbek = "Transport turlari",
            explanationUzbek = """Transport vositalari: car, bus, bike, plane.""",
            exampleEnglish = "Big bus.",
            exampleUzbek = "Katta avtobus.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_41_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            titleUzbek = "Predloglar: In, On, Under",
            explanationUzbek = """In (ichida), On (ustida), Under (ostida): in the box, on the table, under the bed.""",
            exampleEnglish = "In the box.",
            exampleUzbek = "Qutida.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_42_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            titleUzbek = "In my toy box ifodasi",
            explanationUzbek = """In my toy box: o'yinchoq qutisi ichida nimalar borligini bildiradi.""",
            exampleEnglish = "A ball in my toy box.",
            exampleUzbek = "O'yinchoq qutimda to'p.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_43_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            titleUzbek = "One va Many",
            explanationUzbek = """One + birlik ot: One ball. Many + ko'plik ot(-s): Many balls.""",
            exampleEnglish = "Many balls.",
            exampleUzbek = "Ko'p to'plar.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_44_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            titleUzbek = "Tasdiq va inkor javob",
            explanationUzbek = """Yes (ha) va No (yo'q): Yes, I am / No, I am not.""",
            exampleEnglish = "Yes, I am.",
            exampleUzbek = "Ha, men.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_45_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            titleUzbek = "Odob so'zlari",
            explanationUzbek = """Please (iltimos) so'rashda, Thank you (rahmat) minnatdorchilikda ishlatiladi.""",
            exampleEnglish = "Thank you!",
            exampleUzbek = "Rahmat!",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_46_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            titleUzbek = "Uzr so'rash",
            explanationUzbek = """I am sorry yoki qisqa Sorry: xato qilganda uzr so'rash uchun ishlatiladi.""",
            exampleEnglish = "I am sorry.",
            exampleUzbek = "Kechirasiz.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_47_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            titleUzbek = "Xayrlashuv so'zi",
            explanationUzbek = """Goodbye — xayrlashayotganda aytiladi: Goodbye, mom!""",
            exampleEnglish = "Goodbye!",
            exampleUzbek = "Xayr!",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_48_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            titleUzbek = "My pet is iborasi",
            explanationUzbek = """My pet is a + hayvon: uy hayvonini aytish uchun: My pet is a cat.""",
            exampleEnglish = "My pet is a cat.",
            exampleUzbek = "Mening uy hayvonim mushuk.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_49_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            titleUzbek = "I love ifodasi",
            explanationUzbek = """I love + shaxs: I love mom, I love dad, I love my family.""",
            exampleEnglish = "I love mom.",
            exampleUzbek = "Men onamni yaxshi ko'raman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_50_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            titleUzbek = "I am a boy / girl",
            explanationUzbek = """O'z jinsini aytishda: I am a boy (o'g'il bola) yoki I am a girl (qiz bola).""",
            exampleEnglish = "I am a boy.",
            exampleUzbek = "Men bolaman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_51_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            titleUzbek = "Holatni aytish",
            explanationUzbek = """I am + holat sifati: I am happy, I am sad, I am tired.""",
            exampleEnglish = "I am happy.",
            exampleUzbek = "Men baxtliman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_52_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            titleUzbek = "Go fe'li",
            explanationUzbek = """I go to + joy: biror manzilga borishni bildiradi: I go to school.""",
            exampleEnglish = "I go to school.",
            exampleUzbek = "Men maktabga boraman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        ),
        GrammarRuleEntity(
            id = "g_a0_53_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            titleUzbek = "A0 barcha qoidalar xulosasi",
            explanationUzbek = """Salomlashish, ism aytish, ranglar, sonlar, oila, tana, o'yinchoqlar, ovqat va fe'llar xulosasi.""",
            exampleEnglish = "Hello! I am happy.",
            exampleUzbek = "Salom! Men baxtliman.",
            associatedVocabularyIdsCsv = "",
            isStudied = false,
            isMastered = false
        )
    )

    fun buildExercises(): List<ExerciseEntity> = listOf(
        ExerciseEntity(
            id = "ex_a0_01_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Hello” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hello!",
            optionsPipeSeparated = "xayr|salom|rahmat",
            correctAnswer = "salom",
            explanationUzbek = """Hello — salom degani.""",
            vocabularyIdsCsv = "v_a0_01_01",
            grammarRuleIdsCsv = "g_a0_01_01"
        ),
        ExerciseEntity(
            id = "ex_a0_01_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Dadamga salom berdim. Qaysi gapni aytdim?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "Hello, Dad!|Hello, Mom!|Goodbye, Dad!",
            correctAnswer = "Hello, Dad!",
            explanationUzbek = "Dad — dada. Hello bilan salom beramiz.",
            vocabularyIdsCsv = "v_a0_01_01",
            grammarRuleIdsCsv = "g_a0_01_01"
        ),
        ExerciseEntity(
            id = "ex_a0_01_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_1_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Sinfga kirib, do‘stingizga duch keldingiz. Suhbatni qaysi so‘z bilan boshlaysiz?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = null,
            optionsPipeSeparated = "Hello!|Goodbye!|Thanks!",
            correctAnswer = "Hello!",
            explanationUzbek = "Hello! — uchrashganda aytiladigan salom.",
            vocabularyIdsCsv = "v_a0_01_01",
            grammarRuleIdsCsv = "g_a0_01_01"
        ),
        ExerciseEntity(
            id = "ex_a0_02_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“My name is” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My name is",
            optionsPipeSeparated = "mening ismim|sen kimsan|salom",
            correctAnswer = "mening ismim",
            explanationUzbek = """My name is — mening ismim degani.""",
            vocabularyIdsCsv = "v_a0_02_01",
            grammarRuleIdsCsv = "g_a0_02_01"
        ),
        ExerciseEntity(
            id = "ex_a0_02_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: My ______ is Aziz.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My name is Aziz.",
            optionsPipeSeparated = "name|cat|ball",
            correctAnswer = "name",
            explanationUzbek = """My name is Aziz — mening ismim Aziz.""",
            vocabularyIdsCsv = "v_a0_02_01",
            grammarRuleIdsCsv = "g_a0_02_01"
        ),
        ExerciseEntity(
            id = "ex_a0_02_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_2_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Laylo ismini ayting:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My name is Laylo.",
            optionsPipeSeparated = "My name is Laylo.|Hello Laylo.|Laylo cat.",
            correctAnswer = "My name is Laylo.",
            explanationUzbek = """Ism aytishda: My name is Laylo.""",
            vocabularyIdsCsv = "v_a0_02_01",
            grammarRuleIdsCsv = "g_a0_02_01"
        ),
        ExerciseEntity(
            id = "ex_a0_03_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍎 qanday rang?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Red apple.",
            optionsPipeSeparated = "red|blue|green",
            correctAnswer = "red",
            explanationUzbek = """Olma qizil — red.""",
            vocabularyIdsCsv = "v_a0_03_01",
            grammarRuleIdsCsv = "g_a0_03_01"
        ),
        ExerciseEntity(
            id = "ex_a0_03_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Osmon qanday rang?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Blue sky.",
            optionsPipeSeparated = "red|blue|black",
            correctAnswer = "blue",
            explanationUzbek = """Osmon ko'k — blue.""",
            vocabularyIdsCsv = "v_a0_03_01",
            grammarRuleIdsCsv = "g_a0_03_01"
        ),
        ExerciseEntity(
            id = "ex_a0_03_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_3_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Green” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Green",
            optionsPipeSeparated = "qizil|yashil|sariq",
            correctAnswer = "yashil",
            explanationUzbek = """Green — yashil degani.""",
            vocabularyIdsCsv = "v_a0_03_01",
            grammarRuleIdsCsv = "g_a0_03_01"
        ),
        ExerciseEntity(
            id = "ex_a0_04_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Three” nechchi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Three",
            optionsPipeSeparated = "2|3|5",
            correctAnswer = "3",
            explanationUzbek = """Three — 3 raqami.""",
            vocabularyIdsCsv = "v_a0_04_01",
            grammarRuleIdsCsv = "g_a0_04_01"
        ),
        ExerciseEntity(
            id = "ex_a0_04_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "5 qanday yoziladi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Five",
            optionsPipeSeparated = "five|four|three",
            correctAnswer = "five",
            explanationUzbek = """5 — five deb yoziladi.""",
            vocabularyIdsCsv = "v_a0_04_01",
            grammarRuleIdsCsv = "g_a0_04_01"
        ),
        ExerciseEntity(
            id = "ex_a0_04_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_4_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: ______ apples (3)",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Three apples.",
            optionsPipeSeparated = "Three|One|Two",
            correctAnswer = "Three",
            explanationUzbek = """3 ta olma — Three apples.""",
            vocabularyIdsCsv = "v_a0_04_01",
            grammarRuleIdsCsv = "g_a0_04_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Eight” nechchi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Eight",
            optionsPipeSeparated = "6|8|10",
            correctAnswer = "8",
            explanationUzbek = """Eight — 8 soni.""",
            vocabularyIdsCsv = "v_a0_05_01",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "10 qanday yoziladi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Ten",
            optionsPipeSeparated = "ten|nine|six",
            correctAnswer = "ten",
            explanationUzbek = """10 — ten deb yoziladi.""",
            vocabularyIdsCsv = "v_a0_05_01",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_05_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_5_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: ______ fingers (10)",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Ten fingers.",
            optionsPipeSeparated = "Ten|Six|Eight",
            correctAnswer = "Ten",
            explanationUzbek = """O'nta barmoq — Ten fingers.""",
            vocabularyIdsCsv = "v_a0_05_01",
            grammarRuleIdsCsv = "g_a0_05_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐱 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Cat",
            optionsPipeSeparated = "cat|dog|fish",
            correctAnswer = "cat",
            explanationUzbek = """Mushuk — cat deyiladi.""",
            vocabularyIdsCsv = "v_a0_06_01",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐘 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Elephant",
            optionsPipeSeparated = "bird|elephant|cat",
            correctAnswer = "elephant",
            explanationUzbek = """Fil — elephant deyiladi.""",
            vocabularyIdsCsv = "v_a0_06_01",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_06_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_6_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Fish” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Fish",
            optionsPipeSeparated = "it|qush|baliq",
            correctAnswer = "baliq",
            explanationUzbek = """Fish — baliq degani.""",
            vocabularyIdsCsv = "v_a0_06_01",
            grammarRuleIdsCsv = "g_a0_06_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👩 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Mom",
            optionsPipeSeparated = "mom|dad|sister",
            correctAnswer = "mom",
            explanationUzbek = """Ona — mom.""",
            vocabularyIdsCsv = "v_a0_07_01",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👨 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Dad",
            optionsPipeSeparated = "mom|dad|brother",
            correctAnswer = "dad",
            explanationUzbek = """Dada — dad.""",
            vocabularyIdsCsv = "v_a0_07_01",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_07_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_7_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Sister” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sister",
            optionsPipeSeparated = "aka|opa/singil|chaqaloq",
            correctAnswer = "opa/singil",
            explanationUzbek = """Sister — opa yoki singil.""",
            vocabularyIdsCsv = "v_a0_07_01",
            grammarRuleIdsCsv = "g_a0_07_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👃 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Nose",
            optionsPipeSeparated = "nose|eyes|mouth",
            correctAnswer = "nose",
            explanationUzbek = """Burun — nose.""",
            vocabularyIdsCsv = "v_a0_08_01",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👂 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Ears",
            optionsPipeSeparated = "eyes|ears|hands",
            correctAnswer = "ears",
            explanationUzbek = """Quloqlar — ears.""",
            vocabularyIdsCsv = "v_a0_08_01",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_08_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_8_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Mouth” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Mouth",
            optionsPipeSeparated = "ko'z|burun|og'iz",
            correctAnswer = "og'iz",
            explanationUzbek = """Mouth — og'iz degani.""",
            vocabularyIdsCsv = "v_a0_08_01",
            grammarRuleIdsCsv = "g_a0_08_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "⚽ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Ball",
            optionsPipeSeparated = "ball|car|teddy",
            correctAnswer = "ball",
            explanationUzbek = """To'p — ball.""",
            vocabularyIdsCsv = "v_a0_09_01",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🚗 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Car",
            optionsPipeSeparated = "ball|car|blocks",
            correctAnswer = "car",
            explanationUzbek = """Mashina — car.""",
            vocabularyIdsCsv = "v_a0_09_01",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_09_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_9_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Teddy” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Teddy",
            optionsPipeSeparated = "to'p|mashina|ayiqcha",
            correctAnswer = "ayiqcha",
            explanationUzbek = """Teddy — ayiqcha o'yinchoq.""",
            vocabularyIdsCsv = "v_a0_09_01",
            grammarRuleIdsCsv = "g_a0_09_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍞 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Bread",
            optionsPipeSeparated = "bread|rice|egg",
            correctAnswer = "bread",
            explanationUzbek = """Non — bread.""",
            vocabularyIdsCsv = "v_a0_10_01",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🥚 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Egg",
            optionsPipeSeparated = "bread|egg|meat",
            correctAnswer = "egg",
            explanationUzbek = """Tuxum — egg.""",
            vocabularyIdsCsv = "v_a0_10_01",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_10_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_10_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Cheese” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Cheese",
            optionsPipeSeparated = "non|go'sht|pishloq",
            correctAnswer = "pishloq",
            explanationUzbek = """Cheese — pishloq.""",
            vocabularyIdsCsv = "v_a0_10_01",
            grammarRuleIdsCsv = "g_a0_10_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "💧 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Water",
            optionsPipeSeparated = "water|milk|tea",
            correctAnswer = "water",
            explanationUzbek = """Suv — water.""",
            vocabularyIdsCsv = "v_a0_11_01",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🥛 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Milk",
            optionsPipeSeparated = "water|milk|juice",
            correctAnswer = "milk",
            explanationUzbek = """Sut — milk.""",
            vocabularyIdsCsv = "v_a0_11_01",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_11_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_11_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Juice” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Juice",
            optionsPipeSeparated = "suv|sut|sharbat",
            correctAnswer = "sharbat",
            explanationUzbek = """Juice — sharbat.""",
            vocabularyIdsCsv = "v_a0_11_01",
            grammarRuleIdsCsv = "g_a0_11_01"
        ),
        ExerciseEntity(
            id = "ex_a0_12_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍎 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Apple",
            optionsPipeSeparated = "apple|banana|orange",
            correctAnswer = "apple",
            explanationUzbek = """Olma — apple.""",
            vocabularyIdsCsv = "v_a0_12_01",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        ExerciseEntity(
            id = "ex_a0_12_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍌 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Banana",
            optionsPipeSeparated = "apple|banana|grapes",
            correctAnswer = "banana",
            explanationUzbek = """Banan — banana.""",
            vocabularyIdsCsv = "v_a0_12_01",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        ExerciseEntity(
            id = "ex_a0_12_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_12_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Strawberry” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Strawberry",
            optionsPipeSeparated = "uzum|apelsin|qulupnay",
            correctAnswer = "qulupnay",
            explanationUzbek = """Strawberry — qulupnay.""",
            vocabularyIdsCsv = "v_a0_12_01",
            grammarRuleIdsCsv = "g_a0_12_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🥕 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Carrot",
            optionsPipeSeparated = "carrot|tomato|onion",
            correctAnswer = "carrot",
            explanationUzbek = """Sabzi — carrot.""",
            vocabularyIdsCsv = "v_a0_13_01",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍅 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Tomato",
            optionsPipeSeparated = "carrot|tomato|cucumber",
            correctAnswer = "tomato",
            explanationUzbek = """Pomidor — tomato.""",
            vocabularyIdsCsv = "v_a0_13_01",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_13_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_13_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Onion” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Onion",
            optionsPipeSeparated = "bodring|sabzi|piyoz",
            correctAnswer = "piyoz",
            explanationUzbek = """Onion — piyoz.""",
            vocabularyIdsCsv = "v_a0_13_01",
            grammarRuleIdsCsv = "g_a0_13_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👕 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "T-shirt",
            optionsPipeSeparated = "T-shirt|pants|shoes",
            correctAnswer = "T-shirt",
            explanationUzbek = """Futbolka — T-shirt.""",
            vocabularyIdsCsv = "v_a0_14_01",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👟 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Shoes",
            optionsPipeSeparated = "socks|shoes|dress",
            correctAnswer = "shoes",
            explanationUzbek = """Poyabzal — shoes.""",
            vocabularyIdsCsv = "v_a0_14_01",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_14_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_14_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Dress” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Dress",
            optionsPipeSeparated = "shim|paypoq|ko'ylak",
            correctAnswer = "ko'ylak",
            explanationUzbek = """Dress — ko'ylak.""",
            vocabularyIdsCsv = "v_a0_14_01",
            grammarRuleIdsCsv = "g_a0_14_01"
        ),
        ExerciseEntity(
            id = "ex_a0_15_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🚪 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Door",
            optionsPipeSeparated = "door|window|bed",
            correctAnswer = "door",
            explanationUzbek = """Eshik — door.""",
            vocabularyIdsCsv = "v_a0_15_01",
            grammarRuleIdsCsv = "g_a0_15_01"
        ),
        ExerciseEntity(
            id = "ex_a0_15_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🛏️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Bed",
            optionsPipeSeparated = "chair|bed|table",
            correctAnswer = "bed",
            explanationUzbek = """Karavot — bed.""",
            vocabularyIdsCsv = "v_a0_15_01",
            grammarRuleIdsCsv = "g_a0_15_01"
        ),
        ExerciseEntity(
            id = "ex_a0_15_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_15_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Window” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Window",
            optionsPipeSeparated = "eshik|karavot|deraza",
            correctAnswer = "deraza",
            explanationUzbek = """Window — deraza.""",
            vocabularyIdsCsv = "v_a0_15_01",
            grammarRuleIdsCsv = "g_a0_15_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🎨 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Picture",
            optionsPipeSeparated = "picture|ball|books",
            correctAnswer = "picture",
            explanationUzbek = """Rasm — picture.""",
            vocabularyIdsCsv = "v_a0_16_01",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "📚 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Books",
            optionsPipeSeparated = "bed|books|picture",
            correctAnswer = "books",
            explanationUzbek = """Kitoblar — books.""",
            vocabularyIdsCsv = "v_a0_16_01",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_16_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_16_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Room” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Room",
            optionsPipeSeparated = "xona|eshik|karavot",
            correctAnswer = "xona",
            explanationUzbek = """Room — xona.""",
            vocabularyIdsCsv = "v_a0_16_01",
            grammarRuleIdsCsv = "g_a0_16_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "📖 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Book",
            optionsPipeSeparated = "book|pen|bag",
            correctAnswer = "book",
            explanationUzbek = """Kitob — book.""",
            vocabularyIdsCsv = "v_a0_17_01",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🎒 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Bag",
            optionsPipeSeparated = "book|bag|pencil",
            correctAnswer = "bag",
            explanationUzbek = """Sumka — bag.""",
            vocabularyIdsCsv = "v_a0_17_01",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_17_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_17_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Teacher” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Teacher",
            optionsPipeSeparated = "kitob|sumka|o'qituvchi",
            correctAnswer = "o'qituvchi",
            explanationUzbek = """Teacher — o'qituvchi.""",
            vocabularyIdsCsv = "v_a0_17_01",
            grammarRuleIdsCsv = "g_a0_17_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "☀️ qanday ob-havo?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sunny",
            optionsPipeSeparated = "sunny|rainy|snowy",
            correctAnswer = "sunny",
            explanationUzbek = """Quyoshli — sunny.""",
            vocabularyIdsCsv = "v_a0_18_01",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🌧️ qanday ob-havo?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Rainy",
            optionsPipeSeparated = "sunny|rainy|windy",
            correctAnswer = "rainy",
            explanationUzbek = """Yomg'irli — rainy.""",
            vocabularyIdsCsv = "v_a0_18_01",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_18_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_18_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Snowy” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Snowy",
            optionsPipeSeparated = "quyoshli|yomg'irli|qorli",
            correctAnswer = "qorli",
            explanationUzbek = """Snowy — qorli.""",
            vocabularyIdsCsv = "v_a0_18_01",
            grammarRuleIdsCsv = "g_a0_18_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐘 qanday?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Big",
            optionsPipeSeparated = "big|small",
            correctAnswer = "big",
            explanationUzbek = """Fil katta — big.""",
            vocabularyIdsCsv = "v_a0_19_01",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐭 qanday?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Small",
            optionsPipeSeparated = "big|small",
            correctAnswer = "small",
            explanationUzbek = """Sichqon kichik — small.""",
            vocabularyIdsCsv = "v_a0_19_01",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_19_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_19_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Big” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Big",
            optionsPipeSeparated = "kichik|katta",
            correctAnswer = "katta",
            explanationUzbek = """Big — katta.""",
            vocabularyIdsCsv = "v_a0_19_01",
            grammarRuleIdsCsv = "g_a0_19_01"
        ),
        ExerciseEntity(
            id = "ex_a0_20_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "⭕ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Circle",
            optionsPipeSeparated = "circle|square|star",
            correctAnswer = "circle",
            explanationUzbek = """Doira — circle.""",
            vocabularyIdsCsv = "v_a0_20_01",
            grammarRuleIdsCsv = "g_a0_20_01"
        ),
        ExerciseEntity(
            id = "ex_a0_20_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "⭐ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Star",
            optionsPipeSeparated = "circle|star|triangle",
            correctAnswer = "star",
            explanationUzbek = """Yulduz — star.""",
            vocabularyIdsCsv = "v_a0_20_01",
            grammarRuleIdsCsv = "g_a0_20_01"
        ),
        ExerciseEntity(
            id = "ex_a0_20_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_20_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Triangle” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Triangle",
            optionsPipeSeparated = "doira|kvadrat|uchburchak",
            correctAnswer = "uchburchak",
            explanationUzbek = """Triangle — uchburchak.""",
            vocabularyIdsCsv = "v_a0_20_01",
            grammarRuleIdsCsv = "g_a0_20_01"
        ),
        ExerciseEntity(
            id = "ex_a0_21_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I like” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I like",
            optionsPipeSeparated = "men yaxshi ko'raman|men boraman|men ko'raman",
            correctAnswer = "men yaxshi ko'raman",
            explanationUzbek = """I like — men yaxshi ko'raman.""",
            vocabularyIdsCsv = "v_a0_21_01",
            grammarRuleIdsCsv = "g_a0_21_01"
        ),
        ExerciseEntity(
            id = "ex_a0_21_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: I ______ apples.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I like apples.",
            optionsPipeSeparated = "like|go|see",
            correctAnswer = "like",
            explanationUzbek = """I like apples — men olmalarni yaxshi ko'raman.""",
            vocabularyIdsCsv = "v_a0_21_01",
            grammarRuleIdsCsv = "g_a0_21_01"
        ),
        ExerciseEntity(
            id = "ex_a0_21_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_21_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Men sutni yaxshi ko'raman gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I like milk.",
            optionsPipeSeparated = "I like milk.|I have milk.|Milk is good.",
            correctAnswer = "I like milk.",
            explanationUzbek = """I like milk.""",
            vocabularyIdsCsv = "v_a0_21_01",
            grammarRuleIdsCsv = "g_a0_21_01"
        ),
        ExerciseEntity(
            id = "ex_a0_22_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I can” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I can",
            optionsPipeSeparated = "men qila olaman|men boraman|men yaxshi ko'raman",
            correctAnswer = "men qila olaman",
            explanationUzbek = """I can — men qila olaman.""",
            vocabularyIdsCsv = "v_a0_22_01",
            grammarRuleIdsCsv = "g_a0_22_01"
        ),
        ExerciseEntity(
            id = "ex_a0_22_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: I ______ run.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I can run.",
            optionsPipeSeparated = "can|like|have",
            correctAnswer = "can",
            explanationUzbek = """I can run — men yugura olaman.""",
            vocabularyIdsCsv = "v_a0_22_01",
            grammarRuleIdsCsv = "g_a0_22_01"
        ),
        ExerciseEntity(
            id = "ex_a0_22_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_22_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Men sakray olaman gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I can jump.",
            optionsPipeSeparated = "I can jump.|I like jump.|Jump me.",
            correctAnswer = "I can jump.",
            explanationUzbek = """I can jump.""",
            vocabularyIdsCsv = "v_a0_22_01",
            grammarRuleIdsCsv = "g_a0_22_01"
        ),
        ExerciseEntity(
            id = "ex_a0_23_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have",
            optionsPipeSeparated = "menda bor|men boraman|men ko'raman",
            correctAnswer = "menda bor",
            explanationUzbek = """I have — menda bor.""",
            vocabularyIdsCsv = "v_a0_23_01",
            grammarRuleIdsCsv = "g_a0_23_01"
        ),
        ExerciseEntity(
            id = "ex_a0_23_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: I ______ a ball.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have a ball.",
            optionsPipeSeparated = "have|can|see",
            correctAnswer = "have",
            explanationUzbek = """I have a ball — menda to'p bor.""",
            vocabularyIdsCsv = "v_a0_23_01",
            grammarRuleIdsCsv = "g_a0_23_01"
        ),
        ExerciseEntity(
            id = "ex_a0_23_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_23_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Menda kitob bor gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have a book.",
            optionsPipeSeparated = "I have a book.|I see a book.|Book is mine.",
            correctAnswer = "I have a book.",
            explanationUzbek = """I have a book.""",
            vocabularyIdsCsv = "v_a0_23_01",
            grammarRuleIdsCsv = "g_a0_23_01"
        ),
        ExerciseEntity(
            id = "ex_a0_24_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I see” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I see",
            optionsPipeSeparated = "men ko'ryapman|men boraman|men yaxshi ko'raman",
            correctAnswer = "men ko'ryapman",
            explanationUzbek = """I see — men ko'ryapman.""",
            vocabularyIdsCsv = "v_a0_24_01",
            grammarRuleIdsCsv = "g_a0_24_01"
        ),
        ExerciseEntity(
            id = "ex_a0_24_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: I ______ a cat.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I see a cat.",
            optionsPipeSeparated = "see|have|can",
            correctAnswer = "see",
            explanationUzbek = """I see a cat — men mushukni ko'ryapman.""",
            vocabularyIdsCsv = "v_a0_24_01",
            grammarRuleIdsCsv = "g_a0_24_01"
        ),
        ExerciseEntity(
            id = "ex_a0_24_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_24_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Men qushni ko'ryapman gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I see a bird.",
            optionsPipeSeparated = "I see a bird.|I have a bird.|Bird can fly.",
            correctAnswer = "I see a bird.",
            explanationUzbek = """I see a bird.""",
            vocabularyIdsCsv = "v_a0_24_01",
            grammarRuleIdsCsv = "g_a0_24_01"
        ),
        ExerciseEntity(
            id = "ex_a0_25_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I want” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I want",
            optionsPipeSeparated = "men xohlayman|men boraman|men ko'raman",
            correctAnswer = "men xohlayman",
            explanationUzbek = """I want — men xohlayman.""",
            vocabularyIdsCsv = "v_a0_25_01",
            grammarRuleIdsCsv = "g_a0_25_01"
        ),
        ExerciseEntity(
            id = "ex_a0_25_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: I ______ water.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I want water.",
            optionsPipeSeparated = "want|like|see",
            correctAnswer = "want",
            explanationUzbek = """I want water — men suv xohlayman.""",
            vocabularyIdsCsv = "v_a0_25_01",
            grammarRuleIdsCsv = "g_a0_25_01"
        ),
        ExerciseEntity(
            id = "ex_a0_25_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_25_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Men sut xohlayman gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I want milk.",
            optionsPipeSeparated = "I want milk.|I like milk.|Milk please.",
            correctAnswer = "I want milk.",
            explanationUzbek = """I want milk.""",
            vocabularyIdsCsv = "v_a0_25_01",
            grammarRuleIdsCsv = "g_a0_25_01"
        ),
        ExerciseEntity(
            id = "ex_a0_26_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Sit down” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sit down",
            optionsPipeSeparated = "tur|o'tir|yugur",
            correctAnswer = "o'tir",
            explanationUzbek = """Sit down — o'tir degani.""",
            vocabularyIdsCsv = "v_a0_26_01",
            grammarRuleIdsCsv = "g_a0_26_01"
        ),
        ExerciseEntity(
            id = "ex_a0_26_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Stand up” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Stand up",
            optionsPipeSeparated = "tur|o'tir|yugur",
            correctAnswer = "tur",
            explanationUzbek = """Stand up — tur degani.""",
            vocabularyIdsCsv = "v_a0_26_01",
            grammarRuleIdsCsv = "g_a0_26_01"
        ),
        ExerciseEntity(
            id = "ex_a0_26_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_26_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joyni to‘ldiring: ______ down, please.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sit down, please.",
            optionsPipeSeparated = "Sit|Stand|Run",
            correctAnswer = "Sit",
            explanationUzbek = """Sit down, please — o'tiring, iltimos.""",
            vocabularyIdsCsv = "v_a0_26_01",
            grammarRuleIdsCsv = "g_a0_26_01"
        ),
        ExerciseEntity(
            id = "ex_a0_27_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🏃 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Run",
            optionsPipeSeparated = "run|jump|walk",
            correctAnswer = "run",
            explanationUzbek = """Yugur — run.""",
            vocabularyIdsCsv = "v_a0_27_01",
            grammarRuleIdsCsv = "g_a0_27_01"
        ),
        ExerciseEntity(
            id = "ex_a0_27_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🦘 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Jump",
            optionsPipeSeparated = "run|jump|walk",
            correctAnswer = "jump",
            explanationUzbek = """Sakra — jump.""",
            vocabularyIdsCsv = "v_a0_27_01",
            grammarRuleIdsCsv = "g_a0_27_01"
        ),
        ExerciseEntity(
            id = "ex_a0_27_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_27_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I ______ (yur).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I walk.",
            optionsPipeSeparated = "walk|run|jump",
            correctAnswer = "walk",
            explanationUzbek = """Yur — walk.""",
            vocabularyIdsCsv = "v_a0_27_01",
            grammarRuleIdsCsv = "g_a0_27_01"
        ),
        ExerciseEntity(
            id = "ex_a0_28_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍽️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Eat",
            optionsPipeSeparated = "eat|drink|sleep",
            correctAnswer = "eat",
            explanationUzbek = """Ye — eat.""",
            vocabularyIdsCsv = "v_a0_28_01",
            grammarRuleIdsCsv = "g_a0_28_01"
        ),
        ExerciseEntity(
            id = "ex_a0_28_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "😴 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sleep",
            optionsPipeSeparated = "eat|drink|sleep",
            correctAnswer = "sleep",
            explanationUzbek = """Uxla — sleep.""",
            vocabularyIdsCsv = "v_a0_28_01",
            grammarRuleIdsCsv = "g_a0_28_01"
        ),
        ExerciseEntity(
            id = "ex_a0_28_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_28_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I ______ milk.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I drink milk.",
            optionsPipeSeparated = "drink|eat|sleep",
            correctAnswer = "drink",
            explanationUzbek = """Ich — drink.""",
            vocabularyIdsCsv = "v_a0_28_01",
            grammarRuleIdsCsv = "g_a0_28_01"
        ),
        ExerciseEntity(
            id = "ex_a0_29_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Open” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Open",
            optionsPipeSeparated = "och|yop",
            correctAnswer = "och",
            explanationUzbek = """Open — och degani.""",
            vocabularyIdsCsv = "v_a0_29_01",
            grammarRuleIdsCsv = "g_a0_29_01"
        ),
        ExerciseEntity(
            id = "ex_a0_29_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Close” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Close",
            optionsPipeSeparated = "och|yop",
            correctAnswer = "yop",
            explanationUzbek = """Close — yop degani.""",
            vocabularyIdsCsv = "v_a0_29_01",
            grammarRuleIdsCsv = "g_a0_29_01"
        ),
        ExerciseEntity(
            id = "ex_a0_29_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_29_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: ______ the door (yop).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Close the door.",
            optionsPipeSeparated = "Close|Open|Run",
            correctAnswer = "Close",
            explanationUzbek = """Close the door — eshikni yop.""",
            vocabularyIdsCsv = "v_a0_29_01",
            grammarRuleIdsCsv = "g_a0_29_01"
        ),
        ExerciseEntity(
            id = "ex_a0_30_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Give me” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Give me",
            optionsPipeSeparated = "menga ber|menga ko'rsat",
            correctAnswer = "menga ber",
            explanationUzbek = """Give me — menga ber.""",
            vocabularyIdsCsv = "v_a0_30_01",
            grammarRuleIdsCsv = "g_a0_30_01"
        ),
        ExerciseEntity(
            id = "ex_a0_30_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Show me” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Show me",
            optionsPipeSeparated = "menga ber|menga ko'rsat",
            correctAnswer = "menga ko'rsat",
            explanationUzbek = """Show me — menga ko'rsat.""",
            vocabularyIdsCsv = "v_a0_30_01",
            grammarRuleIdsCsv = "g_a0_30_01"
        ),
        ExerciseEntity(
            id = "ex_a0_30_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_30_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: ______ me a book (ber).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Give me a book.",
            optionsPipeSeparated = "Give|Show|Eat",
            correctAnswer = "Give",
            explanationUzbek = """Give me a book — menga kitob ber.""",
            vocabularyIdsCsv = "v_a0_30_01",
            grammarRuleIdsCsv = "g_a0_30_01"
        ),
        ExerciseEntity(
            id = "ex_a0_31_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🔥 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hot",
            optionsPipeSeparated = "hot|cold",
            correctAnswer = "hot",
            explanationUzbek = """Issiq — hot.""",
            vocabularyIdsCsv = "v_a0_31_01",
            grammarRuleIdsCsv = "g_a0_31_01"
        ),
        ExerciseEntity(
            id = "ex_a0_31_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "❄️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Cold",
            optionsPipeSeparated = "hot|cold",
            correctAnswer = "cold",
            explanationUzbek = """Sovuq — cold.""",
            vocabularyIdsCsv = "v_a0_31_01",
            grammarRuleIdsCsv = "g_a0_31_01"
        ),
        ExerciseEntity(
            id = "ex_a0_31_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_31_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: The tea is ______.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "The tea is hot.",
            optionsPipeSeparated = "hot|cold|run",
            correctAnswer = "hot",
            explanationUzbek = """Choy issiq — hot.""",
            vocabularyIdsCsv = "v_a0_31_01",
            grammarRuleIdsCsv = "g_a0_31_01"
        ),
        ExerciseEntity(
            id = "ex_a0_32_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "😊 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Happy",
            optionsPipeSeparated = "happy|sad",
            correctAnswer = "happy",
            explanationUzbek = """Baxtli — happy.""",
            vocabularyIdsCsv = "v_a0_32_01",
            grammarRuleIdsCsv = "g_a0_32_01"
        ),
        ExerciseEntity(
            id = "ex_a0_32_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "😢 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sad",
            optionsPipeSeparated = "happy|sad",
            correctAnswer = "sad",
            explanationUzbek = """Xafa — sad.""",
            vocabularyIdsCsv = "v_a0_32_01",
            grammarRuleIdsCsv = "g_a0_32_01"
        ),
        ExerciseEntity(
            id = "ex_a0_32_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_32_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I am ______ (baxtli).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am happy.",
            optionsPipeSeparated = "happy|sad|cold",
            correctAnswer = "happy",
            explanationUzbek = """Baxtli — happy.""",
            vocabularyIdsCsv = "v_a0_32_01",
            grammarRuleIdsCsv = "g_a0_32_01"
        ),
        ExerciseEntity(
            id = "ex_a0_33_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "✨ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Clean",
            optionsPipeSeparated = "clean|dirty",
            correctAnswer = "clean",
            explanationUzbek = """Toza — clean.""",
            vocabularyIdsCsv = "v_a0_33_01",
            grammarRuleIdsCsv = "g_a0_33_01"
        ),
        ExerciseEntity(
            id = "ex_a0_33_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "💩 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Dirty",
            optionsPipeSeparated = "clean|dirty",
            correctAnswer = "dirty",
            explanationUzbek = """Iflos — dirty.""",
            vocabularyIdsCsv = "v_a0_33_01",
            grammarRuleIdsCsv = "g_a0_33_01"
        ),
        ExerciseEntity(
            id = "ex_a0_33_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_33_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: My hands are ______ (toza).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My hands are clean.",
            optionsPipeSeparated = "clean|dirty|hot",
            correctAnswer = "clean",
            explanationUzbek = """Toza — clean.""",
            vocabularyIdsCsv = "v_a0_33_01",
            grammarRuleIdsCsv = "g_a0_33_01"
        ),
        ExerciseEntity(
            id = "ex_a0_34_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐆 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Fast",
            optionsPipeSeparated = "fast|slow",
            correctAnswer = "fast",
            explanationUzbek = """Tez — fast.""",
            vocabularyIdsCsv = "v_a0_34_01",
            grammarRuleIdsCsv = "g_a0_34_01"
        ),
        ExerciseEntity(
            id = "ex_a0_34_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐢 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Slow",
            optionsPipeSeparated = "fast|slow",
            correctAnswer = "slow",
            explanationUzbek = """Sekin — slow.""",
            vocabularyIdsCsv = "v_a0_34_01",
            grammarRuleIdsCsv = "g_a0_34_01"
        ),
        ExerciseEntity(
            id = "ex_a0_34_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_34_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: The car is ______ (tez).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "The car is fast.",
            optionsPipeSeparated = "fast|slow|clean",
            correctAnswer = "fast",
            explanationUzbek = """Tez — fast.""",
            vocabularyIdsCsv = "v_a0_34_01",
            grammarRuleIdsCsv = "g_a0_34_01"
        ),
        ExerciseEntity(
            id = "ex_a0_35_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "⬆️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "High",
            optionsPipeSeparated = "high|low",
            correctAnswer = "high",
            explanationUzbek = """Baland — high.""",
            vocabularyIdsCsv = "v_a0_35_01",
            grammarRuleIdsCsv = "g_a0_35_01"
        ),
        ExerciseEntity(
            id = "ex_a0_35_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "⬇️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Low",
            optionsPipeSeparated = "high|low",
            correctAnswer = "low",
            explanationUzbek = """Past — low.""",
            vocabularyIdsCsv = "v_a0_35_01",
            grammarRuleIdsCsv = "g_a0_35_01"
        ),
        ExerciseEntity(
            id = "ex_a0_35_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_35_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: The tree is ______ (baland).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "The tree is high.",
            optionsPipeSeparated = "high|low|fast",
            correctAnswer = "high",
            explanationUzbek = """Baland — high.""",
            vocabularyIdsCsv = "v_a0_35_01",
            grammarRuleIdsCsv = "g_a0_35_01"
        ),
        ExerciseEntity(
            id = "ex_a0_36_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍽️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hungry",
            optionsPipeSeparated = "hungry|full",
            correctAnswer = "hungry",
            explanationUzbek = """Och — hungry.""",
            vocabularyIdsCsv = "v_a0_36_01",
            grammarRuleIdsCsv = "g_a0_36_01"
        ),
        ExerciseEntity(
            id = "ex_a0_36_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "😋 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Full",
            optionsPipeSeparated = "hungry|full",
            correctAnswer = "full",
            explanationUzbek = """To'q — full.""",
            vocabularyIdsCsv = "v_a0_36_01",
            grammarRuleIdsCsv = "g_a0_36_01"
        ),
        ExerciseEntity(
            id = "ex_a0_36_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_36_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I am ______ (och).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am hungry.",
            optionsPipeSeparated = "hungry|full|clean",
            correctAnswer = "hungry",
            explanationUzbek = """Och — hungry.""",
            vocabularyIdsCsv = "v_a0_36_01",
            grammarRuleIdsCsv = "g_a0_36_01"
        ),
        ExerciseEntity(
            id = "ex_a0_37_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "☀️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Day",
            optionsPipeSeparated = "day|night",
            correctAnswer = "day",
            explanationUzbek = """Kun — day.""",
            vocabularyIdsCsv = "v_a0_37_01",
            grammarRuleIdsCsv = "g_a0_37_01"
        ),
        ExerciseEntity(
            id = "ex_a0_37_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🌙 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Night",
            optionsPipeSeparated = "day|night",
            correctAnswer = "night",
            explanationUzbek = """Tun — night.""",
            vocabularyIdsCsv = "v_a0_37_01",
            grammarRuleIdsCsv = "g_a0_37_01"
        ),
        ExerciseEntity(
            id = "ex_a0_37_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_37_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: It is ______ (tun).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "It is night.",
            optionsPipeSeparated = "night|day|hot",
            correctAnswer = "night",
            explanationUzbek = """Tun — night.""",
            vocabularyIdsCsv = "v_a0_37_01",
            grammarRuleIdsCsv = "g_a0_37_01"
        ),
        ExerciseEntity(
            id = "ex_a0_38_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🌅 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Morning",
            optionsPipeSeparated = "morning|night",
            correctAnswer = "morning",
            explanationUzbek = """Tong — morning.""",
            vocabularyIdsCsv = "v_a0_38_01",
            grammarRuleIdsCsv = "g_a0_38_01"
        ),
        ExerciseEntity(
            id = "ex_a0_38_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🌙 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Night",
            optionsPipeSeparated = "morning|night",
            correctAnswer = "night",
            explanationUzbek = """Tun — night.""",
            vocabularyIdsCsv = "v_a0_38_01",
            grammarRuleIdsCsv = "g_a0_38_01"
        ),
        ExerciseEntity(
            id = "ex_a0_38_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_38_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: Good ______! (tong).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Good morning!",
            optionsPipeSeparated = "morning|night|day",
            correctAnswer = "morning",
            explanationUzbek = """Good morning — xayrli tong.""",
            vocabularyIdsCsv = "v_a0_38_01",
            grammarRuleIdsCsv = "g_a0_38_01"
        ),
        ExerciseEntity(
            id = "ex_a0_39_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Monday” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Monday",
            optionsPipeSeparated = "dushanba|seshanba",
            correctAnswer = "dushanba",
            explanationUzbek = """Monday — dushanba.""",
            vocabularyIdsCsv = "v_a0_39_01",
            grammarRuleIdsCsv = "g_a0_39_01"
        ),
        ExerciseEntity(
            id = "ex_a0_39_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Sunday” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sunday",
            optionsPipeSeparated = "dushanba|yakshanba",
            correctAnswer = "yakshanba",
            explanationUzbek = """Sunday — yakshanba.""",
            vocabularyIdsCsv = "v_a0_39_01",
            grammarRuleIdsCsv = "g_a0_39_01"
        ),
        ExerciseEntity(
            id = "ex_a0_39_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_39_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: Today is ______ (seshanba).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Today is Tuesday.",
            optionsPipeSeparated = "Tuesday|Monday|Sunday",
            correctAnswer = "Tuesday",
            explanationUzbek = """Seshanba — Tuesday.""",
            vocabularyIdsCsv = "v_a0_39_01",
            grammarRuleIdsCsv = "g_a0_39_01"
        ),
        ExerciseEntity(
            id = "ex_a0_40_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🚗 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Car",
            optionsPipeSeparated = "car|bus",
            correctAnswer = "car",
            explanationUzbek = """Mashina — car.""",
            vocabularyIdsCsv = "v_a0_40_01",
            grammarRuleIdsCsv = "g_a0_40_01"
        ),
        ExerciseEntity(
            id = "ex_a0_40_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "✈️ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Plane",
            optionsPipeSeparated = "bike|plane",
            correctAnswer = "plane",
            explanationUzbek = """Samolyot — plane.""",
            vocabularyIdsCsv = "v_a0_40_01",
            grammarRuleIdsCsv = "g_a0_40_01"
        ),
        ExerciseEntity(
            id = "ex_a0_40_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_40_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Bike” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Bike",
            optionsPipeSeparated = "mashina|velosiped",
            correctAnswer = "velosiped",
            explanationUzbek = """Bike — velosiped.""",
            vocabularyIdsCsv = "v_a0_40_01",
            grammarRuleIdsCsv = "g_a0_40_01"
        ),
        ExerciseEntity(
            id = "ex_a0_41_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“In” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "In",
            optionsPipeSeparated = "ichida|ustida|ostida",
            correctAnswer = "ichida",
            explanationUzbek = """In — ichida.""",
            vocabularyIdsCsv = "v_a0_41_01",
            grammarRuleIdsCsv = "g_a0_41_01"
        ),
        ExerciseEntity(
            id = "ex_a0_41_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“On” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "On",
            optionsPipeSeparated = "ichida|ustida|ostida",
            correctAnswer = "ustida",
            explanationUzbek = """On — ustida.""",
            vocabularyIdsCsv = "v_a0_41_01",
            grammarRuleIdsCsv = "g_a0_41_01"
        ),
        ExerciseEntity(
            id = "ex_a0_41_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_41_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Under” nima degani?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Under",
            optionsPipeSeparated = "ichida|ustida|ostida",
            correctAnswer = "ostida",
            explanationUzbek = """Under — ostida.""",
            vocabularyIdsCsv = "v_a0_41_01",
            grammarRuleIdsCsv = "g_a0_41_01"
        ),
        ExerciseEntity(
            id = "ex_a0_42_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Toy box” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Toy box",
            optionsPipeSeparated = "o'yinchoq qutisi|kitob",
            correctAnswer = "o'yinchoq qutisi",
            explanationUzbek = """Toy box — o'yinchoq qutisi.""",
            vocabularyIdsCsv = "v_a0_42_01",
            grammarRuleIdsCsv = "g_a0_42_01"
        ),
        ExerciseEntity(
            id = "ex_a0_42_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: A ball is ______ my toy box.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "A ball is in my toy box.",
            optionsPipeSeparated = "in|on|under",
            correctAnswer = "in",
            explanationUzbek = """Ichida — in.""",
            vocabularyIdsCsv = "v_a0_42_01",
            grammarRuleIdsCsv = "g_a0_42_01"
        ),
        ExerciseEntity(
            id = "ex_a0_42_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_42_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Ayiqcha qutimda deb ayting:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "A teddy in my toy box.",
            optionsPipeSeparated = "A teddy in my toy box.|Teddy under bed.|Toy box open.",
            correctAnswer = "A teddy in my toy box.",
            explanationUzbek = """A teddy in my toy box.""",
            vocabularyIdsCsv = "v_a0_42_01",
            grammarRuleIdsCsv = "g_a0_42_01"
        ),
        ExerciseEntity(
            id = "ex_a0_43_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "1️⃣ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "One",
            optionsPipeSeparated = "one|many",
            correctAnswer = "one",
            explanationUzbek = """Bitta — one.""",
            vocabularyIdsCsv = "v_a0_43_01",
            grammarRuleIdsCsv = "g_a0_43_01"
        ),
        ExerciseEntity(
            id = "ex_a0_43_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🔢 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Many",
            optionsPipeSeparated = "one|many",
            correctAnswer = "many",
            explanationUzbek = """Ko'p — many.""",
            vocabularyIdsCsv = "v_a0_43_01",
            grammarRuleIdsCsv = "g_a0_43_01"
        ),
        ExerciseEntity(
            id = "ex_a0_43_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_43_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: ______ balls (ko‘p).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Many balls.",
            optionsPipeSeparated = "Many|One|Cat",
            correctAnswer = "Many",
            explanationUzbek = """Ko'p — many.""",
            vocabularyIdsCsv = "v_a0_43_01",
            grammarRuleIdsCsv = "g_a0_43_01"
        ),
        ExerciseEntity(
            id = "ex_a0_44_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "✅ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Yes",
            optionsPipeSeparated = "yes|no",
            correctAnswer = "yes",
            explanationUzbek = """Ha — yes.""",
            vocabularyIdsCsv = "v_a0_44_01",
            grammarRuleIdsCsv = "g_a0_44_01"
        ),
        ExerciseEntity(
            id = "ex_a0_44_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "❌ nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "No",
            optionsPipeSeparated = "yes|no",
            correctAnswer = "no",
            explanationUzbek = """Yo'q — no.""",
            vocabularyIdsCsv = "v_a0_44_01",
            grammarRuleIdsCsv = "g_a0_44_01"
        ),
        ExerciseEntity(
            id = "ex_a0_44_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_44_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: ______, I am. (ha).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Yes, I am.",
            optionsPipeSeparated = "Yes|No|Not",
            correctAnswer = "Yes",
            explanationUzbek = """Ha — yes.""",
            vocabularyIdsCsv = "v_a0_44_01",
            grammarRuleIdsCsv = "g_a0_44_01"
        ),
        ExerciseEntity(
            id = "ex_a0_45_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Please” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Please",
            optionsPipeSeparated = "iltimos|rahmat",
            correctAnswer = "iltimos",
            explanationUzbek = """Please — iltimos.""",
            vocabularyIdsCsv = "v_a0_45_01",
            grammarRuleIdsCsv = "g_a0_45_01"
        ),
        ExerciseEntity(
            id = "ex_a0_45_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Thank you” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Thank you",
            optionsPipeSeparated = "iltimos|rahmat",
            correctAnswer = "rahmat",
            explanationUzbek = """Thank you — rahmat.""",
            vocabularyIdsCsv = "v_a0_45_01",
            grammarRuleIdsCsv = "g_a0_45_01"
        ),
        ExerciseEntity(
            id = "ex_a0_45_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_45_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: ______, give me water.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Please, give me water.",
            optionsPipeSeparated = "Please|Thank you|Hello",
            correctAnswer = "Please",
            explanationUzbek = """Iltimos — please.""",
            vocabularyIdsCsv = "v_a0_45_01",
            grammarRuleIdsCsv = "g_a0_45_01"
        ),
        ExerciseEntity(
            id = "ex_a0_46_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I am sorry” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am sorry",
            optionsPipeSeparated = "kechirasiz|rahmat",
            correctAnswer = "kechirasiz",
            explanationUzbek = """I am sorry — kechirasiz.""",
            vocabularyIdsCsv = "v_a0_46_01",
            grammarRuleIdsCsv = "g_a0_46_01"
        ),
        ExerciseEntity(
            id = "ex_a0_46_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I am ______.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am sorry.",
            optionsPipeSeparated = "sorry|happy|clean",
            correctAnswer = "sorry",
            explanationUzbek = """I am sorry — kechirasiz.""",
            vocabularyIdsCsv = "v_a0_46_01",
            grammarRuleIdsCsv = "g_a0_46_01"
        ),
        ExerciseEntity(
            id = "ex_a0_46_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_46_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Ovoz chiqarib ayting:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am sorry!",
            optionsPipeSeparated = "I am sorry!|Hello!|Goodbye!",
            correctAnswer = "I am sorry!",
            explanationUzbek = """I am sorry!""",
            vocabularyIdsCsv = "v_a0_46_01",
            grammarRuleIdsCsv = "g_a0_46_01"
        ),
        ExerciseEntity(
            id = "ex_a0_47_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Goodbye” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Goodbye",
            optionsPipeSeparated = "salom|xayr",
            correctAnswer = "xayr",
            explanationUzbek = """Goodbye — xayr.""",
            vocabularyIdsCsv = "v_a0_47_01",
            grammarRuleIdsCsv = "g_a0_47_01"
        ),
        ExerciseEntity(
            id = "ex_a0_47_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: Good ______, mom!",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Goodbye, mom!",
            optionsPipeSeparated = "bye|hello|night",
            correctAnswer = "bye",
            explanationUzbek = """Goodbye — xayr.""",
            vocabularyIdsCsv = "v_a0_47_01",
            grammarRuleIdsCsv = "g_a0_47_01"
        ),
        ExerciseEntity(
            id = "ex_a0_47_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_47_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Do'st bilan xayrlashish:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Goodbye, friend!",
            optionsPipeSeparated = "Goodbye, friend!|Hello, friend!|Thank you, friend!",
            correctAnswer = "Goodbye, friend!",
            explanationUzbek = """Goodbye, friend!""",
            vocabularyIdsCsv = "v_a0_47_01",
            grammarRuleIdsCsv = "g_a0_47_01"
        ),
        ExerciseEntity(
            id = "ex_a0_48_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Pet” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Pet",
            optionsPipeSeparated = "uy hayvoni|o'yinchoq",
            correctAnswer = "uy hayvoni",
            explanationUzbek = """Pet — uy hayvoni.""",
            vocabularyIdsCsv = "v_a0_48_01",
            grammarRuleIdsCsv = "g_a0_48_01"
        ),
        ExerciseEntity(
            id = "ex_a0_48_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: My ______ is a cat.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My pet is a cat.",
            optionsPipeSeparated = "pet|toy|food",
            correctAnswer = "pet",
            explanationUzbek = """Uy hayvoni — pet.""",
            vocabularyIdsCsv = "v_a0_48_01",
            grammarRuleIdsCsv = "g_a0_48_01"
        ),
        ExerciseEntity(
            id = "ex_a0_48_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_48_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Mening uy hayvonim it gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "My pet is a dog.",
            optionsPipeSeparated = "My pet is a dog.|Dog is running.|I see dog.",
            correctAnswer = "My pet is a dog.",
            explanationUzbek = """My pet is a dog.""",
            vocabularyIdsCsv = "v_a0_48_01",
            grammarRuleIdsCsv = "g_a0_48_01"
        ),
        ExerciseEntity(
            id = "ex_a0_49_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I love” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I love",
            optionsPipeSeparated = "juda yaxshi ko'raman|yaxshi ko'raman",
            correctAnswer = "juda yaxshi ko'raman",
            explanationUzbek = """Love — juda kuchli yaxshi ko'rish.""",
            vocabularyIdsCsv = "v_a0_49_01",
            grammarRuleIdsCsv = "g_a0_49_01"
        ),
        ExerciseEntity(
            id = "ex_a0_49_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I ______ mom.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I love mom.",
            optionsPipeSeparated = "love|run|see",
            correctAnswer = "love",
            explanationUzbek = """I love mom.""",
            vocabularyIdsCsv = "v_a0_49_01",
            grammarRuleIdsCsv = "g_a0_49_01"
        ),
        ExerciseEntity(
            id = "ex_a0_49_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_49_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Dadamni yaxshi ko'raman gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I love dad.",
            optionsPipeSeparated = "I love dad.|I see dad.|Dad is happy.",
            correctAnswer = "I love dad.",
            explanationUzbek = """I love dad.""",
            vocabularyIdsCsv = "v_a0_49_01",
            grammarRuleIdsCsv = "g_a0_49_01"
        ),
        ExerciseEntity(
            id = "ex_a0_50_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👦 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Boy",
            optionsPipeSeparated = "boy|girl",
            correctAnswer = "boy",
            explanationUzbek = """O'g'il bola — boy.""",
            vocabularyIdsCsv = "v_a0_50_01",
            grammarRuleIdsCsv = "g_a0_50_01"
        ),
        ExerciseEntity(
            id = "ex_a0_50_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "👧 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Girl",
            optionsPipeSeparated = "boy|girl",
            correctAnswer = "girl",
            explanationUzbek = """Qiz — girl.""",
            vocabularyIdsCsv = "v_a0_50_01",
            grammarRuleIdsCsv = "g_a0_50_01"
        ),
        ExerciseEntity(
            id = "ex_a0_50_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_50_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I am a ______ (bola).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am a boy.",
            optionsPipeSeparated = "boy|girl|cat",
            correctAnswer = "boy",
            explanationUzbek = """I am a boy.""",
            vocabularyIdsCsv = "v_a0_50_01",
            grammarRuleIdsCsv = "g_a0_50_01"
        ),
        ExerciseEntity(
            id = "ex_a0_51_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "😊 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Happy",
            optionsPipeSeparated = "happy|sad",
            correctAnswer = "happy",
            explanationUzbek = """Baxtli — happy.""",
            vocabularyIdsCsv = "v_a0_51_01",
            grammarRuleIdsCsv = "g_a0_51_01"
        ),
        ExerciseEntity(
            id = "ex_a0_51_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "😢 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Sad",
            optionsPipeSeparated = "happy|sad",
            correctAnswer = "sad",
            explanationUzbek = """Xafa — sad.""",
            vocabularyIdsCsv = "v_a0_51_01",
            grammarRuleIdsCsv = "g_a0_51_01"
        ),
        ExerciseEntity(
            id = "ex_a0_51_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_51_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I am ______ (charchadim).",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I am tired.",
            optionsPipeSeparated = "tired|happy|sad",
            correctAnswer = "tired",
            explanationUzbek = """Charchadim — tired.""",
            vocabularyIdsCsv = "v_a0_51_01",
            grammarRuleIdsCsv = "g_a0_51_01"
        ),
        ExerciseEntity(
            id = "ex_a0_52_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Go” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Go",
            optionsPipeSeparated = "bormoq|kelmoq",
            correctAnswer = "bormoq",
            explanationUzbek = """Go — bormoq.""",
            vocabularyIdsCsv = "v_a0_52_01",
            grammarRuleIdsCsv = "g_a0_52_01"
        ),
        ExerciseEntity(
            id = "ex_a0_52_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I ______ to school.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I go to school.",
            optionsPipeSeparated = "go|see|can",
            correctAnswer = "go",
            explanationUzbek = """I go to school — men maktabga boraman.""",
            vocabularyIdsCsv = "v_a0_52_01",
            grammarRuleIdsCsv = "g_a0_52_01"
        ),
        ExerciseEntity(
            id = "ex_a0_52_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_52_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Maktabga boraman gapini tuzing:",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I go to school.",
            optionsPipeSeparated = "I go to school.|I see school.|School is big.",
            correctAnswer = "I go to school.",
            explanationUzbek = """I go to school.""",
            vocabularyIdsCsv = "v_a0_52_01",
            grammarRuleIdsCsv = "g_a0_52_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_01",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Hello” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hello",
            optionsPipeSeparated = "salom|xayr",
            correctAnswer = "salom",
            explanationUzbek = """Hello — salom.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_02",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🍎 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Apple",
            optionsPipeSeparated = "apple|banana",
            correctAnswer = "apple",
            explanationUzbek = """Olma — apple.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_03",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🐱 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Cat",
            optionsPipeSeparated = "cat|dog",
            correctAnswer = "cat",
            explanationUzbek = """Mushuk — cat.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_04",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Mom” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Mom",
            optionsPipeSeparated = "ona|dada",
            correctAnswer = "ona",
            explanationUzbek = """Ona — mom.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_05",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Three” nechchi?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Three",
            optionsPipeSeparated = "2|3",
            correctAnswer = "3",
            explanationUzbek = """Three — 3 soni.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_06",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "🔴 nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Red",
            optionsPipeSeparated = "red|blue",
            correctAnswer = "red",
            explanationUzbek = """Qizil — red.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_07",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Big” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Big",
            optionsPipeSeparated = "katta|kichik",
            correctAnswer = "katta",
            explanationUzbek = """Big — katta.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_08",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“Hot” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hot",
            optionsPipeSeparated = "issiq|sovuq",
            correctAnswer = "issiq",
            explanationUzbek = """Hot — issiq.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_09",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I like” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I like",
            optionsPipeSeparated = "yaxshi ko'raman|boraman",
            correctAnswer = "yaxshi ko'raman",
            explanationUzbek = """I like — yaxshi ko'raman.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_10",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I can” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I can",
            optionsPipeSeparated = "qila olaman|boraman",
            correctAnswer = "qila olaman",
            explanationUzbek = """I can — qila olaman.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_11",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I have” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I have",
            optionsPipeSeparated = "menda bor|men ko'raman",
            correctAnswer = "menda bor",
            explanationUzbek = """I have — menda bor.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_12",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "“I go” nima?",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I go",
            optionsPipeSeparated = "boraman|kelaman",
            correctAnswer = "boraman",
            explanationUzbek = """I go — boraman.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_13",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: The apple is ______ the box.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "The apple is in the box.",
            optionsPipeSeparated = "in|under|on",
            correctAnswer = "in",
            explanationUzbek = """Ichida — in.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_14",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Bo‘sh joy: I ______ to school.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "I go to school.",
            optionsPipeSeparated = "go|see|can",
            correctAnswer = "go",
            explanationUzbek = """I go to school — maktabga boraman.""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        ),
        ExerciseEntity(
            id = "ex_a0_53_15",
            topicId = CurriculumLevel.A0.topicId,
            sectionId = DARS_53_SECTION_ID,
            practiceCategory = "SECTION_QUIZ",
            exerciseType = "MULTIPLE_CHOICE",
            difficulty = 1,
            promptText = "Ovoz chiqarib o‘qing: Hello! My name is Aziz. I am happy.",
            subPromptText = "To‘g‘ri javobni tanlang:",
            audioTextToSpeak = "Hello! My name is Aziz. I am happy.",
            optionsPipeSeparated = "O'qidim|Keyinroq",
            correctAnswer = "O'qidim",
            explanationUzbek = """Barakalla!""",
            vocabularyIdsCsv = "v_a0_53_01",
            grammarRuleIdsCsv = "g_a0_53_01"
        )
    )

    fun getMisollar(sectionId: String): List<CurriculumSeedData.LessonExampleSeed>? = when (sectionId) {
        DARS_1_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Hello!", "Hello", "", "Salom!", "Salom"),
            CurriculumSeedData.LessonExampleSeed("Hello, mom!", "Hello", "mom", "Salom, oyijon!", "oyijon"),
            CurriculumSeedData.LessonExampleSeed("Hello, Dad!", "Hello", "Dad", "Salom, dada!", "dada")
        )
        DARS_2_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My name is Aziz.", "My name", "Aziz", "Mening ismim Aziz.", "Aziz"),
            CurriculumSeedData.LessonExampleSeed("My name is Laylo.", "My name", "Laylo", "Mening ismim Laylo.", "Laylo"),
            CurriculumSeedData.LessonExampleSeed("My name is Tom.", "My name", "Tom", "Mening ismim Tom.", "Tom")
        )
        DARS_3_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Red apple", "Red", "apple", "Qizil olma", "olma"),
            CurriculumSeedData.LessonExampleSeed("Blue sky", "Blue", "sky", "Ko'k osmon", "osmon"),
            CurriculumSeedData.LessonExampleSeed("Green tree", "Green", "tree", "Yashil daraxt", "daraxt")
        )
        DARS_4_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("One book", "One", "book", "Bitta kitob", "kitob"),
            CurriculumSeedData.LessonExampleSeed("Two cats", "Two", "cats", "Ikkita mushuk", "mushuk"),
            CurriculumSeedData.LessonExampleSeed("Three apples", "Three", "apples", "Uchta olma", "olma")
        )
        DARS_5_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Six pens", "Six", "pens", "Oltita ruchka", "ruchka"),
            CurriculumSeedData.LessonExampleSeed("Seven dogs", "Seven", "dogs", "Yettita it", "it"),
            CurriculumSeedData.LessonExampleSeed("Ten fingers", "Ten", "fingers", "O'nta barmoq", "barmoq")
        )
        DARS_6_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Hello, cat!", "Hello", "cat", "Salom, mushuk!", "mushuk"),
            CurriculumSeedData.LessonExampleSeed("Hello, dog!", "Hello", "dog", "Salom, kuchuk!", "it"),
            CurriculumSeedData.LessonExampleSeed("Big elephant", "Big", "elephant", "Katta fil", "fil")
        )
        DARS_7_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Hello, mom!", "Hello", "mom", "Salom, oyijon!", "oyijon"),
            CurriculumSeedData.LessonExampleSeed("Hello, dad!", "Hello", "dad", "Salom, dada!", "dada"),
            CurriculumSeedData.LessonExampleSeed("My sister", "My", "sister", "Mening singlim", "singlim")
        )
        DARS_8_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My eyes", "My", "eyes", "Mening ko'zlarim", "ko'zlarim"),
            CurriculumSeedData.LessonExampleSeed("My nose", "My", "nose", "Mening burnim", "burnim"),
            CurriculumSeedData.LessonExampleSeed("My hands", "My", "hands", "Mening qo'llarim", "qo'llarim")
        )
        DARS_9_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My ball", "My", "ball", "Mening to'pim", "to'pim"),
            CurriculumSeedData.LessonExampleSeed("My teddy", "My", "teddy", "Mening ayiqcham", "ayiqcham"),
            CurriculumSeedData.LessonExampleSeed("My car", "My", "car", "Mening mashinam", "mashinam")
        )
        DARS_10_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Fresh bread", "Fresh", "bread", "Yangi non", "non"),
            CurriculumSeedData.LessonExampleSeed("White rice", "White", "rice", "Oq guruch", "guruch"),
            CurriculumSeedData.LessonExampleSeed("One egg", "One", "egg", "Bitta tuxum", "tuxum")
        )
        DARS_11_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Cold water", "Cold", "water", "Sovuq suv", "suv"),
            CurriculumSeedData.LessonExampleSeed("White milk", "White", "milk", "Oq sut", "sut"),
            CurriculumSeedData.LessonExampleSeed("Sweet juice", "Sweet", "juice", "Shirin sharbat", "sharbat")
        )
        DARS_12_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Red apple", "Red", "apple", "Qizil olma", "olma"),
            CurriculumSeedData.LessonExampleSeed("Yellow banana", "Yellow", "banana", "Sariq banan", "banan"),
            CurriculumSeedData.LessonExampleSeed("Sweet strawberry", "Sweet", "strawberry", "Shirin qulupnay", "qulupnay")
        )
        DARS_13_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Orange carrot", "Orange", "carrot", "To'q sariq sabzi", "sabzi"),
            CurriculumSeedData.LessonExampleSeed("Red tomato", "Red", "tomato", "Qizil pomidor", "pomidor"),
            CurriculumSeedData.LessonExampleSeed("Green cucumber", "Green", "cucumber", "Yashil bodring", "bodring")
        )
        DARS_14_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My T-shirt", "My", "T-shirt", "Mening futbolkam", "futbolkam"),
            CurriculumSeedData.LessonExampleSeed("Blue pants", "Blue", "pants", "Ko'k shim", "shim"),
            CurriculumSeedData.LessonExampleSeed("New shoes", "New", "shoes", "Yangi poyabzal", "poyabzal")
        )
        DARS_15_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My house", "My", "house", "Mening uyim", "uyim"),
            CurriculumSeedData.LessonExampleSeed("Big door", "Big", "door", "Katta eshik", "eshik"),
            CurriculumSeedData.LessonExampleSeed("Wooden table", "Wooden", "table", "Yog'och stol", "stol")
        )
        DARS_16_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My room", "My", "room", "Mening xonam", "xonam"),
            CurriculumSeedData.LessonExampleSeed("My bed", "My", "bed", "Mening karavotim", "karavotim"),
            CurriculumSeedData.LessonExampleSeed("Nice picture", "Nice", "picture", "Chiroyli rasm", "rasm")
        )
        DARS_17_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My school", "My", "school", "Mening maktabim", "maktabim"),
            CurriculumSeedData.LessonExampleSeed("Hello, teacher!", "Hello", "teacher", "Assalomu alaykum, ustoz!", "o'qituvchi"),
            CurriculumSeedData.LessonExampleSeed("My bag", "My", "bag", "Mening sumkam", "sumkam")
        )
        DARS_18_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("It is sunny.", "It is", "sunny", "Havo quyoshli.", "quyoshli"),
            CurriculumSeedData.LessonExampleSeed("It is rainy.", "It is", "rainy", "Havo yomg'irli.", "yomg'irli"),
            CurriculumSeedData.LessonExampleSeed("It is snowy.", "It is", "snowy", "Havo qorli.", "qorli")
        )
        DARS_19_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Big elephant", "Big", "elephant", "Katta fil", "fil"),
            CurriculumSeedData.LessonExampleSeed("Small mouse", "Small", "mouse", "Kichik sichqon", "sichqon"),
            CurriculumSeedData.LessonExampleSeed("Big ball", "Big", "ball", "Katta to'p", "to'p")
        )
        DARS_20_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("A circle", "A", "circle", "Bitta doira", "doira"),
            CurriculumSeedData.LessonExampleSeed("A square", "A", "square", "Bitta kvadrat", "kvadrat"),
            CurriculumSeedData.LessonExampleSeed("A star", "A", "star", "Bitta yulduz", "yulduz")
        )
        DARS_21_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I like apples.", "I like", "apples", "Men olmalarni yaxshi ko'raman.", "olmalarni"),
            CurriculumSeedData.LessonExampleSeed("I like milk.", "I like", "milk", "Men sutni yaxshi ko'raman.", "sutni"),
            CurriculumSeedData.LessonExampleSeed("I like cats.", "I like", "cats", "Men mushuklarni yaxshi ko'raman.", "mushuklarni")
        )
        DARS_22_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I can run.", "I can", "run", "Men yugura olaman.", "yugura olaman"),
            CurriculumSeedData.LessonExampleSeed("I can jump.", "I can", "jump", "Men sakray olaman.", "sakray olaman"),
            CurriculumSeedData.LessonExampleSeed("I can sing.", "I can", "sing", "Men qo'shiq aytaman.", "qo'shiq aytaman")
        )
        DARS_23_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I have a ball.", "I have", "ball", "Menda to'p bor.", "to'p"),
            CurriculumSeedData.LessonExampleSeed("I have a book.", "I have", "book", "Menda kitob bor.", "kitob"),
            CurriculumSeedData.LessonExampleSeed("I have a cat.", "I have", "cat", "Menda mushuk bor.", "mushuk")
        )
        DARS_24_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I see a cat.", "I see", "cat", "Men mushukni ko'ryapman.", "mushukni"),
            CurriculumSeedData.LessonExampleSeed("I see a ball.", "I see", "ball", "Men to'pni ko'ryapman.", "to'pni"),
            CurriculumSeedData.LessonExampleSeed("I see a bird.", "I see", "bird", "Men qushni ko'ryapman.", "qushni")
        )
        DARS_25_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I want water.", "I want", "water", "Men suv xohlayman.", "suv"),
            CurriculumSeedData.LessonExampleSeed("I want milk.", "I want", "milk", "Men sut xohlayman.", "sut"),
            CurriculumSeedData.LessonExampleSeed("I want a ball.", "I want", "ball", "Men to'p xohlayman.", "to'p")
        )
        DARS_26_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Sit down, please.", "Sit down", "please", "O'tiring, iltimos.", "iltimos"),
            CurriculumSeedData.LessonExampleSeed("Stand up, please.", "Stand up", "please", "Turing, iltimos.", "iltimos"),
            CurriculumSeedData.LessonExampleSeed("I sit down.", "I", "sit down", "Men o'tiraman.", "o'tiraman")
        )
        DARS_27_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I run.", "I", "run", "Men yuguraman.", "yuguraman"),
            CurriculumSeedData.LessonExampleSeed("I jump.", "I", "jump", "Men sakrayman.", "sakrayman"),
            CurriculumSeedData.LessonExampleSeed("I walk.", "I", "walk", "Men yuraman.", "yuraman")
        )
        DARS_28_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I eat bread.", "I eat", "bread", "Men non yeyman.", "non"),
            CurriculumSeedData.LessonExampleSeed("I drink milk.", "I drink", "milk", "Men sut ichaman.", "sut"),
            CurriculumSeedData.LessonExampleSeed("I sleep.", "I", "sleep", "Men uxlayman.", "uxlayman")
        )
        DARS_29_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Open the door.", "Open", "door", "Eshikni och.", "Eshikni"),
            CurriculumSeedData.LessonExampleSeed("Close the door.", "Close", "door", "Eshikni yop.", "Eshikni"),
            CurriculumSeedData.LessonExampleSeed("Open the box.", "Open", "box", "Qutini och.", "Qutini")
        )
        DARS_30_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Give me a ball.", "Give me", "ball", "Menga to'p ber.", "to'p"),
            CurriculumSeedData.LessonExampleSeed("Show me a ball.", "Show me", "ball", "Menga to'pni ko'rsat.", "to'pni"),
            CurriculumSeedData.LessonExampleSeed("Give me a book.", "Give me", "book", "Menga kitob ber.", "kitob")
        )
        DARS_31_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Hot tea", "Hot", "tea", "Issiq choy", "choy"),
            CurriculumSeedData.LessonExampleSeed("Cold water", "Cold", "water", "Sovuq suv", "suv"),
            CurriculumSeedData.LessonExampleSeed("The tea is hot.", "The tea is", "hot", "Choy issiq.", "issiq")
        )
        DARS_32_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I am happy.", "I am", "happy", "Men baxtliman.", "baxtliman"),
            CurriculumSeedData.LessonExampleSeed("I am sad.", "I am", "sad", "Men xafaman.", "xafaman"),
            CurriculumSeedData.LessonExampleSeed("Mom is happy.", "Mom is", "happy", "Ona baxtli.", "baxtli")
        )
        DARS_33_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Clean hands", "Clean", "hands", "Toza qo'llar", "qo'llar"),
            CurriculumSeedData.LessonExampleSeed("Dirty hands", "Dirty", "hands", "Iflos qo'llar", "qo'llar"),
            CurriculumSeedData.LessonExampleSeed("My hands are clean.", "hands are", "clean", "Qo'llarim toza.", "toza")
        )
        DARS_34_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Fast car", "Fast", "car", "Tez mashina", "mashina"),
            CurriculumSeedData.LessonExampleSeed("Slow turtle", "Slow", "turtle", "Sekin toshbaqa", "toshbaqa"),
            CurriculumSeedData.LessonExampleSeed("The car is fast.", "car is", "fast", "Mashina tez.", "tez")
        )
        DARS_35_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("High tree", "High", "tree", "Baland daraxt", "daraxt"),
            CurriculumSeedData.LessonExampleSeed("Low chair", "Low", "chair", "Past stul", "stul"),
            CurriculumSeedData.LessonExampleSeed("The tree is high.", "tree is", "high", "Daraxt baland.", "baland")
        )
        DARS_36_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I am hungry.", "I am", "hungry", "Men ochman.", "ochman"),
            CurriculumSeedData.LessonExampleSeed("I am full.", "I am", "full", "Men to'qman.", "to'qman"),
            CurriculumSeedData.LessonExampleSeed("The cat is full.", "cat is", "full", "Mushuk to'q.", "to'q")
        )
        DARS_37_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("It is day.", "It is", "day", "Kun.", "Kun"),
            CurriculumSeedData.LessonExampleSeed("It is night.", "It is", "night", "Tun.", "Tun"),
            CurriculumSeedData.LessonExampleSeed("Good day!", "Good", "day", "Yaxshi kun!", "kun")
        )
        DARS_38_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Good morning!", "Good", "morning", "Xayrli tong!", "tong"),
            CurriculumSeedData.LessonExampleSeed("Good night!", "Good", "night", "Xayrli tun!", "tun"),
            CurriculumSeedData.LessonExampleSeed("It is morning.", "It is", "morning", "Ertalab.", "Ertalab")
        )
        DARS_39_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Today is Monday.", "Today is", "Monday", "Bugun dushanba.", "dushanba"),
            CurriculumSeedData.LessonExampleSeed("Today is Sunday.", "Today is", "Sunday", "Bugun yakshanba.", "yakshanba"),
            CurriculumSeedData.LessonExampleSeed("Monday", "Monday", "", "Dushanba", "Dushanba")
        )
        DARS_40_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My car", "My", "car", "Mening mashinam", "mashinam"),
            CurriculumSeedData.LessonExampleSeed("Big bus", "Big", "bus", "Katta avtobus", "avtobus"),
            CurriculumSeedData.LessonExampleSeed("Fast plane", "Fast", "plane", "Tez samolyot", "samolyot")
        )
        DARS_41_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("The apple is in the box.", "is in", "box", "Olma qutida.", "qutida"),
            CurriculumSeedData.LessonExampleSeed("The book is on the table.", "is on", "table", "Kitob stolda.", "stolda"),
            CurriculumSeedData.LessonExampleSeed("The cat is under the bed.", "is under", "bed", "Mushuk karavot ostida.", "karavot")
        )
        DARS_42_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My toy box", "My", "toy box", "Mening o'yinchoq qutim", "qutim"),
            CurriculumSeedData.LessonExampleSeed("A ball in my toy box.", "ball in", "toy box", "O'yinchoq qutimda to'p.", "to'p"),
            CurriculumSeedData.LessonExampleSeed("A teddy in my toy box.", "teddy in", "toy box", "O'yinchoq qutimda ayiqcha.", "ayiqcha")
        )
        DARS_43_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("One ball", "One", "ball", "Bitta to'p", "to'p"),
            CurriculumSeedData.LessonExampleSeed("Many balls", "Many", "balls", "Ko'p to'plar", "to'plar"),
            CurriculumSeedData.LessonExampleSeed("Many cats", "Many", "cats", "Ko'p mushuklar", "mushuklar")
        )
        DARS_44_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Yes, I am.", "Yes", "I am", "Ha, men.", "Ha"),
            CurriculumSeedData.LessonExampleSeed("No, I am not.", "No", "I am not", "Yo'q, men emas.", "Yo'q"),
            CurriculumSeedData.LessonExampleSeed("Yes, I like apples.", "Yes", "apples", "Ha, men olmalarni yaxshi ko'raman.", "Ha")
        )
        DARS_45_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Please, give me water.", "Please", "water", "Iltimos, menga suv ber.", "Iltimos"),
            CurriculumSeedData.LessonExampleSeed("Thank you!", "Thank you", "", "Rahmat!", "Rahmat"),
            CurriculumSeedData.LessonExampleSeed("Thank you, mom!", "Thank you", "mom", "Rahmat, ona!", "Rahmat")
        )
        DARS_46_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I am sorry.", "I am sorry", "", "Kechirasiz.", "Kechirasiz"),
            CurriculumSeedData.LessonExampleSeed("Sorry!", "Sorry", "", "Kechirasiz!", "Kechirasiz"),
            CurriculumSeedData.LessonExampleSeed("I am sorry, mom.", "I am sorry", "mom", "Kechirasiz, ona.", "Kechirasiz")
        )
        DARS_47_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Goodbye!", "Goodbye", "", "Xayr!", "Xayr"),
            CurriculumSeedData.LessonExampleSeed("Goodbye, mom!", "Goodbye", "mom", "Xayr, ona!", "ona"),
            CurriculumSeedData.LessonExampleSeed("Goodbye, friend!", "Goodbye", "friend", "Xayr, do'st!", "do'st")
        )
        DARS_48_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("My pet is a cat.", "My pet is", "cat", "Mening uy hayvonim mushuk.", "mushuk"),
            CurriculumSeedData.LessonExampleSeed("My pet is a dog.", "My pet is", "dog", "Mening uy hayvonim it.", "it"),
            CurriculumSeedData.LessonExampleSeed("My pet is a fish.", "My pet is", "fish", "Mening uy hayvonim baliq.", "baliq")
        )
        DARS_49_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I love mom.", "I love", "mom", "Men onamni juda yaxshi ko'raman.", "onamni"),
            CurriculumSeedData.LessonExampleSeed("I love dad.", "I love", "dad", "Men dadamni juda yaxshi ko'raman.", "dadamni"),
            CurriculumSeedData.LessonExampleSeed("I love my family.", "I love", "family", "Men oilamni juda yaxshi ko'raman.", "oilamni")
        )
        DARS_50_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I am a boy.", "I am a", "boy", "Men bolaman.", "bolaman"),
            CurriculumSeedData.LessonExampleSeed("I am a girl.", "I am a", "girl", "Men qizman.", "qizman"),
            CurriculumSeedData.LessonExampleSeed("He is a boy.", "He is a", "boy", "U bola.", "bola")
        )
        DARS_51_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I am happy.", "I am", "happy", "Men baxtliman.", "baxtliman"),
            CurriculumSeedData.LessonExampleSeed("I am tired.", "I am", "tired", "Men charchadim.", "charchadim"),
            CurriculumSeedData.LessonExampleSeed("He is happy.", "He is", "happy", "U baxtli.", "baxtli")
        )
        DARS_52_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("I go to school.", "I go to", "school", "Men maktabga boraman.", "maktabga"),
            CurriculumSeedData.LessonExampleSeed("I go to the park.", "I go to", "park", "Men parkga boraman.", "parkga"),
            CurriculumSeedData.LessonExampleSeed("I go home.", "I go", "home", "Men uyga boraman.", "uyga")
        )
        DARS_53_SECTION_ID -> listOf(
            CurriculumSeedData.LessonExampleSeed("Hello! My name is Aziz.", "Hello", "Aziz", "Salom! Mening ismim Aziz.", "Aziz"),
            CurriculumSeedData.LessonExampleSeed("I am happy. I have a ball.", "happy", "ball", "Men baxtliman. Menda to'p bor.", "to'p"),
            CurriculumSeedData.LessonExampleSeed("I go to school. Goodbye!", "go to", "Goodbye", "Men maktabga boraman. Xayr!", "Xayr")
        )
        else -> null
    }
}
