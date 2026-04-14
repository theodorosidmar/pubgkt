package pubgkt

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPlayerByAccountIdTest {

    private val engine = mockEngine(
        MockResponse(
            body = PLAYER_RESPONSE_JSON,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
        ),
    )
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getPlayerByAccountId("account.abc123")

        assertTrue(
            engine.requestHistory.last().url.encodedPath.endsWith("players/account.abc123"),
        )
    }

    @Test
    fun `deserializes single-resource response`() = runTest {
        val player = api.getPlayerByAccountId("account.abc123")

        assertEquals("account.abc123", player.id)
        assertEquals("PlayerOne", player.name)
    }
}
