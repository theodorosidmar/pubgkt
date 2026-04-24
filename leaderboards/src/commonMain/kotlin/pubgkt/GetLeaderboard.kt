package pubgkt


/**
 * Returns the [Leaderboard] by their season id, game mode and platform region.
 *
 * @param seasonId The id of the season
 * @param gameMode The game mode
 * @param platformRegion The platform region
 * @return The [Leaderboard] for the given parameters.
 * @see <a href="https://documentation.pubg.com/en/leaderboards-endpoint.html#/Leaderboards/get_leaderboards__seasonId___gameMode_">PUBG Developer Portal – Get the leaderboard for a game mode</a>
 */
public suspend fun PubgApi.getLeaderboard(
    seasonId: String,
    gameMode: GameMode,
    platformRegion: PlatformRegion = PlatformRegion.PC_SA,
): Leaderboard =
    client
        .get("$LEADERBOARDS_PATH/$seasonId/${gameMode.path}", platformRegion)
        .deserialize(LeaderboardSerializer)

