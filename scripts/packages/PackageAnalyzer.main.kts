#!/usr/bin/env kotlin

import java.io.File
import java.util.regex.Pattern

enum class SourceScope {
    PRODUCTION,
    TEST,
}

data class GraphTarget(
    val scope: SourceScope,
    val label: String,
    val dotFile: File,
    val pngFile: File,
)

val packagePattern: Pattern = Pattern.compile("^\\s*package\\s+([\\w.]+)\\s*$")
val importPattern: Pattern = Pattern.compile("^\\s*import\\s+([\\w.]+)\\s*$")
val excludedTopLevelDirectories = setOf("samples")

val workingDirectory: File = File(System.getProperty("user.dir")).canonicalFile
val projectRoot = findProjectRoot(workingDirectory)
val scriptFile = resolvePath(__FILE__.path, projectRoot)
val scriptDir: File? = scriptFile.parentFile
val root =
    args
        .getOrNull(0)
        ?.let { path -> resolvePath(path, workingDirectory) }
        ?: projectRoot
val outputBasePath =
    args
        .getOrNull(1)
        ?.let { path -> resolvePath(path, workingDirectory) }
        ?: scriptDir?.resolve("packages")

require(root.exists() && root.isDirectory) {
    "Root directory does not exist or is not a directory: ${root.absolutePath}"
}

val sourceFiles = collectSourceFiles(root)
val targets =
    listOf(
        GraphTarget(
            scope = SourceScope.PRODUCTION,
            label = "production",
            dotFile = File("${outputBasePath?.absolutePath}-production.dot"),
            pngFile = File("${outputBasePath?.absolutePath}-production.png"),
        ),
        GraphTarget(
            scope = SourceScope.TEST,
            label = "tests",
            dotFile = File("${outputBasePath?.absolutePath}-test.dot"),
            pngFile = File("${outputBasePath?.absolutePath}-test.png"),
        ),
    )

targets.forEach { target ->
    val fileTree = buildFileTree(sourceFiles, target.scope)
    generateDotFile(target.dotFile, fileTree, target.label)
    createImageOutput(target.dotFile, target.pngFile)
}

println("Scanned root: ${root.absolutePath}")
targets.forEach { target ->
    println("${target.label.replaceFirstChar(Char::uppercaseChar)} DOT written: ${target.dotFile.absolutePath}")
    println("${target.label.replaceFirstChar(Char::uppercaseChar)} PNG written: ${target.pngFile.absolutePath}")
}

fun resolvePath(
    path: String,
    baseDir: File,
): File =
    File(path).let { file ->
        if (file.isAbsolute) file.canonicalFile else baseDir.resolve(path).canonicalFile
    }

fun findProjectRoot(startDir: File): File {
    generateSequence(startDir) { it.parentFile }
        .firstOrNull { candidate ->
            candidate.resolve("settings.gradle.kts").isFile || candidate.resolve("gradlew").isFile
        }?.let { return it }

    return startDir
}

fun collectSourceFiles(rootDir: File): List<File> =
    rootDir
        .walkTopDown()
        .onEnter { dir -> !isBuildDir(dir, rootDir) && !isExcludedTopLevelDir(dir, rootDir) }
        .filter { file -> file.isFile && file.extension == "kt" && classifySourceScope(file, rootDir) != null }
        .toList()

fun isExcludedTopLevelDir(
    dir: File,
    rootDir: File,
): Boolean {
    if (!dir.isDirectory) return false
    val rel = dir.relativeTo(rootDir).invariantSeparatorsPath
    if (rel.isEmpty() || rel == ".") return false
    val topLevel = rel.substringBefore('/')
    return topLevel in excludedTopLevelDirectories
}

fun buildFileTree(
    files: List<File>,
    scope: SourceScope,
): Map<String, Set<String>> =
    files
        .asSequence()
        .filter { file -> classifySourceScope(file, root) == scope }
        .map { file -> file.extractPackageAndImports() }
        .groupingBy { it.first }
        .fold(emptySet()) { acc, (_, importedPackages) -> acc + importedPackages }

fun classifySourceScope(
    file: File,
    rootDir: File,
): SourceScope? {
    val path = file.relativeTo(rootDir).invariantSeparatorsPath
    val srcIndex = path.indexOf("/src/")
    if (srcIndex == -1) return null

    val sourceSetSegment = path.substring(srcIndex + 5).substringBefore('/')
    return when {
        sourceSetSegment == "main" || sourceSetSegment.endsWith("Main") -> SourceScope.PRODUCTION
        sourceSetSegment == "test" || sourceSetSegment.endsWith("Test") -> SourceScope.TEST
        else -> null
    }
}

fun isBuildDir(
    dir: File,
    rootDir: File,
): Boolean {
    if (!dir.isDirectory) return false
    val rel = dir.relativeTo(rootDir).invariantSeparatorsPath
    return rel == "build" || rel.endsWith("/build")
}

fun File.extractPackageAndImports(): Pair<String, Set<String>> {
    val lines = readLines()

    val packageName =
        lines.firstNotNullOfOrNull { line ->
            packagePattern.matcher(line).takeIf { it.find() }?.group(1)
        } ?: "<root>"

    val importedPackages =
        lines
            .mapNotNull { line ->
                val m = importPattern.matcher(line)
                if (!m.find()) return@mapNotNull null
                val fqName = m.group(1)
                if (!fqName.startsWith("pubgkt.")) return@mapNotNull null
                fqName.substringBeforeLast('.', missingDelimiterValue = fqName)
            }.toSet()

    return packageName to importedPackages
}

fun generateDotFile(
    dotFile: File,
    fileTree: Map<String, Set<String>>,
    graphLabel: String,
) {
    dotFile.printWriter().use { out ->
        val allPackages = (fileTree.keys + fileTree.values.flatten()).toSortedSet()
        val packagesByCluster = allPackages.groupBy(::clusterIdForPackage).toSortedMap()
        val edges =
            buildList {
                fileTree.forEach { (pkg, imports) ->
                    imports.forEach { importedPkg ->
                        // Keep the semantic direction: dependent -> dependency.
                        add(pkg to importedPkg)
                    }
                }
            }
        val layers = computeLayers(allPackages, edges)

        out.println("digraph KotlinPackageDependencies {")
        out.println("rankdir=BT;")
        out.println("layout=dot;")
        out.println("ranksep=1.0;")
        out.println("nodesep=0.45;")
        out.println("splines=ortho;")
        out.println("concentrate=true;")
        out.println("newrank=true;")
        out.println("compound=true;")
        out.println("label=\"PUBGKT package dependencies (${graphLabel.replaceFirstChar(Char::uppercaseChar)})\";")
        out.println("labelloc=t;")
        out.println("fontsize=20;")
        out.println("node [shape=box];")

        packagesByCluster.forEach { (clusterId, packages) ->
            out.println("subgraph \"cluster_$clusterId\" {")
            out.println("label=\"${clusterLabel(clusterId)}\";")
            out.println("style=rounded;")
            out.println("color=\"${clusterColor(clusterId)}\";")

            packages.forEach { pkg ->
                out.println("\"$pkg\";")
            }

            out.println("}")
        }

        layers.entries
            .groupBy({ it.value }, { it.key })
            .toSortedMap()
            .forEach { (_, pkgsInLayer) ->
                val nodes = pkgsInLayer.sorted().joinToString("; ") { "\"$it\"" }
                out.println("{ rank=same; $nodes; }")
            }

        edges.forEach { (from, to) ->
            out.println("\"$from\" -> \"$to\";")
        }

        out.println("}")
    }
}

fun computeLayers(
    nodes: Set<String>,
    edges: List<Pair<String, String>>,
): Map<String, Int> {
    val predecessors = nodes.associateWith { mutableSetOf<String>() }
    edges.forEach { (from, to) ->
        predecessors.getValue(to).add(from)
    }

    val memo = mutableMapOf<String, Int>()

    fun depth(
        node: String,
        visiting: MutableSet<String>,
    ): Int {
        memo[node]?.let { return it }
        if (!visiting.add(node)) {
            // Break cycles gracefully; DOT will still render the cycle.
            return 0
        }

        val parentDepth =
            predecessors
                .getValue(node)
                .maxOfOrNull { parent -> depth(parent, visiting) + 1 }
                ?: 0

        visiting.remove(node)
        memo[node] = parentDepth
        return parentDepth
    }

    nodes.forEach { node ->
        depth(node, mutableSetOf())
    }

    return memo
}

fun clusterIdForPackage(packageName: String): String =
    when {
        packageName == "<root>" -> "misc"

        packageName == "pubgkt" || packageName.startsWith("pubgkt.http") || packageName.startsWith("pubgkt.jsonapi") ||
            packageName.startsWith("pubgkt.ratelimit") -> "shared"

        packageName.startsWith("pubgkt.test") -> "testing"

        packageName.startsWith("pubgkt.") -> packageName.substringAfter("pubgkt.").substringBefore('.')

        else -> "misc"
    }

fun clusterLabel(clusterId: String): String =
    when (clusterId) {
        "shared" -> "Shared infrastructure"
        "testing" -> "Testing"
        "misc" -> "Misc"
        else -> clusterId.replaceFirstChar(Char::uppercaseChar)
    }

fun clusterColor(clusterId: String): String =
    when (clusterId) {
        "shared" -> "#6C8EBF"
        "testing" -> "#B85450"
        "misc" -> "#999999"
        else -> "#82B366"
    }

fun createImageOutput(
    dotFile: File,
    outputPngFile: File,
) {
    val process =
        ProcessBuilder(
            "dot",
            "-Tpng",
            dotFile.absolutePath,
            "-o",
            outputPngFile.absolutePath,
        ).redirectErrorStream(true).start()

    val output = process.inputStream.bufferedReader().readText()
    val exit = process.waitFor()
    check(exit == 0) { "Graphviz 'dot' failed (exit=$exit)\n$output" }
}
