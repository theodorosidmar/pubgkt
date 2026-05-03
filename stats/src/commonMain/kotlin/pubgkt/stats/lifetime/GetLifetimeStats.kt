package pubgkt.stats.lifetime

import io.ktor.client.request.parameter
import pubgkt.GameMode
import pubgkt.Platform
import pubgkt.PubgApi
import pubgkt.http.deserialize
import pubgkt.http.deserializeList
import pubgkt.http.get
import pubgkt.stats.FILTER_PLAYER_IDS
import pubgkt.stats.MAX_PLAYERS_COUNT

/**
 * Returns the lifetime stats for a single player by their account ID for a given [platform].
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @param platform The shard platform to query. Defaults to [Platform.STEAM].
 * @return The [LifetimePlayerStats] for the given account ID.
 * @see <a href=
 * "https://documentation.pubg.com/en/lifetime-stats.html#/Lifetime_Stats/get_players__accountId__seasons_lifetime">
 * PUBG Developer Portal – Get lifetime stats by account ID</a>
 */
public suspend fun PubgApi.getLifetimeStatsByAccountId(
    accountId: String,
    platform: Platform = Platform.STEAM,
): LifetimePlayerStats = client
    .get("players/$accountId/seasons/lifetime", platform)
    .deserialize(LifetimePlayerStatsSerializer)

/**
 * Returns lifetime stats for up to 10 players for the given [platform].
 *
 * PUBG allows a maximum of 10 ids per request; extra ids are ignored.
 *
 * @param gameMode Game mode to query
 * @param accountIds Account IDs to query.
 * @param platform Platform shard used for this request. Defaults to [Platform.STEAM]
 * @see <a href=
 * "https://documentation.pubg.com/en/lifetime-stats.html#/Lifetime_Stats/
 * get_seasons_lifetime_gameMode__gameMode__players">PUBG Developer Portal – Get lifetime stats for up to 10 players</a>
 */
public suspend fun PubgApi.getLifetimeStatsByGameModeAndPlayers(
    gameMode: GameMode,
    accountIds: List<String>,
    platform: Platform = Platform.STEAM,
): List<LifetimeGameModeStats> {
    require(accountIds.isNotEmpty()) { "accountIds must not be empty" }
    return client
        .get("seasons/lifetime/gameMode/${gameMode.path}/players", platform) {
            parameter(
                key = FILTER_PLAYER_IDS,
                value = accountIds.take(MAX_PLAYERS_COUNT).joinToString(separator = ","),
            )
        }.deserializeList(LifetimeGameModeStatsSerializer)
}
