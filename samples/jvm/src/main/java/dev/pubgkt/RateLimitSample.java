package dev.pubgkt;

import dev.pubgkt.ratelimit.ConcurrentDelayRateLimiter;
import dev.pubgkt.ratelimit.DelayRateLimiter;
import dev.pubgkt.ratelimit.RateLimiter;

import java.util.List;

import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RateLimitSample {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: RateLimitSample <api-key>");
        }
        String apiKey = args[0];

        System.out.println("=== No rate limiting ===");
        {
            PubgApi api = new PubgApi(apiKey, RateLimiter.Companion.getNone());
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
        }

        System.out.println("\n=== DelayRateLimiter (default) ===");
        {
            PubgApi api = new PubgApi(apiKey, new DelayRateLimiter());
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
        }

        System.out.println("\n=== ConcurrentDelayRateLimiter ===");
        {
            PubgApi api = new PubgApi(apiKey, new ConcurrentDelayRateLimiter());
            Boolean up = BuildersKt.runBlocking(
                    EmptyCoroutineContext.INSTANCE,
                    (_, cont) -> StatusKt.isUp(api, cont)
            );
            System.out.println("API is up: " + up);
        }

        System.out.println("\n=== Custom RateLimiter ===");
        {
            RateLimiter loggingLimiter = new RateLimiter() {
                private int requestCount = 0;

                @Nullable
                @Override
                public Object throttle(@NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> cont) {
                    requestCount++;
                    System.out.println("  [RateLimiter] Request #" + requestCount + " — allowing");
                    return kotlin.Unit.INSTANCE;
                }

                @Override
                public void onResponse(@Nullable Integer limit, @Nullable Integer remaining, @Nullable Long reset) {
                    System.out.println("  [RateLimiter] Response — limit=" + limit + ", remaining=" + remaining + ", reset=" + reset);
                }
            };
            PubgApi api = new PubgApi(apiKey, loggingLimiter);
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
