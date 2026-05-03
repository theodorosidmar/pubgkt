package pubgkt.stats

/**
 * Game Mode stats objects contain a player's aggregated stats for a game mode in the context of a season
 */
public data class GameModeStats(
    /**
     * Number of enemy players this player damaged that were killed by teammates
     * Minimum: 0
     */
    val assists: Int,
    /**
     * Number of boost items used
     * Minimum: 0
     */
    val boosts: Int,
    /**
     * Number of players knocked
     * Minimum: 0
     */
    val dbnos: Int,
    /**
     * Number of kills during the most recent day played
     */
    val dailyKills: Int,
    /**
     * Number of wins during the most recent day played
     */
    val dailyWins: Int,
    /**
     * Total damage dealt. Note: Self inflicted damage is subtracted
     * Minimum: 0
     */
    val damageDealt: Double,
    /**
     * Minimum: 0
     */
    val days: Int,
    /**
     * Number of enemy players killed with headshots
     * Minimum: 0
     */
    val headshotKills: Int,
    /**
     * Number of healing items used
     * Minimum: 0
     */
    val heals: Int,
    /**
     * Number of enemy players killed
     * Minimum: 0
     */
    val kills: Int,
    /**
     * Longest kill
     * Minimum: 0
     */
    val longestKill: Double,
    /**
     * Longest time survived in a match
     * Minimum: 0
     */
    val longestTimeSurvived: Double,
    /**
     * Number of matches lost
     * Minimum: 0
     */
    val losses: Int,
    /**
     * Minimum: 0
     */
    val maxKillStreaks: Int,
    /**
     * Longest time survived in a match
     * Minimum: 0
     */
    val mostSurvivalTime: Double,
    /**
     * Number of times this player revived teammates
     * Minimum: 0
     */
    val revives: Int,
    /**
     * Total distance traveled in vehicles measured in meters
     * Minimum: 0
     */
    val rideDistance: Double,
    /**
     * Number of kills while in a vehicle
     * Minimum: 0
     */
    val roadKills: Int,
    /**
     * Highest number of kills in a single match
     * Minimum: 0
     */
    val roundMostKills: Int,
    /**
     * Number of matches played
     * Minimum: 0
     */
    val roundsPlayed: Int,
    /**
     * Number of self-inflicted deaths
     * Minimum: 0
     */
    val suicides: Int,
    /**
     * Total distance traveled while swimming measured in meters
     * Minimum: 0
     */
    val swimDistance: Double,
    /**
     * Number of times this player killed a teammate
     * Minimum: 0
     */
    val teamKills: Int,
    /**
     * Amount of time survived measured in seconds
     * Minimum: 0
     */
    val timeSurvived: Double,
    /**
     * Number of times this player made it to the top 10 in a match
     * Minimum: 0
     */
    val top10s: Int,
    /**
     * Number of vehicles destroyed
     * Minimum: 0
     */
    val vehicleDestroys: Int,
    /**
     * Total distance traveled on foot measured in meters
     * Minimum: 0
     */
    val walkDistance: Double,
    /**
     * Number of weapons picked up
     * Minimum: 0
     */
    val weaponsAcquired: Int,
    /**
     * Number of kills during the most recent week played
     * Minimum: 0
     */
    val weeklyKills: Int,
    /**
     * Number of wins during the most recent week played
     * Minimum: 0
     */
    val weeklyWins: Int,
    /**
     * Number of matches won
     * Minimum: 0
     */
    val wins: Int,
)
