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

## No API-key prompts

The application never asks end users for an API key. The credential field/modal and its
header button have been removed. Tap the microphone to begin; only Android's microphone
permission may be requested.

For a PERSONAL development build, the developer configures the connection once:

1. Copy `local.properties.example` to `local.properties` and retain your Android SDK path.
2. Set `GEMINI_API_KEY` there, or in the developer computer's environment.
3. Build the debug APK. `local.properties` and real credentials are not committed to GitHub.
4. Install on a 64-bit Android 8.0+ device with an offline English TTS voice.

No credential was supplied for this repository/session, so the default build reports that
the conversation service has not been configured. Removing a prompt does not create API
access. STT/TTS remain local; Gemini responses need a configured, valid account.

The key is embedded only in a personal DEBUG build and can be extracted from that APK.
Do not publicly distribute that build with an owner's paid key. Release builds deliberately
have an empty key; a public end-user release needs an authenticated API gateway or other
server-side credential setup. That server is not provisioned by this change.

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
session cost remain available in internal UI state for developer diagnostics. Prices and availability must be checked
before future releases. See [docs/COSTS.md](docs/COSTS.md).

## Upgrade and previous version

This replaces the previous learning product at the user's request. Application ID
`com.aistudio.talkme.xkpqvr` is preserved and versionCode is increased to 4. The rebuild
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

## 2.0.2 developer-configured connection

API-key entry, the settings/credential button, and credential arguments in the presentation
and domain APIs are removed. The data adapter owns the development credential supplied by
Hilt. If absent, the ViewModel reports a setup issue before loading audio models or issuing
requests. Errors direct connection problems to the developer rather than asking end users
to supply credentials. The microphone, transcript toggle, end control and B design remain.
