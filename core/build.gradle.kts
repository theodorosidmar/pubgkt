plugins {
    id("kmp-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.players)
        }
    }
}

// Aggregate Dokka documentation from all library modules into a single output.
// Run: ./gradlew :core:dokkaGeneratePublicationHtml
// Output: core/build/dokka/html/
dependencies {
    dokka(projects.common)
    dokka(projects.players)
}
