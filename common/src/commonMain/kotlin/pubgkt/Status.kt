package pubgkt

import io.ktor.http.HttpStatusCode

/**
 * Returns the status of the PUBG API.
 *
 * @return `true` if the PUBG API is up and running and healthy. `false` otherwise.
 * @see <a href="https://documentation.pubg.com/en/status-endpoint.html#/Status/get_status">PUBG Developer Portal – Check the status of the API</a>
 */
public suspend fun PubgApi.isUp(): Boolean =
    client
        .getWithPublicRequest("https://$HOST/status")
        .status == HttpStatusCode.OK
