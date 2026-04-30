package pubgkt.stats.lifetime

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import pubgkt.JsonApiResourceDeserializer
import pubgkt.requireRelationships
import pubgkt.requiredDouble
import pubgkt.requiredObject
import pubgkt.requiredString
import pubgkt.stats.deserializeGameModeStats

internal object LifetimePlayerStatsSerializer :
    JsonApiResourceDeserializer<LifetimePlayerStats>("pubgkt.stats.lifetime.LifetimePlayerStats") {

    override fun deserializeResource(
        attributes: JsonObject,
        id: String?,
        relationships: JsonObject?,
        included: JsonArray?,
    ): LifetimePlayerStats {
        requireRelationships(relationships)
        val gameModeStats = attributes.requiredObject("gameModeStats")
        return LifetimePlayerStats(
            playerId = relationships.requiredObject("player").requiredObject("data").requiredString("id"),
            bestRankPoint = attributes.requiredDouble("bestRankPoint"),
            soloTpp = deserializeGameModeStats(gameModeStats.requiredObject("solo")),
            duoTpp = deserializeGameModeStats(gameModeStats.requiredObject("duo")),
            squadTpp = deserializeGameModeStats(gameModeStats.requiredObject("squad")),
            soloFpp = deserializeGameModeStats(gameModeStats.requiredObject("solo-fpp")),
            duoFpp = deserializeGameModeStats(gameModeStats.requiredObject("duo-fpp")),
            squadFpp = deserializeGameModeStats(gameModeStats.requiredObject("squad-fpp")),
        )
    }
}
