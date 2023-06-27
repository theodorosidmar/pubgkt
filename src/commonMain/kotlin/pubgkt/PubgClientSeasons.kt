package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

private const val SEASONS_PATH = "seasons"

/**
 * [PUBG Documentation](https://documentation.pubg.com/en/seasons-endpoint.html#/Seasons/get_seasons)
 */
internal suspend fun HttpClient.getSeasons(): HttpResponse = get(SEASONS_PATH)
