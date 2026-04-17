package pubgkt

import kotlin.time.Instant

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
     * @see [Platform]
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
     * @see GameMode
     */
    val gameMode: GameMode,

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
)
