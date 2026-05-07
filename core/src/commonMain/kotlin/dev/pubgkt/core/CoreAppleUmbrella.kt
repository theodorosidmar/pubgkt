package dev.pubgkt.core

/**
 * Marker object required to make `core` a non-empty Kotlin/Native source module.
 *
 * Kotlin Multiplatform only generates Apple framework binaries (and the resulting XCFramework)
 * for modules that contain at least one source file targeting Apple platforms. Without any
 * source, the `:core:assemblePubgktReleaseXCFramework` task is skipped as `NO-SOURCE`, even
 * though `core` re-exports all library modules via `export(...)` in its framework binary
 * configuration.
 *
 * This object has no runtime behavior — it simply ensures the module is treated as a
 * legitimate source set participant so the umbrella Apple XCFramework is produced correctly.
 */
internal object CoreAppleUmbrella
