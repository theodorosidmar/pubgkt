plugins {
    `kmp-library`
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.common)
        }
    }
}
