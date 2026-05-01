package pubgkt.stats.lifetime

import io.ktor.http.HttpMethod
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import pubgkt.PubgApi
import pubgkt.test.lastRequest
import pubgkt.test.mockEngine

class GetLifetimePlayerStatsTest {

    private val engine = mockEngine {
        body = LIFETIME_PLAYER_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getLifetimeStatsByAccountId("account.abc123")

        val request = engine.lastRequest

        assertEquals(HttpMethod.Get, request.method)
        assertTrue(
            request
                .url
                .encodedPath
                .endsWith("shards/steam/players/account.abc123/seasons/lifetime"),
        )
    }

    @Test
    fun `deserializes a lifetime player stats response`() = runTest {
        val lifetimePlayerStats = api.getLifetimeStatsByAccountId("account.91186dcca3cb4ad198fac1e4ab1d5b80")

        assertEquals(2906.1135, lifetimePlayerStats.bestRankPoint)
        assertEquals("account.91186dcca3cb4ad198fac1e4ab1d5b80", lifetimePlayerStats.playerId)
        assertNotNull(lifetimePlayerStats.soloTpp)
        assertNotNull(lifetimePlayerStats.duoTpp)
        assertNotNull(lifetimePlayerStats.squadTpp)
        assertNotNull(lifetimePlayerStats.soloFpp)
        assertNotNull(lifetimePlayerStats.duoFpp)
        assertEquals(6408, lifetimePlayerStats.squadFpp.assists)
        assertEquals(33353, lifetimePlayerStats.squadFpp.boosts)
        assertEquals(53791, lifetimePlayerStats.squadFpp.dbnos)
        assertEquals(60, lifetimePlayerStats.squadFpp.dailyKills)
        assertEquals(2, lifetimePlayerStats.squadFpp.dailyWins)
        assertEquals(8210634.0, lifetimePlayerStats.squadFpp.damageDealt)
        assertEquals(1212, lifetimePlayerStats.squadFpp.days)
        assertEquals(18269, lifetimePlayerStats.squadFpp.headshotKills)
        assertEquals(31134, lifetimePlayerStats.squadFpp.heals)
        assertEquals(62641, lifetimePlayerStats.squadFpp.kills)
        assertEquals(707.8984, lifetimePlayerStats.squadFpp.longestKill)
        assertEquals(1978.0, lifetimePlayerStats.squadFpp.longestTimeSurvived)
        assertEquals(13646, lifetimePlayerStats.squadFpp.losses)
        assertEquals(8, lifetimePlayerStats.squadFpp.maxKillStreaks)
        assertEquals(1978.0, lifetimePlayerStats.squadFpp.mostSurvivalTime)
        assertEquals(1985, lifetimePlayerStats.squadFpp.revives)
        assertEquals(11483215.0, lifetimePlayerStats.squadFpp.rideDistance)
        assertEquals(170, lifetimePlayerStats.squadFpp.roadKills)
        assertEquals(34, lifetimePlayerStats.squadFpp.roundMostKills)
        assertEquals(14160, lifetimePlayerStats.squadFpp.roundsPlayed)
        assertEquals(207, lifetimePlayerStats.squadFpp.suicides)
        assertEquals(7358.4614, lifetimePlayerStats.squadFpp.swimDistance)
        assertEquals(163, lifetimePlayerStats.squadFpp.teamKills)
        assertEquals(6368624.0, lifetimePlayerStats.squadFpp.timeSurvived)
        assertEquals(2487, lifetimePlayerStats.squadFpp.top10s)
        assertEquals(227, lifetimePlayerStats.squadFpp.vehicleDestroys)
        assertEquals(8597544.0, lifetimePlayerStats.squadFpp.walkDistance)
        assertEquals(61481, lifetimePlayerStats.squadFpp.weaponsAcquired)
        assertEquals(60, lifetimePlayerStats.squadFpp.weeklyKills)
        assertEquals(2, lifetimePlayerStats.squadFpp.weeklyWins)
        assertEquals(631, lifetimePlayerStats.squadFpp.wins)
    }
}
