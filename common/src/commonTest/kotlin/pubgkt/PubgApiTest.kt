package pubgkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class PubgApiTest {

    @Test
    fun `constructs without throwing`() {
        PubgApi("any-key")
    }

    @Test
    fun `default platform is STEAM`() {
        val api = PubgApi("any-key")
        assertEquals(Platform.STEAM, api.platform)
    }

    @Test
    fun `default rateLimiter is DelayRateLimiter`() {
        val api = PubgApi("any-key")
        assertIs<DelayRateLimiter>(api.rateLimiter)
    }

    @Test
    fun `platform assignment is reflected`() {
        val api = PubgApi("any-key")
        api.platform = Platform.PSN
        assertEquals(Platform.PSN, api.platform)
    }

    @Test
    fun `RateLimiter None disables rate limiting`() {
        val api = PubgApi("any-key", RateLimiter.None)
        assertEquals(RateLimiter.None, api.rateLimiter)
    }
}
