import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SourcesJar

plugins {
    id("com.vanniktech.maven.publish")
    kotlin("npm-publish")
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

        scm {
            connection = "scm:git:ssh://github.com/theodorosidmar/pubgkt.git"
            developerConnection = "scm:git:ssh://github.com/theodorosidmar/pubgkt.git"
            url = Library.PROJECT_URL
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

if (plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
    npmPublish {
        organization.set(Library.NAME)
        version.set(Library.VERSION)

        packages {
            named("js") {
                packageName.set(project.name)
                readme.set(project.file("readme.md"))
                packageJson {
                    "license" by "MIT"
                    "homepage" by Library.PROJECT_URL
                    "description" by Library.DESCRIPTION
                    "author" by "theodorosidmar"
                    "keywords" by arrayOf("pubg", "pubg-api", "kotlin", "multiplatform", "kmp")
                    "types" by "pubgkt-${project.name}.d.mts"
                    "repository" by
                        mapOf(
                            "type" to "git",
                            "url" to "git+${Library.PROJECT_URL}.git",
                        )
                    "bugs" by
                        mapOf(
                            "url" to "${Library.PROJECT_URL}/issues",
                        )
                }
            }
        }

        registries {
            register("npmjs") {
                uri.set("https://registry.npmjs.org")
                authToken.set(providers.environmentVariable("NPM_TOKEN"))
            }
        }
    }
}
