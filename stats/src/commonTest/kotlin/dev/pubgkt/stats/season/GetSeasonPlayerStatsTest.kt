package dev.pubgkt.stats.season

import dev.pubgkt.PubgApi
import dev.pubgkt.test.lastRequest
import dev.pubgkt.test.mockEngine
import io.ktor.http.HttpMethod
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GetSeasonPlayerStatsTest {
    private val engine =
        mockEngine {
            body = SEASON_PLAYER_RESPONSE_JSON
        }
    private val api = PubgApi(engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getSeasonStatsByAccountId("account.abc123", "season123")

        val request = engine.lastRequest

        assertEquals(HttpMethod.Get, request.method)
        assertTrue(
            request
                .url
                .encodedPath
                .endsWith("shards/steam/players/account.abc123/seasons/season123"),
        )
    }

    @Test
    fun `deserializes a lifetime player stats response`() = runTest {
        val seasonPlayerStats = api.getSeasonStatsByAccountId("account.abc123", "season123")

        assertEquals(0.0, seasonPlayerStats.bestRankPoint)
        assertEquals("account.91186dcca3cb4ad198fac1e4ab1d5b80", seasonPlayerStats.playerId)
        assertEquals("division.bro.official.pc-2018-41", seasonPlayerStats.seasonId)
        assertNotNull(seasonPlayerStats.soloTpp)
        assertNotNull(seasonPlayerStats.duoTpp)
        assertNotNull(seasonPlayerStats.squadFpp)
        assertNotNull(seasonPlayerStats.soloFpp)
        assertNotNull(seasonPlayerStats.duoFpp)
        assertEquals(73, seasonPlayerStats.squadTpp.assists)
        assertEquals(179, seasonPlayerStats.squadTpp.boosts)
        assertEquals(243, seasonPlayerStats.squadTpp.dbnos)
        assertEquals(5, seasonPlayerStats.squadTpp.dailyKills)
        assertEquals(0, seasonPlayerStats.squadTpp.dailyWins)
        assertEquals(37483.324, seasonPlayerStats.squadTpp.damageDealt)
        assertEquals(9, seasonPlayerStats.squadTpp.days)
        assertEquals(88, seasonPlayerStats.squadTpp.headshotKills)
        assertEquals(163, seasonPlayerStats.squadTpp.heals)
        assertEquals(298, seasonPlayerStats.squadTpp.kills)
        assertEquals(244.99663, seasonPlayerStats.squadTpp.longestKill)
        assertEquals(1597.0, seasonPlayerStats.squadTpp.longestTimeSurvived)
        assertEquals(47, seasonPlayerStats.squadTpp.losses)
        assertEquals(4, seasonPlayerStats.squadTpp.maxKillStreaks)
        assertEquals(1597.0, seasonPlayerStats.squadTpp.mostSurvivalTime)
        assertEquals(11, seasonPlayerStats.squadTpp.revives)
        assertEquals(96965.54, seasonPlayerStats.squadTpp.rideDistance)
        assertEquals(1, seasonPlayerStats.squadTpp.roadKills)
        assertEquals(22, seasonPlayerStats.squadTpp.roundMostKills)
        assertEquals(50, seasonPlayerStats.squadTpp.roundsPlayed)
        assertEquals(3, seasonPlayerStats.squadTpp.suicides)
        assertEquals(0.0, seasonPlayerStats.squadTpp.swimDistance)
        assertEquals(0, seasonPlayerStats.squadTpp.teamKills)
        assertEquals(30792.0, seasonPlayerStats.squadTpp.timeSurvived)
        assertEquals(18, seasonPlayerStats.squadTpp.top10s)
        assertEquals(2, seasonPlayerStats.squadTpp.vehicleDestroys)
        assertEquals(72183.35, seasonPlayerStats.squadTpp.walkDistance)
        assertEquals(412, seasonPlayerStats.squadTpp.weaponsAcquired)
        assertEquals(83, seasonPlayerStats.squadTpp.weeklyKills)
        assertEquals(1, seasonPlayerStats.squadTpp.weeklyWins)
        assertEquals(5, seasonPlayerStats.squadTpp.wins)
    }
}
