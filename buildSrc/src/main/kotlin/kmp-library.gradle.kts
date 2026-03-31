import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17

plugins {
    kotlin("multiplatform")
}

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
        progressiveMode = true
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JVM_17)
        }
    }
}
