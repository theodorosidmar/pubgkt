package pubgkt

object PubgApiFactory {
    fun createForSteam(key: String): PubgApi = PubgSteamApi(key)
    fun createForPsn(key: String): PubgApi = PubgPsnApi(key)
    fun createForXbox(key: String): PubgXboxApi = PubgXboxApi(key)
}
