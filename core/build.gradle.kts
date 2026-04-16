plugins {
    `kmp-library`
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.players)
            api(projects.clans)
        }
    }
}

// Aggregate Dokka documentation from all library modules into a single output.
// Run: ./gradlew :core:dokkaGeneratePublicationHtml
// Output: core/build/dokka/html/
// Aggregate Kover coverage from all library modules into a single output
// Run: ./gradlew :core:koverHtmlReport
dependencies {
    dokka(projects.common)
    dokka(projects.players)
    dokka(projects.clans)
    kover(projects.common)
    kover(projects.players)
    kover(projects.clans)
}
