package com.kumagai.melisample.domain.use_case

import com.kumagai.melisample.core.formatWhitespacesToAscii
import com.kumagai.melisample.data.mapper.toSearchResultData
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.repository.MeliRepository

class SearchUseCaseImpl(
    private val repository: MeliRepository
) : SearchUseCase {
    override suspend fun invoke(query: String) : Result<SearchResult> {
        return try {
            val response = repository.searchItems(query.formatWhitespacesToAscii())
            Result.success(response.toSearchResultData())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
