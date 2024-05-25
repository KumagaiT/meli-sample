package com.kumagai.melisample.presentation.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kumagai.melisample.R
import com.kumagai.melisample.core.hideKeyboard

class SearchFragment : Fragment(R.layout.search_fragment) {

    private lateinit var etSearch: EditText

    private val searchViewModel : SearchViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etSearch = view.findViewById(R.id.et_search)

        onKeyListener()
    }

    private fun onKeyListener() {
        etSearch.setOnKeyListener { _, keyCode, event ->
            var isEventHandled = false
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                this.activity?.hideKeyboard()
                searchViewModel.searchItems(etSearch.text.toString())
                isEventHandled = true
            }

            return@setOnKeyListener isEventHandled
        }
    }
}