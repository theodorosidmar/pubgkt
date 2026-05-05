package dev.pubgkt.stats.season

import dev.pubgkt.GameMode
import dev.pubgkt.jsonapi.JsonApiResourceDeserializer
import dev.pubgkt.jsonapi.requireRelationships
import dev.pubgkt.jsonapi.requiredDouble
import dev.pubgkt.jsonapi.requiredObject
import dev.pubgkt.jsonapi.requiredString
import dev.pubgkt.stats.deserializeGameModeStats
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object SeasonGameModeStatsSerializer :
    JsonApiResourceDeserializer<SeasonGameModeStats>(
        "pubgkt.stats.season.SeasonGameModeStats",
    ) {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String?,
        relationships: JsonObject?,
        included: JsonArray?,
    ): SeasonGameModeStats {
        requireRelationships(relationships)
        val gameModeString = attributes.requiredObject("gameModeStats").keys.first()
        val gameMode = GameMode.of(gameModeString)
        return SeasonGameModeStats(
            playerId = relationships.requiredObject("player").requiredObject("data").requiredString("id"),
            seasonId = relationships.requiredObject("season").requiredObject("data").requiredString("id"),
            bestRankPoint = attributes.requiredDouble("bestRankPoint"),
            gameMode = gameMode,
            stats = deserializeGameModeStats(
                attributes.requiredObject("gameModeStats").requiredObject(gameModeString),
            ),
        )
    }
}
