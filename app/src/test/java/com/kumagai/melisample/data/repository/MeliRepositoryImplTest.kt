package com.kumagai.melisample.data.repository

import com.kumagai.melisample.data.model.Currency
import com.kumagai.melisample.data.model.ItemDto
import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.data.model.ShippingDto
import com.kumagai.melisample.data.remote.MeliApi
import com.kumagai.melisample.domain.repository.MeliRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MeliRepositoryImplTest {

    private lateinit var subject: MeliRepositoryImpl
    private lateinit var api: MeliApi

    private val mockData = SearchDto(
        listOf(
            ItemDto(
                "title",
                "thumbnail",
                Currency.BRL,
                "storeName",
                24.60,
                54.00,
                true,
                ShippingDto(true),
                listOf(),
                "permalink"
            )
        ),
        "query"
    )

    @Before
    fun setup() {
        subject = MeliRepositoryImpl(api)
    }


    @Test
    fun searchItems() {
        coEvery { api.search("query") } returns mockData

        runBlocking {
            val result = subject.searchItems("query")

            assertEquals(mockData, result)
        }
    }
}