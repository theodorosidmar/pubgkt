package pubgkt

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class UnauthorizedExceptionTest {

    @Test
    fun `throws UnauthorizedException on HTTP 401`() = runTest {
        val api = PubgApi(
            engine = mockEngine {
                body = ""
                status = HttpStatusCode.Unauthorized
            },
        )

        assertFailsWith<UnauthorizedException> {
            api.client.get("test")
        }
    }
}
