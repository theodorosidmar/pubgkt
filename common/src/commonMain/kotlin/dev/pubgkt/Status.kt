package dev.pubgkt

import dev.pubgkt.http.PublicRequestPolicy
import dev.pubgkt.http.get
import io.ktor.http.HttpStatusCode
import kotlin.js.JsExport

/**
 * Returns the status of the PUBG API.
 *
 * @return `true` if the PUBG API is up and running and healthy. `false` otherwise.
 * @see <a href="
 * https://documentation.pubg.com/en/status-endpoint.html#/Status/get_status">
 * PUBG Developer Portal – Check the status of the API</a>
 */
@JsExport
public suspend fun PubgApi.isUp(): Boolean = client
    .get("https://$HOST/status", policy = PublicRequestPolicy)
    .status == HttpStatusCode.OK
