import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.FailOnSeverity.Warning
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
}

detekt {
    config.setFrom(rootProject.file("detekt/detekt.yml"))
    buildUponDefaultConfig = false
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
}
