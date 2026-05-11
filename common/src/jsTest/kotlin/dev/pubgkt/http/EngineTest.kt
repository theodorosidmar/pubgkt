package dev.pubgkt.http

import io.ktor.client.engine.js.Js
import kotlin.test.Test
import kotlin.test.assertIs

class EngineTest {
    @Test
    fun engine() {
        assertIs<Js>(engine)
    }
}
