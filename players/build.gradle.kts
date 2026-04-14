plugins {
    id("kmp-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.common)
        }

        commonTest.dependencies {
            implementation(projects.testSupport)
            implementation(libs.kotlin.test)
        }
    }
}
