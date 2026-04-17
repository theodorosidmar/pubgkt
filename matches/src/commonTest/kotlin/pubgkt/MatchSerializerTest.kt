package pubgkt

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class MatchSerializerTest {

    @Test
    fun `maps all fields correctly`() {
        val element = Json.parseToJsonElement(MATCH_TDM_RESOURCE_JSON)
        val match = json.decodeFromJsonElement(MatchSerializer, element)

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
