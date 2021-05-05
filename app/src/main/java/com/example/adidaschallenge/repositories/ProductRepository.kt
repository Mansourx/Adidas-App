package com.example.adidaschallenge.repositories

import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.network.ResultWrapper


/**
 * Created by Ahmad Mansour on 5/6/21
 * Dubai, UAE.
 */


interface ProductRepository {

    suspend fun getProducts(url: String): ResultWrapper<List<Product>>
    suspend fun getProduct(url: String): ResultWrapper<Product?>
    suspend fun addReview(url: String, review: Review): ResultWrapper<Review>
    suspend fun getReviews(url: String): ResultWrapper<MutableList<Review>>

}