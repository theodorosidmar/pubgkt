package pubgkt

internal actual val MATCH_TDM_RESPONSE_JSON: String =
    {}::class.java
        .getResourceAsStream("/match_tdm_response.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing match_tdm_response.json")

