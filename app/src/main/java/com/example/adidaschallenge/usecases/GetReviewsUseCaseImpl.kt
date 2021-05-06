package com.example.adidaschallenge.usecases

import com.example.adidaschallenge.repositories.ProductRepositoryImpl


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


class GetReviewsUseCaseImpl : GetReviewsUseCase {

    private val repository: ProductRepositoryImpl
        get() = ProductRepositoryImpl.instance!!

    override suspend fun invoke(url: String) = repository.getReviews(url)

}