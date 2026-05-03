package pubgkt.stats.ranked

import kotlinx.serialization.json.JsonObject
import pubgkt.jsonapi.requiredInt
import pubgkt.jsonapi.requiredString

public data class Tier(val tier: String, val subTier: Int) {
    internal companion object {
        fun fromJsonObject(jsonObject: JsonObject): Tier = Tier(
            tier = jsonObject.requiredString("tier"),
            subTier = jsonObject.requiredInt("subTier"),
        )
    }
}
