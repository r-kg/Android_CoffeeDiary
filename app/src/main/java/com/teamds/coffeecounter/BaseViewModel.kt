package com.teamds.coffeecounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    protected fun showLoading(){
        _isLoading.value = true
    }

    protected fun hideLoading(){
        _isLoading.value = false
    }
}
