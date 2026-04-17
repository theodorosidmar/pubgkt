package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonObject

internal object PlayerSerializer : JsonApiResourceDeserializer<Player>("pubgkt.Player") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
    ): Player = Player(
        id = id,
        banType = attributes.requiredString("banType").toBanType(),
        clanId = attributes.optionalString("clanId"),
        name = attributes.requiredString("name"),
        titleId = attributes.requiredString("titleId"),
        shardId = attributes.requiredString("shardId"),
        patchVersion = attributes.optionalString("patchVersion"),
        matches = relationships
            ?.optionalObject("matches")
            ?.optionalArray("data")
            ?.filterIsInstance<JsonObject>()
            ?.map { item ->
                item.requiredString("id").let(::PlayerMatch)
            }.orEmpty(),
    )
}

private fun String.toBanType(): BanType =
    BanType.entries.firstOrNull { it.name.equals(this, ignoreCase = true) }
        ?: throw SerializationException("Unknown banType '$this'")
