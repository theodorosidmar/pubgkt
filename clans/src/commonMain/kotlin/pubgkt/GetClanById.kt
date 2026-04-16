package pubgkt

import io.ktor.client.request.get

/**
 * Returns a single [Clan] by their clan ID.
 *
 * @param clanId The player's unique account ID (e.g. `"account.abc123"`).
 * @return The [Clan] for the given clan ID.
 * @see <a href="https://documentation.pubg.com/en/clans-endpoint.html#/Clans/get_clans__clanId_">PUBG Developer Portal – Get player by account ID</a>
 */
public suspend fun PubgApi.getClanById(clanId: String): Clan =
    client
        .get("$CLANS_PATH/$clanId")
        .deserialize(ClanSerializer)
