package pubgkt.stats.lifetime

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import pubgkt.GameMode
import pubgkt.jsonapi.JsonApiResourceDeserializer
import pubgkt.jsonapi.requireRelationships
import pubgkt.jsonapi.requiredDouble
import pubgkt.jsonapi.requiredObject
import pubgkt.jsonapi.requiredString
import pubgkt.stats.deserializeGameModeStats

internal object LifetimeGameModeStatsSerializer :
    JsonApiResourceDeserializer<LifetimeGameModeStats>("pubgkt.stats.lifetime.LifetimeGameModeStats") {
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
            stats = deserializeGameModeStats(attributes.requiredObject("gameModeStats").requiredObject(gameModeString)),
        )
    }
}
