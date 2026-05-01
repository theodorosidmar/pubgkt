package pubgkt

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.serialization

context(
    pubgApi: PubgApi,
    config: HttpClientConfig<*>,
)
internal fun configureClient() {
    with(config) {
        configureContentNegotiation()
        configureHttpRetry()
        configureContentEncoding()
        configureLogging()
        configureRequestPolicy()
        configureDefaultRequest()
        configureHttpResponseValidator()
    }
}

/**
 * Validates HTTP responses and throws domain-specific exceptions for known error statuses.
 *
 * These exceptions are **never retried** regardless of [RetryPolicy] configuration, because they
 * are thrown after the response is received (bypassing the retry plugin). An [UnauthorizedException]
 * indicates an invalid API key that won't resolve on retry, and a [RateLimitExceededException]
 * is already mitigated proactively by the configured [RateLimiter].
 */
private fun HttpClientConfig<*>.configureHttpResponseValidator() {
    HttpResponseValidator {
        validateResponse { response ->
            if (response.status == HttpStatusCode.Unauthorized) {
                throw UnauthorizedException()
            }

            if (response.status == HttpStatusCode.TooManyRequests) {
                throw RateLimitExceededException(
                    resetAtEpochSeconds = response.headers[HEADER_RATE_LIMIT_RESET]?.toLongOrNull(),
                )
            }
        }
    }
}

private fun HttpClientConfig<*>.configureDefaultRequest() {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = HOST
        }

        contentType(
            ContentType(
                contentType = "application",
                contentSubtype = "vnd.api+json",
            )
        )
    }
}

context(pubgApi: PubgApi)
private fun HttpClientConfig<*>.configureRequestPolicy() {
    install(RequestPolicyPlugin) {
        rateLimiter = pubgApi.rateLimiter
        apiKey = pubgApi.apiKey
    }
}

/**
 * Installs and configures the Ktor [HttpRequestRetry] plugin based on the
 * [RetryPolicy] defined in [PubgApi.retry].
 *
 * Does nothing when [PubgApi.retry] is [NoRetry].
 */
context(pubgApi: PubgApi)
private fun HttpClientConfig<*>.configureHttpRetry() {
    if (!pubgApi.retry.enabled) return

    pubgApi.retry as Retry

    install(HttpRequestRetry) {
        val (maxRetries, backoff, retryOnExceptions) = pubgApi.retry

        if (maxRetries > 0) {
            retryOnServerErrors(maxRetries = maxRetries)
        }

        if (backoff.enabled) {
            backoff as ExponentialBackoff
            exponentialDelay(
                base = backoff.base,
                baseDelayMs = backoff.baseDelayMs,
                maxDelayMs = backoff.maxDelayMs,
                randomizationMs = backoff.randomizationMs,
                respectRetryAfterHeader = false,
            )
        }

        if (retryOnExceptions.isNotEmpty()) {
            retryOnExceptionIf { _, cause ->
                retryOnExceptions.any { throwable -> throwable.isInstance(cause) }
            }
        }
    }
}

private fun HttpClientConfig<*>.configureLogging() {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
}

private fun HttpClientConfig<*>.configureContentEncoding() {
    install(ContentEncoding) {
        gzip()
    }
}

private fun HttpClientConfig<*>.configureContentNegotiation() {
    install(ContentNegotiation) {
        serialization(
            contentType = ContentType.Application.Json,
            format = json,
        )
    }
}
