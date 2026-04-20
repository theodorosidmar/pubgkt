package pubgkt

/**
 * Returns weapon mastery information for a single player
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @return The [WeaponMastery] for the given account ID.
 * @see <a href="https://documentation.pubg.com/en/mastery-endpoint.html#/Weapon_Mastery/get_players__accountId__weapon_mastery">PUBG Developer Portal – Get weapon mastery by account ID</a>
 */
public suspend fun PubgApi.getWeaponMasteryByAccountId(accountId: String): WeaponMastery =
    client
        .get("players/${accountId}/weapon_mastery")
        .deserialize(WeaponMasterySerializer)
