package com.kumagai.melisample.data.remote

import com.kumagai.melisample.data.model.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MeliApi {
    @GET("sites/MLB/search")
    suspend fun search(
        @Query("q") searchValue: String
    ) : SearchDto
}
