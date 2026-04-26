package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GetMatchByIdTest {
    private val engine = mockEngine {
        body = MATCH_TDM_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine, apiKey = "")

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getMatchById("a131e486-5bcf-4c2e-aa5a-515489ee57aa")

        assertTrue(
            engine.lastRequest
                .url
                .encodedPath
                .endsWith("/shards/steam/matches/a131e486-5bcf-4c2e-aa5a-515489ee57aa"),
        )
        assertFalse(
            engine.lastRequest.headers.contains("Authorization")
        )
    }

    @Test
    fun `deserializes a tdm match response`() = runTest {
        val match = api.getMatchById("a131e486-5bcf-4c2e-aa5a-515489ee57aa")

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
        assertEquals(2, match.participants.size)
    }

    @Test
    fun `deserializes a squad match response`() = runTest {
        val engine = mockEngine {
            body = MATCH_SQUAD_RESPONSE_JSON
        }
        val api = PubgApi(engine = engine, apiKey = "")
        val match = api.getMatchById("4a57679f-6b4b-4d4c-85ea-72e2e8272c77")

        assertEquals("4a57679f-6b4b-4d4c-85ea-72e2e8272c77", match.id)
        assertEquals("bluehole-pubg", match.titleId)
        assertEquals("steam", match.platform.name.lowercase())
        assertEquals(false, match.isCustomMatch)
        assertEquals("official", match.matchType.name.lowercase())
        assertEquals("squad", match.gameMode.name.lowercase())
        assertEquals("neon_main", match.mapName.name.lowercase())
        assertEquals("progress", match.seasonState.name.lowercase())
        assertEquals("2026-04-15T21:45:33Z", match.createdAt.toString())
        assertEquals(1555, match.duration)
        assertEquals(28, match.participants.size)

        val teamToBeTested = match.participants.first { it.teamId == 18 }
        assertEquals(18, teamToBeTested.teamId)
        assertEquals(1, teamToBeTested.rank)
        assertEquals(true, teamToBeTested.won)
        assertEquals(3, teamToBeTested.players.size)

        val playerToBeTested = teamToBeTested.players.first { it.name == "sparkingg" }
        assertEquals(11, playerToBeTested.dbnos)
        assertEquals(6, playerToBeTested.assists)
        assertEquals(9, playerToBeTested.boosts)
        assertEquals(2129.0615, playerToBeTested.damageDealt)
        assertEquals("alive", playerToBeTested.deathType.name.lowercase())
        assertEquals(5, playerToBeTested.headshotKills)
        assertEquals(4, playerToBeTested.heals)
        assertEquals(1, playerToBeTested.killPlace)
        assertEquals(3, playerToBeTested.killStreaks)
        assertEquals(15, playerToBeTested.kills)
        assertEquals(199.76648, playerToBeTested.longestKill)
        assertEquals("sparkingg", playerToBeTested.name)
        assertEquals( "account.91186dcca3cb4ad198fac1e4ab1d5b80", playerToBeTested.id)
        assertEquals(1, playerToBeTested.revives)
        assertEquals(8827.315, playerToBeTested.rideDistance)
        assertEquals(0, playerToBeTested.roadKills)
        assertEquals(0.0, playerToBeTested.swimDistance)
        assertEquals(0, playerToBeTested.teamKills)
        assertEquals(1555, playerToBeTested.timeSurvived)
        assertEquals(0, playerToBeTested.vehicleDestroys)
        assertEquals(3300.7288, playerToBeTested.walkDistance)
        assertEquals(24, playerToBeTested.weaponsAcquired)
        assertEquals(1, playerToBeTested.winPlace)
    }

    @Test
    fun `deserializes null when included is not present`() = runTest {
        val engine = mockEngine {
            body = MATCH_MISSING_INCLUDED_RESPONSE_JSON
        }
        val api = PubgApi(engine = engine, apiKey = "")
        val match = api.getMatchById("4a57679f-6b4b-4d4c-85ea-72e2e8272c77")

        assertTrue(match.participants.isEmpty())
    }
}
