plugins {
    id("kmp-internal-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.ktor.client.mock)
            api(libs.coroutines.test)
        }
    }
}
