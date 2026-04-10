package pubgkt

import io.ktor.client.plugins.api.createClientPlugin

internal class RateLimitConfig {
    lateinit var rateLimiter: RateLimiter
}

internal val RateLimitPlugin = createClientPlugin("PubgktRateLimit", ::RateLimitConfig) {
    val limiter = pluginConfig.rateLimiter

    onRequest { _, _ ->
        limiter.throttle()
    }
}
