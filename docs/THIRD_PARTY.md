# Third-party notices

| Component | Version | License | Source |
| --- | --- | --- | --- |
| whisper.cpp / ggml | v1.7.6 | MIT | https://github.com/ggml-org/whisper.cpp |
| OpenAI Whisper base.en model, GGML Q5_1 conversion | base.en-q5_1 | MIT | https://huggingface.co/ggerganov/whisper.cpp |
| Dagger / Hilt | 2.60.1 | Apache 2.0 | https://github.com/google/dagger |
| OkHttp | 4.12.0 | Apache 2.0 | https://github.com/square/okhttp |
| AndroidX | See Gradle | Apache 2.0 | https://android.googlesource.com/platform/frameworks/support/ |
| Kotlin coroutines | 1.10.2 | Apache 2.0 | https://github.com/Kotlin/kotlinx.coroutines |
| Gradle wrapper | 9.3.1 | Apache 2.0 | https://github.com/gradle/gradle |

Full license texts: APACHE-2.0.txt, WHISPER-MIT.txt and OPENAI-WHISPER-MIT.txt in this directory.
The model and source archive hashes are in tools/model.sha256 and tools/whisper.sha256.
The native source and model are downloaded at build time, not committed as large binaries.
No Vosk/JNA runtime is included in this version.
