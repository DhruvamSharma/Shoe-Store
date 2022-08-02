package com.udacity.shoestore.screens.shoe_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.shared_view_models.ShoeListViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.shared_view_models.SaveEvent

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

        viewModel.onSaveEvent.observe(viewLifecycleOwner) {
            onSave(it)
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun onSave(state: SaveEvent) {
        when (state) {
            SaveEvent.EMPTY_NAME -> {
                Snackbar.make(binding.saveButton, resources.getString(R.string.empty_name), Snackbar.LENGTH_SHORT).show()
                viewModel.onErrorDone()
            }
            SaveEvent.EMPTY_COMPANY_NAME -> {
                Snackbar.make(binding.saveButton, resources.getString(R.string.empty_company_name), Snackbar.LENGTH_SHORT).show()
                viewModel.onErrorDone()
            }
            SaveEvent.EMPTY_SIZE -> {
                Snackbar.make(binding.saveButton, resources.getString(R.string.empty_size), Snackbar.LENGTH_SHORT).show()
                viewModel.onErrorDone()
            }
            SaveEvent.EMPTY_DESCRIPTION -> {
                Snackbar.make(binding.saveButton, resources.getString(R.string.empty_description), Snackbar.LENGTH_SHORT).show()
                viewModel.onErrorDone()
            }
            SaveEvent.SUCCESS -> {
                // pop the route
                findNavController().navigateUp()
            }
            else -> {
                // do nothing
            }
        }
    }
}