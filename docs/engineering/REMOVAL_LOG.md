# Intentional voice-product replacement

This change supersedes the old product at the user's explicit request. These are product
scope removals, not claims that the prior features were unused or nonfunctional.

The complete previous tree and history are retained on:
https://github.com/Mrxforte/Talk-me/tree/archive/talkme-before-voice-v2-2026-11-24

## Replaced areas

- Previous multi-feature app/src implementation, resources and tests: replaced by a single voice destination.
- Lessons, curriculum roadmap, profile, gamification and dictionary UI: omitted from the requested voice-only product.
- Unity/Blender/avatar renderer sources and artwork: omitted after selection of the procedural-orb B design.
- Vosk/JNA runtime and model pipeline: replaced by locally compiled whisper.cpp and its pinned model.
- Old mixed build/plugin setup: replaced by minimal XML/navigation/Hilt/Whisper build configuration.
- IDE metadata and unrelated web/public assets: no longer part of the active Android product tree.

## Reference and data checks

The active Android manifest references only TalkMeApplication and MainActivity. The only
navigation destination is ChatFragment. Hilt binds the three domain ports to the active
implementations. No old classes/resources are required by the new app. Whole-project
compile, tests and lint check the replacement's references and navigation resources.

Legacy on-device database/preferences files are not cleared or migrated destructively.
Git replacement affects source history, not the device's application data directory.

## 2.0.2 credential UI removal

Remove ChatFragment's key-entry dialog, editable credential field and header settings button. Remove presentation-owned key storage and key arguments from TutorClient.reply. Replace these with developer debug configuration in the data adapter. No credential is written to Git. Tests check that the screen contains no editable entry field and that missing configuration performs no audio preparation or HTTP request.
