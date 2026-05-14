plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("dev.pubgkt.PlayersSampleKt")
}

dependencies {
    if (project.hasProperty("local")) {
        implementation(projects.core)
    } else {
        implementation(platform("dev.pubgkt:bom:1.0.1"))
        implementation("dev.pubgkt:core")
    }
    implementation(libs.coroutines.jdk8)
}

val sampleModules = listOf(
    "Common",
    "Players",
    "Clans",
    "Matches",
    "Leaderboards",
    "Mastery",
    "Stats",
    "RateLimit",
    "Retry",
)

sampleModules.forEach { module ->
    tasks {
        register<JavaExec>("run${module}Kotlin") {
            description = "Run Kotlin code for $module"
            classpath = sourceSets["main"].runtimeClasspath
            mainClass.set("dev.pubgkt.${module}SampleKt")
            if (project.hasProperty("args")) {
                args = (project.property("args") as String).split(" ")
            }
        }

        register<JavaExec>("run${module}Java") {
            description = "Run Java code for $module"
            classpath = sourceSets["main"].runtimeClasspath
            mainClass.set("dev.pubgkt.${module}Sample")
            if (project.hasProperty("args")) {
                args = (project.property("args") as String).split(" ")
            }
        }
    }
}
