package pubgkt.stats.lifetime

import pubgkt.GameMode
import pubgkt.stats.GameModeStats

public data class LifetimeGameModeStats(
    /**
     * Account ID of the player associated with this stats
     */
    val playerId: String,

    /**
     * Best rank point
     */
    val bestRankPoint: Double,

    /**
     * Game mode for this stats
     * @see GameMode
     */
    val gameMode: GameMode,

    /**
     * Statistics for this game mode and player
     */
    val stats: GameModeStats,
)
