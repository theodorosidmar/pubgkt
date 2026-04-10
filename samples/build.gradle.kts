plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("pubgkt.PlayersSampleKt")
}

dependencies {
    implementation(projects.players)
    implementation(libs.coroutines.jdk8)
}

// Run the Kotlin sample:  ./gradlew :samples:run --args="<api-key> [accountId] [playerName]"
// Run the Java sample:    ./gradlew :samples:runJava --args="<api-key> [accountId] [playerName]"
tasks.register<JavaExec>("runJava") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("pubgkt.PlayersSample")
    if (project.hasProperty("args")) {
        args = (project.property("args") as String).split(" ")
    }
}
