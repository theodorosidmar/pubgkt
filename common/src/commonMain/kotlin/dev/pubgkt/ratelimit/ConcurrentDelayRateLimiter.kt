package dev.pubgkt.ratelimit

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.time.Clock

/**
 * A [DelayRateLimiter] variant that serializes [throttle] calls across coroutines.
 *
 * This implementation preserves the proactive waiting behavior of [DelayRateLimiter],
 * but wraps [throttle] in a [Mutex] so only one coroutine evaluates and applies the
 * wait logic at a time.
 *
 * Use this limiter when many coroutines can issue requests concurrently from the same
 * [dev.pubgkt.PubgApi] instance and you want to avoid race conditions around shared rate-limit
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
@JsExport
public class ConcurrentDelayRateLimiter @JsExport.Ignore constructor(clock: Clock = Clock.System) :
    DelayRateLimiter(clock) {

    /**
     * [kotlin.time.Clock] is not exported to JavaScript.
     * This secondary constructor allows JavaScript consumers to instantiate [ConcurrentDelayRateLimiter]
     */
    @JsName("create")
    public constructor() : this(clock = Clock.System)

    private val mutex = Mutex()

    override suspend fun throttle() {
        mutex.withLock {
            super.throttle()
        }
    }
}
