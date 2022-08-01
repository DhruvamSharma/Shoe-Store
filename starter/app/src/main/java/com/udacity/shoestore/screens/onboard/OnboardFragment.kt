package com.udacity.shoestore.screens.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnboardBinding
import com.udacity.shoestore.screens.login.LoginFragment

/**
 * A simple [Fragment] subclass.
 */
class OnboardFragment : Fragment() {
    companion object {
        const val ONBOARD_SUCCESSFUL: String = "ONBOARD_SUCCESSFUL"
    }
    // saved state handle for managing user login state on back press
    private lateinit var savedStateHandle: SavedStateHandle
    // binding for Onboard Fragment
    private lateinit var binding: FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard, container, false)
        binding.finishOnBoardButton.setOnClickListener {
            savedStateHandle[ONBOARD_SUCCESSFUL] = true
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[ONBOARD_SUCCESSFUL] = false
    }
}