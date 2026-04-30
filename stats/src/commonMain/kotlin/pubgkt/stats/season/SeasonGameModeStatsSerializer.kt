package pubgkt.stats.season

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import pubgkt.GameMode
import pubgkt.JsonApiResourceDeserializer
import pubgkt.requireRelationships
import pubgkt.requiredDouble
import pubgkt.requiredObject
import pubgkt.requiredString
import pubgkt.stats.deserializeGameModeStats

internal object SeasonGameModeStatsSerializer :
    JsonApiResourceDeserializer<SeasonGameModeStats>("pubgkt.stats.season.SeasonGameModeStats") {

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
            stats = deserializeGameModeStats(attributes.requiredObject("gameModeStats").requiredObject(gameModeString))
        )
    }
}
