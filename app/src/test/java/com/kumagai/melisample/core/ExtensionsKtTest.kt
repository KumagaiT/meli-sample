package com.kumagai.melisample.core

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class ExtensionsKtTest {

    @Test
    fun `check if query format extension works`() {
        val query = "unit test"

        assertEquals("unit%20test", query.formatWhitespacesToAscii())
    }

    @Test
    fun `check if monetization extension works`() {
        val locale = Locale("pt", "BR")

        assertEquals("R$\u00A010,00", 10.0.toMonetary(locale))
        assertEquals("R$\u00A010.000,00", 10000.0.toMonetary(locale))
        assertEquals("R$\u00A010,50", 10.5.toMonetary(locale))
    }
}