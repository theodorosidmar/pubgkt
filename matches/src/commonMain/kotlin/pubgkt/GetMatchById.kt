package pubgkt

/**
 * Returns a single [Match] by their match ID.
 *
 * @param matchId The match ID (e.g. `"cdc2a4bf-0d03-483c-8ab6-49a38202000e"`).
 * @return The [Match] for the given match ID.
 * @see <a href="https://documentation.pubg.com/en/matches-endpoint.html#/Matches/get_matches__id_">PUBG Developer Portal – Get a single match</a>
 */
public suspend fun PubgApi.getMatchById(matchId: String): Match =
    client
        .get("$MATCHES_PATH/$matchId", policy = PublicRequestPolicy)
        .deserialize(MatchSerializer)
