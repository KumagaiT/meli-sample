package com.kumagai.melisample.presentation

import androidx.lifecycle.ViewModel
import com.kumagai.melisample.domain.use_case.SearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    fun searchItems() {
        CoroutineScope(Dispatchers.IO).launch {
            searchUseCase.invoke("Motorola")
        }
    }
}