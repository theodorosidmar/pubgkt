package pubgkt

/**
 * Returns survival mastery information for a single player
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @param platform The shard platform to query. Defaults to [Platform.STEAM].
 * @return The [SurvivalMastery] for the given account ID.
 * @see <a href="https://documentation.pubg.com/en/mastery-endpoint.html#/Survival_Mastery/get_players__accountId__survival_mastery">PUBG Developer Portal – Get survival mastery by account ID</a>
 */
public suspend fun PubgApi.getSurvivalMasteryByAccountId(
    accountId: String,
    platform: Platform = Platform.STEAM,
): SurvivalMastery =
    client
        .get("players/${accountId}/survival_mastery", platform)
        .deserialize(SurvivalMasterySerializer)
