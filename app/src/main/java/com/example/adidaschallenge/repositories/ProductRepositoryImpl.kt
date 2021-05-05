package com.example.adidaschallenge.repositories

import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.network.Api
import com.example.adidaschallenge.network.ResultWrapper
import com.example.adidaschallenge.network.RetrofitService
import com.example.adidaschallenge.network.safeApiCall


/**
 * Created by Ahmad Mansour on 5/5/21
 * Dubai, UAE.
 */


class ProductRepositoryImpl : ProductRepository {

    private val productsApi: Api = RetrofitService.createService(Api::class.java)

    override suspend fun getProducts(url: String): ResultWrapper<List<Product>> {
        return safeApiCall {
            productsApi.getProductsListAsync(url)
        }
    }

    override suspend fun getProduct(url: String): ResultWrapper<Product?> {
        return safeApiCall {
            productsApi.getProductAsync(url)
        }
    }

    override suspend fun addReview(url: String, review: Review): ResultWrapper<Review> {
        return safeApiCall {
            productsApi.addProductReview(url, review)
        }
    }

    override suspend fun getReviews(url: String): ResultWrapper<MutableList<Review>> {
        return safeApiCall {
            productsApi.getProductReviews(url)
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