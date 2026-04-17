package pubgkt

class StubRateLimiter : RateLimiter {
    var throttleCount: Int = 0
        private set
    var onResponseCount: Int = 0
        private set
    var limit: Int? = null
        private set
    var remaining: Int? = null
        private set
    var reset: Long? = null
        private set

    override suspend fun throttle() {
        throttleCount++
    }

    override fun onResponse(limit: Int?, remaining: Int?, reset: Long?) {
        this.limit = limit
        this.remaining = remaining
        this.reset = reset
        onResponseCount++
    }
}
