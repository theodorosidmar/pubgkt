import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_3
import org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest
import org.jetbrains.kotlin.gradle.targets.native.tasks.KotlinNativeTest

plugins {
    kotlin("multiplatform")
    id("com.android.kotlin.multiplatform.library")
}

kotlin {
    withSourcesJar(publish = true)

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

    js {
        nodejs()
        generateTypeScriptDefinitions()
        useEsModules()
        binaries.library()
        compilerOptions {
            freeCompilerArgs.addAll(
                "-Xenable-suspend-function-exporting",
                "-Xes-long-as-bigint",
            )
        }
    }

    macosArm64()
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    watchosArm64()
    watchosArm32()

    android {
        namespace = Library.GROUP
        compileSdk = 36
        minSdk = 24
        withHostTest {}
        withDeviceTest {}
        compilerOptions {
            jvmTarget.set(JVM_17)
        }
    }
}

tasks {
    withType<KotlinNativeTest>().configureEach {
        val moduleDir = project.projectDir.absolutePath
        environment("PUBGKT_TEST_MODULE_DIR", moduleDir)
        environment("SIMCTL_CHILD_PUBGKT_TEST_MODULE_DIR", moduleDir)
    }

    withType<KotlinJsTest>().configureEach {
        val moduleDir = project.projectDir.absolutePath
        environment("PUBGKT_TEST_MODULE_DIR", moduleDir)
    }
}
