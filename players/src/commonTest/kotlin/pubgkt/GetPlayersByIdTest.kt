package pubgkt

class GetPlayersByIdTest : GetPlayersTest() {

    override val queryParameterName = FILTER_PLAYER_IDS

    override suspend fun PubgApi.fetchPlayers(vararg values: String): List<Player> =
        getPlayersById(*values)

    override suspend fun PubgApi.fetchPlayers(values: List<String>): List<Player> =
        getPlayersById(values)
}
