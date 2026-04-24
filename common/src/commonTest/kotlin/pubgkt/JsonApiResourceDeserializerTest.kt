package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject

class JsonApiResourceDeserializerTest {

    /** Minimal stub that exposes the raw fields the base class extracts. */
    private data class Stub(
        val id: String,
        val attrs: JsonObject,
        val rels: JsonObject?,
        val included: JsonArray?,
    )

    private object StubDeserializer : JsonApiResourceDeserializer<Stub>("test.Stub") {
        override fun deserializeResource(
            attributes: JsonObject,
            id: String,
            relationships: JsonObject?,
            included: JsonArray?,
        ): Stub = Stub(
            id = id,
            attrs = attributes,
            rels = relationships,
            included = included,
        )
    }

    private fun deserialize(resourceJson: String): Stub {
        val element = Json.parseToJsonElement(resourceJson)
        return json.decodeFromJsonElement(StubDeserializer, element)
    }

    @Test
    fun `extracts id from root`() {
        val stub = deserialize(RESOURCE_JSON)
        assertEquals("abc-123", stub.id)
    }

    @Test
    fun `extracts attributes as JsonObject`() {
        val stub = deserialize(RESOURCE_JSON)
        assertEquals("hello", stub.attrs.requiredString("field"))
    }

    @Test
    fun `extracts relationships when present`() {
        val stub = deserialize(RESOURCE_JSON)
        assertEquals("value", stub.rels?.requiredString("rel"))
    }

    @Test
    fun `extracts included as JsonArray`() {
        val stub = deserialize(RESOURCE_JSON)
        assertEquals("hello", stub.included?.first()?.jsonObject?.requiredString("field"))
    }

    @Test
    fun `relationships is null when absent`() {
        val stub = deserialize(RESOURCE_NO_RELATIONSHIPS_JSON)
        assertNull(stub.rels)
    }

    @Test
    fun `included is null when absent`() {
        val stub = deserialize(RESOURCE_NO_INCLUDED_JSON)
        assertNull(stub.included)
    }

    @Test
    fun `throws when id is missing`() {
        assertFailsWith<SerializationException> {
            deserialize(RESOURCE_MISSING_ID_JSON)
        }
    }

    @Test
    fun `throws when attributes is missing`() {
        assertFailsWith<SerializationException> {
            deserialize(RESOURCE_MISSING_ATTRIBUTES_JSON)
        }
    }

    @Test
    fun `fromResource extracts fields from resource object`() {
        val resource = Json.parseToJsonElement(RESOURCE_OBJECT_JSON).jsonObject
        val included = Json.parseToJsonElement(INCLUDED_JSON) as JsonArray

        val stub = StubDeserializer.fromResource(resource, included)

        assertEquals("abc-123", stub.id)
        assertEquals("hello", stub.attrs.requiredString("field"))
        assertEquals("value", stub.rels?.requiredString("rel"))
        assertEquals("hello", stub.included?.first()?.jsonObject?.requiredString("field"))
    }

    @Test
    fun `fromResource passes null included when omitted`() {
        val resource = Json.parseToJsonElement(RESOURCE_OBJECT_JSON).jsonObject

        val stub = StubDeserializer.fromResource(resource)

        assertEquals("abc-123", stub.id)
        assertNull(stub.included)
    }
}

private const val RESOURCE_JSON = """
{
  "data": {
    "type": "thing",
    "id": "abc-123",
    "attributes": { "field": "hello" },
    "relationships": { "rel": "value" }
  },
  "included": [{ "field": "hello" }]
}
"""

private const val RESOURCE_NO_RELATIONSHIPS_JSON = """
{
  "data": {
    "type": "thing",
    "id": "abc-123",
    "attributes": { "field": "hello" }
  }
}
"""

private const val RESOURCE_NO_INCLUDED_JSON = """
{
  "data": {
    "type": "thing",
    "id": "abc-123",
    "attributes": { "field": "hello" },
    "relationships": { "rel": "value" }
  }
}
"""

private const val RESOURCE_MISSING_ID_JSON = """
{
  "data": {
    "type": "thing",
    "attributes": { "field": "hello" }
  }
}
"""

private const val RESOURCE_MISSING_ATTRIBUTES_JSON = """
{
  "data": {
    "type": "thing",
    "id": "abc-123"
  }
}
"""

private const val RESOURCE_OBJECT_JSON = """
{
  "type": "thing",
  "id": "abc-123",
  "attributes": { "field": "hello" },
  "relationships": { "rel": "value" }
}
"""

private const val INCLUDED_JSON = """
[{ "field": "hello" }]
"""
