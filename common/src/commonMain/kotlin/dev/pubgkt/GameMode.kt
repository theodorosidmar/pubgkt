package dev.pubgkt

/**
 * PUBG Game Mode used for leaderboards and stats
 */
public enum class GameMode {
    SOLO,
    SOLO_FPP,
    DUO,
    DUO_FPP,
    SQUAD,
    SQUAD_FPP,
    ;

    @PubgktInternal
    public val path: String =
        name
            .lowercase()
            .replace(oldValue = "_", newValue = "-")

    @PubgktInternal
    public companion object {
        @PubgktInternal
        public fun of(value: String): GameMode = valueOf(value.uppercase().replace(oldValue = "-", newValue = "_"))
    }
}
