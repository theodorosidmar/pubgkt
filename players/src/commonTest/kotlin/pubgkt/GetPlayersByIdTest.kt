package pubgkt

import kotlinx.coroutines.test.runTest
import pubgkt.fixtures.PLAYERS_RESPONSE_JSON
import pubgkt.support.MockPubgApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetPlayersByIdTest {

    private val mock = MockPubgApi(PLAYERS_RESPONSE_JSON)
    private val api = mock.api

    @Test
    fun `maps all players correctly`() = runTest {
        val players = api.getPlayersById("account.abc123", "account.def456")

        assertEquals(2, players.size)
        assertEquals("account.abc123", players[0].accountId)
        assertEquals("PlayerOne", players[0].name)
        assertEquals("account.def456", players[1].accountId)
        assertEquals("PlayerTwo", players[1].name)
    }

    @Test
    fun `vararg overload delegates to list overload`() = runTest {
        val fromVararg = api.getPlayersById("account.abc123")
        val fromList = api.getPlayersById(listOf("account.abc123"))

        assertEquals(fromList.size, fromVararg.size)
    }

    @Test
    fun `throws for empty list`() = runTest {
        assertFailsWith<IllegalArgumentException> {
            api.getPlayersById(emptyList())
        }
    }

    @Test
    fun `caps request at 10 ids`() = runTest {
        val ids = (1..15).map { "account.id$it" }
        api.getPlayersById(ids)

        val queryParams = mock.lastRequest.url.parameters
        val sentIds = queryParams["filter[playerIds]"]!!.split(",")
        assertEquals(10, sentIds.size)
    }

    @Test
    fun `uses filter playerIds query parameter`() = runTest {
        api.getPlayersById("account.abc123")

        val queryParams = mock.lastRequest.url.parameters
        assertEquals(true, queryParams.contains("filter[playerIds]"))
    }
}
