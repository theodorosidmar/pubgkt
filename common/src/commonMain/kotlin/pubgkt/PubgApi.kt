package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.serialization
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Entry point for all PUBG API interactions.
 *
 * Create a single instance per API key and reuse it across requests. The
 * underlying HTTP client is initialised lazily on first use.
 *
 * ```kotlin
 * val api = PubgApi(apiKey = "your-api-key", platform = Platform.STEAM)
 * val player = api.getPlayerByAccountId("account.abc123")
 * ```
 *
 * @param apiKey Your PUBG API key, obtained from the PUBG Developer Portal.
 * @param rateLimiter Controls request throughput. Defaults to [DelayRateLimiter],
 *   which proactively delays requests when the rate limit is exhausted.
 *   Pass [RateLimiter.None] to disable rate limiting entirely.
 * @param platform The platform shard used to scope all requests.
 *   Defaults to [Platform.STEAM]. Change this before making requests if you
 *   need to target a different platform.
 * @see Platform
 * @see RateLimiter
 * @see <a href="https://documentation.pubg.com/en/introduction.html">PUBG Developer Portal</a>
 */
public class PubgApi @JvmOverloads constructor(
    private val apiKey: String,
    public val rateLimiter: RateLimiter = DelayRateLimiter(),
    public var platform: Platform = Platform.STEAM,
    public override val coroutineContext: CoroutineContext = Dispatchers.IO,
) : CoroutineScope {

    private var _engineOverride: HttpClientEngine? = null

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
        rateLimiter: RateLimiter = RateLimiter.None,
    ) : this(apiKey = "", rateLimiter = rateLimiter) {
        _engineOverride = engine
    }

    private fun createHttpClient(): HttpClient {
        val override = _engineOverride
        return if (override != null) {
            HttpClient(override) { configureClient() }
        } else {
            HttpClient(engine) { configureClient() }
        }
    }

    private fun HttpClientConfig<*>.configureClient() {
        install(ContentNegotiation) {
            serialization(
                ContentType.Application.Json,
                json,
            )
        }

        install(ContentEncoding) {
            gzip()
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }

        install(RateLimitPlugin) {
            rateLimiter = this@PubgApi.rateLimiter
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = HOST
                url("shards/${platform.name.lowercase()}/")
            }
            bearerAuth(apiKey)
            contentType(
                ContentType(
                    contentType = "application",
                    contentSubtype = "vnd.api+json",
                )
            )
        }

        HttpResponseValidator {
            validateResponse { response ->
                if (response.status == HttpStatusCode.Unauthorized) {
                    throw UnauthorizedException()
                }

                rateLimiter.onResponse(
                    limit = response.headers[HEADER_RATE_LIMIT_LIMIT]?.toIntOrNull(),
                    remaining = response.headers[HEADER_RATE_LIMIT_REMAINING]?.toIntOrNull(),
                    reset = response.headers[HEADER_RATE_LIMIT_RESET]?.toLongOrNull(),
                )
                if (response.status == HttpStatusCode.TooManyRequests) {
                    throw RateLimitExceededException(
                        resetAtEpochSeconds = response.headers[HEADER_RATE_LIMIT_RESET]?.toLongOrNull(),
                    )
                }
            }
        }
    }
}
