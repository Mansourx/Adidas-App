package com.example.adidaschallenge.usecases

import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.repositories.ProductRepositoryImpl


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


class AddReviewUseCaseImpl : AddReviewUseCase {

    private val repository: ProductRepositoryImpl
        get() = ProductRepositoryImpl.instance!!

    override suspend fun invoke(url: String, review: Review) = repository.addReview(url, review)

}