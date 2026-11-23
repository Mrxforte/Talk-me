import java.net.URI

pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven { url = URI.create("https://jitpack.io") }
    maven { url = URI.create("https://oss.sonatype.org/content/repositories/snapshots") }
  }
}

rootProject.name = "TalkMe"

include(":app")

// Explicit opt-in: source authoring files alone are not a Unity Android runtime.
if (providers.gradleProperty("talkmeUnity").orNull == "true") {
  val unityExport = file("unity-export/unityLibrary")
  require(unityExport.resolve("build.gradle").isFile || unityExport.resolve("build.gradle.kts").isFile) {
    "Missing Unity Android export. Export the approved characters from the unity project first."
  }
  include(":unityLibrary")
  project(":unityLibrary").projectDir = unityExport
}
