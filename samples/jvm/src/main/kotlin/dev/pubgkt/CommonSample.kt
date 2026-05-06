package dev.pubgkt

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: CommonSample <api-key>")
    val api = PubgApi(apiKey)

    println("=== isUp ===")
    val up = api.isUp()
    println("API is up: $up")

    println("\n=== seasons (by platform) ===")
    val seasons = api.seasons(Platform.STEAM)
    seasons.forEach { println(it) }

    println("\n=== current season ===")
    val current = seasons.currentOrNull()
    println("Current: $current")

    println("\n=== seasons (by platform region) ===")
    val regionalSeasons = api.seasons(PlatformRegion.PC_NA)
    regionalSeasons.forEach { println(it) }
}
