plugins {
    `kotlin-dsl`
}

dependencies {
    api(libs.gradle.kotlin)
    api(libs.gradle.kotlin.serialization)
    api(libs.gradle.dokka)
    api(libs.gradle.vanniktech)
    api(libs.gradle.detekt)
}
