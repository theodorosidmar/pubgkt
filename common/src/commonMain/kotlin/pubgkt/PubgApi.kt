package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import pubgkt.http.configureClient
import pubgkt.http.engine
import pubgkt.ratelimit.DelayRateLimiter
import pubgkt.ratelimit.RateLimiter

/**
 * Entry point for all PUBG API interactions.
 *
 * Create a single instance per API key and reuse it across requests. The
 * underlying HTTP client is initialised lazily on first use.
 *
 * ```kotlin
 * val api = PubgApi(apiKey = "your-api-key")
 * val player = api.getPlayerByAccountId("account.abc123", Platform.STEAM)
 * ```
 *
 * @param apiKey Your PUBG API key, obtained from the PUBG Developer Portal.
 * @param rateLimiter Controls request throughput. Defaults to [DelayRateLimiter],
 *   which proactively delays requests when the rate limit is exhausted.
 *   Pass [RateLimiter.None] to disable rate limiting entirely.
 * @param retry Controls automatic retry behavior. Defaults to [NoRetry].
 *   Pass a [Retry] instance to enable retries with configurable backoff.
 * @see RateLimiter
 * @see RetryPolicy
 * @see <a href="https://documentation.pubg.com/en/introduction.html">PUBG Developer Portal</a>
 */
public class PubgApi
@JvmOverloads
constructor(
    internal val apiKey: String,
    internal val rateLimiter: RateLimiter = DelayRateLimiter(),
    internal val retry: RetryPolicy = NoRetry,
) {
    private var engineOverride: HttpClientEngine? = null

    /**
     * The underlying Ktor [HttpClient] used for all API requests.
     *
     * This property is intended for internal library use only. Do not use it
     * in application code.
     */
    @PubgktInternal
    public val client: HttpClient by lazy {
        createHttpClient()
    }

    /**
     * Constructs a [PubgApi] backed by a custom [HttpClientEngine].
     *
     * This constructor exists for testing purposes and should not be used in
     * production code. The full client pipeline (plugins, validators,
     * default-request) is preserved; only the engine is swapped.
     *
     * @param engine A pre-configured [HttpClientEngine], typically a [io.ktor.client.engine.mock.MockEngine].
     * @param rateLimiter Controls request throughput. Defaults to [RateLimiter.None].
     */
    @PubgktInternal
    public constructor(
        engine: HttpClientEngine,
        apiKey: String = "",
        rateLimiter: RateLimiter = RateLimiter.None,
        retry: RetryPolicy = NoRetry,
    ) : this(
        apiKey = apiKey,
        rateLimiter = rateLimiter,
        retry = retry,
    ) {
        engineOverride = engine
    }

    private fun createHttpClient(): HttpClient {
        val override = engineOverride
        return if (override != null) {
            HttpClient(override) { configureClient() }
        } else {
            HttpClient(engine) { configureClient() }
        }
    }
}
