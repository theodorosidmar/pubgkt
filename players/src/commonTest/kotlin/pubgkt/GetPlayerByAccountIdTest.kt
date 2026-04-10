package pubgkt

import kotlinx.coroutines.test.runTest
import pubgkt.fixtures.PLAYER_RESPONSE_JSON
import pubgkt.support.MockPubgApi
import kotlin.test.Test
import kotlin.test.assertEquals

class GetPlayerByAccountIdTest {

    private val mock = MockPubgApi(PLAYER_RESPONSE_JSON)
    private val api = mock.api

    @Test
    fun `maps all fields correctly`() = runTest {
        val player = api.getPlayerByAccountId("account.abc123")

        assertEquals("account.abc123", player.accountId)
        assertEquals("PlayerOne", player.name)
        assertEquals("14.1", player.patchVersion)
        assertEquals("bluehole-pubg", player.titleId)
        assertEquals(listOf("match-id-1", "match-id-2"), player.matchIds)
    }

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getPlayerByAccountId("account.abc123")

        val path = mock.lastRequest.url.encodedPath
        assertEquals(true, path.endsWith("players/account.abc123"), "Expected path to end with players/account.abc123 but was $path")
    }
}
