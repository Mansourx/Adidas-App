package com.example.adidaschallenge.network


/**
 * Created by Ahmad Mansour on 5/6/21
 * Dubai, UAE.
 */


sealed class UIState {
    object Loading : UIState()
    object Completed : UIState()
    object NetworkError : UIState()
    object Unauthorised : UIState()
    class GenericError(val errorMsg: String = "") : UIState()
}
