import com.vanniktech.maven.publish.JavaPlatform
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

plugins {
    `java-platform`
    publication
}

val me = project
rootProject.subprojects {
    if (name != me.name) {
        me.evaluationDependsOn(path)
    }
}

dependencies {
    constraints {
        rootProject.subprojects.forEach { subproject ->
            if (subproject.plugins.hasPlugin("maven-publish") && subproject.name != name) {
                val publishing = subproject.extensions.findByType<PublishingExtension>() ?: return@forEach
                publishing.publications.withType<MavenPublication>().configureEach {
                    if (!artifactId.endsWith("-metadata") &&
                        !artifactId.endsWith("-kotlinMultiplatform")
                    ) {
                        api("$groupId:$artifactId:$version")
                    }
                }
            }
        }
    }
}

mavenPublishing {
    configure(JavaPlatform())
}
