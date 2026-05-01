package pubgkt.leaderboards

/**
 * Leaderboard game mode
 */
public enum class GameMode {
    SOLO,
    SOLO_FPP,
    DUO,
    DUO_FPP,
    SQUAD,
    SQUAD_FPP;

    internal val path: String =
        name
            .lowercase()
            .replace(oldValue = "_", newValue = "-")

    internal companion object {
        fun of(value: String): GameMode =
            valueOf(value.uppercase().replace(oldValue = "-", newValue = "_"))
    }
}
