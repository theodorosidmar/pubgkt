package pubgkt

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class PlayerSerializerTest {

    private fun deserialize(resourceJson: String): Player {
        val element = Json.parseToJsonElement(resourceJson)
        return json.decodeFromJsonElement(PlayerSerializer, element)
    }

    @Test
    fun `maps all fields correctly`() {
        val player = deserialize(PLAYER_RESOURCE_JSON)

        assertEquals("account.abc123", player.id)
        assertEquals("PlayerOne", player.name)
        assertEquals(BanType.Innocent, player.banType)
        assertEquals("clan-42", player.clanId)
        assertEquals("14.1", player.patchVersion)
        assertEquals("bluehole-pubg", player.titleId)
        assertEquals("steam", player.shardId)
        assertEquals(listOf("match-id-1", "match-id-2"), player.matches.map(Match::id))
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
