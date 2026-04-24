package pubgkt

import kotlin.time.Instant
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object MatchSerializer : JsonApiResourceDeserializer<Match>("pubgkt.Match") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
        included: JsonArray?,
    ): Match = Match(
        id = id,
        titleId = attributes.requiredString("titleId"),
        platform = Platform.valueOf(attributes.requiredString("shardId").uppercase()),
        isCustomMatch = attributes.requiredBoolean("isCustomMatch"),
        matchType = MatchType.valueOf(attributes.requiredString("matchType").uppercase()),
        gameMode = GameMode.valueOf(attributes.requiredString("gameMode").uppercase()),
        mapName = Map.valueOf(attributes.requiredString("mapName").uppercase()),
        seasonState = SeasonState.valueOf(attributes.requiredString("seasonState").uppercase()),
        createdAt = Instant.parse(attributes.requiredString("createdAt").uppercase()),
        duration = attributes.requiredInt("duration"),
    )
}
