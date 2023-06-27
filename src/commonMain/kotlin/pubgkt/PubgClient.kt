package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

internal typealias PubgClient = HttpClient

internal suspend fun PubgClient.getLifetimeStats(player: Player, gameMode: GameMode): HttpResponse =
    get("seasons/lifetime/gameMode/${gameMode.id}/players") {
        url {
            parameters.append(FILTER_PLAYER_IDS, player.id)
        }
    }

internal suspend fun PubgClient.getLifetimeStats(players: List<Player>, gameMode: GameMode): HttpResponse =
    get("seasons/lifetime/gameMode/${gameMode.id}/players") {
        url {
            parameters.append(
                FILTER_PLAYER_IDS,
                players.joinToString(separator = ",") { it.id },
            )
        }
    }

internal suspend fun PubgClient.getPlayerByName(name: String): HttpResponse =
    get(PLAYERS_PATH) {
        url {
            parameters.append(FILTER_PLAYER_NAMES, name)
        }
    }

internal suspend fun PubgClient.getPlayersByName(names: List<String>): HttpResponse =
    get(PLAYERS_PATH) {
        url {
            parameters.append(FILTER_PLAYER_NAMES, names.joinToString(separator = ","))
        }
    }

internal suspend fun PubgClient.getPlayersByIds(ids: List<String>): HttpResponse =
    get(PLAYERS_PATH) {
        url {
            parameters.append(FILTER_PLAYER_IDS, ids.joinToString(separator = ","))
        }
    }
