package pubgkt

import io.ktor.http.fullPath
import kotlinx.coroutines.test.runTest
import pubgkt.http.get
import pubgkt.test.lastRequest
import pubgkt.test.mockEngine
import kotlin.test.Test
import kotlin.test.assertTrue

class ShardTest {
    private val engine = mockEngine()
    private val api = PubgApi(engine)

    @Test
    fun `should use shard at requests`() = runTest {
        api.client.get("test", Platform.STEAM)
        assertTrue(
            engine.lastRequest
                .url
                .fullPath
                .contains(Platform.STEAM.path),
        )

        api.client.get("test", PlatformRegion.PC_SA)
        assertTrue(
            engine.lastRequest
                .url
                .fullPath
                .contains(PlatformRegion.PC_SA.path),
        )
    }
}
