package pubgkt

import io.ktor.client.request.get
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.test.runTest

class RequestPolicyTest {
    private val stubRateLimiter = StubRateLimiter()

    @Test
    fun `should rate limit and authorize when policy is default`() = runTest {
        val engine = mockEngine {}
        val api = PubgApi(
            apiKey = "test",
            engine = engine,
            rateLimiter = stubRateLimiter,
        )

        api.client.get("test")

        val authorization = engine.lastRequest.headers["Authorization"]

        assertEquals("Bearer test", authorization)
        assertEquals(1, stubRateLimiter.throttleCount)
        assertEquals(1, stubRateLimiter.onResponseCount)
    }

    @Test
    fun `should not rate limit and authorize when policy is public`() = runTest {
        val engine = mockEngine {}
        val api = PubgApi(
            apiKey = "test",
            engine = engine,
            rateLimiter = stubRateLimiter,
        )

        api.client.getWithPublicRequest("test")

        val authorization = engine.lastRequest.headers["Authorization"]

        assertNull(authorization)
        assertEquals(0, stubRateLimiter.throttleCount)
        assertEquals(0, stubRateLimiter.onResponseCount)
    }

    @Test
    fun `should work with both`() = runTest {
        val expectedRemaining = 9
        val expectedLimit = 10
        val expectedReset = 1L

        val firstResponse = mockResponse {
            remaining = expectedRemaining
            limit = expectedLimit
            reset = expectedReset
        }
        val secondResponse = mockResponse {}

        val engine = mockEngine(firstResponse, secondResponse)
        val api = PubgApi(
            engine = engine,
            apiKey = "test",
            rateLimiter = stubRateLimiter,
        )

        api.client.get("test")
        api.client.getWithPublicRequest("test")

        val firstRequest = engine.requestHistory.first()
        val secondRequest = engine.requestHistory.last()

        assertEquals("Bearer test", firstRequest.headers["Authorization"])
        assertNull(secondRequest.headers["Authorization"])
        assertEquals(1, stubRateLimiter.throttleCount)
        assertEquals(1, stubRateLimiter.onResponseCount)
        assertEquals(expectedRemaining, stubRateLimiter.remaining)
        assertEquals(expectedLimit, stubRateLimiter.limit)
        assertEquals(expectedReset, stubRateLimiter.reset)
    }
}
