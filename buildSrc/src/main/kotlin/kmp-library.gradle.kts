import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3
import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.dokka")
    id("publication")
}

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
        progressiveMode = true
        apiVersion = KOTLIN_2_3
        languageVersion = KOTLIN_2_3
        optIn.addAll(pubgktOptIns)
    }

    explicitApi()

    jvm {
        compilerOptions {
            jvmTarget.set(JVM_17)
        }
    }

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
