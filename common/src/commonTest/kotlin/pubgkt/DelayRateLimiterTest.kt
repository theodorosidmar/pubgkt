package pubgkt

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock
import kotlin.time.Instant

@OptIn(ExperimentalCoroutinesApi::class)
class DelayRateLimiterTest {

    @Test
    fun `no delay when remaining is positive`() = runTest {
        val limiter = DelayRateLimiter(fixedClock(nowEpoch = 1000L))
        limiter.onResponse(limit = 10, remaining = 5, reset = 2000L)
        limiter.throttle()
        assertEquals(0L, currentTime)
    }

    @Test
    fun `no delay when remaining is zero but reset is in the past`() = runTest {
        val limiter = DelayRateLimiter(fixedClock(nowEpoch = 2001L))
        limiter.onResponse(limit = 10, remaining = 0, reset = 2000L)
        limiter.throttle()
        assertEquals(0L, currentTime)
    }

    @Test
    fun `delays until reset when remaining is zero and reset is in the future`() = runTest {
        val limiter = DelayRateLimiter(fixedClock(nowEpoch = 1000L))
        limiter.onResponse(limit = 10, remaining = 0, reset = 1005L)
        limiter.throttle()
        // waitMs = (1005 - 1000 + 1) * 1000 = 6000 ms
        assertEquals(6_000L, currentTime)
    }

    @Test
    fun `onResponse with null values does not change state`() = runTest {
        val limiter = DelayRateLimiter(fixedClock(nowEpoch = 1000L))
        limiter.onResponse(limit = null, remaining = null, reset = null)
        limiter.throttle()
        assertEquals(0L, currentTime)
    }
}

private fun fixedClock(nowEpoch: Long): Clock = object : Clock {
    override fun now(): Instant = Instant.fromEpochSeconds(nowEpoch)
}
