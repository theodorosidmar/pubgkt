package pubgkt

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respond
import io.ktor.http.Headers

/**
 * Creates a [MockEngine] that returns [response] for every request.
 */
// TODO(Remove duplication)
fun mockEngine(response: MockResponse): MockEngine = MockEngine {
    val headers = Headers.build {
        appendAll(response.headers)
        if (response.remaining != null) {
            append("X-RateLimit-Remaining", response.remaining.toString())
        }
        if (response.reset != null) {
            append("X-RateLimit-Reset", response.reset.toString())
        }
    }
    respond(
        content = response.body,
        status = response.status,
        headers = headers,
    )
}

/**
 * Creates a [MockEngine] that consumes one [MockResponse] per request, in order.
 *
 * Throws if more requests are made than responses provided.
 */
fun mockEngine(responses: List<MockResponse>): MockEngine = MockEngine(
    MockEngineConfig().apply {
        reuseHandlers = false
        responses.forEach { response ->
            addHandler {
                val headers = Headers.build {
                    appendAll(response.headers)
                    if (response.remaining != null) {
                        append("X-RateLimit-Remaining", response.remaining.toString())
                    }
                    if (response.reset != null) {
                        append("X-RateLimit-Reset", response.reset.toString())
                    }
                }
                respond(
                    content = response.body,
                    status = response.status,
                    headers = headers,
                )
            }
        }
    }
)
