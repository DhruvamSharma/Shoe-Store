package com.udacity.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.shared_view_models.LoginState
import com.udacity.shoestore.shared_view_models.UserViewModel

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }
    // shared user view model
    private val userViewModel: UserViewModel by activityViewModels()
    // saved state handle for managing user login state on back press
    private lateinit var savedStateHandle: SavedStateHandle
    // binding for current fragment
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginButton.setOnClickListener {
            login()
        }
        binding.signUpButton.setOnClickListener {
            login()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun login() {
        val emailId = binding.emailTextField.editText?.text.toString()
        val password = binding.passwordTextField.editText?.text.toString()
        // login
        userViewModel.login(emailId, password)
        // setup observer for login state
        userViewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                LoginState.EMPTY_EMAIL -> {
                    Snackbar.make(binding.loginButton, R.string.no_email_error, Snackbar.LENGTH_SHORT).show()
                }
                LoginState.EMPTY_PASSWORD -> {
                    Snackbar.make(binding.loginButton, R.string.no_password_error, Snackbar.LENGTH_SHORT).show()
                }
                LoginState.SUCCESS -> {
                    savedStateHandle[LOGIN_SUCCESSFUL] = true
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOnboardFragment())
                }
                else -> {
                    // do nothing
                }
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[LOGIN_SUCCESSFUL] = false
    }
}