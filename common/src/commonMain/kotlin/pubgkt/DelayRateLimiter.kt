package pubgkt

import kotlinx.coroutines.delay
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

/**
 * A [RateLimiter] that proactively delays requests when the API rate limit is exhausted.
 *
 * After each response, this implementation reads the `X-RateLimit-Remaining` and
 * `X-RateLimit-Reset` headers to track the current window. When [remaining] reaches
 * zero, [throttle] suspends the caller until the reset timestamp has passed before
 * allowing the next request.
 *
 * This prevents [RateLimitExceededException] under normal single-instance usage.
 * If multiple [PubgApi] instances share the same API key, 429 responses can still
 * occur and will be surfaced as [RateLimitExceededException].
 *
 * This implementation is not designed for high-concurrency scenarios where many
 * coroutines fire requests simultaneously.
 *
 * @see RateLimiter
 * @see RateLimitExceededException
 */
public open class DelayRateLimiter(
    protected val clock: Clock = Clock.System,
) : RateLimiter {
    private var remaining: Int = Int.MAX_VALUE
    private var resetAt: Instant = Instant.fromEpochSeconds(0L)

    override suspend fun throttle() {
        if (remaining <= 0) {
            val wait = (resetAt - clock.now() + 1.seconds).coerceAtLeast(0.seconds)
            if (wait.isPositive()) delay(wait)
        }
    }

    override fun onResponse(limit: Int?, remaining: Int?, reset: Long?) {
        remaining?.let { this.remaining = it }
        reset?.let { this.resetAt = Instant.fromEpochSeconds(it) }
    }
}
