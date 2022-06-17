package com.example.favoritecats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.favoritecats.R
import com.example.favoritecats.databinding.ActivityMainBinding


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
        binding.apply {
            aboutBtn.setOnClickListener {
                clearStack()
                navController.navigate(R.id.aboutFragment)
            }
            bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.voteFragment -> {
                        clearStack()
                        navController.navigate(R.id.voteFragment)
                    }
                    R.id.favouritesCatsFragment -> {
                        clearStack()
                        navController.navigate(R.id.favouritesCatsFragment)
                    }
                }
                true
            }
        }
    }


    private fun clearStack() {
        navController.popBackStack(R.id.aboutFragment, true)
        navController.popBackStack(R.id.favouritesCatsFragment, true)
        navController.popBackStack(R.id.voteFragment, true)
    }
}