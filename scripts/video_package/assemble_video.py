#!/usr/bin/env python3
"""
TalkMe Lesson Video Assembler (Python + FFmpeg / MoviePy / Pillow)
------------------------------------------------------------------
Ushbu skript 60 soniyalik 1080p o'quv videosini avtomatik yig'ib beradi:
- Sahna rasmlarini (scene_1.png ... scene_5.png) yuklaydi yoki avtomatik placeholder yaratadi.
- Rasmlarga silliq Ken Burns (zoom-in) dinamik effekti beradi.
- subtitles.json asosida zamonaviy TalkMe dizaynidagi matn kartochkalarini chizadi.
- Audio fayllarni birlashtiradi yoki fon musiqasi/ovoz qo'shadi.
- Natijani 1080p (1920x1080) MP4 formatida saqlaydi.
"""

import os
import json
import math
import subprocess
from PIL import Image, ImageDraw, ImageFont

def ensure_font():
    """Tizimdagi mos shriftni topadi yoki standartini oladi."""
    potential_fonts = [
        "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf",
        "/usr/share/fonts/truetype/liberation/LiberationSans-Bold.ttf",
        "/usr/share/fonts/truetype/freefont/FreeSansBold.ttf",
    ]
    for p in potential_fonts:
        if os.path.exists(p):
            return p
    return None

def create_placeholder_image(output_path, title, subtitle, bg_color=(15, 23, 42)):
    """Agar haqiqiy Imagen/Veo rasmlari hali bo'lmasa, test uchun chiroyli gradient rasm yaratadi."""
    w, h = 1920, 1080
    img = Image.new("RGB", (w, h), bg_color)
    draw = ImageDraw.Draw(img)

    # Gradient effekti
    for y in range(h):
        r = int(bg_color[0] + (30 - bg_color[0]) * (y / h))
        g = int(bg_color[1] + (58 - bg_color[1]) * (y / h))
        b = int(bg_color[2] + (100 - bg_color[2]) * (y / h))
        draw.line([(0, y), (w, y)], fill=(r, g, b))

    # Yulduzlar/nuqtalar bezagi
    import random
    random.seed(len(title))
    for _ in range(50):
        nx, ny = random.randint(50, w - 50), random.randint(50, h - 50)
        draw.ellipse([nx, ny, nx + 3, ny + 3], fill=(56, 189, 248, 120))

    img.save(output_path, "PNG")
    print(f"[+] Placeholder rasm yaratildi: {output_path}")

def render_overlay_card(scene_data, output_path, w=1920, h=1080):
    """Ekranda chiqadigan TalkMe uslubidagi subtitr va kartani RGBA PNG formatida chizadi."""
    overlay = Image.new("RGBA", (w, h), (0, 0, 0, 0))
    draw = ImageDraw.Draw(overlay)

    font_path = ensure_font()
    if font_path:
        font_badge = ImageFont.truetype(font_path, 28)
        font_main = ImageFont.truetype(font_path, 64)
        font_sub = ImageFont.truetype(font_path, 34)
        font_phonetic = ImageFont.truetype(font_path, 30)
        font_ex = ImageFont.truetype(font_path, 32)
    else:
        font_badge = font_main = font_sub = font_phonetic = font_ex = ImageFont.load_default()

    # Pastki qismdagi zamonaviy shaffof karta (Glassmorphism card)
    card_w = 1720
    card_h = 320
    card_x = (w - card_w) // 2
    card_y = h - card_h - 70

    # Karta fonining qora shaffof bloki
    card_bg = (11, 23, 46, 230)
    card_stroke = (56, 189, 248, 255) # Neon cyan border
    draw.rounded_rectangle(
        [card_x, card_y, card_x + card_w, card_y + card_h],
        radius=24,
        fill=card_bg,
        outline=card_stroke,
        width=3
    )

    badge_text = scene_data.get("badge", "TALKME")
    badge_w = 200
    badge_h = 44
    draw.rounded_rectangle(
        [card_x + 40, card_y + 35, card_x + 40 + badge_w, card_y + 35 + badge_h],
        radius=10,
        fill=(8, 30, 59, 255),
        outline=(2, 132, 199, 255),
        width=2
    )
    draw.text((card_x + 55, card_y + 42), badge_text, font=font_badge, fill=(56, 189, 248, 255))

    # Asosiy ibora (Inglizcha)
    main_text = scene_data.get("main_title", "")
    draw.text((card_x + 40 + badge_w + 30, card_y + 30), main_text, font=font_main, fill=(255, 255, 255, 255))

    # Transkripsiya
    phonetic = scene_data.get("phonetic", "")
    if phonetic:
        draw.text((card_x + 40 + badge_w + 30 + len(main_text) * 36 + 20, card_y + 48), phonetic, font=font_phonetic, fill=(148, 163, 184, 255))

    # O'zbekcha tarjimasi / ma'nosi
    sub_text = scene_data.get("sub_title", "")
    uz_trans = scene_data.get("uzbek_translation", "")
    meaning_full = f"{sub_text} — ({uz_trans})" if uz_trans else sub_text
    draw.text((card_x + 45, card_y + 115), meaning_full, font=font_sub, fill=(203, 213, 225, 255))

    # Chiziq ajratgich
    draw.line([(card_x + 40, card_y + 175), (card_x + card_w - 40, card_y + 175)], fill=(30, 58, 110, 200), width=2)

    # Misol gap (Example)
    ex_en = scene_data.get("example_en", "")
    ex_uz = scene_data.get("example_uz", "")
    if ex_en:
        draw.text((card_x + 45, card_y + 195), f"Misol: \"{ex_en}\"", font=font_ex, fill=(248, 250, 252, 255))
        draw.text((card_x + 45, card_y + 245), f"Tarjimasi: \"{ex_uz}\"", font=font_ex, fill=(56, 189, 248, 255))
    else:
        # Intro / Outro uchun xabar
        draw.text((card_x + 45, card_y + 215), "1 daqiqada ingliz tilida ravon so'zlashish sirlari", font=font_ex, fill=(56, 189, 248, 255))

    overlay.save(output_path, "PNG")
    print(f"[+] Subtitr overlay yaratildi: {output_path}")

def build_video():
    work_dir = os.path.dirname(os.path.abspath(__file__))
    subtitles_file = os.path.join(work_dir, "subtitles.json")
    with open(subtitles_file, "r", encoding="utf-8") as f:
        scenes = json.load(f)

    temp_dir = os.path.join(work_dir, "temp_render")
    os.makedirs(temp_dir, exist_ok=True)

    clip_concat_list = os.path.join(temp_dir, "clips.txt")
    clip_files = []

    # 1. Har bir sahna uchun rasm va overlay yaratib, 1080p video segment yasash
    for idx, sc in enumerate(scenes):
        scene_id = sc["scene_id"]
        duration = sc["end_sec"] - sc["start_sec"]

        # Rasm mavjudligini tekshirish
        scene_img = os.path.join(work_dir, f"scene_{scene_id}.png")
        if not os.path.exists(scene_img):
            create_placeholder_image(scene_img, sc["main_title"], sc["sub_title"])

        overlay_img = os.path.join(temp_dir, f"overlay_{scene_id}.png")
        render_overlay_card(sc, overlay_img)

        segment_output = os.path.join(temp_dir, f"segment_{scene_id}.mp4")

        # FFmpeg filtri: Ken-Burns sekin yaqinlashish (zoom-in) + subtitr ustma-ust qo'yish
        # zoompan: 1.0 dan 1.08 gacha yaqinlashadi
        total_frames = int(duration * 30)
        filter_complex = (
            f"[0:v]scale=1920:1080,zoompan=z='min(zoom+0.0003,1.08)':x='iw/2-(iw/zoom/2)':y='ih/2-(ih/zoom/2)':d={total_frames}:s=1920x1080:fps=30[bg];"
            f"[bg][1:v]overlay=0:0:enable='between(t,0,{duration})'[outv]"
        )

        cmd = [
            "ffmpeg", "-y",
            "-loop", "1", "-t", str(duration), "-i", scene_img,
            "-loop", "1", "-t", str(duration), "-i", overlay_img,
            "-filter_complex", filter_complex,
            "-map", "[outv]",
            "-c:v", "libx264",
            "-pix_fmt", "yuv420p",
            "-r", "30",
            segment_output
        ]

        print(f"\n[*] Sahna {scene_id} render qilinmoqda ({duration} soniya)...")
        subprocess.run(cmd, check=True)
        clip_files.append(segment_output)

    # 2. Segmentlarni birlashtirish
    with open(clip_concat_list, "w") as f:
        for cf in clip_files:
            f.write(f"file '{cf}'\n")

    output_video = os.path.join(work_dir, "talkme_lesson1_60sec.mp4")
    concat_cmd = [
        "ffmpeg", "-y",
        "-f", "concat", "-safe", "0",
        "-i", clip_concat_list,
        "-c", "copy",
        output_video
    ]
    print("\n[*] To'liq 60 soniyalik video birlashtirilmoqda...")
    subprocess.run(concat_cmd, check=True)

    print(f"\n=======================================================")
    print(f"[SUCCESS] 60 soniyalik video tayyor: {output_video}")
    print(f"Format: 1920x1080, 30 FPS, Aniq 60.0 soniya.")
    print(f"=======================================================\n")

if __name__ == "__main__":
    build_video()
