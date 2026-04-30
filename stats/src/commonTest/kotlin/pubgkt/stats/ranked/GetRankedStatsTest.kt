package pubgkt.stats.ranked

import io.ktor.http.HttpMethod
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import pubgkt.PubgApi
import pubgkt.lastRequest
import pubgkt.mockEngine

class GetRankedStatsTest {

    private val engine = mockEngine {
        body = RANKED_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getRankedStatsByAccountIdAndSeasonId("account.abc123", "season123")

        val request = engine.lastRequest

        assertEquals(HttpMethod.Get, request.method)
        assertTrue(
            request
                .url
                .encodedPath
                .endsWith("shards/steam/players/account.abc123/seasons/season123/ranked"),
        )
    }

    @Test
    fun `deserializes a ranked stats response`() = runTest {
        val rankedStats = api.getRankedStatsByAccountIdAndSeasonId("account.abc123", "season123")

        assertEquals("account.82bad0072f31455d8d9f8d834da2f2f3", rankedStats.playerId)
        assertEquals("division.bro.official.pc-2018-41", rankedStats.seasonId)
        assertEquals(2647, rankedStats.currentRankPoint)
        assertEquals(2647, rankedStats.bestRankPoint)
        assertEquals(Tier("Crystal", 4), rankedStats.currentTier)
        assertEquals(Tier("Crystal", 3), rankedStats.bestTier)
        assertEquals(17, rankedStats.roundsPlayed)
        assertEquals(5.9411764, rankedStats.avgRank)
        assertEquals(0.7647059, rankedStats.top10Ratio)
        assertEquals(0.47058824, rankedStats.winRatio)
        assertEquals(66, rankedStats.assists)
        assertEquals(8, rankedStats.wins)
        assertEquals(0.0, rankedStats.kda)
        assertEquals(4.8235292, rankedStats.avgKill)
        assertEquals(82, rankedStats.kills)
        assertEquals(12, rankedStats.deaths)
        assertEquals(12376.169, rankedStats.damageDealt)
        assertEquals(73, rankedStats.dbnos)
    }
}
