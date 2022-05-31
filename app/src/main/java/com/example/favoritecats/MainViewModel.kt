package com.example.favoritecats

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoritecats.model.CatData
import com.example.favoritecats.model.FavouriteCatData
import com.example.favoritecats.model.ImageCat
import com.example.favoritecats.network.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RepositoryImpl): ViewModel() {


    private var result = mutableListOf<CatData>()

    val list = MutableLiveData<List<CatData>>()

    val favouriteCatsList = MutableLiveData<List<FavouriteCatData>>()

    val imagesCats = MutableLiveData<ImageCat>()

    fun getCat(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRandomCat()
            list.postValue(response)
            Log.i("Response_ViewModel", list.toString())
        }
    }

    fun like(imageId: String, value: Int, subId: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.likeCat(imageId, value, subId)
        }
    }

    fun favouriteCats(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFavouriteCats()
            favouriteCatsList.postValue(response)
            Log.i("Response_ViewModel", favouriteCatsList.toString())
        }
    }

    fun setImageFromId(imageId: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getImageFromId(imageId)
            imagesCats.postValue(response)
            Log.i("Response_Cats", imagesCats.toString())
        }
    }
    fun deleteFromFavouriteList(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavouriteList(id)
        }
    }

    /*
    suspend fun getRandomCat(): List<CatData>{
        viewModelScope.launch(Dispatchers.IO) {
            result = repository.getRandomCat() as MutableList<CatData>
        withContext(Dispatchers.Main){
            list.postValue(result)
            Log.i("TEXT_TEST", result.toString())
        }

        }
        return result.toList()
    }

    fun getCat(){
        viewModelScope.launch {
        val cat = getRandomCat()
            list.postValue(cat)
        }
    }

     */


}