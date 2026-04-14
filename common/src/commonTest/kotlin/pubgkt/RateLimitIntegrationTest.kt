package pubgkt

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RateLimitIntegrationTest {

    @Test
    fun `first request is never delayed`() = runTest {
        val api = PubgApi(
            engine = mockEngine(MockResponse(remaining = 5, reset = 2000L)),
            rateLimiter = DelayRateLimiter(FixedClock(1000L)),
        )

        api.client.get("test")

        assertEquals(0L, currentTime)
    }

    @Test
    fun `no delay when remaining is positive`() = runTest {
        val mockEngine = mockEngine(
            listOf(
                MockResponse(remaining = 5, reset = 2000L),
                MockResponse(remaining = 4, reset = 2000L),
            )
        )

        val api = PubgApi(
            engine = mockEngine,
            rateLimiter = DelayRateLimiter(FixedClock(1000L))
        )

        api.client.get("test")
        api.client.get("test")

        assertEquals(0L, currentTime)
    }

    @Test
    fun `delays next request when remaining is zero and reset is in the future`() = runTest {
        val mockEngine = mockEngine(
            listOf(
                MockResponse(remaining = 0, reset = 1005L),
                MockResponse(remaining = 10, reset = 2000L),
            )
        )
        val api = PubgApi(
            engine = mockEngine,
            rateLimiter = DelayRateLimiter(FixedClock(1000L)),
        )

        api.client.get("test")
        api.client.get("test")

        // wait = (1005 - 1000 + 1) * 1000 = 6000 ms
        assertEquals(6_000L, currentTime)
    }

    @Test
    fun `throws RateLimitExceededException on HTTP 429`() = runTest {
        val mockEngine = mockEngine(
            MockResponse(status = HttpStatusCode.TooManyRequests, remaining = 0, reset = 1005L),
        )
        val api = PubgApi(
            engine = mockEngine,
            rateLimiter = DelayRateLimiter(FixedClock(1000L)),
        )

        val exception = assertFailsWith<RateLimitExceededException> {
            api.client.get("test")
        }

        assertEquals(1005L, exception.resetAtEpochSeconds)
    }
}
