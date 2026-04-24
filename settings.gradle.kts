enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "pubgkt"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
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
    "samples",
    "test-support",
)
