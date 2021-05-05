package com.example.adidaschallenge.network


/**
 * Created by Ahmad Mansour on 5/6/21
 * NAMSHI General Trading,
 * Dubai, UAE.
 */


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
     data class GenericError(val code: Int? = null, val error: String? = null) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}
