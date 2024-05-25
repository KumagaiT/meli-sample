package com.kumagai.melisample.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import java.text.NumberFormat
import java.util.Locale


fun String.formatWhitespacesToAscii() = this.replace(" ", "%20")

fun Double.toMonetary(locale: Locale) : String {
    val currency = NumberFormat.getCurrencyInstance(locale)
    return currency.format(this)
}

fun Activity.hideKeyboard() {
    val view = this.currentFocus

    if (view != null) {
        val manager = ContextCompat.getSystemService(this, InputMethodManager::class.java)
        manager?.hideSoftInputFromWindow(
                view.windowToken, 0
            )
    }
}