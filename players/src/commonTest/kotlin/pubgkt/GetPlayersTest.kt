package pubgkt

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Shared test behaviour for batch player endpoints (`getPlayersById`, `getPlayersByNames`).
 *
 * Concrete subclasses supply the query-parameter name and delegate calls to the
 * appropriate [PubgApi] extension function.
 */
abstract class GetPlayersTest {

    private val engine = mockEngine(
        MockResponse(
            body = PLAYERS_RESPONSE_JSON,
            headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
        ),
    )
    private val api = PubgApi(engine = engine)

    /** The query-parameter key used by the endpoint (e.g. `"filter[playerIds]"`). */
    protected abstract val queryParameterName: String

    /** Calls the endpoint under test with a vararg of values. */
    protected abstract suspend fun PubgApi.fetchPlayers(vararg values: String): List<Player>

    /** Calls the endpoint under test with a list of values. */
    protected abstract suspend fun PubgApi.fetchPlayers(values: List<String>): List<Player>

    @Test
    fun `maps all players correctly`() = runTest {
        val players = api.fetchPlayers("value1", "value2")

        assertEquals(2, players.size)
        assertEquals("PlayerOne", players[0].name)
        assertEquals("PlayerTwo", players[1].name)
    }

    @Test
    fun `vararg overload delegates to list overload`() = runTest {
        val fromVararg = api.fetchPlayers("value1")
        val fromList = api.fetchPlayers(listOf("value1"))

        assertEquals(fromList.size, fromVararg.size)
    }

    @Test
    fun `returns empty for empty list`() = runTest {
        assertEquals(emptyList(), api.fetchPlayers(emptyList()))
        assertEquals(emptyList(), api.fetchPlayers())
    }

    @Test
    fun `caps request at 10 values`() = runTest {
        val values = (1..15).map { "value$it" }
        api.fetchPlayers(values)

        val sent = engine.requestHistory.last().url.parameters[queryParameterName]!!.split(",")
        assertEquals(10, sent.size)
    }

    @Test
    fun `uses correct query parameter`() = runTest {
        api.fetchPlayers("value1")

        assertTrue(engine.requestHistory.last().url.parameters.contains(queryParameterName))
    }
}
