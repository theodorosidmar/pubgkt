package dev.pubgkt.test

actual fun readJsonResourceFile(filename: String): String =
    {}::class.java
        .getResourceAsStream("/$filename.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing $filename.json")
