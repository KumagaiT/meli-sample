package com.kumagai.melisample.core

import java.text.NumberFormat
import java.util.Locale

fun String.formatWhitespacesToAscii() = this.replace(" ", "%20")

fun Double.toMonetary(locale: Locale) : String {
    val currency = NumberFormat.getCurrencyInstance(locale)
    return currency.format(this)
}
