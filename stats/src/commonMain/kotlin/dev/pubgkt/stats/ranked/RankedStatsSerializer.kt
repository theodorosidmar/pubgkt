package dev.pubgkt.stats.ranked

import dev.pubgkt.jsonapi.JsonApiResourceDeserializer
import dev.pubgkt.jsonapi.requireRelationships
import dev.pubgkt.jsonapi.requiredDouble
import dev.pubgkt.jsonapi.requiredInt
import dev.pubgkt.jsonapi.requiredObject
import dev.pubgkt.jsonapi.requiredString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object RankedStatsSerializer : JsonApiResourceDeserializer<RankedStats>(
    "pubgkt.stats.ranked.RankedStats",
) {
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
            currentTier = stats.requiredObject("currentTier")
                .let(Tier.Companion::fromJsonObject),
            bestTier = stats.requiredObject("bestTier").let(Tier.Companion::fromJsonObject),
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
