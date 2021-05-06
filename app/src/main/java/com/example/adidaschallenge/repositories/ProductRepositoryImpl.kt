package com.example.adidaschallenge.repositories

import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.network.api.Api
import com.example.adidaschallenge.network.ResultWrapper
import com.example.adidaschallenge.network.RetrofitService
import com.example.adidaschallenge.network.safeApiCall


/**
 * Created by Ahmad Mansour on 5/5/21
 * Dubai, UAE.
 */


class ProductRepositoryImpl : ProductRepository {

    private val apiR2: Api = RetrofitService.createService(Api::class.java)

    override suspend fun getProducts(url: String): ResultWrapper<List<Product>> {
        return safeApiCall {
            apiR2.getProductsListAsync(url)
        }
    }

    override suspend fun getProduct(url: String): ResultWrapper<Product?> {
        return safeApiCall {
            apiR2.getProductAsync(url)
        }
    }

    override suspend fun addReview(url: String, review: Review): ResultWrapper<Review> {
        return safeApiCall {
            apiR2.addProductReviewAsync(url, review)
        }
    }

    override suspend fun getReviews(url: String): ResultWrapper<MutableList<Review>> {
        return safeApiCall {
            apiR2.getProductReviewsAsync(url)
        }
    }

    companion object {
        private var newsRepositoryImpl: ProductRepositoryImpl? = null
        val instance: ProductRepositoryImpl?
            get() {
                if (newsRepositoryImpl == null) {
                    newsRepositoryImpl = ProductRepositoryImpl()
                }
                return newsRepositoryImpl
            }
    }
}