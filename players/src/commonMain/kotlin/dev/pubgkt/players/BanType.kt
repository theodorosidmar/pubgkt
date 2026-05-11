package dev.pubgkt.players

import kotlin.js.JsExport

/**
 * PUBG Ban Type
 */
@JsExport
public enum class BanType {
    Innocent,
    TemporaryBan,
    PermanentBan,
}
