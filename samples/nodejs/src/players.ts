// Players module sample: search and lookup players
// Usage: PUBG_API_KEY=your-key npm run players

import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames, getPlayerByAccountId, getPlayersById } from "@pubgkt/players";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run players");
    process.exit(1);
}

const api = new PubgApi(apiKey);

console.log("=== getPlayersByNames ===");
const byNames = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg", "TGLTN"]), Platform.STEAM);
const byNamesArray = byNames.asJsReadonlyArrayView();
byNamesArray.forEach((p) => console.log(`${p.name} (${p.id}) ban=${p.banType.name}`));

console.log("\n=== getPlayerByAccountId ===");
const accountId = byNamesArray[0].id;
const player = await getPlayerByAccountId(api, accountId);
console.log(`${player.name} (${player.id}) matches=${player.matches.asJsReadonlyArrayView().length}`);

console.log("\n=== getPlayersById ===");
const ids = Array.from(byNamesArray.map((p) => p.id));
const byIds = await getPlayersById(api, KtList.fromJsArray(ids), Platform.STEAM);
byIds.asJsReadonlyArrayView().forEach((p) => console.log(`${p.name} (${p.id})`));
