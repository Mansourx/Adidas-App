package com.example.adidaschallenge.usecases

import com.example.adidaschallenge.models.Review
import com.example.adidaschallenge.network.ResultWrapper


/**
 * Created by Ahmad Mansour on 5/8/21
 * Dubai, UAE.
 */


interface AddReviewUseCase {

    suspend fun invoke(url: String, review: Review): ResultWrapper<Review>?

}