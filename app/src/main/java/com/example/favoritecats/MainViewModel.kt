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

class MainViewModel(private val repository: RepositoryImpl) : ViewModel() {

    val list = MutableLiveData<List<CatData>>()

    val favouriteCatsList = MutableLiveData<List<FavouriteCatData>>()

    fun getCat() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRandomCat()
            list.postValue(response)
        }
    }

    fun like(imageId: String, value: Int, subId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.likeCat(imageId, value, subId)
        }
    }

    fun favouriteCats() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getFavouriteCats()
            favouriteCatsList.postValue(response)
        }
    }

    fun deleteFromFavouriteList(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromFavouriteList(id)
        }
    }

}