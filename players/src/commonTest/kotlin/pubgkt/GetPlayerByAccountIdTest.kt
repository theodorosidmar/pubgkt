package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPlayerByAccountIdTest {

    private val engine = mockEngine {
        body = PLAYER_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getPlayerByAccountId("account.abc123")

        assertTrue(
            engine.lastRequest.url.encodedPath.endsWith("players/account.abc123"),
        )
    }

    @Test
    fun `deserializes single-resource response`() = runTest {
        val player = api.getPlayerByAccountId("account.abc123")

        assertEquals("account.abc123", player.id)
        assertEquals("PlayerOne", player.name)
    }
}
