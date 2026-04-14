enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "pubgkt"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(
    "bom",
    "common",
    "core",
    "players",
    "samples",
    "test-support",
)
