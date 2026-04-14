plugins {
    id("kmp-internal-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.common)
            api(libs.ktor.client.mock)
        }
    }
}
