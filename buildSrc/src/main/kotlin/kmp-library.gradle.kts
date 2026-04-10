import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3

plugins {
    kotlin("multiplatform")
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
}
