# TalkMe Voice v2 — implementation specification

Implement the user's selected **B design without visible transcript containers** as the
new voice-only version in Mrxforte/Talk-me main. Preserve the previous main on the archive
branch before replacement. Preserve app ID com.aistudio.talkme.xkpqvr, increment versionCode,
and do not delete or reset existing on-device data files.

Use Kotlin, XML, ViewBinding, a single MainActivity hosting one ChatFragment through
Navigation Component, Hilt, immutable StateFlow, coroutines and pure domain interfaces.
No lessons, profile, dashboard, avatar runtime, composer keyboard or bottom navigation.

UI: charcoal background; ivory text; restrained mint accents; large procedural voice orb;
clear state labels; plain user and assistant text directly on the background. No cards,
bubbles, borders, shadows or tinted panels behind transcript text. Three floating controls:
transcript visibility toggle, central microphone, and end conversation. Header has TalkMe,
Jonli suhbat and a session timer. Never show API-key fields, credential dialogs or a settings control for keys.
All labels/colors/spacing are Android resources; hit targets are at least 48dp.

Use **open-source local Whisper via whisper.cpp**, replacing Vosk. Pin whisper.cpp v1.7.6
and ggml-base.en-q5_1.bin to verified SHA-256 values. Build the JNI runtime with Android NDK
and CMake for arm64-v8a and x86_64 with 16 KB page compatibility. Do not use a billed Whisper API.

Capture one 16 kHz mono PCM16 stream off the main thread. Use bounded adaptive energy
endpointing with pre-roll and trailing silence. Distinguish this detector from neural VAD.
Run Whisper on finalized utterances and display a transcription state; do not fabricate
streaming partial words. Empty/noise-only results must not generate Gemini calls. Serialize
native context access, use its abort callback for cancellation, and free the context on its
owner worker after pending operations complete.

Gemini receives only recognized text. Preserve recognition output and never silently
rewrite it with an LLM. Use concise conversational English, with Uzbek explanations when
requested. Keep repeated history bounded, a short fixed system instruction, and 160 maximum
output tokens. Silence, speech recognition and local TTS cause no provider usage charge.
Report actual usageMetadata, while labeling all dollar amounts as estimates.

Use an installed offline English Android TTS voice. Pause capture during decoding/AI/playback,
then automatically resume after playback. The mic button starts/mutes/resumes/interrupts;
this baseline does not promise automatic full-duplex voice interruption.

Handle runtime permission denial, device microphone privacy mute, model errors, no offline
voice, network loss, authentication/model errors and quota limits. Stop all audio and
requests on pause/end. Ignore late callbacks using session generations. Observe flows with
viewLifecycleOwner.repeatOnLifecycle and clear the binding in onDestroyView.

For a personal DEBUG build, read a developer credential from ignored local.properties or the developer environment. Do not ask the app user for credentials. Keep credential ownership inside the data adapter, outside presentation/domain APIs. Never commit real credentials or embed them in release builds. A missing configuration must produce a service-not-ready state before audio initialization or requests. A public shared-key deployment needs an authenticated rate-limited gateway; that server must be provisioned separately. STT/TTS stay local.

Verify the actual Android APK, unit tests, native JNI decoding/cancellation, transparent
layout rendering, resource references and Android lint. Do not claim physical-device,
AI API, microphone quality or Android speed tests that have not been performed. Document
missing host tools honestly; use actual Gradle build evidence when compile_applet is unavailable.

Deliver the updated main branch, a debug APK and a preview produced from the real layout.
