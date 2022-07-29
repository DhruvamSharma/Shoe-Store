package com.udacity.shoestore.screens.shoe_list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.shared_view_models.ShoeListViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
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

        userViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null) {
                // move to login fragment
                navController.navigate(R.id.loginFragment)
            }
        }

        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle

        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
                if (!success) {
                    activity?.finish()
                }
            }

        savedStateHandle.getLiveData<Boolean>(OnboardFragment.ONBOARD_SUCCESSFUL)
            .observe(currentBackStackEntry) { success ->
                if (!success) {
                    activity?.finish()
                }
            }


        shoeListViewModel.currentNumber.observe(viewLifecycleOwner) { shoeList ->
            shoeList.forEach { item ->
                val layout = View.inflate(context, R.layout.shoe_item, null) as ConstraintLayout
                val textView = layout.getViewById(R.id.shoeTitle) as TextView
                textView.text = item.toString()
                textView.layoutParams = binding.listView.layoutParams
                binding.listView.addView(layout)
            }
        }

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