package pubgkt

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

abstract class RateLimitIntegrationTest {
    protected abstract val rateLimiter: RateLimiter

    private fun createSubject(vararg responses: MockResponse): PubgApi =
        PubgApi(
            engine = mockEngine(*responses),
            rateLimiter = rateLimiter,
            apiKey = "",
        )

    @Test
    fun `first request is never delayed`() = runTest {
        val mockEngine = mockResponse {
            remaining = 5
            reset = 2000L
        }
        val api = createSubject(mockEngine)

        api.client.get("test")

        assertEquals(0L, currentTime)
    }

    @Test
    fun `no delay when remaining is positive`() = runTest {
        val mockFirstResponse = mockResponse {
            remaining = 5
            reset = 2000L
        }
        val mockSecondResponse = mockResponse {
            remaining = 4
            reset = 2000L
        }

        val api = createSubject(mockFirstResponse, mockSecondResponse)

        api.client.get("test")
        api.client.get("test")

        assertEquals(0L, currentTime)
    }

    @Test
    fun `delays next request when remaining is zero and reset is in the future`() = runTest {
        val mockFirstResponse = mockResponse {
            remaining = 0
            reset = 1005L
        }
        val mockSecondResponse = mockResponse {
            remaining = 10
            reset = 2000L
        }

        val api = createSubject(mockFirstResponse, mockSecondResponse)

        api.client.get("test")
        api.client.get("test")

        // wait = (1005 - 1000 + 1) * 1000 = 6000 ms
        assertEquals(6_000L, currentTime)
    }

    @Test
    fun `throws RateLimitExceededException on HTTP 429`() = runTest {
        val mockEngine = mockResponse {
            status = HttpStatusCode.TooManyRequests
            reset = 1005L
        }
        val api = createSubject(mockEngine)

        val exception = assertFailsWith<RateLimitExceededException> {
            api.client.get("test")
        }

        assertEquals(1005L, exception.resetAtEpochSeconds)
    }
}
