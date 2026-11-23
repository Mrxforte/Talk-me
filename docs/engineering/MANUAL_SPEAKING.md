# Speaking: tap to record, tap to send

The Speaking microphone now starts a manual audio recording on the first tap.
The next tap finalizes the WAV and submits that turn to the existing Gemini
pipeline. Previously the Fragment's second tap called `stopConversation()`,
discarding the recorded turn instead of submitting it.

Opening/resuming Speaking prepares its lesson context but does not start audio
capture. Microphone permission is requested after the learner taps Record. A
record request made during lesson preparation is queued until preparation ends;
leaving the screen cancels it.

`SpeechRepository.startManualListening()` records the complete PCM turn. VAD can
update listening indicators, but pauses do not finalize or submit a manual turn.
Measured voice energy also recognizes usable recordings when VAD misses speech;
silent recordings still do not create invented transcripts. The existing 25-second
capture limit closes the recording and retains its result until the learner taps
Send. Processing disables repeated submissions. Tutor playback can be interrupted
to record another turn. Explicit recording clears the old playback echo hint so
the learner can intentionally repeat the tutor's sentence.

Successful tutor playback returns to idle. It does not automatically reopen the
microphone. The separate automatic `startConversation()` API remains available
and its existing automatic-conversation tests call that API explicitly.

Stop, pause, and screen exit discard pending capture and cancel network/playback
work. A send queued before cancellation cannot restore a stale finalizing state.
An existing live-capture regression was corrected to assert that the recorder
is released before tutor playback and successfully reopened on a later turn.

## Validation

Run `gradle :app:testDebugUnitTest :app:assembleDebug` with the repository's SDK
and JDK settings. Regression coverage includes manual first/second tap behavior,
duplicate Send taps, cancellation before submission, VAD silence without automatic
submission, and recorder cleanup. Physical microphone input and live Gemini
responses still require a device and the existing API/Firebase configuration.

On 2026-11-23, a clean Gradle 9.3.1 / JDK 25 / Android SDK 36.1 build completed
successfully: **68 tests, 0 failures, 0 errors, 0 skipped**, and a debug APK was
generated. The environment does not provide `compile_applet`; standard Android
Gradle compilation and resource linking were used instead.

This change contains no new avatar assets or 3D rendering claims.
