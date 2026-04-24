package pubgkt

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

/**
 * Deserializes a JSON:API single-resource response (`{"data": { … }}`) into [T].
 *
 * Strips the `"data"` envelope automatically and passes the optional
 * `"included"` array to the [deserializer].
 *
 * ```kotlin
 * val player: Player = client.get("players/$id").deserialize(PlayerSerializer)
 * ```
 */
@PubgktInternal
public suspend fun <T> HttpResponse.deserialize(deserializer: JsonApiResourceDeserializer<T>): T {
    val root = root()
    return deserializer.fromResource(
        resource = root.requiredObject("data"),
        included = root.optionalArray("included"),
    )
}

/**
 * Deserializes a JSON:API collection response (`{"data": [ … ]}`) into `List<T>`.
 *
 * Strips the `"data"` envelope automatically and passes the optional
 * `"included"` array to the [deserializer] for each resource element.
 *
 * ```kotlin
 * val players: List<Player> = client.get("players").deserializeList(PlayerSerializer)
 * ```
 */
@PubgktInternal
public suspend fun <T> HttpResponse.deserializeList(deserializer: JsonApiResourceDeserializer<T>): List<T> {
    val root = root()
    val included = root.optionalArray("included")
    val data = root["data"] as? JsonArray
        ?: throw SerializationException("Missing 'data' array")
    return data.map { deserializer.fromResource(it.jsonObject, included) }
}

private suspend fun HttpResponse.root(): JsonObject =
    json.parseToJsonElement(bodyAsText()).jsonObject
