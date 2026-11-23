# TalkMe AI Classroom — Implementation Plan

## Verified repository baseline
- Single-Activity Android XML/ViewBinding and Fragment Navigation (see AGENTS.md).
- Preserve existing four-stage lesson UI and current user progress.
- Existing curriculum currently documents 400 lessons; 600 lessons is a **target**, not a completed migration.
- No unreviewed Compose migration.

## Product contract
- 600 planned lessons from A0 to C2: RULE, EXAMPLES, EXERCISES, WRAP_UP.
- AI tutor Lera explains with Uzbek voice in UZ mode; English phrases remain pronounced in English.
- EN mode uses English-only explanations and conversation, adjusted to learner level.
- Full-screen 3D tutor can shrink to picture-in-picture while reading/writing.
- Reading, writing, listening, speaking all happen within the same classroom flow.
- 200 planned role-play scenarios, independent entry point, multiple difficulty modes, and scenario-based resume.
- Preserve free text/audio learning. Premium AI conversations require entitlement and metered usage.
- Persist progress after each completed step, not only on app exit.

## Implementation sequence
1. Introduce pure domain content contracts and first complete A0 lesson. (Initial model and seed included in this branch.)
2. Audit current LessonDetailFragment and introduce a new XML Fragment screen with ViewBinding and resource-backed labels; connect via navigation.
3. Implement Room resume entity and migration; tests for restore after process death.
4. Integrate microphone permission, streaming STT, VAD, transcript, lifecycle cleanup and retry.
5. Integrate an authenticated AI gateway with per-session cost limits; never embed provider secrets in APK.
6. Evaluate Uzbek TTS against native-speaker audio before selecting a production voice; route English utterances to English TTS.
7. Integrate Android 3D avatar renderer, animation state machine, and phoneme/viseme lip sync; add fallback static avatar for low-end devices.
8. Implement guided/assisted/independent role-play, persona constraints and feedback; populate and audit all 200 scenarios.
9. Populate and pedagogically audit all 600 lessons; link grammar concepts, skills, and role plays.
10. Build, run Android unit/instrumentation tests, accessibility and performance testing on physical devices before merging.

## Guardrails
- Do not remove existing XML lesson screens before replacement has parity and regression tests.
- Keep AI generated utterances separate from canonical lesson content.
- UZ/EN mode switch affects explanation and feedback, not the language of target English exercises.
- Never mark a lesson completed without actual evaluation.
- Premium does not mean unlimited unmetered cloud inference.
- The starter file is a content/domain foundation only; it does **not** implement 3D, live speech, billing, persistence, or all 600 lessons.
