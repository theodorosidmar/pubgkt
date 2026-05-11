package dev.pubgkt.players

import dev.pubgkt.Platform
import dev.pubgkt.PubgApi
import dev.pubgkt.http.deserialize
import dev.pubgkt.http.get
import kotlin.js.JsExport

/**
 * Returns a single player by their account ID.
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @param platform The shard platform to query. Defaults to [dev.pubgkt.Platform.STEAM].
 * @return The [Player] for the given account ID.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_playersAccountId">
 *     PUBG Developer Portal – Get player by account ID</a>
 */
@JsExport
public suspend fun PubgApi.getPlayerByAccountId(accountId: String, platform: Platform = Platform.STEAM): Player = client
    .get("$PLAYERS_PATH/$accountId", platform)
    .deserialize(PlayerSerializer)
