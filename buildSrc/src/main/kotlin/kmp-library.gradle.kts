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
        enabled.set(true)
        filters {
            exclude {
                annotatedWith.add("pubgkt.PubgktInternal")
                byNames.add("pubgkt.PubgktInternal")
            }

            include {
                byNames.add("pubgkt.**")
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
    detektPlugins("dev.detekt:detekt-rules-ktlint-wrapper:2.0.0-alpha.3")
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

    check {
        dependsOn(
            detektCommonMainSourceSet,
            detektCommonTestSourceSet,
        )
    }

    val detektMergeReport by registering(ReportMergeTask::class) {
        output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
    }

    detektMergeReport {
        input.from(withType<Detekt>().map { it.reports.sarif.outputLocation })
    }
}
