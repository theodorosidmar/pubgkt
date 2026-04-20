package pubgkt

public data class WeaponSummary(
    /**
     * The total amount of XP earned for this weapon
     */
    val xpTotal: Int,

    /**
     * The current level of this weapon
     */
    val currentLevel: Int,

    /**
     * The current tier of this weapon
     */
    val currentTier: Int,

    /**
     * The weapon mastery stats for this weapon
     */
    val statsTotal: WeaponStatsTotal,

    /**
     * The weapon mastery stats for this weapon for games played in Official mode only
     */
    val officialStatsTotal: WeaponOfficialStatsTotal,

    /**
     * The weapon mastery stats for this weapon for games played in Official mode only
     */
    val competitiveStatsTotal: WeaponOfficialStatsTotal,
)
