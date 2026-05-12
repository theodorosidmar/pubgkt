import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: LeaderboardsSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    print("=== getLeaderboard ===")
    let seasons = try await api.seasons(platform: Platform.steam)
    guard let currentSeason = SeasonKt.currentOrNull(seasons) else {
        print("No current season found")
        return
    }

    let leaderboard = try await api.getLeaderboard(
        seasonId: currentSeason.id,
        gameMode: GameMode_.squadFpp,
        platformRegion: PlatformRegion.pcNa
    )
    print("Season: \(leaderboard.seasonId), Mode: \(leaderboard.gameMode)")
    print("Top 10:")
    for p in leaderboard.placements.prefix(10) {
        print("  #\(p.rank) \(p.playerName) — \(p.rankPoints) RP, \(p.wins) wins, KDA \(p.kda)")
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
