package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import dev.pubgkt.clans.Clan;
import dev.pubgkt.clans.GetClanByIdKt;
import dev.pubgkt.players.GetPlayersByNameKt;
import dev.pubgkt.players.Player;

public class ClansSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: ClansSample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        System.out.println("=== getClanById ===");
        List<Player> players = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg"), Platform.STEAM, cont)
        );
        String clanId = players.get(0).getClanId();

        Clan clan = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetClanByIdKt.getClanById(api, clanId, Platform.STEAM, cont)
        );
        System.out.println(clan);
    }
}
