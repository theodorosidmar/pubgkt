package pubgkt

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlinx.coroutines.test.runTest

abstract class PubgApiTest(private val api: PubgApi) {
    @Test
    fun playersAmount() = runTest {
        assertFailsWith<IllegalArgumentException> {
            api.getPlayersByNames(List(11) { "" })
        }
        assertFailsWith<IllegalArgumentException> {
            api.getPlayersByIds(List(11) { "" })
        }
        assertFailsWith<IllegalArgumentException> {
            api.getLifetimeStats(List(11) { "" }, GameMode.DuoFpp)
        }
    }
}
