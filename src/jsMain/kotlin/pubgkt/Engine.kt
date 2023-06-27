package pubgkt

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.Js

internal actual val engine: HttpClientEngineFactory<*> = Js
