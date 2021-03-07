package com.example.moviedatasource.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviedatasource.R
import com.example.moviedatasource.databinding.ActivityMainBinding
import com.example.moviedatasource.utils.animateHideDown
import com.example.moviedatasource.utils.animateHideUp
import com.example.moviedatasource.utils.animateShowDown
import com.example.moviedatasource.utils.animateShowUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.mainNavView.animateShowUp()
//                    binding.appBar.mainAppBar.animateHideUp()
                }
                R.id.movieDetailFragment -> {
                    binding.mainNavView.animateHideDown()
                    binding.appBar.mainAppBar.animateHideDown()
                }
                else -> {
                    binding.mainNavView.animateShowUp()
                    binding.appBar.mainAppBar.animateShowDown()
                }
            }
        }

        binding.mainNavView.setupWithNavController(navController)
    }
}