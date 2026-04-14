package pubgkt;

import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.future.FutureKt;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Demonstrates calling the pubgkt library from Java using
 * {@code kotlinx-coroutines-jdk8} to bridge suspend functions to
 * {@link java.util.concurrent.CompletableFuture}.
 *
 * <p>Run via Gradle:
 * <pre>
 *   ./gradlew :samples:runJava --args="&lt;api-key&gt; [accountId] [playerName]"
 * </pre>
 */
public class PlayersSample {
    static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: PlayersSample <api-key> [accountId] [playerName]");
        }

        String apiKey = args[0];
        String accountId = args.length > 1 ? args[1] : "account.c766e217ed7345499ac1b342de1de0dd";
        String playerName = args.length > 2 ? args[2] : "sparkingg";

        PubgApi api = new PubgApi(apiKey);
        api.setPlatform(Platform.STEAM);

        // 1. Get a single player by account ID
        Player player = FutureKt.<Player>future(
                api, api.getCoroutineContext(), CoroutineStart.DEFAULT,
                (scope, cont) -> GetPlayerByAccountIdKt.getPlayerByAccountId(api, accountId, cont)
        ).get();
        System.out.println("=== getPlayerByAccountId ===");
        System.out.println(player);

        // 2. Get players by account IDs
        List<Player> byIds = FutureKt.<List<Player>>future(
                api, api.getCoroutineContext(), CoroutineStart.DEFAULT,
                (scope, cont) -> GetPlayersByIdKt.getPlayersById(api, List.of(accountId), cont)
        ).get();
        System.out.println("\n=== getPlayersById ===");
        byIds.forEach(System.out::println);

        // 3. Get players by names
        List<Player> byNames = FutureKt.<List<Player>>future(
                api, api.getCoroutineContext(), CoroutineStart.DEFAULT,
                (scope, cont) -> GetPlayersByNameKt.getPlayersByNames(api, List.of(playerName), cont)
        ).get();
        System.out.println("\n=== getPlayersByNames ===");
        byNames.forEach(System.out::println);
    }
}
