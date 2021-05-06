package com.example.adidaschallenge.usecases

import com.example.adidaschallenge.models.Product
import com.example.adidaschallenge.network.ResultWrapper


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


interface GetProductUseCase {

    suspend fun invoke(url: String): ResultWrapper<Product?>?

}