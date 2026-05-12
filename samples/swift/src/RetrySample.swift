import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: RetrySample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]

    print("=== No retry (default) ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: RateLimiterCompanion.shared.None,
            retry: NoRetry.shared
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
    }

    print("\n=== Retry with NoBackoff (immediate retries) ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: RateLimiterCompanion.shared.None,
            retry: Retry(maxRetries: 3, backoff: NoBackoff.shared, retryOnExceptions: [])
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
    }

    print("\n=== Retry with ExponentialBackoff (default settings) ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: RateLimiterCompanion.shared.None,
            retry: Retry(
                maxRetries: 3,
                backoff: ExponentialBackoff(base: 2.0, baseDelayMs: 1000, maxDelayMs: 60_000, randomizationMs: 1000),
                retryOnExceptions: []
            )
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
    }

    print("\n=== Retry with ExponentialBackoff (custom settings) ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: RateLimiterCompanion.shared.None,
            retry: Retry(
                maxRetries: 5,
                backoff: ExponentialBackoff(
                    base: 2.0,
                    baseDelayMs: 500,
                    maxDelayMs: 30_000,
                    randomizationMs: 200
                ),
                retryOnExceptions: []
            )
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
        let seasons = try await api.seasons(platform: Platform.steam)
        print("Seasons: \(seasons.count)")
    }
}

Task {
    do {
        try await main()
    } catch {
        print("Error: \(error)")
    }
    exit(0)
}
dispatchMain()
