import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: MasterySample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    let players = try await api.getPlayersByNames(playerNames: ["sparkingg"], platform: Platform.steam)
    let accountId = players.first!.id

    print("=== getWeaponMasteryByAccountId ===")
    let weaponMastery = try await api.getWeaponMasteryByAccountId(accountId: accountId, platform: Platform.steam)
    print("Player: \(weaponMastery.playerId), Platform: \(weaponMastery.platform)")

    print("\n=== getSurvivalMasteryByAccountId ===")
    let survivalMastery = try await api.getSurvivalMasteryByAccountId(accountId: accountId, platform: Platform.steam)
    print("Player: \(survivalMastery.playerId)")
    print("XP: \(survivalMastery.xp), Level: \(survivalMastery.level), Tier: \(survivalMastery.tier)")
    print("Total matches: \(survivalMastery.totalMatchesPlayed)")
    print("Top 10s: \(survivalMastery.top10)")
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
