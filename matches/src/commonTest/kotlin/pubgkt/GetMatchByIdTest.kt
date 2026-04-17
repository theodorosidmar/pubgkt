package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetMatchByIdTest {
    private val engine = mockEngine {
        body = MATCH_TDM_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getMatchById("a131e486-5bcf-4c2e-aa5a-515489ee57aa")

        assertTrue(
            engine.lastRequest.url.encodedPath.endsWith("a131e486-5bcf-4c2e-aa5a-515489ee57aa"),
        )
    }

    @Test
    fun `deserializes single-resource response`() = runTest {
        val match = api.getMatchById("matches/a131e486-5bcf-4c2e-aa5a-515489ee57aa")

        assertEquals("a131e486-5bcf-4c2e-aa5a-515489ee57aa", match.id)
        assertEquals("bluehole-pubg", match.titleId)
        assertEquals("steam", match.platform.name.lowercase())
        assertEquals(false, match.isCustomMatch)
        assertEquals("arcade", match.matchType.name.lowercase())
        assertEquals("tdm", match.gameMode.name.lowercase())
        assertEquals("tiger_main", match.mapName.name.lowercase())
        assertEquals("progress", match.seasonState.name.lowercase())
        assertEquals("2026-04-07T22:26:23Z", match.createdAt.toString())
        assertEquals(376, match.duration)
    }
}
