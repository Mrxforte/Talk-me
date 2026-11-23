# Voice v2 verification — 2026-11-24

## Completed

- `:app:assembleDebug :app:testDebugUnitTest :app:lintDebug`: **BUILD SUCCESSFUL**.
- **25 unit/render tests, 0 failures, 0 errors**.
- Lint: **0 errors**, 28 warnings about newer dependency versions, backup metadata and unused resources.
- The final development APK compiles Whisper and GGML math targets with `-O3`; generated Ninja flags were inspected. No Android speed benchmark is claimed.
- Hilt generation, single-destination navigation resources, native CMake compilation and APK packaging succeeded.
- arm64-v8a and x86_64 libraries are genuine Android 26 ELF binaries built by NDK r27c.
- Every packaged native ELF LOAD segment has 16,384-byte alignment; no 16 KB library warnings.
- Packaged Whisper model SHA-256 matches the pinned original artifact. No Vosk/JNA library or model is packaged.
- APK signature verified with Android build-tools `apksigner verify`; its development certificate matches the previous TalkMe debug build.
- Android layout rendered with Robolectric native graphics at 393×852dp. The transcript ScrollView and its content
  have null backgrounds. Controls meet 48dp targets and have content descriptions. This is a view render,
  not a physical-device screenshot or an end-to-end app launch.
- Endpoint tests cover long silence, click rejection, pre-roll/normalization and bounded long utterances.
- Conversation tests cover bounded repeated context, text preservation, quota errors, cancel/mute races and late callbacks.

## Actual Whisper audio and JNI checks

The pinned base.en Q5_1 model decoded the official whisper.cpp `samples/jfk.wav` using the
project's exact JNI C++ source, built as a desktop shared library. Recognition contained the expected
English words. A stale epoch returned an empty result, and changing the epoch during a running
30-second-buffer decode aborted it and suppressed the result. These assertions passed and the context was released.
The decoder emits a failed-encode diagnostic when intentionally aborted; that is expected in this cancellation test.

This validates model loading and the JNI/cancellation logic on desktop. It does not establish
Android phone speed, microphone recognition quality, battery use or noise robustness.

## Host tool limitation

The host does not expose `compile_applet`. The actual Android Gradle build above was used;
no applet compilation or successful applet preview is claimed.

## Remaining device/API verification

- Physical Android microphone, permission/privacy dialogs, audio routing and offline voice availability.
- Actual Android execution of JNI recognition and performance on the user's phone.
- Real Gemini authentication/model access, response latency, token usage and end-to-end paid API calls.
- Long sessions, background/restart behavior on OEM devices, accents, quiet/noisy audio and high font-scale/landscape layouts.

No Gemini API key was supplied and no paid inference request was made. The API tests use a local mock server.

## Debug artifact

Application ID: `com.aistudio.talkme.xkpqvr`; versionCode 4; versionName `2.0.2-whisper`.
APK bytes: 69235272.
SHA-256: `fcbf8dac7a5abd8e97b8910a445cb72400c10e11f11aac1d38fce3ec0d699aec`.

Previous main is preserved at `archive/talkme-before-voice-v2-2026-11-24`.

## API diagnostic regression checks in 2.0.1

Mock HTTP 404 remains a model failure instead of authentication. Google error-info invalid-key
reasons, project/service restrictions, billing prerequisites, bad request payloads and temporary
server failures are distinguished. Raw provider messages and arbitrary secret-like status/reason
values are excluded from display. Diagnostics survive failure and clear at the next session.
These tests do not diagnose or validate the user's actual API account or key.

## 2.0.2: no credential-entry flow

The view render asserts there is no editable entry field. The header settings/key button
and credential modal are removed. Unit tests show that an unconfigured data adapter makes
no HTTP request and that the ViewModel performs no audio preparation and reports SETUP.
Presentation/domain APIs no longer pass API keys. The debug build reads developer-local
configuration; release builds have an empty key by default. No provider connection or
public gateway is provisioned in this change.

Developer credential present in this verification build: **false**. No secret value is recorded.
