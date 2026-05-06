package dev.pubgkt;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

public class CommonSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: CommonSample <api-key>");
        }

        PubgApi api = new PubgApi(args[0]);

        System.out.println("=== isUp ===");
        Boolean up = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> StatusKt.isUp(api, cont)
        );
        System.out.println("API is up: " + up);

        System.out.println("\n=== seasons (by platform) ===");
        List<Season> seasons = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> SeasonKt.seasons(api, Platform.STEAM, cont)
        );
        seasons.forEach(System.out::println);

        System.out.println("\n=== seasons (by platform region) ===");
        List<Season> regionalSeasons = BuildersKt.runBlocking(
                EmptyCoroutineContext.INSTANCE,
                (_, cont) -> SeasonKt.seasons(api, PlatformRegion.PC_NA, cont)
        );
        regionalSeasons.forEach(System.out::println);
    }
}
