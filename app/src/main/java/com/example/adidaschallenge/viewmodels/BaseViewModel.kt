package com.example.adidaschallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.adidaschallenge.network.UIState


/**
 * Created by Ahmad Mansour on 5/6/21
 * Dubai, UAE.
 */


open class BaseViewModel : ViewModel() {
    internal val uiState: MutableLiveData<UIState> = MutableLiveData()
    internal fun uiState(): LiveData<UIState> = uiState
}