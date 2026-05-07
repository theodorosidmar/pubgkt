package dev.pubgkt.http

import io.ktor.client.engine.android.Android
import kotlin.test.Test
import kotlin.test.assertIs

class EngineTest {
    @Test
    fun engine() {
        assertIs<Android>(engine)
    }
}
