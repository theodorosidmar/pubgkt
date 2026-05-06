package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import dev.pubgkt.players.GetPlayersByNameKt;
import dev.pubgkt.players.Player;
import dev.pubgkt.stats.lifetime.GetLifetimeStatsKt;
import dev.pubgkt.stats.lifetime.LifetimeGameModeStats;
import dev.pubgkt.stats.lifetime.LifetimePlayerStats;
import dev.pubgkt.stats.ranked.GetRankedStatsKt;
import dev.pubgkt.stats.ranked.RankedStats;
import dev.pubgkt.stats.season.GetSeasonStatsKt;
import dev.pubgkt.stats.season.SeasonGameModeStats;
import dev.pubgkt.stats.season.SeasonPlayerStats;

public class StatsSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: StatsSample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        List<Player> players = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg", "TGLTN"), Platform.STEAM, cont)
        );
        String accountId = players.get(0).getId();
        List<String> accountIds = players.stream().map(Player::getId).toList();

        List<Season> seasons = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> SeasonKt.seasons(api, Platform.STEAM, cont)
        );
        String seasonId = seasons.stream().filter(Season::isCurrentSeason).findFirst().orElseThrow().getId();

        System.out.println("=== getLifetimeStatsByAccountId ===");
        LifetimePlayerStats lifetime = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetLifetimeStatsKt.getLifetimeStatsByAccountId(api, accountId, Platform.STEAM, cont)
        );
        System.out.println("Player: " + lifetime.getPlayerId());
        System.out.println("Squad FPP — Wins: " + lifetime.getSquadFpp().getWins() + ", Kills: " + lifetime.getSquadFpp().getKills());

        System.out.println("\n=== getLifetimeStatsByGameModeAndPlayers ===");
        List<LifetimeGameModeStats> lifetimeByMode = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetLifetimeStatsKt.getLifetimeStatsByGameModeAndPlayers(api, GameMode.SQUAD_FPP, accountIds, Platform.STEAM, cont)
        );
        lifetimeByMode.forEach(s -> System.out.println(s.getPlayerId() + ": " + s.getStats().getKills() + " kills"));

        System.out.println("\n=== getSeasonStatsByAccountId ===");
        SeasonPlayerStats seasonStats = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetSeasonStatsKt.getSeasonStatsByAccountId(api, accountId, seasonId, Platform.STEAM, cont)
        );
        System.out.println("Season: " + seasonStats.getSeasonId());
        System.out.println("Squad FPP — Wins: " + seasonStats.getSquadFpp().getWins() + ", Kills: " + seasonStats.getSquadFpp().getKills());

        System.out.println("\n=== getSeasonStatsByGameModeAndPlayers ===");
        List<SeasonGameModeStats> seasonByMode = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetSeasonStatsKt.getSeasonStatsByGameModeAndPlayers(api, seasonId, GameMode.SQUAD_FPP, accountIds, Platform.STEAM, cont)
        );
        seasonByMode.forEach(s -> System.out.println(s.getPlayerId() + ": " + s.getStats().getKills() + " kills"));

        System.out.println("\n=== getRankedStatsByAccountIdAndSeasonId ===");
        RankedStats ranked = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetRankedStatsKt.getRankedStatsByAccountIdAndSeasonId(api, accountId, seasonId, Platform.STEAM, cont)
        );
        System.out.println("Player: " + ranked.getPlayerId());
        System.out.println("Tier: " + ranked.getCurrentTier() + ", RP: " + ranked.getCurrentRankPoint() + ", KDA: " + ranked.getKda());
    }
}
