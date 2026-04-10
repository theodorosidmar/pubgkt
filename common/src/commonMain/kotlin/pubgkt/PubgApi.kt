package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.serialization
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext

/**
 * Class to interact with the PUBG API.
 * @param apiKey Your PUBG API key.
 * @property platform The [Platform] you want to use
 */
public class PubgApi(private val apiKey: String) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    public var platform: Platform = Platform.STEAM

    @PubgktInternal
    public val client: HttpClient by lazy {
        HttpClient(engine) {
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

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = PATH
                    path(platform.name.lowercase())
                }
                bearerAuth(apiKey)
                contentType(
                    ContentType(
                        contentType = "application",
                        contentSubtype = "vnd.api+json",
                    )
                )
            }
        }
    }
}
