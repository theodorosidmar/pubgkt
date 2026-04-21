package pubgkt

import io.ktor.client.request.parameter

/**
 * Returns players filtered by in-game names for the given [platform].
 *
 * Delegates to [getPlayersByNames] with a list.
 * PUBG allows a maximum of 10 names per request; extra names are ignored.
 *
 * @param platform Platform shard used for this request.
 * @param playerNames One or more in-game names.
 * @return A list of matching [Player], or an empty list when no names are provided.
 */
public suspend fun PubgApi.getPlayersByNames(
    vararg playerNames: String,
    platform: Platform = Platform.STEAM,
): List<Player> =
    getPlayersByNames(playerNames.toList(), platform)

/**
 * Returns players filtered by in-game names for the given [platform].
 *
 * PUBG allows a maximum of 10 names per request; extra names are ignored.
 *
 * @param playerNames In-game names to query.
 * @param platform Platform shard used for this request. Defaults to [Platform.STEAM].
 * @return A list of matching [Player], or an empty list when [playerNames] is empty.
 */
public suspend fun PubgApi.getPlayersByNames(
    playerNames: List<String>,
    platform: Platform = Platform.STEAM,
): List<Player> =
    if (playerNames.isEmpty()) {
        emptyList()
    } else {
        client
            .get(PLAYERS_PATH, platform) {
                parameter(
                    key = FILTER_PLAYER_NAMES,
                    value = playerNames.take(MAX_PLAYERS_COUNT).joinToString(","),
                )
            }
            .deserializeList(PlayerSerializer)
    }
