package pubgkt

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import pubgkt.http.deserializeList
import pubgkt.http.get
import pubgkt.jsonapi.JsonApiResourceDeserializer
import pubgkt.jsonapi.requireId
import pubgkt.jsonapi.requiredBoolean

public data class Season(val id: String, val isCurrentSeason: Boolean, val isOffSeason: Boolean)

/**
 * Returns the current season among a list of available [Season] or null.
 */
public fun List<Season>.currentOrNull(): Season? = firstOrNull { it.isCurrentSeason }

internal object SeasonSerializer : JsonApiResourceDeserializer<Season>("pubgkt.Season") {
    override fun deserializeResource(
        attributes: JsonObject,
        id: String?,
        relationships: JsonObject?,
        included: JsonArray?,
    ): Season {
        requireId(id)
        return Season(
            id = id,
            isCurrentSeason = attributes.requiredBoolean("isCurrentSeason"),
            isOffSeason = attributes.requiredBoolean("isOffseason"),
        )
    }
}

/**
 * Returns the list of available [Season] by platform.
 *
 * Note: The list of seasons will only be changing about once every two months when a new season is added.
 * Applications should not be querying for the list of seasons more than once per month.
 *
 * @param platform The platform
 * @return A list of available [Season].
 * @see <a href="
 * https://documentation.pubg.com/en/seasons-endpoint.html#/Seasons/get_seasons">
 * PUBG Developer Portal – Get seasons</a>
 */
public suspend fun PubgApi.seasons(platform: Platform = Platform.STEAM): List<Season> = client
    .get("seasons", platform)
    .deserializeList(SeasonSerializer)

/**
 * Returns the list of available [Season] by platform-region.
 *
 * Note: The list of seasons will only be changing about once every two months when a new season is added.
 * Applications should not be querying for the list of seasons more than once per month.
 *
 * @param platformRegion The platform region
 * @return A list of available [Season].
 * @see <a href="
 * https://documentation.pubg.com/en/seasons-endpoint.html#/Seasons/get_seasons">
 * PUBG Developer Portal – Get seasons</a>
 */
public suspend fun PubgApi.seasons(platformRegion: PlatformRegion = PlatformRegion.PC_SA): List<Season> = client
    .get("seasons", platformRegion)
    .deserializeList(SeasonSerializer)
