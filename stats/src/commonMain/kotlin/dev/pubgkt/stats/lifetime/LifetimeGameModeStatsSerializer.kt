package dev.pubgkt.stats.lifetime

import dev.pubgkt.GameMode
import dev.pubgkt.jsonapi.JsonApiResourceDeserializer
import dev.pubgkt.jsonapi.requireRelationships
import dev.pubgkt.jsonapi.requiredDouble
import dev.pubgkt.jsonapi.requiredObject
import dev.pubgkt.jsonapi.requiredString
import dev.pubgkt.stats.deserializeGameModeStats
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object LifetimeGameModeStatsSerializer :
    JsonApiResourceDeserializer<LifetimeGameModeStats>(
        "pubgkt.stats.lifetime.LifetimeGameModeStats",
    ) {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String?,
        relationships: JsonObject?,
        included: JsonArray?,
    ): LifetimeGameModeStats {
        requireRelationships(relationships)
        val gameModeString = attributes.requiredObject("gameModeStats").keys.first()
        val gameMode = GameMode.of(gameModeString)
        return LifetimeGameModeStats(
            playerId = relationships.requiredObject("player").requiredObject("data").requiredString("id"),
            bestRankPoint = attributes.requiredDouble("bestRankPoint"),
            gameMode = gameMode,
            stats = deserializeGameModeStats(
                attributes.requiredObject("gameModeStats").requiredObject(gameModeString),
            ),
        )
    }
}
