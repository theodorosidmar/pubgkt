package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class GetWeaponMasteryByAccountIdTest {
    private val engine = mockEngine {
        body = WEAPON_MASTERY_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getWeaponMasteryByAccountId("account.82bad0072f31455d8d9f8d834da2f2f3")

        assertTrue(
            engine
                .lastRequest
                .url
                .encodedPath
                .endsWith("players/account.82bad0072f31455d8d9f8d834da2f2f3/weapon_mastery"),
        )
    }

    @Test
    @Suppress("LongMethod")
    fun `deserializes single-resource response`() = runTest {
        val mastery = api.getWeaponMasteryByAccountId("account.82bad0072f31455d8d9f8d834da2f2f3")

        assertEquals(Platform.STEAM, mastery.platform)
        assertEquals(623551, mastery.ace32?.xpTotal)
        assertEquals(76, mastery.ace32?.currentLevel)
        assertEquals(5, mastery.ace32?.currentTier)
        assertEquals(8, mastery.ace32?.statsTotal?.mostDefeatsInAGame)
        assertEquals(204, mastery.ace32?.statsTotal?.defeats)
        assertEquals(936.0536079406738, mastery.ace32?.statsTotal?.mostDamagePlayerInAGame)
        assertEquals(21836.193599700928, mastery.ace32?.statsTotal?.damagePlayer)
        assertEquals(5, mastery.ace32?.statsTotal?.mostHeadShotsInAGame)
        assertEquals(105, mastery.ace32?.statsTotal?.headShots)
        assertEquals(153.96017456054688, mastery.ace32?.statsTotal?.longestDefeat)
        assertEquals(3, mastery.ace32?.statsTotal?.longRangeDefeats)
        assertEquals(175, mastery.ace32?.statsTotal?.kills)
        assertEquals(8, mastery.ace32?.statsTotal?.mostKillsInAGame)
        assertEquals(140, mastery.ace32?.statsTotal?.groggies)
        assertEquals(6, mastery.ace32?.statsTotal?.mostGroggiesInAGame)
        assertEquals(0, mastery.ace32?.officialStatsTotal?.mostDefeatsInAGame)
        assertEquals(0, mastery.ace32?.officialStatsTotal?.defeats)
        assertEquals(167950.toDouble(), mastery.ace32?.officialStatsTotal?.damagePlayer)
        assertEquals(805, mastery.ace32?.officialStatsTotal?.headShots)
        assertEquals(1296, mastery.ace32?.officialStatsTotal?.kills)
        assertEquals(13, mastery.ace32?.officialStatsTotal?.mostKillsInAGame)
        assertEquals(1127, mastery.ace32?.officialStatsTotal?.groggies)
        assertEquals(287.toDouble(), mastery.ace32?.officialStatsTotal?.longestKill)
        assertEquals(0, mastery.ace32?.competitiveStatsTotal?.mostDefeatsInAGame)
        assertEquals(0, mastery.ace32?.competitiveStatsTotal?.defeats)
        assertEquals(46615.toDouble(), mastery.ace32?.competitiveStatsTotal?.damagePlayer)
        assertEquals(258, mastery.ace32?.competitiveStatsTotal?.headShots)
        assertEquals(322, mastery.ace32?.competitiveStatsTotal?.kills)
        assertEquals(6, mastery.ace32?.competitiveStatsTotal?.mostKillsInAGame)
        assertEquals(309, mastery.ace32?.competitiveStatsTotal?.groggies)
        assertEquals(202.toDouble(), mastery.ace32?.competitiveStatsTotal?.longestKill)
        assertNotNull(mastery.ak47)
        assertNotNull(mastery.aug)
        assertNotNull(mastery.awm)
        assertNotNull(mastery.s686)
        assertNotNull(mastery.beryl)
        assertNotNull(mastery.bizon)
        assertNotNull(mastery.bluezoneGrenade)
        assertNotNull(mastery.c4)
        assertNotNull(mastery.crossbow)
        assertNotNull(mastery.dp12)
        assertNotNull(mastery.dp28)
        assertNotNull(mastery.desertEagle)
        assertNotNull(mastery.dragunov)
        assertNotNull(mastery.famas)
        assertNotNull(mastery.slr)
        assertNotNull(mastery.g18)
        assertNotNull(mastery.g36)
        assertNotNull(mastery.grenade)
        assertNotNull(mastery.groza)
        assertNotNull(mastery.m416)
        assertNotNull(mastery.js9)
        assertNotNull(mastery.k2)
        assertNotNull(mastery.kar98k)
        assertNotNull(mastery.lynx)
        assertNotNull(mastery.m16a4)
        assertNotNull(mastery.p1911)
        assertNotNull(mastery.m249)
        assertNotNull(mastery.m24)
        assertNotNull(mastery.p92)
        assertNotNull(mastery.mg3)
        assertNotNull(mastery.mp5k)
        assertNotNull(mastery.mp9)
        assertNotNull(mastery.mini14)
        assertNotNull(mastery.mk12)
        assertNotNull(mastery.mk14)
        assertNotNull(mastery.mutant)
        assertNotNull(mastery.molotov)
        assertNotNull(mastery.mortar)
        assertNotNull(mastery.mosinNagant)
        assertNotNull(mastery.r1895)
        assertNotNull(mastery.o12)
        assertNotNull(mastery.p90)
        assertNotNull(mastery.panzerfaust)
        assertNotNull(mastery.qbu)
        assertNotNull(mastery.qbz)
        assertNotNull(mastery.r45)
        assertNotNull(mastery.scarl)
        assertNotNull(mastery.sks)
        assertNotNull(mastery.s12k)
        assertNotNull(mastery.sawedOff)
        assertNotNull(mastery.stickyBomb)
        assertNotNull(mastery.tommyGun)
        assertNotNull(mastery.ump)
        assertNotNull(mastery.uzi)
        assertNotNull(mastery.vss)
        assertNotNull(mastery.vector)
        assertNotNull(mastery.win94)
        assertNotNull(mastery.winchester)
        assertNotNull(mastery.skorpion)
    }
}
