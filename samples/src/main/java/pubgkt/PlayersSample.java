package pubgkt;

import kotlinx.coroutines.BuildersKt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;

/**
 * Demonstrates calling the pubgkt library from Java by bridging suspend
 * functions with {@code runBlocking}.
 *
 * <p>Run via Gradle:
 * <pre>
 *   ./gradlew :samples:runJava --args="&lt;api-key&gt; [accountId] [playerName]"
 * </pre>
 */
public class PlayersSample {
    static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: PlayersSample <api-key> [accountId] [playerName]");
        }

        String apiKey = args[0];
        String accountId = args.length > 1 ? args[1] : "account.c766e217ed7345499ac1b342de1de0dd";
        String playerName = args.length > 2 ? args[2] : "sparkingg";

        PubgApi api = new PubgApi(apiKey);
        api.setPlatform(Platform.STEAM);

        // 1. Get a single player by account ID
        Player player = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayerByAccountIdKt.getPlayerByAccountId(api, accountId, cont)
        );
        System.out.println("=== getPlayerByAccountId ===");
        System.out.println(player);

        // 2. Get players by account IDs
        List<Player> byIds = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByIdKt.getPlayersById(api, List.of(accountId), cont)
        );
        System.out.println("\n=== getPlayersById ===");
        byIds.forEach(System.out::println);

        // 3. Get players by names
        List<Player> byNames = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of(playerName), cont)
        );
        System.out.println("\n=== getPlayersByNames ===");
        byNames.forEach(System.out::println);

        // 4. Get clan by id
        Clan clan = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> GetClanByIdKt.getClanById(api, "clan.d52aad6adcfb4c4783a85eb250f6e822", cont)
        );
        System.out.println("\n=== getClanById ===");
        System.out.println(clan);
    }
}
