package pubgkt

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode

data class MockResponse(
    val body: String = "",
    val status: HttpStatusCode = HttpStatusCode.OK,
    val headers: Headers = Headers.Empty,
    val remaining: Int? = null,
    val reset: Long? = null,
)
