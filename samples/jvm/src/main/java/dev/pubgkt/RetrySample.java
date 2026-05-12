package dev.pubgkt;

import dev.pubgkt.ratelimit.DelayRateLimiter;

import java.util.Collections;
import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

public class RetrySample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: RetrySample <api-key>");
        }
        String apiKey = args[0];

        System.out.println("=== No retry (default) ===");
        {
            PubgApi api = new PubgApi(apiKey, new DelayRateLimiter(), NoRetry.INSTANCE);
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
        }

        System.out.println("\n=== Retry with NoBackoff (immediate retries) ===");
        {
            PubgApi api = new PubgApi(
                    apiKey,
                    new DelayRateLimiter(),
                    new Retry(3, NoBackoff.INSTANCE, Collections.emptyList())
            );
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
        }

        System.out.println("\n=== Retry with ExponentialBackoff (default settings) ===");
        {
            PubgApi api = new PubgApi(
                    apiKey,
                    new DelayRateLimiter(),
                    new Retry(3, new ExponentialBackoff(), Collections.emptyList())
            );
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
        }

        System.out.println("\n=== Retry with ExponentialBackoff (custom settings) ===");
        {
            PubgApi api = new PubgApi(
                    apiKey,
                    new DelayRateLimiter(),
                    new Retry(
                            5,
                            new ExponentialBackoff(2.0, 500, 30_000, 200),
                            Collections.emptyList()
                    )
            );
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
            @SuppressWarnings("unchecked")
            List<Season> seasons = (List<Season>) BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> SeasonKt.seasons(api, Platform.STEAM, cont)
            );
            System.out.println("Seasons: " + seasons.size());
        }
    }
}
