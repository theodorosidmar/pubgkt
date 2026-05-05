package dev.pubgkt

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformRegionTest {
    @Test
    fun `should transform pubg jsonapi platform region shard string into PlatformRegion`() {
        assertEquals(PlatformRegion.PC_AS, PlatformRegion.of("pc-as"))
        assertEquals(PlatformRegion.PC_EU, PlatformRegion.of("pc-eu"))
        assertEquals(PlatformRegion.PC_JP, PlatformRegion.of("pc-jp"))
        assertEquals(PlatformRegion.PC_KAKAO, PlatformRegion.of("pc-kakao"))
        assertEquals(PlatformRegion.PC_KRJP, PlatformRegion.of("pc-krjp"))
        assertEquals(PlatformRegion.PC_NA, PlatformRegion.of("pc-na"))
        assertEquals(PlatformRegion.PC_OC, PlatformRegion.of("pc-oc"))
        assertEquals(PlatformRegion.PC_RU, PlatformRegion.of("pc-ru"))
        assertEquals(PlatformRegion.PC_SA, PlatformRegion.of("pc-sa"))
        assertEquals(PlatformRegion.PC_SEA, PlatformRegion.of("pc-sea"))
        assertEquals(PlatformRegion.PC_TOURNAMENT, PlatformRegion.of("pc-tournament"))
        assertEquals(PlatformRegion.PSN_AS, PlatformRegion.of("psn-as"))
        assertEquals(PlatformRegion.PSN_EU, PlatformRegion.of("psn-eu"))
        assertEquals(PlatformRegion.PSN_NA, PlatformRegion.of("psn-na"))
        assertEquals(PlatformRegion.PSN_OC, PlatformRegion.of("psn-oc"))
        assertEquals(PlatformRegion.XBOX_AS, PlatformRegion.of("xbox-as"))
        assertEquals(PlatformRegion.XBOX_EU, PlatformRegion.of("xbox-eu"))
        assertEquals(PlatformRegion.XBOX_NA, PlatformRegion.of("xbox-na"))
        assertEquals(PlatformRegion.XBOX_OC, PlatformRegion.of("xbox-oc"))
        assertEquals(PlatformRegion.XBOX_SA, PlatformRegion.of("xbox-sa"))
    }

    @Test
    fun `should transform PlatformRegion instance in pubg jsonapi platform region shard path`() {
        assertEquals("pc-as", PlatformRegion.PC_AS.path)
        assertEquals("pc-eu", PlatformRegion.PC_EU.path)
        assertEquals("pc-jp", PlatformRegion.PC_JP.path)
        assertEquals("pc-kakao", PlatformRegion.PC_KAKAO.path)
        assertEquals("pc-krjp", PlatformRegion.PC_KRJP.path)
        assertEquals("pc-na", PlatformRegion.PC_NA.path)
        assertEquals("pc-oc", PlatformRegion.PC_OC.path)
        assertEquals("pc-ru", PlatformRegion.PC_RU.path)
        assertEquals("pc-sa", PlatformRegion.PC_SA.path)
        assertEquals("pc-sea", PlatformRegion.PC_SEA.path)
        assertEquals("pc-tournament", PlatformRegion.PC_TOURNAMENT.path)
        assertEquals("psn-as", PlatformRegion.PSN_AS.path)
        assertEquals("psn-eu", PlatformRegion.PSN_EU.path)
        assertEquals("psn-na", PlatformRegion.PSN_NA.path)
        assertEquals("psn-oc", PlatformRegion.PSN_OC.path)
        assertEquals("xbox-as", PlatformRegion.XBOX_AS.path)
        assertEquals("xbox-eu", PlatformRegion.XBOX_EU.path)
        assertEquals("xbox-na", PlatformRegion.XBOX_NA.path)
        assertEquals("xbox-oc", PlatformRegion.XBOX_OC.path)
        assertEquals("xbox-sa", PlatformRegion.XBOX_SA.path)
    }
}
