package pubgkt

public data class SurvivalStats(
    /**
     * Career total
     */
    val total: Double,

    /**
     * Career average
     */
    val average: Double,

    /**
     * Career best
     */
    val careerBest: Double,

    /**
     * Value in last match
     */
    val lastMatchValue: Double,
)
