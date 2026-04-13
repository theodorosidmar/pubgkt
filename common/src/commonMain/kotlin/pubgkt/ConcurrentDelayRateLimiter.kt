package pubgkt

import kotlin.time.Clock
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * A [DelayRateLimiter] variant that serializes [throttle] calls across coroutines.
 *
 * This implementation preserves the proactive waiting behavior of [DelayRateLimiter],
 * but wraps [throttle] in a [Mutex] so only one coroutine evaluates and applies the
 * wait logic at a time.
 *
 * Use this limiter when many coroutines can issue requests concurrently from the same
 * [PubgApi] instance and you want to avoid race conditions around shared rate-limit
 * state.
 *
 * Like [DelayRateLimiter], this class relies on rate-limit headers provided via
 * [onResponse]. It reduces local contention issues, but cannot prevent 429 responses
 * caused by external consumers using the same API key.
 *
 * @see DelayRateLimiter
 * @see RateLimiter
 * @see RateLimitExceededException
 */
public class ConcurrentDelayRateLimiter(
    clock: Clock = Clock.System,
) : DelayRateLimiter(clock) {
    private val mutex = Mutex()

    override suspend fun throttle() {
        mutex.withLock {
            super.throttle()
        }
    }
}
