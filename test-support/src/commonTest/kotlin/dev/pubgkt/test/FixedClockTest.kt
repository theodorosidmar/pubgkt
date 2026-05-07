package dev.pubgkt.test

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Instant

class FixedClockTest {
    @Test
    fun `should return a fixed clock`() {
        val yesterday: Instant = Clock.System.now() - 1.days
        val yesterdayEpochSeconds = yesterday.epochSeconds
        val fixedClock: Clock = FixedClock(yesterdayEpochSeconds)
        assertEquals(
            Instant.fromEpochSeconds(yesterdayEpochSeconds),
            fixedClock.now(),
        )
    }
}
