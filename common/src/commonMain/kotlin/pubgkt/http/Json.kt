package pubgkt.http

import kotlinx.serialization.json.Json
import pubgkt.PubgktInternal

@PubgktInternal
public val json: Json by lazy {
    Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = true
        useArrayPolymorphism = false
    }
}
