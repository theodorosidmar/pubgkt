package pubgkt

public data class WeaponStatsTotal(
    /**
     * Most defeats in a single match
     */
    val mostDefeatsInAGame: Int,

    /**
     * The total number of defeats in their career
     */
    val defeats: Int,

    /**
     * The most damage that the player did in a single match
     */
    val mostDamagePlayerInAGame: Double,

    /**
     * The total damage that the player has done in their career
     */
    val damagePlayer: Double,

    /**
     * The most headshots that the player did in a single match
     */
    val mostHeadShotsInAGame: Int,

    /**
     * The total headshots that the player has done in their career
     */
    val headShots: Int,

    /**
     * The longest distance that the player got a defeat for
     */
    val longestDefeat: Double,

    /**
     * The number of long range defeats for the player
     */
    val longRangeDefeats: Int,

    /**
     * The total number of kills for the player
     */
    val kills: Int,

    /**
     * The most kills for a player in a single match
     */
    val mostKillsInAGame: Int,

    /**
     * The total number of times that the player has caused another player to become groggy during their career
     */
    val groggies: Int,

    /**
     * The highest number of times that the player has caused another player to become groggy during a match
     */
    val mostGroggiesInAGame: Int,
)
