@file:Suppress("MatchingDeclarationName")

package pubgkt

import kotlin.RequiresOptIn.Level.ERROR
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR
import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.TYPEALIAS

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
