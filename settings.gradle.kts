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
    "matches",
    "players",
    "clans",
    "samples",
    "test-support",
)
