package pubgkt

import kotlin.test.Test
import kotlin.test.assertEquals

class PubgContentTypeTest {
    @Test
    fun pubgContentType() {
        assertEquals("application", pubgContentType.contentType)
        assertEquals("vnd.api+json", pubgContentType.contentSubtype)
    }
}
