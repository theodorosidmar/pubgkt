package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

private const val LIFETIME_STATS_PATH = "seasons/lifetime/gameMode"

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/lifetime-stats.html#/Lifetime_Stats/get_players__accountId__seasons_lifetime)
 */
internal suspend fun HttpClient.getLifetimeStats(
    playerId: String,
): HttpResponse = get("players/$playerId/seasons/lifetime")

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/lifetime-stats.html#/Lifetime_Stats/get_seasons_lifetime_gameMode__gameMode__players)
 */
internal suspend fun HttpClient.getLifetimeStats(
    playerId: String,
    gameMode: String,
): HttpResponse =
    get("$LIFETIME_STATS_PATH/$gameMode/players") {
        url {
            parameters.append(FILTER_PLAYER_IDS, playerId)
        }
    }

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/lifetime-stats.html#/Lifetime_Stats/get_seasons_lifetime_gameMode__gameMode__players)
 */
internal suspend fun HttpClient.getLifetimeStats(
    playerIds: List<String>,
    gameMode: String,
): HttpResponse =
    get("$LIFETIME_STATS_PATH/$gameMode/players") {
        url {
            parameters.append(FILTER_PLAYER_IDS, playerIds.joinToString(separator = ","))
        }
    }
