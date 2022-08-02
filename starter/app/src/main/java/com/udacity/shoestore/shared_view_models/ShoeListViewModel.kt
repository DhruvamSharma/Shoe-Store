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

    private val _currentShoe = MutableLiveData<Shoe>()
    val currentShoe: LiveData<Shoe> get() = _currentShoe

    private val _shoeName =  MutableLiveData<String>()
    val shoeName: LiveData<String> get() = _shoeName

    init {
        _currentNumber.value = mutableListOf()
    }

    fun saveNewShoe() {
        if (currentShoe.value != null) {
            _currentNumber.value?.add(currentShoe.value!!)
        }
    }
}