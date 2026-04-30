@file:OptIn(ExperimentalContracts::class)

package pubgkt

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

/**
 * Asserts that the JSON:API resource `id` is present, smart-casting it to [String]
 * at the call site via a Kotlin Contract.
 *
 * Most JSON:API resources include a top-level `"id"` field. A small number of
 * endpoints (e.g. lifetime stats) intentionally omit it, which is why
 * [JsonApiResourceDeserializer.deserializeResource] declares `id` as `String?`.
 * Serializers that **require** an id should call this function instead of using
 * the unsafe `!!` operator. After the call the compiler knows `id` is non-null,
 * so no further assertion or cast is needed.
 *
 * ```kotlin
 * override fun deserializeResource(
 *     attributes: JsonObject,
 *     id: String?,
 *     relationships: JsonObject?,
 *     included: JsonArray?,
 * ): Player {
 *     requireId(id)              // smart-casts id → String
 *     return Player(id = id, …)  // id is String here, no !! needed
 * }
 * ```
 *
 * @param id The `id` value received from [JsonApiResourceDeserializer.deserializeResource].
 * @throws SerializationException if [id] is `null`.
 */
@PubgktInternal
public fun requireId(id: String?) {
    contract {
        returns() implies (id != null)
    }
    if (id == null) {
        throw SerializationException("Missing required resource 'id'")
    }
}

/**
 * Asserts that a JSON:API resource has a non-null `relationships` object.
 *
 * This follows the same contract-based pattern as [requireId], but for
 * serializers that require `relationships` to be present.
 *
 * @throws SerializationException if [relationships] is `null`.
 */
@PubgktInternal
public fun requireRelationships(relationships: JsonObject?) {
    contract {
        returns() implies (relationships != null)
    }
    if (relationships == null) {
        throw SerializationException("Missing required resource 'relationships'")
    }
}

/**
 * Asserts that a JSON:API resource has a non-null `included` object.
 *
 * This follows the same contract-based pattern as [requireId], but for
 * serializers that require `included` to be present.
 *
 * @throws SerializationException if [included] is `null`.
 */
@PubgktInternal
public fun requireIncluded(included: JsonArray?) {
    contract {
        returns() implies (included != null)
    }
    if (included == null) {
        throw SerializationException("Missing required resource 'included'")
    }
}
