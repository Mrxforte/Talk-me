# TalkMe Android — Repository Instructions for AI Agents & Engineers

Welcome to the TalkMe Android project. All engineers, AI coding agents, and automated workflows operating in this repository must adhere strictly to these rules.

---

## 1. Core Architecture & Stack Rules
- **UI Framework:** Android XML Views with ViewBinding.
- **Single Activity Architecture:** `MainActivity.kt` is the sole application Activity host. Feature destinations are Jetpack Fragments managed by `res/navigation/nav_graph.xml`.
- **Do Not Migrate to Jetpack Compose:** Do NOT convert existing XML screens to Jetpack Compose unless explicitly instructed by the user.
- **Clean Architecture Boundaries:**
  - `presentation`: Activities, Fragments, Adapters, ViewModels, and UI state models (`UiState`).
  - `domain`: Pure Kotlin business logic, domain models, and repository contracts. Zero dependencies on UI or Data/Room/Retrofit.
  - `data`: Repository implementations, local Room database, DAOs, and network sources.
- **Dependency Injection:** Hilt (`@AndroidEntryPoint`, `@HiltViewModel`, `@Inject`). Do not introduce secondary DI frameworks.

---

## 2. Memory & Lifecycle Invariants
- **ViewBinding Cleanup:** Every Fragment **MUST** override `onDestroyView()` and set `_binding = null`.
- **Flow Observation:** In Fragments, observe Flows strictly inside `viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)`.
- **Audio Cleanup:** Audio playback and TTS synthesis must stop on Fragment `onPause()` and ViewModel `onCleared()`.
- **ViewModels:** ViewModels must never hold references to an `Activity`, `Fragment`, `View`, `ViewBinding`, or short-lived `Context`.

---

## 3. UI Resources & Styling Rules
- **Strings:** All user-visible labels, errors, and messages must reside in `res/values/strings.xml`. Use formatted string arguments (`%1$s`, `%1$d`).
- **Colors:** Use semantic color tokens in `res/values/colors.xml` (`color_cosmic_background`, `color_cosmic_card`, `color_neon_cyan`, etc.). Never write raw hex color literals in layout XML or Kotlin code.
- **Dimensions:** Spacing, radii, and typography must use `@dimen/...` tokens. Touch targets must be at least **48dp x 48dp**.
- **Learner vs Code Language:** Technical code, architecture, and comments are written in professional English. Learner-facing explanations and instructions are written in natural, accurate Uzbek (Latin script).

---

## 4. Curriculum & Pedagogical Standards
- **A0–C2 Curriculum:** The app contains 400 lessons across 20 curriculum batches (`CurriculumSeedData` through `CurriculumPartiya18To20SeedData`).
- **4-Stage Universal Lesson Layout:**
  1. **Qoida:** Grammatical rule, Uzbek explanation, formula, contrast, interactive visual showcase.
  2. **Misollar:** 3 highlighted sentences with TTS audio and Uzbek translation.
  3. **Mashqlar:** 3 interactive fill-in-the-blank questions with live feedback.
  4. **Wrap-up:** Summary takeaways, core sentence model, and golden completion star.
- **Data Preservation:** Never delete or reset existing user learning progress, streak days, or database records.

---

## 5. Performance & Dead Code Removal
- **RecyclerViews:** All adapters must extend `ListAdapter` with a dedicated `DiffUtil.ItemCallback`. Never use unconstrained `notifyDataSetChanged()`.
- **Dead Code Safety:** Verify AST references, navigation graph, and manifest before deleting code. Record any removals in `docs/engineering/REMOVAL_LOG.md`.
- **Evidence-Based Claims:** Never claim performance improvements without reproducible benchmark or test evidence.

---

## 6. Mandatory Quality Verification Gates
Before finishing any turn:
1. Run `compile_applet` — build must succeed.
2. Run `gradle :app:testDebugUnitTest` — all unit & Robolectric tests must pass (`BUILD SUCCESSFUL`).
3. Check `docs/engineering/DEFINITION_OF_DONE.md` compliance.
