package pubgkt

import io.ktor.http.HttpMethod
import kotlinx.coroutines.test.runTest
import pubgkt.test.lastRequest
import pubgkt.test.mockEngine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SeasonTest {
    private val engine =
        mockEngine {
            body = SEASONS_RESPONSE_JSON
        }
    private val api = PubgApi(engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.seasons(Platform.STEAM)

        val request = engine.lastRequest

        assertEquals(HttpMethod.Get, request.method)
        assertTrue(
            request
                .url
                .encodedPath
                .endsWith("shards/steam/seasons"),
        )
    }

    @Test
    fun `deserializes seasons response`() = runTest {
        val seasons = api.seasons(Platform.STEAM)
        assertEquals(2, seasons.size)
        val currentSeason = seasons.currentOrNull()
        assertEquals("division.bro.official.pc-2018-41", currentSeason?.id)
        assertEquals(true, currentSeason?.isCurrentSeason)
        assertEquals(false, currentSeason?.isOffSeason)
    }
}

private const val SEASONS_RESPONSE_JSON = """
{
  "data": [
    {
      "type": "season",
      "id": "division.bro.official.pc-2018-41",
      "attributes": {
        "isCurrentSeason": true,
        "isOffseason": false
      }
    },
    {
      "type": "season",
      "id": "division.bro.official.pc-2018-40",
      "attributes": {
        "isOffseason": false,
        "isCurrentSeason": false
      }
    }
  ],
  "links": {
    "self": "https://api.pubg.com/shards/pc-na/seasons"
  },
  "meta": {}
}
"""
