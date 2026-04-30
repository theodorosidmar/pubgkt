package pubgkt.stats.ranked

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import pubgkt.JsonApiResourceDeserializer
import pubgkt.requireRelationships
import pubgkt.requiredDouble
import pubgkt.requiredInt
import pubgkt.requiredObject
import pubgkt.requiredString

internal object RankedStatsSerializer : JsonApiResourceDeserializer<RankedStats>("pubgkt.stats.ranked.RankedStats") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String?,
        relationships: JsonObject?,
        included: JsonArray?,
    ): RankedStats {
        requireRelationships(relationships)
        val stats = attributes.requiredObject("rankedGameModeStats").requiredObject("squad")
        return RankedStats(
            playerId = relationships.requiredObject("player").requiredObject("data").requiredString("id"),
            seasonId = relationships.requiredObject("season").requiredObject("data").requiredString("id"),
            currentRankPoint = stats.requiredInt("currentRankPoint"),
            bestRankPoint = stats.requiredInt("bestRankPoint"),
            currentTier = stats.requiredObject("currentTier").let(Tier::fromJsonObject),
            bestTier = stats.requiredObject("bestTier").let(Tier::fromJsonObject),
            roundsPlayed = stats.requiredInt("roundsPlayed"),
            avgRank = stats.requiredDouble("avgRank"),
            top10Ratio = stats.requiredDouble("top10Ratio"),
            winRatio = stats.requiredDouble("winRatio"),
            assists = stats.requiredInt("assists"),
            wins = stats.requiredInt("wins"),
            kda = stats.requiredDouble("kda"),
            avgKill = stats.requiredDouble("avgKill"),
            kills = stats.requiredInt("kills"),
            deaths = stats.requiredInt("deaths"),
            damageDealt = stats.requiredDouble("damageDealt"),
            dbnos = stats.requiredInt("dBNOs"),
        )
    }
}
