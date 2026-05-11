package dev.pubgkt.matches

import kotlin.js.JsExport

/**
 * The way by which this player died in a match, or alive if they didn't
 */
@JsExport
public enum class DeathType {
    ALIVE,
    BYPLAYER,
    BYZONE,
    SUICIDE,
    LOGOUT,
}
