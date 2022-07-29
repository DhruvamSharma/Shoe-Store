package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment)
        navController = navHostFragment.navController

        // helps in setting up back button
        // and hiding back button on login fragment
        NavigationUI.setupActionBarWithNavController(this, navController, AppBarConfiguration(topLevelDestinationIds = setOf(
            R.id.shoeListFragment,
            R.id.loginFragment,
        )))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
