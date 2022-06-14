package com.example.favoritecats.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favoritecats.data.room.RoomCatsRepository
import com.example.favoritecats.data.network.NetworkRepository

class MainViewModelFactory(private val networkRepository: NetworkRepository, private val roomRepository: RoomCatsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(networkRepository, roomRepository) as T
    }
}