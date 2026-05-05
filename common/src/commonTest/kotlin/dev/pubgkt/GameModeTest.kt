package dev.pubgkt

import kotlin.test.Test
import kotlin.test.assertEquals

class GameModeTest {
    @Test
    fun `should transform pubg jsonapi game mode string into GameMode`() {
        assertEquals(GameMode.SOLO, GameMode.of("solo"))
        assertEquals(GameMode.SOLO_FPP, GameMode.of("solo-fpp"))
        assertEquals(GameMode.DUO, GameMode.of("duo"))
        assertEquals(GameMode.DUO_FPP, GameMode.of("duo-fpp"))
        assertEquals(GameMode.SQUAD, GameMode.of("squad"))
        assertEquals(GameMode.SQUAD_FPP, GameMode.of("squad-fpp"))
    }

    @Test
    fun `should transform GameMode instance in pubg jsonapi game mode path`() {
        assertEquals("solo", GameMode.SOLO.path)
        assertEquals("solo-fpp", GameMode.SOLO_FPP.path)
        assertEquals("duo", GameMode.DUO.path)
        assertEquals("duo-fpp", GameMode.DUO_FPP.path)
        assertEquals("squad", GameMode.SQUAD.path)
        assertEquals("squad-fpp", GameMode.SQUAD_FPP.path)
    }
}
