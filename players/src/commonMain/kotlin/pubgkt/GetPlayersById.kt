package pubgkt

import io.ktor.client.request.get
import io.ktor.client.request.parameter

/**
 * Returns players filtered by account IDs.
 *
 * PUBG allows a maximum of 10 ids per request; extra ids are ignored.
 */
public suspend fun PubgApi.getPlayersById(vararg accountIds: String): List<Player> =
    getPlayersById(accountIds.toList())

/**
 * Returns players filtered by account IDs.
 *
 * PUBG allows a maximum of 10 ids per request; extra ids are ignored.
 */
public suspend fun PubgApi.getPlayersById(accountIds: List<String>): List<Player> {
    if (accountIds.isEmpty()) return emptyList()
    return client
        .get(PLAYERS_PATH) {
            parameter(
                key = FILTER_PLAYER_IDS,
                value = accountIds.take(MAX_PLAYERS_COUNT).joinToString(","),
            )
        }
        .deserializeList(PlayerSerializer)
}
