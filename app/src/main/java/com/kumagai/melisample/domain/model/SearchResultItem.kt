package com.kumagai.melisample.domain.model

import android.os.Parcel
import android.os.Parcelable

data class SearchResultItem(
    val title: String,
    val thumbnailUrl: String,
    val storeName: String?,
    val price: String,
    val originalPrice: String?,
    val isMercadoPagoAccepted: Boolean,
    val isFreeShippingAvailable: Boolean,
    val permalink: String,
    val attributeList: List<ItemAttribute>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString()?: "",
        parcel.readString(),
        parcel.readString()?: "",
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()?: "",
        parcel.createTypedArrayList(ItemAttribute) ?: listOf()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(thumbnailUrl)
        parcel.writeString(storeName)
        parcel.writeString(price)
        parcel.writeString(originalPrice)
        parcel.writeByte(if (isMercadoPagoAccepted) 1 else 0)
        parcel.writeByte(if (isFreeShippingAvailable) 1 else 0)
        parcel.writeString(permalink)
        parcel.writeTypedList(attributeList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResultItem> {
        override fun createFromParcel(parcel: Parcel): SearchResultItem {
            return SearchResultItem(parcel)
        }

        override fun newArray(size: Int): Array<SearchResultItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class ItemAttribute(val name: String, val value: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemAttribute> {
        override fun createFromParcel(parcel: Parcel): ItemAttribute {
            return ItemAttribute(parcel)
        }

        override fun newArray(size: Int): Array<ItemAttribute?> {
            return arrayOfNulls(size)
        }
    }
}
