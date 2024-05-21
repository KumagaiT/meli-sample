package com.kumagai.melisample.domain.model

data class SearchResultItem(
    val title: String,
    val thumbnailUrl: String,
    val storeName: String?,
    val price: String,
    val originalPrice: String?,
    val isMercadoPagoAccepted: Boolean
)