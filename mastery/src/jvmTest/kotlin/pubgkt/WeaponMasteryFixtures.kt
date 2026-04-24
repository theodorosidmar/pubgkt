package pubgkt

internal actual val WEAPON_MASTERY_RESPONSE_JSON: String =
    {}::class.java
        .getResourceAsStream("/weapon_mastery_response.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing weapon_mastery_response.json")
