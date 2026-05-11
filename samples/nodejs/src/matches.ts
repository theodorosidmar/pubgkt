// Matches module sample: look up a match by ID
// Usage: PUBG_API_KEY=your-key npm run matches

import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getMatchById } from "@pubgkt/matches";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run matches");
    process.exit(1);
}

const api = new PubgApi(apiKey);

const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg"]), Platform.STEAM);
const player = players.asJsReadonlyArrayView()[0];
const matchIds = player.matches.asJsReadonlyArrayView();
console.log(`Player: ${player.name}, recent matches: ${matchIds.length}`);

if (matchIds.length > 0) {
    console.log("\n=== getMatchById ===");
    const match = await getMatchById(api, matchIds[0].id, Platform.STEAM);
    const participants = match.participants.asJsReadonlyArrayView();
    console.log(`Match: ${match.id}`);
    console.log(`  mode=${match.gameMode.name} map=${match.mapName.name} duration=${match.duration}s`);
    console.log(`  participants=${participants.length}`);
}
