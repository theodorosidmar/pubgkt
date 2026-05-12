import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: ClansSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    print("=== getClanById ===")
    let players = try await api.getPlayersByNames(playerNames: ["sparkingg"], platform: Platform.steam)
    let player = players.first!
    guard let clanId = player.clanId else {
        print("Player has no clan")
        return
    }
    let clan = try await api.getClanById(clanId: clanId, platform: Platform.steam)
    print(clan)
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
