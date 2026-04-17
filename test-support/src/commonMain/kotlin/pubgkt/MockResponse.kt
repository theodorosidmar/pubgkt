package pubgkt

import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import io.ktor.http.headersOf

fun mockResponse(builder: MockResponse.Builder.() -> Unit): MockResponse =
    MockResponse.Builder().apply(builder).build()

class MockResponse private constructor(
    val body: String = "",
    val status: HttpStatusCode = HttpStatusCode.OK,
    val headers: Headers = Headers.Empty,
) {
    data class Builder(
        var body: String = "",
        var status: HttpStatusCode = HttpStatusCode.OK,
        var headers: Headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
    ) {
        var reset: Long? = null
            set(value) {
                headers("X-RateLimit-Reset", value.toString())
                field = value
            }

        var remaining: Int? = null
            set(value) {
                headers("X-RateLimit-Remaining", value.toString())
                field = value
            }

        var limit: Int? = null
            set(value) {
                headers("X-RateLimit-Limit", value.toString())
                field = value
            }

        private fun headers(key: String, value: String) {
            this.headers = headers {
                appendAll(this@Builder.headers)
                append(key, value)
            }
        }

        internal fun build(): MockResponse = MockResponse(
            body = body,
            status = status,
            headers = headers,
        )
    }
}
