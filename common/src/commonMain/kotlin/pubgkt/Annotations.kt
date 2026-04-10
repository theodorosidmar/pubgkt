package pubgkt

import kotlin.RequiresOptIn.Level.ERROR
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.*

/**
 * Marks an API for internal use only.
 */
@MustBeDocumented
@Retention(BINARY)
@Target(CLASS, CONSTRUCTOR, PROPERTY, FUNCTION, TYPEALIAS)
@RequiresOptIn(
    message = "This API is intended for internal use only.",
    level = ERROR,
)
public annotation class PubgktInternal
