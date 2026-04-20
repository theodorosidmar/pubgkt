package pubgkt

internal actual val WEAPON_MASTERY_RESPONSE_JSON: String =
    {}::class.java
        .getResourceAsStream("/weapon_mastery_response.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing weapon_mastery_response.json")

internal actual val WEAPON_MASTERY_RESOURCE_JSON: String =
    object {}.javaClass
        .getResourceAsStream("/weapon_mastery_resource.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing weapon_mastery_resource.json")
