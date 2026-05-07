enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "pubgkt"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

include(
    "bom",
    "clans",
    "common",
    "core",
    "leaderboards",
    "mastery",
    "matches",
    "players",
    "samples:jvm",
    "stats",
    "test-support",
)
