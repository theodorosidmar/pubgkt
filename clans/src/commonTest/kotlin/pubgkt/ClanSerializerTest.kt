package pubgkt

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ClanSerializerTest {

    @Test
    fun `maps all fields correctly`() {
        val element = Json.parseToJsonElement(CLAN_RESOURCE_JSON)
        val clan = json.decodeFromJsonElement(ClanSerializer, element)

        assertEquals("clan.d52aad6adcfb4c4783a85eb250f6e822", clan.id)
        assertEquals("SPARKINGG", clan.name)
        assertEquals("KING", clan.tag)
        assertEquals(20, clan.level)
        assertEquals(100, clan.memberCount)
    }
}
