package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import dev.pubgkt.players.GetPlayerByAccountIdKt;
import dev.pubgkt.players.GetPlayersByIdKt;
import dev.pubgkt.players.GetPlayersByNameKt;
import dev.pubgkt.players.Player;

public class PlayersSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: PlayersSample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        System.out.println("=== getPlayersByNames ===");
        List<Player> byNames = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of("sparkingg", "TGLTN"), Platform.STEAM, cont)
        );
        byNames.forEach(System.out::println);

        String accountId = byNames.get(0).getId();

        System.out.println("\n=== getPlayerByAccountId ===");
        Player player = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayerByAccountIdKt.getPlayerByAccountId(api, accountId, Platform.STEAM, cont)
        );
        System.out.println(player);

        System.out.println("\n=== getPlayersById ===");
        List<String> ids = byNames.stream().map(Player::getId).toList();
        List<Player> byIds = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByIdKt.getPlayersById(api, ids, Platform.STEAM, cont)
        );
        byIds.forEach(System.out::println);
    }
}
