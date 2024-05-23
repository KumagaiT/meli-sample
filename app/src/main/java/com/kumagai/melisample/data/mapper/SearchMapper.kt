package com.kumagai.melisample.data.mapper

import com.kumagai.melisample.core.toMonetary
import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.model.SearchResultItem

fun SearchDto.toSearchResultData() = SearchResult(this.results.map { item ->
    SearchResultItem(
        item.title,
        item.thumbnailUrl,
        item.storeName,
        item.price.toMonetary(item.currencyId.locale),
        item.originalPrice?.toMonetary(item.currencyId.locale),
        item.isMercadoPagoAccepted
    )
})
