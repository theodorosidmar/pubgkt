plugins {
    kotlin(module = "multiplatform") version "1.9.21"
    kotlin(module = "plugin.serialization") version "1.9.21"
    `maven-publish`
}

group = "pubgkt"

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    sourceSets {
        val coroutinesVersion = "1.7.2"
        val ktorVersion = "2.3.1"

        commonMain.dependencies {
            // Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

            // Ktor (client)
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.ktor:ktor-client-encoding:$ktorVersion")
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
        }

        jvmMain.dependencies {
            implementation("io.ktor:ktor-client-cio:$ktorVersion")
        }
    }
}
