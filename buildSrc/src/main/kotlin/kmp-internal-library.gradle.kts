import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeSimulatorTest

plugins {
    kotlin("multiplatform")
}

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
        progressiveMode = true
        apiVersion = KOTLIN_2_3
        languageVersion = KOTLIN_2_3
        freeCompilerArgs.addAll(
            "-Xcontext-parameters",
            "-Xname-based-destructuring=complete",
        )
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JVM_17)
        }
    }

    iosArm64()
    iosX64()
    iosSimulatorArm64()
    watchosArm64()
    watchosArm32()
}

tasks {
    withType<KotlinNativeSimulatorTest>().configureEach {
        val moduleDir = project.projectDir.absolutePath
        environment("PUBGKT_TEST_MODULE_DIR", moduleDir)
        environment("SIMCTL_CHILD_PUBGKT_TEST_MODULE_DIR", moduleDir)
    }
}
