package pubgkt.stats.season

import io.ktor.http.HttpMethod
import io.ktor.http.encodeURLParameter
import kotlinx.coroutines.test.runTest
import pubgkt.GameMode
import pubgkt.PubgApi
import pubgkt.stats.FILTER_PLAYER_IDS
import pubgkt.test.lastRequest
import pubgkt.test.mockEngine
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetSeasonGameModeStatsTest {
    private val engine =
        mockEngine {
            body = SEASON_GAME_MODE_RESPONSE_JSON
        }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        val gameMode = GameMode.SQUAD_FPP
        api.getSeasonStatsByGameModeAndPlayers(
            seasonId = "season123",
            gameMode = gameMode,
            accountIds = listOf("account.abc123", "account.abc456"),
        )

        val request = engine.lastRequest

        assertEquals(HttpMethod.Get, request.method)
        assertTrue(
            request
                .url
                .encodedPath
                .endsWith("shards/steam/seasons/season123/gameMode/${gameMode.path}/players"),
        )
        assertTrue(
            request
                .url
                .encodedQuery
                .endsWith(
                    FILTER_PLAYER_IDS.encodeURLParameter() + "=" +
                        "account.abc123,account.abc456".encodeURLParameter(),
                ),
        )
    }

    @Test
    fun `deserializes a lifetime game mode stats response`() = runTest {
        val lifetimeGameModeStats =
            api.getSeasonStatsByGameModeAndPlayers(
                seasonId = "season123",
                gameMode = GameMode.SQUAD_FPP,
                accountIds = listOf("account.abc123"),
            )

        assertEquals(2, lifetimeGameModeStats.size)
        assertEquals(lifetimeGameModeStats.first(), lifetimeGameModeStats.last())

        val dataToCheck = lifetimeGameModeStats.first()
        assertEquals("account.91186dcca3cb4ad198fac1e4ab1d5b80", dataToCheck.playerId)
        assertEquals("division.bro.official.pc-2018-41", dataToCheck.seasonId)
        assertEquals(0.0, dataToCheck.bestRankPoint)
        assertEquals(GameMode.SQUAD_FPP, dataToCheck.gameMode)
        assertEquals(6408, dataToCheck.stats.assists)
        assertEquals(33353, dataToCheck.stats.boosts)
        assertEquals(53791, dataToCheck.stats.dbnos)
        assertEquals(60, dataToCheck.stats.dailyKills)
        assertEquals(2, dataToCheck.stats.dailyWins)
        assertEquals(8210634.0, dataToCheck.stats.damageDealt)
        assertEquals(1212, dataToCheck.stats.days)
        assertEquals(18269, dataToCheck.stats.headshotKills)
        assertEquals(31134, dataToCheck.stats.heals)
        assertEquals(62641, dataToCheck.stats.kills)
        assertEquals(707.8984, dataToCheck.stats.longestKill)
        assertEquals(1978.0, dataToCheck.stats.longestTimeSurvived)
        assertEquals(13646, dataToCheck.stats.losses)
        assertEquals(8, dataToCheck.stats.maxKillStreaks)
        assertEquals(1978.0, dataToCheck.stats.mostSurvivalTime)
        assertEquals(1985, dataToCheck.stats.revives)
        assertEquals(11483215.0, dataToCheck.stats.rideDistance)
        assertEquals(170, dataToCheck.stats.roadKills)
        assertEquals(34, dataToCheck.stats.roundMostKills)
        assertEquals(14160, dataToCheck.stats.roundsPlayed)
        assertEquals(207, dataToCheck.stats.suicides)
        assertEquals(7358.4614, dataToCheck.stats.swimDistance)
        assertEquals(163, dataToCheck.stats.teamKills)
        assertEquals(6368624.0, dataToCheck.stats.timeSurvived)
        assertEquals(2487, dataToCheck.stats.top10s)
        assertEquals(227, dataToCheck.stats.vehicleDestroys)
        assertEquals(8597544.0, dataToCheck.stats.walkDistance)
        assertEquals(61481, dataToCheck.stats.weaponsAcquired)
        assertEquals(60, dataToCheck.stats.weeklyKills)
        assertEquals(2, dataToCheck.stats.weeklyWins)
        assertEquals(631, dataToCheck.stats.wins)
    }
}
