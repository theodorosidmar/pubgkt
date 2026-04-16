package pubgkt

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetClanByIdTest {
    private val engine = mockEngine {
        body = CLAN_RESPONSE_JSON
    }
    private val api = PubgApi(engine = engine)

    @Test
    fun `sends request to the correct path`() = runTest {
        api.getClanById("clan.d52aad6adcfb4c4783a85eb250f6e822")

        assertTrue(
            engine.lastRequest.url.encodedPath.endsWith("clans/clan.d52aad6adcfb4c4783a85eb250f6e822"),
        )
    }

    @Test
    fun `deserializes single-resource response`() = runTest {
        val clan = api.getClanById("clans/clan.d52aad6adcfb4c4783a85eb250f6e822")

        assertEquals("clan.d52aad6adcfb4c4783a85eb250f6e822", clan.id)
        assertEquals("SPARKINGG", clan.name)
        assertEquals("KING", clan.tag)
        assertEquals(20, clan.level)
        assertEquals(100, clan.memberCount)
    }
}
