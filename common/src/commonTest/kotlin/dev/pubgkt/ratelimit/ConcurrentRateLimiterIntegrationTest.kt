package dev.pubgkt.ratelimit

import dev.pubgkt.test.FixedClock

class ConcurrentRateLimiterIntegrationTest : RateLimitIntegrationTest() {
    override val rateLimiter: RateLimiter = ConcurrentDelayRateLimiter(FixedClock(1000L))
}
