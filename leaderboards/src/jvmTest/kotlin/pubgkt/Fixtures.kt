package pubgkt

internal actual val LEADERBOARD_RESPONSE_JSON: String =
    {}::class.java
        .getResourceAsStream("/leaderboard_response.json")
        ?.bufferedReader(Charsets.UTF_8)
        ?.use { it.readText() }
        ?: error("Missing leaderboard.json")
