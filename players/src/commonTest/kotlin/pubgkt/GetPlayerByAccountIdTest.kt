package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPlayerByAccountIdTest {

    private val mock = MockPubgApi(PLAYER_RESPONSE_JSON)
    private val api = mock.api

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getPlayerByAccountId("account.abc123")

        assertTrue(
            mock.lastRequest.url.encodedPath.endsWith("players/account.abc123"),
        )
    }

    @Test
    fun `deserializes single-resource response`() = runTest {
        val player = api.getPlayerByAccountId("account.abc123")

        assertEquals("account.abc123", player.id)
        assertEquals("PlayerOne", player.name)
    }
}
