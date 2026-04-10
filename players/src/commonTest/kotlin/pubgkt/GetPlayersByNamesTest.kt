package pubgkt

import kotlinx.coroutines.test.runTest
import pubgkt.fixtures.PLAYERS_RESPONSE_JSON
import pubgkt.support.MockPubgApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetPlayersByNamesTest {

    private val mock = MockPubgApi(PLAYERS_RESPONSE_JSON)
    private val api = mock.api

    @Test
    fun `maps all players correctly`() = runTest {
        val players = api.getPlayersByNames("PlayerOne", "PlayerTwo")

        assertEquals(2, players.size)
        assertEquals("PlayerOne", players[0].name)
        assertEquals("PlayerTwo", players[1].name)
    }

    @Test
    fun `vararg overload delegates to list overload`() = runTest {
        val fromVararg = api.getPlayersByNames("PlayerOne")
        val fromList = api.getPlayersByNames(listOf("PlayerOne"))

        assertEquals(fromList.size, fromVararg.size)
    }

    @Test
    fun `throws for empty list`() = runTest {
        assertFailsWith<IllegalArgumentException> {
            api.getPlayersByNames(emptyList())
        }
    }

    @Test
    fun `caps request at 10 names`() = runTest {
        val names = (1..15).map { "Player$it" }
        api.getPlayersByNames(names)

        val queryParams = mock.lastRequest.url.parameters
        val sentNames = queryParams["filter[playerNames]"]!!.split(",")
        assertEquals(10, sentNames.size)
    }

    @Test
    fun `uses filter playerNames query parameter`() = runTest {
        api.getPlayersByNames("PlayerOne")

        val queryParams = mock.lastRequest.url.parameters
        assertEquals(true, queryParams.contains("filter[playerNames]"))
    }
}
