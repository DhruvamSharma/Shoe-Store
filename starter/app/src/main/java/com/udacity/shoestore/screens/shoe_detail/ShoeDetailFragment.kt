package com.udacity.shoestore.screens.shoe_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.AppViewModel
import com.udacity.shoestore.R
import timber.log.Timber
import java.util.RandomAccess
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.setCurrentNumber(Random.nextInt(until = 20))
        return inflater.inflate(R.layout.fragment_shoe_detail, container, false)
    }
}