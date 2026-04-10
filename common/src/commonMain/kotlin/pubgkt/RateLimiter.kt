package pubgkt

/**
 * Controls request throughput to stay within PUBG API rate limits.
 *
 * The PUBG API enforces a default limit of 10 requests per minute per API key.
 * Exceeding this results in an HTTP 429 response.
 *
 * Implement this interface to provide custom throttling behaviour and pass the
 * implementation to the [PubgApi] constructor. The default implementation is
 * [DelayRateLimiter], which proactively delays requests based on response headers.
 *
 * To disable rate limiting entirely (e.g. when you manage it externally), use [RateLimiter.None].
 *
 * @see DelayRateLimiter
 * @see <a href="https://documentation.pubg.com/en/rate-limiting.html">PUBG Developer Portal – Rate Limiting</a>
 */
public interface RateLimiter {

    /**
     * Called before each outbound request. Implementations should suspend here if
     * the rate limit has been (or is about to be) reached.
     */
    public suspend fun throttle()

    /**
     * Called after every response to update internal state from rate-limit headers.
     * Any parameter may be `null` if the corresponding header was absent.
     *
     * @param limit     Value of `X-RateLimit-Limit` — requests allowed per window.
     * @param remaining Value of `X-RateLimit-Remaining` — requests left in the current window.
     * @param reset     Value of `X-RateLimit-Reset` — window reset time as a UNIX epoch timestamp.
     */
    public fun onResponse(limit: Int?, remaining: Int?, reset: Long?)

    public companion object {
        /**
         * A no-op [RateLimiter] that performs no throttling.
         *
         * Use this when you manage rate limiting yourself or when running tests that
         * should not introduce artificial delays.
         */
        public val None: RateLimiter = object : RateLimiter {
            override suspend fun throttle() = Unit
            override fun onResponse(limit: Int?, remaining: Int?, reset: Long?) = Unit
        }
    }
}
