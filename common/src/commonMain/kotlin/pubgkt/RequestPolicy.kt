package pubgkt

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes

@PubgktInternal
public sealed interface RequestPolicy {
    public val requiresAuth: Boolean
    public val isRateLimited: Boolean
}

internal data object DefaultRequestPolicy : RequestPolicy {
    override val requiresAuth: Boolean = true
    override val isRateLimited: Boolean = true
}

internal data object PublicRequestPolicy : RequestPolicy {
    override val requiresAuth: Boolean = false
    override val isRateLimited: Boolean = false
}

internal val RequestPolicyKey: AttributeKey<RequestPolicy> = AttributeKey("pubgkt.request.policy")

internal fun HttpRequestBuilder.requestPolicy(policy: RequestPolicy) {
    attributes.put(RequestPolicyKey, policy)
}

internal val HttpRequestBuilder.requestPolicyOrDefault: RequestPolicy
    get() = attributes.getOrNull(RequestPolicyKey) ?: DefaultRequestPolicy

internal val Attributes.requestPolicyOrDefault: RequestPolicy
    get() = getOrNull(RequestPolicyKey) ?: DefaultRequestPolicy
