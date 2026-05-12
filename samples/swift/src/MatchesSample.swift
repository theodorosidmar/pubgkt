import Foundation
import PubgKt

func main() async throws {
    guard CommandLine.arguments.count > 1 else {
        print("Usage: MatchesSample <api-key>")
        return
    }
    let apiKey = CommandLine.arguments[1]
    let api = PubgApi(apiKey: apiKey, rateLimiter: RateLimiterCompanion.shared.None, retry: NoRetry.shared)

    print("=== getMatchById ===")
    let players = try await api.getPlayersByNames(playerNames: ["sparkingg"], platform: Platform.steam)
    let player = players.first!
    guard let firstMatch = player.matches.first else {
        print("Player has no recent matches")
        return
    }
    let match = try await api.getMatchById(matchId: firstMatch.id, platform: Platform.steam)
    print("Match: \(match.id)")
    print("Map: \(match.mapName), Mode: \(match.gameMode), Duration: \(match.duration)s")
    print("Participants: \(match.participants.count) teams")
    for t in match.participants.prefix(3) {
        print("  Team \(t.teamId) (rank \(t.rank)):")
        for mp in t.players {
            print("    \(mp.name) — \(mp.kills) kills, \(mp.damageDealt) dmg")
        }
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
