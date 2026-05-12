val sampleModules =
    listOf(
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

val frameworkDir =
    rootProject
        .project(":core")
        .layout
        .buildDirectory
        .dir("bin/macosArm64/debugFramework")

val buildFramework =
    tasks.register("buildFramework") {
        description = "Build framework from core project"
        dependsOn(":core:linkDebugFrameworkMacosArm64")
    }

sampleModules.forEach { module ->
    val compile =
        tasks.register<Exec>("compile${module}Swift") {
            group = "swift samples"
            description = "Compile Swift code for $module"
            dependsOn(buildFramework)

            val swiftFile = file("src/${module}Sample.swift")
            val outputFile =
                layout.buildDirectory
                    .file("${module}Sample")
                    .get()
                    .asFile

            inputs.file(swiftFile)
            inputs.dir(frameworkDir)
            outputs.file(outputFile)

            doFirst { outputFile.parentFile.mkdirs() }

            commandLine(
                "swiftc",
                "-F",
                frameworkDir.get().asFile.absolutePath,
                "-framework",
                "PubgKt",
                swiftFile.absolutePath,
                "-o",
                outputFile.absolutePath,
            )
        }

    tasks.register<Exec>("run${module}Swift") {
        group = "swift samples"
        description = "Run Swift code for $module"
        dependsOn(compile)

        val outputFile =
            layout.buildDirectory
                .file("${module}Sample")
                .get()
                .asFile
        executable = outputFile.absolutePath

        if (project.hasProperty("args")) {
            args = (project.property("args") as String).split(" ")
        }
    }
}
