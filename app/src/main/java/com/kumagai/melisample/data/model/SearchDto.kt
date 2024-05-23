package com.kumagai.melisample.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Locale

data class SearchDto(
    @SerializedName("results") val results: List<ItemDto>
) : Serializable

data class ItemDto(
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnailUrl: String,
    @SerializedName("currency_id") val currencyId: Currency,
    @SerializedName("official_store_name") val storeName: String?,
    @SerializedName("price") val price: Double,
    @SerializedName("original_price") val originalPrice: Double?,
    @SerializedName("accepts_mercadopago") val isMercadoPagoAccepted: Boolean,
    @SerializedName("shipping") val shippingInfo: ShippingDto,
    @SerializedName("attributes") val attributeList: List<AttributeDto>,
    @SerializedName("permalink") val permalink: String
) : Serializable

data class AttributeDto(
    @SerializedName("name") val name: String,
    @SerializedName("value_name") val valueName: String,
)

data class ShippingDto(
    @SerializedName("free_shipping") val isFreeShippingAvailable: Boolean
): Serializable

enum class Currency(val locale: Locale) {
    BRL(Locale("pt", "BR")),
    USD(Locale("en", "US")),
    ARS(Locale("es", "AR"))
}
