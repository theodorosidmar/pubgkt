package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import dev.pubgkt.mastery.GetSurvivalMasteryByAccountIdKt;
import dev.pubgkt.mastery.GetWeaponMasteryByAccountIdKt;
import dev.pubgkt.mastery.SurvivalMastery;
import dev.pubgkt.mastery.WeaponMastery;
import dev.pubgkt.players.GetPlayersByNameKt;
import dev.pubgkt.players.Player;

public class MasterySample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: MasterySample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        List<Player> players = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg"), Platform.STEAM, cont)
        );
        String accountId = players.get(0).getId();

        System.out.println("=== getWeaponMasteryByAccountId ===");
        WeaponMastery weaponMastery = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetWeaponMasteryByAccountIdKt.getWeaponMasteryByAccountId(api, accountId, Platform.STEAM, cont)
        );
        System.out.println("Player: " + weaponMastery.getPlayerId() + ", Platform: " + weaponMastery.getPlatform());

        System.out.println("\n=== getSurvivalMasteryByAccountId ===");
        SurvivalMastery survivalMastery = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetSurvivalMasteryByAccountIdKt.getSurvivalMasteryByAccountId(api, accountId, Platform.STEAM, cont)
        );
        System.out.println("Player: " + survivalMastery.getPlayerId());
        System.out.println("XP: " + survivalMastery.getXp() + ", Level: " + survivalMastery.getLevel() + ", Tier: " + survivalMastery.getTier());
        System.out.println("Total matches: " + survivalMastery.getTotalMatchesPlayed());
    }
}
