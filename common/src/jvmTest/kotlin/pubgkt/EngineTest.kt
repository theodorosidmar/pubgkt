package pubgkt

import io.ktor.client.engine.cio.CIO
import kotlin.test.Test
import kotlin.test.assertIs

class EngineTest {
    @Test
    fun `engine should be CIO`() {
        assertIs<CIO>(engine)
    }
}
