package com.kumagai.melisample.data.repository

import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.data.remote.MeliApi
import com.kumagai.melisample.domain.repository.MeliRepository

class MeliRepositoryImpl(
    private val api: MeliApi
) : MeliRepository {
    override suspend fun searchItems(query: String) : SearchDto {
        return api.search(query)
    }
}