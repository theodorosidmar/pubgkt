package dev.pubgkt.test

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ReadJsonResourceFileTest {
    @Test
    fun `should read json resource`() {
        val jsonString: String = readJsonResourceFile("resource")
        assertEquals("""
            {
              "name": "pubgkt"
            }
            """.trimIndent(),
            jsonString.trimIndent(),
        )
    }

    @Test
    fun `should throw if file is missing`() {
        assertFailsWith<IllegalStateException> {
            readJsonResourceFile("missing")
        }
    }
}
