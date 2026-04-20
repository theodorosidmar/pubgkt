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
        assertNotNull(mastery.ace32)
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
