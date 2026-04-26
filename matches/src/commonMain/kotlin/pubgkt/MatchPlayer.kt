package pubgkt

/**
 * Represents a player in a specific match
 */
public data class MatchPlayer(
    /**
     * Account ID of the player associated with this participant
     */
    val id: String,

    /**
     * PUBG IGN of the player associated with this participant
     */
    val name: String,

    /**
     * Number of players knocked
     * Minimum: 0
     */
    val dbnos: Int,

    /**
     * Number of enemy players this player damaged that were killed by teammates
     * Minimum: 0
     * Maximum: 128
     */
    val assists: Int,

    /**
     * Number of boost items used
     * Minimum: 0
     */
    val boosts: Int,

    /**
     * Total damage dealt. Note: Self inflicted damage is subtracted
     * Minimum: 0
     */
    val damageDealt: Double,

    /**
     * The way by which this player died, or alive if they didn't
     */
    val deathType: DeathType,

    /**
     * Number of enemy players killed with headshots
     * Minimum: 0
     * Maximum: 129
     */
    val headshotKills: Int,

    /**
     * Number of healing items used
     * Minimum: 0
     */
    val heals: Int,

    /**
     * This player's rank in the match based on kills
     * Minimum: 1
     * Maximum: 130
     */
    val killPlace: Int,

    /**
     * Total number of kill streaks
     * Minimum: 0
     */
    val killStreaks: Int,

    /**
     * Number of enemy players killed
     * Minimum: 0
     * Maximum: 129
     */
    val kills: Int,

    /**
     * Longest kill
     * Minimum: 0
     */
    val longestKill: Double,

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
    val timeSurvived: Int,

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
     * This player's placement in the match
     * Minimum: 1
     * Maximum: 130
     */
    val winPlace: Int,
)
