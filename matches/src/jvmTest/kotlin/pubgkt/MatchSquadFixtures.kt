package pubgkt

internal actual val MATCH_SQUAD_RESPONSE_JSON: String =
    {}::class.java
        .getResourceAsStream("/match_squad_response.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing match_squad_response.json")
