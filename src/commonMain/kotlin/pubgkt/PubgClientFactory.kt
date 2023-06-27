package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.serialization
import kotlinx.serialization.json.Json

internal fun makePubgClient(
    apiKey: String,
    platform: PubgPlatform,
): PubgClient =
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
                    prettyPrint = false
                    useArrayPolymorphism = false
                },
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install(ContentEncoding) {
            gzip()
        }
        defaultRequest {
            bearerAuth(apiKey)
            contentType(pubgContentType)
            when (platform) {
                PubgPlatform.XBOX -> url(PUBG_XBOX_PATH)
                PubgPlatform.STEAM -> url(PUBG_STEAM_PATH)
                PubgPlatform.PSN -> url(PUBG_PSN_PATH)
            }
        }
    }
