# Xarajatlar — 2026-11-23 holatidagi taxmin

Faraz: 100 foydalanuvchi, har biri kuniga 1 soat. Oy 30 kun, yil 365 kun.
Narxlar USD; soliq, hosting, ishlab chiqish va foydalanuvchi interneti hisobga kiritilmagan.
Bu hisob xizmatning qat'iy taklifi emas. Tariflar o'zgarsa, hisob ham o'zgaradi.

## Tavsiya: lokal STT + Gemini matn + lokal TTS

Gemini 2.5 Flash-Lite Standard: kirish $0.10 / 1 mln token,
chiqish $0.40 / 1 mln token. Faqat matn yuboriladi.

Bir foydalanuvchining 1 soatlik suhbatiga MISOL farazi:

- 60 ta javob uchun jami 100,000 kirish tokeni. Takroriy suhbat tarixi va system instruction ham shu jami ichida.
- Jami 6,000 chiqish tokeni, ya'ni o'rtacha 100 token/javob.
- Lokal inglizcha STT va o'rnatilgan oflayn TTS uchun providerga ishlatish to'lovi $0.

Hisob: 100,000 / 1,000,000 × $0.10 + 6,000 / 1,000,000 × $0.40 = **$0.0124 / foydalanuvchi-soat**.

| Miqyos | Kuniga | Oyiga, 30 kun | Yiliga, 365 kun |
| --- | ---: | ---: | ---: |
| 1 foydalanuvchi × 1 soat/kun | $0.0124 | $0.372 | $4.526 |
| 100 foydalanuvchi × 1 soat/kun | $1.24 | $37.20 | $452.60 |
| 1,000 foydalanuvchi × 1 soat/kun | $12.40 | $372.00 | $4,526.00 |

Rejalashtirish uchun $0.01–0.03 / foydalanuvchi-soat oralig'i ishlatilsa,
100 foydalanuvchi uchun $30–90/oy yoki $365–1,095/yil bo'ladi.
Bu o'lchangan natija emas: suhbatlar soni, haqiqiy tokenizatsiya va javoblar uzunligiga
bog'liq senariy. Kengroq xotira yoki uzun javoblar ko'proq xarajat qiladi.

Ilova usageMetadata dagi haqiqiy token sonlarini yig'adi. Hisoblagich shu tarif bilan
taxminiy dollar qiymatini ko'rsatadi; yakuniy hisob-kitob provayder billingida tekshiriladi.

## Gemini Live bilan solishtirish

Gemini 3.8 Live sahifasidagi yaxlitlangan audio tariflari: input taxminan $0.005/min,
output $0.018/min.

- 30 daqiqa kirish + 30 daqiqa AI ovozi: taxminan **$0.69**.
- 60 daqiqa uzatilgan mikrofon ovozi + 30 daqiqa AI ovozi: taxminan **$0.84**.

Bu FAQAT yangi audio uchun bazaviy hisob. **Suhbat tarixini qayta ishlash,
matnli transkriptlar va boshqa tokenlar kiritilmagan. Bu yakuniy soatlik narx yoki yuqori chegara emas.**
Google Live API har bir navbatda faol kontekstdagi avvalgi tokenlarni ham hisoblashini
ko'rsatadi. Shu sababli uzluksiz bir soatlik seans yuqoridagi bazadan ancha qimmat bo'lishi mumkin.

| Miqyos | Kuniga, yangi audio bazasi | Oyiga, 30 kun | Yiliga, 365 kun |
| --- | ---: | ---: | ---: |
| 1 foydalanuvchi | $0.69–0.84 | $20.70–25.20 | $251.85–306.60 |
| 100 foydalanuvchi | $69–84 | $2,070–2,520 | $25,185–30,660 |

Eslatma: 1 soat sessiya davomiyligi bir soat AI gapiradi degani emas. Input va output
davomiyligi alohida hisoblanadi. API token tariflari aniqlik uchun asosiy mezon;
yuqoridagi daqiqalik narxlar provayder bergan yaxlitlangan ko'rsatkichlardir.

## Sifat va xarajat o'rtasidagi farq

Tejamkor versiya: gapirish → qisqa kutish → AI javobi. Mikrofon har safar qo'lda
bosilmaydi; javobdan keyin o'zi tinglaydi. AI gapini tugma bilan bo'lish mumkin.
Tabiiy to'liq dupleks suhbat, avtomatik ovozli bo'lish va premium audio sifati bu
birinchi tejamkor versiyada kafolatlanmaydi.

## Rasmiy manbalar

- https://ai.google.dev/gemini-api/docs/pricing#gemini-2.5-flash-lite
- https://ai.google.dev/gemini-api/docs/pricing#gemini-3.8-live
- https://ai.google.dev/gemini-api/docs/live-api/best-practices#pricing-billing
- https://github.com/ggml-org/whisper.cpp
- https://huggingface.co/ggerganov/whisper.cpp
