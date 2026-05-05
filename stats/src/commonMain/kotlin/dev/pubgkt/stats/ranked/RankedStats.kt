package dev.pubgkt.stats.ranked

public data class RankedStats(
    /**
     * Account ID of the player associated with this stats
     */
    val playerId: String,
    /**
     * [dev.pubgkt.Season] id of the stats
     */
    val seasonId: String,
    /**
     * Player's current rank points
     * Minimum: 0
     */
    val currentRankPoint: Int,
    /**
     * Player's highest rank points
     * Minimum: 0
     */
    val bestRankPoint: Int,
    /**
     * Player's current ranked tier
     */
    val currentTier: Tier,
    /**
     * Player's highest ranked tier
     */
    val bestTier: Tier,
    /**
     * Number of matches played
     * Minimum: 0
     */
    val roundsPlayed: Int,
    /**
     * Average rank
     * Minimum: 0
     */
    val avgRank: Double,
    /**
     * Ratio of number of times this player made it to the top 10 in a match / times didn't make it to top 10
     * Minimum: 0
     */
    val top10Ratio: Double,
    /**
     * Ratio of number of matches won / matches didn't win
     * Minimum: 0
     */
    val winRatio: Double,
    /**
     * Number of enemy players this player damaged that were killed by teammates
     */
    val assists: Int,
    /**
     * Number of matches won
     */
    val wins: Int,
    /**
     * Kill death assist ratio
     */
    val kda: Double,
    /**
     * Average kills per match
     * Minimum: 0
     */
    val avgKill: Double,
    /**
     * Number of enemy players killed
     * Minimum: 0
     */
    val kills: Int,
    /**
     * Number of player deaths
     * Minimum: 0
     */
    val deaths: Int,
    /**
     * Total damage dealt. Note: Self inflicted damage is subtracted
     * Minimum: 0
     */
    val damageDealt: Double,
    /**
     * Number of enemy players knocked
     */
    val dbnos: Int,
)
