import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.FailOnSeverity.Warning
import dev.detekt.gradle.report.ReportMergeTask
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    id("kmp-internal-library")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    id("org.jetbrains.dokka")
    id("publication")
    id("dev.detekt")
}

kotlin {
    compilerOptions {
        optIn.addAll(pubgktOptIns)
    }

    explicitApi()

    @OptIn(ExperimentalAbiValidation::class)
    abiValidation {
        filters {
            exclude {
                annotatedWith.add("dev.pubgkt.PubgktInternal")
                byNames.add("dev.pubgkt.PubgktInternal")
            }

            include {
                byNames.add("dev.pubgkt.**")
            }
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(project(":test-support"))
        }
    }
}

dependencies {
    detektPlugins(detektKtlint)
}

detekt {
    config.setFrom(rootProject.file("detekt/detekt.yml"))
    buildUponDefaultConfig = false
    autoCorrect = true
    parallel = true
    debug = false
    ignoreFailures = false
    failOnSeverity = Warning
}

tasks {
    withType<Detekt>().configureEach {
        reports {
            checkstyle.required.set(true)
            html.required.set(true)
            sarif.required.set(true)
            markdown.required.set(true)
        }
    }

    val detektAll = register("detektAll") {
        group = "verification"
        description = "Runs detekt against all modules"
        dependsOn(withType<Detekt>())
    }

    check {
        dependsOn(detektAll)
    }

    val detektMergeReport = register("detektMergeReport", ReportMergeTask::class) {
        group = "verification"
        output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
    }

    detektMergeReport {
        input.from(withType<Detekt>().map { it.reports.sarif.outputLocation })
    }
}
