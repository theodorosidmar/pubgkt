package pubgkt

import io.ktor.client.plugins.api.createClientPlugin

internal val RateLimitPlugin = createClientPlugin("PubgRateLimit", ::RateLimitConfig) {
    val limiter = pluginConfig.rateLimiter

    onRequest { _, _ ->
        limiter.throttle()
    }
}
