package pubgkt

import kotlinx.serialization.json.JsonObject

internal object ClanSerializer : JsonApiResourceDeserializer<Clan>("pubgkt.Clan") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
    ): Clan = Clan(
        id = id,
        name = attributes.requiredString("clanName"),
        tag = attributes.requiredString("clanTag"),
        level = attributes.requiredInt("clanLevel"),
        memberCount = attributes.requiredInt("clanMemberCount"),
    )
}
