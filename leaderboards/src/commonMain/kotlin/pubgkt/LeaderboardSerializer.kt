package pubgkt

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

internal object LeaderboardSerializer : JsonApiResourceDeserializer<Leaderboard>("pubgkt.Leaderboard") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
        included: JsonArray?,
    ): Leaderboard = Leaderboard(
        seasonId = attributes.requiredString("seasonId"),
        gameMode = attributes.requiredString("gameMode").let(GameMode::of),
        platformRegion = attributes.requiredString("shardId").let(PlatformRegion::of),
        placements = included
            ?.filterIsInstance<JsonObject>()
            ?.filter { it.requiredString("type") == "player" }
            ?.map { player ->
                val playerAttributes = player.requiredObject("attributes")
                val stats = playerAttributes.requiredObject("stats")
                LeaderboardPlacement(
                    playerId = player.requiredString("id"),
                    playerName = playerAttributes.requiredString("name"),
                    rank = playerAttributes.requiredInt("rank"),
                    rankPoints = stats.requiredInt("rankPoints"),
                    wins = stats.requiredInt("wins"),
                    games = stats.requiredInt("games"),
                    averageDamage = stats.requiredInt("averageDamage"),
                    kills = stats.requiredInt("kills"),
                    kda = stats.requiredInt("kda"),
                    averageRank = stats.requiredDouble("averageRank"),
                    averageKill = stats.requiredDouble("averageKill"),
                    tier = stats.requiredString("tier"),
                    subTier = stats.requiredString("subTier"),
                )
            }.orEmpty(),
    )
}
