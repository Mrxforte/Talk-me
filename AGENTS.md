# TalkMe Android — Voice v2

The user authorized replacing the previous learning application with one economical
voice-conversation screen. The previous product remains on the archive branch linked in README.

## Stack and architecture

- Kotlin, XML Views, ViewBinding. Do not introduce Compose without a user request.
- One MainActivity hosts ChatFragment through `res/navigation/nav_graph.xml`.
- Use Hilt for dependency injection, ViewModel/StateFlow for state, and pure Kotlin domain contracts.
- ViewModels must not retain Activities, Fragments, Views, bindings or short-lived Contexts.
- Learner-facing strings are natural Uzbek Latin; technical code/comments are English.
- Strings, colors and UI dimensions belong in Android resources. Touch targets are at least 48dp.

## Audio and lifecycle

- Local whisper.cpp is the speech recognizer. Do not silently restore Vosk or paid STT.
- Whisper is utterance based, not a native incremental streaming decoder. Do not fabricate partial transcripts.
- Endpoint detection is adaptive energy based; do not advertise it as neural VAD.
- Stop microphone, requests and playback on pause/end; cancel native decoding using its epoch callback.
- Release the Whisper context on its single owner worker after pending calls finish.
- Fragment binding must be nulled in onDestroyView; flows use viewLifecycleOwner.repeatOnLifecycle(STARTED).

## Product constraints

- Keep one conversation screen. No lessons/profile/dashboard/bottom navigation.
- Design B: procedural mint orb, transparent transcripts directly on the background, floating controls.
- No card/bubble fill, border, shadow or tinted panel behind transcript text.
- Microphone button starts, mutes/resumes, and interrupts the current reply. End is a separate control.
- Keep credentials out of code, logs, backups and saved state. The app must never ask the end user for a key. Personal debug builds read a developer key from ignored local.properties or the developer environment; release builds never embed it.
- Bound repeated text context and answer length; no paid silence streaming. Do not claim a guaranteed hourly cost.

## Data and history

- Preserve application ID `com.aistudio.talkme.xkpqvr` and increment versionCode for releases.
- Do not delete or reset existing database or preference files. The voice-only app does not open legacy Room databases.
- Record intentional product removals in `docs/engineering/REMOVAL_LOG.md` and architecture changes in AUDIT_REPORT.
- Never rewrite main history or force-push an unrelated branch head. Preserve the prior version before replacement.

## Verification

- Run `:app:assembleDebug`, `:app:testDebugUnitTest`, `:app:lintDebug` and inspect results.
- Run compile_applet when exposed by the host; if unavailable, record that limit and the actual Gradle build evidence.
- Review `docs/engineering/DEFINITION_OF_DONE.md`.
- Distinguish compile/unit/render/recorded-audio evidence from physical Android microphone and paid Gemini tests.
