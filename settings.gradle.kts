enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "pubgkt"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(
    "common",
    "core",
    "players",
)
