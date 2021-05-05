package com.example.adidaschallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Ahmad Mansour on 5/5/21
 * NAMSHI General Trading,
 * Dubai, UAE.
 */


object RetrofitService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.149:3001/product/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }
}