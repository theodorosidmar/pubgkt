package pubgkt

/**
 * [MatchParticipant] track the scores of each opposing group of participants.
 * [players] can have one or many participants depending on the game mode.
 * [MatchParticipant] objects are only meaningful within the context of a match and are not exposed as a standalone resource
 */
public data class MatchParticipant(
    /**
     * An arbitrary ID assigned to this participant within the match
     */
    val teamId: Int,

    /**
     * This participant's placement in the match
     * minimum: 1
     * maximum: 130
     */
    val rank: Int,

    /**
     * Indicates if this participant won the match
     */
    val won: Boolean,

    /**
     * An array of references to participant players
     */
    val players: List<MatchPlayer>,
)
