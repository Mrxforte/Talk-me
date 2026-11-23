#include <jni.h>
#include <whisper.h>
#include <atomic>
#include <algorithm>
#include <cstdint>
#include <string>
#include <vector>

namespace {
struct Engine { whisper_context *context; std::atomic<int64_t> epoch{0}; };
struct AbortState { Engine *engine; int64_t epoch; };
bool should_abort(void *data) {
    auto *state = static_cast<AbortState *>(data);
    return state->engine->epoch.load(std::memory_order_relaxed) != state->epoch;
}
void fail(JNIEnv *env, const char *message) {
    env->ThrowNew(env->FindClass("java/lang/IllegalStateException"), message);
}
}
extern "C" JNIEXPORT jlong JNICALL
Java_uz_talkme_next_audio_WhisperNative_load(JNIEnv *env, jobject, jstring path) {
    const char *filename = env->GetStringUTFChars(path, nullptr);
    auto params = whisper_context_default_params();
    params.use_gpu = false;
    auto *context = whisper_init_from_file_with_params(filename, params);
    env->ReleaseStringUTFChars(path, filename);
    if (!context) { fail(env, "Cannot load the offline Whisper model"); return 0; }
    return reinterpret_cast<jlong>(new Engine{context});
}
extern "C" JNIEXPORT void JNICALL
Java_uz_talkme_next_audio_WhisperNative_setEpoch(JNIEnv *, jobject, jlong handle, jlong epoch) {
    if (handle) reinterpret_cast<Engine *>(handle)->epoch.store(epoch, std::memory_order_relaxed);
}
extern "C" JNIEXPORT jstring JNICALL
Java_uz_talkme_next_audio_WhisperNative_transcribe(JNIEnv *env, jobject, jlong handle,
                                                jfloatArray input, jlong epoch, jint threads) {
    auto *engine = reinterpret_cast<Engine *>(handle);
    if (!engine || engine->epoch.load() != epoch) return env->NewStringUTF("");
    const auto count = env->GetArrayLength(input);
    if (count < 1600) return env->NewStringUTF("");
    std::vector<float> samples(count);
    env->GetFloatArrayRegion(input, 0, count, samples.data());
    if (env->ExceptionCheck()) return nullptr;
    auto params = whisper_full_default_params(WHISPER_SAMPLING_GREEDY);
    params.n_threads = std::clamp(static_cast<int>(threads), 1, 4);
    params.language = "en";
    params.translate = false;
    params.no_context = true;
    params.no_timestamps = true;
    params.single_segment = true;
    params.print_realtime = false;
    params.print_progress = false;
    params.print_timestamps = false;
    params.print_special = false;
    params.suppress_blank = true;
    params.suppress_nst = true;
    params.temperature = 0.0f;
    params.temperature_inc = 0.0f;
    params.greedy.best_of = 1;
    AbortState abort{engine, epoch};
    params.abort_callback = should_abort;
    params.abort_callback_user_data = &abort;
    const int status = whisper_full(engine->context, params, samples.data(), count);
    if (engine->epoch.load() != epoch) return env->NewStringUTF("");
    if (status != 0) { fail(env, "Offline Whisper decoding failed"); return nullptr; }
    std::string result;
    for (int i = 0; i < whisper_full_n_segments(engine->context); ++i) {
        if (whisper_full_get_segment_no_speech_prob(engine->context, i) > 0.8f) continue;
        result += whisper_full_get_segment_text(engine->context, i);
    }
    return env->NewStringUTF(result.c_str());
}
extern "C" JNIEXPORT void JNICALL
Java_uz_talkme_next_audio_WhisperNative_release(JNIEnv *, jobject, jlong handle) {
    auto *engine = reinterpret_cast<Engine *>(handle);
    if (engine) { whisper_free(engine->context); delete engine; }
}
