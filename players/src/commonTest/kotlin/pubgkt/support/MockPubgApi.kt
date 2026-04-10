package pubgkt.support

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import pubgkt.PubgApi

/**
 * Builds a [PubgApi] backed by a [MockEngine] that returns [responseJson] for every request.
 *
 * The captured requests are stored so tests can inspect URLs, headers, and query parameters.
 */
internal class MockPubgApi(
    responseJson: String,
    status: HttpStatusCode = HttpStatusCode.OK,
) {
    private val requests = mutableListOf<HttpRequestData>()

    val lastRequest: HttpRequestData
        get() = requests.last()

    val api: PubgApi = PubgApi(
        apiKey = "test-api-key",
        httpClient = HttpClient(
            MockEngine { request ->
                requests += request
                respond(
                    content = responseJson,
                    status = status,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString(),
                    ),
                )
            }
        ),
    )
}
