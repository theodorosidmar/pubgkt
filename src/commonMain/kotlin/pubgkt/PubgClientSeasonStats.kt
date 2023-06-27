package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/seasons-endpoint.html#/Season_Stats/get_players__accountId__seasons__seasonId_)
 */
internal suspend fun HttpClient.getSeasonStats(
    player: String,
    seasonId: String,
): HttpResponse = get("/players/$player/seasons/$seasonId")

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/seasons-endpoint.html#/Season_Stats/get_seasons__seasonId__gameMode__gameMode__players)
 */
internal suspend fun HttpClient.getSeasonStatsByPlayerIds(
    seasonId: String,
    gameMode: String,
    playerIds: List<String>,
): HttpResponse =
    get("/seasons/$seasonId/gameMode/$gameMode/players") {
        url {
            parameters.append(FILTER_PLAYER_IDS, playerIds.joinToString(separator = ","))
        }
    }

