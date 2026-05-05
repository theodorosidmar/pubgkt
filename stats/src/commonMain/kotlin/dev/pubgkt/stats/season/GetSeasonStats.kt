package dev.pubgkt.stats.season

import dev.pubgkt.GameMode
import dev.pubgkt.Platform
import dev.pubgkt.PubgApi
import dev.pubgkt.http.deserialize
import dev.pubgkt.http.deserializeList
import dev.pubgkt.http.get
import dev.pubgkt.stats.FILTER_PLAYER_IDS
import dev.pubgkt.stats.MAX_PLAYERS_COUNT
import io.ktor.client.request.parameter

/**
 * Returns the season stats for a single player by their account ID for a given [platform] and [seasonId].
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @param seasonId The [dev.pubgkt.Season] id (e.g. `"division.bro.official.pc-2018-41"`).
 * @param platform The shard platform to query. Defaults to [Platform.STEAM].
 * @return The [dev.pubgkt.stats.season.SeasonPlayerStats] for the given account ID.
 * @see <a href="
 * https://documentation.pubg.com/en/seasons-endpoint.html#/Season_Stats/get_players__accountId__seasons__seasonId_">
 * PUBG Developer Portal – Get season information for a single player.</a>
 */
public suspend fun PubgApi.getSeasonStatsByAccountId(
    accountId: String,
    seasonId: String,
    platform: Platform = Platform.STEAM,
): SeasonPlayerStats = client
    .get("players/$accountId/seasons/$seasonId", platform)
    .deserialize(SeasonPlayerStatsSerializer)

/**
 * Returns season stats for up to 10 players for the given [platform].
 *
 * PUBG allows a maximum of 10 ids per request; extra ids are ignored.
 *
 * @param seasonId The [dev.pubgkt.Season] id (e.g. `"division.bro.official.pc-2018-41"`).
 * @param gameMode - Game mode to query
 * @param accountIds Account IDs to query.
 * @param platform - Platform shard used for this request. Defaults to [Platform.STEAM]
 * @see <a href="https://documentation.pubg.com/en/seasons-endpoint.html#/Season_Stats/
 * get_seasons__seasonId__gameMode__gameMode__players">
 * PUBG Developer Portal – Get season information for up to 10 players.</a>
 */
public suspend fun PubgApi.getSeasonStatsByGameModeAndPlayers(
    seasonId: String,
    gameMode: GameMode,
    accountIds: List<String>,
    platform: Platform = Platform.STEAM,
): List<SeasonGameModeStats> {
    require(accountIds.isNotEmpty()) { "accountIds must not be empty" }
    return client
        .get("seasons/$seasonId/gameMode/${gameMode.path}/players", platform) {
            parameter(
                key = FILTER_PLAYER_IDS,
                value = accountIds.take(
                    MAX_PLAYERS_COUNT,
                ).joinToString(separator = ","),
            )
        }.deserializeList(SeasonGameModeStatsSerializer)
}
