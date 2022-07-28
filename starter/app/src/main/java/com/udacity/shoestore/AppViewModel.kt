package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {
    private val _currentNumber = MutableLiveData<MutableList<Int>>()
    val currentNumber: LiveData<MutableList<Int>> get() = _currentNumber

    fun setCurrentNumber(value: Int) {
        if(_currentNumber.value == null) {
            _currentNumber.value = mutableListOf()
        }
        _currentNumber.value?.add(value)
    }
}