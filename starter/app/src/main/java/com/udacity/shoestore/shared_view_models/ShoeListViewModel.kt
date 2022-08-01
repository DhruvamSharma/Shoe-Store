package com.udacity.shoestore.shared_view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class ShoeListViewModel: ViewModel() {

    // shoe list data
    private val _currentNumber = MutableLiveData<MutableList<Shoe>>()
    val currentNumber: LiveData<MutableList<Shoe>> get() = _currentNumber

    init {
        _currentNumber.value = mutableListOf()
    }

    fun setCurrentNumber(newShoe: Shoe) {
        _currentNumber.value?.add(newShoe)
    }
}