package pubgkt

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

private const val PLAYERS_PATH = "players"

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players)
 */
internal suspend fun PubgClient.getPlayerByName(
    name: String,
): HttpResponse =
    get(PLAYERS_PATH) {
        url {
            parameters.append(
                FILTER_PLAYER_NAMES,
                name,
            )
        }
    }

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players)
 */
internal suspend fun PubgClient.getPlayersByName(
    names: List<String>,
): HttpResponse =
    get(PLAYERS_PATH) {
        url {
            parameters.append(
                FILTER_PLAYER_NAMES,
                names.joinToString(separator = ","),
            )
        }
    }

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players)
 */
internal suspend fun PubgClient.getPlayersByIds(
    ids: List<String>,
): HttpResponse =
    get(PLAYERS_PATH) {
        url {
            parameters.append(
                FILTER_PLAYER_IDS,
                ids.joinToString(separator = ","),
            )
        }
    }
