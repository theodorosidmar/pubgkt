package dev.pubgkt.stats.ranked

import dev.pubgkt.Platform
import dev.pubgkt.PubgApi
import dev.pubgkt.http.deserialize
import dev.pubgkt.http.get

/**
 * Returns the ranked stats for a single player by their account ID for a given [platform] and [seasonId].
 *
 * @param accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @param seasonId The [dev.pubgkt.Season] id (e.g. `"division.bro.official.pc-2018-41"`).
 * @param platform The shard platform to query. Defaults to [Platform.STEAM].
 * @return The [dev.pubgkt.stats.ranked.RankedStats] for the given account ID.
 * @see <a href=" https://documentation.pubg.com/en/seasons-endpoint.html#/
 * Ranked_Stats/get_players__accountId__seasons__seasonId__ranked">
 * PUBG Developer Portal – Get ranked stats for a single player.</a>
 */
public suspend fun PubgApi.getRankedStatsByAccountIdAndSeasonId(
    accountId: String,
    seasonId: String,
    platform: Platform = Platform.STEAM,
): RankedStats = client
    .get("players/$accountId/seasons/$seasonId/ranked", platform)
    .deserialize(RankedStatsSerializer)
