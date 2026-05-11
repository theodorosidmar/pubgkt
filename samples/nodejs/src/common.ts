// Common module sample: status check and seasons
// Usage: PUBG_API_KEY=your-key npm run common

import { PubgApi, Platform, PlatformRegion, isUp, getSeasonsByPlatform, getSeasonsByPlatformRegion } from "@pubgkt/common";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run common");
    process.exit(1);
}

const api = new PubgApi(apiKey);

console.log("=== isUp ===");
const up = await isUp(api);
console.log("API is up:", up);

console.log("\n=== seasons (by platform) ===");
const seasonsList = await getSeasonsByPlatform(api, Platform.STEAM);
const seasonsArray = seasonsList.asJsReadonlyArrayView();
seasonsArray.forEach((s) => console.log(`${s.id} current=${s.isCurrentSeason} offSeason=${s.isOffSeason}`));

const current = seasonsArray.find((s) => s.isCurrentSeason);
console.log("\n=== current season ===");
console.log("Current:", current?.id);

console.log("\n=== seasons (by platform region) ===");
const regionalSeasons = await getSeasonsByPlatformRegion(api, PlatformRegion.PC_NA);
regionalSeasons.asJsReadonlyArrayView().forEach((s) => console.log(s.id));
