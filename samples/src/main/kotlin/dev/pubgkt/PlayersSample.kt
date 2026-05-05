package dev.pubgkt

import dev.pubgkt.players.getPlayersByNames
import dev.pubgkt.stats.season.getSeasonStatsByGameModeAndPlayers

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: PlayersSample <api-key> [accountId] [playerName]")
    val accountId = args.getOrElse(1) { "account.91186dcca3cb4ad198fac1e4ab1d5b80" }
    val playerName = args.getOrElse(2) { "sparkingg" }
    val clanId = args.getOrElse(3) { "clan.d52aad6adcfb4c4783a85eb250f6e822" }

    val api = PubgApi(apiKey)
    val playerIds = api.getPlayersByNames("sparkingg", "TGLTN").map { it.id }
    println(
        api.getSeasonStatsByGameModeAndPlayers(
            seasonId = "division.bro.official.pc-2018-41",
            gameMode = GameMode.SQUAD_FPP,
            accountIds = playerIds,
        ),
    )

//    println("=== getPlayerByAccountId ===")
//    val player = api.getPlayerByAccountId(accountId)
//    println(player)
//
//    println("\n=== getPlayersById ===")
//    val byIds = api.getPlayersById(accountId)
//    byIds.forEach(::println)
//
//    println("\n=== getPlayersByNames ===")
//    val byNames = api.getPlayersByNames(playerName)
//    byNames.forEach(::println)
//
//    println("\n=== getClanById ===")
//    val clan = api.getClanById(clanId)
//    println(clan)
}
