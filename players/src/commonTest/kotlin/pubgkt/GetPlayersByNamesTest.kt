package pubgkt

class GetPlayersByNamesTest : GetPlayersTest() {

    override val queryParameterName = "filter[playerNames]"

    override suspend fun PubgApi.fetchPlayers(vararg values: String): List<Player> =
        getPlayersByNames(*values)

    override suspend fun PubgApi.fetchPlayers(values: List<String>): List<Player> =
        getPlayersByNames(values)
}
