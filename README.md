# TalkMe — Voice v2

One Android screen for economical English conversation, using the selected **B design
with no transcript containers**: a procedural mint orb, plain text on charcoal, and
floating microphone/transcript/end controls.

## Voice pipeline

**16 kHz microphone → local endpoint detection → open-source whisper.cpp → Gemini text → offline Android TTS.**

Speech recognition uses the English `base.en-q5_1` model (59,721,011 bytes, about 57 MiB).
It runs on the phone, with no STT provider fee or uploaded recording. The model and pinned
whisper.cpp v1.7.6 source are downloaded and SHA-256 verified at BUILD time, then bundled/compiled.
There is no Vosk/JNA dependency and no OpenAI Whisper API call.

Whisper processes short utterances after a pause. It is not an incremental streaming
word recognizer. The screen shows a distinct transcription state rather than invented
partial words. Endpointing is adaptive energy based: 300 ms pre-roll, 800 ms trailing
silence, minimum 250 ms voiced content, and at most 20 seconds per captured utterance.
The decoder uses up to four CPU threads, greedy English decoding, and cancellation epochs.

Microphone capture pauses while transcribing, waiting for AI and speaking. Listening
resumes automatically. Tap the mic to interrupt, mute or resume. This is an economical
turn-based conversation, not automatic full-duplex voice interruption.

## Install and test

1. Install the debug APK on a **64-bit Android 8.0+** device (arm64-v8a, or x86_64 emulator).
2. Ensure the phone has an installed **offline English TTS voice**.
3. Tap the central microphone, enter your own Gemini key inside the app, and allow microphone access.
4. Speak English and pause briefly. Whisper decodes locally, then Gemini replies.

The key is memory-only and is never compiled into the APK or committed. The settings
icon opens a small API-key modal. Transcript visibility toggles on the same screen.
All microphone, decode, HTTP and playback work stops on pause/end.

Public distribution using a shared owner key requires an authenticated, rate-limited
API gateway. Do not put a shared paid API key in an APK. Local STT/TTS can remain unchanged.

## Android Studio / CLI

Open the repository root. Use AGP 9.1.1, JDK 21+, compile SDK 36.1, NDK 27.2.12479018 and
CMake 3.22.1. Android Studio can install missing SDK components. The first build needs
internet for these tools, dependencies, the 57 MiB model and the pinned native source.

```bash
./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
```

Windows: use `.\gradlew.bat` with the same tasks.
APK: `app/build/outputs/apk/debug/app-debug.apk`.
Unit/render tests: `app/build/reports/tests/testDebugUnitTest/index.html`.
GitHub Actions uploads a debug APK artifact after successful checks.

The default text model is `gemini-2.5-flash-lite`, with a 160-output-token limit and
approximately 6,000-character bounded repeated history. Token usage and an estimated
session cost are visible in the settings modal. Prices and availability must be checked
before future releases. See [docs/COSTS.md](docs/COSTS.md).

## Upgrade and previous version

This replaces the previous learning product at the user's request. Application ID
`com.aistudio.talkme.xkpqvr` is preserved and versionCode is increased to 3. The rebuild
never deletes or resets existing on-device lesson databases/preferences; the voice-only
app simply does not expose the previous lesson screens.

The full previous product is retained at:
https://github.com/Mrxforte/Talk-me/tree/archive/talkme-before-voice-v2-2026-11-24

Installing as an update requires the same signing certificate. If a local `debug.keystore`
is provided, the previous development signing configuration is reused. Fresh clones/CI
use their own default debug key. Release signing continues to use the existing environment variables.

## Verification and limitations

Read [docs/VALIDATION.md](docs/VALIDATION.md). Build/unit/native/render evidence must be
kept separate from physical Android microphone checks and real paid Gemini requests.
Phone speed, temperature, battery life, noise and accent accuracy remain device dependent.
The baseline recognizer and selected offline TTS voice are English only.

Architecture: one MainActivity → one ChatFragment/NavHost, XML/ViewBinding,
Hilt, ViewModel/StateFlow and pure Kotlin domain ports. No lessons, profile, dashboard,
avatars, keyboard composer or bottom navigation.

Detailed specification: [docs/MASTER_PROMPT.md](docs/MASTER_PROMPT.md).
Licenses: [docs/THIRD_PARTY.md](docs/THIRD_PARTY.md).

## 2.0.1 API diagnostics

The settings modal displays the actual selected model. The text model remains
`gemini-2.5-flash-lite`; this patch does not silently move users to a higher-cost model.
Google's deprecation documentation checked on 2026-11-24 lists Flash-Lite as still available.
Actual model access remains dependent on the user's own project and key.

HTTP 404/model-not-found, 401/invalid key, 403/project permission, 402/billing, 429/quota,
400/request or prerequisites, and 5xx/service failures now have separate messages. Only
allowlisted provider status/reason codes are displayed; raw server messages and keys are
never included. An old screenshot containing the generic message is insufficient to
identify the user's exact account problem. Reproduce with this build and report only the
HTTP/status code, never the API key.
