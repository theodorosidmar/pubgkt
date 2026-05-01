package pubgkt.clans

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import pubgkt.jsonapi.JsonApiResourceDeserializer
import pubgkt.jsonapi.requireId
import pubgkt.jsonapi.requiredInt
import pubgkt.jsonapi.requiredString

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
