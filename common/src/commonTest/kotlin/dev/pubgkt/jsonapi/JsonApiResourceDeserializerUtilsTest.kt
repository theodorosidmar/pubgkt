package dev.pubgkt.jsonapi

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class JsonApiResourceDeserializerUtilsTest {
    @Test
    fun `requireId throws when id is null`() {
        assertFailsWith<SerializationException> {
            requireId(null)
        }
    }

    @Test
    fun `requireId allows using id as non-null after call`() {
        val id: String? = "a"

        requireId(id)

        val nonNullId: String = id
        assertEquals("a", nonNullId)
    }

    @Test
    fun `requireRelationships throws when relationships is null`() {
        assertFailsWith<SerializationException> {
            requireRelationships(null)
        }
    }

    @Test
    fun `requireRelationships allows using relationships as non-null after call`() {
        val relationships =
            buildJsonObject {
                put("player", buildJsonObject { })
            }

        val nullableRelationships: JsonObject? = relationships

        requireRelationships(nullableRelationships)

        val nonNullRelationships: JsonObject = nullableRelationships
        assertEquals(relationships, nonNullRelationships)
    }

    @Test
    fun `requireIncluded throws when included is null`() {
        assertFailsWith<SerializationException> {
            requireIncluded(null)
        }
    }

    @Test
    fun `requireIncluded allows using included as non-null after call`() {
        val included =
            buildJsonArray {
                add(buildJsonObject { })
            }

        val nullableIncluded: JsonArray? = included

        requireIncluded(nullableIncluded)

        val nonNullIncluded: JsonArray = nullableIncluded
        assertEquals(included, nonNullIncluded)
    }
}
