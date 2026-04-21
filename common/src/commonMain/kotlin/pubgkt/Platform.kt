package pubgkt

/**
 * PUBG Platform shard
 * @see <a href="https://documentation.pubg.com/en/making-requests.html#platforms-and-regions">PUBG Developer Portal - Platforms & Regions</a>
 */
public enum class Platform {
    CONSOLE,
    KAKAO,
    PSN,
    STADIA,
    STEAM,
    TOURNAMENT,
    XBOX;

    internal val path: String = name.lowercase()
}
