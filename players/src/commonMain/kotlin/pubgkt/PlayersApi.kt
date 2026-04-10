package pubgkt

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

private const val FILTER_PLAYER_IDS = "filter[playerIds]"
private const val FILTER_PLAYER_NAMES = "filter[playerNames]"

/**
 * Returns up to 10 players matching the given account IDs.
 *
 * Delegates to [getPlayersById(List)].
 *
 * @param ids One or more account IDs (e.g. `"account.abc123"`). At most 10 are sent;
 *   any beyond the first 10 are silently dropped.
 * @return List of [Player] objects for the matched accounts.
 * @throws IllegalArgumentException if no IDs are provided.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal – Get players</a>
 */
public suspend fun PubgApi.getPlayersById(vararg ids: String): List<Player> =
    getPlayersById(ids.toList())

/**
 * Returns up to 10 players matching the given account IDs.
 *
 * The PUBG API accepts a maximum of 10 IDs per request; any IDs beyond the first 10
 * are silently dropped.
 *
 * @param ids List of account IDs (e.g. `"account.abc123"`).
 * @return List of [Player] objects for the matched accounts.
 * @throws IllegalArgumentException if [ids] is empty.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal – Get players</a>
 */
public suspend fun PubgApi.getPlayersById(ids: List<String>): List<Player> =
    fetchPlayers(FILTER_PLAYER_IDS, ids)

/**
 * Returns up to 10 players matching the given in-game display names.
 *
 * Delegates to [getPlayersByNames(List)].
 *
 * @param names One or more player names. At most 10 are sent; any beyond the first
 *   10 are silently dropped.
 * @return List of [Player] objects for the matched names.
 * @throws IllegalArgumentException if no names are provided.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal – Get players</a>
 */
public suspend fun PubgApi.getPlayersByNames(vararg names: String): List<Player> =
    getPlayersByNames(names.toList())

/**
 * Returns up to 10 players matching the given in-game display names.
 *
 * The PUBG API accepts a maximum of 10 names per request; any names beyond the first
 * 10 are silently dropped. Name matching is case-sensitive.
 *
 * @param names List of player display names.
 * @return List of [Player] objects for the matched names.
 * @throws IllegalArgumentException if [names] is empty.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal – Get players</a>
 */
public suspend fun PubgApi.getPlayersByNames(names: List<String>): List<Player> =
    fetchPlayers(FILTER_PLAYER_NAMES, names)

/**
 * Returns a single player by their account ID.
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @return The [Player] for the given account ID.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_playersAccountId">PUBG Developer Portal – Get player by account ID</a>
 */
public suspend fun PubgApi.getPlayerByAccountId(accountId: String): Player {
    val text = client.get("players/$accountId").bodyAsText()
    return playersJson.decodeFromString<PlayerResponse>(text).data.toPlayer()
}

private suspend fun PubgApi.fetchPlayers(param: String, values: List<String>): List<Player> {
    require(values.isNotEmpty()) { "At least one value must be provided" }
    val text = client.get("players") {
        parameter(param, values.take(10).joinToString(","))
    }.bodyAsText()
    return playersJson.decodeFromString<PlayersResponse>(text).data.map { it.toPlayer() }
}
