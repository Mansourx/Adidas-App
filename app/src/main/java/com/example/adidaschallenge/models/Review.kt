package com.example.adidaschallenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Ahmad Mansour on 5/5/21
 * NAMSHI General Trading,
 * Dubai, UAE.
 */

@Parcelize
data class Review(
    @SerializedName("productId") val productId: String? = null,
    @SerializedName("locale") val locale: String? = null,
    @SerializedName("rating") val rating: Int? = null,
    @SerializedName("text") val text: String? = null
) : Parcelable