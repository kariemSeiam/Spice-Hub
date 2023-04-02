package com.pigo.spicehub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.pigo.spicehub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup navigation
        setupNavigation()
    }

    private fun setupNavigation() {

        // Get NavHostFragment
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Get NavController
        navController = navHostFragment.navController

        // Setup bottom navigation with NavController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }
}