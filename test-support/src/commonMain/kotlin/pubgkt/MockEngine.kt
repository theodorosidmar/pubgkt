package pubgkt

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData

/**
 * Creates a [MockEngine] that returns response created by [MockResponse.Builder] for every request.
 */
fun mockEngine(builder: MockResponse.Builder.() -> Unit): MockEngine {
    val response = MockResponse.Builder().apply(builder).build()
    return MockEngine {
        respond(
            content = response.body,
            status = response.status,
            headers = response.headers,
        )
    }
}

/**
 * Creates a [MockEngine] that consumes one [MockResponse] per request, in order.
 *
 * Throws if more requests are made than responses provided.
 */
fun mockEngine(responses: List<MockResponse>): MockEngine =
    if (responses.isEmpty()) {
        mockEngine {}
    } else {
        MockEngine.Queue().apply {
            responses.forEach { response ->
                enqueue {
                    respond(
                        content = response.body,
                        status = response.status,
                        headers = response.headers,
                    )
                }
            }
        }
    }

fun mockEngine(vararg responses: MockResponse): MockEngine = mockEngine(responses.toList())

val MockEngine.lastRequest: HttpRequestData
    get() = requestHistory.last()
