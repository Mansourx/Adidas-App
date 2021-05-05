package com.example.adidaschallenge.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


/**
 * Created by Ahmad Mansour on 5/5/21
 * NAMSHI General Trading,
 * Dubai, UAE.
 */


data class Review(
    @SerializedName("productId") val productId: String? = null,
    @SerializedName("locale") val locale: String? = null,
    @SerializedName("rating") val rating: Int? = null,
    @SerializedName("text") val text: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(locale)
        parcel.writeValue(rating)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }
}
