package pubgkt

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.bearerAuth
import io.ktor.client.statement.request

internal val RequestPolicyPlugin = createClientPlugin("PubgktRequestPolicy", ::RequestPolicyPluginConfig) {
    val apiKey = pluginConfig.apiKey
    val rateLimiter = pluginConfig.rateLimiter

    onRequest { request, _ ->
        val policy = request.requestPolicyOrDefault

        if (policy.requiresAuth) {
            request.bearerAuth(apiKey)
        }

        if (policy.isRateLimited) {
            rateLimiter.throttle()
        }
    }

    onResponse { response ->
        val policy = response.request.attributes.requestPolicyOrDefault

        if (policy.isRateLimited) {
            rateLimiter.onResponse(
                limit = response.headers[HEADER_RATE_LIMIT_LIMIT]?.toInt(),
                remaining = response.headers[HEADER_RATE_LIMIT_REMAINING]?.toInt(),
                reset = response.headers[HEADER_RATE_LIMIT_RESET]?.toLong(),
            )
        }
    }
}
