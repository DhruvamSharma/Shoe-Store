package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.databinding.FragmentOnboardBinding

/**
 * A simple [Fragment] subclass.
 */
class InstructionsFragment : Fragment() {
    companion object {
        const val INSTRUCTIONS_SUCCESSFUL: String = "INSTRUCTIONS_SUCCESSFUL"
    }
    // saved state handle for managing user login state on back press
    private lateinit var savedStateHandle: SavedStateHandle
    // binding for Onboard Fragment
    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        binding.finishInstructionsButton.setOnClickListener {
            savedStateHandle[INSTRUCTIONS_SUCCESSFUL] = true
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle[INSTRUCTIONS_SUCCESSFUL] = false
    }
}