package pubgkt

import io.ktor.client.call.body
import kotlinx.coroutines.coroutineScope

/**
 * PUBG API wrapper to interact with PUBG API features like Players, Stats, Clans, Seasons etc.
 *
 * @param apiKey PUBG API Key provided at [PUBG Developer Portal](https://developer.pubg.com/)
 * @param platform Which platform you wish to search
 */
abstract class PubgApi(apiKey: String, platform: PubgPlatform) {
    private val client = makePubgClient(apiKey, platform)

    /**
     * Same as [getPlayersByNames], but returns a single [Player].
     */
    suspend fun getPlayerByName(name: String): Result<Player> = coroutineScope {
        runCatching {
            client
                .getPlayerByName(name)
                .body<PlayersResponse>()
                .data
                .first()
        }
    }

    /**
     * Get a collection of up to 10 players. Names are case-sensitive
     *
     * Check [documentation](https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players)
     * @throws IllegalArgumentException when `names` is bigger than 10
     */
    suspend fun getPlayersByNames(names: List<String>): Result<List<Player>> {
        require(names.size <= 10) { "10 players max" }
        return runCatching {
            client
                .getPlayersByName(names)
                .body<PlayersResponse>()
                .data
        }
    }

    /**
     * Get a collection of up to 10 players.
     *
     * Check [documentation](https://documentation.pubg.com/en/players-endpoint.html#/Players/get_players)
     * @throws IllegalArgumentException when `ids` is greater than 10
     */
    suspend fun getPlayersByIds(ids: List<String>): Result<List<Player>> {
        require(ids.size <= 10) { "10 players max" }
        return runCatching {
            client
                .getPlayersByIds(ids)
                .body<PlayersResponse>()
                .data
        }
    }

    /**
     * Same as [getLifetimeStats], but returns a single [Stats]
     */
    suspend fun getLifetimeStats(playerName: String, gameMode: GameMode): Result<Stats> =
        coroutineScope {
            runCatching {
                val player = getPlayerByName(playerName).getOrThrow()
                val lifetime: LifetimeResponse = client.getLifetimeStats(player.id, gameMode.id).body()
                lifetime.data.first().attributes.gameModeStats[gameMode.id]!!
            }
        }

    /**
     * Get lifetime stats for up to 10 players
     *
     * Check [documentation](https://documentation.pubg.com/en/lifetime-stats.html#/Lifetime_Stats/get_seasons_lifetime_gameMode__gameMode__players)
     *
     * @throws IllegalArgumentException when [playerNames] is greater than 10
     */
    suspend fun getLifetimeStats(playerNames: List<String>, gameMode: GameMode): Result<List<Stats>> {
        require(playerNames.size <= 10) { "10 players max" }
        return coroutineScope {
            runCatching {
                val players = getPlayersByNames(playerNames).getOrThrow()
                val lifetime: LifetimeResponse = client.getLifetimeStats(players.map { it.id }, gameMode.id).body()
                lifetime.data.map { it.attributes.gameModeStats[gameMode.id]!! }
            }
        }
    }
}
