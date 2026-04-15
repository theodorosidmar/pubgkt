package pubgkt

class ConcurrentRateLimiterIntegrationTest : RateLimitIntegrationTest() {
    override val rateLimiter: RateLimiter = ConcurrentDelayRateLimiter(FixedClock(1000L))
}
