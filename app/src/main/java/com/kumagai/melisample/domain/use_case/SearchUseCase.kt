package com.kumagai.melisample.domain.use_case

import com.kumagai.melisample.domain.model.SearchResult

interface SearchUseCase {
    suspend fun invoke(query: String) : Result<SearchResult>
}
