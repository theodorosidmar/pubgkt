package dev.pubgkt.ratelimit

import dev.pubgkt.test.FixedClock

class DelayRateLimitIntegrationTest : RateLimitIntegrationTest() {
    override val rateLimiter: RateLimiter = DelayRateLimiter(FixedClock(1000L))
}
