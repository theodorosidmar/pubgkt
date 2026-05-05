package dev.pubgkt.http

import dev.pubgkt.ratelimit.RateLimiter

internal class RequestPolicyPluginConfig {
    lateinit var apiKey: String
    lateinit var rateLimiter: RateLimiter
}
