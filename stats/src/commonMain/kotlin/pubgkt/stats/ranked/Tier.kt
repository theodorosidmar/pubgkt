package pubgkt.stats.ranked

import kotlinx.serialization.json.JsonObject
import pubgkt.requiredInt
import pubgkt.requiredString

public data class Tier(
    val tier: String,
    val subTier: Int,
) {
    internal companion object {
        fun fromJsonObject(jsonObject: JsonObject): Tier =
            Tier(
                tier = jsonObject.requiredString("tier"),
                subTier = jsonObject.requiredInt("subTier"),
            )
    }
}
