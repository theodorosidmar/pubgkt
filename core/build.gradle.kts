import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    `kmp-library`
}

kotlin {
    val xcf = XCFramework("PubgKt")
    targets.withType<KotlinNativeTarget>().configureEach {
        if (!konanTarget.family.isAppleFamily) return@configureEach

        binaries.framework {
            baseName = "PubgKt"
            binaryOption("bundleId", "dev.pubgkt.PubgKt")
            isStatic = true
            export(projects.common)
            export(projects.players)
            export(projects.clans)
            export(projects.matches)
            export(projects.leaderboards)
            export(projects.mastery)
            export(projects.stats)
            xcf.add(this)
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.common)
            api(projects.clans)
            api(projects.leaderboards)
            api(projects.mastery)
            api(projects.matches)
            api(projects.players)
            api(projects.stats)
        }
    }
}

// Aggregate Dokka documentation from all library modules into a single output.
// Run: ./gradlew :core:dokkaGeneratePublicationHtml
// Output: core/build/dokka/html/
// Aggregate Kover coverage from all library modules into a single output
// Run: ./gradlew :core:koverHtmlReport
dependencies {
    dokka(projects.clans)
    dokka(projects.common)
    dokka(projects.leaderboards)
    dokka(projects.mastery)
    dokka(projects.matches)
    dokka(projects.players)
    dokka(projects.stats)

    kover(projects.clans)
    kover(projects.common)
    kover(projects.leaderboards)
    kover(projects.mastery)
    kover(projects.matches)
    kover(projects.players)
    kover(projects.stats)
}

npmPublish {
    packages {
        named("js") {
            packageJson {
                "private" by true
            }
        }
    }
}

val libraryModules = listOf("common", "players", "clans", "matches", "leaderboards", "mastery", "stats")

// Splits the core module's single JS compilation into per-module npm packages (`@pubgkt/*`).
// Kotlin/JS compiles each module independently with its own copy of the runtime (stdlib,
// coroutines, ktor, serialization). When multiple packages coexist at runtime, duplicate
// runtime instances have incompatible prototype chains and crash. This task solves that by
// using `core`'s aggregate compilation — where all modules share one runtime — and splitting
// its output into separate packages:
//
// - `@pubgkt/common` bundles the shared runtime `.mjs` files plus `pubgkt-common.mjs`.
// - Each other package (`@pubgkt/players`, etc.) contains only its own `.mjs` with imports
//   rewritten from relative (`'./...'`) to `'@pubgkt/common/...'`.
//
// TypeScript definitions (`.d.mts`) come from per-module individual compilations. Non-common
// modules have their local `KtList` declaration replaced with an import from `@pubgkt/common`
// to avoid duplicate `unique symbol` type incompatibilities.
//
// Each package directory is finalized with `npm pack` to produce a `.tgz` archive.
// Run: `./gradlew :core:assembleNpmPackages`
// Output: `core/build/npmPackages/{module}/pubgkt-{module}-{version}.tgz`
tasks.register("assembleNpmPackages") {
    group = "npm"
    description = "Splits core JS compilation into per-module npm packages"

    dependsOn("assembleJsPackage")
    libraryModules.forEach { mod -> dependsOn(":$mod:compileProductionLibraryKotlinJs") }

    val coreJsDir = layout.buildDirectory.dir("packages/js")
    val outputDir = layout.buildDirectory.dir("npmPackages")
    val version = "1.0.1"

    // Resolve .d.mts paths at configuration time as plain strings (configuration-cache safe)
    val dtsPaths: Map<String, String> = libraryModules.associateWith { mod ->
        project(":$mod").layout.buildDirectory
            .file("compileSync/js/main/productionLibrary/kotlin/pubgkt-$mod.d.mts")
            .get().asFile.absolutePath
    }
    val modules = libraryModules.toList()

    inputs.dir(coreJsDir)
    dtsPaths.values.forEach { inputs.file(it) }
    outputs.dir(outputDir)

    doLast {
        val jsDir = coreJsDir.get().asFile
        val outDir = outputDir.get().asFile
        outDir.deleteRecursively()

        val nonCommonModules = modules - "common"
        val nonCommonMjsNames = nonCommonModules.map { "pubgkt-$it.mjs" }.toSet()

        // --- @pubgkt/common: bundles runtime + pubgkt-common ---
        val commonDir = outDir.resolve("common")
        commonDir.mkdirs()

        jsDir.listFiles()
            .filter { it.name.endsWith(".mjs") && it.name != "pubgkt-core.mjs" && it.name !in nonCommonMjsNames }
            .forEach { it.copyTo(commonDir.resolve(it.name)) }

        // Re-export KtList from stdlib so consumers can `import { KtList } from "@pubgkt/common"`
        commonDir.resolve("pubgkt-common.mjs").appendText(
            "\nexport { KtList } from './kotlin-kotlin-stdlib.mjs';\n"
        )

        File(dtsPaths.getValue("common")).copyTo(commonDir.resolve("pubgkt-common.d.mts"))

        commonDir.resolve("package.json").writeText("""
            {
              "name": "@pubgkt/common",
              "version": "$version",
              "type": "module",
              "main": "pubgkt-common.mjs",
              "types": "pubgkt-common.d.mts",
              "exports": {
                ".": { "import": "./pubgkt-common.mjs", "types": "./pubgkt-common.d.mts" },
                "./*.mjs": "./*.mjs"
              },
              "dependencies": { "ws": "8.18.3" }
            }
        """.trimIndent())

        // --- Non-common modules ---
        val ktListDeclaration = Regex(
            """(?s)^type Nullable<T>.*?declare function KtSingleton.*?\n""" +
            """export declare interface KtList<E>.*?\}\n""" +
            """export declare namespace KtList \{.*?\}\n"""
        )

        nonCommonModules.forEach { module ->
            val modDir = outDir.resolve(module)
            modDir.mkdirs()

            val srcMjs = jsDir.resolve("pubgkt-$module.mjs")
            val rewritten = srcMjs.readText()
                .replace("from './", "from '@pubgkt/common/")
            modDir.resolve("pubgkt-$module.mjs").writeText(rewritten)

            val dtsContent = File(dtsPaths.getValue(module)).readText()
            val patchedDts = ktListDeclaration.replaceFirst(dtsContent,
                "import { KtList } from '@pubgkt/common';\n" +
                "type Nullable<T> = T | null | undefined\n" +
                "declare function KtSingleton<T>(): T & (abstract new() => any);\n"
            )
            modDir.resolve("pubgkt-$module.d.mts").writeText(patchedDts)

            modDir.resolve("package.json").writeText("""
                {
                  "name": "@pubgkt/$module",
                  "version": "$version",
                  "type": "module",
                  "main": "pubgkt-$module.mjs",
                  "types": "pubgkt-$module.d.mts",
                  "exports": {
                    ".": { "import": "./pubgkt-$module.mjs", "types": "./pubgkt-$module.d.mts" }
                  },
                  "dependencies": { "@pubgkt/common": "$version" }
                }
            """.trimIndent())
        }

        // --- Run npm pack on each ---
        modules.forEach { module ->
            val pkgDir = outDir.resolve(module)
            val proc = ProcessBuilder("npm", "pack")
                .directory(pkgDir)
                .redirectErrorStream(true)
                .start()
            proc.waitFor()
            if (proc.exitValue() != 0) {
                throw GradleException("npm pack failed for $module: ${proc.inputStream.bufferedReader().readText()}")
            }
        }
    }
}

