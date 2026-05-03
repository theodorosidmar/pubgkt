package pubgkt.http

import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import pubgkt.ExponentialBackoff
import pubgkt.NoBackoff
import pubgkt.PubgApi
import pubgkt.Retry
import pubgkt.isUp
import pubgkt.test.mockEngineWithHandler
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class HttpRetryTest {
    @Test
    fun `stops retrying after reaching max retry attempts`() = runTest {
        val maxRetries = 3
        val engine = mockEngineWithHandler { respond("", HttpStatusCode.InternalServerError) }
        val api =
            PubgApi(
                engine = engine,
                retry = Retry(maxRetries = maxRetries, backoff = NoBackoff),
            )

        runCatching { api.isUp() }

        assertEquals(maxRetries + 1, engine.requestHistory.size)
    }

    @Test
    fun `applies exponential backoff delay between retry attempts`() = runTest {
        val maxRetries = 3
        val timestamps = mutableListOf<Long>()
        val engine =
            mockEngineWithHandler {
                timestamps.add(testScheduler.currentTime)
                respond("", HttpStatusCode.InternalServerError)
            }
        val api =
            PubgApi(
                engine = engine,
                retry =
                Retry(
                    maxRetries = maxRetries,
                    backoff =
                    ExponentialBackoff(
                        base = 2.0,
                        baseDelayMs = 100,
                        maxDelayMs = 5_000,
                        randomizationMs = 0,
                    ),
                ),
            )

        runCatching { api.isUp() }

        assertEquals(maxRetries + 1, timestamps.size)
        // First request has no delay
        assertEquals(0, timestamps[0])
        // Subsequent requests must occur after increasing delays
        for (i in 1 until timestamps.size) {
            assertTrue(
                timestamps[i] > timestamps[i - 1],
                "Retry $i should occur after retry ${i - 1}",
            )
        }
        // Each successive gap should be larger than the previous (exponential growth)
        val gaps = timestamps.zipWithNext { a, b -> b - a }
        for (i in 1 until gaps.size) {
            assertTrue(
                gaps[i] > gaps[i - 1],
                "Gap ${gaps[i]} should be larger than previous gap ${gaps[i - 1]}",
            )
        }
    }

    @Test
    fun `retries request when thrown exception matches configured types`() = runTest {
        class RetryableException : Throwable()

        var requestCount = 0
        val maxRetries = 2
        val engine =
            mockEngineWithHandler {
                requestCount++
                throw RetryableException()
            }
        val api =
            PubgApi(
                engine = engine,
                retry =
                Retry(
                    maxRetries = maxRetries,
                    backoff = NoBackoff,
                    retryOnExceptions = listOf(RetryableException::class),
                ),
            )

        assertFailsWith<RetryableException> { api.isUp() }
        assertEquals(maxRetries + 1, requestCount)
    }

    @Test
    fun `does not retry when thrown exception is not in configured types`() = runTest {
        class NonRetryableException : Throwable()

        class ConfiguredException : Throwable()

        var requestCount = 0
        val engine =
            mockEngineWithHandler {
                requestCount++
                throw NonRetryableException()
            }
        val api =
            PubgApi(
                engine = engine,
                retry =
                Retry(
                    maxRetries = 3,
                    backoff = NoBackoff,
                    retryOnExceptions = listOf(ConfiguredException::class),
                ),
            )

        assertFailsWith<NonRetryableException> { api.isUp() }
        assertEquals(1, requestCount)
    }
}
