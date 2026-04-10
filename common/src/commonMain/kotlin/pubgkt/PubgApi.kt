package pubgkt

import io.ktor.client.HttpClient
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
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

/**
 * Entry point for all PUBG API interactions.
 *
 * Create a single instance per API key and reuse it across requests. The
 * underlying HTTP client is initialised lazily on first use.
 *
 * ```kotlin
 * val api = PubgApi("your-api-key").apply { platform = Platform.STEAM }
 * val player = api.getPlayerByAccountId("account.abc123")
 * ```
 *
 * @param apiKey Your PUBG API key, obtained from the PUBG Developer Portal.
 * @param rateLimiter Controls request throughput. Defaults to [DelayRateLimiter],
 *   which proactively delays requests when the rate limit is exhausted.
 *   Pass [RateLimiter.None] to disable rate limiting entirely.
 * @see Platform
 * @see RateLimiter
 * @see <a href="https://documentation.pubg.com/en/introduction.html">PUBG Developer Portal</a>
 */
public class PubgApi @JvmOverloads constructor(
    private val apiKey: String,
    public val rateLimiter: RateLimiter = DelayRateLimiter(),
) : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO

    /**
     * The platform shard used to scope all requests.
     *
     * Defaults to [Platform.STEAM]. Change this before making requests if you
     * need to target a different platform.
     */
    public var platform: Platform = Platform.STEAM

    private var _clientOverride: HttpClient? = null

    /**
     * The underlying Ktor [HttpClient] used for all API requests.
     *
     * This property is intended for internal library use only. Do not use it
     * in application code.
     */
    @PubgktInternal
    public val client: HttpClient by lazy {
        _clientOverride ?: createHttpClient()
    }

    /**
     * Constructs a [PubgApi] with a pre-built [HttpClient].
     *
     * This constructor exists for testing purposes and should not be used in
     * production code. Rate limiting is disabled when using this constructor.
     *
     * @param apiKey Your PUBG API key.
     * @param httpClient A pre-configured [HttpClient], typically backed by a mock engine.
     */
    @PubgktInternal
    public constructor(apiKey: String, httpClient: HttpClient) : this(apiKey, RateLimiter.None) {
        _clientOverride = httpClient
    }

    private fun createHttpClient(): HttpClient = HttpClient(engine) {
        install(ContentNegotiation) {
            serialization(
                ContentType.Application.Json,
                Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    allowStructuredMapKeys = true
                    prettyPrint = true
                    useArrayPolymorphism = false
                },
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
