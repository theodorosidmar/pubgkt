package pubgkt.http

import io.ktor.client.engine.HttpClientEngineFactory

internal expect val engine: HttpClientEngineFactory<*>
