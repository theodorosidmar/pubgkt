package pubgkt

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock

class ConcurrentDelayRateLimiterTest : DelayRateLimiterTest() {
    override fun createSubject(clock: Clock): ConcurrentDelayRateLimiter = ConcurrentDelayRateLimiter(clock)

    @Test
    fun `concurrent throttle calls are serialized`() = runTest {
        val limiter = createSubject(FixedClock(nowEpoch = 1000L)).apply {
            onResponse(limit = 10, remaining = 0, reset = 1005L)
        }

        List(2) {
            async {
                limiter.throttle()
            }
        }.awaitAll()

        // Each call waits 6 seconds, and Mutex serialization makes these waits cumulative.
        assertEquals(12_000L, currentTime)
    }
}
