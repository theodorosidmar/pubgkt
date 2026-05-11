package dev.pubgkt.stats.lifetime

import dev.pubgkt.stats.GameModeStats
import kotlin.js.JsExport

@JsExport
public data class LifetimePlayerStats(
    /**
     * Account ID of the player associated with this stats
     */
    val playerId: String,
    /**
     * Best rank point
     */
    val bestRankPoint: Double,
    /**
     * Solo TPP lifetime stats
     */
    val soloTpp: GameModeStats,
    /**
     * Duo TPP lifetime stats
     */
    val duoTpp: GameModeStats,
    /**
     * Squad TPP lifetime stats
     */
    val squadTpp: GameModeStats,
    /**
     * Solo FPP lifetime stats
     */
    val soloFpp: GameModeStats,
    /**
     * Duo FPP lifetime stats
     */
    val duoFpp: GameModeStats,
    /**
     * Squad FPP lifetime stats
     */
    val squadFpp: GameModeStats,
)
