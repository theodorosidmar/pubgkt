package pubgkt

import io.ktor.client.request.get
import io.ktor.client.request.parameter

/**
 * Returns players filtered by in-game names.
 *
 * PUBG allows a maximum of 10 names per request; extra names are ignored.
 */
public suspend fun PubgApi.getPlayersByNames(vararg playerNames: String): List<Player> =
    getPlayersByNames(playerNames.toList())

/**
 * Returns players filtered by in-game names.
 *
 * PUBG allows a maximum of 10 names per request; extra names are ignored.
 */
public suspend fun PubgApi.getPlayersByNames(playerNames: List<String>): List<Player> {
    if (playerNames.isEmpty()) return emptyList()
    return client
        .get(PLAYERS_PATH) {
            parameter(
                key = FILTER_PLAYER_NAMES,
                value = playerNames.take(MAX_PLAYERS_COUNT).joinToString(","),
            )
        }
        .deserializeList(PlayerSerializer)
}
