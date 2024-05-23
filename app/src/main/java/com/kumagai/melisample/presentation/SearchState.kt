package com.kumagai.melisample.presentation

import com.kumagai.melisample.domain.model.SearchResultItem

sealed class SearchState {
    object Loading : SearchState()
    object Empty : SearchState()

    data class Success(
        val data: List<SearchResultItem>
    ) : SearchState()

    object Error : SearchState()
}