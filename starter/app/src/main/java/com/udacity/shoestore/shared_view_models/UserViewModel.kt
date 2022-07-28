package com.udacity.shoestore.shared_view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class UserViewModel: ViewModel() {
    // user data
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    init {
        _user.value = null
    }

    fun login() {
        _user.value = User(name = "Dhuvam", password = "tata, see you")
    }
    fun logOut() {
        _user.value = null
    }
}