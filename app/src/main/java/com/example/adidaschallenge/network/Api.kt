package com.example.adidaschallenge.network

import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.models.Review
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url


/**
 * Created by Ahmad Mansour on 5/5/21
 * Dubai, UAE.
 */

interface Api {

    @GET
    suspend fun getProductsListAsync(@Url url: String): Response<List<Product>>

    @GET
    suspend fun getProductAsync(@Url url: String): Response<Product?>

    @POST
    suspend fun addProductReview(@Url url: String, @Body review: Review): Response<Review>

    @GET
    suspend fun getProductReviews(@Url url: String): Response<MutableList<Review>>

}