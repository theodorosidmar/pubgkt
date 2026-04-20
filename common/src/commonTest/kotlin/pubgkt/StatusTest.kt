package pubgkt

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class StatusTest {
    private val engine = mockEngine()
    private val stubRateLimiter = StubRateLimiter()
    private val api = PubgApi(
        engine = engine,
        rateLimiter = stubRateLimiter
    )

    @Test
    fun `returns true when status is OK`() = runTest {
        assertTrue(api.isUp())
    }

    @Test
    fun `does not use shard on path or auth or rate limit`() = runTest {
        api.isUp()

        val request = engine.lastRequest

        assertNull(request.headers["Authorization"])
        assertEquals(0, stubRateLimiter.throttleCount)
        assertEquals("https://api.pubg.com/status", request.url.toString())
    }
}
