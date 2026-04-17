package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class JsonObjectExtensionsTest {

    private val obj = buildJsonObject {
        put("name", JsonPrimitive("Alice"))
        put("nullable", JsonNull)
        put("nested", buildJsonObject { put("key", JsonPrimitive("value")) })
        put("items", buildJsonArray { add(JsonPrimitive(1)) })
        put("int", JsonPrimitive(1))
        put("boolean", JsonPrimitive(true))
        put("booleanString", JsonPrimitive("true"))
    }

    // ── requiredString ──────────────────────────────────────────────────

    @Test
    fun `requiredString returns value when present`() {
        assertEquals("Alice", obj.requiredString("name"))
    }

    @Test
    fun `requiredString throws when key is missing`() {
        assertFailsWith<SerializationException> {
            obj.requiredString("missing")
        }
    }

    @Test
    fun `requiredString throws when value is null`() {
        assertFailsWith<SerializationException> {
            obj.requiredString("nullable")
        }
    }

    // ── optionalString ──────────────────────────────────────────────────

    @Test
    fun `optionalString returns value when present`() {
        assertEquals("Alice", obj.optionalString("name"))
    }

    @Test
    fun `optionalString returns null when key is missing`() {
        assertNull(obj.optionalString("missing"))
    }

    @Test
    fun `optionalString returns null for JsonNull`() {
        assertNull(obj.optionalString("nullable"))
    }

    // ── requiredInt ──────────────────────────────────────────────────

    @Test
    fun `requiredInt returns value when present`() {
        assertEquals(1, obj.requiredInt("int"))
    }

    @Test
    fun `requiredInt throws when key is missing`() {
        assertFailsWith<SerializationException> {
            obj.requiredInt("missing")
        }
    }

    @Test
    fun `requiredInt throws when value is null`() {
        assertFailsWith<SerializationException> {
            obj.requiredInt("nullable")
        }
    }

    // ── optionalInt ──────────────────────────────────────────────────

    @Test
    fun `optionalInt returns value when present`() {
        assertEquals(1, obj.optionalInt("int"))
    }

    @Test
    fun `optionalInt returns null when key is missing`() {
        assertNull(obj.optionalInt("missing"))
    }

    @Test
    fun `optionalInt returns null for JsonNull`() {
        assertNull(obj.optionalInt("nullable"))
    }

    // ── requiredBoolean ──────────────────────────────────────────────────

    @Test
    fun `requiredBoolean returns value when present`() {
        assertEquals(true, obj.requiredBoolean("boolean"))
        assertEquals(true, obj.requiredBoolean("booleanString"))
    }

    @Test
    fun `requiredBoolean throws when key is missing`() {
        assertFailsWith<SerializationException> {
            obj.requiredBoolean("missing")
        }
    }

    @Test
    fun `requiredBoolean throws when value is null`() {
        assertFailsWith<SerializationException> {
            obj.requiredBoolean("nullable")
        }
    }

    // ── optionalBoolean ──────────────────────────────────────────────────

    @Test
    fun `optionalBoolean returns value when present`() {
        assertEquals(true, obj.optionalBoolean("boolean"))
        assertEquals(true, obj.optionalBoolean("booleanString"))
    }

    @Test
    fun `optionalBoolean returns null when key is missing`() {
        assertNull(obj.optionalBoolean("missing"))
    }

    @Test
    fun `optionalBoolean returns null for JsonNull`() {
        assertNull(obj.optionalBoolean("nullable"))
    }

    // ── requiredObject ──────────────────────────────────────────────────

    @Test
    fun `requiredObject returns object when present`() {
        val nested = obj.requiredObject("nested")
        assertEquals("value", nested.requiredString("key"))
    }

    @Test
    fun `requiredObject throws when key is missing`() {
        assertFailsWith<SerializationException> {
            obj.requiredObject("missing")
        }
    }

    @Test
    fun `requiredObject throws when value is not an object`() {
        assertFailsWith<SerializationException> {
            obj.requiredObject("name")
        }
    }

    // ── optionalObject ──────────────────────────────────────────────────

    @Test
    fun `optionalObject returns object when present`() {
        val nested = obj.optionalObject("nested")
        assertEquals("value", nested?.requiredString("key"))
    }

    @Test
    fun `optionalObject returns null when key is missing`() {
        assertNull(obj.optionalObject("missing"))
    }

    @Test
    fun `optionalObject returns null when value is not an object`() {
        assertNull(obj.optionalObject("name"))
    }

    // ── optionalArray ───────────────────────────────────────────────────

    @Test
    fun `optionalArray returns array when present`() {
        val items = obj.optionalArray("items")
        assertEquals(1, items?.size)
    }

    @Test
    fun `optionalArray returns null when key is missing`() {
        assertNull(obj.optionalArray("missing"))
    }

    @Test
    fun `optionalArray returns null when value is not an array`() {
        assertNull(obj.optionalArray("name"))
    }
}
