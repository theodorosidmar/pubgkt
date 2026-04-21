package pubgkt

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

/**
 * Performs an HTTP GET request to [urlString] applying only [policy].
 *
 * This overload does not prepend any shard segment. Use it for already-qualified
 * paths (for example, when the caller builds `shards/{...}/...`) or absolute URLs.
 *
 * @param urlString Relative or absolute URL/path to request.
 * @param policy Per-request behavior flags (authentication/rate-limit handling).
 * @return The received [HttpResponse].
 */
@PubgktInternal
public suspend fun HttpClient.get(
    urlString: String,
    policy: RequestPolicy,
): HttpResponse =
    get(urlString) {
        requestPolicy = policy
    }

/**
  * Performs an HTTP GET request to [urlString] using a [Platform] shard.
  *
  * The request path is rewritten to:
  * `shards/{platform.path}/{urlString}`
  *
  * Prefer this overload for endpoints that are platform-scoped (players, matches,
  * clans, mastery, etc.).
  *
  * @param urlString Endpoint-relative path (for example, `players/{accountId}`).
  * @param platform Platform shard used when building the final request path.
  * @param policy Per-request behavior flags. Defaults to [DefaultRequestPolicy].
  * @return The received [HttpResponse].
  */
@PubgktInternal
public suspend fun HttpClient.get(
    urlString: String,
    platform: Platform = Platform.STEAM,
    policy: RequestPolicy = DefaultRequestPolicy,
): HttpResponse =
    get {
        url("shards/${platform.path}/$urlString")
        requestPolicy = policy
    }

/**
 * Performs an HTTP GET request to [urlString] using a [Platform] shard,
 * and applies additional request customization via [block].
 *
 * The request path is rewritten to:
 * `shards/{platform.path}/{urlString}`
 *
 * This overload is useful when callers need to add query parameters, headers,
 * or other request-level options while still using platform-scoped routing.
 *
 * @param urlString Endpoint-relative path to request.
 * @param platform Platform shard used when building the final request path.
 * @param policy Per-request behavior flags. Defaults to [DefaultRequestPolicy].
 * @param block Additional request customization applied to the [HttpRequestBuilder].
 * @return The received [HttpResponse].
 */
@PubgktInternal
public suspend fun HttpClient.get(
    urlString: String,
    platform: Platform = Platform.STEAM,
    policy: RequestPolicy = DefaultRequestPolicy,
    block: HttpRequestBuilder.() -> Unit = {}
): HttpResponse =
    get {
        url("shards/${platform.path}/$urlString")
        requestPolicy = policy
        block()
    }

/**
 * Performs an HTTP GET request to [urlString] using a [PlatformRegion] shard.
 *
 * The request path is rewritten to:
 * `shards/{platformRegion.path}/{urlString}`
 *
 * Prefer this overload for endpoints that are region-scoped.
 *
 * @param urlString Endpoint-relative path to request.
 * @param platformRegion Platform-region shard used when building the final request path.
 * @param policy Per-request behavior flags. Defaults to [DefaultRequestPolicy].
 * @return The received [HttpResponse].
 */
@PubgktInternal
public suspend fun HttpClient.get(
    urlString: String,
    platformRegion: PlatformRegion = PlatformRegion.PC_SA,
    policy: RequestPolicy = DefaultRequestPolicy,
): HttpResponse =
    get(urlString) {
        url("shards/${platformRegion.path}/$urlString")
        requestPolicy = policy
    }
