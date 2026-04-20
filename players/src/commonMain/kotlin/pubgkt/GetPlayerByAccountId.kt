package pubgkt

/**
 * Returns a single player by their account ID.
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @return The [Player] for the given account ID.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html#/Players/get_playersAccountId">PUBG Developer Portal – Get player by account ID</a>
 */
public suspend fun PubgApi.getPlayerByAccountId(accountId: String): Player =
    client
        .get("$PLAYERS_PATH/$accountId")
        .deserialize(PlayerSerializer)
