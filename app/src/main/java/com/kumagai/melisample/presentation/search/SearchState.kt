package com.kumagai.melisample.presentation.search

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.kumagai.melisample.domain.model.SearchResultItem

sealed class SearchState {
    object Loading : SearchState()

    data class Success(
        val query: String,
        val data: List<SearchResultItem>
    ) : SearchState()

    data class Error(
        @StringRes val errorTitleId: Int,
        @StringRes val errorSubtitleId: Int,
        @ColorRes val errorTitleColorId: Int? = null,
    ) : SearchState()
}