# TalkMe Android Engineering — Definition of Done (DoD)

Before any pull request, code contribution, or architectural update is marked complete, it must satisfy all criteria in this checklist.

---

## 1. Architecture & Navigation Check
- [ ] **Single Activity**: All new user-facing screens are built as `Fragment` components hosted inside `MainActivity` via `res/navigation/nav_graph.xml`. No new Activities created.
- [ ] **XML ViewBinding**: UI is constructed using XML layouts and ViewBinding. No unrequested migrations to Jetpack Compose.
- [ ] **Clean Architecture Boundaries**:
  - Presentation depends only on Domain interfaces.
  - ViewModels hold no `View`, `Activity`, `Fragment`, `ViewBinding`, or short-lived `Context` references.
  - Domain layer has zero dependencies on Presentation or Data/Core frameworks.
- [ ] **Unidirectional State Flow**: ViewModels expose immutable `StateFlow<UiState>` and keep mutable flows private.

## 2. Lifecycle & Memory Leak Prevention
- [ ] **ViewBinding Nullification**: Every Fragment overrides `onDestroyView()` and sets `_binding = null`.
- [ ] **Lifecycle-Safe Flow Collection**: Flows are collected strictly inside `viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)`.
- [ ] **Audio Lifecycle Cleanup**: Any active audio synthesis or TTS stops on Fragment `onPause()` and ViewModel `onCleared()`.
- [ ] **Coroutine Cancellation**: `CancellationException` is preserved and not caught/swallowed.

## 3. Resources & Centralization
- [ ] **No Hardcoded Strings**: All user-visible labels, errors, and toast/snackbar messages reside in `res/values/strings.xml`.
- [ ] **No Hardcoded Colors**: Colors reference semantic `@color/...` tokens in `res/values/colors.xml`.
- [ ] **Touch Targets**: All clickable buttons and interactive items meet the minimum **48dp x 48dp** touch target requirement.
- [ ] **Accessibility**: All `ImageView` and `ImageButton` elements provide descriptive `android:contentDescription` attributes.

## 4. List & Performance Optimization
- [ ] **DiffUtil in RecyclerViews**: All list adapters extend `ListAdapter` with a dedicated `DiffUtil.ItemCallback`. No indiscriminate `notifyDataSetChanged()` calls.
- [ ] **Canvas/Custom Views**: Custom views (`TasksCircularProgressView`, `SkillAnalyticsChartView`) size dynamically via DrawScope / onSizeChanged without hardcoded pixel values.

## 5. Pedagogical & Content Integrity
- [ ] **Universal 4-Stage Template**: Lessons follow the **Qoida → Misollar → Mashqlar → Wrap-up** flow.
- [ ] **Preserved Existing Progress**: Room database migrations or seed merging preserve existing user progress, streak days, and review schedules without resetting data.
- [ ] **Zero Raw Phonetics**: No IPA bracket notation in learner-facing text; English pronunciation is driven by TTS.

## 6. Verification & Automated Quality Gates
- [ ] **Applet Compilation**: `compile_applet` runs and returns `Build succeeded`.
- [ ] **Unit & Robolectric Tests**: `gradle :app:testDebugUnitTest` passes with zero failures (`BUILD SUCCESSFUL`).
- [ ] **Documentation Sync**: Any deleted dead code is recorded in `docs/engineering/REMOVAL_LOG.md`, and any architectural alterations are updated in `docs/engineering/AUDIT_REPORT.md`.
