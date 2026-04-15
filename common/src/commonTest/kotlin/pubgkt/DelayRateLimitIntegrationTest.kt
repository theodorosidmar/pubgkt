package pubgkt

class DelayRateLimitIntegrationTest : RateLimitIntegrationTest() {
    override val rateLimiter: RateLimiter = DelayRateLimiter(FixedClock(1000L))
}
