group = "pubgkt"

plugins {
    alias(libs.plugins.kotlinMultiplatform) apply true
    alias(libs.plugins.serialization) apply true

//    alias(libs.plugins.androidLibrary) apply true

    `maven-publish`
}

kotlin {
    jvm()

    js {
        browser()
        nodejs()
    }

//    androidTarget {
//        publishLibraryVariants("release")
//        compilations.all {
//            kotlinOptions {
//                jvmTarget = "11"
//            }
//        }
//    }

    sourceSets {
        commonMain.dependencies {
            // Coroutines
            implementation(libs.coroutines.core)

            // Ktor (client)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentnegotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.encoding)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }

        jvmTest.dependencies {
            runtimeOnly(libs.logback.classic)
        }
//
//        androidMain.dependencies {
//            implementation(libs.ktor.client.cio)
//        }
    }
}

//android {
//    namespace = "pubgkt"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//    defaultConfig {
//        minSdk = libs.versions.android.minSdk.get().toInt()
//    }
//}
