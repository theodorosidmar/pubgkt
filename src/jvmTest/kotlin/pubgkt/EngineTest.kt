package pubgkt

import io.ktor.client.engine.cio.CIO
import kotlin.test.Test
import kotlin.test.assertEquals

class EngineTest {
    @Test
    fun `should have CIO engine`() {
        assertEquals(CIO, engine)
    }
}
