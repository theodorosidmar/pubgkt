package dev.pubgkt

import dev.pubgkt.clans.getClanById
import dev.pubgkt.players.getPlayersByNames

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: ClansSample <api-key>")
    val api = PubgApi(apiKey)

    println("=== getClanById ===")
    val players = api.getPlayersByNames("sparkingg")
    val clanId = players.first().clanId ?: error("Player has no clan")
    val clan = api.getClanById(clanId)
    println(clan)
}
