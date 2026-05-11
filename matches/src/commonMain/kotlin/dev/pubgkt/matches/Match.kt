package dev.pubgkt.matches

import dev.pubgkt.Platform
import kotlin.js.JsExport
import kotlin.time.Instant

@Suppress("NON_EXPORTABLE_TYPE")
@JsExport
public data class Match(
    /**
     * Match id (e.g: a131e486-5bcf-4c2e-aa5a-515489ee57aa)
     */
    val id: String,
    /**
     * Title id (e.g: bluehole-pubg)
     */
    val titleId: String,
    /**
     * The shard/platform
     * @see [dev.pubgkt.Platform]
     */
    val platform: Platform,
    // val tags: {}
    /**
     * If the match is custom or not
     */
    val isCustomMatch: Boolean,
    /**
     * Match type
     * @see MatchType
     */
    val matchType: MatchType,
    // val stats: {}
    /**
     * Game Mode
     * @see MatchGameMode
     */
    val gameMode: MatchGameMode,
    /**
     * Map of the match
     * @see [Map]
     */
    val mapName: Map,
    /**
     * The state of the season
     * @see SeasonState
     */
    val seasonState: SeasonState,
    /**
     * Time this match object was stored in the API
     */
    val createdAt: Instant,
    /**
     * Length of the match measured in seconds
     */
    val duration: Int,
    /**
     * List of participants of this match
     * @see MatchParticipant
     */
    val participants: List<MatchParticipant>,
)
