package com.example.adidaschallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.network.ResultWrapper
import com.example.adidaschallenge.network.UIState
import com.example.adidaschallenge.repositories.ProductRepositoryImpl
import kotlinx.coroutines.launch
import java.net.HttpURLConnection


/**
 * Created by Ahmad Mansour on 5/5/21
 * Dubai, UAE.
 */


class ProductsViewModel : BaseViewModel() {

    private val productRepositoryImpl: ProductRepositoryImpl

    private var productData: MutableLiveData<Product> = MutableLiveData()
    private var productsDetails: MutableLiveData<List<Product>> = MutableLiveData()

    private var reviewDetails: MutableLiveData<Review> = MutableLiveData()
    private var productReviews: MutableLiveData<MutableList<Review>> = MutableLiveData()

    init {
        productRepositoryImpl = ProductRepositoryImpl.instance!!
    }

    fun getAllProducts() = viewModelScope.launch {
        when (val response = productRepositoryImpl.getProducts(PRODUCT_URL)) {
            is ResultWrapper.Success -> {
                productsDetails.postValue(response.value)
                uiState.postValue(UIState.Completed)
            }
            is ResultWrapper.GenericError -> handleGenericError(response.code, response.error)
            is ResultWrapper.NetworkError -> uiState.postValue(UIState.NetworkError)
        }
    }

    fun getProduct(id: String) = viewModelScope.launch {
        when (val response = productRepositoryImpl.getProduct("$PRODUCT_URL$id")) {
            is ResultWrapper.Success -> {
                productData.postValue(response.value)
                uiState.postValue(UIState.Completed)
            }
            is ResultWrapper.GenericError -> handleGenericError(response.code, response.error)
            is ResultWrapper.NetworkError -> uiState.postValue(UIState.NetworkError)
        }
    }

    fun addReview(id: String, review: Review) = viewModelScope.launch {
        when (val response = productRepositoryImpl.addReview("$REVIEWS_URL$id", review)) {
            is ResultWrapper.Success -> {
                reviewDetails.postValue(response.value)
                uiState.postValue(UIState.Completed)
            }
            is ResultWrapper.GenericError -> handleGenericError(response.code, response.error)
            is ResultWrapper.NetworkError -> uiState.postValue(UIState.NetworkError)
        }
    }

    fun getReviews(id: String) = viewModelScope.launch {
        when (val reviews = productRepositoryImpl.getReviews("$REVIEWS_URL$id")) {
            is ResultWrapper.Success -> {
                productReviews.postValue(reviews.value)
                uiState.postValue(UIState.Completed)
            }
            is ResultWrapper.GenericError -> handleGenericError(reviews.code, reviews.error)
            is ResultWrapper.NetworkError -> uiState.postValue(UIState.NetworkError)
        }
    }

    private fun handleGenericError(code: Int?, error: String?) {
        when (code) {
            HttpURLConnection.HTTP_FORBIDDEN -> Unit
            HttpURLConnection.HTTP_BAD_REQUEST -> {
                val errorMessage = error ?: ""
                uiState.postValue(UIState.GenericError(errorMessage))
            }
            else -> uiState.postValue(UIState.GenericError(""))
        }
    }

    fun observeProductsList(): LiveData<List<Product>> = productsDetails

    fun observeProduct(): LiveData<Product> = productData

    fun observeReviews(): LiveData<MutableList<Review>> = productReviews

    fun observeReviewDetails(): LiveData<Review> = reviewDetails

    fun observeUiState(): LiveData<UIState> = uiState

    companion object {
        const val PRODUCT_URL = "http://192.168.1.149:3001/product/"
        const val REVIEWS_URL = "http://192.168.1.149:3002/reviews/"
    }
}
