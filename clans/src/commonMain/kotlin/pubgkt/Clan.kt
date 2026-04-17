package pubgkt

public data class Clan(
    /**
     * Clan id
     */
    val id: String,

    /**
     * Clan name (e.g: SPARKINGG)
     */
    val name: String,

    /**
     * Clan tag (e.g: KING)
     */
    val tag: String,

    /**
     * Clan level (e.g: 20)
     */
    val level: Int,

    /**
     * Clan member count (e.g: 100)
     */
    val memberCount: Int,
)
