package dev.pubgkt

import dev.pubgkt.ratelimit.ConcurrentDelayRateLimiter
import dev.pubgkt.ratelimit.DelayRateLimiter
import dev.pubgkt.ratelimit.RateLimiter

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: RateLimitSample <api-key>")

    println("=== No rate limiting ===")
    run {
        val api = PubgApi(apiKey, rateLimiter = RateLimiter.None)
        println("API is up: ${api.isUp()}")
    }

    println("\n=== DelayRateLimiter (default) ===")
    run {
        val api = PubgApi(apiKey, rateLimiter = DelayRateLimiter())
        println("API is up: ${api.isUp()}")
    }

    println("\n=== ConcurrentDelayRateLimiter ===")
    run {
        val api = PubgApi(apiKey, rateLimiter = ConcurrentDelayRateLimiter())
        println("API is up: ${api.isUp()}")
    }

    println("\n=== Custom RateLimiter ===")
    run {
        val loggingLimiter =
            object : RateLimiter {
                private var requestCount = 0

                override suspend fun throttle() {
                    requestCount++
                    println("  [RateLimiter] Request #$requestCount — allowing")
                }

                override fun onResponse(
                    limit: Int?,
                    remaining: Int?,
                    reset: Long?,
                ) {
                    println("  [RateLimiter] Response — limit=$limit, remaining=$remaining, reset=$reset")
                }
            }
        val api = PubgApi(apiKey, rateLimiter = loggingLimiter)
        println("API is up: ${api.isUp()}")
        println("Seasons: ${api.seasons(Platform.STEAM).size}")
    }
}
