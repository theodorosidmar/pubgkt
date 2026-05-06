package dev.pubgkt

import dev.pubgkt.players.getPlayersByNames
import dev.pubgkt.stats.lifetime.getLifetimeStatsByAccountId
import dev.pubgkt.stats.lifetime.getLifetimeStatsByGameModeAndPlayers
import dev.pubgkt.stats.ranked.getRankedStatsByAccountIdAndSeasonId
import dev.pubgkt.stats.season.getSeasonStatsByAccountId
import dev.pubgkt.stats.season.getSeasonStatsByGameModeAndPlayers

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: StatsSample <api-key>")
    val api = PubgApi(apiKey)

    val players = api.getPlayersByNames("sparkingg", "TGLTN")
    val accountId = players.first().id
    val accountIds = players.map { it.id }

    val seasons = api.seasons(Platform.STEAM)
    val currentSeason = seasons.currentOrNull() ?: error("No current season found")

    println("=== getLifetimeStatsByAccountId ===")
    val lifetime = api.getLifetimeStatsByAccountId(accountId)
    println("Player: ${lifetime.playerId}")
    println("Squad FPP — Wins: ${lifetime.squadFpp.wins}, Kills: ${lifetime.squadFpp.kills}")

    println("\n=== getLifetimeStatsByGameModeAndPlayers ===")
    val lifetimeByMode = api.getLifetimeStatsByGameModeAndPlayers(
        gameMode = GameMode.SQUAD_FPP,
        accountIds = accountIds,
    )
    lifetimeByMode.forEach { println("${it.playerId}: ${it.stats.kills} kills, ${it.stats.wins} wins") }

    println("\n=== getSeasonStatsByAccountId ===")
    val seasonStats = api.getSeasonStatsByAccountId(accountId, seasonId = currentSeason.id)
    println("Season: ${seasonStats.seasonId}")
    println("Squad FPP — Wins: ${seasonStats.squadFpp.wins}, Kills: ${seasonStats.squadFpp.kills}")

    println("\n=== getSeasonStatsByGameModeAndPlayers ===")
    val seasonByMode = api.getSeasonStatsByGameModeAndPlayers(
        seasonId = currentSeason.id,
        gameMode = GameMode.SQUAD_FPP,
        accountIds = accountIds,
    )
    seasonByMode.forEach { println("${it.playerId}: ${it.stats.kills} kills, ${it.stats.wins} wins") }

    println("\n=== getRankedStatsByAccountIdAndSeasonId ===")
    val ranked = api.getRankedStatsByAccountIdAndSeasonId(accountId, seasonId = currentSeason.id)
    println("Player: ${ranked.playerId}")
    println("Tier: ${ranked.currentTier}, RP: ${ranked.currentRankPoint}, KDA: ${ranked.kda}")
}
