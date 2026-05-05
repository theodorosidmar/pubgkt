package dev.pubgkt

import kotlin.reflect.KClass

/**
 * Defines the retry behavior for failed HTTP requests made by [PubgApi].
 *
 * Use [NoRetry] to disable retries entirely (the default), or [Retry] to
 * configure automatic retry logic including max attempts, backoff strategy,
 * and which exceptions should trigger a retry.
 *
 * @see Retry
 * @see NoRetry
 */
public sealed interface RetryPolicy {
    /** Whether retry logic is active. */
    public val enabled: Boolean
}

/**
 * Disables all retry behavior. Failed requests will propagate immediately.
 *
 * This is the default [RetryPolicy] used by [PubgApi].
 */
public data object NoRetry : RetryPolicy {
    override val enabled: Boolean = false
}

/**
 * Enables automatic retries for failed HTTP requests.
 *
 * Server errors (5xx responses) are always retried up to [maxRetries] times.
 * Additionally, exceptions matching [retryOnExceptions] will trigger a retry.
 *
 * ```kotlin
 * val api = PubgApi(
 *     apiKey = "your-key",
 *     retry = Retry(
 *         maxRetries = 3,
 *         backoff = ExponentialBackoff(baseDelayMs = 500),
 *         retryOnExceptions = listOf(IOException::class),
 *     ),
 * )
 * ```
 *
 * @param maxRetries Maximum number of retry attempts after the initial request fails.
 * @param backoff The delay strategy between retry attempts. Defaults to [NoBackoff].
 * @param retryOnExceptions Exception types that should trigger a retry.
 *   Only exact type matches or subclasses are considered.
 * @see BackoffStrategy
 */
public data class Retry(
    val maxRetries: Int = 5,
    val backoff: BackoffStrategy = NoBackoff,
    val retryOnExceptions: List<KClass<out Throwable>> = emptyList(),
) : RetryPolicy {
    override val enabled: Boolean = true
}

/**
 * Controls the delay between retry attempts.
 *
 * Use [NoBackoff] to retry immediately, or [ExponentialBackoff] to apply
 * progressively longer delays between attempts.
 *
 * @see ExponentialBackoff
 * @see NoBackoff
 */
public sealed interface BackoffStrategy {
    /** Whether a backoff delay is applied between retries. */
    public val enabled: Boolean
}

/**
 * No delay between retry attempts. Retries are issued immediately.
 */
public data object NoBackoff : BackoffStrategy {
    override val enabled: Boolean = false
}

/**
 * Applies exponential backoff between retry attempts.
 *
 * The delay for attempt *n* is calculated as:
 * `min(baseDelayMs * base^n + random(0..randomizationMs), maxDelayMs)`
 *
 * @param base The exponential base multiplier. Defaults to `2.0`.
 * @param baseDelayMs The initial delay in milliseconds before the first retry. Defaults to `1000`.
 * @param maxDelayMs The maximum delay in milliseconds, capping exponential growth. Defaults to `60000`.
 * @param randomizationMs Random jitter added to each delay to avoid thundering herd. Defaults to `1000`.
 */
public data class ExponentialBackoff(
    val base: Double = 2.0,
    val baseDelayMs: Long = 1_000,
    val maxDelayMs: Long = 60_000,
    val randomizationMs: Long = 1_000,
) : BackoffStrategy {
    override val enabled: Boolean = true
}
