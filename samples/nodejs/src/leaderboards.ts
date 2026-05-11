// Leaderboards module sample: query leaderboards
// Usage: PUBG_API_KEY=your-key npm run leaderboards

import { PubgApi, Platform, GameMode, getSeasonsByPlatform } from "@pubgkt/common";
import { getLeaderboard } from "@pubgkt/leaderboards";

const apiKey = process.env.PUBG_API_KEY;
if (!apiKey) {
    console.error("Usage: PUBG_API_KEY=your-key npm run leaderboards");
    process.exit(1);
}

const api = new PubgApi(apiKey);

const seasonsList = await getSeasonsByPlatform(api, Platform.STEAM);
const current = seasonsList.asJsReadonlyArrayView().find((s) => s.isCurrentSeason);

if (current) {
    console.log(`=== getLeaderboard (season=${current.id}, SQUAD_FPP) ===`);
    const leaderboard = await getLeaderboard(api, current.id, GameMode.SQUAD_FPP);
    const placements = leaderboard.placements.asJsReadonlyArrayView();
    console.log(`Top ${Math.min(10, placements.length)} players:`);
    placements.slice(0, 10).forEach((p) =>
        console.log(`  #${p.rank} ${p.playerName} — ${p.rankPoints.toFixed(0)} RP, KDA=${p.kda.toFixed(2)}`)
    );
}
