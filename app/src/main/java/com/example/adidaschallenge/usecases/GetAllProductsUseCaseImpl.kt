package com.example.adidaschallenge.usecases

import com.example.adidaschallenge.repositories.ProductRepositoryImpl


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


class GetAllProductsUseCaseImpl : GetAllProductsUseCase {

    private val repository: ProductRepositoryImpl?
        get() = ProductRepositoryImpl.instance

    override suspend fun invoke(url: String) = repository?.getProducts(url)

}