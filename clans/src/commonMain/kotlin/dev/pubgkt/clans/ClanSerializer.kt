package dev.pubgkt.clans

import dev.pubgkt.jsonapi.JsonApiResourceDeserializer
import dev.pubgkt.jsonapi.requireId
import dev.pubgkt.jsonapi.requiredInt
import dev.pubgkt.jsonapi.requiredString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object ClanSerializer : JsonApiResourceDeserializer<Clan>("pubgkt.clans.Clan") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String?,
        relationships: JsonObject?,
        included: JsonArray?,
    ): Clan {
        requireId(id)
        return Clan(
            id = id,
            name = attributes.requiredString("clanName"),
            tag = attributes.requiredString("clanTag"),
            level = attributes.requiredInt("clanLevel"),
            memberCount = attributes.requiredInt("clanMemberCount"),
        )
    }
}
