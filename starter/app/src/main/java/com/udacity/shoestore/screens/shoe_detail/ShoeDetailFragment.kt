package com.udacity.shoestore.screens.shoe_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.shared_view_models.ShoeListViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeSize
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    // common view model
    private val viewModel: ShoeListViewModel by activityViewModels()
    // binding for current fragment
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.saveButton.setOnClickListener {
            Snackbar.make(binding.saveButton, "Shoe Name is : ${viewModel.shoeName.value}", Snackbar.LENGTH_SHORT).show()
        }
        return binding.root
    }
}