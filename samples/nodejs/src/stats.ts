// Stats module sample: season, lifetime, and ranked stats
// Usage: PUBG_API_KEY=your-key npm run stats

import { PubgApi, Platform, KtList, getSeasonsByPlatform } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getSeasonStatsByAccountId, getLifetimeStatsByAccountId, getRankedStatsByAccountIdAndSeasonId } from "@pubgkt/stats";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run stats");
    process.exit(1);
}

const api = new PubgApi(apiKey);

const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg"]), Platform.STEAM);
const player = players.asJsReadonlyArrayView()[0];
console.log(`Player: ${player.name} (${player.id})`);

const seasonsList = await getSeasonsByPlatform(api, Platform.STEAM);
const current = seasonsList.asJsReadonlyArrayView().find((s) => s.isCurrentSeason);
console.log(`Current season: ${current?.id}`);

if (current) {
    console.log("\n=== getSeasonStatsByAccountId ===");
    const seasonStats = await getSeasonStatsByAccountId(api, player.id, current.id, Platform.STEAM);
    const squad = seasonStats.squadFpp;
    console.log(`Squad FPP: ${squad.roundsPlayed} games, ${squad.wins} wins, ${squad.kills} kills`);

    console.log("\n=== getLifetimeStatsByAccountId ===");
    const lifetime = await getLifetimeStatsByAccountId(api, player.id, Platform.STEAM);
    const ltSquad = lifetime.squadFpp;
    console.log(`Lifetime Squad FPP: ${ltSquad.roundsPlayed} games, ${ltSquad.wins} wins, ${ltSquad.kills} kills`);

    console.log("\n=== getRankedStatsByAccountIdAndSeasonId ===");
    try {
        const ranked = await getRankedStatsByAccountIdAndSeasonId(api, player.id, current.id, Platform.STEAM);
        console.log(`Ranked: tier=${ranked.currentTier.tier}-${ranked.currentTier.subTier}, KDA=${ranked.kda}`);
    } catch (e) {
        console.log(`Ranked stats not available: ${(e as Error).message}`);
    }
}
