plugins {
    id("kmp-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.coroutines.core)
            api(libs.ktor.client.core)
            api(libs.ktor.client.contentnegotiation)
            api(libs.ktor.client.logging)
            api(libs.ktor.client.encoding)
            api(libs.ktor.serialization.kotlinx.json)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.coroutines.test)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }
    }
}
