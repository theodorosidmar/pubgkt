plugins {
    `maven-publish`
}

publishing {
    repositories {
        maven {
            name = "GitHub"
            url = uri("https://maven.pkg.github.com/theodorosidmar/pubgkt")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        getByName<MavenPublication>("jvm") {
            artifactId = "pubgkt"
        }
    }
}
