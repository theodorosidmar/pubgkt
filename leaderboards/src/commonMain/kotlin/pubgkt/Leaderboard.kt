package pubgkt

/**
 * Leaderboard objects show the current rank of the top 500 players for a game mode.
 */
public data class Leaderboard(
    /**
     * The season id of the leaderboard.
     */
    val seasonId: String,

    /**
     * The game mode of the leaderboard.
     */
    val gameMode: GameMode,

    /**
     * The platform region of the leaderboard.
     */
    val platformRegion: PlatformRegion,

    /**
     * The list of players in the leaderboard.
     */
    val placements: List<LeaderboardPlacement>,
)

