package dev.pubgkt

import dev.pubgkt.mastery.getSurvivalMasteryByAccountId
import dev.pubgkt.mastery.getWeaponMasteryByAccountId
import dev.pubgkt.players.getPlayersByNames

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: MasterySample <api-key>")
    val api = PubgApi(apiKey)

    val players = api.getPlayersByNames("sparkingg")
    val accountId = players.first().id

    println("=== getWeaponMasteryByAccountId ===")
    val weaponMastery = api.getWeaponMasteryByAccountId(accountId)
    println("Player: ${weaponMastery.playerId}, Platform: ${weaponMastery.platform}")

    println("\n=== getSurvivalMasteryByAccountId ===")
    val survivalMastery = api.getSurvivalMasteryByAccountId(accountId)
    println("Player: ${survivalMastery.playerId}")
    println("XP: ${survivalMastery.xp}, Level: ${survivalMastery.level}, Tier: ${survivalMastery.tier}")
    println("Total matches: ${survivalMastery.totalMatchesPlayed}")
    println("Top 10s: ${survivalMastery.top10}")
}
