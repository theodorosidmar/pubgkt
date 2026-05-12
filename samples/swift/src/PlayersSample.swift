import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: PlayersSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    print("=== getPlayersByNames ===")
    let byNames = try await api.getPlayersByNames(playerNames: ["sparkingg", "TGLTN"], platform: Platform.steam)
    for player in byNames { print(player) }

    print("\n=== getPlayerByAccountId ===")
    let accountId = byNames.first!.id
    let player = try await api.getPlayerByAccountId(accountId: accountId, platform: Platform.steam)
    print(player)

    print("\n=== getPlayersById ===")
    let ids = byNames.map { $0.id }
    let byIds = try await api.getPlayersById(accountIds: ids, platform: Platform.steam)
    for player in byIds { print(player) }
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
