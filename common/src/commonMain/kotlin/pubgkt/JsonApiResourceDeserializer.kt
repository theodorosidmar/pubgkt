package pubgkt

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

/**
 * Base deserializer for JSON:API resource objects.
 *
 * Every resource in the PUBG JSON:API follows the same envelope:
 * ```json
 * {
 *   "type": "player",
 *   "id":   "account.abc123",
 *   "attributes":    { … },
 *   "relationships": { … }
 * }
 * ```
 *
 * Subclasses only need to implement [deserializeResource] to map the
 * pre-parsed `id`, `attributes`, and `relationships` into the domain model.
 *
 * ### Example
 * ```kotlin
 * internal object PlayerSerializer : JsonApiResourceDeserializer<Player>("pubgkt.Player") {
 *
 *     override fun deserializeResource(id: String, attributes: JsonObject, relationships: JsonObject?) =
 *         Player(id = id, name = attributes.requiredString("name"), …)
 * }
 * ```
 *
 * @param serialName Unique descriptor name, conventionally the fully-qualified
 *   class name of the target type (e.g. `"pubgkt.Player"`).
 */
@PubgktInternal
public abstract class JsonApiResourceDeserializer<T>(
    serialName: String,
) : DeserializationStrategy<T> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor(serialName)

    /**
     * Constructs `T` from the pre-parsed JSON:API resource fields.
     *
     * @param id            Value of the top-level `"id"` key.
     * @param attributes    The `"attributes"` [JsonObject].
     * @param relationships The `"relationships"` [JsonObject], or `null` when absent.
     */
    protected abstract fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
    ): T

    override fun deserialize(decoder: Decoder): T {
        val root = (decoder as JsonDecoder).decodeJsonElement().jsonObject
        return deserializeResource(
            attributes = root.requiredObject("attributes"),
            id = root.requiredString("id"),
            relationships = root.optionalObject("relationships"),
        )
    }
}
