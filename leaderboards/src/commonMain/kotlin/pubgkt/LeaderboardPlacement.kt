package pubgkt

public data class LeaderboardPlacement(
    /**
     * The id of the player in the leaderboard.
     */
    val playerId: String,

    /**
     * The name of the player in the leaderboard.
     */
    val playerName: String,

    /**
     * The rank of the player in the leaderboard.
     */
    val rank: Int,

    /**
     * Number of rank points the player was awarded
     */
    val rankPoints: Int,

    /**
     * Number of matches won
     */
    val wins: Int,

    /**
     * Number of games played
     */
    val games: Int,

    /**
     * Average amount of damage dealt per match
     */
    val averageDamage: Int,

    /**
     * Number of enemy players killed
     */
    val kills: Int,

    /**
     * Kill death assist ratio
     */
    val kda: Int,

    /**
     * Average rank
     */
    val averageRank: Double,

    /**
     * Average kill
     */
    val averageKill: Double,

    /**
     * Player's tier
     */
    val tier: String,

    /**
     * Player's subtier
     */
    val subTier: String,
)
