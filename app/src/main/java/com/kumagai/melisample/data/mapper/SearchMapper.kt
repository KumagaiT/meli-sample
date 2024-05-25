package com.kumagai.melisample.data.mapper

import com.kumagai.melisample.core.toMonetary
import com.kumagai.melisample.data.model.AttributeDto
import com.kumagai.melisample.data.model.SearchDto
import com.kumagai.melisample.domain.model.ItemAttribute
import com.kumagai.melisample.domain.model.SearchResult
import com.kumagai.melisample.domain.model.SearchResultItem

fun SearchDto.toSearchResultData() = SearchResult(
    this.query,
    this.results.map { item ->
        SearchResultItem(
            item.title,
            item.thumbnailUrl,
            item.storeName,
            item.price.toMonetary(item.currencyId.locale),
            item.originalPrice?.toMonetary(item.currencyId.locale),
            item.isMercadoPagoAccepted,
            item.shippingInfo.isFreeShippingAvailable,
            item.permalink,
            item.attributeList.toItemAttributeList()
        )
    })

fun List<AttributeDto>.toItemAttributeList(): List<ItemAttribute> = this.map {
    ItemAttribute(it.name, it.valueName)
}
