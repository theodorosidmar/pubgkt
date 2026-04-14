package pubgkt

import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock

open class DelayRateLimiterTest : AbstractDelayRateLimiterTest() {
    override fun createSubject(clock: Clock): DelayRateLimiter = DelayRateLimiter(clock)

    @Test
    fun `no delay when remaining is positive`() = runTest {
        with(createSubject(FixedClock(nowEpoch = 1000L))) {
            onResponse(limit = 10, remaining = 5, reset = 2000L)
            throttle()
        }
        assertEquals(0L, currentTime)
    }

    @Test
    fun `no delay when remaining is zero but reset is in the past`() = runTest {
        with(createSubject(FixedClock(nowEpoch = 2001L))) {
            onResponse(limit = 10, remaining = 0, reset = 2000L)
            throttle()
        }
        assertEquals(0L, currentTime)
    }

    @Test
    fun `delays until reset when remaining is zero and reset is in the future`() = runTest {
        with(createSubject(FixedClock(nowEpoch = 1000L))) {
            onResponse(limit = 10, remaining = 0, reset = 1005L)
            throttle()
        }
        // waitMs = (1005 - 1000 + 1) * 1000 = 6000 ms
        assertEquals(6_000L, currentTime)
    }

    @Test
    fun `onResponse with null values does not change state`() = runTest {
        with(createSubject(FixedClock(nowEpoch = 1000L))) {
            onResponse(limit = null, remaining = null, reset = null)
            throttle()
        }
        assertEquals(0L, currentTime)
    }
}
