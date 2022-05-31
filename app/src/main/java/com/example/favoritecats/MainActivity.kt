package com.example.favoritecats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.favoritecats.ui.FavouritesCatsFragment
import com.example.favoritecats.ui.VoteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenu

class MainActivity : AppCompatActivity() {

    private lateinit var voteFragment: VoteFragment

    private lateinit var favouritesCatsFragment: FavouritesCatsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        voteFragment = VoteFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_view, voteFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.voteFragment -> {
                    voteFragment = VoteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_view, voteFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.favouritesCatsFragment -> {
                    favouritesCatsFragment = FavouritesCatsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_view, favouritesCatsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }


    }

/*
  @OptIn(DelicateCoroutinesApi::class)
    private fun makeRequest() {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            val cat: List<CatData> = client.get("https://api.thecatapi.com/v1/images/search"){
                header("x-api-key", "b8ba2d27-bdee-4f70-bcf5-339a73a5e1fd")
            }.body()
            cat.forEach {
                Log.i("SEARCH_LIST", "${it.id}, ${it.url}")
            }
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun getFavouriteCats() {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

        }
        GlobalScope.launch(Dispatchers.IO) {

            val cat: List<FavouriteCatData> = client.get("https://api.thecatapi.com/v1/favourites"){
                header("x-api-key", "b8ba2d27-bdee-4f70-bcf5-339a73a5e1fd")
            }.body()
            Log.i("FAV_LIST", cat.toString())
        }
    }
 */

}