package com.example.favoritecats.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.favoritecats.data.network.CatData
import com.example.favoritecats.data.network.FavouriteCatData
import com.example.favoritecats.data.network.NetworkRepository
import com.example.favoritecats.data.room.RoomCatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkRepository: NetworkRepository,
    private val roomRepository: RoomCatsRepository
) : ViewModel() {

    val randomCats: MutableLiveData<List<CatData>> by lazy {
        MutableLiveData<List<CatData>>().also {
            randomCat()
        }
    }

    val favouriteCatsList = MutableLiveData<List<FavouriteCatData>>()

    var dataList: List<FavouriteCatData>? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataList = roomRepository.getAllCats()
            storageType()
        }
    }

    fun randomCat() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkRepository.getRandomCat()
            randomCats.postValue(response)
        }
    }

    fun likeCat(imageId: String, value: Int, subId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.likeCat(imageId, value, subId)
            refreshListOfFavoriteCats()
        }
    }

    private fun listOfFavoriteCats() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkRepository.getFavouriteCats()
            roomRepository.insertAll(response)
            favouriteCatsList.postValue(response)
        }
    }

    private fun readDataFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            dataList = roomRepository.getAllCats()
            favouriteCatsList.postValue(dataList)
        }
    }

    fun refreshListOfFavoriteCats() {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.insertAll(networkRepository.getFavouriteCats())
            val response = roomRepository.getAllCats()
            favouriteCatsList.postValue(response)
        }
    }

    fun deleteFromFavouriteList(id: Int, uid: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.deleteFromFavouriteList(id)
            roomRepository.deleteCatFromDb(uid)
            refreshListOfFavoriteCats()
        }
    }

    private fun storageType() {
        val result = if (dataList?.isEmpty() == true) {
            REMOTE_STORAGE
        } else {
            LOCAL_STORAGE
        }
        when (result) {
            LOCAL_STORAGE -> readDataFromDatabase()
            REMOTE_STORAGE -> listOfFavoriteCats()
        }
    }

    companion object {
        const val LOCAL_STORAGE = 0
        const val REMOTE_STORAGE = 1
    }

}