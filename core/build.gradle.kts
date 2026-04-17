plugins {
    `kmp-library`
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.clans)
            api(projects.matches)
            api(projects.players)
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
    dokka(projects.clans)
    dokka(projects.matches)
    dokka(projects.players)
    kover(projects.common)
    kover(projects.clans)
    kover(projects.matches)
    kover(projects.players)
}
