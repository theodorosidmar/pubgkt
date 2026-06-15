import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider

internal val Project.libs: VersionCatalog get() =
    extensions
        .getByType(VersionCatalogsExtension::class.java)
        .named("libs")

internal fun VersionCatalog.requiredLibrary(alias: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(alias).orElseThrow {
        IllegalStateException("$alias not found in version catalog")
    }

internal val Project.detektKtlint: Provider<MinimalExternalModuleDependency>
    get() = libs.requiredLibrary("detekt-ktlint")
