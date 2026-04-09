package pubgkt

import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.bearerAuth
import io.ktor.http.path
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * Class to interact with the PUBG API.
 * @param apiKey Your PUBG API key.
 * @property platform The [Platform] you want to use
 */
public class PubgApi(private val apiKey: String) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    public var platform: Platform = Platform.STEAM
        set(value) {
            client.config {
                defaultRequest(replace = false) {
                    url {
                        path(PATH, value.name.lowercase())
                    }
                }
            }
            field = value
        }

    init {
        client.config {
            defaultRequest(replace = false) {
                bearerAuth(apiKey)
            }
        }
    }
}
