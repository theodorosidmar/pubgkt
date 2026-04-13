package pubgkt

import kotlin.time.Clock
import kotlin.time.Instant

fun FixedClock(nowEpoch: Long): Clock = object : Clock {
    override fun now(): Instant =
        Instant.fromEpochSeconds(nowEpoch)
}
