# TalkMe Android Dead Code & Obsolete Asset Removal Log

**Date:** 2026-11-12  
**Policy:** Zero-Risk Safe Removal. No deletion performed without verifying AST references, Android manifest, Navigation component XML, Room database entities, and Hilt injection bindings.

---

## 1. Removed Code Artifacts

### Artifact 1: `TalkMeCurriculumNavGraph.kt`
- **Location:** `app/src/main/java/com/example/feature/lessons/TalkMeCurriculumNavGraph.kt`
- **Size:** 854 lines (36,844 bytes)
- **Why Considered Unused:**
  - This file contained an exploratory Jetpack Compose navigation graph (`TalkMeCurriculumNavGraph`) generated in an early prototype turn.
  - The TalkMe application is strictly an Android XML Single-Activity architecture hosted in `MainActivity.kt` with Navigation Component defined in `res/navigation/nav_graph.xml`.
  - The function was never invoked in `MainActivity`, any Fragment, or any test.
- **Checks Performed:**
  1. `grep -rnI "TalkMeCurriculumNavGraph" app/src` confirmed zero usages outside its own declaration line.
  2. Verified `res/navigation/nav_graph.xml` links directly to `LessonsFragment` (`com.example.feature.lessons.LessonsFragment`), `LessonDetailFragment`, and other XML Fragments.
  3. Verified unit tests (`ExampleRobolectricTest`, `ExampleUnitTest`) do not reference this file.
- **Action Taken:** File permanently deleted.
- **Replacement:** The production XML-based navigation graph (`app/src/main/res/navigation/nav_graph.xml`) and production Fragment implementations (`LessonsFragment.kt`, `TopicDetailFragment`, and `LessonDetailFragment`).

---

## 2. Refactored Legacy Patterns (Clean Architecture Inversion)

### Artifact 2: Domain-to-Core Coupling on `VadState`
- **Location:** `app/src/main/java/com/example/domain/repository/SpeechRepository.kt:3`
- **Problem:** `SpeechRepository` imported `com.example.core.speech.VadState`. This inverted Clean Architecture dependencies (Domain depending on Core/Implementation).
- **Checks Performed:**
  1. Verified all usages of `VadState` across `SpeechRepositoryImpl.kt`, `ConversationCoordinator.kt`, and `SileroVadDetector.kt`.
- **Action Taken:**
  1. Moved `enum class VadState` to `com.example.domain.model.DomainModels.kt`.
  2. Updated `SpeechRepository.kt` to import `com.example.domain.model.VadState`.
  3. Added `typealias VadState = com.example.domain.model.VadState` in `SileroVadDetector.kt` to preserve complete binary and source backward compatibility.
- **Replacement:** Pure Domain Model `com.example.domain.model.VadState`.

---

## 3. Retained Candidates (Deliberately NOT Deleted)

The following candidates were audited and intentionally retained:
1. **All 20 Curriculum Partiya Seed Files (`CurriculumPartiya2SeedData.kt` through `CurriculumPartiya18To20SeedData.kt`)**:
   - *Reason*: These files contain the full 400 lessons across A0 through C2. They are aggregated into `CurriculumSeedData.kt` via `buildSections()`, `buildVocabulary()`, `buildGrammarRules()`, and `buildExercises()`. Deleting any partiya would destroy user curriculum content.
2. **`SpeakingComposeComponents.kt`**:
   - *Reason*: While the application is XML-first, `SpeakingAiFragment` embeds a focused `ComposeView` (`binding.composePermissionGate` and `binding.composeAuxiliaryControls`) using `MicrophonePermissionGate` to handle Android 14+ runtime microphone permissions and auxiliary controls cleanly. Retained for active runtime voice interaction.
3. **Database Entities and Dao Methods**:
   - *Reason*: Retained to protect existing user progress, streak days, memorized word statistics, and offline review logs.


## 2026-11-13 — User-requested curriculum replacement

Removed all rejected lesson, vocabulary, grammar and exercise seeds, including
CurriculumPartiya*SeedData files. Kept XML screens, navigation and lesson engine.
Seven empty level containers replace the curriculum; C1 and C2 are independent.
Removed CEFR filter chips, fake completion rows and hardcoded lesson totals.
The transactional first-launch reset clears retired curriculum content and its
mistakes on existing installs; profile, streak and activity/session history remain.
The A0 container marks initialization, so later authored lessons survive relaunch.
Empty levels do not complete; only finishing every lesson unlocks the next level.
Removed seed-generated sample mistakes and fabricated daily-task history.

Verification: source references and navigation/manifest inspected; XML parsing and
whitespace checks run. Full compilation and Gradle tests are unavailable in this
workspace (no compile_applet or Gradle executable, and no Gradle wrapper in repo).
This change requires Android build and device validation before merge.
