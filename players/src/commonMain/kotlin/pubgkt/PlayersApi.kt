package pubgkt

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

private const val FILTER_PLAYER_IDS = "filter[playerIds]"
private const val FILTER_PLAYER_NAMES = "filter[playerNames]"

/**
 * Get players by id
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal - Get players</a>
 */
public suspend fun PubgApi.getPlayersById(vararg ids: String) {
    TODO()
}

/**
 * Get players by id
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal - Get players</a>
 */
public suspend fun PubgApi.getPlayersById(ids: List<String>) {
    TODO()
}

/**
 * Get players by names
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal - Get players</a>
 * @param names Up to 10 names. If more than 10 names are provided, the first 10 names will be picked up.
 */
public suspend fun PubgApi.getPlayersByNames(vararg names: String) {
    TODO()
}

/**
 * Get players by names
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players">PUBG Developer Portal - Get players</a>
 * @param names Up to 10 names. If more than 10 names are provided, the first 10 names will be picked up.
 */
public suspend fun PubgApi.getPlayersByNames(names: List<String>) {
    TODO()
}

/**
 * Get a single player by account id.
 * @param accountId The account ID to search for.
 */
public suspend fun PubgApi.getPlayerByAccountId(accountId: String): String {
    return client.get("/players/$accountId").bodyAsText()
}
