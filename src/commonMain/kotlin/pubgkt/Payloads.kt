package pubgkt

import kotlinx.serialization.Serializable

@Serializable
data class PlayersResponse(val data: List<Player>)

@Serializable
data class Player(val id: String)

@Serializable
data class LifetimeResponse(val data: List<Lifetime>)

@Serializable
data class Lifetime(val attributes: Attributes)

@Serializable
data class Attributes(val gameModeStats: Map<String, Stats>)

@Serializable
data class Stats(
    val assists: Int,
    val boosts: Int,
    val dBNOs: Int,
    val dailyKills: Int,
    val dailyWins: Int,
    val damageDealt: Double,
    val days: Int,
    val headshotKills: Int,
    val heals: Int,
    val killPoints: Int,
    val kills: Int,
    val longestKill: Double,
    val longestTimeSurvived: Double,
    val losses: Int,
    val maxKillStreaks: Int,
    val mostSurvivalTime: Double,
    val rankPoints: Int,
    val rankPointsTitle: String,
    val revives: Int,
    val rideDistance: Double,
    val roadKills: Int,
    val roundMostKills: Int,
    val roundsPlayed: Int,
    val suicides: Int,
    val swimDistance: Double,
    val teamKills: Int,
    val timeSurvived: Double,
    val top10s: Int,
    val vehicleDestroys: Int,
    val walkDistance: Double,
    val weaponsAcquired: Int,
    val weeklyKills: Int,
    val weeklyWins: Int,
    val winPoints: Int,
    val wins: Int,
)
