package pubgkt.ratelimit

import pubgkt.test.FixedClock

class DelayRateLimitIntegrationTest : RateLimitIntegrationTest() {
    override val rateLimiter: RateLimiter = DelayRateLimiter(FixedClock(1000L))
}
