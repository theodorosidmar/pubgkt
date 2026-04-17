package pubgkt

import io.ktor.client.request.get
import io.ktor.http.fullPath
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class PlatformDefaultRequestTest {
    private val engine = mockEngine()
    private val api = PubgApi(engine, apiKey = "")

    @Test
    fun `should update platform and use it at requests`() = runTest {
        with(api) {
            client.get("test")
            assertTrue(
                engine.lastRequest
                    .url
                    .fullPath
                    .contains(api.platform.name.lowercase()),
            )

            platform = Platform.XBOX
            client.get("test")
            assertTrue(
                engine.lastRequest
                    .url
                    .fullPath
                    .contains(api.platform.name.lowercase()),
            )
        }
    }
}
