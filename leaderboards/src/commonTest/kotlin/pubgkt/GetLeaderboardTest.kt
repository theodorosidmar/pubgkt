package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetLeaderboardTest {
    private val engine = mockEngine {
        body = LEADERBOARD_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine, apiKey = "")

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getLeaderboard(
            seasonId = "seasonId",
            gameMode = GameMode.SQUAD_FPP,
            platformRegion = PlatformRegion.PC_SA,
        )

        assertTrue(
            engine
                .lastRequest
                .url
                .encodedPath
                .endsWith("shards/${PlatformRegion.PC_SA.path}/leaderboards/seasonId/${GameMode.SQUAD_FPP.path}"),
        )
    }

    @Test
    fun `deserializes single-resource response`() = runTest {
        val leaderboard = api.getLeaderboard(
            seasonId = "seasonId",
            gameMode = GameMode.SQUAD,
            platformRegion = PlatformRegion.PC_SA,
        )

        assertEquals("division.bro.official.pc-2018-41", leaderboard.seasonId)
        assertEquals(GameMode.SQUAD, leaderboard.gameMode)
        assertEquals(PlatformRegion.PC_SA, leaderboard.platformRegion)
        assertEquals(500, leaderboard.placements.size)
    }
}
