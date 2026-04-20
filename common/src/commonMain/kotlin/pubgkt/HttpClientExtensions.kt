package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

/**
  * Performs an HTTP GET request to the given [urlString] with the specified [policy].
  *
  * @param urlString The URL to send the GET request to.
  * @param policy The [RequestPolicy] to apply to this request. Defaults to [DefaultRequestPolicy].
  * @return The [HttpResponse] received from the server.
  */
@PubgktInternal
public suspend fun HttpClient.get(
    urlString: String,
    policy: RequestPolicy = DefaultRequestPolicy,
): HttpResponse =
    get(urlString) {
        requestPolicy = policy
    }
