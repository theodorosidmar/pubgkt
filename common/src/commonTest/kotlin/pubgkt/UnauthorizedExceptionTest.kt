package pubgkt

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlinx.coroutines.test.runTest

class UnauthorizedExceptionTest {

    @Test
    fun `throws UnauthorizedException on HTTP 401`() = runTest {
        val api = PubgApi(
            engine = mockEngine {
                body = ""
                status = HttpStatusCode.Unauthorized
            },
            apiKey = "",
        )

        assertFailsWith<UnauthorizedException> {
            api.client.get("test")
        }
    }
}
