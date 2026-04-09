package pubgkt

suspend fun main(args: List<String>) {
    val apiKey = args.firstOrNull() ?: error("PUBG API key is required")
    val pubgApi = PubgApi(apiKey).apply { platform = Platform.STEAM }
    val player = pubgApi.getPlayerByAccountId("account.c766e217ed7345499ac1b342de1de0dd")
    println(player)
}
