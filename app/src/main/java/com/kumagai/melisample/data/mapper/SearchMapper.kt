package com.kumagai.melisample.data.mapper

import com.kumagai.melisample.core.Mapper
import com.kumagai.melisample.core.toMonetary
import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.model.SearchResultItem

object SearchMapper : Mapper<SearchDto, SearchResult> {
    override fun mapFrom(from: SearchDto): SearchResult {
        return SearchResult(from.results.map { item ->
            SearchResultItem(
                item.title,
                item.thumbnailUrl,
                item.storeName,
                item.price.toMonetary(item.currencyId.locale),
                item.originalPrice?.toMonetary(item.currencyId.locale),
                item.isMercadoPagoAccepted
            )
        })
    }
}