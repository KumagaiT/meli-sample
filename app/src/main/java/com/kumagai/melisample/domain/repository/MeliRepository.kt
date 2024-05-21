package com.kumagai.melisample.domain.repository

import com.kumagai.melisample.data.model.SearchDto

interface MeliRepository {
    suspend fun searchItems(query: String) : SearchDto
}