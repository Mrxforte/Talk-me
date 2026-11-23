# Voice v2 definition of done

- [ ] One Activity, one navigation destination, XML/ViewBinding and Hilt.
- [ ] Immutable StateFlow UI state; domain does not depend on Android/presentation/data.
- [ ] Transparent transcript area, clear state labels, accessible controls, system-bar insets.
- [ ] Fragment binding cleanup and repeatOnLifecycle observation.
- [ ] No background capture/playback; requests and native decoder are cancellable.
- [ ] Whisper model and source versions pinned with SHA-256 validation.
- [ ] No paid speech provider, no silence-triggered Gemini requests, bounded repeated text context.
- [ ] Secrets excluded from repository and saved state.
- [ ] Existing application ID preserved, versionCode increased, no legacy database reset.
- [ ] Prior main preserved on an archive branch; removal/audit reports updated.
- [ ] Debug APK assembles, unit tests pass, lint reports zero errors.
- [ ] compile_applet run when available; otherwise unavailability documented.
- [ ] Render and recorded-audio checks report their exact limits.
- [ ] Physical-device and paid-API checks are marked verified only after actual testing.
