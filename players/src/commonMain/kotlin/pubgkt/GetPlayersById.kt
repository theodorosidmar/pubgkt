package pubgkt

import io.ktor.client.request.parameter

/**
 * Returns players filtered by account IDs for the given [platform].
 *
 * Delegates to [getPlayersById] with a list.
 * PUBG allows a maximum of 10 ids per request; extra ids are ignored.
 *
 * @param platform Platform shard used for this request.
 * @param accountIds One or more account IDs.
 * @return A list of matching [Player], or an empty list when no ids are provided.
 */
public suspend fun PubgApi.getPlayersById(
    vararg accountIds: String,
    platform: Platform = Platform.STEAM,
): List<Player> =
    getPlayersById(accountIds.toList(), platform)

/**
 * Returns players filtered by account IDs for the given [platform].
 *
 * PUBG allows a maximum of 10 ids per request; extra ids are ignored.
 *
 * @param accountIds Account IDs to query.
 * @param platform Platform shard used for this request. Defaults to [Platform.STEAM].
 * @return A list of matching [Player], or an empty list when [accountIds] is empty.
 */
public suspend fun PubgApi.getPlayersById(
    accountIds: List<String>,
    platform: Platform = Platform.STEAM,
): List<Player> =
    if (accountIds.isEmpty()) {
        emptyList()
    } else {
        client
            .get(PLAYERS_PATH, platform) {
                parameter(
                    key = FILTER_PLAYER_IDS,
                    value = accountIds.take(MAX_PLAYERS_COUNT).joinToString(","),
                )
            }
            .deserializeList(PlayerSerializer)
    }
