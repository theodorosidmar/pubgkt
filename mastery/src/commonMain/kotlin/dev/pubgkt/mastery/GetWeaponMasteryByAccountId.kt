package dev.pubgkt.mastery

import dev.pubgkt.Platform
import dev.pubgkt.PubgApi
import dev.pubgkt.http.deserialize
import dev.pubgkt.http.get
import kotlin.js.JsExport

/**
 * Returns weapon mastery information for a single player
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @param platform The shard platform to query. Defaults to [dev.pubgkt.Platform.STEAM].
 * @return The [WeaponMastery] for the given account ID.
 * @see <a href="
 * https://documentation.pubg.com/en/mastery-endpoint.html#/Weapon_Mastery/get_players__accountId__weapon_mastery">
 * PUBG Developer Portal – Get weapon mastery by account ID</a>
 */
@JsExport
public suspend fun PubgApi.getWeaponMasteryByAccountId(
    accountId: String,
    platform: Platform = Platform.STEAM,
): WeaponMastery = client
    .get("players/$accountId/weapon_mastery", platform)
    .deserialize(WeaponMasterySerializer)
