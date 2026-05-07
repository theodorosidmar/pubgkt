package dev.pubgkt.http

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

internal actual val engine: HttpClientEngineFactory<*> = Android
