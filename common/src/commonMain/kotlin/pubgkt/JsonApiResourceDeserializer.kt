package pubgkt

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonArray
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
 * pre-parsed `id`, `attributes`, `relationships`, and `included` into the
 * domain model.
 *
 * ### Example
 * ```kotlin
 * internal object PlayerSerializer : JsonApiResourceDeserializer<Player>("pubgkt.Player") {
 *
 *     override fun deserializeResource(
 *         attributes: JsonObject, id: String, relationships: JsonObject?, included: JsonArray?,
 *     ) = Player(id = id, name = attributes.requiredString("name"), …)
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
     * @param attributes    The `"attributes"` [JsonObject].
     * @param id            Value of the top-level `"id"` key.
     * @param relationships The `"relationships"` [JsonObject], or `null` when absent.
     * @param included      The root-level `"included"` [JsonArray], or `null` when absent.
     */
    protected abstract fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
        included: JsonArray?,
    ): T

    /**
     * Deserializes a single resource [JsonObject] with optional [included] context.
     *
     * This is the primary entry point used by [HttpResponse][io.ktor.client.statement.HttpResponse]
     * extension functions. It extracts `id`, `attributes`, and `relationships`
     * from [resource] and delegates to [deserializeResource].
     *
     * @param resource A JSON:API resource object (the content of a `"data"` field).
     * @param included The root-level `"included"` array, shared across all
     *   resources in a response. Pass `null` when the response has no side-loaded data.
     */
    @PubgktInternal
    public fun fromResource(
        resource: JsonObject,
        included: JsonArray? = null,
    ): T =
        deserializeResource(
            attributes = resource.requiredObject("attributes"),
            id = resource.requiredString("id"),
            relationships = resource.optionalObject("relationships"),
            included = included,
        )

    /**
     * [DeserializationStrategy] entry point.
     *
     * Expects the decoded [JsonElement][kotlinx.serialization.json.JsonElement] to
     * be a full JSON:API envelope (`{ "data": { … }, "included": [ … ] }`).
     * Useful when calling [Json.decodeFromJsonElement][kotlinx.serialization.json.Json.decodeFromJsonElement]
     * directly (e.g. in tests).
     */
    override fun deserialize(decoder: Decoder): T {
        val root = (decoder as JsonDecoder).decodeJsonElement().jsonObject
        return fromResource(
            resource = root.requiredObject("data"),
            included = root.optionalArray("included"),
        )
    }
}
