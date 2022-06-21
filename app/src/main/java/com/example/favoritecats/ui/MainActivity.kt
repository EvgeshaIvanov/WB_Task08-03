package com.example.favoritecats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.favoritecats.R
import com.example.favoritecats.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.aboutBtn.setOnClickListener {
            navController.popBackStack(R.id.aboutFragment, true)
            navController.navigate(R.id.aboutFragment)

        }
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.voteFragment -> {
                    navController.popBackStack(R.id.voteFragment, true)
                    navController.navigate(R.id.voteFragment)
                }

                R.id.favouritesCatsFragment -> {
                    navController.popBackStack(R.id.favouritesCatsFragment, true)
                    navController.navigate(R.id.favouritesCatsFragment)
                }
            }
            true
        }
    }
}