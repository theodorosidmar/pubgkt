package pubgkt.ratelimit

import kotlin.time.Clock

abstract class AbstractDelayRateLimiterTest {
    protected abstract fun createSubject(clock: Clock): DelayRateLimiter
}
