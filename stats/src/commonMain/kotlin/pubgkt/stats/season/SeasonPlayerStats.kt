package pubgkt.stats.season

import pubgkt.stats.GameModeStats

public data class SeasonPlayerStats(
    /**
     * Account ID of the player associated with this stats
     */
    val playerId: String,
    /**
     * [pubgkt.Season] id of the stats
     */
    val seasonId: String,
    /**
     * Best rank point
     */
    val bestRankPoint: Double,
    /**
     * Solo TPP stats
     */
    val soloTpp: GameModeStats,
    /**
     * Duo TPP stats
     */
    val duoTpp: GameModeStats,
    /**
     * Squad TPP stats
     */
    val squadTpp: GameModeStats,
    /**
     * Solo FPP stats
     */
    val soloFpp: GameModeStats,
    /**
     * Duo FPP stats
     */
    val duoFpp: GameModeStats,
    /**
     * Squad FPP stats
     */
    val squadFpp: GameModeStats,
)
