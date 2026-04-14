package pubgkt

/**
 * Player objects contain information about a player and a list of their recent matches
 * (up to 14 days old). Note: player objects are specific to platform shards.
 */
public data class Player(
    /**
     * Player id
     */
    val id: String,

    /**
     * PUBG Ban type
     */
    val banType: BanType,

    /**
     * PUBG Clan id
     */
    val clanId: String?,

    /**
     * PUBG IGN
     */
    val name: String,

    // val stats: {}

    /**
     * Identifies the studio and game
     */
    val titleId: String,

    /**
     * PUBG Platform/Shard
     * @see Platform
     */
    val shardId: String,

    /**
     * Version of the game
     */
    val patchVersion: String?,

    /**
     * Player recent matches (up to 14 days old)
     */
    val matches: List<Match>,
)
