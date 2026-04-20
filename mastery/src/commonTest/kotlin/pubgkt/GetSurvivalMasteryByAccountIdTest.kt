package pubgkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class GetSurvivalMasteryByAccountIdTest {
    private val engine = mockEngine {
        body = SURVIVAL_MASTERY_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getSurvivalMasteryByAccountId("account.82bad0072f31455d8d9f8d834da2f2f3")

        assertTrue(
            engine
                .lastRequest
                .url
                .encodedPath
                .endsWith("players/account.82bad0072f31455d8d9f8d834da2f2f3/survival_mastery"),
        )
    }

    @Test
    @Suppress("LongMethod")
    fun `deserializes single-resource response`() = runTest {
        val mastery = api.getSurvivalMasteryByAccountId("account.82bad0072f31455d8d9f8d834da2f2f3")

        assertEquals("account.82bad0072f31455d8d9f8d834da2f2f3", mastery.playerId)
        assertEquals(8579174, mastery.xp)
        assertEquals(5, mastery.tier)
        assertEquals(500, mastery.level)
        assertEquals(27545, mastery.totalMatchesPlayed)
        assertEquals(zeroedSurvivalStats, mastery.airDropsCalled)
        assertEquals(zeroedSurvivalStats, mastery.damageDealt)
        assertEquals(zeroedSurvivalStats, mastery.damageTaken)
        assertEquals(zeroedSurvivalStats, mastery.distanceBySwimming)
        assertEquals(zeroedSurvivalStats, mastery.distanceByVehicle)
        assertEquals(zeroedSurvivalStats, mastery.distanceOnFoot)
        assertEquals(zeroedSurvivalStats, mastery.distanceTotal)
        assertEquals(zeroedSurvivalStats, mastery.healed)
        assertEquals(0, mastery.hotDropLandings)
        assertEquals(zeroedSurvivalStats, mastery.enemyCratesLooted)
        assertEquals(zeroedSurvivalStats, mastery.uniqueItemsLooted)
        assertEquals(zeroedSurvivalStats, mastery.position)
        assertEquals(zeroedSurvivalStats, mastery.revived)
        assertEquals(zeroedSurvivalStats, mastery.teammatesRevived)
        assertEquals(zeroedSurvivalStats.copy(lastMatchValue = 1197.0), mastery.timeSurvived)
        assertEquals(zeroedSurvivalStats, mastery.throwablesThrown)
        assertEquals(0, mastery.top10)
    }
}
