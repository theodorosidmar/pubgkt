package dev.pubgkt.stats.ranked

import dev.pubgkt.jsonapi.requiredInt
import dev.pubgkt.jsonapi.requiredString
import kotlinx.serialization.json.JsonObject

public data class Tier(val tier: String, val subTier: Int) {
    internal companion object {
        fun fromJsonObject(jsonObject: JsonObject): Tier = Tier(
            tier = jsonObject.requiredString("tier"),
            subTier = jsonObject.requiredInt("subTier"),
        )
    }
}
