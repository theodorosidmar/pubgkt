package dev.pubgkt.test

@JsModule("node:fs")
private external object Fs {
    fun readFileSync(path: String, encoding: String): String
}

@JsModule("node:process")
private external object Process {
    val env: dynamic
}

actual fun readJsonResourceFile(filename: String): String {
    val moduleDir = (Process.env["PUBGKT_TEST_MODULE_DIR"] as? String)
        ?: error("Missing environment variable 'PUBGKT_TEST_MODULE_DIR'")

    val path = "$moduleDir/src/commonTest/resources/$filename.json"

    return try {
        Fs.readFileSync(path, "utf8")
    } catch (_: Throwable) {
        error("Missing $filename.json")
    }
}
