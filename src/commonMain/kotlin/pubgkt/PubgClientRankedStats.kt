package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/seasons-endpoint.html#/Ranked_Stats/get_players__accountId__seasons__seasonId__ranked)
 */
internal suspend fun HttpClient.getRankedStats(
    playerId: String,
    seasonId: String,
): HttpResponse = get("players/$playerId/seasons/$seasonId/ranked")
