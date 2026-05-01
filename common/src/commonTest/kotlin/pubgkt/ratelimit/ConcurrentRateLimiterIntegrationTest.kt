package pubgkt.ratelimit

import pubgkt.test.FixedClock

class ConcurrentRateLimiterIntegrationTest : RateLimitIntegrationTest() {
    override val rateLimiter: RateLimiter = ConcurrentDelayRateLimiter(FixedClock(1000L))
}
