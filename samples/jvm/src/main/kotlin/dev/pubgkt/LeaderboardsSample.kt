package dev.pubgkt

import dev.pubgkt.leaderboards.GameMode
import dev.pubgkt.leaderboards.getLeaderboard

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: LeaderboardsSample <api-key>")
    val api = PubgApi(apiKey)

    println("=== getLeaderboard ===")
    val seasons = api.seasons(Platform.STEAM)
    val currentSeason = seasons.currentOrNull() ?: error("No current season found")

    val leaderboard = api.getLeaderboard(
        seasonId = currentSeason.id,
        gameMode = GameMode.SQUAD_FPP,
        platformRegion = PlatformRegion.PC_NA,
    )
    println("Season: ${leaderboard.seasonId}, Mode: ${leaderboard.gameMode}")
    println("Top 10:")
    leaderboard.placements.take(10).forEach { p ->
        println("  #${p.rank} ${p.playerName} — ${p.rankPoints} RP, ${p.wins} wins, KDA ${p.kda}")
    }
}
