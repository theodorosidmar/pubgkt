package dev.pubgkt.matches

import kotlin.js.JsExport

/**
 * PUBG Match type
 */
@JsExport
public enum class MatchType {
    AIROYALE,
    ARCADE,
    CUSTOM,
    EVENT,
    OFFICIAL,
    SEASONAL,
    TRAINING,
}
