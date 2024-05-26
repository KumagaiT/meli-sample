package com.kumagai.melisample.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.model.SearchResultItem
import com.kumagai.melisample.domain.use_case.SearchUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import com.kumagai.melisample.R

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest() {

    private lateinit var subject: SearchViewModel
    private lateinit var useCase: SearchUseCase

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

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

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        useCase = mockk()
        subject = SearchViewModel(useCase, testCoroutineDispatcher)
    }

    @Test
    fun `when query is succeeded should emmit loading and success value`() {
        val observer = mockk<Observer<SearchState>>()
        val slot = slot<SearchState>()
        val list = arrayListOf<SearchState>()

        coEvery { useCase.invoke(any()) } returns Result.success(mappedMockData)

        subject.searchLiveData.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        subject.searchItems("query")

        assertEquals(list[0], SearchState.Loading)
        assertEquals(list[1], SearchState.Success("query", mappedMockData.results))
    }

    @Test
    fun `when query is succeeded with empty response should emmit loading and error value`() {
        val observer = mockk<Observer<SearchState>>()
        val slot = slot<SearchState>()
        val list = arrayListOf<SearchState>()

        coEvery { useCase.invoke(any()) } returns Result.success(mappedMockData.copy(results = listOf()))

        subject.searchLiveData.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        subject.searchItems("query")

        assertEquals(list[0], SearchState.Loading)
        assertEquals(
            list[1],
            SearchState.Error(R.string.empty_search_title, R.string.empty_search_subtitle)
        )
    }

    @Test(expected = Throwable::class)
    fun `when query is not succeeded should emmit loading and error value`() {
        val observer = mockk<Observer<SearchState>>()
        val slot = slot<SearchState>()
        val list = arrayListOf<SearchState>()

        coEvery { useCase.invoke(any()) } returns Result.failure(Throwable())

        subject.searchLiveData.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        subject.searchItems("query")

        assertEquals(list[0], SearchState.Loading)
        assertEquals(
            list[1],
            SearchState.Error(
                R.string.error_search_title,
                R.string.error_search_title,
                R.color.error_red
            )
        )
    }
}