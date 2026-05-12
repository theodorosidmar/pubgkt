import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    `kmp-library`
}

kotlin {
    val xcf = XCFramework("PubgKt")
    targets.withType<KotlinNativeTarget>().configureEach {
        if (!konanTarget.family.isAppleFamily) return@configureEach

        binaries.framework {
            baseName = "PubgKt"
            binaryOption("bundleId", "dev.pubgkt.PubgKt")
            isStatic = true
            export(projects.common)
            export(projects.players)
            export(projects.clans)
            export(projects.matches)
            export(projects.leaderboards)
            export(projects.mastery)
            export(projects.stats)
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.common)
            api(projects.clans)
            api(projects.leaderboards)
            api(projects.mastery)
            api(projects.matches)
            api(projects.players)
            api(projects.stats)
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
    dokka(projects.stats)

    kover(projects.clans)
    kover(projects.common)
    kover(projects.leaderboards)
    kover(projects.mastery)
    kover(projects.matches)
    kover(projects.players)
    kover(projects.stats)
}
