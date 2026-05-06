package dev.pubgkt

import dev.pubgkt.matches.getMatchById
import dev.pubgkt.players.getPlayersByNames

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: MatchesSample <api-key>")
    val api = PubgApi(apiKey)

    println("=== getMatchById ===")
    val players = api.getPlayersByNames("sparkingg")
    val matchId = players.first().matches.firstOrNull()?.id ?: error("Player has no recent matches")
    val match = api.getMatchById(matchId)
    println("Match: ${match.id}")
    println("Map: ${match.mapName}, Mode: ${match.gameMode}, Duration: ${match.duration}s")
    println("Participants: ${match.participants.size} teams")
    match.participants.take(3).forEach { team ->
        println("  Team ${team.teamId} (rank ${team.rank}):")
        team.players.forEach { p -> println("    ${p.name} — ${p.kills} kills, ${p.damageDealt} dmg") }
    }
}
