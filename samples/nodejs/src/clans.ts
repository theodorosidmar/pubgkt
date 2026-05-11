// Clans module sample: look up a player's clan
// Usage: PUBG_API_KEY=your-key npm run clans

import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getClanById } from "@pubgkt/clans";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run clans");
    process.exit(1);
}

const api = new PubgApi(apiKey);

console.log("=== getPlayersByNames ===");
const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg"]), Platform.STEAM);
const player = players.asJsReadonlyArrayView()[0];
console.log(`${player.name} clanId=${player.clanId}`);

if (player.clanId) {
    console.log("\n=== getClanById ===");
    const clan = await getClanById(api, player.clanId, Platform.STEAM);
    console.log(`Clan: ${clan.tag} (${clan.name}) level=${clan.level} members=${clan.memberCount}`);
}
