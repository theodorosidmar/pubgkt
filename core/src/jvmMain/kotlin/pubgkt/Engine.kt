package pubgkt

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

internal actual val engine: HttpClientEngineFactory<*> = CIO
