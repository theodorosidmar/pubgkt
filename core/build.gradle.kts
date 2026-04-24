plugins {
    `kmp-library`
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.clans)
            api(projects.leaderboards)
            api(projects.mastery)
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
    dokka(projects.clans)
    dokka(projects.common)
    dokka(projects.leaderboards)
    dokka(projects.mastery)
    dokka(projects.matches)
    dokka(projects.players)

    kover(projects.clans)
    kover(projects.common)
    kover(projects.leaderboards)
    kover(projects.mastery)
    kover(projects.matches)
    kover(projects.players)
}
