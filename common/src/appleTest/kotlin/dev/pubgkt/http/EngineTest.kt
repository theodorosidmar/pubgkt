package dev.pubgkt.http

import io.ktor.client.engine.darwin.Darwin
import kotlin.test.Test
import kotlin.test.assertIs

class EngineTest {
    @Test
    fun engine() {
        assertIs<Darwin>(engine)
    }
}
