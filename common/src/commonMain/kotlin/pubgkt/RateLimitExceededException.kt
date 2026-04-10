package pubgkt

/**
 * Thrown when the PUBG API returns HTTP 429 (Too Many Requests).
 *
 * This exception is thrown even when a [RateLimiter] is in use, as a 429 can still
 * occur if multiple [PubgApi] instances share the same API key, or if the rate limit
 * was consumed externally.
 *
 * @property resetAtEpochSeconds The UNIX timestamp at which the rate-limit window
 *   resets, as reported by the `X-RateLimit-Reset` response header, or `null` if
 *   the header was absent.
 * @see RateLimiter
 */
public class RateLimitExceededException(
    public val resetAtEpochSeconds: Long?,
) : Exception(
    buildString {
        append("PUBG API rate limit exceeded (HTTP 429).")
        if (resetAtEpochSeconds != null) append(" Resets at epoch second: $resetAtEpochSeconds.")
    }
)
