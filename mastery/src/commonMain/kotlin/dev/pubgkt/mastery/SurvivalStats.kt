package dev.pubgkt.mastery

import kotlin.js.JsExport

@JsExport
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
