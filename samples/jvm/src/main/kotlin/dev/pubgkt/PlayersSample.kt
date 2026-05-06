package dev.pubgkt

import dev.pubgkt.players.getPlayerByAccountId
import dev.pubgkt.players.getPlayersById
import dev.pubgkt.players.getPlayersByNames

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: PlayersSample <api-key>")
    val api = PubgApi(apiKey)

    println("=== getPlayersByNames ===")
    val byNames = api.getPlayersByNames("sparkingg", "TGLTN")
    byNames.forEach { println(it) }

    println("\n=== getPlayerByAccountId ===")
    val accountId = byNames.first().id
    val player = api.getPlayerByAccountId(accountId)
    println(player)

    println("\n=== getPlayersById ===")
    val byIds = api.getPlayersById(byNames.map { it.id })
    byIds.forEach { println(it) }
}
