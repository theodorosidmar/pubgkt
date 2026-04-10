import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SourcesJar

plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    coordinates(Library.GROUP, project.name, Library.VERSION)

    pom {
        name.set("${Library.NAME}:${project.name}")
        description.set("${Library.DESCRIPTION} – ${project.name} module")
        url.set(Library.PROJECT_URL)
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("theodorosidmar")
                name.set("Sidmar Theodoro")
                url.set("https://github.com/theodorosidmar")
            }
        }
        issueManagement {
            system.set("GitHub Issues")
            url.set("${Library.PROJECT_URL}/issues")
        }
    }

    if (plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")) {
        configure(
            KotlinMultiplatform(
                JavadocJar.Dokka("dokkaGeneratePublicationHtml"),
                sourcesJar = SourcesJar.Sources()
            )
        )
    } else if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
        configure(KotlinJvm(JavadocJar.Dokka("dokkaGeneratePublicationHtml"), sourcesJar = SourcesJar.Sources()))
    }
}

publishing {
    repositories {
        mavenLocal()
    }
}
