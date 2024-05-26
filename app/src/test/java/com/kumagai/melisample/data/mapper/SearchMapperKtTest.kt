package com.kumagai.melisample.data.mapper

import com.kumagai.melisample.data.model.AttributeDto
import com.kumagai.melisample.data.model.Currency
import com.kumagai.melisample.data.model.ItemDto
import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.data.model.ShippingDto
import com.kumagai.melisample.domain.model.ItemAttribute
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.model.SearchResultItem
import org.junit.Assert.*

import org.junit.Test

class SearchMapperKtTest {

    @Test
    fun `check if search data is correctly mapped`() {
        val mockData = SearchDto(
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

        val expected = SearchResult(
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

        assertEquals(expected, mockData.toSearchResultData())
    }

    @Test
    fun `check if attribute list is correctly mapped`() {
        val mockData = listOf(
            AttributeDto("item1", "value1"),
            AttributeDto("item2", "value2"),
        )

        val expected = listOf(
            ItemAttribute("item1", "value1"),
            ItemAttribute("item2", "value2")
        )

        assertEquals(expected, mockData.toItemAttributeList())
    }
}