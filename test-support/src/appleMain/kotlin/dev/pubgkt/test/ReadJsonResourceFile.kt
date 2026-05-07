@file:OptIn(
    ExperimentalForeignApi::class,
    UnsafeNumber::class,
)

package dev.pubgkt.test

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.convert
import kotlinx.cinterop.toKString
import kotlinx.cinterop.usePinned
import platform.posix.SEEK_END
import platform.posix.fclose
import platform.posix.fopen
import platform.posix.fread
import platform.posix.fseek
import platform.posix.ftell
import platform.posix.getenv
import platform.posix.rewind
import platform.posix.size_t

actual fun readJsonResourceFile(filename: String): String {
    val moduleDir = getenv("PUBGKT_TEST_MODULE_DIR")?.toKString()
        ?: error("Missing environment variable 'PUBGKT_TEST_MODULE_DIR'")

    val path = "$moduleDir/src/commonTest/resources/$filename.json"

    return readUtf8File(path)
        ?: error("Missing resource '$path'")
}

internal fun readUtf8File(path: String): String? {
    val file = fopen(path, "rb") ?: return null

    return try {
        if (fseek(file, 0, SEEK_END) != 0) return null
        val size = ftell(file)
        if (size < 0) return null

        val maxSize = Int.MAX_VALUE.toLong()
        if (size > maxSize) return null

        rewind(file)

        val bytes = ByteArray(size.convert())
        if (bytes.isNotEmpty()) {
            val read = bytes.usePinned { pinned ->
                fread(pinned.addressOf(0), 1.convert(), bytes.size.convert(), file)
            }
            if (read != bytes.size.convert<size_t>()) return null
        }

        bytes.decodeToString()
    } finally {
        fclose(file)
    }
}
