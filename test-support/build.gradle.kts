plugins {
    `kmp-internal-library`
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.test.common)
            api(libs.ktor.client.mock)
        }
    }
}
