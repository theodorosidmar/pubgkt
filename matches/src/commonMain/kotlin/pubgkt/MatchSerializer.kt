package pubgkt

import kotlin.time.Instant
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

internal object MatchSerializer : JsonApiResourceDeserializer<Match>("pubgkt.Match") {
    @Suppress("LongMethod")
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
        included: JsonArray?,
    ): Match {
        val participants = included?.filter { it.jsonObject.requiredString("type") == "participant" }
        val rosters = included?.filter { it.jsonObject.requiredString("type") == "roster" }

        return Match(
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
            participants = rosters?.map { squad ->
                val rosterObject = squad.jsonObject
                val squadAttributes = rosterObject.requiredObject("attributes")
                val playerIds = rosterObject
                    .requiredObject("relationships")
                    .requiredObject("participants")
                    .requiredArray("data")
                    .map { player ->
                        player.jsonObject.requiredString("id")
                    }

                MatchParticipant(
                    teamId = squadAttributes.requiredObject("stats").requiredInt("teamId"),
                    rank = squadAttributes.requiredObject("stats").requiredInt("rank"),
                    won = squadAttributes.requiredBoolean("won"),
                    players = playerIds.mapNotNull { playerId ->
                        participants
                            ?.first { it.jsonObject.requiredString("id") == playerId }
                            ?.jsonObject
                            ?.requiredObject("attributes")
                            ?.requiredObject("stats")
                            ?.let {
                                MatchPlayer(
                                    id = it.requiredString("playerId"),
                                    name = it.requiredString("name"),
                                    dbnos = it.requiredInt("DBNOs"),
                                    assists = it.requiredInt("assists"),
                                    boosts = it.requiredInt("boosts"),
                                    damageDealt = it.requiredDouble("damageDealt"),
                                    deathType = DeathType.valueOf(it.requiredString("deathType").uppercase()),
                                    headshotKills = it.requiredInt("headshotKills"),
                                    heals = it.requiredInt("heals"),
                                    killPlace = it.requiredInt("killPlace"),
                                    killStreaks = it.requiredInt("killStreaks"),
                                    kills = it.requiredInt("kills"),
                                    longestKill = it.requiredDouble("longestKill"),
                                    revives = it.requiredInt("revives"),
                                    rideDistance = it.requiredDouble("rideDistance"),
                                    roadKills = it.requiredInt("roadKills"),
                                    swimDistance = it.requiredDouble("swimDistance"),
                                    teamKills = it.requiredInt("teamKills"),
                                    timeSurvived = it.requiredInt("timeSurvived"),
                                    vehicleDestroys = it.requiredInt("vehicleDestroys"),
                                    walkDistance = it.requiredDouble("walkDistance"),
                                    weaponsAcquired = it.requiredInt("weaponsAcquired"),
                                    winPlace = it.requiredInt("winPlace"),
                                )
                            }
                    },
                )
            }.orEmpty(),
        )
    }
}
