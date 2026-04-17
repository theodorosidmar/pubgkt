package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

@PubgktInternal
public suspend fun HttpClient.getWithPublicRequest(urlString: String): HttpResponse =
    get(urlString) {
        requestPolicy(PublicRequestPolicy)
    }
