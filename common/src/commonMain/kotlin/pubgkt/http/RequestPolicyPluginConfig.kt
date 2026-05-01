package pubgkt.http

import pubgkt.ratelimit.RateLimiter

internal class RequestPolicyPluginConfig {
    lateinit var apiKey: String
    lateinit var rateLimiter: RateLimiter
}
