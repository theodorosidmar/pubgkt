import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: CommonSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    print("=== isUp ===")
    let up = try await api.isUp()
    print("API is up: \(up)")

    print("\n=== seasons (by platform) ===")
    let seasons = try await api.seasons(platform: Platform.steam)
    for season in seasons { print(season) }

    print("\n=== current season ===")
    let current = SeasonKt.currentOrNull(seasons)
    print("Current: \(String(describing: current))")

    print("\n=== seasons (by platform region) ===")
    let regionalSeasons = try await api.seasons(platformRegion: PlatformRegion.pcNa)
    for season in regionalSeasons { print(season) }
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
