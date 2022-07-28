package com.udacity.shoestore.shared_view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class ShoeListViewModel: ViewModel() {

    // shoe list data
    private val _currentNumber = MutableLiveData<MutableList<Int>>()
    val currentNumber: LiveData<MutableList<Int>> get() = _currentNumber

    init {
        _currentNumber.value = mutableListOf()
    }

    fun setCurrentNumber(value: Int) {
        _currentNumber.value?.add(value)
    }
}