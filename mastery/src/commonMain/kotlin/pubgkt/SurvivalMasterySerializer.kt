package pubgkt

import kotlinx.serialization.json.JsonObject

internal object SurvivalMasterySerializer : JsonApiResourceDeserializer<SurvivalMastery>("pubgkt.SurivalMastery") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String,
        relationships: JsonObject?,
    ): SurvivalMastery {
        val stats = attributes.requiredObject("stats")
        return SurvivalMastery(
            playerId = id,
            xp = attributes.requiredInt("xp"),
            tier = attributes.requiredInt("tier"),
            level = attributes.requiredInt("level"),
            totalMatchesPlayed = attributes.requiredInt("totalMatchesPlayed"),
            airDropsCalled = stats.requiredObject("airDropsCalled").let(::deserializeStats),
            damageDealt = stats.requiredObject("damageDealt").let(::deserializeStats),
            damageTaken = stats.requiredObject("damageTaken").let(::deserializeStats),
            distanceBySwimming = stats.requiredObject("distanceBySwimming").let(::deserializeStats),
            distanceByVehicle = stats.requiredObject("distanceByVehicle").let(::deserializeStats),
            distanceOnFoot = stats.requiredObject("distanceOnFoot").let(::deserializeStats),
            distanceTotal = stats.requiredObject("distanceTotal").let(::deserializeStats),
            healed = stats.requiredObject("healed").let(::deserializeStats),
            hotDropLandings = stats.requiredObject("hotDropLandings").requiredInt("total"),
            enemyCratesLooted = stats.requiredObject("enemyCratesLooted").let(::deserializeStats),
            uniqueItemsLooted = stats.requiredObject("uniqueItemsLooted").let(::deserializeStats),
            position = stats.requiredObject("position").let(::deserializeStats),
            revived = stats.requiredObject("revived").let(::deserializeStats),
            teammatesRevived = stats.requiredObject("teammatesRevived").let(::deserializeStats),
            timeSurvived = stats.requiredObject("timeSurvived").let(::deserializeStats),
            throwablesThrown = stats.requiredObject("throwablesThrown").let(::deserializeStats),
            top10 = stats.requiredObject("top10").requiredInt("total"),
        )
    }

    private fun deserializeStats(stats: JsonObject): SurvivalStats = SurvivalStats(
        total = stats.optionalDouble("total") ?: 0.0,
        average = stats.optionalDouble("average") ?: 0.0,
        careerBest = stats.optionalDouble("careerBest") ?: 0.0,
        lastMatchValue = stats.optionalDouble("lastMatchValue") ?: 0.0,
    )
}
