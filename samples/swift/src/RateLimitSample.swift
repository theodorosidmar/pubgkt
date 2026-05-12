import Foundation
import PubgKt

// MARK: - KotlinClock implementation for Swift

/// Bridges Swift's system clock to Kotlin's Clock interface.
/// Required because DelayRateLimiter and ConcurrentDelayRateLimiter
/// take a Clock parameter, and the Kotlin default (Clock.System) does
/// not carry over to ObjC/Swift interop.
class SystemClock: KotlinClock {
    func now() -> KotlinInstant {
        let epochMs = Int64(Date().timeIntervalSince1970 * 1000)
        return KotlinInstant.companion.fromEpochMilliseconds(epochMilliseconds: epochMs)
    }
}

// MARK: - Custom RateLimiter

/// A custom RateLimiter that logs every request and response.
class LoggingRateLimiter: RateLimiter {
    private var requestCount = 0

    func throttle() async throws {
        requestCount += 1
        print("  [RateLimiter] Request #\(requestCount) — allowing")
    }

    func onResponse(limit: KotlinInt?, remaining: KotlinInt?, reset: KotlinLong?) {
        print("  [RateLimiter] Response — limit=\(String(describing: limit)), remaining=\(String(describing: remaining)), reset=\(String(describing: reset))")
    }
}

// MARK: - Main

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: RateLimitSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]

    print("=== No rate limiting ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: RateLimiterCompanion.shared.None,
            retry: NoRetry.shared
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
    }

    print("\n=== DelayRateLimiter ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: DelayRateLimiter(clock: SystemClock()),
            retry: NoRetry.shared
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
    }

    print("\n=== ConcurrentDelayRateLimiter ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: ConcurrentDelayRateLimiter(clock: SystemClock()),
            retry: NoRetry.shared
        )
        let up = try await api.isUp()
        print("API is up: \(up)")
    }

    print("\n=== Custom logging RateLimiter ===")
    do {
        let api = PubgApi(
            apiKey: apiKey,
            rateLimiter: LoggingRateLimiter(),
            retry: NoRetry.shared
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
