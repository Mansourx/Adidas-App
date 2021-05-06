package com.example.adidaschallenge.network

import retrofit2.Response


/**
 * Created by Ahmad Mansour on 5/6/21
 * Dubai, UAE.
 */


suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ResultWrapper<T> {
    return try {
        val response = apiCall.invoke()
        if (response.isSuccessful)
            ResultWrapper.Success(response.body()!!)
        else {
            
            ResultWrapper.GenericError(response.code(), "serverError")
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is NoConnectionException -> ResultWrapper.NetworkError
            else -> {
                ResultWrapper.GenericError(null, null)
            }
        }
    }
}
