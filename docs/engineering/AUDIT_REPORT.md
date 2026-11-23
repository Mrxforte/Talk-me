# Voice v2 architecture audit

## Authorized product change

The user requested a fresh single-screen voice application, selected design B, removed
transcript containers, requested open-source Whisper in place of Vosk, and authorized
publishing the new version to the existing Talk-me main branch.

Previous main: `75fc6bd68b9362523c00a5a66351fa71bce240f9`.
Archive: `archive/talkme-before-voice-v2-2026-11-24`.

## Resulting architecture

MainActivity → Navigation host → ChatFragment → Hilt ChatViewModel.
Pure Kotlin SpeechInput/SpeechOutput/TutorClient ports separate the UI from Android/network code.
SpeechInput: AudioRecord PCM16/16kHz → adaptive energy endpoint detector → whisper.cpp JNI.
TutorClient: bounded transcript context → Gemini text generateContent request.
SpeechOutput: installed offline English Android TTS voice.

One native Whisper context lives on one worker. Cancellation changes an atomic native
epoch, checked by whisper.cpp's abort callback. Release is queued on the context owner.
The recorder is released before native decoding. No simultaneous AI voice capture occurs.

The recognizer uses `base.en-q5_1` and returns a final utterance after endpointing and
decoding. It does not claim streaming partial words. The endpoint detector is energy based,
not Silero VAD; noise/accents and low-volume speech require physical-device evaluation.

## Data and upgrade identity

Application ID remains `com.aistudio.talkme.xkpqvr`; versionCode increases from 1 to 2.
The rebuild does not issue DELETE SQL, clear preferences, use destructive Room migration,
or delete legacy databases. The old lesson UI is intentionally absent from this voice-only version.
Upgrade installation requires a matching signing certificate; the supplied local development
key is reused when present. CI/fresh clones use their own standard debug certificate.

## UI

Native procedural orb, resource-based vector controls and transparent text.
Transcript visibility can be toggled without navigation. API settings use a small modal.
No avatar renderer, Unity runtime, lessons, login or bottom navigation is included.

Validation evidence and remaining gaps are recorded in `docs/VALIDATION.md`.

## 2.0.1: actionable API failures

Increment versionCode to 3. Previously HTTP 400/401/403/404 were all mapped to AUTH,
which hid invalid request, project permission and missing-model failures. The client now
classifies provider status/error-info metadata into distinct issues and propagates a safe
ApiDiagnostic through the ViewModel. UI strings describe the issue and display HTTP/status
codes; the model is visible in the key-entry modal. Only public allowlisted codes survive,
never raw error messages or credentials. New tests cover error distinctions and diagnostic
cleanup between sessions. The user's real key/account is not available to the test suite.
