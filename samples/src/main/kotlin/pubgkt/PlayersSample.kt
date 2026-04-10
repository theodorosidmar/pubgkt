package pubgkt

suspend fun main(args: Array<String>) {
    val apiKey = args.getOrNull(0) ?: error("Usage: PlayersSample <api-key> [accountId] [playerName]")
    val accountId = args.getOrElse(1) { "account.c766e217ed7345499ac1b342de1de0dd" }
    val playerName = args.getOrElse(2) { "sparkingg" }

    val api = PubgApi(apiKey).apply { platform = Platform.STEAM }

    println("=== getPlayerByAccountId ===")
    val player = api.getPlayerByAccountId(accountId)
    println(player)

    println("\n=== getPlayersById ===")
    val byIds = api.getPlayersById(accountId)
    byIds.forEach(::println)

    println("\n=== getPlayersByNames ===")
    val byNames = api.getPlayersByNames(playerName)
    byNames.forEach(::println)
}
