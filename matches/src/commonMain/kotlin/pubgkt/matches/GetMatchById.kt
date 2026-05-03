package pubgkt.matches

import pubgkt.Platform
import pubgkt.PubgApi
import pubgkt.http.PublicRequestPolicy
import pubgkt.http.deserialize
import pubgkt.http.get

/**
 * Returns a single [Match] by their match ID.
 *
 * @param matchId The match ID (e.g. `"cdc2a4bf-0d03-483c-8ab6-49a38202000e"`).
 * @param platform The shard platform to query. Defaults to [pubgkt.Platform.STEAM].
 * @return The [Match] for the given match ID.
 * @see <a href="
 * https://documentation.pubg.com/en/matches-endpoint.html#/Matches/get_matches__id_">
 * PUBG Developer Portal – Get a single match</a>
 */
public suspend fun PubgApi.getMatchById(matchId: String, platform: Platform = Platform.STEAM): Match = client
    .get("$MATCHES_PATH/$matchId", platform, policy = PublicRequestPolicy)
    .deserialize(MatchSerializer)
