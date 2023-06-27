package pubgkt

import io.ktor.client.engine.js.Js
import kotlin.test.Test
import kotlin.test.assertEquals

class EngineTest {
    @Test
    fun engine() {
        assertEquals(Js, engine)
    }
}
