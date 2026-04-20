package pubgkt

public data class WeaponOfficialStatsTotal(
    /**
     * Most defeats in a single match
     */
    val mostDefeatsInAGame: Int,

    /**
     * The total number of defeats in their career
     */
    val defeats: Int,

    /**
     * The total damage that the player has done in their career
     */
    val damagePlayer: Double,

    /**
     * The total headshots that the player has done in their career
     */
    val headShots: Int,

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
     * The longest distance that the player got a kill for
     */
    val longestKill: Double,
)
