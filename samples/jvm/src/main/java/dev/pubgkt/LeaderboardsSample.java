package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import dev.pubgkt.leaderboards.GameMode;
import dev.pubgkt.leaderboards.GetLeaderboardKt;
import dev.pubgkt.leaderboards.Leaderboard;

public class LeaderboardsSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: LeaderboardsSample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        System.out.println("=== getLeaderboard ===");
        List<Season> seasons = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> SeasonKt.seasons(api, Platform.STEAM, cont)
        );
        Season currentSeason = seasons.stream().filter(Season::isCurrentSeason).findFirst().orElseThrow();

        Leaderboard leaderboard = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetLeaderboardKt.getLeaderboard(api, currentSeason.getId(), GameMode.SQUAD_FPP, PlatformRegion.PC_NA, cont)
        );
        System.out.println("Season: " + leaderboard.getSeasonId() + ", Mode: " + leaderboard.getGameMode());
        System.out.println("Top 10:");
        leaderboard.getPlacements().stream().limit(10).forEach(p ->
                System.out.println("  #" + p.getRank() + " " + p.getPlayerName() + " — " + p.getRankPoints() + " RP")
        );
    }
}
