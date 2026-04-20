package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * Returns the nested [JsonObject] for the given [key], or throws
 * [SerializationException] if the key is missing or not an object.
 */
@PubgktInternal
public fun JsonObject.requiredObject(key: String): JsonObject =
    this[key] as? JsonObject
        ?: throw SerializationException("Missing object field '$key'")

/**
 * Returns the nested [JsonObject] for the given [key], or `null` when the key
 * is absent or the value is not an object.
 */
@PubgktInternal
public fun JsonObject.optionalObject(key: String): JsonObject? =
    this[key] as? JsonObject

/**
 * Returns the nested [JsonArray] for the given [key], or `null` when the key
 * is absent or the value is not an array.
 */
@PubgktInternal
public fun JsonObject.optionalArray(key: String): JsonArray? =
    this[key] as? JsonArray

/**
 * Returns the [String] value for the given [key], or throws
 * [SerializationException] if the key is missing or null.
 */
@PubgktInternal
public fun JsonObject.requiredString(key: String): String =
    optionalString(key) ?: throw SerializationException("Missing string field '$key'")

/**
 * Returns the [Int] value for the given [key], or throws
 * [SerializationException] if the key is missing or null.
 */
@PubgktInternal
public fun JsonObject.requiredInt(key: String): Int =
    optionalInt(key) ?: throw SerializationException("Missing int field '$key'")

/**
 * Returns the [Double] value for the given [key], or throws
 * [SerializationException] if the key is missing or null.
 */
@PubgktInternal
public fun JsonObject.requiredDouble(key: String): Double =
    optionalDouble(key) ?: throw SerializationException("Missing double field '$key'")

/**
 * Returns the [Boolean] value for the given [key], or throws
 * [SerializationException] if the key is missing or null.
 */
@PubgktInternal
public fun JsonObject.requiredBoolean(key: String): Boolean =
    optionalBoolean(key) ?: throw SerializationException("Missing boolean field '$key'")

/**
 * Returns the [String] value for the given [key], or `null` when the key
 * is absent, the value is [JsonNull], or not a primitive.
 */
@PubgktInternal
public fun JsonObject.optionalString(key: String): String? =
    (this[key] as? JsonPrimitive)
        ?.let { primitive ->
            if (primitive == JsonNull) null else primitive.content
        }

/**
 * Returns the [Int] value for the given [key], or `null` when the key
 * is absent, the value is [JsonNull], or not a primitive.
 */
@PubgktInternal
public fun JsonObject.optionalInt(key: String): Int? =
    (this[key] as? JsonPrimitive)
        ?.let { primitive ->
            if (primitive == JsonNull) null else primitive.content.toInt()
        }

/**
 * Returns the [Double] value for the given [key], or `null` when the key
 * is absent, the value is [JsonNull], or not a primitive.
 */
@PubgktInternal
public fun JsonObject.optionalDouble(key: String): Double? =
    (this[key] as? JsonPrimitive)
        ?.let { primitive ->
            if (primitive == JsonNull) null else primitive.content.toDouble()
        }

/**
 * Returns the [Boolean] value for the given [key], or `null` when the key
 * is absent, the value is [JsonNull], or not a primitive.
 */
@PubgktInternal
public fun JsonObject.optionalBoolean(key: String): Boolean? =
    (this[key] as? JsonPrimitive)
        ?.let { primitive ->
            if (primitive == JsonNull) null else primitive.content.toBoolean()
        }
