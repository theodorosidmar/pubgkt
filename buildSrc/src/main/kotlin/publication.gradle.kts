import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SourcesJar

plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    coordinates(
        groupId = Library.GROUP,
        artifactId = project.name,
        version = Library.VERSION,
    )

    if (project.hasProperty("release")) {
        signAllPublications()
        publishToMavenCentral(automaticRelease = true)
    }

    pom {
        name = "${Library.NAME}:${project.name}"
        description = "${Library.DESCRIPTION} – ${project.name} module"
        url = Library.PROJECT_URL

        licenses {
            license {
                name = "MIT"
                url = "https://opensource.org/licenses/MIT"
            }
        }

        developers {
            developer {
                id = "theodorosidmar"
                name = "Sidmar Theodoro"
                url = "https://github.com/theodorosidmar"
            }
        }

        issueManagement {
            system = "GitHub Issues"
            url = "${Library.PROJECT_URL}/issues"
        }
    }

    if (plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
        configure(
            KotlinMultiplatform(
                JavadocJar.Dokka("dokkaGeneratePublicationHtml"),
                sourcesJar = SourcesJar.Sources(),
            ),
        )
    } else if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
        configure(
            KotlinJvm(
                JavadocJar.Dokka("dokkaGeneratePublicationHtml"),
                sourcesJar = SourcesJar.Sources(),
            ),
        )
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}
