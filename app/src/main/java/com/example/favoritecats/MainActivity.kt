package com.example.favoritecats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.favoritecats.ui.VoteFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.container_view, VoteFragment()).commit()

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