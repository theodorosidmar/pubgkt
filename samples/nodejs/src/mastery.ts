// Mastery module sample: weapon and survival mastery
// Usage: PUBG_API_KEY=your-key npm run mastery

import { PubgApi, Platform, KtList } from "@pubgkt/common";
import { getPlayersByNames } from "@pubgkt/players";
import { getWeaponMasteryByAccountId, getSurvivalMasteryByAccountId } from "@pubgkt/mastery";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run mastery");
    process.exit(1);
}

const api = new PubgApi(apiKey);

const players = await getPlayersByNames(api, KtList.fromJsArray(["sparkingg"]), Platform.STEAM);
const player = players.asJsReadonlyArrayView()[0];
console.log(`Player: ${player.name} (${player.id})`);

console.log("\n=== getWeaponMasteryByAccountId ===");
const weaponMastery = await getWeaponMasteryByAccountId(api, player.id, Platform.STEAM);
const ak47 = weaponMastery.ak47;
if (ak47) {
    console.log(`AK47: level=${ak47.currentLevel} tier=${ak47.currentTier} kills=${ak47.statsTotal.kills}`);
}
const m416 = weaponMastery.m416;
if (m416) {
    console.log(`M416: level=${m416.currentLevel} tier=${m416.currentTier} kills=${m416.statsTotal.kills}`);
}

console.log("\n=== getSurvivalMasteryByAccountId ===");
const survival = await getSurvivalMasteryByAccountId(api, player.id, Platform.STEAM);
console.log(`XP=${survival.xp} level=${survival.level} tier=${survival.tier} matches=${survival.totalMatchesPlayed}`);
