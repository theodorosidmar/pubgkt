group = "pubgkt"
version = "0.0.1"

plugins {
    alias(libs.plugins.kotlinMultiplatform) apply true
    alias(libs.plugins.serialization) apply true

    `maven-publish`
}

kotlin {
    jvm()

    js {
        browser()
        nodejs()
    }

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
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/theodorosidmar/pubgkt")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}
