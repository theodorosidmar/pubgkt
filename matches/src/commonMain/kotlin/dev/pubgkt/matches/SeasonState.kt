package dev.pubgkt.matches

import kotlin.js.JsExport

/**
 * State of the season
 */
@JsExport
public enum class SeasonState {
    CLOSED,
    PREPARE,
    PROGRESS,
}
