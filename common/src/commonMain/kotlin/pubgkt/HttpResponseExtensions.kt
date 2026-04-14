package pubgkt

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

/**
 * Deserializes a JSON:API single-resource response (`{"data": { … }}`) into [T].
 *
 * Strips the `"data"` envelope automatically, then delegates to the given
 * [deserializer] for the inner resource object.
 *
 * ```kotlin
 * val player: Player = client.get("players/$id").deserializeResource(PlayerSerializer)
 * ```
 */
@PubgktInternal
public suspend fun <T> HttpResponse.deserialize(deserializer: DeserializationStrategy<T>): T =
    json.decodeFromJsonElement(deserializer, data())

/**
 * Deserializes a JSON:API collection response (`{"data": [ … ]}`) into `List<T>`.
 *
 * Strips the `"data"` envelope automatically, then delegates to the given
 * [deserializer] for each element in the array.
 *
 * ```kotlin
 * val players: List<Player> = client.get("players").deserializeResourceList(PlayerSerializer)
 * ```
 */
@PubgktInternal
public suspend fun <T> HttpResponse.deserializeList(deserializer: DeserializationStrategy<T>): List<T> =
    data().jsonArray.map { json.decodeFromJsonElement(deserializer, it) }

private suspend fun HttpResponse.data(): JsonElement {
    val root = json.parseToJsonElement(bodyAsText()).jsonObject
    return root["data"] ?: throw SerializationException("Missing 'data' field")
}
