import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: StatsSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    let players = try await api.getPlayersByNames(playerNames: ["sparkingg", "TGLTN"], platform: Platform.steam)
    let accountId = players.first!.id
    let accountIds = players.map { $0.id }

    let seasons = try await api.seasons(platform: Platform.steam)
    guard let currentSeason = SeasonKt.currentOrNull(seasons) else {
        print("No current season found")
        return
    }

    print("=== getLifetimeStatsByAccountId ===")
    let lifetime = try await api.getLifetimeStatsByAccountId(accountId: accountId, platform: Platform.steam)
    print("Player: \(lifetime.playerId)")
    print("Squad FPP — Wins: \(lifetime.squadFpp.wins), Kills: \(lifetime.squadFpp.kills)")

    print("\n=== getLifetimeStatsByGameModeAndPlayers ===")
    let lifetimeByMode = try await api.getLifetimeStatsByGameModeAndPlayers(
        gameMode: GameMode.squadFpp,
        accountIds: accountIds,
        platform: Platform.steam
    )
    for s in lifetimeByMode {
        print("\(s.playerId): \(s.stats.kills) kills, \(s.stats.wins) wins")
    }

    print("\n=== getSeasonStatsByAccountId ===")
    let seasonStats = try await api.getSeasonStatsByAccountId(
        accountId: accountId,
        seasonId: currentSeason.id,
        platform: Platform.steam
    )
    print("Season: \(seasonStats.seasonId)")
    print("Squad FPP — Wins: \(seasonStats.squadFpp.wins), Kills: \(seasonStats.squadFpp.kills)")

    print("\n=== getSeasonStatsByGameModeAndPlayers ===")
    let seasonByMode = try await api.getSeasonStatsByGameModeAndPlayers(
        seasonId: currentSeason.id,
        gameMode: GameMode.squadFpp,
        accountIds: accountIds,
        platform: Platform.steam
    )
    for s in seasonByMode {
        print("\(s.playerId): \(s.stats.kills) kills, \(s.stats.wins) wins")
    }

    print("\n=== getRankedStatsByAccountIdAndSeasonId ===")
    let ranked = try await api.getRankedStatsByAccountIdAndSeasonId(
        accountId: accountId,
        seasonId: currentSeason.id,
        platform: Platform.steam
    )
    print("Player: \(ranked.playerId)")
    print("Tier: \(ranked.currentTier), RP: \(ranked.currentRankPoint), KDA: \(ranked.kda)")
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
