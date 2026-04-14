package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class JsonApiResourceDeserializerTest {

    /** Minimal stub that exposes the raw fields the base class extracts. */
    private data class Stub(
        val id: String,
        val attrs: JsonObject,
        val rels: JsonObject?,
    )

    private object StubDeserializer : JsonApiResourceDeserializer<Stub>("test.Stub") {
        override fun deserializeResource(
            attributes: JsonObject,
            id: String,
            relationships: JsonObject?,
        ): Stub = Stub(id = id, attrs = attributes, rels = relationships)
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
    fun `relationships is null when absent`() {
        val stub = deserialize(RESOURCE_NO_RELATIONSHIPS_JSON)
        assertNull(stub.rels)
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
}

private const val RESOURCE_JSON = """
{
  "type": "thing",
  "id": "abc-123",
  "attributes": { "field": "hello" },
  "relationships": { "rel": "value" }
}
"""

private const val RESOURCE_NO_RELATIONSHIPS_JSON = """
{
  "type": "thing",
  "id": "abc-123",
  "attributes": { "field": "hello" }
}
"""

private const val RESOURCE_MISSING_ID_JSON = """
{
  "type": "thing",
  "attributes": { "field": "hello" }
}
"""

private const val RESOURCE_MISSING_ATTRIBUTES_JSON = """
{
  "type": "thing",
  "id": "abc-123"
}
"""
