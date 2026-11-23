import java.io.File
import java.net.URI
import java.security.MessageDigest

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}
android {
    namespace = "uz.talkme.next"
    compileSdk { version = release(36) { minorApiLevel = 1 } }
    ndkVersion = "27.2.12479018"
    defaultConfig {
        applicationId = "com.aistudio.talkme.xkpqvr"
        minSdk = 26
        targetSdk = 36
        versionCode = 2
        versionName = "2.0.0-whisper"
        ndk { abiFilters.addAll(listOf("arm64-v8a", "x86_64")) }
        externalNativeBuild.cmake {
            arguments.add("-DWHISPER_SOURCE_DIR=${layout.buildDirectory.dir("whisperSource").get().asFile.absolutePath}")
            arguments.add("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            arguments.add("-DANDROID_STL=c++_shared")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures { viewBinding = true }
    testOptions { unitTests.isIncludeAndroidResources = true }
    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_PATH") ?: "${rootDir}/my-upload-key.jks")
            storePassword = System.getenv("STORE_PASSWORD")
            keyAlias = "upload"
            keyPassword = System.getenv("KEY_PASSWORD")
        }
        create("debugConfig") {
            storeFile = file("${rootDir}/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    buildTypes {
        release { signingConfig = signingConfigs.getByName("release") }
        debug {
            // Preserve the supplied development certificate; fresh clones use Gradle's debug key.
            if (rootProject.file("debug.keystore").exists()) signingConfig = signingConfigs.getByName("debugConfig")
        }
    }
    sourceSets.getByName("main").assets.srcDir(layout.buildDirectory.dir("speechAssets").get().asFile)
    externalNativeBuild { cmake { path = file("src/main/cpp/CMakeLists.txt"); version = "3.22.1" } }
    androidResources { noCompress.add("bin") }
    packaging { jniLibs.useLegacyPackaging = false }
}

// Both artifacts are pinned and verified. Downloads happen only at build time.
fun verifyDownload(file: File, expected: String) {
    val digest = MessageDigest.getInstance("SHA-256")
    file.inputStream().use { source ->
        val buffer = ByteArray(65536)
        while (true) { val size = source.read(buffer); if (size < 0) break; digest.update(buffer, 0, size) }
    }
    check(digest.digest().joinToString("") { "%02x".format(it) } == expected) {
        "SHA-256 mismatch: ${file.name}. Delete app/build/downloads and retry."
    }
}
val prepareSpeechModel by tasks.registering {
    val destination = layout.buildDirectory.file("speechAssets/whisper/ggml-base.en-q5_1.bin")
    inputs.file(rootProject.file("tools/model.sha256"))
    outputs.file(destination)
    doLast {
        val cached = layout.buildDirectory.file("downloads/ggml-base.en-q5_1.bin").get().asFile
        cached.parentFile.mkdirs()
        if (!cached.exists()) URI("https://huggingface.co/ggerganov/whisper.cpp/resolve/c521a4b02f422512d734391fdf08bb08c0862f68/ggml-base.en-q5_1.bin")
            .toURL().openStream().use { source -> cached.outputStream().use { source.copyTo(it) } }
        verifyDownload(cached, rootProject.file("tools/model.sha256").readText().trim())
        destination.get().asFile.parentFile.mkdirs()
        cached.copyTo(destination.get().asFile, overwrite = true)
    }
}
val prepareWhisperSource by tasks.registering {
    val destination = layout.buildDirectory.dir("whisperSource")
    inputs.file(rootProject.file("tools/whisper.sha256"))
    outputs.dir(destination)
    doLast {
        val cached = layout.buildDirectory.file("downloads/whisper-v1.7.6.tar.gz").get().asFile
        cached.parentFile.mkdirs()
        if (!cached.exists()) URI("https://github.com/ggml-org/whisper.cpp/archive/refs/tags/v1.7.6.tar.gz")
            .toURL().openStream().use { source -> cached.outputStream().use { source.copyTo(it) } }
        verifyDownload(cached, rootProject.file("tools/whisper.sha256").readText().trim())
        copy {
            from(tarTree(resources.gzip(cached)))
            include("whisper.cpp-1.7.6/**")
            eachFile { path = path.removePrefix("whisper.cpp-1.7.6/") }
            includeEmptyDirs = false
            into(destination)
        }
    }
}
tasks.named("preBuild").configure { dependsOn(prepareSpeechModel, prepareWhisperSource) }
tasks.configureEach { if (name.startsWith("configureCMake")) dependsOn(prepareWhisperSource) }

dependencies {
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.9")
    implementation("com.google.dagger:hilt-android:2.60.1")
    ksp("com.google.dagger:hilt-compiler:2.60.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.json:json:20240303")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("org.robolectric:robolectric:4.16.1")
}

tasks.withType<org.gradle.api.tasks.testing.Test>().configureEach {
    systemProperty("robolectric.dependency.repo.url", "https://repo.maven.apache.org/maven2")
    listOf("http.proxyHost", "http.proxyPort", "https.proxyHost", "https.proxyPort", "javax.net.ssl.trustStore").forEach { key ->
        System.getProperty(key)?.let { systemProperty(key, it) }
    }
}
