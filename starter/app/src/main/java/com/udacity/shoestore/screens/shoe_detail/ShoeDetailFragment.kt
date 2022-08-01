package com.udacity.shoestore.screens.shoe_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.shared_view_models.ShoeListViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeSize
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    private val viewModel: ShoeListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.setCurrentNumber(Shoe(name = "Air Trapper", size = ShoeSize.M, description = "Hello BABAY", company = "Nike"))
        return inflater.inflate(R.layout.fragment_shoe_detail, container, false)
    }
}