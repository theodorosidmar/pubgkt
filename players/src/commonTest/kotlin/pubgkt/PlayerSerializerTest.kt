package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class PlayerSerializerTest {

    private fun deserialize(resourceJson: String): Player {
        val resource = Json.parseToJsonElement(resourceJson).jsonObject
        return PlayerSerializer.fromResource(resource)
    }

    @Test
    fun `missing optional fields default to null`() {
        val player = deserialize(PLAYER_RESOURCE_MISSING_OPTIONALS_JSON)

        assertNull(player.clanId)
        assertNull(player.patchVersion)
    }

    @Test
    fun `missing relationships yields empty matches`() {
        val player = deserialize(PLAYER_RESOURCE_NO_RELATIONSHIPS_JSON)

        assertEquals(emptyList(), player.matches)
    }

    @Test
    fun `empty matches array yields empty list`() {
        val player = deserialize(PLAYER_RESOURCE_EMPTY_MATCHES_JSON)

        assertEquals(emptyList(), player.matches)
    }

    @Test
    fun `unknown banType throws SerializationException`() {
        assertFailsWith<SerializationException> {
            deserialize(PLAYER_RESOURCE_UNKNOWN_BAN_TYPE_JSON)
        }
    }

    @Test
    fun `banType matching is case-insensitive`() {
        val player = deserialize(PLAYER_RESOURCE_LOWERCASE_BAN_TYPE_JSON)

        assertEquals(BanType.PermanentBan, player.banType)
    }
}
