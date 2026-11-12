# TalkMe Android Engineering Rules & Architectural Guidelines

**Version:** 1.0  
**Scope:** All contributors, subagents, and automated tooling working on the TalkMe Android repository.

---

## 1. UI & Single-Activity Architectural Invariants

1. **Single Activity Host**:
   - `MainActivity.kt` is the sole application host Activity.
   - Never create additional Activities for feature screens (e.g. `LessonActivity`, `ProfileActivity`, `TasksActivity`).
   - All feature destinations must be implemented as Android Jetpack `Fragment` classes registered in `res/navigation/nav_graph.xml`.

2. **Android XML Views Priority**:
   - The primary UI framework is **Android XML Layouts with ViewBinding**.
   - Do NOT rewrite or migrate existing XML layouts to Jetpack Compose unless explicitly ordered by the project brief.
   - If a focused ComposeView is used for specialized sub-components (such as audio permission gates), it must be localized and wrapped with `DisposeOnViewTreeLifecycleDestroyed`.

3. **Lifecycle-Safe ViewBinding**:
   - Every Fragment must declare its ViewBinding using the standard backing-property pattern:
     ```kotlin
     private var _binding: FragmentExampleBinding? = null
     private val binding get() = _binding!!
     ```
   - Every Fragment **MUST** override `onDestroyView()` and set `_binding = null`:
     ```kotlin
     override fun onDestroyView() {
         super.onDestroyView()
         _binding = null
     }
     ```
   - Failing to nullify `_binding` in `onDestroyView()` is an immediate blocker that leaks the entire view hierarchy on the back-stack.

---

## 2. Clean Architecture & Layer Boundaries

```text
Presentation Layer (Activities, Fragments, ViewModels, Adapters)
         │ (depends on)
         ▼
    Domain Layer (Use Cases, Domain Models, Repository Contracts)
         ▲
         │ (implements)
    Data Layer (Room DAOs, Database, Remote APIs, Repository Impls)
```

1. **Dependency Direction (STRICT)**:
   - Presentation depends on Domain.
   - Data depends on Domain.
   - **Domain MUST NEVER depend on Presentation or Data**.
   - Domain must not import `android.view`, `android.widget`, Room annotations, or Retrofit DTOs.

2. **ViewModel Invariants**:
   - ViewModels must NEVER hold references to an `Activity`, `Fragment`, `View`, `ViewBinding`, or short-lived `Context`.
   - If an Android context is strictly required (e.g., for system services), use `@ApplicationContext private val context: Context` injected via Hilt.
   - ViewModels must expose **immutable** UI state (`StateFlow<UiState>`) and keep mutable flows (`MutableStateFlow`) private.

3. **No Business Logic in UI Classes**:
   - Fragments and Adapters must NEVER perform database queries, calculations of spaced repetition intervals, CEFR level evaluations, or direct network calls.
   - All logic must reside in Domain Use Cases (`CurriculumDomainLogic`) or Repository implementations.

---

## 3. Asynchronous Work, Coroutines & Audio Safety

1. **Main-Safe Suspend APIs**:
   - All repository methods that access Room DB, disk, or network must be main-safe, internally switching to `Dispatchers.IO` using `withContext(Dispatchers.IO)`.

2. **Lifecycle-Aware Flow Collection**:
   - In Fragments, flows must ALWAYS be collected inside `viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)`:
     ```kotlin
     viewLifecycleOwner.lifecycleScope.launch {
         viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
             viewModel.uiState.collect { state ->
                 renderUi(state)
             }
         }
     }
     ```
   - Never collect flows in `lifecycleScope` directly without `repeatOnLifecycle`, as it continues processing in background states and wastes CPU/battery.

3. **Cancellation Preservation**:
   - Never catch `CancellationException` and swallow it. Always re-throw `CancellationException` in coroutine try-catch blocks to preserve structured concurrency cancellation.

4. **Audio & Media Lifecycle Safety**:
   - Any Fragment or ViewModel that initiates TTS or voice playback must stop playback in `onPause()` or `onCleared()`:
     ```kotlin
     override fun onPause() {
         super.onPause()
         viewModel.stopTts()
     }
     ```

---

## 4. Resource Centralization & Hardcoding Prohibition

1. **User-Facing Strings**:
   - All user-visible labels, headers, button texts, hints, and feedback messages MUST be declared in `res/values/strings.xml`.
   - Use string formatting parameters (`%1$s`, `%1$d`, `%2$d`) instead of Kotlin string concatenation.
   - Structured curriculum data (400 lessons, grammar explanations, vocabulary) remains stored in the data seed/database layer, not `strings.xml`.

2. **Colors**:
   - Use semantic color tokens declared in `res/values/colors.xml` (e.g., `@color/color_cosmic_background`, `@color/color_neon_cyan`, `@color/color_neon_green`).
   - Never scatter raw hex strings (`#00E5FF`, `#0A1326`) across XML layouts or Kotlin files.

3. **Dimensions & Typography**:
   - Spatial spacing and corner radii must reference `@dimen/...` tokens (e.g., `@dimen/spacing_medium`, `@dimen/radius_card_large`).
   - Text sizes must use `sp` and reference typography dimension tokens.
   - Interactive touch targets must meet the minimum accessibility standard of **48dp x 48dp** (`@dimen/touch_target_min`).

---

## 5. Pedagogical Structure: 4-Step Universal Lesson Layout

Every curriculum lesson must adhere to the 4-step interactive flow:
1. **Qoida (Rule)**:
   - Clear objective and real-life conversational context with Lera.
   - Structural formula, grammatical rule explanation in natural Uzbek, Uzbek-vs-English contrast, and interactive 3-column visual concept showcase.
2. **Misollar (Examples)**:
   - 3 highlighted example sentences with audio pronunciation buttons and Uzbek translations.
3. **Mashqlar (Exercises)**:
   - 3 interactive fill-in-the-blank questions with live feedback, audio playback, and error explanations.
4. **Wrap-up**:
   - Summary takeaways, core sentence model, and golden completion star.

---

## 6. Language & Code Style Conventions

- **Code & Architecture**: Professional English for all class, method, variable, parameter names, and technical comments.
- **Learner-Facing Content**: Natural, respectful, grammatically accurate Uzbek (Latin script) for all explanations and instructions.
- **No Fake IPA/Phonetics**: Do not generate phonetic transcriptions or IPA brackets in learner-facing text. Audio pronunciation is handled via Text-to-Speech (`TtsManager`).
