package com.example.favoritecats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.favoritecats.ui.FavouritesCatsFragment
import com.example.favoritecats.ui.VoteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenu

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launchFragment(VoteFragment())

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.voteFragment -> {
                    launchFragment(VoteFragment())
                }

                R.id.favouritesCatsFragment -> {
                    launchFragment(FavouritesCatsFragment())
                }
            }
            true
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_view, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}