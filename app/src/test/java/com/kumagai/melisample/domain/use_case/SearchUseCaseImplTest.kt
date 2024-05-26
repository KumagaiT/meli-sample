package com.kumagai.melisample.domain.use_case

import com.kumagai.melisample.data.model.Currency
import com.kumagai.melisample.data.model.ItemDto
import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.data.model.ShippingDto
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.model.SearchResultItem
import com.kumagai.melisample.domain.repository.MeliRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.jvm.Throws

class SearchUseCaseImplTest {

    private lateinit var subject: SearchUseCase
    private lateinit var repository: MeliRepository

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

    private val mappedMockData = SearchResult(
        "query",
        listOf(
            SearchResultItem(
                "title",
                "thumbnail",
                "storeName",
                "R$ 24,60",
                "R$ 54,00",
                true,
                true,
                "permalink",
                listOf()
            )
        )
    )

    @Before
    fun setUp() {
        repository = mockk()
        subject = SearchUseCaseImpl(repository)
    }

    @Test
    fun `on success API response should return correct value`(): Unit = runBlocking {
        coEvery { repository.searchItems(any()) } returns mockData

        val actual = subject.invoke("query")

        assertEquals(actual, Result.success(mappedMockData))
    }

    @Test(expected = Throwable::class)
    fun `on failed API response should return correct value`() : Unit = runBlocking {
        val exception = Throwable()
        coEvery { repository.searchItems(any()) } throws exception

        val actual = subject.invoke("query")

        assertEquals(actual, Result.failure<Throwable>(exception))
    }
}