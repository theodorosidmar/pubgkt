package pubgkt

public data class SurvivalMastery(
    /**
     * ID of the player owner of the mastery
     */
    val playerId: String,

    /**
     * Survival mastery experience points
     */
    val xp: Int,

    /**
     * Survival mastery tier
     */
    val tier: Int,

    /**
     * Survival mastery level
     */
    val level: Int,

    /**
     * Total number of matches played that count toward Survival Mastery
     */
    val totalMatchesPlayed: Int,

    /**
     * Total number of air drops called
     */
    val airDropsCalled: SurvivalStats,

    /**
     * Total number of damage dealt
     */
    val damageDealt: SurvivalStats,

    /**
     * Total number of damage taken
     */
    val damageTaken: SurvivalStats,

    /**
     * Total number of distance by swimming
     */
    val distanceBySwimming: SurvivalStats,

    /**
     * Total number of distance by vehicle
     */
    val distanceByVehicle: SurvivalStats,

    /**
     * Total number of distance on foot
     */
    val distanceOnFoot: SurvivalStats,

    /**
     * Total number of distance total
     */
    val distanceTotal: SurvivalStats,

    /**
     * Total number of healed
     */
    val healed: SurvivalStats,

    /**
     * Total number of hot drop landings
     */
    val hotDropLandings: Int,

    /**
     * Total number of enemy crates looted
     */
    val enemyCratesLooted: SurvivalStats,

    /**
     * Total number of unique items looted
     */
    val uniqueItemsLooted: SurvivalStats,

    /**
     * Total number of position
     */
    val position: SurvivalStats,

    /**
     * Total number of revives
     */
    val revived: SurvivalStats,

    /**
     * Total number of teammates revived
     */
    val teammatesRevived: SurvivalStats,

    /**
     * Total number of time survived
     */
    val timeSurvived: SurvivalStats,

    /**
     * Total number of throwables thrown
     */
    val throwablesThrown: SurvivalStats,

    /**
     * Total number of top 10s
     */
    val top10: Int,
)
