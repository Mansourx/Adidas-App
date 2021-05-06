package com.example.adidaschallenge.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ahmad Mansour on 5/5/21
 * Dubai, UAE.
 */

@Parcelize
data class Product(
    @SerializedName("currency") val currency: String? = null,
    @SerializedName("price") val price: Int? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imgUrl") val imgUrl: String? = null,
    @SerializedName("reviews") val reviews: MutableList<Review>? = null
) : Parcelable