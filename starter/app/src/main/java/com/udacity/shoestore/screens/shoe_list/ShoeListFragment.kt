package com.udacity.shoestore.screens.shoe_list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.imageview.ShapeableImageView
import com.udacity.shoestore.shared_view_models.ShoeListViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.screens.instructions.InstructionsFragment
import com.udacity.shoestore.screens.login.LoginFragment
import com.udacity.shoestore.screens.onboard.OnboardFragment
import com.udacity.shoestore.shared_view_models.UserViewModel


/**
 * A simple [Fragment] subclass.
 */
class ShoeListFragment : Fragment(), MenuProvider {
    // binding for current fragment
    private lateinit var binding: FragmentShoeListBinding

    // view model for shoe list
    private val shoeListViewModel: ShoeListViewModel by activityViewModels()

    // view model for user
    private val userViewModel: UserViewModel by activityViewModels()

    // nav controller
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        navController = findNavController()
        binding.addShoeButton.setOnClickListener {
            navController.navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        // observe the user state, if user is null, send to login fragment
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null) {
                // move to login fragment
                navController.navigate(R.id.loginFragment)
            }
        }

        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle

        // setting up on back click from Login if not done
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
                if (!success) {
                    activity?.finish()
                }
            }
        // setting up on back click from Onboarding
        savedStateHandle.getLiveData<Boolean>(OnboardFragment.ONBOARD_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
                if (!success) {
                    activity?.finish()
                }
            }

        // setting up on back click from Instructions
        savedStateHandle.getLiveData<Boolean>(InstructionsFragment.INSTRUCTIONS_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
                if (!success) {
                    activity?.finish()
                }
            }


        // render the list items for the shoes
        shoeListViewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            shoeList.forEach { item ->
                val layout = View.inflate(context, R.layout.shoe_item, null) as CardView
                val shoeItemLayout = layout[0] as ConstraintLayout

                // shoe title
                val titleTextView = shoeItemLayout.getViewById(R.id.shoeTitle) as TextView
                titleTextView.text = item.name

                // shoe company
                val companyTextView = shoeItemLayout.getViewById(R.id.shoeCompany) as TextView
                companyTextView.text = item.company

                // shoe size
                val sizeTextView = shoeItemLayout.getViewById(R.id.shoeSize) as TextView
                sizeTextView.text = item.size

                // shoe company
                val descriptionTextView = shoeItemLayout.getViewById(R.id.shoeDescription) as TextView
                descriptionTextView.text = item.description

                // shoe image
                val shoeImageView = shoeItemLayout.getViewById(R.id.shoeImage) as ShapeableImageView
                shoeImageView.setImageResource(R.drawable.brand_logo)

                layout.elevation = 5F
                binding.listView.addView(layout)
            }
            // when the list changes, we want to reset the data, so that
            // when user goes back to the detail fragment, the data is empty
            shoeListViewModel.onSaveReset()
        }

        // setting up menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.loginFragment -> {
                userViewModel.logOut()
                return NavigationUI.onNavDestinationSelected(menuItem, navController)
            }
        }
        return super.onContextItemSelected(menuItem)
    }
}