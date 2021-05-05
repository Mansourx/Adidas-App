package com.example.adidaschallenge.network

import java.io.IOException


/**
 * Created by Ahmad Mansour on 5/6/21
 * Dubai, UAE.
 */


class NoConnectionException : IOException() {
    companion object {
        const val NO_CONNECITON = "No network available. Please check your connection"
    }

    override val message: String
        get() = NO_CONNECITON
}