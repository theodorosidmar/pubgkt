package pubgkt.stats

import kotlinx.serialization.json.JsonObject
import pubgkt.jsonapi.requiredDouble
import pubgkt.jsonapi.requiredInt

internal fun deserializeGameModeStats(stats: JsonObject): GameModeStats = GameModeStats(
    assists = stats.requiredInt("assists"),
    boosts = stats.requiredInt("boosts"),
    dbnos = stats.requiredInt("dBNOs"),
    dailyKills = stats.requiredInt("dailyKills"),
    dailyWins = stats.requiredInt("dailyWins"),
    damageDealt = stats.requiredDouble("damageDealt"),
    days = stats.requiredInt("days"),
    headshotKills = stats.requiredInt("headshotKills"),
    heals = stats.requiredInt("heals"),
    kills = stats.requiredInt("kills"),
    longestKill = stats.requiredDouble("longestKill"),
    longestTimeSurvived = stats.requiredDouble("longestTimeSurvived"),
    losses = stats.requiredInt("losses"),
    maxKillStreaks = stats.requiredInt("maxKillStreaks"),
    mostSurvivalTime = stats.requiredDouble("mostSurvivalTime"),
    revives = stats.requiredInt("revives"),
    rideDistance = stats.requiredDouble("rideDistance"),
    roadKills = stats.requiredInt("roadKills"),
    roundMostKills = stats.requiredInt("roundMostKills"),
    roundsPlayed = stats.requiredInt("roundsPlayed"),
    suicides = stats.requiredInt("suicides"),
    swimDistance = stats.requiredDouble("swimDistance"),
    teamKills = stats.requiredInt("teamKills"),
    timeSurvived = stats.requiredDouble("timeSurvived"),
    top10s = stats.requiredInt("top10s"),
    vehicleDestroys = stats.requiredInt("vehicleDestroys"),
    walkDistance = stats.requiredDouble("walkDistance"),
    weaponsAcquired = stats.requiredInt("weaponsAcquired"),
    weeklyKills = stats.requiredInt("weeklyKills"),
    weeklyWins = stats.requiredInt("weeklyWins"),
    wins = stats.requiredInt("wins"),
)
