package com.udacity.shoestore.shared_view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class UserViewModel: ViewModel() {
    // user data
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    init {
        _user.value = null
        _loginState.value = LoginState.EMPTY
    }

    fun login(email: String, password: String) {
        if (email.isEmpty()) {
            _loginState.value = LoginState.EMPTY_EMAIL
        } else if (password.isEmpty()) {
            _loginState.value = LoginState.EMPTY_PASSWORD
        } else {
            _loginState.value = LoginState.SUCCESS
            _user.value = User(email = email, password = password)
        }
    }
    fun logOut() {
        _user.value = null
    }
}

enum class LoginState {
    EMPTY,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    SUCCESS,
}