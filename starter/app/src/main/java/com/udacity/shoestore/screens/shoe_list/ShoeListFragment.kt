package com.udacity.shoestore.screens.shoe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.AppViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: AppViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        viewModel.currentNumber.observe(viewLifecycleOwner) {
            Timber.i("list is $it -- and child count is ${binding.listView.childCount}")
            val layout = View.inflate(context, R.layout.shoe_item, null) as ConstraintLayout
            val textView = layout.getViewById(R.id.shoeTitle) as TextView
            textView.text = it.last().toString()
            textView.layoutParams = binding.listView.layoutParams
            binding.listView.addView(layout)
            Timber.i("child count is ${binding.listView.childCount}")
        }

        return binding.root
    }
}