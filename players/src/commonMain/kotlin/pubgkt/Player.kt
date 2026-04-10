package pubgkt

/**
 * Represents a PUBG player.
 *
 * @property accountId The player's unique account ID (e.g. `"account.abc123"`).
 * @property name The player's in-game display name.
 * @property patchVersion The game patch version active when the player's data was last recorded,
 *   or `null` if not provided by the API.
 * @property titleId The game title identifier (e.g. `"bluehole-pubg"`),
 *   or `null` if not provided by the API.
 * @property matchIds IDs of the player's recent matches. Use these with the matches
 *   endpoint to fetch full match data.
 * @see <a href="https://documentation.pubg.com/en/players-endpoint.html">PUBG Developer Portal – Players</a>
 */
public data class Player(
    public val accountId: String,
    public val name: String,
    public val patchVersion: String?,
    public val titleId: String?,
    public val matchIds: List<String>,
)
